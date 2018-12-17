package com.googlecode.mp4parser.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented @Inherited @Retention(value=RetentionPolicy.RUNTIME) @Target(value={ElementType.METHOD, ElementType.TYPE}) @public interface ParseDetail {
}

