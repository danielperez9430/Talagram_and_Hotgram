package org.telegram.ui.Components.Paint;

import android.graphics.Bitmap;
import android.graphics.PointF;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.Landmark;
import java.util.Iterator;
import org.telegram.ui.Components.Point;
import org.telegram.ui.Components.Size;

public class PhotoFace {
    private float angle;
    private Point chinPoint;
    private Point eyesCenterPoint;
    private float eyesDistance;
    private Point foreheadPoint;
    private Point mouthPoint;
    private float width;

    public PhotoFace(Face arg9, Bitmap arg10, Size arg11, boolean arg12) {
        super();
        Iterator v9 = arg9.getLandmarks().iterator();
        Point v0 = null;
        Point v1 = v0;
        Point v2 = v1;
        Point v3 = v2;
        while(v9.hasNext()) {
            Object v4 = v9.next();
            PointF v5 = ((Landmark)v4).getPosition();
            switch(((Landmark)v4).getType()) {
                case 4: {
                    goto label_20;
                }
                case 5: {
                    goto label_18;
                }
                case 10: {
                    goto label_16;
                }
                case 11: {
                    goto label_14;
                }
            }

            continue;
        label_18:
            v2 = this.transposePoint(v5, arg10, arg11, arg12);
            continue;
        label_20:
            v0 = this.transposePoint(v5, arg10, arg11, arg12);
            continue;
        label_14:
            v3 = this.transposePoint(v5, arg10, arg11, arg12);
            continue;
        label_16:
            v1 = this.transposePoint(v5, arg10, arg11, arg12);
        }

        float v9_1 = 90f;
        float v10 = 0.5f;
        if(v0 != null && v1 != null) {
            this.eyesCenterPoint = new Point(v0.x * v10 + v1.x * v10, v0.y * v10 + v1.y * v10);
            this.eyesDistance = ((float)Math.hypot(((double)(v1.x - v0.x)), ((double)(v1.y - v0.y))));
            this.angle = ((float)Math.toDegrees(Math.atan2(((double)(v1.y - v0.y)), ((double)(v1.x - v0.x))) + 3.141593));
            this.width = this.eyesDistance * 2.35f;
            float v12 = this.eyesDistance * 0.8f;
            double v4_1 = ((double)(((float)Math.toRadians(((double)(this.angle - v9_1))))));
            this.foreheadPoint = new Point(this.eyesCenterPoint.x + (((float)Math.cos(v4_1))) * v12, this.eyesCenterPoint.y + v12 * (((float)Math.sin(v4_1))));
        }

        if(v2 != null && v3 != null) {
            this.mouthPoint = new Point(v2.x * v10 + v3.x * v10, v2.y * v10 + v3.y * v10);
            float v11 = this.eyesDistance * 0.7f;
            double v0_1 = ((double)(((float)Math.toRadians(((double)(this.angle + v9_1))))));
            this.chinPoint = new Point(this.mouthPoint.x + (((float)Math.cos(v0_1))) * v11, this.mouthPoint.y + v11 * (((float)Math.sin(v0_1))));
        }
    }

    public float getAngle() {
        return this.angle;
    }

    public Point getPointForAnchor(int arg1) {
        switch(arg1) {
            case 0: {
                goto label_9;
            }
            case 1: {
                goto label_7;
            }
            case 2: {
                goto label_5;
            }
            case 3: {
                goto label_3;
            }
        }

        return null;
    label_3:
        return this.chinPoint;
    label_5:
        return this.mouthPoint;
    label_7:
        return this.eyesCenterPoint;
    label_9:
        return this.foreheadPoint;
    }

    public float getWidthForAnchor(int arg2) {
        if(arg2 == 1) {
            return this.eyesDistance;
        }

        return this.width;
    }

    public boolean isSufficient() {
        boolean v0 = this.eyesCenterPoint != null ? true : false;
        return v0;
    }

    private Point transposePoint(PointF arg4, Bitmap arg5, Size arg6, boolean arg7) {
        int v0 = arg7 ? arg5.getHeight() : arg5.getWidth();
        float v0_1 = ((float)v0);
        int v5 = arg7 ? arg5.getWidth() : arg5.getHeight();
        float v5_1 = ((float)v5);
        return new Point(arg6.width * arg4.x / v0_1, arg6.height * arg4.y / v5_1);
    }
}

