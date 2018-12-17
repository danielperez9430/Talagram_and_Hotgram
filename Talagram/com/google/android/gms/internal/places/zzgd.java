package com.google.android.gms.internal.places;

import java.util.List;
import java.util.Map;

final class zzgd implements zzix {
    private int tag;
    private int zznq;
    private final zzga zzom;
    private int zzon;

    private zzgd(zzga arg2) {
        super();
        this.zzon = 0;
        this.zzom = zzhb.zzb(arg2, "input");
        this.zzom.zzoe = this;
    }

    public final int getTag() {
        return this.tag;
    }

    public final double readDouble() {
        this.zzy(1);
        return this.zzom.readDouble();
    }

    public final float readFloat() {
        this.zzy(5);
        return this.zzom.readFloat();
    }

    public final String readString() {
        this.zzy(2);
        return this.zzom.readString();
    }

    public final void readStringList(List arg2) {
        this.zzb(arg2, false);
    }

    private static void zzaa(int arg0) {
        if((arg0 & 3) == 0) {
            return;
        }

        throw zzhh.zzef();
    }

    private final void zzab(int arg2) {
        if(this.zzom.zzcl() == arg2) {
            return;
        }

        throw zzhh.zzdz();
    }

    public static zzgd zzb(zzga arg1) {
        if(arg1.zzoe != null) {
            return arg1.zzoe;
        }

        return new zzgd(arg1);
    }

    private final Object zzb(zzke arg2, Class arg3, zzgl arg4) {
        switch(zzge.zznn[arg2.ordinal()]) {
            case 1: {
                goto label_53;
            }
            case 2: {
                goto label_51;
            }
            case 3: {
                goto label_48;
            }
            case 4: {
                goto label_45;
            }
            case 5: {
                goto label_42;
            }
            case 6: {
                goto label_39;
            }
            case 7: {
                goto label_36;
            }
            case 8: {
                goto label_33;
            }
            case 9: {
                goto label_30;
            }
            case 10: {
                goto label_28;
            }
            case 11: {
                goto label_25;
            }
            case 12: {
                goto label_22;
            }
            case 13: {
                goto label_19;
            }
            case 14: {
                goto label_16;
            }
            case 15: {
                goto label_14;
            }
            case 16: {
                goto label_11;
            }
            case 17: {
                goto label_8;
            }
        }

        throw new RuntimeException("unsupported field type.");
    label_33:
        return Integer.valueOf(this.zzbk());
    label_36:
        return Float.valueOf(this.readFloat());
    label_39:
        return Long.valueOf(this.zzbl());
    label_8:
        return Long.valueOf(this.zzbi());
    label_42:
        return Integer.valueOf(this.zzbm());
    label_11:
        return Integer.valueOf(this.zzbq());
    label_45:
        return Integer.valueOf(this.zzbr());
    label_14:
        return this.zzbo();
    label_48:
        return Double.valueOf(this.readDouble());
    label_16:
        return Long.valueOf(this.zzbv());
    label_51:
        return this.zzbp();
    label_19:
        return Integer.valueOf(this.zzbu());
    label_53:
        return Boolean.valueOf(this.zzbn());
    label_22:
        return Long.valueOf(this.zzbt());
    label_25:
        return Integer.valueOf(this.zzbs());
    label_28:
        return this.zzb(arg3, arg4);
    label_30:
        return Long.valueOf(this.zzbj());
    }

    public final Object zzb(Class arg2, zzgl arg3) {
        this.zzy(2);
        return this.zzc(zzis.zzfc().zzg(arg2), arg3);
    }

    private final void zzb(List arg3, boolean arg4) {
        int v0_2;
        int v3;
        if((this.tag & 7) == 2) {
            if(((arg3 instanceof zzhq)) && !arg4) {
                List v0 = arg3;
                do {
                    ((zzhq)v0).zzd(this.zzbp());
                    if(!this.zzom.zzbf()) {
                        v3 = this.zzom.zzcj();
                        if(v3 == this.tag) {
                            continue;
                        }

                        break;
                    }
                    else {
                        return;
                    }
                }
                while(true);

                this.zzon = v3;
                return;
            }

            do {
                String v0_1 = arg4 ? this.zzbo() : this.readString();
                arg3.add(v0_1);
                if(this.zzom.zzbf()) {
                    return;
                }

                v0_2 = this.zzom.zzcj();
            }
            while(v0_2 == this.tag);

            this.zzon = v0_2;
            return;
        }

        throw zzhh.zzed();
    }

