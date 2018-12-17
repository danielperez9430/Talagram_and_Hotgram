package com.google.android.gms.wearable;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.wearable.internal.zzah;
import com.google.android.gms.wearable.internal.zzas;
import com.google.android.gms.wearable.internal.zzaw;
import com.google.android.gms.wearable.internal.zzen;
import com.google.android.gms.wearable.internal.zzfe;
import com.google.android.gms.wearable.internal.zzfo;
import com.google.android.gms.wearable.internal.zzhp;
import com.google.android.gms.wearable.internal.zzi;
import java.util.List;

public class WearableListenerService extends Service implements CapabilityListener, ChannelListener, DataListener, MessageListener {
    final class zza extends ChannelCallback {
        zza(WearableListenerService arg1, zzk arg2) {
            this(arg1);
        }

        private zza(WearableListenerService arg1) {
            this.zzak = arg1;
            super();
        }

        public final void onChannelClosed(Channel arg2, int arg3, int arg4) {
            this.zzak.onChannelClosed(arg2, arg3, arg4);
        }

        public final void onChannelOpened(Channel arg2) {
            this.zzak.onChannelOpened(arg2);
        }

        public final void onInputClosed(Channel arg2, int arg3, int arg4) {
            this.zzak.onInputClosed(arg2, arg3, arg4);
        }

        public final void onOutputClosed(Channel arg2, int arg3, int arg4) {
            this.zzak.onOutputClosed(arg2, arg3, arg4);
        }
    }

    final class zzb implements ServiceConnection {
        private zzb(WearableListenerService arg1) {
            super();
        }

        zzb(WearableListenerService arg1, zzk arg2) {
            this(arg1);
        }

        public final void onServiceConnected(ComponentName arg1, IBinder arg2) {
        }

        public final void onServiceDisconnected(ComponentName arg1) {
        }
    }

    final class zzc extends Handler {
        private boolean started;
        private final zzb zzal;

        zzc(WearableListenerService arg2, Looper arg3) {
            this.zzak = arg2;
            super(arg3);
            this.zzal = new zzb(this.zzak, null);
        }

        public final void dispatchMessage(Message arg2) {
            this.zzb();
            try {
                super.dispatchMessage(arg2);
            }
            catch(Throwable v2) {
                if(!this.hasMessages(0)) {
                    this.zzb("dispatch");
                }

                throw v2;
            }

            if(!this.hasMessages(0)) {
                this.zzb("dispatch");
            }
        }

        final void quit() {
            this.getLooper().quit();
            this.zzb("quit");
        }

        @SuppressLint(value={"UntrackedBindService"}) private final void zzb() {
            __monitor_enter(this);
            try {
                if(!this.started) {
                    goto label_5;
                }
            }
            catch(Throwable v0) {
                goto label_33;
            }

            __monitor_exit(this);
            return;
            try {
            label_5:
                if(Log.isLoggable("WearableLS", 2)) {
                    String v1 = String.valueOf(WearableListenerService.zza(this.zzak));
                    StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 13);
                    v3.append("bindService: ");
                    v3.append(v1);
                    Log.v("WearableLS", v3.toString());
                }

                this.zzak.bindService(WearableListenerService.zzb(this.zzak), this.zzal, 1);
                this.started = true;
            }
            catch(Throwable v0) {
            label_33:
                __monitor_exit(this);
                throw v0;
            }

            __monitor_exit(this);
        }

