package org.telegram.customization.compression.lz4;

import java.io.InputStream;
import java.io.OutputStream;

public class LZ4StreamHelper {
    public LZ4StreamHelper() {
        super();
    }

    static int readLength(InputStream arg4) {
        int v0 = arg4.read();
        int v1 = arg4.read();
        int v2 = arg4.read();
        int v4 = arg4.read();
        int v3 = -1;
        if(v3 != v0 && v3 != v1 && v3 != v2) {
            if(v3 == v4) {
            }
            else {
                v3 = v0 << 24 | v1 << 16 | v2 << 8 | v4;
            }
        }

        return v3;
    }

    static void writeLength(int arg4, OutputStream arg5) {
        arg5.write((-16777216 & arg4) >> 24);
        arg5.write((16711680 & arg4) >> 16);
        arg5.write((65280 & arg4) >> 8);
        arg5.write(arg4 & -16776961);
    }
}

