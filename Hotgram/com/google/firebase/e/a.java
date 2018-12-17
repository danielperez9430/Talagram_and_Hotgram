package com.google.firebase.e;

import android.content.Context;
import android.content.pm.PackageManager$NameNotFoundException;
import android.os.AsyncTask;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.config.zzal;
import com.google.android.gms.internal.config.zzam;
import com.google.android.gms.internal.config.zzan;
import com.google.android.gms.internal.config.zzao;
import com.google.android.gms.internal.config.zzaq;
import com.google.android.gms.internal.config.zzar;
import com.google.android.gms.internal.config.zzas;
import com.google.android.gms.internal.config.zzat;
import com.google.android.gms.internal.config.zzau;
import com.google.android.gms.internal.config.zzav;
import com.google.android.gms.internal.config.zzaw;
import com.google.android.gms.internal.config.zzax;
import com.google.android.gms.internal.config.zzay;
import com.google.android.gms.internal.config.zzbh;
import com.google.android.gms.internal.config.zze;
import com.google.android.gms.internal.config.zzj;
import com.google.android.gms.internal.config.zzk;
import com.google.android.gms.internal.config.zzv;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.b;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.concurrent.GuardedBy;

public class a {
    public static final byte[] a;
    @GuardedBy(value="FirebaseRemoteConfig.class") private static a b;
    private zzao c;
    private zzao d;
    private zzao e;
    private zzar f;
    private final Context g;
    private final b h;
    private final ReadWriteLock i;
    private final com.google.firebase.a.a j;

    static {
        a.a = new byte[0];
    }

    private a(Context arg3, zzao arg4, zzao arg5, zzao arg6, zzar arg7) {
        super();
        this.i = new ReentrantReadWriteLock(true);
        this.g = arg3;
        if(arg7 == null) {
            arg7 = new zzar();
        }

        this.f = arg7;
        this.f.zzc(this.b(this.g));
        this.c = arg4;
        this.d = arg5;
        this.e = arg6;
        this.h = b.a(this.g);
        this.j = a.d(this.g);
    }

    private static zzao a(zzas arg12) {
        if(arg12 == null) {
            return null;
        }

        HashMap v0 = new HashMap();
        zzav[] v1 = arg12.zzbf;
        int v2 = v1.length;
        int v3 = 0;
        int v4;
        for(v4 = 0; v4 < v2; ++v4) {
            zzav v5 = v1[v4];
            String v6 = v5.namespace;
            HashMap v7 = new HashMap();
            zzat[] v5_1 = v5.zzbo;
            int v8 = v5_1.length;
            int v9;
            for(v9 = 0; v9 < v8; ++v9) {
                ((Map)v7).put(v5_1[v9].zzbi, v5_1[v9].zzbj);
            }

            ((Map)v0).put(v6, v7);
        }

        byte[][] v1_1 = arg12.zzbg;
        ArrayList v2_1 = new ArrayList();
        v4 = v1_1.length;
        while(v3 < v4) {
            ((List)v2_1).add(v1_1[v3]);
            ++v3;
        }

        return new zzao(((Map)v0), arg12.timestamp, ((List)v2_1));
    }

    private final Task a(long arg11, zzv arg13) {
        TaskCompletionSource v0 = new TaskCompletionSource();
        this.i.readLock().lock();
        try {
            zzj v1 = new zzj();
            v1.zza(arg11);
            if(this.h != null) {
                v1.zza(this.h.c().a());
            }

            if(this.f.isDeveloperModeEnabled()) {
                v1.zza("_rcn_developer", "true");
            }

            v1.zza(10300);
            int v12 = 2147483647;
            long v2 = 2147483647;
            long v4 = -1;
            if(this.d != null && this.d.getTimestamp() != v4) {
                long v6 = TimeUnit.SECONDS.convert(System.currentTimeMillis() - this.d.getTimestamp(), TimeUnit.MILLISECONDS);
                int v11_1 = v6 < v2 ? ((int)v6) : 2147483647;
                v1.zzc(v11_1);
            }

            if(this.c != null && this.c.getTimestamp() != v4) {
                v4 = TimeUnit.SECONDS.convert(System.currentTimeMillis() - this.c.getTimestamp(), TimeUnit.MILLISECONDS);
                if(v4 < v2) {
                    v12 = ((int)v4);
                }

                v1.zzb(v12);
            }

            zze.zze.zza(arg13.asGoogleApiClient(), v1.zzf()).setResultCallback(new f(this, v0));
        }
        catch(Throwable v11) {
            this.i.readLock().unlock();
            throw v11;
        }

        this.i.readLock().unlock();
        return v0.getTask();
    }

