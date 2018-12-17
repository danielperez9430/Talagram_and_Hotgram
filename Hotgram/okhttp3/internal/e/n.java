package okhttp3.internal.e;

import java.io.IOException;

public final class n extends IOException {
    public final b a;

    public n(b arg3) {
        super("stream was reset: " + arg3);
        this.a = arg3;
    }
}

