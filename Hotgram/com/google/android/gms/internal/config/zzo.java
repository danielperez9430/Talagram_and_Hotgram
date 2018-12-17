package com.google.android.gms.internal.config;

import android.os.Bundle;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataBufferSafeParcelable;
import com.google.android.gms.common.data.DataHolder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Pattern;

public final class zzo implements zzg {
    private static final Charset UTF_8;
    private static final Pattern zzl;
    private static final Pattern zzm;

    static {
        zzo.UTF_8 = Charset.forName("UTF-8");
        zzo.zzl = Pattern.compile("^(1|true|t|yes|y|on)$", 2);
        zzo.zzm = Pattern.compile("^(0|false|f|no|n|off|)$", 2);
    }

    public zzo() {
        super();
    }

    private static HashMap zza(zzad arg7) {
        HashMap v0 = null;
        if(arg7 == null) {
            return v0;
        }

        DataHolder v1 = arg7.zzi();
        if(v1 == null) {
            return v0;
        }

        Object v0_1 = new DataBufferSafeParcelable(v1, zzaj.CREATOR).get(0);
        arg7.zzk();
        HashMap v7 = new HashMap();
        Iterator v1_1 = ((zzaj)v0_1).zzm().keySet().iterator();
    label_17:
        if(v1_1.hasNext()) {
            Object v2 = v1_1.next();
            TreeMap v3 = new TreeMap();
            v7.put(v2, v3);
            Bundle v2_1 = ((zzaj)v0_1).zzm().getBundle(((String)v2));
            Iterator v4 = v2_1.keySet().iterator();
            while(true) {
                if(!v4.hasNext()) {
                    goto label_17;
                }

                Object v5 = v4.next();
                v3.put(v5, v2_1.getByteArray(((String)v5)));
            }
        }

        return v7;
    }

    public final PendingResult zza(GoogleApiClient arg2, zzi arg3) {
        if(arg2 != null) {
            if(arg3 == null) {
            }
            else {
                return arg2.enqueue(new zzp(this, arg2, arg3));
            }
        }

        return null;
    }

    static List zzb(zzad arg4) {
        List v0 = null;
        if(arg4 == null) {
            return v0;
        }

        DataHolder v1 = arg4.zzj();
        if(v1 == null) {
            return v0;
        }

        ArrayList v0_1 = new ArrayList();
        Iterator v1_1 = new DataBufferSafeParcelable(v1, zzx.CREATOR).iterator();
        while(v1_1.hasNext()) {
            ((List)v0_1).add(v1_1.next().getPayload());
        }

        arg4.zzl();
        return ((List)v0_1);
    }

    static HashMap zzc(zzad arg0) {
        return zzo.zza(arg0);
    }

    private static Status zzd(int arg2) {
        String v1;
        if(arg2 == -6508) {
            v1 = "SUCCESS_CACHE_STALE";
        }
        else if(arg2 != 6507) {
            switch(arg2) {
                case -6506: {
                    goto label_21;
                }
                case -6505: {
                    goto label_19;
                }
            }

            switch(arg2) {
                case 6500: {
                    goto label_17;
                }
                case 6501: {
                    goto label_15;
                }
                case 6502: {
                    goto label_13;
                }
                case 6503: {
                    goto label_11;
                }
                case 6504: {
                    goto label_9;
                }
            }

            v1 = CommonStatusCodes.getStatusCodeString(arg2);
            goto label_26;
        label_17:
            v1 = "NOT_AUTHORIZED_TO_FETCH";
            goto label_26;
        label_9:
            v1 = "FAILURE_CACHE";
            goto label_26;
        label_11:
            v1 = "NOT_AVAILABLE";
            goto label_26;
        label_13:
            v1 = "FETCH_THROTTLED";
            goto label_26;
        label_15:
            v1 = "ANOTHER_FETCH_INFLIGHT";
            goto label_26;
        label_19:
            v1 = "SUCCESS_FRESH";
            goto label_26;
        label_21:
            v1 = "SUCCESS_CACHE";
        }
        else {
            v1 = "FETCH_THROTTLED_STALE";
        }

    label_26:
        return new Status(arg2, v1);
    }

    static Status zze(int arg0) {
        return zzo.zzd(arg0);
    }
}

