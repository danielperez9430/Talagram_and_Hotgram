package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DataBufferSafeParcelable extends AbstractDataBuffer {
    public static final String DATA_FIELD = "data";
    private static final String[] zznk;
    private final Parcelable$Creator zznl;

    static {
        DataBufferSafeParcelable.zznk = new String[]{"data"};
    }

    public DataBufferSafeParcelable(DataHolder arg1, Parcelable$Creator arg2) {
        super(arg1);
        this.zznl = arg2;
    }

    public static void addValue(Builder arg3, SafeParcelable arg4) {
        Parcel v0 = Parcel.obtain();
        arg4.writeToParcel(v0, 0);
        ContentValues v4 = new ContentValues();
        v4.put("data", v0.marshall());
        arg3.withRow(v4);
        v0.recycle();
    }

    public static Builder buildDataHolder() {
        return DataHolder.builder(DataBufferSafeParcelable.zznk);
    }

    public SafeParcelable get(int arg4) {
        byte[] v4 = this.mDataHolder.getByteArray("data", arg4, this.mDataHolder.getWindowIndex(arg4));
        Parcel v0 = Parcel.obtain();
        v0.unmarshall(v4, 0, v4.length);
        v0.setDataPosition(0);
        Object v4_1 = this.zznl.createFromParcel(v0);
        v0.recycle();
        return ((SafeParcelable)v4_1);
    }

    public Object get(int arg1) {
        return this.get(arg1);
    }
}

