package com.google.a.b.a;

import com.google.a.b.g;
import com.google.a.c.a;
import com.google.a.d.b;
import com.google.a.d.c;
import com.google.a.f;
import com.google.a.v;
import com.google.a.w;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class h extends v {
    final class com.google.a.b.a.h$1 implements w {
        com.google.a.b.a.h$1() {
            super();
        }

        public v create(f arg2, a arg3) {
            if(arg3.a() == Object.class) {
                return new h(arg2);
            }

            return null;
        }
    }

    class com.google.a.b.a.h$2 {
        static {
            com.google.a.b.a.h$2.a = new int[b.values().length];
            try {
                com.google.a.b.a.h$2.a[b.a.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    com.google.a.b.a.h$2.a[b.c.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        com.google.a.b.a.h$2.a[b.f.ordinal()] = 3;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                        try {
                        label_19:
                            com.google.a.b.a.h$2.a[b.g.ordinal()] = 4;
                            goto label_24;
                        }
                        catch(NoSuchFieldError ) {
                            try {
                            label_24:
                                com.google.a.b.a.h$2.a[b.h.ordinal()] = 5;
                                goto label_29;
                            }
                            catch(NoSuchFieldError ) {
                                try {
                                label_29:
                                    com.google.a.b.a.h$2.a[b.i.ordinal()] = 6;
                                    return;
                                }
                                catch(NoSuchFieldError ) {
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static final w a;
    private final f b;

    static {
        h.a = new com.google.a.b.a.h$1();
    }

    h(f arg1) {
        super();
        this.b = arg1;
    }

    public Object read(com.google.a.d.a arg4) {
        switch(com.google.a.b.a.h$2.a[arg4.f().ordinal()]) {
            case 1: {
                goto label_30;
            }
            case 2: {
                goto label_19;
            }
            case 3: {
                goto label_17;
            }
            case 4: {
                goto label_14;
            }
            case 5: {
                goto label_11;
            }
            case 6: {
                goto label_8;
            }
        }

        throw new IllegalStateException();
    label_17:
        return arg4.h();
    label_19:
        g v0 = new g();
        arg4.c();
        while(arg4.e()) {
            ((Map)v0).put(arg4.g(), this.read(arg4));
        }

        arg4.d();
        return v0;
    label_8:
        arg4.j();
        return null;
    label_11:
        return Boolean.valueOf(arg4.i());
    label_30:
        ArrayList v0_1 = new ArrayList();
        arg4.a();
        while(arg4.e()) {
            ((List)v0_1).add(this.read(arg4));
        }

        arg4.b();
        return v0_1;
    label_14:
        return Double.valueOf(arg4.k());
    }

    public void write(c arg3, Object arg4) {
        if(arg4 == null) {
            arg3.f();
            return;
        }

        v v0 = this.b.a(arg4.getClass());
        if((v0 instanceof h)) {
            arg3.d();
            arg3.e();
            return;
        }

        v0.write(arg3, arg4);
    }
}

