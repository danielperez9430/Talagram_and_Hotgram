package com.f.a.c;

import org.json.JSONException;
import org.json.JSONObject;

class b {
    class a {
        public String a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;

        a() {
            super();
        }
    }

    static a a(String arg2) {
        a v0 = new a();
        try {
            JSONObject v2 = new JSONObject(arg2).getJSONObject("error");
            v0.f = com.f.a.e.b.b(v2, "charge");
            v0.c = com.f.a.e.b.b(v2, "code");
            v0.e = com.f.a.e.b.b(v2, "decline_code");
            v0.b = com.f.a.e.b.b(v2, "message");
            v0.d = com.f.a.e.b.b(v2, "param");
            v0.a = com.f.a.e.b.b(v2, "type");
        }
        catch(JSONException ) {
            v0.b = "An improperly formatted error response was found.";
        }

        return v0;
    }
}

