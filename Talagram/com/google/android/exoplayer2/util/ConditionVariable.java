package com.google.android.exoplayer2.util;

import android.os.SystemClock;

public final class ConditionVariable {
    private boolean isOpen;

    public ConditionVariable() {
        super();
    }

    public void block() {
        __monitor_enter(this);
        try {
            while(!this.isOpen) {
                this.wait();
            }
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }

    public boolean block(long arg4) {
        boolean v4_1;
        __monitor_enter(this);
        try {
            long v0 = SystemClock.elapsedRealtime();
            arg4 += v0;
            while(!this.isOpen) {
                if(v0 >= arg4) {
                    break;
                }

                this.wait(arg4 - v0);
                v0 = SystemClock.elapsedRealtime();
            }

            v4_1 = this.isOpen;
        }
        catch(Throwable v4) {
            goto label_15;
        }

        __monitor_exit(this);
        return v4_1;
    label_15:
        __monitor_exit(this);
        throw v4;
    }

    public boolean close() {
        boolean v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.isOpen;
            this.isOpen = false;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    public boolean open() {
        __monitor_enter(this);
        try {
            if(!this.isOpen) {
                goto label_7;
            }
        }
        catch(Throwable v0) {
            goto label_12;
        }

        __monitor_exit(this);
        return 0;
        try {
        label_7:
            this.isOpen = true;
            this.notifyAll();
        }
        catch(Throwable v0) {
        label_12:
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return 1;
    }
}

