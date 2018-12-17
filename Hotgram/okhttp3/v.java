package okhttp3;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

public final class v {
    private static final Pattern a;
    private static final Pattern b;
    private final String c;
    private final String d;
    private final String e;
    @Nullable private final String f;

    static {
        v.a = Pattern.compile("([a-zA-Z0-9-!#$%&\'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&\'*+.^_`{|}~]+)");
        v.b = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&\'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&\'*+.^_`{|}~]+)|\"([^\"]*)\"))?");
    }

    private v(String arg1, String arg2, String arg3, @Nullable String arg4) {
        super();
        this.c = arg1;
        this.d = arg2;
        this.e = arg3;
        this.f = arg4;
    }

    @Nullable public static v a(String arg9) {
        Matcher v0 = v.a.matcher(((CharSequence)arg9));
        v v2 = null;
        if(!v0.lookingAt()) {
            return v2;
        }

        String v3 = v0.group(1).toLowerCase(Locale.US);
        int v4 = 2;
        String v5 = v0.group(v4).toLowerCase(Locale.US);
        Matcher v6 = v.b.matcher(((CharSequence)arg9));
        int v0_1 = v0.end();
        String v7 = ((String)v2);
        while(v0_1 < arg9.length()) {
            v6.region(v0_1, arg9.length());
            if(!v6.lookingAt()) {
                return v2;
            }

            String v0_2 = v6.group(1);
            if(v0_2 != null) {
                if(!v0_2.equalsIgnoreCase("charset")) {
                }
                else {
                    v0_2 = v6.group(v4);
                    if(v0_2 == null) {
                        v0_2 = v6.group(3);
                    }
                    else if((v0_2.startsWith("\'")) && (v0_2.endsWith("\'")) && v0_2.length() > v4) {
                        v0_2 = v0_2.substring(1, v0_2.length() - 1);
                    }

                    if(v7 != null && !v0_2.equalsIgnoreCase(v7)) {
                        return v2;
                    }

                    v7 = v0_2;
                }
            }

            v0_1 = v6.end();
        }

        return new v(arg9, v3, v5, v7);
    }

    @Nullable public Charset a(@Nullable Charset arg2) {
        try {
            if(this.f != null) {
                arg2 = Charset.forName(this.f);
            }

            return arg2;
        }
        catch(IllegalArgumentException ) {
            return arg2;
        }
    }

    public String a() {
        return this.d;
    }

    @Nullable public Charset b() {
        return this.a(null);
    }

    public boolean equals(@Nullable Object arg2) {
        boolean v2 = !(arg2 instanceof v) || !((v)arg2).c.equals(this.c) ? false : true;
        return v2;
    }

    public int hashCode() {
        return this.c.hashCode();
    }

    public String toString() {
        return this.c;
    }
}

