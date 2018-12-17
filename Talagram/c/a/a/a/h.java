package c.a.a.a;

import c.a.a.a.a.b.u;
import c.a.a.a.a.c.e;
import c.a.a.a.a.c.f;
import c.a.a.a.a.c.m;

class h extends f {
    final i a;

    public h(i arg1) {
        super();
        this.a = arg1;
    }

    private u a(String arg4) {
        StringBuilder v1 = new StringBuilder();
        v1.append(this.a.b());
        v1.append(".");
        v1.append(arg4);
        u v0 = new u(v1.toString(), "KitInitialization");
        v0.a();
        return v0;
    }

    protected Object a(Object[] arg1) {
        return this.a(((Void[])arg1));
    }

    protected Object a(Void[] arg2) {
        u v2 = this.a("doInBackground");
        Object v0 = !this.e() ? this.a.e() : null;
        v2.b();
        return v0;
    }

    protected void a() {
        super.a();
        u v0 = this.a("onPreExecute");
        try {
            boolean v2_3 = this.a.b_();
        }
        catch(Throwable v2) {
        label_22:
            v0.b();
            this.a(true);
            throw v2;
        }
        catch(Exception v2_1) {
            try {
                c.h().e("Fabric", "Failure onPreExecute()", ((Throwable)v2_1));
            }
            catch(Throwable v2) {
                goto label_22;
            }

            v0.b();
            goto label_8;
        }
        catch(m v2_2) {
            try {
                throw v2_2;
            }
            catch(Throwable v2) {
                goto label_22;
            }
        }

        v0.b();
        if(!v2_3) {
        label_8:
            this.a(true);
        }
    }

    protected void a(Object arg2) {
        this.a.a(arg2);
        this.a.h.a(arg2);
    }

    public e b() {
        return e.c;
    }

    protected void b(Object arg2) {
        this.a.b(arg2);
        StringBuilder v2 = new StringBuilder();
        v2.append(this.a.b());
        v2.append(" Initialization was cancelled");
        this.a.h.a(new g(v2.toString()));
    }
}

