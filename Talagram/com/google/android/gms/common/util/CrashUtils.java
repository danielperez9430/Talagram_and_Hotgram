package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Debug;
import android.os.DropBoxManager;
import android.provider.Settings$Secure;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.annotation.concurrent.GuardedBy;

public final class CrashUtils {
    @Retention(value=RetentionPolicy.SOURCE) @public interface ErrorDialogData {
        public static final int AVG_CRASH_FREQ = 2;
        public static final int BINDER_CRASH = 268435456;
        public static final int DYNAMITE_CRASH = 536870912;
        public static final int FORCED_SHUSHED_BY_WRAPPER = 4;
        public static final int NONE = 0;
        public static final int POPUP_FREQ = 1;
        public static final int SUPPRESSED = 1073741824;

    }

    private static final String[] zzzc;
    private static DropBoxManager zzzd;
    private static boolean zzze;
    private static boolean zzzf;
    private static boolean zzzg;
    private static int zzzh;
    @GuardedBy(value="CrashUtils.class") private static int zzzi;
    @GuardedBy(value="CrashUtils.class") private static int zzzj;

    static {
        CrashUtils.zzzc = new String[]{"android.", "com.android.", "dalvik.", "java.", "javax."};
        CrashUtils.zzzd = null;
        CrashUtils.zzze = false;
        CrashUtils.zzzh = -1;
        CrashUtils.zzzi = 0;
        CrashUtils.zzzj = 0;
    }

    public CrashUtils() {
        super();
    }

    public static boolean addDynamiteErrorToDropBox(Context arg1, Throwable arg2) {
        return CrashUtils.addErrorToDropBoxInternal(arg1, arg2, 536870912);
    }

    @Deprecated public static boolean addErrorToDropBox(Context arg0, Throwable arg1) {
        return CrashUtils.addDynamiteErrorToDropBox(arg0, arg1);
    }

    public static boolean addErrorToDropBoxInternal(Context arg3, Throwable arg4, int arg5) {
        boolean v4_1;
        try {
            Preconditions.checkNotNull(arg3);
            Preconditions.checkNotNull(arg4);
            if(!CrashUtils.isPackageSide()) {
                return 0;
            }

            if(!CrashUtils.zzdb()) {
                arg4 = CrashUtils.zza(arg4);
                if(arg4 == null) {
                    return 0;
                }
            }

            return CrashUtils.zza(arg3, Log.getStackTraceString(arg4), ProcessUtils.getMyProcessName(), arg5, arg4);
        }
        catch(Exception v3) {
            try {
                v4_1 = CrashUtils.zzdb();
            }
            catch(Exception v4) {
                Log.e("CrashUtils", "Error determining which process we\'re running in!", ((Throwable)v4));
                v4_1 = false;
            }

            if(!v4_1) {
                Log.e("CrashUtils", "Error adding exception to DropBox!", ((Throwable)v3));
                return 0;
            }

            throw v3;
        }
    }

    public static boolean addErrorToDropBoxInternal(Context arg1, String arg2, String arg3, int arg4) {
        return CrashUtils.zza(arg1, arg2, arg3, arg4, null);
    }

    private static boolean isPackageSide() {
        if(CrashUtils.zzze) {
            return CrashUtils.zzzf;
        }

        return 0;
    }

    public static boolean isSystemClassPrefixInternal(String arg5) {
        if(TextUtils.isEmpty(((CharSequence)arg5))) {
            return 0;
        }

        String[] v0 = CrashUtils.zzzc;
        int v2 = v0.length;
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            if(arg5.startsWith(v0[v3])) {
                return 1;
            }
        }

