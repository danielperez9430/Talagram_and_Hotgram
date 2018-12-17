package com.google.android.gms.internal.places;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public final class zzkp {
    private final int tag;
    private final int type;
    protected final Class zzaag;
    protected final boolean zzaah;
    private final zzgz zzsj;

    private zzkp(int arg7, Class arg8, int arg9, boolean arg10) {
        this(11, arg8, null, arg9, false);
    }

    private zzkp(int arg1, Class arg2, zzgz arg3, int arg4, boolean arg5) {
        super();
        this.type = arg1;
        this.zzaag = arg2;
        this.tag = arg4;
        this.zzaah = false;
        this.zzsj = null;
    }

    public final boolean equals(Object arg5) {
        if(this == (((zzkp)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof zzkp)) {
            return 0;
        }

        if(this.type == ((zzkp)arg5).type && this.zzaag == ((zzkp)arg5).zzaag && this.tag == ((zzkp)arg5).tag && this.zzaah == ((zzkp)arg5).zzaah) {
            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        return (((this.type + 1147) * 31 + this.zzaag.hashCode()) * 31 + this.tag) * 31 + this.zzaah;
    }

    private final Object zzab(zzkl arg5) {
        String v0_1;
        StringBuilder v3;
        Class v0 = this.zzaah ? this.zzaag.getComponentType() : this.zzaag;
        try {
            switch(this.type) {
                case 10: {
                    goto label_13;
                }
                case 11: {
                    goto label_10;
                }
            }

            int v1 = this.type;
            v3 = new StringBuilder(24);
            v3.append("Unknown type ");
            v3.append(v1);
            throw new IllegalArgumentException(v3.toString());
        label_10:
            Object v1_1 = v0.newInstance();
            arg5.zzb(((zzku)v1_1));
            return v1_1;
        label_13:
            v1_1 = v0.newInstance();
            arg5.zzb(((zzku)v1_1), this.tag >>> 3);
            return v1_1;
        }
        catch(IOException v5) {
            throw new IllegalArgumentException("Error reading extension field", ((Throwable)v5));
        }
        catch(IllegalAccessException v5_1) {
            v0_1 = String.valueOf(v0);
            v3 = new StringBuilder(String.valueOf(v0_1).length() + 33);
            v3.append("Error creating instance of class ");
            v3.append(v0_1);
            throw new IllegalArgumentException(v3.toString(), ((Throwable)v5_1));
        }
        catch(InstantiationException v5_2) {
            v0_1 = String.valueOf(v0);
            v3 = new StringBuilder(String.valueOf(v0_1).length() + 33);
            v3.append("Error creating instance of class ");
            v3.append(v0_1);
            throw new IllegalArgumentException(v3.toString(), ((Throwable)v5_2));
        }
    }

    final Object zzae(List arg7) {
        Object v0 = null;
        if(arg7 == null) {
            return v0;
        }

        if(this.zzaah) {
            ArrayList v1 = new ArrayList();
            int v2 = 0;
            int v3;
            for(v3 = 0; v3 < arg7.size(); ++v3) {
                Object v4 = arg7.get(v3);
                if(((zzkw)v4).zzoa.length != 0) {
                    ((List)v1).add(this.zzab(zzkl.zzh(((zzkw)v4).zzoa)));
                }
            }

            int v7 = ((List)v1).size();
            if(v7 == 0) {
                return v0;
            }

            v0 = this.zzaag.cast(Array.newInstance(this.zzaag.getComponentType(), v7));
            while(v2 < v7) {
                Array.set(v0, v2, ((List)v1).get(v2));
                ++v2;
            }

            return v0;
        }

        if(arg7.isEmpty()) {
            return v0;
        }

        return this.zzaag.cast(this.zzab(zzkl.zzh(arg7.get(arg7.size() - 1).zzoa)));
    }

    public static zzkp zzb(int arg1, Class arg2, long arg3) {
        return new zzkp(11, arg2, ((int)arg3), false);
    }

    protected final void zzb(Object arg3, zzkm arg4) {
        try {
            arg4.zzbt(this.tag);
            switch(this.type) {
                case 10: {
                    goto label_8;
                }
                case 11: {
                    goto label_6;
                }
            }

            int v4 = this.type;
            StringBuilder v1 = new StringBuilder(24);
            v1.append("Unknown type ");
            v1.append(v4);
            throw new IllegalArgumentException(v1.toString());
        label_6:
            arg4.zzc(((zzku)arg3));
            return;
        label_8:
            int v0 = this.tag >>> 3;
            ((zzku)arg3).zzb(arg4);
            arg4.zzd(v0, 4);
            return;
        }
        catch(IOException v3) {
            throw new IllegalStateException(((Throwable)v3));
        }
    }

    protected final int zzt(Object arg4) {
        int v0 = this.tag >>> 3;
        switch(this.type) {
            case 10: {
                goto label_17;
            }
            case 11: {
                goto label_15;
            }
        }

        v0 = this.type;
        StringBuilder v2 = new StringBuilder(24);
        v2.append("Unknown type ");
        v2.append(v0);
        throw new IllegalArgumentException(v2.toString());
    label_17:
        return (zzkm.zzas(v0) << 1) + ((zzku)arg4).zzdg();
    label_15:
        return zzkm.zzc(v0, ((zzku)arg4));
    }
}

