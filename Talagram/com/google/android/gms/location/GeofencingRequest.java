package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.internal.location.zzbh;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Class(creator="GeofencingRequestCreator") @Reserved(value={1000}) public class GeofencingRequest extends AbstractSafeParcelable {
    public final class Builder {
        private String tag;
        private final List zzap;
        private int zzaq;

        public Builder() {
            super();
            this.zzap = new ArrayList();
            this.zzaq = 5;
            this.tag = "";
        }

        public final Builder addGeofence(Geofence arg3) {
            Preconditions.checkNotNull(arg3, "geofence can\'t be null.");
            Preconditions.checkArgument(arg3 instanceof zzbh, "Geofence must be created using Geofence.Builder.");
            this.zzap.add(arg3);
            return this;
        }

        public final Builder addGeofences(List arg2) {
            if(arg2 != null) {
                if(arg2.isEmpty()) {
                }
                else {
                    Iterator v2 = arg2.iterator();
                    while(v2.hasNext()) {
                        Object v0 = v2.next();
                        if(v0 == null) {
                            continue;
                        }

                        this.addGeofence(((Geofence)v0));
                    }
                }
            }

            return this;
        }

        public final GeofencingRequest build() {
            Preconditions.checkArgument(this.zzap.isEmpty() ^ 1, "No geofence has been added to this request.");
            return new GeofencingRequest(this.zzap, this.zzaq, this.tag);
        }

        public final Builder setInitialTrigger(int arg1) {
            this.zzaq = arg1 & 7;
            return this;
        }
    }

    public static final Parcelable$Creator CREATOR = null;
    public static final int INITIAL_TRIGGER_DWELL = 4;
    public static final int INITIAL_TRIGGER_ENTER = 1;
    public static final int INITIAL_TRIGGER_EXIT = 2;
    @Field(defaultValue="", getter="getTag", id=3) private final String tag;
    @Field(getter="getParcelableGeofences", id=1) private final List zzap;
    @Field(getter="getInitialTrigger", id=2) private final int zzaq;

    static {
        GeofencingRequest.CREATOR = new zzq();
    }

    @Constructor GeofencingRequest(@Param(id=1) List arg1, @Param(id=2) int arg2, @Param(id=3) String arg3) {
        super();
        this.zzap = arg1;
        this.zzaq = arg2;
        this.tag = arg3;
    }

    public List getGeofences() {
        ArrayList v0 = new ArrayList();
        ((List)v0).addAll(this.zzap);
        return ((List)v0);
    }

    public int getInitialTrigger() {
        return this.zzaq;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder();
        v0.append("GeofencingRequest[");
        v0.append("geofences=");
        v0.append(this.zzap);
        int v1 = this.zzaq;
        StringBuilder v2 = new StringBuilder(30);
        v2.append(", initialTrigger=");
        v2.append(v1);
        v2.append(", ");
        v0.append(v2.toString());
        String v1_1 = "tag=";
        String v2_1 = String.valueOf(this.tag);
        v1_1 = v2_1.length() != 0 ? v1_1.concat(v2_1) : new String(v1_1);
        v0.append(v1_1);
        v0.append("]");
        return v0.toString();
    }

    public void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeTypedList(arg4, 1, this.zzap, false);
        SafeParcelWriter.writeInt(arg4, 2, this.getInitialTrigger());
        SafeParcelWriter.writeString(arg4, 3, this.tag, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

