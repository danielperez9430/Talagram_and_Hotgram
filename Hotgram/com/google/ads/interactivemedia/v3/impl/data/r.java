package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.UiElement;
import com.google.ads.interactivemedia.v3.internal.gp;
import com.google.ads.interactivemedia.v3.internal.hx;
import com.google.ads.interactivemedia.v3.internal.hy;
import com.google.ads.interactivemedia.v3.internal.hz;
import com.google.ads.interactivemedia.v3.internal.ko;

public class r implements UiElement {
    class com.google.ads.interactivemedia.v3.impl.data.r$1 extends gp {
        com.google.ads.interactivemedia.v3.impl.data.r$1() {
            super();
        }

        public r read(hx arg3) {
            if(arg3.f() == hy.i) {
                arg3.j();
                return null;
            }

            return new r(arg3.h());
        }

        public Object read(hx arg1) {
            return this.read(arg1);
        }

        public void write(hz arg1, r arg2) {
            if(arg2 == null) {
                arg1.f();
                return;
            }

            arg1.b(arg2.getName());
        }

        public void write(hz arg1, Object arg2) {
            this.write(arg1, ((r)arg2));
        }
    }

    public static final gp GSON_TYPE_ADAPTER;
    private final String name;

    static {
        r.GSON_TYPE_ADAPTER = new com.google.ads.interactivemedia.v3.impl.data.r$1();
    }

    public r(String arg1) {
        super();
        this.name = arg1;
    }

    public boolean equals(Object arg3) {
        if(this == (((r)arg3))) {
            return 1;
        }

        if(arg3 == null) {
            return 0;
        }

        if(!(arg3 instanceof r)) {
            return 0;
        }

        return this.name.equals(((r)arg3).name);
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        return ko.a(new Object[]{this.name});
    }

    public String toString() {
        String v0 = this.name;
        StringBuilder v2 = new StringBuilder(String.valueOf(v0).length() + 20);
        v2.append("UiElementImpl[name=");
        v2.append(v0);
        v2.append("]");
        return v2.toString();
    }
}

