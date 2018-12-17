package com.google.android.gms.internal.places;

public final class zzkq implements Cloneable {
    private int mSize;
    private static final zzkr zzaai;
    private boolean zzaaj;
    private int[] zzaak;
    private zzkr[] zzaal;

    static {
        zzkq.zzaai = new zzkr();
    }

    zzkq() {
        this(10);
    }

    private zzkq(int arg3) {
        super();
        this.zzaaj = false;
        arg3 = zzkq.idealIntArraySize(arg3);
        this.zzaak = new int[arg3];
        this.zzaal = new zzkr[arg3];
        this.mSize = 0;
    }

    public final Object clone() {
        int v0 = this.mSize;
        zzkq v1 = new zzkq(v0);
        int v4 = 0;
        System.arraycopy(this.zzaak, 0, v1.zzaak, 0, v0);
        while(v4 < v0) {
            if(this.zzaal[v4] != null) {
                v1.zzaal[v4] = this.zzaal[v4].clone();
            }

            ++v4;
        }

        v1.mSize = v0;
        return v1;
    }

    public final boolean equals(Object arg9) {
        int v9_1;
        int v1_1;
        if((((zzkq)arg9)) == this) {
            return 1;
        }

        if(!(arg9 instanceof zzkq)) {
            return 0;
        }

        if(this.mSize != ((zzkq)arg9).mSize) {
            return 0;
        }

        int[] v1 = this.zzaak;
        int[] v3 = ((zzkq)arg9).zzaak;
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
            zzkr[] v1_2 = this.zzaal;
            zzkr[] v9 = ((zzkq)arg9).zzaal;
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
            v0 = (v0 * 31 + this.zzaak[v1]) * 31 + this.zzaal[v1].hashCode();
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

    final void zzb(int arg7, zzkr arg8) {
        int v0 = this.zzbw(arg7);
        if(v0 >= 0) {
            this.zzaal[v0] = arg8;
            return;
        }

        v0 ^= -1;
        if(v0 < this.mSize && this.zzaal[v0] == zzkq.zzaai) {
            this.zzaak[v0] = arg7;
            this.zzaal[v0] = arg8;
            return;
        }

        if(this.mSize >= this.zzaak.length) {
            int v1 = zzkq.idealIntArraySize(this.mSize + 1);
            int[] v2 = new int[v1];
            zzkr[] v1_1 = new zzkr[v1];
            System.arraycopy(this.zzaak, 0, v2, 0, this.zzaak.length);
            System.arraycopy(this.zzaal, 0, v1_1, 0, this.zzaal.length);
            this.zzaak = v2;
            this.zzaal = v1_1;
        }

        if(this.mSize - v0 != 0) {
            int v3 = v0 + 1;
            System.arraycopy(this.zzaak, v0, this.zzaak, v3, this.mSize - v0);
            System.arraycopy(this.zzaal, v0, this.zzaal, v3, this.mSize - v0);
        }

        this.zzaak[v0] = arg7;
        this.zzaal[v0] = arg8;
        ++this.mSize;
    }

    final zzkr zzbu(int arg3) {
        arg3 = this.zzbw(arg3);
        if(arg3 >= 0) {
            if(this.zzaal[arg3] == zzkq.zzaai) {
            }
            else {
                return this.zzaal[arg3];
            }
        }

        return null;
    }

    final zzkr zzbv(int arg2) {
        return this.zzaal[arg2];
    }

    private final int zzbw(int arg5) {
        int v2;
        int v0 = this.mSize - 1;
        int v1 = 0;
        while(true) {
            if(v1 > v0) {
                goto label_15;
            }

            v2 = v1 + v0 >>> 1;
            int v3 = this.zzaak[v2];
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

