package org.telegram.ui.Components;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Keep;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View$OnClickListener;
import android.view.View$OnTouchListener;
import android.view.View;
import android.view.WindowManager$LayoutParams;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.Collection;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.PhotoViewer;

public class PipVideoView {
    class MiniControlsView extends FrameLayout {
        class org.telegram.ui.Components.PipVideoView$MiniControlsView$1 implements Runnable {
            org.telegram.ui.Components.PipVideoView$MiniControlsView$1(MiniControlsView arg1) {
                this.this$1 = arg1;
                super();
            }

            public void run() {
                this.this$1.show(false, true);
            }
        }

        class org.telegram.ui.Components.PipVideoView$MiniControlsView$2 implements Runnable {
            org.telegram.ui.Components.PipVideoView$MiniControlsView$2(MiniControlsView arg1) {
                this.this$1 = arg1;
                super();
            }

            public void run() {
                if(this.this$1.this$0.photoViewer == null) {
                    return;
                }

                VideoPlayer v0 = this.this$1.this$0.photoViewer.getVideoPlayer();
                if(v0 == null) {
                    return;
                }

                this.this$1.setProgress((((float)v0.getCurrentPosition())) / (((float)v0.getDuration())));
                if(this.this$1.this$0.photoViewer == null) {
                    this.this$1.setBufferedProgress((((float)v0.getBufferedPosition())) / (((float)v0.getDuration())));
                }

                AndroidUtilities.runOnUIThread(this.this$1.progressRunnable, 1000);
            }
        }

        private float bufferedPosition;
        private AnimatorSet currentAnimation;
        private Runnable hideRunnable;
        private ImageView inlineButton;
        private boolean isCompleted;
        private boolean isVisible;
        private ImageView playButton;
        private float progress;
        private Paint progressInnerPaint;
        private Paint progressPaint;
        private Runnable progressRunnable;

        public MiniControlsView(PipVideoView arg5, Context arg6, boolean arg7) {
            PipVideoView.this = arg5;
            super(arg6);
            this.isVisible = true;
            this.hideRunnable = new org.telegram.ui.Components.PipVideoView$MiniControlsView$1(this);
            this.progressRunnable = new org.telegram.ui.Components.PipVideoView$MiniControlsView$2(this);
            this.inlineButton = new ImageView(arg6);
            this.inlineButton.setScaleType(ImageView$ScaleType.CENTER);
            this.inlineButton.setImageResource(2131231213);
            int v1 = 48;
            this.addView(this.inlineButton, LayoutHelper.createFrame(56, v1, 53));
            this.inlineButton.setOnClickListener(new View$OnClickListener(arg5) {
                public void onClick(View arg1) {
                    if(this.this$1.this$0.parentSheet != null) {
                        this.this$1.this$0.parentSheet.exitFromPip();
                    }
                    else if(this.this$1.this$0.photoViewer != null) {
                        this.this$1.this$0.photoViewer.exitFromPip();
                    }
                }
            });
            if(arg7) {
                this.progressPaint = new Paint();
                this.progressPaint.setColor(-15095832);
                this.progressInnerPaint = new Paint();
                this.progressInnerPaint.setColor(-6975081);
                this.setWillNotDraw(false);
                this.playButton = new ImageView(arg6);
                this.playButton.setScaleType(ImageView$ScaleType.CENTER);
                this.addView(this.playButton, LayoutHelper.createFrame(v1, v1, 17));
                this.playButton.setOnClickListener(new View$OnClickListener(arg5) {
                    public void onClick(View arg2) {
                        if(this.this$1.this$0.photoViewer == null) {
                            return;
                        }

                        VideoPlayer v2 = this.this$1.this$0.photoViewer.getVideoPlayer();
                        if(v2 == null) {
                            return;
                        }

                        if(v2.isPlaying()) {
                            v2.pause();
                        }
                        else {
                            v2.play();
                        }

                        MiniControlsView.access$300(this.this$1);
                    }
                });
            }

            this.setOnTouchListener(new View$OnTouchListener(arg5) {
                public boolean onTouch(View arg1, MotionEvent arg2) {
                    return 1;
                }
            });
            this.updatePlayButton();
            this.show(false, false);
        }

        static Runnable access$100(MiniControlsView arg0) {
            return arg0.progressRunnable;
        }

        static boolean access$1102(MiniControlsView arg0, boolean arg1) {
            arg0.isCompleted = arg1;
            return arg1;
        }

        static float access$1202(MiniControlsView arg0, float arg1) {
            arg0.progress = arg1;
            return arg1;
        }

        static float access$1302(MiniControlsView arg0, float arg1) {
            arg0.bufferedPosition = arg1;
            return arg1;
        }

        static void access$300(MiniControlsView arg0) {
            arg0.updatePlayButton();
        }