    public final Object zzb(zziy arg2, zzgl arg3) {
        this.zzy(2);
        return this.zzc(arg2, arg3);
    }

    public final void zzb(List arg3, zziy arg4, zzgl arg5) {
        int v1;
        if((this.tag & 7) == 2) {
            int v0 = this.tag;
            do {
                arg3.add(this.zzc(arg4, arg5));
                if(!this.zzom.zzbf()) {
                    if(this.zzon != 0) {
                    }
                    else {
                        v1 = this.zzom.zzcj();
                        if(v1 == v0) {
                            continue;
                        }

                        break;
                    }
                }

                return;
            }
            while(true);

            this.zzon = v1;
            return;
        }

        throw zzhh.zzed();
    }

    public final void zzb(Map arg6, zzia arg7, zzgl arg8) {
        this.zzy(2);
        int v0 = this.zzom.zzak(this.zzom.zzbq());
        Object v1 = arg7.zzuu;
        Object v2 = arg7.zzss;
        try {
            while(true) {
            label_8:
                int v3 = this.zzbg();
                if(v3 != 2147483647 && !this.zzom.zzbf()) {
                    break;
                }

                goto label_41;
            }
        }
        catch(Throwable v6) {
            goto label_47;
        }

        switch(v3) {
            case 1: {
                goto label_23;
            }
            case 2: {
                goto label_17;
            }
        }

        try {
            boolean v3_1 = this.zzbh();
            if(v3_1) {
            }
            else {
                throw new zzhh("Unable to parse map entry.");
            label_17:
                v2 = this.zzb(arg7.zzuv, arg7.zzss.getClass(), arg8);
                goto label_8;
            label_23:
                v1 = this.zzb(arg7.zzut, null, null);
            }

            goto label_8;
        }
        catch(Throwable v6) {
        }
        catch(zzhi ) {
            try {
                if(this.zzbh()) {
                    goto label_8;
                }
                else {
                    throw new zzhh("Unable to parse map entry.");
                }

            label_41:
                arg6.put(v1, v2);
            }
            catch(Throwable v6) {
            label_47:
                this.zzom.zzal(v0);
                throw v6;
            }
        }

        this.zzom.zzal(v0);
    }

    public final int zzbg() {
        if(this.zzon != 0) {
            this.tag = this.zzon;
            this.zzon = 0;
        }
        else {
            this.tag = this.zzom.zzcj();
        }

        if(this.tag != 0) {
            if(this.tag == this.zznq) {
            }
            else {
                return this.tag >>> 3;
            }
        }

        return 2147483647;
    }

    public final boolean zzbh() {
        if(!this.zzom.zzbf()) {
            if(this.tag == this.zznq) {
            }
            else {
                return this.zzom.zzai(this.tag);
            }
        }

        return 0;
    }

    public final long zzbi() {
        this.zzy(0);
        return this.zzom.zzbi();
    }

    public final long zzbj() {
        this.zzy(0);
        return this.zzom.zzbj();
    }

    public final int zzbk() {
        this.zzy(0);
        return this.zzom.zzbk();
    }

    public final long zzbl() {
        this.zzy(1);
        return this.zzom.zzbl();
    }

    public final int zzbm() {
        this.zzy(5);
        return this.zzom.zzbm();
    }

    public final boolean zzbn() {
        this.zzy(0);
        return this.zzom.zzbn();
    }

    public final String zzbo() {
        this.zzy(2);
        return this.zzom.zzbo();
    }

    public final zzfr zzbp() {
        this.zzy(2);
        return this.zzom.zzbp();
    }

    public final int zzbq() {
        this.zzy(0);
        return this.zzom.zzbq();
    }

