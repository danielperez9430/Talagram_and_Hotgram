package com.google.ads.interactivemedia.v3.internal;

import java.util.HashSet;
import org.json.JSONObject;

public class ai implements b {
    private JSONObject a;
    private final an b;

    public ai(an arg1) {
        super();
        this.b = arg1;
    }

    public void a() {
        this.b.b(new ao(((b)this)));
    }

    public void a(JSONObject arg1) {
        this.a = arg1;
    }

    public void a(JSONObject arg9, HashSet arg10, double arg11) {
        this.b.b(new aq(this, arg10, arg9, arg11));
    }

    public JSONObject b() {
        return this.a;
    }

    public void b(JSONObject arg9, HashSet arg10, double arg11) {
        this.b.b(new ap(this, arg10, arg9, arg11));
    }
}

