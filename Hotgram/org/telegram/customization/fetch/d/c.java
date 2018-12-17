package org.telegram.customization.fetch.d;

import java.util.Iterator;
import java.util.List;

public final class c {
    private final long a;
    private final int b;
    private final String c;
    private final String d;
    private final int e;
    private final long f;
    private final long g;
    private final int h;
    private final List i;
    private final int j;

    public c(long arg1, int arg3, String arg4, String arg5, int arg6, long arg7, long arg9, int arg11, List arg12, int arg13) {
        super();
        if(arg4 != null) {
            if(arg5 != null) {
                if(arg12 != null) {
                    this.a = arg1;
                    this.b = arg3;
                    this.c = arg4;
                    this.d = arg5;
                    this.e = arg6;
                    this.f = arg7;
                    this.g = arg9;
                    this.h = arg11;
                    this.i = arg12;
                    this.j = arg13;
                    return;
                }

                throw new NullPointerException("Headers cannot be null");
            }

            throw new NullPointerException("FilePath cannot be null");
        }

        throw new NullPointerException("Url cannot be null");
    }

    public long a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public int e() {
        return this.e;
    }

    public long f() {
        return this.f;
    }

    public long g() {
        return this.g;
    }

    public int h() {
        return this.h;
    }

    public List i() {
        return this.i;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder();
        Iterator v1 = this.i.iterator();
        while(v1.hasNext()) {
            v0.append(v1.next().toString());
            v0.append(",");
        }

        if(this.i.size() > 0) {
            v0.deleteCharAt(v0.length() - 1);
        }

        return "{id:" + this.a + ",status:" + this.b + ",url:" + this.c + ",filePath:" + this.d + ",progress:" + this.e + ",fileSize:" + this.g + ",error:" + this.h + ",headers:{" + v0.toString() + "},priority:" + this.j + "}";
    }
}

