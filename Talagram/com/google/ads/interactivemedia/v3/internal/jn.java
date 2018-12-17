package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.net.Uri$Builder;
import android.net.Uri;
import android.util.Log;
import com.google.ads.interactivemedia.v3.api.AdError$AdErrorCode;
import com.google.ads.interactivemedia.v3.api.AdError$AdErrorType;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.BaseDisplayContainer;
import com.google.ads.interactivemedia.v3.api.StreamDisplayContainer;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.ads.interactivemedia.v3.api.player.VideoStreamPlayer$VideoStreamPlayerCallback;
import com.google.ads.interactivemedia.v3.api.player.VideoStreamPlayer;
import com.google.ads.interactivemedia.v3.impl.data.p;
import com.google.ads.interactivemedia.v3.impl.data.s;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class jn implements VideoStreamPlayerCallback, b, jo {
    class com.google.ads.interactivemedia.v3.internal.jn$1 {
        static {
            com.google.ads.interactivemedia.v3.internal.jn$1.a = new int[com.google.ads.interactivemedia.v3.internal.jc$b.values().length];
            try {
                com.google.ads.interactivemedia.v3.internal.jn$1.a[com.google.ads.interactivemedia.v3.internal.jc$b.loadStream.ordinal()] = 1;
                return;
            }
            catch(NoSuchFieldError ) {
                return;
            }
        }
    }

    abstract class a {
        a() {
            super();
        }

        abstract String TXXX();

        public static a create(String arg1) {
            return new ip(arg1);
        }
    }

    private VideoStreamPlayer a;
    private jd b;
    private final jl c;
    private boolean d;
    private iv e;
    private il f;
    private final String g;
    private String h;

    public jn(String arg11, jf arg12, jd arg13, jl arg14, StreamDisplayContainer arg15, String arg16, Context arg17) {
        this(arg11, arg12, arg13, arg14, arg15, arg16, null, null, arg17);
    }

    public jn(String arg2, jf arg3, jd arg4, jl arg5, StreamDisplayContainer arg6, String arg7, iv arg8, il arg9, Context arg10) {
        super();
        this.d = false;
        this.a = arg6.getVideoStreamPlayer();
        if(this.a != null) {
            this.c = arg5;
            this.g = arg2;
            this.b = arg4;
            this.h = arg7;
            this.d = false;
            this.e = arg8;
            if(this.e == null) {
                this.e = new iv(this.a, arg3.a());
            }

            this.f = arg9;
            if(this.f == null) {
                try {
                    this.f = new il(arg4, ((BaseDisplayContainer)arg6));
                }
                catch(AdError v2) {
                    Log.e("IMASDK", "Error creating ad UI: ", ((Throwable)v2));
                }
            }

            return;
        }

        throw new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "Server-side ad insertion player was not provided.");
    }

    private void a(com.google.ads.interactivemedia.v3.internal.jc$b arg5, Object arg6) {
        this.b.b(new jc(com.google.ads.interactivemedia.v3.internal.jc$a.videoDisplay, arg5, this.g, arg6));
    }

    public String a(String arg7) {
        if(arg7 != null && this.h != null) {
            if(this.h.length() == 0) {
            }
            else {
                String v0 = this.h.trim().replaceAll("\\s+", "");
                if(v0.charAt(0) == 63) {
                    v0 = v0.substring(1);
                }

                if(v0.length() == 0) {
                    return arg7;
                }

                Map v1 = ju.a(Uri.parse(arg7));
                HashMap v2 = new HashMap();
                Uri$Builder v7 = Uri.parse(arg7).buildUpon();
                v7.clearQuery();
                String v3 = "http://www.dom.com/path?";
                v0 = String.valueOf(v0);
                v0 = v0.length() != 0 ? v3.concat(v0) : new String(v3);
                Map v0_1 = ju.a(Uri.parse(v0));
                ((Map)v2).putAll(v0_1);
                if(!v1.isEmpty()) {
                    Iterator v3_1 = v1.keySet().iterator();
                    while(v3_1.hasNext()) {
                        Object v4 = v3_1.next();
                        if(v0_1.containsKey(v4)) {
                            continue;
                        }

                        ((Map)v2).put(v4, v1.get(v4));
                    }
                }

                v7.encodedQuery(ju.a(((Map)v2)));
                arg7 = v7.build().toString();
            }
        }

        return arg7;
    }

    public void a() {
        this.f.a();
    }

    public void a(VideoProgressUpdate arg3) {
        if(!this.d) {
            this.a(com.google.ads.interactivemedia.v3.internal.jc$b.start, s.builder().volumePercentage(this.a.getVolume()).build());
            this.d = true;
        }

        this.a(com.google.ads.interactivemedia.v3.internal.jc$b.timeupdate, arg3);
    }

    public void a(com.google.ads.interactivemedia.v3.impl.data.b arg2) {
        this.f.a(arg2);
    }

    public void a(com.google.ads.interactivemedia.v3.internal.jc$b arg5, p arg6) {
        if(com.google.ads.interactivemedia.v3.internal.jn$1.a[arg5.ordinal()] != 1) {
        }
        else {
            if(arg6 != null && arg6.streamUrl != null) {
                this.d = false;
                this.e.b();
                this.a.loadUrl(this.a(arg6.streamUrl), arg6.subtitles);
                return;
            }

            this.c.a(new id(new AdError(AdErrorType.LOAD, AdErrorCode.INTERNAL_ERROR, "Load message must contain video url.")));
        }
    }

    public void a(boolean arg1) {
        this.e.a(((b)this));
    }

    public void b() {
        this.a.onAdBreakStarted();
    }

    public boolean b(com.google.ads.interactivemedia.v3.internal.jc$b arg1, p arg2) {
        return 0;
    }

    public void c() {
        this.a.onAdBreakEnded();
    }

    public void d() {
    }

    public void e() {
        Log.d("SDK_DEBUG", "Destroying StreamVideoDisplay");
        this.h();
        VideoStreamPlayer v0 = null;
        this.a = v0;
        this.b = ((jd)v0);
        if(this.e != null) {
            this.e.c();
            this.e.b(((b)this));
        }

        this.e = ((iv)v0);
        this.f.b();
        this.f = ((il)v0);
    }

    public boolean f() {
        return 1;
    }

    public void g() {
        this.a.addCallback(((VideoStreamPlayerCallback)this));
    }

    public VideoProgressUpdate getAdProgress() {
        return this.a.getContentProgress();
    }

    public void h() {
        this.a.removeCallback(((VideoStreamPlayerCallback)this));
    }

    public void onAdError(AdErrorEvent arg1) {
    }

    public void onUserTextReceived(String arg2) {
        this.a(com.google.ads.interactivemedia.v3.internal.jc$b.timedMetadata, a.create(arg2));
    }

    public void onVolumeChanged(int arg2) {
        this.a(com.google.ads.interactivemedia.v3.internal.jc$b.volumeChange, s.builder().volumePercentage(arg2).build());
    }
}

