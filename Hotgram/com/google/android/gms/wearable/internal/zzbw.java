package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.net.Uri;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi$DataListener;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataItemAsset;
import com.google.android.gms.wearable.PutDataRequest;

public final class zzbw implements DataApi {
    public zzbw() {
        super();
    }

    public final PendingResult addListener(GoogleApiClient arg4, DataListener arg5) {
        return zzbw.zza(arg4, arg5, new IntentFilter[]{zzgj.zzc("com.google.android.gms.wearable.DATA_CHANGED")});
    }

    public final PendingResult addListener(GoogleApiClient arg5, DataListener arg6, Uri arg7, int arg8) {
        Asserts.checkNotNull(arg7, "uri must not be null");
        boolean v2 = arg8 == 0 || arg8 == 1 ? true : false;
        Preconditions.checkArgument(v2, "invalid filter type");
        return zzbw.zza(arg5, arg6, new IntentFilter[]{zzgj.zza("com.google.android.gms.wearable.DATA_CHANGED", arg7, arg8)});
    }

    public final PendingResult deleteDataItems(GoogleApiClient arg2, Uri arg3) {
        return this.deleteDataItems(arg2, arg3, 0);
    }

    public final PendingResult deleteDataItems(GoogleApiClient arg3, Uri arg4, int arg5) {
        Asserts.checkNotNull(arg4, "uri must not be null");
        boolean v0 = true;
        if(arg5 != 0) {
            if(arg5 == 1) {
            }
            else {
                v0 = false;
            }
        }

        Preconditions.checkArgument(v0, "invalid filter type");
        return arg3.enqueue(new zzcb(this, arg3, arg4, arg5));
    }

    public final PendingResult getDataItem(GoogleApiClient arg2, Uri arg3) {
        return arg2.enqueue(new zzby(this, arg2, arg3));
    }

    public final PendingResult getDataItems(GoogleApiClient arg2) {
        return arg2.enqueue(new zzbz(this, arg2));
    }

    public final PendingResult getDataItems(GoogleApiClient arg2, Uri arg3) {
        return this.getDataItems(arg2, arg3, 0);
    }

    public final PendingResult getDataItems(GoogleApiClient arg3, Uri arg4, int arg5) {
        Asserts.checkNotNull(arg4, "uri must not be null");
        boolean v0 = true;
        if(arg5 != 0) {
            if(arg5 == 1) {
            }
            else {
                v0 = false;
            }
        }

        Preconditions.checkArgument(v0, "invalid filter type");
        return arg3.enqueue(new zzca(this, arg3, arg4, arg5));
    }

    public final PendingResult getFdForAsset(GoogleApiClient arg2, Asset arg3) {
        if(arg3 != null) {
            if(arg3.getDigest() != null) {
                if(arg3.getData() == null) {
                    return arg2.enqueue(new zzcc(this, arg2, arg3));
                }

                throw new IllegalArgumentException("invalid asset");
            }

            throw new IllegalArgumentException("invalid asset");
        }

        throw new IllegalArgumentException("asset is null");
    }

    public final PendingResult getFdForAsset(GoogleApiClient arg2, DataItemAsset arg3) {
        return arg2.enqueue(new zzcd(this, arg2, arg3));
    }

    public final PendingResult putDataItem(GoogleApiClient arg2, PutDataRequest arg3) {
        return arg2.enqueue(new zzbx(this, arg2, arg3));
    }

    public final PendingResult removeListener(GoogleApiClient arg2, DataListener arg3) {
        return arg2.enqueue(new zzcf(this, arg2, arg3));
    }

    private static PendingResult zza(GoogleApiClient arg1, DataListener arg2, IntentFilter[] arg3) {
        return zzb.zza(arg1, new zzce(arg3), arg2);
    }
}

