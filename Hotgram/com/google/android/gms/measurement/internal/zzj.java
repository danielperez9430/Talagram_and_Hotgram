package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v4.f.a;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzfv;
import com.google.android.gms.internal.measurement.zzfw;
import com.google.android.gms.internal.measurement.zzfx;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zzfz;
import com.google.android.gms.internal.measurement.zzgd;
import com.google.android.gms.internal.measurement.zzge;
import com.google.android.gms.internal.measurement.zzgf;
import com.google.android.gms.internal.measurement.zzgg;
import com.google.android.gms.internal.measurement.zzgj;
import com.google.android.gms.internal.measurement.zzgk;
import com.google.android.gms.internal.measurement.zzgl;
import com.google.android.gms.internal.measurement.zzyy;
import com.google.android.gms.internal.measurement.zzzg;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

final class zzj extends zzez {
    zzj(zzfa arg1) {
        super(arg1);
    }

    final zzgd[] zza(String arg84, zzgf[] arg85, zzgl[] arg86) {
        byte[] v4_8;
        zzgk[] v15_4;
        a v82;
        Iterator v81;
        Object v8_3;
        zzgj v3_6;
        a v11_5;
        int v0_10;
        String v12_3;
        a v80;
        a v79;
        zzar v5_7;
        int v6_3;
        a v78;
        a v77;
        a v76;
        Iterator v75;
        Object v74;
        Iterator v2_10;
        a v72;
        BitSet v13_4;
        int v70;
        Object v69;
        a v68;
        Object v13_3;
        Iterator v6_2;
        a v5_6;
        Map v5_5;
        zzgl v4_6;
        Integer v0_9;
        Integer v3_5;
        zzar v1_13;
        Boolean v4_5;
        String v4_4;
        String v3_4;
        zzar v2_9;
        Boolean v1_12;
        Object v66;
        zzgf v15_2;
        BitSet v11_4;
        int v17_1;
        a v61;
        Iterator v16_2;
        Object v7_1;
        Object v58_1;
        a v58;
        BitSet v2_8;
        a v2_7;
        Object v54;
        BitSet v4_3;
        a v7;
        Object v53;
        Iterator v52;
        Object v51;
        Object v2_6;
        a v15_1;
        int v13_2;
        Object v11_3;
        a v1_10;
        String v5_4;
        zzgf v2_5;
        HashSet v46;
        a v47;
        a v48;
        a v45;
        a v43;
        a v44;
        zzgf v42_1;
        String v0_8;
        a v57;
        a v65;
        a v63;
        a v62;
        HashSet v14;
        a v64;
        a v50;
        Object v25;
        zzgg[] v29_1;
        Object v42;
        int v5_3;
        boolean v24;
        zzgf v13_1;
        String[] v4_2;
        Object v39;
        String v2_3;
        SQLiteDatabase v0_4;
        long v1_5;
        Object v16;
        int v26;
        a v41;
        Object v10_2;
        int v38;
        String v10_1;
        Object v0_2;
        zzgd v1_3;
        int v8_1;
        a v35;
        a v34;
        a v33;
        int v1_2;
        BitSet v3_3;
        BitSet v5_1;
        int v31;
        HashSet v30;
        int v3_1;
        Object v29;
        Iterator v28;
        a v0_1;
        Map v27;
        Object v3;
        Object v5;
        Object v4;
        int v2;
        String v15 = arg84;
        zzgf[] v13 = arg85;
        Preconditions.checkNotEmpty(arg84);
        HashSet v11 = new HashSet();
        a v12 = new a();
        a v10 = new a();
        a v9 = new a();
        a v8 = new a();
        a v6 = new a();
        boolean v23 = ((zzco)this).zzgq().zzd(v15, zzaf.zzakw);
        Map v0 = ((zzey)this).zzjq().zzbo(v15);
        if(v0 != null) {
            Iterator v1 = v0.keySet().iterator();
            while(v1.hasNext()) {
                v2 = v1.next().intValue();
                v4 = v0.get(Integer.valueOf(v2));
                v5 = ((Map)v10).get(Integer.valueOf(v2));
                v3 = ((Map)v9).get(Integer.valueOf(v2));
                if(v23) {
                    v27 = v0;
                    v0_1 = new a();
                    if(v4 != null) {
                        v28 = v1;
                        if(((zzgj)v4).zzayg == null) {
                            goto label_63;
                        }
                        else {
                            zzge[] v1_1 = ((zzgj)v4).zzayg;
                            v29 = v3;
                            v3_1 = v1_1.length;
                            v30 = v11;
                            int v11_1 = 0;
                            while(v11_1 < v3_1) {
                                v31 = v3_1;
                                zzge v3_2 = v1_1[v11_1];
                                zzge[] v32 = v1_1;
                                if(v3_2.zzawq != null) {
                                    ((Map)v0_1).put(v3_2.zzawq, v3_2.zzawr);
                                }

                                ++v11_1;
                                v3_1 = v31;
                                v1_1 = v32;
                            }
                        }
                    }
                    else {
                        v28 = v1;
                    label_63:
                        v29 = v3;
                        v30 = v11;
                    }

                    ((Map)v8).put(Integer.valueOf(v2), v0_1);
                }
                else {
                    v27 = v0;
                    v28 = v1;
                    v29 = v3;
                    v30 = v11;
                    v0_1 = null;
                }

                if(v5 == null) {
                    v5_1 = new BitSet();
                    ((Map)v10).put(Integer.valueOf(v2), v5_1);
                    v3_3 = new BitSet();
                    ((Map)v9).put(Integer.valueOf(v2), v3_3);
                }
                else {
                    v3 = v29;
                }

                v1_2 = 0;
                while(v1_2 < ((zzgj)v4).zzaye.length << 6) {
                    if(zzfg.zza(((zzgj)v4).zzaye, v1_2)) {
                        v33 = v8;
                        v34 = v9;
                        v35 = v10;
                        ((zzco)this).zzgo().zzjl().zze("Filter already evaluated. audience ID, filter ID", Integer.valueOf(v2), Integer.valueOf(v1_2));
                        v3_3.set(v1_2);
                        if(zzfg.zza(((zzgj)v4).zzayf, v1_2)) {
                            v5_1.set(v1_2);
                            v8_1 = 1;
                        }
                        else {
                            goto label_111;
                        }
                    }
                    else {
                        v33 = v8;
                        v34 = v9;
                        v35 = v10;
                    label_111:
                        v8_1 = 0;
                    }

                    if(v0_1 != null && v8_1 == 0) {
                        ((Map)v0_1).remove(Integer.valueOf(v1_2));
                    }

                    ++v1_2;
                    v8 = v33;
                    v9 = v34;
                    v10 = v35;
                }

                v33 = v8;
                v34 = v9;
                v35 = v10;
                v1_3 = new zzgd();
                ((Map)v12).put(Integer.valueOf(v2), v1_3);
                v1_3.zzawo = Boolean.valueOf(false);
                v1_3.zzawn = ((zzgj)v4);
                v1_3.zzawm = new zzgj();
                v1_3.zzawm.zzayf = zzfg.zza(v5_1);
                v1_3.zzawm.zzaye = zzfg.zza(v3_3);
                if(v23) {
                    v1_3.zzawm.zzayg = zzj.zzd(((Map)v0_1));
                    ((Map)v6).put(Integer.valueOf(v2), new a());
                }

                v0 = v27;
                v1 = v28;
                v11 = v30;
                v8 = v33;
                v9 = v34;
                v10 = v35;
            }
        }

        v33 = v8;
        v34 = v9;
        v35 = v10;
        v30 = v11;
        if(v13 != null) {
            v9 = new a();
            v8_1 = v13.length;
            long v27_1 = 0;
            long v2_1 = v27_1;
            v0_2 = null;
            Object v1_4 = null;
            int v4_1 = 0;
            while(v4_1 < v8_1) {
                zzgf v5_2 = v13[v4_1];
                v10_1 = v5_2.name;
                zzgg[] v11_2 = v5_2.zzawt;
                long v36 = v2_1;
                if(((zzco)this).zzgq().zzd(v15, zzaf.zzakq)) {
                    ((zzey)this).zzjo();
                    v3 = zzfg.zzb(v5_2, "_eid");
                    v2 = v3 != null ? 1 : 0;
                    if(v2 != 0) {
                        v38 = v4_1;
                        if(v10_1.equals("_ep")) {
                            v4_1 = 1;
                        }
                        else {
                            goto label_194;
                        }
                    }
                    else {
                        v38 = v4_1;
                    label_194:
                        v4_1 = 0;
                    }

                    if(v4_1 != 0) {
                        ((zzey)this).zzjo();
                        v10_2 = zzfg.zzb(v5_2, "_en");
                        if(TextUtils.isEmpty(((CharSequence)v10_2))) {
                            ((zzco)this).zzgo().zzjd().zzg("Extra parameter without an event name. eventId", v3);
                            v41 = v6;
                            v26 = v38;
                        }
                        else {
                            if(v0_2 == null || v1_4 == null || ((Long)v3).longValue() != ((Long)v1_4).longValue()) {
                                Pair v2_2 = ((zzey)this).zzjq().zza(v15, ((Long)v3));
                                if(v2_2 != null) {
                                    if(v2_2.first == null) {
                                    }
                                    else {
                                        v0_2 = v2_2.first;
                                        v1_5 = v2_2.second.longValue();
                                        ((zzey)this).zzjo();
                                        v16 = zzfg.zzb(((zzgf)v0_2), "_eid");
                                        v4 = v0_2;
                                        goto label_234;
                                    }
                                }

                                goto label_333;
                            }
                            else {
                                v4 = v0_2;
                                v16 = v1_4;
                                v1_5 = v36;
                            }

                        label_234:
                            long v17 = v1_5 - 1;
                            if(v17 <= v27_1) {
                                zzq v1_6 = ((zzey)this).zzjq();
                                ((zzco)v1_6).zzaf();
                                ((zzco)v1_6).zzgo().zzjl().zzg("Clearing complex main event info. appId", v15);
                                try {
                                    v0_4 = v1_6.getWritableDatabase();
                                    v2_3 = "delete from main_event_params where app_id=?";
                                    v39 = v4;
                                }
                                catch(SQLiteException v0_3) {
                                    v39 = v4;
                                    goto label_260;
                                }

                                try {
                                    v4_2 = new String[1];
                                }
                                catch(SQLiteException v0_3) {
                                    goto label_260;
                                }

                                try {
                                    v4_2[0] = v15;
                                    v0_4.execSQL(v2_3, ((Object[])v4_2));
                                    goto label_264;
                                }
                                catch(SQLiteException v0_3) {
                                }

                            label_260:
                                ((zzco)v1_6).zzgo().zzjd().zzg("Error clearing complex main event", v0_3);
                            label_264:
                                v13_1 = v5_2;
                                v41 = v6;
                                v26 = v38;
                                v1_4 = v39;
                                v24 = true;
                                goto label_284;
                            }
                            else {
                                v13_1 = v5_2;
                                v26 = v38;
                                v24 = true;
                                v41 = v6;
                                ((zzey)this).zzjq().zza(arg84, ((Long)v3), v17, v4);
                                v1_4 = v4;
                            label_284:
                                zzgg[] v0_5 = new zzgg[((zzgf)v1_4).zzawt.length + v11_2.length];
                                zzgg[] v2_4 = ((zzgf)v1_4).zzawt;
                                v3_1 = v2_4.length;
                                v4_1 = 0;
                                v5_3 = 0;
                                while(v4_1 < v3_1) {
                                    zzgg v6_1 = v2_4[v4_1];
                                    ((zzey)this).zzjo();
                                    v42 = v1_4;
                                    if(zzfg.zza(v13_1, v6_1.name) == null) {
                                        v0_5[v5_3] = v6_1;
                                        ++v5_3;
                                    }

                                    ++v4_1;
                                    v1_4 = v42;
                                }

                                v42 = v1_4;
                                if(v5_3 > 0) {
                                    v1_2 = v11_2.length;
                                    v2 = 0;
                                    goto label_310;
                                }
                                else {
                                    ((zzco)this).zzgo().zzjg().zzg("No unique parameters in main event. eventName", v10_2);
                                    v0_2 = v10_2;
                                    v29_1 = v11_2;
                                    goto label_330;
                                label_310:
                                    while(v2 < v1_2) {
                                        v0_5[v5_3] = v11_2[v2];
                                        ++v2;
                                        ++v5_3;
                                    }

                                    if(v5_3 == v0_5.length) {
                                    }
                                    else {
                                        Object[] v0_6 = Arrays.copyOf(((Object[])v0_5), v5_3);
                                    }

                                    v29_1 = v0_5;
                                    v0_2 = v10_2;
                                }

                            label_330:
                                v25 = v16;
                                v36 = v17;
                                goto label_389;
                            }

                        label_333:
                            v41 = v6;
                            v26 = v38;
                            ((zzco)this).zzgo().zzjd().zze("Extra parameter without existing main event. eventName, eventId", v10_2, v3);
                        }

                        v31 = v8_1;
                        v50 = v9;
                        v64 = v12;
                        v14 = v30;
                        v62 = v33;
                        v63 = v34;
                        v65 = v35;
                        v2_1 = v36;
                        v57 = v41;
                        goto label_785;
                    }

                    v13_1 = v5_2;
                    v41 = v6;
                    v26 = v38;
                    v24 = true;
                    if(v2 == 0) {
                        goto label_385;
                    }

                    ((zzey)this).zzjo();
                    Long v1_7 = Long.valueOf(v27_1);
                    v0_2 = zzfg.zzb(v13_1, "_epc");
                    if(v0_2 == null) {
                        Long v0_7 = v1_7;
                    }

                    long v16_1 = ((Long)v0_2).longValue();
                    if(v16_1 <= v27_1) {
                        ((zzco)this).zzgo().zzjg().zzg("Complex event with zero extra param count. eventName", v10_1);
                        v0_2 = v3;
                    }
                    else {
                        v0_2 = v3;
                        ((zzey)this).zzjq().zza(arg84, ((Long)v3), v16_1, v13_1);
                    }

                    v25 = v0_2;
                    v0_8 = v10_1;
                    v29_1 = v11_2;
                    v42_1 = v13_1;
                    v36 = v16_1;
                    goto label_389;
                }
                else {
                    v26 = v4_1;
                    v13_1 = v5_2;
                    v41 = v6;
                    v24 = true;
                }

            label_385:
                v42 = v0_2;
                v25 = v1_4;
                v0_8 = v10_1;
                v29_1 = v11_2;
            label_389:
                zzz v1_8 = ((zzey)this).zzjq().zzg(v15, v13_1.name);
                if(v1_8 == null) {
                    ((zzco)this).zzgo().zzjg().zze("Event aggregate wasn\'t created during raw event logging. appId, event", zzap.zzbv(arg84), ((zzco)this).zzgl().zzbs(v0_8));
                    v31 = v8_1;
                    v44 = v9;
                    v43 = v34;
                    v45 = v35;
                    v48 = v33;
                    v47 = v12;
                    v46 = v30;
                    v2_5 = v13_1;
                    v5_4 = v15;
                    v1_8 = new zzz(arg84, v13_1.name, 1, 1, v13_1.zzawu.longValue(), 0, null, null, null, null);
                }
                else {
                    v31 = v8_1;
                    v44 = v9;
                    v47 = v12;
                    v2_5 = v13_1;
                    v5_4 = v15;
                    v46 = v30;
                    v48 = v33;
                    v43 = v34;
                    v45 = v35;
                    v1_8 = v1_8.zziu();
                }

                ((zzey)this).zzjq().zza(v1_8);
                long v8_2 = v1_8.zzaie;
                v10 = v44;
                v1_4 = ((Map)v10).get(v0_8);
                if(v1_4 == null) {
                    Map v1_9 = ((zzey)this).zzjq().zzl(v5_4, v0_8);
                    if(v1_9 == null) {
                        v1_10 = new a();
                    }

                    ((Map)v10).put(v0_8, v1_10);
                }

                v11_3 = v1_10;
                Iterator v12_1 = ((Map)v11_3).keySet().iterator();
                while(true) {
                label_458:
                    if(!v12_1.hasNext()) {
                        goto label_775;
                    }

                    v13_2 = v12_1.next().intValue();
                    v14 = v46;
                    if(!((Set)v14).contains(Integer.valueOf(v13_2))) {
                        break;
                    }

                    ((zzco)this).zzgo().zzjl().zzg("Skipping failed audience ID", Integer.valueOf(v13_2));
                    v46 = v14;
                }

                v15_1 = v47;
                v1_4 = ((Map)v15_1).get(Integer.valueOf(v13_2));
                v6 = v45;
                v4 = ((Map)v6).get(Integer.valueOf(v13_2));
                zzgf v49 = v2_5;
                v50 = v10;
                v10 = v43;
                v2_6 = ((Map)v10).get(Integer.valueOf(v13_2));
                if(v23) {
                    v51 = v2_6;
                    v52 = v12_1;
                    v12 = v48;
                    v53 = ((Map)v12).get(Integer.valueOf(v13_2));
                    v7 = v41;
                    v2_6 = ((Map)v7).get(Integer.valueOf(v13_2));
                }
                else {
                    v51 = v2_6;
                    v52 = v12_1;
                    v7 = v41;
                    v12 = v48;
                    v2_6 = null;
                    v53 = null;
                }

                if(v1_4 == null) {
                    v1_3 = new zzgd();
                    ((Map)v15_1).put(Integer.valueOf(v13_2), v1_3);
                    v1_3.zzawo = Boolean.valueOf(v24);
                    v4_3 = new BitSet();
                    ((Map)v6).put(Integer.valueOf(v13_2), v4_3);
                    BitSet v1_11 = new BitSet();
                    v54 = v2_6;
                    ((Map)v10).put(Integer.valueOf(v13_2), v1_11);
                    if(v23) {
                        v2_7 = new a();
                        BitSet v55 = v1_11;
                        ((Map)v12).put(Integer.valueOf(v13_2), v2_7);
                        v1_10 = new a();
                        a v56 = v2_7;
                        ((Map)v7).put(Integer.valueOf(v13_2), v1_10);
                        v57 = v7;
                        v2_8 = v55;
                        v58 = v56;
                        v7 = v1_10;
                    }
                    else {
                        v57 = v7;
                        v58_1 = v53;
                        v7_1 = v54;
                        v2_8 = v1_11;
                    }
                }
                else {
                    v54 = v2_6;
                    v57 = v7;
                    v2_6 = v51;
                    v58_1 = v53;
                    v7_1 = v54;
                }

                v16_2 = ((Map)v11_3).get(Integer.valueOf(v13_2)).iterator();
                goto label_547;
            label_775:
                v50 = v10;
                v57 = v41;
                v63 = v43;
                v65 = v45;
                v14 = v46;
                v64 = v47;
                v62 = v48;
                v1_4 = v25;
                v2_1 = v36;
                v0_2 = v42_1;
                goto label_785;
            label_547:
                while(v16_2.hasNext()) {
                    v1_4 = v16_2.next();
                    BitSet v59 = ((BitSet)v2_6);
                    Object v60 = v11_3;
                    if(((zzco)this).zzgo().isLoggable(2)) {
                        v61 = v6;
                        v62 = v12;
                        ((zzco)this).zzgo().zzjl().zzd("Evaluating filter. audience, filter, event", Integer.valueOf(v13_2), ((zzfv)v1_4).zzave, ((zzco)this).zzgl().zzbs(((zzfv)v1_4).zzavf));
                        ((zzco)this).zzgo().zzjl().zzg("Filter definition", ((zzey)this).zzjo().zza(((zzfv)v1_4)));
                    }
                    else {
                        v61 = v6;
                        v62 = v12;
                    }

                    if(((zzfv)v1_4).zzave == null || ((zzfv)v1_4).zzave.intValue() > 256) {
                        v66 = v0_8;
                        v0_2 = v1_4;
                        v63 = v10;
                        v64 = v15_1;
                        v15_2 = v49;
                        v12 = ((a)v58_1);
                        v11_4 = v59;
                        v65 = v61;
                        v10_2 = v4_3;
                        v1_13 = ((zzco)this).zzgo().zzjg();
                        v2_3 = "Invalid event filter ID. appId, id";
                        v3 = zzap.zzbv(arg84);
                        v0_8 = String.valueOf(((zzfv)v0_2).zzave);
                    label_747:
                        v1_13.zze(v2_3, v3_5, v0_9);
                    }
                    else if(v23) {
                        int v12_2 = v1_4 == null || ((zzfv)v1_4).zzavb == null || !((zzfv)v1_4).zzavb.booleanValue() ? 0 : 1;
                        v17_1 = v1_4 == null || ((zzfv)v1_4).zzavc == null || !((zzfv)v1_4).zzavc.booleanValue() ? 0 : 1;
                        if((v4_3.get(((zzfv)v1_4).zzave.intValue())) && v12_2 == 0 && v17_1 == 0) {
                            ((zzco)this).zzgo().zzjl().zze("Event filter already evaluated true and it is not associated with a dynamic audience. audience ID, filter ID", Integer.valueOf(v13_2), ((zzfv)v1_4).zzave);
                            v2_8 = v59;
                            v11_3 = v60;
                            v6 = v61;
                            v12 = v62;
                            continue;
                        }

                        v11_4 = v59;
                        v63 = v10;
                        v64 = v15_1;
                        v15_2 = v49;
                        v3 = v0_8;
                        v10_2 = v4_3;
                        v66 = v0_8;
                        v0_2 = v1_4;
                        a v67 = ((a)v58_1);
                        v65 = v61;
                        v1_12 = this.zza(v1_4, ((String)v3), v29_1, v8_2);
                        v2_9 = ((zzco)this).zzgo().zzjl();
                        v3_4 = "Event filter result";
                        if(v1_12 == null) {
                            v4_4 = "null";
                        }
                        else {
                            v4_5 = v1_12;
                        }

                        v2_9.zzg(v3_4, v4_4);
                        if(v1_12 == null) {
                            ((Set)v14).add(Integer.valueOf(v13_2));
                        }
                        else {
                            v11_4.set(((zzfv)v0_2).zzave.intValue());
                            if(v1_12.booleanValue()) {
                                ((BitSet)v10_2).set(((zzfv)v0_2).zzave.intValue());
                                if(v12_2 == 0 && v17_1 == 0) {
                                    goto label_649;
                                }

                                if(v15_2.zzawu == null) {
                                    goto label_649;
                                }

                                if(v17_1 != 0) {
                                    zzj.zzb(((Map)v7_1), ((zzfv)v0_2).zzave.intValue(), v15_2.zzawu.longValue());
                                    goto label_649;
                                }

                                v12 = v67;
                                zzj.zza(((Map)v12), ((zzfv)v0_2).zzave.intValue(), v15_2.zzawu.longValue());
                                goto label_748;
                            }
                        }

                    label_649:
                        v4 = v10_2;
                        v2_8 = v11_4;
                        v49 = v15_2;
                        v11_3 = v60;
                        v12 = v62;
                        v10 = v63;
                        v15_1 = v64;
                        v6 = v65;
                        v0_2 = v66;
                        v58 = v67;
                        continue;
                    }
                    else {
                        v66 = v0_8;
                        v0_2 = v1_4;
                        v63 = v10;
                        v64 = v15_1;
                        v15_2 = v49;
                        v12 = ((a)v58_1);
                        v11_4 = v59;
                        v65 = v61;
                        v10_2 = v4_3;
                        if(((BitSet)v10_2).get(((zzfv)v0_2).zzave.intValue())) {
                            v1_13 = ((zzco)this).zzgo().zzjl();
                            v2_3 = "Event filter already evaluated true. audience ID, filter ID";
                            v3_5 = Integer.valueOf(v13_2);
                            v0_9 = ((zzfv)v0_2).zzave;
                            goto label_747;
                        }

                        v1_12 = this.zza(v0_2, v66, v29_1, v8_2);
                        v2_9 = ((zzco)this).zzgo().zzjl();
                        v3_4 = "Event filter result";
                        if(v1_12 == null) {
                            v4_4 = "null";
                        }
                        else {
                            v4_5 = v1_12;
                        }

                        v2_9.zzg(v3_4, v4_4);
                        if(v1_12 == null) {
                            ((Set)v14).add(Integer.valueOf(v13_2));
                            goto label_748;
                        }

                        v11_4.set(((zzfv)v0_2).zzave.intValue());
                        if(!v1_12.booleanValue()) {
                            goto label_748;
                        }

                        ((BitSet)v10_2).set(((zzfv)v0_2).zzave.intValue());
                    }

                label_748:
                    v4 = v10_2;
                    v2_8 = v11_4;
                    v58 = v12;
                    v49 = v15_2;
                    v11_3 = v60;
                    v12 = v62;
                    v10 = v63;
                    v15_1 = v64;
                    v6 = v65;
                    v0_2 = v66;
                }

                v45 = v6;
                v43 = v10;
                v48 = v12;
                v46 = v14;
                v2_5 = v49;
                v10 = v50;
                v12_1 = v52;
                v41 = v57;
                v47 = v15_1;
                goto label_458;
            label_785:
                v4_1 = v26 + 1;
                v13 = arg85;
                v30 = v14;
                v8_1 = v31;
                v9 = v50;
                v6 = v57;
                v33 = v62;
                v34 = v63;
                v12 = v64;
                v35 = v65;
                v15 = arg84;
            }
        }

        v57 = v6;
        v64 = v12;
        v14 = v30;
        v62 = v33;
        v63 = v34;
        v65 = v35;
        zzgl[] v1_14 = arg86;
        if(v1_14 != null) {
            v0_1 = new a();
            v2 = v1_14.length;
            v3_1 = 0;
            while(true) {
                if(v3_1 < v2) {
                    v4_6 = v1_14[v3_1];
                    v5 = ((Map)v0_1).get(v4_6.name);
                    if(v5 == null) {
                        v5_5 = ((zzey)this).zzjq().zzm(arg84, v4_6.name);
                        if(v5_5 == null) {
                            v5_6 = new a();
                        }

                        ((Map)v0_1).put(v4_6.name, v5_5);
                    }

                    v6_2 = v5_5.keySet().iterator();
                    while(true) {
                    label_830:
                        if(!v6_2.hasNext()) {
                            goto label_1102;
                        }

                        v8_1 = v6_2.next().intValue();
                        if(!((Set)v14).contains(Integer.valueOf(v8_1))) {
                            break;
                        }

                        ((zzco)this).zzgo().zzjl().zzg("Skipping failed audience ID", Integer.valueOf(v8_1));
                    }

                    v10 = v64;
                    Object v9_1 = ((Map)v10).get(Integer.valueOf(v8_1));
                    v12 = v65;
                    v11_3 = ((Map)v12).get(Integer.valueOf(v8_1));
                    v15_1 = v63;
                    v13_3 = ((Map)v15_1).get(Integer.valueOf(v8_1));
                    if(v23) {
                        v68 = v0_1;
                        v1_10 = v62;
                        v69 = ((Map)v1_10).get(Integer.valueOf(v8_1));
                        v70 = v2;
                        v2_7 = v57;
                        v0_2 = ((Map)v2_7).get(Integer.valueOf(v8_1));
                    }
                    else {
                        v68 = v0_1;
                        v70 = v2;
                        v2_7 = v57;
                        v1_10 = v62;
                        v0_2 = null;
                        v69 = null;
                    }

                    if(v9_1 == null) {
                        zzgd v9_2 = new zzgd();
                        ((Map)v10).put(Integer.valueOf(v8_1), v9_2);
                        v9_2.zzawo = Boolean.valueOf(true);
                        v11_4 = new BitSet();
                        ((Map)v12).put(Integer.valueOf(v8_1), v11_4);
                        v13_4 = new BitSet();
                        ((Map)v15_1).put(Integer.valueOf(v8_1), v13_4);
                        if(v23) {
                            v0_1 = new a();
                            ((Map)v1_10).put(Integer.valueOf(v8_1), v0_1);
                            v9 = new a();
                            ((Map)v2_7).put(Integer.valueOf(v8_1), v9);
                            v72 = v2_7;
                            v0_1 = v0_1;
                        }
                        else {
                            goto label_897;
                        }
                    }
                    else {
                    label_897:
                        v9_1 = v0_2;
                        v72 = v2_7;
                        v0_2 = v69;
                    }

                    v2_10 = v5_5.get(Integer.valueOf(v8_1)).iterator();
                    break;
                label_1102:
                    ++v3_1;
                    v1_14 = arg86;
                    continue;
                }

                goto label_1113;
            }

            while(true) {
                if(!v2_10.hasNext()) {
                    goto label_1091;
                }

                Iterator v73 = v2_10;
                v2_6 = v2_10.next();
                v74 = v5_5;
                v75 = v6_2;
                if(((zzco)this).zzgo().isLoggable(2)) {
                    v76 = v1_10;
                    v77 = v15_1;
                    v78 = v10;
                    ((zzco)this).zzgo().zzjl().zzd("Evaluating filter. audience, filter, property", Integer.valueOf(v8_1), ((zzfy)v2_6).zzave, ((zzco)this).zzgl().zzbu(((zzfy)v2_6).zzavu));
                    ((zzco)this).zzgo().zzjl().zzg("Filter definition", ((zzey)this).zzjo().zza(((zzfy)v2_6)));
                }
                else {
                    v76 = v1_10;
                    v78 = v10;
                    v77 = v15_1;
                }

                if(((zzfy)v2_6).zzave != null) {
                    if(((zzfy)v2_6).zzave.intValue() > 256) {
                    }
                    else {
                        if(v23) {
                            v1_2 = v2_6 == null || ((zzfy)v2_6).zzavb == null || !((zzfy)v2_6).zzavb.booleanValue() ? 0 : 1;
                            v6_3 = v2_6 == null || ((zzfy)v2_6).zzavc == null || !((zzfy)v2_6).zzavc.booleanValue() ? 0 : 1;
                            if((((BitSet)v11_3).get(((zzfy)v2_6).zzave.intValue())) && v1_2 == 0 && v6_3 == 0) {
                                ((zzco)this).zzgo().zzjl().zze("Property filter already evaluated true and it is not associated with a dynamic audience. audience ID, filter ID", Integer.valueOf(v8_1), ((zzfy)v2_6).zzave);
                                v2_10 = v73;
                                v5 = v74;
                                v6_2 = v75;
                                v1_10 = v76;
                                v15_1 = v77;
                                v10 = v78;
                                continue;
                            }

                            Boolean v15_3 = this.zza(((zzfy)v2_6), v4_6);
                            v5_7 = ((zzco)this).zzgo().zzjl();
                            v79 = v72;
                            v10_1 = "Property filter result";
                            if(v15_3 == null) {
                                v80 = v12;
                                v12_3 = "null";
                            }
                            else {
                                v80 = v12;
                                Boolean v12_4 = v15_3;
                            }

                            v5_7.zzg(v10_1, v12_3);
                            if(v15_3 == null) {
                                goto label_1057;
                            }

                            v13_4.set(((zzfy)v2_6).zzave.intValue());
                            ((BitSet)v11_3).set(((zzfy)v2_6).zzave.intValue(), v15_3.booleanValue());
                            if(!v15_3.booleanValue()) {
                                goto label_1037;
                            }

                            if(v1_2 == 0 && v6_3 == 0) {
                                goto label_1037;
                            }

                            if(v4_6.zzayl == null) {
                                goto label_1037;
                            }

                            if(v6_3 != 0) {
                                zzj.zzb(((Map)v9), ((zzfy)v2_6).zzave.intValue(), v4_6.zzayl.longValue());
                                goto label_1037;
                            }

                            zzj.zza(((Map)v0_1), ((zzfy)v2_6).zzave.intValue(), v4_6.zzayl.longValue());
                        }
                        else {
                            v80 = v12;
                            v79 = v72;
                            zzj v7_2 = this;
                            if(((BitSet)v11_3).get(((zzfy)v2_6).zzave.intValue())) {
                                ((zzco)this).zzgo().zzjl().zze("Property filter already evaluated true. audience ID, filter ID", Integer.valueOf(v8_1), ((zzfy)v2_6).zzave);
                                goto label_1037;
                            }

                            v1_12 = v7_2.zza(((zzfy)v2_6), v4_6);
                            v5_7 = ((zzco)this).zzgo().zzjl();
                            String v6_4 = "Property filter result";
                            if(v1_12 == null) {
                                v10_1 = "null";
                            }
                            else {
                                Boolean v10_3 = v1_12;
                            }

                            v5_7.zzg(v6_4, v10_1);
                            if(v1_12 == null) {
                            label_1057:
                                ((Set)v14).add(Integer.valueOf(v8_1));
                                goto label_1037;
                            }

                            v13_4.set(((zzfy)v2_6).zzave.intValue());
                            if(!v1_12.booleanValue()) {
                                goto label_1037;
                            }

                            ((BitSet)v11_3).set(((zzfy)v2_6).zzave.intValue());
                        }

                    label_1037:
                        v2_10 = v73;
                        v5 = v74;
                        v6_2 = v75;
                        v1_10 = v76;
                        v15_1 = v77;
                        v10 = v78;
                        v72 = v79;
                        v12 = v80;
                        continue;
                    }
                }

                break;
            }

            ((zzco)this).zzgo().zzjg().zze("Invalid property filter ID. appId, id", zzap.zzbv(arg84), String.valueOf(((zzfy)v2_6).zzave));
            ((Set)v14).add(Integer.valueOf(v8_1));
            v0_1 = v68;
            v2 = v70;
            v5 = v74;
            v6_2 = v75;
            v62 = v76;
            v63 = v77;
            v64 = v78;
            v57 = v72;
            v65 = v12;
            goto label_830;
        label_1091:
            v62 = v1_10;
            v64 = v10;
            v65 = v12;
            v63 = v15_1;
            v0_1 = v68;
            v2 = v70;
            v57 = v72;
            goto label_830;
        }

    label_1113:
        v79 = v57;
        v76 = v62;
        v77 = v63;
        v78 = v64;
        v80 = v65;
        zzgd[] v1_15 = new zzgd[((Map)v80).size()];
        v2_10 = ((Map)v80).keySet().iterator();
        v3_1 = 0;
        do {
        label_1124:
            if(!v2_10.hasNext()) {
                goto label_1287;
            }

            v0_10 = v2_10.next().intValue();
        }
        while(((Set)v14).contains(Integer.valueOf(v0_10)));

        v5_6 = v78;
        v4 = ((Map)v5_6).get(Integer.valueOf(v0_10));
        if(v4 == null) {
            zzgd v4_7 = new zzgd();
        }

        v6_3 = v3_1 + 1;
        v1_15[v3_1] = ((zzgd)v4);
        ((zzgd)v4).zzauy = Integer.valueOf(v0_10);
        ((zzgd)v4).zzawm = new zzgj();
        v9 = v80;
        ((zzgd)v4).zzawm.zzayf = zzfg.zza(((Map)v9).get(Integer.valueOf(v0_10)));
        v10 = v77;
        ((zzgd)v4).zzawm.zzaye = zzfg.zza(((Map)v10).get(Integer.valueOf(v0_10)));
        if(v23) {
            v11_5 = v76;
            ((zzgd)v4).zzawm.zzayg = zzj.zzd(((Map)v11_5).get(Integer.valueOf(v0_10)));
            v3_6 = ((zzgd)v4).zzawm;
            v12 = v79;
            v8_3 = ((Map)v12).get(Integer.valueOf(v0_10));
            if(v8_3 == null) {
                v81 = v2_10;
                v82 = v5_6;
                v15_4 = new zzgk[0];
            }
            else {
                v15_4 = new zzgk[((Map)v8_3).size()];
                v16_2 = ((Map)v8_3).keySet().iterator();
                v17_1 = 0;
                goto label_1180;
            }

            goto label_1216;
        }
        else {
            v81 = v2_10;
            v82 = v5_6;
            v11_5 = v76;
            v12 = v79;
            goto label_1222;
        label_1287:
            return Arrays.copyOf(((Object[])v1_15), v3_1);
        label_1180:
            while(v16_2.hasNext()) {
                v13_3 = v16_2.next();
                v81 = v2_10;
                zzgk v2_11 = new zzgk();
                v2_11.zzawq = ((Integer)v13_3);
                v13_3 = ((Map)v8_3).get(v13_3);
                if(v13_3 != null) {
                    Collections.sort(((List)v13_3));
                    v82 = v5_6;
                    long[] v5_8 = new long[((List)v13_3).size()];
                    Iterator v13_5 = ((List)v13_3).iterator();
                    int v18;
                    for(v18 = 0; v13_5.hasNext(); ++v18) {
                        v5_8[v18] = v13_5.next().longValue();
                    }

                    v2_11.zzayj = v5_8;
                }
                else {
                    v82 = v5_6;
                }

                v15_4[v17_1] = v2_11;
                ++v17_1;
                v2_10 = v81;
                v5_6 = v82;
            }

            v81 = v2_10;
            v82 = v5_6;
        label_1216:
            v3_6.zzayh = v15_4;
        }

    label_1222:
        zzq v2_12 = ((zzey)this).zzjq();
        v3_6 = ((zzgd)v4).zzawm;
        ((zzez)v2_12).zzcl();
        ((zzco)v2_12).zzaf();
        Preconditions.checkNotEmpty(arg84);
        Preconditions.checkNotNull(v3_6);
        try {
            v4_8 = new byte[((zzzg)v3_6).zzvu()];
            v5_3 = v4_8.length;
        }
        catch(IOException v0_11) {
            goto label_1270;
        }

        try {
            zzyy v5_9 = zzyy.zzk(v4_8, 0, v5_3);
            ((zzzg)v3_6).zza(v5_9);
            v5_9.zzyt();
            goto label_1235;
        }
        catch(IOException v0_11) {
        }

    label_1270:
        v2_9 = ((zzco)v2_12).zzgo().zzjd();
        v3_4 = "Configuration loss. Failed to serialize filter results. appId";
        goto label_1275;
    label_1235:
        ContentValues v3_7 = new ContentValues();
        v3_7.put("app_id", arg84);
        v3_7.put("audience_id", Integer.valueOf(v0_10));
        v3_7.put("current_results", v4_8);
        try {
            v0_4 = v2_12.getWritableDatabase();
            v4_4 = "audience_filter_values";
            v5_3 = 5;
            v15 = null;
        }
        catch(SQLiteException v0_3) {
            goto label_1262;
        }

        try {
            if(v0_4.insertWithOnConflict(v4_4, v15, v3_7, v5_3) != -1) {
                goto label_1277;
            }

            ((zzco)v2_12).zzgo().zzjd().zzg("Failed to insert filter results (got -1). appId", zzap.zzbv(arg84));
            goto label_1277;
        }
        catch(SQLiteException v0_3) {
        }

    label_1262:
        v2_9 = ((zzco)v2_12).zzgo().zzjd();
        v3_4 = "Error storing filter results. appId";
    label_1275:
        v2_9.zze(v3_4, zzap.zzbv(arg84), v0_3);
    label_1277:
        v3_1 = v6_3;
        v80 = v9;
        v77 = v10;
        v76 = v11_5;
        v79 = v12;
        v2_10 = v81;
        v78 = v82;
        goto label_1124;
    }

