package com.persianswitch.a.a.a;

import com.persianswitch.b.c;
import com.persianswitch.b.e;
import com.persianswitch.b.f;
import com.persianswitch.b.h;
import com.persianswitch.b.l;
import com.persianswitch.b.s;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

class k {
    private final com.persianswitch.b.k a;
    private int b;
    private final e c;

    public k(e arg3) {
        super();
        this.a = new com.persianswitch.b.k(new h(((s)arg3)) {
            public long a(c arg6, long arg7) {
                long v1 = -1;
                if(k.a(this.a) == 0) {
                    return v1;
                }

                long v6 = super.a(arg6, Math.min(arg7, ((long)k.a(this.a))));
                if(v6 == v1) {
                    return v1;
                }

                k.a(this.a, ((int)((((long)k.a(this.a))) - v6)));
                return v6;
            }
        }, new Inflater() {
            public int inflate(byte[] arg3, int arg4, int arg5) {
                int v0 = super.inflate(arg3, arg4, arg5);
                if(v0 == 0 && (this.needsDictionary())) {
                    this.setDictionary(o.a);
                    v0 = super.inflate(arg3, arg4, arg5);
                }

                return v0;
            }
        });
        this.c = l.a(this.a);
    }

    static int a(k arg0) {
        return arg0.b;
    }

    static int a(k arg0, int arg1) {
        arg0.b = arg1;
        return arg1;
    }

    public List a(int arg6) {
        this.b += arg6;
        arg6 = this.c.j();
        if(arg6 < 0) {
            goto label_37;
        }

        if(arg6 > 1024) {
            goto label_28;
        }

        ArrayList v0 = new ArrayList(arg6);
        int v1;
        for(v1 = 0; true; ++v1) {
            if(v1 >= arg6) {
                goto label_26;
            }

            f v2 = this.b().d();
            f v3 = this.b();
            if(v2.e() == 0) {
                break;
            }

            ((List)v0).add(new com.persianswitch.a.a.a.f(v2, v3));
        }

        throw new IOException("name.size == 0");
    label_26:
        this.c();
        return ((List)v0);
    label_28:
        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("numberOfPairs > 1024: ");
        v1_1.append(arg6);
        throw new IOException(v1_1.toString());
    label_37:
        v1_1 = new StringBuilder();
        v1_1.append("numberOfPairs < 0: ");
        v1_1.append(arg6);
        throw new IOException(v1_1.toString());
    }

    public void a() {
        this.c.close();
    }

    private f b() {
        return this.c.c(((long)this.c.j()));
    }

    private void c() {
        if(this.b > 0) {
            this.a.b();
            if(this.b == 0) {
            }
            else {
                StringBuilder v1 = new StringBuilder();
                v1.append("compressedLimit > 0: ");
                v1.append(this.b);
                throw new IOException(v1.toString());
            }
        }
    }
}

