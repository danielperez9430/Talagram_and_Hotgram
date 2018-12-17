package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build$VERSION;
import android.os.StrictMode$ThreadPolicy$Builder;
import android.os.StrictMode$ThreadPolicy;
import android.os.StrictMode;
import android.util.Log;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdError$AdErrorCode;
import com.google.ads.interactivemedia.v3.api.AdError$AdErrorType;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent$AdErrorListener;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.AdsLoader$AdsLoadedListener;
import com.google.ads.interactivemedia.v3.api.AdsLoader;
import com.google.ads.interactivemedia.v3.api.AdsManagerLoadedEvent;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.api.ImaSdkFactory;
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.api.StreamDisplayContainer;
import com.google.ads.interactivemedia.v3.api.StreamRequest;
import com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration;
import com.google.ads.interactivemedia.v3.impl.data.o;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SortedSet;
import java.util.UUID;

public class ii implements AdsLoader {
    class com.google.ads.interactivemedia.v3.internal.ii$1 implements b {
        com.google.ads.interactivemedia.v3.internal.ii$1(ii arg1) {
            this.a = arg1;
            super();
        }

        public void a(String arg3, AdErrorType arg4, int arg5, String arg6) {
            Object v3 = ii.a(this.a).get(arg3) != null ? ii.a(this.a).get(arg3).getUserRequestContext() : ii.f(this.a).get(arg3).getUserRequestContext();
            ii.e(this.a).a(new id(new AdError(arg4, arg5, arg6), v3));
        }

        public void a(String arg3, AdErrorType arg4, AdErrorCode arg5, String arg6) {
            Object v3 = ii.a(this.a).get(arg3) != null ? ii.a(this.a).get(arg3).getUserRequestContext() : ii.f(this.a).get(arg3).getUserRequestContext();
            ii.e(this.a).a(new id(new AdError(arg4, arg5, arg6), v3));
        }

        public void a(String arg15, jf arg16, String arg17, boolean arg18) {
            com.google.ads.interactivemedia.v3.internal.ii$1 v1 = this;
            Object v11 = ii.f(v1.a).get(arg15);
            ii.c(v1.a).a(true);
            try {
                v1.a.a(new ik(new jl(arg15, ii.b(v1.a), arg16, v11, ii.c(v1.a), ii.d(v1.a), arg17, arg18), ((StreamRequest)v11).getUserRequestContext()));
            }
            catch(AdError v0) {
                ii.e(v1.a).a(new id(v0, ((StreamRequest)v11).getUserRequestContext()));
            }
        }

        public void a(String arg17, jf arg18, List arg19, SortedSet arg20, boolean arg21) {
            com.google.ads.interactivemedia.v3.internal.ii$1 v1 = this;
            Object v13 = ii.a(v1.a).get(arg17);
            try {
                v1.a.a(new ik(new ij(arg17, ii.b(v1.a), arg18, ((AdsRequest)v13).getAdDisplayContainer(), ((AdsRequest)v13).getContentProgressProvider(), arg19, arg20, ii.c(v1.a), ii.d(v1.a), arg21), ((AdsRequest)v13).getUserRequestContext()));
            }
            catch(AdError v0) {
                ii.e(v1.a).a(new id(v0, ((AdsRequest)v13).getUserRequestContext()));
            }
        }
    }

    class a extends AsyncTask {
        private final AdsRequest b;
        private final String c;

        public a(ii arg1, AdsRequest arg2, String arg3) {
            this.a = arg1;
            super();
            this.b = arg2;
            this.c = arg3;
        }

        protected String a(String[] arg6) {
            String v6 = arg6[0];
            Object v0 = ii.g(this.a);
            __monitor_enter(v0);
            try {
                if(ii.h(this.a) == null) {
                    ii.a(this.a, new kb(ka.a("a.3.10.2", ii.d(this.a))));
                }

                if(v6 != null) {
                    Uri v1 = Uri.parse(v6);
                    if(ii.h(this.a).b(v1)) {
                        try {
                            v6 = ii.h(this.a).a(v1, ii.d(this.a)).toString();
                            goto label_29;
                        }
                        catch(kc ) {
                        label_29:
                            __monitor_exit(v0);
                            goto label_30;
                        }
                    }
                }

                goto label_29;
            }
            catch(Throwable v6_1) {
                try {
                label_38:
                    __monitor_exit(v0);
                }
                catch(Throwable v6_1) {
                    goto label_38;
                }

                throw v6_1;
            }

        label_30:
            ii.a(this.a, new js(ii.d(this.a)));
            return v6;
        }

        protected void a(String arg9) {
            this.b.setAdTagUrl(arg9);
            ii.b(this.a).b(new jc(com.google.ads.interactivemedia.v3.internal.jc$a.adsLoader, com.google.ads.interactivemedia.v3.internal.jc$b.requestAds, this.c, o.create(this.b, ii.i(this.a), ii.j(this.a), ii.k(this.a), ii.l(this.a), jr.a(ii.d(this.a), ii.m(this.a)), ii.n(this.a))));
        }

