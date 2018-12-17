package com.google.android.gms.common.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.os.Process;
import android.os.WorkSource;
import android.util.Log;
import com.google.android.gms.common.wrappers.Wrappers;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorkSourceUtil {
    public static final String TAG = "WorkSourceUtil";
    private static final int zzaam;
    private static final Method zzaan;
    private static final Method zzaao;
    private static final Method zzaap;
    private static final Method zzaaq;
    private static final Method zzaar;
    private static final Method zzaas;
    private static final Method zzaat;

    static {
        WorkSourceUtil.zzaam = Process.myUid();
        WorkSourceUtil.zzaan = WorkSourceUtil.zzdf();
        WorkSourceUtil.zzaao = WorkSourceUtil.zzdg();
        WorkSourceUtil.zzaap = WorkSourceUtil.zzdh();
        WorkSourceUtil.zzaaq = WorkSourceUtil.zzdi();
        WorkSourceUtil.zzaar = WorkSourceUtil.zzdj();
        WorkSourceUtil.zzaas = WorkSourceUtil.zzdk();
        WorkSourceUtil.zzaat = WorkSourceUtil.zzdl();
    }

    private WorkSourceUtil() {
        super();
    }

    public static void add(WorkSource arg4, int arg5, String arg6) {
        if(WorkSourceUtil.zzaao != null) {
            if(arg6 == null) {
                arg6 = "";
            }

            try {
                WorkSourceUtil.zzaao.invoke(arg4, Integer.valueOf(arg5), arg6);
                return;
            }
            catch(Exception v4) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", ((Throwable)v4));
                return;
            }
        }

        if(WorkSourceUtil.zzaan != null) {
            try {
                WorkSourceUtil.zzaan.invoke(arg4, Integer.valueOf(arg5));
                return;
            }
            catch(Exception v4) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", ((Throwable)v4));
            }
        }
    }

    public static WorkSource fromPackage(Context arg3, String arg4) {
        String v1;
        String v3_1;
        ApplicationInfo v3;
        WorkSource v0 = null;
        if(arg3 == null) {
            return v0;
        }

        if(arg3.getPackageManager() == null) {
            return v0;
        }

        if(arg4 == null) {
            return v0;
        }

        try {
            v3 = Wrappers.packageManager(arg3).getApplicationInfo(arg4, 0);
            if(v3 != null) {
                goto label_21;
            }
        }
        catch(PackageManager$NameNotFoundException ) {
            v3_1 = "WorkSourceUtil";
            v1 = "Could not find package: ";
            arg4 = String.valueOf(arg4);
            arg4 = arg4.length() != 0 ? v1.concat(arg4) : new String(v1);
            Log.e(v3_1, arg4);
            return v0;
        }

        v3_1 = "WorkSourceUtil";
        v1 = "Could not get applicationInfo from package: ";
        arg4 = String.valueOf(arg4);
        arg4 = arg4.length() != 0 ? v1.concat(arg4) : new String(v1);
        Log.e(v3_1, arg4);
        return v0;
    label_21:
        return WorkSourceUtil.fromUidAndPackage(v3.uid, arg4);
    }

    public static WorkSource fromPackageAndModuleExperimentalPi(Context arg7, String arg8, String arg9) {
        WorkSource v0 = null;
        if(arg7 != null && arg7.getPackageManager() != null && arg9 != null) {
            if(arg8 == null) {
            }
            else {
                int v7 = WorkSourceUtil.zzc(arg7, arg8);
                if(v7 < 0) {
                    return v0;
                }
                else {
                    v0 = new WorkSource();
                    if(WorkSourceUtil.zzaas != null) {
                        if(WorkSourceUtil.zzaat == null) {
                        }
                        else {
                            try {
                                Object v1 = WorkSourceUtil.zzaas.invoke(v0);
                                int v5 = 2;
                                if(v7 != WorkSourceUtil.zzaam) {
                                    Method v3 = WorkSourceUtil.zzaat;
                                    v3.invoke(v1, Integer.valueOf(v7), arg8);
                                }

                                Method v7_2 = WorkSourceUtil.zzaat;
                                v7_2.invoke(v1, Integer.valueOf(WorkSourceUtil.zzaam), arg9);
                            }
                            catch(Exception v7_1) {
                                Log.w("WorkSourceUtil", "Unable to assign chained blame through WorkSource", ((Throwable)v7_1));
                            }

                            return v0;
                        }
                    }

                    WorkSourceUtil.add(v0, v7, arg8);
                    return v0;
                }
            }
        }

        Log.w("WorkSourceUtil", "Unexpected null arguments");
        return v0;
    }

    public static WorkSource fromUidAndPackage(int arg1, String arg2) {
        WorkSource v0 = new WorkSource();
        WorkSourceUtil.add(v0, arg1, arg2);
        return v0;
    }

    public static int get(WorkSource arg3, int arg4) {
        if(WorkSourceUtil.zzaaq != null) {
            try {
                return WorkSourceUtil.zzaaq.invoke(arg3, Integer.valueOf(arg4)).intValue();
            }
            catch(Exception v3) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", ((Throwable)v3));
            }
        }

        return 0;
    }

    public static String getName(WorkSource arg3, int arg4) {
        if(WorkSourceUtil.zzaar != null) {
            try {
                return WorkSourceUtil.zzaar.invoke(arg3, Integer.valueOf(arg4));
            }
            catch(Exception v3) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", ((Throwable)v3));
            }
        }

        return null;
    }

    public static List getNames(WorkSource arg5) {
        int v0 = 0;
        int v1 = arg5 == null ? 0 : WorkSourceUtil.size(arg5);
        if(v1 == 0) {
            return Collections.emptyList();
        }

        ArrayList v2 = new ArrayList();
        while(v0 < v1) {
            String v3 = WorkSourceUtil.getName(arg5, v0);
            if(!Strings.isEmptyOrWhitespace(v3)) {
                ((List)v2).add(v3);
            }

            ++v0;
        }

        return ((List)v2);
    }

    public static boolean hasWorkSourcePermission(Context arg3) {
        if(arg3 == null) {
            return 0;
        }

        if(arg3.getPackageManager() == null) {
            return 0;
        }

        if(Wrappers.packageManager(arg3).checkPermission("android.permission.UPDATE_DEVICE_STATS", arg3.getPackageName()) == 0) {
            return 1;
        }

        return 0;
    }

    public static int size(WorkSource arg3) {
        if(WorkSourceUtil.zzaap != null) {
            try {
                return WorkSourceUtil.zzaap.invoke(arg3).intValue();
            }
            catch(Exception v3) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", ((Throwable)v3));
            }
        }

        return 0;
    }

    private static int zzc(Context arg3, String arg4) {
        String v1;
        String v3_1;
        ApplicationInfo v3;
        int v0 = -1;
        try {
            v3 = Wrappers.packageManager(arg3).getApplicationInfo(arg4, 0);
            if(v3 != null) {
                goto label_16;
            }
        }
        catch(PackageManager$NameNotFoundException ) {
            v3_1 = "WorkSourceUtil";
            v1 = "Could not find package: ";
            arg4 = String.valueOf(arg4);
            arg4 = arg4.length() != 0 ? v1.concat(arg4) : new String(v1);
            Log.e(v3_1, arg4);
            return v0;
        }

        v3_1 = "WorkSourceUtil";
        v1 = "Could not get applicationInfo from package: ";
        arg4 = String.valueOf(arg4);
        arg4 = arg4.length() != 0 ? v1.concat(arg4) : new String(v1);
        Log.e(v3_1, arg4);
        return v0;
    label_16:
        return v3.uid;
    }

    private static Method zzdf() {
        Method v0;
        try {
            v0 = WorkSource.class.getMethod("add", Integer.TYPE);
        }
        catch(Exception ) {
            v0 = null;
        }

        return v0;
    }

    private static Method zzdg() {
        Method v0;
        if(PlatformVersion.isAtLeastJellyBeanMR2()) {
            try {
                v0 = WorkSource.class.getMethod("add", Integer.TYPE, String.class);
            }
            catch(Exception ) {
            label_14:
                v0 = null;
            }
        }
        else {
            goto label_14;
        }

        return v0;
    }

    private static Method zzdh() {
        Method v0;
        try {
            v0 = WorkSource.class.getMethod("size");
        }
        catch(Exception ) {
            v0 = null;
        }

        return v0;
    }

    private static Method zzdi() {
        Method v0;
        try {
            v0 = WorkSource.class.getMethod("get", Integer.TYPE);
        }
        catch(Exception ) {
            v0 = null;
        }

        return v0;
    }

    private static Method zzdj() {
        Method v0;
        if(PlatformVersion.isAtLeastJellyBeanMR2()) {
            try {
                v0 = WorkSource.class.getMethod("getName", Integer.TYPE);
            }
            catch(Exception ) {
            label_11:
                v0 = null;
            }
        }
        else {
            goto label_11;
        }

        return v0;
    }

    private static final Method zzdk() {
        if(PlatformVersion.isAtLeastP()) {
            try {
                Method v0_1 = WorkSource.class.getMethod("createWorkChain");
                return v0_1;
            }
            catch(Exception v0) {
                Log.w("WorkSourceUtil", "Missing WorkChain API createWorkChain", ((Throwable)v0));
            }
        }

        return null;
    }

    @SuppressLint(value={"PrivateApi"}) private static final Method zzdl() {
        if(PlatformVersion.isAtLeastP()) {
            try {
                Method v0_1 = Class.forName("android.os.WorkSource$WorkChain").getMethod("addNode", Integer.TYPE, String.class);
                return v0_1;
            }
            catch(Exception v0) {
                Log.w("WorkSourceUtil", "Missing WorkChain class", ((Throwable)v0));
            }
        }

        return null;
    }
}

