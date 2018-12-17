package com.google.android.gms.internal.measurement;

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

class zzxm extends AbstractMap {
    private boolean zzbpo;
    private final int zzcca;
    private List zzccb;
    private Map zzccc;
    private volatile zzxv zzccd;
    private Map zzcce;
    private volatile zzxp zzccf;

    private zzxm(int arg1) {
        super();
        this.zzcca = arg1;
        this.zzccb = Collections.emptyList();
        this.zzccc = Collections.emptyMap();
        this.zzcce = Collections.emptyMap();
    }

    zzxm(int arg1, zzxn arg2) {
        this(arg1);
    }

    public void clear() {
        this.zzxz();
        if(!this.zzccb.isEmpty()) {
            this.zzccb.clear();
        }

        if(!this.zzccc.isEmpty()) {
            this.zzccc.clear();
        }
    }

    public boolean containsKey(Object arg2) {
        if(this.zza(((Comparable)arg2)) < 0) {
            if(this.zzccc.containsKey(arg2)) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public Set entrySet() {
        if(this.zzccd == null) {
            this.zzccd = new zzxv(this, null);
        }

        return this.zzccd;
    }

    public boolean equals(Object arg8) {
        if(this == (((zzxm)arg8))) {
            return 1;
        }

        if(!(arg8 instanceof zzxm)) {
            return super.equals(arg8);
        }

        int v1 = this.size();
        if(v1 != ((zzxm)arg8).size()) {
            return 0;
        }

        int v2 = this.zzxw();
        if(v2 != ((zzxm)arg8).zzxw()) {
            return this.entrySet().equals(((zzxm)arg8).entrySet());
        }

        int v4;
        for(v4 = 0; v4 < v2; ++v4) {
            if(!this.zzbu(v4).equals(((zzxm)arg8).zzbu(v4))) {
                return 0;
            }
        }

        if(v2 != v1) {
            return this.zzccc.equals(((zzxm)arg8).zzccc);
        }

        return 1;
    }

    public Object get(Object arg2) {
        int v0 = this.zza(((Comparable)arg2));
        if(v0 >= 0) {
            return this.zzccb.get(v0).getValue();
        }

        return this.zzccc.get(arg2);
    }

    public int hashCode() {
        int v0 = this.zzxw();
        int v1 = 0;
        int v2 = 0;
        while(v1 < v0) {
            v2 += this.zzccb.get(v1).hashCode();
            ++v1;
        }

        if(this.zzccc.size() > 0) {
            v2 += this.zzccc.hashCode();
        }

        return v2;
    }

    public final boolean isImmutable() {
        return this.zzbpo;
    }

    public Object put(Object arg1, Object arg2) {
        return this.zza(((Comparable)arg1), arg2);
    }

    public Object remove(Object arg2) {
        this.zzxz();
        int v0 = this.zza(((Comparable)arg2));
        if(v0 >= 0) {
            return this.zzbv(v0);
        }

        if(this.zzccc.isEmpty()) {
            return null;
        }

        return this.zzccc.remove(arg2);
    }

    public int size() {
        return this.zzccb.size() + this.zzccc.size();
    }

    public final Object zza(Comparable arg5, Object arg6) {
        this.zzxz();
        int v0 = this.zza(arg5);
        if(v0 >= 0) {
            return this.zzccb.get(v0).setValue(arg6);
        }

        this.zzxz();
        if((this.zzccb.isEmpty()) && !(this.zzccb instanceof ArrayList)) {
            this.zzccb = new ArrayList(this.zzcca);
        }

        v0 = -(v0 + 1);
        if(v0 >= this.zzcca) {
            return this.zzya().put(arg5, arg6);
        }

        if(this.zzccb.size() == this.zzcca) {
            Object v1 = this.zzccb.remove(this.zzcca - 1);
            this.zzya().put(((zzxt)v1).getKey(), ((zzxt)v1).getValue());
        }

        this.zzccb.add(v0, new zzxt(this, arg5, arg6));
        return null;
    }

    private final int zza(Comparable arg5) {
        int v2;
        int v1;
        int v0 = this.zzccb.size() - 1;
        if(v0 >= 0) {
            v1 = arg5.compareTo(this.zzccb.get(v0).getKey());
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
            int v3 = arg5.compareTo(this.zzccb.get(v2).getKey());
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

    static Object zza(zzxm arg0, int arg1) {
        return arg0.zzbv(arg1);
    }

    static void zza(zzxm arg0) {
        arg0.zzxz();
    }

    static List zzb(zzxm arg0) {
        return arg0.zzccb;
    }

    static zzxm zzbt(int arg1) {
        return new zzxn(arg1);
    }

    public final Map$Entry zzbu(int arg2) {
        return this.zzccb.get(arg2);
    }

    private final Object zzbv(int arg5) {
        this.zzxz();
        Object v5 = this.zzccb.remove(arg5).getValue();
        if(!this.zzccc.isEmpty()) {
            Iterator v0 = this.zzya().entrySet().iterator();
            this.zzccb.add(new zzxt(this, v0.next()));
            v0.remove();
        }

        return v5;
    }

    static Map zzc(zzxm arg0) {
        return arg0.zzccc;
    }

    static Map zzd(zzxm arg0) {
        return arg0.zzcce;
    }

    public void zzsm() {
        if(!this.zzbpo) {
            Map v0 = this.zzccc.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzccc);
            this.zzccc = v0;
            v0 = this.zzcce.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzcce);
            this.zzcce = v0;
            this.zzbpo = true;
        }
    }

    public final int zzxw() {
        return this.zzccb.size();
    }

    public final Iterable zzxx() {
        if(this.zzccc.isEmpty()) {
            return zzxq.zzyc();
        }

        return this.zzccc.entrySet();
    }

    final Set zzxy() {
        if(this.zzccf == null) {
            this.zzccf = new zzxp(this, null);
        }

        return this.zzccf;
    }

    private final void zzxz() {
        if(!this.zzbpo) {
            return;
        }

        throw new UnsupportedOperationException();
    }

    private final SortedMap zzya() {
        this.zzxz();
        if((this.zzccc.isEmpty()) && !(this.zzccc instanceof TreeMap)) {
            this.zzccc = new TreeMap();
            this.zzcce = this.zzccc.descendingMap();
        }

        return this.zzccc;
    }
}

