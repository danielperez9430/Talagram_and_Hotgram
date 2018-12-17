package org.telegram.ui.Components.voip;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.Collection;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;

public class CallSwipeView extends View {
    class ArrowAnimWrapper {
        private int index;

        public ArrowAnimWrapper(CallSwipeView arg1, int arg2) {
            CallSwipeView.this = arg1;
            super();
            this.index = arg2;
        }

        public int getArrowAlpha() {
            return CallSwipeView.this.arrowAlphas[this.index];
        }

        public void setArrowAlpha(int arg3) {
            CallSwipeView.this.arrowAlphas[this.index] = arg3;
        }
    }

    public interface Listener {
        void onDragCancel();

        void onDragComplete();

        void onDragStart();
    }

    private boolean animatingArrows;
    private Path arrow;
    private int[] arrowAlphas;
    private AnimatorSet arrowAnim;
    private Paint arrowsPaint;
    private boolean canceled;
    private boolean dragFromRight;
    private float dragStartX;
    private boolean dragging;
    private Listener listener;
    private Paint pullBgPaint;
    private RectF tmpRect;
    private View viewToDrag;

    public CallSwipeView(Context arg2) {
        super(arg2);
        this.arrowAlphas = new int[]{64, 64, 64};
        this.dragging = false;
        this.tmpRect = new RectF();
        this.arrow = new Path();
        this.animatingArrows = false;
        this.canceled = false;
        this.init();
    }

    static AnimatorSet access$000(CallSwipeView arg0) {
        return arg0.arrowAnim;
    }

    static boolean access$100(CallSwipeView arg0) {
        return arg0.canceled;
    }

    static boolean access$102(CallSwipeView arg0, boolean arg1) {
        arg0.canceled = arg1;
        return arg1;
    }

    static boolean access$200(CallSwipeView arg0) {
        return arg0.animatingArrows;
    }

    static int[] access$300(CallSwipeView arg0) {
        return arg0.arrowAlphas;
    }

    private int getDraggedViewWidth() {
        return this.getHeight();
    }

