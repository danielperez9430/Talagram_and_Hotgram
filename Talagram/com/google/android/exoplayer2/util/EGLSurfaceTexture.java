package com.google.android.exoplayer2.util;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture$OnFrameAvailableListener;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.os.Handler;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@TargetApi(value=17) public final class EGLSurfaceTexture implements SurfaceTexture$OnFrameAvailableListener, Runnable {
    class com.google.android.exoplayer2.util.EGLSurfaceTexture$1 {
    }

    public final class GlException extends RuntimeException {
        GlException(String arg1, com.google.android.exoplayer2.util.EGLSurfaceTexture$1 arg2) {
            this(arg1);
        }

        private GlException(String arg1) {
            super(arg1);
        }
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface SecureMode {
    }

    public interface TextureImageListener {
        void onFrameAvailable();
    }

    private static final int[] EGL_CONFIG_ATTRIBUTES = null;
    private static final int EGL_PROTECTED_CONTENT_EXT = 12992;
    private static final int EGL_SURFACE_HEIGHT = 1;
    private static final int EGL_SURFACE_WIDTH = 1;
    public static final int SECURE_MODE_NONE = 0;
    public static final int SECURE_MODE_PROTECTED_PBUFFER = 2;
    public static final int SECURE_MODE_SURFACELESS_CONTEXT = 1;
    private final TextureImageListener callback;
    private EGLContext context;
    private EGLDisplay display;
    private final Handler handler;
    private EGLSurface surface;
    private SurfaceTexture texture;
    private final int[] textureIdHolder;

    static {
        EGLSurfaceTexture.EGL_CONFIG_ATTRIBUTES = new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12327, 12344, 12339, 4, 12344};
    }

    public EGLSurfaceTexture(Handler arg2) {
        this(arg2, null);
    }

    public EGLSurfaceTexture(Handler arg1, TextureImageListener arg2) {
        super();
        this.handler = arg1;
        this.callback = arg2;
        this.textureIdHolder = new int[1];
    }

    private static EGLConfig chooseEGLConfig(EGLDisplay arg11) {
        EGLConfig[] v9 = new EGLConfig[1];
        int[] v10 = new int[1];
        boolean v11 = EGL14.eglChooseConfig(arg11, EGLSurfaceTexture.EGL_CONFIG_ATTRIBUTES, 0, v9, 0, 1, v10, 0);
        if((v11) && v10[0] > 0 && v9[0] != null) {
            return v9[0];
        }

        throw new GlException(Util.formatInvariant("eglChooseConfig failed: success=%b, numConfigs[0]=%d, configs[0]=%s", new Object[]{Boolean.valueOf(v11), Integer.valueOf(v10[0]), v9[0]}), null);
    }

    private static EGLContext createEGLContext(EGLDisplay arg2, EGLConfig arg3, int arg4) {
        int[] v4 = arg4 == 0 ? new int[]{12440, 2, 12344} : new int[]{12440, 2, 12992, 1, 12344};
        EGLContext v2 = EGL14.eglCreateContext(arg2, arg3, EGL14.EGL_NO_CONTEXT, v4, 0);
        if(v2 != null) {
            return v2;
        }

        throw new GlException("eglCreateContext failed", null);
    }

    private static EGLSurface createEGLSurface(EGLDisplay arg2, EGLConfig arg3, EGLContext arg4, int arg5) {
        EGLSurface v3;
        com.google.android.exoplayer2.util.EGLSurfaceTexture$1 v0 = null;
        if(arg5 == 1) {
            v3 = EGL14.EGL_NO_SURFACE;
        }
        else {
            int[] v5 = arg5 == 2 ? new int[]{12375, 1, 12374, 1, 12992, 1, 12344} : new int[]{12375, 1, 12374, 1, 12344};
            v3 = EGL14.eglCreatePbufferSurface(arg2, arg3, v5, 0);
            if(v3 == null) {
                goto label_22;
            }
        }

        if(EGL14.eglMakeCurrent(arg2, v3, v3, arg4)) {
            return v3;
        }

        throw new GlException("eglMakeCurrent failed", v0);
    label_22:
        throw new GlException("eglCreatePbufferSurface failed", v0);
    }

    private void dispatchOnFrameAvailable() {
        if(this.callback != null) {
            this.callback.onFrameAvailable();
        }
    }

    private static void generateTextureIds(int[] arg3) {
        GLES20.glGenTextures(1, arg3, 0);
        int v3 = GLES20.glGetError();
        if(v3 == 0) {
            return;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("glGenTextures failed. Error: ");
        v1.append(Integer.toHexString(v3));
        throw new GlException(v1.toString(), null);
    }

    private static EGLDisplay getDefaultDisplay() {
        EGLDisplay v1 = EGL14.eglGetDisplay(0);
        com.google.android.exoplayer2.util.EGLSurfaceTexture$1 v2 = null;
        if(v1 != null) {
            int[] v3 = new int[2];
            if(EGL14.eglInitialize(v1, v3, 0, v3, 1)) {
                return v1;
            }

            throw new GlException("eglInitialize failed", v2);
        }

        throw new GlException("eglGetDisplay failed", v2);
    }

    public SurfaceTexture getSurfaceTexture() {
        return Assertions.checkNotNull(this.texture);
    }

    public void init(int arg4) {
        this.display = EGLSurfaceTexture.getDefaultDisplay();
        EGLConfig v0 = EGLSurfaceTexture.chooseEGLConfig(this.display);
        this.context = EGLSurfaceTexture.createEGLContext(this.display, v0, arg4);
        this.surface = EGLSurfaceTexture.createEGLSurface(this.display, v0, this.context, arg4);
        EGLSurfaceTexture.generateTextureIds(this.textureIdHolder);
        this.texture = new SurfaceTexture(this.textureIdHolder[0]);
        this.texture.setOnFrameAvailableListener(((SurfaceTexture$OnFrameAvailableListener)this));
    }

    public void onFrameAvailable(SurfaceTexture arg1) {
        this.handler.post(((Runnable)this));
    }

    public void release() {
        this.handler.removeCallbacks(((Runnable)this));
        int v0 = 19;
        EGLDisplay v1 = null;
        try {
            if(this.texture != null) {
                this.texture.release();
                GLES20.glDeleteTextures(1, this.textureIdHolder, 0);
            }
        }
        catch(Throwable v2) {
            if(this.display != null && !this.display.equals(EGL14.EGL_NO_DISPLAY)) {
                EGL14.eglMakeCurrent(this.display, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
            }

            if(this.surface != null && !this.surface.equals(EGL14.EGL_NO_SURFACE)) {
                EGL14.eglDestroySurface(this.display, this.surface);
            }

            if(this.context != null) {
                EGL14.eglDestroyContext(this.display, this.context);
            }

            if(Util.SDK_INT >= v0) {
                EGL14.eglReleaseThread();
            }

            if(this.display != null && !this.display.equals(EGL14.EGL_NO_DISPLAY)) {
                EGL14.eglTerminate(this.display);
            }

            this.display = v1;
            this.context = ((EGLContext)v1);
            this.surface = ((EGLSurface)v1);
            this.texture = ((SurfaceTexture)v1);
            throw v2;
        }

        if(this.display != null && !this.display.equals(EGL14.EGL_NO_DISPLAY)) {
            EGL14.eglMakeCurrent(this.display, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
        }

        if(this.surface != null && !this.surface.equals(EGL14.EGL_NO_SURFACE)) {
            EGL14.eglDestroySurface(this.display, this.surface);
        }

        if(this.context != null) {
            EGL14.eglDestroyContext(this.display, this.context);
        }

        if(Util.SDK_INT >= v0) {
            EGL14.eglReleaseThread();
        }

        if(this.display != null && !this.display.equals(EGL14.EGL_NO_DISPLAY)) {
            EGL14.eglTerminate(this.display);
        }

        this.display = v1;
        this.context = ((EGLContext)v1);
        this.surface = ((EGLSurface)v1);
        this.texture = ((SurfaceTexture)v1);
    }

    public void run() {
        this.dispatchOnFrameAvailable();
        if(this.texture != null) {
            try {
                this.texture.updateTexImage();
                return;
            }
            catch(RuntimeException ) {
                return;
            }
        }
    }
}

