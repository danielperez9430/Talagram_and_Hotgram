package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map$Entry;
import java.util.Map;
import java.util.Set;

public final class zzwn extends LinkedHashMap {
    private boolean zzbtu;
    private static final zzwn zzcau;

    static {
        zzwn v0 = new zzwn();
        zzwn.zzcau = v0;
        v0.zzbtu = false;
    }

    private zzwn() {
        super();
        this.zzbtu = true;
    }

    private zzwn(Map arg1) {
        super(arg1);
        this.zzbtu = true;
    }

    public final void clear() {
        this.zzxc();
        super.clear();
    }

    public final Set entrySet() {
        if(this.isEmpty()) {
            return Collections.emptySet();
        }

        return super.entrySet();
    }

    public final boolean equals(Object arg7) {
        int v7;
        if((arg7 instanceof Map)) {
            if(this != (((zzwn)arg7))) {
                if(((Map)this).size() == ((Map)arg7).size()) {
                    Iterator v2 = ((Map)this).entrySet().iterator();
                    do {
                        if(v2.hasNext()) {
                            Object v3 = v2.next();
                            if(!((Map)arg7).containsKey(((Map$Entry)v3).getKey())) {
                            }
                            else {
                                Object v4 = ((Map$Entry)v3).getValue();
                                v3 = ((Map)arg7).get(((Map$Entry)v3).getKey());
                                boolean v3_1 = !(v4 instanceof byte[]) || !(v3 instanceof byte[]) ? v4.equals(v3) : Arrays.equals(((byte[])v4), ((byte[])v3));
                                if(v3_1) {
                                    continue;
                                }

                                break;
                            }
                        }
                        else {
                            goto label_31;
                        }

                        break;
                    }
                    while(true);
                }

                v7 = 0;
            }
            else {
            label_31:
                v7 = 1;
            }

            if(v7 == 0) {
                return 0;
            }

            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        Iterator v0 = ((Map)this).entrySet().iterator();
        int v1;
        for(v1 = 0; v0.hasNext(); v1 += zzwn.zzx(((Map$Entry)v2).getValue()) ^ zzwn.zzx(((Map$Entry)v2).getKey())) {
            Object v2 = v0.next();
        }

        return v1;
    }

    public final boolean isMutable() {
        return this.zzbtu;
    }

    public final Object put(Object arg1, Object arg2) {
        this.zzxc();
        zzvo.checkNotNull(arg1);
        zzvo.checkNotNull(arg2);
        return super.put(arg1, arg2);
    }

    public final void putAll(Map arg3) {
        this.zzxc();
        Iterator v0 = arg3.keySet().iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            zzvo.checkNotNull(v1);
            zzvo.checkNotNull(arg3.get(v1));
        }

        super.putAll(arg3);
    }

    public final Object remove(Object arg1) {
        this.zzxc();
        return super.remove(arg1);
    }

    public final void zza(zzwn arg2) {
        this.zzxc();
        if(!arg2.isEmpty()) {
            this.putAll(((Map)arg2));
        }
    }

    public final void zzsm() {
        this.zzbtu = false;
    }

    private static int zzx(Object arg1) {
        if((arg1 instanceof byte[])) {
            return zzvo.hashCode(((byte[])arg1));
        }

        if(!(arg1 instanceof zzvp)) {
            return arg1.hashCode();
        }

        throw new UnsupportedOperationException();
    }

    public static zzwn zzxa() {
        return zzwn.zzcau;
    }

    public final zzwn zzxb() {
        if(this.isEmpty()) {
            return new zzwn();
        }

        return new zzwn(((Map)this));
    }

    private final void zzxc() {
        if(this.zzbtu) {
            return;
        }

        throw new UnsupportedOperationException();
    }
}

