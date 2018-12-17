package com.google.android.gms.internal.clearcut;

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

class zzei extends AbstractMap {
    private boolean zzgu;
    private final int zzol;
    private List zzom;
    private Map zzon;
    private volatile zzer zzoo;
    private Map zzop;
    private volatile zzel zzoq;

    private zzei(int arg1) {
        super();
        this.zzol = arg1;
        this.zzom = Collections.emptyList();
        this.zzon = Collections.emptyMap();
        this.zzop = Collections.emptyMap();
    }

    zzei(int arg1, zzej arg2) {
        this(arg1);
    }

    public void clear() {
        this.zzdu();
        if(!this.zzom.isEmpty()) {
            this.zzom.clear();
        }

        if(!this.zzon.isEmpty()) {
            this.zzon.clear();
        }
    }

    public boolean containsKey(Object arg2) {
        if(this.zza(((Comparable)arg2)) < 0) {
            if(this.zzon.containsKey(arg2)) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public Set entrySet() {
        if(this.zzoo == null) {
            this.zzoo = new zzer(this, null);
        }

        return this.zzoo;
    }

    public boolean equals(Object arg8) {
        if(this == (((zzei)arg8))) {
            return 1;
        }

        if(!(arg8 instanceof zzei)) {
            return super.equals(arg8);
        }

        int v1 = this.size();
        if(v1 != ((zzei)arg8).size()) {
            return 0;
        }

        int v2 = this.zzdr();
        if(v2 != ((zzei)arg8).zzdr()) {
            return this.entrySet().equals(((zzei)arg8).entrySet());
        }

        int v4;
        for(v4 = 0; v4 < v2; ++v4) {
            if(!this.zzak(v4).equals(((zzei)arg8).zzak(v4))) {
                return 0;
            }
        }

        if(v2 != v1) {
            return this.zzon.equals(((zzei)arg8).zzon);
        }

        return 1;
    }

    public Object get(Object arg2) {
        int v0 = this.zza(((Comparable)arg2));
        if(v0 >= 0) {
            return this.zzom.get(v0).getValue();
        }

        return this.zzon.get(arg2);
    }

    public int hashCode() {
        int v0 = this.zzdr();
        int v1 = 0;
        int v2 = 0;
        while(v1 < v0) {
            v2 += this.zzom.get(v1).hashCode();
            ++v1;
        }

        if(this.zzon.size() > 0) {
            v2 += this.zzon.hashCode();
        }

        return v2;
    }

    public final boolean isImmutable() {
        return this.zzgu;
    }

    public Object put(Object arg1, Object arg2) {
        return this.zza(((Comparable)arg1), arg2);
    }

    public Object remove(Object arg2) {
        this.zzdu();
        int v0 = this.zza(((Comparable)arg2));
        if(v0 >= 0) {
            return this.zzal(v0);
        }

        if(this.zzon.isEmpty()) {
            return null;
        }

        return this.zzon.remove(arg2);
    }

    public int size() {
        return this.zzom.size() + this.zzon.size();
    }

    public final Object zza(Comparable arg5, Object arg6) {
        this.zzdu();
        int v0 = this.zza(arg5);
        if(v0 >= 0) {
            return this.zzom.get(v0).setValue(arg6);
        }

        this.zzdu();
        if((this.zzom.isEmpty()) && !(this.zzom instanceof ArrayList)) {
            this.zzom = new ArrayList(this.zzol);
        }

        v0 = -(v0 + 1);
        if(v0 >= this.zzol) {
            return this.zzdv().put(arg5, arg6);
        }

        if(this.zzom.size() == this.zzol) {
            Object v1 = this.zzom.remove(this.zzol - 1);
            this.zzdv().put(((zzep)v1).getKey(), ((zzep)v1).getValue());
        }

        this.zzom.add(v0, new zzep(this, arg5, arg6));
        return null;
    }

    private final int zza(Comparable arg5) {
        int v2;
        int v1;
        int v0 = this.zzom.size() - 1;
        if(v0 >= 0) {
            v1 = arg5.compareTo(this.zzom.get(v0).getKey());
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
            int v3 = arg5.compareTo(this.zzom.get(v2).getKey());
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

    static Object zza(zzei arg0, int arg1) {
        return arg0.zzal(arg1);
    }

    static void zza(zzei arg0) {
        arg0.zzdu();
    }

    static zzei zzaj(int arg1) {
        return new zzej(arg1);
    }

    public final Map$Entry zzak(int arg2) {
        return this.zzom.get(arg2);
    }

    private final Object zzal(int arg5) {
        this.zzdu();
        Object v5 = this.zzom.remove(arg5).getValue();
        if(!this.zzon.isEmpty()) {
            Iterator v0 = this.zzdv().entrySet().iterator();
            this.zzom.add(new zzep(this, v0.next()));
            v0.remove();
        }

        return v5;
    }

    static List zzb(zzei arg0) {
        return arg0.zzom;
    }

    static Map zzc(zzei arg0) {
        return arg0.zzon;
    }

    static Map zzd(zzei arg0) {
        return arg0.zzop;
    }

    public final int zzdr() {
        return this.zzom.size();
    }

    public final Iterable zzds() {
        if(this.zzon.isEmpty()) {
            return zzem.zzdx();
        }

        return this.zzon.entrySet();
    }

    final Set zzdt() {
        if(this.zzoq == null) {
            this.zzoq = new zzel(this, null);
        }

        return this.zzoq;
    }

    private final void zzdu() {
        if(!this.zzgu) {
            return;
        }

        throw new UnsupportedOperationException();
    }

    private final SortedMap zzdv() {
        this.zzdu();
        if((this.zzon.isEmpty()) && !(this.zzon instanceof TreeMap)) {
            this.zzon = new TreeMap();
            this.zzop = this.zzon.descendingMap();
        }

        return this.zzon;
    }

    public void zzv() {
        if(!this.zzgu) {
            Map v0 = this.zzon.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzon);
            this.zzon = v0;
            v0 = this.zzop.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzop);
            this.zzop = v0;
            this.zzgu = true;
        }
    }
}

