package com.google.android.gms.internal.clearcut;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map$Entry;

final class zzej extends zzei {
    zzej(int arg2) {
        super(arg2, null);
    }

    public final void zzv() {
        if(!((zzei)this).isImmutable()) {
            int v0;
            for(v0 = 0; v0 < ((zzei)this).zzdr(); ++v0) {
                Map$Entry v1 = ((zzei)this).zzak(v0);
                if(v1.getKey().zzaw()) {
                    v1.setValue(Collections.unmodifiableList(v1.getValue()));
                }
            }

            Iterator v0_1 = ((zzei)this).zzds().iterator();
            while(v0_1.hasNext()) {
                Object v1_1 = v0_1.next();
                if(!((Map$Entry)v1_1).getKey().zzaw()) {
                    continue;
                }

                ((Map$Entry)v1_1).setValue(Collections.unmodifiableList(((Map$Entry)v1_1).getValue()));
            }
        }

        super.zzv();
    }
}

