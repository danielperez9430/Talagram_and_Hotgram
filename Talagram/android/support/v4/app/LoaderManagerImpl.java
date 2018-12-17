package android.support.v4.app;

import android.arch.lifecycle.g;
import android.arch.lifecycle.m;
import android.arch.lifecycle.s;
import android.arch.lifecycle.t$a;
import android.arch.lifecycle.u;
import android.os.Bundle;
import android.support.v4.content.b;
import android.support.v4.f.d;
import android.support.v4.f.n;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;

class LoaderManagerImpl extends t {
    class LoaderViewModel extends s {
        final class android.support.v4.app.LoaderManagerImpl$LoaderViewModel$1 implements a {
            android.support.v4.app.LoaderManagerImpl$LoaderViewModel$1() {
                super();
            }

            public s a(Class arg1) {
                return new LoaderViewModel();
            }
        }

        private static final a a;
        private n b;
        private boolean c;

        static {
            LoaderViewModel.a = new android.support.v4.app.LoaderManagerImpl$LoaderViewModel$1();
        }

        LoaderViewModel() {
            super();
            this.b = new n();
            this.c = false;
        }

        static LoaderViewModel a(u arg2) {
            return new android.arch.lifecycle.t(arg2, LoaderViewModel.a).a(LoaderViewModel.class);
        }

        public void a(String arg5, FileDescriptor arg6, PrintWriter arg7, String[] arg8) {
            if(this.b.b() > 0) {
                arg7.print(arg5);
                arg7.println("Loaders:");
                String v0_1 = arg5 + "    ";
                int v1;
                for(v1 = 0; v1 < this.b.b(); ++v1) {
                    Object v2 = this.b.e(v1);
                    arg7.print(arg5);
                    arg7.print("  #");
                    arg7.print(this.b.d(v1));
                    arg7.print(": ");
                    arg7.println(((android.support.v4.app.LoaderManagerImpl$a)v2).toString());
                    ((android.support.v4.app.LoaderManagerImpl$a)v2).a(v0_1, arg6, arg7, arg8);
                }
            }
        }

        protected void a() {
            super.a();
            int v0 = this.b.b();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                this.b.e(v1).a(true);
            }

            this.b.c();
        }

        void b() {
            int v0 = this.b.b();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                this.b.e(v1).g();
            }
        }
    }

    public class android.support.v4.app.LoaderManagerImpl$a extends m implements android.support.v4.content.b$a {
        private final int a;
        private final Bundle b;
        private final b c;
        private g d;
        private android.support.v4.app.LoaderManagerImpl$b e;
        private b f;

        b a(boolean arg4) {
            if(LoaderManagerImpl.a) {
                Log.v("LoaderManager", "  Destroying: " + this);
            }

            this.c.c();
            this.c.g();
            android.support.v4.app.LoaderManagerImpl$b v0 = this.e;
            if(v0 != null) {
                this.a(((android.arch.lifecycle.n)v0));
                if(arg4) {
                    v0.b();
                }
            }

            this.c.a(((android.support.v4.content.b$a)this));
            if(v0 != null && !v0.a() || (arg4)) {
                this.c.i();
                return this.f;
            }

            return this.c;
        }

        public void a(String arg4, FileDescriptor arg5, PrintWriter arg6, String[] arg7) {
            arg6.print(arg4);
            arg6.print("mId=");
            arg6.print(this.a);
            arg6.print(" mArgs=");
            arg6.println(this.b);
            arg6.print(arg4);
            arg6.print("mLoader=");
            arg6.println(this.c);
            b v0 = this.c;
            v0.a(arg4 + "  ", arg5, arg6, arg7);
            if(this.e != null) {
                arg6.print(arg4);
                arg6.print("mCallbacks=");
                arg6.println(this.e);
                android.support.v4.app.LoaderManagerImpl$b v5 = this.e;
                v5.a(arg4 + "  ", arg6);
            }

            arg6.print(arg4);
            arg6.print("mData=");
            arg6.println(this.f().a(this.a()));
            arg6.print(arg4);
            arg6.print("mStarted=");
            arg6.println(this.d());
        }

        public void a(android.arch.lifecycle.n arg1) {
            super.a(arg1);
            this.d = null;
            this.e = null;
        }

        public void a(Object arg1) {
            super.a(arg1);
            if(this.f != null) {
                this.f.i();
                this.f = null;
            }
        }

        protected void b() {
            if(LoaderManagerImpl.a) {
                Log.v("LoaderManager", "  Starting: " + this);
            }

            this.c.a();
        }

        protected void c() {
            if(LoaderManagerImpl.a) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }

            this.c.e();
        }

        b f() {
            return this.c;
        }

        void g() {
            g v0 = this.d;
            android.support.v4.app.LoaderManagerImpl$b v1 = this.e;
            if(v0 != null && v1 != null) {
                super.a(((android.arch.lifecycle.n)v1));
                this.a(v0, ((android.arch.lifecycle.n)v1));
            }
        }

        public String toString() {
            StringBuilder v0 = new StringBuilder(64);
            v0.append("LoaderInfo{");
            v0.append(Integer.toHexString(System.identityHashCode(this)));
            v0.append(" #");
            v0.append(this.a);
            v0.append(" : ");
            d.a(this.c, v0);
            v0.append("}}");
            return v0.toString();
        }
    }

    class android.support.v4.app.LoaderManagerImpl$b implements android.arch.lifecycle.n {
        private final b a;
        private final android.support.v4.app.t$a b;
        private boolean c;

        public void a(Object arg4) {
            if(LoaderManagerImpl.a) {
                Log.v("LoaderManager", "  onLoadFinished in " + this.a + ": " + this.a.a(arg4));
            }

            this.b.a(this.a, arg4);
            this.c = true;
        }

        public void a(String arg1, PrintWriter arg2) {
            arg2.print(arg1);
            arg2.print("mDeliveredData=");
            arg2.println(this.c);
        }

        boolean a() {
            return this.c;
        }

        void b() {
            if(this.c) {
                if(LoaderManagerImpl.a) {
                    Log.v("LoaderManager", "  Resetting: " + this.a);
                }

                this.b.a(this.a);
            }
        }

        public String toString() {
            return this.b.toString();
        }
    }

    static boolean a = false;
    private final g b;
    private final LoaderViewModel c;

    static {
    }

    LoaderManagerImpl(g arg1, u arg2) {
        super();
        this.b = arg1;
        this.c = LoaderViewModel.a(arg2);
    }

    public void a() {
        this.c.b();
    }

    @Deprecated public void a(String arg2, FileDescriptor arg3, PrintWriter arg4, String[] arg5) {
        this.c.a(arg2, arg3, arg4, arg5);
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder(128);
        v0.append("LoaderManager{");
        v0.append(Integer.toHexString(System.identityHashCode(this)));
        v0.append(" in ");
        d.a(this.b, v0);
        v0.append("}}");
        return v0.toString();
    }
}

