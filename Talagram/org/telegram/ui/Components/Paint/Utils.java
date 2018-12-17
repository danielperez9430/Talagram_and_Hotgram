package org.telegram.ui.Components.Paint;

import android.graphics.RectF;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

public class Utils {
    public Utils() {
        super();
    }

    public static void HasGLError() {
        int v0 = GLES20.glGetError();
        if(v0 != 0) {
            Log.d("Paint", GLUtils.getEGLErrorString(v0));
        }
    }

    public static void RectFIntegral(RectF arg2) {
        arg2.left = ((float)(((int)Math.floor(((double)arg2.left)))));
        arg2.top = ((float)(((int)Math.floor(((double)arg2.top)))));
        arg2.right = ((float)(((int)Math.ceil(((double)arg2.right)))));
        arg2.bottom = ((float)(((int)Math.ceil(((double)arg2.bottom)))));
    }
}

