package com.google.android.gms.common.logging;

import android.annotation.SuppressLint;
import android.util.Log;
import com.google.android.gms.common.internal.GmsLogger;
import java.util.Locale;

public class Logger {
    private final String mTag;
    private final String zzud;
    private final GmsLogger zzvd;
    private final int zzve;

    private Logger(String arg1, String arg2) {
        super();
        this.zzud = arg2;
        this.mTag = arg1;
        this.zzvd = new GmsLogger(arg1);
        int v1;
        for(v1 = 2; 7 >= v1; ++v1) {
            if(Log.isLoggable(this.mTag, v1)) {
                break;
            }
        }

        this.zzve = v1;
    }

    public Logger(String arg7, String[] arg8) {
        String v8;
        if(arg8.length == 0) {
            v8 = "";
        }
        else {
            StringBuilder v0 = new StringBuilder();
            v0.append('[');
            int v1 = arg8.length;
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                String v3 = arg8[v2];
                if(v0.length() > 1) {
                    v0.append(",");
                }

                v0.append(v3);
            }

            v0.append(']');
            v0.append(' ');
            v8 = v0.toString();
        }

        this(arg7, v8);
    }

    public void d(String arg2, Throwable arg3, Object[] arg4) {
        if(this.isLoggable(3)) {
            Log.d(this.mTag, this.format(arg2, arg4), arg3);
        }
    }

    public void d(String arg2, Object[] arg3) {
        if(this.isLoggable(3)) {
            Log.d(this.mTag, this.format(arg2, arg3));
        }
    }

    public void e(String arg2, Throwable arg3, Object[] arg4) {
        Log.e(this.mTag, this.format(arg2, arg4), arg3);
    }

    public void e(String arg2, Object[] arg3) {
        Log.e(this.mTag, this.format(arg2, arg3));
    }

    public String elidePii(Object arg4) {
        boolean v0 = this.zzvd.canLogPii();
        if(arg4 == null) {
            return "<NULL>";
        }

        String v4 = arg4.toString().trim();
        if(v4.isEmpty()) {
            return "<EMPTY>";
        }

        if(v0) {
            return v4;
        }

        return String.format("<ELLIDED:%s>", Integer.valueOf(v4.hashCode()));
    }

    protected String format(String arg2, Object[] arg3) {
        if(arg3 != null && arg3.length > 0) {
            arg2 = String.format(Locale.US, arg2, arg3);
        }

        return this.zzud.concat(arg2);
    }

    public String getTag() {
        return this.mTag;
    }

    public void i(String arg2, Throwable arg3, Object[] arg4) {
        Log.i(this.mTag, this.format(arg2, arg4), arg3);
    }

    public void i(String arg2, Object[] arg3) {
        Log.i(this.mTag, this.format(arg2, arg3));
    }

    public boolean isLoggable(int arg2) {
        if(this.zzve <= arg2) {
            return 1;
        }

        return 0;
    }

    public boolean isPiiLoggable() {
        return this.zzvd.canLogPii();
    }

    public void v(String arg2, Throwable arg3, Object[] arg4) {
        if(this.isLoggable(2)) {
            Log.v(this.mTag, this.format(arg2, arg4), arg3);
        }
    }

    public void v(String arg2, Object[] arg3) {
        if(this.isLoggable(2)) {
            Log.v(this.mTag, this.format(arg2, arg3));
        }
    }

    public void w(String arg2, Throwable arg3, Object[] arg4) {
        Log.w(this.mTag, this.format(arg2, arg4), arg3);
    }

    public void w(String arg2, Object[] arg3) {
        Log.w(this.mTag, this.format(arg2, arg3));
    }

    public void w(Throwable arg2) {
        Log.w(this.mTag, arg2);
    }

    public void wtf(String arg2, Throwable arg3, Object[] arg4) {
        Log.wtf(this.mTag, this.format(arg2, arg4), arg3);
    }

    @SuppressLint(value={"WtfWithoutException"}) public void wtf(String arg2, Object[] arg3) {
        Log.wtf(this.mTag, this.format(arg2, arg3));
    }

    public void wtf(Throwable arg2) {
        Log.wtf(this.mTag, arg2);
    }
}

