package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.ki;

@ki(a=l.class) public abstract class s {
    public abstract class a {
        public a() {
            super();
        }

        public abstract s build();

        public abstract a volume(float arg1);

        public a volumePercentage(int arg2) {
            return this.volume((((float)arg2)) / 100f);
        }
    }

    public s() {
        super();
    }

    public static a builder() {
        return new com.google.ads.interactivemedia.v3.impl.data.l$a();
    }

    public abstract float volume();
}

