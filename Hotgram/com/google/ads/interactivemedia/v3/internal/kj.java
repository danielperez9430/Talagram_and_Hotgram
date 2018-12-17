package com.google.ads.interactivemedia.v3.internal;

import java.lang.annotation.Annotation;
import java.util.Arrays;

public class kj implements fv {
    public kj() {
        super();
    }

    public boolean a(fw arg3) {
        Annotation v0 = arg3.a().getAnnotation(ki.class);
        boolean v3 = v0 == null || !Arrays.asList(((ki)v0).b()).contains(arg3.b()) ? false : true;
        return v3;
    }

    public boolean a(Class arg1) {
        return 0;
    }
}

