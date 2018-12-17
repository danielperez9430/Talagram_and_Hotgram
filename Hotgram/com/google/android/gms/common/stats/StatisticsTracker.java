package com.google.android.gms.common.stats;

public interface StatisticsTracker {
    void registerEvent(ConnectionEvent arg1);

    void registerEvent(WakeLockEvent arg1);
}

