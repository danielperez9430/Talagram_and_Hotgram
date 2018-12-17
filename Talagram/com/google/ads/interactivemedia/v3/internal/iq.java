package com.google.ads.interactivemedia.v3.internal;

import android.view.View;
import android.view.ViewGroup;
import com.google.ads.interactivemedia.v3.api.BaseDisplayContainer;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class iq implements BaseDisplayContainer {
    public interface a {
        void a(View arg1);

        void a();
    }

    private ViewGroup a;
    private Collection b;
    private Map c;
    private final Set d;
    private a e;
    private static int f;

    static {
    }

    public iq() {
        super();
        this.b = Collections.emptyList();
        this.c = null;
        this.d = new HashSet();
        this.e = null;
    }

    public Map a() {
        if(this.c == null) {
            com.google.ads.interactivemedia.v3.internal.lb$a v0 = new com.google.ads.interactivemedia.v3.internal.lb$a();
            Iterator v1 = this.b.iterator();
            while(v1.hasNext()) {
                Object v2 = v1.next();
                if(v2 == null) {
                    continue;
                }

                int v3 = iq.f;
                iq.f = v3 + 1;
                StringBuilder v5 = new StringBuilder(20);
                v5.append("compSlot_");
                v5.append(v3);
                v0.a(v5.toString(), v2);
            }

            this.c = v0.a();
        }

        return this.c;
    }

    public void a(a arg1) {
        this.e = arg1;
    }

    public Set b() {
        return new HashSet(this.d);
    }

    public ViewGroup getAdContainer() {
        return this.a;
    }

    public Collection getCompanionSlots() {
        return this.b;
    }

    public void registerVideoControlsOverlay(View arg2) {
        if(arg2 != null) {
            if(this.d.contains(arg2)) {
            }
            else {
                this.d.add(arg2);
                if(this.e == null) {
                    return;
                }
                else {
                    this.e.a(arg2);
                }
            }
        }
    }

    public void setAdContainer(ViewGroup arg1) {
        this.a = arg1;
    }

    public void setCompanionSlots(Collection arg1) {
        this.b = arg1;
    }

    public void unregisterAllVideoControlsOverlays() {
        this.d.clear();
        if(this.e == null) {
            return;
        }

        this.e.a();
    }
}

