package com.google.android.gms.vision.face;

import android.graphics.PointF;
import java.util.List;

public class Face {
    public static final float UNCOMPUTED_PROBABILITY = 0f;
    private float height;
    private int id;
    private float width;
    private PointF zzbo;
    private float zzbp;
    private float zzbq;
    private List zzbr;
    private float zzbs;
    private float zzbt;
    private float zzbu;

    public Face(int arg1, PointF arg2, float arg3, float arg4, float arg5, float arg6, Landmark[] arg7, float arg8, float arg9, float arg10) {
        // Method was not decompiled
    }

    public float getEulerY() {
        return this.zzbp;
    }

    public float getEulerZ() {
        return this.zzbq;
    }

    public float getHeight() {
        return this.height;
    }

    public int getId() {
        return this.id;
    }

    public float getIsLeftEyeOpenProbability() {
        return this.zzbs;
    }

    public float getIsRightEyeOpenProbability() {
        return this.zzbt;
    }

    public float getIsSmilingProbability() {
        return this.zzbu;
    }

    public List getLandmarks() {
        return this.zzbr;
    }

    public PointF getPosition() {
        return new PointF(this.zzbo.x - this.width / 2f, this.zzbo.y - this.height / 2f);
    }

    public float getWidth() {
        return this.width;
    }
}

