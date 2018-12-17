package android.support.e;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.zip.CRC32;
import java.util.zip.ZipException;

final class c {
    class a {
        long a;
        long b;

        a() {
            super();
        }
    }

    static long a(File arg3) {
        long v1;
        RandomAccessFile v0 = new RandomAccessFile(arg3, "r");
        try {
            v1 = c.a(v0, c.a(v0));
        }
        catch(Throwable v3) {
            v0.close();
            throw v3;
        }

        v0.close();
        return v1;
    }

    static long a(RandomAccessFile arg9, a arg10) {
        CRC32 v0 = new CRC32();
        long v1 = arg10.b;
        arg9.seek(arg10.a);
        long v3 = 16384;
        int v10 = ((int)Math.min(v3, v1));
        byte[] v5 = new byte[16384];
        while(true) {
            v10 = arg9.read(v5, 0, v10);
            if(v10 != -1) {
                v0.update(v5, 0, v10);
                v1 -= ((long)v10);
                if(v1 == 0) {
                }
                else {
                    v10 = ((int)Math.min(v3, v1));
                    continue;
                }
            }

            break;
        }

        return v0.getValue();
    }

    static a a(RandomAccessFile arg7) {
        long v0 = arg7.length() - 22;
        long v2 = 0;
        if(v0 < v2) {
            goto label_42;
        }

        long v4 = v0 - 65536;
        if(v4 < v2) {
        }
        else {
            v2 = v4;
        }

        int v4_1 = Integer.reverseBytes(101010256);
        while(true) {
            arg7.seek(v0);
            if(arg7.readInt() == v4_1) {
                goto label_15;
            }

            --v0;
            if(v0 < v2) {
                break;
            }
        }

        throw new ZipException("End Of Central Directory signature not found");
    label_15:
        arg7.skipBytes(2);
        arg7.skipBytes(2);
        arg7.skipBytes(2);
        arg7.skipBytes(2);
        a v0_1 = new a();
        v0_1.b = (((long)Integer.reverseBytes(arg7.readInt()))) & 4294967295L;
        v0_1.a = (((long)Integer.reverseBytes(arg7.readInt()))) & 4294967295L;
        return v0_1;
    label_42:
        StringBuilder v1 = new StringBuilder();
        v1.append("File too short to be a zip file: ");
        v1.append(arg7.length());
        throw new ZipException(v1.toString());
    }
}

