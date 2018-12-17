package com.google.android.exoplayer2.scheduler;

public interface Scheduler {
    public static final boolean DEBUG = false;

    boolean cancel();

    boolean schedule(Requirements arg1, String arg2, String arg3);
}

