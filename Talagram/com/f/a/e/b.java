package com.f.a.e;

import org.json.JSONObject;

public class b {
    public static String a(JSONObject arg0, String arg1) {
        return b.a(arg0.getString(arg1));
    }

    static String a(String arg1) {
        if(("null".equals(arg1)) || ("".equals(arg1))) {
            arg1 = null;
        }

        return arg1;
    }

    public static String b(JSONObject arg0, String arg1) {
        return b.a(arg0.optString(arg1));
    }
}

