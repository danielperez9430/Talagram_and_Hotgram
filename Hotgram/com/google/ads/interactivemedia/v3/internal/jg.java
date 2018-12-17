package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.os.Build$VERSION;
import android.util.Log;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdError$AdErrorCode;
import com.google.ads.interactivemedia.v3.api.AdError$AdErrorType;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.BaseDisplayContainer;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.ads.interactivemedia.v3.impl.data.p;

public class jg implements jo {
    class com.google.ads.interactivemedia.v3.internal.jg$1 {
        static {
            com.google.ads.interactivemedia.v3.internal.jg$1.a = new int[b.values().length];
            try {
                com.google.ads.interactivemedia.v3.internal.jg$1.a[b.play.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    com.google.ads.interactivemedia.v3.internal.jg$1.a[b.pause.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        com.google.ads.interactivemedia.v3.internal.jg$1.a[b.resume.ordinal()] = 3;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                        try {
                        label_19:
                            com.google.ads.interactivemedia.v3.internal.jg$1.a[b.load.ordinal()] = 4;
                            goto label_24;
                        }
                        catch(NoSuchFieldError ) {
                            try {
                            label_24:
                                com.google.ads.interactivemedia.v3.internal.jg$1.a[b.showVideo.ordinal()] = 5;
                                goto label_29;
                            }
                            catch(NoSuchFieldError ) {
                                try {
                                label_29:
                                    com.google.ads.interactivemedia.v3.internal.jg$1.a[b.hide.ordinal()] = 6;
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

    private final VideoAdPlayer a;
    private final ih b;
    private final ij c;
    private final il d;
    private final if e;
    private boolean f;
    private boolean g;

    public jg(String arg10, jf arg11, jd arg12, ij arg13, AdDisplayContainer arg14, Context arg15) {
        this(arg10, arg11, arg12, arg13, arg14, null, null, arg15);
    }

    public jg(String arg4, jf arg5, jd arg6, ij arg7, AdDisplayContainer arg8, ih arg9, il arg10, Context arg11) {
        super();
        this.g = false;
        if(arg8.getPlayer() != null) {
            this.a = arg8.getPlayer();
            this.f = true;
        }
        else if(Build$VERSION.SDK_INT >= 16) {
            this.a = new iy(arg11, arg8.getAdContainer());
            this.f = false;
        }
        else {
            goto label_39;
        }

        if(arg9 == null) {
            arg9 = new ih(this.a, arg5.a());
        }

        this.b = arg9;
        this.c = arg7;
        this.d = arg10 != null ? arg10 : new il(arg6, ((BaseDisplayContainer)arg8));
        this.e = new if(arg6, arg4, this.b, this.a);
        return;
    label_39:
        throw new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "Ad Player was not provided. SDK-owned ad playback requires API 16+");
    }

    public void a() {
        this.a.stopAd();
        this.d.a();
    }

    public void a(com.google.ads.interactivemedia.v3.impl.data.b arg2) {
        if(!this.g || !arg2.canDisableUi()) {
            arg2.setUiDisabled(false);
            this.d.a(arg2);
        }
        else {
            arg2.setUiDisabled(true);
        }
    }

    public void a(b arg5, p arg6) {
        switch(com.google.ads.interactivemedia.v3.internal.jg$1.a[arg5.ordinal()]) {
            case 1: {
                this.a.playAd();
                break;
            }
            case 2: {
                this.a.pauseAd();
                break;
            }
            case 3: {
                this.a.resumeAd();
                break;
            }
            case 4: {
                if(arg6 != null && arg6.videoUrl != null) {
                    this.b.b();
                    this.a.loadAd(arg6.videoUrl);
                    return;
                }

                this.c.a(new id(new AdError(AdErrorType.LOAD, AdErrorCode.INTERNAL_ERROR, "Load message must contain video url.")));
                break;
            }
            default: {
                break;
            }
        }
    }

    public void a(boolean arg3) {
        this.b.a(this.e);
        this.g = arg3;
    }

    public void b() {
    }

    public boolean b(b arg1, p arg2) {
        switch(com.google.ads.interactivemedia.v3.internal.jg$1.a[arg1.ordinal()]) {
            case 5: {
                goto label_14;
            }
            case 6: {
                goto label_6;
            }
        }

        return 0;
    label_6:
        if(!this.f) {
            this.a.b();
        }

        this.a.removeCallback(this.e);
        return 1;
    label_14:
        if(!this.f) {
            this.a.a();
        }

        this.a.addCallback(this.e);
        return 1;
    }

    public void c() {
    }

    public void d() {
        this.b.c();
    }

    public void e() {
        Log.d("SDK_DEBUG", "Destroying NativeVideoDisplay");
        this.b.c();
        this.b.b(this.e);
        this.d.b();
        this.a.removeCallback(this.e);
        if((this.a instanceof jj)) {
            this.a.c();
        }
    }

    public boolean f() {
        return this.f;
    }

    public VideoProgressUpdate getAdProgress() {
        return this.a.getAdProgress();
    }

    public void onAdError(AdErrorEvent arg1) {
        this.d.a();
    }
}

