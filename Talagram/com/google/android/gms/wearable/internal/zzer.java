package com.google.android.gms.wearable.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;

final class zzer {
    private final Map zzeb;

    zzer() {
        super();
        this.zzeb = new HashMap();
    }

    public final void zza(IBinder arg11) {
        IInterface v11_2;
        Map v0 = this.zzeb;
        __monitor_enter(v0);
        if(arg11 == null) {
            zzep v11 = null;
        }
        else {
            try {
                IInterface v1 = arg11.queryLocalInterface("com.google.android.gms.wearable.internal.IWearableService");
                if((v1 instanceof zzep)) {
                    v11_2 = v1;
                }
                else {
                    zzeq v11_3 = new zzeq(arg11);
                }

            label_14:
                zzgz v1_1 = new zzgz();
                Iterator v2 = this.zzeb.entrySet().iterator();
                while(v2.hasNext()) {
                    Object v3 = v2.next();
                    Object v4 = ((Map$Entry)v3).getValue();
                    try {
                        ((zzep)v11_2).zza(((zzek)v1_1), new zzd(((zzhk)v4)));
                        if(!Log.isLoggable("WearableClient", 3)) {
                            continue;
                        }

                        String v6 = String.valueOf(((Map$Entry)v3).getKey());
                        String v7 = String.valueOf(v4);
                        StringBuilder v9 = new StringBuilder(String.valueOf(v6).length() + 27 + String.valueOf(v7).length());
                        v9.append("onPostInitHandler: added: ");
                        v9.append(v6);
                        v9.append("/");
                        v9.append(v7);
                        Log.d("WearableClient", v9.toString());
                    }
                    catch(RemoteException ) {
                        String v3_1 = String.valueOf(((Map$Entry)v3).getKey());
                        String v4_1 = String.valueOf(v4);
                        StringBuilder v7_1 = new StringBuilder(String.valueOf(v3_1).length() + 32 + String.valueOf(v4_1).length());
                        v7_1.append("onPostInitHandler: Didn\'t add: ");
                        v7_1.append(v3_1);
                        v7_1.append("/");
                        v7_1.append(v4_1);
                        Log.w("WearableClient", v7_1.toString());
                    }
                }

                __monitor_exit(v0);
                return;
            label_75:
                __monitor_exit(v0);
                goto label_76;
            }
            catch(Throwable v11_1) {
                goto label_75;
            }
        }

        goto label_14;
    label_76:
        throw v11_1;
    }

    public final void zza(zzhg arg7, ResultHolder arg8, Object arg9) {
        Map v0 = this.zzeb;
        __monitor_enter(v0);
        try {
            Object v1 = this.zzeb.remove(arg9);
            int v2 = 2;
            if(v1 == null) {
                if(Log.isLoggable("WearableClient", v2)) {
                    String v9 = String.valueOf(arg9);
                    StringBuilder v2_1 = new StringBuilder(String.valueOf(v9).length() + 25);
                    v2_1.append("remove Listener unknown: ");
                    v2_1.append(v9);
                    Log.v("WearableClient", v2_1.toString());
                }

                arg8.setResult(new Status(4002));
                __monitor_exit(v0);
                return;
            }

            ((zzhk)v1).clear();
            if(Log.isLoggable("WearableClient", v2)) {
                String v3 = String.valueOf(arg9);
                StringBuilder v5 = new StringBuilder(String.valueOf(v3).length() + 24);
                v5.append("service.removeListener: ");
                v5.append(v3);
                Log.v("WearableClient", v5.toString());
            }

            arg7.getService().zza(new zzet(this.zzeb, arg9, arg8), new zzfw(((zzem)v1)));
            __monitor_exit(v0);
            return;
        label_53:
            __monitor_exit(v0);
        }
        catch(Throwable v7) {
            goto label_53;
        }

        throw v7;
    }

    public final void zza(zzhg arg6, ResultHolder arg7, Object arg8, zzhk arg9) {
        Map v0 = this.zzeb;
        __monitor_enter(v0);
        try {
            int v2 = 2;
            if(this.zzeb.get(arg8) != null) {
                if(Log.isLoggable("WearableClient", v2)) {
                    String v8 = String.valueOf(arg8);
                    StringBuilder v1 = new StringBuilder(String.valueOf(v8).length() + 20);
                    v1.append("duplicate listener: ");
                    v1.append(v8);
                    Log.v("WearableClient", v1.toString());
                }

                arg7.setResult(new Status(4001));
                __monitor_exit(v0);
                return;
            }

            if(Log.isLoggable("WearableClient", v2)) {
                String v2_1 = String.valueOf(arg8);
                StringBuilder v4 = new StringBuilder(String.valueOf(v2_1).length() + 14);
                v4.append("new listener: ");
                v4.append(v2_1);
                Log.v("WearableClient", v4.toString());
            }

            this.zzeb.put(arg8, arg9);
            try {
                arg6.getService().zza(new zzes(this.zzeb, arg8, arg7), new zzd(arg9));
            }
            catch(RemoteException v6_1) {
                if(Log.isLoggable("WearableClient", 3)) {
                    String v9 = String.valueOf(arg8);
                    StringBuilder v2_2 = new StringBuilder(String.valueOf(v9).length() + 39);
                    v2_2.append("addListener failed, removing listener: ");
                    v2_2.append(v9);
                    Log.d("WearableClient", v2_2.toString());
                }

                this.zzeb.remove(arg8);
                throw v6_1;
            }

            __monitor_exit(v0);
            return;
        label_74:
            __monitor_exit(v0);
        }
        catch(Throwable v6) {
            goto label_74;
        }

        throw v6;
    }
}

