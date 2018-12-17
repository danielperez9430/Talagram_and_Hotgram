package org.telegram.ui.Components.Paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.TextureView$SurfaceTextureListener;
import android.view.TextureView;
import java.util.concurrent.CountDownLatch;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLog;
import org.telegram.ui.Components.Size;

public class RenderView extends TextureView {
    class CanvasInternal extends DispatchQueue {
        class org.telegram.ui.Components.Paint.RenderView$CanvasInternal$1 implements Runnable {
            org.telegram.ui.Components.Paint.RenderView$CanvasInternal$1(CanvasInternal arg1) {
                this.this$1 = arg1;
                super();
            }

            public void run() {
                if(this.this$1.initialized) {
                    if(this.this$1.this$0.shuttingDown) {
                    }
                    else {
                        this.this$1.setCurrentContext();
                        GLES20.glBindFramebuffer(36160, 0);
                        GLES20.glViewport(0, 0, this.this$1.bufferWidth, this.this$1.bufferHeight);
                        GLES20.glClearColor(0f, 0f, 0f, 1f);
                        GLES20.glClear(16384);
                        this.this$1.this$0.painting.render();
                        GLES20.glBlendFunc(1, 771);
                        this.this$1.egl10.eglSwapBuffers(this.this$1.eglDisplay, this.this$1.eglSurface);
                        if(!this.this$1.ready) {
                            this.this$1.this$0.queue.postRunnable(new Runnable() {
                                public void run() {
                                    this.this$2.this$1.ready = true;
                                }
                            }, 200);
                        }
                    }
                }
            }
        }

        private final int EGL_CONTEXT_CLIENT_VERSION;
        private final int EGL_OPENGL_ES2_BIT;
        private int bufferHeight;
        private int bufferWidth;
        private Runnable drawRunnable;
        private EGL10 egl10;
        private EGLConfig eglConfig;
        private EGLContext eglContext;
        private EGLDisplay eglDisplay;
        private EGLSurface eglSurface;
        private boolean initialized;
        private long lastRenderCallTime;
        private boolean ready;
        private Runnable scheduledRunnable;
        private SurfaceTexture surfaceTexture;

        public CanvasInternal(RenderView arg1, SurfaceTexture arg2) {
            RenderView.this = arg1;
            super("CanvasInternal");
            this.EGL_CONTEXT_CLIENT_VERSION = 12440;
            this.EGL_OPENGL_ES2_BIT = 4;
            this.drawRunnable = new org.telegram.ui.Components.Paint.RenderView$CanvasInternal$1(this);
            this.surfaceTexture = arg2;
        }

        static boolean access$1300(CanvasInternal arg0) {
            return arg0.setCurrentContext();
        }

        static EGLDisplay access$1400(CanvasInternal arg0) {
            return arg0.eglDisplay;
        }

        static EGLSurface access$1500(CanvasInternal arg0) {
            return arg0.eglSurface;
        }

        static EGL10 access$1600(CanvasInternal arg0) {
            return arg0.egl10;
        }

        static Runnable access$1700(CanvasInternal arg0) {
            return arg0.drawRunnable;
        }

        static Runnable access$1802(CanvasInternal arg0, Runnable arg1) {
            arg0.scheduledRunnable = arg1;
            return arg1;
        }

        static boolean access$600(CanvasInternal arg0) {
            return arg0.initialized;
        }

        static boolean access$700(CanvasInternal arg0) {
            return arg0.ready;
        }

        static boolean access$702(CanvasInternal arg0, boolean arg1) {
            arg0.ready = arg1;
            return arg1;
        }

        static int access$800(CanvasInternal arg0) {
            return arg0.bufferWidth;
        }

        static int access$900(CanvasInternal arg0) {
            return arg0.bufferHeight;
        }

        private void checkBitmap() {
            Size v0 = RenderView.this.painting.getSize();
            if((((float)RenderView.this.bitmap.getWidth())) != v0.width || (((float)RenderView.this.bitmap.getHeight())) != v0.height || RenderView.this.orientation != 0) {
                float v1 = ((float)RenderView.this.bitmap.getWidth());
                if(RenderView.this.orientation % 360 == 90 || RenderView.this.orientation % 360 == 270) {
                    v1 = ((float)RenderView.this.bitmap.getHeight());
                }

                RenderView.this.bitmap = this.createBitmap(RenderView.this.bitmap, v0.width / v1);
                RenderView.this.orientation = 0;
                RenderView.this.transformedBitmap = true;
            }
        }

