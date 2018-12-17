package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;

public class DataBufferRef {
    protected final DataHolder mDataHolder;
    protected int mDataRow;
    private int zznj;

    public DataBufferRef(DataHolder arg1, int arg2) {
        super();
        this.mDataHolder = Preconditions.checkNotNull(arg1);
        this.setDataRow(arg2);
    }

    protected void copyToBuffer(String arg4, CharArrayBuffer arg5) {
        this.mDataHolder.copyToBuffer(arg4, this.mDataRow, this.zznj, arg5);
    }

    public boolean equals(Object arg4) {
        if(((arg4 instanceof DataBufferRef)) && (Objects.equal(Integer.valueOf(((DataBufferRef)arg4).mDataRow), Integer.valueOf(this.mDataRow))) && (Objects.equal(Integer.valueOf(((DataBufferRef)arg4).zznj), Integer.valueOf(this.zznj))) && ((DataBufferRef)arg4).mDataHolder == this.mDataHolder) {
            return 1;
        }

        return 0;
    }

    protected boolean getBoolean(String arg4) {
        return this.mDataHolder.getBoolean(arg4, this.mDataRow, this.zznj);
    }

    protected byte[] getByteArray(String arg4) {
        return this.mDataHolder.getByteArray(arg4, this.mDataRow, this.zznj);
    }

    protected int getDataRow() {
        return this.mDataRow;
    }

    protected double getDouble(String arg4) {
        return this.mDataHolder.getDouble(arg4, this.mDataRow, this.zznj);
    }

    protected float getFloat(String arg4) {
        return this.mDataHolder.getFloat(arg4, this.mDataRow, this.zznj);
    }

    protected int getInteger(String arg4) {
        return this.mDataHolder.getInteger(arg4, this.mDataRow, this.zznj);
    }

    protected long getLong(String arg4) {
        return this.mDataHolder.getLong(arg4, this.mDataRow, this.zznj);
    }

    protected String getString(String arg4) {
        return this.mDataHolder.getString(arg4, this.mDataRow, this.zznj);
    }

    public boolean hasColumn(String arg2) {
        return this.mDataHolder.hasColumn(arg2);
    }

    protected boolean hasNull(String arg4) {
        return this.mDataHolder.hasNull(arg4, this.mDataRow, this.zznj);
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.mDataRow), Integer.valueOf(this.zznj), this.mDataHolder});
    }

    public boolean isDataValid() {
        if(!this.mDataHolder.isClosed()) {
            return 1;
        }

        return 0;
    }

    protected Uri parseUri(String arg4) {
        return this.mDataHolder.parseUri(arg4, this.mDataRow, this.zznj);
    }

    protected void setDataRow(int arg2) {
        boolean v0 = arg2 < 0 || arg2 >= this.mDataHolder.getCount() ? false : true;
        Preconditions.checkState(v0);
        this.mDataRow = arg2;
        this.zznj = this.mDataHolder.getWindowIndex(this.mDataRow);
    }
}

