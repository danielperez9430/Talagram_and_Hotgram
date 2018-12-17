package com.google.android.gms.internal.places;

final class zzgk {
    private static final Class zzpb;

    static {
        zzgk.zzpb = zzgk.zzcw();
    }

    private static Class zzcw() {
        try {
            return Class.forName("com.google.protobuf.ExtensionRegistry");
        }
        catch(ClassNotFoundException ) {
            return null;
        }
    }

    public static zzgl zzcx() {
        if(zzgk.zzpb == null) {
            goto label_5;
        }

        try {
            return zzgk.zzn("getEmptyRegistry");
        }
        catch(Exception ) {
        label_5:
            return zzgl.zzpf;
        }
    }

    static zzgl zzcy() {
        zzgl v0;
        if(zzgk.zzpb != null) {
            try {
                v0 = zzgk.zzn("loadGeneratedRegistry");
            }
            catch(Exception ) {
            label_5:
                v0 = null;
            }
        }
        else {
            goto label_5;
        }

        if(v0 == null) {
            v0 = zzgl.zzcy();
        }

        if(v0 == null) {
            v0 = zzgk.zzcx();
        }

        return v0;
    }

    private static final zzgl zzn(String arg3) {
        return zzgk.zzpb.getDeclaredMethod(arg3).invoke(null);
    }
}