    public final int zzbr() {
        this.zzy(0);
        return this.zzom.zzbr();
    }

    public final int zzbs() {
        this.zzy(5);
        return this.zzom.zzbs();
    }

    public final long zzbt() {
        this.zzy(1);
        return this.zzom.zzbt();
    }

    public final int zzbu() {
        this.zzy(0);
        return this.zzom.zzbu();
    }

    public final long zzbv() {
        this.zzy(0);
        return this.zzom.zzbv();
    }

    private final Object zzc(zziy arg5, zzgl arg6) {
        int v0 = this.zzom.zzbq();
        if(this.zzom.zzob < this.zzom.zzoc) {
            v0 = this.zzom.zzak(v0);
            Object v1 = arg5.newInstance();
            ++this.zzom.zzob;
            arg5.zzb(v1, ((zzix)this), arg6);
            arg5.zzd(v1);
            this.zzom.zzah(0);
            --this.zzom.zzob;
            this.zzom.zzal(v0);
            return v1;
        }

        throw zzhh.zzee();
    }

    public final Object zzc(Class arg2, zzgl arg3) {
        this.zzy(3);
        return this.zze(zzis.zzfc().zzg(arg2), arg3);
    }

    public final void zzc(List arg3, zziy arg4, zzgl arg5) {
        int v1;
        if((this.tag & 7) == 3) {
            int v0 = this.tag;
            do {
                arg3.add(this.zze(arg4, arg5));
                if(!this.zzom.zzbf()) {
                    if(this.zzon != 0) {
                    }
                    else {
                        v1 = this.zzom.zzcj();
                        if(v1 == v0) {
                            continue;
                        }

                        break;
                    }
                }

                return;
            }
            while(true);

            this.zzon = v1;
            return;
        }

        throw zzhh.zzed();
    }

    public final Object zzd(zziy arg2, zzgl arg3) {
        this.zzy(3);
        return this.zze(arg2, arg3);
    }

    private final Object zze(zziy arg3, zzgl arg4) {
        Object v1;
        int v0 = this.zznq;
        this.zznq = this.tag >>> 3 << 3 | 4;
        try {
            v1 = arg3.newInstance();
            arg3.zzb(v1, ((zzix)this), arg4);
            arg3.zzd(v1);
            if(this.tag != this.zznq) {
                goto label_14;
            }
        }
        catch(Throwable v3) {
            goto label_17;
        }

        this.zznq = v0;
        return v1;
        try {
        label_14:
            throw zzhh.zzef();
        }
        catch(Throwable v3) {
        label_17:
            this.zznq = v0;
            throw v3;
        }
    }

    public final void zze(List arg5) {
        int v0;
        if(!(arg5 instanceof zzgi)) {
            goto label_33;
        }

        switch(this.tag & 7) {
            case 1: {
                goto label_20;
            }
            case 2: {
                goto label_7;
            }
        }

        throw zzhh.zzed();
        do {
        label_20:
            ((zzgi)arg5).zzd(this.zzom.readDouble());
            if(this.zzom.zzbf()) {
                return;
            }

            v0 = this.zzom.zzcj();
        }
        while(v0 == this.tag);

        this.zzon = v0;
        return;
    label_7:
        v0 = this.zzom.zzbq();
        zzgd.zzz(v0);
        int v1 = this.zzom.zzcl() + v0;
        do {
            ((zzgi)arg5).zzd(this.zzom.readDouble());
        }
        while(this.zzom.zzcl() < v1);

        return;
    label_33:
        switch(this.tag & 7) {
            case 1: {
                goto label_52;
            }
            case 2: {
                goto label_38;
            }
        }

        throw zzhh.zzed();
        do {
        label_52:
            arg5.add(Double.valueOf(this.zzom.readDouble()));
            if(this.zzom.zzbf()) {
                return;
            }

            v0 = this.zzom.zzcj();
        }
        while(v0 == this.tag);

        this.zzon = v0;
        return;
    label_38:
        v0 = this.zzom.zzbq();
        zzgd.zzz(v0);
        v1 = this.zzom.zzcl() + v0;
        do {
            arg5.add(Double.valueOf(this.zzom.readDouble()));
        }
        while(this.zzom.zzcl() < v1);
    }

