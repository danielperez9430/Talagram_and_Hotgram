package org.linphone.mediastream.video;

import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.opengl.GLSurfaceView$Renderer;
import android.opengl.GLSurfaceView;
import android.view.Surface$OutOfResourcesException;
import android.view.Surface;
import android.view.SurfaceHolder$Callback;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import org.linphone.mediastream.Log;
import org.linphone.mediastream.video.display.OpenGLESDisplay;

public class AndroidVideoWindowImpl {
    class Renderer implements GLSurfaceView$Renderer {
        int height;
        boolean initPending;
        int ptr;
        int width;

        public Renderer() {
            super();
            this.ptr = 0;
            this.initPending = false;
        }

        public void onDrawFrame(GL10 arg3) {
            __monitor_enter(this);
            try {
                if(this.ptr == 0) {
                    __monitor_exit(this);
                    return;
                }

                if(this.initPending) {
                    OpenGLESDisplay.init(this.ptr, this.width, this.height);
                    this.initPending = false;
                }

                OpenGLESDisplay.render(this.ptr);
                __monitor_exit(this);
                return;
            label_18:
                __monitor_exit(this);
            }
            catch(Throwable v3) {
                goto label_18;
            }

            throw v3;
        }

        public void onSurfaceChanged(GL10 arg1, int arg2, int arg3) {
            this.width = arg2;
            this.height = arg3;
            this.initPending = true;
        }

        public void onSurfaceCreated(GL10 arg1, EGLConfig arg2) {
        }

        public void setOpenGLESDisplay(int arg2) {
            __monitor_enter(this);
            try {
                if(this.ptr != 0 && arg2 != this.ptr) {
                    this.initPending = true;
                }

                this.ptr = arg2;
                __monitor_exit(this);
                return;
            label_11:
                __monitor_exit(this);
            }
            catch(Throwable v2) {
                goto label_11;
            }

            throw v2;
        }
    }

    public interface VideoWindowListener {
        void onVideoPreviewSurfaceDestroyed(AndroidVideoWindowImpl arg1);

        void onVideoPreviewSurfaceReady(AndroidVideoWindowImpl arg1, SurfaceView arg2);

        void onVideoRenderingSurfaceDestroyed(AndroidVideoWindowImpl arg1);

        void onVideoRenderingSurfaceReady(AndroidVideoWindowImpl arg1, SurfaceView arg2);
    }

    private Bitmap mBitmap;
    private VideoWindowListener mListener;
    private Surface mSurface;
    private SurfaceView mVideoPreviewView;
    private SurfaceView mVideoRenderingView;
    private Renderer renderer;
    private boolean useGLrendering;

    public AndroidVideoWindowImpl(SurfaceView arg2, SurfaceView arg3) {
        super();
        this.mBitmap = null;
        this.mSurface = null;
        this.mListener = null;
        this.mVideoRenderingView = arg2;
        this.mVideoPreviewView = arg3;
        this.useGLrendering = arg2 instanceof GLSurfaceView;
    }

    public AndroidVideoWindowImpl(SurfaceView arg2, SurfaceView arg3, VideoWindowListener arg4) {
        super();
        this.mBitmap = null;
        this.mSurface = null;
        this.mListener = null;
        this.mVideoRenderingView = arg2;
        this.mVideoPreviewView = arg3;
        this.useGLrendering = arg2 instanceof GLSurfaceView;
        this.mListener = arg4;
        this.init();
    }

    static boolean access$000(AndroidVideoWindowImpl arg0) {
        return arg0.useGLrendering;
    }

    static Bitmap access$102(AndroidVideoWindowImpl arg0, Bitmap arg1) {
        arg0.mBitmap = arg1;
        return arg1;
    }

    static Surface access$202(AndroidVideoWindowImpl arg0, Surface arg1) {
        arg0.mSurface = arg1;
        return arg1;
    }

    static VideoWindowListener access$300(AndroidVideoWindowImpl arg0) {
        return arg0.mListener;
    }

    static SurfaceView access$400(AndroidVideoWindowImpl arg0) {
        return arg0.mVideoRenderingView;
    }

    static SurfaceView access$500(AndroidVideoWindowImpl arg0) {
        return arg0.mVideoPreviewView;
    }

    public Bitmap getBitmap() {
        if(this.useGLrendering) {
            Log.e(new Object[]{"View class does not match Video display filter used (you must use a non-GL View)"});
        }

        return this.mBitmap;
    }

    public Surface getSurface() {
        if(this.useGLrendering) {
            Log.e(new Object[]{"View class does not match Video display filter used (you must use a non-GL View)"});
        }

        return this.mVideoRenderingView.getHolder().getSurface();
    }

