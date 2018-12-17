package net.hockeyapp.android.e;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.hockeyapp.android.c.b;
import net.hockeyapp.android.c.c;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class d {
    class net.hockeyapp.android.e.d$1 {
    }

    class a {
        static final d a;

        static {
            a.a = new d(null);
        }
    }

    private d() {
        super();
    }

    d(net.hockeyapp.android.e.d$1 arg1) {
        this();
    }

    public net.hockeyapp.android.c.d a(String arg31) {
        JSONObject v1_4;
        net.hockeyapp.android.c.d v1_3;
        net.hockeyapp.android.c.d v2_3;
        net.hockeyapp.android.c.a v1_2;
        ArrayList v0_4;
        String v25;
        String v24;
        int v22;
        int v23;
        JSONObject v18;
        JSONObject v17;
        JSONObject v2;
        String v0 = arg31;
        if(v0 == null) {
            return null;
        }

        try {
            v2 = new JSONObject(v0);
            JSONObject v3 = v2.getJSONObject("feedback");
            net.hockeyapp.android.c.a v4 = new net.hockeyapp.android.c.a();
            JSONArray v0_2 = v3.getJSONArray("messages");
            if(v0_2.length() > 0) {
                ArrayList v5 = new ArrayList();
                int v7 = 0;
                while(v7 < v0_2.length()) {
                    String v8 = v0_2.getJSONObject(v7).getString("subject");
                    String v9 = v0_2.getJSONObject(v7).getString("text");
                    String v10 = v0_2.getJSONObject(v7).getString("oem");
                    String v11 = v0_2.getJSONObject(v7).getString("model");
                    String v12 = v0_2.getJSONObject(v7).getString("os_version");
                    String v13 = v0_2.getJSONObject(v7).getString("created_at");
                    int v14 = v0_2.getJSONObject(v7).getInt("id");
                    String v1 = v0_2.getJSONObject(v7).getString("token");
                    int v6 = v0_2.getJSONObject(v7).getInt("via");
                    v17 = v2;
                    String v2_1 = v0_2.getJSONObject(v7).getString("user_string");
                    v18 = v3;
                    String v3_1 = v0_2.getJSONObject(v7).getString("clean_text");
                    net.hockeyapp.android.c.a v19 = v4;
                    String v4_1 = v0_2.getJSONObject(v7).getString("name");
                    ArrayList v20 = v5;
                    String v5_1 = v0_2.getJSONObject(v7).getString("app_id");
                    JSONArray v21 = v0_2;
                    v0_2 = v0_2.getJSONObject(v7).optJSONArray("attachments");
                    List v15 = Collections.emptyList();
                    if(v0_2 != null) {
                        ArrayList v15_1 = new ArrayList();
                        v23 = v6;
                        v22 = v7;
                        v7 = 0;
                        while(v7 < v0_2.length()) {
                            v24 = v2_1;
                            int v2_2 = v0_2.getJSONObject(v7).getInt("id");
                            v25 = v1;
                            int v1_1 = v0_2.getJSONObject(v7).getInt("feedback_message_id");
                            String v26 = v9;
                            String v6_1 = v0_2.getJSONObject(v7).getString("file_name");
                            String v27 = v8;
                            v8 = v0_2.getJSONObject(v7).getString("url");
                            String v28 = v12;
                            v9 = v0_2.getJSONObject(v7).getString("created_at");
                            JSONArray v29 = v0_2;
                            v0 = v0_2.getJSONObject(v7).getString("updated_at");
                            b v12_1 = new b();
                            v12_1.a(v2_2);
                            v12_1.b(v1_1);
                            v12_1.a(v6_1);
                            v12_1.b(v8);
                            v12_1.c(v9);
                            v12_1.d(v0);
                            ((List)v15_1).add(v12_1);
                            ++v7;
                            v2_1 = v24;
                            v1 = v25;
                            v9 = v26;
                            v8 = v27;
                            v12 = v28;
                            v0_2 = v29;
                        }

                        v25 = v1;
                        v24 = v2_1;
                    }
                    else {
                        v25 = v1;
                        v24 = v2_1;
                        v23 = v6;
                        v22 = v7;
                    }

                    c v0_3 = new c();
                    v0_3.k(v5_1);
                    v0_3.i(v3_1);
                    v0_3.f(v13);
                    v0_3.a(v14);
                    v0_3.d(v11);
                    v0_3.j(v4_1);
                    v0_3.c(v10);
                    v0_3.e(v12);
                    v0_3.a(v8);
                    v0_3.b(v9);
                    v0_3.g(v25);
                    v0_3.h(v24);
                    v0_3.b(v23);
                    v0_3.a(v15);
                    v20.add(v0_3);
                    v7 = v22 + 1;
                    v5 = v20;
                    v2 = v17;
                    v3 = v18;
                    v4 = v19;
                    v0_2 = v21;
                }

                v17 = v2;
                v18 = v3;
                v0_4 = v5;
                v1_2 = v4;
            }
            else {
                v17 = v2;
                v18 = v3;
                v1_2 = v4;
                v0_4 = null;
            }

            v1_2.a(v0_4);
        }
        catch(JSONException v0_1) {
            goto label_225;
        }

        try {
            v0 = "name";
            v2 = v18;
        }
        catch(JSONException v0_1) {
            v2 = v18;
            goto label_175;
        }

        try {
            v1_2.a(v2.getString(v0));
            goto label_177;
        }
        catch(JSONException v0_1) {
        }

        try {
        label_175:
            e.b("Failed to parse \"name\" in feedback response", ((Throwable)v0_1));
        }
        catch(JSONException v0_1) {
            goto label_225;
        }

        try {
        label_177:
            v1_2.b(v2.getString("email"));
        }
        catch(JSONException v0_1) {
            try {
                e.b("Failed to parse \"email\" in feedback response", ((Throwable)v0_1));
            }
            catch(JSONException v0_1) {
                goto label_225;
            }
        }

        try {
            v1_2.a(v2.getInt("id"));
        }
        catch(JSONException v0_1) {
            try {
                e.b("Failed to parse \"id\" in feedback response", ((Throwable)v0_1));
            }
            catch(JSONException v0_1) {
                goto label_225;
            }
        }

        try {
            v1_2.c(v2.getString("created_at"));
            goto label_198;
        }
        catch(JSONException v0_1) {
            try {
                e.b("Failed to parse \"created_at\" in feedback response", ((Throwable)v0_1));
            label_198:
                v2_3 = new net.hockeyapp.android.c.d();
            }
            catch(JSONException v0_1) {
            label_225:
                v1_3 = null;
                goto label_226;
            }
        }

        try {
            v2_3.a(v1_2);
        }
        catch(JSONException v0_1) {
            goto label_222;
        }

        try {
            v0 = "status";
            v1_4 = v17;
        }
        catch(JSONException v0_1) {
            v1_4 = v17;
            goto label_210;
        }

        try {
            v2_3.a(v1_4.getString(v0));
            goto label_212;
        }
        catch(JSONException v0_1) {
        }

        try {
        label_210:
            e.b("Failed to parse \"status\" in feedback response", ((Throwable)v0_1));
        }
        catch(JSONException v0_1) {
            goto label_222;
        }

        try {
        label_212:
            v2_3.b(v1_4.getString("token"));
            return v2_3;
        }
        catch(JSONException v0_1) {
            try {
                e.b("Failed to parse \"token\" in feedback response", ((Throwable)v0_1));
                return v2_3;
            }
            catch(JSONException v0_1) {
            label_222:
                v1_3 = v2_3;
            }
        }

    label_226:
        e.b("Failed to parse feedback response", ((Throwable)v0_1));
        net.hockeyapp.android.c.d v16 = v1_3;
        return v16;
    }

    public static d a() {
        return a.a;
    }
}

