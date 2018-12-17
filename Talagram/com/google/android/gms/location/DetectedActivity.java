package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import java.util.Comparator;

@Class(creator="DetectedActivityCreator") @Reserved(value={1000}) public class DetectedActivity extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR = null;
    public static final int IN_VEHICLE = 0;
    public static final int ON_BICYCLE = 1;
    public static final int ON_FOOT = 2;
    public static final int RUNNING = 8;
    public static final int STILL = 3;
    public static final int TILTING = 5;
    public static final int UNKNOWN = 4;
    public static final int WALKING = 7;
    @Field(id=1) private int zzi;
    private static final Comparator zzo;
    private static final int[] zzp;
    private static final int[] zzq;
    private static final int[] zzr;
    @Field(id=2) private int zzs;

    static {
        DetectedActivity.zzo = new zzh();
        DetectedActivity.zzp = new int[]{9, 10};
        DetectedActivity.zzq = new int[]{0, 1, 2, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 16, 17, 18, 19};
        DetectedActivity.zzr = new int[]{0, 1, 2, 3, 7, 8, 16, 17};
        DetectedActivity.CREATOR = new zzi();
    }

    @Constructor public DetectedActivity(@Param(id=1) int arg1, @Param(id=2) int arg2) {
        super();
        this.zzi = arg1;
        this.zzs = arg2;
    }

    public boolean equals(Object arg5) {
        if(this == (((DetectedActivity)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else if(this.zzi == ((DetectedActivity)arg5).zzi && this.zzs == ((DetectedActivity)arg5).zzs) {
                return 1;
            }
        }

        return 0;
    }

    public int getConfidence() {
        return this.zzs;
    }

    public int getType() {
        int v0 = this.zzi;
        if(v0 <= 19) {
            if(v0 < 0) {
            }
            else {
                return v0;
            }
        }

        return 4;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zzi), Integer.valueOf(this.zzs)});
    }

    public String toString() {
        String v0_1;
        int v0 = this.getType();
        switch(v0) {
            case 0: {
                goto label_28;
            }
            case 1: {
                goto label_26;
            }
            case 2: {
                goto label_24;
            }
            case 3: {
                goto label_22;
            }
            case 4: {
                goto label_20;
            }
            case 5: {
                goto label_18;
            }
            default: {
                switch(v0) {
                    case 7: {
                        goto label_16;
                    }
                    case 8: {
                        goto label_14;
                    }
                    default: {
                        switch(v0) {
                            case 16: {
                                v0_1 = "IN_ROAD_VEHICLE";
                                goto label_29;
                            label_14:
                                v0_1 = "RUNNING";
                                goto label_29;
                            label_16:
                                v0_1 = "WALKING";
                                goto label_29;
                            label_18:
                                v0_1 = "TILTING";
                                goto label_29;
                            label_20:
                                v0_1 = "UNKNOWN";
                                goto label_29;
                            label_22:
                                v0_1 = "STILL";
                                goto label_29;
                            label_24:
                                v0_1 = "ON_FOOT";
                                goto label_29;
                            label_26:
                                v0_1 = "ON_BICYCLE";
                                goto label_29;
                            label_28:
                                v0_1 = "IN_VEHICLE";
                                goto label_29;
                            }
                            case 17: {
                                v0_1 = "IN_RAIL_VEHICLE";
                                goto label_29;
                            }
                            case 18: {
                                v0_1 = "IN_TWO_WHEELER_VEHICLE";
                                goto label_29;
                            }
                            case 19: {
                                v0_1 = "IN_FOUR_WHEELER_VEHICLE";
                                goto label_29;
                            }
                            default: {
                                v0_1 = Integer.toString(v0);
                                goto label_29;
                            }
                        }
                    }
                }
            }
        }

    label_29:
        int v1 = this.zzs;
        StringBuilder v3 = new StringBuilder(String.valueOf(v0_1).length() + 48);
        v3.append("DetectedActivity [type=");
        v3.append(v0_1);
        v3.append(", confidence=");
        v3.append(v1);
        v3.append("]");
        return v3.toString();
    }

    public void writeToParcel(Parcel arg3, int arg4) {
        arg4 = SafeParcelWriter.beginObjectHeader(arg3);
        SafeParcelWriter.writeInt(arg3, 1, this.zzi);
        SafeParcelWriter.writeInt(arg3, 2, this.zzs);
        SafeParcelWriter.finishObjectHeader(arg3, arg4);
    }

    public static void zzb(int arg5) {
        int[] v0 = DetectedActivity.zzr;
        int v1 = v0.length;
        int v2 = 0;
        int v3 = 0;
        while(v2 < v1) {
            if(v0[v2] == arg5) {
                v3 = 1;
            }

            ++v2;
        }

        if(v3 == 0) {
            StringBuilder v2_1 = new StringBuilder(81);
            v2_1.append(arg5);
            v2_1.append(" is not a valid DetectedActivity supported by Activity Transition API.");
            Log.w("DetectedActivity", v2_1.toString());
        }
    }
}