        static AnimatorSet access$402(MiniControlsView arg0, AnimatorSet arg1) {
            arg0.currentAnimation = arg1;
            return arg1;
        }

        private void checkNeedHide() {
            AndroidUtilities.cancelRunOnUIThread(this.hideRunnable);
            if(this.isVisible) {
                AndroidUtilities.runOnUIThread(this.hideRunnable, 3000);
            }
        }

        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.checkNeedHide();
        }

        protected void onDraw(Canvas arg20) {
            MiniControlsView v0 = this;
            int v1 = this.getMeasuredWidth();
            float v3 = 3f;
            int v2 = this.getMeasuredHeight() - AndroidUtilities.dp(v3);
            AndroidUtilities.dp(7f);
            float v1_1 = ((float)v1);
            int v5 = ((int)(v0.progress * v1_1));
            if(v0.bufferedPosition != 0f) {
                float v8 = ((float)0);
                arg20.drawRect(v8, ((float)v2), v8 + v1_1 * v0.bufferedPosition, ((float)(AndroidUtilities.dp(v3) + v2)), v0.progressInnerPaint);
            }

            arg20.drawRect(((float)0), ((float)v2), ((float)v5), ((float)(v2 + AndroidUtilities.dp(v3))), v0.progressPaint);
        }

        public boolean onInterceptTouchEvent(MotionEvent arg2) {
            if(arg2.getAction() == 0) {
                if(!this.isVisible) {
                    this.show(true, true);
                    return 1;
                }
                else {
                    this.checkNeedHide();
                }
            }

            return super.onInterceptTouchEvent(arg2);
        }

        public void requestDisallowInterceptTouchEvent(boolean arg1) {
            super.requestDisallowInterceptTouchEvent(arg1);
            this.checkNeedHide();
        }

        public void setBufferedProgress(float arg1) {
            this.bufferedPosition = arg1;
            this.invalidate();
        }

        public void setProgress(float arg1) {
            this.progress = arg1;
            this.invalidate();
        }

