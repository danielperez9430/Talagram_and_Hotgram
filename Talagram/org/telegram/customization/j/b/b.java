package org.telegram.customization.j.b;

import b.a.c;
import org.telegram.customization.Activities.r;
import org.telegram.customization.Activities.s;
import org.telegram.customization.i.l;
import org.telegram.customization.j.c.d;
import org.telegram.customization.j.c.e;
import org.telegram.customization.j.c.f;
import org.telegram.customization.j.c.g;
import org.telegram.customization.j.c.h;
import org.telegram.customization.j.c.i;
import org.telegram.customization.j.c.j;
import org.telegram.customization.j.c.k;
import org.telegram.customization.j.c.m;
import org.telegram.customization.j.c.n;
import org.telegram.customization.j.c.o;
import org.telegram.customization.voip.SipWhatsUp;
import org.telegram.customization.voip.SipWhatsUp_MembersInjector;
import org.telegram.customization.voip.linphoneSip.linphone.LinphoneManager;
import org.telegram.customization.voip.linphoneSip.linphone.LinphoneManager_MembersInjector;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ApplicationLoader_MembersInjector;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BaseFragment_MembersInjector;

public final class b implements a {
    class org.telegram.customization.j.b.b$1 {
    }

    public final class org.telegram.customization.j.b.b$a {
        private org.telegram.customization.j.c.a a;
        private k b;
        private i c;

        org.telegram.customization.j.b.b$a(org.telegram.customization.j.b.b$1 arg1) {
            this();
        }

        private org.telegram.customization.j.b.b$a() {
            super();
        }

        static org.telegram.customization.j.c.a a(org.telegram.customization.j.b.b$a arg0) {
            return arg0.a;
        }

        public a a() {
            if(this.a != null) {
                if(this.b == null) {
                    this.b = new k();
                }

                if(this.c == null) {
                    this.c = new i();
                }

                return new b(this, null);
            }

            StringBuilder v1 = new StringBuilder();
            v1.append(org.telegram.customization.j.c.a.class.getCanonicalName());
            v1.append(" must be set");
            throw new IllegalStateException(v1.toString());
        }

        public org.telegram.customization.j.b.b$a a(org.telegram.customization.j.c.a arg1) {
            this.a = c.a(arg1);
            return this;
        }

        public org.telegram.customization.j.b.b$a a(i arg1) {
            this.c = c.a(arg1);
            return this;
        }

        public org.telegram.customization.j.b.b$a a(k arg1) {
            this.b = c.a(arg1);
            return this;
        }

        static k b(org.telegram.customization.j.b.b$a arg0) {
            return arg0.b;
        }

        static i c(org.telegram.customization.j.b.b$a arg0) {
            return arg0.c;
        }
    }

    private javax.a.a b;
    private javax.a.a c;
    private b.a d;
    private javax.a.a e;
    private javax.a.a f;
    private javax.a.a g;
    private javax.a.a h;
    private javax.a.a i;
    private javax.a.a j;
    private javax.a.a k;
    private javax.a.a l;
    private b.a m;
    private b.a n;
    private javax.a.a o;
    private b.a p;
    private b.a q;
    private b.a r;
    private b.a s;
    private b.a t;
    private b.a u;
    private javax.a.a v;
    private b.a w;

    static {
        b.a = b.class.desiredAssertionStatus() ^ 1;
    }

    private b(org.telegram.customization.j.b.b$a arg2) {
        super();
        if(!b.a) {
            if(arg2 != null) {
            }
            else {
                throw new AssertionError();
            }
        }

        this.a(arg2);
    }

    b(org.telegram.customization.j.b.b$a arg1, org.telegram.customization.j.b.b$1 arg2) {
        this(arg1);
    }

    private void a(org.telegram.customization.j.b.b$a arg12) {
        this.b = b.a.a.a(org.telegram.customization.j.c.c.a(org.telegram.customization.j.b.b$a.a(arg12)));
        this.c = b.a.a.a(d.a(org.telegram.customization.j.b.b$a.a(arg12), this.b));
        this.d = l.a(this.c);
        this.e = b.a.a.a(org.telegram.customization.j.c.l.a(org.telegram.customization.j.b.b$a.b(arg12)));
        this.f = b.a.a.a(j.a(org.telegram.customization.j.b.b$a.c(arg12)));
        this.g = b.a.a.a(e.a(org.telegram.customization.j.b.b$a.a(arg12), this.b));
        this.h = b.a.a.a(org.telegram.customization.j.c.b.a(org.telegram.customization.j.b.b$a.a(arg12), this.b));
        this.i = b.a.a.a(g.a(org.telegram.customization.j.b.b$a.a(arg12), this.b));
        this.j = b.a.a.a(f.a(org.telegram.customization.j.b.b$a.a(arg12), this.b));
        this.k = b.a.a.a(m.a(org.telegram.customization.j.b.b$a.b(arg12)));
        this.l = b.a.a.a(n.a(org.telegram.customization.j.b.b$a.b(arg12)));
        this.m = org.telegram.customization.j.a.b.a(this.e, this.f, this.b, this.c, this.g, this.h, this.i, this.j, this.k, this.l);
        this.n = org.telegram.customization.Activities.f.a(this.e, this.j, this.f, this.g);
        this.o = b.a.a.a(h.a(org.telegram.customization.j.b.b$a.a(arg12), this.b));
        this.p = org.telegram.customization.service.b.a(this.e, this.o, this.i);
        this.q = BaseFragment_MembersInjector.create(this.e, this.h, this.o);
        this.r = ApplicationLoader_MembersInjector.create(this.e, this.c, this.g, this.j, this.k);
        this.s = s.a(this.e);
        this.t = org.telegram.customization.Activities.b.a(this.h);
        this.u = LinphoneManager_MembersInjector.create(this.g);
        this.v = b.a.a.a(o.a(org.telegram.customization.j.b.b$a.b(arg12)));
        this.w = SipWhatsUp_MembersInjector.create(this.v);
    }

    public static org.telegram.customization.j.b.b$a a() {
        return new org.telegram.customization.j.b.b$a(null);
    }

    public void a(org.telegram.customization.Activities.a arg2) {
        this.t.injectMembers(arg2);
    }

    public void a(org.telegram.customization.Activities.e arg2) {
        this.n.injectMembers(arg2);
    }

    public void a(r arg2) {
        this.s.injectMembers(arg2);
    }

    public void a(org.telegram.customization.i.k arg2) {
        this.d.injectMembers(arg2);
    }

    public void a(org.telegram.customization.j.a.a arg2) {
        this.m.injectMembers(arg2);
    }

    public void a(org.telegram.customization.service.a arg2) {
        this.p.injectMembers(arg2);
    }

    public void a(SipWhatsUp arg2) {
        this.w.injectMembers(arg2);
    }

    public void a(LinphoneManager arg2) {
        this.u.injectMembers(arg2);
    }

    public void a(ApplicationLoader arg2) {
        this.r.injectMembers(arg2);
    }

    public void a(BaseFragment arg2) {
        this.q.injectMembers(arg2);
    }
}

