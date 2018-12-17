package org.telegram.customization.util.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer$OnCompletionListener;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View$OnClickListener;
import android.view.View$OnTouchListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.animation.Animation$AnimationListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar$OnSeekBarChangeListener;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import org.telegram.messenger.FileLog;
import org.telegram.ui.LaunchActivity;
import utils.view.VideoController.DensityUtil;
import utils.view.VideoController.LightnessController;
import utils.view.VideoController.VolumnController;

public class VideoActivity extends Activity implements View$OnClickListener {
    class org.telegram.customization.util.view.VideoActivity$1 implements Runnable {
        org.telegram.customization.util.view.VideoActivity$1(VideoActivity arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            VideoActivity.a(this.a);
        }
    }

    class org.telegram.customization.util.view.VideoActivity$5 extends Handler {
        org.telegram.customization.util.view.VideoActivity$5(VideoActivity arg1) {
            this.a = arg1;
            super();
        }

        public void handleMessage(Message arg5) {
            super.handleMessage(arg5);
            switch(arg5.what) {
                case 1: {
                    if(VideoActivity.b(this.a).getCurrentPosition() > 0) {
                        VideoActivity.c(this.a).setText(VideoActivity.a(this.a, ((long)VideoActivity.b(this.a).getCurrentPosition())));
                        VideoActivity.d(this.a).setProgress(VideoActivity.b(this.a).getCurrentPosition() * 100 / VideoActivity.b(this.a).getDuration());
                        if(VideoActivity.b(this.a).getCurrentPosition() > VideoActivity.b(this.a).getDuration() - 100) {
                            VideoActivity.c(this.a).setText("00:00");
                            VideoActivity.d(this.a).setProgress(0);
                        }

                        VideoActivity.d(this.a).setSecondaryProgress(VideoActivity.b(this.a).getBufferPercentage());
                        return;
                    }

                    VideoActivity.c(this.a).setText("00:00");
                    VideoActivity.d(this.a).setProgress(0);
                    break;
                }
                case 2: {
                    VideoActivity.a(this.a);
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }

    class org.telegram.customization.util.view.VideoActivity$6 implements SeekBar$OnSeekBarChangeListener {
        org.telegram.customization.util.view.VideoActivity$6(VideoActivity arg1) {
            this.a = arg1;
            super();
        }

        public void onProgressChanged(SeekBar arg1, int arg2, boolean arg3) {
            if(arg3) {
                VideoActivity.b(this.a).seekTo(arg2 * VideoActivity.b(this.a).getDuration() / 100);
            }
        }

        public void onStartTrackingTouch(SeekBar arg2) {
            VideoActivity.f(this.a).removeCallbacks(VideoActivity.e(this.a));
        }

        public void onStopTrackingTouch(SeekBar arg4) {
            VideoActivity.f(this.a).postDelayed(VideoActivity.e(this.a), 5000);
        }
    }

    class org.telegram.customization.util.view.VideoActivity$7 implements View$OnTouchListener {
        org.telegram.customization.util.view.VideoActivity$7(VideoActivity arg1) {
            this.a = arg1;
            super();
        }

        public boolean onTouch(View arg9, MotionEvent arg10) {
            float v9 = arg10.getX();
            float v0 = arg10.getY();
            int v1 = 0;
            switch(arg10.getAction()) {
                case 0: {
                    VideoActivity.a(this.a, v9);
                    VideoActivity.b(this.a, v0);
                    VideoActivity.a(this.a, ((int)v9));
                    VideoActivity.b(this.a, ((int)v0));
                    break;
                }
                case 1: {
                    if(Math.abs(v9 - (((float)VideoActivity.k(this.a)))) > (((float)VideoActivity.i(this.a))) || Math.abs(v0 - (((float)VideoActivity.l(this.a)))) > (((float)VideoActivity.i(this.a)))) {
                        VideoActivity.a(this.a, false);
                    }

                    VideoActivity.a(this.a, 0f);
                    VideoActivity.b(this.a, 0f);
                    VideoActivity.a(this.a, 0);
                    if(VideoActivity.m(this.a)) {
                        VideoActivity.a(this.a);
                    }

                    VideoActivity.a(this.a, true);
                    break;
                }
                case 2: {
                    float v10 = v9 - VideoActivity.g(this.a);
                    float v4 = v0 - VideoActivity.h(this.a);
                    float v5 = Math.abs(v10);
                    float v6 = Math.abs(v4);
                    if(v5 <= (((float)VideoActivity.i(this.a))) || v6 <= (((float)VideoActivity.i(this.a)))) {
                        if(v5 < (((float)VideoActivity.i(this.a))) && v6 > (((float)VideoActivity.i(this.a)))) {
                        label_25:
                            v1 = 1;
                            goto label_44;
                        }

                        if(v5 > (((float)VideoActivity.i(this.a))) && v6 < (((float)VideoActivity.i(this.a)))) {
                            goto label_44;
                        }

                        return 1;
                    }
                    else {
                        if(v5 >= v6) {
                            goto label_44;
                        }

                        goto label_25;
                    }

                label_44:
                    if(v1 != 0) {
                        if(v9 < VideoActivity.j(this.a) / 2f) {
                            if(v4 > 0f) {
                                goto label_51;
                            }
                            else if(v4 >= 0f) {
                                goto label_69;
                            }
                        }
                        else if(v4 > 0f) {
                        label_51:
                            VideoActivity.c(this.a, v6);
                            goto label_69;
                        }
                        else if(v4 < 0f) {
                        }
                        else {
                            goto label_69;
                        }

                        VideoActivity.d(this.a, v6);
                    }
                    else {
                        if(v10 > 0f) {
                            VideoActivity.e(this.a, v5);
                            goto label_69;
                        }

                        if(v10 >= 0f) {
                            goto label_69;
                        }

                        VideoActivity.f(this.a, v5);
                    }

                label_69:
                    VideoActivity.a(this.a, v9);
                    VideoActivity.b(this.a, v0);
                    return 1;
                }
                default: {
                    break;
                }
            }

            return 1;
        }
    }

    private int A;
    private int B;
    private int C;
    private boolean D;
    private View$OnTouchListener E;
    boolean a;
    ProgressBar b;
    LinearLayout c;
    ImageView d;
    private String e;
    private View f;
    private VideoView g;
    private View h;
    private View i;
    private SeekBar j;
    private ImageView k;
    private TextView l;
    private TextView m;
    private AudioManager n;
    private float o;
    private float p;
    private int q;
    private String r;
    private VolumnController s;
    private LightnessController t;
    private int u;
    private Runnable v;
    @SuppressLint(value={"HandlerLeak"}) private Handler w;
    private SeekBar$OnSeekBarChangeListener x;
    private float y;
    private float z;

    public VideoActivity() {
        super();
        this.a = false;
        this.r = "http://hn2.asset.aparat.com/aparat-video/7dcaacabfc08704d721b5e585efe03f51978186__95263.apt?start=0";
        this.v = new org.telegram.customization.util.view.VideoActivity$1(this);
        this.w = new org.telegram.customization.util.view.VideoActivity$5(this);
        this.x = new org.telegram.customization.util.view.VideoActivity$6(this);
        this.D = true;
        this.E = new org.telegram.customization.util.view.VideoActivity$7(this);
    }

    static float a(VideoActivity arg0, float arg1) {
        arg0.y = arg1;
        return arg1;
    }

    static int a(VideoActivity arg0, int arg1) {
        arg0.A = arg1;
        return arg1;
    }

    @SuppressLint(value={"SimpleDateFormat"}) private String a(long arg3) {
        try {
            String[] v3_1 = new SimpleDateFormat("mm:ss").format(new Date(arg3)).split(":");
            int v4 = Integer.parseInt(v3_1[0]) - 30;
            return v4 + ":" + v3_1[1];
        }
        catch(Exception v3) {
            v3.printStackTrace();
            return " ";
        }
    }

    static String a(VideoActivity arg0, long arg1) {
        return arg0.a(arg1);
    }

    private void a(float arg4) {
        int v0 = this.g.getCurrentPosition() - (((int)(arg4 / this.o * (((float)this.g.getDuration())))));
        this.g.seekTo(v0);
        this.j.setProgress(v0 * 100 / this.g.getDuration());
        this.l.setText(this.a(((long)v0)));
    }

    static void a(VideoActivity arg0) {
        arg0.e();
    }

    static boolean a(VideoActivity arg0, boolean arg1) {
        arg0.D = arg1;
        return arg1;
    }

    public String a() {
        return this.e;
    }

    public void a(String arg1) {
        this.e = arg1;
    }

    static float b(VideoActivity arg0, float arg1) {
        arg0.z = arg1;
        return arg1;
    }

    static int b(VideoActivity arg0, int arg1) {
        arg0.B = arg1;
        return arg1;
    }

    static VideoView b(VideoActivity arg0) {
        return arg0.g;
    }

    private void b(float arg4) {
        int v0 = this.g.getCurrentPosition() + (((int)(arg4 / this.o * (((float)this.g.getDuration())))));
        this.g.seekTo(v0);
        this.j.setProgress(v0 * 100 / this.g.getDuration());
        this.l.setText(this.a(((long)v0)));
    }

    private void b(String arg4) {
        this.d();
        try {
            this.f.setVisibility(4);
        }
        catch(Exception v0) {
            v0.printStackTrace();
        }

        this.b.setVisibility(0);
        this.g.setVideoPath(arg4);
        this.g.requestFocus();
        this.g.setOnPreparedListener(new MediaPlayer$OnPreparedListener() {
            public void onPrepared(MediaPlayer arg8) {
                VideoActivity.o(this.a).setVisibility(0);
                VideoActivity.p(this.a);
                VideoActivity.b(this.a).start();
                this.a.b.setVisibility(8);
                if(VideoActivity.q(this.a) != 0) {
                    VideoActivity.b(this.a).seekTo(VideoActivity.q(this.a));
                }

                VideoActivity.f(this.a).removeCallbacks(VideoActivity.e(this.a));
                VideoActivity.f(this.a).postDelayed(VideoActivity.e(this.a), 5000);
                VideoActivity.r(this.a).setText(VideoActivity.a(this.a, ((long)VideoActivity.b(this.a).getDuration())));
                new Timer().schedule(new TimerTask() {
                    public void run() {
                        VideoActivity.f(this.a.a).sendEmptyMessage(1);
                    }
                }, 0, 1000);
            }
        });
        this.g.setOnCompletionListener(new MediaPlayer$OnCompletionListener() {
            public void onCompletion(MediaPlayer arg3) {
                VideoActivity.p(this.a);
                VideoActivity.b(this.a).stopPlayback();
                arg3.release();
                VideoActivity.s(this.a).setVisibility(0);
                VideoActivity.s(this.a).clearAnimation();
                VideoActivity.s(this.a).startAnimation(AnimationUtils.loadAnimation(this.a.getApplicationContext(), 2130771991));
                VideoActivity.t(this.a).setVisibility(0);
                VideoActivity.t(this.a).clearAnimation();
                VideoActivity.t(this.a).startAnimation(AnimationUtils.loadAnimation(this.a.getApplicationContext(), 2130771990));
                this.a.d.setVisibility(0);
            }
        });
        this.g.setOnTouchListener(this.E);
        new Handler().postDelayed(new Runnable() {
            public void run() {
            }
        }, 200);
    }

    protected void b() {
        int v1 = 2;
        if(this.getResources().getConfiguration().orientation == v1) {
            Configuration v0 = new Configuration();
            v0.orientation = v1;
            this.onConfigurationChanged(v0);
        }

        this.s = new VolumnController(((Context)this));
        this.t = new LightnessController(((Context)this));
        this.g = this.findViewById(2131297083);
        this.l = this.findViewById(2131296795);
        this.m = this.findViewById(2131296978);
        this.k = this.findViewById(2131296794);
        this.j = this.findViewById(2131296883);
        this.h = this.findViewById(2131296975);
        this.i = this.findViewById(2131296332);
        this.b = this.findViewById(2131296773);
        this.d = this.findViewById(2131296628);
        this.f = this.findViewById(2131296856);
        this.g.setOnErrorListener(new MediaPlayer$OnErrorListener() {
            public boolean onError(MediaPlayer arg2, int arg3, int arg4) {
                FileLog.d("TELEGRAM playing error " + arg3 + " - " + arg4);
                if(arg3 != 100) {
                    this.a.d.setVisibility(0);
                    this.a.b.setVisibility(8);
                }
                else {
                    VideoActivity.n(this.a);
                }

                return 1;
            }
        });
        this.n = this.getSystemService("audio");
        this.o = DensityUtil.b(((Context)this));
        this.p = DensityUtil.a(((Context)this));
        this.C = DensityUtil.a(((Context)this), 18f);
        this.u = LightnessController.a(((Activity)this));
        this.k.setOnClickListener(((View$OnClickListener)this));
        this.j.setOnSeekBarChangeListener(this.x);
        this.c = this.findViewById(2131296686);
        this.d.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg1) {
                VideoActivity.n(this.a);
            }
        });
    }

