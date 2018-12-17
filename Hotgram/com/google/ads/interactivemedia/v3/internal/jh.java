package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent$AdErrorListener;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.AdEvent$AdEventListener;
import com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.ads.interactivemedia.v3.impl.data.q;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class jh implements AdErrorListener, AdEventListener, a {
    class com.google.ads.interactivemedia.v3.internal.jh$1 {
        static {
            com.google.ads.interactivemedia.v3.internal.jh$1.a = new int[AdEventType.values().length];
            try {
                com.google.ads.interactivemedia.v3.internal.jh$1.a[AdEventType.LOADED.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    com.google.ads.interactivemedia.v3.internal.jh$1.a[AdEventType.STARTED.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        com.google.ads.interactivemedia.v3.internal.jh$1.a[AdEventType.COMPLETED.ordinal()] = 3;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                        try {
                        label_19:
                            com.google.ads.interactivemedia.v3.internal.jh$1.a[AdEventType.SKIPPED.ordinal()] = 4;
                            return;
                        }
                        catch(NoSuchFieldError ) {
                            return;
                        }
                    }
                }
            }
        }
    }

    public class com.google.ads.interactivemedia.v3.internal.jh$a {
        protected com.google.ads.interactivemedia.v3.internal.jh$a() {
            super();
        }

        protected void a(String arg1, Context arg2) {
            com.google.ads.interactivemedia.v3.internal.a.a(arg1, arg2);
        }

        protected String a() {
            return com.google.ads.interactivemedia.v3.internal.a.a();
        }

        protected d a(h arg1, h arg2, boolean arg3) {
            return d.a(arg1, arg2, arg3);
        }

        protected i a(String arg1, String arg2) {
            return i.a(arg1, arg2);
        }

        protected c a(d arg1, e arg2) {
            return c.a(arg1, arg2);
        }

        protected e a(i arg1, WebView arg2, String arg3) {
            return e.a(arg1, arg2, arg3);
        }
    }

    private final jd a;
    private final com.google.ads.interactivemedia.v3.internal.jh$a b;
    private final Context c;
    private View d;
    private String e;
    private final Set f;
    private static boolean g = false;
    private boolean h;
    private static jh i;
    private c j;

    static {
    }

    jh(jd arg2, Context arg3) {
        this(arg2, arg3, new com.google.ads.interactivemedia.v3.internal.jh$a());
    }

    jh(jd arg2, Context arg3, com.google.ads.interactivemedia.v3.internal.jh$a arg4) {
        super();
        this.h = false;
        this.a = arg2;
        this.b = arg4;
        this.c = arg3;
        this.f = new HashSet();
        jh.i = this;
        if(jh.g) {
            arg4.a(arg4.a(), arg3);
        }
    }

    public void a(boolean arg1) {
        this.h = arg1;
    }

    private void a(List arg6) {
        Object v6_1;
        if(arg6 != null) {
            q v6 = q.builder().views(((Collection)arg6)).build();
        }
        else {
            v6_1 = null;
        }

        this.a.b(new jc(com.google.ads.interactivemedia.v3.internal.jc$a.omid, b.registerFriendlyObstructions, this.e, v6_1));
    }

    public void a() {
        this.g();
    }

    public void a(View arg1) {
        this.c(arg1);
    }

    public void a(String arg1) {
        this.e = arg1;
    }

    public static void b() {
        if(jh.i != null && !jh.g) {
            jh.i.b.a(jh.i.b.a(), jh.i.c);
        }

        jh.g = true;
    }

    public void b(View arg1) {
        this.d = arg1;
    }

    public static void c() {
        jh.g = false;
    }

    public void c(View arg3) {
        if(this.f.contains(arg3)) {
            return;
        }

        this.f.add(arg3);
        if(this.j == null) {
            return;
        }

        this.j.b(arg3);
        this.a(Arrays.asList(new View[]{arg3}));
    }

    public void d() {
        if((jh.g) && this.j == null) {
            if(this.d == null) {
            }
            else {
                d v0 = this.b.a(h.b, h.b, true);
                i v1 = this.b.a("Google1", this.b.a());
                com.google.ads.interactivemedia.v3.internal.jh$a v2 = this.b;
                WebView v3 = this.a.b();
                String v4 = this.h ? "true" : "false";
                StringBuilder v6 = new StringBuilder(String.valueOf(v4).length() + 7);
                v6.append("{ssai:");
                v6.append(v4);
                v6.append("}");
                this.j = this.b.a(v0, v2.a(v1, v3, v6.toString()));
                this.j.a(this.d);
                Iterator v0_1 = this.f.iterator();
                while(v0_1.hasNext()) {
                    this.j.b(v0_1.next());
                }

                if(!this.f.isEmpty()) {
                    this.a(new ArrayList(this.f));
                }

                this.j.a();
            }
        }
    }

    public static String e() {
        return new com.google.ads.interactivemedia.v3.internal.jh$a().a();
    }

    public boolean f() {
        if(jh.g) {
            if(this.j == null) {
            }
            else {
                this.j.b();
                this.j = null;
                return 1;
            }
        }

        return 0;
    }

    public void g() {
        this.f.clear();
        if(this.j == null) {
            return;
        }

        this.j.c();
        this.a(null);
    }

    public void onAdError(AdErrorEvent arg1) {
        if(jh.g) {
            if(this.j == null) {
            }
            else {
                this.j.b();
                this.j = null;
            }
        }
    }

    public void onAdEvent(AdEvent arg2) {
        if(jh.g) {
            switch(com.google.ads.interactivemedia.v3.internal.jh$1.a[arg2.getType().ordinal()]) {
                case 1: 
                case 2: {
                    goto label_10;
                }
                case 3: 
                case 4: {
                    goto label_8;
                }
            }

            return;
        label_8:
            this.f();
            return;
        label_10:
            this.d();
        }
    }
}

