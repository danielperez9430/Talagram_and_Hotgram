package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Class(creator="AncsNotificationParcelableCreator") @Reserved(value={1}) @Immutable public final class zzl extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getId", id=2) private int id;
    @Field(getter="getPackageName", id=13) @Nullable private final String packageName;
    @Field(getter="getAppId", id=3) private final String zzbf;
    @Field(getter="getDateTime", id=4) @Nullable private final String zzbg;
    @Field(getter="getNotificationText", id=5) private final String zzbh;
    @Field(getter="getTitle", id=6) private final String zzbi;
    @Field(getter="getSubtitle", id=7) private final String zzbj;
    @Field(getter="getDisplayName", id=8) @Nullable private final String zzbk;
    @Field(getter="getEventId", id=9) private final byte zzbl;
    @Field(getter="getEventFlags", id=10) private final byte zzbm;
    @Field(getter="getCategoryId", id=11) private final byte zzbn;
    @Field(getter="getCategoryCount", id=12) private final byte zzbo;

    static {
        zzl.CREATOR = new zzm();
    }

    @Constructor public zzl(@Param(id=2) int arg1, @Param(id=3) String arg2, @Param(id=4) @Nullable String arg3, @Param(id=5) String arg4, @Param(id=6) String arg5, @Param(id=7) String arg6, @Param(id=8) @Nullable String arg7, @Param(id=9) byte arg8, @Param(id=10) byte arg9, @Param(id=11) byte arg10, @Param(id=12) byte arg11, @Param(id=13) @Nullable String arg12) {
        super();
        this.id = arg1;
        this.zzbf = arg2;
        this.zzbg = arg3;
        this.zzbh = arg4;
        this.zzbi = arg5;
        this.zzbj = arg6;
        this.zzbk = arg7;
        this.zzbl = arg8;
        this.zzbm = arg9;
        this.zzbn = arg10;
        this.zzbo = arg11;
        this.packageName = arg12;
    }

    public final boolean equals(Object arg5) {
        if(this == (((zzl)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else if(this.id != ((zzl)arg5).id) {
                return 0;
            }
            else if(this.zzbl != ((zzl)arg5).zzbl) {
                return 0;
            }
            else if(this.zzbm != ((zzl)arg5).zzbm) {
                return 0;
            }
            else if(this.zzbn != ((zzl)arg5).zzbn) {
                return 0;
            }
            else if(this.zzbo != ((zzl)arg5).zzbo) {
                return 0;
            }
            else if(!this.zzbf.equals(((zzl)arg5).zzbf)) {
                return 0;
            }
            else {
                if(this.zzbg != null) {
                    if(!this.zzbg.equals(((zzl)arg5).zzbg)) {
                        return 0;
                    }
                }
                else if(((zzl)arg5).zzbg != null) {
                    return 0;
                }

                if(!this.zzbh.equals(((zzl)arg5).zzbh)) {
                    return 0;
                }

                if(!this.zzbi.equals(((zzl)arg5).zzbi)) {
                    return 0;
                }

                if(!this.zzbj.equals(((zzl)arg5).zzbj)) {
                    return 0;
                }

                if(this.zzbk != null) {
                    if(!this.zzbk.equals(((zzl)arg5).zzbk)) {
                        return 0;
                    }
                }
                else if(((zzl)arg5).zzbk != null) {
                    return 0;
                }

                if(this.packageName != null) {
                    return this.packageName.equals(((zzl)arg5).packageName);
                }

                if(((zzl)arg5).packageName != null) {
                    return 0;
                }

                return 1;
            }
        }

        return 0;
    }

    public final int hashCode() {
        int v0 = ((this.id + 31) * 31 + this.zzbf.hashCode()) * 31;
        int v2 = 0;
        int v1 = this.zzbg != null ? this.zzbg.hashCode() : 0;
        v0 = ((((v0 + v1) * 31 + this.zzbh.hashCode()) * 31 + this.zzbi.hashCode()) * 31 + this.zzbj.hashCode()) * 31;
        v1 = this.zzbk != null ? this.zzbk.hashCode() : 0;
        v0 = (((((v0 + v1) * 31 + this.zzbl) * 31 + this.zzbm) * 31 + this.zzbn) * 31 + this.zzbo) * 31;
        if(this.packageName != null) {
            v2 = this.packageName.hashCode();
        }

        return v0 + v2;
    }

    public final String toString() {
        int v0 = this.id;
        String v1 = this.zzbf;
        String v2 = this.zzbg;
        String v3 = this.zzbh;
        String v4 = this.zzbi;
        String v5 = this.zzbj;
        String v6 = this.zzbk;
        byte v7 = this.zzbl;
        byte v8 = this.zzbm;
        byte v9 = this.zzbn;
        byte v10 = this.zzbo;
        String v11 = this.packageName;
        StringBuilder v13 = new StringBuilder(String.valueOf(v1).length() + 211 + String.valueOf(v2).length() + String.valueOf(v3).length() + String.valueOf(v4).length() + String.valueOf(v5).length() + String.valueOf(v6).length() + String.valueOf(v11).length());
        v13.append("AncsNotificationParcelable{, id=");
        v13.append(v0);
        v13.append(", appId=\'");
        v13.append(v1);
        v13.append('\'');
        v13.append(", dateTime=\'");
        v13.append(v2);
        v13.append('\'');
        v13.append(", notificationText=\'");
        v13.append(v3);
        v13.append('\'');
        v13.append(", title=\'");
        v13.append(v4);
        v13.append('\'');
        v13.append(", subtitle=\'");
        v13.append(v5);
        v13.append('\'');
        v13.append(", displayName=\'");
        v13.append(v6);
        v13.append('\'');
        v13.append(", eventId=");
        v13.append(v7);
        v13.append(", eventFlags=");
        v13.append(v8);
        v13.append(", categoryId=");
        v13.append(v9);
        v13.append(", categoryCount=");
        v13.append(v10);
        v13.append(", packageName=\'");
        v13.append(v11);
        v13.append('\'');
        v13.append('}');
        return v13.toString();
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeInt(arg4, 2, this.id);
        SafeParcelWriter.writeString(arg4, 3, this.zzbf, false);
        SafeParcelWriter.writeString(arg4, 4, this.zzbg, false);
        SafeParcelWriter.writeString(arg4, 5, this.zzbh, false);
        SafeParcelWriter.writeString(arg4, 6, this.zzbi, false);
        SafeParcelWriter.writeString(arg4, 7, this.zzbj, false);
        String v0 = this.zzbk == null ? this.zzbf : this.zzbk;
        SafeParcelWriter.writeString(arg4, 8, v0, false);
        SafeParcelWriter.writeByte(arg4, 9, this.zzbl);
        SafeParcelWriter.writeByte(arg4, 10, this.zzbm);
        SafeParcelWriter.writeByte(arg4, 11, this.zzbn);
        SafeParcelWriter.writeByte(arg4, 12, this.zzbo);
        SafeParcelWriter.writeString(arg4, 13, this.packageName, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

