package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.SparseArray;
import com.google.android.gms.internal.vision.zzr;
import com.google.android.gms.internal.vision.zzx;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TextBlock implements Text {
    private Point[] cornerPoints;
    private zzx[] zzdb;
    private List zzdc;
    private String zzdd;
    private Rect zzde;

    TextBlock(SparseArray arg4) {
        super();
        this.zzdb = new zzx[arg4.size()];
        int v0;
        for(v0 = 0; v0 < this.zzdb.length; ++v0) {
            this.zzdb[v0] = arg4.valueAt(v0);
        }
    }

    public Rect getBoundingBox() {
        if(this.zzde == null) {
            this.zzde = zzc.zza(((Text)this));
        }

        return this.zzde;
    }

    public List getComponents() {
        int v1 = 0;
        if(this.zzdb.length == 0) {
            return new ArrayList(0);
        }

        if(this.zzdc == null) {
            this.zzdc = new ArrayList(this.zzdb.length);
            zzx[] v0 = this.zzdb;
            int v2 = v0.length;
            while(v1 < v2) {
                this.zzdc.add(new Line(v0[v1]));
                ++v1;
            }
        }

        return this.zzdc;
    }

    public Point[] getCornerPoints() {
        int v17;
        double v13_1;
        int v16;
        double v8;
        TextBlock v0 = this;
        if(v0.cornerPoints == null) {
            int v2 = 0;
            if(v0.zzdb.length == 0) {
                v0.cornerPoints = new Point[0];
            }
            else {
                int v3 = 2147483647;
                int v1 = 0;
                int v4 = 2147483647;
                int v5 = -2147483648;
                int v6 = -2147483648;
                while(true) {
                    int v10 = 4;
                    if(v1 >= v0.zzdb.length) {
                        break;
                    }

                    zzr v7 = v0.zzdb[v1].zzdj;
                    zzr v12 = v0.zzdb[v2].zzdj;
                    int v13 = -v12.left;
                    int v14 = -v12.top;
                    v8 = Math.sin(Math.toRadians(((double)v12.zzdh)));
                    double v11 = Math.cos(Math.toRadians(((double)v12.zzdh)));
                    Point[] v15 = new Point[v10];
                    v16 = v6;
                    v15[0] = new Point(v7.left, v7.top);
                    v15[0].offset(v13, v14);
                    v13_1 = ((double)v15[0].x);
                    Double.isNaN(v13_1);
                    v17 = v3;
                    double v2_1 = ((double)v15[0].y);
                    Double.isNaN(v2_1);
                    v2 = ((int)(v13_1 * v11 + v2_1 * v8));
                    v13_1 = ((double)(-v15[0].x));
                    Double.isNaN(v13_1);
                    v13_1 *= v8;
                    v8 = ((double)v15[0].y);
                    Double.isNaN(v8);
                    v6 = ((int)(v13_1 + v8 * v11));
                    v15[0].x = v2;
                    v15[0].y = v6;
                    v15[1] = new Point(v7.width + v2, v6);
                    v15[2] = new Point(v7.width + v2, v7.height + v6);
                    v15[3] = new Point(v2, v6 + v7.height);
                    v6 = v16;
                    v3 = v17;
                    for(v2 = 0; v2 < 4; ++v2) {
                        Point v7_1 = v15[v2];
                        v3 = Math.min(v3, v7_1.x);
                        v5 = Math.max(v5, v7_1.x);
                        v4 = Math.min(v4, v7_1.y);
                        v6 = Math.max(v6, v7_1.y);
                    }

                    ++v1;
                    v2 = 0;
                }

                v17 = v3;
                v16 = v6;
                zzr v1_1 = v0.zzdb[0].zzdj;
                v2 = v1_1.left;
                v3 = v1_1.top;
                double v6_1 = Math.sin(Math.toRadians(((double)v1_1.zzdh)));
                v8 = Math.cos(Math.toRadians(((double)v1_1.zzdh)));
                Point[] v10_1 = new Point[4];
                int v12_1 = 0;
                v10_1[0] = new Point(v17, v4);
                v10_1[1] = new Point(v5, v4);
                v10_1[2] = new Point(v5, v16);
                v10_1[3] = new Point(v17, v16);
                while(v12_1 < 4) {
                    double v4_1 = ((double)v10_1[v12_1].x);
                    Double.isNaN(v4_1);
                    v13_1 = ((double)v10_1[v12_1].y);
                    Double.isNaN(v13_1);
                    v4 = ((int)(v4_1 * v8 - v13_1 * v6_1));
                    v13_1 = ((double)v10_1[v12_1].x);
                    Double.isNaN(v13_1);
                    int v18 = v2;
                    double v1_2 = ((double)v10_1[v12_1].y);
                    Double.isNaN(v1_2);
                    v10_1[v12_1].x = v4;
                    v10_1[v12_1].y = ((int)(v13_1 * v6_1 + v1_2 * v8));
                    v2 = v18;
                    v10_1[v12_1].offset(v2, v3);
                    ++v12_1;
                }

                v0.cornerPoints = v10_1;
            }
        }

        return v0.cornerPoints;
    }

    public String getLanguage() {
        if(this.zzdd != null) {
            return this.zzdd;
        }

        HashMap v0 = new HashMap();
        zzx[] v1 = this.zzdb;
        int v2 = v1.length;
        int v4;
        for(v4 = 0; v4 < v2; ++v4) {
            zzx v5 = v1[v4];
            int v6 = v0.containsKey(v5.zzdd) ? v0.get(v5.zzdd).intValue() : 0;
            v0.put(v5.zzdd, Integer.valueOf(v6 + 1));
        }

        this.zzdd = Collections.max(v0.entrySet(), new zza(this)).getKey();
        if(this.zzdd == null || (this.zzdd.isEmpty())) {
            this.zzdd = "und";
        }

        return this.zzdd;
    }

    public String getValue() {
        if(this.zzdb.length == 0) {
            return "";
        }

        StringBuilder v0 = new StringBuilder(this.zzdb[0].zzdm);
        int v1;
        for(v1 = 1; v1 < this.zzdb.length; ++v1) {
            v0.append("\n");
            v0.append(this.zzdb[v1].zzdm);
        }

        return v0.toString();
    }
}