    public void init() {
        this.mVideoRenderingView.getHolder().addCallback(new SurfaceHolder$Callback() {
            public void surfaceChanged(SurfaceHolder arg5, int arg6, int arg7, int arg8) {
                Log.i(new Object[]{"Video display surface is being changed."});
                if(!AndroidVideoWindowImpl.this.useGLrendering) {
                    AndroidVideoWindowImpl v0 = AndroidVideoWindowImpl.this;
                    __monitor_enter(v0);
                    try {
                        AndroidVideoWindowImpl.this.mBitmap = Bitmap.createBitmap(arg7, arg8, Bitmap$Config.RGB_565);
                        AndroidVideoWindowImpl.this.mSurface = arg5.getSurface();
                        __monitor_exit(v0);
                        goto label_23;
                    label_21:
                        __monitor_exit(v0);
                    }
                    catch(Throwable v5) {
                        goto label_21;
                    }

                    throw v5;
                }

            label_23:
                if(AndroidVideoWindowImpl.this.mListener != null) {
                    AndroidVideoWindowImpl.this.mListener.onVideoRenderingSurfaceReady(AndroidVideoWindowImpl.this, AndroidVideoWindowImpl.this.mVideoRenderingView);
                }

                Log.w(new Object[]{"Video display surface changed"});
            }

            public void surfaceCreated(SurfaceHolder arg3) {
                Log.w(new Object[]{"Video display surface created"});
            }

            public void surfaceDestroyed(SurfaceHolder arg3) {
                if(!AndroidVideoWindowImpl.this.useGLrendering) {
                    AndroidVideoWindowImpl v3 = AndroidVideoWindowImpl.this;
                    __monitor_enter(v3);
                    try {
                        AndroidVideoWindowImpl.this.mSurface = null;
                        AndroidVideoWindowImpl.this.mBitmap = null;
                        __monitor_exit(v3);
                        goto label_15;
                    label_13:
                        __monitor_exit(v3);
                    }
                    catch(Throwable v0) {
                        goto label_13;
                    }

                    throw v0;
                }

            label_15:
                if(AndroidVideoWindowImpl.this.mListener != null) {
                    AndroidVideoWindowImpl.this.mListener.onVideoRenderingSurfaceDestroyed(AndroidVideoWindowImpl.this);
                }

                Log.d(new Object[]{"Video display surface destroyed"});
            }
        });
        if(this.mVideoPreviewView != null) {
            this.mVideoPreviewView.getHolder().addCallback(new SurfaceHolder$Callback() {
                public void surfaceChanged(SurfaceHolder arg2, int arg3, int arg4, int arg5) {
                    Log.i(new Object[]{"Video preview surface is being changed."});
                    if(AndroidVideoWindowImpl.this.mListener != null) {
                        AndroidVideoWindowImpl.this.mListener.onVideoPreviewSurfaceReady(AndroidVideoWindowImpl.this, AndroidVideoWindowImpl.this.mVideoPreviewView);
                    }

                    Log.w(new Object[]{"Video preview surface changed"});
                }

                public void surfaceCreated(SurfaceHolder arg3) {
                    Log.w(new Object[]{"Video preview surface created"});
                }

                public void surfaceDestroyed(SurfaceHolder arg3) {
                    if(AndroidVideoWindowImpl.this.mListener != null) {
                        AndroidVideoWindowImpl.this.mListener.onVideoPreviewSurfaceDestroyed(AndroidVideoWindowImpl.this);
                    }

                    Log.d(new Object[]{"Video preview surface destroyed"});
                }
            });
        }

        if(this.useGLrendering) {
            this.renderer = new Renderer();
            this.mVideoRenderingView.setRenderer(this.renderer);
            this.mVideoRenderingView.setRenderMode(0);
        }
    }

    public void release() {
    }

    public void requestRender() {
        this.mVideoRenderingView.requestRender();
    }

    public static int rotationToAngle(int arg1) {
        switch(arg1) {
            case 0: {
                return 0;
            }
            case 1: {
                return 90;
            }
            case 2: {
                return 180;
            }
            case 3: {
                return 270;
            }
        }

        return 0;
    }

    public void setListener(VideoWindowListener arg1) {
        this.mListener = arg1;
    }

    public void setOpenGLESDisplay(int arg4) {
        if(!this.useGLrendering) {
            Log.e(new Object[]{"View class does not match Video display filter used (you must use a GL View)"});
        }

        this.renderer.setOpenGLESDisplay(arg4);
    }

    public void update() {
        __monitor_enter(this);
        try {
            if(this.mSurface == null) {
                goto label_17;
            }

            try {
                Canvas v0_3 = this.mSurface.lockCanvas(null);
                v0_3.drawBitmap(this.mBitmap, 0f, 0f, null);
                this.mSurface.unlockCanvasAndPost(v0_3);
            }
            catch(Surface$OutOfResourcesException v0_1) {
                try {
                    v0_1.printStackTrace();
                }
                catch(Throwable v0) {
                label_20:
                    __monitor_exit(this);
                    throw v0;
                }
            }
            catch(IllegalArgumentException v0_2) {
                try {
                    v0_2.printStackTrace();
                }
                catch(Throwable v0) {
                    goto label_20;
                }
            }
        }
        catch(Throwable v0) {
            goto label_20;
        }

    label_17:
        __monitor_exit(this);
    }
}

