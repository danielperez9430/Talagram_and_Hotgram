package org.telegram.ui.Components.Paint;

import android.graphics.PointF;

public class Point {
    public boolean edge;
    public double x;
    public double y;
    public double z;

    public Point(double arg1, double arg3, double arg5) {
        super();
        this.x = arg1;
        this.y = arg3;
        this.z = arg5;
    }

    public Point(Point arg3) {
        super();
        this.x = arg3.x;
        this.y = arg3.y;
        this.z = arg3.z;
    }

    Point add(Point arg11) {
        return new Point(this.x + arg11.x, this.y + arg11.y, this.z + arg11.z);
    }

    void alteringAddMultiplication(Point arg5, double arg6) {
        this.x += arg5.x * arg6;
        this.y += arg5.y * arg6;
        this.z += arg5.z * arg6;
    }

    public boolean equals(Object arg8) {
        boolean v0 = false;
        if(arg8 == null) {
            return 0;
        }

        if((((Point)arg8)) == this) {
            return 1;
        }

        if(!(arg8 instanceof Point)) {
            return 0;
        }

        if(this.x == ((Point)arg8).x && this.y == ((Point)arg8).y && this.z == ((Point)arg8).z) {
            v0 = true;
        }

        return v0;
    }

    float getDistanceTo(Point arg9) {
        return ((float)Math.sqrt(Math.pow(this.x - arg9.x, 2) + Math.pow(this.y - arg9.y, 2) + Math.pow(this.z - arg9.z, 2)));
    }

    private double getMagnitude() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    Point getNormalized() {
        return this.multiplyByScalar(1 / this.getMagnitude());
    }

    Point multiplyAndAdd(double arg9, Point arg11) {
        return new Point(this.x * arg9 + arg11.x, this.y * arg9 + arg11.y, this.z * arg9 + arg11.z);
    }

    Point multiplyByScalar(double arg9) {
        return new Point(this.x * arg9, this.y * arg9, this.z * arg9);
    }

    Point multiplySum(Point arg11, double arg12) {
        return new Point((this.x + arg11.x) * arg12, (this.y + arg11.y) * arg12, (this.z + arg11.z) * arg12);
    }

    Point substract(Point arg11) {
        return new Point(this.x - arg11.x, this.y - arg11.y, this.z - arg11.z);
    }

    PointF toPointF() {
        return new PointF(((float)this.x), ((float)this.y));
    }
}

