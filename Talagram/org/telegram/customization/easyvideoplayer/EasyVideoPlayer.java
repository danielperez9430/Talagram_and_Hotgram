package org.telegram.customization.easyvideoplayer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.PorterDuff$Mode;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.media.MediaPlayer$OnBufferingUpdateListener;
import android.media.MediaPlayer$OnCompletionListener;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer$OnVideoSizeChangedListener;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build$VERSION;
import android.os.Handler;
import android.support.v4.view.t;
import android.support.v7.app.e;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView$SurfaceTextureListener;
import android.view.TextureView;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.SeekBar$OnSeekBarChangeListener;
import android.widget.SeekBar;
import android.widget.TextView;
import java.io.IOException;
import org.telegram.messenger.R$styleable;

public class EasyVideoPlayer extends FrameLayout implements MediaPlayer$OnBufferingUpdateListener, MediaPlayer$OnCompletionListener, MediaPlayer$OnErrorListener, MediaPlayer$OnPreparedListener, MediaPlayer$OnVideoSizeChangedListener, TextureView$SurfaceTextureListener, View$OnClickListener, SeekBar$OnSeekBarChangeListener {
    class org.telegram.customization.easyvideoplayer.EasyVideoPlayer$1 implements Runnable {
        org.telegram.customization.easyvideoplayer.EasyVideoPlayer$1(EasyVideoPlayer arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            if(EasyVideoPlayer.a(this.a) != null && (EasyVideoPlayer.b(this.a)) && EasyVideoPlayer.c(this.a) != null) {
                if(EasyVideoPlayer.d(this.a) == null) {
                }
                else {
                    int v0 = EasyVideoPlayer.d(this.a).getCurrentPosition();
                    int v1 = EasyVideoPlayer.d(this.a).getDuration();
                    if(v0 > v1) {
                        v0 = v1;
                    }

                    EasyVideoPlayer.e(this.a).setText(c.a(((long)v0), false));
                    EasyVideoPlayer.f(this.a).setText(c.a(((long)(v1 - v0)), true));
                    EasyVideoPlayer.c(this.a).setProgress(v0);
                    EasyVideoPlayer.c(this.a).setMax(v1);
                    if(EasyVideoPlayer.g(this.a) != null) {
                        EasyVideoPlayer.g(this.a).a(v0, v1);
                    }

                    if(EasyVideoPlayer.a(this.a) == null) {
                        return;
                    }

                    EasyVideoPlayer.a(this.a).postDelayed(((Runnable)this), 100);
                }
            }
        }
    }

    private CharSequence A;
    private CharSequence B;
    private Drawable C;
    private Drawable D;
    private Drawable E;
    private CharSequence F;
    private CharSequence G;
    private boolean H;
    private boolean I;
    private int J;
    private boolean K;
    private int L;
    private boolean M;
    private boolean N;
    private final Runnable O;
    public ImageButton a;
    public TextView b;
    private TextureView c;
    private Surface d;
    private View e;
    private View f;
    private View g;
    private SeekBar h;
    private TextView i;
    private TextView j;
    private ImageButton k;
    private TextView l;
    private TextView m;
    private TextView n;
    private MediaPlayer o;
    private boolean p;
    private boolean q;
    private boolean r;
    private int s;
    private int t;
    private Handler u;
    private Uri v;
    private a w;
    private b x;
    private int y;
    private int z;

    public EasyVideoPlayer(Context arg3) {
        super(arg3);
        this.y = 1;
        this.z = 3;
        this.H = true;
        this.J = -1;
        this.L = 0;
        this.M = false;
        this.N = false;
        this.O = new org.telegram.customization.easyvideoplayer.EasyVideoPlayer$1(this);
        this.a(arg3, null);
    }

    public EasyVideoPlayer(Context arg3, AttributeSet arg4) {
        super(arg3, arg4);
        this.y = 1;
        this.z = 3;
        this.H = true;
        this.J = -1;
        this.L = 0;
        this.M = false;
        this.N = false;
        this.O = new org.telegram.customization.easyvideoplayer.EasyVideoPlayer$1(this);
        this.a(arg3, arg4);
    }

