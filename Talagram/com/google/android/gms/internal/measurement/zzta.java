package com.google.android.gms.internal.measurement;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

final class zzta extends zzsx {
    private final zzsy zzbrz;

    zzta() {
        super();
        this.zzbrz = new zzsy();
    }

    public final void zza(Throwable arg4, PrintStream arg5) {
        arg4.printStackTrace(arg5);
        List v4 = this.zzbrz.zza(arg4, false);
        if(v4 == null) {
            return;
        }

        __monitor_enter(v4);
        try {
            Iterator v0 = v4.iterator();
            while(v0.hasNext()) {
                Object v1 = v0.next();
                arg5.print("Suppressed: ");
                ((Throwable)v1).printStackTrace(arg5);
            }

            __monitor_exit(v4);
            return;
        label_18:
            __monitor_exit(v4);
        }
        catch(Throwable v5) {
            goto label_18;
        }

        throw v5;
    }
}

