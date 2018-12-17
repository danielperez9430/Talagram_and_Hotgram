package com.google.android.gms.common.server;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;

@Class(creator="FavaDiagnosticsEntityCreator") public class FavaDiagnosticsEntity extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable$Creator CREATOR = null;
    public static final String EXTRA_NAMESPACE = "namespace";
    public static final String EXTRA_TYPE_NUM = "typeNum";
    @Field(id=2) public final String namespace;
    @Field(id=3) public final int typeNum;
    @VersionField(id=1) private final int zzal;

    static {
        FavaDiagnosticsEntity.CREATOR = new FavaDiagnosticsEntityCreator();
    }

    @Constructor public FavaDiagnosticsEntity(@Param(id=1) int arg1, @Param(id=2) String arg2, @Param(id=3) int arg3) {
        super();
        this.zzal = arg1;
        this.namespace = arg2;
        this.typeNum = arg3;
    }

    public FavaDiagnosticsEntity(String arg2, int arg3) {
        super();
        this.zzal = 1;
        this.namespace = arg2;
        this.typeNum = arg3;
    }

    public void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeInt(arg4, 1, this.zzal);
        SafeParcelWriter.writeString(arg4, 2, this.namespace, false);
        SafeParcelWriter.writeInt(arg4, 3, this.typeNum);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

