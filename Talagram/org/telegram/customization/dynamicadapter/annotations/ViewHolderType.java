package org.telegram.customization.dynamicadapter.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value=RetentionPolicy.RUNTIME) @Target(value={ElementType.TYPE}) @public interface ViewHolderType {
    Class model();

    int type();
}

