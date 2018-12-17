package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.Map;

final class zzur implements zzxi {
    private int tag;
    private final zzuo zzbur;
    private int zzbus;
    private int zzbut;

    private zzur(zzuo arg2) {
        super();
        this.zzbut = 0;
        this.zzbur = zzvo.zza(arg2, "input");
        this.zzbur.zzbuk = this;
    }

    public final int getTag() {
        return this.tag;
    }

    public final double readDouble() {
        this.zzat(1);
        return this.zzbur.readDouble();
    }

    public final float readFloat() {
        this.zzat(5);
        return this.zzbur.readFloat();
    }

    public final String readString() {
        this.zzat(2);
        return this.zzbur.readString();
    }

    public final void readStringList(List arg2) {
        this.zza(arg2, false);
    }

    public static zzur zza(zzuo arg1) {
        if(arg1.zzbuk != null) {
            return arg1.zzbuk;
        }

        return new zzur(arg1);
    }

    private final Object zza(zzyq arg2, Class arg3, zzuz arg4) {
        switch(zzus.zzbuu[arg2.ordinal()]) {
            case 1: {
                goto label_57;
            }
            case 2: {
                goto label_55;
            }
            case 3: {
                goto label_52;
            }
            case 4: {
                goto label_49;
            }
            case 5: {
                goto label_46;
            }
            case 6: {
                goto label_43;
            }
            case 7: {
                goto label_40;
            }
            case 8: {
                goto label_37;
            }
            case 9: {
                goto label_34;
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
    label_34:
        return Long.valueOf(this.zzui());
    label_37:
        return Integer.valueOf(this.zzuj());
    label_40:
        return Float.valueOf(this.readFloat());
    label_8:
        return Long.valueOf(this.zzuh());
    label_43:
        return Long.valueOf(this.zzuk());
    label_11:
        return Integer.valueOf(this.zzup());
    label_46:
        return Integer.valueOf(this.zzul());
    label_14:
        return this.zzun();
    label_16:
        return Long.valueOf(this.zzuu());
    label_49:
        return Integer.valueOf(this.zzuq());
    label_19:
        return Integer.valueOf(this.zzut());
    label_52:
        return Double.valueOf(this.readDouble());
    label_22:
        return Long.valueOf(this.zzus());
    label_55:
        return this.zzuo();
    label_57:
        return Boolean.valueOf(this.zzum());
    label_25:
        return Integer.valueOf(this.zzur());
    label_28:
        this.zzat(2);
        return this.zzc(zzxf.zzxn().zzi(arg3), arg4);
    }

    private final void zza(List arg3, boolean arg4) {
        int v0_2;
        int v3;
        if((this.tag & 7) == 2) {
            if(((arg3 instanceof zzwc)) && !arg4) {
                List v0 = arg3;
                do {
                    ((zzwc)v0).zzc(this.zzuo());
                    if(!this.zzbur.zzuw()) {
                        v3 = this.zzbur.zzug();
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

                this.zzbut = v3;
                return;
            }

            do {
                String v0_1 = arg4 ? this.zzun() : this.readString();
                arg3.add(v0_1);
                if(this.zzbur.zzuw()) {
                    return;
                }

                v0_2 = this.zzbur.zzug();
            }
            while(v0_2 == this.tag);

            this.zzbut = v0_2;
            return;
        }

        throw zzvt.zzwo();
    }

    public final Object zza(zzxj arg2, zzuz arg3) {
        this.zzat(2);
        return this.zzc(arg2, arg3);
    }

    public final void zza(List arg3, zzxj arg4, zzuz arg5) {
        int v1;
        if((this.tag & 7) == 2) {
            int v0 = this.tag;
            do {
                arg3.add(this.zzc(arg4, arg5));
                if(!this.zzbur.zzuw()) {
                    if(this.zzbut != 0) {
                    }
                    else {
                        v1 = this.zzbur.zzug();
                        if(v1 == v0) {
                            continue;
                        }

                        break;
                    }
                }

                return;
            }
            while(true);

            this.zzbut = v1;
            return;
        }

        throw zzvt.zzwo();
    }

    public final void zza(Map arg6, zzwm arg7, zzuz arg8) {
        this.zzat(2);
        int v0 = this.zzbur.zzaq(this.zzbur.zzup());
        Object v1 = arg7.zzcas;
        Object v2 = arg7.zzbre;
        try {
            while(true) {
            label_8:
                int v3 = this.zzve();
                if(v3 != 2147483647 && !this.zzbur.zzuw()) {
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
            boolean v3_1 = this.zzvf();
            if(v3_1) {
            }
            else {
                throw new zzvt("Unable to parse map entry.");
            label_17:
                v2 = this.zza(arg7.zzcat, arg7.zzbre.getClass(), arg8);
                goto label_8;
            label_23:
                v1 = this.zza(arg7.zzcar, null, null);
            }

            goto label_8;
        }
        catch(Throwable v6) {
        }
        catch(zzvu ) {
            try {
                if(this.zzvf()) {
                    goto label_8;
                }
                else {
                    throw new zzvt("Unable to parse map entry.");
                }

            label_41:
                arg6.put(v1, v2);
            }
            catch(Throwable v6) {
            label_47:
                this.zzbur.zzar(v0);
                throw v6;
            }
        }

        this.zzbur.zzar(v0);
    }

    private final void zzat(int arg2) {
        if((this.tag & 7) == arg2) {
            return;
        }

        throw zzvt.zzwo();
    }

    private static void zzau(int arg0) {
        if((arg0 & 7) == 0) {
            return;
        }

        throw zzvt.zzwq();
    }

    private static void zzav(int arg0) {
        if((arg0 & 3) == 0) {
            return;
        }

        throw zzvt.zzwq();
    }

    private final void zzaw(int arg2) {
        if(this.zzbur.zzux() == arg2) {
            return;
        }

        throw zzvt.zzwk();
    }

    public final Object zzb(zzxj arg2, zzuz arg3) {
        this.zzat(3);
        return this.zzd(arg2, arg3);
    }

    public final void zzb(List arg3, zzxj arg4, zzuz arg5) {
        int v1;
        if((this.tag & 7) == 3) {
            int v0 = this.tag;
            do {
                arg3.add(this.zzd(arg4, arg5));
                if(!this.zzbur.zzuw()) {
                    if(this.zzbut != 0) {
                    }
                    else {
                        v1 = this.zzbur.zzug();
                        if(v1 == v0) {
                            continue;
                        }

                        break;
                    }
                }

                return;
            }
            while(true);

            this.zzbut = v1;
            return;
        }

        throw zzvt.zzwo();
    }

    private final Object zzc(zzxj arg5, zzuz arg6) {
        int v0 = this.zzbur.zzup();
        if(this.zzbur.zzbuh < this.zzbur.zzbui) {
            v0 = this.zzbur.zzaq(v0);
            Object v1 = arg5.newInstance();
            ++this.zzbur.zzbuh;
            arg5.zza(v1, ((zzxi)this), arg6);
            arg5.zzu(v1);
            this.zzbur.zzan(0);
            --this.zzbur.zzbuh;
            this.zzbur.zzar(v0);
            return v1;
        }

        throw zzvt.zzwp();
    }

    private final Object zzd(zzxj arg3, zzuz arg4) {
        Object v1;
        int v0 = this.zzbus;
        this.zzbus = this.tag >>> 3 << 3 | 4;
        try {
            v1 = arg3.newInstance();
            arg3.zza(v1, ((zzxi)this), arg4);
            arg3.zzu(v1);
            if(this.tag != this.zzbus) {
                goto label_14;
            }
        }
        catch(Throwable v3) {
            goto label_17;
        }

        this.zzbus = v0;
        return v1;
        try {
        label_14:
            throw zzvt.zzwq();
        }
        catch(Throwable v3) {
        label_17:
            this.zzbus = v0;
            throw v3;
        }
    }

    public final void zzh(List arg5) {
        int v0;
        if(!(arg5 instanceof zzuw)) {
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

        throw zzvt.zzwo();
        do {
        label_20:
            ((zzuw)arg5).zzd(this.zzbur.readDouble());
            if(this.zzbur.zzuw()) {
                return;
            }

            v0 = this.zzbur.zzug();
        }
        while(v0 == this.tag);

        this.zzbut = v0;
        return;
    label_7:
        v0 = this.zzbur.zzup();
        zzur.zzau(v0);
        int v1 = this.zzbur.zzux() + v0;
        do {
            ((zzuw)arg5).zzd(this.zzbur.readDouble());
        }
        while(this.zzbur.zzux() < v1);

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

        throw zzvt.zzwo();
        do {
        label_52:
            arg5.add(Double.valueOf(this.zzbur.readDouble()));
            if(this.zzbur.zzuw()) {
                return;
            }

            v0 = this.zzbur.zzug();
        }
        while(v0 == this.tag);

        this.zzbut = v0;
        return;
    label_38:
        v0 = this.zzbur.zzup();
        zzur.zzau(v0);
        v1 = this.zzbur.zzux() + v0;
        do {
            arg5.add(Double.valueOf(this.zzbur.readDouble()));
        }
        while(this.zzbur.zzux() < v1);
    }

    public final void zzi(List arg5) {
        int v1 = 5;
        int v2 = 2;
        if((arg5 instanceof zzvj)) {
            List v0 = arg5;
            int v5 = this.tag & 7;
            if(v5 != v2) {
                if(v5 == v1) {
                    do {
                        ((zzvj)v0).zzc(this.zzbur.readFloat());
                        if(this.zzbur.zzuw()) {
                            return;
                        }

                        v5 = this.zzbur.zzug();
                    }
                    while(v5 == this.tag);

                    this.zzbut = v5;
                    return;
                }

                throw zzvt.zzwo();
            }

            v5 = this.zzbur.zzup();
            zzur.zzav(v5);
            int v3 = this.zzbur.zzux() + v5;
            do {
                ((zzvj)v0).zzc(this.zzbur.readFloat());
            }
            while(this.zzbur.zzux() < v3);

            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != v2) {
            if(v0_1 == v1) {
                do {
                    arg5.add(Float.valueOf(this.zzbur.readFloat()));
                    if(this.zzbur.zzuw()) {
                        return;
                    }

                    v0_1 = this.zzbur.zzug();
                }
                while(v0_1 == this.tag);

                this.zzbut = v0_1;
                return;
            }

            throw zzvt.zzwo();
        }

        v0_1 = this.zzbur.zzup();
        zzur.zzav(v0_1);
        v1 = this.zzbur.zzux() + v0_1;
        do {
            arg5.add(Float.valueOf(this.zzbur.readFloat()));
        }
        while(this.zzbur.zzux() < v1);
    }

    public final void zzj(List arg5) {
        int v1 = 2;
        if((arg5 instanceof zzwh)) {
            List v0 = arg5;
            int v5 = this.tag & 7;
            if(v5 != 0) {
                if(v5 == v1) {
                    v1 = this.zzbur.zzux() + this.zzbur.zzup();
                    do {
                        ((zzwh)v0).zzbg(this.zzbur.zzuh());
                    }
                    while(this.zzbur.zzux() < v1);

                    this.zzaw(v1);
                    return;
                }
                else {
                    throw zzvt.zzwo();
                }
            }

            do {
                ((zzwh)v0).zzbg(this.zzbur.zzuh());
                if(this.zzbur.zzuw()) {
                    return;
                }

                v5 = this.zzbur.zzug();
            }
            while(v5 == this.tag);

            this.zzbut = v5;
            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != 0) {
            if(v0_1 == v1) {
                v1 = this.zzbur.zzux() + this.zzbur.zzup();
                do {
                    arg5.add(Long.valueOf(this.zzbur.zzuh()));
                }
                while(this.zzbur.zzux() < v1);

                this.zzaw(v1);
                return;
            }
            else {
                throw zzvt.zzwo();
            }
        }

        do {
            arg5.add(Long.valueOf(this.zzbur.zzuh()));
            if(this.zzbur.zzuw()) {
                return;
            }

            v0_1 = this.zzbur.zzug();
        }
        while(v0_1 == this.tag);

        this.zzbut = v0_1;
    }

    public final void zzk(List arg5) {
        int v1 = 2;
        if((arg5 instanceof zzwh)) {
            List v0 = arg5;
            int v5 = this.tag & 7;
            if(v5 != 0) {
                if(v5 == v1) {
                    v1 = this.zzbur.zzux() + this.zzbur.zzup();
                    do {
                        ((zzwh)v0).zzbg(this.zzbur.zzui());
                    }
                    while(this.zzbur.zzux() < v1);

                    this.zzaw(v1);
                    return;
                }
                else {
                    throw zzvt.zzwo();
                }
            }

            do {
                ((zzwh)v0).zzbg(this.zzbur.zzui());
                if(this.zzbur.zzuw()) {
                    return;
                }

                v5 = this.zzbur.zzug();
            }
            while(v5 == this.tag);

            this.zzbut = v5;
            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != 0) {
            if(v0_1 == v1) {
                v1 = this.zzbur.zzux() + this.zzbur.zzup();
                do {
                    arg5.add(Long.valueOf(this.zzbur.zzui()));
                }
                while(this.zzbur.zzux() < v1);

                this.zzaw(v1);
                return;
            }
            else {
                throw zzvt.zzwo();
            }
        }

        do {
            arg5.add(Long.valueOf(this.zzbur.zzui()));
            if(this.zzbur.zzuw()) {
                return;
            }

            v0_1 = this.zzbur.zzug();
        }
        while(v0_1 == this.tag);

        this.zzbut = v0_1;
    }

    public final void zzl(List arg3) {
        int v1 = 2;
        if((arg3 instanceof zzvn)) {
            List v0 = arg3;
            int v3 = this.tag & 7;
            if(v3 != 0) {
                if(v3 == v1) {
                    v1 = this.zzbur.zzux() + this.zzbur.zzup();
                    do {
                        ((zzvn)v0).zzbm(this.zzbur.zzuj());
                    }
                    while(this.zzbur.zzux() < v1);

                    this.zzaw(v1);
                    return;
                }
                else {
                    throw zzvt.zzwo();
                }
            }

            do {
                ((zzvn)v0).zzbm(this.zzbur.zzuj());
                if(this.zzbur.zzuw()) {
                    return;
                }

                v3 = this.zzbur.zzug();
            }
            while(v3 == this.tag);

            this.zzbut = v3;
            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != 0) {
            if(v0_1 == v1) {
                v1 = this.zzbur.zzux() + this.zzbur.zzup();
                do {
                    arg3.add(Integer.valueOf(this.zzbur.zzuj()));
                }
                while(this.zzbur.zzux() < v1);

                this.zzaw(v1);
                return;
            }
            else {
                throw zzvt.zzwo();
            }
        }

        do {
            arg3.add(Integer.valueOf(this.zzbur.zzuj()));
            if(this.zzbur.zzuw()) {
                return;
            }

            v0_1 = this.zzbur.zzug();
        }
        while(v0_1 == this.tag);

        this.zzbut = v0_1;
    }

    public final void zzm(List arg5) {
        int v0;
        if(!(arg5 instanceof zzwh)) {
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

        throw zzvt.zzwo();
        do {
        label_20:
            ((zzwh)arg5).zzbg(this.zzbur.zzuk());
            if(this.zzbur.zzuw()) {
                return;
            }

            v0 = this.zzbur.zzug();
        }
        while(v0 == this.tag);

        this.zzbut = v0;
        return;
    label_7:
        v0 = this.zzbur.zzup();
        zzur.zzau(v0);
        int v1 = this.zzbur.zzux() + v0;
        do {
            ((zzwh)arg5).zzbg(this.zzbur.zzuk());
        }
        while(this.zzbur.zzux() < v1);

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

        throw zzvt.zzwo();
        do {
        label_52:
            arg5.add(Long.valueOf(this.zzbur.zzuk()));
            if(this.zzbur.zzuw()) {
                return;
            }

            v0 = this.zzbur.zzug();
        }
        while(v0 == this.tag);

        this.zzbut = v0;
        return;
    label_38:
        v0 = this.zzbur.zzup();
        zzur.zzau(v0);
        v1 = this.zzbur.zzux() + v0;
        do {
            arg5.add(Long.valueOf(this.zzbur.zzuk()));
        }
        while(this.zzbur.zzux() < v1);
    }

    public final void zzn(List arg5) {
        int v1 = 5;
        int v2 = 2;
        if((arg5 instanceof zzvn)) {
            List v0 = arg5;
            int v5 = this.tag & 7;
            if(v5 != v2) {
                if(v5 == v1) {
                    do {
                        ((zzvn)v0).zzbm(this.zzbur.zzul());
                        if(this.zzbur.zzuw()) {
                            return;
                        }

                        v5 = this.zzbur.zzug();
                    }
                    while(v5 == this.tag);

                    this.zzbut = v5;
                    return;
                }

                throw zzvt.zzwo();
            }

            v5 = this.zzbur.zzup();
            zzur.zzav(v5);
            int v3 = this.zzbur.zzux() + v5;
            do {
                ((zzvn)v0).zzbm(this.zzbur.zzul());
            }
            while(this.zzbur.zzux() < v3);

            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != v2) {
            if(v0_1 == v1) {
                do {
                    arg5.add(Integer.valueOf(this.zzbur.zzul()));
                    if(this.zzbur.zzuw()) {
                        return;
                    }

                    v0_1 = this.zzbur.zzug();
                }
                while(v0_1 == this.tag);

                this.zzbut = v0_1;
                return;
            }

            throw zzvt.zzwo();
        }

        v0_1 = this.zzbur.zzup();
        zzur.zzav(v0_1);
        v1 = this.zzbur.zzux() + v0_1;
        do {
            arg5.add(Integer.valueOf(this.zzbur.zzul()));
        }
        while(this.zzbur.zzux() < v1);
    }

    public final void zzo(List arg3) {
        int v1 = 2;
        if((arg3 instanceof zzub)) {
            List v0 = arg3;
            int v3 = this.tag & 7;
            if(v3 != 0) {
                if(v3 == v1) {
                    v1 = this.zzbur.zzux() + this.zzbur.zzup();
                    do {
                        ((zzub)v0).addBoolean(this.zzbur.zzum());
                    }
                    while(this.zzbur.zzux() < v1);

                    this.zzaw(v1);
                    return;
                }
                else {
                    throw zzvt.zzwo();
                }
            }

            do {
                ((zzub)v0).addBoolean(this.zzbur.zzum());
                if(this.zzbur.zzuw()) {
                    return;
                }

                v3 = this.zzbur.zzug();
            }
            while(v3 == this.tag);

            this.zzbut = v3;
            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != 0) {
            if(v0_1 == v1) {
                v1 = this.zzbur.zzux() + this.zzbur.zzup();
                do {
                    arg3.add(Boolean.valueOf(this.zzbur.zzum()));
                }
                while(this.zzbur.zzux() < v1);

                this.zzaw(v1);
                return;
            }
            else {
                throw zzvt.zzwo();
            }
        }

        do {
            arg3.add(Boolean.valueOf(this.zzbur.zzum()));
            if(this.zzbur.zzuw()) {
                return;
            }

            v0_1 = this.zzbur.zzug();
        }
        while(v0_1 == this.tag);

        this.zzbut = v0_1;
    }

    public final void zzp(List arg2) {
        this.zza(arg2, true);
    }

    public final void zzq(List arg3) {
        int v0;
        if((this.tag & 7) == 2) {
            do {
                arg3.add(this.zzuo());
                if(this.zzbur.zzuw()) {
                    return;
                }

                v0 = this.zzbur.zzug();
            }
            while(v0 == this.tag);

            this.zzbut = v0;
            return;
        }

        throw zzvt.zzwo();
    }

    public final void zzr(List arg3) {
        int v1 = 2;
        if((arg3 instanceof zzvn)) {
            List v0 = arg3;
            int v3 = this.tag & 7;
            if(v3 != 0) {
                if(v3 == v1) {
                    v1 = this.zzbur.zzux() + this.zzbur.zzup();
                    do {
                        ((zzvn)v0).zzbm(this.zzbur.zzup());
                    }
                    while(this.zzbur.zzux() < v1);

                    this.zzaw(v1);
                    return;
                }
                else {
                    throw zzvt.zzwo();
                }
            }

            do {
                ((zzvn)v0).zzbm(this.zzbur.zzup());
                if(this.zzbur.zzuw()) {
                    return;
                }

                v3 = this.zzbur.zzug();
            }
            while(v3 == this.tag);

            this.zzbut = v3;
            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != 0) {
            if(v0_1 == v1) {
                v1 = this.zzbur.zzux() + this.zzbur.zzup();
                do {
                    arg3.add(Integer.valueOf(this.zzbur.zzup()));
                }
                while(this.zzbur.zzux() < v1);

                this.zzaw(v1);
                return;
            }
            else {
                throw zzvt.zzwo();
            }
        }

        do {
            arg3.add(Integer.valueOf(this.zzbur.zzup()));
            if(this.zzbur.zzuw()) {
                return;
            }

            v0_1 = this.zzbur.zzug();
        }
        while(v0_1 == this.tag);

        this.zzbut = v0_1;
    }

    public final void zzs(List arg3) {
        int v1 = 2;
        if((arg3 instanceof zzvn)) {
            List v0 = arg3;
            int v3 = this.tag & 7;
            if(v3 != 0) {
                if(v3 == v1) {
                    v1 = this.zzbur.zzux() + this.zzbur.zzup();
                    do {
                        ((zzvn)v0).zzbm(this.zzbur.zzuq());
                    }
                    while(this.zzbur.zzux() < v1);

                    this.zzaw(v1);
                    return;
                }
                else {
                    throw zzvt.zzwo();
                }
            }

            do {
                ((zzvn)v0).zzbm(this.zzbur.zzuq());
                if(this.zzbur.zzuw()) {
                    return;
                }

                v3 = this.zzbur.zzug();
            }
            while(v3 == this.tag);

            this.zzbut = v3;
            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != 0) {
            if(v0_1 == v1) {
                v1 = this.zzbur.zzux() + this.zzbur.zzup();
                do {
                    arg3.add(Integer.valueOf(this.zzbur.zzuq()));
                }
                while(this.zzbur.zzux() < v1);

                this.zzaw(v1);
                return;
            }
            else {
                throw zzvt.zzwo();
            }
        }

        do {
            arg3.add(Integer.valueOf(this.zzbur.zzuq()));
            if(this.zzbur.zzuw()) {
                return;
            }

            v0_1 = this.zzbur.zzug();
        }
        while(v0_1 == this.tag);

        this.zzbut = v0_1;
    }

    public final void zzt(List arg5) {
        int v1 = 5;
        int v2 = 2;
        if((arg5 instanceof zzvn)) {
            List v0 = arg5;
            int v5 = this.tag & 7;
            if(v5 != v2) {
                if(v5 == v1) {
                    do {
                        ((zzvn)v0).zzbm(this.zzbur.zzur());
                        if(this.zzbur.zzuw()) {
                            return;
                        }

                        v5 = this.zzbur.zzug();
                    }
                    while(v5 == this.tag);

                    this.zzbut = v5;
                    return;
                }

                throw zzvt.zzwo();
            }

            v5 = this.zzbur.zzup();
            zzur.zzav(v5);
            int v3 = this.zzbur.zzux() + v5;
            do {
                ((zzvn)v0).zzbm(this.zzbur.zzur());
            }
            while(this.zzbur.zzux() < v3);

            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != v2) {
            if(v0_1 == v1) {
                do {
                    arg5.add(Integer.valueOf(this.zzbur.zzur()));
                    if(this.zzbur.zzuw()) {
                        return;
                    }

                    v0_1 = this.zzbur.zzug();
                }
                while(v0_1 == this.tag);

                this.zzbut = v0_1;
                return;
            }

            throw zzvt.zzwo();
        }

        v0_1 = this.zzbur.zzup();
        zzur.zzav(v0_1);
        v1 = this.zzbur.zzux() + v0_1;
        do {
            arg5.add(Integer.valueOf(this.zzbur.zzur()));
        }
        while(this.zzbur.zzux() < v1);
    }

    public final void zzu(List arg5) {
        int v0;
        if(!(arg5 instanceof zzwh)) {
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

        throw zzvt.zzwo();
        do {
        label_20:
            ((zzwh)arg5).zzbg(this.zzbur.zzus());
            if(this.zzbur.zzuw()) {
                return;
            }

            v0 = this.zzbur.zzug();
        }
        while(v0 == this.tag);

        this.zzbut = v0;
        return;
    label_7:
        v0 = this.zzbur.zzup();
        zzur.zzau(v0);
        int v1 = this.zzbur.zzux() + v0;
        do {
            ((zzwh)arg5).zzbg(this.zzbur.zzus());
        }
        while(this.zzbur.zzux() < v1);

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

        throw zzvt.zzwo();
        do {
        label_52:
            arg5.add(Long.valueOf(this.zzbur.zzus()));
            if(this.zzbur.zzuw()) {
                return;
            }

            v0 = this.zzbur.zzug();
        }
        while(v0 == this.tag);

        this.zzbut = v0;
        return;
    label_38:
        v0 = this.zzbur.zzup();
        zzur.zzau(v0);
        v1 = this.zzbur.zzux() + v0;
        do {
            arg5.add(Long.valueOf(this.zzbur.zzus()));
        }
        while(this.zzbur.zzux() < v1);
    }

    public final long zzuh() {
        this.zzat(0);
        return this.zzbur.zzuh();
    }

    public final long zzui() {
        this.zzat(0);
        return this.zzbur.zzui();
    }

    public final int zzuj() {
        this.zzat(0);
        return this.zzbur.zzuj();
    }

    public final long zzuk() {
        this.zzat(1);
        return this.zzbur.zzuk();
    }

    public final int zzul() {
        this.zzat(5);
        return this.zzbur.zzul();
    }

    public final boolean zzum() {
        this.zzat(0);
        return this.zzbur.zzum();
    }

    public final String zzun() {
        this.zzat(2);
        return this.zzbur.zzun();
    }

    public final zzud zzuo() {
        this.zzat(2);
        return this.zzbur.zzuo();
    }

    public final int zzup() {
        this.zzat(0);
        return this.zzbur.zzup();
    }

    public final int zzuq() {
        this.zzat(0);
        return this.zzbur.zzuq();
    }

    public final int zzur() {
        this.zzat(5);
        return this.zzbur.zzur();
    }

    public final long zzus() {
        this.zzat(1);
        return this.zzbur.zzus();
    }

    public final int zzut() {
        this.zzat(0);
        return this.zzbur.zzut();
    }

    public final long zzuu() {
        this.zzat(0);
        return this.zzbur.zzuu();
    }

    public final void zzv(List arg3) {
        int v1 = 2;
        if((arg3 instanceof zzvn)) {
            List v0 = arg3;
            int v3 = this.tag & 7;
            if(v3 != 0) {
                if(v3 == v1) {
                    v1 = this.zzbur.zzux() + this.zzbur.zzup();
                    do {
                        ((zzvn)v0).zzbm(this.zzbur.zzut());
                    }
                    while(this.zzbur.zzux() < v1);

                    this.zzaw(v1);
                    return;
                }
                else {
                    throw zzvt.zzwo();
                }
            }

            do {
                ((zzvn)v0).zzbm(this.zzbur.zzut());
                if(this.zzbur.zzuw()) {
                    return;
                }

                v3 = this.zzbur.zzug();
            }
            while(v3 == this.tag);

            this.zzbut = v3;
            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != 0) {
            if(v0_1 == v1) {
                v1 = this.zzbur.zzux() + this.zzbur.zzup();
                do {
                    arg3.add(Integer.valueOf(this.zzbur.zzut()));
                }
                while(this.zzbur.zzux() < v1);

                this.zzaw(v1);
                return;
            }
            else {
                throw zzvt.zzwo();
            }
        }

        do {
            arg3.add(Integer.valueOf(this.zzbur.zzut()));
            if(this.zzbur.zzuw()) {
                return;
            }

            v0_1 = this.zzbur.zzug();
        }
        while(v0_1 == this.tag);

        this.zzbut = v0_1;
    }

    public final int zzve() {
        if(this.zzbut != 0) {
            this.tag = this.zzbut;
            this.zzbut = 0;
        }
        else {
            this.tag = this.zzbur.zzug();
        }

        if(this.tag != 0) {
            if(this.tag == this.zzbus) {
            }
            else {
                return this.tag >>> 3;
            }
        }

        return 2147483647;
    }

    public final boolean zzvf() {
        if(!this.zzbur.zzuw()) {
            if(this.tag == this.zzbus) {
            }
            else {
                return this.zzbur.zzao(this.tag);
            }
        }

        return 0;
    }

    public final void zzw(List arg5) {
        int v1 = 2;
        if((arg5 instanceof zzwh)) {
            List v0 = arg5;
            int v5 = this.tag & 7;
            if(v5 != 0) {
                if(v5 == v1) {
                    v1 = this.zzbur.zzux() + this.zzbur.zzup();
                    do {
                        ((zzwh)v0).zzbg(this.zzbur.zzuu());
                    }
                    while(this.zzbur.zzux() < v1);

                    this.zzaw(v1);
                    return;
                }
                else {
                    throw zzvt.zzwo();
                }
            }

            do {
                ((zzwh)v0).zzbg(this.zzbur.zzuu());
                if(this.zzbur.zzuw()) {
                    return;
                }

                v5 = this.zzbur.zzug();
            }
            while(v5 == this.tag);

            this.zzbut = v5;
            return;
        }

        int v0_1 = this.tag & 7;
        if(v0_1 != 0) {
            if(v0_1 == v1) {
                v1 = this.zzbur.zzux() + this.zzbur.zzup();
                do {
                    arg5.add(Long.valueOf(this.zzbur.zzuu()));
                }
                while(this.zzbur.zzux() < v1);

                this.zzaw(v1);
                return;
            }
            else {
                throw zzvt.zzwo();
            }
        }

        do {
            arg5.add(Long.valueOf(this.zzbur.zzuu()));
            if(this.zzbur.zzuw()) {
                return;
            }

            v0_1 = this.zzbur.zzug();
        }
        while(v0_1 == this.tag);

        this.zzbut = v0_1;
    }
}