    public final void zzf(List arg5) {
        int v1 = 5;
        int v2 = 2;
        if((arg5 instanceof zzgw)) {
            List v0 = arg5;
            int v5 = this.tag & 7;
            if(v5 != v2) {
                if(v5 == v1) {
                    do {
                        ((zzgw)v0).zzf(this.zzom.readFloat());
                        if(this.zzom.zzbf()) {
                            return;
                        }

                        v5 = this.zzom.zzcj();
                    }
                    while(v5 == this.tag);

                    this.zzon = v5;
                    return;
                }

                throw zzhh.zzed();
            }

            v5 = this.zzom.zzbq();
            zzgd.zzaa(v5);
            int v3 = this.zzom.zzcl() + v5;
            do {
                ((zzgw)v0).zzf(this.zzom.readFloat());
            }
            while(this.zzom.zzcl() < v3);

            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != v2) {
            if(v0_1 == v1) {
                do {
                    arg5.add(Float.valueOf(this.zzom.readFloat()));
                    if(this.zzom.zzbf()) {
                        return;
                    }

                    v0_1 = this.zzom.zzcj();
                }
                while(v0_1 == this.tag);

                this.zzon = v0_1;
                return;
            }

            throw zzhh.zzed();
        }

        v0_1 = this.zzom.zzbq();
        zzgd.zzaa(v0_1);
        v1 = this.zzom.zzcl() + v0_1;
        do {
            arg5.add(Float.valueOf(this.zzom.readFloat()));
        }
        while(this.zzom.zzcl() < v1);
    }

    public final void zzg(List arg5) {
        int v1 = 2;
        if((arg5 instanceof zzhv)) {
            List v0 = arg5;
            int v5 = this.tag & 7;
            if(v5 != 0) {
                if(v5 == v1) {
                    v1 = this.zzom.zzcl() + this.zzom.zzbq();
                    do {
                        ((zzhv)v0).zzp(this.zzom.zzbi());
                    }
                    while(this.zzom.zzcl() < v1);

                    this.zzab(v1);
                    return;
                }
                else {
                    throw zzhh.zzed();
                }
            }

            do {
                ((zzhv)v0).zzp(this.zzom.zzbi());
                if(this.zzom.zzbf()) {
                    return;
                }

                v5 = this.zzom.zzcj();
            }
            while(v5 == this.tag);

            this.zzon = v5;
            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != 0) {
            if(v0_1 == v1) {
                v1 = this.zzom.zzcl() + this.zzom.zzbq();
                do {
                    arg5.add(Long.valueOf(this.zzom.zzbi()));
                }
                while(this.zzom.zzcl() < v1);

                this.zzab(v1);
                return;
            }
            else {
                throw zzhh.zzed();
            }
        }

        do {
            arg5.add(Long.valueOf(this.zzom.zzbi()));
            if(this.zzom.zzbf()) {
                return;
            }

            v0_1 = this.zzom.zzcj();
        }
        while(v0_1 == this.tag);

        this.zzon = v0_1;
    }

    public final void zzh(List arg5) {
        int v1 = 2;
        if((arg5 instanceof zzhv)) {
            List v0 = arg5;
            int v5 = this.tag & 7;
            if(v5 != 0) {
                if(v5 == v1) {
                    v1 = this.zzom.zzcl() + this.zzom.zzbq();
                    do {
                        ((zzhv)v0).zzp(this.zzom.zzbj());
                    }
                    while(this.zzom.zzcl() < v1);

                    this.zzab(v1);
                    return;
                }
                else {
                    throw zzhh.zzed();
                }
            }

            do {
                ((zzhv)v0).zzp(this.zzom.zzbj());
                if(this.zzom.zzbf()) {
                    return;
                }

                v5 = this.zzom.zzcj();
            }
            while(v5 == this.tag);

            this.zzon = v5;
            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != 0) {
            if(v0_1 == v1) {
                v1 = this.zzom.zzcl() + this.zzom.zzbq();
                do {
                    arg5.add(Long.valueOf(this.zzom.zzbj()));
                }
                while(this.zzom.zzcl() < v1);

                this.zzab(v1);
                return;
            }
            else {
                throw zzhh.zzed();
            }
        }

        do {
            arg5.add(Long.valueOf(this.zzom.zzbj()));
            if(this.zzom.zzbf()) {
                return;
            }

            v0_1 = this.zzom.zzcj();
        }
        while(v0_1 == this.tag);

        this.zzon = v0_1;
    }

