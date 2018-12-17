package com.google.android.gms.security;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources$NotFoundException;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.reflect.Method;

public class ProviderInstaller {
    public interface ProviderInstallListener {
        void onProviderInstallFailed(int arg1, Intent arg2);

        void onProviderInstalled();
    }

    public static final String PROVIDER_NAME = "GmsCore_OpenSSL";
    private static final Object sLock;
    private static final GoogleApiAvailabilityLight zzacw;
    private static Method zzacx;

    static {
        ProviderInstaller.zzacw = GoogleApiAvailabilityLight.getInstance();
        ProviderInstaller.sLock = new Object();
        ProviderInstaller.zzacx = null;
    }

    public ProviderInstaller() {
        super();
    }

    public static void installIfNeeded(Context arg8) {
        Preconditions.checkNotNull(arg8, "Context must not be null");
        ProviderInstaller.zzacw.verifyGooglePlayServicesIsAvailable(arg8, 11925000);
        int v0 = 8;
        int v1 = 6;
        try {
            arg8 = GooglePlayServicesUtilLight.getRemoteContext(arg8);
            if(arg8 != null) {
                goto label_18;
            }
        }
        catch(Resources$NotFoundException ) {
            if(Log.isLoggable("ProviderInstaller", v1)) {
                Log.e("ProviderInstaller", "Failed to get remote context - resource not found");
            }

            throw new GooglePlayServicesNotAvailableException(v0);
        }

        if(Log.isLoggable("ProviderInstaller", v1)) {
            Log.e("ProviderInstaller", "Failed to get remote context");
        }

        throw new GooglePlayServicesNotAvailableException(v0);
    label_18:
        Object v2 = ProviderInstaller.sLock;
        __monitor_enter(v2);
        try {
            if(ProviderInstaller.zzacx == null) {
                ProviderInstaller.zzacx = arg8.getClassLoader().loadClass("com.google.android.gms.common.security.ProviderInstallerImpl").getMethod("insertProvider", Context.class);
            }

            ProviderInstaller.zzacx.invoke(null, arg8);
        }
        catch(Exception v8) {
            Throwable v3 = v8.getCause();
            if(Log.isLoggable("ProviderInstaller", v1)) {
                String v8_1 = v3 == null ? v8.getMessage() : v3.getMessage();
                String v1_1 = "ProviderInstaller";
                String v3_1 = "Failed to install provider: ";
                v8_1 = String.valueOf(v8_1);
                v8_1 = v8_1.length() != 0 ? v3_1.concat(v8_1) : new String(v3_1);
                Log.e(v1_1, v8_1);
            }

            throw new GooglePlayServicesNotAvailableException(v0);
        }

        __monitor_exit(v2);
        return;
        __monitor_exit(v2);
        throw v8_2;
    }

    public static void installIfNeededAsync(Context arg1, ProviderInstallListener arg2) {
        Preconditions.checkNotNull(arg1, "Context must not be null");
        Preconditions.checkNotNull(arg2, "Listener must not be null");
        Preconditions.checkMainThread("Must be called on the UI thread");
        new zza(arg1, arg2).execute(new Void[0]);
    }

    static GoogleApiAvailabilityLight zzdn() {
        return ProviderInstaller.zzacw;
    }
}

