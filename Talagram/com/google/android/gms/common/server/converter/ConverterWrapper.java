package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.server.response.FastJsonResponse$FieldConverter;

@Class(creator="ConverterWrapperCreator") public class ConverterWrapper extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @VersionField(id=1) private final int zzal;
    @Field(getter="getStringToIntConverter", id=2) private final StringToIntConverter zzwd;

    static {
        ConverterWrapper.CREATOR = new ConverterWrapperCreator();
    }

    @Constructor ConverterWrapper(@Param(id=1) int arg1, @Param(id=2) StringToIntConverter arg2) {
        super();
        this.zzal = arg1;
        this.zzwd = arg2;
    }

    private ConverterWrapper(StringToIntConverter arg2) {
        super();
        this.zzal = 1;
        this.zzwd = arg2;
    }

    public FieldConverter unwrap() {
        if(this.zzwd != null) {
            return this.zzwd;
        }

        throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
    }

    public static ConverterWrapper wrap(FieldConverter arg1) {
        if((arg1 instanceof StringToIntConverter)) {
            return new ConverterWrapper(((StringToIntConverter)arg1));
        }

        throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 1, this.zzal);
        SafeParcelWriter.writeParcelable(arg5, 2, this.zzwd, arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

