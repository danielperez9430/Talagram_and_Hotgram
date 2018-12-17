package com.d.a.b.a;

import java.io.FilterInputStream;
import java.io.InputStream;

public class c extends FilterInputStream {
    public c(InputStream arg1) {
        super(arg1);
    }

    public long skip(long arg8) {
        long v0 = 0;
        long v2;
        for(v2 = v0; v2 < arg8; v2 += v4) {
            long v4 = this.in.skip(arg8 - v2);
            if(v4 == v0) {
                if(this.read() < 0) {
                    return v2;
                }
                else {
                    v4 = 1;
                }
            }
        }

        return v2;
    }
}

