package com.google.android.exoplayer2.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.os.Handler$Callback;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.Surface;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.EGLSurfaceTexture;
import com.google.android.exoplayer2.util.Util;

@TargetApi(value=17) public final class DummySurface extends Surface {
    class com.google.android.exoplayer2.video.DummySurface$1 {
    }

    class DummySurfaceThread extends HandlerThread implements Handler$Callback {
        private static final int MSG_INIT = 1;
        private static final int MSG_RELEASE = 2;
        private EGLSurfaceTexture eglSurfaceTexture;
        private Handler handler;
        private Error initError;
        private RuntimeException initException;
        private DummySurface surface;

        public DummySurfaceThread() {
            super("dummySurface");
        }

        public boolean handleMessage(Message arg4) {
            switch(arg4.what) {
                case 1: {
                    goto label_17;
                }
                case 2: {
                    goto label_4;
                }
            }

            return 1;
            try {
            label_17:
                this.initInternal(arg4.arg1);
            }
            catch(Throwable v4) {
            label_52:
                __monitor_enter(this);
                try {
                    this.notify();
                    __monitor_exit(this);
                }
                catch(Throwable v4) {
                    try {
                    label_57:
                        __monitor_exit(this);
                    }
                    catch(Throwable v4) {
                        goto label_57;
                    }

                    throw v4;
                }

                throw v4;
            }
            catch(Error v4_1) {
                try {
                    Log.e("DummySurface", "Failed to initialize dummy surface", ((Throwable)v4_1));
                    this.initError = v4_1;
                }
                catch(Throwable v4) {
                    goto label_52;
                }

                __monitor_enter(this);
                try {
                    this.notify();
                    __monitor_exit(this);
                    return 1;
                label_38:
                    __monitor_exit(this);
                }
                catch(Throwable v4) {
                    goto label_38;
                }

                throw v4;
            }
            catch(RuntimeException v4_2) {
                try {
                    Log.e("DummySurface", "Failed to initialize dummy surface", ((Throwable)v4_2));
                    this.initException = v4_2;
                }
                catch(Throwable v4) {
                    goto label_52;
                }

                __monitor_enter(this);
                try {
                    this.notify();
                    __monitor_exit(this);
                    return 1;
                }
                catch(Throwable v4) {
                    goto label_50;
                }
            }

            __monitor_enter(this);
            try {
                this.notify();
                __monitor_exit(this);
                return 1;
            label_24:
                __monitor_exit(this);
            }
            catch(Throwable v4) {
                goto label_24;
            }

            throw v4;
            try {
                return 1;
            label_50:
                __monitor_exit(this);
            }
            catch(Throwable v4) {
                goto label_50;
            }

            throw v4;
            try {
            label_4:
                this.releaseInternal();
            }
            catch(Throwable v4) {
            }
            catch(Throwable v4) {
                try {
                    Log.e("DummySurface", "Failed to release dummy surface", v4);
                }
                catch(Throwable v4) {
                    this.quit();
                    throw v4;
                }
            }

            this.quit();
            return 1;
        }

        public DummySurface init(int arg4) {
            this.start();
            this.handler = new Handler(this.getLooper(), ((Handler$Callback)this));
            this.eglSurfaceTexture = new EGLSurfaceTexture(this.handler);
            __monitor_enter(this);
            try {
                int v2 = 0;
                this.handler.obtainMessage(1, arg4, 0).sendToTarget();
                while(this.surface == null) {
                    if(this.initException != null) {
                        break;
                    }

                    if(this.initError != null) {
                        break;
                    }

                    try {
                        this.wait();
                    }
                    catch(InterruptedException ) {
                        v2 = 1;
                    }
                }
            }
            catch(Throwable v4) {
                goto label_41;
            }

            try {
                __monitor_exit(this);
                if(v2 != 0) {
                    goto label_27;
                }

                goto label_29;
            }
            catch(Throwable v4) {
                goto label_41;
            }

        label_27:
            Thread.currentThread().interrupt();
        label_29:
            if(this.initException == null) {
                if(this.initError == null) {
                    return Assertions.checkNotNull(this.surface);
                }

                throw this.initError;
            }

            throw this.initException;
            try {
            label_41:
                __monitor_exit(this);
            }
            catch(Throwable v4) {
                goto label_41;
            }

            throw v4;
        }

