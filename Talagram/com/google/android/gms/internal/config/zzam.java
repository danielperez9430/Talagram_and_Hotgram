package com.google.android.gms.internal.config;

import com.google.firebase.a.a;
import java.util.List;

public final class zzam implements Runnable {
    private final a zzam;
    private final List zzas;

    public zzam(a arg1, List arg2) {
        super();
        this.zzam = arg1;
        this.zzas = arg2;
    }

    public final void run() {
        if(this.zzam != null) {
            this.zzam.a(this.zzas);
        }
    }
}

