package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import com.google.ads.interactivemedia.v3.api.UiElement;
import com.google.ads.interactivemedia.v3.impl.data.p;
import com.google.ads.interactivemedia.v3.impl.data.r;
import java.lang.reflect.Type;
import java.net.MalformedURLException;

public class jc {
    class com.google.ads.interactivemedia.v3.internal.jc$1 implements gm {
        com.google.ads.interactivemedia.v3.internal.jc$1() {
            super();
        }

        public gf a(CompanionAdSlot arg3, Type arg4, gl arg5) {
            int v5 = arg3.getWidth();
            int v3 = arg3.getHeight();
            StringBuilder v0 = new StringBuilder(23);
            v0.append(v5);
            v0.append("x");
            v0.append(v3);
            return new gk(v0.toString());
        }

        public gf a(Object arg1, Type arg2, gl arg3) {
            return this.a(((CompanionAdSlot)arg1), arg2, arg3);
        }
    }

    public enum a {
        public static final enum a activityMonitor;
        public static final enum a adsLoader;
        public static final enum a adsManager;
        public static final enum a contentTimeUpdate;
        public static final enum a displayContainer;
        public static final enum a i18n;
        public static final enum a log;
        public static final enum a omid;
        public static final enum a videoDisplay;
        public static final enum a webViewLoaded;

        static {
            a.activityMonitor = new a("activityMonitor", 0);
            a.adsManager = new a("adsManager", 1);
            a.adsLoader = new a("adsLoader", 2);
            a.contentTimeUpdate = new a("contentTimeUpdate", 3);
            a.displayContainer = new a("displayContainer", 4);
            a.i18n = new a("i18n", 5);
            a.log = new a("log", 6);
            a.omid = new a("omid", 7);
            a.videoDisplay = new a("videoDisplay", 8);
            a.webViewLoaded = new a("webViewLoaded", 9);
            a.a = new a[]{a.activityMonitor, a.adsManager, a.adsLoader, a.contentTimeUpdate, a.displayContainer, a.i18n, a.log, a.omid, a.videoDisplay, a.webViewLoaded};
        }

        private a(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static a valueOf(String arg1) {
            return Enum.valueOf(a.class, arg1);
        }

        public static a[] values() {
            // Method was not decompiled
        }
    }

    public enum b {
        public static final enum b adBreakEnded;
        public static final enum b adBreakReady;
        public static final enum b adBreakStarted;
        public static final enum b adMetadata;
        public static final enum b adProgress;
        public static final enum b adRemainingTime;
        public static final enum b adsLoaded;
        public static final enum b allAdsCompleted;
        public static final enum b appStateChanged;
        public static final enum b click;
        public static final enum b companionView;
        public static final enum b complete;
        public static final enum b contentComplete;
        public static final enum b contentPauseRequested;
        public static final enum b contentResumeRequested;
        public static final enum b contentTimeUpdate;
        public static final enum b csi;
        public static final enum b cuepointsChanged;
        public static final enum b destroy;
        public static final enum b discardAdBreak;
        public static final enum b displayCompanions;
        public static final enum b end;
        public static final enum b error;
        public static final enum b firstquartile;
        public static final enum b focusSkipButton;
        public static final enum b fullscreen;
        public static final enum b getViewability;
        public static final enum b hide;
        public static final enum b impression;
        public static final enum b init;
        public static final enum b initialized;
        public static final enum b learnMore;
        public static final enum b load;
        public static final enum b loadStream;
        public static final enum b loaded;
        public static final enum b log;
        public static final enum b midpoint;
        public static final enum b mute;
        public static final enum b omidReady;
        public static final enum b omidUnavailable;
        public static final enum b pause;
        public static final enum b play;
        public static final enum b preSkipButton;
        public static final enum b registerFriendlyObstructions;
        public static final enum b reportVastEvent;
        public static final enum b requestAds;
        public static final enum b requestNextAdBreak;
        public static final enum b requestStream;
        public static final enum b resume;
        public static final enum b setPlaybackOptions;
        public static final enum b showVideo;
        public static final enum b skip;
        public static final enum b skipButton;
        public static final enum b skipShown;
        public static final enum b skippableStateChanged;
        public static final enum b start;
        public static final enum b startTracking;
        public static final enum b stop;
        public static final enum b stopTracking;
        public static final enum b streamInitialized;
        public static final enum b thirdquartile;
        public static final enum b timedMetadata;
        public static final enum b timeupdate;
        public static final enum b unload;
        public static final enum b unmute;
        public static final enum b videoClicked;
        public static final enum b videoIconClicked;
        public static final enum b viewability;
        public static final enum b volumeChange;

