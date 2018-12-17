package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map$Entry;

final class zzxn extends zzxm {
    zzxn(int arg2) {
        super(arg2, null);
    }

    public final void zzsm() {
        if(!((zzxm)this).isImmutable()) {
            int v0;
            for(v0 = 0; v0 < ((zzxm)this).zzxw(); ++v0) {
                Map$Entry v1 = ((zzxm)this).zzbu(v0);
                if(v1.getKey().zzvy()) {
                    v1.setValue(Collections.unmodifiableList(v1.getValue()));
                }
            }

            Iterator v0_1 = ((zzxm)this).zzxx().iterator();
            while(v0_1.hasNext()) {
                Object v1_1 = v0_1.next();
                if(!((Map$Entry)v1_1).getKey().zzvy()) {
                    continue;
                }

                ((Map$Entry)v1_1).setValue(Collections.unmodifiableList(((Map$Entry)v1_1).getValue()));
            }
        }

        super.zzsm();
    }
}

