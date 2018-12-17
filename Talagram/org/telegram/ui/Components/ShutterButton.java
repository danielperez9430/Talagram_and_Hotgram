package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import org.telegram.messenger.AndroidUtilities;

public class ShutterButton extends View {
    class org.telegram.ui.Components.ShutterButton$1 implements Runnable {
        org.telegram.ui.Components.ShutterButton$1(ShutterButton arg1) {
            ShutterButton.this = arg1;
            super();
        }

        public void run() {
            if(ShutterButton.this.delegate != null && !ShutterButton.this.delegate.shutterLongPressed()) {
                ShutterButton.this.processRelease = false;
            }
        }
    }

    public interface ShutterButtonDelegate {
        void shutterCancel();

        boolean shutterLongPressed();

        void shutterReleased();
    }

    public enum State {
        public static final enum State DEFAULT;
        public static final enum State RECORDING;

        static {
            State.DEFAULT = new State("DEFAULT", 0);
            State.RECORDING = new State("RECORDING", 1);
            State.$VALUES = new State[]{State.DEFAULT, State.RECORDING};
        }

        private State(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static State valueOf(String arg1) {
            return Enum.valueOf(State.class, arg1);
        }

        public static State[] values() {
            // Method was not decompiled
        }
    }

    private static final int LONG_PRESS_TIME = 800;
    private ShutterButtonDelegate delegate;
    private DecelerateInterpolator interpolator;
    private long lastUpdateTime;
    private Runnable longPressed;
    private boolean pressed;
    private boolean processRelease;
    private Paint redPaint;
    private float redProgress;
    private Drawable shadowDrawable;
    private State state;
    private long totalTime;
    private Paint whitePaint;

    public ShutterButton(Context arg3) {
        super(arg3);
        this.interpolator = new DecelerateInterpolator();
        this.longPressed = new org.telegram.ui.Components.ShutterButton$1(this);
        this.shadowDrawable = this.getResources().getDrawable(2131230972);
        this.whitePaint = new Paint(1);
        this.whitePaint.setStyle(Paint$Style.FILL);
        this.whitePaint.setColor(-1);
        this.redPaint = new Paint(1);
        this.redPaint.setStyle(Paint$Style.FILL);
        this.redPaint.setColor(-3324089);
        this.state = State.DEFAULT;
    }

    static ShutterButtonDelegate access$000(ShutterButton arg0) {
        return arg0.delegate;
    }

    static boolean access$102(ShutterButton arg0, boolean arg1) {
        arg0.processRelease = arg1;
        return arg1;
    }

    public ShutterButtonDelegate getDelegate() {
        return this.delegate;
    }

    public State getState() {
        return this.state;
    }

    protected void onDraw(Canvas arg10) {
        float v3_1;
        int v0 = this.getMeasuredWidth() / 2;
        int v1 = this.getMeasuredHeight() / 2;
        this.shadowDrawable.setBounds(v0 - AndroidUtilities.dp(36f), v1 - AndroidUtilities.dp(36f), AndroidUtilities.dp(36f) + v0, AndroidUtilities.dp(36f) + v1);
        this.shadowDrawable.draw(arg10);
        float v4 = 1f;
        if((this.pressed) || this.getScaleX() != v4) {
            float v2 = (this.getScaleX() - v4) / 0.06f;
            this.whitePaint.setAlpha(((int)(255f * v2)));
            float v0_1 = ((float)v0);
            float v1_1 = ((float)v1);
            float v5 = 26f;
            arg10.drawCircle(v0_1, v1_1, ((float)AndroidUtilities.dp(v5)), this.whitePaint);
            if(this.state == State.RECORDING) {
                if(this.redProgress != v4) {
                    long v3 = Math.abs(System.currentTimeMillis() - this.lastUpdateTime);
                    long v6 = 17;
                    if(v3 > v6) {
                        v3 = v6;
                    }

                    this.totalTime += v3;
                    v6 = 120;
                    if(this.totalTime > v6) {
                        this.totalTime = v6;
                    }

                    this.redProgress = this.interpolator.getInterpolation((((float)this.totalTime)) / 120f);
                    this.invalidate();
                }

                v3_1 = (((float)AndroidUtilities.dp(v5))) * v2;
                v2 = this.redProgress;
            }
            else {
                if(this.redProgress == 0f) {
                    return;
                }

                v3_1 = ((float)AndroidUtilities.dp(v5));
            }

            arg10.drawCircle(v0_1, v1_1, v3_1 * v2, this.redPaint);
        }
        else if(this.redProgress != 0f) {
            this.redProgress = 0f;
        }
    }

