package com.google.android.gms.common;

import java.lang.ref.WeakReference;

abstract class zzc extends CertData {
    private WeakReference zzbe;
    private static final WeakReference zzbf;

    static {
        zzc.zzbf = new WeakReference(null);
    }

    zzc(byte[] arg1) {
        super(arg1);
        this.zzbe = zzc.zzbf;
    }

    final byte[] getBytes() {
        byte[] v0_2;
        __monitor_enter(this);
        try {
            Object v0_1 = this.zzbe.get();
            if(v0_1 == null) {
                v0_2 = this.zzf();
                this.zzbe = new WeakReference(v0_2);
            }

            __monitor_exit(this);
            return v0_2;
        label_11:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_11;
        }

        throw v0;
    }

    protected abstract byte[] zzf();
}

