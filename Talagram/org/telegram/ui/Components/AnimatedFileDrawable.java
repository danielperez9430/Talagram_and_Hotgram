package org.telegram.ui.Components;

import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix$ScaleToFit;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader$TileMode;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import java.io.File;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor$DiscardPolicy;
import java.util.concurrent.TimeUnit;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;

public class AnimatedFileDrawable extends BitmapDrawable implements Animatable {
    class org.telegram.ui.Components.AnimatedFileDrawable$1 implements Runnable {
        org.telegram.ui.Components.AnimatedFileDrawable$1(AnimatedFileDrawable arg1) {
            AnimatedFileDrawable.this = arg1;
            super();
        }

        public void run() {
            // Method was not decompiled
        }
    }

    class org.telegram.ui.Components.AnimatedFileDrawable$2 implements Runnable {
        org.telegram.ui.Components.AnimatedFileDrawable$2(AnimatedFileDrawable arg1) {
            AnimatedFileDrawable.this = arg1;
            super();
        }

        public void run() {
            if(!AnimatedFileDrawable.this.isRecycled) {
                if(!AnimatedFileDrawable.this.decoderCreated && AnimatedFileDrawable.this.nativePtr == 0) {
                    AnimatedFileDrawable.this.nativePtr = AnimatedFileDrawable.createDecoder(AnimatedFileDrawable.this.path.getAbsolutePath(), AnimatedFileDrawable.this.metaData);
                    AnimatedFileDrawable.this.decoderCreated = true;
                }

                try {
                    if(AnimatedFileDrawable.this.backgroundBitmap != null) {
                        goto label_55;
                    }
                }
                catch(Throwable v0) {
                    goto label_70;
                }

                try {
                    AnimatedFileDrawable.this.backgroundBitmap = Bitmap.createBitmap(AnimatedFileDrawable.this.metaData[0], AnimatedFileDrawable.this.metaData[1], Bitmap$Config.ARGB_8888);
                    goto label_38;
                }
                catch(Throwable v0) {
                    try {
                        FileLog.e(v0);
                    label_38:
                        if(AnimatedFileDrawable.this.backgroundShader == null && AnimatedFileDrawable.this.backgroundBitmap != null && AnimatedFileDrawable.this.roundRadius != 0) {
                            AnimatedFileDrawable.this.backgroundShader = new BitmapShader(AnimatedFileDrawable.this.backgroundBitmap, Shader$TileMode.CLAMP, Shader$TileMode.CLAMP);
                        }

                    label_55:
                        if(AnimatedFileDrawable.this.backgroundBitmap == null) {
                            goto label_71;
                        }

                        AnimatedFileDrawable.this.lastFrameDecodeTime = System.currentTimeMillis();
                        AnimatedFileDrawable.getVideoFrame(AnimatedFileDrawable.this.nativePtr, AnimatedFileDrawable.this.backgroundBitmap, AnimatedFileDrawable.this.metaData);
                    }
                    catch(Throwable v0) {
                    label_70:
                        FileLog.e(v0);
                    }
                }
            }

        label_71:
            AndroidUtilities.runOnUIThread(AnimatedFileDrawable.this.uiRunnable);
        }
    }

    private RectF actualDrawRect;
    private boolean applyTransformation;
    private Bitmap backgroundBitmap;
    private BitmapShader backgroundShader;
    private RectF bitmapRect;
    private boolean decodeSingleFrame;
    private boolean decoderCreated;
    private boolean destroyWhenDone;
    private final Rect dstRect;
    private static ScheduledThreadPoolExecutor executor;
    private int invalidateAfter;
    private volatile boolean isRecycled;
    private volatile boolean isRunning;
    private long lastFrameDecodeTime;
    private long lastFrameTime;
    private int lastTimeStamp;
    private Runnable loadFrameRunnable;
    private Runnable loadFrameTask;
    protected final Runnable mInvalidateTask;
    private final Runnable mStartTask;
    private final int[] metaData;
    private volatile long nativePtr;
    private Bitmap nextRenderingBitmap;
    private BitmapShader nextRenderingShader;
    private View parentView;
    private File path;
    private boolean recycleWithSecond;
    private Bitmap renderingBitmap;
    private BitmapShader renderingShader;
    private int roundRadius;
    private RectF roundRect;
    private float scaleX;
    private float scaleY;
    private View secondParentView;
    private Matrix shaderMatrix;
    private boolean singleFrameDecoded;
    private static final Handler uiHandler;
    private Runnable uiRunnable;

