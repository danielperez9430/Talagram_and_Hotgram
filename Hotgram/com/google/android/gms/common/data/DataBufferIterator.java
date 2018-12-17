package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DataBufferIterator implements Iterator {
    protected final DataBuffer mDataBuffer;
    protected int mPosition;

    public DataBufferIterator(DataBuffer arg1) {
        super();
        this.mDataBuffer = Preconditions.checkNotNull(arg1);
        this.mPosition = -1;
    }

    public boolean hasNext() {
        if(this.mPosition < this.mDataBuffer.getCount() - 1) {
            return 1;
        }

        return 0;
    }

    public Object next() {
        int v1;
        if(this.hasNext()) {
            DataBuffer v0 = this.mDataBuffer;
            v1 = this.mPosition + 1;
            this.mPosition = v1;
            return v0.get(v1);
        }

        v1 = this.mPosition;
        StringBuilder v3 = new StringBuilder(46);
        v3.append("Cannot advance the iterator beyond ");
        v3.append(v1);
        throw new NoSuchElementException(v3.toString());
    }

    public void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}