        static {
            b.adBreakEnded = new b("adBreakEnded", 0);
            b.adBreakReady = new b("adBreakReady", 1);
            b.adBreakStarted = new b("adBreakStarted", 2);
            b.adMetadata = new b("adMetadata", 3);
            b.adProgress = new b("adProgress", 4);
            b.adsLoaded = new b("adsLoaded", 5);
            b.allAdsCompleted = new b("allAdsCompleted", 6);
            b.appStateChanged = new b("appStateChanged", 7);
            b.click = new b("click", 8);
            b.complete = new b("complete", 9);
            b.companionView = new b("companionView", 10);
            b.contentComplete = new b("contentComplete", 11);
            b.contentPauseRequested = new b("contentPauseRequested", 12);
            b.contentResumeRequested = new b("contentResumeRequested", 13);
            b.contentTimeUpdate = new b("contentTimeUpdate", 14);
            b.csi = new b("csi", 15);
            b.cuepointsChanged = new b("cuepointsChanged", 16);
            b.discardAdBreak = new b("discardAdBreak", 17);
            b.displayCompanions = new b("displayCompanions", 18);
            b.destroy = new b("destroy", 19);
            b.end = new b("end", 20);
            b.error = new b("error", 21);
            b.firstquartile = new b("firstquartile", 22);
            b.focusSkipButton = new b("focusSkipButton", 23);
            b.fullscreen = new b("fullscreen", 24);
            b.getViewability = new b("getViewability", 25);
            b.hide = new b("hide", 26);
            b.impression = new b("impression", 27);
            b.init = new b("init", 28);
            b.initialized = new b("initialized", 29);
            b.load = new b("load", 30);
            b.loaded = new b("loaded", 31);
            b.loadStream = new b("loadStream", 32);
            b.log = new b("log", 33);
            b.midpoint = new b("midpoint", 34);
            b.mute = new b("mute", 35);
            b.omidReady = new b("omidReady", 36);
            b.omidUnavailable = new b("omidUnavailable", 37);
            b.pause = new b("pause", 38);
            b.play = new b("play", 39);
            b.registerFriendlyObstructions = new b("registerFriendlyObstructions", 40);
            b.reportVastEvent = new b("reportVastEvent", 41);
            b.resume = new b("resume", 42);
            b.requestAds = new b("requestAds", 43);
            b.requestNextAdBreak = new b("requestNextAdBreak", 44);
            b.requestStream = new b("requestStream", 45);
            b.setPlaybackOptions = new b("setPlaybackOptions", 46);
            b.showVideo = new b("showVideo", 47);
            b.skip = new b("skip", 48);
            b.skippableStateChanged = new b("skippableStateChanged", 49);
            b.skipShown = new b("skipShown", 50);
            b.start = new b("start", 51);
            b.startTracking = new b("startTracking", 52);
            b.stop = new b("stop", 53);
            b.stopTracking = new b("stopTracking", 54);
            b.streamInitialized = new b("streamInitialized", 55);
            b.thirdquartile = new b("thirdquartile", 56);
            b.timedMetadata = new b("timedMetadata", 57);
            b.timeupdate = new b("timeupdate", 58);
            b.unload = new b("unload", 59);
            b.unmute = new b("unmute", 60);
            b.viewability = new b("viewability", 61);
            b.videoClicked = new b("videoClicked", 62);
            b.videoIconClicked = new b("videoIconClicked", 63);
            b.volumeChange = new b("volumeChange", 64);
            b.adRemainingTime = new b("adRemainingTime", 65);
            b.learnMore = new b("learnMore", 66);
            b.preSkipButton = new b("preSkipButton", 67);
            b.skipButton = new b("skipButton", 68);
            b.a = new b[]{b.adBreakEnded, b.adBreakReady, b.adBreakStarted, b.adMetadata, b.adProgress, b.adsLoaded, b.allAdsCompleted, b.appStateChanged, b.click, b.complete, b.companionView, b.contentComplete, b.contentPauseRequested, b.contentResumeRequested, b.contentTimeUpdate, b.csi, b.cuepointsChanged, b.discardAdBreak, b.displayCompanions, b.destroy, b.end, b.error, b.firstquartile, b.focusSkipButton, b.fullscreen, b.getViewability, b.hide, b.impression, b.init, b.initialized, b.load, b.loaded, b.loadStream, b.log, b.midpoint, b.mute, b.omidReady, b.omidUnavailable, b.pause, b.play, b.registerFriendlyObstructions, b.reportVastEvent, b.resume, b.requestAds, b.requestNextAdBreak, b.requestStream, b.setPlaybackOptions, b.showVideo, b.skip, b.skippableStateChanged, b.skipShown, b.start, b.startTracking, b.stop, b.stopTracking, b.streamInitialized, b.thirdquartile, b.timedMetadata, b.timeupdate, b.unload, b.unmute, b.viewability, b.videoClicked, b.videoIconClicked, b.volumeChange, b.adRemainingTime, b.learnMore, b.preSkipButton, b.skipButton};
        }

