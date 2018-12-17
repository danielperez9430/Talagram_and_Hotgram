package com.google.android.gms.internal.measurement;

final class zzvc {
    private static final zzva zzbvo;
    private static final zzva zzbvp;

    static {
        zzvc.zzbvo = new zzvb();
        zzvc.zzbvp = zzvc.zzvq();
    }

    private static zzva zzvq() {
        try {
            return Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor().newInstance();
        }
        catch(Exception ) {
            return null;
        }
    }

    static zzva zzvr() {
        return zzvc.zzbvo;
    }

    static zzva zzvs() {
        if(zzvc.zzbvp != null) {
            return zzvc.zzbvp;
        }

        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}