    static {
        AnimatedFileDrawable.uiHandler = new Handler(Looper.getMainLooper());
        AnimatedFileDrawable.executor = new ScheduledThreadPoolExecutor(2, new ThreadPoolExecutor$DiscardPolicy());
    }

    public AnimatedFileDrawable(File arg2, boolean arg3) {
        super();
        this.invalidateAfter = 50;
        this.metaData = new int[4];
        this.actualDrawRect = new RectF();
        this.roundRect = new RectF();
        this.bitmapRect = new RectF();
        this.shaderMatrix = new Matrix();
        this.scaleX = 1f;
        this.scaleY = 1f;
        this.dstRect = new Rect();
        this.parentView = null;
        this.secondParentView = null;
        this.mInvalidateTask = new -$$Lambda$AnimatedFileDrawable$D96GYyKDLrUXCvNeQ7iluME9Yq4(this);
        this.uiRunnable = new org.telegram.ui.Components.AnimatedFileDrawable$1(this);
        this.loadFrameRunnable = new org.telegram.ui.Components.AnimatedFileDrawable$2(this);
        this.mStartTask = new -$$Lambda$AnimatedFileDrawable$AmB2znRBjaDHOIPDjH2S8BYovYQ(this);
        this.path = arg2;
        if(arg3) {
            this.nativePtr = AnimatedFileDrawable.createDecoder(arg2.getAbsolutePath(), this.metaData);
            this.decoderCreated = true;
        }
    }

    static boolean access$000(AnimatedFileDrawable arg0) {
        return arg0.destroyWhenDone;
    }

    static long access$100(AnimatedFileDrawable arg2) {
        return arg2.nativePtr;
    }

    static int[] access$1000(AnimatedFileDrawable arg0) {
        return arg0.metaData;
    }

    static long access$102(AnimatedFileDrawable arg0, long arg1) {
        arg0.nativePtr = arg1;
        return arg1;
    }

    static int access$1100(AnimatedFileDrawable arg0) {
        return arg0.lastTimeStamp;
    }

    static int access$1102(AnimatedFileDrawable arg0, int arg1) {
        arg0.lastTimeStamp = arg1;
        return arg1;
    }

    static int access$1202(AnimatedFileDrawable arg0, int arg1) {
        arg0.invalidateAfter = arg1;
        return arg1;
    }

    static View access$1300(AnimatedFileDrawable arg0) {
        return arg0.secondParentView;
    }

    static View access$1400(AnimatedFileDrawable arg0) {
        return arg0.parentView;
    }

    static void access$1500(AnimatedFileDrawable arg0) {
        arg0.scheduleNextGetFrame();
    }

    static boolean access$1600(AnimatedFileDrawable arg0) {
        return arg0.isRecycled;
    }

    static boolean access$1700(AnimatedFileDrawable arg0) {
        return arg0.decoderCreated;
    }

    static boolean access$1702(AnimatedFileDrawable arg0, boolean arg1) {
        arg0.decoderCreated = arg1;
        return arg1;
    }

    static File access$1800(AnimatedFileDrawable arg0) {
        return arg0.path;
    }

    static long access$1900(String arg0, int[] arg1) {
        return AnimatedFileDrawable.createDecoder(arg0, arg1);
    }

    static void access$200(long arg0) {
        AnimatedFileDrawable.destroyDecoder(arg0);
    }

    static int access$2000(AnimatedFileDrawable arg0) {
        return arg0.roundRadius;
    }

    static long access$2102(AnimatedFileDrawable arg0, long arg1) {
        arg0.lastFrameDecodeTime = arg1;
        return arg1;
    }

    static int access$2200(long arg0, Bitmap arg2, int[] arg3) {
        return AnimatedFileDrawable.getVideoFrame(arg0, arg2, arg3);
    }

    static Runnable access$2300(AnimatedFileDrawable arg0) {
        return arg0.uiRunnable;
    }

    static Bitmap access$300(AnimatedFileDrawable arg0) {
        return arg0.renderingBitmap;
    }

    static Bitmap access$302(AnimatedFileDrawable arg0, Bitmap arg1) {
        arg0.renderingBitmap = arg1;
        return arg1;
    }

    static Bitmap access$400(AnimatedFileDrawable arg0) {
        return arg0.backgroundBitmap;
    }

    static Bitmap access$402(AnimatedFileDrawable arg0, Bitmap arg1) {
        arg0.backgroundBitmap = arg1;
        return arg1;
    }

