package com.google.android.gms.internal.measurement;

public final class zzzc implements Cloneable {
    private int mSize;
    private static final zzzd zzcff;
    private boolean zzcfg;
    private int[] zzcfh;
    private zzzd[] zzcfi;

    static {
        zzzc.zzcff = new zzzd();
    }

    zzzc() {
        this(10);
    }

    private zzzc(int arg3) {
        super();
        this.zzcfg = false;
        arg3 = zzzc.idealIntArraySize(arg3);
        this.zzcfh = new int[arg3];
        this.zzcfi = new zzzd[arg3];
        this.mSize = 0;
    }

    public final Object clone() {
        int v0 = this.mSize;
        zzzc v1 = new zzzc(v0);
        int v4 = 0;
        System.arraycopy(this.zzcfh, 0, v1.zzcfh, 0, v0);
        while(v4 < v0) {
            if(this.zzcfi[v4] != null) {
                v1.zzcfi[v4] = this.zzcfi[v4].clone();
            }

            ++v4;
        }

        v1.mSize = v0;
        return v1;
    }

    public final boolean equals(Object arg9) {
        int v9_1;
        int v1_1;
        if((((zzzc)arg9)) == this) {
            return 1;
        }

        if(!(arg9 instanceof zzzc)) {
            return 0;
        }

        if(this.mSize != ((zzzc)arg9).mSize) {
            return 0;
        }

        int[] v1 = this.zzcfh;
        int[] v3 = ((zzzc)arg9).zzcfh;
        int v4 = this.mSize;
        int v5 = 0;
        while(true) {
            if(v5 >= v4) {
                break;
            }
            else if(v1[v5] != v3[v5]) {
                v1_1 = 0;
            }
            else {
                ++v5;
                continue;
            }

            goto label_24;
        }

        v1_1 = 1;
    label_24:
        if(v1_1 != 0) {
            zzzd[] v1_2 = this.zzcfi;
            zzzd[] v9 = ((zzzc)arg9).zzcfi;
            int v3_1 = this.mSize;
            v4 = 0;
            while(true) {
                if(v4 >= v3_1) {
                    break;
                }
                else if(!v1_2[v4].equals(v9[v4])) {
                    v9_1 = 0;
                }
                else {
                    ++v4;
                    continue;
                }

                goto label_39;
            }

            v9_1 = 1;
        label_39:
            if(v9_1 == 0) {
                return 0;
            }

            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        int v0 = 17;
        int v1;
        for(v1 = 0; v1 < this.mSize; ++v1) {
            v0 = (v0 * 31 + this.zzcfh[v1]) * 31 + this.zzcfi[v1].hashCode();
        }

        return v0;
    }

    private static int idealIntArraySize(int arg3) {
        arg3 <<= 2;
        int v0 = 4;
        int v1 = 4;
        while(v1 < 32) {
            int v2 = (1 << v1) - 12;
            if(arg3 <= v2) {
                arg3 = v2;
            }
            else {
                ++v1;
                continue;
            }

            break;
        }

        return arg3 / v0;
    }

    public final boolean isEmpty() {
        if(this.mSize == 0) {
            return 1;
        }

        return 0;
    }

    final int size() {
        return this.mSize;
    }

    final void zza(int arg7, zzzd arg8) {
        int v0 = this.zzcd(arg7);
        if(v0 >= 0) {
            this.zzcfi[v0] = arg8;
            return;
        }

        v0 ^= -1;
        if(v0 < this.mSize && this.zzcfi[v0] == zzzc.zzcff) {
            this.zzcfh[v0] = arg7;
            this.zzcfi[v0] = arg8;
            return;
        }

        if(this.mSize >= this.zzcfh.length) {
            int v1 = zzzc.idealIntArraySize(this.mSize + 1);
            int[] v2 = new int[v1];
            zzzd[] v1_1 = new zzzd[v1];
            System.arraycopy(this.zzcfh, 0, v2, 0, this.zzcfh.length);
            System.arraycopy(this.zzcfi, 0, v1_1, 0, this.zzcfi.length);
            this.zzcfh = v2;
            this.zzcfi = v1_1;
        }

        if(this.mSize - v0 != 0) {
            int v3 = v0 + 1;
            System.arraycopy(this.zzcfh, v0, this.zzcfh, v3, this.mSize - v0);
            System.arraycopy(this.zzcfi, v0, this.zzcfi, v3, this.mSize - v0);
        }

        this.zzcfh[v0] = arg7;
        this.zzcfi[v0] = arg8;
        ++this.mSize;
    }

    final zzzd zzcb(int arg3) {
        arg3 = this.zzcd(arg3);
        if(arg3 >= 0) {
            if(this.zzcfi[arg3] == zzzc.zzcff) {
            }
            else {
                return this.zzcfi[arg3];
            }
        }

        return null;
    }

    final zzzd zzcc(int arg2) {
        return this.zzcfi[arg2];
    }

    private final int zzcd(int arg5) {
        int v2;
        int v0 = this.mSize - 1;
        int v1 = 0;
        while(true) {
            if(v1 > v0) {
                goto label_15;
            }

            v2 = v1 + v0 >>> 1;
            int v3 = this.zzcfh[v2];
            if(v3 < arg5) {
                v1 = v2 + 1;
                continue;
            }

            if(v3 <= arg5) {
                return v2;
            }

            v0 = v2 - 1;
        }

        return v2;
    label_15:
        return v1 ^ -1;
    }
}

