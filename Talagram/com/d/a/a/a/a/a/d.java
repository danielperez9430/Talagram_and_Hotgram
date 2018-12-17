package com.d.a.a.a.a.a;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

final class d {
    static final Charset a;
    static final Charset b;

    static {
        d.a = Charset.forName("US-ASCII");
        d.b = Charset.forName("UTF-8");
    }

    static void a(Closeable arg0) {
        if(arg0 != null) {
            try {
                arg0.close();
                return;
            }
            catch(Exception ) {
                return;
            }
            catch(RuntimeException v0) {
                throw v0;
            }
        }
    }

    static void a(File arg4) {
        File v2;
        File[] v0 = arg4.listFiles();
        if(v0 == null) {
            goto label_23;
        }

        int v4 = v0.length;
        int v1;
        for(v1 = 0; true; ++v1) {
            if(v1 >= v4) {
                return;
            }

            v2 = v0[v1];
            if(v2.isDirectory()) {
                d.a(v2);
            }

            if(!v2.delete()) {
                break;
            }
        }

        StringBuilder v0_1 = new StringBuilder();
        v0_1.append("failed to delete file: ");
        v0_1.append(v2);
        throw new IOException(v0_1.toString());
        return;
    label_23:
        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("not a readable directory: ");
        v1_1.append(arg4);
        throw new IOException(v1_1.toString());
    }
}

