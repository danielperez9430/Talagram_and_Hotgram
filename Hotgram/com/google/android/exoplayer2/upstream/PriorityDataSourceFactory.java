package com.google.android.exoplayer2.upstream;

import com.google.android.exoplayer2.util.PriorityTaskManager;

public final class PriorityDataSourceFactory implements Factory {
    private final int priority;
    private final PriorityTaskManager priorityTaskManager;
    private final Factory upstreamFactory;

    public PriorityDataSourceFactory(Factory arg1, PriorityTaskManager arg2, int arg3) {
        super();
        this.upstreamFactory = arg1;
        this.priorityTaskManager = arg2;
        this.priority = arg3;
    }

    public DataSource createDataSource() {
        return this.createDataSource();
    }

    public PriorityDataSource createDataSource() {
        return new PriorityDataSource(this.upstreamFactory.createDataSource(), this.priorityTaskManager, this.priority);
    }
}

