package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import java.util.Map;

public final class PriorityDataSource implements DataSource {
    private final int priority;
    private final PriorityTaskManager priorityTaskManager;
    private final DataSource upstream;

    public PriorityDataSource(DataSource arg1, PriorityTaskManager arg2, int arg3) {
        super();
        this.upstream = Assertions.checkNotNull(arg1);
        this.priorityTaskManager = Assertions.checkNotNull(arg2);
        this.priority = arg3;
    }

    public void addTransferListener(TransferListener arg2) {
        this.upstream.addTransferListener(arg2);
    }

    public void close() {
        this.upstream.close();
    }

    public Map getResponseHeaders() {
        return this.upstream.getResponseHeaders();
    }

    public Uri getUri() {
        return this.upstream.getUri();
    }

    public long open(DataSpec arg3) {
        this.priorityTaskManager.proceedOrThrow(this.priority);
        return this.upstream.open(arg3);
    }

    public int read(byte[] arg3, int arg4, int arg5) {
        this.priorityTaskManager.proceedOrThrow(this.priority);
        return this.upstream.read(arg3, arg4, arg5);
    }
}

