package com.google.android.gms.internal.config;

public final class zzay {
    private final byte[] buffer;
    private final int zzbw;
    private final int zzbx;
    private int zzby;
    private int zzbz;
    private int zzca;
    private int zzcb;
    private int zzcc;
    private int zzcd;
    private int zzce;
    private int zzcf;

    private zzay(byte[] arg1, int arg2, int arg3) {
        super();
        this.zzcc = 2147483647;
        this.zzce = 64;
        this.zzcf = 67108864;
        this.buffer = arg1;
        this.zzbw = 0;
        arg3 = arg3;
        this.zzby = arg3;
        this.zzbx = arg3;
        this.zzca = 0;
    }

    public final int getPosition() {
        return this.zzca - this.zzbw;
    }

    public final byte[] readBytes() {
        int v0 = this.zzy();
        if(v0 >= 0) {
            if(v0 == 0) {
                return zzbk.zzdd;
            }

            if(v0 <= this.zzby - this.zzca) {
                byte[] v1 = new byte[v0];
                System.arraycopy(this.buffer, this.zzca, v1, 0, v0);
                this.zzca += v0;
                return v1;
            }

            throw zzbg.zzaf();
        }

        throw zzbg.zzag();
    }

    public final String readString() {
        int v0 = this.zzy();
        if(v0 >= 0) {
            if(v0 <= this.zzby - this.zzca) {
                String v1 = new String(this.buffer, this.zzca, v0, zzbf.UTF_8);
                this.zzca += v0;
                return v1;
            }

            throw zzbg.zzaf();
        }

        throw zzbg.zzag();
    }

    public static zzay zza(byte[] arg1, int arg2, int arg3) {
        return new zzay(arg1, 0, arg3);
    }

    public final void zza(zzbh arg4) {
        int v0 = this.zzy();
        if(this.zzcd < this.zzce) {
            if(v0 >= 0) {
                v0 += this.zzca;
                int v1 = this.zzcc;
                if(v0 <= v1) {
                    this.zzcc = v0;
                    this.zzaa();
                    ++this.zzcd;
                    arg4.zza(this);
                    this.zzg(0);
                    --this.zzcd;
                    this.zzcc = v1;
                    this.zzaa();
                    return;
                }

                throw zzbg.zzaf();
            }

            throw zzbg.zzag();
        }

        throw new zzbg("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    public final byte[] zza(int arg4, int arg5) {
        if(arg5 == 0) {
            return zzbk.zzdd;
        }

        byte[] v0 = new byte[arg5];
        System.arraycopy(this.buffer, this.zzbw + arg4, v0, 0, arg5);
        return v0;
    }

    private final void zzaa() {
        this.zzby += this.zzbz;
        int v0 = this.zzby;
        if(v0 > this.zzcc) {
            this.zzbz = v0 - this.zzcc;
            this.zzby -= this.zzbz;
            return;
        }

        this.zzbz = 0;
    }

    private final byte zzab() {
        if(this.zzca != this.zzby) {
            byte[] v0 = this.buffer;
            int v1 = this.zzca;
            this.zzca = v1 + 1;
            return v0[v1];
        }

        throw zzbg.zzaf();
    }

    final void zzb(int arg4, int arg5) {
        if(arg4 <= this.zzca - this.zzbw) {
            if(arg4 >= 0) {
                this.zzca = this.zzbw + arg4;
                this.zzcb = arg5;
                return;
            }

            StringBuilder v1 = new StringBuilder(24);
            v1.append("Bad position ");
            v1.append(arg4);
            throw new IllegalArgumentException(v1.toString());
        }

        int v0 = this.zzca - this.zzbw;
        StringBuilder v2 = new StringBuilder(50);
        v2.append("Position ");
        v2.append(arg4);
        v2.append(" is beyond current ");
        v2.append(v0);
        throw new IllegalArgumentException(v2.toString());
    }

    private final void zzg(int arg2) {
        if(this.zzcb == arg2) {
            return;
        }

        throw new zzbg("Protocol message end-group tag did not match expected tag.");
    }

    public final boolean zzh(int arg3) {
        switch(arg3 & 7) {
            case 0: {
                goto label_28;
            }
            case 1: {
                goto label_26;
            }
            case 2: {
                goto label_23;
            }
            case 3: {
                goto label_14;
            }
            case 4: {
                return 0;
            }
            case 5: {
                goto label_7;
            }
        }

        throw new zzbg("Protocol message tag had invalid wire type.");
    label_23:
        this.zzi(this.zzy());
        return 1;
    label_7:
        this.zzab();
        this.zzab();
        this.zzab();
        this.zzab();
        return 1;
    label_26:
        this.zzz();
        return 1;
    label_28:
        this.zzy();
        return 1;
        do {
        label_14:
            int v0 = this.zzx();
            if(v0 == 0) {
                break;
            }
        }
        while(this.zzh(v0));

        this.zzg(arg3 >>> 3 << 3 | 4);
        return 1;
    }

    private final void zzi(int arg3) {
        if(arg3 >= 0) {
            if(this.zzca + arg3 <= this.zzcc) {
                if(arg3 <= this.zzby - this.zzca) {
                    this.zzca += arg3;
                    return;
                }

                throw zzbg.zzaf();
            }

            this.zzi(this.zzcc - this.zzca);
            throw zzbg.zzaf();
        }

        throw zzbg.zzag();
    }

    public final int zzx() {
        if(this.zzca == this.zzby) {
            this.zzcb = 0;
            return 0;
        }

        this.zzcb = this.zzy();
        if(this.zzcb != 0) {
            return this.zzcb;
        }

        throw new zzbg("Protocol message contained an invalid tag (zero).");
    }

    public final int zzy() {
        int v0 = this.zzab();
        if(v0 >= 0) {
            return v0;
        }

        v0 &= 127;
        int v1 = this.zzab();
        if(v1 >= 0) {
            v1 <<= 7;
            goto label_7;
        }
        else {
            v0 |= (v1 & 127) << 7;
            v1 = this.zzab();
            if(v1 >= 0) {
                v1 <<= 14;
                goto label_7;
            }
            else {
                v0 |= (v1 & 127) << 14;
                v1 = this.zzab();
                if(v1 >= 0) {
                    v1 <<= 21;
                label_7:
                    v0 |= v1;
                }
                else {
                    v0 |= (v1 & 127) << 21;
                    v1 = this.zzab();
                    v0 |= v1 << 28;
                    if(v1 < 0) {
                        v1 = 0;
                        while(true) {
                            if(v1 >= 5) {
                                break;
                            }
                            else if(this.zzab() >= 0) {
                                return v0;
                            }
                            else {
                                ++v1;
                                continue;
                            }

                            return v0;
                        }

                        throw new zzbg("CodedInputStream encountered a malformed varint.");
                    }
                }
            }
        }

        return v0;
    }

    public final long zzz() {
        return ((((long)this.zzab())) & 255) << 8 | (((long)this.zzab())) & 255 | ((((long)this.zzab())) & 255) << 16 | ((((long)this.zzab())) & 255) << 24 | ((((long)this.zzab())) & 255) << 32 | ((((long)this.zzab())) & 255) << 40 | ((((long)this.zzab())) & 255) << 48 | ((((long)this.zzab())) & 255) << 56;
    }
}

