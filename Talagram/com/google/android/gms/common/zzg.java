package com.google.android.gms.common;

import android.util.Log;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

@CheckReturnValue class zzg {
    private final Throwable cause;
    private static final zzg zzbk;
    final boolean zzbl;
    private final String zzbm;

    static {
        zzg.zzbk = new zzg(true, null, null);
    }

    zzg(boolean arg1, @Nullable String arg2, @Nullable Throwable arg3) {
        super();
        this.zzbl = arg1;
        this.zzbm = arg2;
        this.cause = arg3;
    }

    @Nullable String getErrorMessage() {
        return this.zzbm;
    }

    static zzg zza(String arg2, Throwable arg3) {
        return new zzg(false, arg2, arg3);
    }

    static zzg zza(String arg7, CertData arg8, boolean arg9, boolean arg10) {
        return new zzi(arg7, arg8, arg9, arg10, null);
    }

    static zzg zze(String arg3) {
        return new zzg(false, arg3, null);
    }

    static zzg zzg() {
        return zzg.zzbk;
    }

    final void zzh() {
        if(!this.zzbl) {
            String v0 = String.valueOf("GoogleCertificatesRslt: ");
            String v1 = String.valueOf(this.getErrorMessage());
            v0 = v1.length() != 0 ? v0.concat(v1) : new String(v0);
            if(this.cause != null) {
                throw new SecurityException(v0, this.cause);
            }

            throw new SecurityException(v0);
        }
    }

    final void zzi() {
        if(!this.zzbl) {
            if(this.cause != null) {
                Log.d("GoogleCertificatesRslt", this.getErrorMessage(), this.cause);
                return;
            }
            else {
                Log.d("GoogleCertificatesRslt", this.getErrorMessage());
            }
        }
    }
}

