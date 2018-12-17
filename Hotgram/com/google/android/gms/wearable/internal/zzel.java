package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.wearable.zzb;
import com.google.android.gms.internal.wearable.zzc;

public abstract class zzel extends zzb implements zzek {
    public zzel() {
        super("com.google.android.gms.wearable.internal.IWearableCallbacks");
    }

    protected final boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
        switch(arg1) {
            case 2: {
                goto label_103;
            }
            case 3: {
                goto label_99;
            }
            case 4: {
                goto label_95;
            }
            case 5: {
                goto label_91;
            }
            case 6: {
                goto label_87;
            }
            case 7: {
                goto label_83;
            }
            case 8: {
                goto label_79;
            }
            case 9: {
                goto label_75;
            }
            case 10: {
                goto label_71;
            }
            case 11: {
                goto label_67;
            }
            case 12: {
                goto label_63;
            }
            case 13: {
                goto label_59;
            }
            case 14: {
                goto label_55;
            }
            case 15: {
                goto label_51;
            }
            case 16: {
                goto label_47;
            }
            case 17: {
                goto label_43;
            }
            case 18: {
                goto label_39;
            }
            case 19: {
                goto label_35;
            }
            case 20: {
                goto label_31;
            }
            case 22: {
                goto label_27;
            }
            case 23: {
                goto label_23;
            }
            case 26: {
                goto label_19;
            }
            case 27: {
                goto label_15;
            }
            case 28: {
                goto label_11;
            }
            case 29: {
                goto label_7;
            }
            case 30: {
                goto label_3;
            }
        }

        return 0;
    label_99:
        this.zza(zzc.zza(arg2, zzfu.CREATOR));
        goto label_106;
    label_35:
        this.zza(zzc.zza(arg2, zzbn.CREATOR));
        goto label_106;
    label_103:
        this.zza(zzc.zza(arg2, zzdw.CREATOR));
        goto label_106;
    label_39:
        this.zza(zzc.zza(arg2, zzdo.CREATOR));
        goto label_106;
    label_43:
        this.zza(zzc.zza(arg2, zzdm.CREATOR));
        goto label_106;
    label_47:
        this.zzb(zzc.zza(arg2, zzbt.CREATOR));
        goto label_106;
    label_51:
        this.zza(zzc.zza(arg2, zzbt.CREATOR));
        goto label_106;
    label_55:
        this.zza(zzc.zza(arg2, zzfq.CREATOR));
        goto label_106;
    label_59:
        this.zza(zzc.zza(arg2, zzdy.CREATOR));
        goto label_106;
    label_63:
        this.zza(zzc.zza(arg2, zzge.CREATOR));
        goto label_106;
    label_67:
        this.zza(zzc.zza(arg2, Status.CREATOR));
        goto label_106;
    label_3:
        this.zza(zzc.zza(arg2, zzdt.CREATOR));
        goto label_106;
    label_71:
        this.zza(zzc.zza(arg2, zzea.CREATOR));
        goto label_106;
    label_7:
        this.zza(zzc.zza(arg2, zzdv.CREATOR));
        goto label_106;
    label_75:
        this.zza(zzc.zza(arg2, zzeg.CREATOR));
        goto label_106;
    label_11:
        this.zza(zzc.zza(arg2, zzdr.CREATOR));
        goto label_106;
    label_79:
        this.zza(zzc.zza(arg2, zzee.CREATOR));
        goto label_106;
    label_15:
        this.zza(zzc.zza(arg2, zzfy.CREATOR));
        goto label_106;
    label_83:
        this.zza(zzc.zza(arg2, zzga.CREATOR));
        goto label_106;
    label_19:
        this.zza(zzc.zza(arg2, zzf.CREATOR));
        goto label_106;
    label_87:
        this.zza(zzc.zza(arg2, zzdg.CREATOR));
        goto label_106;
    label_23:
        this.zza(zzc.zza(arg2, zzdi.CREATOR));
        goto label_106;
    label_91:
        this.zzb(zzc.zza(arg2, DataHolder.CREATOR));
        goto label_106;
    label_27:
        this.zza(zzc.zza(arg2, zzdk.CREATOR));
        goto label_106;
    label_95:
        this.zza(zzc.zza(arg2, zzec.CREATOR));
        goto label_106;
    label_31:
        this.zza(zzc.zza(arg2, zzbp.CREATOR));
    label_106:
        arg3.writeNoException();
        return 1;
    }
}

