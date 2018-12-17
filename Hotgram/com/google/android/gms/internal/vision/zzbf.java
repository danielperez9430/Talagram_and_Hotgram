package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class zzbf implements zzdx {
    protected int zzgi;
    private static boolean zzgj = false;

    static {
    }

    public zzbf() {
        super();
        this.zzgi = 0;
    }

    public final byte[] toByteArray() {
        try {
            byte[] v0_1 = new byte[this.zzbl()];
            zzca v1 = zzca.zzd(v0_1);
            this.zzb(v1);
            v1.zzba();
            return v0_1;
        }
        catch(IOException v0) {
            String v3 = this.getClass().getName();
            StringBuilder v5 = new StringBuilder(String.valueOf(v3).length() + 62 + String.valueOf("byte array").length());
            v5.append("Serializing ");
            v5.append(v3);
            v5.append(" to a ");
            v5.append("byte array");
            v5.append(" threw an IOException (should never happen).");
            throw new RuntimeException(v5.toString(), ((Throwable)v0));
        }
    }

    protected static void zza(Iterable arg4, List arg5) {
        String v4_3;
        StringBuilder v2_1;
        int v4_2;
        Object v2;
        zzct.checkNotNull(arg4);
        int v1 = 37;
        if(!(arg4 instanceof zzdg)) {
            goto label_38;
        }

        List v4 = ((zzdg)arg4).zzck();
        List v0 = arg5;
        int v5 = arg5.size();
        Iterator v4_1 = v4.iterator();
        while(v4_1.hasNext()) {
            v2 = v4_1.next();
            if(v2 == null) {
                v4_2 = ((zzdg)v0).size() - v5;
                v2_1 = new StringBuilder(v1);
                v2_1.append("Element at index ");
                v2_1.append(v4_2);
                v2_1.append(" is null.");
                v4_3 = v2_1.toString();
                v1 = ((zzdg)v0).size() - 1;
                goto label_24;
            }

            if((v2 instanceof zzbo)) {
                ((zzdg)v0).zzc(((zzbo)v2));
                continue;
            }

            ((zzdg)v0).add(v2);
        }

        return;
    label_24:
        while(v1 >= v5) {
            ((zzdg)v0).remove(v1);
            --v1;
        }

        throw new NullPointerException(v4_3);
    label_38:
        if((arg4 instanceof zzej)) {
            arg5.addAll(((Collection)arg4));
            return;
        }

        if(((arg5 instanceof ArrayList)) && ((arg4 instanceof Collection))) {
            arg5.ensureCapacity(arg5.size() + arg4.size());
        }

        int v0_1 = arg5.size();
        v4_1 = arg4.iterator();
        while(v4_1.hasNext()) {
            v2 = v4_1.next();
            if(v2 == null) {
                v4_2 = arg5.size() - v0_1;
                v2_1 = new StringBuilder(v1);
                v2_1.append("Element at index ");
                v2_1.append(v4_2);
                v2_1.append(" is null.");
                v4_3 = v2_1.toString();
                v1 = arg5.size() - 1;
                goto label_70;
            }

            arg5.add(v2);
        }

        return;
    label_70:
        while(v1 >= v0_1) {
            arg5.remove(v1);
            --v1;
        }

        throw new NullPointerException(v4_3);
    }

    public final zzbo zzak() {
        try {
            zzbt v0_1 = zzbo.zzm(this.zzbl());
            this.zzb(v0_1.zzax());
            return v0_1.zzaw();
        }
        catch(IOException v0) {
            String v3 = this.getClass().getName();
            StringBuilder v5 = new StringBuilder(String.valueOf(v3).length() + 62 + String.valueOf("ByteString").length());
            v5.append("Serializing ");
            v5.append(v3);
            v5.append(" to a ");
            v5.append("ByteString");
            v5.append(" threw an IOException (should never happen).");
            throw new RuntimeException(v5.toString(), ((Throwable)v0));
        }
    }

    int zzal() {
        throw new UnsupportedOperationException();
    }

    void zzh(int arg1) {
        throw new UnsupportedOperationException();
    }
}