    public final void zzi(List arg3) {
        int v1 = 2;
        if((arg3 instanceof zzha)) {
            List v0 = arg3;
            int v3 = this.tag & 7;
            if(v3 != 0) {
                if(v3 == v1) {
                    v1 = this.zzom.zzcl() + this.zzom.zzbq();
                    do {
                        ((zzha)v0).zzbe(this.zzom.zzbk());
                    }
                    while(this.zzom.zzcl() < v1);

                    this.zzab(v1);
                    return;
                }
                else {
                    throw zzhh.zzed();
                }
            }

            do {
                ((zzha)v0).zzbe(this.zzom.zzbk());
                if(this.zzom.zzbf()) {
                    return;
                }

                v3 = this.zzom.zzcj();
            }
            while(v3 == this.tag);

            this.zzon = v3;
            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != 0) {
            if(v0_1 == v1) {
                v1 = this.zzom.zzcl() + this.zzom.zzbq();
                do {
                    arg3.add(Integer.valueOf(this.zzom.zzbk()));
                }
                while(this.zzom.zzcl() < v1);

                this.zzab(v1);
                return;
            }
            else {
                throw zzhh.zzed();
            }
        }

        do {
            arg3.add(Integer.valueOf(this.zzom.zzbk()));
            if(this.zzom.zzbf()) {
                return;
            }

            v0_1 = this.zzom.zzcj();
        }
        while(v0_1 == this.tag);

        this.zzon = v0_1;
    }

    public final void zzj(List arg5) {
        int v0;
        if(!(arg5 instanceof zzhv)) {
            goto label_33;
        }

        switch(this.tag & 7) {
            case 1: {
                goto label_20;
            }
            case 2: {
                goto label_7;
            }
        }

        throw zzhh.zzed();
        do {
        label_20:
            ((zzhv)arg5).zzp(this.zzom.zzbl());
            if(this.zzom.zzbf()) {
                return;
            }

            v0 = this.zzom.zzcj();
        }
        while(v0 == this.tag);

        this.zzon = v0;
        return;
    label_7:
        v0 = this.zzom.zzbq();
        zzgd.zzz(v0);
        int v1 = this.zzom.zzcl() + v0;
        do {
            ((zzhv)arg5).zzp(this.zzom.zzbl());
        }
        while(this.zzom.zzcl() < v1);

        return;
    label_33:
        switch(this.tag & 7) {
            case 1: {
                goto label_52;
            }
            case 2: {
                goto label_38;
            }
        }

        throw zzhh.zzed();
        do {
        label_52:
            arg5.add(Long.valueOf(this.zzom.zzbl()));
            if(this.zzom.zzbf()) {
                return;
            }

            v0 = this.zzom.zzcj();
        }
        while(v0 == this.tag);

        this.zzon = v0;
        return;
    label_38:
        v0 = this.zzom.zzbq();
        zzgd.zzz(v0);
        v1 = this.zzom.zzcl() + v0;
        do {
            arg5.add(Long.valueOf(this.zzom.zzbl()));
        }
        while(this.zzom.zzcl() < v1);
    }

