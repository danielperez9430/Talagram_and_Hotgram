package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.measurement.AppMeasurement$Event;
import com.google.android.gms.measurement.AppMeasurement$UserProperty;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.security.auth.x500.X500Principal;

public final class zzfk extends zzcp {
    private int zzaed;
    private static final String[] zzaui;
    private SecureRandom zzauj;
    private final AtomicLong zzauk;
    private Integer zzaul;

    static {
        zzfk.zzaui = new String[]{"firebase_", "google_", "ga_"};
    }

    zzfk(zzbt arg3) {
        super(arg3);
        this.zzaul = null;
        this.zzauk = new AtomicLong(0);
    }

    public final Context getContext() {
        return super.getContext();
    }

    static MessageDigest getMessageDigest() {
        MessageDigest v1;
        int v0;
        for(v0 = 0; v0 < 2; ++v0) {
            try {
                v1 = MessageDigest.getInstance("MD5");
                if(v1 == null) {
                    goto label_7;
                }
            }
            catch(NoSuchAlgorithmException ) {
                goto label_7;
            }

            return v1;
        label_7:
        }

        return null;
    }

    public static String zza(String arg3, String[] arg4, String[] arg5) {
        Preconditions.checkNotNull(arg4);
        Preconditions.checkNotNull(arg5);
        int v0 = Math.min(arg4.length, arg5.length);
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            if(zzfk.zzu(arg3, arg4[v1])) {
                return arg5[v1];
            }
        }

