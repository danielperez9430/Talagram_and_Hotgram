package org.telegram.customization.i;

public enum g {
    public static final enum g a;
    public static final enum g b;
    public static final enum g c;
    public static final enum g d;
    public static final enum g e;
    public static final enum g f;
    public static final enum g g;
    public static final enum g h;
    public static final enum g i;
    public static final enum g j;
    public static final enum g k;
    public static final enum g l;
    public static final enum g m;
    public static final enum g n;
    public static final enum g o;
    public static final enum g p;
    public static final enum g q;
    public static final enum g r;
    public static final enum g s;
    public static final enum g t;

    static {
        g.a = new g("register", 0);
        g.b = new g("getProxy", 1);
        g.c = new g("getConfig", 2);
        g.d = new g("checkUrl", 3);
        g.e = new g("sendStats", 4);
        g.f = new g("saveInfo", 5);
        g.g = new g("saveChannels", 6);
        g.h = new g("saveContacts", 7);
        g.i = new g("getContentFilter", 8);
        g.j = new g("getTheme", 9);
        g.k = new g("getFilterChannels", 10);
        g.l = new g("getJob", 11);
        g.m = new g("checkSafe", 12);
        g.n = new g("callWhatsUp", 13);
        g.o = new g("launch", 14);
        g.p = new g("callRequest", 15);
        g.q = new g("rateCall", 16);
        g.r = new g("startCall", 17);
        g.s = new g("endCall", 18);
        g.t = new g("getWhatsUp", 19);
        g.u = new g[]{g.a, g.b, g.c, g.d, g.e, g.f, g.g, g.h, g.i, g.j, g.k, g.l, g.m, g.n, g.o, g.p, g.q, g.r, g.s, g.t};
    }

    private g(String arg1, int arg2) {
        super(arg1, arg2);
    }

    public static g valueOf(String arg1) {
        return Enum.valueOf(g.class, arg1);
    }

    public static g[] values() {
        // Method was not decompiled
    }
}

