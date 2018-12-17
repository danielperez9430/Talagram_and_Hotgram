package com.google.android.gms.internal.clearcut;

public final class zzfw implements Cloneable {
    private int mSize;
    private static final zzfx zzrl;
    private boolean zzrm;
    private int[] zzrn;
    private zzfx[] zzro;

    static {
        zzfw.zzrl = new zzfx();
    }

    zzfw() {
        this(10);
    }

    private zzfw(int arg5) {
        super();
        this.zzrm = false;
        arg5 <<= 2;
        int v1 = 4;
        int v2 = 4;
        while(v2 < 32) {
            int v3 = (1 << v2) - 12;
            if(arg5 <= v3) {
                arg5 = v3;
            }
            else {
                ++v2;
                continue;
            }

            break;
        }

        arg5 /= v1;
        this.zzrn = new int[arg5];
        this.zzro = new zzfx[arg5];
        this.mSize = 0;
    }

    public final Object clone() {
        int v0 = this.mSize;
        zzfw v1 = new zzfw(v0);
        int v4 = 0;
        System.arraycopy(this.zzrn, 0, v1.zzrn, 0, v0);
        while(v4 < v0) {
            if(this.zzro[v4] != null) {
                v1.zzro[v4] = this.zzro[v4].clone();
            }

            ++v4;
        }

        v1.mSize = v0;
        return v1;
    }

    public final boolean equals(Object arg9) {
        int v9_1;
        int v1_1;
        if((((zzfw)arg9)) == this) {
            return 1;
        }

        if(!(arg9 instanceof zzfw)) {
            return 0;
        }

        if(this.mSize != ((zzfw)arg9).mSize) {
            return 0;
        }

        int[] v1 = this.zzrn;
        int[] v3 = ((zzfw)arg9).zzrn;
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
            zzfx[] v1_2 = this.zzro;
            zzfx[] v9 = ((zzfw)arg9).zzro;
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
            v0 = (v0 * 31 + this.zzrn[v1]) * 31 + this.zzro[v1].hashCode();
        }

        return v0;
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

    final zzfx zzaq(int arg2) {
        return this.zzro[arg2];
    }
}

