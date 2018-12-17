package f;

import javax.annotation.Nullable;
import okhttp3.ac;
import okhttp3.ad;

public final class l {
    private final ac a;
    @Nullable private final Object b;
    @Nullable private final ad c;

    private l(ac arg1, @Nullable Object arg2, @Nullable ad arg3) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
    }

    public static l a(@Nullable Object arg2, ac arg3) {
        o.a(arg3, "rawResponse == null");
        if(arg3.c()) {
            return new l(arg3, arg2, null);
        }

        throw new IllegalArgumentException("rawResponse must be successful response");
    }

    public static l a(ad arg2, ac arg3) {
        o.a(arg2, "body == null");
        o.a(arg3, "rawResponse == null");
        if(!arg3.c()) {
            return new l(arg3, null, arg2);
        }

        throw new IllegalArgumentException("rawResponse should not be successful response");
    }

    public int a() {
        return this.a.b();
    }

    public boolean b() {
        return this.a.c();
    }

    @Nullable public Object c() {
        return this.b;
    }

    @Nullable public ad d() {
        return this.c;
    }

    public String toString() {
        return this.a.toString();
    }
}