    private void init() {
        this.arrowsPaint = new Paint(1);
        this.arrowsPaint.setColor(-1);
        this.arrowsPaint.setStyle(Paint$Style.STROKE);
        this.arrowsPaint.setStrokeWidth(((float)AndroidUtilities.dp(2.5f)));
        this.pullBgPaint = new Paint(1);
        ArrayList v0 = new ArrayList();
        int v1;
        for(v1 = 0; v1 < this.arrowAlphas.length; ++v1) {
            ObjectAnimator v2 = ObjectAnimator.ofInt(new ArrowAnimWrapper(this, v1), "arrowAlpha", new int[]{64, 255, 64});
            v2.setDuration(700);
            v2.setStartDelay(((long)(v1 * 200)));
            v0.add(v2);
        }

        this.arrowAnim = new AnimatorSet();
        this.arrowAnim.playTogether(((Collection)v0));
        this.arrowAnim.addListener(new AnimatorListenerAdapter() {
            class org.telegram.ui.Components.voip.CallSwipeView$1$1 implements Runnable {
                org.telegram.ui.Components.voip.CallSwipeView$1$1(org.telegram.ui.Components.voip.CallSwipeView$1 arg1) {
                    this.this$1 = arg1;
                    super();
                }

                public void run() {
                    if(this.this$1.this$0.arrowAnim != null) {
                        this.this$1.this$0.arrowAnim.start();
                    }
                }
            }

            private Runnable restarter;
            private long startTime;

            public void onAnimationCancel(Animator arg2) {
                CallSwipeView.this.canceled = true;
            }

            public void onAnimationEnd(Animator arg7) {
                if(System.currentTimeMillis() - this.startTime < arg7.getDuration() / 4) {
                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.w("Not repeating animation because previous loop was too fast");
                    }

                    return;
                }

                if(!CallSwipeView.this.canceled && (CallSwipeView.this.animatingArrows)) {
                    CallSwipeView.this.post(this.restarter);
                }
            }

            public void onAnimationStart(Animator arg3) {
                this.startTime = System.currentTimeMillis();
            }
        });
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(this.arrowAnim != null) {
            this.canceled = true;
            this.arrowAnim.cancel();
            this.arrowAnim = null;
        }
    }

    protected void onDraw(Canvas arg8) {
        // Method was not decompiled
    }

    public boolean onTouchEvent(MotionEvent arg7) {
        if(!this.isEnabled()) {
            return 0;
        }

        if(arg7.getAction() == 0) {
            if((this.dragFromRight) || arg7.getX() >= (((float)this.getDraggedViewWidth()))) {
                if(!this.dragFromRight) {
                }
                else if(arg7.getX() > (((float)(this.getWidth() - this.getDraggedViewWidth())))) {
                    goto label_21;
                }

                goto label_88;
            }

        label_21:
            this.dragging = true;
            this.dragStartX = arg7.getX();
            this.getParent().requestDisallowInterceptTouchEvent(true);
            this.listener.onDragStart();
            this.stopAnimatingArrows();
        }
        else {
            float v4 = 0f;
            if(arg7.getAction() == 2) {
                View v0 = this.viewToDrag;
                float v1 = this.dragFromRight ? ((float)(-(this.getWidth() - this.getDraggedViewWidth()))) : 0f;
                float v7 = arg7.getX() - this.dragStartX;
                if(this.dragFromRight) {
                }
                else {
                    v4 = ((float)(this.getWidth() - this.getDraggedViewWidth()));
                }

                v0.setTranslationX(Math.max(v1, Math.min(v7, v4)));
                this.invalidate();
                goto label_88;
            }

            if(arg7.getAction() != 1 && arg7.getAction() != 3) {
                goto label_88;
            }

            if(Math.abs(this.viewToDrag.getTranslationX()) >= (((float)(this.getWidth() - this.getDraggedViewWidth()))) && arg7.getAction() == 1) {
                this.listener.onDragComplete();
                goto label_88;
            }

            this.listener.onDragCancel();
            this.viewToDrag.animate().translationX(0f).setDuration(200).start();
            this.invalidate();
            this.startAnimatingArrows();
            this.dragging = false;
        }

    label_88:
        return this.dragging;
    }

    public void reset() {
        if(this.arrowAnim != null) {
            if(this.canceled) {
            }
            else {
                this.listener.onDragCancel();
                this.viewToDrag.animate().translationX(0f).setDuration(200).start();
                this.invalidate();
                this.startAnimatingArrows();
                this.dragging = false;
            }
        }
    }

    public void setColor(int arg2) {
        this.pullBgPaint.setColor(arg2);
        this.pullBgPaint.setAlpha(178);
    }

    public void setListener(Listener arg1) {
        this.listener = arg1;
    }

    public void setViewToDrag(View arg1, boolean arg2) {
        this.viewToDrag = arg1;
        this.dragFromRight = arg2;
        this.updateArrowPath();
    }

    public void startAnimatingArrows() {
        if(!this.animatingArrows) {
            if(this.arrowAnim == null) {
            }
            else {
                this.animatingArrows = true;
                if(this.arrowAnim != null) {
                    this.arrowAnim.start();
                }
            }
        }
    }

    public void stopAnimatingArrows() {
        this.animatingArrows = false;
    }

    private void updateArrowPath() {
        this.arrow.reset();
        int v0 = AndroidUtilities.dp(6f);
        if(this.dragFromRight) {
            float v3 = ((float)v0);
            this.arrow.moveTo(v3, ((float)(-v0)));
            this.arrow.lineTo(0f, 0f);
            this.arrow.lineTo(v3, v3);
        }
        else {
            this.arrow.moveTo(0f, ((float)(-v0)));
            float v0_1 = ((float)v0);
            this.arrow.lineTo(v0_1, 0f);
            this.arrow.lineTo(0f, v0_1);
        }
    }
}

