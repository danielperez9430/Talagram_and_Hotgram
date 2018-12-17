package com.google.android.gms.wearable.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager$NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.BaseGmsClient$ConnectionProgressReportCallbacks;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.CapabilityApi$CapabilityListener;
import com.google.android.gms.wearable.ChannelApi$ChannelListener;
import com.google.android.gms.wearable.DataApi$DataListener;
import com.google.android.gms.wearable.MessageApi$MessageListener;
import com.google.android.gms.wearable.PutDataRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import javax.annotation.Nullable;

public final class zzhg extends GmsClient {
    private final ExecutorService zzew;
    private final zzer zzex;
    private final zzer zzey;
    private final zzer zzez;
    private final zzer zzfa;
    private final zzer zzfb;
    private final zzer zzfc;
    private final zzer zzfd;
    private final zzer zzfe;
    private final zzhp zzff;

    public zzhg(Context arg9, Looper arg10, ConnectionCallbacks arg11, OnConnectionFailedListener arg12, ClientSettings arg13) {
        this(arg9, arg10, arg11, arg12, arg13, Executors.newCachedThreadPool(), zzhp.zza(arg9));
    }

    @VisibleForTesting private zzhg(Context arg8, Looper arg9, ConnectionCallbacks arg10, OnConnectionFailedListener arg11, ClientSettings arg12, ExecutorService arg13, zzhp arg14) {
        super(arg8, arg9, 14, arg12, arg10, arg11);
        this.zzex = new zzer();
        this.zzey = new zzer();
        this.zzez = new zzer();
        this.zzfa = new zzer();
        this.zzfb = new zzer();
        this.zzfc = new zzer();
        this.zzfd = new zzer();
        this.zzfe = new zzer();
        this.zzew = Preconditions.checkNotNull(arg13);
        this.zzff = arg14;
    }

    public final void connect(ConnectionProgressReportCallbacks arg7) {
        if(!this.requiresGooglePlayServices()) {
            try {
                Bundle v0 = this.getContext().getPackageManager().getApplicationInfo("com.google.android.wearable.app.cn", 128).metaData;
                int v0_1 = v0 != null ? v0.getInt("com.google.android.wearable.api.version", 0) : 0;
                if(v0_1 >= GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE) {
                    goto label_59;
                }

                int v3 = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
                StringBuilder v5 = new StringBuilder(86);
                v5.append("The Wear OS app is out of date. Requires API version ");
                v5.append(v3);
                v5.append(" but found ");
                v5.append(v0_1);
                Log.w("WearableClient", v5.toString());
                v0_1 = 6;
                Context v2 = this.getContext();
                Context v3_1 = this.getContext();
                Intent v4 = new Intent("com.google.android.wearable.app.cn.UPDATE_ANDROID_WEAR").setPackage("com.google.android.wearable.app.cn");
                if(v3_1.getPackageManager().resolveActivity(v4, 65536) != null) {
                }
                else {
                    v4 = new Intent("android.intent.action.VIEW", Uri.parse("market://details").buildUpon().appendQueryParameter("id", "com.google.android.wearable.app.cn").build());
                }

                this.triggerNotAvailable(arg7, v0_1, PendingIntent.getActivity(v2, 0, v4, 0));
                return;
            }
            catch(PackageManager$NameNotFoundException ) {
                this.triggerNotAvailable(arg7, 16, null);
                return;
            }
        }

    label_59:
        super.connect(arg7);
    }

    protected final IInterface createServiceInterface(IBinder arg3) {
        if(arg3 == null) {
            return null;
        }

        IInterface v0 = arg3.queryLocalInterface("com.google.android.gms.wearable.internal.IWearableService");
        if((v0 instanceof zzep)) {
            return v0;
        }

        return new zzeq(arg3);
    }

    public final int getMinApkVersion() {
        return 12451000;
    }

    protected final String getServiceDescriptor() {
        return "com.google.android.gms.wearable.internal.IWearableService";
    }

