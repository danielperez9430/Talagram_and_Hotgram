package com.google.android.exoplayer2.extractor.flv;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

final class ScriptTagPayloadReader extends TagPayloadReader {
    private static final int AMF_TYPE_BOOLEAN = 1;
    private static final int AMF_TYPE_DATE = 11;
    private static final int AMF_TYPE_ECMA_ARRAY = 8;
    private static final int AMF_TYPE_END_MARKER = 9;
    private static final int AMF_TYPE_NUMBER = 0;
    private static final int AMF_TYPE_OBJECT = 3;
    private static final int AMF_TYPE_STRICT_ARRAY = 10;
    private static final int AMF_TYPE_STRING = 2;
    private static final String KEY_DURATION = "duration";
    private static final String NAME_METADATA = "onMetaData";
    private long durationUs;

    public ScriptTagPayloadReader() {
        super(null);
        this.durationUs = -9223372036854775807L;
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    protected boolean parseHeader(ParsableByteArray arg1) {
        return 1;
    }

    protected void parsePayload(ParsableByteArray arg3, long arg4) {
        if(ScriptTagPayloadReader.readAmfType(arg3) == 2) {
            if(!"onMetaData".equals(ScriptTagPayloadReader.readAmfString(arg3))) {
                return;
            }

            if(ScriptTagPayloadReader.readAmfType(arg3) != 8) {
                return;
            }

            HashMap v3 = ScriptTagPayloadReader.readAmfEcmaArray(arg3);
            if(((Map)v3).containsKey("duration")) {
                double v3_1 = ((Map)v3).get("duration").doubleValue();
                if(v3_1 > 0) {
                    this.durationUs = ((long)(v3_1 * 1000000));
                }
            }

            return;
        }

        throw new ParserException();
    }

    private static Boolean readAmfBoolean(ParsableByteArray arg1) {
        boolean v0 = true;
        if(arg1.readUnsignedByte() == 1) {
        }
        else {
            v0 = false;
        }

        return Boolean.valueOf(v0);
    }

    private static Object readAmfData(ParsableByteArray arg1, int arg2) {
        if(arg2 == 8) {
            goto label_18;
        }

        switch(arg2) {
            case 0: {
                goto label_16;
            }
            case 1: {
                goto label_14;
            }
            case 2: {
                goto label_12;
            }
            case 3: {
                goto label_10;
            }
        }

        switch(arg2) {
            case 10: {
                goto label_8;
            }
            case 11: {
                goto label_6;
            }
        }

        return null;
    label_6:
        return ScriptTagPayloadReader.readAmfDate(arg1);
    label_8:
        return ScriptTagPayloadReader.readAmfStrictArray(arg1);
    label_10:
        return ScriptTagPayloadReader.readAmfObject(arg1);
    label_12:
        return ScriptTagPayloadReader.readAmfString(arg1);
    label_14:
        return ScriptTagPayloadReader.readAmfBoolean(arg1);
    label_16:
        return ScriptTagPayloadReader.readAmfDouble(arg1);
    label_18:
        return ScriptTagPayloadReader.readAmfEcmaArray(arg1);
    }

    private static Date readAmfDate(ParsableByteArray arg3) {
        Date v0 = new Date(((long)ScriptTagPayloadReader.readAmfDouble(arg3).doubleValue()));
        arg3.skipBytes(2);
        return v0;
    }

    private static Double readAmfDouble(ParsableByteArray arg2) {
        return Double.valueOf(Double.longBitsToDouble(arg2.readLong()));
    }

    private static HashMap readAmfEcmaArray(ParsableByteArray arg5) {
        int v0 = arg5.readUnsignedIntToInt();
        HashMap v1 = new HashMap(v0);
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1.put(ScriptTagPayloadReader.readAmfString(arg5), ScriptTagPayloadReader.readAmfData(arg5, ScriptTagPayloadReader.readAmfType(arg5)));
        }

        return v1;
    }

    private static HashMap readAmfObject(ParsableByteArray arg4) {
        HashMap v0 = new HashMap();
        while(true) {
            String v1 = ScriptTagPayloadReader.readAmfString(arg4);
            int v2 = ScriptTagPayloadReader.readAmfType(arg4);
            if(v2 == 9) {
                return v0;
            }

            v0.put(v1, ScriptTagPayloadReader.readAmfData(arg4, v2));
        }

        return v0;
    }

    private static ArrayList readAmfStrictArray(ParsableByteArray arg4) {
        int v0 = arg4.readUnsignedIntToInt();
        ArrayList v1 = new ArrayList(v0);
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1.add(ScriptTagPayloadReader.readAmfData(arg4, ScriptTagPayloadReader.readAmfType(arg4)));
        }

        return v1;
    }

    private static String readAmfString(ParsableByteArray arg3) {
        int v0 = arg3.readUnsignedShort();
        int v1 = arg3.getPosition();
        arg3.skipBytes(v0);
        return new String(arg3.data, v1, v0);
    }

    private static int readAmfType(ParsableByteArray arg0) {
        return arg0.readUnsignedByte();
    }

    public void seek() {
    }
}

