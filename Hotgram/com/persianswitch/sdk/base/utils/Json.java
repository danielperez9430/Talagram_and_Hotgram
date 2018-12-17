package com.persianswitch.sdk.base.utils;

import java.util.Map;
import org.json.JSONObject;

public final class Json {
    public Json() {
        super();
    }

    public static JSONObject a(Map arg1) {
        return new JSONObject(arg1);
    }
}

