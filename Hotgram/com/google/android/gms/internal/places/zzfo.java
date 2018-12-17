package com.google.android.gms.internal.places;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

final class zzfo extends zzfm {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private int tag;
    private final boolean zzno;
    private final int zznp;
    private int zznq;

    public zzfo(ByteBuffer arg2, boolean arg3) {
        super(null);
        this.zzno = true;
        this.buffer = arg2.array();
        int v3 = arg2.arrayOffset() + arg2.position();
        this.pos = v3;
        this.zznp = v3;
        this.limit = arg2.arrayOffset() + arg2.limit();
    }

    public final int getTag() {
        return this.tag;
    }

    private final byte readByte() {
        if(this.pos != this.limit) {
            byte[] v0 = this.buffer;
            int v1 = this.pos;
            this.pos = v1 + 1;
            return v0[v1];
        }

        throw zzhh.zzdz();
    }

    public final double readDouble() {
        this.zzy(1);
        return Double.longBitsToDouble(this.zzca());
    }

    public final float readFloat() {
        this.zzy(5);
        return Float.intBitsToFloat(this.zzbz());
    }

    public final String readString() {
        return this.zzc(false);
    }

    public final void readStringList(List arg2) {
        this.zzb(arg2, false);
    }

    private final void zzaa(int arg1) {
        this.zzx(arg1);
        if((arg1 & 3) == 0) {
            return;
        }

        throw zzhh.zzef();
    }

    private final void zzab(int arg2) {
        if(this.pos == arg2) {
            return;
        }

        throw zzhh.zzdz();
    }

