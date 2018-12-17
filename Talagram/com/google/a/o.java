package com.google.a;

import com.google.a.b.g;
import java.util.Set;

public final class o extends l {
    private final g a;

    public o() {
        super();
        this.a = new g();
    }

    public void a(String arg2, l arg3) {
        n v3;
        if(arg3 == null) {
            v3 = n.a;
        }

        this.a.put(arg2, v3);
    }

    public l a(String arg2) {
        return this.a.remove(arg2);
    }

    public l b(String arg2) {
        return this.a.get(arg2);
    }

    public boolean equals(Object arg2) {
        boolean v2;
        if((((o)arg2)) != this) {
            if(((arg2 instanceof o)) && (((o)arg2).a.equals(this.a))) {
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