    static TextView c(VideoActivity arg0) {
        return arg0.l;
    }

    private void c() {
        this.d.setVisibility(8);
        try {
            this.b(this.a());
        }
        catch(Exception v0) {
            FileLog.d("TELEGRAM " + v0.getMessage());
        }
    }

    private void c(float arg5) {
        int v0 = this.n.getStreamMaxVolume(3);
        int v2 = Math.max(this.n.getStreamVolume(3) - (((int)(arg5 / this.p * (((float)v0)) * 3f))), 0);
        this.n.setStreamVolume(3, v2, 0);
        this.s.a(((float)(v2 * 100 / v0)));
    }

    static void c(VideoActivity arg0, float arg1) {
        arg0.c(arg1);
    }

    private void d() {
        try {
            if(this.g.isPlaying()) {
                this.g.pause();
                this.k.setImageResource(2131231698);
                return;
            }

            this.g.start();
            this.k.setImageResource(2131231699);
        }
        catch(Exception v0) {
            FileLog.d("TELEGRAM" + v0.getMessage());
        }
    }

    static SeekBar d(VideoActivity arg0) {
        return arg0.j;
    }

    private void d(float arg5) {
        int v0 = this.n.getStreamMaxVolume(3);
        int v5 = Math.min(this.n.getStreamVolume(3) + (((int)(arg5 / this.p * (((float)v0)) * 3f))), v0);
        this.n.setStreamVolume(3, v5, 0);
        this.s.a(((float)(v5 * 100 / v0)));
    }

