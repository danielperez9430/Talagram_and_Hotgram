package c.a.a.a.a.b;

import c.a.a.a.a.e.c;
import c.a.a.a.a.e.d;
import c.a.a.a.a.e.e;
import c.a.a.a.i;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class a {
    protected final i a;
    private static final Pattern b;
    private final String c;
    private final e d;
    private final c e;
    private final String f;

    static {
        a.b = Pattern.compile("http(s?)://[^\\/]+", 2);
    }

    public a(i arg1, String arg2, String arg3, e arg4, c arg5) {
        super();
        if(arg3 != null) {
            if(arg4 != null) {
                this.a = arg1;
                this.f = arg2;
                this.c = this.a(arg3);
                this.d = arg4;
                this.e = arg5;
                return;
            }

            throw new IllegalArgumentException("requestFactory must not be null.");
        }

        throw new IllegalArgumentException("url must not be null.");
    }

    private String a(String arg2) {
        if(!c.a.a.a.a.b.i.d(this.f)) {
            arg2 = a.b.matcher(((CharSequence)arg2)).replaceFirst(this.f);
        }

        return arg2;
    }

    protected d a(Map arg4) {
        d v4 = this.d.a(this.e, this.a(), arg4).a(false).a(10000);
        StringBuilder v1 = new StringBuilder();
        v1.append("Crashlytics Android SDK/");
        v1.append(this.a.a());
        return v4.a("User-Agent", v1.toString()).a("X-CRASHLYTICS-DEVELOPER-TOKEN", "470fa2b4ae81cd56ecbcda9735803434cec591fa");
    }

    protected String a() {
        return this.c;
    }

    protected d b() {
        return this.a(Collections.emptyMap());
    }
}

