package org.telegram.ui.Components.Crop;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class CropState {
    private float height;
    private Matrix matrix;
    private float minimumScale;
    private float rotation;
    private float scale;
    private float[] values;
    private float width;
    private float x;
    private float y;

    public CropState(Bitmap arg2) {
        super();
        this.width = ((float)arg2.getWidth());
        this.height = ((float)arg2.getHeight());
        this.x = 0f;
        this.y = 0f;
        this.scale = 1f;
        this.rotation = 0f;
        this.matrix = new Matrix();
        this.values = new float[9];
    }

    public void getConcatMatrix(Matrix arg2) {
        arg2.postConcat(this.matrix);
    }

    public float getHeight() {
        return this.height;
    }

    public Matrix getMatrix() {
        Matrix v0 = new Matrix();
        v0.set(this.matrix);
        return v0;
    }

    public float getRotation() {
        return this.rotation;
    }

    public float getScale() {
        return this.scale;
    }

    public float getWidth() {
        return this.width;
    }

    public float getX() {
        this.updateValues();
        return this.values[2];
    }

    public float getY() {
        this.updateValues();
        return this.values[5];
    }

    public void reset(CropAreaView arg3) {
        this.matrix.reset();
        this.x = 0f;
        this.y = 0f;
        this.rotation = 0f;
        this.minimumScale = arg3.getCropWidth() / this.width;
        this.scale = this.minimumScale;
        this.matrix.postScale(this.scale, this.scale);
    }

    public void rotate(float arg2, float arg3, float arg4) {
        this.rotation += arg2;
        this.matrix.postRotate(arg2, arg3, arg4);
    }

    public void scale(float arg2, float arg3, float arg4) {
        this.scale *= arg2;
        this.matrix.postScale(arg2, arg2, arg3, arg4);
    }

    public void translate(float arg2, float arg3) {
        this.x += arg2;
        this.y += arg3;
        this.matrix.postTranslate(arg2, arg3);
    }

    private void updateValues() {
        this.matrix.getValues(this.values);
    }
}