    private final Object zzb(zzke arg2, Class arg3, zzgl arg4) {
        switch(zzfn.zznn[arg2.ordinal()]) {
            case 1: {
                goto label_54;
            }
            case 2: {
                goto label_52;
            }
            case 3: {
                goto label_49;
            }
            case 4: {
                goto label_46;
            }
            case 5: {
                goto label_43;
            }
            case 6: {
                goto label_40;
            }
            case 7: {
                goto label_37;
            }
            case 8: {
                goto label_34;
            }
            case 9: {
                goto label_31;
            }
            case 10: {
                goto label_29;
            }
            case 11: {
                goto label_26;
            }
            case 12: {
                goto label_23;
            }
            case 13: {
                goto label_20;
            }
            case 14: {
                goto label_17;
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
    label_34:
        return Integer.valueOf(this.zzbk());
    label_37:
        return Float.valueOf(this.readFloat());
    label_40:
        return Long.valueOf(this.zzbl());
    label_8:
        return Long.valueOf(this.zzbi());
    label_43:
        return Integer.valueOf(this.zzbm());
    label_11:
        return Integer.valueOf(this.zzbq());
    label_46:
        return Integer.valueOf(this.zzbr());
    label_14:
        return this.zzc(true);
    label_49:
        return Double.valueOf(this.readDouble());
    label_17:
        return Long.valueOf(this.zzbv());
    label_52:
        return this.zzbp();
    label_20:
        return Integer.valueOf(this.zzbu());
    label_54:
        return Boolean.valueOf(this.zzbn());
    label_23:
        return Long.valueOf(this.zzbt());
    label_26:
        return Integer.valueOf(this.zzbs());
    label_29:
        return this.zzb(arg3, arg4);
    label_31:
        return Long.valueOf(this.zzbj());
    }

    public final Object zzb(Class arg2, zzgl arg3) {
        this.zzy(2);
        return this.zzc(zzis.zzfc().zzg(arg2), arg3);
    }

    private final void zzb(List arg4, boolean arg5) {
        int v0_1;
        int v4;
        if((this.tag & 7) == 2) {
            if(((arg4 instanceof zzhq)) && !arg5) {
                List v0 = arg4;
                do {
                    ((zzhq)v0).zzd(this.zzbp());
                    if(!this.zzbf()) {
                        v4 = this.pos;
                        if(this.zzbw() == this.tag) {
                            continue;
                        }

                        break;
                    }
                    else {
                        return;
                    }
                }
                while(true);

                this.pos = v4;
                return;
            }

            do {
                arg4.add(this.zzc(arg5));
                if(this.zzbf()) {
                    return;
                }

                v0_1 = this.pos;
            }
            while(this.zzbw() == this.tag);

            this.pos = v0_1;
            return;
        }

        throw zzhh.zzed();
    }

    public final Object zzb(zziy arg2, zzgl arg3) {
        this.zzy(2);
        return this.zzc(arg2, arg3);
    }

    public final void zzb(List arg4, zziy arg5, zzgl arg6) {
        int v1;
        if((this.tag & 7) == 2) {
            int v0 = this.tag;
            do {
                arg4.add(this.zzc(arg5, arg6));
                if(this.zzbf()) {
                    return;
                }

                v1 = this.pos;
            }
            while(this.zzbw() == v0);

            this.pos = v1;
            return;
        }

        throw zzhh.zzed();
    }

    public final void zzb(Map arg6, zzia arg7, zzgl arg8) {
        Object v2;
        Object v0_1;
        this.zzy(2);
        int v0 = this.zzbw();
        this.zzx(v0);
        int v1 = this.limit;
        this.limit = this.pos + v0;
        try {
            v0_1 = arg7.zzuu;
            v2 = arg7.zzss;
            while(true) {
            label_10:
                int v3 = this.zzbg();
                if(v3 == 2147483647) {
                    goto label_40;
                }

                break;
            }
        }
        catch(Throwable v6) {
            goto label_44;
        }

        switch(v3) {
            case 1: {
                goto label_22;
            }
            case 2: {
                goto label_16;
            }
        }

        try {
            boolean v3_1 = this.zzbh();
            if(v3_1) {
                goto label_10;
            }

            throw new zzhh("Unable to parse map entry.");
        label_22:
            v0_1 = this.zzb(arg7.zzut, null, null);
            goto label_10;
        label_16:
            v2 = this.zzb(arg7.zzuv, arg7.zzss.getClass(), arg8);
            goto label_10;
        }
        catch(Throwable v6) {
        label_44:
            this.limit = v1;
            throw v6;
        }
        catch(zzhi ) {
            try {
                if(this.zzbh()) {
                    goto label_10;
                }

                throw new zzhh("Unable to parse map entry.");
            label_40:
                arg6.put(v0_1, v2);
            }
            catch(Throwable v6) {
                goto label_44;
            }

            this.limit = v1;
            return;
        }
    }

    private final boolean zzbf() {
        if(this.pos == this.limit) {
            return 1;
        }

        return 0;
    }

    public final int zzbg() {
        int v1 = 2147483647;
        if(this.zzbf()) {
            return v1;
        }

        this.tag = this.zzbw();
        if(this.tag == this.zznq) {
            return v1;
        }

        return this.tag >>> 3;
    }

    public final boolean zzbh() {
        int v1 = 0;
        if(!this.zzbf()) {
            if(this.tag == this.zznq) {
            }
            else {
                int v0 = this.tag & 7;
                int v3 = 4;
                if(v0 != 5) {
                    switch(v0) {
                        case 0: {
                            goto label_39;
                        }
                        case 1: {
                            goto label_37;
                        }
                        case 2: {
                            goto label_34;
                        }
                        case 3: {
                            goto label_16;
                        }
                    }

                    throw zzhh.zzed();
                label_34:
                    v0 = this.zzbw();
                    goto label_35;
                label_37:
                    v0 = 8;
                label_35:
                    this.zzw(v0);
                    return 1;
                label_39:
                    int v2 = 10;
                    if(this.limit - this.pos >= v2) {
                        byte[] v0_1 = this.buffer;
                        int v5 = this.pos;
                        v3 = 0;
                        while(true) {
                            if(v3 < v2) {
                                int v6 = v5 + 1;
                                if(v0_1[v5] >= 0) {
                                    this.pos = v6;
                                }
                                else {
                                    ++v3;
                                    v5 = v6;
                                    continue;
                                }
                            }
                            else {
                                goto label_57;
                            }

                            return 1;
                        }
                    }
                    else {
                        while(true) {
                        label_57:
                            if(v1 >= v2) {
                                break;
                            }
                            else if(this.readByte() < 0) {
                                ++v1;
                                continue;
                            }

                            return 1;
                        }

                        throw zzhh.zzeb();
                    }

                    return 1;
                label_16:
                    v0 = this.zznq;
                    this.zznq = this.tag >>> 3 << 3 | v3;
                    do {
                        if(this.zzbg() == 2147483647) {
                            break;
                        }
                    }
                    while(this.zzbh());

                    if(this.tag == this.zznq) {
                        this.zznq = v0;
                        return 1;
                    }

                    throw zzhh.zzef();
                }
                else {
                    this.zzw(v3);
                    return 1;
                }
            }
        }

        return 0;
    }

    public final long zzbi() {
        this.zzy(0);
        return this.zzbx();
    }

    public final long zzbj() {
        this.zzy(0);
        return this.zzbx();
    }

    public final int zzbk() {
        this.zzy(0);
        return this.zzbw();
    }

    public final long zzbl() {
        this.zzy(1);
        return this.zzca();
    }

    public final int zzbm() {
        this.zzy(5);
        return this.zzbz();
    }

    public final boolean zzbn() {
        boolean v0 = false;
        this.zzy(0);
        if(this.zzbw() != 0) {
            v0 = true;
        }

        return v0;
    }

    public final String zzbo() {
        return this.zzc(true);
    }

    public final zzfr zzbp() {
        this.zzy(2);
        int v0 = this.zzbw();
        if(v0 == 0) {
            return zzfr.zznt;
        }

        this.zzx(v0);
        zzfr v1 = this.zzno ? zzfr.zzd(this.buffer, this.pos, v0) : zzfr.zzc(this.buffer, this.pos, v0);
        this.pos += v0;
        return v1;
    }

    public final int zzbq() {
        this.zzy(0);
        return this.zzbw();
    }

    public final int zzbr() {
        this.zzy(0);
        return this.zzbw();
    }

    public final int zzbs() {
        this.zzy(5);
        return this.zzbz();
    }

    public final long zzbt() {
        this.zzy(1);
        return this.zzca();
    }

    public final int zzbu() {
        this.zzy(0);
        return zzga.zzan(this.zzbw());
    }

    public final long zzbv() {
        this.zzy(0);
        return zzga.zzd(this.zzbx());
    }

    private final int zzbw() {
        int v0 = this.pos;
        if(this.limit != this.pos) {
            int v2 = v0 + 1;
            v0 = this.buffer[v0];
            if(v0 >= 0) {
                this.pos = v2;
                return v0;
            }

            if(this.limit - v2 < 9) {
                return ((int)this.zzby());
            }

            int v3 = v2 + 1;
            v0 ^= this.buffer[v2] << 7;
            if(v0 < 0) {
                v0 ^= -128;
            }
            else {
                v2 = v3 + 1;
                v0 ^= this.buffer[v3] << 14;
                if(v0 >= 0) {
                    v0 ^= 16256;
                }
                else {
                    v3 = v2 + 1;
                    v0 ^= this.buffer[v2] << 21;
                    if(v0 < 0) {
                        v0 ^= -2080896;
                        goto label_74;
                    }
                    else {
                        v2 = v3 + 1;
                        v0 = v0 ^ this.buffer[v3] << 28 ^ 266354560;
                        if(this.buffer[v3] < 0) {
                            v3 = v2 + 1;
                            if(this.buffer[v2] < 0) {
                                v2 = v3 + 1;
                                if(this.buffer[v3] < 0) {
                                    v3 = v2 + 1;
                                    if(this.buffer[v2] < 0) {
                                        v2 = v3 + 1;
                                        if(this.buffer[v3] < 0) {
                                            v3 = v2 + 1;
                                            if(this.buffer[v2] >= 0) {
                                                goto label_74;
                                            }
                                            else {
                                                throw zzhh.zzeb();
                                            }
                                        }
                                    }
                                    else {
                                        goto label_74;
                                    }
                                }
                            }
                            else {
                                goto label_74;
                            }
                        }
                    }
                }

                v3 = v2;
            }

        label_74:
            this.pos = v3;
            return v0;
        }

        throw zzhh.zzdz();
    }

    private final long zzbx() {
        long v0_1;
        int v6;
        long v2_1;
        long v9;
        int v0 = this.pos;
        if(this.limit != v0) {
            byte[] v1 = this.buffer;
            int v2 = v0 + 1;
            v0 = v1[v0];
            if(v0 >= 0) {
                this.pos = v2;
                return ((long)v0);
            }

            if(this.limit - v2 < 9) {
                return this.zzby();
            }

            int v3 = v2 + 1;
            v0 ^= v1[v2] << 7;
            if(v0 < 0) {
                v0 ^= -128;
                goto label_22;
            }
            else {
                v2 = v3 + 1;
                v0 ^= v1[v3] << 14;
                if(v0 >= 0) {
                    v9 = ((long)(v0 ^ 16256));
                    v0 = v2;
                    goto label_25;
                }
                else {
                    v3 = v2 + 1;
                    v0 ^= v1[v2] << 21;
                    if(v0 < 0) {
                        v0 ^= -2080896;
                    label_22:
                        v9 = ((long)v0);
                        v0 = v3;
                    label_25:
                        v2_1 = v9;
                    }
                    else {
                        long v4 = ((long)v0);
                        v0 = v3 + 1;
                        v2_1 = (((long)v1[v3])) << 28 ^ v4;
                        v4 = 0;
                        if(v2_1 >= v4) {
                            v4 = 266354560;
                        }
                        else {
                            v6 = v0 + 1;
                            v2_1 ^= (((long)v1[v0])) << 35;
                            if(v2_1 < v4) {
                                v0_1 = -34093383808L;
                                goto label_65;
                            }
                            else {
                                v0 = v6 + 1;
                                v2_1 ^= (((long)v1[v6])) << 42;
                                if(v2_1 >= v4) {
                                    v4 = 4363953127296L;
                                    goto label_55;
                                }
                                else {
                                    goto label_78;
                                }
                            }

                            goto label_67;
                        }

                    label_55:
                        v2_1 ^= v4;
                        goto label_103;
                    label_78:
                        v6 = v0 + 1;
                        v2_1 ^= (((long)v1[v0])) << 49;
                        if(v2_1 < v4) {
                            v0_1 = -558586000294016L;
                        label_65:
                            v2_1 ^= v0_1;
                        }
                        else {
                            v0 = v6 + 1;
                            v2_1 = v2_1 ^ (((long)v1[v6])) << 56 ^ 71499008037633920L;
                            if(v2_1 < v4) {
                                v6 = v0 + 1;
                                if((((long)v1[v0])) >= v4) {
                                }
                                else {
                                    goto label_101;
                                }
                            }
                            else {
                                goto label_103;
                            }
                        }

                    label_67:
                        v0 = v6;
                        goto label_103;
                    label_101:
                        throw zzhh.zzeb();
                    }
                }
            }

        label_103:
            this.pos = v0;
            return v2_1;
        }

        throw zzhh.zzdz();
    }

    private final long zzby() {
        long v0 = 0;
        int v2;
        for(v2 = 0; v2 < 64; v2 += 7) {
            int v3 = this.readByte();
            v0 |= (((long)(v3 & 127))) << v2;
            if((v3 & 128) == 0) {
                return v0;
            }
        }

        throw zzhh.zzeb();
    }

    private final int zzbz() {
        this.zzx(4);
        return this.zzcb();
    }

    private final String zzc(boolean arg5) {
        this.zzy(2);
        int v0 = this.zzbw();
        if(v0 == 0) {
            return "";
        }

        this.zzx(v0);
        if(arg5) {
            if(zzjy.zzh(this.buffer, this.pos, this.pos + v0)) {
            }
            else {
                throw zzhh.zzeg();
            }
        }

        String v5 = new String(this.buffer, this.pos, v0, zzhb.UTF_8);
        this.pos += v0;
        return v5;
    }

    private final Object zzc(zziy arg4, zzgl arg5) {
        Object v0_1;
        int v0 = this.zzbw();
        this.zzx(v0);
        int v1 = this.limit;
        int v2 = this.pos + v0;
        this.limit = v2;
        try {
            v0_1 = arg4.newInstance();
            arg4.zzb(v0_1, ((zzix)this), arg5);
            arg4.zzd(v0_1);
            if(this.pos != v2) {
                goto label_13;
            }
        }
        catch(Throwable v4) {
            goto label_16;
        }

        this.limit = v1;
        return v0_1;
        try {
        label_13:
            throw zzhh.zzef();
        }
        catch(Throwable v4) {
        label_16:
            this.limit = v1;
            throw v4;
        }
    }

    public final Object zzc(Class arg2, zzgl arg3) {
        this.zzy(3);
        return this.zze(zzis.zzfc().zzg(arg2), arg3);
    }

    public final void zzc(List arg4, zziy arg5, zzgl arg6) {
        int v1;
        if((this.tag & 7) == 3) {
            int v0 = this.tag;
            do {
                arg4.add(this.zze(arg5, arg6));
                if(this.zzbf()) {
                    return;
                }

                v1 = this.pos;
            }
            while(this.zzbw() == v0);

            this.pos = v1;
            return;
        }

        throw zzhh.zzed();
    }

    private final long zzca() {
        this.zzx(8);
        return this.zzcc();
    }

    private final int zzcb() {
        int v0 = this.pos;
        byte[] v1 = this.buffer;
        this.pos = v0 + 4;
        return (v1[v0 + 3] & 255) << 24 | (v1[v0] & 255 | (v1[v0 + 1] & 255) << 8 | (v1[v0 + 2] & 255) << 16);
    }

    private final long zzcc() {
        int v0 = this.pos;
        byte[] v1 = this.buffer;
        this.pos = v0 + 8;
        return ((((long)v1[v0 + 7])) & 255) << 56 | ((((long)v1[v0])) & 255 | ((((long)v1[v0 + 1])) & 255) << 8 | ((((long)v1[v0 + 2])) & 255) << 16 | ((((long)v1[v0 + 3])) & 255) << 24 | ((((long)v1[v0 + 4])) & 255) << 32 | ((((long)v1[v0 + 5])) & 255) << 40 | ((((long)v1[v0 + 6])) & 255) << 48);
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
            goto label_29;
        }

        switch(this.tag & 7) {
            case 1: {
                goto label_18;
            }
            case 2: {
                goto label_7;
            }
        }

        throw zzhh.zzed();
        do {
        label_18:
            ((zzgi)arg5).zzd(this.readDouble());
            if(this.zzbf()) {
                return;
            }

            v0 = this.pos;
        }
        while(this.zzbw() == this.tag);

        this.pos = v0;
        return;
    label_7:
        v0 = this.zzbw();
        this.zzz(v0);
        int v1 = this.pos + v0;
        while(this.pos < v1) {
            ((zzgi)arg5).zzd(Double.longBitsToDouble(this.zzcc()));
        }

        return;
    label_29:
        switch(this.tag & 7) {
            case 1: {
                goto label_46;
            }
            case 2: {
                goto label_34;
            }
        }

        throw zzhh.zzed();
    label_34:
        v0 = this.zzbw();
        this.zzz(v0);
        v1 = this.pos + v0;
        while(this.pos < v1) {
            arg5.add(Double.valueOf(Double.longBitsToDouble(this.zzcc())));
        }

        return;
        do {
        label_46:
            arg5.add(Double.valueOf(this.readDouble()));
            if(this.zzbf()) {
                return;
            }

            v0 = this.pos;
        }
        while(this.zzbw() == this.tag);

        this.pos = v0;
    }

    public final void zzf(List arg4) {
        int v0;
        int v1 = 5;
        int v2 = 2;
        if((arg4 instanceof zzgw)) {
            v0 = this.tag & 7;
            if(v0 != v2) {
                if(v0 == v1) {
                    do {
                        ((zzgw)arg4).zzf(this.readFloat());
                        if(this.zzbf()) {
                            return;
                        }

                        v0 = this.pos;
                    }
                    while(this.zzbw() == this.tag);

                    this.pos = v0;
                    return;
                }

                throw zzhh.zzed();
            }

            v0 = this.zzbw();
            this.zzaa(v0);
            v1 = this.pos + v0;
            while(this.pos < v1) {
                ((zzgw)arg4).zzf(Float.intBitsToFloat(this.zzcb()));
            }

            return;
        }

        v0 = this.tag & 7;
        if(v0 != v2) {
            if(v0 == v1) {
                do {
                    arg4.add(Float.valueOf(this.readFloat()));
                    if(this.zzbf()) {
                        return;
                    }

                    v0 = this.pos;
                }
                while(this.zzbw() == this.tag);

                this.pos = v0;
                return;
            }

            throw zzhh.zzed();
        }

        v0 = this.zzbw();
        this.zzaa(v0);
        v1 = this.pos + v0;
        while(this.pos < v1) {
            arg4.add(Float.valueOf(Float.intBitsToFloat(this.zzcb())));
        }
    }

    public final void zzg(List arg5) {
        int v1 = 2;
        if((arg5 instanceof zzhv)) {
            List v0 = arg5;
            int v5 = this.tag & 7;
            if(v5 != 0) {
                if(v5 == v1) {
                    v1 = this.pos + this.zzbw();
                    while(this.pos < v1) {
                        ((zzhv)v0).zzp(this.zzbx());
                    }

                    this.zzab(v1);
                    return;
                }
                else {
                    throw zzhh.zzed();
                }
            }

            do {
                ((zzhv)v0).zzp(this.zzbi());
                if(this.zzbf()) {
                    return;
                }

                v5 = this.pos;
            }
            while(this.zzbw() == this.tag);

            this.pos = v5;
            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != 0) {
            if(v0_1 == v1) {
                v1 = this.pos + this.zzbw();
                while(this.pos < v1) {
                    arg5.add(Long.valueOf(this.zzbx()));
                }

                this.zzab(v1);
                return;
            }
            else {
                throw zzhh.zzed();
            }
        }

        do {
            arg5.add(Long.valueOf(this.zzbi()));
            if(this.zzbf()) {
                return;
            }

            v0_1 = this.pos;
        }
        while(this.zzbw() == this.tag);

        this.pos = v0_1;
    }

    public final void zzh(List arg5) {
        int v1 = 2;
        if((arg5 instanceof zzhv)) {
            List v0 = arg5;
            int v5 = this.tag & 7;
            if(v5 != 0) {
                if(v5 == v1) {
                    v1 = this.pos + this.zzbw();
                    while(this.pos < v1) {
                        ((zzhv)v0).zzp(this.zzbx());
                    }

                    this.zzab(v1);
                    return;
                }
                else {
                    throw zzhh.zzed();
                }
            }

            do {
                ((zzhv)v0).zzp(this.zzbj());
                if(this.zzbf()) {
                    return;
                }

                v5 = this.pos;
            }
            while(this.zzbw() == this.tag);

            this.pos = v5;
            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != 0) {
            if(v0_1 == v1) {
                v1 = this.pos + this.zzbw();
                while(this.pos < v1) {
                    arg5.add(Long.valueOf(this.zzbx()));
                }

                this.zzab(v1);
                return;
            }
            else {
                throw zzhh.zzed();
            }
        }

        do {
            arg5.add(Long.valueOf(this.zzbj()));
            if(this.zzbf()) {
                return;
            }

            v0_1 = this.pos;
        }
        while(this.zzbw() == this.tag);

        this.pos = v0_1;
    }

    public final void zzi(List arg4) {
        int v1 = 2;
        if((arg4 instanceof zzha)) {
            List v0 = arg4;
            int v4 = this.tag & 7;
            if(v4 != 0) {
                if(v4 == v1) {
                    v1 = this.pos + this.zzbw();
                    while(this.pos < v1) {
                        ((zzha)v0).zzbe(this.zzbw());
                    }

                    this.zzab(v1);
                    return;
                }
                else {
                    throw zzhh.zzed();
                }
            }

            do {
                ((zzha)v0).zzbe(this.zzbk());
                if(this.zzbf()) {
                    return;
                }

                v4 = this.pos;
            }
            while(this.zzbw() == this.tag);

            this.pos = v4;
            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != 0) {
            if(v0_1 == v1) {
                v1 = this.pos + this.zzbw();
                while(this.pos < v1) {
                    arg4.add(Integer.valueOf(this.zzbw()));
                }

                this.zzab(v1);
                return;
            }
            else {
                throw zzhh.zzed();
            }
        }

        do {
            arg4.add(Integer.valueOf(this.zzbk()));
            if(this.zzbf()) {
                return;
            }

            v0_1 = this.pos;
        }
        while(this.zzbw() == this.tag);

        this.pos = v0_1;
    }

    public final void zzj(List arg5) {
        int v0;
        if(!(arg5 instanceof zzhv)) {
            goto label_28;
        }

        switch(this.tag & 7) {
            case 1: {
                goto label_17;
            }
            case 2: {
                goto label_7;
            }
        }

        throw zzhh.zzed();
        do {
        label_17:
            ((zzhv)arg5).zzp(this.zzbl());
            if(this.zzbf()) {
                return;
            }

            v0 = this.pos;
        }
        while(this.zzbw() == this.tag);

        this.pos = v0;
        return;
    label_7:
        v0 = this.zzbw();
        this.zzz(v0);
        int v1 = this.pos + v0;
        while(this.pos < v1) {
            ((zzhv)arg5).zzp(this.zzcc());
        }

        return;
    label_28:
        switch(this.tag & 7) {
            case 1: {
                goto label_44;
            }
            case 2: {
                goto label_33;
            }
        }

        throw zzhh.zzed();
    label_33:
        v0 = this.zzbw();
        this.zzz(v0);
        v1 = this.pos + v0;
        while(this.pos < v1) {
            arg5.add(Long.valueOf(this.zzcc()));
        }

        return;
        do {
        label_44:
            arg5.add(Long.valueOf(this.zzbl()));
            if(this.zzbf()) {
                return;
            }

            v0 = this.pos;
        }
        while(this.zzbw() == this.tag);

        this.pos = v0;
    }

    public final void zzk(List arg4) {
        int v0;
        int v1 = 5;
        int v2 = 2;
        if((arg4 instanceof zzha)) {
            v0 = this.tag & 7;
            if(v0 != v2) {
                if(v0 == v1) {
                    do {
                        ((zzha)arg4).zzbe(this.zzbm());
                        if(this.zzbf()) {
                            return;
                        }

                        v0 = this.pos;
                    }
                    while(this.zzbw() == this.tag);

                    this.pos = v0;
                    return;
                }

                throw zzhh.zzed();
            }

            v0 = this.zzbw();
            this.zzaa(v0);
            v1 = this.pos + v0;
            while(this.pos < v1) {
                ((zzha)arg4).zzbe(this.zzcb());
            }

            return;
        }

        v0 = this.tag & 7;
        if(v0 != v2) {
            if(v0 == v1) {
                do {
                    arg4.add(Integer.valueOf(this.zzbm()));
                    if(this.zzbf()) {
                        return;
                    }

                    v0 = this.pos;
                }
                while(this.zzbw() == this.tag);

                this.pos = v0;
                return;
            }

            throw zzhh.zzed();
        }

        v0 = this.zzbw();
        this.zzaa(v0);
        v1 = this.pos + v0;
        while(this.pos < v1) {
            arg4.add(Integer.valueOf(this.zzcb()));
        }
    }

    public final void zzl(List arg5) {
        int v3 = 2;
        if((arg5 instanceof zzfp)) {
            List v0 = arg5;
            int v5 = this.tag & 7;
            if(v5 != 0) {
                if(v5 == v3) {
                    v3 = this.pos + this.zzbw();
                    while(this.pos < v3) {
                        boolean v5_1 = this.zzbw() != 0 ? true : false;
                        ((zzfp)v0).addBoolean(v5_1);
                    }

                    this.zzab(v3);
                    return;
                }
                else {
                    throw zzhh.zzed();
                }
            }

            do {
                ((zzfp)v0).addBoolean(this.zzbn());
                if(this.zzbf()) {
                    return;
                }

                v5 = this.pos;
            }
            while(this.zzbw() == this.tag);

            this.pos = v5;
            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != 0) {
            if(v0_1 == v3) {
                v3 = this.pos + this.zzbw();
                while(this.pos < v3) {
                    boolean v0_2 = this.zzbw() != 0 ? true : false;
                    arg5.add(Boolean.valueOf(v0_2));
                }

                this.zzab(v3);
                return;
            }
            else {
                throw zzhh.zzed();
            }
        }

        do {
            arg5.add(Boolean.valueOf(this.zzbn()));
            if(this.zzbf()) {
                return;
            }

            v0_1 = this.pos;
        }
        while(this.zzbw() == this.tag);

        this.pos = v0_1;
    }

    public final void zzm(List arg2) {
        this.zzb(arg2, true);
    }

    public final void zzn(List arg4) {
        int v0;
        if((this.tag & 7) == 2) {
            do {
                arg4.add(this.zzbp());
                if(this.zzbf()) {
                    return;
                }

                v0 = this.pos;
            }
            while(this.zzbw() == this.tag);

            this.pos = v0;
            return;
        }

        throw zzhh.zzed();
    }

    public final void zzo(List arg4) {
        int v1 = 2;
        if((arg4 instanceof zzha)) {
            List v0 = arg4;
            int v4 = this.tag & 7;
            if(v4 != 0) {
                if(v4 == v1) {
                    v1 = this.pos + this.zzbw();
                    while(this.pos < v1) {
                        ((zzha)v0).zzbe(this.zzbw());
                    }

                    return;
                }
                else {
                    throw zzhh.zzed();
                }
            }

            do {
                ((zzha)v0).zzbe(this.zzbq());
                if(this.zzbf()) {
                    return;
                }

                v4 = this.pos;
            }
            while(this.zzbw() == this.tag);

            this.pos = v4;
            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != 0) {
            if(v0_1 == v1) {
                v1 = this.pos + this.zzbw();
                while(this.pos < v1) {
                    arg4.add(Integer.valueOf(this.zzbw()));
                }

                return;
            }
            else {
                throw zzhh.zzed();
            }
        }

        do {
            arg4.add(Integer.valueOf(this.zzbq()));
            if(this.zzbf()) {
                return;
            }

            v0_1 = this.pos;
        }
        while(this.zzbw() == this.tag);

        this.pos = v0_1;
    }

    public final void zzp(List arg4) {
        int v1 = 2;
        if((arg4 instanceof zzha)) {
            List v0 = arg4;
            int v4 = this.tag & 7;
            if(v4 != 0) {
                if(v4 == v1) {
                    v1 = this.pos + this.zzbw();
                    while(this.pos < v1) {
                        ((zzha)v0).zzbe(this.zzbw());
                    }

                    return;
                }
                else {
                    throw zzhh.zzed();
                }
            }

            do {
                ((zzha)v0).zzbe(this.zzbr());
                if(this.zzbf()) {
                    return;
                }

                v4 = this.pos;
            }
            while(this.zzbw() == this.tag);

            this.pos = v4;
            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != 0) {
            if(v0_1 == v1) {
                v1 = this.pos + this.zzbw();
                while(this.pos < v1) {
                    arg4.add(Integer.valueOf(this.zzbw()));
                }

                return;
            }
            else {
                throw zzhh.zzed();
            }
        }

        do {
            arg4.add(Integer.valueOf(this.zzbr()));
            if(this.zzbf()) {
                return;
            }

            v0_1 = this.pos;
        }
        while(this.zzbw() == this.tag);

        this.pos = v0_1;
    }

    public final void zzq(List arg4) {
        int v0;
        int v1 = 5;
        int v2 = 2;
        if((arg4 instanceof zzha)) {
            v0 = this.tag & 7;
            if(v0 != v2) {
                if(v0 == v1) {
                    do {
                        ((zzha)arg4).zzbe(this.zzbs());
                        if(this.zzbf()) {
                            return;
                        }

                        v0 = this.pos;
                    }
                    while(this.zzbw() == this.tag);

                    this.pos = v0;
                    return;
                }

                throw zzhh.zzed();
            }

            v0 = this.zzbw();
            this.zzaa(v0);
            v1 = this.pos + v0;
            while(this.pos < v1) {
                ((zzha)arg4).zzbe(this.zzcb());
            }

            return;
        }

        v0 = this.tag & 7;
        if(v0 != v2) {
            if(v0 == v1) {
                do {
                    arg4.add(Integer.valueOf(this.zzbs()));
                    if(this.zzbf()) {
                        return;
                    }

                    v0 = this.pos;
                }
                while(this.zzbw() == this.tag);

                this.pos = v0;
                return;
            }

            throw zzhh.zzed();
        }

        v0 = this.zzbw();
        this.zzaa(v0);
        v1 = this.pos + v0;
        while(this.pos < v1) {
            arg4.add(Integer.valueOf(this.zzcb()));
        }
    }

    public final void zzr(List arg5) {
        int v0;
        if(!(arg5 instanceof zzhv)) {
            goto label_28;
        }

        switch(this.tag & 7) {
            case 1: {
                goto label_17;
            }
            case 2: {
                goto label_7;
            }
        }

        throw zzhh.zzed();
        do {
        label_17:
            ((zzhv)arg5).zzp(this.zzbt());
            if(this.zzbf()) {
                return;
            }

            v0 = this.pos;
        }
        while(this.zzbw() == this.tag);

        this.pos = v0;
        return;
    label_7:
        v0 = this.zzbw();
        this.zzz(v0);
        int v1 = this.pos + v0;
        while(this.pos < v1) {
            ((zzhv)arg5).zzp(this.zzcc());
        }

        return;
    label_28:
        switch(this.tag & 7) {
            case 1: {
                goto label_44;
            }
            case 2: {
                goto label_33;
            }
        }

        throw zzhh.zzed();
    label_33:
        v0 = this.zzbw();
        this.zzz(v0);
        v1 = this.pos + v0;
        while(this.pos < v1) {
            arg5.add(Long.valueOf(this.zzcc()));
        }

        return;
        do {
        label_44:
            arg5.add(Long.valueOf(this.zzbt()));
            if(this.zzbf()) {
                return;
            }

            v0 = this.pos;
        }
        while(this.zzbw() == this.tag);

        this.pos = v0;
    }

    public final void zzs(List arg4) {
        int v1 = 2;
        if((arg4 instanceof zzha)) {
            List v0 = arg4;
            int v4 = this.tag & 7;
            if(v4 != 0) {
                if(v4 == v1) {
                    v1 = this.pos + this.zzbw();
                    while(this.pos < v1) {
                        ((zzha)v0).zzbe(zzga.zzan(this.zzbw()));
                    }

                    return;
                }
                else {
                    throw zzhh.zzed();
                }
            }

            do {
                ((zzha)v0).zzbe(this.zzbu());
                if(this.zzbf()) {
                    return;
                }

                v4 = this.pos;
            }
            while(this.zzbw() == this.tag);

            this.pos = v4;
            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != 0) {
            if(v0_1 == v1) {
                v1 = this.pos + this.zzbw();
                while(this.pos < v1) {
                    arg4.add(Integer.valueOf(zzga.zzan(this.zzbw())));
                }

                return;
            }
            else {
                throw zzhh.zzed();
            }
        }

        do {
            arg4.add(Integer.valueOf(this.zzbu()));
            if(this.zzbf()) {
                return;
            }

            v0_1 = this.pos;
        }
        while(this.zzbw() == this.tag);

        this.pos = v0_1;
    }

    public final void zzt(List arg5) {
        int v1 = 2;
        if((arg5 instanceof zzhv)) {
            List v0 = arg5;
            int v5 = this.tag & 7;
            if(v5 != 0) {
                if(v5 == v1) {
                    v1 = this.pos + this.zzbw();
                    while(this.pos < v1) {
                        ((zzhv)v0).zzp(zzga.zzd(this.zzbx()));
                    }

                    return;
                }
                else {
                    throw zzhh.zzed();
                }
            }

            do {
                ((zzhv)v0).zzp(this.zzbv());
                if(this.zzbf()) {
                    return;
                }

                v5 = this.pos;
            }
            while(this.zzbw() == this.tag);

            this.pos = v5;
            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != 0) {
            if(v0_1 == v1) {
                v1 = this.pos + this.zzbw();
                while(this.pos < v1) {
                    arg5.add(Long.valueOf(zzga.zzd(this.zzbx())));
                }

                return;
            }
            else {
                throw zzhh.zzed();
            }
        }

        do {
            arg5.add(Long.valueOf(this.zzbv()));
            if(this.zzbf()) {
                return;
            }

            v0_1 = this.pos;
        }
        while(this.zzbw() == this.tag);

        this.pos = v0_1;
    }

    private final void zzw(int arg2) {
        this.zzx(arg2);
        this.pos += arg2;
    }

    private final void zzx(int arg3) {
        if(arg3 >= 0 && arg3 <= this.limit - this.pos) {
            return;
        }

        throw zzhh.zzdz();
    }

    private final void zzy(int arg2) {
        if((this.tag & 7) == arg2) {
            return;
        }

        throw zzhh.zzed();
    }

    private final void zzz(int arg1) {
        this.zzx(arg1);
        if((arg1 & 7) == 0) {
            return;
        }

        throw zzhh.zzef();
    }
}

