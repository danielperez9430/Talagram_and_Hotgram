package com.google.android.exoplayer2.util;

import android.os.Looper;
import android.text.TextUtils;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

public final class Assertions {
    private Assertions() {
        super();
    }

    public static void checkArgument(boolean arg0) {
        if(arg0) {
            return;
        }

        throw new IllegalArgumentException();
    }

    public static void checkArgument(boolean arg0, Object arg1) {
        if(arg0) {
            return;
        }

        throw new IllegalArgumentException(String.valueOf(arg1));
    }

    public static int checkIndex(int arg0, int arg1, int arg2) {
        if(arg0 >= arg1 && arg0 < arg2) {
            return arg0;
        }

        throw new IndexOutOfBoundsException();
    }

    public static void checkMainThread() {
        if(Looper.myLooper() == Looper.getMainLooper()) {
            return;
        }

        throw new IllegalStateException("Not in applications main thread");
    }

    @EnsuresNonNull(value={"#1"}) public static String checkNotEmpty(String arg1) {
        if(!TextUtils.isEmpty(((CharSequence)arg1))) {
            return arg1;
        }

        throw new IllegalArgumentException();
    }

    @EnsuresNonNull(value={"#1"}) public static String checkNotEmpty(String arg1, Object arg2) {
        if(!TextUtils.isEmpty(((CharSequence)arg1))) {
            return arg1;
        }

        throw new IllegalArgumentException(String.valueOf(arg2));
    }

    @EnsuresNonNull(value={"#1"}) public static Object checkNotNull(Object arg0) {
        if(arg0 != null) {
            return arg0;
        }

        throw new NullPointerException();
    }

    @EnsuresNonNull(value={"#1"}) public static Object checkNotNull(Object arg0, Object arg1) {
        if(arg0 != null) {
            return arg0;
        }

        throw new NullPointerException(String.valueOf(arg1));
    }

    public static void checkState(boolean arg0) {
        if(arg0) {
            return;
        }

        throw new IllegalStateException();
    }

    public static void checkState(boolean arg0, Object arg1) {
        if(arg0) {
            return;
        }

        throw new IllegalStateException(String.valueOf(arg1));
    }
}

