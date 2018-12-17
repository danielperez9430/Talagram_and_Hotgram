package com.crashlytics.android.a;

public class m extends d {
    private final String a;

    public m(String arg2) {
        super();
        if(arg2 != null) {
            this.a = this.b.a(arg2);
            return;
        }

        throw new NullPointerException("eventName must not be null");
    }

    String a() {
        return this.a;
    }

    public String toString() {
        return "{eventName:\"" + this.a + '\"' + ", customAttributes:" + this.c + "}";
    }
}

