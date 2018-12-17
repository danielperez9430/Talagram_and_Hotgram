package com.persianswitch.sdk.payment.model.req;

import com.persianswitch.sdk.base.utils.JsonParser;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public final class ClientSyncRequest {
    class com.persianswitch.sdk.payment.model.req.ClientSyncRequest$1 {
    }

    public class EntityJsonParser implements JsonParser {
        public EntityJsonParser() {
            super();
        }

        public JSONObject a(ClientSyncRequest arg5) {
            JSONObject v0 = new JSONObject();
            JSONArray v1 = new JSONArray();
            com.persianswitch.sdk.payment.model.req.ClientSyncRequest$SyncVersion$EntityJsonParser v2 = new com.persianswitch.sdk.payment.model.req.ClientSyncRequest$SyncVersion$EntityJsonParser(null);
            Iterator v5 = arg5.a().iterator();
            while(v5.hasNext()) {
                v1.put(v2.a(v5.next()));
            }

            v0.put("syncs", v1);
            return v0;
        }
    }

    public class SyncVersion {
        class com.persianswitch.sdk.payment.model.req.ClientSyncRequest$SyncVersion$EntityJsonParser implements JsonParser {
            private com.persianswitch.sdk.payment.model.req.ClientSyncRequest$SyncVersion$EntityJsonParser() {
                super();
            }

            com.persianswitch.sdk.payment.model.req.ClientSyncRequest$SyncVersion$EntityJsonParser(com.persianswitch.sdk.payment.model.req.ClientSyncRequest$1 arg1) {
                this();
            }

            public JSONObject a(SyncVersion arg4) {
                JSONObject v0 = new JSONObject();
                v0.put("type", arg4.a());
                v0.put("ver", arg4.b());
                return v0;
            }
        }

        private final String a;
        private final String b;

        public SyncVersion(String arg1, String arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        public String a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }
    }

    private final ArrayList a;

    public ClientSyncRequest(ArrayList arg1) {
        super();
        this.a = arg1;
    }

    public ArrayList a() {
        return this.a;
    }
}

