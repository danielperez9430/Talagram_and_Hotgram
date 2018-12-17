package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class zzzd implements Cloneable {
    private Object value;
    private zzzb zzcfj;
    private List zzcfk;

    zzzd() {
        super();
        this.zzcfk = new ArrayList();
    }

    public final Object clone() {
        return this.zzyv();
    }

    public final boolean equals(Object arg4) {
        if((((zzzd)arg4)) == this) {
            return 1;
        }

        if(!(arg4 instanceof zzzd)) {
            return 0;
        }

        if(this.value != null && ((zzzd)arg4).value != null) {
            if(this.zzcfj != ((zzzd)arg4).zzcfj) {
                return 0;
            }
            else if(!this.zzcfj.zzcfd.isArray()) {
                return this.value.equals(((zzzd)arg4).value);
            }
            else if((this.value instanceof byte[])) {
                return Arrays.equals(this.value, ((zzzd)arg4).value);
            }
            else if((this.value instanceof int[])) {
                return Arrays.equals(this.value, ((zzzd)arg4).value);
            }
            else if((this.value instanceof long[])) {
                return Arrays.equals(this.value, ((zzzd)arg4).value);
            }
            else if((this.value instanceof float[])) {
                return Arrays.equals(this.value, ((zzzd)arg4).value);
            }
            else if((this.value instanceof double[])) {
                return Arrays.equals(this.value, ((zzzd)arg4).value);
            }
            else if((this.value instanceof boolean[])) {
                return Arrays.equals(this.value, ((zzzd)arg4).value);
            }
            else {
                return Arrays.deepEquals(this.value, ((zzzd)arg4).value);
            }
        }

        if(this.zzcfk != null && ((zzzd)arg4).zzcfk != null) {
            return this.zzcfk.equals(((zzzd)arg4).zzcfk);
        }

        try {
            return Arrays.equals(this.toByteArray(), ((zzzd)arg4).toByteArray());
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
        byte[] v0 = new byte[this.zzf()];
        this.zza(zzyy.zzo(v0));
        return v0;
    }

    final void zza(zzyy arg6) {
        Object v1;
        if(this.value != null) {
            zzzb v0 = this.zzcfj;
            v1 = this.value;
            if(v0.zzcfe) {
                int v2 = Array.getLength(v1);
                int v3;
                for(v3 = 0; v3 < v2; ++v3) {
                    Object v4 = Array.get(v1, v3);
                    if(v4 != null) {
                        v0.zza(v4, arg6);
                    }
                }

                return;
            }

            v0.zza(v1, arg6);
            return;
        }

        Iterator v0_1 = this.zzcfk.iterator();
        while(v0_1.hasNext()) {
            v1 = v0_1.next();
            arg6.zzca(((zzzi)v1).tag);
            arg6.zzp(((zzzi)v1).zzbug);
        }
    }

    final void zza(zzzi arg5) {
        Object v5_2;
        zzzg v5_1;
        if(this.zzcfk != null) {
            this.zzcfk.add(arg5);
            return;
        }

        if((this.value instanceof zzzg)) {
            byte[] v5 = arg5.zzbug;
            zzyx v0 = zzyx.zzj(v5, 0, v5.length);
            int v1 = v0.zzuy();
            if(v1 == v5.length - zzyy.zzbc(v1)) {
                v5_1 = this.value.zza(v0);
            }
            else {
                throw zzzf.zzyw();
            }
        }
        else if((this.value instanceof zzzg[])) {
            v5_2 = this.zzcfj.zzah(Collections.singletonList(arg5));
            Object v0_1 = this.value;
            Object[] v2 = Arrays.copyOf(((Object[])v0_1), v0_1.length + v5_2.length);
            System.arraycopy(v5_2, 0, v2, v0_1.length, v5_2.length);
            Object[] v5_3 = v2;
        }
        else {
            v5_2 = this.zzcfj.zzah(Collections.singletonList(arg5));
        }

        this.zzcfj = this.zzcfj;
        this.value = v5_1;
        this.zzcfk = null;
    }

    final Object zzb(zzzb arg2) {
        if(this.value == null) {
            this.zzcfj = arg2;
            this.value = arg2.zzah(this.zzcfk);
            this.zzcfk = null;
        }
        else if(this.zzcfj.equals(arg2)) {
        }
        else {
            throw new IllegalStateException("Tried to getExtension with a different Extension.");
        }

        return this.value;
    }

    final int zzf() {
        int v4;
        Object v2;
        int v1 = 0;
        if(this.value != null) {
            zzzb v0 = this.zzcfj;
            v2 = this.value;
            if(v0.zzcfe) {
                int v3 = Array.getLength(v2);
                v4 = 0;
                while(v1 < v3) {
                    if(Array.get(v2, v1) != null) {
                        v4 += v0.zzak(Array.get(v2, v1));
                    }

                    ++v1;
                }
            }
            else {
                v4 = v0.zzak(v2);
            }
        }
        else {
            Iterator v0_1 = this.zzcfk.iterator();
            for(v4 = 0; v0_1.hasNext(); v4 += zzyy.zzbj(((zzzi)v2).tag) + ((zzzi)v2).zzbug.length) {
                v2 = v0_1.next();
            }
        }

        return v4;
    }

    private final zzzd zzyv() {
        // Method was not decompiled
    }
}