    protected final String getStartServiceAction() {
        return "com.google.android.gms.wearable.BIND";
    }

    protected final String getStartServicePackage() {
        if(this.zzff.zze("com.google.android.wearable.app.cn")) {
            return "com.google.android.wearable.app.cn";
        }

        return "com.google.android.gms";
    }

    protected final void onPostInitHandler(int arg4, IBinder arg5, Bundle arg6, int arg7) {
        if(Log.isLoggable("WearableClient", 2)) {
            StringBuilder v2 = new StringBuilder(41);
            v2.append("onPostInitHandler: statusCode ");
            v2.append(arg4);
            Log.v("WearableClient", v2.toString());
        }

        if(arg4 == 0) {
            this.zzex.zza(arg5);
            this.zzey.zza(arg5);
            this.zzez.zza(arg5);
            this.zzfa.zza(arg5);
            this.zzfb.zza(arg5);
            this.zzfc.zza(arg5);
            this.zzfd.zza(arg5);
            this.zzfe.zza(arg5);
        }

        super.onPostInitHandler(arg4, arg5, arg6, arg7);
    }

    public final boolean requiresGooglePlayServices() {
        if(!this.zzff.zze("com.google.android.wearable.app.cn")) {
            return 1;
        }

        return 0;
    }

    public final void zza(ResultHolder arg2, CapabilityListener arg3, ListenerHolder arg4, IntentFilter[] arg5) {
        this.zzfe.zza(this, arg2, arg3, zzhk.zzd(arg4, arg5));
    }

    public final void zza(ResultHolder arg2, CapabilityListener arg3) {
        this.zzfe.zza(this, arg2, arg3);
    }

    public final void zza(ResultHolder arg2, ChannelListener arg3, ListenerHolder arg4, @Nullable String arg5, IntentFilter[] arg6) {
        if(arg5 == null) {
            this.zzez.zza(this, arg2, arg3, zzhk.zzc(arg4, arg6));
            return;
        }

        this.zzez.zza(this, arg2, new zzgc(arg5, arg3), zzhk.zza(arg4, arg5, arg6));
    }

    public final void zza(ResultHolder arg2, ChannelListener arg3, String arg4) {
        if(arg4 == null) {
            this.zzez.zza(this, arg2, arg3);
            return;
        }

        this.zzez.zza(this, arg2, new zzgc(arg4, arg3));
    }

    public final void zza(ResultHolder arg2, DataListener arg3, ListenerHolder arg4, IntentFilter[] arg5) {
        this.zzfa.zza(this, arg2, arg3, zzhk.zza(arg4, arg5));
    }

    public final void zza(ResultHolder arg2, DataListener arg3) {
        this.zzfa.zza(this, arg2, arg3);
    }

    public final void zza(ResultHolder arg2, MessageListener arg3, ListenerHolder arg4, IntentFilter[] arg5) {
        this.zzfb.zza(this, arg2, arg3, zzhk.zzb(arg4, arg5));
    }

    public final void zza(ResultHolder arg2, MessageListener arg3) {
        this.zzfb.zza(this, arg2, arg3);
    }

    public final void zza(ResultHolder arg3, Asset arg4) {
        this.getService().zza(new zzgx(arg3), arg4);
    }

