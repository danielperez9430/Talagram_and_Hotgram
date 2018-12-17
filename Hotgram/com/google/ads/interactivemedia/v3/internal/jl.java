package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import com.google.ads.interactivemedia.v3.api.Ad;
import com.google.ads.interactivemedia.v3.api.AdError$AdErrorCode;
import com.google.ads.interactivemedia.v3.api.AdError$AdErrorType;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent$AdErrorListener;
import com.google.ads.interactivemedia.v3.api.AdEvent$AdEventListener;
import com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType;
import com.google.ads.interactivemedia.v3.api.AdProgressInfo;
import com.google.ads.interactivemedia.v3.api.AdsRenderingSettings;
import com.google.ads.interactivemedia.v3.api.CuePoint;
import com.google.ads.interactivemedia.v3.api.StreamDisplayContainer;
import com.google.ads.interactivemedia.v3.api.StreamManager;
import com.google.ads.interactivemedia.v3.api.StreamRequest;
import com.google.ads.interactivemedia.v3.api.player.ContentProgressProvider;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class jl extends ir implements StreamManager {
    class com.google.ads.interactivemedia.v3.internal.jl$1 {
        static {
            com.google.ads.interactivemedia.v3.internal.jl$1.a = new int[AdEventType.values().length];
            try {
                com.google.ads.interactivemedia.v3.internal.jl$1.a[AdEventType.AD_BREAK_STARTED.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    com.google.ads.interactivemedia.v3.internal.jl$1.a[AdEventType.AD_BREAK_ENDED.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        com.google.ads.interactivemedia.v3.internal.jl$1.a[AdEventType.CUEPOINTS_CHANGED.ordinal()] = 3;
                        return;
                    }
                    catch(NoSuchFieldError ) {
                        return;
                    }
                }
            }
        }
    }

    private final String h;
    private List i;

    jl(String arg15, jd arg16, jf arg17, StreamRequest arg18, jh arg19, Context arg20, String arg21, boolean arg22) {
        this(arg15, arg16, arg17, arg18.getStreamDisplayContainer(), arg18.getStreamDisplayContainer().getVideoStreamPlayer(), arg18.getManifestSuffix(), null, null, null, arg19, arg20, arg21, arg22);
    }

    jl(String arg12, jd arg13, jf arg14, StreamDisplayContainer arg15, ContentProgressProvider arg16, String arg17, jo arg18, iv arg19, ib arg20, jh arg21, Context arg22, String arg23, boolean arg24) {
        jl v9 = this;
        jo v10 = arg18;
        super(arg12, arg13, arg14, arg15, arg20, arg21, arg22, arg24);
        v9.i = new ArrayList();
        v9.h = arg23;
        if(v10 != null) {
            v9.d = v10;
        }
        else {
            v9.d = new jn(arg12, arg14, arg13, this, arg15, arg17, arg22);
            v9.d.g();
        }

        this.addAdErrorListener(v9.d);
        arg13.a(v9.d, arg12);
    }

    public void a(AdErrorType arg1, int arg2, String arg3) {
        super.a(arg1, arg2, arg3);
    }

    public void a(AdErrorType arg1, AdErrorCode arg2, String arg3) {
        super.a(arg1, arg2, arg3);
    }

    public void a(c arg3) {
        switch(com.google.ads.interactivemedia.v3.internal.jl$1.a[arg3.a.ordinal()]) {
            case 1: {
                this.d.b();
                break;
            }
            case 2: {
                this.d.c();
                break;
            }
            case 3: {
                this.i = arg3.d;
                break;
            }
            default: {
                break;
            }
        }

        super.a(arg3);
    }

    public void a(Map arg1) {
        super.a(arg1);
    }

    public void addAdErrorListener(AdErrorListener arg1) {
        super.addAdErrorListener(arg1);
    }

    public void addAdEventListener(AdEventListener arg1) {
        super.addAdEventListener(arg1);
    }

    public void destroy() {
        this.a(b.contentComplete);
        this.g = true;
        this.a();
    }

    public VideoProgressUpdate getAdProgress() {
        return super.getAdProgress();
    }

    public AdProgressInfo getAdProgressInfo() {
        return super.getAdProgressInfo();
    }

    public double getContentTimeForStreamTime(double arg10) {
        Iterator v0 = this.i.iterator();
        double v1;
        for(v1 = arg10; v0.hasNext(); v1 -= arg10 - ((CuePoint)v3).getStartTime()) {
            Object v3 = v0.next();
            if(((CuePoint)v3).getStartTime() > ((CuePoint)v3).getEndTime()) {
                return 0;
            }

            if(arg10 >= ((CuePoint)v3).getEndTime()) {
                v1 -= ((CuePoint)v3).getEndTime() - ((CuePoint)v3).getStartTime();
                continue;
            }

            if(arg10 >= ((CuePoint)v3).getEndTime()) {
                continue;
            }

            if(arg10 <= ((CuePoint)v3).getStartTime()) {
                continue;
            }
        }

        return v1;
    }

    public List getCuePoints() {
        return Collections.unmodifiableList(this.i);
    }

    public Ad getCurrentAd() {
        return super.getCurrentAd();
    }

    public CuePoint getPreviousCuePointForStreamTime(double arg7) {
        Iterator v0 = this.i.iterator();
        CuePoint v1 = null;
        while(v0.hasNext()) {
            Object v2 = v0.next();
            if(((CuePoint)v2).getStartTime() >= arg7) {
                continue;
            }

            Object v1_1 = v2;
        }

        return v1;
    }

    public String getStreamId() {
        return this.h;
    }

    public double getStreamTimeForContentTime(double arg17) {
        Iterator v1 = this.i.iterator();
        double v2 = 0;
        double v4 = arg17;
        double v6 = v2;
        double v8;
        for(v8 = v6; v1.hasNext(); v8 = ((CuePoint)v10).getEndTime()) {
            Object v10 = v1.next();
            if(((CuePoint)v10).getStartTime() > ((CuePoint)v10).getEndTime()) {
                return v2;
            }

            v6 += ((CuePoint)v10).getStartTime() - v8;
            if(v6 > arg17) {
                return v4;
            }

            v4 += ((CuePoint)v10).getEndTime() - ((CuePoint)v10).getStartTime();
        }

        return v4;
    }

    public void init() {
        super.init();
    }

    public void init(AdsRenderingSettings arg1) {
        super.init(arg1);
    }

    public boolean isCustomPlaybackUsed() {
        return 1;
    }

    public void removeAdErrorListener(AdErrorListener arg1) {
        super.removeAdErrorListener(arg1);
    }

    public void removeAdEventListener(AdEventListener arg1) {
        super.removeAdEventListener(arg1);
    }
}

