package com.google.ads.interactivemedia.v3.internal;

import java.io.PrintStream;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public final class lp {
    abstract class a {
        protected static final Throwable[] a;

        static {
            a.a = new Throwable[0];
        }

        a() {
            super();
        }

        public abstract void a(Throwable arg1);
    }

    final class b {
        final class com.google.ads.interactivemedia.v3.internal.lp$b$a extends WeakReference {
            private final int a;

            public com.google.ads.interactivemedia.v3.internal.lp$b$a(Throwable arg1, ReferenceQueue arg2) {
                super(arg1, arg2);
                if(arg1 != null) {
                    this.a = System.identityHashCode(arg1);
                    return;
                }

                throw new NullPointerException("The referent cannot be null");
            }

            public boolean equals(Object arg5) {
                boolean v0 = false;
                if(arg5 != null) {
                    if(arg5.getClass() != this.getClass()) {
                    }
                    else if(this == (((com.google.ads.interactivemedia.v3.internal.lp$b$a)arg5))) {
                        return 1;
                    }
                    else if(this.a == ((com.google.ads.interactivemedia.v3.internal.lp$b$a)arg5).a && this.get() == ((com.google.ads.interactivemedia.v3.internal.lp$b$a)arg5).get()) {
                        v0 = true;
                    }
                }

                return v0;
            }

            public int hashCode() {
                return this.a;
            }
        }

        private final ConcurrentHashMap a;
        private final ReferenceQueue b;

        b() {
            super();
            this.a = new ConcurrentHashMap(16, 0.75f, 10);
            this.b = new ReferenceQueue();
        }

        public List a(Throwable arg4, boolean arg5) {
            this.a();
            Object v0 = this.a.get(new com.google.ads.interactivemedia.v3.internal.lp$b$a(arg4, null));
            if(!arg5) {
                return ((List)v0);
            }

            if(v0 != null) {
                return ((List)v0);
            }

            Vector v5 = new Vector(2);
            Object v4 = this.a.putIfAbsent(new com.google.ads.interactivemedia.v3.internal.lp$b$a(arg4, this.b), v5);
            if(v4 == null) {
                Vector v4_1 = v5;
            }

            return ((List)v4);
        }

        void a() {
            while(true) {
                Reference v0 = this.b.poll();
                if(v0 == null) {
                    return;
                }

                this.a.remove(v0);
            }
        }
    }

    final class c extends a {
        private final b b;

        c() {
            super();
            this.b = new b();
        }

        public void a(Throwable arg5) {
            arg5.printStackTrace();
            List v5 = this.b.a(arg5, false);
            if(v5 == null) {
                return;
            }

            __monitor_enter(v5);
            try {
                Iterator v0_1 = v5.iterator();
                while(v0_1.hasNext()) {
                    Object v1 = v0_1.next();
                    System.err.print("Suppressed: ");
                    ((Throwable)v1).printStackTrace();
                }

                __monitor_exit(v5);
                return;
            label_19:
                __monitor_exit(v5);
            }
            catch(Throwable v0) {
                goto label_19;
            }

            throw v0;
        }
    }

    final class d extends a {
        d() {
            super();
        }

        public void a(Throwable arg1) {
            arg1.printStackTrace();
        }
    }

    final class e extends a {
        e() {
            super();
        }

        public void a(Throwable arg1) {
            arg1.printStackTrace();
        }
    }

    static final a a;
    static final int b;

    static {
        d v1_3;
        e v1_1;
        Integer v0;
        try {
            v0 = lp.b();
            if(v0 == null) {
                goto label_10;
            }
        }
        catch(Throwable v1) {
            v0 = null;
            goto label_20;
        }

        try {
            if(v0.intValue() >= 19) {
                v1_1 = new e();
                goto label_39;
            }

        label_10:
            if(lp.a()) {
                c v1_2 = new c();
                goto label_39;
            }

            v1_3 = new d();
            goto label_39;
        label_9:
        }
        catch(Throwable v1) {
            goto label_9;
        }

    label_20:
        PrintStream v2 = System.err;
        String v3 = d.class.getName();
        StringBuilder v5 = new StringBuilder(String.valueOf(v3).length() + 132);
        v5.append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ");
        v5.append(v3);
        v5.append("will be used. The error is: ");
        v2.println(v5.toString());
        v1.printStackTrace(System.err);
        v1_3 = new d();
    label_39:
        lp.a = ((a)v1_1);
        int v0_1 = v0 == null ? 1 : v0.intValue();
        lp.b = v0_1;
    }

    public static void a(Throwable arg1) {
        lp.a.a(arg1);
    }

    private static boolean a() {
        return Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic") ^ 1;
    }

    private static Integer b() {
        Object v0 = null;
        try {
            return Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(v0);
        }
        catch(Exception v1) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            v1.printStackTrace(System.err);
            return ((Integer)v0);
        }
    }
}

