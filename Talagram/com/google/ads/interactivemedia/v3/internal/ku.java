package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;

final class ku extends lh implements Serializable {
    final kn a;
    final lh b;

    ku(kn arg1, lh arg2) {
        super();
        this.a = kr.a(arg1);
        this.b = kr.a(arg2);
    }

    public int compare(Object arg3, Object arg4) {
        return this.b.compare(this.a.a(arg3), this.a.a(arg4));
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if((((ku)arg5)) == this) {
            return 1;
        }

        if((arg5 instanceof ku)) {
            if(!this.a.equals(((ku)arg5).a) || !this.b.equals(((ku)arg5).b)) {
                v0 = false;
            }
            else {
            }

            return v0;
        }

        return 0;
    }

    public int hashCode() {
        return ko.a(new Object[]{this.a, this.b});
    }

    public String toString() {
        String v0 = String.valueOf(this.b);
        String v1 = String.valueOf(this.a);
        StringBuilder v3 = new StringBuilder(String.valueOf(v0).length() + 13 + String.valueOf(v1).length());
        v3.append(v0);
        v3.append(".onResultOf(");
        v3.append(v1);
        v3.append(")");
        return v3.toString();
    }
}