        public void show(boolean arg7, boolean arg8) {
            org.telegram.ui.Components.PipVideoView$MiniControlsView$6 v8;
            AnimatorSet v7_1;
            float v7;
            if(this.isVisible == arg7) {
                return;
            }

            this.isVisible = arg7;
            if(this.currentAnimation != null) {
                this.currentAnimation.cancel();
            }

            long v0 = 150;
            if(this.isVisible) {
                v7 = 1f;
                if(arg8) {
                    this.currentAnimation = new AnimatorSet();
                    this.currentAnimation.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, "alpha", new float[]{v7})});
                    this.currentAnimation.setDuration(v0);
                    v7_1 = this.currentAnimation;
                    v8 = new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator arg2) {
                            this.this$1.currentAnimation = null;
                        }
                    };
                    goto label_31;
                }
                else {
                    goto label_35;
                }
            }
            else {
                v7 = 0f;
                if(arg8) {
                    this.currentAnimation = new AnimatorSet();
                    this.currentAnimation.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, "alpha", new float[]{0f})});
                    this.currentAnimation.setDuration(v0);
                    v7_1 = this.currentAnimation;
                    org.telegram.ui.Components.PipVideoView$MiniControlsView$7 v8_1 = new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator arg2) {
                            this.this$1.currentAnimation = null;
                        }
                    };
                label_31:
                    v7_1.addListener(((Animator$AnimatorListener)v8));
                    this.currentAnimation.start();
                }
                else {
                label_35:
                    this.setAlpha(v7);
                }
            }

            this.checkNeedHide();
        }

        private void updatePlayButton() {
            int v1;
            ImageView v0_1;
            if(PipVideoView.this.photoViewer == null) {
                return;
            }

            VideoPlayer v0 = PipVideoView.this.photoViewer.getVideoPlayer();
            if(v0 == null) {
                return;
            }

            AndroidUtilities.cancelRunOnUIThread(this.progressRunnable);
            if(!v0.isPlaying()) {
                if(this.isCompleted) {
                    v0_1 = this.playButton;
                    v1 = 2131231125;
                }
                else {
                    v0_1 = this.playButton;
                    v1 = 2131231224;
                }

                v0_1.setImageResource(v1);
            }
            else {
                this.playButton.setImageResource(2131231215);
                AndroidUtilities.runOnUIThread(this.progressRunnable, 500);
            }
        }
    }

    private View controlsView;
    private DecelerateInterpolator decelerateInterpolator;
    private Activity parentActivity;
    private EmbedBottomSheet parentSheet;
    private PhotoViewer photoViewer;
    private SharedPreferences preferences;
    private int videoHeight;
    private int videoWidth;
    private WindowManager$LayoutParams windowLayoutParams;
    private WindowManager windowManager;
    private FrameLayout windowView;

    public PipVideoView() {
        super();
    }

    static PhotoViewer access$000(PipVideoView arg0) {
        return arg0.photoViewer;
    }

    static void access$1000(PipVideoView arg0) {
        arg0.animateToBoundsMaybe();
    }

    static EmbedBottomSheet access$200(PipVideoView arg0) {
        return arg0.parentSheet;
    }

    static View access$500(PipVideoView arg0) {
        return arg0.controlsView;
    }

    static WindowManager$LayoutParams access$600(PipVideoView arg0) {
        return arg0.windowLayoutParams;
    }

    static int access$700(PipVideoView arg0) {
        return arg0.videoWidth;
    }

    static FrameLayout access$800(PipVideoView arg0) {
        return arg0.windowView;
    }

    static WindowManager access$900(PipVideoView arg0) {
        return arg0.windowManager;
    }

    private void animateToBoundsMaybe() {
        ObjectAnimator v5_1;
        ArrayList v4_2;
        int[] v9;
        String v4_1;
        ArrayList v0_1;
        int v0 = PipVideoView.getSideCoord(true, 0, 0f, this.videoWidth);
        int v4 = PipVideoView.getSideCoord(true, 1, 0f, this.videoWidth);
        int v5 = PipVideoView.getSideCoord(false, 0, 0f, this.videoHeight);
        int v6 = PipVideoView.getSideCoord(false, 1, 0f, this.videoHeight);
        SharedPreferences$Editor v7 = this.preferences.edit();
        int v8 = AndroidUtilities.dp(20f);
        int v10 = 2;
        float v11 = 1f;
        if(Math.abs(v0 - this.windowLayoutParams.x) > v8) {
            if(this.windowLayoutParams.x < 0 && this.windowLayoutParams.x > -this.videoWidth / 4) {
                goto label_112;
            }

            if(Math.abs(v4 - this.windowLayoutParams.x) > v8) {
                if(this.windowLayoutParams.x > AndroidUtilities.displaySize.x - this.videoWidth && this.windowLayoutParams.x < AndroidUtilities.displaySize.x - this.videoWidth / 4 * 3) {
                    goto label_93;
                }

                if(this.windowView.getAlpha() != v11) {
                    v0_1 = new ArrayList();
                    if(this.windowLayoutParams.x < 0) {
                        v4_1 = "x";
                        v9 = new int[]{-this.videoWidth};
                    }
                    else {
                        v4_1 = "x";
                        v9 = new int[]{AndroidUtilities.displaySize.x};
                    }

                    v0_1.add(ObjectAnimator.ofInt(this, v4_1, v9));
                    v4_2 = v0_1;
                    v0 = 1;
                    goto label_131;
                }

                v7.putFloat("px", (((float)(this.windowLayoutParams.x - v0))) / (((float)(v4 - v0))));
                v7.putInt("sidex", v10);
                v0_1 = null;
            }
            else {
            label_93:
                v0_1 = new ArrayList();
                v7.putInt("sidex", 1);
                if(this.windowView.getAlpha() != v11) {
                    v0_1.add(ObjectAnimator.ofFloat(this.windowView, "alpha", new float[]{v11}));
                }

                v0_1.add(ObjectAnimator.ofInt(this, "x", new int[]{v4}));
            }

            v4_2 = v0_1;
            goto label_130;
        }
        else {
        label_112:
            v4_2 = new ArrayList();
            v7.putInt("sidex", 0);
            if(this.windowView.getAlpha() != v11) {
                v4_2.add(ObjectAnimator.ofFloat(this.windowView, "alpha", new float[]{v11}));
            }

            v4_2.add(ObjectAnimator.ofInt(this, "x", new int[]{v0}));
        label_130:
            v0 = 0;
        }

    label_131:
        if(v0 == 0) {
            if(Math.abs(v5 - this.windowLayoutParams.y) <= v8 || this.windowLayoutParams.y <= ActionBar.getCurrentActionBarHeight()) {
                if(v4_2 == null) {
                    v4_2 = new ArrayList();
                }

                v7.putInt("sidey", 0);
                v5_1 = ObjectAnimator.ofInt(this, "y", new int[]{v5});
            label_178:
                v4_2.add(v5_1);
            }
            else if(Math.abs(v6 - this.windowLayoutParams.y) <= v8) {
                if(v4_2 == null) {
                    v4_2 = new ArrayList();
                }

                v7.putInt("sidey", 1);
                v5_1 = ObjectAnimator.ofInt(this, "y", new int[]{v6});
                goto label_178;
            }
            else {
                v7.putFloat("py", (((float)(this.windowLayoutParams.y - v5))) / (((float)(v6 - v5))));
                v7.putInt("sidey", v10);
            }

            v7.commit();
        }

        if(v4_2 != null) {
            if(this.decelerateInterpolator == null) {
                this.decelerateInterpolator = new DecelerateInterpolator();
            }

            AnimatorSet v5_2 = new AnimatorSet();
            v5_2.setInterpolator(this.decelerateInterpolator);
            v5_2.setDuration(150);
            if(v0 != 0) {
                v4_2.add(ObjectAnimator.ofFloat(this.windowView, "alpha", new float[]{0f}));
                v5_2.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator arg1) {
                        if(PipVideoView.this.parentSheet != null) {
                            PipVideoView.this.parentSheet.destroy();
                        }
                        else if(PipVideoView.this.photoViewer != null) {
                            PipVideoView.this.photoViewer.destroyPhotoViewer();
                        }
                    }
                });
            }

            v5_2.playTogether(((Collection)v4_2));
            v5_2.start();
        }
    }

    public void close() {
        try {
            this.windowManager.removeView(this.windowView);
            goto label_3;
        }
        catch(Exception ) {
        label_3:
            this.parentSheet = null;
            this.photoViewer = null;
            this.parentActivity = null;
            return;
        }
    }

    public static Rect getPipRect(float arg9) {
        // Method was not decompiled
    }

    private static int getSideCoord(boolean arg2, int arg3, float arg4, int arg5) {
        int v0;
        if(arg2) {
            v0 = AndroidUtilities.displaySize.x;
        }
        else {
            v0 = AndroidUtilities.displaySize.y - arg5;
            arg5 = ActionBar.getCurrentActionBarHeight();
        }

        v0 -= arg5;
        float v5 = 10f;
        if(arg3 == 0) {
            arg3 = AndroidUtilities.dp(v5);
        }
        else if(arg3 == 1) {
            arg3 = v0 - AndroidUtilities.dp(v5);
        }
        else {
            arg3 = Math.round((((float)(v0 - AndroidUtilities.dp(20f)))) * arg4) + AndroidUtilities.dp(v5);
        }

        if(!arg2) {
            arg3 += ActionBar.getCurrentActionBarHeight();
        }

        return arg3;
    }

    @Keep public int getX() {
        return this.windowLayoutParams.x;
    }

    @Keep public int getY() {
        return this.windowLayoutParams.y;
    }

    public void onConfigurationChanged() {
        int v0 = this.preferences.getInt("sidex", 1);
        int v1 = this.preferences.getInt("sidey", 0);
        float v3 = this.preferences.getFloat("px", 0f);
        float v5 = this.preferences.getFloat("py", 0f);
        this.windowLayoutParams.x = PipVideoView.getSideCoord(true, v0, v3, this.videoWidth);
        this.windowLayoutParams.y = PipVideoView.getSideCoord(false, v1, v5, this.videoHeight);
        this.windowManager.updateViewLayout(this.windowView, this.windowLayoutParams);
    }

    public void onVideoCompleted() {
        if((this.controlsView instanceof MiniControlsView)) {
            View v0 = this.controlsView;
            MiniControlsView.access$1102(((MiniControlsView)v0), true);
            MiniControlsView.access$1202(((MiniControlsView)v0), 0f);
            MiniControlsView.access$1302(((MiniControlsView)v0), 0f);
            MiniControlsView.access$300(((MiniControlsView)v0));
            ((MiniControlsView)v0).invalidate();
            ((MiniControlsView)v0).show(true, true);
        }
    }

    public void setBufferedProgress(float arg2) {
        if((this.controlsView instanceof MiniControlsView)) {
            this.controlsView.setBufferedProgress(arg2);
        }
    }

    @Keep public void setX(int arg3) {
        this.windowLayoutParams.x = arg3;
        this.windowManager.updateViewLayout(this.windowView, this.windowLayoutParams);
    }

    @Keep public void setY(int arg3) {
        this.windowLayoutParams.y = arg3;
        this.windowManager.updateViewLayout(this.windowView, this.windowLayoutParams);
    }

    public TextureView show(Activity arg9, EmbedBottomSheet arg10, View arg11, float arg12, int arg13, WebView arg14) {
        return this.show(arg9, null, arg10, arg11, arg12, arg13, arg14);
    }

    public TextureView show(Activity arg4, PhotoViewer arg5, EmbedBottomSheet arg6, View arg7, float arg8, int arg9, WebView arg10) {
        // Method was not decompiled
    }

    public TextureView show(Activity arg9, PhotoViewer arg10, float arg11, int arg12) {
        return this.show(arg9, arg10, null, null, arg11, arg12, null);
    }

    public void updatePlayButton() {
        if((this.controlsView instanceof MiniControlsView)) {
            View v0 = this.controlsView;
            MiniControlsView.access$300(((MiniControlsView)v0));
            ((MiniControlsView)v0).invalidate();
        }
    }
}

