package com.google.ads.interactivemedia.v3.internal;

import java.util.Set;

public final class gi extends gf {
    private final hc a;

    public gi() {
        super();
        this.a = new hc();
    }

    public void a(String arg2, gf arg3) {
        gh v3;
        if(arg3 == null) {
            v3 = gh.a;
        }

        this.a.put(arg2, v3);
    }

    public boolean equals(Object arg2) {
        boolean v2;
        if((((gi)arg2)) != this) {
            if(((arg2 instanceof gi)) && (((gi)arg2).a.equals(this.a))) {
                goto label_10;
            }

            v2 = false;
        }
        else {
        label_10:
            v2 = true;
        }

        return v2;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public Set o() {
        return this.a.entrySet();
    }
}

