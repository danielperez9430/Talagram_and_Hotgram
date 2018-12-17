package com.google.android.gms.location.places.internal;

import android.os.Parcelable$Creator;
import android.util.Log;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.internal.places.zzdq;
import com.google.android.gms.internal.places.zzkt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class zzav extends DataBufferRef {
    public zzav(DataHolder arg1, int arg2) {
        super(arg1, arg2);
    }

    private final byte[] zzb(String arg1, byte[] arg2) {
        if((this.hasColumn(arg1)) && !this.hasNull(arg1)) {
            return this.getByteArray(arg1);
        }

        return null;
    }

    protected final float zzb(String arg2, float arg3) {
        if((this.hasColumn(arg2)) && !this.hasNull(arg2)) {
            return this.getFloat(arg2);
        }

        return arg3;
    }

    protected final SafeParcelable zzb(String arg2, Parcelable$Creator arg3) {
        byte[] v0 = null;
        byte[] v2 = this.zzb(arg2, v0);
        if(v2 == null) {
            return ((SafeParcelable)v0);
        }

        return SafeParcelableSerializer.deserializeFromBytes(v2, arg3);
    }

    protected final List zzb(String arg5, Parcelable$Creator arg6, List arg7) {
        ArrayList v0;
        byte[] v5 = this.zzb(arg5, null);
        if(v5 == null) {
            return arg7;
        }

        try {
            zzdq v5_2 = zzdq.zzb(v5);
            if(v5_2.zzhy == null) {
                return arg7;
            }

            v0 = new ArrayList(v5_2.zzhy.length);
            byte[][] v5_3 = v5_2.zzhy;
            int v1 = v5_3.length;
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                ((List)v0).add(SafeParcelableSerializer.deserializeFromBytes(v5_3[v2], arg6));
            }
        }
        catch(zzkt v5_1) {
            goto label_26;
        }

        return ((List)v0);
    label_26:
        if(Log.isLoggable("SafeDataBufferRef", 6)) {
            Log.e("SafeDataBufferRef", "Cannot parse byte[]", ((Throwable)v5_1));
        }

        return arg7;
    }

    protected final List zzb(String arg4, List arg5) {
        ArrayList v0;
        byte[] v4 = this.zzb(arg4, null);
        if(v4 == null) {
            return arg5;
        }

        try {
            zzdq v4_2 = zzdq.zzb(v4);
            if(v4_2.zzhx == null) {
                return arg5;
            }

            v0 = new ArrayList(v4_2.zzhx.length);
            int v1;
            for(v1 = 0; v1 < v4_2.zzhx.length; ++v1) {
                ((List)v0).add(Integer.valueOf(v4_2.zzhx[v1]));
            }
        }
        catch(zzkt v4_1) {
            goto label_27;
        }

        return ((List)v0);
    label_27:
        if(Log.isLoggable("SafeDataBufferRef", 6)) {
            Log.e("SafeDataBufferRef", "Cannot parse byte[]", ((Throwable)v4_1));
        }

        return arg5;
    }

    protected final int zzc(String arg2, int arg3) {
        if((this.hasColumn(arg2)) && !this.hasNull(arg2)) {
            return this.getInteger(arg2);
        }

        return arg3;
    }

    protected final String zzc(String arg2, String arg3) {
        if((this.hasColumn(arg2)) && !this.hasNull(arg2)) {
            return this.getString(arg2);
        }

        return arg3;
    }

    protected final List zzc(String arg3, List arg4) {
        byte[] v3 = this.zzb(arg3, null);
        if(v3 == null) {
            return arg4;
        }

        try {
            zzdq v3_2 = zzdq.zzb(v3);
            if(v3_2.zzhw == null) {
                return arg4;
            }

            return Arrays.asList(v3_2.zzhw);
        }
        catch(zzkt v3_1) {
            if(Log.isLoggable("SafeDataBufferRef", 6)) {
                Log.e("SafeDataBufferRef", "Cannot parse byte[]", ((Throwable)v3_1));
            }

            return arg4;
        }
    }
}

