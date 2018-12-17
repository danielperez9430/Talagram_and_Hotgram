package org.telegram.ui.Components.Paint;

import java.util.Arrays;
import java.util.Vector;

public class Path {
    private float baseWeight;
    private Brush brush;
    private int color;
    private Vector points;
    public double remainder;

    public Path(Point[] arg2) {
        super();
        this.points = new Vector();
        this.points.addAll(Arrays.asList(((Object[])arg2)));
    }

    public Path(Point arg2) {
        super();
        this.points = new Vector();
        this.points.add(arg2);
    }

    public float getBaseWeight() {
        return this.baseWeight;
    }

    public Brush getBrush() {
        return this.brush;
    }

    public int getColor() {
        return this.color;
    }

    public int getLength() {
        if(this.points == null) {
            return 0;
        }

        return this.points.size();
    }

    public Point[] getPoints() {
        Point[] v0 = new Point[this.points.size()];
        this.points.toArray(((Object[])v0));
        return v0;
    }

    public void setup(int arg1, float arg2, Brush arg3) {
        this.color = arg1;
        this.baseWeight = arg2;
        this.brush = arg3;
    }
}