        private b(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static b valueOf(String arg1) {
            return Enum.valueOf(b.class, arg1);
        }

        public static b[] values() {
            // Method was not decompiled
        }
    }

    private static final fz a;
    private final a b;
    private final Object c;
    private final String d;
    private final b e;

    static {
        jc.a = new ga().a(UiElement.class, r.GSON_TYPE_ADAPTER).a(CompanionAdSlot.class, new com.google.ads.interactivemedia.v3.internal.jc$1()).a(new kk()).a();
    }

    public jc(a arg2, b arg3, String arg4) {
        this(arg2, arg3, arg4, null);
    }

    public jc(a arg1, b arg2, String arg3, Object arg4) {
        super();
        this.b = arg1;
        this.e = arg2;
        this.d = arg3;
        this.c = arg4;
    }

    public static jc a(String arg6) {
        Uri v6 = Uri.parse(arg6);
        String v0 = v6.getPath().substring(1);
        if(v6.getQueryParameter("sid") != null) {
            return new jc(a.valueOf(v0), b.valueOf(v6.getQueryParameter("type")), v6.getQueryParameter("sid"), jc.a.a(v6.getQueryParameter("data"), p.class));
        }

        throw new MalformedURLException("Session id must be provided in message.");
    }

    public a a() {
        return this.b;
    }

    public b b() {
        return this.e;
    }

    public Object c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        com.google.ads.interactivemedia.v3.internal.lb$a v0 = new com.google.ads.interactivemedia.v3.internal.lb$a().a("type", this.e).a("sid", this.d);
        if(this.c != null) {
            v0.a("data", this.c);
        }

        return String.format("%s(\'%s\', %s);", "javascript:adsense.mobileads.afmanotify.receiveMessage", this.b, jc.a.a(v0.a()));
    }

    public boolean equals(Object arg5) {
        if(this == (((jc)arg5))) {
            return 1;
        }

        if(arg5 == null) {
            return 0;
        }

        if(!(arg5 instanceof jc)) {
            return 0;
        }

        if(this.b != ((jc)arg5).b) {
            return 0;
        }

        if(!ko.a(this.c, ((jc)arg5).c)) {
            return 0;
        }

        if(!ko.a(this.d, ((jc)arg5).d)) {
            return 0;
        }

        if(this.e != ((jc)arg5).e) {
            return 0;
        }

        return 1;
    }

    public int hashCode() {
        return ko.a(new Object[]{this.b, this.c, this.d, this.e});
    }

    public String toString() {
        return String.format("JavaScriptMessage [command=%s, type=%s, sid=%s, data=%s]", this.b, this.e, this.d, this.c);
    }
}

