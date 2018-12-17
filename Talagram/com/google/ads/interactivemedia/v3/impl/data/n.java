package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.ki;

@ki(a=g.class) public abstract class n {
    public n() {
        super();
    }

    private static n create(double arg7, double arg9, boolean arg11) {
        return new g(arg7, arg9, arg11);
    }

    public abstract double end();

    public abstract boolean played();

    public abstract double start();
}