        @SuppressLint(value={"UntrackedBindService"}) private final void zzb(String arg5) {
            __monitor_enter(this);
            try {
                if(this.started) {
                    goto label_5;
                }
            }
            catch(Throwable v5) {
                goto label_42;
            }

            __monitor_exit(this);
            return;
            try {
            label_5:
                if(Log.isLoggable("WearableLS", 2)) {
                    String v1 = String.valueOf(WearableListenerService.zza(this.zzak));
                    StringBuilder v3 = new StringBuilder(String.valueOf(arg5).length() + 17 + String.valueOf(v1).length());
                    v3.append("unbindService: ");
                    v3.append(arg5);
                    v3.append(", ");
                    v3.append(v1);
                    Log.v("WearableLS", v3.toString());
                }

                try {
                    this.zzak.unbindService(this.zzal);
                    goto label_37;
                }
                catch(RuntimeException v5_1) {
                    try {
                        Log.e("WearableLS", "Exception when unbinding from local service", ((Throwable)v5_1));
                    label_37:
                        this.started = false;
                    }
                    catch(Throwable v5) {
                    label_42:
                        __monitor_exit(this);
                        throw v5;
                    }
                }
            }
            catch(Throwable v5) {
                goto label_42;
            }

            __monitor_exit(this);
        }
    }

    final class zzd extends zzen {
        private volatile int zzam;

        zzd(WearableListenerService arg1, zzk arg2) {
            this(arg1);
        }

        private zzd(WearableListenerService arg1) {
            this.zzak = arg1;
            super();
            this.zzam = -1;
        }

        public final void onConnectedNodes(List arg3) {
            this.zza(new zzp(this, arg3), "onConnectedNodes", arg3);
        }

        private final boolean zza(Runnable arg6, String arg7, Object arg8) {
            int v1 = 3;
            if(Log.isLoggable("WearableLS", v1)) {
                Log.d("WearableLS", String.format("%s: %s %s", arg7, WearableListenerService.zza(this.zzak).toString(), arg8));
            }

            int v7 = Binder.getCallingUid();
            if(v7 != this.zzam) {
                if(!zzhp.zza(this.zzak).zze("com.google.android.wearable.app.cn") || !UidVerifier.uidHasPackageName(this.zzak, v7, "com.google.android.wearable.app.cn")) {
                    if(UidVerifier.isGooglePlayServicesUid(this.zzak, v7)) {
                    }
                    else {
                        goto label_38;
                    }
                }

                this.zzam = v7;
                goto label_21;
            }
            else {
            label_21:
                v7 = 1;
                goto label_48;
            label_38:
                StringBuilder v1_2 = new StringBuilder(57);
                v1_2.append("Caller is not GooglePlayServices; caller UID: ");
                v1_2.append(v7);
                Log.e("WearableLS", v1_2.toString());
                v7 = 0;
            }

        label_48:
            if(v7 == 0) {
                return 0;
            }

            Object v7_1 = WearableListenerService.zzd(this.zzak);
            __monitor_enter(v7_1);
            try {
                if(WearableListenerService.zze(this.zzak)) {
                    __monitor_exit(v7_1);
                    return 0;
                }

                WearableListenerService.zzf(this.zzak).post(arg6);
                __monitor_exit(v7_1);
                return 1;
            label_64:
                __monitor_exit(v7_1);
            }
            catch(Throwable v6) {
                goto label_64;
            }

            throw v6;
        }

        public final void zza(DataHolder arg7) {
            zzl v0 = new zzl(this, arg7);
            try {
                String v2 = String.valueOf(arg7);
                int v3 = arg7.getCount();
                StringBuilder v5 = new StringBuilder(String.valueOf(v2).length() + 18);
                v5.append(v2);
                v5.append(", rows=");
                v5.append(v3);
                if(this.zza(((Runnable)v0), "onDataItemChanged", v5.toString())) {
                    return;
                }
            }
            catch(Throwable v0_1) {
                arg7.close();
                throw v0_1;
            }

            arg7.close();
        }

        public final void zza(zzah arg3) {
            this.zza(new zzq(this, arg3), "onConnectedCapabilityChanged", arg3);
        }

        public final void zza(zzaw arg3) {
            this.zza(new zzt(this, arg3), "onChannelEvent", arg3);
        }

        public final void zza(zzfe arg3) {
            this.zza(new zzm(this, arg3), "onMessageReceived", arg3);
        }

        public final void zza(zzfo arg3) {
            this.zza(new zzn(this, arg3), "onPeerConnected", arg3);
        }

        public final void zza(zzi arg3) {
            this.zza(new zzs(this, arg3), "onEntityUpdate", arg3);
        }

        public final void zza(com.google.android.gms.wearable.internal.zzl arg3) {
            this.zza(new zzr(this, arg3), "onNotificationReceived", arg3);
        }

        public final void zzb(zzfo arg3) {
            this.zza(new zzo(this, arg3), "onPeerDisconnected", arg3);
        }
    }

    public static final String BIND_LISTENER_INTENT_ACTION = "com.google.android.gms.wearable.BIND_LISTENER";
    private ComponentName service;
    private zzc zzad;
    private IBinder zzae;
    private Intent zzaf;
    private Looper zzag;
    private final Object zzah;
    private boolean zzai;
    private zzas zzaj;

    public WearableListenerService() {
        super();
        this.zzah = new Object();
        this.zzaj = new zzas(new zza(this, null));
    }

    public Looper getLooper() {
        if(this.zzag == null) {
            HandlerThread v0 = new HandlerThread("WearableListenerService");
            v0.start();
            this.zzag = v0.getLooper();
        }

        return this.zzag;
    }

    public final IBinder onBind(Intent arg2) {
        if("com.google.android.gms.wearable.BIND_LISTENER".equals(arg2.getAction())) {
            return this.zzae;
        }

        return null;
    }

    public void onCapabilityChanged(CapabilityInfo arg1) {
    }

    public void onChannelClosed(com.google.android.gms.wearable.Channel arg1, int arg2, int arg3) {
    }

    public void onChannelClosed(Channel arg1, int arg2, int arg3) {
    }

    public void onChannelOpened(com.google.android.gms.wearable.Channel arg1) {
    }

    public void onChannelOpened(Channel arg1) {
    }

    public void onConnectedNodes(List arg1) {
    }

    public void onCreate() {
        super.onCreate();
        this.service = new ComponentName(((Context)this), this.getClass().getName());
        if(Log.isLoggable("WearableLS", 3)) {
            String v1 = String.valueOf(this.service);
            StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 10);
            v3.append("onCreate: ");
            v3.append(v1);
            Log.d("WearableLS", v3.toString());
        }

        this.zzad = new zzc(this, this.getLooper());
        this.zzaf = new Intent("com.google.android.gms.wearable.BIND_LISTENER");
        this.zzaf.setComponent(this.service);
        this.zzae = new zzd(this, null);
    }

    public void onDataChanged(DataEventBuffer arg1) {
    }

    public void onDestroy() {
        if(Log.isLoggable("WearableLS", 3)) {
            String v1 = String.valueOf(this.service);
            StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 11);
            v3.append("onDestroy: ");
            v3.append(v1);
            Log.d("WearableLS", v3.toString());
        }

        Object v0 = this.zzah;
        __monitor_enter(v0);
        try {
            this.zzai = true;
            if(this.zzad == null) {
                goto label_28;
            }

            this.zzad.quit();
            __monitor_exit(v0);
        }
        catch(Throwable v1_1) {
            goto label_43;
        }

        super.onDestroy();
        return;
        try {
        label_28:
            String v2 = String.valueOf(this.service);
            StringBuilder v4 = new StringBuilder(String.valueOf(v2).length() + 111);
            v4.append("onDestroy: mServiceHandler not set, did you override onCreate() but forget to call super.onCreate()? component=");
            v4.append(v2);
            throw new IllegalStateException(v4.toString());
        label_43:
            __monitor_exit(v0);
        }
        catch(Throwable v1_1) {
            goto label_43;
        }

        throw v1_1;
    }

    public void onEntityUpdate(com.google.android.gms.wearable.zzb arg1) {
    }

    public void onInputClosed(com.google.android.gms.wearable.Channel arg1, int arg2, int arg3) {
    }

    public void onInputClosed(Channel arg1, int arg2, int arg3) {
    }

    public void onMessageReceived(MessageEvent arg1) {
    }

    public void onNotificationReceived(com.google.android.gms.wearable.zzd arg1) {
    }

    public void onOutputClosed(com.google.android.gms.wearable.Channel arg1, int arg2, int arg3) {
    }

    public void onOutputClosed(Channel arg1, int arg2, int arg3) {
    }

    public void onPeerConnected(Node arg1) {
    }

    public void onPeerDisconnected(Node arg1) {
    }

    static ComponentName zza(WearableListenerService arg0) {
        return arg0.service;
    }

    static Intent zzb(WearableListenerService arg0) {
        return arg0.zzaf;
    }

    static zzas zzc(WearableListenerService arg0) {
        return arg0.zzaj;
    }

    static Object zzd(WearableListenerService arg0) {
        return arg0.zzah;
    }

    static boolean zze(WearableListenerService arg0) {
        return arg0.zzai;
    }

    static zzc zzf(WearableListenerService arg0) {
        return arg0.zzad;
    }
}

