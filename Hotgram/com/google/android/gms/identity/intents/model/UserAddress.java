package com.google.android.gms.identity.intents.model;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="UserAddressCreator") @Reserved(value={1}) public final class UserAddress extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) private String name;
    @Field(id=10) private String zzk;
    @Field(id=3) private String zzl;
    @Field(id=4) private String zzm;
    @Field(id=5) private String zzn;
    @Field(id=6) private String zzo;
    @Field(id=7) private String zzp;
    @Field(id=8) private String zzq;
    @Field(id=9) private String zzr;
    @Field(id=11) private String zzs;
    @Field(id=12) private String zzt;
    @Field(id=13) private String zzu;
    @Field(id=14) private boolean zzv;
    @Field(id=15) private String zzw;
    @Field(id=16) private String zzx;

    static {
        UserAddress.CREATOR = new zzb();
    }

    @Constructor UserAddress(@Param(id=2) String arg1, @Param(id=3) String arg2, @Param(id=4) String arg3, @Param(id=5) String arg4, @Param(id=6) String arg5, @Param(id=7) String arg6, @Param(id=8) String arg7, @Param(id=9) String arg8, @Param(id=10) String arg9, @Param(id=11) String arg10, @Param(id=12) String arg11, @Param(id=13) String arg12, @Param(id=14) boolean arg13, @Param(id=15) String arg14, @Param(id=16) String arg15) {
        super();
        this.name = arg1;
        this.zzl = arg2;
        this.zzm = arg3;
        this.zzn = arg4;
        this.zzo = arg5;
        this.zzp = arg6;
        this.zzq = arg7;
        this.zzr = arg8;
        this.zzk = arg9;
        this.zzs = arg10;
        this.zzt = arg11;
        this.zzu = arg12;
        this.zzv = arg13;
        this.zzw = arg14;
        this.zzx = arg15;
    }

    UserAddress() {
        super();
    }

    public static UserAddress fromIntent(Intent arg1) {
        if(arg1 != null) {
            if(!arg1.hasExtra("com.google.android.gms.identity.intents.EXTRA_ADDRESS")) {
            }
            else {
                return arg1.getParcelableExtra("com.google.android.gms.identity.intents.EXTRA_ADDRESS");
            }
        }

        return null;
    }

    public final String getAddress1() {
        return this.zzl;
    }

    public final String getAddress2() {
        return this.zzm;
    }

    public final String getAddress3() {
        return this.zzn;
    }

    public final String getAddress4() {
        return this.zzo;
    }

    public final String getAddress5() {
        return this.zzp;
    }

    public final String getAdministrativeArea() {
        return this.zzq;
    }

    public final String getCompanyName() {
        return this.zzw;
    }

    public final String getCountryCode() {
        return this.zzk;
    }

    public final String getEmailAddress() {
        return this.zzx;
    }

    public final String getLocality() {
        return this.zzr;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPhoneNumber() {
        return this.zzu;
    }

    public final String getPostalCode() {
        return this.zzs;
    }

    public final String getSortingCode() {
        return this.zzt;
    }

    public final boolean isPostBox() {
        return this.zzv;
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 2, this.name, false);
        SafeParcelWriter.writeString(arg4, 3, this.zzl, false);
        SafeParcelWriter.writeString(arg4, 4, this.zzm, false);
        SafeParcelWriter.writeString(arg4, 5, this.zzn, false);
        SafeParcelWriter.writeString(arg4, 6, this.zzo, false);
        SafeParcelWriter.writeString(arg4, 7, this.zzp, false);
        SafeParcelWriter.writeString(arg4, 8, this.zzq, false);
        SafeParcelWriter.writeString(arg4, 9, this.zzr, false);
        SafeParcelWriter.writeString(arg4, 10, this.zzk, false);
        SafeParcelWriter.writeString(arg4, 11, this.zzs, false);
        SafeParcelWriter.writeString(arg4, 12, this.zzt, false);
        SafeParcelWriter.writeString(arg4, 13, this.zzu, false);
        SafeParcelWriter.writeBoolean(arg4, 14, this.zzv);
        SafeParcelWriter.writeString(arg4, 15, this.zzw, false);
        SafeParcelWriter.writeString(arg4, 16, this.zzx, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