    static boolean access$502(AnimatedFileDrawable arg0, boolean arg1) {
        arg0.singleFrameDecoded = arg1;
        return arg1;
    }

    static Runnable access$602(AnimatedFileDrawable arg0, Runnable arg1) {
        arg0.loadFrameTask = arg1;
        return arg1;
    }

    static Bitmap access$702(AnimatedFileDrawable arg0, Bitmap arg1) {
        arg0.nextRenderingBitmap = arg1;
        return arg1;
    }

    static BitmapShader access$802(AnimatedFileDrawable arg0, BitmapShader arg1) {
        arg0.nextRenderingShader = arg1;
        return arg1;
    }

    static BitmapShader access$900(AnimatedFileDrawable arg0) {
        return arg0.backgroundShader;
    }

    static BitmapShader access$902(AnimatedFileDrawable arg0, BitmapShader arg1) {
        arg0.backgroundShader = arg1;
        return arg1;
    }

    private static native long createDecoder(String arg0, int[] arg1) {
    }

    private static native void destroyDecoder(long arg0) {
    }

    public void draw(Canvas arg13) {
        Matrix$ScaleToFit v6_1;
        RectF v4_1;
        RectF v3_2;
        Matrix v2_2;
        int v2;
        if(this.nativePtr == 0 && (this.decoderCreated) || (this.destroyWhenDone)) {
            return;
        }

        long v0 = System.currentTimeMillis();
        Bitmap v3 = null;
        if(this.isRunning) {
            if(this.renderingBitmap == null && this.nextRenderingBitmap == null) {
                this.scheduleNextGetFrame();
                goto label_47;
            }

            if(Math.abs(v0 - this.lastFrameTime) < (((long)this.invalidateAfter))) {
                goto label_47;
            }

            if(this.nextRenderingBitmap == null) {
                goto label_47;
            }

            goto label_26;
        }
        else {
            if(this.isRunning) {
                goto label_47;
            }

            if(!this.decodeSingleFrame) {
                goto label_47;
            }

            if(Math.abs(v0 - this.lastFrameTime) < (((long)this.invalidateAfter))) {
                goto label_47;
            }

            if(this.nextRenderingBitmap == null) {
                goto label_47;
            }

        label_26:
            this.renderingBitmap = this.nextRenderingBitmap;
            this.renderingShader = this.nextRenderingShader;
            this.nextRenderingBitmap = v3;
            this.nextRenderingShader = ((BitmapShader)v3);
            this.lastFrameTime = v0;
        }

    label_47:
        if(this.renderingBitmap != null) {
            int v3_1 = 270;
            int v4 = 90;
            int v5 = 2;
            if(this.applyTransformation) {
                v2 = this.renderingBitmap.getWidth();
                int v6 = this.renderingBitmap.getHeight();
                if(this.metaData[v5] == v4 || this.metaData[v5] == v3_1) {
                    int v11 = v6;
                    v6 = v2;
                    v2 = v11;
                }

                this.dstRect.set(this.getBounds());
                this.scaleX = (((float)this.dstRect.width())) / (((float)v2));
                this.scaleY = (((float)this.dstRect.height())) / (((float)v6));
                this.applyTransformation = false;
            }

            if(this.roundRadius != 0) {
                float v2_1 = Math.max(this.scaleX, this.scaleY);
                if(this.renderingShader == null) {
                    this.renderingShader = new BitmapShader(this.backgroundBitmap, Shader$TileMode.CLAMP, Shader$TileMode.CLAMP);
                }

                this.getPaint().setShader(this.renderingShader);
                this.roundRect.set(this.dstRect);
                this.shaderMatrix.reset();
                if(Math.abs(this.scaleX - this.scaleY) > 0.00001f) {
                    if(this.metaData[v5] == v4 || this.metaData[v5] == v3_1) {
                        v3_1 = ((int)Math.floor(((double)((((float)this.dstRect.height())) / v2_1))));
                        v4 = this.dstRect.width();
                    }
                    else {
                        v3_1 = ((int)Math.floor(((double)((((float)this.dstRect.width())) / v2_1))));
                        v4 = this.dstRect.height();
                    }

                    v2 = ((int)Math.floor(((double)((((float)v4)) / v2_1))));
                    this.bitmapRect.set(((float)((this.renderingBitmap.getWidth() - v3_1) / v5)), ((float)((this.renderingBitmap.getHeight() - v2) / v5)), ((float)v3_1), ((float)v2));
                    v2_2 = this.shaderMatrix;
                    v3_2 = this.bitmapRect;
                    v4_1 = this.roundRect;
                    v5 = this.metaData[v5];
                    v6_1 = Matrix$ScaleToFit.START;
                }
                else {
                    this.bitmapRect.set(0f, 0f, ((float)this.renderingBitmap.getWidth()), ((float)this.renderingBitmap.getHeight()));
                    v2_2 = this.shaderMatrix;
                    v3_2 = this.bitmapRect;
                    v4_1 = this.roundRect;
                    v5 = this.metaData[v5];
                    v6_1 = Matrix$ScaleToFit.FILL;
                }

                AndroidUtilities.setRectToRect(v2_2, v3_2, v4_1, v5, v6_1);
                this.renderingShader.setLocalMatrix(this.shaderMatrix);
                arg13.drawRoundRect(this.actualDrawRect, ((float)this.roundRadius), ((float)this.roundRadius), this.getPaint());
            }
            else {
                arg13.translate(((float)this.dstRect.left), ((float)this.dstRect.top));
                if(this.metaData[v5] == v4) {
                    arg13.rotate(90f);
                    arg13.translate(0f, ((float)(-this.dstRect.width())));
                }
                else if(this.metaData[v5] == 180) {
                    arg13.rotate(180f);
                    arg13.translate(((float)(-this.dstRect.width())), ((float)(-this.dstRect.height())));
                }
                else if(this.metaData[v5] == v3_1) {
                    arg13.rotate(270f);
                    arg13.translate(((float)(-this.dstRect.height())), 0f);
                }

                arg13.scale(this.scaleX, this.scaleY);
                arg13.drawBitmap(this.renderingBitmap, 0f, 0f, this.getPaint());
            }

            if(!this.isRunning) {
                return;
            }

            v0 = Math.max(1, (((long)this.invalidateAfter)) - (v0 - this.lastFrameTime) - 17);
            AnimatedFileDrawable.uiHandler.removeCallbacks(this.mInvalidateTask);
            AnimatedFileDrawable.uiHandler.postDelayed(this.mInvalidateTask, Math.min(v0, ((long)this.invalidateAfter)));
        }
    }

