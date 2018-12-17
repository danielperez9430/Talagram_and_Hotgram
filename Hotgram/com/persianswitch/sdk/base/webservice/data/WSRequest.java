package com.persianswitch.sdk.base.webservice.data;

import android.content.Context;
import android.util.Base64;
import com.persianswitch.sdk.base.BaseSetting;
import com.persianswitch.sdk.base.Config;
import com.persianswitch.sdk.base.security.DeviceInfo;
import com.persianswitch.sdk.base.utils.CertificateUtils;
import com.persianswitch.sdk.base.utils.strings.Jsonable$JsonWriteException;
import com.persianswitch.sdk.base.utils.strings.Jsonable;
import com.persianswitch.sdk.payment.SDKConfig;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class WSRequest {
    class JsonParser implements Jsonable {
        JsonParser() {
            super();
        }

        public JSONObject a(WSRequest arg5) {
            try {
                HashMap v0 = new HashMap();
                ((Map)v0).put("se", "");
                ((Map)v0).put("ap", Long.valueOf(WSRequest.a(arg5)));
                if(WSRequest.b(arg5) != null) {
                    ((Map)v0).put("at", WSRequest.b(arg5));
                }

                ((Map)v0).put("op", Integer.valueOf(WSRequest.c(arg5)));
                ((Map)v0).put("tr", Long.valueOf(WSRequest.d(arg5)));
                if(WSRequest.e(arg5) != null) {
                    ((Map)v0).put("av", WSRequest.e(arg5));
                }

                if(WSRequest.f(arg5) != null) {
                    ((Map)v0).put("te", WSRequest.f(arg5));
                }

                if(WSRequest.g(arg5) != null) {
                    ((Map)v0).put("mo", WSRequest.g(arg5));
                }

                ((Map)v0).put("hi", Integer.valueOf(WSRequest.h(arg5)));
                if(WSRequest.i(arg5) != null) {
                    ((Map)v0).put("de", WSRequest.i(arg5));
                }

                ((Map)v0).put("pn", arg5.d());
                ((Map)v0).put("kd", arg5.e());
                ((Map)v0).put("ka", arg5.f());
                if(WSRequest.j(arg5) != null) {
                    ((Map)v0).put("hd", WSRequest.j(arg5));
                }

                if(WSRequest.k(arg5) != null) {
                    ((Map)v0).put("ed", new JSONArray(Arrays.asList(WSRequest.k(arg5))));
                }

                if(WSRequest.l(arg5) != null) {
                    ((Map)v0).put("ej", WSRequest.l(arg5));
                }

                return new JSONObject(((Map)v0));
            }
            catch(Exception v5) {
                throw new JsonWriteException(v5.getMessage());
            }
        }
    }

    private final String a;
    private long b;
    private long c;
    private String d;
    private String e;
    private String f;
    private String g;
    private int h;
    private String i;
    private String j;
    private String k;
    private int l;
    private JSONObject m;
    @Deprecated private String[] n;
    private JSONObject o;

    public WSRequest() {
        super();
        this.a = "SHA-1";
        this.n = new String[0];
        this.o = new JSONObject();
    }

    public void a(long arg1) {
        this.b = arg1;
    }

    public String a(Config arg6) {
        return String.format(Locale.US, "%s/%s/%d/%d", arg6.a(), this.a("1", "6", true), Long.valueOf(this.b()), Integer.valueOf(this.g()));
    }

    static long a(WSRequest arg2) {
        return arg2.c;
    }

    public static WSRequest a(Context arg3, Config arg4, int arg5) {
        WSRequest v0 = new WSRequest();
        v0.b(BaseSetting.c(arg3));
        v0.a(BaseSetting.f(arg3));
        v0.c(DeviceInfo.a(arg3, arg4));
        v0.d(BaseSetting.h(arg3));
        v0.a(arg5);
        v0.b(BaseSetting.g(arg3));
        v0.b(Base64.encodeToString(DeviceInfo.b(arg3).getBytes(), 2));
        v0.f(SDKConfig.a(arg3));
        v0.g(CertificateUtils.a(arg3));
        return v0;
    }

    public void a(String arg1) {
        this.d = arg1;
    }

    public void a(int arg1) {
        this.h = arg1;
    }

    public long a() {
        return this.b;
    }

    public String a(String arg5, String arg6, boolean arg7) {
        int v6 = Integer.parseInt(arg6);
        if(arg7) {
            v6 += 10000;
        }

        return String.format(Locale.US, "%s/%s/%d", this.i(), arg5, Integer.valueOf(v6));
    }

    public void a(JSONObject arg1) {
        this.m = arg1;
    }

    @Deprecated public void a(String[] arg1) {
        this.n = arg1;
    }

    public void b(long arg1) {
        this.c = arg1;
    }

    public void b(int arg1) {
        this.l = arg1;
    }

    public void b(String arg1) {
        this.e = arg1;
    }

    static String b(WSRequest arg0) {
        return arg0.d;
    }

    public long b() {
        return this.c;
    }

    public void b(JSONObject arg1) {
        this.o = arg1;
    }

    public int c() {
        return this.h;
    }

    public void c(String arg1) {
        this.f = arg1;
    }

    static int c(WSRequest arg0) {
        return arg0.h;
    }

    public void d(String arg1) {
        this.g = arg1;
    }

    static long d(WSRequest arg2) {
        return arg2.b;
    }

    public String d() {
        return this.j;
    }

    public void e(String arg1) {
        this.i = arg1;
    }

    static String e(WSRequest arg0) {
        return arg0.f;
    }

    public String e() {
        return this.k;
    }

    public void f(String arg1) {
        this.j = arg1;
    }

    static String f(WSRequest arg0) {
        return arg0.i;
    }

    public String f() {
        return "SHA-1";
    }

    public void g(String arg1) {
        this.k = arg1;
    }

    static String g(WSRequest arg0) {
        return arg0.g;
    }

    public int g() {
        return this.l;
    }

    public String h() {
        try {
            return new JsonParser().a(this).toString();
        }
        catch(Exception ) {
            return "";
        }
    }

    static int h(WSRequest arg0) {
        return arg0.l;
    }

    static String i(WSRequest arg0) {
        return arg0.e;
    }

    protected String i() {
        return "sdk/w01";
    }

    static JSONObject j(WSRequest arg0) {
        return arg0.m;
    }

    static String[] k(WSRequest arg0) {
        return arg0.n;
    }

    static JSONObject l(WSRequest arg0) {
        return arg0.o;
    }
}

