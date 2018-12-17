package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.vision.zzr;

final class zzc {
    static Rect zza(Text arg8) {
        Point[] v8 = arg8.getCornerPoints();
        int v0 = v8.length;
        int v1 = -2147483648;
        int v2 = 2147483647;
        int v3 = 0;
        int v4 = 2147483647;
        int v5 = -2147483648;
        while(v3 < v0) {
            Point v6 = v8[v3];
            v2 = Math.min(v2, v6.x);
            v1 = Math.max(v1, v6.x);
            v4 = Math.min(v4, v6.y);
            v5 = Math.max(v5, v6.y);
            ++v3;
        }

        return new Rect(v2, v4, v1, v5);
    }

    static Point[] zza(zzr arg12) {
        Point[] v0 = new Point[4];
        double v1 = Math.sin(Math.toRadians(((double)arg12.zzdh)));
        double v3 = Math.cos(Math.toRadians(((double)arg12.zzdh)));
        v0[0] = new Point(arg12.left, arg12.top);
        double v7 = ((double)arg12.left);
        double v9 = ((double)arg12.width);
        Double.isNaN(v9);
        Double.isNaN(v7);
        int v7_1 = ((int)(v7 + v9 * v3));
        double v8 = ((double)arg12.top);
        double v10 = ((double)arg12.width);
        Double.isNaN(v10);
        Double.isNaN(v8);
        v0[1] = new Point(v7_1, ((int)(v8 + v10 * v1)));
        v8 = ((double)v0[1].x);
        v10 = ((double)arg12.height);
        Double.isNaN(v10);
        Double.isNaN(v8);
        int v1_1 = ((int)(v8 - v10 * v1));
        v8 = ((double)v0[1].y);
        v10 = ((double)arg12.height);
        Double.isNaN(v10);
        Double.isNaN(v8);
        v0[2] = new Point(v1_1, ((int)(v8 + v10 * v3)));
        v0[3] = new Point(v0[0].x + (v0[2].x - v0[1].x), v0[0].y + (v0[2].y - v0[1].y));
        return v0;
    }
}

