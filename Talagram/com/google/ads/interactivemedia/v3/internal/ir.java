package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.util.Log;
import com.google.ads.interactivemedia.v3.api.Ad;
import com.google.ads.interactivemedia.v3.api.AdError$AdErrorCode;
import com.google.ads.interactivemedia.v3.api.AdError$AdErrorType;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent$AdErrorListener;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.AdEvent$AdEventListener;
import com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.ads.interactivemedia.v3.api.AdProgressInfo;
import com.google.ads.interactivemedia.v3.api.AdsRenderingSettings;
import com.google.ads.interactivemedia.v3.api.BaseDisplayContainer;
import com.google.ads.interactivemedia.v3.api.BaseManager;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.ads.interactivemedia.v3.impl.data.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

abstract class ir implements BaseManager, d {
    class com.google.ads.interactivemedia.v3.internal.ir$1 {
        static {
            com.google.ads.interactivemedia.v3.internal.ir$1.a = new int[AdEventType.values().length];
            try {
                com.google.ads.interactivemedia.v3.internal.ir$1.a[AdEventType.LOADED.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    com.google.ads.interactivemedia.v3.internal.ir$1.a[AdEventType.STARTED.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        com.google.ads.interactivemedia.v3.internal.ir$1.a[AdEventType.AD_BREAK_ENDED.ordinal()] = 3;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                        try {
                        label_19:
                            com.google.ads.interactivemedia.v3.internal.ir$1.a[AdEventType.COMPLETED.ordinal()] = 4;
                            goto label_24;
                        }
                        catch(NoSuchFieldError ) {
                            try {
                            label_24:
                                com.google.ads.interactivemedia.v3.internal.ir$1.a[AdEventType.SKIPPED.ordinal()] = 5;
                                goto label_29;
                            }
                            catch(NoSuchFieldError ) {
                                try {
                                label_29:
                                    com.google.ads.interactivemedia.v3.internal.ir$1.a[AdEventType.CONTENT_PAUSE_REQUESTED.ordinal()] = 6;
                                    goto label_34;
                                }
                                catch(NoSuchFieldError ) {
                                    try {
                                    label_34:
                                        com.google.ads.interactivemedia.v3.internal.ir$1.a[AdEventType.CONTENT_RESUME_REQUESTED.ordinal()] = 7;
                                        goto label_39;
                                    }
                                    catch(NoSuchFieldError ) {
                                        try {
                                        label_39:
                                            com.google.ads.interactivemedia.v3.internal.ir$1.a[AdEventType.CLICKED.ordinal()] = 8;
                                            goto label_44;
                                        }
                                        catch(NoSuchFieldError ) {
                                            try {
                                            label_44:
                                                com.google.ads.interactivemedia.v3.internal.ir$1.a[AdEventType.ICON_TAPPED.ordinal()] = 9;
                                                goto label_49;
                                            }
                                            catch(NoSuchFieldError ) {
                                                try {
                                                label_49:
                                                    com.google.ads.interactivemedia.v3.internal.ir$1.a[AdEventType.SKIPPABLE_STATE_CHANGED.ordinal()] = 10;
                                                    goto label_54;
                                                }
                                                catch(NoSuchFieldError ) {
                                                    try {
                                                    label_54:
                                                        com.google.ads.interactivemedia.v3.internal.ir$1.a[AdEventType.AD_PROGRESS.ordinal()] = 11;
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

    protected final jd a;
    protected final String b;
    protected AdsRenderingSettings c;
    protected jo d;
    protected iu e;
    protected iv f;
    protected boolean g;
    private final List h;
    private final ix i;
    private final Context j;
    private b k;
    private AdProgressInfo l;
    private ib m;
    private jh n;

    protected ir(String arg2, jd arg3, jf arg4, BaseDisplayContainer arg5, ib arg6, jh arg7, Context arg8, boolean arg9) {
        super();
        this.g = false;
        this.h = new ArrayList(1);
        this.i = new ix();
        this.b = arg2;
        this.a = arg3;
        this.j = arg8;
        this.c = new im();
        this.m = arg6 != null ? arg6 : new ib(arg2, arg3, arg5.getAdContainer());
        this.m.a(arg9);
        this.n = this.a(arg7, arg5);
        arg3.a(((d)this), arg2);
        this.m.a();
    }

    private jh a(jh arg3, BaseDisplayContainer arg4) {
        if(arg3 == null) {
            return null;
        }

        arg3.a(this.b);
        arg3.b(arg4.getAdContainer());
        this.addAdEventListener(((AdEventListener)arg3));
        this.addAdErrorListener(((AdErrorListener)arg3));
        Iterator v0 = ((iq)arg4).b().iterator();
        while(v0.hasNext()) {
            arg3.c(v0.next());
        }

        ((iq)arg4).a(((a)arg3));
        return arg3;
    }

    private void a(AdEventType arg2) {
        this.a(arg2, null);
    }

    private void a(AdEventType arg3, Map arg4) {
        ie v0 = new ie(arg3, this.k, arg4);
        Iterator v3 = this.h.iterator();
        while(v3.hasNext()) {
            v3.next().onAdEvent(((AdEvent)v0));
        }
    }

    void a(AdErrorEvent arg2) {
        this.i.a(arg2);
    }

    protected void a() {
        this.d.e();
        if(this.n != null && (this.n.f())) {
            Log.d("IMASDK", "OMID ad session ended on BaseManager destroy.");
        }

        if(this.f != null) {
            this.f.c();
        }

        this.m.b();
        this.a.b(this.b);
        this.k = null;
    }

    public void a(AdErrorType arg3, int arg4, String arg5) {
        this.b(new id(new AdError(arg3, arg4, arg5)));
    }

    public void a(AdErrorType arg3, AdErrorCode arg4, String arg5) {
        this.b(new id(new AdError(arg3, arg4, arg5)));
    }

    void a(b arg1) {
        this.k = arg1;
    }

    protected void a(com.google.ads.interactivemedia.v3.internal.jc$b arg5) {
        this.a.b(new jc(com.google.ads.interactivemedia.v3.internal.jc$a.adsManager, arg5, this.b));
    }

    public void a(c arg5) {
        AdEventType v0 = arg5.a;
        b v1 = arg5.b;
        AdProgressInfo v3 = null;
        switch(com.google.ads.interactivemedia.v3.internal.ir$1.a[v0.ordinal()]) {
            case 1: {
                this.a(v1);
                break;
            }
            case 2: {
                if(v1 != null) {
                    this.a(v1);
                }

                this.d.a(v1);
                break;
            }
            case 3: {
            label_46:
                this.l = v3;
                break;
            }
            case 4: 
            case 5: {
                this.d.a();
                goto label_46;
            }
            case 6: {
                if(this.f != null) {
                    this.f.c();
                }

                this.m.c();
                break;
            }
            case 7: {
                if(this.f != null) {
                    this.f.b();
                }

                this.m.d();
                break;
            }
            case 8: {
                String v1_1 = v1.getClickThruUrl();
                if(jt.a(v1_1)) {
                    goto label_54;
                }

                this.a.c(v1_1);
                break;
            }
            case 9: {
                if(jt.a(arg5.f)) {
                    goto label_54;
                }

                this.a.c(arg5.f);
                break;
            }
            case 10: {
                if(!this.c.getFocusSkipButtonWhenAvailable()) {
                    goto label_54;
                }

                this.a(this.b);
                break;
            }
            case 11: {
                this.l = arg5.e;
                break;
            }
            default: {
                break;
            }
        }

    label_54:
        if(arg5.c != null) {
            this.a(v0, arg5.c);
        }
        else {
            this.a(v0);
        }

        if(v0 == AdEventType.COMPLETED || v0 == AdEventType.SKIPPED) {
            this.a(((b)v3));
        }
    }

    protected void a(String arg5) {
        if(jr.a(this.j, this.a.c())) {
            this.a.b().requestFocus();
            this.a.b(new jc(com.google.ads.interactivemedia.v3.internal.jc$a.videoDisplay, com.google.ads.interactivemedia.v3.internal.jc$b.focusSkipButton, arg5));
        }
    }

    public void a(Map arg1) {
    }

    public void addAdErrorListener(AdErrorListener arg2) {
        this.i.a(arg2);
    }

    public void addAdEventListener(AdEventListener arg2) {
        this.h.add(arg2);
    }

    private void b(AdErrorEvent arg2) {
        this.l = null;
        this.a(arg2);
    }

    public VideoProgressUpdate getAdProgress() {
        VideoProgressUpdate v0 = this.g ? VideoProgressUpdate.VIDEO_TIME_NOT_READY : this.d.getAdProgress();
        return v0;
    }

    public AdProgressInfo getAdProgressInfo() {
        return this.l;
    }

    public Ad getCurrentAd() {
        return this.k;
    }

    public void init() {
        this.init(null);
    }

    public void init(AdsRenderingSettings arg6) {
        if(arg6 == null) {
            arg6 = this.c;
        }

        this.c = arg6;
        HashMap v6 = new HashMap();
        ((Map)v6).put("adsRenderingSettings", this.c);
        if(this.f != null) {
            VideoProgressUpdate v0 = this.f.a();
            if(!v0.equals(VideoProgressUpdate.VIDEO_TIME_NOT_READY)) {
                double v0_1 = ((double)v0.getCurrentTime());
                StringBuilder v4 = new StringBuilder(68);
                v4.append("AdsManager.init -> Setting contentStartTime ");
                v4.append(v0_1);
                Log.d("IMASDK", v4.toString());
                ((Map)v6).put("contentStartTime", Double.valueOf(v0_1));
            }
        }

        if(!this.isCustomPlaybackUsed()) {
            ((Map)v6).put("sdkOwnedPlayer", Boolean.valueOf(true));
        }

        this.d.a(this.c.getDisableUi());
        this.a.a(this.c);
        this.a.b(new jc(com.google.ads.interactivemedia.v3.internal.jc$a.adsManager, com.google.ads.interactivemedia.v3.internal.jc$b.init, this.b, v6));
    }

    public abstract boolean isCustomPlaybackUsed();

    public void removeAdErrorListener(AdErrorListener arg2) {
        this.i.b(arg2);
    }

    public void removeAdEventListener(AdEventListener arg2) {
        this.h.remove(arg2);
    }
}

