package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri$Builder;
import android.os.Bundle;
import android.support.v4.f.a;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzgb;
import com.google.android.gms.internal.measurement.zzgd;
import com.google.android.gms.internal.measurement.zzgf;
import com.google.android.gms.internal.measurement.zzgg;
import com.google.android.gms.internal.measurement.zzgh;
import com.google.android.gms.internal.measurement.zzgi;
import com.google.android.gms.internal.measurement.zzgl;
import com.google.android.gms.internal.measurement.zzyx;
import com.google.android.gms.internal.measurement.zzzg;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class zzfa implements zzcq {
    final class zza implements zzs {
        zzgi zzaua;
        List zzaub;
        List zzauc;
        private long zzaud;

        zza(zzfa arg1, zzfb arg2) {
            this(arg1);
        }

        private zza(zzfa arg1) {
            this.zzaty = arg1;
            super();
        }

        private static long zza(zzgf arg4) {
            return arg4.zzawu.longValue() / 1000 / 60 / 60;
        }

        public final boolean zza(long arg7, zzgf arg9) {
            Preconditions.checkNotNull(arg9);
            if(this.zzauc == null) {
                this.zzauc = new ArrayList();
            }

            if(this.zzaub == null) {
                this.zzaub = new ArrayList();
            }

            if(this.zzauc.size() > 0 && zza.zza(this.zzauc.get(0)) != zza.zza(arg9)) {
                return 0;
            }

            long v2 = this.zzaud + (((long)((zzzg)arg9).zzvu()));
            if(v2 >= (((long)Math.max(0, zzaf.zzajl.get().intValue())))) {
                return 0;
            }

            this.zzaud = v2;
            this.zzauc.add(arg9);
            this.zzaub.add(Long.valueOf(arg7));
            if(this.zzauc.size() >= Math.max(1, zzaf.zzajm.get().intValue())) {
                return 0;
            }

            return 1;
        }

        public final void zzb(zzgi arg1) {
            Preconditions.checkNotNull(arg1);
            this.zzaua = arg1;
        }
    }

    private final zzbt zzadj;
    private static volatile zzfa zzatc;
    private zzbn zzatd;
    private zzat zzate;
    private zzq zzatf;
    private zzay zzatg;
    private zzew zzath;
    private zzj zzati;
    private final zzfg zzatj;
    private boolean zzatk;
    @VisibleForTesting private long zzatl;
    private List zzatm;
    private int zzatn;
    private int zzato;
    private boolean zzatp;
    private boolean zzatq;
    private boolean zzatr;
    private FileLock zzats;
    private FileChannel zzatt;
    private List zzatu;
    private List zzatv;
    private long zzatw;
    private boolean zzvz;

    private zzfa(zzff arg2) {
        this(arg2, null);
    }

    private zzfa(zzff arg3, zzbt arg4) {
        super();
        this.zzvz = false;
        Preconditions.checkNotNull(arg3);
        this.zzadj = zzbt.zza(arg3.zzri, null);
        this.zzatw = -1;
        zzfg v4 = new zzfg(this);
        ((zzez)v4).zzq();
        this.zzatj = v4;
        zzat v4_1 = new zzat(this);
        ((zzez)v4_1).zzq();
        this.zzate = v4_1;
        zzbn v4_2 = new zzbn(this);
        ((zzez)v4_2).zzq();
        this.zzatd = v4_2;
        this.zzadj.zzgn().zzc(new zzfb(this, arg3));
    }

    public final Context getContext() {
        return this.zzadj.getContext();
    }

    protected final void start() {
        this.zzadj.zzgn().zzaf();
        this.zzjq().zzif();
        if(this.zzadj.zzgp().zzane.get() == 0) {
            this.zzadj.zzgp().zzane.set(this.zzadj.zzbx().currentTimeMillis());
        }

        this.zzlv();
    }

    public final byte[] zza(zzad arg34, String arg35) {
        // Method was not decompiled
    }

    static void zza(zzfa arg0, zzff arg1) {
        arg0.zza(arg1);
    }

    static zzg zza(zzfa arg0, zzh arg1) {
        return arg0.zzg(arg1);
    }

    @VisibleForTesting private final int zza(FileChannel arg6) {
        int v6_1;
        this.zzaf();
        if(arg6 != null) {
            if(!arg6.isOpen()) {
            }
            else {
                int v1 = 4;
                ByteBuffer v2 = ByteBuffer.allocate(v1);
                long v3 = 0;
                try {
                    arg6.position(v3);
                    v6_1 = arg6.read(v2);
                    if(v6_1 != v1) {
                        if(v6_1 != -1) {
                            this.zzadj.zzgo().zzjg().zzg("Unexpected data length. Bytes read", Integer.valueOf(v6_1));
                        }

                        return 0;
                    }
                    else {
                        v2.flip();
                        return v2.getInt();
                    }

                    goto label_32;
                }
                catch(IOException v6) {
                    this.zzadj.zzgo().zzjd().zzg("Failed to read from channel", v6);
                    v6_1 = 0;
                }

                return v6_1;
            }
        }

    label_32:
        this.zzadj.zzgo().zzjd().zzbx("Bad channel to read from");
        return 0;
    }

    private final zzh zza(Context arg27, String arg28, String arg29, boolean arg30, boolean arg31, boolean arg32, long arg33, String arg35) {
        int v3_2;
        String v1;
        zzfa v0 = this;
        String v2 = arg28;
        String v3 = "Unknown";
        String v4 = "Unknown";
        PackageManager v5 = arg27.getPackageManager();
        zzh v6 = null;
        if(v5 == null) {
            v0.zzadj.zzgo().zzjd().zzbx("PackageManager is null, can not log app install information");
            return v6;
        }

        try {
            v1 = v5.getInstallerPackageName(v2);
        }
        catch(IllegalArgumentException ) {
            v0.zzadj.zzgo().zzjd().zzg("Error retrieving installer package name. appId", zzap.zzbv(arg28));
        }

        if(v1 == null) {
            v1 = "manual_install";
        }
        else if("com.android.vending".equals(v1)) {
            v1 = "";
        }

        String v7 = v1;
        try {
            PackageInfo v1_1 = Wrappers.packageManager(arg27).getPackageInfo(v2, 0);
            if(v1_1 != null) {
                CharSequence v3_1 = Wrappers.packageManager(arg27).getApplicationLabel(v2);
                if(!TextUtils.isEmpty(v3_1)) {
                    v4 = v3_1.toString();
                }

                v4 = v1_1.versionName;
                v3_2 = v1_1.versionCode;
            }
            else {
                goto label_47;
            }

            goto label_50;
        }
        catch(PackageManager$NameNotFoundException ) {
            v0.zzadj.zzgo().zzjd().zze("Error retrieving newly installed package info. appId, appName", zzap.zzbv(arg28), v4);
            return v6;
        }

    label_47:
        v4 = v3;
        v3_2 = -2147483648;
    label_50:
        long v16 = 0;
        v0.zzadj.zzgr();
        long v5_1 = 0;
        long v18 = v0.zzadj.zzgq().zzbe(v2) ? arg33 : v5_1;
        return new zzh(arg28, arg29, v4, ((long)v3_2), v7, v0.zzadj.zzgq().zzhc(), v0.zzadj.zzgm().zzd(arg27, v2), null, arg30, false, "", v16, v18, 0, arg31, arg32, false, arg35);
    }

    private static void zza(zzez arg3) {
        if(arg3 != null) {
            if(arg3.isInitialized()) {
                return;
            }

            String v3 = String.valueOf(arg3.getClass());
            StringBuilder v2 = new StringBuilder(String.valueOf(v3).length() + 27);
            v2.append("Component not initialized: ");
            v2.append(v3);
            throw new IllegalStateException(v2.toString());
        }

        throw new IllegalStateException("Upload Component not created");
    }

    private final void zza(zzff arg4) {
        this.zzadj.zzgn().zzaf();
        zzq v4 = new zzq(this);
        ((zzez)v4).zzq();
        this.zzatf = v4;
        this.zzadj.zzgq().zza(this.zzatd);
        zzj v4_1 = new zzj(this);
        ((zzez)v4_1).zzq();
        this.zzati = v4_1;
        zzew v4_2 = new zzew(this);
        ((zzez)v4_2).zzq();
        this.zzath = v4_2;
        this.zzatg = new zzay(this);
        if(this.zzatn != this.zzato) {
            this.zzadj.zzgo().zzjd().zze("Not all upload components initialized", Integer.valueOf(this.zzatn), Integer.valueOf(this.zzato));
        }

        this.zzvz = true;
    }

    @VisibleForTesting private final boolean zza(int arg7, FileChannel arg8) {
        this.zzaf();
        if(arg8 != null) {
            if(!arg8.isOpen()) {
            }
            else {
                ByteBuffer v1 = ByteBuffer.allocate(4);
                v1.putInt(arg7);
                v1.flip();
                long v2 = 0;
                try {
                    arg8.truncate(v2);
                    arg8.write(v1);
                    arg8.force(true);
                    if(arg8.size() != 4) {
                        this.zzadj.zzgo().zzjd().zzg("Error writing to channel. Bytes written", Long.valueOf(arg8.size()));
                    }
                }
                catch(IOException v7) {
                    this.zzadj.zzgo().zzjd().zzg("Failed to write to channel", v7);
                    return 0;
                }

                return 1;
            }
        }

        this.zzadj.zzgo().zzjd().zzbx("Bad channel to read from");
        return 0;
    }

    private final boolean zza(String arg14, zzad arg15) {
        long v3_1;
        String v0 = arg15.zzaid.getString("currency");
        if("ecommerce_purchase".equals(arg15.name)) {
            double v5 = 1000000;
            double v3 = arg15.zzaid.zzbq("value").doubleValue() * v5;
            if(v3 == 0) {
                v3 = ((double)arg15.zzaid.getLong("value").longValue());
                Double.isNaN(v3);
                v3 *= v5;
            }

            if(v3 <= 9223372036854776000 && v3 >= -9223372036854776000) {
                v3_1 = Math.round(v3);
                goto label_41;
            }

            this.zzadj.zzgo().zzjg().zze("Data lost. Currency value is too big. appId", zzap.zzbv(arg14), Double.valueOf(v3));
            return 0;
        }
        else {
            v3_1 = arg15.zzaid.getLong("value").longValue();
        }

    label_41:
        if(!TextUtils.isEmpty(((CharSequence)v0))) {
            v0 = v0.toUpperCase(Locale.US);
            if(!v0.matches("[A-Z]{3}")) {
                return 1;
            }

            String v1 = String.valueOf("_ltv_");
            v0 = String.valueOf(v0);
            v0 = v0.length() != 0 ? v1.concat(v0) : new String(v1);
            String v9 = v0;
            zzfj v0_1 = this.zzjq().zzi(arg14, v9);
            if(v0_1 != null) {
                if(!(v0_1.value instanceof Long)) {
                }
                else {
                    v0_1 = new zzfj(arg14, arg15.origin, v9, this.zzadj.zzbx().currentTimeMillis(), Long.valueOf(v0_1.value.longValue() + v3_1));
                    goto label_118;
                }
            }

            zzq v0_2 = this.zzjq();
            int v1_1 = this.zzadj.zzgq().zzb(arg14, zzaf.zzakh) - 1;
            Preconditions.checkNotEmpty(arg14);
            ((zzco)v0_2).zzaf();
            ((zzez)v0_2).zzcl();
            try {
                v0_2.getWritableDatabase().execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like \'_ltv_%\' order by set_timestamp desc limit ?,10);", new String[]{arg14, arg14, String.valueOf(v1_1)});
            }
            catch(SQLiteException v1_2) {
                ((zzco)v0_2).zzgo().zzjd().zze("Error pruning currencies. appId", zzap.zzbv(arg14), v1_2);
            }

            v0_1 = new zzfj(arg14, arg15.origin, v9, this.zzadj.zzbx().currentTimeMillis(), Long.valueOf(v3_1));
        label_118:
            if(this.zzjq().zza(v0_1)) {
                return 1;
            }

            this.zzadj.zzgo().zzjd().zzd("Too many unique user properties are set. Ignoring user property. appId", zzap.zzbv(arg14), this.zzadj.zzgl().zzbu(v0_1.name), v0_1.value);
            this.zzadj.zzgm().zza(arg14, 9, null, null, 0);
        }

        return 1;
    }

    private final zzgd[] zza(String arg2, zzgl[] arg3, zzgf[] arg4) {
        Preconditions.checkNotEmpty(arg2);
        return this.zzjp().zza(arg2, arg4, arg3);
    }

    @VisibleForTesting private static zzgg[] zza(zzgg[] arg3, int arg4) {
        zzgg[] v0 = new zzgg[arg3.length - 1];
        if(arg4 > 0) {
            System.arraycopy(arg3, 0, v0, 0, arg4);
        }

        if(arg4 < v0.length) {
            System.arraycopy(arg3, arg4 + 1, v0, arg4, v0.length - arg4);
        }

        return v0;
    }

    @VisibleForTesting private static zzgg[] zza(zzgg[] arg4, int arg5, String arg6) {
        int v1;
        for(v1 = 0; v1 < arg4.length; ++v1) {
            if("_err".equals(arg4[v1].name)) {
                return arg4;
            }
        }

        zzgg[] v1_1 = new zzgg[arg4.length + 2];
        System.arraycopy(arg4, 0, v1_1, 0, arg4.length);
        zzgg v4 = new zzgg();
        v4.name = "_err";
        v4.zzawx = Long.valueOf(((long)arg5));
        zzgg v5 = new zzgg();
        v5.name = "_ev";
        v5.zzamp = arg6;
        v1_1[v1_1.length - 2] = v4;
        v1_1[v1_1.length - 1] = v5;
        return v1_1;
    }

    @VisibleForTesting private static zzgg[] zza(zzgg[] arg2, String arg3) {
        int v0 = 0;
        while(true) {
            if(v0 >= arg2.length) {
                break;
            }
            else if(arg3.equals(arg2[v0].name)) {
            }
            else {
                ++v0;
                continue;
            }

            goto label_11;
        }

        v0 = -1;
    label_11:
        if(v0 < 0) {
            return arg2;
        }

        return zzfa.zza(arg2, v0);
    }

    @VisibleForTesting final void zza(int arg10, Throwable arg11, byte[] arg12, String arg13) {
        zzq v12_1;
        long v5;
        int v4;
        List v2;
        List v1;
        this.zzaf();
        this.zzlr();
        if(arg12 == null) {
            try {
                arg12 = new byte[0];
            label_8:
                v1 = this.zzatu;
                v2 = null;
                this.zzatu = v2;
                v4 = 1;
                if(arg10 != 200) {
                    goto label_14;
                }
                else {
                    goto label_16;
                }
            }
            catch(Throwable v10) {
                goto label_7;
            }
        }

        goto label_8;
    label_14:
        if(arg10 == 204) {
        label_16:
            if(arg11 == null) {
                try {
                    this.zzadj.zzgp().zzane.set(this.zzadj.zzbx().currentTimeMillis());
                    v5 = 0;
                    this.zzadj.zzgp().zzanf.set(v5);
                    this.zzlv();
                    this.zzadj.zzgo().zzjl().zze("Successful upload. Got network response. code, size", Integer.valueOf(arg10), Integer.valueOf(arg12.length));
                    this.zzjq().beginTransaction();
                }
                catch(SQLiteException v10_1) {
                    goto label_97;
                }
                catch(Throwable v10) {
                    goto label_7;
                }

                try {
                    Iterator v10_2 = v1.iterator();
                    while(true) {
                        if(v10_2.hasNext()) {
                            Object v11 = v10_2.next();
                            try {
                                v12_1 = this.zzjq();
                                long v7 = ((Long)v11).longValue();
                                ((zzco)v12_1).zzaf();
                                ((zzez)v12_1).zzcl();
                                SQLiteDatabase v13 = v12_1.getWritableDatabase();
                                String[] v1_1 = new String[]{String.valueOf(v7)};
                            }
                            catch(SQLiteException v12) {
                                goto label_68;
                            }

                            try {
                                if(v13.delete("queue", "rowid=?", v1_1) == 1) {
                                    continue;
                                }
                                else {
                                    throw new SQLiteException("Deleted fewer rows from queue than expected");
                                }

                                goto label_114;
                            }
                            catch(SQLiteException v13_1) {
                                try {
                                    ((zzco)v12_1).zzgo().zzjd().zzg("Failed to delete a bundle in a queue table", v13_1);
                                    throw v13_1;
                                }
                                catch(Throwable v10) {
                                }
                                catch(SQLiteException v12) {
                                    try {
                                    label_68:
                                        if(this.zzatv != null && (this.zzatv.contains(v11))) {
                                            continue;
                                        }

                                        throw v12;
                                    label_75:
                                        this.zzjq().setTransactionSuccessful();
                                    }
                                    catch(Throwable v10) {
                                        goto label_94;
                                    }

                                    try {
                                        this.zzjq().endTransaction();
                                        this.zzatv = v2;
                                        if(!this.zzlo().zzfb() || !this.zzlu()) {
                                            this.zzatw = -1;
                                            this.zzlv();
                                        }
                                        else {
                                            this.zzlt();
                                        }

                                        this.zzatl = v5;
                                        goto label_148;
                                    label_94:
                                        this.zzjq().endTransaction();
                                        throw v10;
                                    }
                                    catch(Throwable v10) {
                                    label_151:
                                        this.zzatq = false;
                                        this.zzlw();
                                        throw v10;
                                    }
                                    catch(SQLiteException v10_1) {
                                        try {
                                        label_97:
                                            this.zzadj.zzgo().zzjd().zzg("Database error while trying to delete uploaded bundles", v10_1);
                                            this.zzatl = this.zzadj.zzbx().elapsedRealtime();
                                            this.zzadj.zzgo().zzjl().zzg("Disable upload, time", Long.valueOf(this.zzatl));
                                            goto label_148;
                                        label_114:
                                            this.zzadj.zzgo().zzjl().zze("Network upload failed. Will retry later. code, error", Integer.valueOf(arg10), arg11);
                                            this.zzadj.zzgp().zzanf.set(this.zzadj.zzbx().currentTimeMillis());
                                            if(arg10 != 503) {
                                                if(arg10 == 429) {
                                                }
                                                else {
                                                    v4 = 0;
                                                }
                                            }

                                            if(v4 != 0) {
                                                this.zzadj.zzgp().zzang.set(this.zzadj.zzbx().currentTimeMillis());
                                            }

                                            if(this.zzadj.zzgq().zzaz(arg13)) {
                                                this.zzjq().zzc(v1);
                                            }

                                            this.zzlv();
                                            goto label_148;
                                        label_7:
                                            goto label_151;
                                        }
                                        catch(Throwable v10) {
                                            goto label_7;
                                        }
                                    }
                                }
                            }
                        }
                        else {
                            goto label_75;
                        }

                        goto label_114;
                    }
                }
                catch(Throwable v10) {
                    goto label_94;
                }
            }
        }

        goto label_114;
    label_148:
        this.zzatq = false;
        this.zzlw();
    }

    private final void zzaf() {
        this.zzadj.zzgn().zzaf();
    }

    final void zzb(zzl arg11, zzh arg12) {
        Object v1_3;
        Object v0_1;
        String v12;
        zzar v11_2;
        Object v5;
        String v4;
        String v3;
        String v2;
        zzar v1_1;
        Preconditions.checkNotNull(arg11);
        Preconditions.checkNotEmpty(arg11.packageName);
        Preconditions.checkNotNull(arg11.origin);
        Preconditions.checkNotNull(arg11.zzahb);
        Preconditions.checkNotEmpty(arg11.zzahb.name);
        this.zzaf();
        this.zzlr();
        if((TextUtils.isEmpty(arg12.zzafx)) && (TextUtils.isEmpty(arg12.zzagk))) {
            return;
        }

        if(!arg12.zzagg) {
            this.zzg(arg12);
            return;
        }

        zzl v0 = new zzl(arg11);
        int v11 = 0;
        v0.active = false;
        this.zzjq().beginTransaction();
        try {
            zzl v1 = this.zzjq().zzj(v0.packageName, v0.zzahb.name);
            if(v1 != null && !v1.origin.equals(v0.origin)) {
                this.zzadj.zzgo().zzjg().zzd("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzadj.zzgl().zzbu(v0.zzahb.name), v0.origin, v1.origin);
            }

            if(v1 != null && (v1.active)) {
                v0.origin = v1.origin;
                v0.creationTimestamp = v1.creationTimestamp;
                v0.triggerTimeout = v1.triggerTimeout;
                v0.triggerEventName = v1.triggerEventName;
                v0.zzahd = v1.zzahd;
                v0.active = v1.active;
                v0.zzahb = new zzfh(v0.zzahb.name, v1.zzahb.zzaue, v0.zzahb.getValue(), v1.zzahb.origin);
            }
            else if(TextUtils.isEmpty(v0.triggerEventName)) {
                v0.zzahb = new zzfh(v0.zzahb.name, v0.creationTimestamp, v0.zzahb.getValue(), v0.zzahb.origin);
                v0.active = true;
                v11 = 1;
            }

            if(v0.active) {
                zzfj v9 = new zzfj(v0.packageName, v0.origin, v0.zzahb.name, v0.zzahb.zzaue, v0.zzahb.getValue());
                if(this.zzjq().zza(v9)) {
                    v1_1 = this.zzadj.zzgo().zzjk();
                    v2 = "User property updated immediately";
                    v3 = v0.packageName;
                    v4 = this.zzadj.zzgl().zzbu(v9.name);
                    v5 = v9.value;
                }
                else {
                    v1_1 = this.zzadj.zzgo().zzjd();
                    v2 = "(2)Too many active user properties, ignoring";
                    Object v3_1 = zzap.zzbv(v0.packageName);
                    v4 = this.zzadj.zzgl().zzbu(v9.name);
                    v5 = v9.value;
                }

                v1_1.zzd(v2, v3, v4, v5);
                if(v11 == 0) {
                    goto label_145;
                }

                if(v0.zzahd == null) {
                    goto label_145;
                }

                this.zzd(new zzad(v0.zzahd, v0.creationTimestamp), arg12);
            }

        label_145:
            if(this.zzjq().zza(v0)) {
                v11_2 = this.zzadj.zzgo().zzjk();
                v12 = "Conditional property added";
                String v1_2 = v0.packageName;
                v2 = this.zzadj.zzgl().zzbu(v0.zzahb.name);
                v0_1 = v0.zzahb.getValue();
            }
            else {
                v11_2 = this.zzadj.zzgo().zzjd();
                v12 = "Too many conditional properties, ignoring";
                v1_3 = zzap.zzbv(v0.packageName);
                v2 = this.zzadj.zzgl().zzbu(v0.zzahb.name);
                v0_1 = v0.zzahb.getValue();
            }

            v11_2.zzd(v12, v1_3, v2, v0_1);
            this.zzjq().setTransactionSuccessful();
        }
        catch(Throwable v11_1) {
            this.zzjq().endTransaction();
            throw v11_1;
        }

        this.zzjq().endTransaction();
    }

    final void zzb(zzfh arg13, zzh arg14) {
        int v11;
        this.zzaf();
        this.zzlr();
        if((TextUtils.isEmpty(arg14.zzafx)) && (TextUtils.isEmpty(arg14.zzagk))) {
            return;
        }

        if(!arg14.zzagg) {
            this.zzg(arg14);
            return;
        }

        if((this.zzadj.zzgq().zze(arg14.packageName, zzaf.zzalj)) && ("_ap".equals(arg13.name))) {
            zzfj v0 = this.zzjq().zzi(arg14.packageName, "_ap");
            if(v0 != null && ("auto".equals(arg13.origin)) && !"auto".equals(v0.origin)) {
                this.zzadj.zzgo().zzjk().zzbx("Not setting lower priority ad personalization property");
                return;
            }
        }

        int v4 = this.zzadj.zzgm().zzcs(arg13.name);
        int v2 = 24;
        if(v4 != 0) {
            this.zzadj.zzgm();
            String v6 = zzfk.zza(arg13.name, v2, true);
            int v7 = arg13.name != null ? arg13.name.length() : 0;
            this.zzadj.zzgm().zza(arg14.packageName, v4, "_ev", v6, v7);
            return;
        }

        int v8 = this.zzadj.zzgm().zzi(arg13.name, arg13.getValue());
        if(v8 != 0) {
            this.zzadj.zzgm();
            String v10 = zzfk.zza(arg13.name, v2, true);
            Object v13 = arg13.getValue();
            if(v13 != null) {
                if(!(v13 instanceof String) && !(v13 instanceof CharSequence)) {
                    goto label_87;
                }

                v11 = String.valueOf(v13).length();
            }
            else {
            label_87:
                v11 = 0;
            }

            this.zzadj.zzgm().zza(arg14.packageName, v8, "_ev", v10, v11);
            return;
        }

        Object v0_1 = this.zzadj.zzgm().zzj(arg13.name, arg13.getValue());
        if(v0_1 == null) {
            return;
        }

        zzfj v1 = new zzfj(arg14.packageName, arg13.origin, arg13.name, arg13.zzaue, v0_1);
        this.zzadj.zzgo().zzjk().zze("Setting user property", this.zzadj.zzgl().zzbu(v1.name), v0_1);
        this.zzjq().beginTransaction();
        try {
            this.zzg(arg14);
            boolean v13_2 = this.zzjq().zza(v1);
            this.zzjq().setTransactionSuccessful();
            if(v13_2) {
                this.zzadj.zzgo().zzjk().zze("User property set", this.zzadj.zzgl().zzbu(v1.name), v1.value);
            }
            else {
                this.zzadj.zzgo().zzjd().zze("Too many unique user properties are set. Ignoring user property", this.zzadj.zzgl().zzbu(v1.name), v1.value);
                this.zzadj.zzgm().zza(arg14.packageName, 9, null, null, 0);
            }
        }
        catch(Throwable v13_1) {
            this.zzjq().endTransaction();
            throw v13_1;
        }

        this.zzjq().endTransaction();
    }

    private final void zzb(zzg arg11) {
        Map v6_1;
        this.zzaf();
        if((TextUtils.isEmpty(arg11.getGmpAppId())) && (!zzn.zzic() || (TextUtils.isEmpty(arg11.zzgw())))) {
            this.zzb(arg11.zzal(), 204, null, null, null);
            return;
        }

        zzn v0 = this.zzadj.zzgq();
        Uri$Builder v1 = new Uri$Builder();
        String v2 = arg11.getGmpAppId();
        if((TextUtils.isEmpty(((CharSequence)v2))) && (zzn.zzic())) {
            v2 = arg11.zzgw();
        }

        Uri$Builder v3 = v1.scheme(zzaf.zzajh.get()).encodedAuthority(zzaf.zzaji.get());
        String v4 = "config/app/";
        v2 = String.valueOf(v2);
        v2 = v2.length() != 0 ? v4.concat(v2) : new String(v4);
        v3.path(v2).appendQueryParameter("app_instance_id", arg11.getAppInstanceId()).appendQueryParameter("platform", "android").appendQueryParameter("gmp_version", String.valueOf(v0.zzhc()));
        String v0_1 = v1.build().toString();
        try {
            URL v4_1 = new URL(v0_1);
            this.zzadj.zzgo().zzjl().zzg("Fetching remote configuration", arg11.zzal());
            zzgb v1_1 = this.zzln().zzcf(arg11.zzal());
            Map v2_1 = null;
            String v3_1 = this.zzln().zzcg(arg11.zzal());
            if(v1_1 == null || (TextUtils.isEmpty(((CharSequence)v3_1)))) {
                v6_1 = v2_1;
            }
            else {
                a v1_2 = new a();
                ((Map)v1_2).put("If-Modified-Since", v3_1);
                a v6 = v1_2;
            }

            this.zzatp = true;
            zzat v2_2 = this.zzlo();
            v3_1 = arg11.zzal();
            zzfd v7 = new zzfd(this);
            ((zzco)v2_2).zzaf();
            ((zzez)v2_2).zzcl();
            Preconditions.checkNotNull(v4_1);
            Preconditions.checkNotNull(v7);
            ((zzco)v2_2).zzgn().zzd(new zzax(v2_2, v3_1, v4_1, null, v6_1, ((zzav)v7)));
            return;
        }
        catch(MalformedURLException ) {
            this.zzadj.zzgo().zzjd().zze("Failed to parse config URL. Not fetching. appId", zzap.zzbv(arg11.zzal()), v0_1);
            return;
        }
    }

    @VisibleForTesting final void zzb(String arg7, int arg8, Throwable arg9, byte[] arg10, Map arg11) {
        zzq v7_1;
        int v2;
        zzg v1;
        this.zzaf();
        this.zzlr();
        Preconditions.checkNotEmpty(arg7);
        if(arg10 == null) {
            try {
                arg10 = new byte[0];
            label_9:
                this.zzadj.zzgo().zzjl().zzg("onConfigFetched. Response size", Integer.valueOf(arg10.length));
                this.zzjq().beginTransaction();
                goto label_18;
            }
            catch(Throwable v7) {
                goto label_8;
            }
        }

        goto label_9;
        try {
        label_18:
            v1 = this.zzjq().zzbl(arg7);
            int v3 = 1;
            int v4 = 304;
            if(arg8 != 200 && arg8 != 204 && arg8 != v4) {
                goto label_30;
            }
            else if(arg9 == null) {
                v2 = 1;
            }
            else {
            label_30:
                v2 = 0;
            }

            if(v1 == null) {
                this.zzadj.zzgo().zzjg().zzg("App does not exist in onConfigFetched. appId", zzap.zzbv(arg7));
                goto label_138;
            }
            else {
                int v5 = 404;
                if(v2 != 0 || arg8 == v5) {
                    byte[] v9 = null;
                    Object v11 = arg11 != null ? arg11.get("Last-Modified") : v9;
                    if(v11 == null || ((List)v11).size() <= 0) {
                        String v11_1 = ((String)v9);
                    }
                    else {
                        v11 = ((List)v11).get(0);
                    }

                    if(arg8 == v5) {
                        goto label_103;
                    }
                    else if(arg8 == v4) {
                        goto label_103;
                    }
                    else if(!this.zzln().zza(arg7, arg10, ((String)v11))) {
                        goto label_98;
                    }

                    goto label_111;
                }
                else {
                    v1.zzz(this.zzadj.zzbx().currentTimeMillis());
                    this.zzjq().zza(v1);
                    this.zzadj.zzgo().zzjl().zze("Fetching config failed. code, error", Integer.valueOf(arg8), arg9);
                    this.zzln().zzch(arg7);
                    this.zzadj.zzgp().zzanf.set(this.zzadj.zzbx().currentTimeMillis());
                    if(arg8 != 503) {
                        if(arg8 == 429) {
                        }
                        else {
                            v3 = 0;
                        }
                    }

                    if(v3 == 0) {
                        goto label_78;
                    }

                    this.zzadj.zzgp().zzang.set(this.zzadj.zzbx().currentTimeMillis());
                }

                goto label_78;
            }
        }
        catch(Throwable v7) {
            goto label_144;
        }

        try {
        label_98:
            v7_1 = this.zzjq();
            goto label_99;
        }
        catch(Throwable v7) {
            goto label_8;
        }

        try {
        label_103:
            if(this.zzln().zzcf(arg7) == null && !this.zzln().zza(arg7, v9, ((String)v9))) {
                goto label_109;
            }

            goto label_111;
        }
        catch(Throwable v7) {
            goto label_144;
        }

        try {
        label_109:
            v7_1 = this.zzjq();
            goto label_99;
        }
        catch(Throwable v7) {
            goto label_8;
        }

        try {
        label_111:
            v1.zzy(this.zzadj.zzbx().currentTimeMillis());
            this.zzjq().zza(v1);
            if(arg8 == v5) {
                this.zzadj.zzgo().zzji().zzg("Config not found. Using empty config. appId", arg7);
            }
            else {
                this.zzadj.zzgo().zzjl().zze("Successfully fetched config. Got network response. code, size", Integer.valueOf(arg8), Integer.valueOf(arg10.length));
            }

            if(this.zzlo().zzfb()) {
                if(!this.zzlu()) {
                    goto label_78;
                }

                this.zzlt();
            }
            else {
            label_78:
                this.zzlv();
            }

        label_138:
            this.zzjq().setTransactionSuccessful();
            goto label_140;
        }
        catch(Throwable v7) {
            try {
            label_144:
                this.zzjq().endTransaction();
                throw v7;
            label_140:
                v7_1 = this.zzjq();
            label_99:
                v7_1.endTransaction();
                goto label_100;
            label_8:
            }
            catch(Throwable v7) {
                goto label_8;
            }
        }

        this.zzatp = false;
        this.zzlw();
        throw v7;
    label_100:
        this.zzatp = false;
        this.zzlw();
    }

    final void zzb(zzez arg1) {
        ++this.zzatn;
    }

    public final Clock zzbx() {
        return this.zzadj.zzbx();
    }

    final void zzc(zzl arg10, zzh arg11) {
        Preconditions.checkNotNull(arg10);
        Preconditions.checkNotEmpty(arg10.packageName);
        Preconditions.checkNotNull(arg10.zzahb);
        Preconditions.checkNotEmpty(arg10.zzahb.name);
        this.zzaf();
        this.zzlr();
        if((TextUtils.isEmpty(arg11.zzafx)) && (TextUtils.isEmpty(arg11.zzagk))) {
            return;
        }

        if(!arg11.zzagg) {
            this.zzg(arg11);
            return;
        }

        this.zzjq().beginTransaction();
        try {
            this.zzg(arg11);
            zzl v0 = this.zzjq().zzj(arg10.packageName, arg10.zzahb.name);
            if(v0 != null) {
                this.zzadj.zzgo().zzjk().zze("Removing conditional user property", arg10.packageName, this.zzadj.zzgl().zzbu(arg10.zzahb.name));
                this.zzjq().zzk(arg10.packageName, arg10.zzahb.name);
                if(v0.active) {
                    this.zzjq().zzh(arg10.packageName, arg10.zzahb.name);
                }

                if(arg10.zzahe == null) {
                    goto label_94;
                }

                Bundle v1 = null;
                if(arg10.zzahe.zzaid != null) {
                    v1 = arg10.zzahe.zzaid.zziv();
                }

                this.zzd(this.zzadj.zzgm().zza(arg10.packageName, arg10.zzahe.name, v1, v0.origin, arg10.zzahe.zzaip, true, false), arg11);
            }
            else {
                this.zzadj.zzgo().zzjg().zze("Conditional user property doesn\'t exist", zzap.zzbv(arg10.packageName), this.zzadj.zzgl().zzbu(arg10.zzahb.name));
            }

        label_94:
            this.zzjq().setTransactionSuccessful();
        }
        catch(Throwable v10) {
            this.zzjq().endTransaction();
            throw v10;
        }

        this.zzjq().endTransaction();
    }

    final void zzc(zzad arg18, zzh arg19) {
        Object v6_1;
        Object v8_1;
        String v5_1;
        zzar v4_4;
        List v3_1;
        Object v9_1;
        String[] v9;
        List v4_1;
        zzfa v1 = this;
        zzad v0 = arg18;
        zzh v2 = arg19;
        Preconditions.checkNotNull(arg19);
        Preconditions.checkNotEmpty(v2.packageName);
        this.zzaf();
        this.zzlr();
        String v3 = v2.packageName;
        long v11 = v0.zzaip;
        if(!this.zzjo().zze(v0, v2)) {
            return;
        }

        if(!v2.zzagg) {
            v1.zzg(v2);
            return;
        }

        this.zzjq().beginTransaction();
        try {
            zzq v4 = this.zzjq();
            Preconditions.checkNotEmpty(v3);
            ((zzco)v4).zzaf();
            ((zzez)v4).zzcl();
            long v5 = 0;
            int v8 = 2;
            if(Long.compare(v11, v5) < 0) {
                ((zzco)v4).zzgo().zzjg().zze("Invalid time querying timed out conditional properties", zzap.zzbv(v3), Long.valueOf(v11));
                v4_1 = Collections.emptyList();
            }
            else {
                v9 = new String[v8];
                v9[0] = v3;
                v9[1] = String.valueOf(v11);
                v4_1 = v4.zzb("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", v9);
            }

            Iterator v4_2 = v4_1.iterator();
            while(v4_2.hasNext()) {
                Object v7 = v4_2.next();
                if(v7 == null) {
                    continue;
                }

                v1.zzadj.zzgo().zzjk().zzd("User property timed out", ((zzl)v7).packageName, v1.zzadj.zzgl().zzbu(((zzl)v7).zzahb.name), ((zzl)v7).zzahb.getValue());
                if(((zzl)v7).zzahc != null) {
                    v1.zzd(new zzad(((zzl)v7).zzahc, v11), v2);
                }

                this.zzjq().zzk(v3, ((zzl)v7).zzahb.name);
            }

            v4 = this.zzjq();
            Preconditions.checkNotEmpty(v3);
            ((zzco)v4).zzaf();
            ((zzez)v4).zzcl();
            if(v11 < v5) {
                ((zzco)v4).zzgo().zzjg().zze("Invalid time querying expired conditional properties", zzap.zzbv(v3), Long.valueOf(v11));
                v4_1 = Collections.emptyList();
            }
            else {
                v9 = new String[v8];
                v9[0] = v3;
                v9[1] = String.valueOf(v11);
                v4_1 = v4.zzb("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", v9);
            }

            ArrayList v7_1 = new ArrayList(v4_1.size());
            v4_2 = v4_1.iterator();
            while(v4_2.hasNext()) {
                v9_1 = v4_2.next();
                if(v9_1 == null) {
                    continue;
                }

                v1.zzadj.zzgo().zzjk().zzd("User property expired", ((zzl)v9_1).packageName, v1.zzadj.zzgl().zzbu(((zzl)v9_1).zzahb.name), ((zzl)v9_1).zzahb.getValue());
                this.zzjq().zzh(v3, ((zzl)v9_1).zzahb.name);
                if(((zzl)v9_1).zzahe != null) {
                    ((List)v7_1).add(((zzl)v9_1).zzahe);
                }

                this.zzjq().zzk(v3, ((zzl)v9_1).zzahb.name);
            }

            int v4_3 = v7_1.size();
            v8 = 0;
            while(v8 < v4_3) {
                v9_1 = v7_1.get(v8);
                ++v8;
                v1.zzd(new zzad(((zzad)v9_1), v11), v2);
            }

            v4 = this.zzjq();
            String v7_2 = v0.name;
            Preconditions.checkNotEmpty(v3);
            Preconditions.checkNotEmpty(v7_2);
            ((zzco)v4).zzaf();
            ((zzez)v4).zzcl();
            if(v11 < v5) {
                ((zzco)v4).zzgo().zzjg().zzd("Invalid time querying triggered conditional properties", zzap.zzbv(v3), ((zzco)v4).zzgl().zzbs(v7_2), Long.valueOf(v11));
                v3_1 = Collections.emptyList();
            }
            else {
                v3_1 = v4.zzb("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{v3, v7_2, String.valueOf(v11)});
            }

            ArrayList v14 = new ArrayList(v3_1.size());
            Iterator v3_2 = v3_1.iterator();
            while(v3_2.hasNext()) {
                Object v15 = v3_2.next();
                if(v15 == null) {
                    continue;
                }

                zzfj v13 = new zzfj(((zzl)v15).packageName, ((zzl)v15).origin, ((zzl)v15).zzahb.name, v11, ((zzl)v15).zzahb.getValue());
                if(this.zzjq().zza(v13)) {
                    v4_4 = v1.zzadj.zzgo().zzjk();
                    v5_1 = "User property triggered";
                    String v6 = ((zzl)v15).packageName;
                    v7_2 = v1.zzadj.zzgl().zzbu(v13.name);
                    v8_1 = v13.value;
                }
                else {
                    v4_4 = v1.zzadj.zzgo().zzjd();
                    v5_1 = "Too many active user properties, ignoring";
                    v6_1 = zzap.zzbv(((zzl)v15).packageName);
                    v7_2 = v1.zzadj.zzgl().zzbu(v13.name);
                    v8_1 = v13.value;
                }

                v4_4.zzd(v5_1, v6_1, v7_2, v8_1);
                if(((zzl)v15).zzahd != null) {
                    ((List)v14).add(((zzl)v15).zzahd);
                }

                ((zzl)v15).zzahb = new zzfh(v13);
                ((zzl)v15).active = true;
                this.zzjq().zza(((zzl)v15));
            }

            this.zzd(arg18, arg19);
            int v0_2 = v14.size();
            int v3_3 = 0;
            while(v3_3 < v0_2) {
                Object v4_5 = v14.get(v3_3);
                ++v3_3;
                v1.zzd(new zzad(((zzad)v4_5), v11), v2);
            }

            this.zzjq().setTransactionSuccessful();
        }
        catch(Throwable v0_1) {
            goto label_248;
        }

        this.zzjq().endTransaction();
        return;
    label_248:
        this.zzjq().endTransaction();
        throw v0_1;
    }

    final void zzc(zzad arg28, String arg29) {
        zzfa v0 = this;
        zzad v1 = arg28;
        String v3 = arg29;
        zzg v15 = this.zzjq().zzbl(v3);
        if(v15 != null) {
            if(TextUtils.isEmpty(v15.zzak())) {
            }
            else {
                Boolean v2 = v0.zzc(v15);
                if(v2 == null) {
                    if(!"_ui".equals(v1.name)) {
                        v0.zzadj.zzgo().zzjg().zzg("Could not find package. appId", zzap.zzbv(arg29));
                    }
                }
                else if(!v2.booleanValue()) {
                    v0.zzadj.zzgo().zzjd().zzg("App version does not match; dropping event. appId", zzap.zzbv(arg29));
                    return;
                }

                v0.zzc(v1, new zzh(arg29, v15.getGmpAppId(), v15.zzak(), v15.zzha(), v15.zzhb(), v15.zzhc(), v15.zzhd(), null, v15.isMeasurementEnabled(), false, v15.getFirebaseInstanceId(), v15.zzhq(), 0, 0, v15.zzhr(), v15.zzhs(), false, v15.zzgw()));
                return;
            }
        }

        v0.zzadj.zzgo().zzjk().zzg("No app data available; dropping event", v3);
    }

    final void zzc(zzfh arg5, zzh arg6) {
        this.zzaf();
        this.zzlr();
        if((TextUtils.isEmpty(arg6.zzafx)) && (TextUtils.isEmpty(arg6.zzagk))) {
            return;
        }

        if(!arg6.zzagg) {
            this.zzg(arg6);
            return;
        }

        if((this.zzadj.zzgq().zze(arg6.packageName, zzaf.zzalj)) && ("_ap".equals(arg5.name))) {
            zzfj v0 = this.zzjq().zzi(arg6.packageName, "_ap");
            if(v0 != null && ("auto".equals(arg5.origin)) && !"auto".equals(v0.origin)) {
                this.zzadj.zzgo().zzjk().zzbx("Not removing higher priority ad personalization property");
                return;
            }
        }

        this.zzadj.zzgo().zzjk().zzg("Removing user property", this.zzadj.zzgl().zzbu(arg5.name));
        this.zzjq().beginTransaction();
        try {
            this.zzg(arg6);
            this.zzjq().zzh(arg6.packageName, arg5.name);
            this.zzjq().setTransactionSuccessful();
            this.zzadj.zzgo().zzjk().zzg("User property removed", this.zzadj.zzgl().zzbu(arg5.name));
        }
        catch(Throwable v5) {
            this.zzjq().endTransaction();
            throw v5;
        }

        this.zzjq().endTransaction();
    }

    private final Boolean zzc(zzg arg8) {
        // Method was not decompiled
    }

    private final zzh zzco(String arg27) {
        Object v2_1;
        String v4;
        zzar v3_1;
        zzfa v0 = this;
        String v2 = arg27;
        zzg v15 = this.zzjq().zzbl(v2);
        zzh v1 = null;
        if(v15 == null || (TextUtils.isEmpty(v15.zzak()))) {
            v3_1 = v0.zzadj.zzgo().zzjk();
            v4 = "No app data available; dropping";
        }
        else {
            Boolean v3 = v0.zzc(v15);
            if(v3 != null && !v3.booleanValue()) {
                v3_1 = v0.zzadj.zzgo().zzjd();
                v4 = "App version does not match; dropping. appId";
                v2_1 = zzap.zzbv(arg27);
                goto label_19;
            }

            return new zzh(arg27, v15.getGmpAppId(), v15.zzak(), v15.zzha(), v15.zzhb(), v15.zzhc(), v15.zzhd(), null, v15.isMeasurementEnabled(), false, v15.getFirebaseInstanceId(), v15.zzhq(), 0, 0, v15.zzhr(), v15.zzhs(), false, v15.zzgw());
        }

    label_19:
        v3_1.zzg(v4, v2_1);
        return v1;
    }

    @VisibleForTesting final void zzd(zzh arg12) {
        if(this.zzatu != null) {
            this.zzatv = new ArrayList();
            this.zzatv.addAll(this.zzatu);
        }

        zzq v0 = this.zzjq();
        String v1 = arg12.packageName;
        Preconditions.checkNotEmpty(v1);
        ((zzco)v0).zzaf();
        ((zzez)v0).zzcl();
        try {
            SQLiteDatabase v2_1 = v0.getWritableDatabase();
            String[] v3 = new String[]{v1};
            int v5 = v2_1.delete("apps", "app_id=?", v3) + v2_1.delete("events", "app_id=?", v3) + v2_1.delete("user_attributes", "app_id=?", v3) + v2_1.delete("conditional_properties", "app_id=?", v3) + v2_1.delete("raw_events", "app_id=?", v3) + v2_1.delete("raw_events_metadata", "app_id=?", v3) + v2_1.delete("queue", "app_id=?", v3) + v2_1.delete("audience_filter_values", "app_id=?", v3) + v2_1.delete("main_event_params", "app_id=?", v3);
            if(v5 <= 0) {
                goto label_67;
            }

            ((zzco)v0).zzgo().zzjl().zze("Reset analytics data. app, records", v1, Integer.valueOf(v5));
        }
        catch(SQLiteException v2) {
            ((zzco)v0).zzgo().zzjd().zze("Error resetting analytics data. appId, error", zzap.zzbv(v1), v2);
        }

    label_67:
        zzh v0_1 = this.zza(this.zzadj.getContext(), arg12.packageName, arg12.zzafx, arg12.zzagg, arg12.zzagi, arg12.zzagj, arg12.zzagx, arg12.zzagk);
        if(!this.zzadj.zzgq().zzbd(arg12.packageName) || (arg12.zzagg)) {
            this.zzf(v0_1);
        }
    }

    private final void zzd(zzad arg26, zzh arg27) {
        // Method was not decompiled
    }

    private final boolean zzd(String arg63, long arg64) {
        Throwable v1_4;
        SQLiteDatabase v2_5;
        zzq v1_3;
        Long v4_6;
        zzg v4_4;
        int v2_3;
        int v1_2;
        long v18_1;
        zza v14_6;
        long v2_1;
        zza v60;
        Object v10_2;
        zzz v45_1;
        zzz v9_6;
        Object v9_5;
        long v43;
        long v7_6;
        zzgi v41;
        zzgf[] v38;
        zzgf v12_2;
        zzgf[] v7_5;
        zzgf[] v5_9;
        HashMap v4_3;
        zzfj v31;
        zzfj v4_2;
        Object v7_4;
        zzar v5_7;
        boolean v37;
        Object[] v6_5;
        zzgg v10_1;
        int v6_3;
        int v11;
        String v6_2;
        int v34;
        long v16_1;
        long v13_3;
        int v9_3;
        boolean v8_5;
        zzgi v3_7;
        int v3_6;
        zzgf v9_2;
        zzyx v8_4;
        long v6_1;
        Cursor v5_2;
        String[] v17;
        String v16;
        zzgi v13_2;
        zzyx v9_1;
        byte[] v9;
        SQLiteDatabase v23;
        SQLiteException v3_5;
        String v3_3;
        Cursor v22;
        String v13_1;
        Cursor v3_2;
        Cursor v8_1;
        String v7;
        String v14;
        String[] v13;
        SQLiteDatabase v15;
        int v10;
        long v8;
        long v5;
        zzq v4;
        zza v2;
        zzfb v3;
        zzfa v1 = this;
        this.zzjq().beginTransaction();
        try {
            v3 = null;
            v2 = new zza(v1, v3);
            v4 = this.zzjq();
            v5 = v1.zzatw;
            Preconditions.checkNotNull(v2);
            ((zzco)v4).zzaf();
            ((zzez)v4).zzcl();
            v8 = -1;
            v10 = 2;
        }
        catch(Throwable v0) {
            goto label_1381;
        }

        try {
            v15 = v4.getWritableDatabase();
            if(TextUtils.isEmpty(((CharSequence)v3))) {
            }
            else {
                goto label_68;
            }
        }
        catch(SQLiteException v0_1) {
            goto label_257;
        }
        catch(Throwable v0) {
            goto label_253;
        }

        if(v5 != v8) {
            try {
                v13 = new String[v10];
                v13[0] = String.valueOf(v5);
                v13[1] = String.valueOf(arg64);
            }
            catch(SQLiteException v0_1) {
                goto label_30;
            }
            catch(Throwable v0) {
                goto label_27;
            }
        }
        else {
            try {
                v13 = new String[]{String.valueOf(arg64)};
            }
            catch(SQLiteException v0_1) {
                goto label_257;
            }
            catch(Throwable v0) {
                goto label_253;
            }
        }

        if(v5 != v8) {
            try {
                v14 = "rowid <= ? and ";
                goto label_41;
            }
            catch(Throwable v0) {
            }
            catch(SQLiteException v0_1) {
            label_30:
                v7 = ((String)v3);
                v8_1 = ((Cursor)v7);
                goto label_32;
                try {
                label_40:
                    v14 = "";
                label_41:
                    StringBuilder v3_1 = new StringBuilder(String.valueOf(v14).length() + 148);
                    v3_1.append("select app_id, metadata_fingerprint from raw_events where ");
                    v3_1.append(v14);
                    v3_1.append("app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;");
                    v3_2 = v15.rawQuery(v3_1.toString(), v13);
                }
                catch(SQLiteException v0_1) {
                    goto label_257;
                }
                catch(Throwable v0) {
                    goto label_253;
                }

                try {
                    if(v3_2.moveToFirst()) {
                        goto label_58;
                    }
                }
                catch(SQLiteException v0_1) {
                    goto label_248;
                }
                catch(Throwable v0) {
                    goto label_27;
                }

                if(v3_2 == null) {
                    goto label_267;
                }

                goto label_56;
                try {
                label_58:
                    v7 = v3_2.getString(0);
                }
                catch(SQLiteException v0_1) {
                    goto label_248;
                }
                catch(Throwable v0) {
                    goto label_27;
                }

                try {
                    v13_1 = v3_2.getString(1);
                    v3_2.close();
                    v22 = v3_2;
                    v3_3 = v7;
                    v7 = v13_1;
                    goto label_103;
                }
                catch(Throwable v0) {
                }
                catch(SQLiteException v0_1) {
                    String v8_2 = v3_3;
                    goto label_32;
                label_68:
                    if(v5 != v8) {
                        try {
                            String[] v3_4 = new String[v10];
                            v3_4[0] = null;
                            v3_4[1] = String.valueOf(v5);
                            goto label_78;
                        label_75:
                            v3_4 = new String[]{null};
                        label_78:
                            v7 = v5 != v8 ? " and rowid <= ?" : "";
                            StringBuilder v14_1 = new StringBuilder(String.valueOf(v7).length() + 84);
                            v14_1.append("select metadata_fingerprint from raw_events where app_id = ?");
                            v14_1.append(v7);
                            v14_1.append(" order by rowid limit 1;");
                            v3_2 = v15.rawQuery(v14_1.toString(), v3_4);
                            goto label_94;
                        }
                        catch(Throwable v0) {
                        label_253:
                            v8_2 = null;
                            goto label_254;
                        }
                        catch(SQLiteException v0_1) {
                        label_257:
                            v3_5 = v0_1;
                            v7 = null;
                            v8_1 = null;
                            goto label_260;
                        }
                    }
                    else {
                        goto label_75;
                    }

                    goto label_78;
                    try {
                    label_94:
                        if(v3_2.moveToFirst()) {
                            goto label_98;
                        }

                        if(v3_2 == null) {
                            goto label_267;
                        }
                    }
                    catch(SQLiteException v0_1) {
                        goto label_248;
                    }
                    catch(Throwable v0) {
                        goto label_27;
                    }

                    try {
                    label_56:
                        v3_2.close();
                        goto label_267;
                    }
                    catch(Throwable v0) {
                        goto label_1381;
                    }

                    try {
                    label_98:
                        v13_1 = v3_2.getString(0);
                        v3_2.close();
                        v22 = v3_2;
                        v7 = v13_1;
                        v3_2 = null;
                        goto label_103;
                    }
                    catch(Throwable v0) {
                    label_27:
                        v8_1 = v3_2;
                        goto label_254;
                    }
                    catch(SQLiteException v0_1) {
                    label_248:
                        v8_1 = v3_2;
                        v7 = null;
                        goto label_32;
                    }
                }
            }
        }
        else {
            goto label_40;
        }

        goto label_41;
        try {
        label_103:
            v13 = new String[]{"metadata"};
            String[] v8_3 = new String[v10];
            v8_3[0] = ((String)v3_2);
            v8_3[1] = v7;
            v23 = v15;
            v8_1 = v15.query("raw_events_metadata", v13, "app_id = ? and metadata_fingerprint = ?", v8_3, null, null, "rowid", "2");
        }
        catch(Throwable v0) {
            v8_1 = v22;
            goto label_254;
        }
        catch(SQLiteException v0_1) {
            v7 = ((String)v3_2);
            v8_1 = v22;
            goto label_32;
        }

        try {
            if(!v8_1.moveToFirst()) {
                ((zzco)v4).zzgo().zzjd().zzg("Raw event metadata record is missing. appId", zzap.zzbv(((String)v3_2)));
                if(v8_1 == null) {
                    goto label_267;
                }

                goto label_129;
            }

            v9 = v8_1.getBlob(0);
            v9_1 = zzyx.zzj(v9, 0, v9.length);
            v13_2 = new zzgi();
        }
        catch(SQLiteException v0_1) {
            goto label_237;
        }
        catch(Throwable v0) {
            goto label_1371;
        }

        try {
            ((zzzg)v13_2).zza(v9_1);
            goto label_137;
        }
        catch(Throwable v0) {
        }
        catch(SQLiteException v0_1) {
        }
        catch(IOException v0_2) {
            IOException v5_1 = v0_2;
            try {
                ((zzco)v4).zzgo().zzjd().zze("Data loss. Failed to merge raw event metadata. appId", zzap.zzbv(((String)v3_2)), v5_1);
                if(v8_1 == null) {
                    goto label_267;
                }
            }
            catch(SQLiteException v0_1) {
                goto label_237;
            }
            catch(Throwable v0) {
                goto label_1371;
            }

            goto label_129;
            try {
            label_137:
                if(v8_1.moveToNext()) {
                    ((zzco)v4).zzgo().zzjg().zzg("Get multiple raw event metadata records, expected one. appId", zzap.zzbv(((String)v3_2)));
                }

                v8_1.close();
                ((zzs)v2).zzb(v13_2);
                if(v5 != -1) {
                    String[] v14_2 = new String[3];
                    v14_2[0] = ((String)v3_2);
                    v14_2[1] = v7;
                    v14_2[v10] = String.valueOf(v5);
                    v16 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?";
                    v17 = v14_2;
                }
                else {
                    String[] v6 = new String[v10];
                    v6[0] = ((String)v3_2);
                    v6[1] = v7;
                    v16 = "app_id = ? and metadata_fingerprint = ?";
                    v17 = v6;
                }

                String[] v15_1 = new String[4];
                v15_1[0] = "rowid";
                v15_1[1] = "name";
                v15_1[v10] = "timestamp";
                v15_1[3] = "data";
                v5_2 = v23.query("raw_events", v15_1, v16, v17, null, null, "rowid", null);
                goto label_182;
            }
            catch(Throwable v0) {
            }
            catch(SQLiteException v0_1) {
            label_237:
                v7 = ((String)v3_2);
                goto label_32;
                try {
                label_182:
                    if(!v5_2.moveToFirst()) {
                        ((zzco)v4).zzgo().zzjg().zzg("Raw event data disappeared while in transaction. appId", zzap.zzbv(((String)v3_2)));
                        if(v5_2 == null) {
                            goto label_267;
                        }

                        goto label_190;
                    }

                    do {
                    label_192:
                        v6_1 = v5_2.getLong(0);
                        v9 = v5_2.getBlob(3);
                        v8_4 = zzyx.zzj(v9, 0, v9.length);
                        v9_2 = new zzgf();
                        break;
                    }
                    while(true);
                }
                catch(SQLiteException v0_1) {
                    goto label_224;
                }
                catch(Throwable v0) {
                    goto label_221;
                }

                try {
                    ((zzzg)v9_2).zza(v8_4);
                    goto label_200;
                }
                catch(Throwable v0) {
                }
                catch(SQLiteException v0_1) {
                label_224:
                    v7 = ((String)v3_2);
                    v8_1 = v5_2;
                label_32:
                    v3_5 = v0_1;
                    try {
                    label_260:
                        ((zzco)v4).zzgo().zzjd().zze("Data loss. Error selecting raw event. appId", zzap.zzbv(v7), v3_5);
                        if((((String)v8_1)) == null) {
                            goto label_267;
                        }
                    }
                    catch(Throwable v0) {
                    label_1371:
                        goto label_254;
                    }

                    try {
                    label_129:
                        v8_1.close();
                    label_267:
                        v3_6 = v2.zzauc == null || (v2.zzauc.isEmpty()) ? 1 : 0;
                        if(v3_6 != 0) {
                            goto label_1364;
                        }

                        v3_7 = v2.zzaua;
                        v3_7.zzaxb = new zzgf[v2.zzauc.size()];
                        boolean v4_1 = v1.zzadj.zzgq().zzax(v3_7.zztt);
                        int v7_1 = 0;
                        v8_5 = false;
                        v9_3 = 0;
                        v13_3 = 0;
                        while(true) {
                            v16_1 = 1;
                            if(v7_1 >= v2.zzauc.size()) {
                                break;
                            }

                            Object v15_2 = v2.zzauc.get(v7_1);
                            if(this.zzln().zzo(v2.zzaua.zztt, ((zzgf)v15_2).name)) {
                                v1.zzadj.zzgo().zzjg().zze("Dropping blacklisted raw event. appId", zzap.zzbv(v2.zzaua.zztt), v1.zzadj.zzgl().zzbs(((zzgf)v15_2).name));
                                int v5_3 = (this.zzln().zzck(v2.zzaua.zztt)) || (this.zzln().zzcl(v2.zzaua.zztt)) ? 1 : 0;
                                if(v5_3 == 0 && !"_err".equals(((zzgf)v15_2).name)) {
                                    v1.zzadj.zzgm().zza(v2.zzaua.zztt, 11, "_ev", ((zzgf)v15_2).name, 0);
                                }

                                v34 = v7_1;
                            }
                            else {
                                boolean v5_4 = this.zzln().zzp(v2.zzaua.zztt, ((zzgf)v15_2).name);
                                if(!v5_4) {
                                    this.zzjo();
                                    v6_2 = ((zzgf)v15_2).name;
                                    Preconditions.checkNotEmpty(v6_2);
                                    v11 = v6_2.hashCode();
                                    if(v11 != 94660) {
                                        if(v11 != 95025) {
                                            if(v11 != 95027) {
                                                goto label_379;
                                            }
                                            else if(v6_2.equals("_ui")) {
                                                v6_3 = 1;
                                            }
                                            else {
                                                goto label_379;
                                            }
                                        }
                                        else if(v6_2.equals("_ug")) {
                                            v6_3 = 2;
                                        }
                                        else {
                                            goto label_379;
                                        }
                                    }
                                    else if(v6_2.equals("_in")) {
                                        v6_3 = 0;
                                    }
                                    else {
                                    label_379:
                                        v6_3 = -1;
                                    }

                                    switch(v6_3) {
                                        case 0: 
                                        case 1: 
                                        case 2: {
                                            v6_3 = 1;
                                            break;
                                        }
                                        default: {
                                            v6_3 = 0;
                                            break;
                                        }
                                    }

                                    if(v6_3 != 0) {
                                        goto label_389;
                                    }

                                    v34 = v7_1;
                                    goto label_387;
                                }
                                else {
                                label_389:
                                    if(((zzgf)v15_2).zzawt == null) {
                                        ((zzgf)v15_2).zzawt = new zzgg[0];
                                    }

                                    zzgg[] v6_4 = ((zzgf)v15_2).zzawt;
                                    v11 = v6_4.length;
                                    int v12 = 0;
                                    int v18 = 0;
                                    int v19 = 0;
                                    while(v12 < v11) {
                                        v10_1 = v6_4[v12];
                                        zzgg[] v25 = v6_4;
                                        int v26 = v11;
                                        if("_c".equals(v10_1.name)) {
                                            v10_1.zzawx = Long.valueOf(v16_1);
                                            v18 = 1;
                                        }
                                        else if("_r".equals(v10_1.name)) {
                                            v10_1.zzawx = Long.valueOf(v16_1);
                                            v19 = 1;
                                        }

                                        ++v12;
                                        v6_4 = v25;
                                        v11 = v26;
                                    }

                                    if(v18 == 0 && (v5_4)) {
                                        v1.zzadj.zzgo().zzjl().zzg("Marking event as conversion", v1.zzadj.zzgl().zzbs(((zzgf)v15_2).name));
                                        v6_5 = Arrays.copyOf(((zzgf)v15_2).zzawt, ((zzgf)v15_2).zzawt.length + 1);
                                        v10_1 = new zzgg();
                                        v10_1.name = "_c";
                                        v10_1.zzawx = Long.valueOf(v16_1);
                                        v6_5[v6_5.length - 1] = v10_1;
                                        ((zzgf)v15_2).zzawt = ((zzgg[])v6_5);
                                    }

                                    if(v19 == 0) {
                                        v1.zzadj.zzgo().zzjl().zzg("Marking event as real-time", v1.zzadj.zzgl().zzbs(((zzgf)v15_2).name));
                                        v6_5 = Arrays.copyOf(((zzgf)v15_2).zzawt, ((zzgf)v15_2).zzawt.length + 1);
                                        v10_1 = new zzgg();
                                        v10_1.name = "_r";
                                        v10_1.zzawx = Long.valueOf(v16_1);
                                        v6_5[v6_5.length - 1] = v10_1;
                                        ((zzgf)v15_2).zzawt = ((zzgg[])v6_5);
                                    }

                                    v34 = v7_1;
                                    if(this.zzjq().zza(this.zzls(), v2.zzaua.zztt, false, false, false, false, true).zzahu > (((long)v1.zzadj.zzgq().zzat(v2.zzaua.zztt)))) {
                                        v6_3 = 0;
                                        goto label_498;
                                    }
                                    else {
                                        v8_5 = true;
                                        goto label_528;
                                    label_498:
                                        while(v6_3 < ((zzgf)v15_2).zzawt.length) {
                                            if("_r".equals(((zzgf)v15_2).zzawt[v6_3].name)) {
                                                zzgg[] v7_2 = new zzgg[((zzgf)v15_2).zzawt.length - 1];
                                                if(v6_3 > 0) {
                                                    System.arraycopy(((zzgf)v15_2).zzawt, 0, v7_2, 0, v6_3);
                                                }

                                                if(v6_3 < v7_2.length) {
                                                    System.arraycopy(((zzgf)v15_2).zzawt, v6_3 + 1, v7_2, v6_3, v7_2.length - v6_3);
                                                }

                                                ((zzgf)v15_2).zzawt = v7_2;
                                            }
                                            else {
                                                ++v6_3;
                                                continue;
                                            }

                                            break;
                                        }
                                    }

                                label_528:
                                    if((zzfk.zzcq(((zzgf)v15_2).name)) && (v5_4) && this.zzjq().zza(this.zzls(), v2.zzaua.zztt, false, false, true, false, false).zzahs > (((long)v1.zzadj.zzgq().zzb(v2.zzaua.zztt, zzaf.zzajq)))) {
                                        v1.zzadj.zzgo().zzjg().zzg("Too many conversions. Not logging as conversion. appId", zzap.zzbv(v2.zzaua.zztt));
                                        v6_4 = ((zzgf)v15_2).zzawt;
                                        v7_1 = v6_4.length;
                                        v10 = 0;
                                        v11 = 0;
                                        zzgg v12_1 = null;
                                        while(v10 < v7_1) {
                                            int v35 = v7_1;
                                            zzgg v7_3 = v6_4[v10];
                                            zzgg[] v36 = v6_4;
                                            v37 = v8_5;
                                            if("_c".equals(v7_3.name)) {
                                                v12_1 = v7_3;
                                            }
                                            else if("_err".equals(v7_3.name)) {
                                                v11 = 1;
                                            }

                                            ++v10;
                                            v7_1 = v35;
                                            v6_4 = v36;
                                            v8_5 = v37;
                                        }

                                        v37 = v8_5;
                                        if(v11 != 0 && v12_1 != null) {
                                            ((zzgf)v15_2).zzawt = ArrayUtils.removeAll(((zzgf)v15_2).zzawt, new zzgg[]{v12_1});
                                            goto label_612;
                                        }

                                        if(v12_1 != null) {
                                            v12_1.name = "_err";
                                            v12_1.zzawx = Long.valueOf(10);
                                            goto label_612;
                                        }

                                        v1.zzadj.zzgo().zzjd().zzg("Did not find conversion parameter. appId", zzap.zzbv(v2.zzaua.zztt));
                                        goto label_612;
                                    }

                                label_387:
                                    v37 = v8_5;
                                }

                            label_612:
                                if(!v1.zzadj.zzgq().zzbf(v2.zzaua.zztt) || !v5_4) {
                                }
                                else {
                                    zzgg[] v5_5 = ((zzgf)v15_2).zzawt;
                                    v6_3 = 0;
                                    v7_1 = -1;
                                    int v8_6 = -1;
                                    while(v6_3 < v5_5.length) {
                                        if("value".equals(v5_5[v6_3].name)) {
                                            v7_1 = v6_3;
                                        }
                                        else if("currency".equals(v5_5[v6_3].name)) {
                                            v8_6 = v6_3;
                                        }

                                        ++v6_3;
                                    }

                                    if(v7_1 != -1) {
                                        if(v5_5[v7_1].zzawx == null && v5_5[v7_1].zzauh == null) {
                                            v1.zzadj.zzgo().zzji().zzbx("Value must be specified with a numeric type.");
                                            v5_5 = zzfa.zza(zzfa.zza(zzfa.zza(v5_5, v7_1), "_c"), 18, "value");
                                            goto label_659;
                                        }

                                        if(v8_6 == -1) {
                                            v6_3 = 1;
                                        }
                                        else {
                                            v6_2 = v5_5[v8_6].zzamp;
                                            if(v6_2 == null) {
                                            }
                                            else if(v6_2.length() != 3) {
                                            }
                                            else {
                                                v8_6 = 0;
                                                goto label_674;
                                            }

                                            goto label_686;
                                        }

                                        goto label_687;
                                    }
                                    else {
                                    label_659:
                                        goto label_699;
                                        while(true) {
                                        label_674:
                                            if(v8_6 < v6_2.length()) {
                                                v11 = v6_2.codePointAt(v8_6);
                                                if(!Character.isLetter(v11)) {
                                                }
                                                else {
                                                    v8_6 += Character.charCount(v11);
                                                    continue;
                                                }
                                            }
                                            else {
                                                break;
                                            }

                                            goto label_686;
                                        }

                                        v6_3 = 0;
                                        goto label_687;
                                    label_686:
                                        v6_3 = 1;
                                    label_687:
                                        if(v6_3 == 0) {
                                            goto label_699;
                                        }

                                        v1.zzadj.zzgo().zzji().zzbx("Value parameter discarded. You must also supply a 3-letter ISO_4217 currency code in the currency parameter.");
                                        v5_5 = zzfa.zza(zzfa.zza(zzfa.zza(v5_5, v7_1), "_c"), 19, "currency");
                                    }

                                label_699:
                                    ((zzgf)v15_2).zzawt = v5_5;
                                }

                                if((v4_1) && ("_e".equals(((zzgf)v15_2).name))) {
                                    if(((zzgf)v15_2).zzawt == null || ((zzgf)v15_2).zzawt.length == 0) {
                                        v5_7 = v1.zzadj.zzgo().zzjg();
                                        v6_2 = "Engagement event does not contain any parameters. appId";
                                        v7_4 = zzap.zzbv(v2.zzaua.zztt);
                                    }
                                    else {
                                        this.zzjo();
                                        Object v5_6 = zzfg.zzb(((zzgf)v15_2), "_et");
                                        if(v5_6 == null) {
                                            v5_7 = v1.zzadj.zzgo().zzjg();
                                            v6_2 = "Engagement event does not include duration. appId";
                                            v7_4 = zzap.zzbv(v2.zzaua.zztt);
                                        }
                                        else {
                                            v13_3 += ((Long)v5_6).longValue();
                                            goto label_738;
                                        }
                                    }

                                    v5_7.zzg(v6_2, v7_4);
                                }

                            label_738:
                                v3_7.zzaxb[v9_3] = v15_2;
                                ++v9_3;
                                v8_5 = v37;
                            }

                            v7_1 = v34 + 1;
                        }

                        if(v9_3 < v2.zzauc.size()) {
                            v3_7.zzaxb = Arrays.copyOf(v3_7.zzaxb, v9_3);
                        }

                        if(v4_1) {
                            v4_2 = this.zzjq().zzi(v3_7.zztt, "_lte");
                            if(v4_2 == null || v4_2.value == null) {
                                v31 = new zzfj(v3_7.zztt, "auto", "_lte", v1.zzadj.zzbx().currentTimeMillis(), Long.valueOf(v13_3));
                                goto label_792;
                            }
                            else {
                                v4_2 = new zzfj(v3_7.zztt, "auto", "_lte", v1.zzadj.zzbx().currentTimeMillis(), Long.valueOf(v4_2.value.longValue() + v13_3));
                            }

                            goto label_793;
                        }

                        goto label_846;
                    }
                    catch(Throwable v0) {
                        goto label_1381;
                    }

                label_792:
                    v4_2 = v31;
                    try {
                    label_793:
                        zzgl v5_8 = new zzgl();
                        v5_8.name = "_lte";
                        v5_8.zzayl = Long.valueOf(v1.zzadj.zzbx().currentTimeMillis());
                        v5_8.zzawx = v4_2.value;
                        v6_3 = 0;
                        while(true) {
                            if(v6_3 >= v3_7.zzaxc.length) {
                                break;
                            }
                            else if("_lte".equals(v3_7.zzaxc[v6_3].name)) {
                                v3_7.zzaxc[v6_3] = v5_8;
                                v6_3 = 1;
                            }
                            else {
                                ++v6_3;
                                continue;
                            }

                            goto label_821;
                        }

                        v6_3 = 0;
                    label_821:
                        if(v6_3 == 0) {
                            v3_7.zzaxc = Arrays.copyOf(v3_7.zzaxc, v3_7.zzaxc.length + 1);
                            v3_7.zzaxc[v2.zzaua.zzaxc.length - 1] = v5_8;
                        }

                        if(v13_3 > 0) {
                            this.zzjq().zza(v4_2);
                            v1.zzadj.zzgo().zzjk().zzg("Updated lifetime engagement user property with value. Value", v4_2.value);
                        }

                    label_846:
                        v3_7.zzaxt = v1.zza(v3_7.zztt, v3_7.zzaxc, v3_7.zzaxb);
                        if(v1.zzadj.zzgq().zzaw(v2.zzaua.zztt)) {
                        }
                        else {
                            goto label_1184;
                        }
                    }
                    catch(Throwable v0) {
                        goto label_1381;
                    }

                    try {
                        v4_3 = new HashMap();
                        v5_9 = new zzgf[v3_7.zzaxb.length];
                        SecureRandom v6_6 = v1.zzadj.zzgm().zzmd();
                        v7_5 = v3_7.zzaxb;
                        v9_3 = v7_5.length;
                        v10 = 0;
                        v11 = 0;
                        goto label_869;
                    }
                    catch(Throwable v0) {
                        goto label_1181;
                    }

                label_1184:
                    zza v61 = v2;
                    zzgi v1_1 = v3_7;
                    boolean v39 = v8_5;
                    goto label_1187;
                    try {
                        while(true) {
                        label_869:
                            if(v10 < v9_3) {
                                v12_2 = v7_5[v10];
                                if(v12_2.name.equals("_ep")) {
                                    break;
                                }
                                else {
                                    goto label_923;
                                }
                            }
                            else {
                                goto label_1163;
                            }

                            goto label_1187;
                        }
                    }
                    catch(Throwable v0) {
                        goto label_1181;
                    }

                    try {
                        this.zzjo();
                        Object v13_4 = zzfg.zzb(v12_2, "_en");
                        Object v14_3 = ((Map)v4_3).get(v13_4);
                        if(v14_3 == null) {
                            zzz v14_4 = this.zzjq().zzg(v2.zzaua.zztt, ((String)v13_4));
                            ((Map)v4_3).put(v13_4, v14_4);
                        }

                        if(((zzz)v14_3).zzaij == null) {
                            if(((zzz)v14_3).zzaik.longValue() > v16_1) {
                                this.zzjo();
                                v38 = v7_5;
                                v12_2.zzawt = zzfg.zza(v12_2.zzawt, "_sr", ((zzz)v14_3).zzaik);
                            }
                            else {
                                v38 = v7_5;
                            }

                            if(((zzz)v14_3).zzail != null && (((zzz)v14_3).zzail.booleanValue())) {
                                this.zzjo();
                                v12_2.zzawt = zzfg.zza(v12_2.zzawt, "_efs", Long.valueOf(v16_1));
                            }

                            v5_9[v11] = v12_2;
                            v61 = v2;
                            v41 = v3_7;
                            ++v11;
                        }
                        else {
                            goto label_919;
                        }

                        goto label_915;
                    }
                    catch(Throwable v0) {
                        goto label_1381;
                    }

                label_919:
                    v38 = v7_5;
                    v61 = v2;
                    v41 = v3_7;
                label_915:
                    v39 = v8_5;
                    int v40 = v9_3;
                    int v42 = v10;
                    goto label_1155;
                label_923:
                    v38 = v7_5;
                    try {
                        v13_3 = this.zzln().zzcj(v2.zzaua.zztt);
                        v1.zzadj.zzgm();
                        v39 = v8_5;
                        v7_6 = zzfk.zzc(v12_2.zzawu.longValue(), v13_3);
                        String v15_3 = "_dbg";
                        v40 = v9_3;
                        Long v9_4 = Long.valueOf(v16_1);
                        if(!TextUtils.isEmpty(((CharSequence)v15_3))) {
                        }
                        else {
                            goto label_973;
                        }
                    }
                    catch(Throwable v0) {
                        goto label_1181;
                    }

                    if(v9_4 == null) {
                        goto label_973;
                    }
                    else {
                        v41 = v3_7;
                        try {
                            zzgg[] v3_8 = v12_2.zzawt;
                            v42 = v10;
                            v10 = v3_8.length;
                            v43 = v13_3;
                            int v13_5 = 0;
                            while(true) {
                                if(v13_5 < v10) {
                                    zzgg v14_5 = v3_8[v13_5];
                                    zzgg[] v45 = v3_8;
                                    if(v15_3.equals(v14_5.name)) {
                                        if((!(v9_4 instanceof Long) || !v9_4.equals(v14_5.zzawx)) && (!(v9_4 instanceof String) || !v9_4.equals(v14_5.zzamp))) {
                                            if(!(v9_4 instanceof Double)) {
                                                goto label_976;
                                            }

                                            if(!v9_4.equals(v14_5.zzauh)) {
                                                goto label_976;
                                            }
                                        }

                                        v3_6 = 1;
                                    }
                                    else {
                                        ++v13_5;
                                        v3_8 = v45;
                                        continue;
                                    }
                                }
                                else {
                                    goto label_976;
                                }

                                goto label_977;
                            }

                        label_973:
                            v41 = v3_7;
                            v42 = v10;
                            v43 = v13_3;
                        label_976:
                            v3_6 = 0;
                        label_977:
                            v3_6 = v3_6 == 0 ? this.zzln().zzq(v2.zzaua.zztt, v12_2.name) : 1;
                            if(v3_6 <= 0) {
                                v1.zzadj.zzgo().zzjg().zze("Sample rate must be positive. event, rate", v12_2.name, Integer.valueOf(v3_6));
                                v3_6 = v11 + 1;
                                v5_9[v11] = v12_2;
                            }
                            else {
                                goto label_998;
                            }

                            goto label_995;
                        }
                        catch(Throwable v0) {
                            goto label_1381;
                        }
                    }

                    goto label_977;
                    try {
                    label_998:
                        v9_5 = ((Map)v4_3).get(v12_2.name);
                        if(v9_5 == null) {
                            goto label_1001;
                        }

                        goto label_1033;
                    }
                    catch(Throwable v0) {
                        goto label_1181;
                    }

                    try {
                    label_1001:
                        v9_6 = this.zzjq().zzg(v2.zzaua.zztt, v12_2.name);
                        if(v9_6 == null) {
                            v1.zzadj.zzgo().zzjg().zze("Event being bundled has no eventAggregate. appId, eventName", v2.zzaua.zztt, v12_2.name);
                            v45_1 = new zzz(v2.zzaua.zztt, v12_2.name, 1, 1, v12_2.zzawu.longValue(), 0, null, null, null, null);
                            goto label_1032;
                        }

                        goto label_1033;
                    }
                    catch(Throwable v0) {
                        goto label_1381;
                    }

                label_1032:
                    v9_6 = v45_1;
                    try {
                    label_1033:
                        this.zzjo();
                        v10_2 = zzfg.zzb(v12_2, "_eid");
                        boolean v13_6 = v10_2 != null ? true : false;
                        Boolean v13_7 = Boolean.valueOf(v13_6);
                        if(v3_6 != 1) {
                            goto label_1058;
                        }
                    }
                    catch(Throwable v0) {
                        goto label_1181;
                    }

                    v3_6 = v11 + 1;
                    try {
                        v5_9[v11] = v12_2;
                        if(!v13_7.booleanValue()) {
                            goto label_995;
                        }

                        if(((zzz)v9_5).zzaij == null && ((zzz)v9_5).zzaik == null && ((zzz)v9_5).zzail == null) {
                            goto label_995;
                        }

                        ((Map)v4_3).put(v12_2.name, ((zzz)v9_5).zza(null, null, null));
                    }
                    catch(Throwable v0) {
                        goto label_1381;
                    }

                label_995:
                    v61 = v2;
                    v11 = v3_6;
                    goto label_1155;
                    try {
                    label_1058:
                        if(v6_6.nextInt(v3_6) != 0) {
                            goto label_1083;
                        }
                    }
                    catch(Throwable v0) {
                        goto label_1181;
                    }

                    try {
                        this.zzjo();
                        v60 = v2;
                        v2_1 = ((long)v3_6);
                        v12_2.zzawt = zzfg.zza(v12_2.zzawt, "_sr", Long.valueOf(v2_1));
                        v10 = v11 + 1;
                        v5_9[v11] = v12_2;
                        if(v13_7.booleanValue()) {
                            v9_6 = ((zzz)v9_5).zza(null, Long.valueOf(v2_1), null);
                        }

                        ((Map)v4_3).put(v12_2.name, ((zzz)v9_5).zza(v12_2.zzawu.longValue(), v7_6));
                        v11 = v10;
                        v61 = v60;
                        goto label_1155;
                    }
                    catch(Throwable v0) {
                        goto label_1381;
                    }

                label_1083:
                    v60 = v2;
                    try {
                        v14_6 = v60;
                        if(!v1.zzadj.zzgq().zzbh(v14_6.zzaua.zztt)) {
                            goto label_1109;
                        }
                        else if(((zzz)v9_5).zzaii != null) {
                        }
                        else {
                            goto label_1097;
                        }
                    }
                    catch(Throwable v0) {
                        goto label_1181;
                    }

                    try {
                        v18_1 = ((zzz)v9_5).zzaii.longValue();
                        v61 = v14_6;
                        goto label_1104;
                    }
                    catch(Throwable v0) {
                        goto label_1381;
                    }

                    try {
                    label_1097:
                        v1.zzadj.zzgm();
                        v61 = v14_6;
                        v18_1 = zzfk.zzc(v12_2.zzawv.longValue(), v43);
                    label_1104:
                        if(v18_1 == v7_6) {
                            goto label_1107;
                        }

                        goto label_1105;
                    label_1109:
                        v61 = v14_6;
                        if(Math.abs(v12_2.zzawu.longValue() - ((zzz)v9_5).zzaih) >= 86400000) {
                        label_1105:
                            v1_2 = 1;
                        }
                        else {
                        label_1107:
                            v1_2 = 0;
                        }

                        if(v1_2 != 0) {
                            this.zzjo();
                            v12_2.zzawt = zzfg.zza(v12_2.zzawt, "_efs", Long.valueOf(v16_1));
                            this.zzjo();
                            long v14_7 = ((long)v3_6);
                            v12_2.zzawt = zzfg.zza(v12_2.zzawt, "_sr", Long.valueOf(v14_7));
                            v1_2 = v11 + 1;
                            v5_9[v11] = v12_2;
                            if(v13_7.booleanValue()) {
                                v9_6 = ((zzz)v9_5).zza(null, Long.valueOf(v14_7), Boolean.valueOf(true));
                            }

                            ((Map)v4_3).put(v12_2.name, ((zzz)v9_5).zza(v12_2.zzawu.longValue(), v7_6));
                            v11 = v1_2;
                            goto label_1155;
                        }

                        if(v13_7.booleanValue()) {
                            ((Map)v4_3).put(v12_2.name, ((zzz)v9_5).zza(((Long)v10_2), null, null));
                        }

                    label_1155:
                        v10 = v42 + 1;
                        v7_5 = v38;
                        v8_5 = v39;
                        v9_3 = v40;
                        v3_7 = v41;
                        v2 = v61;
                        v1 = this;
                        goto label_869;
                    label_1163:
                        v61 = v2;
                        v1_1 = v3_7;
                        v39 = v8_5;
                        if(v11 < v1_1.zzaxb.length) {
                            v1_1.zzaxb = Arrays.copyOf(((Object[])v5_9), v11);
                        }

                        Iterator v2_2 = ((Map)v4_3).entrySet().iterator();
                        while(v2_2.hasNext()) {
                            this.zzjq().zza(v2_2.next().getValue());
                        }
                    }
                    catch(Throwable v0) {
                        goto label_1181;
                    }

                label_1187:
                    v2_1 = 9223372036854775807L;
                    try {
                        v1_1.zzaxe = Long.valueOf(v2_1);
                        v1_1.zzaxf = Long.valueOf(-9223372036854775808L);
                        v2_3 = 0;
                    label_1194:
                        while(v2_3 >= v1_1.zzaxb.length) {
                            goto label_1215;
                        }
                    }
                    catch(Throwable v0) {
                        goto label_1362;
                    }

                    try {
                        zzgf v3_9 = v1_1.zzaxb[v2_3];
                        if(v3_9.zzawu.longValue() < v1_1.zzaxe.longValue()) {
                            v1_1.zzaxe = v3_9.zzawu;
                        }

                        if(v3_9.zzawu.longValue() > v1_1.zzaxf.longValue()) {
                            v1_1.zzaxf = v3_9.zzawu;
                        }
                    }
                    catch(Throwable v0) {
                        goto label_1181;
                    }

                    ++v2_3;
                    goto label_1194;
                label_1215:
                    v2 = v61;
                    try {
                        v3_3 = v2.zzaua.zztt;
                        v4_4 = this.zzjq().zzbl(v3_3);
                        if(v4_4 == null) {
                        }
                        else {
                            goto label_1231;
                        }
                    }
                    catch(Throwable v0) {
                        goto label_1362;
                    }

                    zzfa v5_10 = this;
                    try {
                        v5_10.zzadj.zzgo().zzjd().zzg("Bundling raw events w/o app info. appId", zzap.zzbv(v2.zzaua.zztt));
                        goto label_1267;
                    label_1231:
                        v5_10 = this;
                        if(v1_1.zzaxb.length > 0) {
                            v6_1 = v4_4.zzgz();
                            Long v8_7 = v6_1 != 0 ? Long.valueOf(v6_1) : null;
                            v1_1.zzaxh = v8_7;
                            v8 = v4_4.zzgy();
                            long v10_3 = 0;
                            if(v8 == v10_3) {
                            }
                            else {
                                v6_1 = v8;
                            }

                            Long v6_7 = v6_1 != v10_3 ? Long.valueOf(v6_1) : null;
                            v1_1.zzaxg = v6_7;
                            v4_4.zzhh();
                            v1_1.zzaxr = Integer.valueOf(((int)v4_4.zzhe()));
                            v4_4.zzs(v1_1.zzaxe.longValue());
                            v4_4.zzt(v1_1.zzaxf.longValue());
                            v1_1.zzagv = v4_4.zzhp();
                            this.zzjq().zza(v4_4);
                        }

                    label_1267:
                        if(v1_1.zzaxb.length > 0) {
                            v5_10.zzadj.zzgr();
                            zzgb v4_5 = this.zzln().zzcf(v2.zzaua.zztt);
                            if(v4_5 != null && v4_5.zzawe != null) {
                                v4_6 = v4_5.zzawe;
                                goto label_1281;
                            }
                            else if(TextUtils.isEmpty(v2.zzaua.zzafx)) {
                                v4_6 = Long.valueOf(-1);
                            label_1281:
                                v1_1.zzaxy = v4_6;
                            }
                            else {
                                v5_10.zzadj.zzgo().zzjg().zzg("Did not find measurement config or missing version info. appId", zzap.zzbv(v2.zzaua.zztt));
                            }

                            this.zzjq().zza(v1_1, v39);
                        }

                        v1_3 = this.zzjq();
                        List v2_4 = v2.zzaub;
                        Preconditions.checkNotNull(v2_4);
                        ((zzco)v1_3).zzaf();
                        ((zzez)v1_3).zzcl();
                        StringBuilder v4_7 = new StringBuilder("rowid in (");
                        for(v6_3 = 0; v6_3 < v2_4.size(); ++v6_3) {
                            if(v6_3 != 0) {
                                v4_7.append(",");
                            }

                            v4_7.append(v2_4.get(v6_3).longValue());
                        }

                        v4_7.append(")");
                        int v4_8 = v1_3.getWritableDatabase().delete("raw_events", v4_7.toString(), null);
                        if(v4_8 != v2_4.size()) {
                            ((zzco)v1_3).zzgo().zzjd().zze("Deleted fewer rows from raw events table than expected", Integer.valueOf(v4_8), Integer.valueOf(v2_4.size()));
                        }

                        v1_3 = this.zzjq();
                        v2_5 = v1_3.getWritableDatabase();
                    }
                    catch(Throwable v0) {
                        goto label_1377;
                    }

                    try {
                        v2_5.execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{v3_3, v3_3});
                        goto label_1354;
                    }
                    catch(Throwable v0) {
                    }
                    catch(SQLiteException v0_1) {
                        SQLiteException v2_6 = v0_1;
                        try {
                            ((zzco)v1_3).zzgo().zzjd().zze("Failed to remove unused event metadata. appId", zzap.zzbv(v3_3), v2_6);
                        label_1354:
                            this.zzjq().setTransactionSuccessful();
                        }
                        catch(Throwable v0) {
                            goto label_1377;
                        }

                        this.zzjq().endTransaction();
                        return 1;
                    label_1362:
                        goto label_1381;
                    label_1181:
                        v1_4 = v0;
                        goto label_1382;
                        try {
                        label_1364:
                            this.zzjq().setTransactionSuccessful();
                        }
                        catch(Throwable v0) {
                            goto label_1377;
                        }

                        this.zzjq().endTransaction();
                        return 0;
                    label_221:
                        v8_1 = v5_2;
                    label_254:
                        v1_4 = v0;
                        if((((String)v8_1)) != null) {
                            try {
                                v8_1.close();
                            label_1378:
                                throw v1_4;
                            label_1377:
                                goto label_1381;
                            }
                            catch(Throwable v0) {
                                goto label_1377;
                            }
                        }

                        goto label_1378;
                    }
                }
                catch(IOException v0_2) {
                    try {
                        ((zzco)v4).zzgo().zzjd().zze("Data loss. Failed to merge raw event. appId", zzap.zzbv(((String)v3_2)), v0_2);
                        goto label_216;
                    label_200:
                        v9_2.name = v5_2.getString(1);
                        v9_2.zzawu = Long.valueOf(v5_2.getLong(v10));
                        if(!((zzs)v2).zza(v6_1, v9_2)) {
                            if(v5_2 == null) {
                                goto label_267;
                            }

                            goto label_190;
                        }

                    label_216:
                        if(v5_2.moveToNext()) {
                            goto label_192;
                        }
                    }
                    catch(SQLiteException v0_1) {
                        goto label_224;
                    }
                    catch(Throwable v0) {
                        goto label_221;
                    }

                    if(v5_2 == null) {
                        goto label_267;
                    }

                    try {
                    label_190:
                        v5_2.close();
                        goto label_267;
                    }
                    catch(Throwable v0) {
                    }
                }
            }
        }

    label_1381:
        v1_4 = v0;
    label_1382:
        this.zzjq().endTransaction();
        throw v1_4;
    }

    final void zze(zzh arg2) {
        this.zzaf();
        this.zzlr();
        Preconditions.checkNotEmpty(arg2.packageName);
        this.zzg(arg2);
    }

    final void zze(zzl arg2) {
        zzh v0 = this.zzco(arg2.packageName);
        if(v0 != null) {
            this.zzb(arg2, v0);
        }
    }

    final void zzf(zzl arg2) {
        zzh v0 = this.zzco(arg2.packageName);
        if(v0 != null) {
            this.zzc(arg2, v0);
        }
    }

    final void zzf(zzh arg19) {
        Bundle v0_11;
        ApplicationInfo v0_10;
        PackageInfo v0_9;
        zzz v0_7;
        String v7_3;
        zzq v0_6;
        zzad v7_2;
        Bundle v7_1;
        String v8;
        zzg v16;
        int v15;
        zzfa v1 = this;
        zzh v2 = arg19;
        this.zzaf();
        this.zzlr();
        Preconditions.checkNotNull(arg19);
        Preconditions.checkNotEmpty(v2.packageName);
        if((TextUtils.isEmpty(v2.zzafx)) && (TextUtils.isEmpty(v2.zzagk))) {
            return;
        }

        zzg v0 = this.zzjq().zzbl(v2.packageName);
        long v3 = 0;
        if(v0 != null && (TextUtils.isEmpty(v0.getGmpAppId())) && !TextUtils.isEmpty(v2.zzafx)) {
            v0.zzy(v3);
            this.zzjq().zza(v0);
            this.zzln().zzci(v2.packageName);
        }

        if(!v2.zzagg) {
            this.zzg(arg19);
            return;
        }

        long v5 = v2.zzagx;
        if(v5 == v3) {
            v5 = v1.zzadj.zzbx().currentTimeMillis();
        }

        int v0_1 = v2.zzagy;
        if(v0_1 == 0 || v0_1 == 1) {
            v15 = v0_1;
        }
        else {
            v1.zzadj.zzgo().zzjg().zze("Incorrect app type, assuming installed app. appId, appType", zzap.zzbv(v2.packageName), Integer.valueOf(v0_1));
            v15 = 0;
        }

        this.zzjq().beginTransaction();
        try {
            v0 = this.zzjq().zzbl(v2.packageName);
            v16 = null;
            if(v0 == null) {
                goto label_136;
            }

            v1.zzadj.zzgm();
            if(!zzfk.zza(v2.zzafx, v0.getGmpAppId(), v2.zzagk, v0.zzgw())) {
                goto label_136;
            }

            v1.zzadj.zzgo().zzjg().zzg("New GMP App Id passed in. Removing cached database data. appId", zzap.zzbv(v0.zzal()));
            zzq v7 = this.zzjq();
            v8 = v0.zzal();
            ((zzez)v7).zzcl();
            ((zzco)v7).zzaf();
            Preconditions.checkNotEmpty(v8);
            try {
                SQLiteDatabase v0_4 = v7.getWritableDatabase();
                String[] v9 = new String[]{v8};
                int v10 = v0_4.delete("events", "app_id=?", v9) + v0_4.delete("user_attributes", "app_id=?", v9) + v0_4.delete("conditional_properties", "app_id=?", v9) + v0_4.delete("apps", "app_id=?", v9) + v0_4.delete("raw_events", "app_id=?", v9) + v0_4.delete("raw_events_metadata", "app_id=?", v9) + v0_4.delete("event_filters", "app_id=?", v9) + v0_4.delete("property_filters", "app_id=?", v9) + v0_4.delete("audience_filter_values", "app_id=?", v9);
                if(v10 <= 0) {
                    goto label_135;
                }

                ((zzco)v7).zzgo().zzjl().zze("Deleted application data. app, records", v8, Integer.valueOf(v10));
                goto label_135;
            }
            catch(SQLiteException v0_3) {
                try {
                    ((zzco)v7).zzgo().zzjd().zze("Error deleting application data. appId, error", zzap.zzbv(v8), v0_3);
                label_135:
                    v0 = v16;
                label_136:
                    if(v0 != null) {
                        if(v0.zzha() == -2147483648) {
                            goto label_159;
                        }
                        else if(v0.zzha() != v2.zzagd) {
                            v7_1 = new Bundle();
                            v7_1.putString("_pv", v0.zzak());
                            v7_2 = new zzad("_au", new zzaa(v7_1), "auto", v5);
                            goto label_156;
                        }
                        else {
                        }
                    }

                    goto label_180;
                }
                catch(Throwable v0_2) {
                    goto label_425;
                }
            }
        }
        catch(Throwable v0_2) {
            goto label_425;
        }

    label_156:
        zzad v0_5 = v7_2;
        goto label_157;
        try {
        label_159:
            if(v0.zzak() == null) {
                goto label_180;
            }
            else if(!v0.zzak().equals(v2.zzts)) {
                v7_1 = new Bundle();
                v7_1.putString("_pv", v0.zzak());
                v0_5 = new zzad("_au", new zzaa(v7_1), "auto", v5);
            }
            else {
                goto label_180;
            }

        label_157:
            v1.zzc(v0_5, v2);
        label_180:
            this.zzg(arg19);
            if(v15 == 0) {
                v0_6 = this.zzjq();
                v7_3 = v2.packageName;
                v8 = "_f";
                goto label_185;
            }
            else if(v15 == 1) {
                v0_6 = this.zzjq();
                v7_3 = v2.packageName;
                v8 = "_v";
            label_185:
                v0_7 = v0_6.zzg(v7_3, v8);
            }
            else {
                v0_7 = ((zzz)v16);
            }

            if(v0_7 != null) {
                goto label_404;
            }

            long v11 = 1;
            long v9_1 = (v5 / 3600000 + v11) * 3600000;
            if(v15 != 0) {
                goto label_350;
            }

            long v13 = v11;
            v1.zzb(new zzfh("_fot", v5, Long.valueOf(v9_1), "auto"), v2);
            if(v1.zzadj.zzgq().zzbg(v2.zzafx)) {
                this.zzaf();
                v1.zzadj.zzkg().zzcd(v2.packageName);
            }

            this.zzaf();
            this.zzlr();
            Bundle v15_1 = new Bundle();
            v15_1.putLong("_c", v13);
            v15_1.putLong("_r", v13);
            v15_1.putLong("_uwa", v3);
            v15_1.putLong("_pfo", v3);
            v15_1.putLong("_sys", v3);
            v15_1.putLong("_sysu", v3);
            if((v1.zzadj.zzgq().zzbd(v2.packageName)) && (v2.zzagz)) {
                v15_1.putLong("_dac", v13);
            }

            if(v1.zzadj.getContext().getPackageManager() == null) {
                v1.zzadj.zzgo().zzjd().zzg("PackageManager is null, first open report might be inaccurate. appId", zzap.zzbv(v2.packageName));
                goto label_328;
            }

            try {
                v0_9 = Wrappers.packageManager(v1.zzadj.getContext()).getPackageInfo(v2.packageName, 0);
                goto label_275;
            }
            catch(PackageManager$NameNotFoundException v0_8) {
                try {
                    v1.zzadj.zzgo().zzjd().zze("Package info is null, first open report might be inaccurate. appId", zzap.zzbv(v2.packageName), v0_8);
                    v0_9 = ((PackageInfo)v16);
                label_275:
                    if(v0_9 != null && v0_9.firstInstallTime != v3) {
                        if(v0_9.firstInstallTime != v0_9.lastUpdateTime) {
                            v15_1.putLong("_uwa", v13);
                            v0_1 = 0;
                        }
                        else {
                            v0_1 = 1;
                        }

                        zzfh v12 = null;
                        v8 = "_fi";
                        v9_1 = v0_1 != 0 ? v13 : v3;
                        super(v8, v5, Long.valueOf(v9_1), "auto");
                        v1.zzb(v12, v2);
                    }

                    try {
                        v0_10 = Wrappers.packageManager(v1.zzadj.getContext()).getApplicationInfo(v2.packageName, 0);
                        goto label_316;
                    }
                    catch(PackageManager$NameNotFoundException v0_8) {
                        try {
                            v1.zzadj.zzgo().zzjd().zze("Application info is null, first open report might be inaccurate. appId", zzap.zzbv(v2.packageName), v0_8);
                            v0_10 = ((ApplicationInfo)v16);
                        label_316:
                            if(v0_10 != null) {
                                if((v0_10.flags & 1) != 0) {
                                    v15_1.putLong("_sys", v13);
                                }

                                if((v0_10.flags & 128) == 0) {
                                    goto label_328;
                                }

                                v15_1.putLong("_sysu", v13);
                            }

                        label_328:
                            v0_6 = this.zzjq();
                            String v3_1 = v2.packageName;
                            Preconditions.checkNotEmpty(v3_1);
                            ((zzco)v0_6).zzaf();
                            ((zzez)v0_6).zzcl();
                            v3 = v0_6.zzn(v3_1, "first_open_count");
                            if(v3 >= 0) {
                                v15_1.putLong("_pfo", v3);
                            }

                            v1.zzc(new zzad("_f", new zzaa(v15_1), "auto", v5), v2);
                            goto label_389;
                        label_350:
                            v13 = v11;
                            if(v15 == 1) {
                                v1.zzb(new zzfh("_fvt", v5, Long.valueOf(v9_1), "auto"), v2);
                                this.zzaf();
                                this.zzlr();
                                v0_11 = new Bundle();
                                v0_11.putLong("_c", v13);
                                v0_11.putLong("_r", v13);
                                if((v1.zzadj.zzgq().zzbd(v2.packageName)) && (v2.zzagz)) {
                                    v0_11.putLong("_dac", v13);
                                }

                                v1.zzc(new zzad("_v", new zzaa(v0_11), "auto", v5), v2);
                            }

                        label_389:
                            v0_11 = new Bundle();
                            v0_11.putLong("_et", v13);
                            v7_2 = new zzad("_e", new zzaa(v0_11), "auto", v5);
                        }
                        catch(Throwable v0_2) {
                            goto label_425;
                        }
                    }
                }
                catch(Throwable v0_2) {
                    goto label_425;
                }
            }
        }
        catch(Throwable v0_2) {
            goto label_425;
        }

        zzad v3_2 = v7_2;
        goto label_402;
        try {
        label_404:
            if(v2.zzagw) {
                v3_2 = new zzad("_cd", new zzaa(new Bundle()), "auto", v5);
            label_402:
                v1.zzc(v3_2, v2);
            }

            this.zzjq().setTransactionSuccessful();
        }
        catch(Throwable v0_2) {
        label_425:
            this.zzjq().endTransaction();
            throw v0_2;
        }

        this.zzjq().endTransaction();
    }

    final void zzg(Runnable arg2) {
        this.zzaf();
        if(this.zzatm == null) {
            this.zzatm = new ArrayList();
        }

        this.zzatm.add(arg2);
    }

    private final zzg zzg(zzh arg9) {
        int v1_1;
        this.zzaf();
        this.zzlr();
        Preconditions.checkNotNull(arg9);
        Preconditions.checkNotEmpty(arg9.packageName);
        zzg v0 = this.zzjq().zzbl(arg9.packageName);
        String v1 = this.zzadj.zzgp().zzbz(arg9.packageName);
        if(v0 == null) {
            v0 = new zzg(this.zzadj, arg9.packageName);
            v0.zzam(this.zzadj.zzgm().zzmf());
            v0.zzap(v1);
            goto label_23;
        }
        else if(!v1.equals(v0.zzgx())) {
            v0.zzap(v1);
            v0.zzam(this.zzadj.zzgm().zzmf());
        label_23:
            v1_1 = 1;
        }
        else {
            v1_1 = 0;
        }

        if(!TextUtils.equals(arg9.zzafx, v0.getGmpAppId())) {
            v0.zzan(arg9.zzafx);
            v1_1 = 1;
        }

        if(!TextUtils.equals(arg9.zzagk, v0.zzgw())) {
            v0.zzao(arg9.zzagk);
            v1_1 = 1;
        }

        if(!TextUtils.isEmpty(arg9.zzafz) && !arg9.zzafz.equals(v0.getFirebaseInstanceId())) {
            v0.zzaq(arg9.zzafz);
            v1_1 = 1;
        }

        if(arg9.zzadt != 0 && arg9.zzadt != v0.zzhc()) {
            v0.zzv(arg9.zzadt);
            v1_1 = 1;
        }

        if(!TextUtils.isEmpty(arg9.zzts) && !arg9.zzts.equals(v0.zzak())) {
            v0.setAppVersion(arg9.zzts);
            v1_1 = 1;
        }

        if(arg9.zzagd != v0.zzha()) {
            v0.zzu(arg9.zzagd);
            v1_1 = 1;
        }

        if(arg9.zzage != null && !arg9.zzage.equals(v0.zzhb())) {
            v0.zzar(arg9.zzage);
            v1_1 = 1;
        }

        if(arg9.zzagf != v0.zzhd()) {
            v0.zzw(arg9.zzagf);
            v1_1 = 1;
        }

        if(arg9.zzagg != v0.isMeasurementEnabled()) {
            v0.setMeasurementEnabled(arg9.zzagg);
            v1_1 = 1;
        }

        if(!TextUtils.isEmpty(arg9.zzagv) && !arg9.zzagv.equals(v0.zzho())) {
            v0.zzas(arg9.zzagv);
            v1_1 = 1;
        }

        if(arg9.zzagh != v0.zzhq()) {
            v0.zzag(arg9.zzagh);
            v1_1 = 1;
        }

        if(arg9.zzagi != v0.zzhr()) {
            v0.zze(arg9.zzagi);
            v1_1 = 1;
        }

        if(arg9.zzagj != v0.zzhs()) {
            v0.zzf(arg9.zzagj);
            v1_1 = 1;
        }

        if(v1_1 != 0) {
            this.zzjq().zza(v0);
        }

        return v0;
    }

    public final zzan zzgl() {
        return this.zzadj.zzgl();
    }

    public final zzfk zzgm() {
        return this.zzadj.zzgm();
    }

    public final zzbo zzgn() {
        return this.zzadj.zzgn();
    }

    public final zzap zzgo() {
        return this.zzadj.zzgo();
    }

    public final zzn zzgq() {
        return this.zzadj.zzgq();
    }

    public final zzk zzgr() {
        return this.zzadj.zzgr();
    }

    final String zzh(zzh arg5) {
        Future v0 = this.zzadj.zzgn().zzb(new zzfe(this, arg5));
        long v1 = 30000;
        try {
            return v0.get(v1, TimeUnit.MILLISECONDS);
        }
        catch(ExecutionException v0_1) {
            this.zzadj.zzgo().zzjd().zze("Failed to get app instance id. appId", zzap.zzbv(arg5.packageName), v0_1);
            return null;
        }
    }

    public final zzfg zzjo() {
        zzfa.zza(this.zzatj);
        return this.zzatj;
    }

    public final zzj zzjp() {
        zzfa.zza(this.zzati);
        return this.zzati;
    }

    public final zzq zzjq() {
        zzfa.zza(this.zzatf);
        return this.zzatf;
    }

    private final zzbn zzln() {
        zzfa.zza(this.zzatd);
        return this.zzatd;
    }

    public final zzat zzlo() {
        zzfa.zza(this.zzate);
        return this.zzate;
    }

    private final zzay zzlp() {
        if(this.zzatg != null) {
            return this.zzatg;
        }

        throw new IllegalStateException("Network broadcast receiver not created");
    }

    private final zzew zzlq() {
        zzfa.zza(this.zzath);
        return this.zzath;
    }

    final void zzlr() {
        if(this.zzvz) {
            return;
        }

        throw new IllegalStateException("UploadController is not initialized");
    }

    private final long zzls() {
        long v0 = this.zzadj.zzbx().currentTimeMillis();
        zzba v2 = this.zzadj.zzgp();
        ((zzcp)v2).zzcl();
        ((zzco)v2).zzaf();
        long v3 = v2.zzani.get();
        if(v3 == 0) {
            v3 = 1 + (((long)((zzco)v2).zzgm().zzmd().nextInt(86400000)));
            v2.zzani.set(v3);
        }

        return (v0 + v3) / 1000 / 60 / 60 / 24;
    }

    final void zzlt() {
        Object v8;
        String v3_1;
        zzar v0_1;
        zzfa v1 = this;
        this.zzaf();
        this.zzlr();
        v1.zzatr = true;
        try {
            v1.zzadj.zzgr();
            Boolean v3 = v1.zzadj.zzgg().zzle();
            if(v3 == null) {
                v0_1 = v1.zzadj.zzgo().zzjg();
                v3_1 = "Upload data called on the client side before use of service was decided";
            }
            else if(v3.booleanValue()) {
                v0_1 = v1.zzadj.zzgo().zzjd();
                v3_1 = "Upload called in the client side when service should be used";
            }
            else {
                long v5 = 0;
                if(v1.zzatl <= v5) {
                    this.zzaf();
                    int v3_2 = v1.zzatu != null ? 1 : 0;
                    if(v3_2 == 0) {
                        goto label_44;
                    }

                    v0_1 = v1.zzadj.zzgo().zzjl();
                    v3_1 = "Uploading requested multiple times";
                    goto label_16;
                }

                goto label_30;
            }

        label_16:
            v0_1.zzbx(v3_1);
            goto label_17;
        label_44:
            if(!this.zzlo().zzfb()) {
                v1.zzadj.zzgo().zzjl().zzbx("Network not connected, ignoring upload request");
            label_30:
                this.zzlv();
                goto label_17;
            }

            long v3_3 = v1.zzadj.zzbx().currentTimeMillis();
            String v9 = null;
            v1.zzd(v9, v3_3 - zzn.zzhx());
            long v7 = v1.zzadj.zzgp().zzane.get();
            if(v7 != v5) {
                v1.zzadj.zzgo().zzjk().zzg("Uploading events. Elapsed time since last upload attempt (ms)", Long.valueOf(Math.abs(v3_3 - v7)));
            }

            String v5_1 = this.zzjq().zzid();
            v7 = -1;
            if(TextUtils.isEmpty(((CharSequence)v5_1))) {
                goto label_247;
            }

            if(v1.zzatw == v7) {
                v1.zzatw = this.zzjq().zzik();
            }

            List v6 = this.zzjq().zzb(v5_1, v1.zzadj.zzgq().zzb(v5_1, zzaf.zzajj), Math.max(0, v1.zzadj.zzgq().zzb(v5_1, zzaf.zzajk)));
            if(v6.isEmpty()) {
                goto label_17;
            }

            Iterator v7_1 = v6.iterator();
            do {
                if(v7_1.hasNext()) {
                    v8 = v7_1.next().first;
                    if(TextUtils.isEmpty(((zzgi)v8).zzaxo)) {
                        continue;
                    }

                    break;
                }
                else {
                    goto label_108;
                }
            }
            while(true);

            String v7_2 = ((zzgi)v8).zzaxo;
            goto label_109;
        label_108:
            v7_2 = v9;
        label_109:
            if(v7_2 != null) {
                int v8_1;
                for(v8_1 = 0; v8_1 < v6.size(); ++v8_1) {
                    Object v10 = v6.get(v8_1).first;
                    if(!TextUtils.isEmpty(((zzgi)v10).zzaxo) && !((zzgi)v10).zzaxo.equals(v7_2)) {
                        v6 = v6.subList(0, v8_1);
                        break;
                    }
                }
            }

            zzgh v7_3 = new zzgh();
            v7_3.zzawy = new zzgi[v6.size()];
            ArrayList v8_2 = new ArrayList(v6.size());
            int v10_1 = !zzn.zzhz() || !v1.zzadj.zzgq().zzav(v5_1) ? 0 : 1;
            int v11;
            for(v11 = 0; v11 < v7_3.zzawy.length; ++v11) {
                v7_3.zzawy[v11] = v6.get(v11).first;
                ((List)v8_2).add(v6.get(v11).second);
                v7_3.zzawy[v11].zzaxn = Long.valueOf(v1.zzadj.zzgq().zzhc());
                v7_3.zzawy[v11].zzaxd = Long.valueOf(v3_3);
                zzgi v12 = v7_3.zzawy[v11];
                v1.zzadj.zzgr();
                v12.zzaxs = Boolean.valueOf(false);
                if(v10_1 == 0) {
                    v7_3.zzawy[v11].zzaya = v9;
                }
            }

            if(v1.zzadj.zzgo().isLoggable(2)) {
                v9 = this.zzjo().zzb(v7_3);
            }

            byte[] v14 = this.zzjo().zza(v7_3);
            Object v6_1 = zzaf.zzajt.get();
            try {
                URL v13 = new URL(((String)v6_1));
                Preconditions.checkArgument(((List)v8_2).isEmpty() ^ 1);
                if(v1.zzatu != null) {
                    v1.zzadj.zzgo().zzjd().zzbx("Set uploading progress before finishing the previous upload");
                }
                else {
                    v1.zzatu = new ArrayList(((Collection)v8_2));
                }

                v1.zzadj.zzgp().zzanf.set(v3_3);
                v3_1 = "?";
                if(v7_3.zzawy.length > 0) {
                    v3_1 = v7_3.zzawy[0].zztt;
                }

                v1.zzadj.zzgo().zzjl().zzd("Uploading data. app, uncompressed size, data", v3_1, Integer.valueOf(v14.length), v9);
                v1.zzatq = true;
                zzat v11_1 = this.zzlo();
                zzfc v0_2 = new zzfc(v1, v5_1);
                ((zzco)v11_1).zzaf();
                ((zzez)v11_1).zzcl();
                Preconditions.checkNotNull(v13);
                Preconditions.checkNotNull(v14);
                Preconditions.checkNotNull(v0_2);
                ((zzco)v11_1).zzgn().zzd(new zzax(v11_1, v5_1, v13, v14, null, v0_2));
            }
            catch(MalformedURLException ) {
                v1.zzadj.zzgo().zzjd().zze("Failed to parse upload URL. Not uploading. appId", zzap.zzbv(v5_1), v6_1);
            }

            goto label_17;
        label_247:
            v1.zzatw = v7;
            String v0_3 = this.zzjq().zzah(v3_3 - zzn.zzhx());
            if(TextUtils.isEmpty(((CharSequence)v0_3))) {
                goto label_17;
            }

            zzg v0_4 = this.zzjq().zzbl(v0_3);
            if(v0_4 == null) {
                goto label_17;
            }

            v1.zzb(v0_4);
        }
        catch(Throwable v0) {
            v1.zzatr = false;
            this.zzlw();
            throw v0;
        }

    label_17:
        v1.zzatr = false;
        this.zzlw();
    }

    private final boolean zzlu() {
        this.zzaf();
        this.zzlr();
        if(!this.zzjq().zzii()) {
            if(!TextUtils.isEmpty(this.zzjq().zzid())) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    private final void zzlv() {
        long v8;
        com.google.android.gms.measurement.internal.zzaf$zza v9_1;
        long v5;
        zzfa v0 = this;
        this.zzaf();
        this.zzlr();
        if(!this.zzlz()) {
            return;
        }

        long v3 = 0;
        if(v0.zzatl > v3) {
            v5 = 3600000 - Math.abs(v0.zzadj.zzbx().elapsedRealtime() - v0.zzatl);
            if(v5 > v3) {
                v0.zzadj.zzgo().zzjl().zzg("Upload has been suspended. Will update scheduling later in approximately ms", Long.valueOf(v5));
                this.zzlp().unregister();
                this.zzlq().cancel();
                return;
            }
            else {
                v0.zzatl = v3;
            }
        }

        if(v0.zzadj.zzkr()) {
            if(!this.zzlu()) {
            }
            else {
                long v1 = v0.zzadj.zzbx().currentTimeMillis();
                v5 = Math.max(v3, zzaf.zzakd.get().longValue());
                int v7 = (this.zzjq().zzij()) || (this.zzjq().zzie()) ? 1 : 0;
                if(v7 != 0) {
                    String v9 = v0.zzadj.zzgq().zzhy();
                    if(!TextUtils.isEmpty(((CharSequence)v9)) && !".none.".equals(v9)) {
                        v9_1 = zzaf.zzajy;
                        goto label_67;
                    }

                    v9_1 = zzaf.zzajx;
                }
                else {
                    v9_1 = zzaf.zzajw;
                }

            label_67:
                long v9_2 = Math.max(v3, v9_1.get().longValue());
                long v11 = v0.zzadj.zzgp().zzane.get();
                long v13 = v0.zzadj.zzgp().zzanf.get();
                long v16 = v9_2;
                long v18 = v5;
                v5 = Math.max(this.zzjq().zzig(), this.zzjq().zzih());
                if(v5 != v3) {
                    v5 = v1 - Math.abs(v5 - v1);
                    v8 = v1 - Math.abs(v11 - v1);
                    v1 -= Math.abs(v13 - v1);
                    v8 = Math.max(v8, v1);
                    long v10 = v5 + v18;
                    if(v7 != 0 && v8 > v3) {
                        v10 = Math.min(v5, v8) + v16;
                    }

                    long v12 = v16;
                    if(!this.zzjo().zzb(v8, v12)) {
                        v8 += v12;
                    }
                    else {
                        v8 = v10;
                    }

                    if(v1 == v3) {
                        goto label_134;
                    }

                    if(v1 < v5) {
                        goto label_134;
                    }

                    int v5_1;
                    for(v5_1 = 0; true; ++v5_1) {
                        if(v5_1 >= Math.min(20, Math.max(0, zzaf.zzakf.get().intValue()))) {
                            goto label_86;
                        }

                        v8 += Math.max(v3, zzaf.zzake.get().longValue()) * (1 << v5_1);
                        if(v8 > v1) {
                            break;
                        }
                    }
                }
                else {
                label_86:
                    v8 = v3;
                }

            label_134:
                if(v8 == v3) {
                    v0.zzadj.zzgo().zzjl().zzbx("Next upload time is 0");
                    this.zzlp().unregister();
                    this.zzlq().cancel();
                    return;
                }

                if(!this.zzlo().zzfb()) {
                    v0.zzadj.zzgo().zzjl().zzbx("No network");
                    this.zzlp().zzey();
                    this.zzlq().cancel();
                    return;
                }

                v1 = v0.zzadj.zzgp().zzang.get();
                v5 = Math.max(v3, zzaf.zzaju.get().longValue());
                if(!this.zzjo().zzb(v1, v5)) {
                    v8 = Math.max(v8, v1 + v5);
                }

                this.zzlp().unregister();
                v8 -= v0.zzadj.zzbx().currentTimeMillis();
                if(v8 <= v3) {
                    v8 = Math.max(v3, zzaf.zzajz.get().longValue());
                    v0.zzadj.zzgp().zzane.set(v0.zzadj.zzbx().currentTimeMillis());
                }

                v0.zzadj.zzgo().zzjl().zzg("Upload scheduled in approximately ms", Long.valueOf(v8));
                this.zzlq().zzh(v8);
                return;
            }
        }

        v0.zzadj.zzgo().zzjl().zzbx("Nothing to upload or uploading impossible");
        this.zzlp().unregister();
        this.zzlq().cancel();
    }

    private final void zzlw() {
        this.zzaf();
        if(!this.zzatp && !this.zzatq) {
            if(this.zzatr) {
            }
            else {
                this.zzadj.zzgo().zzjl().zzbx("Stopping uploading service(s)");
                if(this.zzatm == null) {
                    return;
                }
                else {
                    Iterator v0 = this.zzatm.iterator();
                    while(v0.hasNext()) {
                        v0.next().run();
                    }

                    this.zzatm.clear();
                    return;
                }
            }
        }

        this.zzadj.zzgo().zzjl().zzd("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzatp), Boolean.valueOf(this.zzatq), Boolean.valueOf(this.zzatr));
    }

    @VisibleForTesting private final boolean zzlx() {
        String v2_1;
        zzar v1;
        this.zzaf();
        File v2 = new File(this.zzadj.getContext().getFilesDir(), "google_app_measurement.db");
        try {
            this.zzatt = new RandomAccessFile(v2, "rw").getChannel();
            this.zzats = this.zzatt.tryLock();
            if(this.zzats != null) {
                this.zzadj.zzgo().zzjl().zzbx("Storage concurrent access okay");
                return 1;
            }

            this.zzadj.zzgo().zzjd().zzbx("Storage concurrent data access panic");
            return 0;
        }
        catch(IOException v0) {
            v1 = this.zzadj.zzgo().zzjd();
            v2_1 = "Failed to access storage lock file";
        }
        catch(FileNotFoundException v0_1) {
            v1 = this.zzadj.zzgo().zzjd();
            v2_1 = "Failed to acquire storage lock";
        }

        v1.zzg(v2_1, v0);
        return 0;
    }

    final void zzly() {
        String v3;
        zzar v2;
        this.zzaf();
        this.zzlr();
        if(!this.zzatk) {
            this.zzadj.zzgo().zzjj().zzbx("This instance being marked as an uploader");
            this.zzaf();
            this.zzlr();
            if((this.zzlz()) && (this.zzlx())) {
                int v0 = this.zza(this.zzatt);
                int v1 = this.zzadj.zzgf().zzja();
                this.zzaf();
                if(v0 > v1) {
                    v2 = this.zzadj.zzgo().zzjd();
                    v3 = "Panic: can\'t downgrade version. Previous, current version";
                }
                else if(v0 >= v1) {
                    goto label_44;
                }
                else if(this.zza(v1, this.zzatt)) {
                    v2 = this.zzadj.zzgo().zzjl();
                    v3 = "Storage version upgraded. Previous, current version";
                }
                else {
                    v2 = this.zzadj.zzgo().zzjd();
                    v3 = "Storage version upgrade failed. Previous, current version";
                }

                v2.zze(v3, Integer.valueOf(v0), Integer.valueOf(v1));
            }

        label_44:
            this.zzatk = true;
            this.zzlv();
        }
    }

    private final boolean zzlz() {
        this.zzaf();
        this.zzlr();
        if(this.zzatk) {
            return 1;
        }

        return 0;
    }

    public static zzfa zzm(Context arg2) {
        Preconditions.checkNotNull(arg2);
        Preconditions.checkNotNull(arg2.getApplicationContext());
        if(zzfa.zzatc == null) {
            Class v0 = zzfa.class;
            __monitor_enter(v0);
            try {
                if(zzfa.zzatc == null) {
                    zzfa.zzatc = new zzfa(new zzff(arg2));
                }

                __monitor_exit(v0);
                goto label_19;
            label_17:
                __monitor_exit(v0);
            }
            catch(Throwable v2) {
                goto label_17;
            }

            throw v2;
        }

    label_19:
        return zzfa.zzatc;
    }

    final void zzma() {
        ++this.zzato;
    }

    final zzbt zzmb() {
        return this.zzadj;
    }

    final void zzo(boolean arg1) {
        this.zzlv();
    }
}

