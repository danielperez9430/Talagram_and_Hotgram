package com.google.ads.interactivemedia.v3.impl.data;

final class l extends s {
    class com.google.ads.interactivemedia.v3.impl.data.l$1 {
    }

    final class a extends com.google.ads.interactivemedia.v3.impl.data.s$a {
        private Float volume;

        a() {
            super();
        }

        public s build() {
            String v0 = "";
            if(this.volume == null) {
                v0 = String.valueOf(v0).concat(" volume");
            }

            if(!v0.isEmpty()) {
                String v2 = "Missing required properties:";
                v0 = String.valueOf(v0);
                v0 = v0.length() != 0 ? v2.concat(v0) : new String(v2);
                throw new IllegalStateException(v0);
            }

            return new l(this.volume.floatValue(), null);
        }

        public com.google.ads.interactivemedia.v3.impl.data.s$a volume(float arg1) {
            this.volume = Float.valueOf(arg1);
            return this;
        }
    }

    private final float volume;

    private l(float arg1) {
        super();
        this.volume = arg1;
    }

    l(float arg1, com.google.ads.interactivemedia.v3.impl.data.l$1 arg2) {
        this(arg1);
    }

    public boolean equals(Object arg4) {
        boolean v0 = true;
        if((((l)arg4)) == this) {
            return 1;
        }

        if((arg4 instanceof s)) {
            if(Float.floatToIntBits(this.volume) == Float.floatToIntBits(((s)arg4).volume())) {
            }
            else {
                v0 = false;
            }

            return v0;
        }

        return 0;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.volume) ^ 1000003;
    }

    public String toString() {
        float v0 = this.volume;
        StringBuilder v1 = new StringBuilder(40);
        v1.append("VolumeUpdateData{volume=");
        v1.append(v0);
        v1.append("}");
        return v1.toString();
    }

    public float volume() {
        return this.volume;
    }
}