        return null;
    }

    final Bundle zza(Uri arg7) {
        String v3;
        String v2;
        String v1;
        Bundle v0 = null;
        if(arg7 == null) {
            return v0;
        }

        try {
            if(arg7.isHierarchical()) {
                v1 = arg7.getQueryParameter("utm_campaign");
                v2 = arg7.getQueryParameter("utm_source");
                v3 = arg7.getQueryParameter("utm_medium");
                String v4 = arg7.getQueryParameter("gclid");
            }
            else {
                goto label_14;
            }

            goto label_18;
        }
        catch(UnsupportedOperationException v7) {
            ((zzco)this).zzgo().zzjg().zzg("Install referrer url isn\'t a hierarchical URI", v7);
            return v0;
        }

    label_14:
        CharSequence v1_1 = ((CharSequence)v0);
        CharSequence v2_1 = v1_1;
        CharSequence v3_1 = v2_1;
        CharSequence v4_1 = v3_1;
    label_18:
        if((TextUtils.isEmpty(v1_1)) && (TextUtils.isEmpty(((CharSequence)v2))) && (TextUtils.isEmpty(((CharSequence)v3)))) {
            if(!TextUtils.isEmpty(v4_1)) {
            }
            else {
                return v0;
            }
        }

        v0 = new Bundle();
        if(!TextUtils.isEmpty(v1_1)) {
            v0.putString("campaign", ((String)v1_1));
        }

        if(!TextUtils.isEmpty(((CharSequence)v2))) {
            v0.putString("source", v2);
        }

        if(!TextUtils.isEmpty(((CharSequence)v3))) {
            v0.putString("medium", v3);
        }

        if(!TextUtils.isEmpty(v4_1)) {
            v0.putString("gclid", ((String)v4_1));
        }

        v1 = arg7.getQueryParameter("utm_term");
        if(!TextUtils.isEmpty(((CharSequence)v1))) {
            v0.putString("term", v1);
        }

        v1 = arg7.getQueryParameter("utm_content");
        if(!TextUtils.isEmpty(((CharSequence)v1))) {
            v0.putString("content", v1);
        }

        v1 = arg7.getQueryParameter("aclid");
        if(!TextUtils.isEmpty(((CharSequence)v1))) {
            v0.putString("aclid", v1);
        }

        v1 = arg7.getQueryParameter("cp1");
        if(!TextUtils.isEmpty(((CharSequence)v1))) {
            v0.putString("cp1", v1);
        }

        String v7_1 = arg7.getQueryParameter("anid");
        if(!TextUtils.isEmpty(((CharSequence)v7_1))) {
            v0.putString("anid", v7_1);
        }

        return v0;
    }

    final void zza(Bundle arg3, String arg4, Object arg5) {
        String v3;
        if(arg3 == null) {
            return;
        }

        if((arg5 instanceof Long)) {
            arg3.putLong(arg4, ((Long)arg5).longValue());
            return;
        }

        if((arg5 instanceof String)) {
            arg3.putString(arg4, String.valueOf(arg5));
            return;
        }

        if((arg5 instanceof Double)) {
            arg3.putDouble(arg4, ((Double)arg5).doubleValue());
            return;
        }

        if(arg4 != null) {
            if(arg5 != null) {
                v3 = arg5.getClass().getSimpleName();
            }
            else {
                Object v3_1 = null;
            }

            ((zzco)this).zzgo().zzji().zze("Not putting event parameter. Invalid value type. name, type", ((zzco)this).zzgl().zzbt(arg4), v3);
        }
    }

    static boolean zza(Context arg1, boolean arg2) {
        Preconditions.checkNotNull(arg1);
        String v2 = Build$VERSION.SDK_INT >= 24 ? "com.google.android.gms.measurement.AppMeasurementJobService" : "com.google.android.gms.measurement.AppMeasurementService";
        return zzfk.zzc(arg1, v2);
    }

    static boolean zza(String arg4, String arg5, String arg6, String arg7) {
        boolean v0 = TextUtils.isEmpty(((CharSequence)arg4));
        boolean v1 = TextUtils.isEmpty(((CharSequence)arg5));
        if(!v0 && !v1) {
            if(!arg4.equals(arg5)) {
                return 1;
            }
            else {
                return 0;
            }
        }

        if((v0) && (v1)) {
            if(!TextUtils.isEmpty(((CharSequence)arg6)) && !TextUtils.isEmpty(((CharSequence)arg7))) {
                if(!arg6.equals(arg7)) {
                    return 1;
                }
                else {
                    return 0;
                }
            }

            if(!TextUtils.isEmpty(((CharSequence)arg7))) {
                return 1;
            }

            return 0;
        }

        if(!v0 && (v1)) {
            if(TextUtils.isEmpty(((CharSequence)arg7))) {
                return 0;
            }
            else {
                if(!TextUtils.isEmpty(((CharSequence)arg6))) {
                    if(!arg6.equals(arg7)) {
                    }
                    else {
                        return 0;
                    }
                }

                return 1;
            }
        }

        if(!TextUtils.isEmpty(((CharSequence)arg6))) {
            if(!arg6.equals(arg7)) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    final void zza(String arg2, int arg3, String arg4, String arg5, int arg6) {
        Bundle v2 = new Bundle();
        zzfk.zza(v2, arg3);
        if(!TextUtils.isEmpty(((CharSequence)arg4))) {
            v2.putString(arg4, arg5);
        }

        if(arg3 == 6 || arg3 == 7 || arg3 == 2) {
            v2.putLong("_el", ((long)arg6));
        }

        this.zzadj.zzgr();
        this.zzadj.zzge().logEvent("auto", "_err", v2);
    }

    public static String zza(String arg2, int arg3, boolean arg4) {
        if(arg2.codePointCount(0, arg2.length()) > arg3) {
            if(arg4) {
                return String.valueOf(arg2.substring(0, arg2.offsetByCodePoints(0, arg3))).concat("...");
            }
            else {
                arg2 = null;
            }
        }

        return arg2;
    }

    final zzad zza(String arg8, String arg9, Bundle arg10, String arg11, long arg12, boolean arg14, boolean arg15) {
        if(TextUtils.isEmpty(((CharSequence)arg9))) {
            return null;
        }

        if(this.zzcr(arg9) == 0) {
            Bundle v14 = arg10 != null ? new Bundle(arg10) : new Bundle();
            Bundle v3 = v14;
            v3.putString("_o", arg11);
            return new zzad(arg9, new zzaa(this.zze(this.zza(arg8, arg9, v3, CollectionUtils.listOf("_o"), false, false))), arg11, arg12);
        }

        ((zzco)this).zzgo().zzjd().zzg("Invalid conditional property event name", ((zzco)this).zzgl().zzbu(arg9));
        throw new IllegalArgumentException();
    }

    private static Object zza(int arg2, Object arg3, boolean arg4) {
        Object v0 = null;
        if(arg3 == null) {
            return v0;
        }

        if(!(arg3 instanceof Long)) {
            if((arg3 instanceof Double)) {
            }
            else if((arg3 instanceof Integer)) {
                return Long.valueOf(((long)((Integer)arg3).intValue()));
            }
            else if((arg3 instanceof Byte)) {
                return Long.valueOf(((long)((Byte)arg3).byteValue()));
            }
            else if((arg3 instanceof Short)) {
                return Long.valueOf(((long)((Short)arg3).shortValue()));
            }
            else if((arg3 instanceof Boolean)) {
                long v2 = ((Boolean)arg3).booleanValue() ? 1 : 0;
                return Long.valueOf(v2);
            }
            else {
                if((arg3 instanceof Float)) {
                    return Double.valueOf(((Float)arg3).doubleValue());
                }

                if(!(arg3 instanceof String) && !(arg3 instanceof Character)) {
                    if((arg3 instanceof CharSequence)) {
                    }
                    else {
                        return v0;
                    }
                }

                return zzfk.zza(String.valueOf(arg3), arg2, arg4);
            }
        }

        return arg3;
    }

    private static void zza(Bundle arg3, Object arg4) {
        Preconditions.checkNotNull(arg3);
        if(arg4 != null && (((arg4 instanceof String)) || ((arg4 instanceof CharSequence)))) {
            arg3.putLong("_el", ((long)String.valueOf(arg4).length()));
        }
    }

    private static boolean zza(Bundle arg5, int arg6) {
        if(arg5.getLong("_err") == 0) {
            arg5.putLong("_err", ((long)arg6));
            return 1;
        }

        return 0;
    }

    private final boolean zza(String arg4, String arg5, int arg6, Object arg7, boolean arg8) {
        Object v8;
        int v4;
        if(arg7 == null) {
            return 1;
        }

        if(!(arg7 instanceof Long) && !(arg7 instanceof Float) && !(arg7 instanceof Integer) && !(arg7 instanceof Byte) && !(arg7 instanceof Short) && !(arg7 instanceof Boolean)) {
            if((arg7 instanceof Double)) {
            }
            else {
                if(!(arg7 instanceof String) && !(arg7 instanceof Character)) {
                    if((arg7 instanceof CharSequence)) {
                    }
                    else {
                        if(((arg7 instanceof Bundle)) && (arg8)) {
                            return 1;
                        }

                        if(((arg7 instanceof Parcelable[])) && (arg8)) {
                            v4 = arg7.length;
                            arg6 = 0;
                            while(true) {
                                if(arg6 < v4) {
                                    v8 = arg7[arg6];
                                    if(!(v8 instanceof Bundle)) {
                                        ((zzco)this).zzgo().zzjg().zze("All Parcelable[] elements must be of type Bundle. Value type, name", v8.getClass(), arg5);
                                        return 0;
                                    }
                                    else {
                                        ++arg6;
                                        continue;
                                    }
                                }
                                else {
                                    return 1;
                                }

                                goto label_48;
                            }

                            return 1;
                        }

                    label_48:
                        if(((arg7 instanceof ArrayList)) && (arg8)) {
                            v4 = ((ArrayList)arg7).size();
                            arg6 = 0;
                            do {
                                if(arg6 < v4) {
                                    v8 = ((ArrayList)arg7).get(arg6);
                                    ++arg6;
                                    if((v8 instanceof Bundle)) {
                                        continue;
                                    }

                                    break;
                                }
                                else {
                                    return 1;
                                }
                            }
                            while(true);

                            ((zzco)this).zzgo().zzjg().zze("All ArrayList elements must be of type Bundle. Value type, name", v8.getClass(), arg5);
                            return 0;
                        }

                        return 0;
                    }
                }

                String v7 = String.valueOf(arg7);
                if(v7.codePointCount(0, v7.length()) <= arg6) {
                    return 1;
                }

                ((zzco)this).zzgo().zzjg().zzd("Value is too long; discarded. Value kind, name, value length", arg4, arg5, Integer.valueOf(v7.length()));
                return 0;
            }
        }

        return 1;
    }

    static byte[] zza(Parcelable arg2) {
        byte[] v2_1;
        if(arg2 == null) {
            return null;
        }

        Parcel v0 = Parcel.obtain();
        try {
            arg2.writeToParcel(v0, 0);
            v2_1 = v0.marshall();
        }
        catch(Throwable v2) {
            v0.recycle();
            throw v2;
        }

        v0.recycle();
        return v2_1;
    }

    final Bundle zza(String arg18, String arg19, Bundle arg20, List arg21, boolean arg22, boolean arg23) {
        int v3;
        Object v2_1;
        zzfk v0_2;
        String v1_1;
        boolean v9_1;
        int v2;
        int v1;
        Bundle v10;
        zzfk v6 = this;
        Bundle v7 = arg20;
        List v8 = arg21;
        String[] v9 = null;
        if(v7 != null) {
            v10 = new Bundle(v7);
            Iterator v11 = arg20.keySet().iterator();
            int v13 = 0;
            while(v11.hasNext()) {
                Object v14 = v11.next();
                int v15 = 40;
                int v0 = 3;
                if(v8 == null || !v8.contains(v14)) {
                    v1 = 14;
                    if(arg22) {
                        if(v6.zzr("event param", ((String)v14))) {
                            if(!v6.zza("event param", v9, ((String)v14))) {
                                v2 = 14;
                                goto label_40;
                            }
                            else if(!v6.zza("event param", v15, ((String)v14))) {
                            }
                            else {
                                goto label_39;
                            }
                        }

                        v2 = 3;
                    }
                    else {
                    label_39:
                        v2 = 0;
                    }

                label_40:
                    if(v2 != 0) {
                        goto label_54;
                    }

                    if(v6.zzs("event param", ((String)v14))) {
                        if(!v6.zza("event param", v9, ((String)v14))) {
                            goto label_55;
                        }
                        else if(!v6.zza("event param", v15, ((String)v14))) {
                        }
                        else {
                            goto label_21;
                        }
                    }

                    v1 = 3;
                    goto label_55;
                label_21:
                    v1 = 0;
                    goto label_55;
                label_54:
                    v1 = v2;
                }
                else {
                    goto label_21;
                }

            label_55:
                if(v1 != 0) {
                    if(zzfk.zza(v10, v1)) {
                        v10.putString("_ev", zzfk.zza(((String)v14), v15, true));
                        if(v1 == v0) {
                            zzfk.zza(v10, v14);
                        }
                        else {
                        }
                    }
                    else {
                    }

                    goto label_154;
                }
                else {
                    Object v4 = v7.get(((String)v14));
                    ((zzco)this).zzaf();
                    if(arg23) {
                        String v0_1 = "param";
                        if((v4 instanceof Parcelable[])) {
                            v1 = v4.length;
                            goto label_78;
                        }
                        else if((v4 instanceof ArrayList)) {
                            v1 = v4.size();
                        label_78:
                            if(v1 > 1000) {
                                ((zzco)this).zzgo().zzjg().zzd("Parameter array is too long; discarded. Value kind, name, array length", v0_1, v14, Integer.valueOf(v1));
                                v0 = 0;
                            }
                            else {
                                goto label_87;
                            }
                        }
                        else {
                        label_87:
                            v0 = 1;
                        }

                        if(v0 != 0) {
                            goto label_92;
                        }

                        v0 = 17;
                        v9_1 = true;
                    }
                    else {
                    label_92:
                        if((((zzco)this).zzgq().zzay(arg18)) && (zzfk.zzcv(arg19)) || (zzfk.zzcv(((String)v14)))) {
                            v1_1 = "param";
                            v0_2 = this;
                            v2_1 = v14;
                            v3 = 256;
                            v9_1 = true;
                        }
                        else {
                            v9_1 = true;
                            v1_1 = "param";
                            v3 = 100;
                            v0_2 = this;
                            v2_1 = v14;
                        }

                        boolean v0_3 = v0_2.zza(v1_1, ((String)v2_1), v3, v4, arg23);
                        if(v0_3) {
                            v0 = 0;
                            goto label_119;
                        }

                        v0 = 4;
                    }

                label_119:
                    if(v0 != 0 && !"_ev".equals(v14)) {
                        if(zzfk.zza(v10, v0)) {
                            v10.putString("_ev", zzfk.zza(((String)v14), v15, v9_1));
                            zzfk.zza(v10, v7.get(((String)v14)));
                        }
                        else {
                        }

                        goto label_154;
                    }

                    if(!zzfk.zzcq(((String)v14))) {
                        goto label_131;
                    }

                    ++v13;
                    if(v13 <= 25) {
                        goto label_131;
                    }

                    StringBuilder v1_2 = new StringBuilder(48);
                    v1_2.append("Event can\'t contain more than 25 params");
                    ((zzco)this).zzgo().zzjd().zze(v1_2.toString(), ((zzco)this).zzgl().zzbs(arg19), ((zzco)this).zzgl().zzd(v7));
                    zzfk.zza(v10, 5);
                label_154:
                    v10.remove(((String)v14));
                }

            label_131:
                v9 = null;
            }
        }
        else {
            v10 = null;
        }

        return v10;
    }

    final boolean zza(String arg7, String[] arg8, String arg9) {
        int v8;
        int v1_1;
        if(arg9 == null) {
            ((zzco)this).zzgo().zzjd().zzg("Name is required and can\'t be null. Type", arg7);
            return 0;
        }

        Preconditions.checkNotNull(arg9);
        String[] v1 = zzfk.zzaui;
        int v2 = v1.length;
        int v3 = 0;
        while(true) {
            if(v3 >= v2) {
                break;
            }
            else if(arg9.startsWith(v1[v3])) {
                v1_1 = 1;
            }
            else {
                ++v3;
                continue;
            }

            goto label_21;
        }

        v1_1 = 0;
    label_21:
        if(v1_1 != 0) {
            ((zzco)this).zzgo().zzjd().zze("Name starts with reserved prefix. Type, name", arg7, arg9);
            return 0;
        }

        if(arg8 != null) {
            Preconditions.checkNotNull(arg8);
            v1_1 = arg8.length;
            v2 = 0;
            while(true) {
                if(v2 >= v1_1) {
                    break;
                }
                else if(zzfk.zzu(arg9, arg8[v2])) {
                    v8 = 1;
                }
                else {
                    ++v2;
                    continue;
                }

                goto label_40;
            }

            v8 = 0;
        label_40:
            if(v8 == 0) {
                return 1;
            }

            ((zzco)this).zzgo().zzjd().zze("Name is reserved. Type, name", arg7, arg9);
            return 0;
        }

        return 1;
    }

    final boolean zza(String arg4, int arg5, String arg6) {
        if(arg6 == null) {
            ((zzco)this).zzgo().zzjd().zzg("Name is required and can\'t be null. Type", arg4);
            return 0;
        }

        if(arg6.codePointCount(0, arg6.length()) > arg5) {
            ((zzco)this).zzgo().zzjd().zzd("Name is too long. Type, maximum supported length, name", arg4, Integer.valueOf(arg5), arg6);
            return 0;
        }

        return 1;
    }

    public final void zza(int arg7, String arg8, String arg9, int arg10) {
        this.zza(null, arg7, arg8, arg9, arg10);
    }

    public final void zzaf() {
        super.zzaf();
    }

    public final Clock zzbx() {
        return super.zzbx();
    }

    public static long zzc(long arg2, long arg4) {
        return (arg2 + arg4 * 60000) / 86400000;
    }

    private static boolean zzc(Context arg3, String arg4) {
        try {
            PackageManager v1 = arg3.getPackageManager();
            if(v1 == null) {
                return 0;
            }

            ServiceInfo v3 = v1.getServiceInfo(new ComponentName(arg3, arg4), 0);
            if(v3 != null && (v3.enabled)) {
                return 1;
            }
        }
        catch(PackageManager$NameNotFoundException ) {
        }

        return 0;
    }

    @VisibleForTesting static long zzc(byte[] arg9) {
        Preconditions.checkNotNull(arg9);
        int v1 = 0;
        boolean v0 = arg9.length > 0 ? true : false;
        Preconditions.checkState(v0);
        long v3 = 0;
        int v0_1;
        for(v0_1 = arg9.length - 1; v0_1 >= 0; --v0_1) {
            if(v0_1 < arg9.length - 8) {
                return v3;
            }

            v3 += ((((long)arg9[v0_1])) & 255) << v1;
            v1 += 8;
        }

        return v3;
    }

    static boolean zzcq(String arg3) {
        Preconditions.checkNotEmpty(arg3);
        if(arg3.charAt(0) == 95) {
            if(arg3.equals("_ep")) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    final int zzcr(String arg4) {
        int v1 = 2;
        if(!this.zzs("event", arg4)) {
            return v1;
        }

        if(!this.zza("event", Event.zzadk, arg4)) {
            return 13;
        }

        if(!this.zza("event", 40, arg4)) {
            return v1;
        }

        return 0;
    }

    final int zzcs(String arg4) {
        int v1 = 6;
        if(!this.zzs("user property", arg4)) {
            return v1;
        }

        if(!this.zza("user property", UserProperty.zzado, arg4)) {
            return 15;
        }

        if(!this.zza("user property", 24, arg4)) {
            return v1;
        }

        return 0;
    }

    @VisibleForTesting private static boolean zzct(String arg1) {
        Preconditions.checkNotNull(arg1);
        return arg1.matches("^(1:\\d+:android:[a-f0-9]+|ca-app-pub-.*)$");
    }

    private static int zzcu(String arg1) {
        if("_ldl".equals(arg1)) {
            return 2048;
        }

        if("_id".equals(arg1)) {
            return 256;
        }

        return 36;
    }

    static boolean zzcv(String arg1) {
        if(!TextUtils.isEmpty(((CharSequence)arg1)) && (arg1.startsWith("_"))) {
            return 1;
        }

        return 0;
    }

    final boolean zzcw(String arg2) {
        if(TextUtils.isEmpty(((CharSequence)arg2))) {
            return 0;
        }

        String v0 = ((zzco)this).zzgq().zzhy();
        ((zzco)this).zzgr();
        return v0.equals(arg2);
    }

    static boolean zzd(Intent arg1) {
        String v1 = arg1.getStringExtra("android.intent.extra.REFERRER_NAME");
        if(!"android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(v1) && !"https://www.google.com".equals(v1)) {
            if("android-app://com.google.appcrawler".equals(v1)) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    final long zzd(Context arg7, String arg8) {
        ((zzco)this).zzaf();
        Preconditions.checkNotNull(arg7);
        Preconditions.checkNotEmpty(arg8);
        PackageManager v0 = arg7.getPackageManager();
        MessageDigest v1 = zzfk.getMessageDigest();
        long v2 = -1;
        long v4 = 0;
        if(v1 == null) {
            ((zzco)this).zzgo().zzjd().zzbx("Could not get MD5 instance");
            return v2;
        }

        if(v0 != null) {
            try {
                if(this.zze(arg7, arg8)) {
                    return v4;
                }

                PackageInfo v7_1 = Wrappers.packageManager(arg7).getPackageInfo(((zzco)this).getContext().getPackageName(), 64);
                if(v7_1.signatures != null && v7_1.signatures.length > 0) {
                    return zzfk.zzc(v1.digest(v7_1.signatures[0].toByteArray()));
                }

                ((zzco)this).zzgo().zzjg().zzbx("Could not get signatures");
                return v2;
            }
            catch(PackageManager$NameNotFoundException v7) {
                ((zzco)this).zzgo().zzjd().zzg("Package name not found", v7);
            }
        }

        return v4;
    }

    @VisibleForTesting private final boolean zze(Context arg3, String arg4) {
        String v0_1;
        zzar v4;
        X500Principal v0 = new X500Principal("CN=Android Debug,O=Android,C=US");
        try {
            PackageInfo v3_2 = Wrappers.packageManager(arg3).getPackageInfo(arg4, 64);
            if(v3_2 == null) {
                return 1;
            }

            if(v3_2.signatures == null) {
                return 1;
            }

            if(v3_2.signatures.length <= 0) {
                return 1;
            }

            return CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(v3_2.signatures[0].toByteArray())).getSubjectX500Principal().equals(v0);
        }
        catch(PackageManager$NameNotFoundException v3) {
            v4 = ((zzco)this).zzgo().zzjd();
            v0_1 = "Package name not found";
        }
        catch(CertificateException v3_1) {
            v4 = ((zzco)this).zzgo().zzjd();
            v0_1 = "Error obtaining certificate";
        }

        v4.zzg(v0_1, v3);
        return 1;
    }

    static Bundle[] zze(Object arg2) {
        Object[] v2;
        if((arg2 instanceof Bundle)) {
            return new Bundle[]{arg2};
        }

        if((arg2 instanceof Parcelable[])) {
            v2 = Arrays.copyOf(((Object[])arg2), arg2.length, Bundle[].class);
        }
        else if((arg2 instanceof ArrayList)) {
            v2 = ((ArrayList)arg2).toArray(new Bundle[((ArrayList)arg2).size()]);
        }
        else {
            return null;
        }

        return ((Bundle[])v2);
    }

    final Bundle zze(Bundle arg7) {
        Bundle v0 = new Bundle();
        if(arg7 != null) {
            Iterator v1 = arg7.keySet().iterator();
            while(v1.hasNext()) {
                Object v2 = v1.next();
                Object v3 = this.zzh(((String)v2), arg7.get(((String)v2)));
                if(v3 == null) {
                    ((zzco)this).zzgo().zzjg().zzg("Param value can\'t be null", ((zzco)this).zzgl().zzbt(((String)v2)));
                }
                else {
                    this.zza(v0, ((String)v2), v3);
                }
            }
        }

        return v0;
    }

    public static Object zzf(Object arg4) {
        Object v1_2;
        ObjectInputStream v4;
        ObjectOutputStream v2;
        ByteArrayOutputStream v1_1;
        Object v0 = null;
        if(arg4 == null) {
            return v0;
        }

        try {
            v1_1 = new ByteArrayOutputStream();
            v2 = new ObjectOutputStream(((OutputStream)v1_1));
        }
        catch(Throwable v1) {
            v4 = ((ObjectInputStream)v0);
            v2 = ((ObjectOutputStream)v4);
            goto label_26;
        }

        try {
            v2.writeObject(arg4);
            v2.flush();
            v4 = new ObjectInputStream(new ByteArrayInputStream(v1_1.toByteArray()));
        }
        catch(Throwable v1) {
            v4 = ((ObjectInputStream)v0);
            goto label_26;
        }

        try {
            v1_2 = v4.readObject();
            goto label_15;
        }
        catch(Throwable v1) {
            try {
            label_26:
                if(v2 != null) {
                    v2.close();
                }

                if(v4 != null) {
                    v4.close();
                }

                throw v1;
            label_15:
                v2.close();
                v4.close();
                return v1_2;
            }
            catch(ClassNotFoundException ) {
                return v0;
            }
        }
    }

    public static Bundle zzf(Bundle arg5) {
        int v3;
        Object v2;
        Object v1;
        if(arg5 == null) {
            return new Bundle();
        }

        Bundle v0 = new Bundle(arg5);
        Iterator v5 = v0.keySet().iterator();
        do {
        label_8:
            if(!v5.hasNext()) {
                return v0;
            }

            v1 = v5.next();
            v2 = v0.get(((String)v1));
            if((v2 instanceof Bundle)) {
                v0.putBundle(((String)v1), new Bundle(((Bundle)v2)));
                continue;
            }

            v3 = 0;
            if(!(v2 instanceof Parcelable[])) {
                if(!(v2 instanceof List)) {
                    continue;
                }

                goto label_34;
            }

            goto label_21;
        }
        while(true);

        return v0;
        while(true) {
        label_34:
            if(v3 >= ((List)v2).size()) {
                goto label_8;
            }

            v1 = ((List)v2).get(v3);
            if((v1 instanceof Bundle)) {
                ((List)v2).set(v3, new Bundle(((Bundle)v1)));
            }

            ++v3;
        }

        while(true) {
        label_21:
            if(v3 >= v2.length) {
                goto label_8;
            }

            if((v2[v3] instanceof Bundle)) {
                v2[v3] = new Bundle(v2[v3]);
            }

            ++v3;
        }
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
        return 1;
    }

    protected final void zzgu() {
        ((zzco)this).zzaf();
        SecureRandom v0 = new SecureRandom();
        long v1 = v0.nextLong();
        long v3 = 0;
        if(v1 == v3) {
            v1 = v0.nextLong();
            if(v1 == v3) {
                ((zzco)this).zzgo().zzjg().zzbx("Utils falling back to Random for random id");
            }
        }

        this.zzauk.set(v1);
    }

    final Object zzh(String arg3, Object arg4) {
        boolean v3;
        int v1 = 256;
        if("_ev".equals(arg3)) {
            v3 = true;
        }
        else {
            if(zzfk.zzcv(arg3)) {
            }
            else {
                v1 = 100;
            }

            v3 = false;
        }

        return zzfk.zza(v1, arg4, v3);
    }

    final int zzi(String arg8, Object arg9) {
        boolean v8 = "_ldl".equals(arg8) ? this.zza("user property referrer", arg8, zzfk.zzcu(arg8), arg9, false) : this.zza("user property", arg8, zzfk.zzcu(arg8), arg9, false);
        if(v8) {
            return 0;
        }

        return 7;
    }

    final Object zzj(String arg2, Object arg3) {
        boolean v0;
        int v2;
        if("_ldl".equals(arg2)) {
            v2 = zzfk.zzcu(arg2);
            v0 = true;
        }
        else {
            v2 = zzfk.zzcu(arg2);
            v0 = false;
        }

        return zzfk.zza(v2, arg3, v0);
    }

    public final long zzmc() {
        AtomicLong v0;
        if(this.zzauk.get() == 0) {
            v0 = this.zzauk;
            __monitor_enter(v0);
            try {
                long v1_1 = new Random(System.nanoTime() ^ ((zzco)this).zzbx().currentTimeMillis()).nextLong();
                int v3 = this.zzaed + 1;
                this.zzaed = v3;
                __monitor_exit(v0);
                return v1_1 + (((long)v3));
            label_21:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_21;
            }

            throw v1;
        }

        v0 = this.zzauk;
        __monitor_enter(v0);
        try {
            this.zzauk.compareAndSet(-1, 1);
            __monitor_exit(v0);
            return this.zzauk.getAndIncrement();
        label_34:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_34;
        }

        throw v1;
    }

    final SecureRandom zzmd() {
        ((zzco)this).zzaf();
        if(this.zzauj == null) {
            this.zzauj = new SecureRandom();
        }

        return this.zzauj;
    }

    public final int zzme() {
        if(this.zzaul == null) {
            this.zzaul = Integer.valueOf(GoogleApiAvailabilityLight.getInstance().getApkVersion(((zzco)this).getContext()) / 1000);
        }

        return this.zzaul.intValue();
    }

    final String zzmf() {
        byte[] v0 = new byte[16];
        this.zzmd().nextBytes(v0);
        return String.format(Locale.US, "%032x", new BigInteger(1, v0));
    }

    final boolean zzr(String arg6, String arg7) {
        if(arg7 == null) {
            ((zzco)this).zzgo().zzjd().zzg("Name is required and can\'t be null. Type", arg6);
            return 0;
        }

        if(arg7.length() == 0) {
            ((zzco)this).zzgo().zzjd().zzg("Name is required and can\'t be empty. Type", arg6);
            return 0;
        }

        int v1 = arg7.codePointAt(0);
        if(!Character.isLetter(v1)) {
            ((zzco)this).zzgo().zzjd().zze("Name must start with a letter. Type, name", arg6, arg7);
            return 0;
        }

        int v2 = arg7.length();
        for(v1 = Character.charCount(v1); v1 < v2; v1 += Character.charCount(v3)) {
            int v3 = arg7.codePointAt(v1);
            if(v3 != 95 && !Character.isLetterOrDigit(v3)) {
                ((zzco)this).zzgo().zzjd().zze("Name must consist of letters, digits or _ (underscores). Type, name", arg6, arg7);
                return 0;
            }
        }

        return 1;
    }

    private final boolean zzs(String arg7, String arg8) {
        if(arg8 == null) {
            ((zzco)this).zzgo().zzjd().zzg("Name is required and can\'t be null. Type", arg7);
            return 0;
        }

        if(arg8.length() == 0) {
            ((zzco)this).zzgo().zzjd().zzg("Name is required and can\'t be empty. Type", arg7);
            return 0;
        }

        int v1 = arg8.codePointAt(0);
        int v3 = 95;
        if(!Character.isLetter(v1) && v1 != v3) {
            ((zzco)this).zzgo().zzjd().zze("Name must start with a letter or _ (underscore). Type, name", arg7, arg8);
            return 0;
        }

        int v2 = arg8.length();
        for(v1 = Character.charCount(v1); v1 < v2; v1 += Character.charCount(v4)) {
            int v4 = arg8.codePointAt(v1);
            if(v4 != v3 && !Character.isLetterOrDigit(v4)) {
                ((zzco)this).zzgo().zzjd().zze("Name must consist of letters, digits or _ (underscores). Type, name", arg7, arg8);
                return 0;
            }
        }

        return 1;
    }

    final boolean zzt(String arg3, String arg4) {
        if(TextUtils.isEmpty(((CharSequence)arg3))) {
            if(TextUtils.isEmpty(((CharSequence)arg4))) {
                goto label_26;
            }

            if(zzfk.zzct(arg4)) {
                return 1;
            }

            ((zzco)this).zzgo().zzjd().zzg("Invalid gma_app_id. Analytics disabled.", zzap.zzbv(arg4));
            return 0;
        }
        else if(!zzfk.zzct(arg3)) {
            if(this.zzadj.zzkj()) {
                ((zzco)this).zzgo().zzjd().zzg("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", zzap.zzbv(arg3));
            }

            return 0;
        }

        return 1;
    label_26:
        if(this.zzadj.zzkj()) {
            ((zzco)this).zzgo().zzjd().zzbx("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
        }

        return 0;
    }

    static boolean zzu(String arg0, String arg1) {
        if(arg0 == null && arg1 == null) {
            return 1;
        }

        if(arg0 == null) {
            return 0;
        }

        return arg0.equals(arg1);
    }

    final boolean zzx(String arg3) {
        ((zzco)this).zzaf();
        if(Wrappers.packageManager(((zzco)this).getContext()).checkCallingOrSelfPermission(arg3) == 0) {
            return 1;
        }

        ((zzco)this).zzgo().zzjk().zzg("Permission not granted", arg3);
        return 0;
    }
}

