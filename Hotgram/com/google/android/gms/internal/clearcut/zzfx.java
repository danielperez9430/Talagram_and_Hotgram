package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

final class zzfx implements Cloneable {
    private Object value;
    private zzfv zzrp;
    private List zzrq;

    zzfx() {
        super();
        this.zzrq = new ArrayList();
    }

    public final Object clone() {
        return this.zzeq();
    }

    public final boolean equals(Object arg4) {
        if((((zzfx)arg4)) == this) {
            return 1;
        }

        if(!(arg4 instanceof zzfx)) {
            return 0;
        }

        if(this.value != null && ((zzfx)arg4).value != null) {
            if(this.zzrp != ((zzfx)arg4).zzrp) {
                return 0;
            }
            else if(!this.zzrp.zzrk.isArray()) {
                return this.value.equals(((zzfx)arg4).value);
            }
            else if((this.value instanceof byte[])) {
                return Arrays.equals(this.value, ((zzfx)arg4).value);
            }
            else if((this.value instanceof int[])) {
                return Arrays.equals(this.value, ((zzfx)arg4).value);
            }
            else if((this.value instanceof long[])) {
                return Arrays.equals(this.value, ((zzfx)arg4).value);
            }
            else if((this.value instanceof float[])) {
                return Arrays.equals(this.value, ((zzfx)arg4).value);
            }
            else if((this.value instanceof double[])) {
                return Arrays.equals(this.value, ((zzfx)arg4).value);
            }
            else if((this.value instanceof boolean[])) {
                return Arrays.equals(this.value, ((zzfx)arg4).value);
            }
            else {
                return Arrays.deepEquals(this.value, ((zzfx)arg4).value);
            }
        }

        if(this.zzrq != null && ((zzfx)arg4).zzrq != null) {
            return this.zzrq.equals(((zzfx)arg4).zzrq);
        }

        try {
            return Arrays.equals(this.toByteArray(), ((zzfx)arg4).toByteArray());
        }
        catch(IOException v4) {
            throw new IllegalStateException(((Throwable)v4));
        }
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(this.toByteArray()) + 527;
        }
        catch(IOException v0) {
            throw new IllegalStateException(((Throwable)v0));
        }
    }

    private final byte[] toByteArray() {
        byte[] v0 = new byte[this.zzen()];
        this.zza(zzfs.zzg(v0));
        return v0;
    }

    final void zza(zzfs arg2) {
        if(this.value == null) {
            Iterator v2 = this.zzrq.iterator();
            if(!v2.hasNext()) {
                return;
            }

            v2.next();
            throw new NoSuchMethodError();
        }

        throw new NoSuchMethodError();
    }

    final int zzen() {
        if(this.value == null) {
            Iterator v0 = this.zzrq.iterator();
            if(!v0.hasNext()) {
                return 0;
            }

            v0.next();
            throw new NoSuchMethodError();
        }

        throw new NoSuchMethodError();
    }

    private final zzfx zzeq() {
        // Method was not decompiled
    }
}