    public EasyVideoPlayer(Context arg2, AttributeSet arg3, int arg4) {
        super(arg2, arg3, arg4);
        this.y = 1;
        this.z = 3;
        this.H = true;
        this.J = -1;
        this.L = 0;
        this.M = false;
        this.N = false;
        this.O = new org.telegram.customization.easyvideoplayer.EasyVideoPlayer$1(this);
        this.a(arg2, arg3);
    }

    private void a(Context arg8, AttributeSet arg9) {
        e.a(true);
        this.setBackgroundColor(-16777216);
        int v1 = 2130968774;
        int v2 = 3;
        if(arg9 != null) {
            TypedArray v9 = arg8.getTheme().obtainStyledAttributes(arg9, styleable.EasyVideoPlayer, 0, 0);
            int v4 = 13;
            try {
                String v4_1 = v9.getString(v4);
                if(v4_1 != null && !v4_1.trim().isEmpty()) {
                    this.v = Uri.parse(v4_1);
                }

                this.y = v9.getInteger(6, 1);
                this.z = v9.getInteger(12, v2);
                this.F = v9.getText(v2);
                this.A = v9.getText(11);
                this.B = v9.getText(14);
                this.G = v9.getText(2);
                v4 = -1;
                v2 = v9.getResourceId(10, v4);
                int v5 = v9.getResourceId(9, v4);
                int v6 = v9.getResourceId(8, v4);
                if(v2 != v4) {
                    this.C = android.support.v7.c.a.a.b(arg8, v2);
                }

                if(v5 != v4) {
                    this.D = android.support.v7.c.a.a.b(arg8, v5);
                }

                if(v6 != v4) {
                    this.E = android.support.v7.c.a.a.b(arg8, v6);
                }

                this.H = v9.getBoolean(5, true);
                this.I = v9.getBoolean(1, false);
                this.K = v9.getBoolean(4, false);
                this.L = v9.getColor(15, c.a(arg8, v1));
                this.M = v9.getBoolean(0, false);
                this.N = v9.getBoolean(7, false);
            }
            catch(Throwable v8) {
                v9.recycle();
                throw v8;
            }

            v9.recycle();
        }
        else {
            this.y = 1;
            this.z = v2;
            this.H = true;
            this.I = false;
            this.K = false;
            this.L = c.a(arg8, v1);
            this.M = false;
            this.N = false;
        }

        if(this.A == null) {
            this.A = arg8.getResources().getText(2131626766);
        }

        if(this.B == null) {
            this.B = arg8.getResources().getText(2131626767);
        }

        if(this.C == null) {
            this.C = android.support.v7.c.a.a.b(arg8, 2131231057);
        }

        if(this.D == null) {
            this.D = android.support.v7.c.a.a.b(arg8, 2131231056);
        }

        if(this.E == null) {
            this.E = android.support.v7.c.a.a.b(arg8, 2131231055);
        }
    }

    private Drawable a(Drawable arg1, int arg2) {
        arg1 = android.support.v4.graphics.drawable.a.g(arg1.mutate());
        android.support.v4.graphics.drawable.a.a(arg1, arg2);
        return arg1;
    }

    static Handler a(EasyVideoPlayer arg0) {
        return arg0.u;
    }

    private void a(int arg5, int arg6, int arg7, int arg8) {
        double v0 = ((double)arg8);
        double v7 = ((double)arg7);
        Double.isNaN(v0);
        Double.isNaN(v7);
        v0 /= v7;
        v7 = ((double)arg5);
        Double.isNaN(v7);
        arg7 = ((int)(v7 * v0));
        if(arg6 > arg7) {
            arg8 = arg7;
            arg7 = arg5;
        }
        else {
            v7 = ((double)arg6);
            Double.isNaN(v7);
            arg7 = ((int)(v7 / v0));
            arg8 = arg6;
        }

        Matrix v2 = new Matrix();
        this.c.getTransform(v2);
        v2.setScale((((float)arg7)) / (((float)arg5)), (((float)arg8)) / (((float)arg6)));
        v2.postTranslate(((float)((arg5 - arg7) / 2)), ((float)((arg6 - arg8) / 2)));
        this.c.setTransform(v2);
    }

