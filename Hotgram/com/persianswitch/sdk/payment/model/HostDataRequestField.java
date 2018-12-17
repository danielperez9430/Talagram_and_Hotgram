package com.persianswitch.sdk.payment.model;

import android.content.Context;
import com.persianswitch.sdk.base.log.SDKLog;
import com.persianswitch.sdk.base.utils.Json;
import com.persianswitch.sdk.base.utils.strings.Jsonable$JsonWriteException;
import com.persianswitch.sdk.base.utils.strings.Jsonable;
import com.persianswitch.sdk.payment.model.req.AbsRequest;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class HostDataRequestField {
    class com.persianswitch.sdk.payment.model.HostDataRequestField$1 {
    }

    class EntityJsonParser implements Jsonable {
        EntityJsonParser(com.persianswitch.sdk.payment.model.HostDataRequestField$1 arg1) {
            this();
        }

        private EntityJsonParser() {
            super();
        }

        public JSONObject a(HostDataRequestField arg4) {
            try {
                HashMap v0 = new HashMap();
                ((Map)v0).put("hreq", HostDataRequestField.a(arg4));
                ((Map)v0).put("hsign", HostDataRequestField.b(arg4));
                ((Map)v0).put("ver", HostDataRequestField.c(arg4));
                ((Map)v0).put("hver", HostDataRequestField.d(arg4));
                return Json.a(((Map)v0));
            }
            catch(Exception v4) {
                throw new JsonWriteException(v4.getMessage());
            }
        }
    }

    private final String a;
    private final String b;
    private final String c;
    private final String d;

    public HostDataRequestField(String arg1, String arg2, String arg3, String arg4) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
    }

    public JSONObject a() {
        try {
            return new EntityJsonParser(null).a(this);
        }
        catch(JsonWriteException ) {
            SDKLog.c("HostDataRequestField", "error while parse json for host data request", new Object[0]);
            return new JSONObject();
        }
    }

    public static HostDataRequestField a(Context arg2) {
        return new HostDataRequestField(null, null, null, AbsRequest.b(arg2));
    }

    static String a(HostDataRequestField arg0) {
        return arg0.a;
    }

    static String b(HostDataRequestField arg0) {
        return arg0.b;
    }

    static String c(HostDataRequestField arg0) {
        return arg0.c;
    }

    static String d(HostDataRequestField arg0) {
        return arg0.d;
    }
}

