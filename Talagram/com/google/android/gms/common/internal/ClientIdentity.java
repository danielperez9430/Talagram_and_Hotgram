package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="ClientIdentityCreator") @Reserved(value={1000}) public class ClientIdentity extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(defaultValueUnchecked="null", id=2) public final String packageName;
    @Field(defaultValueUnchecked="0", id=1) public final int uid;

    static {
        ClientIdentity.CREATOR = new ClientIdentityCreator();
    }

    @Constructor public ClientIdentity(@Param(id=1) int arg1, @Param(id=2) String arg2) {
        super();
        this.uid = arg1;
        this.packageName = arg2;
    }

    public boolean equals(Object arg5) {
        if((((ClientIdentity)arg5)) == this) {
            return 1;
        }

        if(arg5 != null) {
            if(!(arg5 instanceof ClientIdentity)) {
            }
            else if(((ClientIdentity)arg5).uid == this.uid && (Objects.equal(((ClientIdentity)arg5).packageName, this.packageName))) {
                return 1;
            }
        }

        return 0;
    }

    public int hashCode() {
        return this.uid;
    }

    public String toString() {
        int v0 = this.uid;
        String v1 = this.packageName;
        StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 12);
        v3.append(v0);
        v3.append(":");
        v3.append(v1);
        return v3.toString();
    }

    public void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeInt(arg4, 1, this.uid);
        SafeParcelWriter.writeString(arg4, 2, this.packageName, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

