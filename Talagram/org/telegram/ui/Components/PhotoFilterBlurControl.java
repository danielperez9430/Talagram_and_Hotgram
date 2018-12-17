package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build$VERSION;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;

public class PhotoFilterBlurControl extends FrameLayout {
    class org.telegram.ui.Components.PhotoFilterBlurControl$1 {
        static {
            org.telegram.ui.Components.PhotoFilterBlurControl$1.$SwitchMap$org$telegram$ui$Components$PhotoFilterBlurControl$BlurViewActiveControl = new int[BlurViewActiveControl.values().length];
            try {
                org.telegram.ui.Components.PhotoFilterBlurControl$1.$SwitchMap$org$telegram$ui$Components$PhotoFilterBlurControl$BlurViewActiveControl[BlurViewActiveControl.BlurViewActiveControlCenter.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    org.telegram.ui.Components.PhotoFilterBlurControl$1.$SwitchMap$org$telegram$ui$Components$PhotoFilterBlurControl$BlurViewActiveControl[BlurViewActiveControl.BlurViewActiveControlInnerRadius.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        org.telegram.ui.Components.PhotoFilterBlurControl$1.$SwitchMap$org$telegram$ui$Components$PhotoFilterBlurControl$BlurViewActiveControl[BlurViewActiveControl.BlurViewActiveControlOuterRadius.ordinal()] = 3;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                        try {
                        label_19:
                            org.telegram.ui.Components.PhotoFilterBlurControl$1.$SwitchMap$org$telegram$ui$Components$PhotoFilterBlurControl$BlurViewActiveControl[BlurViewActiveControl.BlurViewActiveControlRotation.ordinal()] = 4;
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

    enum BlurViewActiveControl {
        public static final enum BlurViewActiveControl BlurViewActiveControlCenter;
        public static final enum BlurViewActiveControl BlurViewActiveControlInnerRadius;
        public static final enum BlurViewActiveControl BlurViewActiveControlNone;
        public static final enum BlurViewActiveControl BlurViewActiveControlOuterRadius;
        public static final enum BlurViewActiveControl BlurViewActiveControlRotation;
        public static final enum BlurViewActiveControl BlurViewActiveControlWholeArea;

        static {
            BlurViewActiveControl.BlurViewActiveControlNone = new BlurViewActiveControl("BlurViewActiveControlNone", 0);
            BlurViewActiveControl.BlurViewActiveControlCenter = new BlurViewActiveControl("BlurViewActiveControlCenter", 1);
            BlurViewActiveControl.BlurViewActiveControlInnerRadius = new BlurViewActiveControl("BlurViewActiveControlInnerRadius", 2);
            BlurViewActiveControl.BlurViewActiveControlOuterRadius = new BlurViewActiveControl("BlurViewActiveControlOuterRadius", 3);
            BlurViewActiveControl.BlurViewActiveControlWholeArea = new BlurViewActiveControl("BlurViewActiveControlWholeArea", 4);
            BlurViewActiveControl.BlurViewActiveControlRotation = new BlurViewActiveControl("BlurViewActiveControlRotation", 5);
            BlurViewActiveControl.$VALUES = new BlurViewActiveControl[]{BlurViewActiveControl.BlurViewActiveControlNone, BlurViewActiveControl.BlurViewActiveControlCenter, BlurViewActiveControl.BlurViewActiveControlInnerRadius, BlurViewActiveControl.BlurViewActiveControlOuterRadius, BlurViewActiveControl.BlurViewActiveControlWholeArea, BlurViewActiveControl.BlurViewActiveControlRotation};
        }

        private BlurViewActiveControl(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static BlurViewActiveControl valueOf(String arg1) {
            return Enum.valueOf(BlurViewActiveControl.class, arg1);
        }

        public static BlurViewActiveControl[] values() {
            // Method was not decompiled
        }
    }

    public interface PhotoFilterLinearBlurControlDelegate {
        void valueChanged(Point arg1, float arg2, float arg3, float arg4);
    }

    private static final float BlurInsetProximity = 0f;
    private static final float BlurMinimumDifference = 0.02f;
    private static final float BlurMinimumFalloff = 0.1f;
    private static final float BlurViewCenterInset;
    private static final float BlurViewRadiusInset;
    private final int GestureStateBegan;
    private final int GestureStateCancelled;
    private final int GestureStateChanged;
    private final int GestureStateEnded;
    private final int GestureStateFailed;
    private BlurViewActiveControl activeControl;
    private Size actualAreaSize;
    private float angle;
    private Paint arcPaint;
    private RectF arcRect;
    private Point centerPoint;
    private boolean checkForMoving;
    private boolean checkForZooming;
    private PhotoFilterLinearBlurControlDelegate delegate;
    private float falloff;
    private boolean isMoving;
    private boolean isZooming;
    private Paint paint;
    private float pointerScale;
    private float pointerStartX;
    private float pointerStartY;
    private float size;
    private Point startCenterPoint;
    private float startDistance;
    private float startPointerDistance;
    private float startRadius;
    private int type;

    static {
        PhotoFilterBlurControl.BlurInsetProximity = ((float)AndroidUtilities.dp(20f));
        PhotoFilterBlurControl.BlurViewCenterInset = ((float)AndroidUtilities.dp(30f));
        PhotoFilterBlurControl.BlurViewRadiusInset = ((float)AndroidUtilities.dp(30f));
    }

    public PhotoFilterBlurControl(Context arg3) {
        super(arg3);
        this.GestureStateBegan = 1;
        this.GestureStateChanged = 2;
        this.GestureStateEnded = 3;
        this.GestureStateCancelled = 4;
        this.GestureStateFailed = 5;
        this.startCenterPoint = new Point();
        this.actualAreaSize = new Size();
        this.centerPoint = new Point(0.5f, 0.5f);
        this.falloff = 0.15f;
        this.size = 0.35f;
        this.arcRect = new RectF();
        this.pointerScale = 1f;
        this.checkForMoving = true;
        this.paint = new Paint(1);
        this.arcPaint = new Paint(1);
        this.setWillNotDraw(false);
        this.paint.setColor(-1);
        this.arcPaint.setColor(-1);
        this.arcPaint.setStrokeWidth(((float)AndroidUtilities.dp(2f)));
        this.arcPaint.setStyle(Paint$Style.STROKE);
    }

    private float degreesToRadians(float arg2) {
        return arg2 * 3.141593f / 180f;
    }

    private Point getActualCenterPoint() {
        float v2 = 2f;
        float v1 = ((((float)this.getWidth())) - this.actualAreaSize.width) / v2 + this.centerPoint.x * this.actualAreaSize.width;
        int v3 = Build$VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0;
        return new Point(v1, (((float)v3)) + ((((float)this.getHeight())) - this.actualAreaSize.height) / v2 - (this.actualAreaSize.width - this.actualAreaSize.height) / v2 + this.centerPoint.y * this.actualAreaSize.width);
    }

    private float getActualInnerRadius() {
        float v0 = this.actualAreaSize.width > this.actualAreaSize.height ? this.actualAreaSize.height : this.actualAreaSize.width;
        return v0 * this.falloff;
    }

    private float getActualOuterRadius() {
        float v0 = this.actualAreaSize.width > this.actualAreaSize.height ? this.actualAreaSize.height : this.actualAreaSize.width;
        return v0 * this.size;
    }

    private float getDistance(MotionEvent arg5) {
        if(arg5.getPointerCount() != 2) {
            return 0;
        }

        float v1 = arg5.getX(0);
        float v0 = arg5.getY(0);
        v1 -= arg5.getX(1);
        v0 -= arg5.getY(1);
        return ((float)Math.sqrt(((double)(v1 * v1 + v0 * v0))));
    }

    private void handlePan(int arg18, MotionEvent arg19) {
        Point v1_1;
        Rect v3_1;
        PhotoFilterBlurControl v0 = this;
        float v1 = arg19.getX();
        float v2 = arg19.getY();
        Point v3 = this.getActualCenterPoint();
        Point v4 = new Point(v1 - v3.x, v2 - v3.y);
        float v5 = ((float)Math.sqrt(((double)(v4.x * v4.x + v4.y * v4.y))));
        float v6 = v0.actualAreaSize.width > v0.actualAreaSize.height ? v0.actualAreaSize.height : v0.actualAreaSize.width;
        float v7 = v0.falloff * v6;
        float v8 = v0.size * v6;
        double v9 = ((double)v4.x);
        double v11 = ((double)v0.degreesToRadians(v0.angle));
        Double.isNaN(v11);
        v11 = Math.cos(v11 + 1.570796);
        Double.isNaN(v9);
        v9 *= v11;
        v11 = ((double)v4.y);
        float v15 = v7;
        float v16 = v8;
        double v7_1 = ((double)v0.degreesToRadians(v0.angle));
        Double.isNaN(v7_1);
        v7_1 = Math.sin(v7_1 + 1.570796);
        Double.isNaN(v11);
        float v4_1 = ((float)Math.abs(v9 + v11 * v7_1));
        int v7_2 = 0;
        v8 = 0f;
        switch(arg18) {
            case 1: {
                goto label_332;
            }
            case 2: {
                float v11_1 = 0.1f;
                float v12 = 0.02f;
                float v13 = 2f;
                if(v0.type == 0) {
                    switch(org.telegram.ui.Components.PhotoFilterBlurControl$1.$SwitchMap$org$telegram$ui$Components$PhotoFilterBlurControl$BlurViewActiveControl[v0.activeControl.ordinal()]) {
                        case 1: {
                            v1 -= v0.pointerStartX;
                            v2 -= v0.pointerStartY;
                            v3_1 = new Rect(((((float)this.getWidth())) - v0.actualAreaSize.width) / v13, ((((float)this.getHeight())) - v0.actualAreaSize.height) / v13, v0.actualAreaSize.width, v0.actualAreaSize.height);
                            v4 = new Point(Math.max(v3_1.x, Math.min(v3_1.x + v3_1.width, v0.startCenterPoint.x + v1)), Math.max(v3_1.y, Math.min(v3_1.y + v3_1.height, v0.startCenterPoint.y + v2)));
                            v1_1 = new Point((v4.x - v3_1.x) / v0.actualAreaSize.width, (v4.y - v3_1.y + (v0.actualAreaSize.width - v0.actualAreaSize.height) / v13) / v0.actualAreaSize.width);
                            goto label_233;
                        }
                        case 2: {
                            v1 = v0.startRadius + (v4_1 - v0.startDistance);
                            goto label_163;
                        }
                        case 3: {
                            v4_1 -= v0.startDistance;
                            v1 = v0.falloff + v12;
                            v2 = v0.startRadius + v4_1;
                            goto label_155;
                        }
                        case 4: {
                            v4_1 = v1 - v0.pointerStartX;
                            v5 = v2 - v0.pointerStartY;
                            int v6_1 = v1 > v3.x ? 1 : 0;
                            int v3_2 = v2 > v3.y ? 1 : 0;
                            if(v6_1 != 0 || v3_2 != 0) {
                                if(v6_1 != 0 && v3_2 == 0) {
                                    if(Math.abs(v5) > Math.abs(v4_1)) {
                                        if(v5 > 0f) {
                                            goto label_100;
                                        }
                                        else {
                                            goto label_129;
                                        }
                                    }
                                    else if(v4_1 > 0f) {
                                        goto label_100;
                                    }
                                    else {
                                        goto label_129;
                                    }
                                }

                                if(v6_1 != 0 && v3_2 != 0) {
                                    if(Math.abs(v5) > Math.abs(v4_1)) {
                                        if(v5 > 0f) {
                                            goto label_100;
                                        }
                                        else {
                                            goto label_129;
                                        }
                                    }
                                    else if(v4_1 < 0f) {
                                        goto label_100;
                                    }
                                    else {
                                        goto label_129;
                                    }
                                }

                                if(Math.abs(v5) > Math.abs(v4_1)) {
                                    if(v5 >= 0f) {
                                        goto label_129;
                                    }

                                    goto label_100;
                                }

                                if(v4_1 >= 0f) {
                                    goto label_129;
                                }

                            label_100:
                                v7_2 = 1;
                            }
                            else if(Math.abs(v5) > Math.abs(v4_1)) {
                                if(v5 >= 0f) {
                                    goto label_129;
                                }

                                goto label_100;
                            }
                            else if(v4_1 > 0f) {
                                goto label_100;
                            }

                        label_129:
                            v0.angle += (((float)Math.sqrt(((double)(v4_1 * v4_1 + v5 * v5))))) * (((float)(v7_2 * 2 - 1))) / 3.141593f / 1.15f;
                            v0.pointerStartX = v1;
                            v0.pointerStartY = v2;
                            goto label_319;
                        label_235:
                            if(v0.type != 1) {
                                goto label_319;
                            }

                            switch(org.telegram.ui.Components.PhotoFilterBlurControl$1.$SwitchMap$org$telegram$ui$Components$PhotoFilterBlurControl$BlurViewActiveControl[v0.activeControl.ordinal()]) {
                                case 1: {
                                    v1 -= v0.pointerStartX;
                                    v2 -= v0.pointerStartY;
                                    v3_1 = new Rect(((((float)this.getWidth())) - v0.actualAreaSize.width) / v13, ((((float)this.getHeight())) - v0.actualAreaSize.height) / v13, v0.actualAreaSize.width, v0.actualAreaSize.height);
                                    v4 = new Point(Math.max(v3_1.x, Math.min(v3_1.x + v3_1.width, v0.startCenterPoint.x + v1)), Math.max(v3_1.y, Math.min(v3_1.y + v3_1.height, v0.startCenterPoint.y + v2)));
                                    v1_1 = new Point((v4.x - v3_1.x) / v0.actualAreaSize.width, (v4.y - v3_1.y + (v0.actualAreaSize.width - v0.actualAreaSize.height) / v13) / v0.actualAreaSize.width);
                                label_233:
                                    v0.centerPoint = v1_1;
                                label_319:
                                    this.invalidate();
                                    if(v0.delegate == null) {
                                        return;
                                    }

                                    v0.delegate.valueChanged(v0.centerPoint, v0.falloff, v0.size, v0.degreesToRadians(v0.angle) + 1.570796f);
                                    return;
                                label_332:
                                    v0.pointerStartX = arg19.getX();
                                    v0.pointerStartY = arg19.getY();
                                    if(Math.abs(v16 - v15) < PhotoFilterBlurControl.BlurInsetProximity) {
                                        v7_2 = 1;
                                    }

                                    v1 = v7_2 != 0 ? 0f : PhotoFilterBlurControl.BlurViewRadiusInset;
                                    if(v7_2 != 0) {
                                    }
                                    else {
                                        v8 = PhotoFilterBlurControl.BlurViewRadiusInset;
                                    }

                                    if(v0.type != 0) {
                                        v6 = v15;
                                        v2 = v16;
                                        if(v0.type != 1) {
                                            goto label_414;
                                        }

                                        if(v5 < PhotoFilterBlurControl.BlurViewCenterInset) {
                                        label_352:
                                            v0.activeControl = BlurViewActiveControl.BlurViewActiveControlCenter;
                                            v0.startCenterPoint = v3;
                                            goto label_414;
                                        }

                                        if(v5 > v6 - PhotoFilterBlurControl.BlurViewRadiusInset && v5 < v6 + v1) {
                                            v0.activeControl = BlurViewActiveControl.BlurViewActiveControlInnerRadius;
                                            v0.startDistance = v5;
                                        label_365:
                                            v0.startRadius = v6;
                                            goto label_414;
                                        }

                                        if(v5 <= v2 - v8) {
                                            goto label_414;
                                        }

                                        if(v5 >= v2 + PhotoFilterBlurControl.BlurViewRadiusInset) {
                                            goto label_414;
                                        }

                                        v0.activeControl = BlurViewActiveControl.BlurViewActiveControlOuterRadius;
                                        v0.startDistance = v5;
                                    label_377:
                                        v0.startRadius = v2;
                                    }
                                    else if(v5 >= PhotoFilterBlurControl.BlurViewCenterInset) {
                                        if(v4_1 > v15 - PhotoFilterBlurControl.BlurViewRadiusInset && v4_1 < v15 + v1) {
                                            v0.activeControl = BlurViewActiveControl.BlurViewActiveControlInnerRadius;
                                            v0.startDistance = v4_1;
                                            v6 = v15;
                                            goto label_365;
                                        }

                                        v6 = v15;
                                        if(v4_1 > v16 - v8 && v4_1 < v16 + PhotoFilterBlurControl.BlurViewRadiusInset) {
                                            v0.activeControl = BlurViewActiveControl.BlurViewActiveControlOuterRadius;
                                            v0.startDistance = v4_1;
                                            v2 = v16;
                                            goto label_377;
                                        }

                                        v2 = v16;
                                        if(v4_1 > v6 - PhotoFilterBlurControl.BlurViewRadiusInset && v4_1 < v2 + PhotoFilterBlurControl.BlurViewRadiusInset) {
                                            goto label_414;
                                        }

                                        v0.activeControl = BlurViewActiveControl.BlurViewActiveControlRotation;
                                    }
                                    else {
                                        goto label_352;
                                    }

                                label_414:
                                    v0.setSelected(true, true);
                                    return;
                                }
                                case 2: {
                                    v1 = v0.startRadius + (v5 - v0.startDistance);
                                label_163:
                                    v0.falloff = Math.min(Math.max(v11_1, v1 / v6), v0.size - v12);
                                    goto label_319;
                                }
                                case 3: {
                                    v5 -= v0.startDistance;
                                    v1 = v0.falloff + v12;
                                    v2 = v0.startRadius + v5;
                                label_155:
                                    v0.size = Math.max(v1, v2 / v6);
                                    goto label_319;
                                }
                                default: {
                                    goto label_319;
                                }
                            }
                        }
                        default: {
                            goto label_319;
                        }
                    }
                }
                else {
                    goto label_235;
                }

                goto label_319;
            }
            case 3: 
            case 4: 
            case 5: {
                v0.activeControl = BlurViewActiveControl.BlurViewActiveControlNone;
                v0.setSelected(false, true);
                break;
            }
            default: {
                break;
            }
        }
    }

    private void handlePinch(int arg5, MotionEvent arg6) {
        float v0 = 1f;
        switch(arg5) {
            case 1: {
                this.startPointerDistance = this.getDistance(arg6);
                this.pointerScale = v0;
                this.activeControl = BlurViewActiveControl.BlurViewActiveControlWholeArea;
                this.setSelected(true, true);
                goto label_15;
            }
            case 2: {
            label_15:
                float v5 = this.getDistance(arg6);
                this.pointerScale += (v5 - this.startPointerDistance) / AndroidUtilities.density * 0.01f;
                this.falloff = Math.max(0.1f, this.falloff * this.pointerScale);
                this.size = Math.max(this.falloff + 0.02f, this.size * this.pointerScale);
                this.pointerScale = v0;
                this.startPointerDistance = v5;
                this.invalidate();
                if(this.delegate == null) {
                    return;
                }

                this.delegate.valueChanged(this.centerPoint, this.falloff, this.size, this.degreesToRadians(this.angle) + 1.570796f);
                break;
            }
            case 3: 
            case 4: 
            case 5: {
                this.activeControl = BlurViewActiveControl.BlurViewActiveControlNone;
                this.setSelected(false, true);
                break;
            }
            default: {
                break;
            }
        }
    }

    protected void onDraw(Canvas arg23) {
        float v18;
        float v5;
        float v16;
        PhotoFilterBlurControl v0 = this;
        Canvas v7 = arg23;
        super.onDraw(arg23);
        Point v1 = this.getActualCenterPoint();
        float v8 = this.getActualInnerRadius();
        float v9 = this.getActualOuterRadius();
        v7.translate(v1.x, v1.y);
        int v10 = 64;
        if(v0.type == 0) {
            v7.rotate(v0.angle);
            float v12 = 6f;
            float v13 = ((float)AndroidUtilities.dp(v12));
            float v14 = ((float)AndroidUtilities.dp(12f));
            float v15 = ((float)AndroidUtilities.dp(1.5f));
            int v6;
            for(v6 = 0; v6 < 30; ++v6) {
                v16 = v14 + v13;
                float v17 = (((float)v6)) * v16;
                v5 = -v8;
                v18 = v17 + v14;
                float v3 = v5;
                float v21 = v5;
                v5 = v15 - v8;
                arg23.drawRect(v17, v3, v18, v5, v0.paint);
                v16 = (((float)(-v6))) * v16 - v13;
                float v20 = v16 - v14;
                arg23.drawRect(v20, v21, v16, v5, v0.paint);
                v5 = v15 + v8;
                arg23.drawRect(v17, v8, v18, v5, v0.paint);
                arg23.drawRect(v20, v8, v16, v5, v0.paint);
            }

            v8 = ((float)AndroidUtilities.dp(v12));
            int v11;
            for(v11 = 0; v11 < v10; ++v11) {
                v12 = v8 + v13;
                v14 = (((float)v11)) * v12;
                float v6_1 = -v9;
                v16 = v8 + v14;
                v5 = v15 - v9;
                arg23.drawRect(v14, v6_1, v16, v5, v0.paint);
                v12 = (((float)(-v11))) * v12 - v13;
                v18 = v12 - v8;
                arg23.drawRect(v18, v6_1, v12, v5, v0.paint);
                v5 = v15 + v9;
                arg23.drawRect(v14, v9, v16, v5, v0.paint);
                arg23.drawRect(v18, v9, v12, v5, v0.paint);
            }
        }
        else if(v0.type == 1) {
            float v2 = -v8;
            v0.arcRect.set(v2, v2, v8, v8);
            int v8_1;
            for(v8_1 = 0; v8_1 < 22; ++v8_1) {
                arg23.drawArc(v0.arcRect, 16.35f * (((float)v8_1)), 10.2f, false, v0.arcPaint);
            }

            v2 = -v9;
            v0.arcRect.set(v2, v2, v9, v9);
            for(v8_1 = 0; v8_1 < v10; ++v8_1) {
                arg23.drawArc(v0.arcRect, 5.62f * (((float)v8_1)), 3.6f, false, v0.arcPaint);
            }
        }

        v7.drawCircle(0f, 0f, ((float)AndroidUtilities.dp(8f)), v0.paint);
    }

    public boolean onTouchEvent(MotionEvent arg18) {
        PhotoFilterBlurControl v0 = this;
        MotionEvent v1 = arg18;
        int v3 = 2;
        int v4 = 3;
        switch(arg18.getActionMasked()) {
            case 2: {
                if(v0.isMoving) {
                    v0.handlePan(v3, v1);
                    return 1;
                }

                if(!v0.isZooming) {
                    return 1;
                }

                v0.handlePinch(v3, v1);
                break;
            }
            case 0: 
            case 5: {
                if(arg18.getPointerCount() == 1) {
                    if(!v0.checkForMoving) {
                        return 1;
                    }

                    if(v0.isMoving) {
                        return 1;
                    }

                    float v2 = arg18.getX();
                    float v3_1 = arg18.getY();
                    Point v4_1 = this.getActualCenterPoint();
                    Point v7 = new Point(v2 - v4_1.x, v3_1 - v4_1.y);
                    v2 = ((float)Math.sqrt(((double)(v7.x * v7.x + v7.y * v7.y))));
                    v3_1 = this.getActualInnerRadius();
                    float v4_2 = this.getActualOuterRadius();
                    int v8 = Math.abs(v4_2 - v3_1) < PhotoFilterBlurControl.BlurInsetProximity ? 1 : 0;
                    float v9 = 0f;
                    float v10 = v8 != 0 ? 0f : PhotoFilterBlurControl.BlurViewRadiusInset;
                    if(v8 != 0) {
                    }
                    else {
                        v9 = PhotoFilterBlurControl.BlurViewRadiusInset;
                    }

                    if(v0.type == 0) {
                        double v11 = ((double)v7.x);
                        double v13 = ((double)v0.degreesToRadians(v0.angle));
                        Double.isNaN(v13);
                        v13 = Math.cos(v13 + 1.570796);
                        Double.isNaN(v11);
                        v11 *= v13;
                        double v7_1 = ((double)v7.y);
                        v13 = ((double)v0.degreesToRadians(v0.angle));
                        Double.isNaN(v13);
                        v13 = Math.sin(v13 + 1.570796);
                        Double.isNaN(v7_1);
                        float v7_2 = ((float)Math.abs(v11 + v7_1 * v13));
                        if(v2 < PhotoFilterBlurControl.BlurViewCenterInset) {
                        }
                        else {
                            if(v7_2 > v3_1 - PhotoFilterBlurControl.BlurViewRadiusInset && v7_2 < v10 + v3_1) {
                                goto label_123;
                            }

                            if(v7_2 > v4_2 - v9 && v7_2 < PhotoFilterBlurControl.BlurViewRadiusInset + v4_2) {
                                goto label_123;
                            }

                            if(v7_2 <= v3_1 - PhotoFilterBlurControl.BlurViewRadiusInset) {
                                goto label_123;
                            }

                            if(v7_2 < v4_2 + PhotoFilterBlurControl.BlurViewRadiusInset) {
                                goto label_137;
                            }
                        }

                        goto label_123;
                    }
                    else {
                        if(v0.type != 1) {
                            goto label_137;
                        }

                        if(v2 >= PhotoFilterBlurControl.BlurViewCenterInset && (v2 <= v3_1 - PhotoFilterBlurControl.BlurViewRadiusInset || v2 >= v3_1 + v10)) {
                            if(v2 <= v4_2 - v9) {
                                goto label_137;
                            }

                            if(v2 >= v4_2 + PhotoFilterBlurControl.BlurViewRadiusInset) {
                                goto label_137;
                            }
                        }

                    label_123:
                        v0.isMoving = true;
                    }

                label_137:
                    v0.checkForMoving = false;
                    if(!v0.isMoving) {
                        return 1;
                    }

                    v0.handlePan(1, v1);
                    return 1;
                }

                if(v0.isMoving) {
                    v0.handlePan(v4, v1);
                    v0.checkForMoving = true;
                    v0.isMoving = false;
                }

                if(arg18.getPointerCount() == v3) {
                    if(!v0.checkForZooming) {
                        return 1;
                    }

                    if(v0.isZooming) {
                        return 1;
                    }

                    v0.handlePinch(1, v1);
                    v0.isZooming = true;
                    return 1;
                }

                v0.handlePinch(v4, v1);
                v0.checkForZooming = true;
                v0.isZooming = false;
                break;
            }
            case 1: 
            case 3: 
            case 6: {
                if(v0.isMoving) {
                    v0.handlePan(v4, v1);
                    v0.isMoving = false;
                }
                else if(v0.isZooming) {
                    v0.handlePinch(v4, v1);
                    v0.isZooming = false;
                }

                v0.checkForMoving = true;
                v0.checkForZooming = true;
                break;
            }
            default: {
                break;
            }
        }

        return 1;
    }

    public void setActualAreaSize(float arg2, float arg3) {
        this.actualAreaSize.width = arg2;
        this.actualAreaSize.height = arg3;
    }

    public void setDelegate(PhotoFilterLinearBlurControlDelegate arg1) {
        this.delegate = arg1;
    }

    private void setSelected(boolean arg1, boolean arg2) {
    }

    public void setType(int arg1) {
        this.type = arg1;
        this.invalidate();
    }
}

