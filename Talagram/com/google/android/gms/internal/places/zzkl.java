package com.google.android.gms.internal.places;

import java.io.IOException;

public final class zzkl {
    private final byte[] buffer;
    private int zzaaa;
    private int zzaab;
    private zzga zzaac;
    private int zzob;
    private int zzoc;
    private int zzod;
    private int zzoi;
    private int zzok;
    private int zzol;
    private final int zzzy;
    private final int zzzz;

    private zzkl(byte[] arg2, int arg3, int arg4) {
        super();
        this.zzol = 2147483647;
        this.zzoc = 64;
        this.zzod = 67108864;
        this.buffer = arg2;
        this.zzzy = arg3;
        arg4 += arg3;
        this.zzaaa = arg4;
        this.zzzz = arg4;
        this.zzaab = arg3;
    }

    public final int getPosition() {
        return this.zzaab - this.zzzy;
    }

    public final byte[] readBytes() {
        int v0 = this.zzcm();
        if(v0 >= 0) {
            if(v0 == 0) {
                return zzkx.zzabb;
            }

            if(v0 <= this.zzaaa - this.zzaab) {
                byte[] v1 = new byte[v0];
                System.arraycopy(this.buffer, this.zzaab, v1, 0, v0);
                this.zzaab += v0;
                return v1;
            }

            throw zzkt.zzhg();
        }

        throw zzkt.zzhh();
    }

    public final String readString() {
        int v0 = this.zzcm();
        if(v0 >= 0) {
            if(v0 <= this.zzaaa - this.zzaab) {
                String v1 = new String(this.buffer, this.zzaab, v0, zzks.UTF_8);
                this.zzaab += v0;
                return v1;
            }

            throw zzkt.zzhg();
        }

        throw zzkt.zzhh();
    }

    public final void zzah(int arg2) {
        if(this.zzok == arg2) {
            return;
        }

        throw new zzkt("Protocol message end-group tag did not match expected tag.");
    }

    public final boolean zzai(int arg3) {
        switch(arg3 & 7) {
            case 0: {
                goto label_25;
            }
            case 1: {
                goto label_23;
            }
            case 2: {
                goto label_20;
            }
            case 3: {
                goto label_11;
            }
            case 4: {
                return 0;
            }
            case 5: {
                goto label_7;
            }
        }

        throw new zzkt("Protocol message tag had invalid wire type.");
    label_20:
        this.zzam(this.zzcm());
        return 1;
    label_23:
        this.zzcp();
        return 1;
    label_7:
        this.zzco();
        return 1;
    label_25:
        this.zzcm();
        return 1;
        do {
        label_11:
            int v0 = this.zzcj();
            if(v0 == 0) {
                break;
            }
        }
        while(this.zzai(v0));

        this.zzah(arg3 >>> 3 << 3 | 4);
        return 1;
    }

    public final int zzak(int arg2) {
        if(arg2 >= 0) {
            arg2 += this.zzaab;
            int v0 = this.zzol;
            if(arg2 <= v0) {
                this.zzol = arg2;
                this.zzcq();
                return v0;
            }

            throw zzkt.zzhg();
        }

        throw zzkt.zzhh();
    }

    public final void zzal(int arg1) {
        this.zzol = arg1;
        this.zzcq();
    }

    private final void zzam(int arg3) {
        if(arg3 >= 0) {
            if(this.zzaab + arg3 <= this.zzol) {
                if(arg3 <= this.zzaaa - this.zzaab) {
                    this.zzaab += arg3;
                    return;
                }

                throw zzkt.zzhg();
            }

            this.zzam(this.zzol - this.zzaab);
            throw zzkt.zzhg();
        }

        throw zzkt.zzhh();
    }

    public final zzgz zzb(zzir arg5) {
        if(this.zzaac == null) {
            this.zzaac = zzga.zzf(this.buffer, this.zzzy, this.zzzz);
        }

        int v0 = this.zzaac.zzcl();
        int v1 = this.zzaab - this.zzzy;
        if(v0 <= v1) {
            this.zzaac.zzam(v1 - v0);
            this.zzaac.zzaj(this.zzoc - this.zzob);
            zzih v5 = this.zzaac.zzb(arg5, zzgl.zzdb());
            this.zzai(this.zzok);
            return ((zzgz)v5);
        }

        throw new IOException(String.format("CodedInputStream read ahead of CodedInputByteBufferNano: %s > %s", Integer.valueOf(v0), Integer.valueOf(v1)));
    }

    public final void zzb(zzku arg4) {
        int v0 = this.zzcm();
        if(this.zzob < this.zzoc) {
            v0 = this.zzak(v0);
            ++this.zzob;
            arg4.zzb(this);
            this.zzah(0);
            --this.zzob;
            this.zzal(v0);
            return;
        }

        throw zzkt.zzhj();
    }