    public final void zzk(List arg5) {
        int v1 = 5;
        int v2 = 2;
        if((arg5 instanceof zzha)) {
            List v0 = arg5;
            int v5 = this.tag & 7;
            if(v5 != v2) {
                if(v5 == v1) {
                    do {
                        ((zzha)v0).zzbe(this.zzom.zzbm());
                        if(this.zzom.zzbf()) {
                            return;
                        }

                        v5 = this.zzom.zzcj();
                    }
                    while(v5 == this.tag);

                    this.zzon = v5;
                    return;
                }

                throw zzhh.zzed();
            }

            v5 = this.zzom.zzbq();
            zzgd.zzaa(v5);
            int v3 = this.zzom.zzcl() + v5;
            do {
                ((zzha)v0).zzbe(this.zzom.zzbm());
            }
            while(this.zzom.zzcl() < v3);

            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != v2) {
            if(v0_1 == v1) {
                do {
                    arg5.add(Integer.valueOf(this.zzom.zzbm()));
                    if(this.zzom.zzbf()) {
                        return;
                    }

                    v0_1 = this.zzom.zzcj();
                }
                while(v0_1 == this.tag);

                this.zzon = v0_1;
                return;
            }

            throw zzhh.zzed();
        }

        v0_1 = this.zzom.zzbq();
        zzgd.zzaa(v0_1);
        v1 = this.zzom.zzcl() + v0_1;
        do {
            arg5.add(Integer.valueOf(this.zzom.zzbm()));
        }
        while(this.zzom.zzcl() < v1);
    }

    public final void zzl(List arg3) {
        int v1 = 2;
        if((arg3 instanceof zzfp)) {
            List v0 = arg3;
            int v3 = this.tag & 7;
            if(v3 != 0) {
                if(v3 == v1) {
                    v1 = this.zzom.zzcl() + this.zzom.zzbq();
                    do {
                        ((zzfp)v0).addBoolean(this.zzom.zzbn());
                    }
                    while(this.zzom.zzcl() < v1);

                    this.zzab(v1);
                    return;
                }
                else {
                    throw zzhh.zzed();
                }
            }

            do {
                ((zzfp)v0).addBoolean(this.zzom.zzbn());
                if(this.zzom.zzbf()) {
                    return;
                }

                v3 = this.zzom.zzcj();
            }
            while(v3 == this.tag);

            this.zzon = v3;
            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != 0) {
            if(v0_1 == v1) {
                v1 = this.zzom.zzcl() + this.zzom.zzbq();
                do {
                    arg3.add(Boolean.valueOf(this.zzom.zzbn()));
                }
                while(this.zzom.zzcl() < v1);

                this.zzab(v1);
                return;
            }
            else {
                throw zzhh.zzed();
            }
        }

        do {
            arg3.add(Boolean.valueOf(this.zzom.zzbn()));
            if(this.zzom.zzbf()) {
                return;
            }

            v0_1 = this.zzom.zzcj();
        }
        while(v0_1 == this.tag);

        this.zzon = v0_1;
    }

    public final void zzm(List arg2) {
        this.zzb(arg2, true);
    }

    public final void zzn(List arg3) {
        int v0;
        if((this.tag & 7) == 2) {
            do {
                arg3.add(this.zzbp());
                if(this.zzom.zzbf()) {
                    return;
                }

                v0 = this.zzom.zzcj();
            }
            while(v0 == this.tag);

            this.zzon = v0;
            return;
        }

        throw zzhh.zzed();
    }

    public final void zzo(List arg3) {
        int v1 = 2;
        if((arg3 instanceof zzha)) {
            List v0 = arg3;
            int v3 = this.tag & 7;
            if(v3 != 0) {
                if(v3 == v1) {
                    v1 = this.zzom.zzcl() + this.zzom.zzbq();
                    do {
                        ((zzha)v0).zzbe(this.zzom.zzbq());
                    }
                    while(this.zzom.zzcl() < v1);

                    this.zzab(v1);
                    return;
                }
                else {
                    throw zzhh.zzed();
                }
            }

            do {
                ((zzha)v0).zzbe(this.zzom.zzbq());
                if(this.zzom.zzbf()) {
                    return;
                }

                v3 = this.zzom.zzcj();
            }
            while(v3 == this.tag);

            this.zzon = v3;
            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != 0) {
            if(v0_1 == v1) {
                v1 = this.zzom.zzcl() + this.zzom.zzbq();
                do {
                    arg3.add(Integer.valueOf(this.zzom.zzbq()));
                }
                while(this.zzom.zzcl() < v1);

                this.zzab(v1);
                return;
            }
            else {
                throw zzhh.zzed();
            }
        }

        do {
            arg3.add(Integer.valueOf(this.zzom.zzbq()));
            if(this.zzom.zzbf()) {
                return;
            }

            v0_1 = this.zzom.zzcj();
        }
        while(v0_1 == this.tag);

        this.zzon = v0_1;
    }

