package com.crashlytics.android.a;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

class c {
    final e a;
    final Map b;

    public c(e arg2) {
        super();
        this.b = new ConcurrentHashMap();
        this.a = arg2;
    }

    void a(String arg3, Number arg4) {
        if(!this.a.a(arg3, "key")) {
            if(this.a.a(arg4, "value")) {
            }
            else {
                this.a(this.a.a(arg3), arg4);
            }
        }
    }

    void a(String arg3, Object arg4) {
        if(!this.a.a(this.b, arg3)) {
            this.b.put(arg3, arg4);
        }
    }

    void a(String arg3, String arg4) {
        if(!this.a.a(arg3, "key")) {
            if(this.a.a(arg4, "value")) {
            }
            else {
                this.a(this.a.a(arg3), this.a.a(arg4));
            }
        }
    }

    public String toString() {
        return new JSONObject(this.b).toString();
    }
}

