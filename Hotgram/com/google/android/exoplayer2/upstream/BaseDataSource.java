package com.google.android.exoplayer2.upstream;

import com.google.android.exoplayer2.util.Assertions;
import java.util.ArrayList;
import java.util.Map;

public abstract class BaseDataSource implements DataSource {
    private DataSpec dataSpec;
    private final boolean isNetwork;
    private final ArrayList listeners;

    protected BaseDataSource(boolean arg2) {
        super();
        this.isNetwork = arg2;
        this.listeners = new ArrayList(1);
    }

    public final void addTransferListener(TransferListener arg2) {
        this.listeners.add(arg2);
    }

    protected final void bytesTransferred(int arg5) {
        Object v0 = Assertions.checkNotNull(this.dataSpec);
        int v1;
        for(v1 = 0; v1 < this.listeners.size(); ++v1) {
            this.listeners.get(v1).onBytesTransferred(((DataSource)this), ((DataSpec)v0), this.isNetwork, arg5);
        }
    }

    public Map getResponseHeaders() {
        return DataSource$-CC.$default$getResponseHeaders(((DataSource)this));
    }

    protected final void transferEnded() {
        Object v0 = Assertions.checkNotNull(this.dataSpec);
        int v1;
        for(v1 = 0; v1 < this.listeners.size(); ++v1) {
            this.listeners.get(v1).onTransferEnd(((DataSource)this), ((DataSpec)v0), this.isNetwork);
        }

        this.dataSpec = null;
    }

    protected final void transferInitializing(DataSpec arg4) {
        int v0;
        for(v0 = 0; v0 < this.listeners.size(); ++v0) {
            this.listeners.get(v0).onTransferInitializing(((DataSource)this), arg4, this.isNetwork);
        }
    }

    protected final void transferStarted(DataSpec arg4) {
        this.dataSpec = arg4;
        int v0;
        for(v0 = 0; v0 < this.listeners.size(); ++v0) {
            this.listeners.get(v0).onTransferStart(((DataSource)this), arg4, this.isNetwork);
        }
    }
}

