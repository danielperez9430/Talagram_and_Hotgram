package com.google.android.gms.internal.measurement;

import java.io.IOException;

public abstract class zztw implements zzwt {
    protected int zzbtr;
    private static boolean zzbts = false;

    static {
    }

    public zztw() {
        super();
        this.zzbtr = 0;
    }

    void zzah(int arg1) {
        throw new UnsupportedOperationException();
    }

    public final zzud zztt() {
        try {
            zzuk v0_1 = zzud.zzam(this.zzvu());
            this.zzb(v0_1.zzuf());
            return v0_1.zzue();
        }
        catch(IOException v0) {
            String v3 = this.getClass().getName();
            StringBuilder v5 = new StringBuilder(String.valueOf(v3).length() + 62 + String.valueOf("ByteString").length());
            v5.append("Serializing ");
            v5.append(v3);
            v5.append(" to a ");
            v5.append("ByteString");
            v5.append(" threw an IOException (should never happen).");
            throw new RuntimeException(v5.toString(), ((Throwable)v0));
        }
    }

    int zztu() {
        throw new UnsupportedOperationException();
    }
}

