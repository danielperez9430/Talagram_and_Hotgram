package com.google.a.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value=RetentionPolicy.RUNTIME) @Target(value={ElementType.TYPE, ElementType.FIELD}) @public interface b {
    Class a();

    boolean b();
}