    public final void zzp(List arg3) {
        int v1 = 2;
        if((arg3 instanceof zzha)) {
            List v0 = arg3;
            int v3 = this.tag & 7;
            if(v3 != 0) {
                if(v3 == v1) {
                    v1 = this.zzom.zzcl() + this.zzom.zzbq();
                    do {
                        ((zzha)v0).zzbe(this.zzom.zzbr());
                    }
                    while(this.zzom.zzcl() < v1);

                    this.zzab(v1);
                    return;
                }
                else {
                    throw zzhh.zzed();
                }
            }

            do {
                ((zzha)v0).zzbe(this.zzom.zzbr());
                if(this.zzom.zzbf()) {
                    return;
                }

                v3 = this.zzom.zzcj();
            }
            while(v3 == this.tag);

            this.zzon = v3;
            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != 0) {
            if(v0_1 == v1) {
                v1 = this.zzom.zzcl() + this.zzom.zzbq();
                do {
                    arg3.add(Integer.valueOf(this.zzom.zzbr()));
                }
                while(this.zzom.zzcl() < v1);

                this.zzab(v1);
                return;
            }
            else {
                throw zzhh.zzed();
            }
        }

        do {
            arg3.add(Integer.valueOf(this.zzom.zzbr()));
            if(this.zzom.zzbf()) {
                return;
            }

            v0_1 = this.zzom.zzcj();
        }
        while(v0_1 == this.tag);

        this.zzon = v0_1;
    }

    public final void zzq(List arg5) {
        int v1 = 5;
        int v2 = 2;
        if((arg5 instanceof zzha)) {
            List v0 = arg5;
            int v5 = this.tag & 7;
            if(v5 != v2) {
                if(v5 == v1) {
                    do {
                        ((zzha)v0).zzbe(this.zzom.zzbs());
                        if(this.zzom.zzbf()) {
                            return;
                        }

                        v5 = this.zzom.zzcj();
                    }
                    while(v5 == this.tag);

                    this.zzon = v5;
                    return;
                }

                throw zzhh.zzed();
            }

            v5 = this.zzom.zzbq();
            zzgd.zzaa(v5);
            int v3 = this.zzom.zzcl() + v5;
            do {
                ((zzha)v0).zzbe(this.zzom.zzbs());
            }
            while(this.zzom.zzcl() < v3);

            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != v2) {
            if(v0_1 == v1) {
                do {
                    arg5.add(Integer.valueOf(this.zzom.zzbs()));
                    if(this.zzom.zzbf()) {
                        return;
                    }

                    v0_1 = this.zzom.zzcj();
                }
                while(v0_1 == this.tag);

                this.zzon = v0_1;
                return;
            }

            throw zzhh.zzed();
        }

        v0_1 = this.zzom.zzbq();
        zzgd.zzaa(v0_1);
        v1 = this.zzom.zzcl() + v0_1;
        do {
            arg5.add(Integer.valueOf(this.zzom.zzbs()));
        }
        while(this.zzom.zzcl() < v1);
    }