    public static a a() {
        return a.a(b.d().a());
    }

    private static a a(Context arg14) {
        zzar v6;
        zzao v5;
        zzao v4;
        Class v0 = a.class;
        __monitor_enter(v0);
        try {
            if(a.b == null) {
                zzaw v1 = a.c(arg14);
                int v2 = 3;
                zzar v3 = null;
                if(v1 == null) {
                    if(Log.isLoggable("FirebaseRemoteConfig", v2)) {
                        Log.d("FirebaseRemoteConfig", "No persisted config was found. Initializing from scratch.");
                    }

                    v4 = ((zzao)v3);
                    v5 = v4;
                    v6 = ((zzar)v5);
                }
                else {
                    if(Log.isLoggable("FirebaseRemoteConfig", v2)) {
                        Log.d("FirebaseRemoteConfig", "Initializing from persisted config.");
                    }

                    zzao v2_1 = a.a(v1.zzbp);
                    v4 = a.a(v1.zzbq);
                    v5 = a.a(v1.zzbr);
                    zzau v6_1 = v1.zzbs;
                    if(v6_1 == null) {
                    }
                    else {
                        v3 = new zzar();
                        v3.zzf(v6_1.zzbk);
                        v3.zza(v6_1.zzbl);
                    }

                    if(v3 != null) {
                        zzax[] v1_1 = v1.zzbt;
                        HashMap v6_2 = new HashMap();
                        if(v1_1 != null) {
                            int v7 = v1_1.length;
                            int v8;
                            for(v8 = 0; v8 < v7; ++v8) {
                                ((Map)v6_2).put(v1_1[v8].namespace, new zzal(v1_1[v8].resourceId, v1_1[v8].zzbv));
                            }
                        }

                        v3.zza(((Map)v6_2));
                    }

                    v6 = v3;
                    zzao v3_1 = v2_1;
                }

                a.b = new a(arg14, ((zzao)v3), v4, v5, v6);
            }

            __monitor_exit(v0);
            return a.b;
        label_69:
            __monitor_exit(v0);
        }
        catch(Throwable v14) {
            goto label_69;
        }

        throw v14;
    }

    private final void a(TaskCompletionSource arg5, Status arg6) {
        if(arg6 == null) {
            Log.w("FirebaseRemoteConfig", "Received null IPC status for failure.");
        }
        else {
            int v1 = arg6.getStatusCode();
            String v6 = arg6.getStatusMessage();
            StringBuilder v3 = new StringBuilder(String.valueOf(v6).length() + 25);
            v3.append("IPC failure: ");
            v3.append(v1);
            v3.append(":");
            v3.append(v6);
            Log.w("FirebaseRemoteConfig", v3.toString());
        }

        this.i.writeLock().lock();
        try {
            this.f.zzf(1);
            arg5.setException(new c());
            this.c();
        }
        catch(Throwable v5) {
            this.i.writeLock().unlock();
            throw v5;
        }

        this.i.writeLock().unlock();
    }

    private static void a(Runnable arg1) {
        AsyncTask.SERIAL_EXECUTOR.execute(arg1);
    }

    public Task a(long arg3) {
        return this.a(arg3, new zzv(this.g));
    }

    public String a(String arg2) {
        return this.a(arg2, "configns:firebase");
    }

    public String a(String arg3, String arg4) {
        String v0;
        if(arg4 == null) {
            return "";
        }

        this.i.readLock().lock();
        try {
            if(this.d == null || !this.d.zzb(arg3, arg4)) {
                if(this.e != null && (this.e.zzb(arg3, arg4))) {
                    v0 = new String(this.e.zzc(arg3, arg4), zzaq.UTF_8);
                    goto label_16;
                }

                goto label_31;
            }
            else {
                v0 = new String(this.d.zzc(arg3, arg4), zzaq.UTF_8);
            }
        }
        catch(Throwable v3) {
            goto label_39;
        }

    label_16:
        this.i.readLock().unlock();
        return v0;
        try {
        label_31:
            arg3 = "";
        }
        catch(Throwable v3) {
        label_39:
            this.i.readLock().unlock();
            throw v3;
        }

        this.i.readLock().unlock();
        return arg3;
    }

