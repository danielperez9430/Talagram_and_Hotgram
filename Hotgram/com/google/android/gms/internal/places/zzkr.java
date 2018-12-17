package com.google.android.gms.internal.places;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class zzkr implements Cloneable {
    private Object value;
    private zzkp zzaam;
    private List zzaan;

    zzkr() {
        super();
        this.zzaan = new ArrayList();
    }

    public final Object clone() {
        return this.zzhf();
    }

    public final boolean equals(Object arg4) {
        if((((zzkr)arg4)) == this) {
            return 1;
        }

        if(!(arg4 instanceof zzkr)) {
            return 0;
        }

        if(this.value != null && ((zzkr)arg4).value != null) {
            if(this.zzaam != ((zzkr)arg4).zzaam) {
                return 0;
            }
            else if(!this.zzaam.zzaag.isArray()) {
                return this.value.equals(((zzkr)arg4).value);
            }
            else if((this.value instanceof byte[])) {
                return Arrays.equals(this.value, ((zzkr)arg4).value);
            }
            else if((this.value instanceof int[])) {
                return Arrays.equals(this.value, ((zzkr)arg4).value);
            }
            else if((this.value instanceof long[])) {
                return Arrays.equals(this.value, ((zzkr)arg4).value);
            }
            else if((this.value instanceof float[])) {
                return Arrays.equals(this.value, ((zzkr)arg4).value);
            }
            else if((this.value instanceof double[])) {
                return Arrays.equals(this.value, ((zzkr)arg4).value);
            }
            else if((this.value instanceof boolean[])) {
                return Arrays.equals(this.value, ((zzkr)arg4).value);
            }
            else {
                return Arrays.deepEquals(this.value, ((zzkr)arg4).value);
            }
        }

        if(this.zzaan != null && ((zzkr)arg4).zzaan != null) {
            return this.zzaan.equals(((zzkr)arg4).zzaan);
        }

        try {
            return Arrays.equals(this.toByteArray(), ((zzkr)arg4).toByteArray());
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
        byte[] v0 = new byte[this.zzal()];
        this.zzb(zzkm.zzi(v0));
        return v0;
    }

    final int zzal() {
        int v4;
        Object v2;
        int v1 = 0;
        if(this.value != null) {
            zzkp v0 = this.zzaam;
            v2 = this.value;
            if(v0.zzaah) {
                int v3 = Array.getLength(v2);
                v4 = 0;
                while(v1 < v3) {
                    if(Array.get(v2, v1) != null) {
                        v4 += v0.zzt(Array.get(v2, v1));
                    }

                    ++v1;
                }
            }
            else {
                v4 = v0.zzt(v2);
            }
        }
        else {
            Iterator v0_1 = this.zzaan.iterator();
            for(v4 = 0; v0_1.hasNext(); v4 += zzkm.zzba(((zzkw)v2).tag) + ((zzkw)v2).zzoa.length) {
                v2 = v0_1.next();
            }
        }

        return v4;
    }

    final void zzb(zzkm arg6) {
        Object v1;
        if(this.value != null) {
            zzkp v0 = this.zzaam;
            v1 = this.value;
            if(v0.zzaah) {
                int v2 = Array.getLength(v1);
                int v3;
                for(v3 = 0; v3 < v2; ++v3) {
                    Object v4 = Array.get(v1, v3);
                    if(v4 != null) {
                        v0.zzb(v4, arg6);
                    }
                }

                return;
            }

            v0.zzb(v1, arg6);
            return;
        }

        Iterator v0_1 = this.zzaan.iterator();
        while(v0_1.hasNext()) {
            v1 = v0_1.next();
            arg6.zzbt(((zzkw)v1).tag);
            arg6.zzk(((zzkw)v1).zzoa);
        }
    }

    final void zzb(zzkw arg5) {
        Object[] v5_3;
        Object v5_2;
        if(this.zzaan != null) {
            this.zzaan.add(arg5);
            return;
        }

        if((this.value instanceof zzku)) {
            byte[] v5 = arg5.zzoa;
            zzkl v0 = zzkl.zzk(v5, 0, v5.length);
            int v1 = v0.zzcm();
            if(v1 == v5.length - zzkm.zzat(v1)) {
                zzku v5_1 = this.value.zzb(v0);
            }
            else {
                throw zzkt.zzhg();
            }
        }
        else if((this.value instanceof zzku[])) {
            v5_2 = this.zzaam.zzae(Collections.singletonList(arg5));
            Object v0_1 = this.value;
            Object[] v2 = Arrays.copyOf(((Object[])v0_1), v0_1.length + v5_2.length);
            System.arraycopy(v5_2, 0, v2, v0_1.length, v5_2.length);
            v5_3 = v2;
        }
        else {
            v5_2 = this.zzaam.zzae(Collections.singletonList(arg5));
        }

        this.zzaam = this.zzaam;
        this.value = v5_3;
        this.zzaan = null;
    }

    private final zzkr zzhf() {
        // Method was not decompiled
    }
}

