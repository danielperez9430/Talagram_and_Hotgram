package com.google.android.gms.common.stats;

import android.os.SystemClock;
import android.support.v4.f.m;
import android.util.Log;

public class PassiveTimedConnectionMatcher {
    private final long zzym;
    private final int zzyn;
    private final m zzyo;

    public PassiveTimedConnectionMatcher() {
        super();
        this.zzym = 60000;
        this.zzyn = 10;
        this.zzyo = new m(10);
    }

    public PassiveTimedConnectionMatcher(int arg1, long arg2) {
        super();
        this.zzym = arg2;
        this.zzyn = arg1;
        this.zzyo = new m();
    }

    public Long get(String arg2) {
        __monitor_enter(this);
        try {
            __monitor_exit(this);
            return this.zzyo.get(arg2);
        label_6:
            __monitor_exit(this);
        }
        catch(Throwable v2) {
            goto label_6;
        }

        throw v2;
    }

    public Long put(String arg9) {
        long v0 = SystemClock.elapsedRealtime();
        long v2 = this.zzym;
        __monitor_enter(this);
        try {
            while(this.zzyo.size() >= this.zzyn) {
                int v4;
                for(v4 = this.zzyo.size() - 1; v4 >= 0; --v4) {
                    if(v0 - this.zzyo.c(v4).longValue() > v2) {
                        this.zzyo.d(v4);
                    }
                }

                v2 /= 2;
                int v5 = this.zzyn;
                StringBuilder v7 = new StringBuilder(94);
                v7.append("The max capacity ");
                v7.append(v5);
                v7.append(" is not enough. Current durationThreshold is: ");
                v7.append(v2);
                Log.w("ConnectionTracker", v7.toString());
            }

            __monitor_exit(this);
            return this.zzyo.put(arg9, Long.valueOf(v0));
        label_43:
            __monitor_exit(this);
        }
        catch(Throwable v9) {
            goto label_43;
        }

        throw v9;
    }

    public boolean remove(String arg2) {
        __monitor_enter(this);
        try {
            boolean v2_1 = this.zzyo.remove(arg2) != null ? true : false;
            __monitor_exit(this);
            return v2_1;
        label_10:
            __monitor_exit(this);
        }
        catch(Throwable v2) {
            goto label_10;
        }

        throw v2;
    }

    public boolean removeByPrefix(String arg5) {
        __monitor_enter(this);
        int v0 = 0;
        boolean v1 = false;
        try {
            while(v0 < this.size()) {
                Object v2 = this.zzyo.b(v0);
                if(v2 != null && (((String)v2).startsWith(arg5))) {
                    this.zzyo.remove(v2);
                    v1 = true;
                }

                ++v0;
            }

            __monitor_exit(this);
            return v1;
        label_18:
            __monitor_exit(this);
        }
        catch(Throwable v5) {
            goto label_18;
        }

        throw v5;
    }

    public int size() {
        __monitor_enter(this);
        try {
            __monitor_exit(this);
            return this.zzyo.size();
        label_6:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_6;
        }

        throw v0;
    }
}

