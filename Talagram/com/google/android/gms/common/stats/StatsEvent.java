package com.google.android.gms.common.stats;

import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public abstract class StatsEvent extends AbstractSafeParcelable implements ReflectedParcelable {
    public interface Types {
        public static final int EVENT_TYPE_ACQUIRE_WAKE_LOCK = 7;
        public static final int EVENT_TYPE_BIND = 2;
        public static final int EVENT_TYPE_CONNECT = 3;
        public static final int EVENT_TYPE_CONNECTION_TIME_OUT = 6;
        public static final int EVENT_TYPE_DISCONNECT = 4;
        public static final int EVENT_TYPE_RELEASE_WAKE_LOCK = 8;
        public static final int EVENT_TYPE_START_SERVICE = 13;
        public static final int EVENT_TYPE_STOP_ALL_SERVICE = 15;
        public static final int EVENT_TYPE_STOP_SERVICE = 14;
        public static final int EVENT_TYPE_SYNC_END = 11;
        public static final int EVENT_TYPE_SYNC_START = 10;
        public static final int EVENT_TYPE_SYNC_TIME_OUT = 12;
        public static final int EVENT_TYPE_UNBIND = 1;
        public static final int EVENT_TYPE_UNKNOWN = -1;
        public static final int EVENT_TYPE_WAKE_LOCK_TIME_OUT = 9;

    }

    public StatsEvent() {
        super();
    }

    public abstract StatsEvent ReconstructCloseEvent(StatsEvent arg1);

    public abstract long getDurationMillis();

    public abstract long getElapsedRealtime();

    public abstract String getEventKey();

    public abstract int getEventType();

    public abstract String getSpecificString();

    public abstract long getTimeMillis();

    public abstract long getTimeout();

    public abstract StatsEvent markTimeOut();

    public abstract StatsEvent setDurationMillis(long arg1);

    public abstract StatsEvent setEventType(int arg1);

    public String toString() {
        long v0 = this.getTimeMillis();
        int v2 = this.getEventType();
        long v3 = this.getDurationMillis();
        String v5 = this.getSpecificString();
        StringBuilder v7 = new StringBuilder(String.valueOf(v5).length() + 53);
        v7.append(v0);
        v7.append("\t");
        v7.append(v2);
        v7.append("\t");
        v7.append(v3);
        v7.append(v5);
        return v7.toString();
    }
}

