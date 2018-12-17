package okhttp3.internal.e;

import e.f;
import java.io.IOException;
import okhttp3.internal.c;

public final class e {
    static final f a;
    static final String[] b;
    static final String[] c;
    private static final String[] d;

    static {
        int v8;
        char v7;
        e.a = f.a("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
        String[] v0 = new String[10];
        int v2 = 0;
        v0[0] = "DATA";
        v0[1] = "HEADERS";
        v0[2] = "PRIORITY";
        int v4 = 3;
        v0[v4] = "RST_STREAM";
        int v5 = 4;
        v0[v5] = "SETTINGS";
        v0[5] = "PUSH_PROMISE";
        v0[6] = "PING";
        v0[7] = "GOAWAY";
        int v6 = 8;
        v0[v6] = "WINDOW_UPDATE";
        v0[9] = "CONTINUATION";
        e.d = v0;
        e.b = new String[64];
        e.c = new String[256];
        int v0_1;
        for(v0_1 = 0; true; ++v0_1) {
            v7 = ' ';
            if(v0_1 >= e.c.length) {
                break;
            }

            e.c[v0_1] = c.a("%8s", new Object[]{Integer.toBinaryString(v0_1)}).replace(v7, '0');
        }

        e.b[0] = "";
        e.b[1] = "END_STREAM";
        int[] v0_2 = new int[]{1};
        e.b[v6] = "PADDED";
        int v1 = v0_2.length;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            v8 = v0_2[v3];
            String[] v9 = e.b;
            v9[v8 | 8] = e.b[v8] + "|PADDED";
        }

        e.b[v5] = "END_HEADERS";
        e.b[v7] = "PRIORITY";
        e.b[36] = "END_HEADERS|PRIORITY";
        int[] v1_1 = new int[]{4, 32, 36};
        v3 = v1_1.length;
        for(v4 = 0; v4 < v3; ++v4) {
            v5 = v1_1[v4];
            int v7_1 = v0_2.length;
            for(v8 = 0; v8 < v7_1; ++v8) {
                int v9_1 = v0_2[v8];
                String[] v10 = e.b;
                int v11_1 = v9_1 | v5;
                v10[v11_1] = e.b[v9_1] + '|' + e.b[v5];
                v10 = e.b;
                v10[v11_1 | v6] = e.b[v9_1] + '|' + e.b[v5] + "|PADDED";
            }
        }

        while(v2 < e.b.length) {
            if(e.b[v2] == null) {
                e.b[v2] = e.c[v2];
            }

            ++v2;
        }
    }

    private e() {
        super();
    }

    static IllegalArgumentException a(String arg1, Object[] arg2) {
        throw new IllegalArgumentException(c.a(arg1, arg2));
    }

    static String a(byte arg2, byte arg3) {
        if(arg3 == 0) {
            return "";
        }

        switch(arg2) {
            case 4: 
            case 6: {
                goto label_10;
            }
            case 2: 
            case 3: 
            case 7: 
            case 8: {
                goto label_17;
            }
        }

        String v0 = arg3 < e.b.length ? e.b[arg3] : e.c[arg3];
        if(arg2 == 5 && (arg3 & 4) != 0) {
            return v0.replace("HEADERS", "PUSH_PROMISE");
        }

        if(arg2 == 0 && (arg3 & 32) != 0) {
            return v0.replace("PRIORITY", "COMPRESSED");
        }

        return v0;
    label_17:
        return e.c[arg3];
    label_10:
        String v2 = arg3 == 1 ? "ACK" : e.c[arg3];
        return v2;
    }

    static String a(boolean arg5, int arg6, int arg7, byte arg8, byte arg9) {
        String v0 = arg8 < e.d.length ? e.d[arg8] : c.a("0x%02x", new Object[]{Byte.valueOf(arg8)});
        String v8 = e.a(arg8, arg9);
        String v9 = "%s 0x%08x %5d %-13s %s";
        Object[] v3 = new Object[5];
        String v5 = arg5 ? "<<" : ">>";
        v3[0] = v5;
        v3[1] = Integer.valueOf(arg6);
        v3[2] = Integer.valueOf(arg7);
        v3[3] = v0;
        v3[4] = v8;
        return c.a(v9, v3);
    }

    static IOException b(String arg1, Object[] arg2) {
        throw new IOException(c.a(arg1, arg2));
    }
}

