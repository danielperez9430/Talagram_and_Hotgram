package com.google.android.gms.common.api;

import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataBuffer;
import java.util.Iterator;

@KeepForSdk public class DataBufferResponse extends Response implements DataBuffer {
    @KeepForSdk public DataBufferResponse() {
        super();
    }

    @KeepForSdk public DataBufferResponse(AbstractDataBuffer arg1) {
        super(((Result)arg1));
    }

    public void close() {
        ((Response)this).getResult().close();
    }

    public Object get(int arg2) {
        return ((Response)this).getResult().get(arg2);
    }

    public int getCount() {
        return ((Response)this).getResult().getCount();
    }

    public Bundle getMetadata() {
        return ((Response)this).getResult().getMetadata();
    }

    public boolean isClosed() {
        return ((Response)this).getResult().isClosed();
    }

    public Iterator iterator() {
        return ((Response)this).getResult().iterator();
    }

    public void release() {
        ((Response)this).getResult().release();
    }

    public Iterator singleRefIterator() {
        return ((Response)this).getResult().singleRefIterator();
    }
}

