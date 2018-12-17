package com.google.android.gms.internal.measurement;

import java.util.Iterator;

final class zzyg implements Iterator {
    private Iterator zzccu;

    zzyg(zzye arg1) {
        this.zzcct = arg1;
        super();
        this.zzccu = zzye.zza(this.zzcct).iterator();
    }

    public final boolean hasNext() {
        return this.zzccu.hasNext();
    }

    public final Object next() {
        return this.zzccu.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

