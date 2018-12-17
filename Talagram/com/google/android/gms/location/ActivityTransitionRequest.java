package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

@Class(creator="ActivityTransitionRequestCreator") @Reserved(value={1000}) public class ActivityTransitionRequest extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    public static final Comparator IS_SAME_TRANSITION;
    @Field(getter="getTag", id=2) private final String tag;
    @Field(getter="getActivityTransitions", id=1) private final List zzl;
    @Field(getter="getClients", id=3) private final List zzm;

    static {
        ActivityTransitionRequest.CREATOR = new zzf();
        ActivityTransitionRequest.IS_SAME_TRANSITION = new zze();
    }

    @Constructor public ActivityTransitionRequest(@Param(id=1) List arg9, @Param(id=2) String arg10, @Param(id=3) List arg11) {
        super();
        Preconditions.checkNotNull(arg9, "transitions can\'t be null");
        boolean v0 = arg9.size() > 0 ? true : false;
        Preconditions.checkArgument(v0, "transitions can\'t be empty.");
        TreeSet v0_1 = new TreeSet(ActivityTransitionRequest.IS_SAME_TRANSITION);
        Iterator v3 = arg9.iterator();
        while(v3.hasNext()) {
            Object v4 = v3.next();
            Preconditions.checkArgument(v0_1.add(v4), String.format("Found duplicated transition: %s.", v4));
        }

        this.zzl = Collections.unmodifiableList(arg9);
        this.tag = arg10;
        arg9 = arg11 == null ? Collections.emptyList() : Collections.unmodifiableList(arg11);
        this.zzm = arg9;
    }

    public ActivityTransitionRequest(List arg2) {
        this(arg2, null, null);
    }

    public boolean equals(Object arg5) {
        if(this == (((ActivityTransitionRequest)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else if((Objects.equal(this.zzl, ((ActivityTransitionRequest)arg5).zzl)) && (Objects.equal(this.tag, ((ActivityTransitionRequest)arg5).tag)) && (Objects.equal(this.zzm, ((ActivityTransitionRequest)arg5).zzm))) {
                return 1;
            }
        }

        return 0;
    }

    public int hashCode() {
        int v0 = this.zzl.hashCode() * 31;
        int v2 = 0;
        int v1 = this.tag != null ? this.tag.hashCode() : 0;
        v0 = (v0 + v1) * 31;
        if(this.zzm != null) {
            v2 = this.zzm.hashCode();
        }

        return v0 + v2;
    }

    public void serializeToIntentExtra(Intent arg2) {
        SafeParcelableSerializer.serializeToIntentExtra(((SafeParcelable)this), arg2, "com.google.android.location.internal.EXTRA_ACTIVITY_TRANSITION_REQUEST");
    }

    public String toString() {
        String v0 = String.valueOf(this.zzl);
        String v1 = this.tag;
        String v2 = String.valueOf(this.zzm);
        StringBuilder v4 = new StringBuilder(String.valueOf(v0).length() + 61 + String.valueOf(v1).length() + String.valueOf(v2).length());
        v4.append("ActivityTransitionRequest [mTransitions=");
        v4.append(v0);
        v4.append(", mTag=\'");
        v4.append(v1);
        v4.append('\'');
        v4.append(", mClients=");
        v4.append(v2);
        v4.append(']');
        return v4.toString();
    }

    public void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeTypedList(arg4, 1, this.zzl, false);
        SafeParcelWriter.writeString(arg4, 2, this.tag, false);
        SafeParcelWriter.writeTypedList(arg4, 3, this.zzm, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

