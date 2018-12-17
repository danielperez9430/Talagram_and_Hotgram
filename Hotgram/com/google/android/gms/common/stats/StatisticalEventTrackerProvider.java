package com.google.android.gms.common.stats;

import android.os.Bundle;

public class StatisticalEventTrackerProvider {
    public interface StatisticalEventTracker {
        int getLogLevel(int arg1);

        Bundle getOptions();

        boolean isEnabled();

        void registerEvent(ConnectionEvent arg1);

        void registerEvent(StatsEvent arg1);

        void registerEvent(WakeLockEvent arg1);
    }

    private static StatisticalEventTracker zzyp;

    private StatisticalEventTrackerProvider() {
        super();
    }

    public static StatisticalEventTracker getImpl() {
        return StatisticalEventTrackerProvider.zzyp;
    }

    public static void setImpl(StatisticalEventTracker arg0) {
        StatisticalEventTrackerProvider.zzyp = arg0;
    }
}