    private final Boolean zza(double arg2, zzfx arg4) {
        try {
            return zzj.zza(new BigDecimal(arg2), arg4, Math.ulp(arg2));
        }
        catch(NumberFormatException ) {
            return null;
        }
    }

    @VisibleForTesting private static Boolean zza(BigDecimal arg7, zzfx arg8, double arg9) {
        BigDecimal v8;
        BigDecimal v4;
        BigDecimal v3;
        Preconditions.checkNotNull(arg8);
        Boolean v1 = null;
        if(arg8.zzavo != null) {
            if(arg8.zzavo.intValue() == 0) {
            }
            else {
                int v2 = 4;
                if(arg8.zzavo.intValue() == v2) {
                    if(arg8.zzavr != null && arg8.zzavs != null) {
                        goto label_20;
                    }

                    return v1;
                }
                else {
                    if(arg8.zzavq != null) {
                        goto label_20;
                    }

                    return v1;
                }

            label_20:
                int v0 = arg8.zzavo.intValue();
                if(arg8.zzavo.intValue() == v2) {
                    if(zzfg.zzcp(arg8.zzavr)) {
                        if(!zzfg.zzcp(arg8.zzavs)) {
                        }
                        else {
                            try {
                                v3 = new BigDecimal(arg8.zzavr);
                                v4 = new BigDecimal(arg8.zzavs);
                                v8 = v3;
                                v3 = ((BigDecimal)v1);
                                goto label_51;
                            }
                            catch(NumberFormatException ) {
                                return v1;
                            }
                        }
                    }

                    return v1;
                }
                else {
                    if(!zzfg.zzcp(arg8.zzavq)) {
                        return v1;
                    }

                    try {
                        v3 = new BigDecimal(arg8.zzavq);
                        v8 = ((BigDecimal)v1);
                        v4 = v8;
                    }
                    catch(NumberFormatException ) {
                        return v1;
                    }
                }

            label_51:
                if(v0 != v2) {
                    if(v3 != null) {
                        goto label_55;
                    }

                    return v1;
                }
                else if(v8 == null) {
                    return v1;
                }

            label_55:
                v2 = -1;
                boolean v5 = false;
                switch(v0) {
                    case 1: {
                        goto label_99;
                    }
                    case 2: {
                        goto label_94;
                    }
                    case 3: {
                        goto label_67;
                    }
                    case 4: {
                        goto label_60;
                    }
                }

                return v1;
            label_99:
                if(arg7.compareTo(v3) == v2) {
                    v5 = true;
                }

                return Boolean.valueOf(v5);
            label_67:
                if(arg9 != 0) {
                    int v1_1 = 2;
                    if(arg7.compareTo(v3.subtract(new BigDecimal(arg9).multiply(new BigDecimal(v1_1)))) == 1 && arg7.compareTo(v3.add(new BigDecimal(arg9).multiply(new BigDecimal(v1_1)))) == v2) {
                        v5 = true;
                    }

                    return Boolean.valueOf(v5);
                }

                if(arg7.compareTo(v3) == 0) {
                    v5 = true;
                }

                return Boolean.valueOf(v5);
            label_60:
                if(arg7.compareTo(v8) != v2 && arg7.compareTo(v4) != 1) {
                    v5 = true;
                }

                return Boolean.valueOf(v5);
            label_94:
                if(arg7.compareTo(v3) == 1) {
                    v5 = true;
                }

                return Boolean.valueOf(v5);
            }
        }

        return v1;
    }

