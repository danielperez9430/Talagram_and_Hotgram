package com.google.firebase.iid;

import android.support.annotation.Keep;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.b.d;
import com.google.firebase.b;
import com.google.firebase.components.e;
import com.google.firebase.components.f;
import java.util.Arrays;
import java.util.List;

@Keep @KeepForSdk public final class Registrar implements e {
    final class a implements com.google.firebase.iid.a.a {
        private final FirebaseInstanceId a;

        public a(FirebaseInstanceId arg1) {
            super();
            this.a = arg1;
        }
    }

    public Registrar() {
        super();
    }

    @Keep public final List getComponents() {
        return Arrays.asList(new com.google.firebase.components.a[]{com.google.firebase.components.a.a(FirebaseInstanceId.class).a(f.a(b.class)).a(f.a(d.class)).a(q.a).a().c(), com.google.firebase.components.a.a(com.google.firebase.iid.a.a.class).a(f.a(FirebaseInstanceId.class)).a(r.a).c()});
    }
}

