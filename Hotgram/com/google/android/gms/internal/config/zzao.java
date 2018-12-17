package com.google.android.gms.internal.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public final class zzao {
    private Map zzaw;
    private long zzax;
    private List zzs;

    public zzao(Map arg1, long arg2, List arg4) {
        super();
        this.zzaw = arg1;
        this.zzax = arg2;
        this.zzs = arg4;
    }

    public final long getTimestamp() {
        return this.zzax;
    }

    public final void setTimestamp(long arg1) {
        this.zzax = arg1;
    }

    public final void zza(Map arg2, String arg3) {
        if(this.zzaw == null) {
            this.zzaw = new HashMap();
        }

        this.zzaw.put(arg3, arg2);
    }

    public final boolean zzb(String arg3) {
        if(arg3 == null) {
            return 0;
        }

        if((this.zzq()) && this.zzaw.get(arg3) != null && !this.zzaw.get(arg3).isEmpty()) {
            return 1;
        }

        return 0;
    }

    public final boolean zzb(String arg2, String arg3) {
        if((this.zzq()) && (this.zzb(arg3)) && this.zzc(arg2, arg3) != null) {
            return 1;
        }

        return 0;
    }

    public final byte[] zzc(String arg2, String arg3) {
        if(arg2 != null) {
            if(!this.zzb(arg3)) {
            }
            else {
                return this.zzaw.get(arg3).get(arg2);
            }
        }

        return null;
    }

    public final Set zzd(String arg4, String arg5) {
        TreeSet v0 = new TreeSet();
        if(!this.zzb(arg5)) {
            return ((Set)v0);
        }

        if(arg4 != null) {
            if(arg4.isEmpty()) {
            }
            else {
                Iterator v5 = this.zzaw.get(arg5).keySet().iterator();
                while(v5.hasNext()) {
                    Object v1 = v5.next();
                    if(!((String)v1).startsWith(arg4)) {
                        continue;
                    }

                    ((Set)v0).add(v1);
                }

                return ((Set)v0);
            }
        }

        return this.zzaw.get(arg5).keySet();
    }

    public final List zzg() {
        return this.zzs;
    }

    public final Map zzp() {
        return this.zzaw;
    }

    public final boolean zzq() {
        if(this.zzaw != null && !this.zzaw.isEmpty()) {
            return 1;
        }

        return 0;
    }
}

