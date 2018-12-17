package com.google.ads.interactivemedia.v3.internal;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

public class hz implements Closeable, Flushable {
    private static final String[] a;
    private static final String[] b;
    private final Writer c;
    private int[] d;
    private int e;
    private String f;
    private String g;
    private boolean h;
    private boolean i;
    private String j;
    private boolean k;

    static {
        // Method was not decompiled
    }

    public hz(Writer arg2) {
        super();
        this.d = new int[32];
        this.e = 0;
        this.a(6);
        this.g = ":";
        this.k = true;
        if(arg2 != null) {
            this.c = arg2;
            return;
        }

        throw new NullPointerException("out == null");
    }

    public hz a(Number arg4) {
        if(arg4 == null) {
            return this.f();
        }

        this.j();
        String v0 = arg4.toString();
        if(!this.h && ((v0.equals("-Infinity")) || (v0.equals("Infinity")) || (v0.equals("NaN")))) {
            StringBuilder v1 = new StringBuilder();
            v1.append("Numeric values must be finite, but was ");
            v1.append(arg4);
            throw new IllegalArgumentException(v1.toString());
        }

        this.m();
        this.c.append(((CharSequence)v0));
        return this;
    }

    public hz a(long arg2) {
        this.j();
        this.m();
        this.c.write(Long.toString(arg2));
        return this;
    }

    public hz a(String arg2) {
        if(arg2 != null) {
            if(this.j == null) {
                if(this.e != 0) {
                    this.j = arg2;
                    return this;
                }

                throw new IllegalStateException("JsonWriter is closed.");
            }

            throw new IllegalStateException();
        }

        throw new NullPointerException("name == null");
    }

    public hz a(boolean arg2) {
        this.j();
        this.m();
        Writer v0 = this.c;
        String v2 = arg2 ? "true" : "false";
        v0.write(v2);
        return this;
    }

    public hz a(Boolean arg2) {
        if(arg2 == null) {
            return this.f();
        }

        this.j();
        this.m();
        Writer v0 = this.c;
        String v2 = arg2.booleanValue() ? "true" : "false";
        v0.write(v2);
        return this;
    }

    private void a(int arg5) {
        int[] v0;
        if(this.e == this.d.length) {
            v0 = new int[this.e * 2];
            System.arraycopy(this.d, 0, v0, 0, this.e);
            this.d = v0;
        }

        v0 = this.d;
        int v1 = this.e;
        this.e = v1 + 1;
        v0[v1] = arg5;
    }

    private int a() {
        if(this.e != 0) {
            return this.d[this.e - 1];
        }

        throw new IllegalStateException("JsonWriter is closed.");
    }

    private hz a(int arg2, int arg3, String arg4) {
        int v0 = this.a();
        if(v0 != arg3) {
            if(v0 == arg2) {
            }
            else {
                throw new IllegalStateException("Nesting problem.");
            }
        }

        if(this.j == null) {
            --this.e;
            if(v0 == arg3) {
                this.k();
            }

            this.c.write(arg4);
            return this;
        }

        StringBuilder v3 = new StringBuilder();
        v3.append("Dangling name: ");
        v3.append(this.j);
        throw new IllegalStateException(v3.toString());
    }

    private hz a(int arg1, String arg2) {
        this.m();
        this.a(arg1);
        this.c.write(arg2);
        return this;
    }

    public hz b(String arg1) {
        if(arg1 == null) {
            return this.f();
        }

        this.j();
        this.m();
        this.d(arg1);
        return this;
    }

    public hz b() {
        this.j();
        return this.a(1, "[");
    }

    public final void b(boolean arg1) {
        this.h = arg1;
    }

    private void b(int arg3) {
        this.d[this.e - 1] = arg3;
    }

    public hz c() {
        return this.a(1, 2, "]");
    }

    public final void c(String arg2) {
        if(arg2.length() == 0) {
            this.f = null;
            arg2 = ":";
        }
        else {
            this.f = arg2;
            arg2 = ": ";
        }

        this.g = arg2;
    }

    public final void c(boolean arg1) {
        this.i = arg1;
    }

    public void close() {
        this.c.close();
        int v0 = this.e;
        if(v0 <= 1 && (v0 != 1 || this.d[v0 - 1] == 7)) {
            this.e = 0;
            return;
        }

        throw new IOException("Incomplete document");
    }

    public final void d(boolean arg1) {
        this.k = arg1;
    }

    public hz d() {
        this.j();
        return this.a(3, "{");
    }

    private void d(String arg8) {
        String v4_1;
        String[] v0 = this.i ? hz.b : hz.a;
        this.c.write("\"");
        int v1 = arg8.length();
        int v2 = 0;
        int v3 = 0;
        while(v2 < v1) {
            int v4 = arg8.charAt(v2);
            if(v4 < 128) {
                v4_1 = v0[v4];
                if(v4_1 == null) {
                }
                else {
                    goto label_25;
                }
            }
            else if(v4 == 8232) {
                v4_1 = "\\u2028";
                goto label_25;
            }
            else if(v4 == 8233) {
                v4_1 = "\\u2029";
            label_25:
                if(v3 < v2) {
                    this.c.write(arg8, v3, v2 - v3);
                }

                this.c.write(v4_1);
                v3 = v2 + 1;
            }

            ++v2;
        }

        if(v3 < v1) {
            this.c.write(arg8, v3, v1 - v3);
        }

        this.c.write("\"");
    }

    public hz e() {
        return this.a(3, 5, "}");
    }

    public hz f() {
        if(this.j != null) {
            if(this.k) {
                this.j();
            }
            else {
                this.j = null;
                return this;
            }
        }

        this.m();
        this.c.write("null");
        return this;
    }

    public void flush() {
        if(this.e != 0) {
            this.c.flush();
            return;
        }

        throw new IllegalStateException("JsonWriter is closed.");
    }

    public boolean g() {
        return this.h;
    }

    public final boolean h() {
        return this.i;
    }

    public final boolean i() {
        return this.k;
    }

    private void j() {
        if(this.j != null) {
            this.l();
            this.d(this.j);
            this.j = null;
        }
    }

    private void k() {
        if(this.f == null) {
            return;
        }

        this.c.write("\n");
        int v0 = this.e;
        int v1;
        for(v1 = 1; v1 < v0; ++v1) {
            this.c.write(this.f);
        }
    }

    private void l() {
        int v0 = this.a();
        if(v0 == 5) {
            this.c.write(44);
        }
        else {
            if(v0 == 3) {
                goto label_9;
            }

            goto label_13;
        }

    label_9:
        this.k();
        this.b(4);
        return;
    label_13:
        throw new IllegalStateException("Nesting problem.");
    }

    private void m() {
        switch(this.a()) {
            case 1: {
                goto label_25;
            }
            case 2: {
                goto label_21;
            }
            case 4: {
                goto label_15;
            }
            case 6: {
                goto label_13;
            }
            case 7: {
                goto label_6;
            }
        }

        throw new IllegalStateException("Nesting problem.");
    label_21:
        this.c.append(',');
        goto label_27;
    label_6:
        if(this.h) {
        }
        else {
            throw new IllegalStateException("JSON must have only one top-level value.");
        label_25:
            this.b(2);
        label_27:
            this.k();
            return;
        }

    label_13:
        int v0 = 7;
        goto label_19;
    label_15:
        this.c.append(this.g);
        v0 = 5;
    label_19:
        this.b(v0);
    }
}

