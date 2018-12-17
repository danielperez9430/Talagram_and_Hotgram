package com.google.ads.interactivemedia.v3.internal;

import java.util.Collection;
import java.util.HashSet;
import org.json.JSONObject;

public abstract class al extends am {
    protected final HashSet a;
    protected final JSONObject b;
    protected final double c;

    public al(b arg1, HashSet arg2, JSONObject arg3, double arg4) {
        super(arg1);
        this.a = new HashSet(((Collection)arg2));
        this.b = arg3;
        this.c = arg4;
    }
}

