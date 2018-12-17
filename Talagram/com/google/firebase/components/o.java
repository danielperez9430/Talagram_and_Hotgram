package com.google.firebase.components;

import com.google.firebase.b.a;
import java.util.Map$Entry;

final class o implements Runnable {
    private final Map$Entry a;
    private final a b;

    o(Map$Entry arg1, a arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public final void run() {
        n.a(this.a, this.b);
    }
}

