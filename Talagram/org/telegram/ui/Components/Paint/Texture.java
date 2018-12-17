package org.telegram.ui.Components.Paint;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import org.telegram.ui.Components.Size;

public class Texture {
    private Bitmap bitmap;
    private int texture;

    public Texture(Bitmap arg1) {
        super();
        this.bitmap = arg1;
    }

    public void cleanResources(boolean arg5) {
        if(this.texture == 0) {
            return;
        }

        GLES20.glDeleteTextures(1, new int[]{this.texture}, 0);
        this.texture = 0;
        if(arg5) {
            this.bitmap.recycle();
        }
    }

    public static int generateTexture(Size arg11) {
        int[] v1 = new int[1];
        GLES20.glGenTextures(1, v1, 0);
        int v0 = v1[0];
        GLES20.glBindTexture(3553, v0);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexImage2D(3553, 0, 6408, ((int)arg11.width), ((int)arg11.height), 0, 6408, 5121, null);
        return v0;
    }

    private boolean isPOT(int arg2) {
        boolean v2 = (arg2 & arg2 - 1) == 0 ? true : false;
        return v2;
    }

    public int texture() {
        if(this.texture != 0) {
            return this.texture;
        }

        if(this.bitmap.isRecycled()) {
            return 0;
        }

        int[] v2 = new int[1];
        GLES20.glGenTextures(1, v2, 0);
        this.texture = v2[0];
        GLES20.glBindTexture(3553, this.texture);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLUtils.texImage2D(3553, 0, this.bitmap, 0);
        Utils.HasGLError();
        return this.texture;
    }
}