    static void d(VideoActivity arg0, float arg1) {
        arg0.d(arg1);
    }

    private void e() {
        if(this.h.getVisibility() == 0) {
            this.h.clearAnimation();
            Animation v0 = AnimationUtils.loadAnimation(((Context)this), 2130771993);
            v0.setAnimationListener(new Animation$AnimationListener() {
                public void onAnimationEnd(Animation arg2) {
                    VideoActivity.s(this.a).setVisibility(8);
                }

                public void onAnimationRepeat(Animation arg1) {
                }

                public void onAnimationStart(Animation arg1) {
                }
            });
            this.h.startAnimation(v0);
            this.i.clearAnimation();
            v0 = AnimationUtils.loadAnimation(((Context)this), 2130771992);
            v0.setAnimationListener(new Animation$AnimationListener() {
                public void onAnimationEnd(Animation arg2) {
                    VideoActivity.t(this.a).setVisibility(8);
                }

                public void onAnimationRepeat(Animation arg1) {
                }

                public void onAnimationStart(Animation arg1) {
                }
            });
            this.i.startAnimation(v0);
        }
        else {
            this.h.setVisibility(0);
            this.h.clearAnimation();
            this.h.startAnimation(AnimationUtils.loadAnimation(((Context)this), 2130771991));
            this.i.setVisibility(0);
            this.i.clearAnimation();
            this.i.startAnimation(AnimationUtils.loadAnimation(((Context)this), 2130771990));
            this.w.removeCallbacks(this.v);
            this.w.postDelayed(this.v, 5000);
        }
    }