    public final void zzr(List arg5) {
        int v0;
        if(!(arg5 instanceof zzhv)) {
            goto label_33;
        }

        switch(this.tag & 7) {
            case 1: {
                goto label_20;
            }
            case 2: {
                goto label_7;
            }
        }

        throw zzhh.zzed();
        do {
        label_20:
            ((zzhv)arg5).zzp(this.zzom.zzbt());
            if(this.zzom.zzbf()) {
                return;
            }

            v0 = this.zzom.zzcj();
        }
        while(v0 == this.tag);

        this.zzon = v0;
        return;
    label_7:
        v0 = this.zzom.zzbq();
        zzgd.zzz(v0);
        int v1 = this.zzom.zzcl() + v0;
        do {
            ((zzhv)arg5).zzp(this.zzom.zzbt());
        }
        while(this.zzom.zzcl() < v1);

        return;
    label_33:
        switch(this.tag & 7) {
            case 1: {
                goto label_52;
            }
            case 2: {
                goto label_38;
            }
        }

        throw zzhh.zzed();
        do {
        label_52:
            arg5.add(Long.valueOf(this.zzom.zzbt()));
            if(this.zzom.zzbf()) {
                return;
            }

            v0 = this.zzom.zzcj();
        }
        while(v0 == this.tag);

        this.zzon = v0;
        return;
    label_38:
        v0 = this.zzom.zzbq();
        zzgd.zzz(v0);
        v1 = this.zzom.zzcl() + v0;
        do {
            arg5.add(Long.valueOf(this.zzom.zzbt()));
        }
        while(this.zzom.zzcl() < v1);
    }

    public final void zzs(List arg3) {
        int v1 = 2;
        if((arg3 instanceof zzha)) {
            List v0 = arg3;
            int v3 = this.tag & 7;
            if(v3 != 0) {
                if(v3 == v1) {
                    v1 = this.zzom.zzcl() + this.zzom.zzbq();
                    do {
                        ((zzha)v0).zzbe(this.zzom.zzbu());
                    }
                    while(this.zzom.zzcl() < v1);

                    this.zzab(v1);
                    return;
                }
                else {
                    throw zzhh.zzed();
                }
            }

            do {
                ((zzha)v0).zzbe(this.zzom.zzbu());
                if(this.zzom.zzbf()) {
                    return;
                }

                v3 = this.zzom.zzcj();
            }
            while(v3 == this.tag);

            this.zzon = v3;
            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != 0) {
            if(v0_1 == v1) {
                v1 = this.zzom.zzcl() + this.zzom.zzbq();
                do {
                    arg3.add(Integer.valueOf(this.zzom.zzbu()));
                }
                while(this.zzom.zzcl() < v1);

                this.zzab(v1);
                return;
            }
            else {
                throw zzhh.zzed();
            }
        }

        do {
            arg3.add(Integer.valueOf(this.zzom.zzbu()));
            if(this.zzom.zzbf()) {
                return;
            }

            v0_1 = this.zzom.zzcj();
        }
        while(v0_1 == this.tag);

        this.zzon = v0_1;
    }

    public final void zzt(List arg5) {
        int v1 = 2;
        if((arg5 instanceof zzhv)) {
            List v0 = arg5;
            int v5 = this.tag & 7;
            if(v5 != 0) {
                if(v5 == v1) {
                    v1 = this.zzom.zzcl() + this.zzom.zzbq();
                    do {
                        ((zzhv)v0).zzp(this.zzom.zzbv());
                    }
                    while(this.zzom.zzcl() < v1);

                    this.zzab(v1);
                    return;
                }
                else {
                    throw zzhh.zzed();
                }
            }

            do {
                ((zzhv)v0).zzp(this.zzom.zzbv());
                if(this.zzom.zzbf()) {
                    return;
                }

                v5 = this.zzom.zzcj();
            }
            while(v5 == this.tag);

            this.zzon = v5;
            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != 0) {
            if(v0_1 == v1) {
                v1 = this.zzom.zzcl() + this.zzom.zzbq();
                do {
                    arg5.add(Long.valueOf(this.zzom.zzbv()));
                }
                while(this.zzom.zzcl() < v1);

                this.zzab(v1);
                return;
            }
            else {
                throw zzhh.zzed();
            }
        }

        do {
            arg5.add(Long.valueOf(this.zzom.zzbv()));
            if(this.zzom.zzbf()) {
                return;
            }

            v0_1 = this.zzom.zzcj();
        }
        while(v0_1 == this.tag);

        this.zzon = v0_1;
    }

    private final void zzy(int arg2) {
        if((this.tag & 7) == arg2) {
            return;
        }

        throw zzhh.zzed();
    }

    private static void zzz(int arg0) {
        if((arg0 & 7) == 0) {
            return;
        }

        throw zzhh.zzef();
    }
}

