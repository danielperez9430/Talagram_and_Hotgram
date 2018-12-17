package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.Iterator;

public abstract class AbstractDataBuffer implements DataBuffer {
    protected final DataHolder mDataHolder;

    protected AbstractDataBuffer(DataHolder arg1) {
        super();
        this.mDataHolder = arg1;
    }

    @Deprecated public final void close() {
        this.release();
    }

    public abstract Object get(int arg1);

    public int getCount() {
        if(this.mDataHolder == null) {
            return 0;
        }

        return this.mDataHolder.getCount();
    }

    public Bundle getMetadata() {
        return this.mDataHolder.getMetadata();
    }

    @Deprecated public boolean isClosed() {
        if(this.mDataHolder != null) {
            if(this.mDataHolder.isClosed()) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public Iterator iterator() {
        return new DataBufferIterator(((DataBuffer)this));
    }

    public void release() {
        if(this.mDataHolder != null) {
            this.mDataHolder.close();
        }
    }

    public Iterator singleRefIterator() {
        return new SingleRefDataBufferIterator(((DataBuffer)this));
    }
}

