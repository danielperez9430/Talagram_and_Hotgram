package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.AdErrorEvent$AdErrorListener;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ix {
    private final List a;

    public ix() {
        super();
        this.a = new ArrayList(1);
    }

    public void a(AdErrorListener arg2) {
        this.a.add(arg2);
    }

    public void a(AdErrorEvent arg3) {
        Iterator v0 = this.a.iterator();
        while(v0.hasNext()) {
            v0.next().onAdError(arg3);
        }
    }

    public void b(AdErrorListener arg2) {
        this.a.remove(arg2);
    }

    public String toString() {
        String v0 = String.valueOf(this.a);
        StringBuilder v2 = new StringBuilder(String.valueOf(v0).length() + 38);
        v2.append("ErrorListenerSupport [errorListeners=");
        v2.append(v0);
        v2.append("]");
        return v2.toString();
    }
}

