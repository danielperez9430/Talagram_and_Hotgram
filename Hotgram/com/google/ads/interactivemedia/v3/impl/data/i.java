package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.la;
import java.util.Collection;
import java.util.List;

final class i extends q {
    class com.google.ads.interactivemedia.v3.impl.data.i$1 {
    }

    final class a extends com.google.ads.interactivemedia.v3.impl.data.q$a {
        private la obstructions;

        a() {
            super();
        }

        public q build() {
            String v0 = "";
            if(this.obstructions == null) {
                v0 = String.valueOf(v0).concat(" obstructions");
            }

            if(!v0.isEmpty()) {
                String v2 = "Missing required properties:";
                v0 = String.valueOf(v0);
                v0 = v0.length() != 0 ? v2.concat(v0) : new String(v2);
                throw new IllegalStateException(v0);
            }

            return new i(this.obstructions, null);
        }

        public com.google.ads.interactivemedia.v3.impl.data.q$a obstructions(List arg1) {
            this.obstructions = la.a(((Collection)arg1));
            return this;
        }
    }

    private final la obstructions;

    private i(la arg1) {
        super();
        this.obstructions = arg1;
    }

    i(la arg1, com.google.ads.interactivemedia.v3.impl.data.i$1 arg2) {
        this(arg1);
    }

    public boolean equals(Object arg2) {
        if((((i)arg2)) == this) {
            return 1;
        }

        if((arg2 instanceof q)) {
            return this.obstructions.equals(((q)arg2).obstructions());
        }

        return 0;
    }

    public int hashCode() {
        return this.obstructions.hashCode() ^ 1000003;
    }

    la obstructions() {
        return this.obstructions;
    }

    public String toString() {
        String v0 = String.valueOf(this.obstructions);
        StringBuilder v2 = new StringBuilder(String.valueOf(v0).length() + 34);
        v2.append("ObstructionListData{obstructions=");
        v2.append(v0);
        v2.append("}");
        return v2.toString();
    }
}

