package com.google.android.gms.maps.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class zzbz {
    private static final String TAG = "zzbz";
    @SuppressLint(value={"StaticFieldLeak"}) private static Context zzcj;
    private static zze zzck;

    static {
    }

    public zzbz() {
        super();
    }

    public static zze zza(Context arg3) {
        IInterface v0_3;
        Preconditions.checkNotNull(arg3);
        if(zzbz.zzck != null) {
            return zzbz.zzck;
        }

        int v0 = GooglePlayServicesUtil.isGooglePlayServicesAvailable(arg3, 12451000);
        if(v0 == 0) {
            Log.i(zzbz.TAG, "Making Creator dynamically");
            Object v0_1 = zzbz.zza(zzbz.zzb(arg3).getClassLoader(), "com.google.android.gms.maps.internal.CreatorImpl");
            if(v0_1 == null) {
                zze v0_2 = null;
            }
            else {
                IInterface v1 = ((IBinder)v0_1).queryLocalInterface("com.google.android.gms.maps.internal.ICreator");
                if((v1 instanceof zze)) {
                    v0_3 = v1;
                }
                else {
                    zzf v0_4 = new zzf(((IBinder)v0_1));
                }
            }

            zzbz.zzck = ((zze)v0_3);
            try {
                zzbz.zzck.zza(ObjectWrapper.wrap(zzbz.zzb(arg3).getResources()), GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE);
            }
            catch(RemoteException v3) {
                throw new RuntimeRemoteException(v3);
            }

            return zzbz.zzck;
        }

        throw new GooglePlayServicesNotAvailableException(v0);
    }

    private static Object zza(ClassLoader arg2, String arg3) {
        try {
            return zzbz.zza(Preconditions.checkNotNull(arg2).loadClass(arg3));
        }
        catch(ClassNotFoundException ) {
            String v0 = "Unable to find dynamic class ";
            arg3 = String.valueOf(arg3);
            arg3 = arg3.length() != 0 ? v0.concat(arg3) : new String(v0);
            throw new IllegalStateException(arg3);
        }
    }

    private static Object zza(Class arg3) {
        String v3;
        String v1;
        try {
            return arg3.newInstance();
        }
        catch(IllegalAccessException ) {
            v1 = "Unable to call the default constructor of ";
            v3 = String.valueOf(arg3.getName());
            v3 = v3.length() != 0 ? v1.concat(v3) : new String(v1);
            throw new IllegalStateException(v3);
        }
        catch(InstantiationException ) {
            v1 = "Unable to instantiate the dynamic class ";
            v3 = String.valueOf(arg3.getName());
            v3 = v3.length() != 0 ? v1.concat(v3) : new String(v1);
            throw new IllegalStateException(v3);
        }
    }

    private static Context zzb(Context arg1) {
        if(zzbz.zzcj != null) {
            return zzbz.zzcj;
        }

        arg1 = zzbz.zzc(arg1);
        zzbz.zzcj = arg1;
        return arg1;
    }

    private static Context zzc(Context arg3) {
        try {
            return DynamiteModule.load(arg3, DynamiteModule.PREFER_REMOTE, "com.google.android.gms.maps_dynamite").getModuleContext();
        }
        catch(Throwable v0) {
            Log.e(zzbz.TAG, "Failed to load maps module, use legacy", v0);
            return GooglePlayServicesUtil.getRemoteContext(arg3);
        }
    }
}

