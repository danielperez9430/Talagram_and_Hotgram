package com.google.android.gms.common;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.ICertData$Stub;
import com.google.android.gms.common.internal.ICertData;
import com.google.android.gms.common.internal.IGoogleCertificatesApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule$LoadingException;
import com.google.android.gms.dynamite.DynamiteModule;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.CheckReturnValue;
import javax.annotation.concurrent.GuardedBy;

@CheckReturnValue final class GoogleCertificates {
    abstract class CertData extends Stub {
        private int zzbc;

        protected CertData(byte[] arg3) {
            super();
            boolean v0 = arg3.length == 25 ? true : false;
            Preconditions.checkArgument(v0);
            this.zzbc = Arrays.hashCode(arg3);
        }

        public boolean equals(Object arg4) {
            if(arg4 != null && ((arg4 instanceof ICertData))) {
                try {
                    if(((ICertData)arg4).getHashCode() != this.hashCode()) {
                        return 0;
                    }

                    IObjectWrapper v4_1 = ((ICertData)arg4).getBytesWrapped();
                    if(v4_1 == null) {
                        return 0;
                    }

                    return Arrays.equals(this.getBytes(), ObjectWrapper.unwrap(v4_1));
                }
                catch(RemoteException v4) {
                    Log.e("GoogleCertificates", "Failed to get Google certificates from remote", ((Throwable)v4));
                }
            }

            return 0;
        }

        abstract byte[] getBytes();

        public IObjectWrapper getBytesWrapped() {
            return ObjectWrapper.wrap(this.getBytes());
        }

        public int getHashCode() {
            return this.hashCode();
        }

        public int hashCode() {
            return this.zzbc;
        }

        protected static byte[] zzd(String arg1) {
            try {
                return arg1.getBytes("ISO-8859-1");
            }
            catch(UnsupportedEncodingException v1) {
                throw new AssertionError(v1);
            }
        }
    }

    private static volatile IGoogleCertificatesApi zzax;
    private static final Object zzay;
    private static Context zzaz;
    @GuardedBy(value="GoogleCertificates.class") private static Set zzba;
    @GuardedBy(value="GoogleCertificates.class") private static Set zzbb;

    static {
        GoogleCertificates.zzay = new Object();
    }

    static void init(Context arg2) {
        Class v0 = GoogleCertificates.class;
        __monitor_enter(v0);
        try {
            if(GoogleCertificates.zzaz != null) {
                goto label_9;
            }
            else if(arg2 != null) {
                GoogleCertificates.zzaz = arg2.getApplicationContext();
                goto label_7;
            }

            goto label_12;
        }
        catch(Throwable v2) {
            goto label_15;
        }

    label_7:
        __monitor_exit(v0);
        return;
        try {
        label_9:
            Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
        }
        catch(Throwable v2) {
        label_15:
            __monitor_exit(v0);
            throw v2;
        }

    label_12:
        __monitor_exit(v0);
    }

    static zzg zza(String arg3, CertData arg4, boolean arg5) {
        String v4;
        try {
            GoogleCertificates.zzc();
        }
        catch(LoadingException v3) {
            v4 = "module init";
            goto label_27;
        }

        Preconditions.checkNotNull(GoogleCertificates.zzaz);
        GoogleCertificatesQuery v0 = new GoogleCertificatesQuery(arg3, arg4, arg5);
        try {
            if(!GoogleCertificates.zzax.isGoogleOrPlatformSigned(v0, ObjectWrapper.wrap(GoogleCertificates.zzaz.getPackageManager()))) {
                goto label_13;
            }

            goto label_11;
        }
        catch(RemoteException v3_1) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", ((Throwable)v3_1));
            v4 = "module call";
        }

    label_27:
        return zzg.zza(v4, ((Throwable)v3_1));
    label_11:
        return zzg.zzg();
    label_13:
        boolean v0_1 = true;
        if((arg5) || !GoogleCertificates.zza(arg3, arg4, true).zzbl) {
            v0_1 = false;
        }
        else {
        }

