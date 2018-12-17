package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.Ad;
import com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;

public class ie implements AdEvent {
    private AdEventType a;
    private Ad b;
    private Map c;

    ie(AdEventType arg1, Ad arg2, Map arg3) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
    }

    private String a() {
        if(this.c == null) {
            return "";
        }

        StringBuilder v0 = new StringBuilder("{");
        Iterator v1 = this.c.entrySet().iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            v0.append(((Map$Entry)v2).getKey());
            v0.append(": ");
            v0.append(((Map$Entry)v2).getValue());
            if(!v1.hasNext()) {
                continue;
            }

            v0.append(", ");
        }

        v0.append("}");
        return v0.toString();
    }

    public boolean equals(Object arg5) {
        if(this == (((ie)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof ie)) {
            return 0;
        }

        if(this.a != ((ie)arg5).a) {
            return 0;
        }

        if(!ko.a(this.b, ((ie)arg5).b)) {
            return 0;
        }

        if(!ko.a(this.c, ((ie)arg5).c)) {
            return 0;
        }

        return 1;
    }

    public Ad getAd() {
        return this.b;
    }

    public Map getAdData() {
        return this.c;
    }

    public AdEventType getType() {
        return this.a;
    }

    public int hashCode() {
        return ko.a(new Object[]{this.a, this.b, this.c});
    }

    public String toString() {
        String v0 = String.valueOf(String.format("AdEvent[type=%s, ad=%s", this.a, this.b));
        String v1 = this.c == null ? "]" : String.format(", adData=%s]", this.a());
        v1 = String.valueOf(v1);
        return v1.length() != 0 ? v0.concat(v1) : new String(v0);
    }
}

