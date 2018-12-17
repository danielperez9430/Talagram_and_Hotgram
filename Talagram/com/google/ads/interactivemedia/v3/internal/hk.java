package com.google.ads.interactivemedia.v3.internal;

import java.lang.annotation.Annotation;

public final class hk implements gq {
    private final gy a;

    public hk(gy arg1) {
        super();
        this.a = arg1;
    }

    public gp a(fz arg3, hw arg4) {
        Annotation v0 = arg4.a().getAnnotation(gs.class);
        if(v0 == null) {
            return null;
        }

        return this.a(this.a, arg3, arg4, ((gs)v0));
    }

    gp a(gy arg8, fz arg9, hw arg10, gs arg11) {
        gm v2_1;
        gp v8_1;
        Object v8 = arg8.a(hw.b(arg11.a())).a();
        if((v8 instanceof gp)) {
        }
        else if((v8 instanceof gq)) {
            v8_1 = ((gq)v8).a(arg9, arg10);
        }
        else {
            boolean v11 = v8 instanceof gm;
            if(!v11) {
                if((v8 instanceof ge)) {
                }
                else {
                    throw new IllegalArgumentException("@JsonAdapter value must be TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer reference.");
                }
            }

            Object v0 = null;
            if(v11) {
                Object v2 = v8;
            }
            else {
                v2_1 = ((gm)v0);
            }

            if((v8 instanceof ge)) {
                v0 = v8;
            }

            hs v8_2 = new hs(v2_1, v0, arg9, arg10, null);
        }

        if(v8 != null) {
            v8_1 = ((gp)v8).nullSafe();
        }

        return ((gp)v8);
    }
}