        private void initInternal(int arg4) {
            Assertions.checkNotNull(this.eglSurfaceTexture);
            this.eglSurfaceTexture.init(arg4);
            SurfaceTexture v1 = this.eglSurfaceTexture.getSurfaceTexture();
            boolean v4 = arg4 != 0 ? true : false;
            this.surface = new DummySurface(this, v1, v4, null);
        }

        public void release() {
            Assertions.checkNotNull(this.handler);
            this.handler.sendEmptyMessage(2);
        }

        private void releaseInternal() {
            Assertions.checkNotNull(this.eglSurfaceTexture);
            this.eglSurfaceTexture.release();
        }
    }

    private static final String EXTENSION_PROTECTED_CONTENT = "EGL_EXT_protected_content";
    private static final String EXTENSION_SURFACELESS_CONTEXT = "EGL_KHR_surfaceless_context";
    private static final String TAG = "DummySurface";
    public final boolean secure;
    private static int secureMode;
    private static boolean secureModeInitialized;
    private final DummySurfaceThread thread;
    private boolean threadReleased;

    private DummySurface(DummySurfaceThread arg1, SurfaceTexture arg2, boolean arg3) {
        super(arg2);
        this.thread = arg1;
        this.secure = arg3;
    }

    DummySurface(DummySurfaceThread arg1, SurfaceTexture arg2, boolean arg3, com.google.android.exoplayer2.video.DummySurface$1 arg4) {
        this(arg1, arg2, arg3);
    }

    private static void assertApiLevel17OrHigher() {
        if(Util.SDK_INT >= 17) {
            return;
        }

        throw new UnsupportedOperationException("Unsupported prior to API level 17");
    }

    @TargetApi(value=24) private static int getSecureModeV24(Context arg4) {
        int v1 = 26;
        if(Util.SDK_INT < v1 && (("samsung".equals(Util.MANUFACTURER)) || ("XT1650".equals(Util.MODEL)))) {
            return 0;
        }

        if(Util.SDK_INT < v1 && !arg4.getPackageManager().hasSystemFeature("android.hardware.vr.high_performance")) {
            return 0;
        }

        String v4 = EGL14.eglQueryString(EGL14.eglGetDisplay(0), 12373);
        if(v4 == null) {
            return 0;
        }

        if(!v4.contains("EGL_EXT_protected_content")) {
            return 0;
        }

        int v4_1 = v4.contains("EGL_KHR_surfaceless_context") ? 1 : 2;
        return v4_1;
    }

    public static boolean isSecureSupported(Context arg5) {
        boolean v2;
        Class v0 = DummySurface.class;
        __monitor_enter(v0);
        try {
            v2 = true;
            if(!DummySurface.secureModeInitialized) {
                int v5_1 = Util.SDK_INT < 24 ? 0 : DummySurface.getSecureModeV24(arg5);
                DummySurface.secureMode = v5_1;
                DummySurface.secureModeInitialized = true;
            }

            if(DummySurface.secureMode == 0) {
                goto label_17;
            }
        }
        catch(Throwable v5) {
            __monitor_exit(v0);
            throw v5;
        }

        goto label_18;
    label_17:
        v2 = false;
    label_18:
        __monitor_exit(v0);
        return v2;
    }

    public static DummySurface newInstanceV17(Context arg1, boolean arg2) {
        DummySurface.assertApiLevel17OrHigher();
        int v0 = 0;
        boolean v1 = !arg2 || (DummySurface.isSecureSupported(arg1)) ? true : false;
        Assertions.checkState(v1);
        DummySurfaceThread v1_1 = new DummySurfaceThread();
        if(arg2) {
            v0 = DummySurface.secureMode;
        }

        return v1_1.init(v0);
    }

    public void release() {
        super.release();
        DummySurfaceThread v0 = this.thread;
        __monitor_enter(v0);
        try {
            if(!this.threadReleased) {
                this.thread.release();
                this.threadReleased = true;
            }

            __monitor_exit(v0);
            return;
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_12;
        }

        throw v1;
    }
}