        private Bitmap createBitmap(Bitmap arg8, float arg9) {
            Matrix v5 = new Matrix();
            v5.setScale(arg9, arg9);
            v5.postRotate(((float)RenderView.this.orientation));
            return Bitmap.createBitmap(arg8, 0, 0, arg8.getWidth(), arg8.getHeight(), v5, true);
        }

        public void finish() {
            EGLSurface v1 = null;
            if(this.eglSurface != null) {
                this.egl10.eglMakeCurrent(this.eglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                this.egl10.eglDestroySurface(this.eglDisplay, this.eglSurface);
                this.eglSurface = v1;
            }

            if(this.eglContext != null) {
                this.egl10.eglDestroyContext(this.eglDisplay, this.eglContext);
                this.eglContext = ((EGLContext)v1);
            }

            if(this.eglDisplay != null) {
                this.egl10.eglTerminate(this.eglDisplay);
                this.eglDisplay = ((EGLDisplay)v1);
            }
        }

        public Bitmap getTexture() {
            if(!this.initialized) {
                return null;
            }

            CountDownLatch v0 = new CountDownLatch(1);
            Bitmap[] v1 = new Bitmap[1];
            try {
                this.postRunnable(new Runnable(v1, v0) {
                    public void run() {
                        this.val$object[0] = this.this$1.this$0.painting.getPaintingData(new RectF(0f, 0f, this.this$1.this$0.painting.getSize().width, this.this$1.this$0.painting.getSize().height), false).bitmap;
                        this.val$countDownLatch.countDown();
                    }
                });
                v0.await();
            }
            catch(Exception v0_1) {
                FileLog.e(((Throwable)v0_1));
            }

            return v1[0];
        }

        private boolean initGL() {
            this.egl10 = EGLContext.getEGL();
            this.eglDisplay = this.egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            if(this.eglDisplay == EGL10.EGL_NO_DISPLAY) {
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.e("eglGetDisplay failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
                }

                this.finish();
                return 0;
            }

            if(!this.egl10.eglInitialize(this.eglDisplay, new int[2])) {
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.e("eglInitialize failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
                }

                this.finish();
                return 0;
            }

            int[] v1 = new int[1];
            EGLConfig[] v9 = new EGLConfig[1];
            if(!this.egl10.eglChooseConfig(this.eglDisplay, new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12326, 0, 12344}, v9, 1, v1)) {
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.e("eglChooseConfig failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
                }

                this.finish();
                return 0;
            }

            if(v1[0] > 0) {
                this.eglConfig = v9[0];
                this.eglContext = this.egl10.eglCreateContext(this.eglDisplay, this.eglConfig, EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
                if(this.eglContext == null) {
                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.e("eglCreateContext failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
                    }

                    this.finish();
                    return 0;
                }

                if((this.surfaceTexture instanceof SurfaceTexture)) {
                    this.eglSurface = this.egl10.eglCreateWindowSurface(this.eglDisplay, this.eglConfig, this.surfaceTexture, null);
                    if(this.eglSurface != null) {
                        if(this.eglSurface == EGL10.EGL_NO_SURFACE) {
                        }
                        else if(!this.egl10.eglMakeCurrent(this.eglDisplay, this.eglSurface, this.eglSurface, this.eglContext)) {
                            if(BuildVars.LOGS_ENABLED) {
                                FileLog.e("eglMakeCurrent failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
                            }

                            this.finish();
                            return 0;
                        }
                        else {
                            GLES20.glEnable(3042);
                            GLES20.glDisable(3024);
                            GLES20.glDisable(2960);
                            GLES20.glDisable(2929);
                            RenderView.this.painting.setupShaders();
                            this.checkBitmap();
                            RenderView.this.painting.setBitmap(RenderView.this.bitmap);
                            Utils.HasGLError();
                            return 1;
                        }
                    }

                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.e("createWindowSurface failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
                    }

                    this.finish();
                    return 0;
                }

                this.finish();
                return 0;
            }

            if(BuildVars.LOGS_ENABLED) {
                FileLog.e("eglConfig not initialized");
            }

            this.finish();
            return 0;
        }

        public void requestRender() {
            this.postRunnable(new Runnable() {
                public void run() {
                    this.this$1.drawRunnable.run();
                }
            });
        }

        public void run() {
            if(RenderView.this.bitmap != null) {
                if(RenderView.this.bitmap.isRecycled()) {
                }
                else {
                    this.initialized = this.initGL();
                    super.run();
                }
            }
        }

        public void scheduleRedraw() {
            if(this.scheduledRunnable != null) {
                this.cancelRunnable(this.scheduledRunnable);
                this.scheduledRunnable = null;
            }

            this.scheduledRunnable = new Runnable() {
                public void run() {
                    this.this$1.scheduledRunnable = null;
                    this.this$1.drawRunnable.run();
                }
            };
            this.postRunnable(this.scheduledRunnable, 1);
        }

        public void setBufferSize(int arg1, int arg2) {
            this.bufferWidth = arg1;
            this.bufferHeight = arg2;
        }

        private boolean setCurrentContext() {
            if(!this.initialized) {
                return 0;
            }

            if((!this.eglContext.equals(this.egl10.eglGetCurrentContext()) || !this.eglSurface.equals(this.egl10.eglGetCurrentSurface(12377))) && !this.egl10.eglMakeCurrent(this.eglDisplay, this.eglSurface, this.eglSurface, this.eglContext)) {
                return 0;
            }

            return 1;
        }

        public void shutdown() {
            this.postRunnable(new Runnable() {
                public void run() {
                    this.this$1.finish();
                    Looper v0 = Looper.myLooper();
                    if(v0 != null) {
                        v0.quit();
                    }
                }
            });
        }
    }

    public interface RenderViewDelegate {
        void onBeganDrawing();

        void onFinishedDrawing(boolean arg1);

        boolean shouldDraw();
    }

    private Bitmap bitmap;
    private Brush brush;
    private int color;
    private RenderViewDelegate delegate;
    private Input input;
    private CanvasInternal internal;
    private int orientation;
    private Painting painting;
    private DispatchQueue queue;
    private boolean shuttingDown;
    private boolean transformedBitmap;
    private UndoStore undoStore;
    private float weight;

    public RenderView(Context arg1, Painting arg2, Bitmap arg3, int arg4) {
        super(arg1);
        this.bitmap = arg3;
        this.orientation = arg4;
        this.painting = arg2;
        this.painting.setRenderView(this);
        this.setSurfaceTextureListener(new TextureView$SurfaceTextureListener() {
            public void onSurfaceTextureAvailable(SurfaceTexture arg4, int arg5, int arg6) {
                if(arg4 != null) {
                    if(RenderView.access$000(RenderView.this) != null) {
                    }
                    else {
                        RenderView.access$002(RenderView.this, new CanvasInternal(RenderView.this, arg4));
                        RenderView.access$000(RenderView.this).setBufferSize(arg5, arg6);
                        RenderView.access$100(RenderView.this);
                        RenderView.access$000(RenderView.this).requestRender();
                        if(RenderView.access$200(RenderView.this).isPaused()) {
                            RenderView.access$200(RenderView.this).onResume();
                        }
                    }
                }
            }

            public boolean onSurfaceTextureDestroyed(SurfaceTexture arg3) {
                if(RenderView.access$000(RenderView.this) == null) {
                    return 1;
                }

                if(!RenderView.access$300(RenderView.this)) {
                    RenderView.access$200(RenderView.this).onPause(new Runnable() {
                        public void run() {
                            RenderView.access$000(this.this$1.this$0).shutdown();
                            RenderView.access$002(this.this$1.this$0, null);
                        }
                    });
                }

                return 1;
            }

            public void onSurfaceTextureSizeChanged(SurfaceTexture arg1, int arg2, int arg3) {
                if(RenderView.access$000(RenderView.this) == null) {
                    return;
                }

                RenderView.access$000(RenderView.this).setBufferSize(arg2, arg3);
                RenderView.access$100(RenderView.this);
                RenderView.access$000(RenderView.this).requestRender();
                RenderView.access$000(RenderView.this).postRunnable(new Runnable() {
                    public void run() {
                        if(RenderView.access$000(this.this$1.this$0) != null) {
                            RenderView.access$000(this.this$1.this$0).requestRender();
                        }
                    }
                });
            }

            public void onSurfaceTextureUpdated(SurfaceTexture arg1) {
            }
        });
        this.input = new Input(this);
        this.painting.setDelegate(new PaintingDelegate() {
            public void contentChanged(RectF arg1) {
                if(RenderView.access$000(RenderView.this) != null) {
                    RenderView.access$000(RenderView.this).scheduleRedraw();
                }
            }

            public DispatchQueue requestDispatchQueue() {
                return RenderView.access$500(RenderView.this);
            }

            public UndoStore requestUndoStore() {
                return RenderView.access$400(RenderView.this);
            }

            public void strokeCommited() {
            }
        });
    }

    static CanvasInternal access$000(RenderView arg0) {
        return arg0.internal;
    }

    static CanvasInternal access$002(RenderView arg0, CanvasInternal arg1) {
        arg0.internal = arg1;
        return arg1;
    }

    static void access$100(RenderView arg0) {
        arg0.updateTransform();
    }

    static boolean access$1000(RenderView arg0) {
        return arg0.transformedBitmap;
    }

    static boolean access$1002(RenderView arg0, boolean arg1) {
        arg0.transformedBitmap = arg1;
        return arg1;
    }

    static Bitmap access$1100(RenderView arg0) {
        return arg0.bitmap;
    }

    static Bitmap access$1102(RenderView arg0, Bitmap arg1) {
        arg0.bitmap = arg1;
        return arg1;
    }

    static int access$1200(RenderView arg0) {
        return arg0.orientation;
    }

    static int access$1202(RenderView arg0, int arg1) {
        arg0.orientation = arg1;
        return arg1;
    }

    static Painting access$200(RenderView arg0) {
        return arg0.painting;
    }

    static boolean access$300(RenderView arg0) {
        return arg0.shuttingDown;
    }

    static UndoStore access$400(RenderView arg0) {
        return arg0.undoStore;
    }

    static DispatchQueue access$500(RenderView arg0) {
        return arg0.queue;
    }

    private float brushWeightForSize(float arg4) {
        float v0 = this.painting.getSize().width;
        return 0.003906f * v0 + v0 * 0.043945f * arg4;
    }

    public Brush getCurrentBrush() {
        return this.brush;
    }

    public int getCurrentColor() {
        return this.color;
    }

    public float getCurrentWeight() {
        return this.weight;
    }

    public Painting getPainting() {
        return this.painting;
    }

    public Bitmap getResultBitmap() {
        Bitmap v0 = this.internal != null ? this.internal.getTexture() : null;
        return v0;
    }

    public void onBeganDrawing() {
        if(this.delegate != null) {
            this.delegate.onBeganDrawing();
        }
    }

    public void onFinishedDrawing(boolean arg2) {
        if(this.delegate != null) {
            this.delegate.onFinishedDrawing(arg2);
        }
    }

    protected void onMeasure(int arg1, int arg2) {
        super.onMeasure(arg1, arg2);
    }

    public boolean onTouchEvent(MotionEvent arg3) {
        if(arg3.getPointerCount() > 1) {
            return 0;
        }

        if(this.internal != null && (CanvasInternal.access$600(this.internal))) {
            if(!CanvasInternal.access$700(this.internal)) {
            }
            else {
                this.input.process(arg3);
            }
        }

        return 1;
    }

    public void performInContext(Runnable arg3) {
        if(this.internal == null) {
            return;
        }

        this.internal.postRunnable(new Runnable(arg3) {
            public void run() {
                if(RenderView.this.internal != null) {
                    if(!CanvasInternal.access$600(RenderView.this.internal)) {
                    }
                    else {
                        CanvasInternal.access$1300(RenderView.this.internal);
                        this.val$action.run();
                    }
                }
            }
        });
    }

    public void setBrush(Brush arg2) {
        Painting v0 = this.painting;
        this.brush = arg2;
        v0.setBrush(arg2);
    }

    public void setBrushSize(float arg1) {
        this.weight = this.brushWeightForSize(arg1);
    }

    public void setColor(int arg1) {
        this.color = arg1;
    }

    public void setDelegate(RenderViewDelegate arg1) {
        this.delegate = arg1;
    }

    public void setQueue(DispatchQueue arg1) {
        this.queue = arg1;
    }

    public void setUndoStore(UndoStore arg1) {
        this.undoStore = arg1;
    }

    public boolean shouldDraw() {
        boolean v0 = this.delegate == null || (this.delegate.shouldDraw()) ? true : false;
        return v0;
    }

    public void shutdown() {
        this.shuttingDown = true;
        if(this.internal != null) {
            this.performInContext(new Runnable() {
                public void run() {
                    RenderView.this.painting.cleanResources(RenderView.this.transformedBitmap);
                    RenderView.this.internal.shutdown();
                    RenderView.this.internal = null;
                }
            });
        }

        this.setVisibility(8);
    }

    private void updateTransform() {
        Matrix v0 = new Matrix();
        float v1 = this.painting != null ? (((float)this.getWidth())) / this.painting.getSize().width : 1f;
        if(v1 <= 0f) {
            v1 = 1f;
        }

        Size v2 = this.getPainting().getSize();
        v0.preTranslate((((float)this.getWidth())) / 2f, (((float)this.getHeight())) / 2f);
        v0.preScale(v1, -v1);
        v0.preTranslate(-v2.width / 2f, -v2.height / 2f);
        this.input.setMatrix(v0);
        this.painting.setRenderProjection(GLMatrix.MultiplyMat4f(GLMatrix.LoadOrtho(0f, ((float)CanvasInternal.access$800(this.internal)), 0f, ((float)CanvasInternal.access$900(this.internal)), -1f, 1f), GLMatrix.LoadGraphicsMatrix(v0)));
    }
}

