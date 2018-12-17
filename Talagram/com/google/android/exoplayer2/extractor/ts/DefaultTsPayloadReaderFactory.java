package com.google.android.exoplayer2.extractor.ts;

import android.util.SparseArray;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.text.cea.Cea708InitializationData;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DefaultTsPayloadReaderFactory implements Factory {
    @Retention(value=RetentionPolicy.SOURCE) @public interface Flags {
    }

    private static final int DESCRIPTOR_TAG_CAPTION_SERVICE = 134;
    public static final int FLAG_ALLOW_NON_IDR_KEYFRAMES = 1;
    public static final int FLAG_DETECT_ACCESS_UNITS = 8;
    public static final int FLAG_IGNORE_AAC_STREAM = 2;
    public static final int FLAG_IGNORE_H264_STREAM = 4;
    public static final int FLAG_IGNORE_SPLICE_INFO_STREAM = 16;
    public static final int FLAG_OVERRIDE_CAPTION_DESCRIPTORS = 32;
    private final List closedCaptionFormats;
    private final int flags;

    public DefaultTsPayloadReaderFactory() {
        this(0);
    }

    public DefaultTsPayloadReaderFactory(int arg4) {
        this(arg4, Collections.singletonList(Format.createTextSampleFormat(null, "application/cea-608", 0, null)));
    }

    public DefaultTsPayloadReaderFactory(int arg1, List arg2) {
        super();
        this.flags = arg1;
        this.closedCaptionFormats = arg2;
    }

    private SeiReader buildSeiReader(EsInfo arg2) {
        return new SeiReader(this.getClosedCaptionFormats(arg2));
    }

    private UserDataReader buildUserDataReader(EsInfo arg2) {
        return new UserDataReader(this.getClosedCaptionFormats(arg2));
    }

    public SparseArray createInitialPayloadReaders() {
        return new SparseArray();
    }

    public TsPayloadReader createPayloadReader(int arg4, EsInfo arg5) {
        PesReader v1_1;
        int v0 = 2;
        TsPayloadReader v1 = null;
        switch(arg4) {
            case 2: {
                goto label_81;
            }
            case 3: 
            case 4: {
                goto label_75;
            }
            case 15: {
                goto label_65;
            }
            case 17: {
                goto label_56;
            }
            case 21: {
                goto label_51;
            }
            case 27: {
                goto label_37;
            }
            case 36: {
                goto label_31;
            }
            case 89: {
                goto label_25;
            }
            case 134: {
                goto label_4;
            }
            case 129: 
            case 135: {
                goto label_19;
            }
            case 130: 
            case 138: {
                goto label_13;
            }
        }

        return v1;
    label_65:
        if(this.isSet(v0)) {
        }
        else {
            v1_1 = new PesReader(new AdtsReader(false, arg5.language));
        }

        return ((TsPayloadReader)v1_1);
    label_4:
        if(this.isSet(16)) {
        }
        else {
            SectionReader v1_2 = new SectionReader(new SpliceInfoSectionReader());
        }

        return v1;
    label_37:
        if(this.isSet(4)) {
        }
        else {
            v1_1 = new PesReader(new H264Reader(this.buildSeiReader(arg5), this.isSet(1), this.isSet(8)));
        }

        return ((TsPayloadReader)v1_1);
    label_75:
        return new PesReader(new MpegAudioReader(arg5.language));
    label_13:
        return new PesReader(new DtsReader(arg5.language));
    label_81:
        return new PesReader(new H262Reader(this.buildUserDataReader(arg5)));
    label_19:
        return new PesReader(new Ac3Reader(arg5.language));
    label_51:
        return new PesReader(new Id3Reader());
    label_56:
        if(this.isSet(v0)) {
        }
        else {
            v1_1 = new PesReader(new LatmReader(arg5.language));
        }

        return ((TsPayloadReader)v1_1);
    label_25:
        return new PesReader(new DvbSubtitleReader(arg5.dvbSubtitleInfos));
    label_31:
        return new PesReader(new H265Reader(this.buildSeiReader(arg5)));
    }

    private List getClosedCaptionFormats(EsInfo arg20) {
        List v18;
        String v7_1;
        int v14;
        DefaultTsPayloadReaderFactory v0 = this;
        if(v0.isSet(32)) {
            return v0.closedCaptionFormats;
        }

        ParsableByteArray v1 = new ParsableByteArray(arg20.descriptorBytes);
        List v2 = v0.closedCaptionFormats;
        while(v1.bytesLeft() > 0) {
            int v3 = v1.readUnsignedByte();
            int v5 = v1.getPosition() + v1.readUnsignedByte();
            if(v3 == 134) {
                ArrayList v2_1 = new ArrayList();
                v3 = v1.readUnsignedByte() & 31;
                int v6;
                for(v6 = 0; v6 < v3; ++v6) {
                    String v13 = v1.readString(3);
                    int v7 = v1.readUnsignedByte();
                    boolean v9 = true;
                    int v8 = (v7 & 128) != 0 ? 1 : 0;
                    if(v8 != 0) {
                        v14 = v7 & 63;
                        v7_1 = "application/cea-708";
                    }
                    else {
                        v7_1 = "application/cea-608";
                        v14 = 1;
                    }

                    byte v10 = ((byte)v1.readUnsignedByte());
                    v1.skipBytes(1);
                    List v11 = null;
                    if(v8 != 0) {
                        if((v10 & 64) != 0) {
                        }
                        else {
                            v9 = false;
                        }

                        v18 = Cea708InitializationData.buildData(v9);
                    }
                    else {
                        v18 = v11;
                    }

                    ((List)v2_1).add(Format.createTextSampleFormat(null, v7_1, null, -1, 0, v13, v14, null, 9223372036854775807L, v18));
                }
            }

            v1.setPosition(v5);
        }

        return v2;
    }

    private boolean isSet(int arg2) {
        boolean v2 = (arg2 & this.flags) != 0 ? true : false;
        return v2;
    }
}

