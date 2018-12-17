package com.google.android.gms.internal.places;

import java.util.Map$Entry;

final class zzji implements Comparable, Map$Entry {
    private Object value;
    private final Comparable zzxn;

    zzji(zzjb arg2, Map$Entry arg3) {
        this(arg2, arg3.getKey(), arg3.getValue());
    }

    zzji(zzjb arg1, Comparable arg2, Object arg3) {
        this.zzxk = arg1;
        super();
        this.zzxn = arg2;
        this.value = arg3;
    }

    public final int compareTo(Object arg2) {
        return this.getKey().compareTo(((zzji)arg2).getKey());
    }

    private static boolean equals(Object arg0, Object arg1) {
        if(arg0 == null) {
            if(arg1 == null) {
                return 1;
            }

            return 0;
        }

        return arg0.equals(arg1);
    }

    public final boolean equals(Object arg5) {
        if((((zzji)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof Map$Entry)) {
            return 0;
        }

        if((zzji.equals(this.zzxn, ((Map$Entry)arg5).getKey())) && (zzji.equals(this.value, ((Map$Entry)arg5).getValue()))) {
            return 1;
        }

        return 0;
    }

    public final Object getKey() {
        return this.zzxn;
    }

    public final Object getValue() {
        return this.value;
    }

    public final int hashCode() {
        int v1 = 0;
        int v0 = this.zzxn == null ? 0 : this.zzxn.hashCode();
        if(this.value == null) {
        }
        else {
            v1 = this.value.hashCode();
        }

        return v0 ^ v1;
    }

    public final Object setValue(Object arg2) {
        zzjb.zzb(this.zzxk);
        Object v0 = this.value;
        this.value = arg2;
        return v0;
    }

    public final String toString() {
        String v0 = String.valueOf(this.zzxn);
        String v1 = String.valueOf(this.value);
        StringBuilder v3 = new StringBuilder(String.valueOf(v0).length() + 1 + String.valueOf(v1).length());
        v3.append(v0);
        v3.append("=");
        v3.append(v1);
        return v3.toString();
    }
}

