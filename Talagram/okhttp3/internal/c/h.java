package okhttp3.internal.c;

import e.e;
import javax.annotation.Nullable;
import okhttp3.ad;
import okhttp3.v;

public final class h extends ad {
    @Nullable private final String a;
    private final long b;
    private final e c;

    public h(@Nullable String arg1, long arg2, e arg4) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg4;
    }

    public v a() {
        v v0 = this.a != null ? v.a(this.a) : null;
        return v0;
    }

    public long b() {
        return this.b;
    }

    public e c() {
        return this.c;
    }
}

