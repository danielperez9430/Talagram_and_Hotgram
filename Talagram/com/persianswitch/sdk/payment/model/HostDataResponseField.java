package com.persianswitch.sdk.payment.model;

import com.persianswitch.sdk.base.utils.strings.Jsonable$JsonParseException;
import com.persianswitch.sdk.base.utils.strings.Jsonable;
import org.json.JSONObject;

public class HostDataResponseField {
    class com.persianswitch.sdk.payment.model.HostDataResponseField$1 {
    }

    public enum ContinuePlan {
        public static final enum ContinuePlan a;
        public static final enum ContinuePlan b;
        private final String c;

        static {
            ContinuePlan.a = new ContinuePlan("ALLOW_CONTINUE", 0, "0");
            ContinuePlan.b = new ContinuePlan("RETURN_TO_MERCHANT", 1, "1");
            ContinuePlan.d = new ContinuePlan[]{ContinuePlan.a, ContinuePlan.b};
        }

        private ContinuePlan(String arg1, int arg2, String arg3) {
            super(arg1, arg2);
            this.c = arg3;
        }

        public static ContinuePlan valueOf(String arg1) {
            return Enum.valueOf(ContinuePlan.class, arg1);
        }

        public static ContinuePlan[] values() {
            // Method was not decompiled
        }
    }

    class JsonParser implements Jsonable {
        JsonParser(com.persianswitch.sdk.payment.model.HostDataResponseField$1 arg1) {
            this();
        }

        private JsonParser() {
            super();
        }

        public JSONObject a(HostDataResponseField arg2, String arg3) {
            JSONObject v0;
            try {
                v0 = new JSONObject(arg3);
                if(v0.has("hresp")) {
                    HostDataResponseField.a(arg2, v0.getString("hresp"));
                }

                if(v0.has("hsign")) {
                    HostDataResponseField.b(arg2, v0.getString("hsign"));
                }

                if(v0.has("hstat")) {
                    HostDataResponseField.a(arg2, v0.getInt("hstat"));
                }
            }
            catch(Exception v2) {
                throw new JsonParseException(v2.getMessage());
            }

            return v0;
        }
    }

    private String a;
    private String b;
    private int c;

    public HostDataResponseField() {
        super();
        this.a = "";
        this.b = "";
        this.c = 0;
    }

    static int a(HostDataResponseField arg0, int arg1) {
        arg0.c = arg1;
        return arg1;
    }

    public static HostDataResponseField a(String arg3) {
        com.persianswitch.sdk.payment.model.HostDataResponseField$1 v0 = null;
        try {
            HostDataResponseField v1 = new HostDataResponseField();
            new JsonParser(v0).a(v1, arg3);
            return v1;
        }
        catch(JsonParseException ) {
            return ((HostDataResponseField)v0);
        }
    }

    static String a(HostDataResponseField arg0, String arg1) {
        arg0.a = arg1;
        return arg1;
    }

    public String a() {
        return this.a;
    }

    static String b(HostDataResponseField arg0, String arg1) {
        arg0.b = arg1;
        return arg1;
    }

    public String b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }
}

