package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.media.MediaCodec$CryptoException;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder$Callback;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer$VideoAdPlayerCallback;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class iy implements jj {
    class com.google.ads.interactivemedia.v3.internal.iy$2 {
        static {
            com.google.ads.interactivemedia.v3.internal.iy$2.a = new int[f.values().length];
            try {
                com.google.ads.interactivemedia.v3.internal.iy$2.a[f.b.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    com.google.ads.interactivemedia.v3.internal.iy$2.a[f.d.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        com.google.ads.interactivemedia.v3.internal.iy$2.a[f.c.ordinal()] = 3;
                        return;
                    }
                    catch(NoSuchFieldError ) {
                        return;
                    }
                }
            }
        }
    }

    class a implements c {
        a(iy arg1) {
            this.a = arg1;
            super();
        }

        public void a() {
        }

        public void a(az arg4) {
            iy.b(this.a);
            String v4 = String.valueOf(arg4);
            StringBuilder v2 = new StringBuilder(String.valueOf(v4).length() + 13);
            v2.append("Player Error:");
            v2.append(v4);
            Log.e("IMA SDK", v2.toString());
        }

        public void a(boolean arg2, int arg3) {
            Iterator v2;
            if(arg3 == 5) {
                v2 = iy.d(this.a).iterator();
                while(v2.hasNext()) {
                    v2.next().onEnded();
                }
            }

            if(arg3 == 4) {
                v2 = iy.d(this.a).iterator();
                while(v2.hasNext()) {
                    v2.next().onLoaded();
                }
            }
        }
    }

    class b implements com.google.ads.interactivemedia.v3.internal.cf$a {
        b(iy arg1) {
            this.a = arg1;
            super();
        }

        public void a(int arg4, IOException arg5) {
            iy.b(this.a);
            String v5 = String.valueOf(arg5);
            StringBuilder v2 = new StringBuilder(String.valueOf(v5).length() + 41);
            v2.append("Load Error from SampleSource:");
            v2.append(arg4);
            v2.append(":");
            v2.append(v5);
            Log.e("IMA SDK", v2.toString());
        }
    }

    class com.google.ads.interactivemedia.v3.internal.iy$c extends d implements com.google.ads.interactivemedia.v3.internal.be$a {
        com.google.ads.interactivemedia.v3.internal.iy$c(iy arg1) {
            super(arg1);
        }

        public void a(int arg1, long arg2, long arg4) {
        }

        public void a(com.google.ads.interactivemedia.v3.internal.bt$d arg1) {
        }

        public void a(com.google.ads.interactivemedia.v3.internal.bt$f arg1) {
        }
    }

    class d implements com.google.ads.interactivemedia.v3.internal.bg$b {
        d(iy arg1) {
            this.a = arg1;
            super();
        }

        public void a(MediaCodec$CryptoException arg1) {
            iy.b(this.a);
        }

        public void a(com.google.ads.interactivemedia.v3.internal.bg$a arg1) {
            iy.b(this.a);
        }

        public void a(String arg1, long arg2, long arg4) {
        }
    }

    class e extends d implements com.google.ads.interactivemedia.v3.internal.bi$a {
        e(iy arg1) {
            this.a = arg1;
            super(arg1);
        }

        public void a(int arg1, int arg2, int arg3, float arg4) {
            at v3 = iy.c(this.a);
            float v1 = arg2 == 0 ? 1f : (((float)arg1)) * arg4 / (((float)arg2));
            v3.a(v1);
        }

        public void a(int arg1, long arg2) {
        }

        public void a(Surface arg1) {
        }
    }

    enum f {
        public static final enum f a;
        public static final enum f b;
        public static final enum f c;
        public static final enum f d;

        static {
            f.a = new f("IDLE", 0);
            f.b = new f("LOADED", 1);
            f.c = new f("PLAYING", 2);
            f.d = new f("PAUSED", 3);
            f.e = new f[]{f.a, f.b, f.c, f.d};
        }

        private f(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static f valueOf(String arg1) {
            return Enum.valueOf(f.class, arg1);
        }

        public static f[] values() {
            // Method was not decompiled
        }
    }

    public enum g {
        public static final enum g a;
        public static final enum g b;
        private final int c;

        static {
            g.a = new g("TYPE_VIDEO", 0, 0);
            g.b = new g("TYPE_AUDIO", 1, 1);
            g.d = new g[]{g.a, g.b};
        }

        private g(String arg1, int arg2, int arg3) {
            super(arg1, arg2);
            this.c = arg3;
        }

        public int a() {
            return this.c;
        }

        public static g valueOf(String arg1) {
            return Enum.valueOf(g.class, arg1);
        }

        public static g[] values() {
            // Method was not decompiled
        }
    }

    private final ba a;
    private final SurfaceView b;
    private final at c;
    private final FrameLayout d;
    private final ViewGroup e;
    private final Context f;
    private final Handler g;
    private final List h;
    private final b i;
    private final e j;
    private final com.google.ads.interactivemedia.v3.internal.iy$c k;
    private final a l;
    private f m;
    private boolean n;
    private bq o;

    public iy(Context arg2, ViewGroup arg3) {
        this(arg2, arg3, com.google.ads.interactivemedia.v3.internal.ba$b.a(2));
    }

    iy(Context arg4, ViewGroup arg5, ba arg6) {
        super();
        this.f = arg4;
        this.e = arg5;
        this.a = arg6;
        this.i = new b(this);
        this.k = new com.google.ads.interactivemedia.v3.internal.iy$c(this);
        this.j = new e(this);
        this.l = new a(this);
        arg6.a(this.l);
        this.g = new Handler();
        this.h = new ArrayList(1);
        this.d = new FrameLayout(arg4);
        this.d.setBackgroundColor(-16777216);
        this.c = new at(arg4);
        FrameLayout$LayoutParams v5 = new FrameLayout$LayoutParams(-1, -1);
        v5.gravity = 17;
        this.c.setLayoutParams(((ViewGroup$LayoutParams)v5));
        this.m = f.a;
        this.b = new SurfaceView(arg4);
        this.b.setZOrderMediaOverlay(true);
        this.b.getHolder().addCallback(new SurfaceHolder$Callback(arg6) {
            public void surfaceChanged(SurfaceHolder arg1, int arg2, int arg3, int arg4) {
            }

            public void surfaceCreated(SurfaceHolder arg4) {
                iy.a(this.b, true);
                if(iy.a(this.b) == f.c || iy.a(this.b) == f.d) {
                    iy.a(this.b, arg4.getSurface(), false);
                }

                if(iy.a(this.b) == f.c) {
                    this.a.a(true);
                }
            }

            public void surfaceDestroyed(SurfaceHolder arg3) {
                iy.a(this.b, null, true);
                this.a.a(false);
                iy.a(this.b, false);
            }
        });
        this.c.addView(this.b);
        this.d.addView(this.c);
        this.e.addView(this.d, new ViewGroup$LayoutParams(-1, -1));
    }

    static boolean a(iy arg0, boolean arg1) {
        arg0.n = arg1;
        return arg1;
    }

    static f a(iy arg0) {
        return arg0.m;
    }

    static void a(iy arg0, Surface arg1, boolean arg2) {
        arg0.a(arg1, arg2);
    }

    private void a(Surface arg3, boolean arg4) {
        if(this.a != null) {
            if(this.o == null) {
            }
            else if(arg4) {
                this.a.b(this.o, 1, arg3);
            }
            else {
                this.a.a(this.o, 1, arg3);
            }
        }
    }

    public void a() {
        this.d.setVisibility(0);
        this.b.setVisibility(0);
    }

    public void addCallback(VideoAdPlayerCallback arg2) {
        this.h.add(arg2);
    }

    static void b(iy arg0) {
        arg0.g();
    }

    public void b() {
        this.d.setVisibility(8);
        this.b.setVisibility(4);
    }

    static at c(iy arg0) {
        return arg0.c;
    }

    public void c() {
        this.a.b(this.l);
        this.a.c();
        this.e.removeView(this.d);
    }

    public b d() {
        return this.i;
    }

    static List d(iy arg0) {
        return arg0.h;
    }

    public e e() {
        return this.j;
    }

    public com.google.ads.interactivemedia.v3.internal.iy$c f() {
        return this.k;
    }

    private void g() {
        Iterator v0 = this.h.iterator();
        while(v0.hasNext()) {
            v0.next().onError();
        }
    }

    public VideoProgressUpdate getAdProgress() {
        if(this.a.a() != 3 && this.a.a() != 4 || this.a.d() <= 0) {
            return VideoProgressUpdate.VIDEO_TIME_NOT_READY;
        }

        return new VideoProgressUpdate(this.a.e(), this.a.d());
    }

    public int getVolume() {
        return 100;
    }

    public void loadAd(String arg5) {
        this.a.b();
        this.a.a(0);
        bq[] v5 = new jp(this.f, ft.a(this.f, "IMA SDK ExoPlayer"), Uri.parse(arg5)).a(this, this.g);
        this.o = v5[g.a.a()];
        this.a.a(v5);
        this.m = f.b;
    }

    public void pauseAd() {
        this.m = f.d;
        this.a.a(false);
        Iterator v0 = this.h.iterator();
        while(v0.hasNext()) {
            v0.next().onPause();
        }
    }

    public void playAd() {
        switch(com.google.ads.interactivemedia.v3.internal.iy$2.a[this.m.ordinal()]) {
            case 1: {
                goto label_26;
            }
            case 2: {
                goto label_19;
            }
            case 3: {
                return;
            }
        }

        String v1 = String.valueOf(this.m);
        StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 53);
        v3.append("Ignoring call to playAd during invalid player state: ");
        v3.append(v1);
        Log.w("IMA SDK", v3.toString());
        return;
    label_19:
        Iterator v0 = this.h.iterator();
        while(true) {
            if(!v0.hasNext()) {
                goto label_38;
            }

            v0.next().onResume();
        }

    label_26:
        v0 = this.h.iterator();
        while(v0.hasNext()) {
            v0.next().onPlay();
        }

        this.a(this.b.getHolder().getSurface(), false);
    label_38:
        this.m = f.c;
        if(this.n) {
            this.a.a(true);
        }
    }

    public void removeCallback(VideoAdPlayerCallback arg2) {
        this.h.remove(arg2);
    }

    public void resumeAd() {
        this.playAd();
    }

    public void stopAd() {
        this.m = f.a;
        this.a.b();
        this.a(null, false);
    }
}

