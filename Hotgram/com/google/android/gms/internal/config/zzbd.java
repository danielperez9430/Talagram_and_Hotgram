package com.google.android.gms.internal.config;

public final class zzbd implements Cloneable {
    private int mSize;
    private static final zzbe zzcj;
    private boolean zzck;
    private int[] zzcl;
    private zzbe[] zzcm;

    static {
        zzbd.zzcj = new zzbe();
    }

    zzbd() {
        this(10);
    }

    private zzbd(int arg3) {
        super();
        this.zzck = false;
        arg3 = zzbd.idealIntArraySize(arg3);
        this.zzcl = new int[arg3];
        this.zzcm = new zzbe[arg3];
        this.mSize = 0;
    }

    public final Object clone() {
        int v0 = this.mSize;
        zzbd v1 = new zzbd(v0);
        int v4 = 0;
        System.arraycopy(this.zzcl, 0, v1.zzcl, 0, v0);
        while(v4 < v0) {
            if(this.zzcm[v4] != null) {
                v1.zzcm[v4] = this.zzcm[v4].clone();
            }

            ++v4;
        }

        v1.mSize = v0;
        return v1;
    }

    public final boolean equals(Object arg9) {
        int v9_1;
        int v1_1;
        if((((zzbd)arg9)) == this) {
            return 1;
        }

        if(!(arg9 instanceof zzbd)) {
            return 0;
        }

        if(this.mSize != ((zzbd)arg9).mSize) {
            return 0;
        }

        int[] v1 = this.zzcl;
        int[] v3 = ((zzbd)arg9).zzcl;
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
            zzbe[] v1_2 = this.zzcm;
            zzbe[] v9 = ((zzbd)arg9).zzcm;
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
            v0 = (v0 * 31 + this.zzcl[v1]) * 31 + this.zzcm[v1].hashCode();
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

    final void zza(int arg7, zzbe arg8) {
        int v0 = this.zzq(arg7);
        if(v0 >= 0) {
            this.zzcm[v0] = arg8;
            return;
        }

        v0 ^= -1;
        if(v0 < this.mSize && this.zzcm[v0] == zzbd.zzcj) {
            this.zzcl[v0] = arg7;
            this.zzcm[v0] = arg8;
            return;
        }

        if(this.mSize >= this.zzcl.length) {
            int v1 = zzbd.idealIntArraySize(this.mSize + 1);
            int[] v2 = new int[v1];
            zzbe[] v1_1 = new zzbe[v1];
            System.arraycopy(this.zzcl, 0, v2, 0, this.zzcl.length);
            System.arraycopy(this.zzcm, 0, v1_1, 0, this.zzcm.length);
            this.zzcl = v2;
            this.zzcm = v1_1;
        }

        if(this.mSize - v0 != 0) {
            int v3 = v0 + 1;
            System.arraycopy(this.zzcl, v0, this.zzcl, v3, this.mSize - v0);
            System.arraycopy(this.zzcm, v0, this.zzcm, v3, this.mSize - v0);
        }

        this.zzcl[v0] = arg7;
        this.zzcm[v0] = arg8;
        ++this.mSize;
    }

    final zzbe zzo(int arg3) {
        arg3 = this.zzq(arg3);
        if(arg3 >= 0) {
            if(this.zzcm[arg3] == zzbd.zzcj) {
            }
            else {
                return this.zzcm[arg3];
            }
        }

        return null;
    }

    final zzbe zzp(int arg2) {
        return this.zzcm[arg2];
    }

    private final int zzq(int arg5) {
        int v2;
        int v0 = this.mSize - 1;
        int v1 = 0;
        while(true) {
            if(v1 > v0) {
                goto label_15;
            }

            v2 = v1 + v0 >>> 1;
            int v3 = this.zzcl[v2];
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

