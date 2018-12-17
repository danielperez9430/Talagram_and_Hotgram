package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepName;

@KeepName public final class BinderWrapper implements Parcelable {
    public static final Parcelable$Creator CREATOR;
    private IBinder zzry;

    static {
        BinderWrapper.CREATOR = new zza();
    }

    public BinderWrapper() {
        super();
        this.zzry = null;
    }

    public BinderWrapper(IBinder arg2) {
        super();
        this.zzry = null;
        this.zzry = arg2;
    }

    private BinderWrapper(Parcel arg2) {
        super();
        this.zzry = null;
        this.zzry = arg2.readStrongBinder();
    }

    BinderWrapper(Parcel arg1, zza arg2) {
        this(arg1);
    }

    public final int describeContents() {
        return 0;
    }

    public final IBinder getBinder() {
        return this.zzry;
    }

    public final void setBinder(IBinder arg1) {
        this.zzry = arg1;
    }

    public final void writeToParcel(Parcel arg1, int arg2) {
        arg1.writeStrongBinder(this.zzry);
    }
}

