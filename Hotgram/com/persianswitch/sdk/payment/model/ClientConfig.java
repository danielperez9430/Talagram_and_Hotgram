package com.persianswitch.sdk.payment.model;

import android.content.Context;
import com.persianswitch.sdk.base.log.SDKLog;
import com.persianswitch.sdk.base.manager.LanguageManager;
import com.persianswitch.sdk.base.utils.strings.Jsonable$JsonParseException;
import com.persianswitch.sdk.base.utils.strings.Jsonable;
import com.persianswitch.sdk.payment.repo.SyncRepo;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public final class ClientConfig {
    class com.persianswitch.sdk.payment.model.ClientConfig$1 {
    }

    class EntityJsonParser implements Jsonable {
        EntityJsonParser(com.persianswitch.sdk.payment.model.ClientConfig$1 arg1) {
            this();
        }

        private EntityJsonParser() {
            super();
        }

        public JSONObject a(ClientConfig arg6, String arg7) {
            JSONObject v0;
            try {
                v0 = new JSONObject(arg7);
                if(v0.has("tranTimeout")) {
                    ClientConfig.a(arg6, v0.getLong("tranTimeout"));
                }

                if(v0.has("returnTimeout")) {
                    ClientConfig.b(arg6, v0.getLong("returnTimeout"));
                }

                if(v0.has("syncCardMinDelay")) {
                    ClientConfig.c(arg6, v0.getLong("syncCardMinDelay"));
                }

                RootConfigTypes v7 = v0.has("root") ? RootConfigTypes.a(v0.getInt("root")) : RootConfigTypes.c;
                ClientConfig.a(arg6, v7);
                if(v0.has("rootMsg")) {
                    ClientConfig.a(arg6, v0.getString("rootMsg"));
                }

                if(v0.has("verifyMobileNo")) {
                    boolean v7_1 = v0.getInt("verifyMobileNo") == 1 ? true : false;
                    ClientConfig.a(arg6, v7_1);
                }

                if(v0.has("originality")) {
                    int v3 = 2;
                    if(v0.getString("originality").length() >= v3) {
                        ClientConfig.b(arg6, "1".equals(v0.getString("originality").substring(0, 1)));
                        ClientConfig.c(arg6, "1".equals(v0.getString("originality").substring(1, v3)));
                    }
                }

                if(v0.has("inquiryWait")) {
                    ClientConfig.d(arg6, v0.getLong("inquiryWait") * 1000);
                }

                if(v0.has("unknownTranMsg")) {
                    ClientConfig.b(arg6, v0.getString("unknownTranMsg"));
                }
            }
            catch(JSONException v6) {
                throw new JsonParseException(v6.getMessage());
            }

            return v0;
        }
    }

    public enum RootConfigTypes {
        public static final enum RootConfigTypes a;
        public static final enum RootConfigTypes b;
        public static final enum RootConfigTypes c;
        private int d;

        static {
            RootConfigTypes.a = new RootConfigTypes("Disable", 0, 2);
            RootConfigTypes.b = new RootConfigTypes("EnableWithWarning", 1, 1);
            RootConfigTypes.c = new RootConfigTypes("EnableNormal", 2, 0);
            RootConfigTypes.e = new RootConfigTypes[]{RootConfigTypes.a, RootConfigTypes.b, RootConfigTypes.c};
        }

        private RootConfigTypes(String arg1, int arg2, int arg3) {
            super(arg1, arg2);
            this.d = arg3;
        }

        public static RootConfigTypes a(int arg5) {
            RootConfigTypes[] v0 = RootConfigTypes.values();
            int v1 = v0.length;
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                RootConfigTypes v3 = v0[v2];
                if(v3.d == arg5) {
                    return v3;
                }
            }

            return RootConfigTypes.c;
        }

        public static RootConfigTypes valueOf(String arg1) {
            return Enum.valueOf(RootConfigTypes.class, arg1);
        }

        public static RootConfigTypes[] values() {
            // Method was not decompiled
        }
    }

    private long a;
    private long b;
    private long c;
    private RootConfigTypes d;
    private String e;
    private boolean f;
    private boolean g;
    private boolean h;
    private long i;
    private String j;

    private ClientConfig() {
        super();
        this.i = TimeUnit.MINUTES.toMillis(1);
        this.a = 420;
        this.b = 15;
        this.c = TimeUnit.SECONDS.convert(1, TimeUnit.DAYS);
        this.g = false;
        this.h = false;
    }

    public static ClientConfig a(Context arg4) {
        String v2;
        String v4_1;
        ClientConfig v0 = new ClientConfig();
        SyncableData v4 = new SyncRepo(arg4).a(SyncType.b, LanguageManager.a(arg4).c());
        if(v4 != null) {
            try {
                new EntityJsonParser(null).a(v0, v4.f());
                return v0;
            }
            catch(JsonParseException ) {
                v4_1 = "ClientConfig";
                v2 = "error in parse client config";
                goto label_21;
            }
        }

        v4_1 = "ClientConfig";
        v2 = "client config not found use default value";
    label_21:
        SDKLog.c(v4_1, v2, new Object[0]);
        return v0;
    }

    static long a(ClientConfig arg0, long arg1) {
        arg0.a = arg1;
        return arg1;
    }

    static RootConfigTypes a(ClientConfig arg0, RootConfigTypes arg1) {
        arg0.d = arg1;
        return arg1;
    }

    static String a(ClientConfig arg0, String arg1) {
        arg0.e = arg1;
        return arg1;
    }

    static boolean a(ClientConfig arg0, boolean arg1) {
        arg0.f = arg1;
        return arg1;
    }

    public boolean a() {
        boolean v0 = (this.g) || (this.h) ? true : false;
        return v0;
    }

    static long b(ClientConfig arg0, long arg1) {
        arg0.b = arg1;
        return arg1;
    }

    static String b(ClientConfig arg0, String arg1) {
        arg0.j = arg1;
        return arg1;
    }

    static boolean b(ClientConfig arg0, boolean arg1) {
        arg0.g = arg1;
        return arg1;
    }

    public long b() {
        return this.i;
    }

    static long c(ClientConfig arg0, long arg1) {
        arg0.c = arg1;
        return arg1;
    }

    static boolean c(ClientConfig arg0, boolean arg1) {
        arg0.h = arg1;
        return arg1;
    }

    public String c() {
        return this.j;
    }

    static long d(ClientConfig arg0, long arg1) {
        arg0.i = arg1;
        return arg1;
    }

    public long d() {
        return this.a;
    }

    public long e() {
        return this.b;
    }

    public long f() {
        return this.c;
    }

    public RootConfigTypes g() {
        return this.d;
    }

    public String h() {
        return this.e;
    }

    public boolean i() {
        return this.f;
    }
}

