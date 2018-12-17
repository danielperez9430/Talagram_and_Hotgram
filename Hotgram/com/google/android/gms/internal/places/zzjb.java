package com.google.android.gms.internal.places;

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

class zzjb extends AbstractMap {
    private boolean zzpk;
    private final int zzxd;
    private List zzxe;
    private Map zzxf;
    private volatile zzjk zzxg;
    private Map zzxh;
    private volatile zzje zzxi;

    private zzjb(int arg1) {
        super();
        this.zzxd = arg1;
        this.zzxe = Collections.emptyList();
        this.zzxf = Collections.emptyMap();
        this.zzxh = Collections.emptyMap();
    }

    zzjb(int arg1, zzjc arg2) {
        this(arg1);
    }

    public void clear() {
        this.zzgj();
        if(!this.zzxe.isEmpty()) {
            this.zzxe.clear();
        }

        if(!this.zzxf.isEmpty()) {
            this.zzxf.clear();
        }
    }

    public boolean containsKey(Object arg2) {
        if(this.zzb(((Comparable)arg2)) < 0) {
            if(this.zzxf.containsKey(arg2)) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public Set entrySet() {
        if(this.zzxg == null) {
            this.zzxg = new zzjk(this, null);
        }

        return this.zzxg;
    }

    public boolean equals(Object arg8) {
        if(this == (((zzjb)arg8))) {
            return 1;
        }

        if(!(arg8 instanceof zzjb)) {
            return super.equals(arg8);
        }

        int v1 = this.size();
        if(v1 != ((zzjb)arg8).size()) {
            return 0;
        }

        int v2 = this.zzgg();
        if(v2 != ((zzjb)arg8).zzgg()) {
            return this.entrySet().equals(((zzjb)arg8).entrySet());
        }

        int v4;
        for(v4 = 0; v4 < v2; ++v4) {
            if(!this.zzbn(v4).equals(((zzjb)arg8).zzbn(v4))) {
                return 0;
            }
        }

        if(v2 != v1) {
            return this.zzxf.equals(((zzjb)arg8).zzxf);
        }

        return 1;
    }

    public Object get(Object arg2) {
        int v0 = this.zzb(((Comparable)arg2));
        if(v0 >= 0) {
            return this.zzxe.get(v0).getValue();
        }

        return this.zzxf.get(arg2);
    }

    public int hashCode() {
        int v0 = this.zzgg();
        int v1 = 0;
        int v2 = 0;
        while(v1 < v0) {
            v2 += this.zzxe.get(v1).hashCode();
            ++v1;
        }

        if(this.zzxf.size() > 0) {
            v2 += this.zzxf.hashCode();
        }

        return v2;
    }

    public final boolean isImmutable() {
        return this.zzpk;
    }

    public Object put(Object arg1, Object arg2) {
        return this.zzb(((Comparable)arg1), arg2);
    }

    public Object remove(Object arg2) {
        this.zzgj();
        int v0 = this.zzb(((Comparable)arg2));
        if(v0 >= 0) {
            return this.zzbo(v0);
        }

        if(this.zzxf.isEmpty()) {
            return null;
        }

        return this.zzxf.remove(arg2);
    }

    public int size() {
        return this.zzxe.size() + this.zzxf.size();
    }

    public final Object zzb(Comparable arg5, Object arg6) {
        this.zzgj();
        int v0 = this.zzb(arg5);
        if(v0 >= 0) {
            return this.zzxe.get(v0).setValue(arg6);
        }

        this.zzgj();
        if((this.zzxe.isEmpty()) && !(this.zzxe instanceof ArrayList)) {
            this.zzxe = new ArrayList(this.zzxd);
        }

        v0 = -(v0 + 1);
        if(v0 >= this.zzxd) {
            return this.zzgk().put(arg5, arg6);
        }

        if(this.zzxe.size() == this.zzxd) {
            Object v1 = this.zzxe.remove(this.zzxd - 1);
            this.zzgk().put(((zzji)v1).getKey(), ((zzji)v1).getValue());
        }

        this.zzxe.add(v0, new zzji(this, arg5, arg6));
        return null;
    }

    private final int zzb(Comparable arg5) {
        int v2;
        int v1;
        int v0 = this.zzxe.size() - 1;
        if(v0 >= 0) {
            v1 = arg5.compareTo(this.zzxe.get(v0).getKey());
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
            int v3 = arg5.compareTo(this.zzxe.get(v2).getKey());
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

    static Object zzb(zzjb arg0, int arg1) {
        return arg0.zzbo(arg1);
    }

    static void zzb(zzjb arg0) {
        arg0.zzgj();
    }

    public void zzbb() {
        if(!this.zzpk) {
            Map v0 = this.zzxf.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzxf);
            this.zzxf = v0;
            v0 = this.zzxh.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzxh);
            this.zzxh = v0;
            this.zzpk = true;
        }
    }

    static zzjb zzbm(int arg1) {
        return new zzjc(arg1);
    }

    public final Map$Entry zzbn(int arg2) {
        return this.zzxe.get(arg2);
    }

    private final Object zzbo(int arg5) {
        this.zzgj();
        Object v5 = this.zzxe.remove(arg5).getValue();
        if(!this.zzxf.isEmpty()) {
            Iterator v0 = this.zzgk().entrySet().iterator();
            this.zzxe.add(new zzji(this, v0.next()));
            v0.remove();
        }

        return v5;
    }

    static List zzc(zzjb arg0) {
        return arg0.zzxe;
    }

    static Map zzd(zzjb arg0) {
        return arg0.zzxf;
    }

    static Map zze(zzjb arg0) {
        return arg0.zzxh;
    }

    public final int zzgg() {
        return this.zzxe.size();
    }

    public final Iterable zzgh() {
        if(this.zzxf.isEmpty()) {
            return zzjf.zzgm();
        }

        return this.zzxf.entrySet();
    }

    final Set zzgi() {
        if(this.zzxi == null) {
            this.zzxi = new zzje(this, null);
        }

        return this.zzxi;
    }

    private final void zzgj() {
        if(!this.zzpk) {
            return;
        }

        throw new UnsupportedOperationException();
    }

    private final SortedMap zzgk() {
        this.zzgj();
        if((this.zzxf.isEmpty()) && !(this.zzxf instanceof TreeMap)) {
            this.zzxf = new TreeMap();
            this.zzxh = this.zzxf.descendingMap();
        }

        return this.zzxf;
    }
}

