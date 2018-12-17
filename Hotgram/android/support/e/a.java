package android.support.e;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build$VERSION;
import android.util.Log;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;

public final class a {
    final class android.support.e.a$a {
        static void a(ClassLoader arg0, List arg1, File arg2) {
            android.support.e.a$a.b(arg0, arg1, arg2);
        }

        private static Object[] a(Object arg6, ArrayList arg7, File arg8) {
            return a.a(arg6, "makeDexElements", new Class[]{ArrayList.class, File.class}).invoke(arg6, arg7, arg8);
        }

        private static void b(ClassLoader arg2, List arg3, File arg4) {
            Object v2 = a.a(arg2, "pathList").get(arg2);
            a.a(v2, "dexElements", android.support.e.a$a.a(v2, new ArrayList(((Collection)arg3)), arg4));
        }
    }

    final class b {
        static void a(ClassLoader arg0, List arg1, File arg2) {
            b.b(arg0, arg1, arg2);
        }

        private static Object[] a(Object arg7, ArrayList arg8, File arg9, ArrayList arg10) {
            return a.a(arg7, "makeDexElements", new Class[]{ArrayList.class, File.class, ArrayList.class}).invoke(arg7, arg8, arg9, arg10);
        }

        private static void b(ClassLoader arg4, List arg5, File arg6) {
            Object[] v6_1;
            Object v4 = a.a(arg4, "pathList").get(arg4);
            ArrayList v0 = new ArrayList();
            a.a(v4, "dexElements", b.a(v4, new ArrayList(((Collection)arg5)), arg6, v0));
            if(v0.size() > 0) {
                Iterator v5 = v0.iterator();
                while(v5.hasNext()) {
                    Log.w("MultiDex", "Exception in makeDexElement", v5.next());
                }

                Field v5_1 = a.a(v4, "dexElementsSuppressedExceptions");
                Object v6 = v5_1.get(v4);
                if(v6 == null) {
                    v6_1 = v0.toArray(new IOException[v0.size()]);
                }
                else {
                    IOException[] v1 = new IOException[v0.size() + v6.length];
                    v0.toArray(((Object[])v1));
                    System.arraycopy(v6, 0, v1, v0.size(), v6.length);
                    IOException[] v6_2 = v1;
                }

                v5_1.set(v4, v6_1);
            }
        }
    }

    final class c {
        static void a(ClassLoader arg0, List arg1) {
            c.b(arg0, arg1);
        }

        private static void b(ClassLoader arg10, List arg11) {
            int v0 = arg11.size();
            Field v1 = a.a(arg10, "path");
            StringBuilder v2 = new StringBuilder(v1.get(arg10));
            String[] v3 = new String[v0];
            File[] v4 = new File[v0];
            ZipFile[] v5 = new ZipFile[v0];
            DexFile[] v0_1 = new DexFile[v0];
            ListIterator v11 = arg11.listIterator();
            while(v11.hasNext()) {
                Object v6 = v11.next();
                String v7 = ((File)v6).getAbsolutePath();
                v2.append(':');
                v2.append(v7);
                int v8 = v11.previousIndex();
                v3[v8] = v7;
                v4[v8] = ((File)v6);
                v5[v8] = new ZipFile(((File)v6));
                StringBuilder v6_1 = new StringBuilder();
                v6_1.append(v7);
                v6_1.append(".dex");
                v0_1[v8] = DexFile.loadDex(v7, v6_1.toString(), 0);
            }

            v1.set(arg10, v2.toString());
            a.a(arg10, "mPaths", ((Object[])v3));
            a.a(arg10, "mFiles", ((Object[])v4));
            a.a(arg10, "mZips", ((Object[])v5));
            a.a(arg10, "mDexs", ((Object[])v0_1));
        }
    }

    private static final Set a;
    private static final boolean b;

    static {
        a.a = new HashSet();
        a.b = a.a(System.getProperty("java.vm.version"));
    }

    static boolean a(String arg5) {
        boolean v0 = false;
        if(arg5 != null) {
            Matcher v1 = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(((CharSequence)arg5));
            if(v1.matches()) {
                try {
                    int v3 = Integer.parseInt(v1.group(1));
                    int v4 = 2;
                    int v1_1 = Integer.parseInt(v1.group(v4));
                    if(v3 <= v4) {
                        goto label_14;
                    }

                    goto label_16;
                }
                catch(NumberFormatException ) {
                    goto label_17;
                }

            label_14:
                if(v3 != v4) {
                }
                else if(v1_1 >= 1) {
                    goto label_16;
                }

                goto label_17;
            label_16:
                v0 = true;
            }
        }

    label_17:
        String v1_2 = "MultiDex";
        StringBuilder v2 = new StringBuilder();
        v2.append("VM with version ");
        v2.append(arg5);
        arg5 = v0 ? " has multidex support" : " does not have multidex support";
        v2.append(arg5);
        Log.i(v1_2, v2.toString());
        return v0;
    }

