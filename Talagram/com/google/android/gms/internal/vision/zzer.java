package com.google.android.gms.internal.vision;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map$Entry;

final class zzer extends zzeq {
    zzer(int arg2) {
        super(arg2, null);
    }

    public final void zzao() {
        if(!((zzeq)this).isImmutable()) {
            int v0;
            for(v0 = 0; v0 < ((zzeq)this).zzdl(); ++v0) {
                Map$Entry v1 = ((zzeq)this).zzan(v0);
                if(v1.getKey().zzbq()) {
                    v1.setValue(Collections.unmodifiableList(v1.getValue()));
                }
            }

            Iterator v0_1 = ((zzeq)this).zzdm().iterator();
            while(v0_1.hasNext()) {
                Object v1_1 = v0_1.next();
                if(!((Map$Entry)v1_1).getKey().zzbq()) {
                    continue;
                }

                ((Map$Entry)v1_1).setValue(Collections.unmodifiableList(((Map$Entry)v1_1).getValue()));
            }
        }

        super.zzao();
    }
}