    public final void zza(ResultHolder arg17, PutDataRequest arg18) {
        ParcelFileDescriptor[] v7;
        Object v3;
        zzhg v1 = this;
        ResultHolder v0 = arg17;
        Iterator v2 = arg18.getAssets().entrySet().iterator();
        while(true) {
            if(!v2.hasNext()) {
                goto label_39;
            }

            v3 = v2.next().getValue();
            if(((Asset)v3).getData() != null) {
                continue;
            }

            if(((Asset)v3).getDigest() != null) {
                continue;
            }

            if(((Asset)v3).getFd() != null) {
                continue;
            }

            if(((Asset)v3).getUri() == null) {
                break;
            }
        }

        String v2_1 = String.valueOf(arg18.getUri());
        String v3_1 = String.valueOf(v3);
        StringBuilder v5 = new StringBuilder(String.valueOf(v2_1).length() + 33 + String.valueOf(v3_1).length());
        v5.append("Put for ");
        v5.append(v2_1);
        v5.append(" contains invalid asset: ");
        v5.append(v3_1);
        throw new IllegalArgumentException(v5.toString());
    label_39:
        PutDataRequest v2_2 = PutDataRequest.zza(arg18.getUri());
        v2_2.setData(arg18.getData());
        if(arg18.isUrgent()) {
            v2_2.setUrgent();
        }

        ArrayList v3_2 = new ArrayList();
        Iterator v4 = arg18.getAssets().entrySet().iterator();
        while(v4.hasNext()) {
            Object v5_1 = v4.next();
            Object v6 = ((Map$Entry)v5_1).getValue();
            if(((Asset)v6).getData() != null) {
                try {
                    v7 = ParcelFileDescriptor.createPipe();
                }
                catch(IOException v0_1) {
                    v3_1 = String.valueOf(arg18);
                    v5 = new StringBuilder(String.valueOf(v3_1).length() + 60);
                    v5.append("Unable to create ParcelFileDescriptor for asset in request: ");
                    v5.append(v3_1);
                    throw new IllegalStateException(v5.toString(), v0_1);
                }

                if(Log.isLoggable("WearableClient", 3)) {
                    String v11 = String.valueOf(v6);
                    String v12 = String.valueOf(v7[0]);
                    String v13 = String.valueOf(v7[1]);
                    StringBuilder v15 = new StringBuilder(String.valueOf(v11).length() + 61 + String.valueOf(v12).length() + String.valueOf(v13).length());
                    v15.append("processAssets: replacing data with FD in asset: ");
                    v15.append(v11);
                    v15.append(" read:");
                    v15.append(v12);
                    v15.append(" write:");
                    v15.append(v13);
                    Log.d("WearableClient", v15.toString());
                }

                v2_2.putAsset(((Map$Entry)v5_1).getKey(), Asset.createFromFd(v7[0]));
                FutureTask v7_1 = new FutureTask(new zzhh(v1, v7[1], ((Asset)v6).getData()));
                ((List)v3_2).add(v7_1);
                v1.zzew.submit(((Runnable)v7_1));
                continue;
            }

            if(((Asset)v6).getUri() != null) {
                try {
                    v2_2.putAsset(((Map$Entry)v5_1).getKey(), Asset.createFromFd(this.getContext().getContentResolver().openFileDescriptor(((Asset)v6).getUri(), "r")));
                    continue;
                }
                catch(FileNotFoundException ) {
                    new zzhb(v0, ((List)v3_2)).zza(new zzfu(4005, null));
                    v2_1 = String.valueOf(((Asset)v6).getUri());
                    StringBuilder v4_1 = new StringBuilder(String.valueOf(v2_1).length() + 28);
                    v4_1.append("Couldn\'t resolve asset URI: ");
                    v4_1.append(v2_1);
                    Log.w("WearableClient", v4_1.toString());
                    return;
                }
            }

            v2_2.putAsset(((Map$Entry)v5_1).getKey(), ((Asset)v6));
        }

        this.getService().zza(new zzhb(v0, ((List)v3_2)), v2_2);
    }

    public final void zza(ResultHolder arg13, String arg14, Uri arg15, long arg16, long arg18) {
        // Method was not decompiled
    }

    public final void zza(ResultHolder arg9, String arg10, Uri arg11, boolean arg12) {
        try {
            ExecutorService v0 = this.zzew;
            Preconditions.checkNotNull(arg9);
            Preconditions.checkNotNull(arg10);
            Preconditions.checkNotNull(arg11);
            v0.execute(new zzhi(this, arg11, arg9, arg12, arg10));
            return;
        }
        catch(RuntimeException v10) {
            arg9.setFailedResult(new Status(8));
            throw v10;
        }
    }
}

