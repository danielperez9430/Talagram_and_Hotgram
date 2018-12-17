package com.google.android.gms.internal.measurement;

final class zzuy {
    private static final Class zzbvi;

    static {
        zzuy.zzbvi = zzuy.zzvk();
    }

    private static final zzuz zzfz(String arg3) {
        return zzuy.zzbvi.getDeclaredMethod(arg3).invoke(null);
    }

    private static Class zzvk() {
        try {
            return Class.forName("com.google.protobuf.ExtensionRegistry");
        }
        catch(ClassNotFoundException ) {
            return null;
        }
    }

    public static zzuz zzvl() {
        if(zzuy.zzbvi == null) {
            goto label_5;
        }

        try {
            return zzuy.zzfz("getEmptyRegistry");
        }
        catch(Exception ) {
        label_5:
            return zzuz.zzbvm;
        }
    }

    static zzuz zzvm() {
        zzuz v0;
        if(zzuy.zzbvi != null) {
            try {
                v0 = zzuy.zzfz("loadGeneratedRegistry");
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
            v0 = zzuz.zzvm();
        }

        if(v0 == null) {
            v0 = zzuy.zzvl();
        }

        return v0;
    }
}

