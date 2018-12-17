package com.google.android.gms.internal.vision;

final class zzce {
    private static final Class zzhn;

    static {
        zzce.zzhn = zzce.zzbd();
    }

    private static Class zzbd() {
        try {
            return Class.forName("com.google.protobuf.ExtensionRegistry");
        }
        catch(ClassNotFoundException ) {
            return null;
        }
    }

    public static zzcf zzbe() {
        if(zzce.zzhn == null) {
            goto label_11;
        }

        try {
            return zzce.zzhn.getDeclaredMethod("getEmptyRegistry").invoke(null);
        }
        catch(Exception ) {
        label_11:
            return zzcf.zzhq;
        }
    }
}