    final void a(TaskCompletionSource arg11, zzk arg12) {
        byte[] v0 = null;
        if(arg12 != null) {
            if(arg12.getStatus() == null) {
            }
            else {
                int v1 = arg12.getStatus().getStatusCode();
                this.i.writeLock().lock();
                int v3 = -1;
                if(v1 != -6508) {
                    if(v1 != 6507) {
                        switch(v1) {
                            case -6506: {
                                goto label_74;
                            }
                            case -6505: {
                                goto label_36;
                            }
                        }

                        switch(v1) {
                            case 6502: {
                                goto label_66;
                            }
                            case 6500: 
                            case 6501: 
                            case 6503: 
                            case 6504: {
                                goto label_34;
                            }
                        }

                        try {
                            if(arg12.getStatus().isSuccess()) {
                                StringBuilder v3_1 = new StringBuilder(45);
                                v3_1.append("Unknown (successful) status code: ");
                                v3_1.append(v1);
                                Log.w("FirebaseRemoteConfig", v3_1.toString());
                            }

                            Status v12 = arg12.getStatus();
                            goto label_30;
                        label_34:
                            v12 = arg12.getStatus();
                        label_30:
                            this.a(arg11, v12);
                            goto label_109;
                        label_36:
                            Map v1_1 = arg12.zzh();
                            HashMap v2 = new HashMap();
                            Iterator v4 = v1_1.keySet().iterator();
                            while(v4.hasNext()) {
                                Object v5 = v4.next();
                                HashMap v6 = new HashMap();
                                Iterator v7 = v1_1.get(v5).iterator();
                                while(v7.hasNext()) {
                                    Object v8 = v7.next();
                                    ((Map)v6).put(v8, arg12.zza(((String)v8), v0, ((String)v5)));
                                }

                                ((Map)v2).put(v5, v6);
                            }

                            this.c = new zzao(((Map)v2), System.currentTimeMillis(), arg12.zzg());
                            this.f.zzf(v3);
                            arg11.setResult(v0);
                            goto label_64;
                        label_66:
                            this.f.zzf(2);
                            arg11.setException(new d(arg12.getThrottleEndTimeMillis()));
                            goto label_64;
                        label_74:
                            this.f.zzf(v3);
                            if(this.c != null && !this.c.zzq()) {
                                v1_1 = arg12.zzh();
                                v2 = new HashMap();
                                Iterator v3_2 = v1_1.keySet().iterator();
                                while(v3_2.hasNext()) {
                                    Object v4_1 = v3_2.next();
                                    HashMap v5_1 = new HashMap();
                                    Iterator v6_1 = v1_1.get(v4_1).iterator();
                                    while(v6_1.hasNext()) {
                                        Object v7_1 = v6_1.next();
                                        ((Map)v5_1).put(v7_1, arg12.zza(((String)v7_1), v0, ((String)v4_1)));
                                    }

                                    ((Map)v2).put(v4_1, v5_1);
                                }

                                this.c = new zzao(((Map)v2), this.c.getTimestamp(), arg12.zzg());
                            }

                            arg11.setResult(v0);
                        label_64:
                            this.c();
                            goto label_109;
                        label_33:
                            goto label_113;
                        }
                        catch(Throwable v11) {
                            goto label_33;
                        }
                    }

                    goto label_66;
                }
                else {
                    goto label_74;
                label_113:
                    this.i.writeLock().unlock();
                    throw v11;
                }

            label_109:
                this.i.writeLock().unlock();
                return;
            }
        }

        this.a(arg11, ((Status)v0));
    }

    public void a(e arg3) {
        this.i.writeLock().lock();
        try {
            boolean v0 = this.f.isDeveloperModeEnabled();
            boolean v3_1 = arg3 == null ? false : arg3.a();
            this.f.zza(v3_1);
            if(v0 != v3_1) {
                this.c();
            }
        }
        catch(Throwable v3) {
            this.i.writeLock().unlock();
            throw v3;
        }

        this.i.writeLock().unlock();
    }

    private final long b(Context arg4) {
        long v0;
        try {
            v0 = Wrappers.packageManager(this.g).getPackageInfo(arg4.getPackageName(), 0).lastUpdateTime;
        }
        catch(PackageManager$NameNotFoundException ) {
            String v4 = arg4.getPackageName();
            StringBuilder v2 = new StringBuilder(String.valueOf(v4).length() + 25);
            v2.append("Package [");
            v2.append(v4);
            v2.append("] was not found!");
            Log.e("FirebaseRemoteConfig", v2.toString());
            v0 = 0;
        }

        return v0;
    }

