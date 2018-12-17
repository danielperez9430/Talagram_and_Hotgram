package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.CheckReturnValue;

@CheckReturnValue public class GoogleSignatureVerifier {
    private final Context mContext;
    private static GoogleSignatureVerifier zzbv;

    private GoogleSignatureVerifier(Context arg1) {
        super();
        this.mContext = arg1.getApplicationContext();
    }

    @Deprecated public Set getAllGoogleSignatures(boolean arg4) {
        Set v4 = arg4 ? GoogleCertificates.zzd() : GoogleCertificates.zze();
        HashSet v0 = new HashSet(v4.size());
        try {
            Iterator v4_2 = v4.iterator();
            while(v4_2.hasNext()) {
                ((Set)v0).add(ObjectWrapper.unwrap(v4_2.next().getBytesWrapped()));
            }
        }
        catch(RemoteException v4_1) {
            Log.e("GoogleSignatureVerifier", "Failed to get Google certificates from remote", ((Throwable)v4_1));
        }

        return ((Set)v0);
    }

    public static GoogleSignatureVerifier getInstance(Context arg2) {
        Preconditions.checkNotNull(arg2);
        Class v0 = GoogleSignatureVerifier.class;
        __monitor_enter(v0);
        try {
            if(GoogleSignatureVerifier.zzbv == null) {
                GoogleCertificates.init(arg2);
                GoogleSignatureVerifier.zzbv = new GoogleSignatureVerifier(arg2);
            }

            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            try {
            label_13:
                __monitor_exit(v0);
            }
            catch(Throwable v2) {
                goto label_13;
            }

            throw v2;
        }

        return GoogleSignatureVerifier.zzbv;
    }

    public boolean isChimeraSigned(PackageManager arg2, PackageInfo arg3) {
        String v2 = arg3.packageName;
        arg3.packageName = "com.google.android.gms.chimera";
        boolean v0 = this.isPackageGoogleSigned(arg3);
        arg3.packageName = v2;
        return v0;
    }

    public boolean isGooglePublicSignedPackage(PackageInfo arg4, boolean arg5) {
        if(arg4 != null && arg4.signatures != null) {
            CertData[] v5 = arg5 ? zzd.zzbg : new CertData[]{zzd.zzbg[0]};
            CertData v4 = GoogleSignatureVerifier.zza(arg4, v5);
            if(v4 == null) {
                return 0;
            }

            return 1;
        }

        return 0;
    }

    public boolean isGooglePublicSignedPackage(PackageInfo arg4) {
        if(arg4 == null) {
            return 0;
        }

        if(this.isGooglePublicSignedPackage(arg4, false)) {
            return 1;
        }

        if(this.isGooglePublicSignedPackage(arg4, true)) {
            if(GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext)) {
                return 1;
            }
            else {
                Log.w("GoogleSignatureVerifier", "Test-keys aren\'t accepted on this build.");
            }
        }

        return 0;
    }

    @Deprecated public boolean isGooglePublicSignedPackage(PackageManager arg1, PackageInfo arg2) {
        return this.isGooglePublicSignedPackage(arg2);
    }

    public boolean isPackageGoogleSigned(PackageInfo arg1) {
        zzg v1 = this.zza(arg1);
        v1.zzi();
        return v1.zzbl;
    }

    @Deprecated public boolean isPackageGoogleSigned(PackageManager arg1, PackageInfo arg2) {
        return this.isPackageGoogleSigned(arg2);
    }

    @Deprecated public boolean isPackageGoogleSigned(PackageManager arg1, String arg2) {
        return this.isPackageGoogleSigned(arg2);
    }

    public boolean isPackageGoogleSigned(String arg1) {
        zzg v1 = this.zzf(arg1);
        v1.zzi();
        return v1.zzbl;
    }

    public boolean isUidGoogleSigned(int arg1) {
        zzg v1 = this.zzb(arg1);
        v1.zzi();
        return v1.zzbl;
    }

    @Deprecated public boolean isUidGoogleSigned(PackageManager arg1, int arg2) {
        return this.isUidGoogleSigned(arg2);
    }

    @VisibleForTesting public static void resetForTests() {
        Class v0 = GoogleSignatureVerifier.class;
        __monitor_enter(v0);
        GoogleSignatureVerifier v1 = null;
        try {
            GoogleSignatureVerifier.zzbv = v1;
        }
        catch(Throwable v1_1) {
            __monitor_exit(v0);
            throw v1_1;
        }

        __monitor_exit(v0);
    }

    @Deprecated public void verifyPackageIsGoogleSigned(PackageManager arg1, String arg2) {
        this.verifyPackageIsGoogleSigned(arg2);
    }

    public void verifyPackageIsGoogleSigned(String arg1) {
        this.zzf(arg1).zzh();
    }

    public void verifyUidIsGoogleSigned(int arg1) {
        this.zzb(arg1).zzh();
    }

    @Deprecated public void verifyUidIsGoogleSigned(PackageManager arg1, int arg2) {
        this.verifyUidIsGoogleSigned(arg2);
    }

    private static CertData zza(PackageInfo arg3, CertData[] arg4) {
        CertData v1 = null;
        if(arg3.signatures == null) {
            return v1;
        }

        if(arg3.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return v1;
        }

        int v2 = 0;
        zzb v0 = new zzb(arg3.signatures[0].toByteArray());
        while(v2 < arg4.length) {
            if(arg4[v2].equals(v0)) {
                return arg4[v2];
            }

            ++v2;
        }

        return v1;
    }

    private final zzg zza(PackageInfo arg7) {
        String v7;
        boolean v0 = GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext);
        if(arg7 == null) {
            v7 = "null pkg";
        }
        else if(arg7.signatures.length != 1) {
            v7 = "single cert required";
        }
        else {
            zzb v1 = new zzb(arg7.signatures[0].toByteArray());
            String v2 = arg7.packageName;
            zzg v4 = GoogleCertificates.zza(v2, ((CertData)v1), v0);
            if((v4.zzbl) && arg7.applicationInfo != null && (arg7.applicationInfo.flags & 2) != 0) {
                if((v0) && !GoogleCertificates.zza(v2, ((CertData)v1), false).zzbl) {
                    return v4;
                }

                v7 = "debuggable release cert app rejected";
                goto label_4;
            }

            return v4;
        }

    label_4:
        return zzg.zze(v7);
    }

    private final zzg zzb(int arg5) {
        String[] v5 = Wrappers.packageManager(this.mContext).getPackagesForUid(arg5);
        if(v5 != null) {
            if(v5.length == 0) {
            }
            else {
                zzg v0 = null;
                int v1 = v5.length;
                int v2 = 0;
                while(true) {
                    if(v2 < v1) {
                        v0 = this.zzf(v5[v2]);
                        if(v0.zzbl) {
                            return v0;
                        }
                        else {
                            ++v2;
                            continue;
                        }
                    }
                    else {
                        return v0;
                    }

                    goto label_19;
                }

                return v0;
            }
        }

    label_19:
        return zzg.zze("no pkgs");
    }

    private final zzg zzf(String arg3) {
        PackageInfo v0;
        try {
            v0 = Wrappers.packageManager(this.mContext).getPackageInfo(arg3, 64);
        }
        catch(PackageManager$NameNotFoundException ) {
            String v0_1 = "no pkg ";
            arg3 = String.valueOf(arg3);
            arg3 = arg3.length() != 0 ? v0_1.concat(arg3) : new String(v0_1);
            return zzg.zze(arg3);
        }

        return this.zza(v0);
    }
}