    private final Boolean zza(long arg2, zzfx arg4) {
        try {
            return zzj.zza(new BigDecimal(arg2), arg4, 0);
        }
        catch(NumberFormatException ) {
            return null;
        }
    }

    private final Boolean zza(zzfv arg9, String arg10, zzgg[] arg11, long arg12) {
        Boolean v3_2;
        Long v4_2;
        String v5;
        Boolean v2 = null;
        if(arg9.zzavi != null) {
            Boolean v12 = this.zza(arg12, arg9.zzavi);
            if(v12 == null) {
                return v2;
            }
            else if(!v12.booleanValue()) {
                return Boolean.valueOf(false);
            }
        }

        HashSet v12_1 = new HashSet();
        zzfw[] v13 = arg9.zzavg;
        int v0 = v13.length;
        int v3;
        for(v3 = 0; v3 < v0; ++v3) {
            zzfw v4 = v13[v3];
            if(TextUtils.isEmpty(v4.zzavn)) {
                ((zzco)this).zzgo().zzjg().zzg("null or empty param name in filter. event", ((zzco)this).zzgl().zzbs(arg10));
                return v2;
            }

            ((Set)v12_1).add(v4.zzavn);
        }

        a v13_1 = new a();
        v0 = arg11.length;
        for(v3 = 0; v3 < v0; ++v3) {
            zzgg v4_1 = arg11[v3];
            if(((Set)v12_1).contains(v4_1.name)) {
                if(v4_1.zzawx != null) {
                    v5 = v4_1.name;
                    v4_2 = v4_1.zzawx;
                }
                else if(v4_1.zzauh != null) {
                    v5 = v4_1.name;
                    Double v4_3 = v4_1.zzauh;
                }
                else if(v4_1.zzamp != null) {
                    v5 = v4_1.name;
                    String v4_4 = v4_1.zzamp;
                }
                else {
                    goto label_58;
                }

                ((Map)v13_1).put(v5, v4_2);
                goto label_68;
            label_58:
                ((zzco)this).zzgo().zzjg().zze("Unknown value for param. event, param", ((zzco)this).zzgl().zzbs(arg10), ((zzco)this).zzgl().zzbt(v4_1.name));
                return v2;
            }

        label_68:
        }

        zzfw[] v9 = arg9.zzavg;
        int v11 = v9.length;
        int v12_2;
        for(v12_2 = 0; true; ++v12_2) {
            if(v12_2 >= v11) {
                goto label_199;
            }

            zzfw v3_1 = v9[v12_2];
            boolean v4_5 = Boolean.TRUE.equals(v3_1.zzavm);
            v5 = v3_1.zzavn;
            if(TextUtils.isEmpty(((CharSequence)v5))) {
                ((zzco)this).zzgo().zzjg().zzg("Event has empty param name. event", ((zzco)this).zzgl().zzbs(arg10));
                return v2;
            }

            Object v6 = ((Map)v13_1).get(v5);
            if((v6 instanceof Long)) {
                if(v3_1.zzavl == null) {
                    ((zzco)this).zzgo().zzjg().zze("No number filter for long param. event, param", ((zzco)this).zzgl().zzbs(arg10), ((zzco)this).zzgl().zzbt(v5));
                    return v2;
                }
                else {
                    v3_2 = this.zza(((Long)v6).longValue(), v3_1.zzavl);
                    if(v3_2 == null) {
                        return v2;
                    }
                    else if((1 ^ v3_2.booleanValue() ^ (((int)v4_5))) != 0) {
                        return Boolean.valueOf(false);
                    }
                }
            }
            else if((v6 instanceof Double)) {
                if(v3_1.zzavl == null) {
                    ((zzco)this).zzgo().zzjg().zze("No number filter for double param. event, param", ((zzco)this).zzgl().zzbs(arg10), ((zzco)this).zzgl().zzbt(v5));
                    return v2;
                }
                else {
                    v3_2 = this.zza(((Double)v6).doubleValue(), v3_1.zzavl);
                    if(v3_2 == null) {
                        return v2;
                    }
                    else if((1 ^ v3_2.booleanValue() ^ (((int)v4_5))) != 0) {
                        return Boolean.valueOf(false);
                    }
                }
            }
            else if((v6 instanceof String)) {
                if(v3_1.zzavk != null) {
                    v3_2 = this.zza(((String)v6), v3_1.zzavk);
                }
                else if(v3_1.zzavl == null) {
                    goto label_170;
                }
                else if(zzfg.zzcp(((String)v6))) {
                    v3_2 = this.zza(((String)v6), v3_1.zzavl);
                }
                else {
                    break;
                }

                if(v3_2 == null) {
                    return v2;
                }

                if((1 ^ v3_2.booleanValue() ^ (((int)v4_5))) == 0) {
                    goto label_159;
                }

                return Boolean.valueOf(false);
            }
            else {
                goto label_179;
            }

        label_159:
        }

        ((zzco)this).zzgo().zzjg().zze("Invalid param value for number filter. event, param", ((zzco)this).zzgl().zzbs(arg10), ((zzco)this).zzgl().zzbt(v5));
        return v2;
    label_170:
        ((zzco)this).zzgo().zzjg().zze("No filter for String param. event, param", ((zzco)this).zzgl().zzbs(arg10), ((zzco)this).zzgl().zzbt(v5));
        return v2;
    label_179:
        if(v6 == null) {
            ((zzco)this).zzgo().zzjl().zze("Missing param for filter. event, param", ((zzco)this).zzgl().zzbs(arg10), ((zzco)this).zzgl().zzbt(v5));
            return Boolean.valueOf(false);
        }

        ((zzco)this).zzgo().zzjg().zze("Unknown param type. event, param", ((zzco)this).zzgl().zzbs(arg10), ((zzco)this).zzgl().zzbt(v5));
        return v2;
    label_199:
        return Boolean.valueOf(true);
    }

