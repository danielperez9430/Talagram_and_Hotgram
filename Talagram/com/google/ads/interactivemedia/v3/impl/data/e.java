package com.google.ads.interactivemedia.v3.impl.data;

final class e extends m {
    class com.google.ads.interactivemedia.v3.impl.data.e$1 {
    }

    final class a extends com.google.ads.interactivemedia.v3.impl.data.m$a {
        private Integer height;
        private Integer left;
        private Integer top;
        private Integer width;

        a() {
            super();
        }

        public m build() {
            String v0 = "";
            if(this.left == null) {
                v0 = String.valueOf(v0).concat(" left");
            }

            if(this.top == null) {
                v0 = String.valueOf(v0).concat(" top");
            }

            if(this.height == null) {
                v0 = String.valueOf(v0).concat(" height");
            }

            if(this.width == null) {
                v0 = String.valueOf(v0).concat(" width");
            }

            if(!v0.isEmpty()) {
                String v2 = "Missing required properties:";
                v0 = String.valueOf(v0);
                v0 = v0.length() != 0 ? v2.concat(v0) : new String(v2);
                throw new IllegalStateException(v0);
            }

            return new e(this.left.intValue(), this.top.intValue(), this.height.intValue(), this.width.intValue(), null);
        }

        public com.google.ads.interactivemedia.v3.impl.data.m$a height(int arg1) {
            this.height = Integer.valueOf(arg1);
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.m$a left(int arg1) {
            this.left = Integer.valueOf(arg1);
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.m$a top(int arg1) {
            this.top = Integer.valueOf(arg1);
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.m$a width(int arg1) {
            this.width = Integer.valueOf(arg1);
            return this;
        }
    }

    private final int height;
    private final int left;
    private final int top;
    private final int width;

    private e(int arg1, int arg2, int arg3, int arg4) {
        super();
        this.left = arg1;
        this.top = arg2;
        this.height = arg3;
        this.width = arg4;
    }

    e(int arg1, int arg2, int arg3, int arg4, com.google.ads.interactivemedia.v3.impl.data.e$1 arg5) {
        this(arg1, arg2, arg3, arg4);
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if((((e)arg5)) == this) {
            return 1;
        }

        if((arg5 instanceof m)) {
            if(this.left != ((m)arg5).left() || this.top != ((m)arg5).top() || this.height != ((m)arg5).height() || this.width != ((m)arg5).width()) {
                v0 = false;
            }
            else {
            }

            return v0;
        }

        return 0;
    }

    public int hashCode() {
        return (((this.left ^ 1000003) * 1000003 ^ this.top) * 1000003 ^ this.height) * 1000003 ^ this.width;
    }

    public int height() {
        return this.height;
    }

    public int left() {
        return this.left;
    }

    public String toString() {
        int v0 = this.left;
        int v1 = this.top;
        int v2 = this.height;
        int v3 = this.width;
        StringBuilder v4 = new StringBuilder(90);
        v4.append("BoundingRectData{left=");
        v4.append(v0);
        v4.append(", top=");
        v4.append(v1);
        v4.append(", height=");
        v4.append(v2);
        v4.append(", width=");
        v4.append(v3);
        v4.append("}");
        return v4.toString();
    }

    public int top() {
        return this.top;
    }

    public int width() {
        return this.width;
    }
}

