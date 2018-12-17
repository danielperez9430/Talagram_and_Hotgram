package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import com.google.android.gms.common.util.Clock;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public final class zzx extends zzcp {
    private long zzahz;
    private String zzaia;
    private Boolean zzaib;

    zzx(zzbt arg1) {
        super(arg1);
    }

    public final Context getContext() {
        return super.getContext();
    }

    public final void zzaf() {
        super.zzaf();
    }

    public final Clock zzbx() {
        return super.zzbx();
    }

    public final void zzga() {
        super.zzga();
    }

    public final void zzgb() {
        super.zzgb();
    }

    public final void zzgc() {
        super.zzgc();
    }

    public final zzx zzgk() {
        return super.zzgk();
    }

    public final zzan zzgl() {
        return super.zzgl();
    }

    public final zzfk zzgm() {
        return super.zzgm();
    }

    public final zzbo zzgn() {
        return super.zzgn();
    }

    public final zzap zzgo() {
        return super.zzgo();
    }

    public final zzba zzgp() {
        return super.zzgp();
    }

    public final zzn zzgq() {
        return super.zzgq();
    }

    public final zzk zzgr() {
        return super.zzgr();
    }

    protected final boolean zzgt() {
        Calendar v0 = Calendar.getInstance();
        this.zzahz = TimeUnit.MINUTES.convert(((long)(v0.get(15) + v0.get(16))), TimeUnit.MILLISECONDS);
        Locale v0_1 = Locale.getDefault();
        String v1 = v0_1.getLanguage().toLowerCase(Locale.ENGLISH);
        String v0_2 = v0_1.getCountry().toLowerCase(Locale.ENGLISH);
        StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 1 + String.valueOf(v0_2).length());
        v3.append(v1);
        v3.append("-");
        v3.append(v0_2);
        this.zzaia = v3.toString();
        return 0;
    }

    public final long zzis() {
        ((zzcp)this).zzcl();
        return this.zzahz;
    }

    public final String zzit() {
        ((zzcp)this).zzcl();
        return this.zzaia;
    }

    public final boolean zzl(Context arg3) {
        if(this.zzaib == null) {
            ((zzco)this).zzgr();
            this.zzaib = Boolean.valueOf(false);
            try {
                PackageManager v3 = arg3.getPackageManager();
                if(v3 != null) {
                    v3.getPackageInfo("com.google.android.gms", 128);
                    this.zzaib = Boolean.valueOf(true);
                }

                goto label_14;
            }
            catch(PackageManager$NameNotFoundException ) {
            label_14:
                return this.zzaib.booleanValue();
            }
        }

        goto label_14;
    }
}

