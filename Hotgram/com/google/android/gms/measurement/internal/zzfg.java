package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzfv;
import com.google.android.gms.internal.measurement.zzfw;
import com.google.android.gms.internal.measurement.zzfx;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zzfz;
import com.google.android.gms.internal.measurement.zzgd;
import com.google.android.gms.internal.measurement.zzgf;
import com.google.android.gms.internal.measurement.zzgg;
import com.google.android.gms.internal.measurement.zzgh;
import com.google.android.gms.internal.measurement.zzgi;
import com.google.android.gms.internal.measurement.zzgj;
import com.google.android.gms.internal.measurement.zzgl;
import com.google.android.gms.internal.measurement.zzyy;
import com.google.android.gms.internal.measurement.zzzg;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.BitSet;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class zzfg extends zzez {
    zzfg(zzfa arg1) {
        super(arg1);
    }

    public final Context getContext() {
        return super.getContext();
    }

    static zzgg[] zza(zzgg[] arg5, String arg6, Object arg7) {
        int v0 = arg5.length;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            zzgg v3 = arg5[v2];
            if(arg6.equals(v3.name)) {
                v3.zzawx = null;
                v3.zzamp = null;
                v3.zzauh = null;
                if((arg7 instanceof Long)) {
                    v3.zzawx = ((Long)arg7);
                }
                else if((arg7 instanceof String)) {
                    v3.zzamp = ((String)arg7);
                }
                else if((arg7 instanceof Double)) {
                    v3.zzauh = ((Double)arg7);
                }

                return arg5;
            }
        }

        zzgg[] v0_1 = new zzgg[arg5.length + 1];
        System.arraycopy(arg5, 0, v0_1, 0, arg5.length);
        zzgg v1 = new zzgg();
        v1.name = arg6;
        if((arg7 instanceof Long)) {
            v1.zzawx = ((Long)arg7);
        }
        else if((arg7 instanceof String)) {
            v1.zzamp = ((String)arg7);
        }
        else if((arg7 instanceof Double)) {
            v1.zzauh = ((Double)arg7);
        }

        v0_1[arg5.length] = v1;
        return v0_1;
    }

    final byte[] zza(zzgh arg4) {
        try {
            byte[] v0 = new byte[((zzzg)arg4).zzvu()];
            zzyy v1 = zzyy.zzk(v0, 0, v0.length);
            ((zzzg)arg4).zza(v1);
            v1.zzyt();
            return v0;
        }
        catch(IOException v4) {
            ((zzco)this).zzgo().zzjd().zzg("Data loss. Failed to serialize batch", v4);
            return null;
        }
    }

    static zzgg zza(zzgf arg4, String arg5) {
        zzgg[] v4 = arg4.zzawt;
        int v0 = v4.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            zzgg v2 = v4[v1];
            if(v2.name.equals(arg5)) {
                return v2;
            }
        }

        return null;
    }

    private static void zza(StringBuilder arg2, int arg3) {
        int v0;
        for(v0 = 0; v0 < arg3; ++v0) {
            arg2.append("  ");
        }
    }

    private final void zza(StringBuilder arg7, int arg8, zzfw arg9) {
        if(arg9 == null) {
            return;
        }

        zzfg.zza(arg7, arg8);
        arg7.append("filter {\n");
        zzfg.zza(arg7, arg8, "complement", arg9.zzavm);
        zzfg.zza(arg7, arg8, "param_name", ((zzco)this).zzgl().zzbt(arg9.zzavn));
        int v0 = arg8 + 1;
        String v1 = "string_filter";
        zzfz v2 = arg9.zzavk;
        if(v2 != null) {
            zzfg.zza(arg7, v0);
            arg7.append(v1);
            arg7.append(" {\n");
            if(v2.zzavw != null) {
                v1 = "UNKNOWN_MATCH_TYPE";
                switch(v2.zzavw.intValue()) {
                    case 1: {
                        v1 = "REGEXP";
                        break;
                    }
                    case 2: {
                        v1 = "BEGINS_WITH";
                        break;
                    }
                    case 3: {
                        v1 = "ENDS_WITH";
                        break;
                    }
                    case 4: {
                        v1 = "PARTIAL";
                        break;
                    }
                    case 5: {
                        v1 = "EXACT";
                        break;
                    }
                    case 6: {
                        v1 = "IN_LIST";
                        break;
                    }
                    default: {
                        break;
                    }
                }

                zzfg.zza(arg7, v0, "match_type", v1);
            }

            zzfg.zza(arg7, v0, "expression", v2.zzavx);
            zzfg.zza(arg7, v0, "case_sensitive", v2.zzavy);
            if(v2.zzavz.length > 0) {
                zzfg.zza(arg7, v0 + 1);
                arg7.append("expression_list {\n");
                String[] v1_1 = v2.zzavz;
                int v2_1 = v1_1.length;
                int v3;
                for(v3 = 0; v3 < v2_1; ++v3) {
                    String v4 = v1_1[v3];
                    zzfg.zza(arg7, v0 + 2);
                    arg7.append(v4);
                    arg7.append("\n");
                }

                arg7.append("}\n");
            }

            zzfg.zza(arg7, v0);
            arg7.append("}\n");
        }

        this.zza(arg7, v0, "number_filter", arg9.zzavl);
        zzfg.zza(arg7, arg8);
        arg7.append("}\n");
    }

    private static void zza(StringBuilder arg0, int arg1, String arg2, Object arg3) {
        if(arg3 == null) {
            return;
        }

        zzfg.zza(arg0, arg1 + 1);
        arg0.append(arg2);
        arg0.append(": ");
        arg0.append(arg3);
        arg0.append('\n');
    }

    private final void zza(StringBuilder arg2, int arg3, String arg4, zzfx arg5) {
        if(arg5 == null) {
            return;
        }

        zzfg.zza(arg2, arg3);
        arg2.append(arg4);
        arg2.append(" {\n");
        if(arg5.zzavo != null) {
            arg4 = "UNKNOWN_COMPARISON_TYPE";
            switch(arg5.zzavo.intValue()) {
                case 1: {
                    arg4 = "LESS_THAN";
                    break;
                }
                case 2: {
                    arg4 = "GREATER_THAN";
                    break;
                }
                case 3: {
                    arg4 = "EQUAL";
                    break;
                }
                case 4: {
                    arg4 = "BETWEEN";
                    break;
                }
                default: {
                    break;
                }
            }

            zzfg.zza(arg2, arg3, "comparison_type", arg4);
        }

        zzfg.zza(arg2, arg3, "match_as_float", arg5.zzavp);
        zzfg.zza(arg2, arg3, "comparison_value", arg5.zzavq);
        zzfg.zza(arg2, arg3, "min_comparison_value", arg5.zzavr);
        zzfg.zza(arg2, arg3, "max_comparison_value", arg5.zzavs);
        zzfg.zza(arg2, arg3);
        arg2.append("}\n");
    }

    private static void zza(StringBuilder arg8, int arg9, String arg10, zzgj arg11) {
        int v4;
        long[] v10;
        if(arg11 == null) {
            return;
        }

        arg9 = 3;
        zzfg.zza(arg8, arg9);
        arg8.append(arg10);
        arg8.append(" {\n");
        char v0 = '\n';
        int v1 = 4;
        int v2 = 0;
        if(arg11.zzayf != null) {
            zzfg.zza(arg8, v1);
            arg8.append("results: ");
            v10 = arg11.zzayf;
            int v3 = v10.length;
            v4 = 0;
            int v5;
            for(v5 = 0; v4 < v3; v5 = v7) {
                Long v6 = Long.valueOf(v10[v4]);
                int v7 = v5 + 1;
                if(v5 != 0) {
                    arg8.append(", ");
                }

                arg8.append(v6);
                ++v4;
            }

            arg8.append(v0);
        }

        if(arg11.zzaye != null) {
            zzfg.zza(arg8, v1);
            arg8.append("status: ");
            v10 = arg11.zzaye;
            int v11 = v10.length;
            for(v1 = 0; v2 < v11; v1 = v4) {
                Long v3_1 = Long.valueOf(v10[v2]);
                v4 = v1 + 1;
                if(v1 != 0) {
                    arg8.append(", ");
                }

                arg8.append(v3_1);
                ++v2;
            }

            arg8.append(v0);
        }

        zzfg.zza(arg8, arg9);
        arg8.append("}\n");
    }

    static boolean zza(long[] arg6, int arg7) {
        if(arg7 >= arg6.length << 6) {
            return 0;
        }

        if((1 << arg7 % 64 & arg6[arg7 / 64]) != 0) {
            return 1;
        }

        return 0;
    }

    static long[] zza(BitSet arg10) {
        int v1 = 64;
        int v0 = (arg10.length() + 63) / v1;
        long[] v2 = new long[v0];
        int v4;
        for(v4 = 0; v4 < v0; ++v4) {
            v2[v4] = 0;
            int v5;
            for(v5 = 0; v5 < v1; ++v5) {
                int v6 = (v4 << 6) + v5;
                if(v6 >= arg10.length()) {
                    break;
                }

                if(arg10.get(v6)) {
                    v2[v4] |= 1 << v5;
                }
            }
        }

        return v2;
    }

    final Parcelable zza(byte[] arg5, Parcelable$Creator arg6) {
        Parcelable v0 = null;
        if(arg5 == null) {
            return v0;
        }

        Parcel v1 = Parcel.obtain();
        try {
            v1.unmarshall(arg5, 0, arg5.length);
            v1.setDataPosition(0);
            Object v5_1 = arg6.createFromParcel(v1);
            v1.recycle();
            return ((Parcelable)v5_1);
        }
        catch(Throwable v5) {
        label_19:
            v1.recycle();
            throw v5;
        }
        catch(ParseException ) {
            try {
                ((zzco)this).zzgo().zzjd().zzbx("Failed to load parcelable from buffer");
            }
            catch(Throwable v5) {
                goto label_19;
            }

            v1.recycle();
            return v0;
        }
    }

    final String zza(zzfv arg7) {
        if(arg7 == null) {
            return "null";
        }

        StringBuilder v0 = new StringBuilder();
        v0.append("\nevent_filter {\n");
        int v3 = 0;
        zzfg.zza(v0, 0, "filter_id", arg7.zzave);
        zzfg.zza(v0, 0, "event_name", ((zzco)this).zzgl().zzbs(arg7.zzavf));
        this.zza(v0, 1, "event_count_filter", arg7.zzavi);
        v0.append("  filters {\n");
        zzfw[] v7 = arg7.zzavg;
        int v1 = v7.length;
        while(v3 < v1) {
            this.zza(v0, 2, v7[v3]);
            ++v3;
        }

        zzfg.zza(v0, 1);
        v0.append("}\n}\n");
        return v0.toString();
    }

    final String zza(zzfy arg6) {
        if(arg6 == null) {
            return "null";
        }

        StringBuilder v0 = new StringBuilder();
        v0.append("\nproperty_filter {\n");
        zzfg.zza(v0, 0, "filter_id", arg6.zzave);
        zzfg.zza(v0, 0, "property_name", ((zzco)this).zzgl().zzbu(arg6.zzavu));
        this.zza(v0, 1, arg6.zzavv);
        v0.append("}\n");
        return v0.toString();
    }

    final void zza(zzgg arg2, Object arg3) {
        Preconditions.checkNotNull(arg3);
        arg2.zzamp = null;
        arg2.zzawx = null;
        arg2.zzauh = null;
        if((arg3 instanceof String)) {
            arg2.zzamp = ((String)arg3);
            return;
        }

        if((arg3 instanceof Long)) {
            arg2.zzawx = ((Long)arg3);
            return;
        }

        if((arg3 instanceof Double)) {
            arg2.zzauh = ((Double)arg3);
            return;
        }

        ((zzco)this).zzgo().zzjd().zzg("Ignoring invalid (type) event param value", arg3);
    }

    final void zza(zzgl arg2, Object arg3) {
        Preconditions.checkNotNull(arg3);
        arg2.zzamp = null;
        arg2.zzawx = null;
        arg2.zzauh = null;
        if((arg3 instanceof String)) {
            arg2.zzamp = ((String)arg3);
            return;
        }

        if((arg3 instanceof Long)) {
            arg2.zzawx = ((Long)arg3);
            return;
        }

        if((arg3 instanceof Double)) {
            arg2.zzauh = ((Double)arg3);
            return;
        }

        ((zzco)this).zzgo().zzjd().zzg("Ignoring invalid (type) user attribute value", arg3);
    }

    final byte[] zza(byte[] arg6) {
        try {
            ByteArrayInputStream v0 = new ByteArrayInputStream(arg6);
            GZIPInputStream v6_1 = new GZIPInputStream(((InputStream)v0));
            ByteArrayOutputStream v1 = new ByteArrayOutputStream();
            byte[] v2 = new byte[1024];
            while(true) {
                int v3 = v6_1.read(v2);
                if(v3 <= 0) {
                    break;
                }

                v1.write(v2, 0, v3);
            }

            v6_1.close();
            v0.close();
            return v1.toByteArray();
        }
        catch(IOException v6) {
            ((zzco)this).zzgo().zzjd().zzg("Failed to ungzip content", v6);
            throw v6;
        }
    }

    public final void zzaf() {
        super.zzaf();
    }

    final byte[] zzb(byte[] arg3) {
        try {
            ByteArrayOutputStream v0 = new ByteArrayOutputStream();
            GZIPOutputStream v1 = new GZIPOutputStream(((OutputStream)v0));
            v1.write(arg3);
            v1.close();
            v0.close();
            return v0.toByteArray();
        }
        catch(IOException v3) {
            ((zzco)this).zzgo().zzjd().zzg("Failed to gzip content", v3);
            throw v3;
        }
    }

    static Object zzb(zzgf arg0, String arg1) {
        zzgg v0 = zzfg.zza(arg0, arg1);
        if(v0 != null) {
            if(v0.zzamp != null) {
                return v0.zzamp;
            }
            else if(v0.zzawx != null) {
                return v0.zzawx;
            }
            else if(v0.zzauh != null) {
                return v0.zzauh;
            }
        }

        return null;
    }

    final boolean zzb(long arg5, long arg7) {
        long v0 = 0;
        if(Long.compare(arg5, v0) != 0) {
            if(arg7 <= v0) {
            }
            else if(Math.abs(((zzco)this).zzbx().currentTimeMillis() - arg5) > arg7) {
                return 1;
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    final String zzb(zzgh arg17) {
        int v9;
        zzgh v0 = arg17;
        StringBuilder v1 = new StringBuilder();
        v1.append("\nbatch {\n");
        if(v0.zzawy != null) {
            zzgi[] v0_1 = v0.zzawy;
            int v2 = v0_1.length;
            int v4;
            for(v4 = 0; v4 < v2; ++v4) {
                zzgi v5 = v0_1[v4];
                if(v5 != null && v5 != null) {
                    zzfg.zza(v1, 1);
                    v1.append("bundle {\n");
                    zzfg.zza(v1, 1, "protocol_version", v5.zzaxa);
                    zzfg.zza(v1, 1, "platform", v5.zzaxi);
                    zzfg.zza(v1, 1, "gmp_version", v5.zzaxm);
                    zzfg.zza(v1, 1, "uploading_gmp_version", v5.zzaxn);
                    zzfg.zza(v1, 1, "config_version", v5.zzaxy);
                    zzfg.zza(v1, 1, "gmp_app_id", v5.zzafx);
                    zzfg.zza(v1, 1, "admob_app_id", v5.zzawj);
                    zzfg.zza(v1, 1, "app_id", v5.zztt);
                    zzfg.zza(v1, 1, "app_version", v5.zzts);
                    zzfg.zza(v1, 1, "app_version_major", v5.zzaxu);
                    zzfg.zza(v1, 1, "firebase_instance_id", v5.zzafz);
                    zzfg.zza(v1, 1, "dev_cert_hash", v5.zzaxq);
                    zzfg.zza(v1, 1, "app_store", v5.zzage);
                    zzfg.zza(v1, 1, "upload_timestamp_millis", v5.zzaxd);
                    zzfg.zza(v1, 1, "start_timestamp_millis", v5.zzaxe);
                    zzfg.zza(v1, 1, "end_timestamp_millis", v5.zzaxf);
                    zzfg.zza(v1, 1, "previous_bundle_start_timestamp_millis", v5.zzaxg);
                    zzfg.zza(v1, 1, "previous_bundle_end_timestamp_millis", v5.zzaxh);
                    zzfg.zza(v1, 1, "app_instance_id", v5.zzafw);
                    zzfg.zza(v1, 1, "resettable_device_id", v5.zzaxo);
                    zzfg.zza(v1, 1, "device_id", v5.zzaxx);
                    zzfg.zza(v1, 1, "ds_id", v5.zzaya);
                    zzfg.zza(v1, 1, "limited_ad_tracking", v5.zzaxp);
                    zzfg.zza(v1, 1, "os_version", v5.zzaxj);
                    zzfg.zza(v1, 1, "device_model", v5.zzaxk);
                    zzfg.zza(v1, 1, "user_default_language", v5.zzaia);
                    zzfg.zza(v1, 1, "time_zone_offset_minutes", v5.zzaxl);
                    zzfg.zza(v1, 1, "bundle_sequential_index", v5.zzaxr);
                    zzfg.zza(v1, 1, "service_upload", v5.zzaxs);
                    zzfg.zza(v1, 1, "health_monitor", v5.zzagv);
                    if(v5.zzaxz != null && v5.zzaxz.longValue() != 0) {
                        zzfg.zza(v1, 1, "android_id", v5.zzaxz);
                    }

                    if(v5.zzayc != null) {
                        zzfg.zza(v1, 1, "retry_counter", v5.zzayc);
                    }

                    zzgl[] v7 = v5.zzaxc;
                    int v8 = 2;
                    if(v7 != null) {
                        v9 = v7.length;
                        int v10;
                        for(v10 = 0; v10 < v9; ++v10) {
                            zzgl v11 = v7[v10];
                            if(v11 != null) {
                                zzfg.zza(v1, v8);
                                v1.append("user_property {\n");
                                zzfg.zza(v1, v8, "set_timestamp_millis", v11.zzayl);
                                zzfg.zza(v1, v8, "name", ((zzco)this).zzgl().zzbu(v11.name));
                                zzfg.zza(v1, v8, "string_value", v11.zzamp);
                                zzfg.zza(v1, v8, "int_value", v11.zzawx);
                                zzfg.zza(v1, v8, "double_value", v11.zzauh);
                                zzfg.zza(v1, v8);
                                v1.append("}\n");
                            }
                        }
                    }

                    zzgd[] v7_1 = v5.zzaxt;
                    if(v7_1 != null) {
                        v9 = v7_1.length;
                        for(v10 = 0; v10 < v9; ++v10) {
                            zzgd v11_1 = v7_1[v10];
                            if(v11_1 != null) {
                                zzfg.zza(v1, v8);
                                v1.append("audience_membership {\n");
                                zzfg.zza(v1, v8, "audience_id", v11_1.zzauy);
                                zzfg.zza(v1, v8, "new_audience", v11_1.zzawo);
                                zzfg.zza(v1, v8, "current_data", v11_1.zzawm);
                                zzfg.zza(v1, v8, "previous_data", v11_1.zzawn);
                                zzfg.zza(v1, v8);
                                v1.append("}\n");
                            }
                        }
                    }

                    zzgf[] v5_1 = v5.zzaxb;
                    if(v5_1 != null) {
                        int v7_2 = v5_1.length;
                        for(v9 = 0; v9 < v7_2; ++v9) {
                            zzgf v10_1 = v5_1[v9];
                            if(v10_1 != null) {
                                zzfg.zza(v1, v8);
                                v1.append("event {\n");
                                zzfg.zza(v1, v8, "name", ((zzco)this).zzgl().zzbs(v10_1.name));
                                zzfg.zza(v1, v8, "timestamp_millis", v10_1.zzawu);
                                zzfg.zza(v1, v8, "previous_timestamp_millis", v10_1.zzawv);
                                zzfg.zza(v1, v8, "count", v10_1.count);
                                zzgg[] v10_2 = v10_1.zzawt;
                                if(v10_2 != null) {
                                    int v11_2 = v10_2.length;
                                    int v12;
                                    for(v12 = 0; v12 < v11_2; ++v12) {
                                        zzgg v13 = v10_2[v12];
                                        if(v13 != null) {
                                            zzfg.zza(v1, 3);
                                            v1.append("param {\n");
                                            zzfg.zza(v1, 3, "name", ((zzco)this).zzgl().zzbt(v13.name));
                                            zzfg.zza(v1, 3, "string_value", v13.zzamp);
                                            zzfg.zza(v1, 3, "int_value", v13.zzawx);
                                            zzfg.zza(v1, 3, "double_value", v13.zzauh);
                                            zzfg.zza(v1, 3);
                                            v1.append("}\n");
                                        }
                                    }
                                }

                                zzfg.zza(v1, v8);
                                v1.append("}\n");
                            }
                        }
                    }

                    zzfg.zza(v1, 1);
                    v1.append("}\n");
                }
            }
        }

        v1.append("}\n");
        return v1.toString();
    }

    public final Clock zzbx() {
        return super.zzbx();
    }

    static boolean zzcp(String arg1) {
        if(arg1 != null && (arg1.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)")) && arg1.length() <= 310) {
            return 1;
        }

        return 0;
    }

    final boolean zze(zzad arg1, zzh arg2) {
        Preconditions.checkNotNull(arg1);
        Preconditions.checkNotNull(arg2);
        if((TextUtils.isEmpty(arg2.zzafx)) && (TextUtils.isEmpty(arg2.zzagk))) {
            ((zzco)this).zzgr();
            return 0;
        }

        return 1;
    }

    public final void zzga() {
        super.zzga();
    }

    public final void zzgb() {
        super.zzgb();
    }

    public final void zzgc() {
        super.zzgc();
    }

    public final zzx zzgk() {
        return super.zzgk();
    }

    public final zzan zzgl() {
        return super.zzgl();
    }

    public final zzfk zzgm() {
        return super.zzgm();
    }

    public final zzbo zzgn() {
        return super.zzgn();
    }

    public final zzap zzgo() {
        return super.zzgo();
    }

    public final zzba zzgp() {
        return super.zzgp();
    }

    public final zzn zzgq() {
        return super.zzgq();
    }

    public final zzk zzgr() {
        return super.zzgr();
    }

    protected final boolean zzgt() {
        return 0;
    }

    public final zzfg zzjo() {
        return super.zzjo();
    }

    public final zzj zzjp() {
        return super.zzjp();
    }

    public final zzq zzjq() {
        return super.zzjq();
    }
}

