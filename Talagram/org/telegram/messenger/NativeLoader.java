package org.telegram.messenger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.hockeyapp.android.a;

public class NativeLoader {
    private static final String LIB_NAME = "tmessages.29";
    private static final String LIB_SO_NAME = "libtmessages.29.so";
    private static final int LIB_VERSION = 29;
    private static final String LOCALE_LIB_SO_NAME = "libtmessages.29loc.so";
    private String crashPath;
    private static volatile boolean nativeLoaded = false;

    static {
    }

    public NativeLoader() {
        super();
        this.crashPath = "";
    }

    private static File getNativeLibraryDir(Context arg4) {
        File v1_1;
        File v0 = null;
        if(arg4 != null) {
            try {
                v1_1 = new File(ApplicationInfo.class.getField("nativeLibraryDir").get(arg4.getApplicationInfo()));
                goto label_13;
            }
            catch(Throwable v1) {
                v1.printStackTrace();
            }
        }

        v1_1 = v0;
    label_13:
        if(v1_1 == null) {
            v1_1 = new File(arg4.getApplicationInfo().dataDir, "lib");
        }

        if(v1_1.isDirectory()) {
            return v1_1;
        }

        return v0;
    }

    private static native void init(String arg0, boolean arg1) {
    }

    @SuppressLint(value={"UnsafeDynamicallyLoadedCode"}) public static void initNativeLibs(Context arg7) {
        String v2_2;
        Class v0 = NativeLoader.class;
        __monitor_enter(v0);
        try {
            if(!NativeLoader.nativeLoaded) {
                goto label_6;
            }
        }
        catch(Throwable v7) {
            goto label_125;
        }

        __monitor_exit(v0);
        return;
        try {
        label_6:
            a.a(arg7);
            try {
                System.loadLibrary("tmessages.29");
                NativeLoader.nativeLoaded = true;
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("loaded normal lib");
                }

                goto label_15;
            }
            catch(Throwable v7) {
            label_18:
                try {
                    v7.printStackTrace();
                    try {
                    label_116:
                        System.loadLibrary("tmessages.29");
                        NativeLoader.nativeLoaded = true;
                    }
                    catch(Error v7_1) {
                        try {
                            FileLog.e(((Throwable)v7_1));
                        }
                        catch(Throwable v7) {
                            goto label_125;
                        }
                    }
                }
                catch(Throwable v7) {
                    goto label_125;
                }
            }
            catch(Error v2) {
                try {
                    FileLog.e(((Throwable)v2));
                    try {
                        if(Build.CPU_ABI.equalsIgnoreCase("x86_64")) {
                            v2_2 = "x86_64";
                        }
                        else if(Build.CPU_ABI.equalsIgnoreCase("arm64-v8a")) {
                            v2_2 = "arm64-v8a";
                        }
                        else if(Build.CPU_ABI.equalsIgnoreCase("armeabi-v7a")) {
                            v2_2 = "armeabi-v7a";
                        }
                        else if(Build.CPU_ABI.equalsIgnoreCase("armeabi")) {
                            v2_2 = "armeabi";
                        }
                        else if(Build.CPU_ABI.equalsIgnoreCase("x86")) {
                            v2_2 = "x86";
                        }
                        else if(Build.CPU_ABI.equalsIgnoreCase("mips")) {
                            v2_2 = "mips";
                        }
                        else {
                            v2_2 = "armeabi";
                            if(BuildVars.LOGS_ENABLED) {
                                FileLog.e("Unsupported arch: " + Build.CPU_ABI);
                            }
                            else {
                            }
                        }

                        goto label_73;
                    }
                    catch(Exception v2_1) {
                        try {
                            FileLog.e(((Throwable)v2_1));
                            v2_2 = "armeabi";
                        label_73:
                            String v3_1 = System.getProperty("os.arch");
                            if(v3_1 != null && (v3_1.contains("686"))) {
                                v2_2 = "x86";
                            }

                            File v3_2 = new File(arg7.getFilesDir(), "lib");
                            v3_2.mkdirs();
                            File v4 = new File(v3_2, "libtmessages.29loc.so");
                            if(!v4.exists()) {
                                goto label_102;
                            }

                            try {
                                if(BuildVars.LOGS_ENABLED) {
                                    FileLog.d("Load local lib");
                                }

                                System.load(v4.getAbsolutePath());
                                NativeLoader.nativeLoaded = true;
                            }
                            catch(Error v5) {
                                try {
                                    FileLog.e(((Throwable)v5));
                                    v4.delete();
                                    goto label_102;
                                }
                                catch(Throwable v7) {
                                    goto label_18;
                                }
                                catch(Throwable v7) {
                                    goto label_125;
                                }
                            }
                        }
                        catch(Throwable v7) {
                            goto label_18;
                        }
                        catch(Throwable v7) {
                            goto label_125;
                        }
                    }
                }
                catch(Throwable v7) {
                    goto label_18;
                }
                catch(Throwable v7) {
                    goto label_125;
                }

                __monitor_exit(v0);
                return;
                try {
                label_102:
                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.e("Library not found, arch = " + v2_2);
                    }

                    if(!NativeLoader.loadFromZip(arg7, v3_2, v4, v2_2)) {
                        goto label_116;
                    }
                }
                catch(Throwable v7) {
                    goto label_18;
                }
                catch(Throwable v7) {
                    goto label_125;
                }

                __monitor_exit(v0);
                return;
            }
        }
        catch(Throwable v7) {
            goto label_125;
        }

        __monitor_exit(v0);
        return;
    label_125:
        __monitor_exit(v0);
        throw v7;
    label_15:
        __monitor_exit(v0);
    }

    @SuppressLint(value={"UnsafeDynamicallyLoadedCode", "SetWorldReadable"}) private static boolean loadFromZip(Context arg5, File arg6, File arg7, String arg8) {
        InputStream v5_4;
        ZipFile v1_1;
        try {
            File[] v6_1 = arg6.listFiles();
            int v1 = v6_1.length;
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                v6_1[v2].delete();
            }
        }
        catch(Exception v6) {
            FileLog.e(((Throwable)v6));
        }

        InputStream v6_2 = null;
        try {
            v1_1 = new ZipFile(arg5.getApplicationInfo().sourceDir);
        }
        catch(Throwable v5) {
            v1_1 = ((ZipFile)v6_2);
            goto label_102;
        }
        catch(Exception v5_1) {
            v1_1 = ((ZipFile)v6_2);
            goto label_89;
        }

        try {
            StringBuilder v5_2 = new StringBuilder();
            v5_2.append("lib/");
            v5_2.append(arg8);
            v5_2.append("/");
            v5_2.append("libtmessages.29.so");
            ZipEntry v5_3 = v1_1.getEntry(v5_2.toString());
            if(v5_3 == null) {
                goto label_69;
            }

            v5_4 = v1_1.getInputStream(v5_3);
        }
        catch(Throwable v5) {
            goto label_102;
        }
        catch(Exception v5_1) {
            goto label_83;
        }

        try {
            FileOutputStream v6_4 = new FileOutputStream(arg7);
            byte[] v8 = new byte[4096];
            while(true) {
                v2 = v5_4.read(v8);
                if(v2 <= 0) {
                    break;
                }

                Thread.yield();
                ((OutputStream)v6_4).write(v8, 0, v2);
            }

            ((OutputStream)v6_4).close();
            arg7.setReadable(true, false);
            arg7.setExecutable(true, false);
            arg7.setWritable(true);
            try {
                System.load(arg7.getAbsolutePath());
                NativeLoader.nativeLoaded = true;
            }
            catch(Error v7) {
                try {
                    FileLog.e(((Throwable)v7));
                }
                catch(Exception v6) {
                    goto label_65;
                }
                catch(Throwable v6_3) {
                    goto label_60;
                }
            }
        }
        catch(Exception v6) {
            goto label_65;
        }
        catch(Throwable v6_3) {
            goto label_60;
        }

        if(v5_4 == null) {
            goto label_54;
        }

        try {
            v5_4.close();
        }
        catch(Exception v5_1) {
            FileLog.e(((Throwable)v5_1));
        }

        try {
        label_54:
            v1_1.close();
        }
        catch(Exception v5_1) {
            FileLog.e(((Throwable)v5_1));
        }

        return 1;
    label_65:
        Exception v4 = v6;
        v6_2 = v5_4;
        v5_1 = v4;
        goto label_89;
    label_60:
        Throwable v4_1 = v6_3;
        v6_2 = v5_4;
        v5 = v4_1;
        goto label_102;
        try {
        label_69:
            StringBuilder v7_1 = new StringBuilder();
            v7_1.append("Unable to find file in apk:lib/");
            v7_1.append(arg8);
            v7_1.append("/");
            v7_1.append("tmessages.29");
            throw new Exception(v7_1.toString());
        }
        catch(Throwable v5) {
        }
        catch(Exception v5_1) {
        label_83:
            try {
            label_89:
                FileLog.e(((Throwable)v5_1));
                if(v6_2 == null) {
                    goto label_95;
                }
            }
            catch(Throwable v5) {
                goto label_102;
            }

            try {
                v6_2.close();
            }
            catch(Exception v5_1) {
                FileLog.e(((Throwable)v5_1));
            }

        label_95:
            if(v1_1 != null) {
                try {
                    v1_1.close();
                }
                catch(Exception v5_1) {
                    FileLog.e(((Throwable)v5_1));
                }
            }

            return 0;
        }

    label_102:
        if(v6_2 != null) {
            try {
                v6_2.close();
            }
            catch(Exception v6) {
                FileLog.e(((Throwable)v6));
            }
        }

        if(v1_1 != null) {
            try {
                v1_1.close();
            }
            catch(Exception v6) {
                FileLog.e(((Throwable)v6));
            }
        }

        throw v5;
    }
}

