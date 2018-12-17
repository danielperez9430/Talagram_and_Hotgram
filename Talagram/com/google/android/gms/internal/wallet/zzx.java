package com.google.android.gms.internal.wallet;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.zzas;

public abstract class zzx extends zzb implements zzw {
    public zzx() {
        super("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
    }

    protected final boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
        switch(arg1) {
            case 1: {
                goto label_92;
            }
            case 2: {
                goto label_85;
            }
            case 3: {
                goto label_79;
            }
            case 4: {
                goto label_74;
            }
            case 6: {
                goto label_68;
            }
            case 7: {
                goto label_60;
            }
            case 8: {
                goto label_54;
            }
            case 9: {
                goto label_47;
            }
            case 10: {
                goto label_39;
            }
            case 11: {
                goto label_33;
            }
            case 12: {
                goto label_25;
            }
            case 13: {
                goto label_19;
            }
            case 14: {
                goto label_11;
            }
            case 15: {
                goto label_3;
            }
        }

        return 0;
    label_33:
        this.zzb(zzc.zza(arg2, Status.CREATOR), zzc.zza(arg2, Bundle.CREATOR));
        return 1;
    label_3:
        this.zza(zzc.zza(arg2, Status.CREATOR), zzc.zza(arg2, zzl.CREATOR), zzc.zza(arg2, Bundle.CREATOR));
        return 1;
    label_68:
        this.zzb(arg2.readInt(), zzc.zza(arg2), zzc.zza(arg2, Bundle.CREATOR));
        return 1;
    label_39:
        this.zza(zzc.zza(arg2, Status.CREATOR), zzc.zza(arg2, zzj.CREATOR), zzc.zza(arg2, Bundle.CREATOR));
        return 1;
    label_74:
        this.zza(arg2.readInt(), zzc.zza(arg2, Bundle.CREATOR));
        return 1;
    label_11:
        this.zza(zzc.zza(arg2, Status.CREATOR), zzc.zza(arg2, PaymentData.CREATOR), zzc.zza(arg2, Bundle.CREATOR));
        return 1;
    label_79:
        this.zza(arg2.readInt(), zzc.zza(arg2), zzc.zza(arg2, Bundle.CREATOR));
        return 1;
    label_47:
        this.zza(zzc.zza(arg2, Status.CREATOR), zzc.zza(arg2), zzc.zza(arg2, Bundle.CREATOR));
        return 1;
    label_19:
        this.zzc(zzc.zza(arg2, Status.CREATOR), zzc.zza(arg2, Bundle.CREATOR));
        return 1;
    label_85:
        this.zza(arg2.readInt(), zzc.zza(arg2, FullWallet.CREATOR), zzc.zza(arg2, Bundle.CREATOR));
        return 1;
    label_54:
        this.zza(zzc.zza(arg2, Status.CREATOR), zzc.zza(arg2, Bundle.CREATOR));
        return 1;
    label_25:
        this.zza(zzc.zza(arg2, Status.CREATOR), zzc.zza(arg2, zzas.CREATOR), zzc.zza(arg2, Bundle.CREATOR));
        return 1;
    label_92:
        this.zza(arg2.readInt(), zzc.zza(arg2, MaskedWallet.CREATOR), zzc.zza(arg2, Bundle.CREATOR));
        return 1;
    label_60:
        this.zza(zzc.zza(arg2, Status.CREATOR), zzc.zza(arg2, zzh.CREATOR), zzc.zza(arg2, Bundle.CREATOR));
        return 1;
    }
}

