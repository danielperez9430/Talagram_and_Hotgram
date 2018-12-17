package com.crashlytics.android.b;

import org.json.JSONObject;

class g {
    g() {
        super();
    }

    public f a(JSONObject arg10) {
        f v0 = null;
        if(arg10 == null) {
            return v0;
        }

        return new f(arg10.optString("url", ((String)v0)), arg10.optString("version_string", ((String)v0)), arg10.optString("display_version", ((String)v0)), arg10.optString("build_version", ((String)v0)), arg10.optString("identifier", ((String)v0)), arg10.optString("instance_identifier", ((String)v0)));
    }
}

