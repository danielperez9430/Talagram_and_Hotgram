package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzu;
import java.util.List;

public final class zzbg {
    final zzbt zzadj;

    zzbg(zzbt arg1) {
        super();
        this.zzadj = arg1;
    }

    @VisibleForTesting final Bundle zza(String arg4, zzu arg5) {
        Bundle v4_1;
        this.zzadj.zzgn().zzaf();
        Bundle v0 = null;
        if(arg5 == null) {
            this.zzadj.zzgo().zzjg().zzbx("Attempting to use Install Referrer Service while it is not initialized");
            return v0;
        }

        Bundle v1 = new Bundle();
        v1.putString("package_name", arg4);
        try {
            v4_1 = arg5.zza(v1);
            if(v4_1 != null) {
                return v4_1;
            }

            this.zzadj.zzgo().zzjd().zzbx("Install Referrer Service returned a null response");
            return v0;
        }
        catch(Exception v4) {
            this.zzadj.zzgo().zzjd().zzg("Exception occurred while retrieving the Install Referrer", v4.getMessage());
            return v0;
        }

        return v4_1;
    }

    protected final void zzcd(String arg5) {
        if(arg5 != null) {
            if(arg5.isEmpty()) {
            }
            else {
                this.zzadj.zzgn().zzaf();
                if(!this.zzka()) {
                    this.zzadj.zzgo().zzjj().zzbx("Install Referrer Reporter is not available");
                    return;
                }
                else {
                    this.zzadj.zzgo().zzjj().zzbx("Install Referrer Reporter is initializing");
                    zzbh v0 = new zzbh(this, arg5);
                    this.zzadj.zzgn().zzaf();
                    Intent v5 = new Intent("com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE");
                    v5.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.externalreferrer.GetInstallReferrerService"));
                    PackageManager v1 = this.zzadj.getContext().getPackageManager();
                    if(v1 == null) {
                        this.zzadj.zzgo().zzjg().zzbx("Failed to obtain Package Manager to verify binding conditions");
                        return;
                    }
                    else {
                        List v1_1 = v1.queryIntentServices(v5, 0);
                        if(v1_1 != null && !v1_1.isEmpty()) {
                            Object v1_2 = v1_1.get(0);
                            if(((ResolveInfo)v1_2).serviceInfo != null) {
                                String v2 = ((ResolveInfo)v1_2).serviceInfo.packageName;
                                if(((ResolveInfo)v1_2).serviceInfo.name != null && ("com.android.vending".equals(v2)) && (this.zzka())) {
                                    Intent v1_3 = new Intent(v5);
                                    try {
                                        boolean v5_2 = ConnectionTracker.getInstance().bindService(this.zzadj.getContext(), v1_3, ((ServiceConnection)v0), 1);
                                        zzar v0_1 = this.zzadj.zzgo().zzjj();
                                        String v1_4 = "Install Referrer Service is";
                                        arg5 = v5_2 ? "available" : "not available";
                                        v0_1.zzg(v1_4, arg5);
                                        return;
                                    }
                                    catch(Exception v5_1) {
                                        this.zzadj.zzgo().zzjd().zzg("Exception occurred while binding to Install Referrer Service", v5_1.getMessage());
                                        return;
                                    }
                                }

                                this.zzadj.zzgo().zzjj().zzbx("Play Store missing or incompatible. Version 8.3.73 or later required");
                            }

                            return;
                        }

                        this.zzadj.zzgo().zzjj().zzbx("Play Service for fetching Install Referrer is unavailable on device");
                        return;
                    }
                }
            }
        }

        this.zzadj.zzgo().zzjj().zzbx("Install Referrer Reporter was called with invalid app package name");
    }

    @VisibleForTesting private final boolean zzka() {
        boolean v0 = false;
        try {
            PackageManagerWrapper v1_1 = Wrappers.packageManager(this.zzadj.getContext());
            if(v1_1 == null) {
                this.zzadj.zzgo().zzjj().zzbx("Failed to retrieve Package Manager to check Play Store compatibility");
                return 0;
            }

            if(v1_1.getPackageInfo("com.android.vending", 128).versionCode < 80837300) {
                return v0;
            }
        }
        catch(Exception v1) {
            this.zzadj.zzgo().zzjj().zzg("Failed to retrieve Play Store version", v1);
            return 0;
        }

        return true;
    }
}

