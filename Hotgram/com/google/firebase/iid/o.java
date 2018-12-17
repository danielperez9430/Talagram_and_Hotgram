package com.google.firebase.iid;

import android.os.Bundle;

final class o extends m {
    o(int arg1, int arg2, Bundle arg3) {
        super(arg1, 1, arg3);
    }

    final void a(Bundle arg2) {
        arg2 = arg2.getBundle("data");
        if(arg2 == null) {
            arg2 = Bundle.EMPTY;
        }

        ((m)this).a(arg2);
    }

    final boolean a() {
        return 0;
    }
}

