package org.telegram.ui.Components.Paint;

import android.graphics.Matrix;
import android.view.MotionEvent;
import java.util.Vector;
import org.telegram.messenger.AndroidUtilities;

public class Input {
    private boolean beganDrawing;
    private boolean clearBuffer;
    private boolean hasMoved;
    private Matrix invertMatrix;
    private boolean isFirst;
    private Point lastLocation;
    private double lastRemainder;
    private Point[] points;
    private int pointsCount;
    private RenderView renderView;
    private float[] tempPoint;

    public Input(RenderView arg2) {
        super();
        this.points = new Point[3];
        this.tempPoint = new float[2];
        this.renderView = arg2;
    }

    static double access$002(Input arg0, double arg1) {
        arg0.lastRemainder = arg1;
        return arg1;
    }

    static boolean access$102(Input arg0, boolean arg1) {
        arg0.clearBuffer = arg1;
        return arg1;
    }

    private void paintPath(Path arg4) {
        arg4.setup(this.renderView.getCurrentColor(), this.renderView.getCurrentWeight(), this.renderView.getCurrentBrush());
        if(this.clearBuffer) {
            this.lastRemainder = 0;
        }

        arg4.remainder = this.lastRemainder;
        this.renderView.getPainting().paintStroke(arg4, this.clearBuffer, new Runnable(arg4) {
            public void run() {
                AndroidUtilities.runOnUIThread(new Runnable() {
                    public void run() {
                        this.this$1.this$0.lastRemainder = this.this$1.val$path.remainder;
                        this.this$1.this$0.clearBuffer = false;
                    }
                });
            }
        });
    }

    public void process(MotionEvent arg12) {
        int v0 = arg12.getActionMasked();
        float v1 = arg12.getX();
        float v2 = (((float)this.renderView.getHeight())) - arg12.getY();
        this.tempPoint[0] = v1;
        this.tempPoint[1] = v2;
        this.invertMatrix.mapPoints(this.tempPoint);
        Point v12 = new Point(((double)this.tempPoint[0]), ((double)this.tempPoint[1]), 1);
        switch(v0) {
            case 1: {
                if(!this.hasMoved) {
                    if(this.renderView.shouldDraw()) {
                        v12.edge = true;
                        this.paintPath(new Path(v12));
                    }

                    this.reset();
                }
                else {
                    if(this.pointsCount <= 0) {
                        goto label_43;
                    }

                    this.smoothenAndPaintPoints(true);
                }

            label_43:
                this.pointsCount = 0;
                this.renderView.getPainting().commitStroke(this.renderView.getCurrentColor());
                this.beganDrawing = false;
                this.renderView.onFinishedDrawing(this.hasMoved);
                break;
            }
            case 0: 
            case 2: {
                if(!this.beganDrawing) {
                    this.beganDrawing = true;
                    this.hasMoved = false;
                    this.isFirst = true;
                    this.lastLocation = v12;
                    this.points[0] = v12;
                    this.pointsCount = 1;
                    this.clearBuffer = true;
                    return;
                }

                if(v12.getDistanceTo(this.lastLocation) < (((float)AndroidUtilities.dp(5f)))) {
                    return;
                }

                if(!this.hasMoved) {
                    this.renderView.onBeganDrawing();
                    this.hasMoved = true;
                }

                this.points[this.pointsCount] = v12;
                ++this.pointsCount;
                if(this.pointsCount == 3) {
                    this.smoothenAndPaintPoints(false);
                }

                this.lastLocation = v12;
                break;
            }
            default: {
                break;
            }
        }
    }

    private void reset() {
        this.pointsCount = 0;
    }

    public void setMatrix(Matrix arg2) {
        this.invertMatrix = new Matrix();
        arg2.invert(this.invertMatrix);
    }

    private Point smoothPoint(Point arg18, Point arg19, Point arg20, float arg21) {
        float v4 = 1f - arg21;
        double v5 = Math.pow(((double)v4), 2);
        double v7 = ((double)(v4 * 2f * arg21));
        double v3 = ((double)(arg21 * arg21));
        double v9 = arg18.x * v5;
        double v11 = arg20.x;
        Double.isNaN(v7);
        v9 += v11 * v7;
        v11 = arg19.x;
        Double.isNaN(v3);
        double v10 = v9 + v11 * v3;
        double v12 = arg18.y * v5;
        v5 = arg20.y;
        Double.isNaN(v7);
        double v0 = arg19.y;
        Double.isNaN(v3);
        return new Point(v10, v12 + v5 * v7 + v0 * v3, 1);
    }

    private void smoothenAndPaintPoints(boolean arg15) {
        int v1 = 2;
        if(this.pointsCount > v1) {
            Vector v0 = new Vector();
            Point v3 = this.points[0];
            Point v4 = this.points[1];
            Point v6 = this.points[v1];
            if(v6 != null && v4 != null) {
                if(v3 == null) {
                }
                else {
                    v3 = v4.multiplySum(v3, 0.5);
                    v6 = v6.multiplySum(v4, 0.5);
                    int v7 = ((int)Math.min(48, Math.max(Math.floor(((double)(v3.getDistanceTo(v6) / (((float)1))))), 24)));
                    float v9 = 1f / (((float)v7));
                    int v8 = 0;
                    float v10 = 0f;
                    while(v8 < v7) {
                        Point v11 = this.smoothPoint(v3, v6, v4, v10);
                        if(this.isFirst) {
                            v11.edge = true;
                            this.isFirst = false;
                        }

                        v0.add(v11);
                        v10 += v9;
                        ++v8;
                    }

                    if(arg15) {
                        v6.edge = true;
                    }

                    v0.add(v6);
                    Point[] v3_1 = new Point[v0.size()];
                    v0.toArray(((Object[])v3_1));
                    this.paintPath(new Path(v3_1));
                    System.arraycopy(this.points, 1, this.points, 0, v1);
                    if(arg15) {
                        this.pointsCount = 0;
                        return;
                    }

                    this.pointsCount = v1;
                    return;
                }
            }

            return;
        }
        else {
            Point[] v15 = new Point[this.pointsCount];
            System.arraycopy(this.points, 0, v15, 0, this.pointsCount);
            this.paintPath(new Path(v15));
        }
    }
}

