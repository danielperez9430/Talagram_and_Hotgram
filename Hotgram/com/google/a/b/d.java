package com.google.a.b;

import com.google.a.a.a;
import com.google.a.a.e;
import com.google.a.c;
import com.google.a.f;
import com.google.a.v;
import com.google.a.w;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class d implements w, Cloneable {
    public static final d a;
    private double b;
    private int c;
    private boolean d;
    private boolean e;
    private List f;
    private List g;

    static {
        d.a = new d();
    }

    public d() {
        super();
        this.b = -1;
        this.c = 136;
        this.d = true;
        this.f = Collections.emptyList();
        this.g = Collections.emptyList();
    }

    public boolean a(Class arg6, boolean arg7) {
        // Method was not decompiled
    }

    public boolean a(Field arg7, boolean arg8) {
        if((this.c & arg7.getModifiers()) != 0) {
            return 1;
        }

        if(this.b != -1 && !this.a(arg7.getAnnotation(com.google.a.a.d.class), arg7.getAnnotation(e.class))) {
            return 1;
        }

        if(arg7.isSynthetic()) {
            return 1;
        }

        if(this.e) {
            Annotation v0 = arg7.getAnnotation(a.class);
            if(v0 != null) {
                if(!arg8) {
                    if(!((a)v0).b()) {
                        return 1;
                    }

                    goto label_31;
                }
                else if(!((a)v0).a()) {
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
            c v0_1 = new c(arg7);
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

    private boolean a(com.google.a.a.d arg5) {
        if(arg5 != null && arg5.a() > this.b) {
            return 0;
        }

        return 1;
    }

    private boolean a(com.google.a.a.d arg1, e arg2) {
        boolean v1 = !this.a(arg1) || !this.a(arg2) ? false : true;
        return v1;
    }

    private boolean a(e arg5) {
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

    protected d a() {
        try {
            return super.clone();
        }
        catch(CloneNotSupportedException v0) {
            throw new AssertionError(v0);
        }
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

    public v create(f arg9, com.google.a.c.a arg10) {
        Class v0 = arg10.a();
        boolean v5 = this.a(v0, true);
        boolean v4 = this.a(v0, false);
        if(!v5 && !v4) {
            return null;
        }

        return new v(v4, v5, arg9, arg10) {
            private v f;

            private v a() {
                v v0 = this.f;
                if(v0 != null) {
                }
                else {
                    v0 = this.c.a(this.e, this.d);
                    this.f = v0;
                }

                return v0;
            }

            public Object read(com.google.a.d.a arg2) {
                if(this.a) {
                    arg2.n();
                    return null;
                }

                return this.a().read(arg2);
            }

            public void write(com.google.a.d.c arg2, Object arg3) {
                if(this.b) {
                    arg2.f();
                    return;
                }

                this.a().write(arg2, arg3);
            }
        };
    }
}