        return 0;
    }

    @VisibleForTesting public static void setTestVariables(DropBoxManager arg2, boolean arg3, boolean arg4, int arg5) {
        Class v0 = CrashUtils.class;
        __monitor_enter(v0);
        try {
            CrashUtils.zzze = true;
            CrashUtils.zzzd = arg2;
            CrashUtils.zzzg = arg3;
            CrashUtils.zzzf = arg4;
            CrashUtils.zzzh = arg5;
            CrashUtils.zzzi = 0;
            CrashUtils.zzzj = 0;
        }
        catch(Throwable v2) {
            __monitor_exit(v0);
            throw v2;
        }

        __monitor_exit(v0);
    }

    private static boolean zza(Context arg4, String arg5, String arg6, int arg7, Throwable arg8) {
        DropBoxManager v8_1;
        int v8;
        int v1;
        Class v0 = CrashUtils.class;
        __monitor_enter(v0);
        try {
            Preconditions.checkNotNull(arg4);
            if(CrashUtils.isPackageSide()) {
                if(Strings.isEmptyOrWhitespace(arg5)) {
                }
                else {
                    v1 = arg5.hashCode();
                    v8 = arg8 == null ? CrashUtils.zzzj : arg8.hashCode();
                    if(CrashUtils.zzzi == v1 && CrashUtils.zzzj == v8) {
                        goto label_18;
                    }

                    goto label_20;
                }
            }

            goto label_41;
        }
        catch(Throwable v4) {
            goto label_44;
        }

    label_18:
        __monitor_exit(v0);
        return 0;
        try {
        label_20:
            CrashUtils.zzzi = v1;
            CrashUtils.zzzj = v8;
            if(CrashUtils.zzzd != null) {
                v8_1 = CrashUtils.zzzd;
            }
            else {
                Object v8_2 = arg4.getSystemService("dropbox");
            }

            if(v8_1 != null) {
                if(!v8_1.isTagEnabled("system_app_crash")) {
                }
                else {
                    v8_1.addText("system_app_crash", CrashUtils.zza(arg4, arg5, arg6, arg7));
                    goto label_36;
                }
            }

            goto label_39;
        }
        catch(Throwable v4) {
        label_44:
            __monitor_exit(v0);
            throw v4;
        }

    label_36:
        __monitor_exit(v0);
        return 1;
    label_39:
        __monitor_exit(v0);
        return 0;
    label_41:
        __monitor_exit(v0);
        return 0;
    }

    @VisibleForTesting private static Throwable zza(Throwable arg14) {
        Throwable v4;
        Class v0 = CrashUtils.class;
        __monitor_enter(v0);
        try {
            LinkedList v1 = new LinkedList();
            while(arg14 != null) {
                v1.push(arg14);
                arg14 = arg14.getCause();
            }

            arg14 = null;
            v4 = arg14;
            int v3;
            for(v3 = 0; !v1.isEmpty(); v3 = v8) {
                Object v5 = v1.pop();
                StackTraceElement[] v6 = ((Throwable)v5).getStackTrace();
                ArrayList v7 = new ArrayList();
                v7.add(new StackTraceElement(v5.getClass().getName(), "<filtered>", "<filtered>", 1));
                int v5_1 = v6.length;
                int v8 = v3;
                for(v3 = 0; v3 < v5_1; ++v3) {
                    StackTraceElement v9 = v6[v3];
                    String v10 = v9.getClassName();
                    String v12 = v9.getFileName();
                    int v12_1 = (TextUtils.isEmpty(((CharSequence)v12))) || !v12.startsWith(":com.google.android.gms") ? 0 : 1;
                    v8 |= v12_1;
                    if(v12_1 == 0 && !CrashUtils.isSystemClassPrefixInternal(v10)) {
                        v9 = new StackTraceElement("<filtered>", "<filtered>", "<filtered>", 1);
                    }

                    v7.add(v9);
                }

                Throwable v3_1 = v4 == null ? new Throwable("<filtered>") : new Throwable("<filtered>", v4);
                v4 = v3_1;
                v4.setStackTrace(v7.toArray(new StackTraceElement[0]));
            }
        }
        catch(Throwable arg14) {
            goto label_74;
        }

        if(v3 == 0) {
            __monitor_exit(v0);
            return arg14;
        }

        __monitor_exit(v0);
        return v4;
    label_74:
        __monitor_exit(v0);
        throw arg14;
    }

    @VisibleForTesting private static String zza(Context arg7, String arg8, String arg9, int arg10) {
        InputStreamReader v9_2;
        Process v7_3;
        InputStreamReader v8;
        int v7_1;
        int v5;
        PackageInfo v3_1;
        String v2;
        int v9;
        StringBuilder v1;
        Class v0 = CrashUtils.class;
        __monitor_enter(v0);
        try {
            v1 = new StringBuilder(1024);
            v1.append("Process: ");
            v1.append(Strings.nullToEmpty(arg9));
            v1.append("\n");
            v1.append("Package: com.google.android.gms");
            v9 = 12451009;
            v2 = "12.4.51 (020308-{{cl}})";
            if(!CrashUtils.zzdb()) {
                goto label_36;
            }

            try {
                v3_1 = Wrappers.packageManager(arg7).getPackageInfo(arg7.getPackageName(), 0);
                v5 = v3_1.versionCode;
            }
            catch(Exception v3) {
                goto label_33;
            }
        }
        catch(Throwable v7) {
            goto label_180;
        }

        try {
            if(v3_1.versionName != null) {
                v2 = v3_1.versionName;
            }

            goto label_26;
        }
        catch(Exception v9_1) {
            v3 = v9_1;
            v9 = v5;
        }

        try {
        label_33:
            Log.w("CrashUtils", "Error while trying to get the package information! Using static version.", ((Throwable)v3));
            goto label_36;
        }
        catch(Throwable v7) {
            goto label_180;
        }

    label_26:
        v9 = v5;
        try {
        label_36:
            v1.append(" v");
            v1.append(v9);
            if(!TextUtils.isEmpty(((CharSequence)v2))) {
                if((v2.contains("(")) && !v2.contains(")")) {
                    if(v2.endsWith("-")) {
                        v2 = String.valueOf(v2).concat("111111111");
                    }

                    v2 = String.valueOf(v2).concat(")");
                }

                v1.append(" (");
                v1.append(v2);
                v1.append(")");
            }

            v1.append("\n");
            v1.append("Build: ");
            v1.append(Build.FINGERPRINT);
            v1.append("\n");
            if(Debug.isDebuggerConnected()) {
                v1.append("Debugger: Connected\n");
            }

            if(arg10 != 0) {
                v1.append("DD-EDD: ");
                v1.append(arg10);
                v1.append("\n");
            }

            v1.append("\n");
            if(!TextUtils.isEmpty(((CharSequence)arg8))) {
                v1.append(arg8);
            }

            if(CrashUtils.zzdb()) {
                arg8 = "logcat_for_system_app_crash";
                v7_1 = CrashUtils.zzzh >= 0 ? CrashUtils.zzzh : Settings$Secure.getInt(arg7.getContentResolver(), arg8, 0);
            }
            else {
                v7_1 = 0;
            }

            if(v7_1 <= 0) {
                goto label_176;
            }

            v1.append("\n");
            v8 = null;
        }
        catch(Throwable v7) {
            goto label_180;
        }

        try {
            v7_3 = new ProcessBuilder(new String[]{"/system/bin/logcat", "-v", "time", "-b", "events", "-b", "system", "-b", "main", "-b", "crash", "-t", String.valueOf(v7_1)}).redirectErrorStream(true).start();
        }
        catch(IOException v7_2) {
            goto label_167;
        }

        try {
            v7_3.getOutputStream().close();
            goto label_145;
        }
        catch(IOException ) {
            try {
            label_145:
                v7_3.getErrorStream().close();
                goto label_147;
            }
            catch(IOException ) {
                try {
                label_147:
                    v9_2 = new InputStreamReader(v7_3.getInputStream());
                    v7_1 = 8192;
                }
                catch(IOException v7_2) {
                    goto label_167;
                }
            }
        }

        try {
            char[] v7_4 = new char[v7_1];
            while(true) {
                int v8_1 = v9_2.read(v7_4);
                if(v8_1 <= 0) {
                    break;
                }

                v1.append(v7_4, 0, v8_1);
            }
        }
        catch(IOException v7_2) {
            goto label_162;
        }
        catch(Throwable v7) {
            goto label_159;
        }

        try {
            v9_2.close();
            goto label_176;
        }
        catch(IOException ) {
            goto label_176;
        }
        catch(Throwable v7) {
            goto label_180;
        }

    label_162:
        v8 = v9_2;
        try {
        label_167:
            Log.e("CrashUtils", "Error running logcat", ((Throwable)v7_2));
            if(v8 == null) {
                goto label_176;
            }
        }
        catch(Throwable v7) {
            goto label_173;
        }

        try {
            v8.close();
            goto label_176;
        }
        catch(IOException ) {
            goto label_176;
        }
        catch(Throwable v7) {
            goto label_180;
        }

    label_159:
        v8 = v9_2;
    label_173:
        if(v8 != null) {
            try {
                v8.close();
                goto label_175;
            }
            catch(IOException ) {
            label_175:
                throw v7;
            }
        }

        goto label_175;
    label_176:
        String v7_5 = v1.toString();
        goto label_177;
    label_180:
        __monitor_exit(v0);
        throw v7;
    label_177:
        __monitor_exit(v0);
        return v7_5;
    }

    private static boolean zzdb() {
        if(CrashUtils.zzze) {
            return CrashUtils.zzzg;
        }

        return 0;
    }
}

