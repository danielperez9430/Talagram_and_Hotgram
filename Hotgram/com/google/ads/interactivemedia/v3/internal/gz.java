package com.google.ads.interactivemedia.v3.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class gz implements gq, Cloneable {
    public static final gz a;
    private double b;
    private int c;
    private boolean d;
    private boolean e;
    private List f;
    private List g;

    static {
        gz.a = new gz();
    }

    public gz() {
        super();
        this.b = -1;
        this.c = 136;
        this.d = true;
        this.f = Collections.emptyList();
        this.g = Collections.emptyList();
    }

    public gz a(fv arg3, boolean arg4, boolean arg5) {
        gz v0 = this.a();
        if(arg4) {
            v0.f = new ArrayList(this.f);
            v0.f.add(arg3);
        }

        if(arg5) {
            v0.g = new ArrayList(this.g);
            v0.g.add(arg3);
        }

        return v0;
    }

    private boolean a(gu arg5) {
        if(arg5 != null && arg5.a() > this.b) {
            return 0;
        }

        return 1;
    }

    private boolean a(gu arg1, gv arg2) {
        boolean v1 = !this.a(arg1) || !this.a(arg2) ? false : true;
        return v1;
    }

    private boolean a(gv arg5) {
        if(arg5 != null && arg5.a() <= this.b) {
            return 0;
        }

        return 1;
    }

    private boolean a(Class arg2) {
        boolean v2;
        if(!Enum.class.isAssignableFrom(arg2)) {
            if(!arg2.isAnonymousClass() && !arg2.isLocalClass()) {
                goto label_9;
            }

            v2 = true;
        }
        else {
        label_9:
            v2 = false;
        }

        return v2;
    }

    public gp a(fz arg9, hw arg10) {
        Class v0 = arg10.a();
        boolean v5 = this.a(v0, true);
        boolean v4 = this.a(v0, false);
        if(!v5 && !v4) {
            return null;
        }

        return new gp(v4, v5, arg9, arg10) {
            private gp f;

            private gp a() {
                gp v0 = this.f;
                if(v0 != null) {
                }
                else {
                    v0 = this.c.a(this.e, this.d);
                    this.f = v0;
                }

                return v0;
            }

            public Object read(hx arg2) {
                if(this.a) {
                    arg2.n();
                    return null;
                }

                return this.a().read(arg2);
            }

            public void write(hz arg2, Object arg3) {
                if(this.b) {
                    arg2.f();
                    return;
                }

                this.a().write(arg2, arg3);
            }
        };
    }

    public boolean a(Class arg6, boolean arg7) {
        // Method was not decompiled
    }

    protected gz a() {
        try {
            return super.clone();
        }
        catch(CloneNotSupportedException v0) {
            throw new AssertionError(v0);
        }
    }

    public boolean a(Field arg7, boolean arg8) {
        if((this.c & arg7.getModifiers()) != 0) {
            return 1;
        }

        if(this.b != -1 && !this.a(arg7.getAnnotation(gu.class), arg7.getAnnotation(gv.class))) {
            return 1;
        }

        if(arg7.isSynthetic()) {
            return 1;
        }

        if(this.e) {
            Annotation v0 = arg7.getAnnotation(gr.class);
            if(v0 != null) {
                if(!arg8) {
                    if(!((gr)v0).b()) {
                        return 1;
                    }

                    goto label_31;
                }
                else if(!((gr)v0).a()) {
                }
                else {
                    goto label_31;
                }
            }

            return 1;
        }

    label_31:
        if(!this.d && (this.b(arg7.getType()))) {
            return 1;
        }

        if(this.a(arg7.getType())) {
            return 1;
        }

        List v8 = arg8 ? this.f : this.g;
        if(!v8.isEmpty()) {
            fw v0_1 = new fw(arg7);
            Iterator v7 = v8.iterator();
            do {
                if(v7.hasNext()) {
                    if(!v7.next().a(v0_1)) {
                        continue;
                    }

                    return 1;
                }

                return 0;
            }
            while(true);

            return 1;
        }

        return 0;
    }

    private boolean b(Class arg2) {
        boolean v2 = !arg2.isMemberClass() || (this.c(arg2)) ? false : true;
        return v2;
    }

    private boolean c(Class arg1) {
        boolean v1 = (arg1.getModifiers() & 8) != 0 ? true : false;
        return v1;
    }

    protected Object clone() {
        return this.a();
    }
}

