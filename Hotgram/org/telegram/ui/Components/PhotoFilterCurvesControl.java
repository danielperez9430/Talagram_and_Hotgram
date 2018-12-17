package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;
import java.util.Locale;
import org.telegram.messenger.AndroidUtilities;

public class PhotoFilterCurvesControl extends View {
    public interface PhotoFilterCurvesControlDelegate {
        void valueChanged();
    }

    private static final int CurvesSegmentBlacks = 1;
    private static final int CurvesSegmentHighlights = 4;
    private static final int CurvesSegmentMidtones = 3;
    private static final int CurvesSegmentNone = 0;
    private static final int CurvesSegmentShadows = 2;
    private static final int CurvesSegmentWhites = 5;
    private static final int GestureStateBegan = 1;
    private static final int GestureStateCancelled = 4;
    private static final int GestureStateChanged = 2;
    private static final int GestureStateEnded = 3;
    private static final int GestureStateFailed = 5;
    private int activeSegment;
    private Rect actualArea;
    private boolean checkForMoving;
    private CurvesToolValue curveValue;
    private PhotoFilterCurvesControlDelegate delegate;
    private boolean isMoving;
    private float lastX;
    private float lastY;
    private Paint paint;
    private Paint paintCurve;
    private Paint paintDash;
    private Path path;
    private TextPaint textPaint;

    public PhotoFilterCurvesControl(Context arg3, CurvesToolValue arg4) {
        super(arg3);
        this.activeSegment = 0;
        this.checkForMoving = true;
        this.actualArea = new Rect();
        this.paint = new Paint(1);
        this.paintDash = new Paint(1);
        this.paintCurve = new Paint(1);
        this.textPaint = new TextPaint(1);
        this.path = new Path();
        this.setWillNotDraw(false);
        this.curveValue = arg4;
        this.paint.setColor(-1711276033);
        this.paint.setStrokeWidth(((float)AndroidUtilities.dp(1f)));
        this.paint.setStyle(Paint$Style.STROKE);
        this.paintDash.setColor(-1711276033);
        this.paintDash.setStrokeWidth(((float)AndroidUtilities.dp(2f)));
        this.paintDash.setStyle(Paint$Style.STROKE);
        this.paintCurve.setColor(-1);
        this.paintCurve.setStrokeWidth(((float)AndroidUtilities.dp(2f)));
        this.paintCurve.setStyle(Paint$Style.STROKE);
        this.textPaint.setColor(-4210753);
        this.textPaint.setTextSize(((float)AndroidUtilities.dp(13f)));
    }

    private void handlePan(int arg6, MotionEvent arg7) {
        float v0 = arg7.getX();
        float v7 = arg7.getY();
        switch(arg6) {
            case 1: {
                this.selectSegmentWithPoint(v0);
                break;
            }
            case 2: {
                float v6 = Math.min(2f, (this.lastY - v7) / 8f);
                CurvesValue v1 = null;
                switch(this.curveValue.activeType) {
                    case 0: {
                        v1 = this.curveValue.luminanceCurve;
                        break;
                    }
                    case 1: {
                        v1 = this.curveValue.redCurve;
                        break;
                    }
                    case 2: {
                        v1 = this.curveValue.greenCurve;
                        break;
                    }
                    case 3: {
                        v1 = this.curveValue.blueCurve;
                        break;
                    }
                    default: {
                        break;
                    }
                }

                float v3 = 100f;
                switch(this.activeSegment) {
                    case 1: {
                        v1.blacksLevel = Math.max(0f, Math.min(v3, v1.blacksLevel + v6));
                        break;
                    }
                    case 2: {
                        v1.shadowsLevel = Math.max(0f, Math.min(v3, v1.shadowsLevel + v6));
                        break;
                    }
                    case 3: {
                        v1.midtonesLevel = Math.max(0f, Math.min(v3, v1.midtonesLevel + v6));
                        break;
                    }
                    case 4: {
                        v1.highlightsLevel = Math.max(0f, Math.min(v3, v1.highlightsLevel + v6));
                        break;
                    }
                    case 5: {
                        v1.whitesLevel = Math.max(0f, Math.min(v3, v1.whitesLevel + v6));
                        break;
                    }
                    default: {
                        break;
                    }
                }

                this.invalidate();
                if(this.delegate != null) {
                    this.delegate.valueChanged();
                }

                this.lastX = v0;
                this.lastY = v7;
                break;
            }
            case 3: 
            case 4: 
            case 5: {
                this.unselectSegments();
                break;
            }
            default: {
                break;
            }
        }
    }

