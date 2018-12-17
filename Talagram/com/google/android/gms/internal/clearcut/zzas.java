package com.google.android.gms.internal.clearcut;

import java.io.IOException;

public abstract class zzas implements zzdo {
    protected int zzex;
    private static boolean zzey = false;

    static {
    }

    public zzas() {
        super();
        this.zzex = 0;
    }

    void zzf(int arg1) {
        throw new UnsupportedOperationException();
    }

    public final zzbb zzr() {
        try {
            zzbg v0_1 = zzbb.zzk(this.zzas());
            this.zzb(v0_1.zzae());
            return v0_1.zzad();
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

    int zzs() {
        throw new UnsupportedOperationException();
    }
}

