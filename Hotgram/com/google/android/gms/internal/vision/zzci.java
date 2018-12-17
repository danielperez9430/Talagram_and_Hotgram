package com.google.android.gms.internal.vision;

final class zzci {
    private static final zzcg zzhs;
    private static final zzcg zzht;

    static {
        zzci.zzhs = new zzch();
        zzci.zzht = zzci.zzbh();
    }

    private static zzcg zzbh() {
        try {
            return Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor().newInstance();
        }
        catch(Exception ) {
            return null;
        }
    }

    static zzcg zzbi() {
        return zzci.zzhs;
    }

    static zzcg zzbj() {
        if(zzci.zzht != null) {
            return zzci.zzht;
        }

        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}

