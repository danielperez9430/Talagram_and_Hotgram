package org.telegram.messenger.video;

import android.annotation.TargetApi;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.view.Surface;

@TargetApi(value=17) public class InputSurface {
    private static final int EGL_OPENGL_ES2_BIT = 4;
    private static final int EGL_RECORDABLE_ANDROID = 12610;
    private EGLContext mEGLContext;
    private EGLDisplay mEGLDisplay;
    private EGLSurface mEGLSurface;
    private Surface mSurface;

    public InputSurface(Surface arg1) {
        super();
        if(arg1 != null) {
            this.mSurface = arg1;
            this.eglSetup();
            return;
        }

        throw new NullPointerException();
    }

    private void checkEglError(String arg3) {
        int v3;
        for(v3 = 0; EGL14.eglGetError() != 12288; v3 = 1) {
        }

        if(v3 == 0) {
            return;
        }

        throw new RuntimeException("EGL error encountered (see log)");
    }

    private void eglSetup() {
        this.mEGLDisplay = EGL14.eglGetDisplay(0);
        if(this.mEGLDisplay != EGL14.EGL_NO_DISPLAY) {
            int[] v1 = new int[2];
            if(EGL14.eglInitialize(this.mEGLDisplay, v1, 0, v1, 1)) {
                int[] v5 = new int[]{12324, 8, 12323, 8, 12322, 8, 12352, 4, 12610, 1, 12344};
                EGLConfig[] v1_1 = new EGLConfig[1];
                if(EGL14.eglChooseConfig(this.mEGLDisplay, v5, 0, v1_1, 0, v1_1.length, new int[1], 0)) {
                    this.mEGLContext = EGL14.eglCreateContext(this.mEGLDisplay, v1_1[0], EGL14.EGL_NO_CONTEXT, new int[]{12440, 2, 12344}, 0);
                    this.checkEglError("eglCreateContext");
                    if(this.mEGLContext != null) {
                        this.mEGLSurface = EGL14.eglCreateWindowSurface(this.mEGLDisplay, v1_1[0], this.mSurface, new int[]{12344}, 0);
                        this.checkEglError("eglCreateWindowSurface");
                        if(this.mEGLSurface != null) {
                            return;
                        }

                        throw new RuntimeException("surface was null");
                    }

                    throw new RuntimeException("null context");
                }

                throw new RuntimeException("unable to find RGB888+recordable ES2 EGL config");
            }

            this.mEGLDisplay = null;
            throw new RuntimeException("unable to initialize EGL14");
        }

        throw new RuntimeException("unable to get EGL14 display");
    }

    public Surface getSurface() {
        return this.mSurface;
    }

    public void makeCurrent() {
        if(EGL14.eglMakeCurrent(this.mEGLDisplay, this.mEGLSurface, this.mEGLSurface, this.mEGLContext)) {
            return;
        }

        throw new RuntimeException("eglMakeCurrent failed");
    }

    public void release() {
        if(EGL14.eglGetCurrentContext().equals(this.mEGLContext)) {
            EGL14.eglMakeCurrent(this.mEGLDisplay, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
        }

        EGL14.eglDestroySurface(this.mEGLDisplay, this.mEGLSurface);
        EGL14.eglDestroyContext(this.mEGLDisplay, this.mEGLContext);
        this.mSurface.release();
        this.mEGLDisplay = null;
        this.mEGLContext = null;
        this.mEGLSurface = null;
        this.mSurface = null;
    }

    @TargetApi(value=18) public void setPresentationTime(long arg3) {
        EGLExt.eglPresentationTimeANDROID(this.mEGLDisplay, this.mEGLSurface, arg3);
    }

    public boolean swapBuffers() {
        return EGL14.eglSwapBuffers(this.mEGLDisplay, this.mEGLSurface);
    }
}

