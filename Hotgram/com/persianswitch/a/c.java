package com.persianswitch.a;

import com.persianswitch.a.a.e;
import java.io.Closeable;
import java.io.Flushable;

public final class c implements Closeable, Flushable {
    final e a;
    private final com.persianswitch.a.a.c b;

    public void close() {
        this.b.close();
    }

    public void flush() {
        this.b.flush();
    }
}

