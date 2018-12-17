package com.google.android.gms.common.internal;

import android.util.Log;

public final class GmsLogger {
    public static final int MAX_PII_TAG_LENGTH = 15;
    public static final int MAX_TAG_LENGTH = 23;
    private static final String zzub;
    private final String zzuc;
    private final String zzud;

    static {
    }

    public GmsLogger(String arg2) {
        this(arg2, null);
    }

    public GmsLogger(String arg7, String arg8) {
        super();
        Preconditions.checkNotNull(arg7, "log tag cannot be null");
        int v3 = 23;
        boolean v0 = arg7.length() <= v3 ? true : false;
        Preconditions.checkArgument(v0, "tag \"%s\" is longer than the %d character maximum", new Object[]{arg7, Integer.valueOf(v3)});
        this.zzuc = arg7;
        if(arg8 != null) {
            if(arg8.length() <= 0) {
            }
            else {
                this.zzud = arg8;
                return;
            }
        }

        this.zzud = null;
    }

    public final boolean canLog(int arg2) {
        return Log.isLoggable(this.zzuc, arg2);
    }

    public final boolean canLogPii() {
        return 0;
    }

    public final void d(String arg2, String arg3) {
        if(this.canLog(3)) {
            Log.d(arg2, this.zzl(arg3));
        }
    }

    public final void d(String arg2, String arg3, Throwable arg4) {
        if(this.canLog(3)) {
            Log.d(arg2, this.zzl(arg3), arg4);
        }
    }

    public final void dfmt(String arg2, String arg3, Object[] arg4) {
        if(this.canLog(3)) {
            Log.d(arg2, this.zza(arg3, arg4));
        }
    }

    public final void e(String arg2, String arg3) {
        if(this.canLog(6)) {
            Log.e(arg2, this.zzl(arg3));
        }
    }

    public final void e(String arg2, String arg3, Throwable arg4) {
        if(this.canLog(6)) {
            Log.e(arg2, this.zzl(arg3), arg4);
        }
    }

    public final void efmt(String arg2, String arg3, Object[] arg4) {
        if(this.canLog(6)) {
            Log.e(arg2, this.zza(arg3, arg4));
        }
    }

    public final String getTag() {
        return this.zzuc;
    }

    public final void i(String arg2, String arg3) {
        if(this.canLog(4)) {
            Log.i(arg2, this.zzl(arg3));
        }
    }

    public final void i(String arg2, String arg3, Throwable arg4) {
        if(this.canLog(4)) {
            Log.i(arg2, this.zzl(arg3), arg4);
        }
    }

    public final void ifmt(String arg2, String arg3, Object[] arg4) {
        if(this.canLog(4)) {
            Log.i(arg2, this.zza(arg3, arg4));
        }
    }

    public static boolean isBuildPiiEnabled() {
        return 0;
    }

    public final void pii(String arg3, String arg4) {
        if(this.canLogPii()) {
            arg3 = String.valueOf(arg3);
            String v0 = String.valueOf(" PII_LOG");
            arg3 = v0.length() != 0 ? arg3.concat(v0) : new String(arg3);
            Log.i(arg3, this.zzl(arg4));
        }
    }

    public final void pii(String arg3, String arg4, Throwable arg5) {
        if(this.canLogPii()) {
            arg3 = String.valueOf(arg3);
            String v0 = String.valueOf(" PII_LOG");
            arg3 = v0.length() != 0 ? arg3.concat(v0) : new String(arg3);
            Log.i(arg3, this.zzl(arg4), arg5);
        }
    }

    public final void piifmt(String arg3, String arg4, Object[] arg5) {
        if(this.canLogPii()) {
            arg3 = String.valueOf(arg3);
            String v0 = String.valueOf(" PII_LOG");
            arg3 = v0.length() != 0 ? arg3.concat(v0) : new String(arg3);
            Log.i(arg3, this.zza(arg4, arg5));
        }
    }

    public final void v(String arg2, String arg3) {
        if(this.canLog(2)) {
            Log.v(arg2, this.zzl(arg3));
        }
    }

    public final void v(String arg2, String arg3, Throwable arg4) {
        if(this.canLog(2)) {
            Log.v(arg2, this.zzl(arg3), arg4);
        }
    }

    public final void vfmt(String arg2, String arg3, Object[] arg4) {
        if(this.canLog(2)) {
            Log.v(arg2, this.zza(arg3, arg4));
        }
    }

    public final void w(String arg2, String arg3) {
        if(this.canLog(5)) {
            Log.w(arg2, this.zzl(arg3));
        }
    }

    public final void w(String arg2, String arg3, Throwable arg4) {
        if(this.canLog(5)) {
            Log.w(arg2, this.zzl(arg3), arg4);
        }
    }

    public final void wfmt(String arg1, String arg2, Object[] arg3) {
        if(this.canLog(5)) {
            Log.w(this.zzuc, this.zza(arg2, arg3));
        }
    }

    public final GmsLogger withMessagePrefix(String arg3) {
        return new GmsLogger(this.zzuc, arg3);
    }

    public final void wtf(String arg2, String arg3, Throwable arg4) {
        if(this.canLog(7)) {
            Log.e(arg2, this.zzl(arg3), arg4);
            Log.wtf(arg2, this.zzl(arg3), arg4);
        }
    }

    private final String zza(String arg1, Object[] arg2) {
        arg1 = String.format(arg1, arg2);
        if(this.zzud == null) {
            return arg1;
        }

        return this.zzud.concat(arg1);
    }

    private final String zzl(String arg2) {
        if(this.zzud == null) {
            return arg2;
        }

        return this.zzud.concat(arg2);
    }
}

