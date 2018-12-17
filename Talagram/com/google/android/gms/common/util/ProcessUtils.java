package com.google.android.gms.common.util;

import android.os.Binder;
import android.os.Process;
import android.os.StrictMode$ThreadPolicy;
import android.os.StrictMode;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import javax.annotation.Nullable;

public class ProcessUtils {
    public class SystemGroupsNotAvailableException extends Exception {
        SystemGroupsNotAvailableException(String arg1, Throwable arg2) {
            super(arg1, arg2);
        }

        SystemGroupsNotAvailableException(String arg1) {
            super(arg1);
        }
    }

    private static String zzaai;
    private static int zzaaj;

    static {
    }

    private ProcessUtils() {
        super();
    }

    @Nullable public static String getCallingProcessName() {
        int v0 = Binder.getCallingPid();
        if(v0 == ProcessUtils.zzde()) {
            return ProcessUtils.getMyProcessName();
        }

        return ProcessUtils.zzl(v0);
    }

    @Nullable public static String getMyProcessName() {
        if(ProcessUtils.zzaai == null) {
            ProcessUtils.zzaai = ProcessUtils.zzl(ProcessUtils.zzde());
        }

        return ProcessUtils.zzaai;
    }

    public static boolean hasSystemGroups() {
        BufferedReader v0_4;
        BufferedReader v4;
        boolean v0_3;
        BufferedReader v1_3;
        Closeable v0 = null;
        try {
            int v1_2 = ProcessUtils.zzde();
            StringBuilder v3 = new StringBuilder(24);
            v3.append("/proc/");
            v3.append(v1_2);
            v3.append("/status");
            v1_3 = ProcessUtils.zzm(v3.toString());
            goto label_12;
        }
        catch(Throwable v1) {
        }
        catch(IOException v1_1) {
            goto label_28;
            try {
            label_12:
                v0_3 = ProcessUtils.zzk(v1_3);
                goto label_13;
            }
            catch(Throwable v0_1) {
                v4 = v1_3;
                v1 = v0_1;
                v0_4 = v4;
            }
            catch(IOException v0_2) {
                v4 = v1_3;
                v1_1 = v0_2;
                v0_4 = v4;
                try {
                label_28:
                    throw new SystemGroupsNotAvailableException("Unable to access /proc/pid/status.", ((Throwable)v1_1));
                }
                catch(Throwable v1) {
                }
            }
        }

        IOUtils.closeQuietly(((Closeable)v0_4));
        throw v1;
    label_13:
        IOUtils.closeQuietly(((Closeable)v1_3));
        return v0_3;
    }

    private static int zzde() {
        if(ProcessUtils.zzaaj == 0) {
            ProcessUtils.zzaaj = Process.myPid();
        }

        return ProcessUtils.zzaaj;
    }

    private static boolean zzk(BufferedReader arg8) {
        String v0;
        do {
            v0 = arg8.readLine();
            if(v0 == null) {
                goto label_27;
            }

            v0 = v0.trim();
        }
        while(!v0.startsWith("Groups:"));

        String[] v8 = v0.substring(7).trim().split("\\s", -1);
        int v0_1 = v8.length;
        int v2 = 0;
        goto label_15;
    label_27:
        throw new SystemGroupsNotAvailableException("Missing Groups entry from proc/pid/status.");
    label_15:
        while(v2 < v0_1) {
            String v3 = v8[v2];
            try {
                long v3_1 = Long.parseLong(v3);
                if(v3_1 >= 1000) {
                    goto label_20;
                }
            }
            catch(NumberFormatException ) {
            }

            goto label_24;
        label_20:
            if(v3_1 < 2000) {
                return 1;
            }

        label_24:
            ++v2;
        }

        return 0;
    }

    @Nullable private static String zzl(int arg4) {
        String v1_1;
        BufferedReader v4_1;
        String v0 = null;
        if(arg4 <= 0) {
            return v0;
        }

        int v1 = 25;
        try {
            StringBuilder v2 = new StringBuilder(v1);
            v2.append("/proc/");
            v2.append(arg4);
            v2.append("/cmdline");
            v4_1 = ProcessUtils.zzm(v2.toString());
        }
        catch(Throwable v4) {
            goto label_24;
        }
        catch(IOException ) {
            Closeable v4_2 = ((Closeable)v0);
            goto label_27;
        }

        try {
            v1_1 = v4_1.readLine().trim();
            goto label_15;
        }
        catch(Throwable v0_1) {
            Throwable v3 = v0_1;
            BufferedReader v0_2 = v4_1;
            v4 = v3;
        }
        catch(IOException ) {
        label_27:
            IOUtils.closeQuietly(((Closeable)v4_1));
            return v0;
        }

    label_24:
        IOUtils.closeQuietly(((Closeable)v0));
        throw v4;
    label_15:
        IOUtils.closeQuietly(((Closeable)v4_1));
        return v1_1;
    }

    private static BufferedReader zzm(String arg3) {
        BufferedReader v1;
        StrictMode$ThreadPolicy v0 = StrictMode.allowThreadDiskReads();
        try {
            v1 = new BufferedReader(new FileReader(arg3));
        }
        catch(Throwable v3) {
            StrictMode.setThreadPolicy(v0);
            throw v3;
        }

        StrictMode.setThreadPolicy(v0);
        return v1;
    }
}

