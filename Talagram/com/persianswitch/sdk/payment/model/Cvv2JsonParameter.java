package com.persianswitch.sdk.payment.model;

import com.persianswitch.sdk.base.utils.Json;
import com.persianswitch.sdk.base.utils.strings.Jsonable$JsonWriteException;
import com.persianswitch.sdk.base.utils.strings.Jsonable;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public final class Cvv2JsonParameter {
    public class JsonParser implements Jsonable {
        public JsonParser() {
            super();
        }

        public JSONObject a(Cvv2JsonParameter arg4) {
            HashMap v0 = new HashMap(10);
            if(arg4.a != null) {
                ((Map)v0).put("hid", arg4.a);
            }

            if(arg4.b != null) {
                ((Map)v0).put("ccv", arg4.b);
            }

            if(arg4.c != null) {
                ((Map)v0).put("op", arg4.c);
            }

            if(arg4.d != null) {
                ((Map)v0).put("am", arg4.d);
            }

            if(arg4.e != null) {
                ((Map)v0).put("bnk", arg4.e);
            }

            if(arg4.f != null) {
                ((Map)v0).put("mrc", arg4.f);
            }

            if(arg4.g != null) {
                ((Map)v0).put("src", arg4.g);
            }

            if(arg4.h != null) {
                ((Map)v0).put("mno", arg4.h);
            }

            return Json.a(((Map)v0));
        }
    }

    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;

    public Cvv2JsonParameter() {
        super();
    }

    public String a() {
        try {
            return new JsonParser().a(this).toString();
        }
        catch(JsonWriteException ) {
            return "{}";
        }
    }
}

