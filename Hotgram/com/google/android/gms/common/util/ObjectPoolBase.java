package com.google.android.gms.common.util;

import java.util.ArrayList;

public abstract class ObjectPoolBase {
    private final ArrayList zzaag;
    private final int zzaah;

    public ObjectPoolBase(int arg2) {
        super();
        this.zzaag = new ArrayList(arg2);
        this.zzaah = arg2;
    }

    public final Object aquire() {
        ArrayList v0 = this.zzaag;
        __monitor_enter(v0);
        try {
            int v1_1 = this.zzaag.size();
            if(v1_1 > 0) {
                __monitor_exit(v0);
                return this.zzaag.remove(v1_1 - 1);
            }

            __monitor_exit(v0);
            return this.newObject();
        label_14:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_14;
        }

        throw v1;
    }

    protected boolean cleanUpObject(Object arg1) {
        return 1;
    }

    protected abstract Object newObject();

    public final boolean release(Object arg6) {
        ArrayList v0 = this.zzaag;
        __monitor_enter(v0);
        try {
            int v1 = this.zzaag.size();
            int v3;
            for(v3 = 0; true; ++v3) {
                if(v3 >= v1) {
                    goto label_25;
                }

                if(this.zzaag.get(v3) == arg6) {
                    break;
                }
            }

            String v6_1 = String.valueOf(arg6);
            StringBuilder v3_1 = new StringBuilder(String.valueOf(v6_1).length() + 25);
            v3_1.append("Object released already: ");
            v3_1.append(v6_1);
            throw new IllegalArgumentException(v3_1.toString());
        label_25:
            if(v1 < this.zzaah && (this.cleanUpObject(arg6))) {
                this.zzaag.add(arg6);
                __monitor_exit(v0);
                return 1;
            }

            __monitor_exit(v0);
            return 0;
        label_37:
            __monitor_exit(v0);
        }
        catch(Throwable v6) {
            goto label_37;
        }

        throw v6;
    }
}

