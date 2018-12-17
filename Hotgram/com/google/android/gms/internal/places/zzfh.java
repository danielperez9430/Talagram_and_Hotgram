package com.google.android.gms.internal.places;

import java.io.IOException;

public abstract class zzfh implements zzih {
    protected int zznh;
    private static boolean zzni = false;

    static {
    }

    public zzfh() {
        super();
        this.zznh = 0;
    }

    public final zzfr zzax() {
        try {
            zzfw v0_1 = zzfr.zzag(this.zzdg());
            this.zzc(v0_1.zzci());
            return v0_1.zzch();
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

    int zzay() {
        throw new UnsupportedOperationException();
    }

    void zzv(int arg1) {
        throw new UnsupportedOperationException();
    }
}