    private void a(View arg3, int arg4) {
        if(Build$VERSION.SDK_INT >= 21 && ((arg3.getBackground() instanceof RippleDrawable))) {
            arg3.getBackground().setColor(ColorStateList.valueOf(c.a(arg4, 0.3f)));
        }
    }

    private static void a(SeekBar arg3, int arg4) {
        ColorStateList v0 = ColorStateList.valueOf(arg4);
        if(Build$VERSION.SDK_INT >= 21) {
            arg3.setThumbTintList(v0);
            arg3.setProgressTintList(v0);
            arg3.setSecondaryProgressTintList(v0);
        }
        else {
            int v2 = 10;
            if(Build$VERSION.SDK_INT > v2) {
                Drawable v4 = android.support.v4.graphics.drawable.a.g(arg3.getProgressDrawable());
                arg3.setProgressDrawable(v4);
                android.support.v4.graphics.drawable.a.a(v4, v0);
                if(Build$VERSION.SDK_INT >= 16) {
                    v4 = android.support.v4.graphics.drawable.a.g(arg3.getThumb());
                    android.support.v4.graphics.drawable.a.a(v4, v0);
                    arg3.setThumb(v4);
                }
            }
            else {
                PorterDuff$Mode v0_1 = PorterDuff$Mode.SRC_IN;
                if(Build$VERSION.SDK_INT <= v2) {
                    v0_1 = PorterDuff$Mode.MULTIPLY;
                }

                if(arg3.getIndeterminateDrawable() != null) {
                    arg3.getIndeterminateDrawable().setColorFilter(arg4, v0_1);
                }

                if(arg3.getProgressDrawable() == null) {
                    return;
                }

                arg3.getProgressDrawable().setColorFilter(arg4, v0_1);
            }
        }
    }

    private void a(Exception arg2) {
        if(this.w != null) {
            this.w.a(this, arg2);
            return;
        }

        throw new RuntimeException(((Throwable)arg2));
    }

    private static void a(String arg0, Object[] arg1) {
        if(arg1 != null) {
            try {
                arg0 = String.format(arg0, arg1);
            label_2:
                Log.d("EasyVideoPlayer", arg0);
                return;
            }
            catch(Exception ) {
                return;
            }
        }

        goto label_2;
    }

    static void a(EasyVideoPlayer arg0, boolean arg1) {
        arg0.setFullscreen(arg1);
    }

