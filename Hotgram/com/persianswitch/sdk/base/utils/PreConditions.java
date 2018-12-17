package com.persianswitch.sdk.base.utils;

public final class PreConditions {
    public PreConditions() {
        super();
    }

    public static void a(Object arg0, String arg1) {
        if(arg0 != null) {
            return;
        }

        throw new NullPointerException();
    }
}

