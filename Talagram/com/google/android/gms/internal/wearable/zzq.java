package com.google.android.gms.internal.wearable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class zzq implements Cloneable {
    private Object value;
    private zzo zzhi;
    private List zzhj;

    zzq() {
        super();
        this.zzhj = new ArrayList();
    }

    public final Object clone() {
        return this.zzt();
    }

    public final boolean equals(Object arg4) {
        if((((zzq)arg4)) == this) {
            return 1;
        }

        if(!(arg4 instanceof zzq)) {
            return 0;
        }

        if(this.value != null && ((zzq)arg4).value != null) {
            if(this.zzhi != ((zzq)arg4).zzhi) {
                return 0;
            }
            else if(!this.zzhi.zzhd.isArray()) {
                return this.value.equals(((zzq)arg4).value);
            }
            else if((this.value instanceof byte[])) {
                return Arrays.equals(this.value, ((zzq)arg4).value);
            }
            else if((this.value instanceof int[])) {
                return Arrays.equals(this.value, ((zzq)arg4).value);
            }
            else if((this.value instanceof long[])) {
                return Arrays.equals(this.value, ((zzq)arg4).value);
            }
            else if((this.value instanceof float[])) {
                return Arrays.equals(this.value, ((zzq)arg4).value);
            }
            else if((this.value instanceof double[])) {
                return Arrays.equals(this.value, ((zzq)arg4).value);
            }
            else if((this.value instanceof boolean[])) {
                return Arrays.equals(this.value, ((zzq)arg4).value);
            }
            else {
                return Arrays.deepEquals(this.value, ((zzq)arg4).value);
            }
        }

        if(this.zzhj != null && ((zzq)arg4).zzhj != null) {
            return this.zzhj.equals(((zzq)arg4).zzhj);
        }

        try {
            return Arrays.equals(this.toByteArray(), ((zzq)arg4).toByteArray());
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
        byte[] v0 = new byte[this.zzg()];
        this.zza(zzl.zzb(v0));
        return v0;
    }

    final void zza(zzl arg4) {
        if(this.value == null) {
            Iterator v0 = this.zzhj.iterator();
            while(v0.hasNext()) {
                Object v1 = v0.next();
                arg4.zzl(((zzv)v1).tag);
                arg4.zzc(((zzv)v1).zzhm);
            }

            return;
        }

        throw new NoSuchMethodError();
    }

    final void zza(zzv arg4) {
        if(this.zzhj != null) {
            this.zzhj.add(arg4);
            return;
        }

        if((this.value instanceof zzt)) {
            byte[] v4 = arg4.zzhm;
            zzk v0 = zzk.zza(v4, 0, v4.length);
            int v1 = v0.zzk();
            if(v1 == v4.length - zzl.zzi(v1)) {
                zzt v4_1 = this.value.zza(v0);
                this.zzhi = this.zzhi;
                this.value = v4_1;
                this.zzhj = null;
                return;
            }

            throw zzs.zzu();
        }

        if((this.value instanceof zzt[])) {
            Collections.singletonList(arg4);
            throw new NoSuchMethodError();
        }

        Collections.singletonList(arg4);
        throw new NoSuchMethodError();
    }

    final int zzg() {
        if(this.value == null) {
            Iterator v0 = this.zzhj.iterator();
            int v2;
            for(v2 = 0; v0.hasNext(); v2 += zzl.zzm(((zzv)v3).tag) + ((zzv)v3).zzhm.length) {
                Object v3 = v0.next();
            }

            return v2;
        }

        throw new NoSuchMethodError();
    }

    private final zzq zzt() {
        // Method was not decompiled
    }
}

