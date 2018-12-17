package com.google.ads.interactivemedia.v3.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ho extends gp {
    final class com.google.ads.interactivemedia.v3.internal.ho$1 implements gq {
        com.google.ads.interactivemedia.v3.internal.ho$1() {
            super();
        }

        public gp a(fz arg2, hw arg3) {
            if(arg3.a() == Object.class) {
                return new ho(arg2);
            }

            return null;
        }
    }

    class com.google.ads.interactivemedia.v3.internal.ho$2 {
        static {
            com.google.ads.interactivemedia.v3.internal.ho$2.a = new int[hy.values().length];
            try {
                com.google.ads.interactivemedia.v3.internal.ho$2.a[hy.a.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    com.google.ads.interactivemedia.v3.internal.ho$2.a[hy.c.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        com.google.ads.interactivemedia.v3.internal.ho$2.a[hy.f.ordinal()] = 3;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                        try {
                        label_19:
                            com.google.ads.interactivemedia.v3.internal.ho$2.a[hy.g.ordinal()] = 4;
                            goto label_24;
                        }
                        catch(NoSuchFieldError ) {
                            try {
                            label_24:
                                com.google.ads.interactivemedia.v3.internal.ho$2.a[hy.h.ordinal()] = 5;
                                goto label_29;
                            }
                            catch(NoSuchFieldError ) {
                                try {
                                label_29:
                                    com.google.ads.interactivemedia.v3.internal.ho$2.a[hy.i.ordinal()] = 6;
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

    public static final gq a;
    private final fz b;

    static {
        ho.a = new com.google.ads.interactivemedia.v3.internal.ho$1();
    }

    ho(fz arg1) {
        super();
        this.b = arg1;
    }

    public Object read(hx arg4) {
        switch(com.google.ads.interactivemedia.v3.internal.ho$2.a[arg4.f().ordinal()]) {
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
        hc v0 = new hc();
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

    public void write(hz arg3, Object arg4) {
        if(arg4 == null) {
            arg3.f();
            return;
        }

        gp v0 = this.b.a(arg4.getClass());
        if((v0 instanceof ho)) {
            arg3.d();
            arg3.e();
            return;
        }

        v0.write(arg3, arg4);
    }
}