    protected void finalize() {
        try {
            this.recycle();
        }
        catch(Throwable v0) {
            super.finalize();
            throw v0;
        }

        super.finalize();
    }

    public Bitmap getAnimatedBitmap() {
        if(this.renderingBitmap != null) {
            return this.renderingBitmap;
        }

        if(this.nextRenderingBitmap != null) {
            return this.nextRenderingBitmap;
        }

        return null;
    }

    public int getIntrinsicHeight() {
        int v0;
        if(this.decoderCreated) {
            int v1 = 2;
            if(this.metaData[v1] != 90) {
                if(this.metaData[v1] == 270) {
                }
                else {
                    v0 = this.metaData[1];
                    return v0;
                }
            }

            v0 = this.metaData[0];
        }
        else {
            v0 = AndroidUtilities.dp(100f);
        }

        return v0;
    }

    public int getIntrinsicWidth() {
        int v0;
        if(this.decoderCreated) {
            int v1 = 2;
            if(this.metaData[v1] != 90) {
                if(this.metaData[v1] == 270) {
                }
                else {
                    v0 = this.metaData[0];
                    return v0;
                }
            }

            v0 = this.metaData[1];
        }
        else {
            v0 = AndroidUtilities.dp(100f);
        }

        return v0;
    }

    public int getMinimumHeight() {
        int v0;
        if(this.decoderCreated) {
            int v1 = 2;
            if(this.metaData[v1] != 90) {
                if(this.metaData[v1] == 270) {
                }
                else {
                    v0 = this.metaData[1];
                    return v0;
                }
            }

            v0 = this.metaData[0];
        }
        else {
            v0 = AndroidUtilities.dp(100f);
        }

        return v0;
    }

    public int getMinimumWidth() {
        int v0;
        if(this.decoderCreated) {
            int v1 = 2;
            if(this.metaData[v1] != 90) {
                if(this.metaData[v1] == 270) {
                }
                else {
                    v0 = this.metaData[0];
                    return v0;
                }
            }

            v0 = this.metaData[1];
        }
        else {
            v0 = AndroidUtilities.dp(100f);
        }

        return v0;
    }

    public int getOpacity() {
        return -2;
    }

    public int getOrientation() {
        return this.metaData[2];
    }

    private static native int getVideoFrame(long arg0, Bitmap arg1, int[] arg2) {
    }

