package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="SymbolBoxParcelCreator") @Reserved(value={1}) public final class zzab extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;

    static {
        zzab.CREATOR = new zzac();
    }

    @Constructor public zzab() {
        super();
    }

    public final void writeToParcel(Parcel arg1, int arg2) {
        SafeParcelWriter.finishObjectHeader(arg1, SafeParcelWriter.beginObjectHeader(arg1));
    }
}

