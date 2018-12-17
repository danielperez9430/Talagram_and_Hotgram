package com.google.android.gms.internal.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class zzbe implements Cloneable {
    private Object value;
    private zzbc zzcn;
    private List zzco;

    zzbe() {
        super();
        this.zzco = new ArrayList();
    }

    public final Object clone() {
        return this.zzae();
    }

    public final boolean equals(Object arg4) {
        if((((zzbe)arg4)) == this) {
            return 1;
        }

        if(!(arg4 instanceof zzbe)) {
            return 0;
        }

        if(this.value != null && ((zzbe)arg4).value != null) {
            if(this.zzcn != ((zzbe)arg4).zzcn) {
                return 0;
            }
            else if(!this.zzcn.zzci.isArray()) {
                return this.value.equals(((zzbe)arg4).value);
            }
            else if((this.value instanceof byte[])) {
                return Arrays.equals(this.value, ((zzbe)arg4).value);
            }
            else if((this.value instanceof int[])) {
                return Arrays.equals(this.value, ((zzbe)arg4).value);
            }
            else if((this.value instanceof long[])) {
                return Arrays.equals(this.value, ((zzbe)arg4).value);
            }
            else if((this.value instanceof float[])) {
                return Arrays.equals(this.value, ((zzbe)arg4).value);
            }
            else if((this.value instanceof double[])) {
                return Arrays.equals(this.value, ((zzbe)arg4).value);
            }
            else if((this.value instanceof boolean[])) {
                return Arrays.equals(this.value, ((zzbe)arg4).value);
            }
            else {
                return Arrays.deepEquals(this.value, ((zzbe)arg4).value);
            }
        }

        if(this.zzco != null && ((zzbe)arg4).zzco != null) {
            return this.zzco.equals(((zzbe)arg4).zzco);
        }

        try {
            return Arrays.equals(this.toByteArray(), ((zzbe)arg4).toByteArray());
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
        byte[] v0 = new byte[this.zzt()];
        this.zza(zzaz.zza(v0));
        return v0;
    }

    final void zza(zzaz arg4) {
        if(this.value == null) {
            Iterator v0 = this.zzco.iterator();
            while(v0.hasNext()) {
                Object v1 = v0.next();
                arg4.zzm(((zzbj)v1).tag);
                arg4.zzc(((zzbj)v1).zzcr);
            }

            return;
        }

        throw new NoSuchMethodError();
    }

    final void zza(zzbj arg4) {
        if(this.zzco != null) {
            this.zzco.add(arg4);
            return;
        }

        if((this.value instanceof zzbh)) {
            byte[] v4 = arg4.zzcr;
            zzay v0 = zzay.zza(v4, 0, v4.length);
            int v1 = v0.zzy();
            if(v1 == v4.length - zzaz.zzj(v1)) {
                zzbh v4_1 = this.value.zza(v0);
                this.zzcn = this.zzcn;
                this.value = v4_1;
                this.zzco = null;
                return;
            }

            throw zzbg.zzaf();
        }

        if((this.value instanceof zzbh[])) {
            Collections.singletonList(arg4);
            throw new NoSuchMethodError();
        }

        Collections.singletonList(arg4);
        throw new NoSuchMethodError();
    }

    private final zzbe zzae() {
        // Method was not decompiled
    }

    final int zzt() {
        if(this.value == null) {
            Iterator v0 = this.zzco.iterator();
            int v2;
            for(v2 = 0; v0.hasNext(); v2 += zzaz.zzn(((zzbj)v3).tag) + ((zzbj)v3).zzcr.length) {
                Object v3 = v0.next();
            }

            return v2;
        }

        throw new NoSuchMethodError();
    }
}

