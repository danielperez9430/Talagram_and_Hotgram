package com.google.android.gms.internal.wearable;

public final class zzp implements Cloneable {
    private int mSize;
    private static final zzq zzhe;
    private boolean zzhf;
    private int[] zzhg;
    private zzq[] zzhh;

    static {
        zzp.zzhe = new zzq();
    }

    zzp() {
        this(10);
    }

    private zzp(int arg3) {
        super();
        this.zzhf = false;
        arg3 = zzp.idealIntArraySize(arg3);
        this.zzhg = new int[arg3];
        this.zzhh = new zzq[arg3];
        this.mSize = 0;
    }

    public final Object clone() {
        int v0 = this.mSize;
        zzp v1 = new zzp(v0);
        int v4 = 0;
        System.arraycopy(this.zzhg, 0, v1.zzhg, 0, v0);
        while(v4 < v0) {
            if(this.zzhh[v4] != null) {
                v1.zzhh[v4] = this.zzhh[v4].clone();
            }

            ++v4;
        }

        v1.mSize = v0;
        return v1;
    }

    public final boolean equals(Object arg9) {
        int v9_1;
        int v1_1;
        if((((zzp)arg9)) == this) {
            return 1;
        }

        if(!(arg9 instanceof zzp)) {
            return 0;
        }

        if(this.mSize != ((zzp)arg9).mSize) {
            return 0;
        }

        int[] v1 = this.zzhg;
        int[] v3 = ((zzp)arg9).zzhg;
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
            zzq[] v1_2 = this.zzhh;
            zzq[] v9 = ((zzp)arg9).zzhh;
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
            v0 = (v0 * 31 + this.zzhg[v1]) * 31 + this.zzhh[v1].hashCode();
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

    final void zza(int arg7, zzq arg8) {
        int v0 = this.zzq(arg7);
        if(v0 >= 0) {
            this.zzhh[v0] = arg8;
            return;
        }

        v0 ^= -1;
        if(v0 < this.mSize && this.zzhh[v0] == zzp.zzhe) {
            this.zzhg[v0] = arg7;
            this.zzhh[v0] = arg8;
            return;
        }

        if(this.mSize >= this.zzhg.length) {
            int v1 = zzp.idealIntArraySize(this.mSize + 1);
            int[] v2 = new int[v1];
            zzq[] v1_1 = new zzq[v1];
            System.arraycopy(this.zzhg, 0, v2, 0, this.zzhg.length);
            System.arraycopy(this.zzhh, 0, v1_1, 0, this.zzhh.length);
            this.zzhg = v2;
            this.zzhh = v1_1;
        }

        if(this.mSize - v0 != 0) {
            int v3 = v0 + 1;
            System.arraycopy(this.zzhg, v0, this.zzhg, v3, this.mSize - v0);
            System.arraycopy(this.zzhh, v0, this.zzhh, v3, this.mSize - v0);
        }

        this.zzhg[v0] = arg7;
        this.zzhh[v0] = arg8;
        ++this.mSize;
    }

    final zzq zzo(int arg3) {
        arg3 = this.zzq(arg3);
        if(arg3 >= 0) {
            if(this.zzhh[arg3] == zzp.zzhe) {
            }
            else {
                return this.zzhh[arg3];
            }
        }

        return null;
    }

    final zzq zzp(int arg2) {
        return this.zzhh[arg2];
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
            int v3 = this.zzhg[v2];
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

