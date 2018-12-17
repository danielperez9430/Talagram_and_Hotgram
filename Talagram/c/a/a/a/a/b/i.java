package c.a.a.a.a.b;

import android.annotation.SuppressLint;
import android.app.ActivityManager$MemoryInfo;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Debug;
import android.os.StatFs;
import android.provider.Settings$Secure;
import android.text.TextUtils;
import c.a.a.a.c;
import c.a.a.a.l;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class i {
    final class c.a.a.a.a.b.i$1 implements Comparator {
        c.a.a.a.a.b.i$1() {
            super();
        }

        public int a(File arg3, File arg4) {
            return ((int)(arg3.lastModified() - arg4.lastModified()));
        }

        public int compare(Object arg1, Object arg2) {
            return this.a(((File)arg1), ((File)arg2));
        }
    }

    enum a {
        public static final enum a a;
        public static final enum a b;
        public static final enum a c;
        public static final enum a d;
        public static final enum a e;
        public static final enum a f;
        public static final enum a g;
        public static final enum a h;
        public static final enum a i;
        public static final enum a j;
        private static final Map k;

        static {
            a.a = new a("X86_32", 0);
            a.b = new a("X86_64", 1);
            a.c = new a("ARM_UNKNOWN", 2);
            a.d = new a("PPC", 3);
            a.e = new a("PPC64", 4);
            a.f = new a("ARMV6", 5);
            a.g = new a("ARMV7", 6);
            a.h = new a("UNKNOWN", 7);
            a.i = new a("ARMV7S", 8);
            a.j = new a("ARM64", 9);
            a.l = new a[]{a.a, a.b, a.c, a.d, a.e, a.f, a.g, a.h, a.i, a.j};
            a.k = new HashMap(4);
            a.k.put("armeabi-v7a", a.g);
            a.k.put("armeabi", a.f);
            a.k.put("arm64-v8a", a.j);
            a.k.put("x86", a.a);
        }

        private a(String arg1, int arg2) {
            super(arg1, arg2);
        }

        static a a() {
            String v0 = Build.CPU_ABI;
            if(TextUtils.isEmpty(((CharSequence)v0))) {
                c.h().a("Fabric", "Architecture#getValue()::Build.CPU_ABI returned null or empty");
                return a.h;
            }

            Object v0_1 = a.k.get(v0.toLowerCase(Locale.US));
            if(v0_1 == null) {
                a v0_2 = a.h;
            }

            return ((a)v0_1);
        }

        public static a valueOf(String arg1) {
            return Enum.valueOf(a.class, arg1);
        }

        public static a[] values() {
            // Method was not decompiled
        }
    }

    public static final Comparator a;
    private static Boolean b;
    private static final char[] c;
    private static long d;

    static {
        i.c = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        i.d = -1;
        i.a = new c.a.a.a.a.b.i$1();
    }

    public static int a(Context arg1, String arg2, String arg3) {
        return arg1.getResources().getIdentifier(arg2, arg3, i.j(arg1));
    }

    public static int a() {
        return a.a().ordinal();
    }

    public static int a(Context arg4, boolean arg5) {
        Float v4 = i.c(arg4);
        if(arg5) {
            if(v4 == null) {
            }
            else {
                double v2 = 99;
                if((((double)v4.floatValue())) >= v2) {
                    return 3;
                }
                else if((((double)v4.floatValue())) < v2) {
                    return 2;
                }
                else {
                    return 0;
                }
            }
        }

        return 1;
    }

    static long a(String arg2, String arg3, int arg4) {
        return Long.parseLong(arg2.split(arg3)[0].trim()) * (((long)arg4));
    }

    public static ActivityManager$RunningAppProcessInfo a(String arg2, Context arg3) {
        ActivityManager$RunningAppProcessInfo v0_1;
        List v3 = arg3.getSystemService("activity").getRunningAppProcesses();
        if(v3 != null) {
            Iterator v3_1 = v3.iterator();
            do {
                if(v3_1.hasNext()) {
                    Object v0 = v3_1.next();
                    if(!((ActivityManager$RunningAppProcessInfo)v0).processName.equals(arg2)) {
                        continue;
                    }

                    break;
                }
                else {
                    goto label_12;
                }
            }
            while(true);
        }
        else {
        label_12:
            v0_1 = null;
        }

        return v0_1;
    }

    public static SharedPreferences a(Context arg2) {
        return arg2.getSharedPreferences("com.crashlytics.prefs", 0);
    }

    public static String a(File arg6, String arg7) {
        String v1_1;
        String[] v2_1;
        Closeable v0_1;
        BufferedReader v0;
        Closeable v1 = null;
        if(!arg6.exists()) {
            return v1_1;
        }

        try {
            v0 = new BufferedReader(new FileReader(arg6), 1024);
        }
        catch(Throwable v6) {
            v0_1 = v1;
            goto label_44;
        }
        catch(Exception v7) {
            v0_1 = v1;
            goto label_33;
        }

        try {
            do {
            label_8:
                String v2 = v0.readLine();
                if(v2 == null) {
                    goto label_23;
                }

                v2_1 = Pattern.compile("\\s*:\\s*").split(((CharSequence)v2), 2);
                if(v2_1.length <= 1) {
                    goto label_8;
                }
            }
            while(!v2_1[0].equals(arg7));

            v1_1 = v2_1[1];
            goto label_23;
        }
        catch(Throwable v6) {
        }
        catch(Exception v7) {
            try {
            label_33:
                l v2_2 = c.h();
                v2_2.e("Fabric", "Error parsing " + arg6, ((Throwable)v7));
                goto label_23;
            }
            catch(Throwable v6) {
            }
        }

    label_44:
        i.a(v0_1, "Failed to close system file reader.");
        throw v6;
    label_23:
        i.a(v0_1, "Failed to close system file reader.");
        return v1_1;
    }

    public static void a(Closeable arg2, String arg3) {
        if(arg2 != null) {
            try {
                arg2.close();
            }
            catch(IOException v2) {
                c.h().e("Fabric", arg3, ((Throwable)v2));
            }
        }
    }

    public static String a(InputStream arg1) {
        Scanner v1 = new Scanner(arg1).useDelimiter("\\A");
        String v1_1 = v1.hasNext() ? v1.next() : "";
        return v1_1;
    }

    private static String a(InputStream arg3, String arg4) {
        try {
            MessageDigest v4 = MessageDigest.getInstance(arg4);
            byte[] v0 = new byte[1024];
            while(true) {
                int v1 = arg3.read(v0);
                if(v1 == -1) {
                    break;
                }

                v4.update(v0, 0, v1);
            }

            return i.a(v4.digest());
        }
        catch(Exception v3) {
            c.h().e("Fabric", "Could not calculate hash for app icon.", ((Throwable)v3));
            return "";
        }
    }

    public static String a(byte[] arg6) {
        char[] v0 = new char[arg6.length * 2];
        int v1;
        for(v1 = 0; v1 < arg6.length; ++v1) {
            int v2 = arg6[v1] & 255;
            int v3 = v1 * 2;
            v0[v3] = i.c[v2 >>> 4];
            v0[v3 + 1] = i.c[v2 & 15];
        }

        return new String(v0);
    }

    public static String a(String arg1) {
        return i.a(arg1, "SHA-1");
    }

    private static String a(String arg0, String arg1) {
        return i.a(arg0.getBytes(), arg1);
    }

    private static String a(byte[] arg4, String arg5) {
        MessageDigest v0;
        try {
            v0 = MessageDigest.getInstance(arg5);
        }
        catch(NoSuchAlgorithmException v4) {
            l v0_1 = c.h();
            v0_1.e("Fabric", "Could not create hashing algorithm: " + arg5 + ", returning empty string.", ((Throwable)v4));
            return "";
        }

        v0.update(arg4);
        return i.a(v0.digest());
    }

    public static String a(String[] arg7) {
        String v0 = null;
        if(arg7 != null) {
            if(arg7.length == 0) {
            }
            else {
                ArrayList v1 = new ArrayList();
                int v2 = arg7.length;
                int v3;
                for(v3 = 0; v3 < v2; ++v3) {
                    String v4 = arg7[v3];
                    if(v4 != null) {
                        ((List)v1).add(v4.replace("-", "").toLowerCase(Locale.US));
                    }
                }

                Collections.sort(((List)v1));
                StringBuilder v7 = new StringBuilder();
                Iterator v1_1 = ((List)v1).iterator();
                while(v1_1.hasNext()) {
                    v7.append(v1_1.next());
                }

                String v7_1 = v7.toString();
                if(v7_1.length() <= 0) {
                    return v0;
                }

                v0 = i.a(v7_1);
            }
        }

        return v0;
    }

    public static void a(Context arg0, int arg1, String arg2, String arg3) {
        if(i.e(arg0)) {
            c.h().a(arg1, "Fabric", arg3);
        }
    }

    public static void a(Context arg1, String arg2) {
        if(i.e(arg1)) {
            c.h().a("Fabric", arg2);
        }
    }

    public static void a(Context arg0, String arg1, Throwable arg2) {
        if(i.e(arg0)) {
            c.h().e("Fabric", arg1);
        }
    }

    public static void a(Closeable arg0) {
        if(arg0 != null) {
            try {
                arg0.close();
                return;
            }
            catch(Exception ) {
                return;
            }
            catch(RuntimeException v0) {
                throw v0;
            }
        }
    }

    public static void a(Flushable arg2, String arg3) {
        if(arg2 != null) {
            try {
                arg2.flush();
            }
            catch(IOException v2) {
                c.h().e("Fabric", arg3, ((Throwable)v2));
            }
        }
    }

    public static void a(InputStream arg2, OutputStream arg3, byte[] arg4) {
        while(true) {
            int v0 = arg2.read(arg4);
            if(v0 == -1) {
                return;
            }

            arg3.write(arg4, 0, v0);
        }
    }

    public static boolean a(Context arg2, String arg3, boolean arg4) {
        if(arg2 != null) {
            Resources v0 = arg2.getResources();
            if(v0 != null) {
                int v1 = i.a(arg2, arg3, "bool");
                if(v1 > 0) {
                    return v0.getBoolean(v1);
                }
                else {
                    int v3 = i.a(arg2, arg3, "string");
                    if(v3 > 0) {
                        return Boolean.parseBoolean(arg2.getString(v3));
                    }
                }
            }
        }

        return arg4;
    }

    public static long b() {
        long v4_1;
        long v1_1;
        Class v0 = i.class;
        __monitor_enter(v0);
        try {
            if(i.d != -1) {
                goto label_58;
            }

            v1_1 = 0;
            String v3 = i.a(new File("/proc/meminfo"), "MemTotal");
            if(TextUtils.isEmpty(((CharSequence)v3))) {
                goto label_57;
            }

            v3 = v3.toUpperCase(Locale.US);
            try {
                if(v3.endsWith("KB")) {
                    v4_1 = i.a(v3, "KB", 1024);
                }
                else if(v3.endsWith("MB")) {
                    v4_1 = i.a(v3, "MB", 1048576);
                }
                else if(v3.endsWith("GB")) {
                    v4_1 = i.a(v3, "GB", 1073741824);
                }
                else {
                    goto label_37;
                }

                v1_1 = v4_1;
                goto label_57;
            label_37:
                l v4_2 = c.h();
                v4_2.a("Fabric", "Unexpected meminfo format while computing RAM: " + v3);
                goto label_57;
            }
            catch(NumberFormatException v4) {
                try {
                    l v5 = c.h();
                    v5.e("Fabric", "Unexpected meminfo format while computing RAM: " + v3, ((Throwable)v4));
                label_57:
                    i.d = v1_1;
                label_58:
                    v1_1 = i.d;
                }
                catch(Throwable v1) {
                label_62:
                    __monitor_exit(v0);
                    throw v1;
                }
            }
        }
        catch(Throwable v1) {
            goto label_62;
        }

        __monitor_exit(v0);
        return v1_1;
    }

    public static long b(Context arg2) {
        ActivityManager$MemoryInfo v0 = new ActivityManager$MemoryInfo();
        arg2.getSystemService("activity").getMemoryInfo(v0);
        return v0.availMem;
    }

    public static String b(Context arg1, String arg2) {
        int v2 = i.a(arg1, arg2, "string");
        if(v2 > 0) {
            return arg1.getString(v2);
        }

        return "";
    }

    public static String b(InputStream arg1) {
        return i.a(arg1, "SHA-1");
    }

    public static String b(String arg1) {
        return i.a(arg1, "SHA-256");
    }

    public static Float c(Context arg3) {
        BroadcastReceiver v1 = null;
        Intent v3 = arg3.registerReceiver(v1, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if(v3 == null) {
            return ((Float)v1);
        }

        return Float.valueOf((((float)v3.getIntExtra("level", -1))) / (((float)v3.getIntExtra("scale", -1))));
    }

    public static long c(String arg7) {
        StatFs v0 = new StatFs(arg7);
        long v1 = ((long)v0.getBlockSize());
        return (((long)v0.getBlockCount())) * v1 - v1 * (((long)v0.getAvailableBlocks()));
    }

    public static boolean c() {
        boolean v0 = (Debug.isDebuggerConnected()) || (Debug.waitingForDebugger()) ? true : false;
        return v0;
    }

    public static boolean c(Context arg0, String arg1) {
        boolean v0 = arg0.checkCallingOrSelfPermission(arg1) == 0 ? true : false;
        return v0;
    }

    public static boolean d(String arg0) {
        boolean v0 = arg0 == null || arg0.length() == 0 ? true : false;
        return v0;
    }

    public static boolean d(Context arg2) {
        boolean v1 = false;
        if(i.f(arg2)) {
            return 0;
        }

        if(arg2.getSystemService("sensor").getDefaultSensor(8) != null) {
            v1 = true;
        }

        return v1;
    }

    public static boolean e(Context arg2) {
        if(i.b == null) {
            i.b = Boolean.valueOf(i.a(arg2, "com.crashlytics.Trace", false));
        }

        return i.b.booleanValue();
    }

    public static boolean f(Context arg2) {
        String v2 = Settings$Secure.getString(arg2.getContentResolver(), "android_id");
        boolean v2_1 = ("sdk".equals(Build.PRODUCT)) || ("google_sdk".equals(Build.PRODUCT)) || v2 == null ? true : false;
        return v2_1;
    }

    public static boolean g(Context arg3) {
        boolean v3 = i.f(arg3);
        String v0 = Build.TAGS;
        if(!v3 && v0 != null && (v0.contains("test-keys"))) {
            return 1;
        }

        if(new File("/system/app/Superuser.apk").exists()) {
            return 1;
        }

        File v0_1 = new File("/system/xbin/su");
        if(!v3 && (v0_1.exists())) {
            return 1;
        }

        return 0;
    }

    public static int h(Context arg1) {
        int v0 = i.f(arg1) ? 1 : 0;
        if(i.g(arg1)) {
            v0 |= 2;
        }

        if(i.c()) {
            v0 |= 4;
        }

        return v0;
    }

    public static boolean i(Context arg0) {
        boolean v0 = (arg0.getApplicationInfo().flags & 2) != 0 ? true : false;
        return v0;
    }

    public static String j(Context arg1) {
        int v0 = arg1.getApplicationContext().getApplicationInfo().icon;
        if(v0 > 0) {
            return arg1.getResources().getResourcePackageName(v0);
        }

        return arg1.getPackageName();
    }

    public static String k(Context arg6) {
        String v1_1;
        Closeable v6_2;
        Throwable v0_1;
        InputStream v6_1;
        String v0 = null;
        try {
            v6_1 = arg6.getResources().openRawResource(i.l(arg6));
        }
        catch(Throwable v6) {
            String v5 = v0;
            v0_1 = v6;
            v6_2 = ((Closeable)v5);
            goto label_29;
        }
        catch(Exception v1) {
            v6_2 = ((Closeable)v0);
            goto label_21;
        }

        try {
            v1_1 = i.b(v6_1);
            if(!i.d(v1_1)) {
                goto label_8;
            }

            goto label_7;
        }
        catch(Throwable v0_1) {
        }
        catch(Exception v1) {
            try {
            label_21:
                c.h().e("Fabric", "Could not calculate hash for app icon.", ((Throwable)v1));
            }
            catch(Throwable v0_1) {
                goto label_29;
            }

            i.a(v6_2, "Failed to close icon input stream.");
            return v0;
        }

    label_29:
        i.a(v6_2, "Failed to close icon input stream.");
        throw v0_1;
    label_7:
        goto label_9;
    label_8:
        v0 = v1_1;
    label_9:
        i.a(((Closeable)v6_1), "Failed to close icon input stream.");
        return v0;
    }

    public static int l(Context arg0) {
        return arg0.getApplicationContext().getApplicationInfo().icon;
    }

    public static String m(Context arg4) {
        String v4;
        int v0 = i.a(arg4, "io.fabric.android.build_id", "string");
        if(v0 == 0) {
            v0 = i.a(arg4, "com.crashlytics.android.build_id", "string");
        }

        if(v0 != 0) {
            v4 = arg4.getResources().getString(v0);
            l v0_1 = c.h();
            v0_1.a("Fabric", "Build ID is: " + v4);
        }
        else {
            v4 = null;
        }

        return v4;
    }

    @SuppressLint(value={"MissingPermission"}) public static boolean n(Context arg2) {
        boolean v1 = true;
        if(i.c(arg2, "android.permission.ACCESS_NETWORK_STATE")) {
            NetworkInfo v2 = arg2.getSystemService("connectivity").getActiveNetworkInfo();
            if(v2 != null && (v2.isConnectedOrConnecting())) {
                return v1;
            }

            v1 = false;
        }

        return v1;
    }
}