    public final void zzb(zzku arg3, int arg4) {
        if(this.zzob < this.zzoc) {
            ++this.zzob;
            arg3.zzb(this);
            this.zzah(arg4 << 3 | 4);
            --this.zzob;
            return;
        }

        throw zzkt.zzhj();
    }

    public final void zzbr(int arg2) {
        this.zzu(arg2, this.zzok);
    }

    public final int zzcj() {
        if(this.zzaab == this.zzaaa) {
            this.zzok = 0;
            return 0;
        }

        this.zzok = this.zzcm();
        if(this.zzok != 0) {
            return this.zzok;
        }

        throw new zzkt("Protocol message contained an invalid tag (zero).");
    }

    public final int zzcm() {
        int v0 = this.zzcr();
        if(v0 >= 0) {
            return v0;
        }

        v0 &= 127;
        int v1 = this.zzcr();
        if(v1 >= 0) {
            v1 <<= 7;
            goto label_7;
        }
        else {
            v0 |= (v1 & 127) << 7;
            v1 = this.zzcr();
            if(v1 >= 0) {
                v1 <<= 14;
                goto label_7;
            }
            else {
                v0 |= (v1 & 127) << 14;
                v1 = this.zzcr();
                if(v1 >= 0) {
                    v1 <<= 21;
                label_7:
                    v0 |= v1;
                }
                else {
                    v0 |= (v1 & 127) << 21;
                    v1 = this.zzcr();
                    v0 |= v1 << 28;
                    if(v1 < 0) {
                        v1 = 0;
                        while(true) {
                            if(v1 >= 5) {
                                break;
                            }
                            else if(this.zzcr() >= 0) {
                                return v0;
                            }
                            else {
                                ++v1;
                                continue;
                            }

                            return v0;
                        }

                        throw zzkt.zzhi();
                    }
                }
            }
        }

        return v0;
    }

    public final long zzcn() {
        int v0 = 0;
        long v1 = 0;
        while(v0 < 64) {
            int v3 = this.zzcr();
            v1 |= (((long)(v3 & 127))) << v0;
            if((v3 & 128) == 0) {
                return v1;
            }

            v0 += 7;
        }

        throw zzkt.zzhi();
    }

    public final int zzco() {
        return this.zzcr() & 255 | (this.zzcr() & 255) << 8 | (this.zzcr() & 255) << 16 | (this.zzcr() & 255) << 24;
    }

    public final long zzcp() {
        return ((((long)this.zzcr())) & 255) << 8 | (((long)this.zzcr())) & 255 | ((((long)this.zzcr())) & 255) << 16 | ((((long)this.zzcr())) & 255) << 24 | ((((long)this.zzcr())) & 255) << 32 | ((((long)this.zzcr())) & 255) << 40 | ((((long)this.zzcr())) & 255) << 48 | ((((long)this.zzcr())) & 255) << 56;
    }

    private final void zzcq() {
        this.zzaaa += this.zzoi;
        int v0 = this.zzaaa;
        if(v0 > this.zzol) {
            this.zzoi = v0 - this.zzol;
            this.zzaaa -= this.zzoi;
            return;
        }

        this.zzoi = 0;
    }

    private final byte zzcr() {
        if(this.zzaab != this.zzaaa) {
            byte[] v0 = this.buffer;
            int v1 = this.zzaab;
            this.zzaab = v1 + 1;
            return v0[v1];
        }

        throw zzkt.zzhg();
    }

    public static zzkl zzh(byte[] arg2) {
        return zzkl.zzk(arg2, 0, arg2.length);
    }

    public final int zzhb() {
        if(this.zzol == 2147483647) {
            return -1;
        }

        return this.zzol - this.zzaab;
    }

    public static zzkl zzk(byte[] arg1, int arg2, int arg3) {
        return new zzkl(arg1, 0, arg3);
    }

    public final byte[] zzt(int arg4, int arg5) {
        if(arg5 == 0) {
            return zzkx.zzabb;
        }

        byte[] v0 = new byte[arg5];
        System.arraycopy(this.buffer, this.zzzy + arg4, v0, 0, arg5);
        return v0;
    }

    final void zzu(int arg4, int arg5) {
        if(arg4 <= this.zzaab - this.zzzy) {
            if(arg4 >= 0) {
                this.zzaab = this.zzzy + arg4;
                this.zzok = arg5;
                return;
            }

            StringBuilder v1 = new StringBuilder(24);
            v1.append("Bad position ");
            v1.append(arg4);
            throw new IllegalArgumentException(v1.toString());
        }

        int v0 = this.zzaab - this.zzzy;
        StringBuilder v2 = new StringBuilder(50);
        v2.append("Position ");
        v2.append(arg4);
        v2.append(" is beyond current ");
        v2.append(v0);
        throw new IllegalArgumentException(v2.toString());
    }
}

