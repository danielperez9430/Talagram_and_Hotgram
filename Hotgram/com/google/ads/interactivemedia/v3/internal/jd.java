package com.google.ads.interactivemedia.v3.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri$Builder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.google.ads.interactivemedia.v3.api.AdError$AdErrorCode;
import com.google.ads.interactivemedia.v3.api.AdError$AdErrorType;
import com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType;
import com.google.ads.interactivemedia.v3.api.AdProgressInfo;
import com.google.ads.interactivemedia.v3.api.AdsRenderingSettings;
import com.google.ads.interactivemedia.v3.api.BaseDisplayContainer;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.impl.data.CompanionData;
import com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration;
import com.google.ads.interactivemedia.v3.impl.data.n;
import com.google.ads.interactivemedia.v3.impl.data.p;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;

public class jd implements a {
    class com.google.ads.interactivemedia.v3.internal.jd$2 {
        static {
            com.google.ads.interactivemedia.v3.internal.jd$2.c = new int[com.google.ads.interactivemedia.v3.impl.data.CompanionData$a.values().length];
            try {
                com.google.ads.interactivemedia.v3.internal.jd$2.c[com.google.ads.interactivemedia.v3.impl.data.CompanionData$a.Html.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
            label_9:
                int v1 = 2;
                try {
                    com.google.ads.interactivemedia.v3.internal.jd$2.c[com.google.ads.interactivemedia.v3.impl.data.CompanionData$a.IFrame.ordinal()] = v1;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                label_14:
                    int v2 = 3;
                    try {
                        com.google.ads.interactivemedia.v3.internal.jd$2.c[com.google.ads.interactivemedia.v3.impl.data.CompanionData$a.Static.ordinal()] = v2;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                    label_19:
                        com.google.ads.interactivemedia.v3.internal.jd$2.b = new int[b.values().length];
                        try {
                            com.google.ads.interactivemedia.v3.internal.jd$2.b[b.omidReady.ordinal()] = 1;
                            goto label_27;
                        }
                        catch(NoSuchFieldError ) {
                            try {
                            label_27:
                                com.google.ads.interactivemedia.v3.internal.jd$2.b[b.omidUnavailable.ordinal()] = v1;
                                goto label_31;
                            }
                            catch(NoSuchFieldError ) {
                                try {
                                label_31:
                                    com.google.ads.interactivemedia.v3.internal.jd$2.b[b.initialized.ordinal()] = v2;
                                    goto label_35;
                                }
                                catch(NoSuchFieldError ) {
                                label_35:
                                    int v3 = 4;
                                    try {
                                        com.google.ads.interactivemedia.v3.internal.jd$2.b[b.log.ordinal()] = v3;
                                        goto label_40;
                                    }
                                    catch(NoSuchFieldError ) {
                                    label_40:
                                        int v4 = 5;
                                        try {
                                            com.google.ads.interactivemedia.v3.internal.jd$2.b[b.displayCompanions.ordinal()] = v4;
                                            goto label_45;
                                        }
                                        catch(NoSuchFieldError ) {
                                        label_45:
                                            int v5 = 6;
                                            try {
                                                com.google.ads.interactivemedia.v3.internal.jd$2.b[b.adsLoaded.ordinal()] = v5;
                                                goto label_50;
                                            }
                                            catch(NoSuchFieldError ) {
                                            label_50:
                                                int v6 = 7;
                                                try {
                                                    com.google.ads.interactivemedia.v3.internal.jd$2.b[b.streamInitialized.ordinal()] = v6;
                                                    goto label_55;
                                                }
                                                catch(NoSuchFieldError ) {
                                                label_55:
                                                    int v7 = 8;
                                                    try {
                                                        com.google.ads.interactivemedia.v3.internal.jd$2.b[b.error.ordinal()] = v7;
                                                        goto label_60;
                                                    }
                                                    catch(NoSuchFieldError ) {
                                                    label_60:
                                                        int v8 = 9;
                                                        try {
                                                            com.google.ads.interactivemedia.v3.internal.jd$2.b[b.adMetadata.ordinal()] = v8;
                                                            goto label_65;
                                                        }
                                                        catch(NoSuchFieldError ) {
                                                            try {
                                                            label_65:
                                                                com.google.ads.interactivemedia.v3.internal.jd$2.b[b.loaded.ordinal()] = 10;
                                                                goto label_70;
                                                            }
                                                            catch(NoSuchFieldError ) {
                                                                try {
                                                                label_70:
                                                                    com.google.ads.interactivemedia.v3.internal.jd$2.b[b.contentPauseRequested.ordinal()] = 11;
                                                                    goto label_75;
                                                                }
                                                                catch(NoSuchFieldError ) {
                                                                    try {
                                                                    label_75:
                                                                        com.google.ads.interactivemedia.v3.internal.jd$2.b[b.contentResumeRequested.ordinal()] = 12;
                                                                        goto label_80;
                                                                    }
                                                                    catch(NoSuchFieldError ) {
                                                                        try {
                                                                        label_80:
                                                                            com.google.ads.interactivemedia.v3.internal.jd$2.b[b.complete.ordinal()] = 13;
                                                                            goto label_85;
                                                                        }
                                                                        catch(NoSuchFieldError ) {
                                                                            try {
                                                                            label_85:
                                                                                com.google.ads.interactivemedia.v3.internal.jd$2.b[b.allAdsCompleted.ordinal()] = 14;
                                                                                goto label_90;
                                                                            }
                                                                            catch(NoSuchFieldError ) {
                                                                                try {
                                                                                label_90:
                                                                                    com.google.ads.interactivemedia.v3.internal.jd$2.b[b.cuepointsChanged.ordinal()] = 15;
                                                                                    goto label_95;
                                                                                }
                                                                                catch(NoSuchFieldError ) {
                                                                                    try {
                                                                                    label_95:
                                                                                        com.google.ads.interactivemedia.v3.internal.jd$2.b[b.skip.ordinal()] = 16;
                                                                                        goto label_100;
                                                                                    }
                                                                                    catch(NoSuchFieldError ) {
                                                                                        try {
                                                                                        label_100:
                                                                                            com.google.ads.interactivemedia.v3.internal.jd$2.b[b.start.ordinal()] = 17;
                                                                                            goto label_105;
                                                                                        }
                                                                                        catch(NoSuchFieldError ) {
                                                                                            try {
                                                                                            label_105:
                                                                                                com.google.ads.interactivemedia.v3.internal.jd$2.b[b.pause.ordinal()] = 18;
                                                                                                goto label_110;
                                                                                            }
                                                                                            catch(NoSuchFieldError ) {
                                                                                                try {
                                                                                                label_110:
                                                                                                    com.google.ads.interactivemedia.v3.internal.jd$2.b[b.resume.ordinal()] = 19;
                                                                                                    goto label_115;
                                                                                                }
                                                                                                catch(NoSuchFieldError ) {
                                                                                                    try {
                                                                                                    label_115:
                                                                                                        com.google.ads.interactivemedia.v3.internal.jd$2.b[b.firstquartile.ordinal()] = 20;
                                                                                                        goto label_120;
                                                                                                    }
                                                                                                    catch(NoSuchFieldError ) {
                                                                                                        try {
                                                                                                        label_120:
                                                                                                            com.google.ads.interactivemedia.v3.internal.jd$2.b[b.midpoint.ordinal()] = 21;
                                                                                                            goto label_125;
                                                                                                        }
                                                                                                        catch(NoSuchFieldError ) {
                                                                                                            try {
                                                                                                            label_125:
                                                                                                                com.google.ads.interactivemedia.v3.internal.jd$2.b[b.thirdquartile.ordinal()] = 22;
                                                                                                                goto label_130;
                                                                                                            }
                                                                                                            catch(NoSuchFieldError ) {
                                                                                                                try {
                                                                                                                label_130:
                                                                                                                    com.google.ads.interactivemedia.v3.internal.jd$2.b[b.click.ordinal()] = 23;
                                                                                                                    goto label_135;
                                                                                                                }
                                                                                                                catch(NoSuchFieldError ) {
                                                                                                                    try {
                                                                                                                    label_135:
                                                                                                                        com.google.ads.interactivemedia.v3.internal.jd$2.b[b.skippableStateChanged.ordinal()] = 24;
                                                                                                                        goto label_140;
                                                                                                                    }
                                                                                                                    catch(NoSuchFieldError ) {
                                                                                                                        try {
                                                                                                                        label_140:
                                                                                                                            com.google.ads.interactivemedia.v3.internal.jd$2.b[b.videoClicked.ordinal()] = 25;
                                                                                                                            goto label_145;
                                                                                                                        }
                                                                                                                        catch(NoSuchFieldError ) {
                                                                                                                            try {
                                                                                                                            label_145:
                                                                                                                                com.google.ads.interactivemedia.v3.internal.jd$2.b[b.videoIconClicked.ordinal()] = 26;
                                                                                                                                goto label_150;
                                                                                                                            }
                                                                                                                            catch(NoSuchFieldError ) {
                                                                                                                                try {
                                                                                                                                label_150:
                                                                                                                                    com.google.ads.interactivemedia.v3.internal.jd$2.b[b.adProgress.ordinal()] = 27;
                                                                                                                                    goto label_155;
                                                                                                                                }
                                                                                                                                catch(NoSuchFieldError ) {
                                                                                                                                    try {
                                                                                                                                    label_155:
                                                                                                                                        com.google.ads.interactivemedia.v3.internal.jd$2.b[b.adBreakReady.ordinal()] = 28;
                                                                                                                                        goto label_160;
                                                                                                                                    }
                                                                                                                                    catch(NoSuchFieldError ) {
                                                                                                                                        try {
                                                                                                                                        label_160:
                                                                                                                                            com.google.ads.interactivemedia.v3.internal.jd$2.b[b.adBreakStarted.ordinal()] = 29;
                                                                                                                                            goto label_165;
                                                                                                                                        }
                                                                                                                                        catch(NoSuchFieldError ) {
                                                                                                                                            try {
                                                                                                                                            label_165:
                                                                                                                                                com.google.ads.interactivemedia.v3.internal.jd$2.b[b.adBreakEnded.ordinal()] = 30;
                                                                                                                                                goto label_170;
                                                                                                                                            }
                                                                                                                                            catch(NoSuchFieldError ) {
                                                                                                                                                try {
                                                                                                                                                label_170:
                                                                                                                                                    com.google.ads.interactivemedia.v3.internal.jd$2.b[b.impression.ordinal()] = 31;
                                                                                                                                                    goto label_175;
                                                                                                                                                }
                                                                                                                                                catch(NoSuchFieldError ) {
                                                                                                                                                    try {
                                                                                                                                                    label_175:
                                                                                                                                                        com.google.ads.interactivemedia.v3.internal.jd$2.b[b.mute.ordinal()] = 32;
                                                                                                                                                        goto label_180;
                                                                                                                                                    }
                                                                                                                                                    catch(NoSuchFieldError ) {
                                                                                                                                                        try {
                                                                                                                                                        label_180:
                                                                                                                                                            com.google.ads.interactivemedia.v3.internal.jd$2.b[b.unmute.ordinal()] = 33;
                                                                                                                                                            goto label_185;
                                                                                                                                                        }
                                                                                                                                                        catch(NoSuchFieldError ) {
                                                                                                                                                            try {
                                                                                                                                                            label_185:
                                                                                                                                                                com.google.ads.interactivemedia.v3.internal.jd$2.b[b.volumeChange.ordinal()] = 34;
                                                                                                                                                                goto label_190;
                                                                                                                                                            }
                                                                                                                                                            catch(NoSuchFieldError ) {
                                                                                                                                                                try {
                                                                                                                                                                label_190:
                                                                                                                                                                    com.google.ads.interactivemedia.v3.internal.jd$2.b[b.getViewability.ordinal()] = 35;
                                                                                                                                                                    goto label_195;
                                                                                                                                                                }
                                                                                                                                                                catch(NoSuchFieldError ) {
                                                                                                                                                                    try {
                                                                                                                                                                    label_195:
                                                                                                                                                                        com.google.ads.interactivemedia.v3.internal.jd$2.b[b.reportVastEvent.ordinal()] = 36;
                                                                                                                                                                        goto label_200;
                                                                                                                                                                    }
                                                                                                                                                                    catch(NoSuchFieldError ) {
                                                                                                                                                                    label_200:
                                                                                                                                                                        com.google.ads.interactivemedia.v3.internal.jd$2.a = new int[com.google.ads.interactivemedia.v3.internal.jc$a.values().length];
                                                                                                                                                                        try {
                                                                                                                                                                            com.google.ads.interactivemedia.v3.internal.jd$2.a[com.google.ads.interactivemedia.v3.internal.jc$a.adsManager.ordinal()] = 1;
                                                                                                                                                                            goto label_208;
                                                                                                                                                                        }
                                                                                                                                                                        catch(NoSuchFieldError ) {
                                                                                                                                                                            try {
                                                                                                                                                                            label_208:
                                                                                                                                                                                com.google.ads.interactivemedia.v3.internal.jd$2.a[com.google.ads.interactivemedia.v3.internal.jc$a.activityMonitor.ordinal()] = v1;
                                                                                                                                                                                goto label_212;
                                                                                                                                                                            }
                                                                                                                                                                            catch(NoSuchFieldError ) {
                                                                                                                                                                                try {
                                                                                                                                                                                label_212:
                                                                                                                                                                                    com.google.ads.interactivemedia.v3.internal.jd$2.a[com.google.ads.interactivemedia.v3.internal.jc$a.videoDisplay.ordinal()] = v2;
                                                                                                                                                                                    goto label_216;
                                                                                                                                                                                }
                                                                                                                                                                                catch(NoSuchFieldError ) {
                                                                                                                                                                                    try {
                                                                                                                                                                                    label_216:
                                                                                                                                                                                        com.google.ads.interactivemedia.v3.internal.jd$2.a[com.google.ads.interactivemedia.v3.internal.jc$a.adsLoader.ordinal()] = v3;
                                                                                                                                                                                        goto label_220;
                                                                                                                                                                                    }
                                                                                                                                                                                    catch(NoSuchFieldError ) {
                                                                                                                                                                                        try {
                                                                                                                                                                                        label_220:
                                                                                                                                                                                            com.google.ads.interactivemedia.v3.internal.jd$2.a[com.google.ads.interactivemedia.v3.internal.jc$a.displayContainer.ordinal()] = v4;
                                                                                                                                                                                            goto label_224;
                                                                                                                                                                                        }
                                                                                                                                                                                        catch(NoSuchFieldError ) {
                                                                                                                                                                                            try {
                                                                                                                                                                                            label_224:
                                                                                                                                                                                                com.google.ads.interactivemedia.v3.internal.jd$2.a[com.google.ads.interactivemedia.v3.internal.jc$a.i18n.ordinal()] = v5;
                                                                                                                                                                                                goto label_228;
                                                                                                                                                                                            }
                                                                                                                                                                                            catch(NoSuchFieldError ) {
                                                                                                                                                                                                try {
                                                                                                                                                                                                label_228:
                                                                                                                                                                                                    com.google.ads.interactivemedia.v3.internal.jd$2.a[com.google.ads.interactivemedia.v3.internal.jc$a.omid.ordinal()] = v6;
                                                                                                                                                                                                    goto label_232;
                                                                                                                                                                                                }
                                                                                                                                                                                                catch(NoSuchFieldError ) {
                                                                                                                                                                                                    try {
                                                                                                                                                                                                    label_232:
                                                                                                                                                                                                        com.google.ads.interactivemedia.v3.internal.jd$2.a[com.google.ads.interactivemedia.v3.internal.jc$a.webViewLoaded.ordinal()] = v7;
                                                                                                                                                                                                        goto label_236;
                                                                                                                                                                                                    }
                                                                                                                                                                                                    catch(NoSuchFieldError ) {
                                                                                                                                                                                                        try {
                                                                                                                                                                                                        label_236:
                                                                                                                                                                                                            com.google.ads.interactivemedia.v3.internal.jd$2.a[com.google.ads.interactivemedia.v3.internal.jc$a.log.ordinal()] = v8;
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
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public interface com.google.ads.interactivemedia.v3.internal.jd$a {
        void a(String arg1, String arg2, String arg3);

        void a(String arg1, String arg2);
    }

    public interface com.google.ads.interactivemedia.v3.internal.jd$b {
        void a(String arg1, jf arg2, String arg3, boolean arg4);

        void a(String arg1, AdErrorType arg2, AdErrorCode arg3, String arg4);

        void a(String arg1, jf arg2, List arg3, SortedSet arg4, boolean arg5);

        void a(String arg1, AdErrorType arg2, int arg3, String arg4);
    }

    public class c {
        public final AdEventType a;
        public final com.google.ads.interactivemedia.v3.impl.data.b b;
        public Map c;
        public List d;
        AdProgressInfo e;
        public String f;

        public c(AdEventType arg1, com.google.ads.interactivemedia.v3.impl.data.b arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        public boolean equals(Object arg2) {
            return lx.a(this, arg2, new String[0]);
        }

        public int hashCode() {
            return lz.a(this, new String[0]);
        }
    }

    public interface d {
        void a(Map arg1);

        void a(AdErrorType arg1, AdErrorCode arg2, String arg3);

        void a(AdErrorType arg1, int arg2, String arg3);

        void a(c arg1);
    }

    public interface e {
        void a(b arg1, String arg2);
    }

    private Map a;
    private Map b;
    private final Set c;
    private Map d;
    private Map e;
    private Map f;
    private Map g;
    private final Context h;
    private final je i;
    private jf j;
    private boolean k;
    private final Queue l;
    private long m;
    private TestingConfiguration n;
    private String o;
    private AdsRenderingSettings p;

    public jd(Context arg2, Uri arg3, ImaSdkSettings arg4, TestingConfiguration arg5) {
        super();
        this.a = new HashMap();
        this.b = new HashMap();
        this.c = new HashSet();
        this.d = new HashMap();
        this.e = new HashMap();
        this.f = new HashMap();
        this.g = new HashMap();
        this.k = false;
        this.l = new ArrayDeque();
        this.h = arg2;
        this.n = arg5;
        this.i = new je(arg2, ((a)this));
        this.o = this.a(arg3, arg4).toString();
    }

    public void a(String arg4, String arg5) {
        if(!ks.a(arg4) && !ks.a(arg5)) {
            HashMap v0 = new HashMap();
            ((Map)v0).put("companionId", arg4);
            this.b(new jc(com.google.ads.interactivemedia.v3.internal.jc$a.displayContainer, b.companionView, arg5, v0));
        }
    }

    public void a(com.google.ads.interactivemedia.v3.internal.jd$a arg2, String arg3) {
        this.b.put(arg3, arg2);
    }

    public void a(String arg2) {
        this.b.remove(arg2);
        this.c.add(arg2);
    }

    public void a() {
        this.m = SystemClock.elapsedRealtime();
        this.i.a(this.o);
    }

    public void a(com.google.ads.interactivemedia.v3.internal.jd$b arg2, String arg3) {
        this.d.put(arg3, arg2);
    }

    public void a(BaseDisplayContainer arg2, String arg3) {
        this.g.put(arg3, arg2);
    }

    protected Uri a(Uri arg3, ImaSdkSettings arg4) {
        Uri$Builder v3 = arg3.buildUpon().appendQueryParameter("sdk_version", "a.3.10.2").appendQueryParameter("hl", arg4.getLanguage()).appendQueryParameter("omv", jh.e());
        String v0 = "omvx";
        String v4 = arg4.getEnableOmidExperimentally() ? "1" : "0";
        v3 = v3.appendQueryParameter(v0, v4).appendQueryParameter("app", this.h.getApplicationContext().getPackageName());
        if(this.n != null) {
            v3.appendQueryParameter("tcnfp", new ga().a(new kk()).a(new kj()).a().a(this.n));
        }

        return v3.build();
    }

    static Context a(jd arg0) {
        return arg0.h;
    }

    private Map a(iq arg5, Set arg6) {
        HashMap v0 = new HashMap(arg6.size());
        Iterator v6 = arg6.iterator();
        while(v6.hasNext()) {
            Object v1 = v6.next();
            Object v2 = arg5.a().get(v1);
            if(((CompanionAdSlot)v2).getContainer() == null) {
                return null;
            }

            ((Map)v0).put(v1, ((CompanionAdSlot)v2).getContainer());
        }

        return ((Map)v0);
    }

    private void a(long arg3, String arg5) {
        HashMap v0 = new HashMap();
        ((Map)v0).put("webViewLoadingTime", Long.valueOf(arg3));
        this.b(new jc(com.google.ads.interactivemedia.v3.internal.jc$a.webViewLoaded, b.csi, arg5, v0));
    }

    private void a(ViewGroup arg4, CompanionData arg5, String arg6, CompanionAdSlot arg7) {
        View v5;
        arg4.removeAllViews();
        List v0 = ((is)arg7).a();
        switch(com.google.ads.interactivemedia.v3.internal.jd$2.c[arg5.type().ordinal()]) {
            case 1: 
            case 2: {
                v5 = this.a(arg4.getContext(), arg5, v0);
                break;
            }
            case 3: {
                v5 = this.a(arg4.getContext(), arg5, arg6, v0);
                break;
            }
            default: {
                v5 = null;
                break;
            }
        }

        v5.setTag(arg6);
        ((is)arg7).a(arg6);
        arg4.addView(v5);
    }

    protected View a(Context arg8, CompanionData arg9, String arg10, List arg11) {
        jb v6 = new jb(arg8, this, arg9, arg10, arg11);
        v6.a();
        return ((View)v6);
    }

    protected View a(Context arg2, CompanionData arg3, List arg4) {
        return new it(arg2, this, arg3, arg4);
    }

    private void a(b arg2) {
        switch(com.google.ads.interactivemedia.v3.internal.jd$2.b[arg2.ordinal()]) {
            case 1: {
                jh.b();
                break;
            }
            case 2: {
                jh.c();
                break;
            }
            default: {
                break;
            }
        }
    }

    private void a(b arg5, String arg6, p arg7) {
        switch(com.google.ads.interactivemedia.v3.internal.jd$2.b[arg5.ordinal()]) {
            case 3: {
                this.j = new jf(arg7.adTimeUpdateMs);
                this.k = true;
                this.a(SystemClock.elapsedRealtime() - this.m, arg6);
                this.d();
                break;
            }
            case 4: {
                if(arg7.ln == null) {
                    goto label_55;
                }
                else if(arg7.n == null) {
                    goto label_55;
                }
                else if(arg7.m == null) {
                    goto label_55;
                }
                else {
                    String v5 = "SDK_LOG:";
                    arg6 = String.valueOf(arg7.n);
                    v5 = arg6.length() != 0 ? v5.concat(arg6) : new String(v5);
                    switch(arg7.ln.charAt(0)) {
                        case 68: {
                            Log.d(v5, arg7.m);
                            return;
                        label_55:
                            v5 = "IMASDK";
                            arg6 = String.valueOf(arg7);
                            StringBuilder v0_1 = new StringBuilder(String.valueOf(arg6).length() + 30);
                            v0_1.append("Invalid logging message data: ");
                            v0_1.append(arg6);
                            arg6 = v0_1.toString();
                        label_66:
                            Log.e(v5, arg6);
                            return;
                        }
                        case 73: {
                            Log.i(v5, arg7.m);
                            return;
                        }
                        case 69: 
                        case 83: {
                            arg6 = arg7.m;
                            goto label_66;
                        }
                        case 86: {
                            Log.v(v5, arg7.m);
                            return;
                        }
                        case 87: {
                        label_36:
                            Log.w(v5, arg7.m);
                            return;
                        }
                        default: {
                            arg6 = "IMASDK";
                            String v0 = "Unrecognized log level: ";
                            String v1 = String.valueOf(arg7.ln);
                            v0 = v1.length() != 0 ? v0.concat(v1) : new String(v0);
                            Log.w(arg6, v0);
                            goto label_36;
                        }
                    }
                }

                goto label_66;
            }
            default: {
                this.a("other", arg5);
                break;
            }
        }
    }

    private void a(String arg4, b arg5) {
        String v5 = String.valueOf(arg5);
        StringBuilder v2 = new StringBuilder(String.valueOf(v5).length() + 43 + String.valueOf(arg4).length());
        v2.append("Illegal message type ");
        v2.append(v5);
        v2.append(" received for ");
        v2.append(arg4);
        v2.append(" channel");
        Log.i("IMASDK", v2.toString());
    }

    public void a(AdsRenderingSettings arg1) {
        this.p = arg1;
    }

    public void a(jc arg6) {
        Object v0 = arg6.c();
        String v1 = arg6.d();
        b v2 = arg6.b();
        switch(com.google.ads.interactivemedia.v3.internal.jd$2.a[arg6.a().ordinal()]) {
            case 1: {
                this.f(v2, v1, ((p)v0));
                break;
            }
            case 2: {
                this.g(v2, v1, ((p)v0));
                break;
            }
            case 3: {
                this.e(v2, v1, ((p)v0));
                break;
            }
            case 4: {
                this.d(v2, v1, ((p)v0));
                break;
            }
            case 5: {
                this.b(v2, v1, ((p)v0));
                break;
            }
            case 6: {
                this.c(v2, v1, ((p)v0));
                break;
            }
            case 7: {
                this.a(v2);
                break;
            }
            case 8: 
            case 9: {
                this.a(v2, v1, ((p)v0));
                break;
            }
            default: {
                String v6 = String.valueOf(arg6.a());
                StringBuilder v2_1 = new StringBuilder(String.valueOf(v6).length() + 25);
                v2_1.append("Unknown message channel: ");
                v2_1.append(v6);
                Log.e("IMASDK", v2_1.toString());
                break;
            }
        }
    }

    public void a(d arg2, String arg3) {
        this.a.put(arg3, arg2);
    }

    public void a(jo arg2, String arg3) {
        this.f.put(arg3, arg2);
    }

    public WebView b() {
        return this.i.a();
    }

    public void b(jc arg2) {
        this.l.add(arg2);
        this.d();
    }

    private String b(String arg3, String arg4) {
        if(arg4 != null) {
            if(arg4.length() == 0) {
            }
            else {
                StringBuilder v1 = new StringBuilder(String.valueOf(arg3).length() + 12 + String.valueOf(arg4).length());
                v1.append(arg3);
                v1.append(" Caused by: ");
                v1.append(arg4);
                arg3 = v1.toString();
            }
        }

        return arg3;
    }

    private void b(b arg7, String arg8, p arg9) {
        String v9;
        AdErrorCode v8;
        AdErrorType v7_1;
        Object v0 = this.g.get(arg8);
        Object v1 = this.a.get(arg8);
        Object v2 = this.f.get(arg8);
        if(v0 != null && v1 != null) {
            if(v2 == null) {
            }
            else {
                if(!((jo)v2).b(arg7, arg9)) {
                    if(com.google.ads.interactivemedia.v3.internal.jd$2.b[arg7.ordinal()] != 5) {
                        this.a(com.google.ads.interactivemedia.v3.internal.jc$a.displayContainer.toString(), arg7);
                    }
                    else {
                        if(arg9 == null || arg9.companions == null) {
                            v7_1 = AdErrorType.LOAD;
                            v8 = AdErrorCode.INTERNAL_ERROR;
                            v9 = "Display companions message requires companions in data.";
                        }
                        else {
                            Map v7 = this.a(((iq)v0), arg9.companions.keySet());
                            ((d)v1).a(arg9.companions);
                            if(v7 == null) {
                                v7_1 = AdErrorType.LOAD;
                                v8 = AdErrorCode.INTERNAL_ERROR;
                                v9 = "Display requested for invalid companion slot.";
                            }
                            else {
                                if(this.p != null && !this.p.isRenderCompanions()) {
                                    return;
                                }

                                Iterator v1_1 = v7.keySet().iterator();
                                while(true) {
                                    if(!v1_1.hasNext()) {
                                        return;
                                    }

                                    v2 = v1_1.next();
                                    this.a(v7.get(v2), arg9.companions.get(v2), arg8, ((iq)v0).a().get(v2));
                                }
                            }
                        }

                        ((d)v1).a(v7_1, v8, v9);
                    }
                }

                return;
            }
        }

        String v7_2 = String.valueOf(arg7);
        StringBuilder v1_2 = new StringBuilder(String.valueOf(v7_2).length() + 60 + String.valueOf(arg8).length());
        v1_2.append("Received displayContainer message: ");
        v1_2.append(v7_2);
        v1_2.append(" for invalid session id: ");
        v1_2.append(arg8);
        Log.e("IMASDK", v1_2.toString());
    }

    public void b(String arg2) {
        this.a.remove(arg2);
        this.g.remove(arg2);
        this.f.remove(arg2);
    }

    public void c(String arg2) {
        if(arg2 != null && arg2.length() > 0) {
            new AsyncTask(arg2) {
                protected Void a(Void[] arg3) {
                    Intent v3 = new Intent("android.intent.action.VIEW", Uri.parse(this.a));
                    if(!(jd.a(this.b) instanceof Activity)) {
                        v3.setFlags(268435456);
                    }

                    jd.a(this.b).startActivity(v3);
                    return null;
                }

                protected Object doInBackground(Object[] arg1) {
                    return this.a(((Void[])arg1));
                }
            }.execute(new Void[0]);
        }
    }

    private void c(b arg2, String arg3, p arg4) {
        Object v3 = this.e.get(arg3);
        if(v3 != null) {
            ((e)v3).a(arg2, arg4.translation);
        }
    }

    TestingConfiguration c() {
        return this.n;
    }

    private void d() {
        while(this.k) {
            if(this.l.isEmpty()) {
                return;
            }

            this.i.a(this.l.remove());
        }
    }

    private void d(b arg8, String arg9, p arg10) {
        String v8;
        Object v1 = this.d.get(arg9);
        if(v1 == null) {
            v8 = String.valueOf(arg8);
            StringBuilder v1_1 = new StringBuilder(String.valueOf(v8).length() + 51 + String.valueOf(arg9).length());
            v1_1.append("Received request message: ");
            v1_1.append(v8);
            v1_1.append(" for invalid session id: ");
            v1_1.append(arg9);
            Log.e("IMASDK", v1_1.toString());
            return;
        }

        switch(com.google.ads.interactivemedia.v3.internal.jd$2.b[arg8.ordinal()]) {
            case 6: {
                if(arg10 == null) {
                    ((com.google.ads.interactivemedia.v3.internal.jd$b)v1).a(arg9, AdErrorType.LOAD, AdErrorCode.INTERNAL_ERROR, "adsLoaded message did not contain cue points.");
                    return;
                }

                ((com.google.ads.interactivemedia.v3.internal.jd$b)v1).a(arg9, this.j, arg10.adCuePoints, arg10.internalCuePoints, arg10.monitorAppLifecycle);
                break;
            }
            case 7: {
                ((com.google.ads.interactivemedia.v3.internal.jd$b)v1).a(arg9, this.j, arg10.streamId, arg10.monitorAppLifecycle);
                v8 = "IMASDK";
                arg9 = "Stream initialized with streamId: ";
                String v10 = String.valueOf(arg10.streamId);
                arg9 = v10.length() != 0 ? arg9.concat(v10) : new String(arg9);
                Log.i(v8, arg9);
                break;
            }
            case 8: {
                ((com.google.ads.interactivemedia.v3.internal.jd$b)v1).a(arg9, AdErrorType.LOAD, arg10.errorCode, this.b(arg10.errorMessage, arg10.innerError));
                break;
            }
            default: {
                this.a(com.google.ads.interactivemedia.v3.internal.jc$a.adsLoader.toString(), arg8);
                break;
            }
        }
    }

    private void e(b arg3, String arg4, p arg5) {
        Object v0 = this.f.get(arg4);
        if(v0 == null) {
            String v3 = String.valueOf(arg3);
            StringBuilder v1 = new StringBuilder(String.valueOf(v3).length() + 56 + String.valueOf(arg4).length());
            v1.append("Received videoDisplay message: ");
            v1.append(v3);
            v1.append(" for invalid session id: ");
            v1.append(arg4);
            Log.w("IMASDK", v1.toString());
            return;
        }

        ((jo)v0).a(arg3, arg5);
    }

    private void f(b arg12, String arg13, p arg14) {
        c v12_1;
        Object v0 = this.a.get(arg13);
        if(v0 == null) {
            String v12 = String.valueOf(arg12);
            StringBuilder v1 = new StringBuilder(String.valueOf(v12).length() + 51 + String.valueOf(arg13).length());
            v1.append("Received manager message: ");
            v1.append(v12);
            v1.append(" for invalid session id: ");
            v1.append(arg13);
            Log.e("IMASDK", v1.toString());
            return;
        }

        com.google.ads.interactivemedia.v3.impl.data.b v13 = null;
        com.google.ads.interactivemedia.v3.impl.data.b v1_1 = arg14 == null || arg14.adData == null ? v13 : arg14.adData;
        int v2 = com.google.ads.interactivemedia.v3.internal.jd$2.b[arg12.ordinal()];
        if(v2 != 4) {
            switch(v2) {
                case 8: {
                    goto label_169;
                }
                case 10: {
                    goto label_156;
                }
                case 11: {
                    goto label_152;
                }
                case 12: {
                    goto label_148;
                }
                case 13: {
                    goto label_144;
                }
                case 14: {
                    goto label_140;
                }
                case 15: {
                    goto label_119;
                }
                case 16: {
                    goto label_115;
                }
                case 17: {
                    goto label_111;
                }
                case 18: {
                    goto label_107;
                }
                case 19: {
                    goto label_103;
                }
                case 20: {
                    goto label_99;
                }
                case 21: {
                    goto label_95;
                }
                case 22: {
                    goto label_91;
                }
                case 23: {
                    goto label_87;
                }
                case 24: {
                    goto label_83;
                }
                case 25: {
                    goto label_79;
                }
                case 26: {
                    goto label_73;
                }
                case 27: {
                    goto label_59;
                }
                case 28: {
                    goto label_47;
                }
                case 29: {
                    goto label_43;
                }
                case 30: {
                    goto label_39;
                }
                case 9: 
                case 31: 
                case 32: 
                case 33: 
                case 34: {
                    return;
                }
            }

            this.a(com.google.ads.interactivemedia.v3.internal.jc$a.adsManager.toString(), arg12);
            return;
        label_99:
            v12_1 = new c(AdEventType.FIRST_QUARTILE, v1_1);
            goto label_182;
        label_103:
            v12_1 = new c(AdEventType.RESUMED, v1_1);
            goto label_182;
        label_39:
            v12_1 = new c(AdEventType.AD_BREAK_ENDED, v1_1);
            goto label_182;
        label_169:
            ((d)v0).a(AdErrorType.PLAY, arg14.errorCode, this.b(arg14.errorMessage, arg14.innerError));
            return;
        label_107:
            v12_1 = new c(AdEventType.PAUSED, v1_1);
            goto label_182;
        label_43:
            v12_1 = new c(AdEventType.AD_BREAK_STARTED, v1_1);
            goto label_182;
        label_111:
            v12_1 = new c(AdEventType.STARTED, v1_1);
            goto label_182;
        label_47:
            v12_1 = new c(AdEventType.AD_BREAK_READY, v13);
            v12_1.c = new android.support.v4.f.a(1);
            v12_1.c.put("adBreakTime", arg14.adBreakTime);
            goto label_182;
        label_115:
            v12_1 = new c(AdEventType.SKIPPED, v1_1);
            goto label_182;
        label_119:
            v12_1 = new c(AdEventType.CUEPOINTS_CHANGED, v13);
            v12_1.d = new ArrayList();
            Iterator v13_1 = arg14.cuepoints.iterator();
            while(true) {
                if(v13_1.hasNext()) {
                    Object v14 = v13_1.next();
                    v12_1.d.add(new iw(((n)v14).start(), ((n)v14).end(), ((n)v14).played()));
                    continue;
                }
                else {
                    goto label_182;
                }
            }

        label_59:
            v12_1 = new c(AdEventType.AD_PROGRESS, v1_1);
            v12_1.e = new ig(arg14.currentTime, arg14.duration, arg14.adPosition, arg14.totalAds, arg14.adBreakDuration);
            goto label_182;
        label_73:
            v12_1 = new c(AdEventType.ICON_TAPPED, v13);
            v12_1.f = arg14.clickThroughUrl;
            goto label_182;
        label_140:
            v12_1 = new c(AdEventType.ALL_ADS_COMPLETED, v13);
            goto label_182;
        label_79:
            v12_1 = new c(AdEventType.TAPPED, v1_1);
            goto label_182;
        label_144:
            v12_1 = new c(AdEventType.COMPLETED, v1_1);
            goto label_182;
        label_83:
            v12_1 = new c(AdEventType.SKIPPABLE_STATE_CHANGED, v1_1);
            goto label_182;
        label_148:
            v12_1 = new c(AdEventType.CONTENT_RESUME_REQUESTED, v13);
            goto label_182;
        label_87:
            v12_1 = new c(AdEventType.CLICKED, v1_1);
            goto label_182;
        label_152:
            v12_1 = new c(AdEventType.CONTENT_PAUSE_REQUESTED, v13);
            goto label_182;
        label_91:
            v12_1 = new c(AdEventType.THIRD_QUARTILE, v1_1);
            goto label_182;
        label_156:
            if(v1_1 != null) {
                v12_1 = new c(AdEventType.LOADED, v1_1);
            }
            else {
                Log.e("IMASDK", "Ad loaded message requires adData");
                ((d)v0).a(AdErrorType.LOAD, AdErrorCode.INTERNAL_ERROR, "Ad loaded message did not contain adData.");
                return;
            label_95:
                v12_1 = new c(AdEventType.MIDPOINT, v1_1);
            }

            goto label_182;
        }
        else {
            v12_1 = new c(AdEventType.LOG, v1_1);
            v12_1.c = arg14.logData.constructMap();
        label_182:
            ((d)v0).a(v12_1);
        }
    }

    private void g(b arg3, String arg4, p arg5) {
        StringBuilder v1;
        String v3;
        String v5;
        if(this.c.contains(arg4)) {
            return;
        }

        Object v0 = this.b.get(arg4);
        if(v0 == null) {
            v5 = "IMASDK";
            v3 = String.valueOf(arg3);
            v1 = new StringBuilder(String.valueOf(v3).length() + 51 + String.valueOf(arg4).length());
            v1.append("Received monitor message: ");
            v1.append(v3);
            v1.append(" for invalid session id: ");
            v1.append(arg4);
        }
        else if(arg5 == null) {
            v5 = "IMASDK";
            v3 = String.valueOf(arg3);
            v1 = new StringBuilder(String.valueOf(v3).length() + 56 + String.valueOf(arg4).length());
            v1.append("Received monitor message: ");
            v1.append(v3);
            v1.append(" for session id: ");
            v1.append(arg4);
            v1.append(" with no data");
        }
        else {
            goto label_46;
        }

        Log.e(v5, v1.toString());
        return;
    label_46:
        switch(com.google.ads.interactivemedia.v3.internal.jd$2.b[arg3.ordinal()]) {
            case 35: {
                ((com.google.ads.interactivemedia.v3.internal.jd$a)v0).a(arg5.queryId, arg5.eventId);
                break;
            }
            case 36: {
                ((com.google.ads.interactivemedia.v3.internal.jd$a)v0).a(arg5.queryId, arg5.eventId, arg5.vastEvent);
                break;
            }
            default: {
                this.a(com.google.ads.interactivemedia.v3.internal.jc$a.activityMonitor.toString(), arg3);
                break;
            }
        }
    }
}

