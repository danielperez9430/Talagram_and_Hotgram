package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map$Entry;
import java.util.Map;
import java.util.Set;

public final class zzdr extends LinkedHashMap {
    private boolean zzgl;
    private static final zzdr zzmz;

    static {
        zzdr v0 = new zzdr();
        zzdr.zzmz = v0;
        v0.zzgl = false;
    }

    private zzdr() {
        super();
        this.zzgl = true;
    }

    private zzdr(Map arg1) {
        super(arg1);
        this.zzgl = true;
    }

    public final void clear() {
        this.zzcr();
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
            if(this != (((zzdr)arg7))) {
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
        for(v1 = 0; v0.hasNext(); v1 += zzdr.zzg(((Map$Entry)v2).getValue()) ^ zzdr.zzg(((Map$Entry)v2).getKey())) {
            Object v2 = v0.next();
        }

        return v1;
    }

    public final boolean isMutable() {
        return this.zzgl;
    }

    public final Object put(Object arg1, Object arg2) {
        this.zzcr();
        zzct.checkNotNull(arg1);
        zzct.checkNotNull(arg2);
        return super.put(arg1, arg2);
    }

    public final void putAll(Map arg3) {
        this.zzcr();
        Iterator v0 = arg3.keySet().iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            zzct.checkNotNull(v1);
            zzct.checkNotNull(arg3.get(v1));
        }

        super.putAll(arg3);
    }

    public final Object remove(Object arg1) {
        this.zzcr();
        return super.remove(arg1);
    }

    public final void zza(zzdr arg2) {
        this.zzcr();
        if(!arg2.isEmpty()) {
            this.putAll(((Map)arg2));
        }
    }

    public final void zzao() {
        this.zzgl = false;
    }

    public static zzdr zzcp() {
        return zzdr.zzmz;
    }

    public final zzdr zzcq() {
        if(this.isEmpty()) {
            return new zzdr();
        }

        return new zzdr(((Map)this));
    }

    private final void zzcr() {
        if(this.zzgl) {
            return;
        }

        throw new UnsupportedOperationException();
    }

    private static int zzg(Object arg1) {
        if((arg1 instanceof byte[])) {
            return zzct.hashCode(((byte[])arg1));
        }

        if(!(arg1 instanceof zzcu)) {
            return arg1.hashCode();
        }

        throw new UnsupportedOperationException();
    }
}

