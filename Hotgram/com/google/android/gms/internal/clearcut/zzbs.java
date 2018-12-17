package com.google.android.gms.internal.clearcut;

final class zzbs {
    private static final Class zzgl;

    static {
        zzbs.zzgl = zzbs.zzak();
    }

    private static Class zzak() {
        try {
            return Class.forName("com.google.protobuf.ExtensionRegistry");
        }
        catch(ClassNotFoundException ) {
            return null;
        }
    }

    public static zzbt zzal() {
        if(zzbs.zzgl == null) {
            goto label_11;
        }

        try {
            return zzbs.zzgl.getDeclaredMethod("getEmptyRegistry").invoke(null);
        }
        catch(Exception ) {
        label_11:
            return zzbt.zzgo;
        }
    }
}

