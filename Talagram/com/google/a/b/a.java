package com.google.a.b;

public final class a {
    public static Object a(Object arg0) {
        if(arg0 != null) {
            return arg0;
        }

        throw new NullPointerException();
    }

    public static void a(boolean arg0) {
        if(arg0) {
            return;
        }

        throw new IllegalArgumentException();
    }
}

