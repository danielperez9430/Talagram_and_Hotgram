package com.google.ads.interactivemedia.v3.internal;

public abstract class kl {
    abstract class a extends kl {
        a() {
            super();
        }
    }

    abstract class b extends a {
        private final String a;

        b(String arg1) {
            super();
            this.a = kr.a(arg1);
        }

        public final String toString() {
            return this.a;
        }
    }

    final class c extends b {
        static final int a;
        static final c b;

        static {
            c.a = Integer.numberOfLeadingZeros(" 　\r\u0085   　 \u000B　   　 \t     \f 　 　　 \n 　".length() - 1);
            c.b = new c();
        }

        c() {
            super("CharMatcher.whitespace()");
        }

        public boolean a(char arg4) {
            boolean v4 = " 　\r\u0085   　 \u000B　   　 \t     \f 　 　　 \n 　".charAt(1682554634 * arg4 >>> c.a) == arg4 ? true : false;
            return v4;
        }
    }

    protected kl() {
        super();
    }

    public boolean a(CharSequence arg4) {
        int v0;
        for(v0 = arg4.length() - 1; v0 >= 0; --v0) {
            if(!this.a(arg4.charAt(v0))) {
                return 0;
            }
        }

        return 1;
    }

    public static kl a() {
        return c.b;
    }

    public abstract boolean a(char arg1);

    public String toString() {
        return super.toString();
    }
}

