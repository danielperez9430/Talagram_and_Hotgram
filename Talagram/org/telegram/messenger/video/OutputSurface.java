package org.telegram.messenger.video;

import android.graphics.SurfaceTexture$OnFrameAvailableListener;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.view.Surface;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

public class OutputSurface implements SurfaceTexture$OnFrameAvailableListener {
    private static final int EGL_CONTEXT_CLIENT_VERSION = 12440;
    private static final int EGL_OPENGL_ES2_BIT = 4;
    private EGL10 mEGL;
    private EGLContext mEGLContext;
    private EGLDisplay mEGLDisplay;
    private EGLSurface mEGLSurface;
    private boolean mFrameAvailable;
    private final Object mFrameSyncObject;
    private int mHeight;
    private ByteBuffer mPixelBuf;
    private Surface mSurface;
    private SurfaceTexture mSurfaceTexture;
    private TextureRenderer mTextureRender;
    private int mWidth;
    private int rotateRender;

    public OutputSurface() {
        super();
        this.mEGLDisplay = null;
        this.mEGLContext = null;
        this.mEGLSurface = null;
        this.mFrameSyncObject = new Object();
        this.rotateRender = 0;
        this.setup();
    }

    public OutputSurface(int arg2, int arg3, int arg4) {
        super();
        this.mEGLDisplay = null;
        this.mEGLContext = null;
        this.mEGLSurface = null;
        this.mFrameSyncObject = new Object();
        this.rotateRender = 0;
        if(arg2 > 0 && arg3 > 0) {
            this.mWidth = arg2;
            this.mHeight = arg3;
            this.rotateRender = arg4;
            this.mPixelBuf = ByteBuffer.allocateDirect(this.mWidth * this.mHeight * 4);
            this.mPixelBuf.order(ByteOrder.LITTLE_ENDIAN);
            this.eglSetup(arg2, arg3);
            this.makeCurrent();
            this.setup();
            return;
        }

        throw new IllegalArgumentException();
    }

    public void awaitNewImage() {
        Object v0 = this.mFrameSyncObject;
        __monitor_enter(v0);
        try {
            while(true) {
                if(this.mFrameAvailable) {
                    goto label_18;
                }

                try {
                    this.mFrameSyncObject.wait(2500);
                    if(this.mFrameAvailable) {
                        continue;
                    }

                    throw new RuntimeException("Surface frame wait timed out");
                }
                catch(InterruptedException v1_1) {
                    try {
                        throw new RuntimeException(((Throwable)v1_1));
                    label_18:
                        this.mFrameAvailable = false;
                        __monitor_exit(v0);
                        break;
                    }
                    catch(Throwable v1) {
                        goto label_28;
                    }
                }
            }
        }
        catch(Throwable v1) {
            goto label_28;
        }

        this.mTextureRender.checkGlError("before updateTexImage");
        this.mSurfaceTexture.updateTexImage();
        return;
        try {
        label_28:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_28;
        }

        throw v1;
    }

    private void checkEglError(String arg2) {
        if(this.mEGL.eglGetError() == 12288) {
            return;
        }

        throw new RuntimeException("EGL error encountered (see log)");
    }

    public void drawImage(boolean arg3) {
        this.mTextureRender.drawFrame(this.mSurfaceTexture, arg3);
    }

    private void eglSetup(int arg10, int arg11) {
        this.mEGL = EGLContext.getEGL();
        this.mEGLDisplay = this.mEGL.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        if(this.mEGLDisplay != EGL10.EGL_NO_DISPLAY) {
            int[] v2 = null;
            if(this.mEGL.eglInitialize(this.mEGLDisplay, v2)) {
                int[] v3 = new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12339, 1, 12352, 4, 12344};
                EGLConfig[] v7 = new EGLConfig[1];
                if(this.mEGL.eglChooseConfig(this.mEGLDisplay, v3, v7, v7.length, new int[1])) {
                    int v1 = 3;
                    this.mEGLContext = this.mEGL.eglCreateContext(this.mEGLDisplay, v7[0], EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
                    this.checkEglError("eglCreateContext");
                    if(this.mEGLContext != null) {
                        v2 = new int[5];
                        v2[0] = 12375;
                        v2[1] = arg10;
                        v2[2] = 12374;
                        v2[v1] = arg11;
                        v2[4] = 12344;
                        this.mEGLSurface = this.mEGL.eglCreatePbufferSurface(this.mEGLDisplay, v7[0], v2);
                        this.checkEglError("eglCreatePbufferSurface");
                        if(this.mEGLSurface != null) {
                            return;
                        }

                        throw new RuntimeException("surface was null");
                    }

                    throw new RuntimeException("null context");
                }

                throw new RuntimeException("unable to find RGB888+pbuffer EGL config");
            }

            this.mEGLDisplay = ((EGLDisplay)v2);
            throw new RuntimeException("unable to initialize EGL10");
        }

        throw new RuntimeException("unable to get EGL10 display");
    }

    public ByteBuffer getFrame() {
        this.mPixelBuf.rewind();
        GLES20.glReadPixels(0, 0, this.mWidth, this.mHeight, 6408, 5121, this.mPixelBuf);
        return this.mPixelBuf;
    }

    public Surface getSurface() {
        return this.mSurface;
    }

    public void makeCurrent() {
        if(this.mEGL != null) {
            this.checkEglError("before makeCurrent");
            if(this.mEGL.eglMakeCurrent(this.mEGLDisplay, this.mEGLSurface, this.mEGLSurface, this.mEGLContext)) {
                return;
            }

            throw new RuntimeException("eglMakeCurrent failed");
        }

        throw new RuntimeException("not configured for makeCurrent");
    }

    public void onFrameAvailable(SurfaceTexture arg3) {
        Object v3 = this.mFrameSyncObject;
        __monitor_enter(v3);
        try {
            if(!this.mFrameAvailable) {
                this.mFrameAvailable = true;
                this.mFrameSyncObject.notifyAll();
                __monitor_exit(v3);
                return;
            }

            throw new RuntimeException("mFrameAvailable already set, frame could be dropped");
        label_15:
            __monitor_exit(v3);
        }
        catch(Throwable v0) {
            goto label_15;
        }

        throw v0;
    }

    public void release() {
        if(this.mEGL != null) {
            if(this.mEGL.eglGetCurrentContext().equals(this.mEGLContext)) {
                this.mEGL.eglMakeCurrent(this.mEGLDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
            }

            this.mEGL.eglDestroySurface(this.mEGLDisplay, this.mEGLSurface);
            this.mEGL.eglDestroyContext(this.mEGLDisplay, this.mEGLContext);
        }

        this.mSurface.release();
        this.mEGLDisplay = null;
        this.mEGLContext = null;
        this.mEGLSurface = null;
        this.mEGL = null;
        this.mTextureRender = null;
        this.mSurface = null;
        this.mSurfaceTexture = null;
    }

    private void setup() {
        this.mTextureRender = new TextureRenderer(this.rotateRender);
        this.mTextureRender.surfaceCreated();
        this.mSurfaceTexture = new SurfaceTexture(this.mTextureRender.getTextureId());
        this.mSurfaceTexture.setOnFrameAvailableListener(((SurfaceTexture$OnFrameAvailableListener)this));
        this.mSurface = new Surface(this.mSurfaceTexture);
    }
}

