package org.telegram.ui.Components.Paint;

import android.graphics.Matrix;

public class GLMatrix {
    public GLMatrix() {
        super();
    }

    public static float[] LoadGraphicsMatrix(Matrix arg6) {
        float[] v0 = new float[16];
        float[] v2 = new float[9];
        arg6.getValues(v2);
        v0[0] = v2[0];
        v0[1] = v2[1];
        v0[2] = 0f;
        v0[3] = 0f;
        v0[4] = v2[3];
        v0[5] = v2[4];
        v0[6] = 0f;
        v0[7] = 0f;
        v0[8] = 0f;
        v0[9] = 0f;
        v0[10] = 1f;
        v0[11] = 0f;
        v0[12] = v2[2];
        v0[13] = v2[5];
        v0[14] = 0f;
        v0[15] = 1f;
        return v0;
    }

    public static float[] LoadOrtho(float arg3, float arg4, float arg5, float arg6, float arg7, float arg8) {
        float v0 = arg4 - arg3;
        float v1 = arg6 - arg5;
        float v2 = arg8 - arg7;
        return new float[]{2f / v0, 0f, 0f, 0f, 0f, 2f / v1, 0f, 0f, 0f, 0f, -2f / v2, 0f, -(arg4 + arg3) / v0, -(arg6 + arg5) / v1, -(arg8 + arg7) / v2, 1f};
    }

    public static float[] MultiplyMat4f(float[] arg21, float[] arg22) {
        return new float[]{arg21[0] * arg22[0] + arg21[4] * arg22[1] + arg21[8] * arg22[2] + arg21[12] * arg22[3], arg21[1] * arg22[0] + arg21[5] * arg22[1] + arg21[9] * arg22[2] + arg21[13] * arg22[3], arg21[2] * arg22[0] + arg21[6] * arg22[1] + arg21[10] * arg22[2] + arg21[14] * arg22[3], arg21[3] * arg22[0] + arg21[7] * arg22[1] + arg21[11] * arg22[2] + arg21[15] * arg22[3], arg21[0] * arg22[4] + arg21[4] * arg22[5] + arg21[8] * arg22[6] + arg21[12] * arg22[7], arg21[1] * arg22[4] + arg21[5] * arg22[5] + arg21[9] * arg22[6] + arg21[13] * arg22[7], arg21[2] * arg22[4] + arg21[6] * arg22[5] + arg21[10] * arg22[6] + arg21[14] * arg22[7], arg21[3] * arg22[4] + arg21[7] * arg22[5] + arg21[11] * arg22[6] + arg21[15] * arg22[7], arg21[0] * arg22[8] + arg21[4] * arg22[9] + arg21[8] * arg22[10] + arg21[12] * arg22[11], arg21[1] * arg22[8] + arg21[5] * arg22[9] + arg21[9] * arg22[10] + arg21[13] * arg22[11], arg21[2] * arg22[8] + arg21[6] * arg22[9] + arg21[10] * arg22[10] + arg21[14] * arg22[11], arg21[3] * arg22[8] + arg21[7] * arg22[9] + arg21[11] * arg22[10] + arg21[15] * arg22[11], arg21[0] * arg22[12] + arg21[4] * arg22[13] + arg21[8] * arg22[14] + arg21[12] * arg22[15], arg21[1] * arg22[12] + arg21[5] * arg22[13] + arg21[9] * arg22[14] + arg21[13] * arg22[15], arg21[2] * arg22[12] + arg21[6] * arg22[13] + arg21[10] * arg22[14] + arg21[14] * arg22[15], arg21[3] * arg22[12] + arg21[7] * arg22[13] + arg21[11] * arg22[14] + arg21[15] * arg22[15]};
    }
}

