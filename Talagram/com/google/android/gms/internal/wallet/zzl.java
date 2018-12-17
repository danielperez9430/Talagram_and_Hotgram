package com.google.android.gms.internal.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.widget.RemoteViews;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;

@Class(creator="GetSaveInstrumentDetailsResponseCreator") public final class zzl extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=1) private String[] zzez;
    @Field(id=2) private int[] zzfa;
    @Field(id=3) private RemoteViews zzfb;
    @Field(id=4) private byte[] zzfc;

    static {
        zzl.CREATOR = new zzm();
    }

    @Constructor public zzl(@Param(id=1) String[] arg1, @Param(id=2) int[] arg2, @Param(id=3) RemoteViews arg3, @Param(id=4) byte[] arg4) {
        super();
        this.zzez = arg1;
        this.zzfa = arg2;
        this.zzfb = arg3;
        this.zzfc = arg4;
    }

    private zzl() {
        super();
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeStringArray(arg5, 1, this.zzez, false);
        SafeParcelWriter.writeIntArray(arg5, 2, this.zzfa, false);
        SafeParcelWriter.writeParcelable(arg5, 3, this.zzfb, arg6, false);
        SafeParcelWriter.writeByteArray(arg5, 4, this.zzfc, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

