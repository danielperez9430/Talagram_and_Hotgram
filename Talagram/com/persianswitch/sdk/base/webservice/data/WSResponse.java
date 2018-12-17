package com.persianswitch.sdk.base.webservice.data;

import android.content.Context;
import com.persianswitch.sdk.base.utils.strings.Jsonable$JsonParseException;
import com.persianswitch.sdk.base.utils.strings.Jsonable;
import com.persianswitch.sdk.base.utils.strings.StringUtils;
import com.persianswitch.sdk.base.webservice.StatusCode;
import org.json.JSONArray;
import org.json.JSONObject;

public class WSResponse {
    class JsonParser implements Jsonable {
        JsonParser() {
            super();
        }

        public JSONObject a(WSResponse arg5, String arg6) {
            JSONObject v0;
            try {
                v0 = new JSONObject(arg6);
                if(v0.has("hi")) {
                    arg5.k = v0.getInt("hi");
                }

                if(v0.has("tr")) {
                    arg5.a = v0.getLong("tr");
                }

                if(v0.has("st")) {
                    arg5.b = v0.getInt("st");
                }

                if(v0.has("op")) {
                    arg5.c = v0.getInt("op");
                }

                if(v0.has("sc")) {
                    arg5.d = v0.getInt("sc");
                }

                if(v0.has("ds")) {
                    arg5.e = v0.getString("ds");
                }

                if(v0.has("sm")) {
                    arg5.f = v0.getString("sm");
                }

                if(v0.has("ad")) {
                    arg5.g = v0.getString("ad");
                }

                if(v0.has("pi")) {
                    arg5.h = v0.getInt("pi");
                }

                if(v0.has("hd")) {
                    arg5.l = v0.getJSONObject("hd");
                }

                if(v0.has("ed")) {
                    JSONArray v6 = v0.getJSONArray("ed");
                    if(v6 != null) {
                        arg5.j = new String[v6.length()];
                        int v1;
                        for(v1 = 0; v1 < v6.length(); ++v1) {
                            arg5.j[v1] = v6.getString(v1);
                        }
                    }
                }

                if(v0.has("ej")) {
                    arg5.i = v0.getJSONObject("ej");
                }
            }
            catch(Exception v5) {
                throw new JsonParseException(v5.getMessage());
            }

            return v0;
        }
    }

    protected long a;
    protected int b;
    protected int c;
    protected int d;
    protected String e;
    protected String f;
    protected String g;
    protected int h;
    protected JSONObject i;
    protected String[] j;
    protected int k;
    protected JSONObject l;

    public WSResponse() {
        super();
    }

    public static WSResponse a(String arg2) {
        try {
            WSResponse v0 = new WSResponse();
            new JsonParser().a(v0, arg2);
            return v0;
        }
        catch(JsonParseException ) {
            return null;
        }
    }

    public int a() {
        return this.d;
    }

    public String a(Context arg2) {
        if(!StringUtils.a(this.e)) {
            return this.e;
        }

        return StatusCode.a(arg2, this.b());
    }

    public int b() {
        return this.b;
    }

    public StatusCode c() {
        return StatusCode.a(this.b());
    }

    public String d() {
        return this.e;
    }

    public int e() {
        return this.h;
    }

    public JSONObject f() {
        return this.l;
    }

    public JSONObject g() {
        return this.i;
    }

    public String[] h() {
        return this.j;
    }

    public String i() {
        return this.f;
    }
}

