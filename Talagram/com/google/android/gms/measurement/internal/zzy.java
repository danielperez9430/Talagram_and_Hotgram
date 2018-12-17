package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;

public final class zzy {
    final String name;
    private final String origin;
    final long timestamp;
    final long zzaic;
    final zzaa zzaid;
    final String zztt;

    zzy(zzbt arg1, String arg2, String arg3, String arg4, long arg5, long arg7, Bundle arg9) {
        zzaa v1;
        Object v5;
        super();
        Preconditions.checkNotEmpty(arg3);
        Preconditions.checkNotEmpty(arg4);
        this.zztt = arg3;
        this.name = arg4;
        if(TextUtils.isEmpty(((CharSequence)arg2))) {
            arg2 = null;
        }

        this.origin = arg2;
        this.timestamp = arg5;
        this.zzaic = arg7;
        if(this.zzaic != 0 && this.zzaic > this.timestamp) {
            arg1.zzgo().zzjg().zzg("Event created with reverse previous/current timestamps. appId", zzap.zzbv(arg3));
        }

        if(arg9 == null || (arg9.isEmpty())) {
            v1 = new zzaa(new Bundle());
        }
        else {
            Bundle v2 = new Bundle(arg9);
            Iterator v3 = v2.keySet().iterator();
            while(v3.hasNext()) {
                Object v4 = v3.next();
                if(v4 == null) {
                    arg1.zzgo().zzjd().zzbx("Param name can\'t be null");
                }
                else {
                    v5 = arg1.zzgm().zzh(((String)v4), v2.get(((String)v4)));
                    if(v5 == null) {
                        arg1.zzgo().zzjg().zzg("Param value can\'t be null", arg1.zzgl().zzbt(((String)v4)));
                    }
                    else {
                        goto label_50;
                    }
                }

                v3.remove();
                continue;
            label_50:
                arg1.zzgm().zza(v2, ((String)v4), v5);
            }

            v1 = new zzaa(v2);
        }

        this.zzaid = v1;
    }

    private zzy(zzbt arg2, String arg3, String arg4, String arg5, long arg6, long arg8, zzaa arg10) {
        super();
        Preconditions.checkNotEmpty(arg4);
        Preconditions.checkNotEmpty(arg5);
        Preconditions.checkNotNull(arg10);
        this.zztt = arg4;
        this.name = arg5;
        if(TextUtils.isEmpty(((CharSequence)arg3))) {
            arg3 = null;
        }

        this.origin = arg3;
        this.timestamp = arg6;
        this.zzaic = arg8;
        if(this.zzaic != 0 && this.zzaic > this.timestamp) {
            arg2.zzgo().zzjg().zze("Event created with reverse previous/current timestamps. appId, name", zzap.zzbv(arg4), zzap.zzbv(arg5));
        }

        this.zzaid = arg10;
    }

    public final String toString() {
        String v0 = this.zztt;
        String v1 = this.name;
        String v2 = String.valueOf(this.zzaid);
        StringBuilder v4 = new StringBuilder(String.valueOf(v0).length() + 33 + String.valueOf(v1).length() + String.valueOf(v2).length());
        v4.append("Event{appId=\'");
        v4.append(v0);
        v4.append("\', name=\'");
        v4.append(v1);
        v4.append("\', params=");
        v4.append(v2);
        v4.append('}');
        return v4.toString();
    }

    final zzy zza(zzbt arg12, long arg13) {
        return new zzy(arg12, this.origin, this.zztt, this.name, this.timestamp, arg13, this.zzaid);
    }
}

