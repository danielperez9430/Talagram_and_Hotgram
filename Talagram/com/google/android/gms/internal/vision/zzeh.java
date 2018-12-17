package com.google.android.gms.internal.vision;

final class zzeh {
    private static final zzef zznv;
    private static final zzef zznw;

    static {
        zzeh.zznv = zzeh.zzdb();
        zzeh.zznw = new zzeg();
    }

    static zzef zzcz() {
        return zzeh.zznv;
    }

    static zzef zzda() {
        return zzeh.zznw;
    }

    private static zzef zzdb() {
        try {
            return Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor().newInstance();
        }
        catch(Exception ) {
            return null;
        }
    }
}

