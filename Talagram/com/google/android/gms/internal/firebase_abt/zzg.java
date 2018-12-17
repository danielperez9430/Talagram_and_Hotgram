package com.google.android.gms.internal.firebase_abt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class zzg implements Cloneable {
    private Object value;
    private zze zzy;
    private List zzz;

    zzg() {
        super();
        this.zzz = new ArrayList();
    }

    public final Object clone() {
        return this.zzk();
    }

    public final boolean equals(Object arg4) {
        if((((zzg)arg4)) == this) {
            return 1;
        }

        if(!(arg4 instanceof zzg)) {
            return 0;
        }

        if(this.value != null && ((zzg)arg4).value != null) {
            if(this.zzy != ((zzg)arg4).zzy) {
                return 0;
            }
            else if(!this.zzy.zzt.isArray()) {
                return this.value.equals(((zzg)arg4).value);
            }
            else if((this.value instanceof byte[])) {
                return Arrays.equals(this.value, ((zzg)arg4).value);
            }
            else if((this.value instanceof int[])) {
                return Arrays.equals(this.value, ((zzg)arg4).value);
            }
            else if((this.value instanceof long[])) {
                return Arrays.equals(this.value, ((zzg)arg4).value);
            }
            else if((this.value instanceof float[])) {
                return Arrays.equals(this.value, ((zzg)arg4).value);
            }
            else if((this.value instanceof double[])) {
                return Arrays.equals(this.value, ((zzg)arg4).value);
            }
            else if((this.value instanceof boolean[])) {
                return Arrays.equals(this.value, ((zzg)arg4).value);
            }
            else {
                return Arrays.deepEquals(this.value, ((zzg)arg4).value);
            }
        }

        if(this.zzz != null && ((zzg)arg4).zzz != null) {
            return this.zzz.equals(((zzg)arg4).zzz);
        }

        try {
            return Arrays.equals(this.toByteArray(), ((zzg)arg4).toByteArray());
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
        Iterator v2_1;
        Object v3;
        if(this.value == null) {
            Iterator v0 = this.zzz.iterator();
            int v2;
            for(v2 = 0; v0.hasNext(); v2 += zzb.zzf(((zzl)v3).tag) + ((zzl)v3).zzac.length) {
                v3 = v0.next();
            }

            byte[] v0_1 = new byte[v2];
            zzb v1 = zzb.zzb(v0_1);
            if(this.value == null) {
                v2_1 = this.zzz.iterator();
            }
            else {
                throw new NoSuchMethodError();
            }

            while(v2_1.hasNext()) {
                v3 = v2_1.next();
                v1.zze(((zzl)v3).tag);
                v1.zzc(((zzl)v3).zzac);
            }

            return v0_1;
        }

        throw new NoSuchMethodError();
    }

    final void zza(zzl arg4) {
        if(this.zzz != null) {
            this.zzz.add(arg4);
            return;
        }

        if((this.value instanceof zzj)) {
            byte[] v4 = arg4.zzac;
            zza v0 = zza.zza(v4, 0, v4.length);
            int v1 = v0.zzg();
            int v4_1 = v4.length;
            int v2 = v1 >= 0 ? zzb.zzf(v1) : 10;
            if(v1 == v4_1 - v2) {
                zzj v4_2 = this.value.zza(v0);
                this.zzy = this.zzy;
                this.value = v4_2;
                this.zzz = null;
                return;
            }

            throw zzi.zzl();
        }

        if((this.value instanceof zzj[])) {
            Collections.singletonList(arg4);
            throw new NoSuchMethodError();
        }

        Collections.singletonList(arg4);
        throw new NoSuchMethodError();
    }

    private final zzg zzk() {
        // Method was not decompiled
    }
}

