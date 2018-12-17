package com.google.android.gms.common.wrappers;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Binder;
import android.os.Process;
import android.support.v4.content.d;
import android.support.v4.f.j;
import android.util.Log;
import com.google.android.gms.common.util.PlatformVersion;

public class PackageManagerWrapper {
    private final Context zzjp;

    public PackageManagerWrapper(Context arg1) {
        super();
        this.zzjp = arg1;
    }

    public boolean allowApiAccess(String arg1, int arg2) {
        return 1;
    }

    public void checkCallerIsNotInstantApp() {
        if(!this.isCallerInstantApp()) {
            return;
        }

        throw new SecurityException("This operation is not supported for instant apps.");
    }

    public int checkCallingOrSelfPermission(String arg2) {
        return this.zzjp.checkCallingOrSelfPermission(arg2);
    }

    public int checkCallingOrSelfPermissionAndAppOps(String arg2) {
        return d.b(this.zzjp, arg2);
    }

    public int checkCallingPermission(String arg2) {
        return this.zzjp.checkCallingPermission(arg2);
    }

    @Deprecated public int checkCallingPermission(String arg1, String arg2) {
        return this.checkPermission(arg1, arg2);
    }

    public int checkCallingPermissionAndAppOps(String arg2, String arg3) {
        return d.a(this.zzjp, arg2, arg3);
    }

    public void checkPackage(int arg4, String arg5) {
        if(this.uidHasPackageName(arg4, arg5)) {
            return;
        }

        StringBuilder v2 = new StringBuilder(String.valueOf(arg5).length() + 39);
        v2.append("Package ");
        v2.append(arg5);
        v2.append(" does not belong to ");
        v2.append(arg4);
        throw new SecurityException(v2.toString());
    }

    public int checkPermission(String arg2, String arg3) {
        return this.zzjp.getPackageManager().checkPermission(arg2, arg3);
    }

    public int checkPermission(String arg2, int arg3, int arg4) {
        return this.zzjp.checkPermission(arg2, arg3, arg4);
    }

    @Deprecated public int checkPermission(String arg1, int arg2, int arg3, String arg4) {
        return this.checkPermission(arg1, arg2, arg3);
    }

    public int checkPermissionAndAppOps(String arg2, int arg3, int arg4, String arg5) {
        return d.a(this.zzjp, arg2, arg3, arg4, arg5);
    }

    public ApplicationInfo getApplicationInfo(String arg2, int arg3) {
        return this.zzjp.getPackageManager().getApplicationInfo(arg2, arg3);
    }

    public CharSequence getApplicationLabel(String arg4) {
        return this.zzjp.getPackageManager().getApplicationLabel(this.zzjp.getPackageManager().getApplicationInfo(arg4, 0));
    }

    public j getApplicationLabelAndIcon(String arg3) {
        ApplicationInfo v3 = this.zzjp.getPackageManager().getApplicationInfo(arg3, 0);
        return j.a(this.zzjp.getPackageManager().getApplicationLabel(v3), this.zzjp.getPackageManager().getApplicationIcon(v3));
    }

    public ComponentName getCallingActivity(Activity arg1) {
        return arg1.getCallingActivity();
    }

    public String getCallingPackage(Activity arg5) {
        ComponentName v5 = arg5.getCallingActivity();
        String v0 = null;
        if(v5 == null) {
            return v0;
        }

        String v1 = v5.getPackageName();
        if(v1 == null) {
            String v5_1 = String.valueOf(v5);
            StringBuilder v3 = new StringBuilder(String.valueOf(v5_1).length() + 54);
            v3.append("getCallingPackage(): Couldn\'t get a package name from ");
            v3.append(v5_1);
            Log.e("PackageManagerWrapper", v3.toString());
            return v0;
        }

        return v1;
    }

    protected Context getContext() {
        return this.zzjp;
    }

    public PackageInfo getPackageInfo(String arg2, int arg3) {
        return this.zzjp.getPackageManager().getPackageInfo(arg2, arg3);
    }

    public String[] getPackagesForUid(int arg2) {
        return this.zzjp.getPackageManager().getPackagesForUid(arg2);
    }

    public boolean isCallerInstantApp() {
        if(Binder.getCallingUid() == Process.myUid()) {
            return InstantApps.isInstantApp(this.zzjp);
        }

        if(PlatformVersion.isAtLeastO()) {
            String v0 = this.zzjp.getPackageManager().getNameForUid(Binder.getCallingUid());
            if(v0 != null) {
                return this.zzjp.getPackageManager().isInstantApp(v0);
            }
        }

        return 0;
    }

    public boolean isInstantAppUid(int arg1) {
        return 0;
    }

    @TargetApi(value=19) public boolean uidHasPackageName(int arg5, String arg6) {
        if(PlatformVersion.isAtLeastKitKat()) {
            try {
                this.zzjp.getSystemService("appops").checkPackage(arg5, arg6);
                return 1;
            }
            catch(SecurityException ) {
                return 0;
            }
        }

        String[] v5 = this.zzjp.getPackageManager().getPackagesForUid(arg5);
        if(arg6 != null && v5 != null) {
            int v0 = 0;
            while(v0 < v5.length) {
                if(arg6.equals(v5[v0])) {
                    return 1;
                }
                else {
                    ++v0;
                    continue;
                }

                return 0;
            }
        }

        return 0;
    }
}

