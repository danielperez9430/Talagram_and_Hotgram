package com.google.android.gms.common.data;

import java.util.NoSuchElementException;

public class SingleRefDataBufferIterator extends DataBufferIterator {
    private Object zzoj;

    public SingleRefDataBufferIterator(DataBuffer arg1) {
        super(arg1);
    }

    public Object next() {
        StringBuilder v3;
        if(((DataBufferIterator)this).hasNext()) {
            ++this.mPosition;
            if(this.mPosition == 0) {
                this.zzoj = this.mDataBuffer.get(0);
                if((this.zzoj instanceof DataBufferRef)) {
                }
                else {
                    String v1 = String.valueOf(this.zzoj.getClass());
                    v3 = new StringBuilder(String.valueOf(v1).length() + 44);
                    v3.append("DataBuffer reference of type ");
                    v3.append(v1);
                    v3.append(" is not movable");
                    throw new IllegalStateException(v3.toString());
                }
            }
            else {
                this.zzoj.setDataRow(this.mPosition);
            }

            return this.zzoj;
        }

        int v1_1 = this.mPosition;
        v3 = new StringBuilder(46);
        v3.append("Cannot advance the iterator beyond ");
        v3.append(v1_1);
        throw new NoSuchElementException(v3.toString());
    }
}