        protected Object doInBackground(Object[] arg1) {
            return this.a(((String[])arg1));
        }

        protected void onPostExecute(Object arg1) {
            this.a(((String)arg1));
        }
    }

    public abstract class com.google.ads.interactivemedia.v3.internal.ii$b {
        public com.google.ads.interactivemedia.v3.internal.ii$b() {
            super();
        }

        public abstract int appVersion();

        public static com.google.ads.interactivemedia.v3.internal.ii$b create(int arg1, String arg2) {
            return new io(arg1, arg2);
        }

        public abstract String packageName();
    }

    class c extends AsyncTask {
        private final StreamRequest b;
        private final String c;

        public c(ii arg1, StreamRequest arg2, String arg3) {
            this.a = arg1;
            super();
            this.b = arg2;
            this.c = arg3;
        }

        protected String a(Void[] arg5) {
            String v0_1;
            Object v5 = ii.g(this.a);
            __monitor_enter(v5);
            try {
                if(ii.h(this.a) == null) {
                    ii.a(this.a, new kb(ka.a("a.3.10.2", ii.d(this.a))));
                }

                v0_1 = ii.h(this.a).a().a(ii.d(this.a));
                __monitor_exit(v5);
            }
            catch(Throwable v0) {
                try {
                label_29:
                    __monitor_exit(v5);
                }
                catch(Throwable v0) {
                    goto label_29;
                }

                throw v0;
            }

            ii.a(this.a, new js(ii.d(this.a)));
            return v0_1;
        }

        protected void a(String arg9) {
            ii.b(this.a).b(new jc(com.google.ads.interactivemedia.v3.internal.jc$a.adsLoader, com.google.ads.interactivemedia.v3.internal.jc$b.requestStream, this.c, o.createFromStreamRequest(this.b, ii.i(this.a), ii.j(this.a), ii.k(this.a), ii.l(this.a), jr.a(ii.d(this.a), ii.m(this.a)), arg9, ii.n(this.a))));
        }

        protected Object doInBackground(Object[] arg1) {
            return this.a(((Void[])arg1));
        }

        protected void onPostExecute(Object arg1) {
            this.a(((String)arg1));
        }
    }

    b a;
    private final jd b;
    private final Context c;
    private final ix d;
    private final List e;
    private final Map f;
    private final Map g;
    private final jh h;
    private kb i;
    private final Object j;
    private js k;
    private ImaSdkSettings l;
    private TestingConfiguration m;

    public ii(Context arg2, Uri arg3, ImaSdkSettings arg4, TestingConfiguration arg5) {
        this(new jd(arg2, arg3, arg4, arg5), arg2);
        this.l = arg4;
        this.m = arg5;
    }

    public ii(Context arg2, Uri arg3, ImaSdkSettings arg4) {
        this(arg2, arg3, arg4, null);
        this.b.a();
    }

    public ii(jd arg3, Context arg4) {
        super();
        this.a = new com.google.ads.interactivemedia.v3.internal.ii$1(this);
        this.d = new ix();
        this.e = new ArrayList(1);
        this.f = new HashMap();
        this.g = new HashMap();
        this.j = new Object();
        this.b = arg3;
        this.c = arg4;
        this.h = new jh(arg3, arg4);
        this.l = ImaSdkFactory.getInstance().createImaSdkSettings();
    }

    public void a() {
        this.b.a();
    }

    static js a(ii arg0, js arg1) {
        arg0.k = arg1;
        return arg1;
    }

    static kb a(ii arg0, kb arg1) {
        arg0.i = arg1;
        return arg1;
    }

    static Map a(ii arg0) {
        return arg0.f;
    }

