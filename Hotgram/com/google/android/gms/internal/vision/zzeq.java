package com.google.android.gms.internal.vision;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class zzeq extends AbstractMap {
    private boolean zzhv;
    private final int zzof;
    private List zzog;
    private Map zzoh;
    private volatile zzez zzoi;
    private Map zzoj;
    private volatile zzet zzok;

    private zzeq(int arg1) {
        super();
        this.zzof = arg1;
        this.zzog = Collections.emptyList();
        this.zzoh = Collections.emptyMap();
        this.zzoj = Collections.emptyMap();
    }

    zzeq(int arg1, zzer arg2) {
        this(arg1);
    }

    public void clear() {
        this.zzdo();
        if(!this.zzog.isEmpty()) {
            this.zzog.clear();
        }

        if(!this.zzoh.isEmpty()) {
            this.zzoh.clear();
        }
    }

    public boolean containsKey(Object arg2) {
        if(this.zza(((Comparable)arg2)) < 0) {
            if(this.zzoh.containsKey(arg2)) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public Set entrySet() {
        if(this.zzoi == null) {
            this.zzoi = new zzez(this, null);
        }

        return this.zzoi;
    }

    public boolean equals(Object arg8) {
        if(this == (((zzeq)arg8))) {
            return 1;
        }

        if(!(arg8 instanceof zzeq)) {
            return super.equals(arg8);
        }

        int v1 = this.size();
        if(v1 != ((zzeq)arg8).size()) {
            return 0;
        }

        int v2 = this.zzdl();
        if(v2 != ((zzeq)arg8).zzdl()) {
            return this.entrySet().equals(((zzeq)arg8).entrySet());
        }

        int v4;
        for(v4 = 0; v4 < v2; ++v4) {
            if(!this.zzan(v4).equals(((zzeq)arg8).zzan(v4))) {
                return 0;
            }
        }

        if(v2 != v1) {
            return this.zzoh.equals(((zzeq)arg8).zzoh);
        }

        return 1;
    }

    public Object get(Object arg2) {
        int v0 = this.zza(((Comparable)arg2));
        if(v0 >= 0) {
            return this.zzog.get(v0).getValue();
        }

        return this.zzoh.get(arg2);
    }

    public int hashCode() {
        int v0 = this.zzdl();
        int v1 = 0;
        int v2 = 0;
        while(v1 < v0) {
            v2 += this.zzog.get(v1).hashCode();
            ++v1;
        }

        if(this.zzoh.size() > 0) {
            v2 += this.zzoh.hashCode();
        }

        return v2;
    }

    public final boolean isImmutable() {
        return this.zzhv;
    }

    public Object put(Object arg1, Object arg2) {
        return this.zza(((Comparable)arg1), arg2);
    }

    public Object remove(Object arg2) {
        this.zzdo();
        int v0 = this.zza(((Comparable)arg2));
        if(v0 >= 0) {
            return this.zzao(v0);
        }

        if(this.zzoh.isEmpty()) {
            return null;
        }

        return this.zzoh.remove(arg2);
    }

    public int size() {
        return this.zzog.size() + this.zzoh.size();
    }

    public final Object zza(Comparable arg5, Object arg6) {
        this.zzdo();
        int v0 = this.zza(arg5);
        if(v0 >= 0) {
            return this.zzog.get(v0).setValue(arg6);
        }

        this.zzdo();
        if((this.zzog.isEmpty()) && !(this.zzog instanceof ArrayList)) {
            this.zzog = new ArrayList(this.zzof);
        }

        v0 = -(v0 + 1);
        if(v0 >= this.zzof) {
            return this.zzdp().put(arg5, arg6);
        }

        if(this.zzog.size() == this.zzof) {
            Object v1 = this.zzog.remove(this.zzof - 1);
            this.zzdp().put(((zzex)v1).getKey(), ((zzex)v1).getValue());
        }

        this.zzog.add(v0, new zzex(this, arg5, arg6));
        return null;
    }

    private final int zza(Comparable arg5) {
        int v2;
        int v1;
        int v0 = this.zzog.size() - 1;
        if(v0 >= 0) {
            v1 = arg5.compareTo(this.zzog.get(v0).getKey());
            if(v1 > 0) {
                return -(v0 + 2);
            }
            else if(v1 == 0) {
                return v0;
            }
        }

        for(v1 = 0; true; v1 = v2 + 1) {
        label_15:
            if(v1 > v0) {
                goto label_29;
            }

            v2 = (v1 + v0) / 2;
            int v3 = arg5.compareTo(this.zzog.get(v2).getKey());
            if(v3 < 0) {
                v0 = v2 - 1;
                goto label_15;
            }

            if(v3 <= 0) {
                return v2;
            }
        }

        return v2;
    label_29:
        return -(v1 + 1);
    }

    static Object zza(zzeq arg0, int arg1) {
        return arg0.zzao(arg1);
    }

    static void zza(zzeq arg0) {
        arg0.zzdo();
    }

    static zzeq zzam(int arg1) {
        return new zzer(arg1);
    }

    public final Map$Entry zzan(int arg2) {
        return this.zzog.get(arg2);
    }

    public void zzao() {
        if(!this.zzhv) {
            Map v0 = this.zzoh.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzoh);
            this.zzoh = v0;
            v0 = this.zzoj.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzoj);
            this.zzoj = v0;
            this.zzhv = true;
        }
    }

    private final Object zzao(int arg5) {
        this.zzdo();
        Object v5 = this.zzog.remove(arg5).getValue();
        if(!this.zzoh.isEmpty()) {
            Iterator v0 = this.zzdp().entrySet().iterator();
            this.zzog.add(new zzex(this, v0.next()));
            v0.remove();
        }

        return v5;
    }

    static List zzb(zzeq arg0) {
        return arg0.zzog;
    }

    static Map zzc(zzeq arg0) {
        return arg0.zzoh;
    }

    static Map zzd(zzeq arg0) {
        return arg0.zzoj;
    }

    public final int zzdl() {
        return this.zzog.size();
    }

    public final Iterable zzdm() {
        if(this.zzoh.isEmpty()) {
            return zzeu.zzdr();
        }

        return this.zzoh.entrySet();
    }

    final Set zzdn() {
        if(this.zzok == null) {
            this.zzok = new zzet(this, null);
        }

        return this.zzok;
    }

    private final void zzdo() {
        if(!this.zzhv) {
            return;
        }

        throw new UnsupportedOperationException();
    }

    private final SortedMap zzdp() {
        this.zzdo();
        if((this.zzoh.isEmpty()) && !(this.zzoh instanceof TreeMap)) {
            this.zzoh = new TreeMap();
            this.zzoj = this.zzoh.descendingMap();
        }

        return this.zzoh;
    }
}