    protected void onMeasure(int arg1, int arg2) {
        this.setMeasuredDimension(AndroidUtilities.dp(84f), AndroidUtilities.dp(84f));
    }

    public boolean onTouchEvent(MotionEvent arg6) {
        float v0 = arg6.getX();
        float v1 = arg6.getX();
        switch(arg6.getAction()) {
            case 0: {
                AndroidUtilities.runOnUIThread(this.longPressed, 800);
                this.pressed = true;
                this.processRelease = true;
                this.setHighlighted(true);
                break;
            }
            case 1: {
                this.setHighlighted(false);
                AndroidUtilities.cancelRunOnUIThread(this.longPressed);
                if(!this.processRelease) {
                    return 1;
                }

                if(v0 < 0f) {
                    return 1;
                }

                if(v1 < 0f) {
                    return 1;
                }

                if(v0 > (((float)this.getMeasuredWidth()))) {
                    return 1;
                }

                if(v1 > (((float)this.getMeasuredHeight()))) {
                    return 1;
                }

                this.delegate.shutterReleased();
                break;
            }
            case 2: {
                if(v0 >= 0f && v1 >= 0f && v0 <= (((float)this.getMeasuredWidth())) && v1 <= (((float)this.getMeasuredHeight()))) {
                    return 1;
                }

                AndroidUtilities.cancelRunOnUIThread(this.longPressed);
                if(this.state != State.RECORDING) {
                    return 1;
                }

                this.setHighlighted(false);
                this.delegate.shutterCancel();
                this.setState(State.DEFAULT, true);
                break;
            }
            case 3: {
                this.setHighlighted(false);
                this.pressed = false;
                break;
            }
            default: {
                break;
            }
        }

        return 1;
    }

    public void setDelegate(ShutterButtonDelegate arg1) {
        this.delegate = arg1;
    }

    private void setHighlighted(boolean arg7) {
        Animator[] v7;
        AnimatorSet v0 = new AnimatorSet();
        int v1 = 2;
        if(arg7) {
            v7 = new Animator[v1];
            v7[0] = ObjectAnimator.ofFloat(this, "scaleX", new float[]{1.06f});
            v7[1] = ObjectAnimator.ofFloat(this, "scaleY", new float[]{1.06f});
            v0.playTogether(v7);
        }
        else {
            v7 = new Animator[v1];
            v7[0] = ObjectAnimator.ofFloat(this, "scaleX", new float[]{1f});
            v7[1] = ObjectAnimator.ofFloat(this, "scaleY", new float[]{1f});
            v0.playTogether(v7);
            v0.setStartDelay(40);
        }

        v0.setDuration(120);
        v0.setInterpolator(this.interpolator);
        v0.start();
    }

    public void setScaleX(float arg1) {
        super.setScaleX(arg1);
        this.invalidate();
    }

    public void setState(State arg3, boolean arg4) {
        if(this.state != arg3) {
            this.state = arg3;
            float v3 = 0f;
            if(arg4) {
                this.lastUpdateTime = System.currentTimeMillis();
                this.totalTime = 0;
                if(this.state == State.RECORDING) {
                    goto label_19;
                }

                goto label_12;
            }
            else if(this.state == State.RECORDING) {
                v3 = 1f;
                goto label_12;
            }
            else {
            label_12:
                this.redProgress = v3;
            }

        label_19:
            this.invalidate();
        }
    }
}