    @VisibleForTesting private final Boolean zza(String arg11, zzfz arg12) {
        ArrayList v8_1;
        List v8;
        Preconditions.checkNotNull(arg12);
        Boolean v0 = null;
        if(arg11 == null) {
            return v0;
        }

        if(arg12.zzavw != null) {
            if(arg12.zzavw.intValue() == 0) {
            }
            else {
                int v2 = 6;
                if(arg12.zzavw.intValue() == v2) {
                    if(arg12.zzavz != null && arg12.zzavz.length != 0) {
                        goto label_23;
                    }

                    return v0;
                }
                else {
                    if(arg12.zzavx != null) {
                        goto label_23;
                    }

                    return v0;
                }

            label_23:
                int v5 = arg12.zzavw.intValue();
                int v3 = 0;
                boolean v6 = arg12.zzavy == null || !arg12.zzavy.booleanValue() ? false : true;
                String v1 = (v6) || v5 == 1 || v5 == v2 ? arg12.zzavx : arg12.zzavx.toUpperCase(Locale.ENGLISH);
                String v7 = v1;
                if(arg12.zzavz == null) {
                    v8 = ((List)v0);
                }
                else {
                    String[] v12 = arg12.zzavz;
                    if(v6) {
                        v8 = Arrays.asList(((Object[])v12));
                    }
                    else {
                        ArrayList v1_1 = new ArrayList();
                        v2 = v12.length;
                        while(v3 < v2) {
                            ((List)v1_1).add(v12[v3].toUpperCase(Locale.ENGLISH));
                            ++v3;
                        }

                        v8_1 = v1_1;
                    }
                }

                String v9 = v5 == 1 ? v7 : ((String)v0);
                return this.zza(arg11, v5, v6, v7, ((List)v8_1), v9);
            }
        }

        return v0;
    }

