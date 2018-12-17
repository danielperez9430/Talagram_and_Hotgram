package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Class(creator="PatternItemCreator") @Reserved(value={1}) public class PatternItem extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR = null;
    private static final String TAG = "PatternItem";
    @Field(getter="getType", id=2) private final int type;
    @Field(getter="getLength", id=3) private final Float zzdu;

    static {
        PatternItem.CREATOR = new zzi();
    }

    @Constructor public PatternItem(@Param(id=2) int arg5, @Param(id=3) Float arg6) {
        super();
        boolean v0 = true;
        if(arg5 != 1 && (arg6 == null || arg6.floatValue() < 0f)) {
            v0 = false;
        }

        String v1 = String.valueOf(arg6);
        StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 45);
        v3.append("Invalid PatternItem: type=");
        v3.append(arg5);
        v3.append(" length=");
        v3.append(v1);
        Preconditions.checkArgument(v0, v3.toString());
        this.type = arg5;
        this.zzdu = arg6;
    }

    public boolean equals(Object arg5) {
        if(this == (((PatternItem)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof PatternItem)) {
            return 0;
        }

        if(this.type == ((PatternItem)arg5).type && (Objects.equal(this.zzdu, ((PatternItem)arg5).zzdu))) {
            return 1;
        }

        return 0;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.type), this.zzdu});
    }

    public String toString() {
        int v0 = this.type;
        String v1 = String.valueOf(this.zzdu);
        StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 39);
        v3.append("[PatternItem: type=");
        v3.append(v0);
        v3.append(" length=");
        v3.append(v1);
        v3.append("]");
        return v3.toString();
    }

    public void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeInt(arg4, 2, this.type);
        SafeParcelWriter.writeFloatObject(arg4, 3, this.zzdu, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }

    static List zza(List arg7) {
        Dot v2_2;
        List v0 = null;
        if(arg7 == null) {
            return v0;
        }

        ArrayList v1 = new ArrayList(arg7.size());
        Iterator v7 = arg7.iterator();
        while(v7.hasNext()) {
            Object v2 = v7.next();
            if(v2 == null) {
                v2 = v0;
            }
            else {
                switch(((PatternItem)v2).type) {
                    case 0: {
                        goto label_34;
                    }
                    case 1: {
                        goto label_31;
                    }
                    case 2: {
                        goto label_26;
                    }
                }

                String v3 = PatternItem.TAG;
                int v4 = ((PatternItem)v2).type;
                StringBuilder v6 = new StringBuilder(37);
                v6.append("Unknown PatternItem type: ");
                v6.append(v4);
                Log.w(v3, v6.toString());
                goto label_39;
            label_34:
                Dash v3_1 = new Dash(((PatternItem)v2).zzdu.floatValue());
                goto label_38;
            label_26:
                Gap v3_2 = new Gap(((PatternItem)v2).zzdu.floatValue());
            label_38:
                Gap v2_1 = v3_2;
                goto label_39;
            label_31:
                v2_2 = new Dot();
            }

        label_39:
            ((List)v1).add(v2_2);
        }

        return ((List)v1);
    }
}

