package com.google.android.gms.common.internal;

import android.os.Looper;
import android.util.Log;

public final class Asserts {
    private Asserts() {
        super();
        throw new AssertionError("Uninstantiable");
    }

    public static void checkMainThread(String arg4) {
        if(Looper.getMainLooper().getThread() == Thread.currentThread()) {
            return;
        }

        String v0 = String.valueOf(Thread.currentThread());
        String v1 = String.valueOf(Looper.getMainLooper().getThread());
        StringBuilder v3 = new StringBuilder(String.valueOf(v0).length() + 57 + String.valueOf(v1).length());
        v3.append("checkMainThread: current thread ");
        v3.append(v0);
        v3.append(" IS NOT the main thread ");
        v3.append(v1);
        v3.append("!");
        Log.e("Asserts", v3.toString());
        throw new IllegalStateException(arg4);
    }

    public static void checkNotMainThread(String arg4) {
        if(Looper.getMainLooper().getThread() != Thread.currentThread()) {
            return;
        }

        String v0 = String.valueOf(Thread.currentThread());
        String v1 = String.valueOf(Looper.getMainLooper().getThread());
        StringBuilder v3 = new StringBuilder(String.valueOf(v0).length() + 56 + String.valueOf(v1).length());
        v3.append("checkNotMainThread: current thread ");
        v3.append(v0);
        v3.append(" IS the main thread ");
        v3.append(v1);
        v3.append("!");
        Log.e("Asserts", v3.toString());
        throw new IllegalStateException(arg4);
    }

    public static void checkNotNull(Object arg1) {
        if(arg1 != null) {
            return;
        }

        throw new IllegalArgumentException("null reference");
    }

    public static void checkNotNull(Object arg0, Object arg1) {
        if(arg0 != null) {
            return;
        }

        throw new IllegalArgumentException(String.valueOf(arg1));
    }

    public static void checkNull(Object arg1) {
        if(arg1 == null) {
            return;
        }

        throw new IllegalArgumentException("non-null reference");
    }

    public static void checkState(boolean arg0, Object arg1) {
        if(arg0) {
            return;
        }

        throw new IllegalStateException(String.valueOf(arg1));
    }

    public static void checkState(boolean arg0) {
        if(arg0) {
            return;
        }

        throw new IllegalStateException();
    }

    public static void checkState(boolean arg0, String arg1, Object[] arg2) {
        if(arg0) {
            return;
        }

        throw new IllegalStateException(String.format(arg1, arg2));
    }

    public static void fail(Object arg1) {
        throw new IllegalStateException(String.valueOf(arg1));
    }
}

