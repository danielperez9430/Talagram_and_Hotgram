package org.telegram.ui.Components.Paint;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.opengl.GLES20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Render {
    public Render() {
        super();
    }

    private static RectF Draw(RenderState arg16) {
        float v6;
        int v1_1;
        RectF v0 = new RectF(0f, 0f, 0f, 0f);
        int v2 = arg16.getCount();
        if(v2 == 0) {
            return v0;
        }

        int v4 = v2 - 1;
        ByteBuffer v3 = ByteBuffer.allocateDirect((v2 * 4 + v4 * 2) * 20);
        v3.order(ByteOrder.nativeOrder());
        FloatBuffer v3_1 = v3.asFloatBuffer();
        int v5 = 0;
        v3_1.position(0);
        arg16.setPosition(0);
        int v7 = 0;
        int v8 = 0;
        while(v7 < v2) {
            float v13 = arg16.read();
            float v14 = arg16.read();
            float v15 = arg16.read();
            float v1 = arg16.read();
            float v9 = arg16.read();
            RectF v10 = new RectF(v13 - v15, v14 - v15, v13 + v15, v14 + v15);
            float[] v11 = new float[8];
            v11[v5] = v10.left;
            v11[1] = v10.top;
            v11[2] = v10.right;
            int v13_1 = 3;
            v11[v13_1] = v10.top;
            v11[4] = v10.left;
            v11[5] = v10.bottom;
            v11[6] = v10.right;
            v11[7] = v10.bottom;
            float v12 = v10.centerX();
            v15 = v10.centerY();
            Matrix v14_1 = new Matrix();
            v14_1.setRotate(((float)Math.toDegrees(((double)v1))), v12, v15);
            v14_1.mapPoints(v11);
            v14_1.mapRect(v10);
            Utils.RectFIntegral(v10);
            v0.union(v10);
            if(v8 != 0) {
                v1_1 = 0;
                v3_1.put(v11[0]);
                v5 = 1;
                v3_1.put(v11[1]);
                v6 = 0f;
                v3_1.put(0f);
                v3_1.put(0f);
                v3_1.put(v9);
                ++v8;
            }
            else {
                v1_1 = 0;
                v5 = 1;
                v6 = 0f;
            }

            v3_1.put(v11[v1_1]);
            v3_1.put(v11[v5]);
            v3_1.put(v6);
            v3_1.put(v6);
            v3_1.put(v9);
            v3_1.put(v11[2]);
            v3_1.put(v11[v13_1]);
            v1 = 1f;
            v3_1.put(v1);
            v3_1.put(v6);
            v3_1.put(v9);
            v3_1.put(v11[4]);
            v3_1.put(v11[5]);
            v3_1.put(v6);
            v3_1.put(v1);
            v3_1.put(v9);
            int v10_1 = 6;
            v3_1.put(v11[v10_1]);
            int v12_1 = 7;
            v3_1.put(v11[v12_1]);
            v3_1.put(v1);
            v3_1.put(v1);
            v3_1.put(v9);
            v8 = v8 + v5 + v5 + v5 + v5;
            if(v7 != v4) {
                v3_1.put(v11[v10_1]);
                v3_1.put(v11[v12_1]);
                v3_1.put(v1);
                v3_1.put(v1);
                v3_1.put(v9);
                ++v8;
            }

            ++v7;
            v5 = 0;
        }

        v3_1.position(0);
        GLES20.glVertexAttribPointer(0, 2, 5126, false, 20, v3_1.slice());
        GLES20.glEnableVertexAttribArray(0);
        v3_1.position(2);
        GLES20.glVertexAttribPointer(1, 2, 5126, true, 20, v3_1.slice());
        GLES20.glEnableVertexAttribArray(1);
        v3_1.position(4);
        GLES20.glVertexAttribPointer(2, 1, 5126, true, 20, v3_1.slice());
        GLES20.glEnableVertexAttribArray(2);
        GLES20.glDrawArrays(5, 0, v8);
        return v0;
    }

    private static void PaintSegment(Point arg21, Point arg22, RenderState arg23) {
        boolean v19_1;
        int v1_1;
        Point v0 = arg21;
        Point v1 = arg22;
        RenderState v8 = arg23;
        double v9 = ((double)arg21.getDistanceTo(arg22));
        Point v2 = v1.substract(v0);
        Point v3 = new Point(1, 1, 0);
        float v4 = Math.abs(v8.angle) > 0f ? v8.angle : ((float)Math.atan2(v2.y, v2.x));
        float v11 = v4;
        float v12 = v8.baseWeight * v8.scale;
        float v5 = 1f;
        double v13 = ((double)Math.max(v5, v8.spacing * v12));
        if(v9 > 0) {
            Double.isNaN(v9);
            v3 = v2.multiplyByScalar(1 / v9);
        }

        Point v15 = v3;
        float v16 = Math.min(v5, v8.alpha * 1.15f);
        boolean v2_1 = v0.edge;
        boolean v7 = v1.edge;
        double v3_1 = v8.remainder;
        Double.isNaN(v9);
        Double.isNaN(v13);
        int v3_2 = ((int)Math.ceil((v9 - v3_1) / v13));
        int v4_1 = arg23.getCount();
        v8.appendValuesCount(v3_2);
        v8.setPosition(v4_1);
        v0 = v0.add(v15.multiplyByScalar(v8.remainder));
        double v17 = v8.remainder;
        boolean v3_3 = true;
        while(v17 <= v9) {
            float v19 = v2_1 ? v16 : v8.alpha;
            v1_1 = 1;
            float v6 = v19;
            v19_1 = v7;
            v3_3 = arg23.addPoint(v0.toPointF(), v12, v11, v6, -1);
            if(!v3_3) {
                goto label_92;
            }

            v0 = v0.add(v15.multiplyByScalar(v13));
            v2_1 = false;
            Double.isNaN(v13);
            v17 += v13;
            v7 = v19_1;
        }

        v19_1 = v7;
        v1_1 = 1;
    label_92:
        if((v3_3) && (v19_1)) {
            v8.appendValuesCount(v1_1);
            arg23.addPoint(arg22.toPointF(), v12, v11, v16, -1);
        }

        Double.isNaN(v9);
        v8.remainder = v17 - v9;
    }

    private static void PaintStamp(Point arg8, RenderState arg9) {
        float v4 = arg9.baseWeight * arg9.scale;
        PointF v3 = arg8.toPointF();
        float v5 = Math.abs(arg9.angle) > 0f ? arg9.angle : 0f;
        float v6 = arg9.alpha;
        arg9.prepare();
        arg9.appendValuesCount(1);
        arg9.addPoint(v3, v4, v5, v6, 0);
    }

    public static RectF RenderPath(Path arg5, RenderState arg6) {
        arg6.baseWeight = arg5.getBaseWeight();
        arg6.spacing = arg5.getBrush().getSpacing();
        arg6.alpha = arg5.getBrush().getAlpha();
        arg6.angle = arg5.getBrush().getAngle();
        arg6.scale = arg5.getBrush().getScale();
        int v0 = arg5.getLength();
        if(v0 == 0) {
            return null;
        }

        int v1 = 0;
        if(v0 == 1) {
            Render.PaintStamp(arg5.getPoints()[0], arg6);
        }
        else {
            Point[] v0_1 = arg5.getPoints();
            arg6.prepare();
            while(v1 < v0_1.length - 1) {
                Point v3 = v0_1[v1];
                ++v1;
                Render.PaintSegment(v3, v0_1[v1], arg6);
            }
        }

        arg5.remainder = arg6.remainder;
        return Render.Draw(arg6);
    }
}