    static Runnable e(VideoActivity arg0) {
        return arg0.v;
    }

    static void e(VideoActivity arg0, float arg1) {
        arg0.b(arg1);
    }

    static Handler f(VideoActivity arg0) {
        return arg0.w;
    }

    static void f(VideoActivity arg0, float arg1) {
        arg0.a(arg1);
    }

    static float g(VideoActivity arg0) {
        return arg0.y;
    }

    static float g(VideoActivity arg0, float arg1) {
        arg0.p = arg1;
        return arg1;
    }

    static float h(VideoActivity arg0) {
        return arg0.z;
    }

    static float h(VideoActivity arg0, float arg1) {
        arg0.o = arg1;
        return arg1;
    }

    static int i(VideoActivity arg0) {
        return arg0.C;
    }

    static float j(VideoActivity arg0) {
        return arg0.o;
    }

    static int k(VideoActivity arg0) {
        return arg0.A;
    }

    static int l(VideoActivity arg0) {
        return arg0.B;
    }

    static boolean m(VideoActivity arg0) {
        return arg0.D;
    }

    static void n(VideoActivity arg0) {
        arg0.c();
    }

    static View o(VideoActivity arg0) {
        return arg0.f;
    }

    public void onBackPressed() {
        if(this.getResources().getConfiguration().orientation == 2) {
            this.setRequestedOrientation(1);
        }
        else if(this.getResources().getConfiguration().orientation == 1) {
            super.onBackPressed();
            if(this.a) {
                this.startActivity(new Intent(this.getApplicationContext(), LaunchActivity.class));
            }

            this.finish();
        }
    }

