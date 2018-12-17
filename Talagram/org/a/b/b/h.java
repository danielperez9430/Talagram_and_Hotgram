package org.a.b.b;

import java.lang.reflect.Modifier;

class h {
    boolean a;
    boolean b;
    boolean c;
    boolean d;
    boolean e;
    boolean f;
    boolean g;
    boolean h;
    int i;
    static h j;
    static h k;
    static h l;

    static {
        h.j = new h();
        h.j.a = true;
        h.j.b = false;
        h.j.c = false;
        h.j.d = false;
        h.j.e = true;
        h.j.f = false;
        h.j.g = false;
        h.j.i = 0;
        h.k = new h();
        h.k.a = true;
        h.k.b = true;
        h.k.c = false;
        h.k.d = false;
        h.k.e = false;
        h.j.i = 1;
        h.l = new h();
        h.l.a = false;
        h.l.b = true;
        h.l.c = false;
        h.l.d = true;
        h.l.e = false;
        h.l.h = false;
        h.l.i = 2;
    }

    h() {
        super();
        this.a = true;
        this.b = true;
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = true;
        this.g = true;
        this.h = true;
    }

    String a(int arg2) {
        if(!this.d) {
            return "";
        }

        String v2 = Modifier.toString(arg2);
        if(v2.length() == 0) {
            return "";
        }

        StringBuffer v0 = new StringBuffer();
        v0.append(v2);
        v0.append(" ");
        return v0.toString();
    }

    public String a(Class arg3) {
        return this.a(arg3, arg3.getName(), this.a);
    }

    String a(Class arg2, String arg3, boolean arg4) {
        if(arg2 == null) {
            return "ANONYMOUS";
        }

        if(arg2.isArray()) {
            arg2 = arg2.getComponentType();
            StringBuffer v3 = new StringBuffer();
            v3.append(this.a(arg2, arg2.getName(), arg4));
            v3.append("[]");
            return v3.toString();
        }

        char v2 = '.';
        char v0 = '$';
        if(arg4) {
            return this.b(arg3).replace(v0, v2);
        }

        return arg3.replace(v0, v2);
    }

    public String a(Class arg2, String arg3) {
        return this.a(arg2, arg3, this.e);
    }

    String a(String arg3) {
        int v0 = arg3.lastIndexOf(45);
        if(v0 == -1) {
            return arg3;
        }

        return arg3.substring(v0 + 1);
    }

    public void a(StringBuffer arg3, Class[] arg4) {
        int v0;
        for(v0 = 0; v0 < arg4.length; ++v0) {
            if(v0 > 0) {
                arg3.append(", ");
            }

            arg3.append(this.a(arg4[v0]));
        }
    }

    String b(String arg3) {
        int v0 = arg3.lastIndexOf(46);
        if(v0 == -1) {
            return arg3;
        }

        return arg3.substring(v0 + 1);
    }

    public void b(StringBuffer arg2, Class[] arg3) {
        String v3;
        if(arg3 == null) {
            return;
        }

        if(this.b) {
            arg2.append("(");
            this.a(arg2, arg3);
            v3 = ")";
        }
        else if(arg3.length == 0) {
            v3 = "()";
        }
        else {
            v3 = "(..)";
        }

        arg2.append(v3);
    }

    public void c(StringBuffer arg2, Class[] arg3) {
        if((this.c) && arg3 != null) {
            if(arg3.length == 0) {
            }
            else {
                arg2.append(" throws ");
                this.a(arg2, arg3);
            }
        }
    }
}

