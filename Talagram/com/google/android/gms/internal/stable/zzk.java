package com.google.android.gms.internal.stable;

import java.io.PrintStream;

public final class zzk {
    final class zza extends zzl {
        zza() {
            super();
        }

        public final void zza(Throwable arg1, Throwable arg2) {
        }
    }

    private static final zzl zzahg;
    private static final int zzahh;

    static {
        zza v2_3;
        zzp v2_1;
        Integer v1;
        int v0 = 1;
        try {
            v1 = zzk.zzdw();
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
                v2_1 = new zzp();
                goto label_42;
            }

        label_11:
            if((Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic") ^ 1) != 0) {
                zzo v2_2 = new zzo();
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
        zzk.zzahg = ((zzl)v2_1);
        if(v1 == null) {
        }
        else {
            v0 = v1.intValue();
        }

        zzk.zzahh = v0;
    }

    public static void zza(Throwable arg1, Throwable arg2) {
        zzk.zzahg.zza(arg1, arg2);
    }

    private static Integer zzdw() {
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

