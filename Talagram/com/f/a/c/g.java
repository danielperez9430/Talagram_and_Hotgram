package com.f.a.c;

import com.f.a.b.b;
import com.f.a.e.d;
import java.util.Date;
import org.json.JSONObject;

public class g {
    public static b a(String arg12) {
        JSONObject v0 = new JSONObject(arg12);
        return new b(com.f.a.e.b.a(v0, "id"), Boolean.valueOf(v0.getBoolean("livemode")).booleanValue(), new Date(Long.valueOf(v0.getLong("created")).longValue() * 1000), Boolean.valueOf(v0.getBoolean("used")), a.a(v0.getJSONObject("card")), d.f(com.f.a.e.b.a(v0, "type")));
    }
}

