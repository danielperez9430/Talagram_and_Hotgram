package com.google.firebase.iid;

import com.google.firebase.components.b;
import com.google.firebase.components.d;

final class q implements d {
    static final d a;

    static {
        q.a = new q();
    }

    private q() {
        super();
    }

    public final Object a(b arg4) {
        return new FirebaseInstanceId(arg4.a(com.google.firebase.b.class), arg4.a(com.google.firebase.b.d.class));
    }
}

