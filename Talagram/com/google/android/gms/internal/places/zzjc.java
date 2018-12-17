package com.google.android.gms.internal.places;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map$Entry;

final class zzjc extends zzjb {
    zzjc(int arg2) {
        super(arg2, null);
    }

    public final void zzbb() {
        if(!((zzjb)this).isImmutable()) {
            int v0;
            for(v0 = 0; v0 < ((zzjb)this).zzgg(); ++v0) {
                Map$Entry v1 = ((zzjb)this).zzbn(v0);
                if(v1.getKey().zzdk()) {
                    v1.setValue(Collections.unmodifiableList(v1.getValue()));
                }
            }

            Iterator v0_1 = ((zzjb)this).zzgh().iterator();
            while(v0_1.hasNext()) {
                Object v1_1 = v0_1.next();
                if(!((Map$Entry)v1_1).getKey().zzdk()) {
                    continue;
                }

                ((Map$Entry)v1_1).setValue(Collections.unmodifiableList(((Map$Entry)v1_1).getValue()));
            }
        }

        super.zzbb();
    }
}

