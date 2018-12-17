package com.persianswitch.a;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class t {
    private static final Pattern a;
    private static final Pattern b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;

    static {
        t.a = Pattern.compile("([a-zA-Z0-9-!#$%&\'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&\'*+.^_`{|}~]+)");
        t.b = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&\'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&\'*+.^_`{|}~]+)|\"([^\"]*)\"))?");
    }

    private t(String arg1, String arg2, String arg3, String arg4) {
        super();
        this.c = arg1;
        this.d = arg2;
        this.e = arg3;
        this.f = arg4;
    }

    public Charset a(Charset arg2) {
        if(this.f != null) {
            arg2 = Charset.forName(this.f);
        }

        return arg2;
    }

    public static t a(String arg9) {
        Matcher v0 = t.a.matcher(((CharSequence)arg9));
        t v2 = null;
        if(!v0.lookingAt()) {
            return v2;
        }

        String v3 = v0.group(1).toLowerCase(Locale.US);
        int v4 = 2;
        String v5 = v0.group(v4).toLowerCase(Locale.US);
        Matcher v6 = t.b.matcher(((CharSequence)arg9));
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
                    v0_2 = v6.group(v4) != null ? v6.group(v4) : v6.group(3);
                    if(v7 != null) {
                        if(v0_2.equalsIgnoreCase(v7)) {
                        }
                        else {
                            StringBuilder v1 = new StringBuilder();
                            v1.append("Multiple different charsets: ");
                            v1.append(arg9);
                            throw new IllegalArgumentException(v1.toString());
                        }
                    }

                    v7 = v0_2;
                }
            }

            v0_1 = v6.end();
        }

        return new t(arg9, v3, v5, v7);
    }

    public Charset a() {
        Charset v0 = this.f != null ? Charset.forName(this.f) : null;
        return v0;
    }

    public boolean equals(Object arg2) {
        boolean v2 = !(arg2 instanceof t) || !((t)arg2).c.equals(this.c) ? false : true;
        return v2;
    }

    public int hashCode() {
        return this.c.hashCode();
    }

    public String toString() {
        return this.c;
    }
}