    private boolean a(AdsRequest arg7) {
        id v1;
        ix v7;
        if(arg7 == null) {
            v7 = this.d;
            v1 = new id(new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "AdsRequest cannot be null."));
        }
        else {
            AdDisplayContainer v1_1 = arg7.getAdDisplayContainer();
            if(v1_1 == null) {
                v7 = this.d;
                v1 = new id(new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "Ad display container must be provided in the AdsRequest."));
            }
            else if(v1_1.getAdContainer() == null) {
                v7 = this.d;
                v1 = new id(new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "Ad display container must have a UI container."));
            }
            else {
                if((ks.a(arg7.getAdTagUrl())) && (ks.a(arg7.getAdsResponse()))) {
                    v7 = this.d;
                    v1 = new id(new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "Ad tag url must non-null and non empty."));
                    goto label_10;
                }

                return 1;
            }
        }

    label_10:
        v7.a(((AdErrorEvent)v1));
        return 0;
    }

    private boolean a(StreamRequest arg7) {
        id v1;
        ix v7;
        if(arg7 == null) {
            v7 = this.d;
            v1 = new id(new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "StreamRequest cannot be null."));
        }
        else {
            StreamDisplayContainer v7_1 = arg7.getStreamDisplayContainer();
            if(v7_1 == null) {
                v7 = this.d;
                v1 = new id(new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "Ad display container must be provided in the StreamRequest."));
            }
            else if(v7_1.getVideoStreamPlayer() == null) {
                v7 = this.d;
                v1 = new id(new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "Stream requests must specify a player."));
            }
            else {
                return 1;
            }
        }

        v7.a(((AdErrorEvent)v1));
        return 0;
    }

    void a(AdsManagerLoadedEvent arg3) {
        Iterator v0 = this.e.iterator();
        while(v0.hasNext()) {
            v0.next().onAdsManagerLoaded(arg3);
        }
    }

    void a(AdsRequest arg3, String arg4) {
        if(!this.a(arg3)) {
            return;
        }

        this.f.put(arg4, arg3);
        this.b.a(this.a, arg4);
        this.b.a(arg3.getAdDisplayContainer(), arg4);
        new a(this, arg3, arg4).execute(new String[]{arg3.getAdTagUrl()});
    }

    void a(StreamRequest arg3, String arg4) {
        if(!this.a(arg3)) {
            return;
        }

        this.g.put(arg4, arg3);
        this.b.a(this.a, arg4);
        this.b.a(arg3.getStreamDisplayContainer(), arg4);
        new c(this, arg3, arg4).execute(new Void[0]);
    }

    public void addAdErrorListener(AdErrorListener arg2) {
        this.d.a(arg2);
    }

    public void addAdsLoadedListener(AdsLoadedListener arg2) {
        this.e.add(arg2);
    }

    static jd b(ii arg0) {
        return arg0.b;
    }

    private String b() {
        if(this.m != null && (this.m.ignoreStrictModeFalsePositives())) {
            StrictMode$ThreadPolicy v0 = StrictMode.getThreadPolicy();
            StrictMode.setThreadPolicy(new StrictMode$ThreadPolicy$Builder(v0).permitDiskReads().build());
            String v1 = UUID.randomUUID().toString();
            StrictMode.setThreadPolicy(v0);
            return v1;
        }

        return UUID.randomUUID().toString();
    }

    static jh c(ii arg0) {
        return arg0.h;
    }

    private String c() {
        return String.format("android%s:%s:%s", Build$VERSION.RELEASE, "3.10.2", this.c.getPackageName());
    }

    public void contentComplete() {
        this.b.b(new jc(com.google.ads.interactivemedia.v3.internal.jc$a.adsLoader, com.google.ads.interactivemedia.v3.internal.jc$b.contentComplete, "*"));
    }

    static Context d(ii arg0) {
        return arg0.c;
    }

    private String d() {
        if(this.c.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            Log.w("IMASDK", "Host application doesn\'t have ACCESS_NETWORK_STATE permission");
            return "android:0";
        }

        NetworkInfo v0 = this.c.getSystemService("connectivity").getActiveNetworkInfo();
        if(v0 == null) {
            return "android:0";
        }

        return String.format(Locale.US, "android:%d:%d", Integer.valueOf(v0.getType()), Integer.valueOf(v0.getSubtype()));
    }

    private com.google.ads.interactivemedia.v3.internal.ii$b e() {
        PackageInfo v0_1;
        PackageManager v0 = this.c.getPackageManager();
        ResolveInfo v1 = v0.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.google.ads.interactivemedia.v3")), 65536);
        com.google.ads.interactivemedia.v3.internal.ii$b v2 = null;
        if(v1 == null) {
            return v2;
        }

        ActivityInfo v1_1 = v1.activityInfo;
        if(v1_1 == null) {
            return v2;
        }

        try {
            v0_1 = v0.getPackageInfo(v1_1.packageName, 0);
            if(v0_1 != null) {
                goto label_20;
            }
        }
        catch(PackageManager$NameNotFoundException ) {
            return v2;
        }

        return v2;
    label_20:
        return com.google.ads.interactivemedia.v3.internal.ii$b.create(v0_1.versionCode, v1_1.packageName);
    }

    static ix e(ii arg0) {
        return arg0.d;
    }

    static Map f(ii arg0) {
        return arg0.g;
    }

    static Object g(ii arg0) {
        return arg0.j;
    }

    public ImaSdkSettings getSettings() {
        return this.l;
    }

    static kb h(ii arg0) {
        return arg0.i;
    }

    static String i(ii arg0) {
        return arg0.c();
    }

    static String j(ii arg0) {
        return arg0.d();
    }

    static ImaSdkSettings k(ii arg0) {
        return arg0.l;
    }

    static com.google.ads.interactivemedia.v3.internal.ii$b l(ii arg0) {
        return arg0.e();
    }

    static TestingConfiguration m(ii arg0) {
        return arg0.m;
    }

    static js n(ii arg0) {
        return arg0.k;
    }

    public void removeAdErrorListener(AdErrorListener arg2) {
        this.d.b(arg2);
    }

    public void removeAdsLoadedListener(AdsLoadedListener arg2) {
        this.e.remove(arg2);
    }

    public void requestAds(AdsRequest arg2) {
        this.a(arg2, this.b());
    }

    public String requestStream(StreamRequest arg2) {
        String v0 = this.b();
        this.a(arg2, v0);
        return v0;
    }
}

