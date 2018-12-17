package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import com.google.ads.interactivemedia.v3.api.Ad;
import com.google.ads.interactivemedia.v3.api.AdError$AdErrorCode;
import com.google.ads.interactivemedia.v3.api.AdError$AdErrorType;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent$AdErrorListener;
import com.google.ads.interactivemedia.v3.api.AdEvent$AdEventListener;
import com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType;
import com.google.ads.interactivemedia.v3.api.AdProgressInfo;
import com.google.ads.interactivemedia.v3.api.AdsManager;
import com.google.ads.interactivemedia.v3.api.AdsRenderingSettings;
import com.google.ads.interactivemedia.v3.api.BaseDisplayContainer;
import com.google.ads.interactivemedia.v3.api.player.ContentProgressProvider;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

public class ij extends ir implements AdsManager {
    class com.google.ads.interactivemedia.v3.internal.ij$1 {
        static {
            com.google.ads.interactivemedia.v3.internal.ij$1.a = new int[AdEventType.values().length];
            try {
                com.google.ads.interactivemedia.v3.internal.ij$1.a[AdEventType.ALL_ADS_COMPLETED.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    com.google.ads.interactivemedia.v3.internal.ij$1.a[AdEventType.CONTENT_RESUME_REQUESTED.ordinal()] = 2;
                    return;
                }
                catch(NoSuchFieldError ) {
                    return;
                }
            }
        }
    }

    private List h;
    private List i;

    ij(String arg15, jd arg16, jf arg17, BaseDisplayContainer arg18, ContentProgressProvider arg19, List arg20, SortedSet arg21, jh arg22, Context arg23, boolean arg24) {
        this(arg15, arg16, arg17, arg18, arg19, arg20, arg21, null, null, null, arg22, arg23, arg24);
    }

    ij(String arg17, jd arg18, jf arg19, BaseDisplayContainer arg20, ContentProgressProvider arg21, List arg22, SortedSet arg23, jo arg24, iv arg25, ib arg26, jh arg27, Context arg28, boolean arg29) {
        ij v9 = this;
        String v10 = arg17;
        jd v11 = arg18;
        ContentProgressProvider v12 = arg21;
        SortedSet v13 = arg23;
        jo v14 = arg24;
        iv v15 = arg25;
        super(arg17, arg18, arg19, arg20, arg26, arg27, arg28, arg29);
        v9.i = arg22;
        if(v13 != null && !arg23.isEmpty()) {
            if(v12 != null) {
                v9.f = v15 != null ? v15 : new iv(v12, arg19.a());
                v9.e = new iu(v11, v13, v10);
                v9.f.a(v9.e);
                v9.f.b();
            }
            else {
                throw new AdError(AdErrorType.PLAY, AdErrorCode.PLAYLIST_NO_CONTENT_TRACKING, "Unable to handle cue points, no content progress provider configured.");
            }
        }

        v9.d = v14 != null ? v14 : new jg(arg17, arg19, arg18, this, arg20, arg28);
        v9.addAdErrorListener(v9.d);
        v11.a(v9.d, v10);
    }

    protected void a() {
        this.h = null;
        super.a();
    }

    public void a(AdErrorType arg1, int arg2, String arg3) {
        super.a(arg1, arg2, arg3);
    }

    public void a(AdErrorType arg1, AdErrorCode arg2, String arg3) {
        super.a(arg1, arg2, arg3);
    }

    public void a(c arg4) {
        AdEventType v0 = arg4.a;
        switch(com.google.ads.interactivemedia.v3.internal.ij$1.a[v0.ordinal()]) {
            case 1: {
                this.a();
                if(this.g) {
                    goto label_14;
                }

                this.a(b.destroy);
                break;
            }
            case 2: {
                this.d.d();
                break;
            }
            default: {
                break;
            }
        }

    label_14:
        if(v0 == AdEventType.COMPLETED || v0 == AdEventType.SKIPPED) {
            this.b(null);
        }

        super.a(arg4);
    }

    public void a(Map arg1) {
        this.b(arg1);
    }

    public void addAdErrorListener(AdErrorListener arg1) {
        super.addAdErrorListener(arg1);
    }

    public void addAdEventListener(AdEventListener arg1) {
        super.addAdEventListener(arg1);
    }

    private void b(Map arg1) {
        la v1;
        if(arg1 != null) {
            v1 = la.a(arg1.values());
        }
        else {
            List v1_1 = null;
        }

        this.h = ((List)v1);
    }

    public void clicked() {
        this.a.b(new jc(a.adsManager, b.click, this.b));
    }

    public void destroy() {
        if(this.f != null) {
            this.f.c();
        }

        this.a(b.destroy);
        this.g = true;
    }

    public void discardAdBreak() {
        this.a(b.discardAdBreak);
    }

    public void focusSkipButton() {
        if(this.getCurrentAd() != null && (this.getCurrentAd().isSkippable())) {
            this.a(this.b);
        }
    }

    public List getAdCuePoints() {
        return this.i;
    }

    public VideoProgressUpdate getAdProgress() {
        return super.getAdProgress();
    }

    public AdProgressInfo getAdProgressInfo() {
        return super.getAdProgressInfo();
    }

    public Ad getCurrentAd() {
        return super.getCurrentAd();
    }

    private List getCurrentCompanions() {
        return this.h;
    }

    public void init() {
        super.init();
    }

    public void init(AdsRenderingSettings arg1) {
        super.init(arg1);
    }

    public boolean isCustomPlaybackUsed() {
        return this.d.f();
    }

    private void onCompanionRendered(String arg3) {
        this.a.a(arg3, this.b);
    }

    public void pause() {
        this.a(b.pause);
    }

    public void removeAdErrorListener(AdErrorListener arg1) {
        super.removeAdErrorListener(arg1);
    }

    public void removeAdEventListener(AdEventListener arg1) {
        super.removeAdEventListener(arg1);
    }

    public void requestNextAdBreak() {
        if(this.f != null) {
            this.a.b(new jc(a.contentTimeUpdate, b.contentTimeUpdate, this.b, this.f.a()));
            this.a(b.requestNextAdBreak);
        }
    }

    public void resume() {
        this.a(b.resume);
    }

    public void skip() {
        this.a(b.skip);
    }

    public void start() {
        this.a(b.start);
    }
}

