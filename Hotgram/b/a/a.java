package b.a;

public final class a implements javax.a.a {
    private static final Object b;
    private volatile javax.a.a c;
    private volatile Object d;

    static {
        a.a = a.class.desiredAssertionStatus() ^ 1;
        a.b = new Object();
    }

    private a(javax.a.a arg2) {
        super();
        this.d = a.b;
        if(!a.a) {
            if(arg2 != null) {
            }
            else {
                throw new AssertionError();
            }
        }

        this.c = arg2;
    }

    public static javax.a.a a(javax.a.a arg1) {
        c.a(arg1);
        if((arg1 instanceof a)) {
            return arg1;
        }

        return new a(arg1);
    }

    public Object a() {
        Object v0 = this.d;
        if(v0 == a.b) {
            __monitor_enter(this);
            try {
                v0 = this.d;
                if(v0 == a.b) {
                    v0 = this.c.a();
                    Object v1 = this.d;
                    if(v1 != a.b) {
                        if(v1 == v0) {
                        }
                        else {
                            StringBuilder v3 = new StringBuilder();
                            v3.append("Scoped provider was invoked recursively returning different results: ");
                            v3.append(v1);
                            v3.append(" & ");
                            v3.append(v0);
                            throw new IllegalStateException(v3.toString());
                        }
                    }

                    this.d = v0;
                    this.c = null;
                }

                __monitor_exit(this);
                return v0;
            label_32:
                __monitor_exit(this);
            }
            catch(Throwable v0_1) {
                goto label_32;
            }

            throw v0_1;
        }

        return v0;
    }
}