    private static File a(Context arg2, File arg3, String arg4) {
        File v0 = new File(arg3, "code_cache");
        try {
            a.a(v0);
        }
        catch(IOException ) {
            v0 = new File(arg2.getFilesDir(), "code_cache");
            a.a(v0);
        }

        File v2 = new File(v0, arg4);
        a.a(v2);
        return v2;
    }

    private static void a(File arg3) {
        arg3.mkdir();
        if(!arg3.isDirectory()) {
            File v0 = arg3.getParentFile();
            String v0_2 = v0 == null ? "Failed to create dir " + arg3.getPath() + ". Parent file is null." : "Failed to create dir " + arg3.getPath() + ". parent file is a dir " + v0.isDirectory() + ", a file " + v0.isFile() + ", exists " + v0.exists() + ", readable " + v0.canRead() + ", writable " + v0.canWrite();
            Log.e("MultiDex", v0_2);
            v1 = new StringBuilder();
            v1.append("Failed to create directory ");
            v1.append(arg3.getPath());
            throw new IOException(v1.toString());
        }
    }

    static Field a(Object arg0, String arg1) {
        return a.b(arg0, arg1);
    }

    static Method a(Object arg0, String arg1, Class[] arg2) {
        return a.b(arg0, arg1, arg2);
    }

    public static void a(Context arg4) {
        int v1;
        String v0;
        String v4;
        Log.i("MultiDex", "Installing application");
        if(a.b) {
            v4 = "MultiDex";
            v0 = "VM has multidex support, MultiDex support library is disabled.";
        }
        else {
            v1 = 4;
            if(Build$VERSION.SDK_INT >= v1) {
                try {
                    ApplicationInfo v0_1 = a.b(arg4);
                    if(v0_1 == null) {
                        Log.i("MultiDex", "No ApplicationInfo available, i.e. running on a test Context: MultiDex support library is disabled.");
                        return;
                    }
                    else {
                        a.a(arg4, new File(v0_1.sourceDir), new File(v0_1.dataDir), "secondary-dexes", "");
                        goto label_27;
                    }

                    goto label_7;
                }
                catch(Exception v4_1) {
                    Log.e("MultiDex", "MultiDex installation failure", ((Throwable)v4_1));
                    StringBuilder v1_1 = new StringBuilder();
                    v1_1.append("MultiDex installation failed (");
                    v1_1.append(v4_1.getMessage());
                    v1_1.append(").");
                    throw new RuntimeException(v1_1.toString());
                }

            label_27:
                v4 = "MultiDex";
                v0 = "install done";
            }
            else {
                goto label_46;
            }
        }

    label_7:
        Log.i(v4, v0);
        return;
    label_46:
        StringBuilder v0_2 = new StringBuilder();
        v0_2.append("MultiDex installation failed. SDK ");
        v0_2.append(Build$VERSION.SDK_INT);
        v0_2.append(" is unsupported. Min SDK version is ");
        v0_2.append(v1);
        v0_2.append(".");
        throw new RuntimeException(v0_2.toString());
    }

    private static void a(Context arg5, File arg6, File arg7, String arg8, String arg9) {
        ClassLoader v1;
        Set v0 = a.a;
        __monitor_enter(v0);
        try {
            if(a.a.contains(arg6)) {
                __monitor_exit(v0);
                return;
            }

            a.a.add(arg6);
            int v2 = 20;
            if(Build$VERSION.SDK_INT > v2) {
                Log.w("MultiDex", "MultiDex is not guaranteed to work in SDK version " + Build$VERSION.SDK_INT + ": SDK version higher than " + v2 + " should be backed by " + "runtime with built-in multidex capabilty but it\'s not the " + "case here: java.vm.version=\"" + System.getProperty("java.vm.version") + "\"");
            }

            try {
                v1 = arg5.getClassLoader();
                if(v1 != null) {
                    goto label_42;
                }

                goto label_37;
            }
            catch(RuntimeException v5_1) {
                try {
                    Log.w("MultiDex", "Failure while trying to obtain Context class loader. Must be running in test mode. Skip patching.", ((Throwable)v5_1));
                    __monitor_exit(v0);
                    return;
                label_37:
                    Log.e("MultiDex", "Context class loader is null. Must be running in test mode. Skip patching.");
                    __monitor_exit(v0);
                    return;
                    try {
                    label_42:
                        a.c(arg5);
                        goto label_48;
                    }
                    catch(Throwable v2_1) {
                        try {
                            Log.w("MultiDex", "Something went wrong when trying to clear old MultiDex extraction, continuing without cleaning.", v2_1);
                        label_48:
                            arg7 = a.a(arg5, arg7, arg8);
                            a.a(v1, arg7, android.support.e.b.a(arg5, arg6, arg7, arg9, false));
                            __monitor_exit(v0);
                            return;
                        label_61:
                            __monitor_exit(v0);
                        }
                        catch(Throwable v5) {
                            goto label_61;
                        }
                    }
                }
                catch(Throwable v5) {
                    goto label_61;
                }
            }
        }
        catch(Throwable v5) {
            goto label_61;
        }

        throw v5;
    }

