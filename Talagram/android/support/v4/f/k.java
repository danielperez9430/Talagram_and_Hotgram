package android.support.v4.f;

public final class k {
    public interface a {
        boolean a(Object arg1);

        Object a();
    }

    public class b implements a {
        private final Object[] a;
        private int b;

        public b(int arg2) {
            super();
            if(arg2 > 0) {
                this.a = new Object[arg2];
                return;
            }

            throw new IllegalArgumentException("The max pool size must be > 0");
        }

        public Object a() {
            Object v1 = null;
            if(this.b > 0) {
                int v0 = this.b - 1;
                Object v2 = this.a[v0];
                this.a[v0] = v1;
                --this.b;
                return v2;
            }

            return v1;
        }

        public boolean a(Object arg3) {
            if(!this.b(arg3)) {
                if(this.b < this.a.length) {
                    this.a[this.b] = arg3;
                    ++this.b;
                    return 1;
                }

                return 0;
            }

            throw new IllegalStateException("Already in the pool!");
        }

        private boolean b(Object arg4) {
            int v1;
            for(v1 = 0; v1 < this.b; ++v1) {
                if(this.a[v1] == arg4) {
                    return 1;
                }
            }

            return 0;
        }
    }

    public class c extends b {
        private final Object a;

        public c(int arg1) {
            super(arg1);
            this.a = new Object();
        }

        public Object a() {
            Object v0 = this.a;
            __monitor_enter(v0);
            try {
                __monitor_exit(v0);
                return super.a();
            label_6:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_6;
            }

            throw v1;
        }

        public boolean a(Object arg2) {
            Object v0 = this.a;
            __monitor_enter(v0);
            try {
                __monitor_exit(v0);
                return super.a(arg2);
            label_6:
                __monitor_exit(v0);
            }
            catch(Throwable v2) {
                goto label_6;
            }

            throw v2;
        }
    }

}

