package com.google.a.b.a;

import com.google.a.a.b;
import com.google.a.b.c;
import com.google.a.c.a;
import com.google.a.f;
import com.google.a.k;
import com.google.a.s;
import com.google.a.v;
import com.google.a.w;
import java.lang.annotation.Annotation;

public final class d implements w {
    private final c a;

    public d(c arg1) {
        super();
        this.a = arg1;
    }

    v a(c arg9, f arg10, a arg11, b arg12) {
        Object v3;
        v v9_1;
        Object v9 = arg9.a(a.b(arg12.a())).a();
        if((v9 instanceof v)) {
        }
        else if((v9 instanceof w)) {
            v9_1 = ((w)v9).create(arg10, arg11);
        }
        else {
            boolean v0 = v9 instanceof s;
            if(!v0) {
                if((v9 instanceof k)) {
                }
                else {
                    StringBuilder v12 = new StringBuilder();
                    v12.append("Invalid attempt to bind an instance of ");
                    v12.append(v9.getClass().getName());
                    v12.append(" as a @JsonAdapter for ");
                    v12.append(arg11.toString());
                    v12.append(". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
                    throw new IllegalArgumentException(v12.toString());
                }
            }

            Object v1 = null;
            if(v0) {
                v3 = v9;
            }
            else {
                s v3_1 = ((s)v1);
            }

            if((v9 instanceof k)) {
                v1 = v9;
            }

            l v9_2 = new l(((s)v3), v1, arg10, arg11, null);
        }

        if(v9_1 != null && (arg12.b())) {
            v9_1 = v9_1.nullSafe();
        }

        return v9_1;
    }

    public v create(f arg3, a arg4) {
        Annotation v0 = arg4.a().getAnnotation(b.class);
        if(v0 == null) {
            return null;
        }

        return this.a(this.a, arg3, arg4, ((b)v0));
    }
}