    private static void a(ClassLoader arg2, File arg3, List arg4) {
        if(!arg4.isEmpty()) {
            if(Build$VERSION.SDK_INT >= 19) {
                b.a(arg2, arg4, arg3);
            }
            else if(Build$VERSION.SDK_INT >= 14) {
                android.support.e.a$a.a(arg2, arg4, arg3);
            }
            else {
                c.a(arg2, arg4);
            }
        }
    }

    static void a(Object arg0, String arg1, Object[] arg2) {
        a.b(arg0, arg1, arg2);
    }

    private static Field b(Object arg3, String arg4) {
        Field v1;
        Class v0 = arg3.getClass();
        while(true) {
            if(v0 == null) {
                goto label_10;
            }

            try {
                v1 = v0.getDeclaredField(arg4);
                if(!v1.isAccessible()) {
                    v1.setAccessible(true);
                }

                return v1;
            }
            catch(NoSuchFieldException ) {
                v0 = v0.getSuperclass();
                continue;
            }
        }

        return v1;
    label_10:
        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("Field ");
        v1_1.append(arg4);
        v1_1.append(" not found in ");
        v1_1.append(arg3.getClass());
        throw new NoSuchFieldException(v1_1.toString());
    }

    private static Method b(Object arg3, String arg4, Class[] arg5) {
        Method v1;
        Class v0 = arg3.getClass();
        while(true) {
            if(v0 == null) {
                goto label_10;
            }

            try {
                v1 = v0.getDeclaredMethod(arg4, arg5);
                if(!v1.isAccessible()) {
                    v1.setAccessible(true);
                }

                return v1;
            }
            catch(NoSuchMethodException ) {
                v0 = v0.getSuperclass();
                continue;
            }
        }

        return v1;
    label_10:
        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("Method ");
        v1_1.append(arg4);
        v1_1.append(" with parameters ");
        v1_1.append(Arrays.asList(((Object[])arg5)));
        v1_1.append(" not found in ");
        v1_1.append(arg3.getClass());
        throw new NoSuchMethodException(v1_1.toString());
    }

    private static ApplicationInfo b(Context arg2) {
        try {
            return arg2.getApplicationInfo();
        }
        catch(RuntimeException v2) {
            Log.w("MultiDex", "Failure while trying to obtain ApplicationInfo from Context. Must be running in test mode. Skip patching.", ((Throwable)v2));
            return null;
        }
    }

    private static void b(Object arg4, String arg5, Object[] arg6) {
        Field v5 = a.b(arg4, arg5);
        Object v0 = v5.get(arg4);
        Object v1 = Array.newInstance(v0.getClass().getComponentType(), v0.length + arg6.length);
        System.arraycopy(v0, 0, v1, 0, v0.length);
        System.arraycopy(arg6, 0, v1, v0.length, arg6.length);
        v5.set(arg4, v1);
    }

    private static void c(Context arg8) {
        File v0 = new File(arg8.getFilesDir(), "secondary-dexes");
        if(v0.isDirectory()) {
            Log.i("MultiDex", "Clearing old secondary dex dir (" + v0.getPath() + ").");
            File[] v8 = v0.listFiles();
            if(v8 == null) {
                Log.w("MultiDex", "Failed to list secondary dex dir content (" + v0.getPath() + ").");
                return;
            }
            else {
                int v1_1 = v8.length;
                int v2;
                for(v2 = 0; v2 < v1_1; ++v2) {
                    File v3 = v8[v2];
                    Log.i("MultiDex", "Trying to delete old file " + v3.getPath() + " of size " + v3.length());
                    if(!v3.delete()) {
                        Log.w("MultiDex", "Failed to delete old file " + v3.getPath());
                    }
                    else {
                        Log.i("MultiDex", "Deleted old file " + v3.getPath());
                    }
                }

                if(!v0.delete()) {
                    Log.w("MultiDex", "Failed to delete secondary dex dir " + v0.getPath());
                    return;
                }

                Log.i("MultiDex", "Deleted old secondary dex dir " + v0.getPath());
            }
        }
    }
}