    public void onClick(View arg2) {
        if(arg2.getId() == 2131296794) {
            this.d();
        }
    }

    public void onConfigurationChanged(Configuration arg3) {
        FileLog.d("TELEGRAM onconfigurationChanged");
        this.findViewById(2131296851).post(new Runnable() {
            public void run() {
                int v1 = 1024;
                int v2 = 2048;
                if(this.a.getResources().getConfiguration().orientation == 2) {
                    this.a.getWindow().clearFlags(v2);
                    this.a.getWindow().setFlags(v1, v1);
                    VideoActivity.g(this.a, DensityUtil.b(this.a));
                    VideoActivity.h(this.a, DensityUtil.a(this.a));
                    View v0 = this.a.findViewById(2131296851);
                    ViewGroup$LayoutParams v1_1 = ((RelativeLayout)v0).getLayoutParams();
                    ((LinearLayout$LayoutParams)v1_1).weight = 100f;
                    ((RelativeLayout)v0).setLayoutParams(v1_1);
                }
                else if(this.a.getResources().getConfiguration().orientation == 1) {
                    this.a.getWindow().clearFlags(v1);
                    this.a.getWindow().setFlags(v2, v2);
                    VideoActivity.b(this.a).setLayoutParams(new LinearLayout$LayoutParams(-1, -2));
                }
            }
        });
        super.onConfigurationChanged(arg3);
    }

    protected void onCreate(Bundle arg4) {
        super.onCreate(arg4);
        this.setContentView(2131493096);
        String v4 = this.getIntent().getStringExtra("EXTRA_VIDEO_URL");
        this.a = this.getIntent().getBooleanExtra("IS_FROM_PUSH", false);
        if(TextUtils.isEmpty(((CharSequence)v4))) {
            this.finish();
            return;
        }

        this.b();
        this.a(v4);
        this.c();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.w.removeMessages(0);
        this.w.removeCallbacksAndMessages(null);
    }

    protected void onPause() {
        super.onPause();
        LightnessController.a(((Activity)this), this.u);
    }

    protected void onResume() {
        super.onResume();
    }

    static void p(VideoActivity arg0) {
        arg0.d();
    }

    static int q(VideoActivity arg0) {
        return arg0.q;
    }

    static TextView r(VideoActivity arg0) {
        return arg0.m;
    }

    static View s(VideoActivity arg0) {
        return arg0.h;
    }

    static View t(VideoActivity arg0) {
        return arg0.i;
    }
}

