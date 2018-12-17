package com.google.android.gms.common.data;

import android.os.Bundle;
import com.google.android.gms.common.internal.Asserts;
import java.util.Iterator;

public abstract class FilteredDataBuffer implements DataBuffer {
    protected final DataBuffer mDataBuffer;

    public FilteredDataBuffer(DataBuffer arg3) {
        super();
        Asserts.checkNotNull(arg3);
        boolean v0 = !(arg3 instanceof FilteredDataBuffer) ? true : false;
        Asserts.checkState(v0, "Not possible to have nested FilteredDataBuffers.");
        this.mDataBuffer = arg3;
    }

    public void close() {
        this.release();
    }

    protected abstract int computeRealPosition(int arg1);

    public Object get(int arg2) {
        return this.mDataBuffer.get(this.computeRealPosition(arg2));
    }

    public Bundle getMetadata() {
        return this.mDataBuffer.getMetadata();
    }

    @Deprecated public boolean isClosed() {
        return this.mDataBuffer.isClosed();
    }

    public Iterator iterator() {
        return new DataBufferIterator(((DataBuffer)this));
    }

    public void release() {
        this.mDataBuffer.release();
    }

    public Iterator singleRefIterator() {
        return this.iterator();
    }
}

