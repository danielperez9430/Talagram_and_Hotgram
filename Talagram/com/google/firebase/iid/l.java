package com.google.firebase.iid;

import android.os.Bundle;

final class l extends m {
    l(int arg1, int arg2, Bundle arg3) {
        super(arg1, 2, arg3);
    }

    final void a(Bundle arg3) {
        if(arg3.getBoolean("ack", false)) {
            ((m)this).a(null);
            return;
        }

        ((m)this).a(new n(4, "Invalid response to one way request"));
    }

    final boolean a() {
        return 1;
    }
}