    private final Boolean zza(String arg5, zzfx arg6) {
        Boolean v1 = null;
        if(!zzfg.zzcp(arg5)) {
            return v1;
        }

        try {
            return zzj.zza(new BigDecimal(arg5), arg6, 0);
        }
        catch(NumberFormatException ) {
            return v1;
        }
    }

    private final Boolean zza(zzfy arg5, zzgl arg6) {
        Boolean v5_2;
        String v1;
        zzar v5_1;
        zzfw v5 = arg5.zzavv;
        Boolean v0 = null;
        if(v5 == null) {
            v5_1 = ((zzco)this).zzgo().zzjg();
            v1 = "Missing property filter. property";
        }
        else {
            boolean v1_1 = Boolean.TRUE.equals(v5.zzavm);
            if(arg6.zzawx != null) {
                if(v5.zzavl == null) {
                    v5_1 = ((zzco)this).zzgo().zzjg();
                    v1 = "No number filter for long property. property";
                    goto label_6;
                }
                else {
                    v5_2 = this.zza(arg6.zzawx.longValue(), v5.zzavl);
                }
            }
            else if(arg6.zzauh != null) {
                if(v5.zzavl == null) {
                    v5_1 = ((zzco)this).zzgo().zzjg();
                    v1 = "No number filter for double property. property";
                    goto label_6;
                }
                else {
                    v5_2 = this.zza(arg6.zzauh.doubleValue(), v5.zzavl);
                }
            }
            else if(arg6.zzamp == null) {
                goto label_75;
            }
            else if(v5.zzavk == null) {
                if(v5.zzavl == null) {
                    ((zzco)this).zzgo().zzjg().zzg("No string or number filter defined. property", ((zzco)this).zzgl().zzbu(arg6.name));
                }
                else if(zzfg.zzcp(arg6.zzamp)) {
                    v5_2 = this.zza(arg6.zzamp, v5.zzavl);
                    goto label_26;
                }
                else {
                    ((zzco)this).zzgo().zzjg().zze("Invalid user property value for Numeric number filter. property, value", ((zzco)this).zzgl().zzbu(arg6.name), arg6.zzamp);
                }

                return v0;
            }
            else {
                v5_2 = this.zza(arg6.zzamp, v5.zzavk);
            }

        label_26:
            return zzj.zza(v5_2, v1_1);
        label_75:
            v5_1 = ((zzco)this).zzgo().zzjg();
            v1 = "User property has no value, property";
        }

    label_6:
        v5_1.zzg(v1, ((zzco)this).zzgl().zzbu(arg6.name));
        return v0;
    }

