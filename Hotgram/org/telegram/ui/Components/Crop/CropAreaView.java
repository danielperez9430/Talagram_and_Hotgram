package org.telegram.ui.Components.Crop;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build$VERSION;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import org.telegram.messenger.AndroidUtilities;

public class CropAreaView extends View {
    class org.telegram.ui.Components.Crop.CropAreaView$3 {
        static {
            org.telegram.ui.Components.Crop.CropAreaView$3.$SwitchMap$org$telegram$ui$Components$Crop$CropAreaView$Control = new int[Control.values().length];
            try {
                org.telegram.ui.Components.Crop.CropAreaView$3.$SwitchMap$org$telegram$ui$Components$Crop$CropAreaView$Control[Control.TOP_LEFT.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    org.telegram.ui.Components.Crop.CropAreaView$3.$SwitchMap$org$telegram$ui$Components$Crop$CropAreaView$Control[Control.TOP_RIGHT.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        org.telegram.ui.Components.Crop.CropAreaView$3.$SwitchMap$org$telegram$ui$Components$Crop$CropAreaView$Control[Control.BOTTOM_LEFT.ordinal()] = 3;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                        try {
                        label_19:
                            org.telegram.ui.Components.Crop.CropAreaView$3.$SwitchMap$org$telegram$ui$Components$Crop$CropAreaView$Control[Control.BOTTOM_RIGHT.ordinal()] = 4;
                            goto label_24;
                        }
                        catch(NoSuchFieldError ) {
                            try {
                            label_24:
                                org.telegram.ui.Components.Crop.CropAreaView$3.$SwitchMap$org$telegram$ui$Components$Crop$CropAreaView$Control[Control.TOP.ordinal()] = 5;
                                goto label_29;
                            }
                            catch(NoSuchFieldError ) {
                                try {
                                label_29:
                                    org.telegram.ui.Components.Crop.CropAreaView$3.$SwitchMap$org$telegram$ui$Components$Crop$CropAreaView$Control[Control.LEFT.ordinal()] = 6;
                                    goto label_34;
                                }
                                catch(NoSuchFieldError ) {
                                    try {
                                    label_34:
                                        org.telegram.ui.Components.Crop.CropAreaView$3.$SwitchMap$org$telegram$ui$Components$Crop$CropAreaView$Control[Control.RIGHT.ordinal()] = 7;
                                        goto label_39;
                                    }
                                    catch(NoSuchFieldError ) {
                                        try {
                                        label_39:
                                            org.telegram.ui.Components.Crop.CropAreaView$3.$SwitchMap$org$telegram$ui$Components$Crop$CropAreaView$Control[Control.BOTTOM.ordinal()] = 8;
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
        }
    }

    interface AreaViewListener {
        void onAreaChange();

        void onAreaChangeBegan();

        void onAreaChangeEnded();
    }

    enum Control {
        public static final enum Control BOTTOM;
        public static final enum Control BOTTOM_LEFT;
        public static final enum Control BOTTOM_RIGHT;
        public static final enum Control LEFT;
        public static final enum Control NONE;
        public static final enum Control RIGHT;
        public static final enum Control TOP;
        public static final enum Control TOP_LEFT;
        public static final enum Control TOP_RIGHT;

        static {
            Control.NONE = new Control("NONE", 0);
            Control.TOP_LEFT = new Control("TOP_LEFT", 1);
            Control.TOP_RIGHT = new Control("TOP_RIGHT", 2);
            Control.BOTTOM_LEFT = new Control("BOTTOM_LEFT", 3);
            Control.BOTTOM_RIGHT = new Control("BOTTOM_RIGHT", 4);
            Control.TOP = new Control("TOP", 5);
            Control.LEFT = new Control("LEFT", 6);
            Control.BOTTOM = new Control("BOTTOM", 7);
            Control.RIGHT = new Control("RIGHT", 8);
            Control.$VALUES = new Control[]{Control.NONE, Control.TOP_LEFT, Control.TOP_RIGHT, Control.BOTTOM_LEFT, Control.BOTTOM_RIGHT, Control.TOP, Control.LEFT, Control.BOTTOM, Control.RIGHT};
        }

        private Control(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static Control valueOf(String arg1) {
            return Enum.valueOf(Control.class, arg1);
        }

        public static Control[] values() {
            // Method was not decompiled
        }
    }

    enum GridType {
        public static final enum GridType MAJOR;
        public static final enum GridType MINOR;
        public static final enum GridType NONE;

        static {
            GridType.NONE = new GridType("NONE", 0);
            GridType.MINOR = new GridType("MINOR", 1);
            GridType.MAJOR = new GridType("MAJOR", 2);
            GridType.$VALUES = new GridType[]{GridType.NONE, GridType.MINOR, GridType.MAJOR};
        }

        private GridType(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static GridType valueOf(String arg1) {
            return Enum.valueOf(GridType.class, arg1);
        }

        public static GridType[] values() {
            // Method was not decompiled
        }
    }

    private Control activeControl;
    private RectF actualRect;
    private Animator animator;
    private RectF bottomEdge;
    private RectF bottomLeftCorner;
    private float bottomPadding;
    private RectF bottomRightCorner;
    Paint dimPaint;
    private boolean dimVisibile;
    Paint framePaint;
    private boolean frameVisible;
    private Animator gridAnimator;
    private float gridProgress;
    private GridType gridType;
    Paint handlePaint;
    AccelerateDecelerateInterpolator interpolator;
    private boolean isDragging;
    private RectF leftEdge;
    Paint linePaint;
    private AreaViewListener listener;
    private float lockAspectRatio;
    private float minWidth;
    private GridType previousGridType;
    private int previousX;
    private int previousY;
    private RectF rightEdge;
    Paint shadowPaint;
    private float sidePadding;
    private RectF tempRect;
    private RectF topEdge;
    private RectF topLeftCorner;
    private RectF topRightCorner;

    public CropAreaView(Context arg3) {
        super(arg3);
        this.topLeftCorner = new RectF();
        this.topRightCorner = new RectF();
        this.bottomLeftCorner = new RectF();
        this.bottomRightCorner = new RectF();
        this.topEdge = new RectF();
        this.leftEdge = new RectF();
        this.bottomEdge = new RectF();
        this.rightEdge = new RectF();
        this.actualRect = new RectF();
        this.tempRect = new RectF();
        this.interpolator = new AccelerateDecelerateInterpolator();
        this.frameVisible = true;
        this.dimVisibile = true;
        this.sidePadding = ((float)AndroidUtilities.dp(16f));
        this.minWidth = ((float)AndroidUtilities.dp(32f));
        this.gridType = GridType.NONE;
        this.dimPaint = new Paint();
        this.dimPaint.setColor(-872415232);
        this.shadowPaint = new Paint();
        this.shadowPaint.setStyle(Paint$Style.FILL);
        this.shadowPaint.setColor(436207616);
        this.shadowPaint.setStrokeWidth(((float)AndroidUtilities.dp(2f)));
        this.linePaint = new Paint();
        this.linePaint.setStyle(Paint$Style.FILL);
        this.linePaint.setColor(-1);
        this.linePaint.setStrokeWidth(((float)AndroidUtilities.dp(1f)));
        this.handlePaint = new Paint();
        this.handlePaint.setStyle(Paint$Style.FILL);
        this.handlePaint.setColor(-1);
        this.framePaint = new Paint();
        this.framePaint.setStyle(Paint$Style.FILL);
        this.framePaint.setColor(-1291845633);
    }

    static Animator access$002(CropAreaView arg0, Animator arg1) {
        arg0.gridAnimator = arg1;
        return arg1;
    }

    static Animator access$102(CropAreaView arg0, Animator arg1) {
        arg0.animator = arg1;
        return arg1;
    }

    public void calculateRect(RectF arg13, float arg14) {
        int v0 = Build$VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0;
        float v0_1 = ((float)v0);
        float v1 = (((float)this.getMeasuredHeight())) - this.bottomPadding - v0_1;
        float v2 = (((float)this.getMeasuredWidth())) / v1;
        float v5 = 2f;
        float v3 = Math.min(((float)this.getMeasuredWidth()), v1) - this.sidePadding * v5;
        float v4 = (((float)this.getMeasuredWidth())) - this.sidePadding * v5;
        float v6 = v1 - this.sidePadding * v5;
        float v7 = (((float)this.getMeasuredWidth())) / v5;
        v0_1 += v1 / v5;
        if((((double)Math.abs(1f - arg14))) < 0.0001) {
            v3 /= v5;
            arg14 = v7 - v3;
            v1 = v0_1 - v3;
            v7 += v3;
            v0_1 += v3;
        }
        else if(arg14 > v2) {
            v1 = v4 / v5;
            v2 = v7 - v1;
            v4 = v4 / arg14 / v5;
            arg14 = v0_1 - v4;
            v7 += v1;
            v0_1 += v4;
            v1 = arg14;
            arg14 = v2;
        }
        else {
            arg14 = arg14 * v6 / v5;
            v1 = v7 - arg14;
            v6 /= v5;
            v2 = v0_1 - v6;
            v7 += arg14;
            v0_1 += v6;
            arg14 = v1;
            v1 = v2;
        }

        arg13.set(arg14, v1, v7, v0_1);
    }

    private void constrainRectByHeight(RectF arg3, float arg4) {
        float v0 = arg3.height();
        arg3.right = arg3.left + arg4 * v0;
        arg3.bottom = arg3.top + v0;
    }

    private void constrainRectByWidth(RectF arg3, float arg4) {
        float v0 = arg3.width();
        arg3.right = arg3.left + v0;
        arg3.bottom = arg3.top + v0 / arg4;
    }

    public void fill(RectF arg7, Animator arg8, boolean arg9) {
        if(arg9) {
            if(this.animator != null) {
                this.animator.cancel();
                this.animator = null;
            }

            AnimatorSet v9 = new AnimatorSet();
            this.animator = ((Animator)v9);
            v9.setDuration(300);
            Animator[] v0 = new Animator[5];
            v0[0] = ObjectAnimator.ofFloat(this, "cropLeft", new float[]{arg7.left});
            v0[0].setInterpolator(this.interpolator);
            v0[1] = ObjectAnimator.ofFloat(this, "cropTop", new float[]{arg7.top});
            v0[1].setInterpolator(this.interpolator);
            v0[2] = ObjectAnimator.ofFloat(this, "cropRight", new float[]{arg7.right});
            v0[2].setInterpolator(this.interpolator);
            v0[3] = ObjectAnimator.ofFloat(this, "cropBottom", new float[]{arg7.bottom});
            v0[3].setInterpolator(this.interpolator);
            v0[4] = arg8;
            v0[4].setInterpolator(this.interpolator);
            v9.playTogether(v0);
            v9.addListener(new AnimatorListenerAdapter(arg7) {
                public void onAnimationEnd(Animator arg2) {
                    CropAreaView.this.setActualRect(this.val$targetRect);
                    CropAreaView.this.animator = null;
                }
            });
            v9.start();
        }
        else {
            this.setActualRect(arg7);
        }
    }

    public float getAspectRatio() {
        return (this.actualRect.right - this.actualRect.left) / (this.actualRect.bottom - this.actualRect.top);
    }

    public float getCropBottom() {
        return this.actualRect.bottom;
    }

    public float getCropCenterX() {
        return this.actualRect.left + (this.actualRect.right - this.actualRect.left) / 2f;
    }

    public float getCropCenterY() {
        return this.actualRect.top + (this.actualRect.bottom - this.actualRect.top) / 2f;
    }

    public float getCropHeight() {
        return this.actualRect.bottom - this.actualRect.top;
    }

    public float getCropLeft() {
        return this.actualRect.left;
    }

    public void getCropRect(RectF arg2) {
        arg2.set(this.actualRect);
    }

    public float getCropRight() {
        return this.actualRect.right;
    }

    public float getCropTop() {
        return this.actualRect.top;
    }

    public float getCropWidth() {
        return this.actualRect.right - this.actualRect.left;
    }

    private float getGridProgress() {
        return this.gridProgress;
    }

    public Interpolator getInterpolator() {
        return this.interpolator;
    }

    public float getLockAspectRatio() {
        return this.lockAspectRatio;
    }

    public RectF getTargetRectToFill() {
        RectF v0 = new RectF();
        this.calculateRect(v0, this.getAspectRatio());
        return v0;
    }

    public boolean isDragging() {
        return this.isDragging;
    }

    protected void onDraw(Canvas arg33) {
        int v29;
        int v31;
        int v28;
        int v30;
        float v2_1;
        float v1_1;
        float v13_1;
        int v15;
        int v13;
        float v17;
        CropAreaView v0 = this;
        int v1 = AndroidUtilities.dp(2f);
        int v2 = AndroidUtilities.dp(16f);
        int v3 = AndroidUtilities.dp(3f);
        int v4 = (((int)v0.actualRect.left)) - v1;
        int v5 = (((int)v0.actualRect.top)) - v1;
        int v7 = v1 * 2;
        int v6 = (((int)(v0.actualRect.right - v0.actualRect.left))) + v7;
        int v8 = (((int)(v0.actualRect.bottom - v0.actualRect.top))) + v7;
        if(v0.dimVisibile) {
            float v7_1 = ((float)(v5 + v1));
            arg33.drawRect(0f, 0f, ((float)this.getWidth()), v7_1, v0.dimPaint);
            v17 = ((float)(v5 + v8 - v1));
            arg33.drawRect(0f, v7_1, ((float)(v4 + v1)), v17, v0.dimPaint);
            arg33.drawRect(((float)(v4 + v6 - v1)), v7_1, ((float)this.getWidth()), v17, v0.dimPaint);
            arg33.drawRect(0f, v17, ((float)this.getWidth()), ((float)this.getHeight()), v0.dimPaint);
        }

        if(!v0.frameVisible) {
            return;
        }

        v7 = v3 - v1;
        int v9 = v3 * 2;
        int v10 = v6 - v9;
        v9 = v8 - v9;
        GridType v11 = v0.gridType;
        if(v11 == GridType.NONE && v0.gridProgress > 0f) {
            v11 = v0.previousGridType;
        }

        v0.shadowPaint.setAlpha(((int)(v0.gridProgress * 26f)));
        v0.linePaint.setAlpha(((int)(v0.gridProgress * 178f)));
        int v12 = 0;
        while(true) {
            v13 = 3;
            if(v12 >= v13) {
                break;
            }

            if(v11 == GridType.MINOR) {
                int v14 = 1;
                while(v14 < 4) {
                    if(v12 != 2 || v14 != v13) {
                        v15 = v4 + v3;
                        int v16 = v10 / 3;
                        v13_1 = ((float)(v15 + v16 / 3 * v14 + v16 * v12));
                        v28 = v2;
                        v2 = v5 + v3;
                        v29 = v8;
                        v30 = v1;
                        v31 = v6;
                        float v24 = ((float)v2);
                        float v26 = ((float)(v2 + v9));
                        arg33.drawLine(v13_1, v24, v13_1, v26, v0.shadowPaint);
                        arg33.drawLine(v13_1, v24, v13_1, v26, v0.linePaint);
                        v1_1 = ((float)v15);
                        v6 = v9 / 3;
                        v2_1 = ((float)(v2 + v6 / 3 * v14 + v6 * v12));
                        float v25 = ((float)(v15 + v10));
                        arg33.drawLine(v1_1, v2_1, v25, v2_1, v0.shadowPaint);
                        arg33.drawLine(v1_1, v2_1, v25, v2_1, v0.linePaint);
                    }
                    else {
                        v30 = v1;
                        v28 = v2;
                        v31 = v6;
                        v29 = v8;
                    }

                    ++v14;
                    v2 = v28;
                    v8 = v29;
                    v1 = v30;
                    v6 = v31;
                    v13 = 3;
                }

                v30 = v1;
                v28 = v2;
                v31 = v6;
                v29 = v8;
            }
            else {
                v30 = v1;
                v28 = v2;
                v31 = v6;
                v29 = v8;
                if(v11 != GridType.MAJOR) {
                    goto label_223;
                }

                if(v12 <= 0) {
                    goto label_223;
                }

                v1 = v4 + v3;
                v2_1 = ((float)(v10 / 3 * v12 + v1));
                v6 = v5 + v3;
                float v8_1 = ((float)v6);
                v17 = ((float)(v6 + v9));
                arg33.drawLine(v2_1, v8_1, v2_1, v17, v0.shadowPaint);
                arg33.drawLine(v2_1, v8_1, v2_1, v17, v0.linePaint);
                v2_1 = ((float)v1);
                float v6_1 = ((float)(v6 + v9 / 3 * v12));
                float v23 = ((float)(v1 + v10));
                arg33.drawLine(v2_1, v6_1, v23, v6_1, v0.shadowPaint);
                arg33.drawLine(v2_1, v6_1, v23, v6_1, v0.linePaint);
            }

        label_223:
            ++v12;
            v2 = v28;
            v8 = v29;
            v1 = v30;
            v6 = v31;
        }

        v30 = v1;
        v31 = v6;
        v1 = v4 + v7;
        v6 = v5 + v7;
        float v14_1 = ((float)v6);
        v15 = v4 + v31;
        v13 = v15 - v7;
        float v12_1 = ((float)v13);
        float v9_1 = ((float)v1);
        float v11_1 = v12_1;
        v17 = v12_1;
        v12_1 = ((float)(v6 + v30));
        v6 = v13;
        arg33.drawRect(v9_1, v14_1, v11_1, v12_1, v0.framePaint);
        v11_1 = ((float)(v1 + v30));
        v1 = v5 + v8;
        v7 = v1 - v7;
        v13_1 = ((float)v7);
        arg33.drawRect(v9_1, v14_1, v11_1, v13_1, v0.framePaint);
        arg33.drawRect(v9_1, ((float)(v7 - v30)), v17, v13_1, v0.framePaint);
        arg33.drawRect(((float)(v6 - v30)), v14_1, v17, v13_1, v0.framePaint);
        v12_1 = ((float)v5);
        v13_1 = ((float)(v4 + v2));
        float v10_1 = ((float)(v5 + v3));
        float v20 = ((float)v4);
        arg33.drawRect(v20, v12_1, v13_1, v10_1, v0.handlePaint);
        float v4_1 = ((float)(v4 + v3));
        float v5_1 = ((float)(v5 + v2));
        arg33.drawRect(v20, v12_1, v4_1, v5_1, v0.handlePaint);
        v14_1 = ((float)(v15 - v2));
        v11_1 = ((float)v15);
        arg33.drawRect(v14_1, v12_1, v11_1, v10_1, v0.handlePaint);
        float v15_1 = ((float)(v15 - v3));
        arg33.drawRect(v15_1, v12_1, v11_1, v5_1, v0.handlePaint);
        float v3_1 = ((float)(v1 - v3));
        v5_1 = ((float)v1);
        arg33.drawRect(v20, v3_1, v13_1, v5_1, v0.handlePaint);
        v1_1 = ((float)(v1 - v2));
        arg33.drawRect(v20, v1_1, v4_1, v5_1, v0.handlePaint);
        arg33.drawRect(v14_1, v3_1, v11_1, v5_1, v0.handlePaint);
        arg33.drawRect(v15_1, v1_1, v11_1, v5_1, v0.handlePaint);
    }

    public boolean onTouchEvent(MotionEvent arg8) {
        float v0_1;
        Control v8_1;
        int v0 = ((int)(arg8.getX() - this.getParent().getX()));
        int v1 = ((int)(arg8.getY() - this.getParent().getY()));
        int v2 = Build$VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0;
        float v2_1 = ((float)v2);
        int v8 = arg8.getActionMasked();
        if(v8 != 0) {
            goto label_77;
        }

        v2_1 = ((float)v0);
        float v5 = ((float)v1);
        if(this.topLeftCorner.contains(v2_1, v5)) {
            v8_1 = Control.TOP_LEFT;
        }
        else if(this.topRightCorner.contains(v2_1, v5)) {
            v8_1 = Control.TOP_RIGHT;
        }
        else if(this.bottomLeftCorner.contains(v2_1, v5)) {
            v8_1 = Control.BOTTOM_LEFT;
        }
        else if(this.bottomRightCorner.contains(v2_1, v5)) {
            v8_1 = Control.BOTTOM_RIGHT;
        }
        else if(this.leftEdge.contains(v2_1, v5)) {
            v8_1 = Control.LEFT;
        }
        else if(this.topEdge.contains(v2_1, v5)) {
            v8_1 = Control.TOP;
        }
        else if(this.rightEdge.contains(v2_1, v5)) {
            v8_1 = Control.RIGHT;
        }
        else if(this.bottomEdge.contains(v2_1, v5)) {
            v8_1 = Control.BOTTOM;
        }
        else {
            goto label_74;
        }

        this.activeControl = v8_1;
        this.previousX = v0;
        this.previousY = v1;
        this.setGridType(GridType.MAJOR, false);
        this.isDragging = true;
        if(this.listener != null) {
            this.listener.onAreaChangeBegan();
        }

        return 1;
    label_74:
        this.activeControl = Control.NONE;
        return 0;
    label_77:
        if(v8 != 1) {
            if(v8 == 3) {
            }
            else if(v8 != 2) {
                return 0;
            }
            else if(this.activeControl == Control.NONE) {
                return 0;
            }
            else {
                this.tempRect.set(this.actualRect);
                float v8_2 = ((float)(v0 - this.previousX));
                float v3 = ((float)(v1 - this.previousY));
                this.previousX = v0;
                this.previousY = v1;
                switch(org.telegram.ui.Components.Crop.CropAreaView$3.$SwitchMap$org$telegram$ui$Components$Crop$CropAreaView$Control[this.activeControl.ordinal()]) {
                    case 1: {
                        this.tempRect.left += v8_2;
                        this.tempRect.top += v3;
                        if(this.lockAspectRatio > 0f) {
                            v0_1 = this.tempRect.width();
                            v5 = this.tempRect.height();
                            if(Math.abs(v8_2) > Math.abs(v3)) {
                                this.constrainRectByWidth(this.tempRect, this.lockAspectRatio);
                            }
                            else {
                                this.constrainRectByHeight(this.tempRect, this.lockAspectRatio);
                            }

                            this.tempRect.left -= this.tempRect.width() - v0_1;
                            this.tempRect.top -= this.tempRect.width() - v5;
                        }
                        else {
                        }

                        break;
                    }
                    case 2: {
                        this.tempRect.right += v8_2;
                        this.tempRect.top += v3;
                        if(this.lockAspectRatio <= 0f) {
                            goto label_251;
                        }

                        v0_1 = this.tempRect.height();
                        if(Math.abs(v8_2) > Math.abs(v3)) {
                            this.constrainRectByWidth(this.tempRect, this.lockAspectRatio);
                        }
                        else {
                            this.constrainRectByHeight(this.tempRect, this.lockAspectRatio);
                        }

                        this.tempRect.top -= this.tempRect.width() - v0_1;
                        break;
                    }
                    case 3: {
                        this.tempRect.left += v8_2;
                        this.tempRect.bottom += v3;
                        if(this.lockAspectRatio <= 0f) {
                            goto label_251;
                        }

                        v0_1 = this.tempRect.width();
                        if(Math.abs(v8_2) > Math.abs(v3)) {
                            this.constrainRectByWidth(this.tempRect, this.lockAspectRatio);
                        }
                        else {
                            this.constrainRectByHeight(this.tempRect, this.lockAspectRatio);
                        }

                        this.tempRect.left -= this.tempRect.width() - v0_1;
                        break;
                    }
                    case 4: {
                        this.tempRect.right += v8_2;
                        this.tempRect.bottom += v3;
                        if(this.lockAspectRatio <= 0f) {
                            goto label_251;
                        }

                        if(Math.abs(v8_2) > Math.abs(v3)) {
                            goto label_125;
                        }

                    label_135:
                        this.constrainRectByHeight(this.tempRect, this.lockAspectRatio);
                        break;
                    }
                    case 5: {
                        this.tempRect.top += v3;
                        if(this.lockAspectRatio <= 0f) {
                            goto label_251;
                        }

                        goto label_135;
                    }
                    case 6: {
                        this.tempRect.left += v8_2;
                        if(this.lockAspectRatio <= 0f) {
                            goto label_251;
                        }

                        goto label_125;
                    }
                    case 7: {
                        this.tempRect.right += v8_2;
                        if(this.lockAspectRatio <= 0f) {
                            goto label_251;
                        }

                    label_125:
                        this.constrainRectByWidth(this.tempRect, this.lockAspectRatio);
                        break;
                    }
                    case 8: {
                        this.tempRect.bottom += v3;
                        if(this.lockAspectRatio <= 0f) {
                            goto label_251;
                        }

                        goto label_135;
                    }
                    default: {
                        break;
                    }
                }

            label_251:
                if(this.tempRect.left < this.sidePadding) {
                    if(this.lockAspectRatio > 0f) {
                        this.tempRect.bottom = this.tempRect.top + (this.tempRect.right - this.sidePadding) / this.lockAspectRatio;
                    }

                    this.tempRect.left = this.sidePadding;
                }
                else {
                    if(this.tempRect.right <= (((float)this.getWidth())) - this.sidePadding) {
                        goto label_296;
                    }

                    this.tempRect.right = (((float)this.getWidth())) - this.sidePadding;
                    if(this.lockAspectRatio <= 0f) {
                        goto label_296;
                    }

                    this.tempRect.bottom = this.tempRect.top + this.tempRect.width() / this.lockAspectRatio;
                }

            label_296:
                v2_1 += this.sidePadding;
                v8_2 = this.bottomPadding + this.sidePadding;
                if(this.tempRect.top < v2_1) {
                    if(this.lockAspectRatio > 0f) {
                        this.tempRect.right = this.tempRect.left + (this.tempRect.bottom - v2_1) * this.lockAspectRatio;
                    }

                    this.tempRect.top = v2_1;
                }
                else {
                    if(this.tempRect.bottom <= (((float)this.getHeight())) - v8_2) {
                        goto label_341;
                    }

                    this.tempRect.bottom = (((float)this.getHeight())) - v8_2;
                    if(this.lockAspectRatio <= 0f) {
                        goto label_341;
                    }

                    this.tempRect.right = this.tempRect.left + this.tempRect.height() * this.lockAspectRatio;
                }

            label_341:
                if(this.tempRect.width() < this.minWidth) {
                    this.tempRect.right = this.tempRect.left + this.minWidth;
                }

                if(this.tempRect.height() < this.minWidth) {
                    this.tempRect.bottom = this.tempRect.top + this.minWidth;
                }

                if(this.lockAspectRatio > 0f) {
                    if(this.lockAspectRatio < 1f) {
                        if(this.tempRect.width() <= this.minWidth) {
                            this.tempRect.right = this.tempRect.left + this.minWidth;
                            this.tempRect.bottom = this.tempRect.top + this.tempRect.width() / this.lockAspectRatio;
                        }
                    }
                    else if(this.tempRect.height() <= this.minWidth) {
                        this.tempRect.bottom = this.tempRect.top + this.minWidth;
                        this.tempRect.right = this.tempRect.left + this.tempRect.height() * this.lockAspectRatio;
                    }
                }

                this.setActualRect(this.tempRect);
                if(this.listener != null) {
                    this.listener.onAreaChange();
                }

                return 1;
            }
        }

        this.isDragging = false;
        if(this.activeControl == Control.NONE) {
            return 0;
        }

        this.activeControl = Control.NONE;
        if(this.listener != null) {
            this.listener.onAreaChangeEnded();
        }

        return 1;
    }

    public void resetAnimator() {
        if(this.animator != null) {
            this.animator.cancel();
            this.animator = null;
        }
    }

    public void setActualRect(RectF arg2) {
        this.actualRect.set(arg2);
        this.updateTouchAreas();
        this.invalidate();
    }

    public void setActualRect(float arg2) {
        this.calculateRect(this.actualRect, arg2);
        this.updateTouchAreas();
        this.invalidate();
    }

    public void setBitmap(Bitmap arg2, boolean arg3, boolean arg4) {
        int v2;
        float v3;
        if(arg2 != null) {
            if(arg2.isRecycled()) {
            }
            else {
                if(arg3) {
                    v3 = ((float)arg2.getHeight());
                    v2 = arg2.getWidth();
                }
                else {
                    v3 = ((float)arg2.getWidth());
                    v2 = arg2.getHeight();
                }

                v3 /= ((float)v2);
                float v2_1 = 1f;
                if(!arg4) {
                    this.lockAspectRatio = v2_1;
                }
                else {
                    v2_1 = v3;
                }

                this.setActualRect(v2_1);
            }
        }
    }

    public void setBottomPadding(float arg1) {
        this.bottomPadding = arg1;
    }

    private void setCropBottom(float arg2) {
        this.actualRect.bottom = arg2;
        this.invalidate();
    }

    private void setCropLeft(float arg2) {
        this.actualRect.left = arg2;
        this.invalidate();
    }

    private void setCropRight(float arg2) {
        this.actualRect.right = arg2;
        this.invalidate();
    }

    private void setCropTop(float arg2) {
        this.actualRect.top = arg2;
        this.invalidate();
    }

    public void setDimVisibility(boolean arg1) {
        this.dimVisibile = arg1;
    }

    public void setFrameVisibility(boolean arg1) {
        this.frameVisible = arg1;
    }

    private void setGridProgress(float arg1) {
        this.gridProgress = arg1;
        this.invalidate();
    }

    public void setGridType(GridType arg5, boolean arg6) {
        if(this.gridAnimator != null && (!arg6 || this.gridType != arg5)) {
            this.gridAnimator.cancel();
            this.gridAnimator = null;
        }

        if(this.gridType == arg5) {
            return;
        }

        this.previousGridType = this.gridType;
        this.gridType = arg5;
        float v0 = arg5 == GridType.NONE ? 0f : 1f;
        if(!arg6) {
            this.gridProgress = v0;
            this.invalidate();
        }
        else {
            this.gridAnimator = ObjectAnimator.ofFloat(this, "gridProgress", new float[]{this.gridProgress, v0});
            long v0_1 = 200;
            this.gridAnimator.setDuration(v0_1);
            this.gridAnimator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator arg2) {
                    CropAreaView.this.gridAnimator = null;
                }
            });
            if(arg5 == GridType.NONE) {
                this.gridAnimator.setStartDelay(v0_1);
            }

            this.gridAnimator.start();
        }
    }

    public void setListener(AreaViewListener arg1) {
        this.listener = arg1;
    }

    public void setLockedAspectRatio(float arg1) {
        this.lockAspectRatio = arg1;
    }

    private void updateTouchAreas() {
        float v0 = ((float)AndroidUtilities.dp(16f));
        this.topLeftCorner.set(this.actualRect.left - v0, this.actualRect.top - v0, this.actualRect.left + v0, this.actualRect.top + v0);
        this.topRightCorner.set(this.actualRect.right - v0, this.actualRect.top - v0, this.actualRect.right + v0, this.actualRect.top + v0);
        this.bottomLeftCorner.set(this.actualRect.left - v0, this.actualRect.bottom - v0, this.actualRect.left + v0, this.actualRect.bottom + v0);
        this.bottomRightCorner.set(this.actualRect.right - v0, this.actualRect.bottom - v0, this.actualRect.right + v0, this.actualRect.bottom + v0);
        this.topEdge.set(this.actualRect.left + v0, this.actualRect.top - v0, this.actualRect.right - v0, this.actualRect.top + v0);
        this.leftEdge.set(this.actualRect.left - v0, this.actualRect.top + v0, this.actualRect.left + v0, this.actualRect.bottom - v0);
        this.rightEdge.set(this.actualRect.right - v0, this.actualRect.top + v0, this.actualRect.right + v0, this.actualRect.bottom - v0);
        this.bottomEdge.set(this.actualRect.left + v0, this.actualRect.bottom - v0, this.actualRect.right - v0, this.actualRect.bottom + v0);
    }
}