    @SuppressLint(value={"DrawAllocation"}) protected void onDraw(Canvas arg12) {
        int v6_1;
        float v4;
        float v0 = this.actualArea.width / 5f;
        int v1 = 0;
        int v2;
        for(v2 = 0; v2 < 4; ++v2) {
            v4 = (((float)v2)) * v0;
            arg12.drawLine(this.actualArea.x + v0 + v4, this.actualArea.y, this.actualArea.x + v0 + v4, this.actualArea.y + this.actualArea.height, this.paint);
        }

        arg12.drawLine(this.actualArea.x, this.actualArea.y + this.actualArea.height, this.actualArea.x + this.actualArea.width, this.actualArea.y, this.paintDash);
        CurvesValue v2_1 = null;
        switch(this.curveValue.activeType) {
            case 0: {
                this.paintCurve.setColor(-1);
                v2_1 = this.curveValue.luminanceCurve;
                break;
            }
            case 1: {
                this.paintCurve.setColor(-1229492);
                v2_1 = this.curveValue.redCurve;
                break;
            }
            case 2: {
                this.paintCurve.setColor(-15667555);
                v2_1 = this.curveValue.greenCurve;
                break;
            }
            case 3: {
                this.paintCurve.setColor(-13404165);
                v2_1 = this.curveValue.blueCurve;
                break;
            }
            default: {
                break;
            }
        }

        int v3;
        for(v3 = 0; v3 < 5; ++v3) {
            v4 = 100f;
            switch(v3) {
                case 0: {
                    goto label_115;
                }
                case 1: {
                    goto label_107;
                }
                case 2: {
                    goto label_99;
                }
                case 3: {
                    goto label_91;
                }
                case 4: {
                    goto label_83;
                }
            }

            String v4_1 = "";
            goto label_123;
        label_115:
            Locale v6 = Locale.US;
            String v7 = "%.2f";
            Object[] v5 = new Object[]{Float.valueOf(v2_1.blacksLevel / v4)};
            goto label_122;
        label_99:
            v6 = Locale.US;
            v7 = "%.2f";
            v5 = new Object[]{Float.valueOf(v2_1.midtonesLevel / v4)};
            goto label_122;
        label_83:
            v6 = Locale.US;
            v7 = "%.2f";
            v5 = new Object[]{Float.valueOf(v2_1.whitesLevel / v4)};
            goto label_122;
        label_107:
            v6 = Locale.US;
            v7 = "%.2f";
            v5 = new Object[]{Float.valueOf(v2_1.shadowsLevel / v4)};
            goto label_122;
        label_91:
            v6 = Locale.US;
            v7 = "%.2f";
            v5 = new Object[]{Float.valueOf(v2_1.highlightsLevel / v4)};
        label_122:
            v4_1 = String.format(v6, v7, v5);
        label_123:
            arg12.drawText(v4_1, this.actualArea.x + (v0 - this.textPaint.measureText(v4_1)) / 2f + (((float)v3)) * v0, this.actualArea.y + this.actualArea.height - (((float)AndroidUtilities.dp(4f))), this.textPaint);
        }

        float[] v0_1 = v2_1.interpolateCurve();
        this.invalidate();
        this.path.reset();
        while(v1 < v0_1.length / 2) {
            float v2_2 = 1f;
            if(v1 == 0) {
                v6_1 = v1 * 2;
                this.path.moveTo(this.actualArea.x + v0_1[v6_1] * this.actualArea.width, this.actualArea.y + (v2_2 - v0_1[v6_1 + 1]) * this.actualArea.height);
            }
            else {
                v6_1 = v1 * 2;
                this.path.lineTo(this.actualArea.x + v0_1[v6_1] * this.actualArea.width, this.actualArea.y + (v2_2 - v0_1[v6_1 + 1]) * this.actualArea.height);
            }

            ++v1;
        }

        arg12.drawPath(this.path, this.paintCurve);
    }

    public boolean onTouchEvent(MotionEvent arg7) {
        int v1 = 3;
        switch(arg7.getActionMasked()) {
            case 2: {
                if(!this.isMoving) {
                    return 1;
                }

                this.handlePan(2, arg7);
                break;
            }
            case 0: 
            case 5: {
                if(arg7.getPointerCount() == 1) {
                    if(!this.checkForMoving) {
                        return 1;
                    }

                    if(this.isMoving) {
                        return 1;
                    }

                    float v0 = arg7.getX();
                    float v1_1 = arg7.getY();
                    this.lastX = v0;
                    this.lastY = v1_1;
                    if(v0 >= this.actualArea.x && v0 <= this.actualArea.x + this.actualArea.width && v1_1 >= this.actualArea.y && v1_1 <= this.actualArea.y + this.actualArea.height) {
                        this.isMoving = true;
                    }

                    this.checkForMoving = false;
                    if(!this.isMoving) {
                        return 1;
                    }

                    this.handlePan(1, arg7);
                    return 1;
                }

                if(!this.isMoving) {
                    return 1;
                }

                this.handlePan(v1, arg7);
                this.checkForMoving = true;
                this.isMoving = false;
                break;
            }
            case 1: 
            case 3: 
            case 6: {
                if(this.isMoving) {
                    this.handlePan(v1, arg7);
                    this.isMoving = false;
                }

                this.checkForMoving = true;
                break;
            }
            default: {
                break;
            }
        }

        return 1;
    }

    private void selectSegmentWithPoint(float arg3) {
        if(this.activeSegment != 0) {
            return;
        }

        this.activeSegment = ((int)Math.floor(((double)((arg3 - this.actualArea.x) / (this.actualArea.width / 5f) + 1f))));
    }

    public void setActualArea(float arg2, float arg3, float arg4, float arg5) {
        this.actualArea.x = arg2;
        this.actualArea.y = arg3;
        this.actualArea.width = arg4;
        this.actualArea.height = arg5;
    }

    public void setDelegate(PhotoFilterCurvesControlDelegate arg1) {
        this.delegate = arg1;
    }

    private void unselectSegments() {
        if(this.activeSegment == 0) {
            return;
        }

        this.activeSegment = 0;
    }
}

