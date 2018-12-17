package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.GmsClientEventManager$GmsClientEventState;

final class zzaw implements GmsClientEventState {
    zzaw(zzav arg1) {
        this.zzit = arg1;
        super();
    }

    public final Bundle getConnectionHint() {
        return null;
    }

    public final boolean isConnected() {
        return this.zzit.isConnected();
    }
}

