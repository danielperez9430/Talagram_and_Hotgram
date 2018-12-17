package okhttp3;

import java.io.Closeable;
import java.io.Flushable;
import okhttp3.internal.a.d;
import okhttp3.internal.a.e;

public final class c implements Closeable, Flushable {
    final e a;
    final d b;

    public void close() {
        this.b.close();
    }

    public void flush() {
        this.b.flush();
    }
}

