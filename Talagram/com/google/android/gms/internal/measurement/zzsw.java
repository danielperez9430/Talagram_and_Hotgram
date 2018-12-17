package com.google.android.gms.internal.measurement;

import java.io.PrintStream;

public final class zzsw {
    final class zza extends zzsx {
        zza() {
            super();
        }

        public final void zza(Throwable arg1, PrintStream arg2) {
            arg1.printStackTrace(arg2);
        }
    }

    private static final zzsx zzbrt;
    private static final int zzbru;

    static {
        zza v2_3;
        zzta v2_2;
        Integer v1;
        int v0 = 1;
        try {
            v1 = zzsw.zztk();
            if(v1 == null) {
                goto label_11;
            }
        }
        catch(Throwable v2) {
            v1 = null;
            goto label_23;
        }

        try {
            if(v1.intValue() >= 19) {
                zztb v2_1 = new zztb();
                goto label_42;
            }

        label_11:
            if((Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic") ^ 1) != 0) {
                v2_2 = new zzta();
                goto label_42;
            }

            v2_3 = new zza();
            goto label_42;
        label_10:
        }
        catch(Throwable v2) {
            goto label_10;
        }

    label_23:
        PrintStream v3 = System.err;
        String v4 = zza.class.getName();
        StringBuilder v6 = new StringBuilder(String.valueOf(v4).length() + 132);
        v6.append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ");
        v6.append(v4);
        v6.append("will be used. The error is: ");
        v3.println(v6.toString());
        v2.printStackTrace(System.err);
        v2_3 = new zza();
    label_42:
        zzsw.zzbrt = ((zzsx)v2_2);
        if(v1 == null) {
        }
        else {
            v0 = v1.intValue();
        }

        zzsw.zzbru = v0;
    }

    public static void zza(Throwable arg1, PrintStream arg2) {
        zzsw.zzbrt.zza(arg1, arg2);
    }

    private static Integer zztk() {
        Object v0 = null;
        try {
            return Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(v0);
        }
        catch(Exception v1) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            v1.printStackTrace(System.err);
            return ((Integer)v0);
        }
    }
}

