package com.coremedia.iso;

import java.nio.ByteBuffer;

public final class IsoTypeReaderVariable {
    public IsoTypeReaderVariable() {
        super();
    }

    public static long read(ByteBuffer arg2, int arg3) {
        if(arg3 == 8) {
            goto label_22;
        }

        switch(arg3) {
            case 1: {
                goto label_20;
            }
            case 2: {
                goto label_18;
            }
            case 3: {
                goto label_15;
            }
            case 4: {
                goto label_13;
            }
        }

        StringBuilder v0 = new StringBuilder("I don\'t know how to read ");
        v0.append(arg3);
        v0.append(" bytes");
        throw new RuntimeException(v0.toString());
    label_18:
        int v2 = IsoTypeReader.readUInt16(arg2);
        goto label_16;
    label_20:
        v2 = IsoTypeReader.readUInt8(arg2);
        goto label_16;
    label_13:
        return IsoTypeReader.readUInt32(arg2);
    label_15:
        v2 = IsoTypeReader.readUInt24(arg2);
    label_16:
        return ((long)v2);
    label_22:
        return IsoTypeReader.readUInt64(arg2);
    }
}

