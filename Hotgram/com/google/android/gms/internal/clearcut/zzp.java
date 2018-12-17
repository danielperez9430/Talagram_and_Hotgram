package com.google.android.gms.internal.clearcut;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.clearcut.ClearcutLogger$zza;
import com.google.android.gms.clearcut.zze;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.phenotype.Phenotype;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public final class zzp implements zza {
    private static final Charset UTF_8;
    private static final zzao zzaq;
    private static final zzao zzar;
    private static final ConcurrentHashMap zzas;
    private static final HashMap zzat;
    @VisibleForTesting private static Boolean zzau;
    @VisibleForTesting private static Long zzav;
    @VisibleForTesting private static final zzae zzaw;
    private final Context zzh;

    static {
        zzp.UTF_8 = Charset.forName("UTF-8");
        zzp.zzaq = new zzao(Phenotype.getContentProviderUri("com.google.android.gms.clearcut.public")).zzc("gms:playlog:service:samplingrules_").zzd("LogSamplingRules__");
        zzp.zzar = new zzao(Phenotype.getContentProviderUri("com.google.android.gms.clearcut.public")).zzc("gms:playlog:service:sampling_").zzd("LogSampling__");
        zzp.zzas = new ConcurrentHashMap();
        zzp.zzat = new HashMap();
        zzp.zzau = null;
        zzp.zzav = null;
        zzp.zzaw = zzp.zzaq.zzc("enable_log_sampling_rules", false);
    }

    public zzp(Context arg1) {
        super();
        this.zzh = arg1;
        if(this.zzh != null) {
            zzae.maybeInit(this.zzh);
        }
    }

    @VisibleForTesting private static long zza(String arg2, long arg3) {
        byte[] v2;
        int v0 = 8;
        if(arg2 == null || (arg2.isEmpty())) {
            v2 = ByteBuffer.allocate(v0).putLong(arg3).array();
        }
        else {
            v2 = arg2.getBytes(zzp.UTF_8);
            ByteBuffer v0_1 = ByteBuffer.allocate(v2.length + v0);
            v0_1.put(v2);
            v0_1.putLong(arg3);
            v2 = v0_1.array();
        }

        return zzk.zza(v2);
    }

    @VisibleForTesting private static zzb zza(String arg9) {
        long v5;
        long v3_1;
        String v2_1;
        zzb v0 = null;
        if(arg9 == null) {
            return v0;
        }

        String v1 = "";
        int v2 = arg9.indexOf(44);
        int v3 = 0;
        if(v2 >= 0) {
            v1 = arg9.substring(0, v2);
            v3 = v2 + 1;
        }

        v2 = arg9.indexOf(47, v3);
        if(v2 <= 0) {
            v1 = "LogSamplerImpl";
            v2_1 = "Failed to parse the rule: ";
            arg9 = String.valueOf(arg9);
            arg9 = arg9.length() != 0 ? v2_1.concat(arg9) : new String(v2_1);
            Log.e(v1, arg9);
            return v0;
        }

        try {
            v3_1 = Long.parseLong(arg9.substring(v3, v2));
            v5 = Long.parseLong(arg9.substring(v2 + 1));
            long v7 = 0;
            if(v3_1 < v7) {
                goto label_39;
            }
        }
        catch(NumberFormatException v1_1) {
            v2_1 = "LogSamplerImpl";
            String v3_2 = "parseLong() failed while parsing: ";
            arg9 = String.valueOf(arg9);
            arg9 = arg9.length() != 0 ? v3_2.concat(arg9) : new String(v3_2);
            Log.e(v2_1, arg9, ((Throwable)v1_1));
            return v0;
        }

        if(v5 < v7) {
        }
        else {
            return zzb.zzfz().zzn(v1).zzr(v3_1).zzs(v5).zzbh();
        }

    label_39:
        StringBuilder v2_2 = new StringBuilder(72);
        v2_2.append("negative values not supported: ");
        v2_2.append(v3_1);
        v2_2.append("/");
        v2_2.append(v5);
        Log.e("LogSamplerImpl", v2_2.toString());
        return v0;
    }

    public final boolean zza(zze arg13) {
        Object v1_1;
        List v0_1;
        zzae v13_2;
        String v0 = arg13.zzag.zzj;
        int v1 = arg13.zzag.zzk;
        int v13 = arg13.zzaa != null ? arg13.zzaa.zzbji : 0;
        String v4 = null;
        if(!zzp.zzaw.get().booleanValue()) {
            if(v0 != null && !v0.isEmpty()) {
            }
            else if(v1 >= 0) {
                v0 = String.valueOf(v1);
            }
            else {
                v0 = v4;
            }

            if(v0 == null) {
                return 1;
            }

            if(this.zzh != null) {
                if(!zzp.zzc(this.zzh)) {
                }
                else {
                    Object v13_1 = zzp.zzat.get(v0);
                    if(v13_1 == null) {
                        v13_2 = zzp.zzar.zza(v0, v4);
                        zzp.zzat.put(v0, v13_2);
                    }

                    Object v4_1 = v13_2.get();
                }
            }

            zzb v13_3 = zzp.zza(v4);
            if(v13_3 == null) {
                return 1;
            }

            return zzp.zzb(zzp.zza(v13_3.zzfw(), zzp.zzd(this.zzh)), v13_3.zzfx(), v13_3.zzfy());
        }
        else {
            if(v0 != null && !v0.isEmpty()) {
            }
            else if(v1 >= 0) {
                v0 = String.valueOf(v1);
            }
            else {
                v0 = v4;
            }

            if(v0 == null) {
                return 1;
            }

            if(this.zzh == null) {
                v0_1 = Collections.emptyList();
            }
            else {
                v1_1 = zzp.zzas.get(v0);
                if(v1_1 == null) {
                    zzae v1_2 = zzp.zzaq.zza(v0, com.google.android.gms.internal.clearcut.zzgw$zza.zzft(), zzq.zzax);
                    Object v0_2 = zzp.zzas.putIfAbsent(v0, v1_2);
                    if(v0_2 != null) {
                        v1_1 = v0_2;
                    }
                }

                v0_1 = ((zzae)v1_1).get().zzfs();
            }

            Iterator v0_3 = v0_1.iterator();
            do {
            label_77:
                if(!v0_3.hasNext()) {
                    return 1;
                }

                v1_1 = v0_3.next();
                if((((zzb)v1_1).zzfv()) && ((zzb)v1_1).getEventCode() != 0 && ((zzb)v1_1).getEventCode() != v13) {
                    goto label_77;
                }
            }
            while(zzp.zzb(zzp.zza(((zzb)v1_1).zzfw(), zzp.zzd(this.zzh)), ((zzb)v1_1).zzfx(), ((zzb)v1_1).zzfy()));

            return 0;
        }

        return 1;
    }

    @VisibleForTesting private static boolean zzb(long arg6, long arg8, long arg10) {
        long v0 = 0;
        if(arg8 >= v0 && arg10 > v0) {
            if(arg6 >= v0) {
                arg6 %= arg10;
            }
            else {
                arg6 = (9223372036854775807L % arg10 + 1 + (arg6 & 9223372036854775807L) % arg10) % arg10;
            }

            if(arg6 < arg8) {
                return 1;
            }

            return 0;
        }

        return 1;
    }

    private static boolean zzc(Context arg1) {
        if(zzp.zzau == null) {
            boolean v1 = Wrappers.packageManager(arg1).checkCallingOrSelfPermission("com.google.android.providers.gsf.permission.READ_GSERVICES") == 0 ? true : false;
            zzp.zzau = Boolean.valueOf(v1);
        }

        return zzp.zzau.booleanValue();
    }

    @VisibleForTesting private static long zzd(Context arg3) {
        if(zzp.zzav == null) {
            long v0 = 0;
            if(arg3 != null) {
                if(zzp.zzc(arg3)) {
                    v0 = zzy.getLong(arg3.getContentResolver(), "android_id", v0);
                }

                zzp.zzav = Long.valueOf(v0);
            }
            else {
                return v0;
            }
        }

        return zzp.zzav.longValue();
    }
}

