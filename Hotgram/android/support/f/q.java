package android.support.f;

import android.animation.TimeInterpolator;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

public class q extends m {
    class a extends n {
        q a;

        a(q arg1) {
            super();
            this.a = arg1;
        }

        public void a(m arg3) {
            --this.a.h;
            if(this.a.h == 0) {
                this.a.i = false;
                this.a.k();
            }

            arg3.b(((c)this));
        }

        public void d(m arg2) {
            if(!this.a.i) {
                this.a.j();
                this.a.i = true;
            }
        }
    }

    int h;
    boolean i;
    private ArrayList j;
    private boolean k;
    private int l;

    public q() {
        super();
        this.j = new ArrayList();
        this.k = true;
        this.i = false;
        this.l = 0;
    }

    public q a(m arg6) {
        this.j.add(arg6);
        arg6.d = this;
        if(this.a >= 0) {
            arg6.a(this.a);
        }

        if((this.l & 1) != 0) {
            arg6.a(this.d());
        }

        if((this.l & 2) != 0) {
            arg6.a(this.n());
        }

        if((this.l & 4) != 0) {
            arg6.a(this.l());
        }

        if((this.l & 8) != 0) {
            arg6.a(this.m());
        }

        return this;
    }

    public q a(int arg4) {
        switch(arg4) {
            case 0: {
                goto label_12;
            }
            case 1: {
                goto label_10;
            }
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Invalid parameter for TransitionSet ordering: ");
        v1.append(arg4);
        throw new AndroidRuntimeException(v1.toString());
    label_10:
        boolean v4 = false;
        goto label_13;
    label_12:
        v4 = true;
    label_13:
        this.k = v4;
        return this;
    }

    public m a(long arg1) {
        return this.c(arg1);
    }

    public m a(TimeInterpolator arg1) {
        return this.b(arg1);
    }

    public m a(c arg1) {
        return this.c(arg1);
    }

    String a(String arg6) {
        String v0 = super.a(arg6);
        int v1;
        for(v1 = 0; v1 < this.j.size(); ++v1) {
            StringBuilder v2 = new StringBuilder();
            v2.append(v0);
            v2.append("\n");
            Object v0_1 = this.j.get(v1);
            StringBuilder v3 = new StringBuilder();
            v3.append(arg6);
            v3.append("  ");
            v2.append(((m)v0_1).a(v3.toString()));
            v0 = v2.toString();
        }

        return v0;
    }

    public void a(g arg3) {
        super.a(arg3);
        this.l |= 4;
        int v0;
        for(v0 = 0; v0 < this.j.size(); ++v0) {
            this.j.get(v0).a(arg3);
        }
    }

    public void a(b arg4) {
        super.a(arg4);
        this.l |= 8;
        int v0 = this.j.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.j.get(v1).a(arg4);
        }
    }

    public void a(p arg4) {
        super.a(arg4);
        this.l |= 2;
        int v0 = this.j.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.j.get(v1).a(arg4);
        }
    }

    public void a(s arg4) {
        if(this.a(arg4.b)) {
            Iterator v0 = this.j.iterator();
            while(v0.hasNext()) {
                Object v1 = v0.next();
                if(!((m)v1).a(arg4.b)) {
                    continue;
                }

                ((m)v1).a(arg4);
                arg4.c.add(v1);
            }
        }
    }

    protected void a(ViewGroup arg13, t arg14, t arg15, ArrayList arg16, ArrayList arg17) {
        q v0 = this;
        long v1 = this.c();
        int v3 = v0.j.size();
        int v4;
        for(v4 = 0; v4 < v3; ++v4) {
            Object v6 = v0.j.get(v4);
            long v7 = 0;
            if(v1 > v7 && ((v0.k) || v4 == 0)) {
                long v9 = ((m)v6).c();
                if(v9 > v7) {
                    ((m)v6).b(v9 + v1);
                    goto label_20;
                }

                ((m)v6).b(v1);
            }

        label_20:
            ((m)v6).a(arg13, arg14, arg15, arg16, arg17);
        }
    }

    public m b(int arg2) {
        if(arg2 >= 0) {
            if(arg2 >= this.j.size()) {
            }
            else {
                return this.j.get(arg2);
            }
        }

        return null;
    }

    public q b(TimeInterpolator arg4) {
        this.l |= 1;
        if(this.j != null) {
            int v0 = this.j.size();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                this.j.get(v1).a(arg4);
            }
        }

        return super.a(arg4);
    }

    public m b(long arg1) {
        return this.d(arg1);
    }

    public m b(c arg1) {
        return this.d(arg1);
    }

    public m b(View arg1) {
        return this.f(arg1);
    }

    public void b(s arg4) {
        if(this.a(arg4.b)) {
            Iterator v0 = this.j.iterator();
            while(v0.hasNext()) {
                Object v1 = v0.next();
                if(!((m)v1).a(arg4.b)) {
                    continue;
                }

                ((m)v1).b(arg4);
                arg4.c.add(v1);
            }
        }
    }

    public q c(long arg6) {
        super.a(arg6);
        if(this.a >= 0) {
            int v0 = this.j.size();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                this.j.get(v1).a(arg6);
            }
        }

        return this;
    }

    public q c(c arg1) {
        return super.a(arg1);
    }

    public m c(View arg1) {
        return this.g(arg1);
    }

    void c(s arg4) {
        super.c(arg4);
        int v0 = this.j.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.j.get(v1).c(arg4);
        }
    }

    public Object clone() {
        return this.o();
    }

    public q d(long arg1) {
        return super.b(arg1);
    }

    public q d(c arg1) {
        return super.b(arg1);
    }

    public void d(View arg4) {
        super.d(arg4);
        int v0 = this.j.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.j.get(v1).d(arg4);
        }
    }

    protected void e() {
        if(this.j.isEmpty()) {
            this.j();
            this.k();
            return;
        }

        this.r();
        if(!this.k) {
            int v0;
            for(v0 = 1; v0 < this.j.size(); ++v0) {
                this.j.get(v0 - 1).a(new n(this.j.get(v0)) {
                    public void a(m arg2) {
                        this.a.e();
                        arg2.b(((c)this));
                    }
                });
            }

            Object v0_1 = this.j.get(0);
            if(v0_1 != null) {
                ((m)v0_1).e();
            }
        }
        else {
            Iterator v0_2 = this.j.iterator();
            while(v0_2.hasNext()) {
                v0_2.next().e();
            }
        }
    }

    public void e(View arg4) {
        super.e(arg4);
        int v0 = this.j.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.j.get(v1).e(arg4);
        }
    }

    public q f(View arg3) {
        int v0;
        for(v0 = 0; v0 < this.j.size(); ++v0) {
            this.j.get(v0).b(arg3);
        }

        return super.b(arg3);
    }

    public q g(View arg3) {
        int v0;
        for(v0 = 0; v0 < this.j.size(); ++v0) {
            this.j.get(v0).c(arg3);
        }

        return super.c(arg3);
    }

    public m o() {
        m v0 = super.o();
        ((q)v0).j = new ArrayList();
        int v1 = this.j.size();
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            ((q)v0).a(this.j.get(v2).o());
        }

        return v0;
    }

    public int q() {
        return this.j.size();
    }

    private void r() {
        a v0 = new a(this);
        Iterator v1 = this.j.iterator();
        while(v1.hasNext()) {
            v1.next().a(((c)v0));
        }

        this.h = this.j.size();
    }
}

