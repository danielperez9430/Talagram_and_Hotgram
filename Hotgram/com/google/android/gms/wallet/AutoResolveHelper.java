package com.google.android.gms.wallet;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.PendingIntent$CanceledException;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender$SendIntentException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.internal.wallet.zze;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AutoResolveHelper {
    final class zza implements OnCompleteListener, Runnable {
        private static final Handler zzr;
        static final SparseArray zzs;
        private static final AtomicInteger zzt;
        int zzu;
        private zzb zzv;
        private Task zzw;

        static {
            zza.zzr = new zze(Looper.getMainLooper());
            zza.zzs = new SparseArray(2);
            zza.zzt = new AtomicInteger();
        }

        zza() {
            super();
        }

        public final void onComplete(Task arg1) {
            this.zzw = arg1;
            this.zzb();
        }

        public final void run() {
            zza.zzs.delete(this.zzu);
        }

        public static zza zza(Task arg4) {
            zza v0 = new zza();
            v0.zzu = zza.zzt.incrementAndGet();
            zza.zzs.put(v0.zzu, v0);
            zza.zzr.postDelayed(((Runnable)v0), AutoResolveHelper.zza());
            arg4.addOnCompleteListener(((OnCompleteListener)v0));
            return v0;
        }

        public final void zza(zzb arg1) {
            this.zzv = arg1;
            this.zzb();
        }

        public final void zzb(zzb arg2) {
            if(this.zzv == arg2) {
                this.zzv = null;
            }
        }

        private final void zzb() {
            if(this.zzw != null && this.zzv != null) {
                zza.zzs.delete(this.zzu);
                zza.zzr.removeCallbacks(((Runnable)this));
                zzb.zza(this.zzv, this.zzw);
            }
        }
    }

    public final class zzb extends Fragment {
        private static String zzaa = "delivered";
        private int zzab;
        private zza zzac;
        private boolean zzad;
        private static String zzx = "resolveCallId";
        private static String zzy = "requestCode";
        private static String zzz = "initializationElapsedRealtime";

        static {
        }

        public zzb() {
            super();
        }

        public final void onCreate(Bundle arg6) {
            Object v0_1;
            super.onCreate(arg6);
            this.zzab = this.getArguments().getInt(zzb.zzy);
            if(AutoResolveHelper.zzq != this.getArguments().getLong(zzb.zzz)) {
                zza v0 = null;
            }
            else {
                v0_1 = zza.zzs.get(this.getArguments().getInt(zzb.zzx));
            }

            this.zzac = ((zza)v0_1);
            boolean v6 = arg6 == null || !arg6.getBoolean(zzb.zzaa) ? false : true;
            this.zzad = v6;
        }

        public final void onPause() {
            super.onPause();
            this.zzc();
        }

        public final void onResume() {
            super.onResume();
            if(this.zzac != null) {
                this.zzac.zza(this);
                return;
            }

            if(Log.isLoggable("AutoResolveHelper", 5)) {
                Log.w("AutoResolveHelper", "Sending canceled result for garbage collected task!");
            }

            this.zzb(null);
        }

        public final void onSaveInstanceState(Bundle arg3) {
            super.onSaveInstanceState(arg3);
            arg3.putBoolean(zzb.zzaa, this.zzad);
            this.zzc();
        }

        private static Fragment zza(int arg3, int arg4) {
            Bundle v0 = new Bundle();
            v0.putInt(zzb.zzx, arg3);
            v0.putInt(zzb.zzy, arg4);
            v0.putLong(zzb.zzz, AutoResolveHelper.zzq);
            zzb v3 = new zzb();
            v3.setArguments(v0);
            return ((Fragment)v3);
        }

        static void zza(zzb arg0, Task arg1) {
            arg0.zzb(arg1);
        }

        static Fragment zzb(int arg0, int arg1) {
            return zzb.zza(arg0, arg1);
        }

        private final void zzb(Task arg4) {
            if(!this.zzad) {
                this.zzad = true;
                Activity v0 = this.getActivity();
                v0.getFragmentManager().beginTransaction().remove(((Fragment)this)).commit();
                if(arg4 != null) {
                    AutoResolveHelper.zzb(v0, this.zzab, arg4);
                    return;
                }
                else {
                    AutoResolveHelper.zzb(v0, this.zzab, 0, new Intent());
                }
            }
        }

        private final void zzc() {
            if(this.zzac != null) {
                this.zzac.zzb(this);
            }
        }
    }

    public static final int RESULT_ERROR = 1;
    private static final long zzp;
    static long zzq;

    static {
        AutoResolveHelper.zzp = TimeUnit.MINUTES.toMillis(10);
        AutoResolveHelper.zzq = SystemClock.elapsedRealtime();
    }

    private AutoResolveHelper() {
        super();
    }

    public static Status getStatusFromIntent(Intent arg1) {
        if(arg1 == null) {
            return null;
        }

        return arg1.getParcelableExtra("com.google.android.gms.common.api.AutoResolveHelper.status");
    }

    public static void putStatusIntoIntent(Intent arg1, Status arg2) {
        if(arg2 == null) {
            arg1.removeExtra("com.google.android.gms.common.api.AutoResolveHelper.status");
            return;
        }

        arg1.putExtra("com.google.android.gms.common.api.AutoResolveHelper.status", ((Parcelable)arg2));
    }

    public static void resolveTask(Task arg2, Activity arg3, int arg4) {
        zza v2 = zza.zza(arg2);
        FragmentTransaction v3 = arg3.getFragmentManager().beginTransaction();
        Fragment v4 = zzb.zzb(v2.zzu, arg4);
        int v2_1 = v2.zzu;
        StringBuilder v0 = new StringBuilder(58);
        v0.append("com.google.android.gms.wallet.AutoResolveHelper");
        v0.append(v2_1);
        v3.add(v4, v0.toString()).commit();
    }

    static long zza() {
        return AutoResolveHelper.zzp;
    }

    private static void zza(Activity arg1, int arg2, int arg3, Intent arg4) {
        PendingIntent v1 = arg1.createPendingResult(arg2, arg4, 1073741824);
        if(v1 == null) {
            if(!Log.isLoggable("AutoResolveHelper", 5)) {
                return;
            }

            Log.w("AutoResolveHelper", "Null pending result returned when trying to deliver task result!");
            return;
        }

        try {
            v1.send(arg3);
            return;
        }
        catch(PendingIntent$CanceledException v1_1) {
            if(!Log.isLoggable("AutoResolveHelper", 6)) {
                return;
            }

            Log.e("AutoResolveHelper", "Exception sending pending result", ((Throwable)v1_1));
        }
    }

    private static void zza(Activity arg5, int arg6, Task arg7) {
        Exception v7;
        if(!arg5.isFinishing()) {
            int v1 = 6;
            if((arg7.getException() instanceof ResolvableApiException)) {
                v7 = arg7.getException();
                try {
                    ((ResolvableApiException)v7).startResolutionForResult(arg5, arg6);
                    return;
                }
                catch(IntentSender$SendIntentException v5) {
                    if(Log.isLoggable("AutoResolveHelper", v1)) {
                        Log.e("AutoResolveHelper", "Error starting pending intent!", ((Throwable)v5));
                    }

                    return;
                }
            }
            else {
                Intent v0 = new Intent();
                int v3 = 1;
                if(arg7.isSuccessful()) {
                    v3 = -1;
                    arg7.getResult().putIntoIntent(v0);
                }
                else if((arg7.getException() instanceof ApiException)) {
                    v7 = arg7.getException();
                    AutoResolveHelper.putStatusIntoIntent(v0, new Status(((ApiException)v7).getStatusCode(), ((ApiException)v7).getMessage(), null));
                }
                else {
                    if(Log.isLoggable("AutoResolveHelper", v1)) {
                        Log.e("AutoResolveHelper", "Unexpected non API exception!", arg7.getException());
                    }

                    AutoResolveHelper.putStatusIntoIntent(v0, new Status(8, "Unexpected non API exception when trying to deliver the task result to an activity!"));
                }

                AutoResolveHelper.zza(arg5, arg6, v3, v0);
            }
        }
        else if(Log.isLoggable("AutoResolveHelper", 3)) {
            Log.d("AutoResolveHelper", "Ignoring task result for, Activity is finishing.");
            return;
        }
    }

    public static void zza(Status arg1, Object arg2, TaskCompletionSource arg3) {
        if(arg1.isSuccess()) {
            arg3.setResult(arg2);
            return;
        }

        arg3.setException(ApiExceptionUtil.fromStatus(arg1));
    }

    static void zzb(Activity arg0, int arg1, int arg2, Intent arg3) {
        AutoResolveHelper.zza(arg0, arg1, 0, arg3);
    }

    static void zzb(Activity arg0, int arg1, Task arg2) {
        AutoResolveHelper.zza(arg0, arg1, arg2);
    }
}