    @VisibleForTesting private static Boolean zza(Boolean arg0, boolean arg1) {
        if(arg0 == null) {
            return null;
        }

        return Boolean.valueOf(arg0.booleanValue() ^ (((int)arg1)));
    }

    private final Boolean zza(String arg3, int arg4, boolean arg5, String arg6, List arg7, String arg8) {
        Boolean v0 = null;
        if(arg3 == null) {
            return v0;
        }

        if(arg4 == 6) {
            if(arg7 != null && arg7.size() != 0) {
                goto label_11;
            }

            return v0;
        }
        else {
            if(arg6 != null) {
                goto label_11;
            }

            return v0;
        }

    label_11:
        if(!arg5) {
            if(arg4 == 1) {
            }
            else {
                arg3 = arg3.toUpperCase(Locale.ENGLISH);
            }
        }

        switch(arg4) {
            case 1: {
                goto label_30;
            }
            case 2: {
                goto label_28;
            }
            case 3: {
                goto label_26;
            }
            case 4: {
                goto label_24;
            }
            case 5: {
                goto label_22;
            }
            case 6: {
                goto label_19;
            }
        }

        return v0;
    label_19:
        boolean v3 = arg7.contains(arg3);
        goto label_20;
    label_22:
        v3 = arg3.equals(arg6);
        goto label_20;
    label_24:
        v3 = arg3.contains(((CharSequence)arg6));
        goto label_20;
    label_26:
        v3 = arg3.endsWith(arg6);
        goto label_20;
    label_28:
        v3 = arg3.startsWith(arg6);
    label_20:
        return Boolean.valueOf(v3);
    label_30:
        arg4 = arg5 ? 0 : 66;
        try {
            return Boolean.valueOf(Pattern.compile(arg8, arg4).matcher(((CharSequence)arg3)).matches());
        }
        catch(PatternSyntaxException ) {
            ((zzco)this).zzgo().zzjg().zzg("Invalid regular expression in REGEXP audience filter. expression", arg8);
            return v0;
        }
    }

    private static void zza(Map arg3, int arg4, long arg5) {
        Object v0 = arg3.get(Integer.valueOf(arg4));
        arg5 /= 1000;
        if(v0 == null || arg5 > ((Long)v0).longValue()) {
            arg3.put(Integer.valueOf(arg4), Long.valueOf(arg5));
        }
    }

    private static void zzb(Map arg1, int arg2, long arg3) {
        Object v0 = arg1.get(Integer.valueOf(arg2));
        if(v0 == null) {
            ArrayList v0_1 = new ArrayList();
            arg1.put(Integer.valueOf(arg2), v0_1);
        }

        ((List)v0).add(Long.valueOf(arg3 / 1000));
    }

    private static zzge[] zzd(Map arg5) {
        if(arg5 == null) {
            return null;
        }

        int v0 = 0;
        zzge[] v1 = new zzge[arg5.size()];
        Iterator v2 = arg5.keySet().iterator();
        while(v2.hasNext()) {
            Object v3 = v2.next();
            zzge v4 = new zzge();
            v4.zzawq = ((Integer)v3);
            v4.zzawr = arg5.get(v3);
            v1[v0] = v4;
            ++v0;
        }

        return v1;
    }

    protected final boolean zzgt() {
        return 0;
    }
}

