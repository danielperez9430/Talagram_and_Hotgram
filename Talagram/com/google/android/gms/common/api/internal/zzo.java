package com.google.android.gms.common.api.internal;

final class zzo extends ThreadLocal {
    zzo() {
        super();
    }

    protected final Object initialValue() {
        return Boolean.valueOf(false);
    }
}

