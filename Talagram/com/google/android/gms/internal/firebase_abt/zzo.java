package com.google.android.gms.internal.firebase_abt;

public final class zzo extends zzd {
    public String zzaq;
    public String zzar;
    public long zzas;
    public String zzat;
    public long zzau;
    public long zzav;
    private String zzaw;
    private String zzax;
    private String zzay;
    private String zzaz;
    private String zzba;
    public zzn[] zzbb;
    public int zzc;

    public zzo() {
        super();
        this.zzaq = "";
        this.zzar = "";
        this.zzas = 0;
        this.zzat = "";
        this.zzau = 0;
        this.zzav = 0;
        this.zzaw = "";
        this.zzax = "";
        this.zzay = "";
        this.zzaz = "";
        this.zzba = "";
        this.zzc = 0;
        this.zzbb = zzn.zzo();
        this.zzs = null;
        this.zzab = -1;
    }

    public final zzj zza(zza arg5) {
        zzn[] v0_1;
        int v1;
        while(true) {
        label_0:
            int v0 = arg5.zzd();
            switch(v0) {
                case 0: {
                    return this;
                }
                case 10: {
                    goto label_70;
                }
                case 18: {
                    goto label_67;
                }
                case 24: {
                    goto label_64;
                }
                case 34: {
                    goto label_61;
                }
                case 40: {
                    goto label_58;
                }
                case 48: {
                    goto label_55;
                }
                case 58: {
                    goto label_52;
                }
                case 66: {
                    goto label_49;
                }
                case 74: {
                    goto label_46;
                }
                case 82: {
                    goto label_43;
                }
                case 90: {
                    goto label_40;
                }
                case 96: {
                    goto label_37;
                }
                case 106: {
                    goto label_5;
                }
            }

            if(super.zza(arg5, v0)) {
                continue;
            }

            return this;
        label_67:
            this.zzar = arg5.readString();
            continue;
        label_37:
            this.zzc = arg5.zzf();
            continue;
        label_5:
            v0 = zzm.zzb(arg5, 106);
            v1 = this.zzbb == null ? 0 : this.zzbb.length;
            v0_1 = new zzn[v0 + v1];
            if(v1 != 0) {
                System.arraycopy(this.zzbb, 0, v0_1, 0, v1);
                break;
            label_70:
                this.zzaq = arg5.readString();
                continue;
            label_40:
                this.zzba = arg5.readString();
                continue;
                return this;
            label_43:
                this.zzaz = arg5.readString();
                continue;
            label_46:
                this.zzay = arg5.readString();
                continue;
            label_49:
                this.zzax = arg5.readString();
                continue;
            label_52:
                this.zzaw = arg5.readString();
                continue;
            label_55:
                this.zzav = arg5.zze();
                continue;
            label_58:
                this.zzau = arg5.zze();
                continue;
            label_61:
                this.zzat = arg5.readString();
                continue;
            label_64:
                this.zzas = arg5.zze();
                continue;
            }

            break;
        }

        while(v1 < v0_1.length - 1) {
            v0_1[v1] = new zzn();
            arg5.zza(v0_1[v1]);
            arg5.zzd();
            ++v1;
        }

        v0_1[v1] = new zzn();
        arg5.zza(v0_1[v1]);
        this.zzbb = v0_1;
        goto label_0;
    }
}

