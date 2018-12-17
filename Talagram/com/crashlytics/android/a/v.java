package com.crashlytics.android.a;

public abstract class v extends d {
    final c d;

    abstract String a();

    public String toString() {
        return "{type:\"" + this.a() + '\"' + ", predefinedAttributes:" + this.d + ", customAttributes:" + this.c + "}";
    }
}

