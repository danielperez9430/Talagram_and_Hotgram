package com.google.android.exoplayer2.util;

import java.io.IOException;
import java.util.Collections;
import java.util.PriorityQueue;

public final class PriorityTaskManager {
    public class PriorityTooLowException extends IOException {
        public PriorityTooLowException(int arg3, int arg4) {
            super("Priority too low [priority=" + arg3 + ", highest=" + arg4 + "]");
        }
    }

    private int highestPriority;
    private final Object lock;
    private final PriorityQueue queue;

    public PriorityTaskManager() {
        super();
        this.lock = new Object();
        this.queue = new PriorityQueue(10, Collections.reverseOrder());
        this.highestPriority = -2147483648;
    }

    public void add(int arg4) {
        Object v0 = this.lock;
        __monitor_enter(v0);
        try {
            this.queue.add(Integer.valueOf(arg4));
            this.highestPriority = Math.max(this.highestPriority, arg4);
            __monitor_exit(v0);
            return;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_11;
        }

        throw v4;
    }

    public void proceed(int arg3) {
        Object v0 = this.lock;
        __monitor_enter(v0);
        try {
            while(this.highestPriority != arg3) {
                this.lock.wait();
            }

            __monitor_exit(v0);
            return;
        label_10:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_10;
        }

        throw v3;
    }

    public boolean proceedNonBlocking(int arg3) {
        Object v0 = this.lock;
        __monitor_enter(v0);
        try {
            boolean v3_1 = this.highestPriority == arg3 ? true : false;
            __monitor_exit(v0);
            return v3_1;
        label_10:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_10;
        }

        throw v3;
    }

    public void proceedOrThrow(int arg4) {
        Object v0 = this.lock;
        __monitor_enter(v0);
        try {
            if(this.highestPriority == arg4) {
                __monitor_exit(v0);
                return;
            }

            throw new PriorityTooLowException(arg4, this.highestPriority);
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_11;
        }

        throw v4;
    }

    public void remove(int arg3) {
        Object v0 = this.lock;
        __monitor_enter(v0);
        try {
            this.queue.remove(Integer.valueOf(arg3));
            arg3 = this.queue.isEmpty() ? -2147483648 : this.queue.peek().intValue();
            this.highestPriority = arg3;
            this.lock.notifyAll();
            __monitor_exit(v0);
            return;
        label_19:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_19;
        }

        throw v3;
    }
}

