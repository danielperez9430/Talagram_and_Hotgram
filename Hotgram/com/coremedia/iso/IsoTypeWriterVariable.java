package com.coremedia.iso;

import java.nio.ByteBuffer;

public final class IsoTypeWriterVariable {
    public IsoTypeWriterVariable() {
        super();
    }

    public static void write(long arg2, ByteBuffer arg4, int arg5) {
        if(arg5 != 8) {
            switch(arg5) {
                case 1: {
                    goto label_25;
                }
                case 2: {
                    goto label_20;
                }
                case 3: {
                    goto label_15;
                }
                case 4: {
                    goto label_13;
                }
            }

            StringBuilder v3 = new StringBuilder("I don\'t know how to read ");
            v3.append(arg5);
            v3.append(" bytes");
            throw new RuntimeException(v3.toString());
        label_20:
            IsoTypeWriter.writeUInt16(arg4, ((int)(arg2 & 65535)));
            return;
        label_25:
            IsoTypeWriter.writeUInt8(arg4, ((int)(arg2 & 255)));
            return;
        label_13:
            IsoTypeWriter.writeUInt32(arg4, arg2);
            return;
        label_15:
            IsoTypeWriter.writeUInt24(arg4, ((int)(arg2 & 16777215)));
        }
        else {
            IsoTypeWriter.writeUInt64(arg4, arg2);
        }
    }
}