    public void a() {
        if(!this.K && !this.c()) {
            if(this.h == null) {
            }
            else {
                this.e.animate().cancel();
                this.e.setAlpha(0f);
                this.e.setVisibility(0);
                this.e.animate().alpha(1f).setInterpolator(new DecelerateInterpolator()).setListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator arg2) {
                        if(EasyVideoPlayer.h(this.a)) {
                            EasyVideoPlayer.a(this.a, false);
                        }
                    }
                }).start();
            }
        }
    }

    public void a(int arg2) {
        if(this.o == null) {
            return;
        }

        this.o.seekTo(arg2);
    }

    static boolean b(EasyVideoPlayer arg0) {
        return arg0.q;
    }

    public void b() {
        if(!this.K && (this.c())) {
            if(this.h == null) {
            }
            else {
                this.e.animate().cancel();
                this.e.setAlpha(1f);
                this.e.setVisibility(0);
                this.e.animate().alpha(0f).setInterpolator(new DecelerateInterpolator()).setListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator arg2) {
                        EasyVideoPlayer.a(this.a, true);
                        if(EasyVideoPlayer.i(this.a) != null) {
                            EasyVideoPlayer.i(this.a).setVisibility(4);
                        }
                    }
                }).start();
            }
        }
    }

    static SeekBar c(EasyVideoPlayer arg0) {
        return arg0.h;
    }

    public boolean c() {
        boolean v0 = (this.K) || this.e == null || this.e.getAlpha() <= 0.5f ? false : true;
        return v0;
    }

    static MediaPlayer d(EasyVideoPlayer arg0) {
        return arg0.o;
    }

    public void d() {
        if(this.K) {
            return;
        }

        if(this.c()) {
            this.b();
        }
        else {
            this.a();
        }
    }

    static TextView e(EasyVideoPlayer arg0) {
        return arg0.i;
    }

    public boolean e() {
        boolean v0 = this.o == null || !this.o.isPlaying() ? false : true;
        return v0;
    }

    static TextView f(EasyVideoPlayer arg0) {
        return arg0.j;
    }

    public void f() {
        if(this.o == null) {
            return;
        }

        this.o.start();
        if(this.w != null) {
            this.w.a(this);
        }

        if(this.u == null) {
            this.u = new Handler();
        }

        this.u.post(this.O);
        this.k.setImageDrawable(this.E);
    }

    static b g(EasyVideoPlayer arg0) {
        return arg0.x;
    }

    public void g() {
        if(this.o != null) {
            if(!this.e()) {
            }
            else {
                this.o.pause();
                if(this.w != null) {
                    this.w.b(this);
                }

                if(this.u == null) {
                    return;
                }

                this.u.removeCallbacks(this.O);
                this.k.setImageDrawable(this.D);
            }
        }
    }

    public int getCurrentPosition() {
        if(this.o == null) {
            return -1;
        }

        return this.o.getCurrentPosition();
    }

    public int getDuration() {
        if(this.o == null) {
            return -1;
        }

        return this.o.getDuration();
    }

    static boolean h(EasyVideoPlayer arg0) {
        return arg0.M;
    }

    public void h() {
        if(this.o == null) {
            return;
        }

        try {
            this.o.stop();
            goto label_5;
        }
        catch(Throwable ) {
        label_5:
            if(this.u == null) {
                return;
            }

            this.u.removeCallbacks(this.O);
            this.k.setImageDrawable(this.E);
            return;
        }
    }

    static View i(EasyVideoPlayer arg0) {
        return arg0.e;
    }

    public void i() {
        this.q = false;
        MediaPlayer v2 = null;
        if(this.o != null) {
            try {
                this.o.release();
                goto label_7;
            }
            catch(Throwable ) {
            label_7:
                this.o = v2;
            }
        }

        if(this.u != null) {
            this.u.removeCallbacks(this.O);
            this.u = ((Handler)v2);
        }

        EasyVideoPlayer.a("Released player and Handler", new Object[0]);
    }

    static a j(EasyVideoPlayer arg0) {
        return arg0.w;
    }

    private void j() {
        this.setControlsEnabled(false);
        this.h.setProgress(0);
        this.h.setEnabled(false);
        this.o.reset();
        if(this.w != null) {
            this.w.c(this);
        }

        try {
            this.k();
        }
        catch(IOException v0) {
            this.a(((Exception)v0));
        }
    }

    private void k() {
        String v2;
        String v1;
        AssetManager v0_1;
        if(this.v.getScheme() != null) {
            if(!this.v.getScheme().equals("http") && !this.v.getScheme().equals("https")) {
                goto label_29;
            }

            EasyVideoPlayer.a("Loading web URI: " + this.v.toString(), new Object[0]);
            this.o.setDataSource(this.v.toString());
        }
        else {
        label_29:
            if(this.v.getScheme() == null || !this.v.getScheme().equals("file") || !this.v.getPath().contains("/android_assets/")) {
                if(this.v.getScheme() != null && (this.v.getScheme().equals("asset"))) {
                    EasyVideoPlayer.a("Loading assets URI: " + this.v.toString(), new Object[0]);
                    v0_1 = this.getContext().getAssets();
                    v1 = this.v.toString();
                    v2 = "asset://";
                    goto label_57;
                }

                goto label_91;
            }
            else {
                EasyVideoPlayer.a("Loading assets URI: " + this.v.toString(), new Object[0]);
                v0_1 = this.getContext().getAssets();
                v1 = this.v.toString();
                v2 = "file:///android_assets/";
            }

        label_57:
            AssetFileDescriptor v0_2 = v0_1.openFd(v1.replace(((CharSequence)v2), ""));
            this.o.setDataSource(v0_2.getFileDescriptor(), v0_2.getStartOffset(), v0_2.getLength());
            v0_2.close();
            goto label_105;
        label_91:
            EasyVideoPlayer.a("Loading local URI: " + this.v.toString(), new Object[0]);
            this.o.setDataSource(this.getContext(), this.v);
        }

    label_105:
        this.o.prepareAsync();
    }

    private void l() {
        if((this.p) && this.v != null && this.o != null && !this.q) {
            if(this.w != null) {
                this.w.c(this);
            }

            try {
                this.o.setSurface(this.d);
                this.k();
            }
            catch(IOException v0) {
                this.a(((Exception)v0));
            }
        }
    }

    private void m() {
        int v2 = 8;
        switch(this.y) {
            case 0: {
                goto label_13;
            }
            case 1: {
                goto label_8;
            }
            case 2: {
                goto label_5;
            }
        }

        goto label_17;
    label_5:
        this.b.setVisibility(0);
        goto label_15;
    label_8:
        this.b.setVisibility(v2);
        this.a.setVisibility(0);
        goto label_17;
    label_13:
        this.b.setVisibility(v2);
    label_15:
        this.a.setVisibility(v2);
    label_17:
        switch(this.z) {
            case 3: {
                goto label_28;
            }
            case 4: {
                goto label_25;
            }
            case 5: {
                goto label_20;
            }
        }

        return;
    label_20:
        this.l.setVisibility(v2);
        this.m.setVisibility(0);
        return;
    label_25:
        this.l.setVisibility(0);
        goto label_30;
    label_28:
        this.l.setVisibility(v2);
    label_30:
        this.m.setVisibility(v2);
    }

    private void n() {
        int v0 = c.a(this.L) ? -1 : -16777216;
        this.e.setBackgroundColor(c.a(this.L, 0.8f));
        this.a(this.a, v0);
        this.a(this.k, v0);
        this.j.setTextColor(v0);
        this.i.setTextColor(v0);
        EasyVideoPlayer.a(this.h, v0);
        this.b.setTextColor(v0);
        this.a(this.b, v0);
        this.l.setTextColor(v0);
        this.a(this.l, v0);
        this.m.setTextColor(v0);
        this.n.setTextColor(v0);
        this.D = this.a(this.D.mutate(), v0);
        this.C = this.a(this.C.mutate(), v0);
        this.E = this.a(this.E.mutate(), v0);
    }

    public void onBufferingUpdate(MediaPlayer arg4, int arg5) {
        EasyVideoPlayer.a("Buffering: %d%%", new Object[]{Integer.valueOf(arg5)});
        if(this.w != null) {
            this.w.a(arg5);
        }

        if(this.h != null) {
            int v4 = 100;
            if(arg5 == v4) {
                this.h.setSecondaryProgress(0);
            }
            else {
                this.h.setSecondaryProgress(this.h.getMax() * (arg5 / v4));
            }
        }
    }

    public void onClick(View arg3) {
        if(arg3.getId() == 2131296334) {
            if(this.o.isPlaying()) {
                this.g();
            }
            else {
                if(!this.H) {
                }
                else if(!this.K) {
                    this.b();
                }
                else {
                }

                goto label_13;
            }
        }
        else if(arg3.getId() == 2131296335) {
            this.a(0);
            if(!this.e()) {
            label_13:
                this.f();
            }
        }
        else if(arg3.getId() == 2131296336) {
            if(this.w != null) {
                this.w.a(this, this.v);
            }
        }
        else if(arg3.getId() == 2131296337 && this.w != null) {
            this.w.b(this, this.v);
        }
    }

    public void onCompletion(MediaPlayer arg2) {
        EasyVideoPlayer.a("onCompletion()", new Object[0]);
        if(this.N) {
            this.k.setImageDrawable(this.D);
            if(this.u != null) {
                this.u.removeCallbacks(this.O);
            }

            this.h.setProgress(this.h.getMax());
            this.a();
        }

        if(this.w != null) {
            this.w.e(this);
            if(this.N) {
                this.w.a(this);
            }
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EasyVideoPlayer.a("Detached from window", new Object[0]);
        this.i();
        SeekBar v0 = null;
        this.h = v0;
        this.i = ((TextView)v0);
        this.j = ((TextView)v0);
        this.k = ((ImageButton)v0);
        this.a = ((ImageButton)v0);
        this.l = ((TextView)v0);
        this.e = ((View)v0);
        this.g = ((View)v0);
        this.f = ((View)v0);
        if(this.u != null) {
            this.u.removeCallbacks(this.O);
            this.u = ((Handler)v0);
        }
    }

    public boolean onError(MediaPlayer arg2, int arg3, int arg4) {
        StringBuilder v3;
        if(arg3 == -38) {
            return 0;
        }

        String v4_1 = "Preparation/playback error (" + arg3 + "): ";
        if(arg3 == -1010) {
            v3 = new StringBuilder();
            v3.append(v4_1);
            v4_1 = "Unsupported";
        }
        else if(arg3 == -1007) {
            v3 = new StringBuilder();
            v3.append(v4_1);
            v4_1 = "Malformed";
        }
        else if(arg3 == -1004) {
            v3 = new StringBuilder();
            v3.append(v4_1);
            v4_1 = "I/O error";
        }
        else if(arg3 == -110) {
            v3 = new StringBuilder();
            v3.append(v4_1);
            v4_1 = "Timed out";
        }
        else if(arg3 == 100) {
            v3 = new StringBuilder();
            v3.append(v4_1);
            v4_1 = "Server died";
        }
        else if(arg3 != 200) {
            v3 = new StringBuilder();
            v3.append(v4_1);
            v4_1 = "Unknown error";
        }
        else {
            v3 = new StringBuilder();
            v3.append(v4_1);
            v4_1 = "Not valid for progressive playback";
        }

        v3.append(v4_1);
        String v3_1 = v3.toString();
        this.a(new Exception(v3_1));
        return 0;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if(this.isInEditMode()) {
            return;
        }

        this.setKeepScreenOn(true);
        this.u = new Handler();
        this.o = new MediaPlayer();
        this.o.setOnPreparedListener(((MediaPlayer$OnPreparedListener)this));
        this.o.setOnBufferingUpdateListener(((MediaPlayer$OnBufferingUpdateListener)this));
        this.o.setOnCompletionListener(((MediaPlayer$OnCompletionListener)this));
        this.o.setOnVideoSizeChangedListener(((MediaPlayer$OnVideoSizeChangedListener)this));
        this.o.setOnErrorListener(((MediaPlayer$OnErrorListener)this));
        this.o.setAudioStreamType(3);
        this.o.setLooping(this.N);
        FrameLayout$LayoutParams v1 = new FrameLayout$LayoutParams(-1, -1);
        this.c = new TextureView(this.getContext());
        this.addView(this.c, ((ViewGroup$LayoutParams)v1));
        this.c.setSurfaceTextureListener(((TextureView$SurfaceTextureListener)this));
        LayoutInflater v1_1 = LayoutInflater.from(this.getContext());
        this.f = v1_1.inflate(2131492989, ((ViewGroup)this), false);
        this.addView(this.f);
        this.g = new FrameLayout(this.getContext());
        this.g.setForeground(c.b(this.getContext(), 2130969160));
        this.addView(this.g, new ViewGroup$LayoutParams(-1, -1));
        this.e = v1_1.inflate(2131492988, ((ViewGroup)this), false);
        v1 = new FrameLayout$LayoutParams(-1, -2);
        v1.gravity = 80;
        this.addView(this.e, ((ViewGroup$LayoutParams)v1));
        if(this.K) {
            this.g.setOnClickListener(null);
            this.e.setVisibility(8);
        }
        else {
            this.g.setOnClickListener(new View$OnClickListener(this) {
                public void onClick(View arg2) {
                    this.b.d();
                    try {
                        EasyVideoPlayer.j(this.b).f(this.a);
                        return;
                    }
                    catch(Exception ) {
                        return;
                    }
                }
            });
        }

        this.h = this.e.findViewById(2131296884);
        this.h.setOnSeekBarChangeListener(((SeekBar$OnSeekBarChangeListener)this));
        this.i = this.e.findViewById(2131296809);
        this.i.setText(c.a(0, false));
        this.j = this.e.findViewById(2131296454);
        this.j.setText(c.a(0, true));
        this.a = this.e.findViewById(2131296335);
        this.a.setOnClickListener(((View$OnClickListener)this));
        this.a.setImageDrawable(this.C);
        this.b = this.e.findViewById(2131296336);
        this.b.setOnClickListener(((View$OnClickListener)this));
        this.b.setText(this.A);
        this.k = this.e.findViewById(2131296334);
        this.k.setOnClickListener(((View$OnClickListener)this));
        this.k.setImageDrawable(this.D);
        this.l = this.e.findViewById(2131296337);
        this.l.setOnClickListener(((View$OnClickListener)this));
        this.l.setText(this.B);
        this.m = this.e.findViewById(2131296646);
        this.m.setText(this.F);
        this.n = this.e.findViewById(2131296645);
        this.setBottomLabelText(this.G);
        this.n();
        this.setControlsEnabled(false);
        this.m();
        this.l();
    }

    public void onPrepared(MediaPlayer arg6) {
        EasyVideoPlayer.a("onPrepared()", new Object[0]);
        this.f.setVisibility(4);
        this.q = true;
        if(this.w != null) {
            this.w.d(this);
        }

        this.i.setText(c.a(0, false));
        this.j.setText(c.a(((long)arg6.getDuration()), false));
        this.h.setProgress(0);
        this.h.setMax(arg6.getDuration());
        this.setControlsEnabled(true);
        if(this.I) {
            if(!this.K && (this.H)) {
                this.b();
            }

            this.f();
            if(this.J <= 0) {
                return;
            }

            this.a(this.J);
            this.J = -1;
        }
        else {
            this.o.start();
            this.o.pause();
        }
    }

    public void onProgressChanged(SeekBar arg1, int arg2, boolean arg3) {
        if(arg3) {
            this.a(arg2);
        }
    }

    public void onStartTrackingTouch(SeekBar arg1) {
        this.r = this.e();
        if(this.r) {
            this.o.pause();
        }
    }

    public void onStopTrackingTouch(SeekBar arg1) {
        if(this.r) {
            this.o.start();
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture arg5, int arg6, int arg7) {
        EasyVideoPlayer.a("Surface texture available: %dx%d", new Object[]{Integer.valueOf(arg6), Integer.valueOf(arg7)});
        this.s = arg6;
        this.t = arg7;
        this.p = true;
        this.d = new Surface(arg5);
        if(this.q) {
            this.o.setSurface(this.d);
        }
        else {
            this.l();
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture arg3) {
        EasyVideoPlayer.a("Surface texture destroyed", new Object[0]);
        this.p = false;
        this.d = null;
        return 0;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture arg4, int arg5, int arg6) {
        EasyVideoPlayer.a("Surface texture changed: %dx%d", new Object[]{Integer.valueOf(arg5), Integer.valueOf(arg6)});
        this.a(arg5, arg6, this.o.getVideoWidth(), this.o.getVideoHeight());
    }

    public void onSurfaceTextureUpdated(SurfaceTexture arg1) {
    }

    public void onVideoSizeChanged(MediaPlayer arg4, int arg5, int arg6) {
        EasyVideoPlayer.a("Video size changed: %dx%d", new Object[]{Integer.valueOf(arg5), Integer.valueOf(arg6)});
        this.a(this.s, this.t, arg5, arg6);
    }

    public void setAutoFullscreen(boolean arg1) {
        this.M = arg1;
    }

    public void setAutoPlay(boolean arg1) {
        this.I = arg1;
    }

    public void setBottomLabelText(CharSequence arg2) {
        int v0;
        TextView v2;
        this.G = arg2;
        this.n.setText(arg2);
        if(arg2 == null || arg2.toString().trim().length() == 0) {
            v2 = this.n;
            v0 = 8;
        }
        else {
            v2 = this.n;
            v0 = 0;
        }

        v2.setVisibility(v0);
    }

    public void setBottomLabelTextRes(int arg2) {
        this.setBottomLabelText(this.getResources().getText(arg2));
    }

    public void setCallback(a arg1) {
    }

    private void setControlsEnabled(boolean arg5) {
        if(this.h == null) {
            return;
        }

        this.h.setEnabled(arg5);
        this.k.setEnabled(arg5);
        this.l.setEnabled(arg5);
        this.a.setEnabled(arg5);
        this.b.setEnabled(arg5);
        ImageButton v0 = this.k;
        float v1 = 0.4f;
        float v3 = arg5 ? 1f : 0.4f;
        v0.setAlpha(v3);
        TextView v0_1 = this.l;
        v3 = arg5 ? 1f : 0.4f;
        v0_1.setAlpha(v3);
        v0 = this.a;
        if(arg5) {
            v1 = 1f;
        }

        v0.setAlpha(v1);
        this.g.setEnabled(arg5);
    }

    public void setCustomLabelText(CharSequence arg2) {
        this.F = arg2;
        this.m.setText(arg2);
        this.setRightAction(5);
    }

    public void setCustomLabelTextRes(int arg2) {
        this.setCustomLabelText(this.getResources().getText(arg2));
    }

    @TargetApi(value=14) private void setFullscreen(boolean arg3) {
        int v3;
        if(Build$VERSION.SDK_INT >= 14 && (this.M)) {
            t.b(this.e, (((int)arg3)) ^ 1);
            if(Build$VERSION.SDK_INT >= 19) {
                int v0 = (((int)arg3)) | 1792;
                v3 = arg3 ? v0 | 2054 : v0;
            }

            this.g.setSystemUiVisibility(v3);
        }
    }

    public void setHideControlsOnPlay(boolean arg1) {
        this.H = arg1;
    }

    public void setInitialPosition(int arg1) {
        this.J = arg1;
    }

    public void setLeftAction(int arg2) {
        if(arg2 >= 0 && arg2 <= 2) {
            this.y = arg2;
            this.m();
            return;
        }

        throw new IllegalArgumentException("Invalid left action specified.");
    }

    public void setLoop(boolean arg2) {
        this.N = arg2;
        if(this.o != null) {
            this.o.setLooping(arg2);
        }
    }

    public void setPauseDrawable(Drawable arg2) {
        this.E = arg2;
        if(this.e()) {
            this.k.setImageDrawable(arg2);
        }
    }

    public void setPauseDrawableRes(int arg2) {
        this.setPauseDrawable(android.support.v7.c.a.a.b(this.getContext(), arg2));
    }

    public void setPlayDrawable(Drawable arg2) {
        this.D = arg2;
        if(!this.e()) {
            this.k.setImageDrawable(arg2);
        }
    }

    public void setPlayDrawableRes(int arg2) {
        this.setPlayDrawable(android.support.v7.c.a.a.b(this.getContext(), arg2));
    }

    public void setProgressCallback(b arg1) {
        this.x = arg1;
    }

    public void setRestartDrawable(Drawable arg2) {
        this.C = arg2;
        this.a.setImageDrawable(arg2);
    }

    public void setRestartDrawableRes(int arg2) {
        this.setRestartDrawable(android.support.v7.c.a.a.b(this.getContext(), arg2));
    }

    public void setRetryText(CharSequence arg2) {
        this.A = arg2;
        this.b.setText(arg2);
    }

    public void setRetryTextRes(int arg2) {
        this.setRetryText(this.getResources().getText(arg2));
    }

    public void setRightAction(int arg2) {
        if(arg2 >= 3 && arg2 <= 5) {
            this.z = arg2;
            this.m();
            return;
        }

        throw new IllegalArgumentException("Invalid right action specified.");
    }

    public void setSource(Uri arg2) {
        int v0 = this.v != null ? 1 : 0;
        if(v0 != 0) {
            this.h();
        }

        this.v = arg2;
        if(this.o != null) {
            if(v0 != 0) {
                this.j();
            }
            else {
                this.l();
            }
        }
    }

    public void setSubmitText(CharSequence arg2) {
        this.B = arg2;
        this.l.setText(arg2);
    }

    public void setSubmitTextRes(int arg2) {
        this.setSubmitText(this.getResources().getText(arg2));
    }

    public void setThemeColor(int arg1) {
        this.L = arg1;
        this.n();
    }

    public void setThemeColorRes(int arg2) {
        this.setThemeColor(android.support.v4.content.a.c(this.getContext(), arg2));
    }
}