    public boolean hasBitmap() {
        boolean v0;
        if(this.nativePtr != 0) {
            if(this.renderingBitmap == null && this.nextRenderingBitmap == null) {
                goto label_9;
            }

            v0 = true;
        }
        else {
        label_9:
            v0 = false;
        }

        return v0;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public static void lambda$new$0(AnimatedFileDrawable arg1) {
        View v0;
        if(arg1.secondParentView != null) {
            v0 = arg1.secondParentView;
            goto label_3;
        }
        else if(arg1.parentView != null) {
            v0 = arg1.parentView;
        label_3:
            v0.invalidate();
        }
    }

    public static void lambda$new$1(AnimatedFileDrawable arg1) {
        View v0;
        if(arg1.secondParentView != null) {
            v0 = arg1.secondParentView;
            goto label_3;
        }
        else if(arg1.parentView != null) {
            v0 = arg1.parentView;
        label_3:
            v0.invalidate();
        }
    }

    public AnimatedFileDrawable makeCopy() {
        AnimatedFileDrawable v0 = new AnimatedFileDrawable(this.path, false);
        v0.metaData[0] = this.metaData[0];
        v0.metaData[1] = this.metaData[1];
        return v0;
    }

    protected void onBoundsChange(Rect arg1) {
        super.onBoundsChange(arg1);
        this.applyTransformation = true;
    }

    public void recycle() {
        if(this.secondParentView != null) {
            this.recycleWithSecond = true;
            return;
        }

        this.isRunning = false;
        this.isRecycled = true;
        if(this.loadFrameTask == null) {
            long v2 = 0;
            if(this.nativePtr != v2) {
                AnimatedFileDrawable.destroyDecoder(this.nativePtr);
                this.nativePtr = v2;
            }

            Bitmap v1 = null;
            if(this.renderingBitmap != null) {
                this.renderingBitmap.recycle();
                this.renderingBitmap = v1;
            }

            if(this.nextRenderingBitmap == null) {
                return;
            }

            this.nextRenderingBitmap.recycle();
            this.nextRenderingBitmap = v1;
        }
        else {
            this.destroyWhenDone = true;
        }
    }

    protected static void runOnUiThread(Runnable arg2) {
        if(Looper.myLooper() == AnimatedFileDrawable.uiHandler.getLooper()) {
            arg2.run();
        }
        else {
            AnimatedFileDrawable.uiHandler.post(arg2);
        }
    }

    private void scheduleNextGetFrame() {
        if(this.loadFrameTask == null) {
            long v2 = 0;
            if(this.nativePtr == v2 && (this.decoderCreated)) {
                return;
            }

            if(this.destroyWhenDone) {
                return;
            }

            if(!this.isRunning) {
                if(!this.decodeSingleFrame) {
                    return;
                }
                else if((this.decodeSingleFrame) && (this.singleFrameDecoded)) {
                    return;
                }
            }

            if(this.lastFrameDecodeTime != v2) {
                v2 = Math.min(((long)this.invalidateAfter), Math.max(v2, (((long)this.invalidateAfter)) - (System.currentTimeMillis() - this.lastFrameDecodeTime)));
            }

            ScheduledThreadPoolExecutor v0 = AnimatedFileDrawable.executor;
            Runnable v1 = this.loadFrameRunnable;
            this.loadFrameTask = v1;
            v0.schedule(v1, v2, TimeUnit.MILLISECONDS);
        }
    }

    public void setActualDrawRect(int arg4, int arg5, int arg6, int arg7) {
        this.actualDrawRect.set(((float)arg4), ((float)arg5), ((float)(arg4 + arg6)), ((float)(arg5 + arg7)));
    }

    public void setAllowDecodeSingleFrame(boolean arg1) {
        this.decodeSingleFrame = arg1;
        if(this.decodeSingleFrame) {
            this.scheduleNextGetFrame();
        }
    }

    public void setParentView(View arg1) {
        this.parentView = arg1;
    }

    public void setRoundRadius(int arg2) {
        this.roundRadius = arg2;
        this.getPaint().setFlags(1);
    }

    public void setSecondParentView(View arg1) {
        this.secondParentView = arg1;
        if(arg1 == null && (this.recycleWithSecond)) {
            this.recycle();
        }
    }

    public void start() {
        if(this.isRunning) {
            return;
        }

        this.isRunning = true;
        this.scheduleNextGetFrame();
        AnimatedFileDrawable.runOnUiThread(this.mStartTask);
    }

    public void stop() {
        this.isRunning = false;
    }
}

