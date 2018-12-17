package com.persianswitch.sdk.payment.model.res;

import com.persianswitch.sdk.base.utils.JsonParser;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ClientSyncResponse {
    public class EntityJsonParser implements JsonParser {
        public EntityJsonParser() {
            super();
        }

        public ClientSyncResponse a(String arg8) {
            ArrayList v0 = new ArrayList();
            JSONArray v8 = new JSONObject(arg8).getJSONArray("syncData");
            if(v8 != null) {
                int v1;
                for(v1 = 0; v1 < v8.length(); ++v1) {
                    JSONObject v2 = v8.getJSONObject(v1);
                    if(v2 != null) {
                        v0.add(new SyncData(v2.getString("type"), v2.getString("ver"), v2.getString("data")));
                    }
                }
            }

            return new ClientSyncResponse(((List)v0));
        }
    }

    public class SyncData {
        private final String a;
        private final String b;
        private final String c;

        public SyncData(String arg1, String arg2, String arg3) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
        }

        public String a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }

        public String c() {
            return this.c;
        }
    }

    private final List a;

    public ClientSyncResponse(List arg1) {
        super();
        this.a = arg1;
    }

    public static ClientSyncResponse a(String arg1) {
        try {
            return new EntityJsonParser().a(arg1);
        }
        catch(JSONException ) {
            return null;
        }
    }

    public List a() {
        return this.a;
    }
}

