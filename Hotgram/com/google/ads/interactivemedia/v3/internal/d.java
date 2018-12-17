package com.google.ads.interactivemedia.v3.internal;

import org.json.JSONObject;

public class d {
    private final h a;
    private final h b;
    private final boolean c;

    private d(h arg1, h arg2, boolean arg3) {
        super();
        this.a = arg1;
        this.b = arg2 == null ? h.c : arg2;
        this.c = arg3;
    }

    public static d a(h arg1, h arg2, boolean arg3) {
        af.a(arg1, "Impression owner is null");
        af.a(arg1);
        return new d(arg1, arg2, arg3);
    }

    public JSONObject a() {
        JSONObject v0 = new JSONObject();
        ac.a(v0, "impressionOwner", this.a);
        ac.a(v0, "videoEventsOwner", this.b);
        ac.a(v0, "isolateVerificationScripts", Boolean.valueOf(this.c));
        return v0;
    }
}

