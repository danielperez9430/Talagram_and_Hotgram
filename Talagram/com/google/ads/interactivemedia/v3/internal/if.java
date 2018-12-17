package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer$VideoAdPlayerCallback;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.ads.interactivemedia.v3.api.player.VolumeProvider;
import com.google.ads.interactivemedia.v3.impl.data.s;

public class if implements VideoAdPlayerCallback, b {
    private final jd a;
    private final String b;
    private final ih c;
    private final VolumeProvider d;
    private boolean e;

    public if(jd arg2, String arg3, ih arg4, VolumeProvider arg5) {
        super();
        this.e = false;
        this.a = arg2;
        this.b = arg3;
        this.c = arg4;
        this.d = arg5;
    }

    public void a(VideoProgressUpdate arg3) {
        if(arg3 != null && arg3.getDuration() > 0f) {
            this.b(arg3);
            this.a(com.google.ads.interactivemedia.v3.internal.jc$b.timeupdate, arg3);
        }
    }

    void a(com.google.ads.interactivemedia.v3.internal.jc$b arg4, Object arg5) {
        this.a.b(new jc(a.videoDisplay, arg4, this.b, arg5));
    }

    void a(com.google.ads.interactivemedia.v3.internal.jc$b arg2) {
        this.a(arg2, null);
    }

    void b(VideoProgressUpdate arg2) {
        if(!this.e && arg2.getCurrentTime() > 0f) {
            this.a(com.google.ads.interactivemedia.v3.internal.jc$b.start, s.builder().volumePercentage(this.d.getVolume()).build());
            this.e = true;
        }
    }

    public void onEnded() {
        this.a(com.google.ads.interactivemedia.v3.internal.jc$b.end);
    }

    public void onError() {
        this.a(com.google.ads.interactivemedia.v3.internal.jc$b.error);
    }

    public void onLoaded() {
        this.a(com.google.ads.interactivemedia.v3.internal.jc$b.loaded);
    }

    public void onPause() {
        this.c.c();
        this.a(com.google.ads.interactivemedia.v3.internal.jc$b.pause);
    }

    public void onPlay() {
        this.e = false;
    }

    public void onResume() {
        this.c.b();
        this.a(com.google.ads.interactivemedia.v3.internal.jc$b.play);
    }

    public void onVolumeChanged(int arg2) {
        this.a(com.google.ads.interactivemedia.v3.internal.jc$b.volumeChange, s.builder().volumePercentage(arg2).build());
    }
}

