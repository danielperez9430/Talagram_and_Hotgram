package com.e.a;

import android.os.Handler;

class f {
    final Handler a;

    void a(b arg4) {
        this.a.sendMessage(this.a.obtainMessage(4, arg4));
    }

    void a(a arg4) {
        this.a.sendMessage(this.a.obtainMessage(1, arg4));
    }

    void b(b arg4) {
        this.a.sendMessageDelayed(this.a.obtainMessage(5, arg4), 500);
    }

    void b(a arg4) {
        this.a.sendMessage(this.a.obtainMessage(2, arg4));
    }

    void c(b arg4) {
        this.a.sendMessage(this.a.obtainMessage(6, arg4));
    }
}

