package com.google.ads.interactivemedia.v3.impl.data;

final class j extends b {
    class com.google.ads.interactivemedia.v3.impl.data.j$1 {
    }

    final class a extends com.google.ads.interactivemedia.v3.impl.data.q$b$a {
        private Boolean attached;
        private m bounds;
        private Boolean hidden;
        private String type;

        a() {
            super();
        }

        public com.google.ads.interactivemedia.v3.impl.data.q$b$a attached(boolean arg1) {
            this.attached = Boolean.valueOf(arg1);
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.q$b$a bounds(m arg2) {
            if(arg2 != null) {
                this.bounds = arg2;
                return this;
            }

            throw new NullPointerException("Null bounds");
        }

        public b build() {
            String v0 = "";
            if(this.attached == null) {
                v0 = String.valueOf(v0).concat(" attached");
            }

            if(this.bounds == null) {
                v0 = String.valueOf(v0).concat(" bounds");
            }

            if(this.hidden == null) {
                v0 = String.valueOf(v0).concat(" hidden");
            }

            if(this.type == null) {
                v0 = String.valueOf(v0).concat(" type");
            }

            if(!v0.isEmpty()) {
                String v2 = "Missing required properties:";
                v0 = String.valueOf(v0);
                v0 = v0.length() != 0 ? v2.concat(v0) : new String(v2);
                throw new IllegalStateException(v0);
            }

            return new j(this.attached.booleanValue(), this.bounds, this.hidden.booleanValue(), this.type, null);
        }

        public com.google.ads.interactivemedia.v3.impl.data.q$b$a hidden(boolean arg1) {
            this.hidden = Boolean.valueOf(arg1);
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.q$b$a type(String arg2) {
            if(arg2 != null) {
                this.type = arg2;
                return this;
            }

            throw new NullPointerException("Null type");
        }
    }

    private final boolean attached;
    private final m bounds;
    private final boolean hidden;
    private final String type;

    private j(boolean arg1, m arg2, boolean arg3, String arg4) {
        super();
        this.attached = arg1;
        this.bounds = arg2;
        this.hidden = arg3;
        this.type = arg4;
    }

    j(boolean arg1, m arg2, boolean arg3, String arg4, com.google.ads.interactivemedia.v3.impl.data.j$1 arg5) {
        this(arg1, arg2, arg3, arg4);
    }

    boolean attached() {
        return this.attached;
    }

    m bounds() {
        return this.bounds;
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if((((j)arg5)) == this) {
            return 1;
        }

        if((arg5 instanceof b)) {
            if(this.attached != ((b)arg5).attached() || !this.bounds.equals(((b)arg5).bounds()) || this.hidden != ((b)arg5).hidden() || !this.type.equals(((b)arg5).type())) {
                v0 = false;
            }
            else {
            }

            return v0;
        }

        return 0;
    }

    public int hashCode() {
        int v1 = 1237;
        int v0 = this.attached ? 1231 : 1237;
        int v3 = 1000003;
        v0 = ((v0 ^ v3) * v3 ^ this.bounds.hashCode()) * v3;
        if(this.hidden) {
            v1 = 1231;
        }

        return (v0 ^ v1) * v3 ^ this.type.hashCode();
    }

    boolean hidden() {
        return this.hidden;
    }

    public String toString() {
        boolean v0 = this.attached;
        String v1 = String.valueOf(this.bounds);
        boolean v2 = this.hidden;
        String v3 = this.type;
        StringBuilder v5 = new StringBuilder(String.valueOf(v1).length() + 61 + String.valueOf(v3).length());
        v5.append("ObstructionData{attached=");
        v5.append(v0);
        v5.append(", bounds=");
        v5.append(v1);
        v5.append(", hidden=");
        v5.append(v2);
        v5.append(", type=");
        v5.append(v3);
        v5.append("}");
        return v5.toString();
    }

    String type() {
        return this.type;
    }
}

