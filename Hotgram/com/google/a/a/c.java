package com.google.a.a;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented @Retention(value=RetentionPolicy.RUNTIME) @Target(value={ElementType.FIELD, ElementType.METHOD}) @public interface c {
    String a();

    String[] b();
}

