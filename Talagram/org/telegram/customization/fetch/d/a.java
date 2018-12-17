package org.telegram.customization.fetch.d;

public final class a {
    private final String a;
    private final String b;

    public a(String arg2, String arg3) {
        super();
        if(arg2 != null) {
            if(!arg2.contains(":")) {
                if(arg3 == null) {
                    arg3 = "";
                }

                this.a = arg2;
                this.b = arg3;
                return;
            }

            throw new IllegalArgumentException("header may not contain \':\'");
        }

        throw new NullPointerException("header cannot be null");
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String toString() {
        return this.a + ":" + this.b;
    }
}

