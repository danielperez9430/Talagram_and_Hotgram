package com.google.android.gms.common.internal.safeparcel;

import android.os.Parcelable;

public interface SafeParcelable extends Parcelable {
    @public interface Class {
        String creator();

        boolean validate();
    }

    @public interface Constructor {
    }

    @public interface Field {
        String defaultValue();

        String defaultValueUnchecked();

        String getter();

        int id();

        String type();
    }

    @public interface Indicator {
        String getter();
    }

    @public interface Param {
        int id();
    }

    @public interface Reserved {
        int[] value();
    }

    @public interface VersionField {
        String getter();

        int id();

        String type();
    }

    public static final String NULL = "SAFE_PARCELABLE_NULL_STRING";

}