        return zzg.zza(arg3, arg4, arg5, v0_1);
    }

    private static Set zza(IBinder[] arg4) {
        int v0 = arg4.length;
        HashSet v1 = new HashSet(v0);
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            ICertData v3 = Stub.asInterface(arg4[v2]);
            if(v3 != null) {
                ((Set)v1).add(v3);
            }
        }

        return ((Set)v1);
    }

    private static void zzc() {
        if(GoogleCertificates.zzax != null) {
            return;
        }

        Preconditions.checkNotNull(GoogleCertificates.zzaz);
        Object v0 = GoogleCertificates.zzay;
        __monitor_enter(v0);
        try {
            if(GoogleCertificates.zzax == null) {
                GoogleCertificates.zzax = com.google.android.gms.common.internal.IGoogleCertificatesApi$Stub.asInterface(DynamiteModule.load(GoogleCertificates.zzaz, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING, "com.google.android.gms.googlecertificates").instantiate("com.google.android.gms.common.GoogleCertificatesImpl"));
            }

            __monitor_exit(v0);
            return;
        label_20:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_20;
        }

        throw v1;
    }

    static Set zzd() {
        IObjectWrapper v1_4;
        Set v1_1;
        Class v0 = GoogleCertificates.class;
        __monitor_enter(v0);
        try {
            if(GoogleCertificates.zzba == null) {
                goto label_7;
            }

            v1_1 = GoogleCertificates.zzba;
        }
        catch(Throwable v1) {
            goto label_38;
        }

        __monitor_exit(v0);
        return v1_1;
        try {
        label_7:
            GoogleCertificates.zzc();
        }
        catch(LoadingException v1_2) {
            try {
                Log.e("GoogleCertificates", "Failed to load com.google.android.gms.googlecertificates", ((Throwable)v1_2));
                v1_1 = Collections.emptySet();
            }
            catch(Throwable v1) {
                goto label_38;
            }

            __monitor_exit(v0);
            return v1_1;
        }

        try {
            v1_4 = GoogleCertificates.zzax.getGoogleCertificates();
            if(v1_4 != null) {
                goto label_17;
            }

            Log.e("GoogleCertificates", "Failed to get Google certificates from remote");
            v1_1 = Collections.emptySet();
        }
        catch(RemoteException v1_3) {
            goto label_24;
        }

        __monitor_exit(v0);
        return v1_1;
        try {
        label_17:
            GoogleCertificates.zzba = GoogleCertificates.zza(ObjectWrapper.unwrap(v1_4));
        }
        catch(RemoteException v1_3) {
            try {
            label_24:
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", ((Throwable)v1_3));
                v1_1 = Collections.emptySet();
            }
            catch(Throwable v1) {
                goto label_38;
            }

            __monitor_exit(v0);
            return v1_1;
        }

        try {
            v1_1 = GoogleCertificates.zzba;
        }
        catch(Throwable v1) {
        label_38:
            __monitor_exit(v0);
            throw v1;
        }

        __monitor_exit(v0);
        return v1_1;
    }

    static Set zze() {
        IObjectWrapper v1_4;
        Set v1_1;
        Class v0 = GoogleCertificates.class;
        __monitor_enter(v0);
        try {
            if(GoogleCertificates.zzbb == null) {
                goto label_7;
            }

            v1_1 = GoogleCertificates.zzbb;
        }
        catch(Throwable v1) {
            goto label_38;
        }

        __monitor_exit(v0);
        return v1_1;
        try {
        label_7:
            GoogleCertificates.zzc();
        }
        catch(LoadingException v1_2) {
            try {
                Log.e("GoogleCertificates", "Failed to load com.google.android.gms.googlecertificates", ((Throwable)v1_2));
                v1_1 = Collections.emptySet();
            }
            catch(Throwable v1) {
                goto label_38;
            }

            __monitor_exit(v0);
            return v1_1;
        }

        try {
            v1_4 = GoogleCertificates.zzax.getGoogleReleaseCertificates();
            if(v1_4 != null) {
                goto label_17;
            }

            Log.e("GoogleCertificates", "Failed to get Google certificates from remote");
            v1_1 = Collections.emptySet();
        }
        catch(RemoteException v1_3) {
            goto label_24;
        }

        __monitor_exit(v0);
        return v1_1;
        try {
        label_17:
            GoogleCertificates.zzbb = GoogleCertificates.zza(ObjectWrapper.unwrap(v1_4));
        }
        catch(RemoteException v1_3) {
            try {
            label_24:
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", ((Throwable)v1_3));
                v1_1 = Collections.emptySet();
            }
            catch(Throwable v1) {
                goto label_38;
            }

            __monitor_exit(v0);
            return v1_1;
        }

        try {
            v1_1 = GoogleCertificates.zzbb;
        }
        catch(Throwable v1) {
        label_38:
            __monitor_exit(v0);
            throw v1;
        }

        __monitor_exit(v0);
        return v1_1;
    }
}