    public boolean b() {
        this.i.writeLock().lock();
        try {
            if(this.c != null) {
                if(this.d != null && this.d.getTimestamp() >= this.c.getTimestamp()) {
                    goto label_6;
                }

                goto label_18;
            }
        }
        catch(Throwable v0) {
            goto label_44;
        }

    label_6:
        this.i.writeLock().unlock();
        return 0;
        try {
        label_18:
            long v0_1 = this.c.getTimestamp();
            this.d = this.c;
            this.d.setTimestamp(System.currentTimeMillis());
            this.c = new zzao(null, v0_1, null);
            a.a(new zzam(this.j, this.d.zzg()));
            this.c();
        }
        catch(Throwable v0) {
        label_44:
            this.i.writeLock().unlock();
            throw v0;
        }

        this.i.writeLock().unlock();
        return 1;
    }

    private static zzaw c(Context arg7) {
        zzaw v2_1;
        Throwable v0_1;
        FileInputStream v7_1;
        zzaw v0 = null;
        if(arg7 == null) {
            return v0;
        }

        try {
            v7_1 = arg7.openFileInput("persisted_config");
        }
        catch(Throwable v7) {
            zzaw v6 = v0;
            v0_1 = v7;
            v7_1 = ((FileInputStream)v6);
            goto label_69;
        }
        catch(IOException v1) {
            v7_1 = ((FileInputStream)v0);
            goto label_40;
        }
        catch(FileNotFoundException v1_1) {
            v7_1 = ((FileInputStream)v0);
            goto label_53;
        }

        try {
            ByteArrayOutputStream v1_2 = new ByteArrayOutputStream();
            byte[] v2 = new byte[4096];
            while(true) {
                int v3 = ((InputStream)v7_1).read(v2);
                if(v3 == -1) {
                    break;
                }

                ((OutputStream)v1_2).write(v2, 0, v3);
            }

            byte[] v1_3 = v1_2.toByteArray();
            zzay v1_4 = zzay.zza(v1_3, 0, v1_3.length);
            v2_1 = new zzaw();
            ((zzbh)v2_1).zza(v1_4);
            if(v7_1 == null) {
                return v2_1;
            }
        }
        catch(Throwable v0_1) {
            goto label_69;
        }
        catch(IOException v1) {
            goto label_30;
        }
        catch(FileNotFoundException v1_1) {
            goto label_32;
        }

        try {
            v7_1.close();
        }
        catch(IOException v7_2) {
            Log.e("FirebaseRemoteConfig", "Failed to close persisted config file.", ((Throwable)v7_2));
        }

        return v2_1;
    label_32:
        try {
        label_53:
            if(Log.isLoggable("FirebaseRemoteConfig", 3)) {
                Log.d("FirebaseRemoteConfig", "Persisted config file was not found.", ((Throwable)v1_1));
            }
        }
        catch(Throwable v0_1) {
            goto label_69;
        }

        if(v7_1 != null) {
            try {
                v7_1.close();
            }
            catch(IOException v7_2) {
                Log.e("FirebaseRemoteConfig", "Failed to close persisted config file.", ((Throwable)v7_2));
            }
        }

        return v0;
    label_30:
        try {
        label_40:
            Log.e("FirebaseRemoteConfig", "Cannot initialize from persisted config.", ((Throwable)v1));
            if(v7_1 == null) {
                return v0;
            }
        }
        catch(Throwable v0_1) {
            goto label_69;
        }

        try {
            v7_1.close();
        }
        catch(IOException v7_2) {
            Log.e("FirebaseRemoteConfig", "Failed to close persisted config file.", ((Throwable)v7_2));
        }

        return v0;
    label_69:
        if(v7_1 != null) {
            try {
                v7_1.close();
            }
            catch(IOException v7_2) {
                Log.e("FirebaseRemoteConfig", "Failed to close persisted config file.", ((Throwable)v7_2));
            }
        }

        throw v0_1;
    }

    private final void c() {
        this.i.readLock().lock();
        try {
            a.a(new zzan(this.g, this.c, this.d, this.e, this.f));
        }
        catch(Throwable v0) {
            this.i.readLock().unlock();
            throw v0;
        }

        this.i.readLock().unlock();
    }

    private static com.google.firebase.a.a d(Context arg3) {
        try {
            return new com.google.firebase.a.a(arg3, "frc", 1);
        }
        catch(NoClassDefFoundError ) {
            Log.w("FirebaseRemoteConfig", "Unable to use ABT: Analytics library is missing.");
            return null;
        }
    }
}

