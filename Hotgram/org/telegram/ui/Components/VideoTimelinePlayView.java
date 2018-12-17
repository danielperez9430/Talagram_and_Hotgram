package org.telegram.ui.Components;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;

@TargetApi(value=10) public class VideoTimelinePlayView extends View {
    public interface VideoTimelineViewDelegate {
        void didStartDragging();

        void didStopDragging();

        void onLeftProgressChanged(float arg1);

        void onPlayProgressChanged(float arg1);

        void onRightProgressChanged(float arg1);
    }

    private float bufferedProgress;
    private AsyncTask currentTask;
    private VideoTimelineViewDelegate delegate;
    private Drawable drawableLeft;
    private Drawable drawableRight;
    private int frameHeight;
    private long frameTimeOffset;
    private int frameWidth;
    private ArrayList frames;
    private int framesToLoad;
    private boolean isRoundFrames;
    private int lastWidth;
    private float maxProgressDiff;
    private MediaMetadataRetriever mediaMetadataRetriever;
    private float minProgressDiff;
    private Paint paint;
    private Paint paint2;
    private float playProgress;
    private float pressDx;
    private boolean pressedLeft;
    private boolean pressedPlay;
    private boolean pressedRight;
    private float progressLeft;
    private float progressRight;
    private Rect rect1;
    private Rect rect2;
    private RectF rect3;
    private static final Object sync;
    private long videoLength;

    static {
        VideoTimelinePlayView.sync = new Object();
    }

    public VideoTimelinePlayView(Context arg5) {
        super(arg5);
        this.progressRight = 1f;
        this.playProgress = 0.5f;
        this.bufferedProgress = 0.5f;
        this.frames = new ArrayList();
        this.maxProgressDiff = 1f;
        this.minProgressDiff = 0f;
        this.rect3 = new RectF();
        this.paint = new Paint(1);
        this.paint.setColor(-1);
        this.paint2 = new Paint();
        this.paint2.setColor(2130706432);
        this.drawableLeft = arg5.getResources().getDrawable(2131231702);
        this.drawableLeft.setColorFilter(new PorterDuffColorFilter(-16777216, PorterDuff$Mode.MULTIPLY));
        this.drawableRight = arg5.getResources().getDrawable(2131231703);
        this.drawableRight.setColorFilter(new PorterDuffColorFilter(-16777216, PorterDuff$Mode.MULTIPLY));
    }

    static long access$000(VideoTimelinePlayView arg2) {
        return arg2.frameTimeOffset;
    }

    static MediaMetadataRetriever access$100(VideoTimelinePlayView arg0) {
        return arg0.mediaMetadataRetriever;
    }

    static int access$200(VideoTimelinePlayView arg0) {
        return arg0.frameWidth;
    }

    static int access$300(VideoTimelinePlayView arg0) {
        return arg0.frameHeight;
    }

    static ArrayList access$400(VideoTimelinePlayView arg0) {
        return arg0.frames;
    }

    static int access$500(VideoTimelinePlayView arg0) {
        return arg0.framesToLoad;
    }

    static void access$600(VideoTimelinePlayView arg0, int arg1) {
        arg0.reloadFrames(arg1);
    }

    public void clearFrames() {
        int v0;
        for(v0 = 0; v0 < this.frames.size(); ++v0) {
            Object v1 = this.frames.get(v0);
            if(v1 != null) {
                ((Bitmap)v1).recycle();
            }
        }

        this.frames.clear();
        if(this.currentTask != null) {
            this.currentTask.cancel(true);
            this.currentTask = null;
        }

        this.invalidate();
    }

    public void destroy() {
        int v0_1;
        Object v0 = VideoTimelinePlayView.sync;
        __monitor_enter(v0);
        MediaMetadataRetriever v1 = null;
        try {
            if(this.mediaMetadataRetriever == null) {
                goto label_13;
            }

            this.mediaMetadataRetriever.release();
            this.mediaMetadataRetriever = v1;
            goto label_13;
        }
        catch(Throwable v1_1) {
        }
        catch(Exception v2) {
            try {
                FileLog.e(((Throwable)v2));
            label_13:
                __monitor_exit(v0);
                v0_1 = 0;
            }
            catch(Throwable v1_1) {
            label_10:
                try {
                    __monitor_exit(v0);
                }
                catch(Throwable v1_1) {
                    goto label_10;
                }

                throw v1_1;
            }
        }

        while(v0_1 < this.frames.size()) {
            Object v2_1 = this.frames.get(v0_1);
            if(v2_1 != null) {
                ((Bitmap)v2_1).recycle();
            }

            ++v0_1;
        }

        this.frames.clear();
        if(this.currentTask != null) {
            this.currentTask.cancel(true);
            this.currentTask = ((AsyncTask)v1);
        }
    }

    public float getLeftProgress() {
        return this.progressLeft;
    }

    public float getProgress() {
        return this.playProgress;
    }

    public float getRightProgress() {
        return this.progressRight;
    }

    public boolean isDragging() {
        return this.pressedPlay;
    }

    protected void onDraw(Canvas arg18) {
        int v1;
        VideoTimelinePlayView v0 = this;
        Canvas v7 = arg18;
        int v8 = this.getMeasuredWidth() - AndroidUtilities.dp(36f);
        float v9 = ((float)v8);
        float v10 = 16f;
        int v11 = (((int)(v0.progressLeft * v9))) + AndroidUtilities.dp(v10);
        int v12 = (((int)(v0.progressRight * v9))) + AndroidUtilities.dp(v10);
        arg18.save();
        float v4 = 48f;
        v7.clipRect(AndroidUtilities.dp(v10), AndroidUtilities.dp(4f), AndroidUtilities.dp(20f) + v8, AndroidUtilities.dp(v4));
        float v2 = 6f;
        int v3 = 0;
        if(!v0.frames.isEmpty() || v0.currentTask != null) {
            v1 = 0;
            while(v3 < v0.frames.size()) {
                Object v5 = v0.frames.get(v3);
                if(v5 != null) {
                    int v6 = AndroidUtilities.dp(v10);
                    int v14 = v0.isRoundFrames ? v0.frameWidth / 2 : v0.frameWidth;
                    v6 += v14 * v1;
                    v14 = AndroidUtilities.dp(v2);
                    if(v0.isRoundFrames) {
                        v0.rect2.set(v6, v14, v6 + AndroidUtilities.dp(28f), v14 + AndroidUtilities.dp(28f));
                        v7.drawBitmap(((Bitmap)v5), v0.rect1, v0.rect2, null);
                        goto label_73;
                    }

                    v7.drawBitmap(((Bitmap)v5), ((float)v6), ((float)v14), null);
                }

            label_73:
                ++v1;
                ++v3;
                v10 = 16f;
            }
        }
        else {
            v0.reloadFrames(0);
        }

        v1 = AndroidUtilities.dp(v2);
        int v10_1 = AndroidUtilities.dp(v4);
        float v13 = ((float)v1);
        float v14_1 = ((float)v11);
        arg18.drawRect(((float)AndroidUtilities.dp(16f)), v13, v14_1, ((float)AndroidUtilities.dp(46f)), v0.paint2);
        arg18.drawRect(((float)(AndroidUtilities.dp(4f) + v12)), v13, ((float)(AndroidUtilities.dp(16f) + v8 + AndroidUtilities.dp(4f))), ((float)AndroidUtilities.dp(46f)), v0.paint2);
        float v15 = ((float)v10_1);
        arg18.drawRect(v14_1, ((float)AndroidUtilities.dp(4f)), ((float)(AndroidUtilities.dp(2f) + v11)), v15, v0.paint);
        arg18.drawRect(((float)(AndroidUtilities.dp(2f) + v12)), ((float)AndroidUtilities.dp(4f)), ((float)(AndroidUtilities.dp(4f) + v12)), v15, v0.paint);
        arg18.drawRect(((float)(AndroidUtilities.dp(2f) + v11)), ((float)AndroidUtilities.dp(4f)), ((float)(AndroidUtilities.dp(4f) + v12)), v13, v0.paint);
        arg18.drawRect(((float)(AndroidUtilities.dp(2f) + v11)), ((float)(v10_1 - AndroidUtilities.dp(2f))), ((float)(AndroidUtilities.dp(4f) + v12)), v15, v0.paint);
        arg18.restore();
        v0.rect3.set(((float)(v11 - AndroidUtilities.dp(8f))), ((float)AndroidUtilities.dp(4f)), ((float)(AndroidUtilities.dp(2f) + v11)), v15);
        v7.drawRoundRect(v0.rect3, ((float)AndroidUtilities.dp(2f)), ((float)AndroidUtilities.dp(2f)), v0.paint);
        v0.drawableLeft.setBounds(v11 - AndroidUtilities.dp(8f), AndroidUtilities.dp(4f) + (AndroidUtilities.dp(44f) - AndroidUtilities.dp(18f)) / 2, v11 + AndroidUtilities.dp(2f), (AndroidUtilities.dp(44f) - AndroidUtilities.dp(18f)) / 2 + AndroidUtilities.dp(22f));
        v0.drawableLeft.draw(v7);
        v0.rect3.set(((float)(AndroidUtilities.dp(2f) + v12)), ((float)AndroidUtilities.dp(4f)), ((float)(AndroidUtilities.dp(12f) + v12)), v15);
        v7.drawRoundRect(v0.rect3, ((float)AndroidUtilities.dp(2f)), ((float)AndroidUtilities.dp(2f)), v0.paint);
        v0.drawableRight.setBounds(AndroidUtilities.dp(2f) + v12, AndroidUtilities.dp(4f) + (AndroidUtilities.dp(44f) - AndroidUtilities.dp(18f)) / 2, v12 + AndroidUtilities.dp(12f), (AndroidUtilities.dp(44f) - AndroidUtilities.dp(18f)) / 2 + AndroidUtilities.dp(22f));
        v0.drawableRight.draw(v7);
        float v1_1 = (((float)AndroidUtilities.dp(18f))) + v9 * (v0.progressLeft + (v0.progressRight - v0.progressLeft) * v0.playProgress);
        v0.rect3.set(v1_1 - (((float)AndroidUtilities.dp(1.5f))), ((float)AndroidUtilities.dp(2f)), (((float)AndroidUtilities.dp(1.5f))) + v1_1, ((float)AndroidUtilities.dp(50f)));
        v7.drawRoundRect(v0.rect3, ((float)AndroidUtilities.dp(1f)), ((float)AndroidUtilities.dp(1f)), v0.paint2);
        v7.drawCircle(v1_1, ((float)AndroidUtilities.dp(52f)), ((float)AndroidUtilities.dp(3.5f)), v0.paint2);
        v0.rect3.set(v1_1 - (((float)AndroidUtilities.dp(1f))), ((float)AndroidUtilities.dp(2f)), (((float)AndroidUtilities.dp(1f))) + v1_1, ((float)AndroidUtilities.dp(50f)));
        v7.drawRoundRect(v0.rect3, ((float)AndroidUtilities.dp(1f)), ((float)AndroidUtilities.dp(1f)), v0.paint);
        v7.drawCircle(v1_1, ((float)AndroidUtilities.dp(52f)), ((float)AndroidUtilities.dp(3f)), v0.paint);
    }

    protected void onMeasure(int arg1, int arg2) {
        super.onMeasure(arg1, arg2);
        arg1 = View$MeasureSpec.getSize(arg1);
        if(this.lastWidth != arg1) {
            this.clearFrames();
            this.lastWidth = arg1;
        }
    }

    public boolean onTouchEvent(MotionEvent arg13) {
        float v13_1;
        int v13;
        if(arg13 == null) {
            return 0;
        }

        float v1 = arg13.getX();
        float v2 = arg13.getY();
        int v3 = this.getMeasuredWidth() - AndroidUtilities.dp(32f);
        float v4 = ((float)v3);
        float v6 = 16f;
        int v5 = (((int)(this.progressLeft * v4))) + AndroidUtilities.dp(v6);
        int v7 = (((int)((this.progressLeft + (this.progressRight - this.progressLeft) * this.playProgress) * v4))) + AndroidUtilities.dp(v6);
        int v8 = (((int)(this.progressRight * v4))) + AndroidUtilities.dp(v6);
        if(arg13.getAction() == 0) {
            this.getParent().requestDisallowInterceptTouchEvent(true);
            if(this.mediaMetadataRetriever == null) {
                return 0;
            }
            else {
                v13 = AndroidUtilities.dp(12f);
                v3 = AndroidUtilities.dp(8f);
                if((((float)(v7 - v3))) > v1 || v1 > (((float)(v3 + v7))) || v2 < 0f || v2 > (((float)this.getMeasuredHeight()))) {
                    if((((float)(v5 - v13))) <= v1 && v1 <= (((float)(v5 + v13))) && v2 >= 0f && v2 <= (((float)this.getMeasuredHeight()))) {
                        if(this.delegate != null) {
                            this.delegate.didStartDragging();
                        }

                        this.pressedLeft = true;
                        v13_1 = ((float)v5);
                        goto label_61;
                    }

                    if((((float)(v8 - v13))) > v1) {
                        return 0;
                    }

                    if(v1 > (((float)(v13 + v8)))) {
                        return 0;
                    }

                    if(v2 < 0f) {
                        return 0;
                    }

                    if(v2 > (((float)this.getMeasuredHeight()))) {
                        return 0;
                    }

                    if(this.delegate != null) {
                        this.delegate.didStartDragging();
                    }

                    this.pressedRight = true;
                    v13_1 = ((float)v8);
                }
                else {
                    if(this.delegate != null) {
                        this.delegate.didStartDragging();
                    }

                    this.pressedPlay = true;
                    v13_1 = ((float)v7);
                }

            label_61:
                this.pressDx = ((float)(((int)(v1 - v13_1))));
                this.invalidate();
                return 1;
            }
        }
        else {
            if(arg13.getAction() != 1) {
                if(arg13.getAction() == 3) {
                }
                else if(arg13.getAction() != 2) {
                    return 0;
                }
                else if(this.pressedPlay) {
                    this.playProgress = (((float)((((int)(v1 - this.pressDx))) - AndroidUtilities.dp(v6)))) / v4;
                    if(this.playProgress < this.progressLeft) {
                        v13_1 = this.progressLeft;
                        goto label_124;
                    }
                    else if(this.playProgress > this.progressRight) {
                        v13_1 = this.progressRight;
                    label_124:
                        this.playProgress = v13_1;
                    }

                    this.playProgress = (this.playProgress - this.progressLeft) / (this.progressRight - this.progressLeft);
                    if(this.delegate != null) {
                        this.delegate.onPlayProgressChanged(this.progressLeft + (this.progressRight - this.progressLeft) * this.playProgress);
                    }

                    this.invalidate();
                    return 1;
                }
                else {
                    if(this.pressedLeft) {
                        v13 = ((int)(v1 - this.pressDx));
                        if(v13 < AndroidUtilities.dp(v6)) {
                            v8 = AndroidUtilities.dp(v6);
                        }
                        else if(v13 > v8) {
                        }
                        else {
                            v8 = v13;
                        }

                        this.progressLeft = (((float)(v8 - AndroidUtilities.dp(v6)))) / v4;
                        if(this.progressRight - this.progressLeft > this.maxProgressDiff) {
                            this.progressRight = this.progressLeft + this.maxProgressDiff;
                        }
                        else if(this.minProgressDiff != 0f && this.progressRight - this.progressLeft < this.minProgressDiff) {
                            this.progressLeft = this.progressRight - this.minProgressDiff;
                            if(this.progressLeft < 0f) {
                                this.progressLeft = 0f;
                            }
                        }

                        if(this.delegate != null) {
                            this.delegate.onLeftProgressChanged(this.progressLeft);
                        }

                        this.invalidate();
                        return 1;
                    }

                    if(!this.pressedRight) {
                        return 0;
                    }

                    v13 = ((int)(v1 - this.pressDx));
                    if(v13 < v5) {
                    }
                    else if(v13 > AndroidUtilities.dp(v6) + v3) {
                        v5 = v3 + AndroidUtilities.dp(v6);
                    }
                    else {
                        v5 = v13;
                    }

                    this.progressRight = (((float)(v5 - AndroidUtilities.dp(v6)))) / v4;
                    if(this.progressRight - this.progressLeft > this.maxProgressDiff) {
                        this.progressLeft = this.progressRight - this.maxProgressDiff;
                    }
                    else if(this.minProgressDiff != 0f && this.progressRight - this.progressLeft < this.minProgressDiff) {
                        this.progressRight = this.progressLeft + this.minProgressDiff;
                        float v0 = 1f;
                        if(this.progressRight > v0) {
                            this.progressRight = v0;
                        }
                    }

                    if(this.delegate != null) {
                        this.delegate.onRightProgressChanged(this.progressRight);
                    }

                    this.invalidate();
                    return 1;
                }
            }

            if(this.pressedLeft) {
                if(this.delegate != null) {
                    this.delegate.didStopDragging();
                }

                this.pressedLeft = false;
                return 1;
            }

            if(this.pressedRight) {
                if(this.delegate != null) {
                    this.delegate.didStopDragging();
                }

                this.pressedRight = false;
                return 1;
            }

            if(!this.pressedPlay) {
                return 0;
            }

            if(this.delegate != null) {
                this.delegate.didStopDragging();
            }

            this.pressedPlay = false;
            return 1;
        }

        return 0;
    }

    private void reloadFrames(int arg5) {
        if(this.mediaMetadataRetriever == null) {
            return;
        }

        if(arg5 == 0) {
            float v1 = 16f;
            if(this.isRoundFrames) {
                int v0 = AndroidUtilities.dp(56f);
                this.frameWidth = v0;
                this.frameHeight = v0;
                this.framesToLoad = ((int)Math.ceil(((double)((((float)(this.getMeasuredWidth() - AndroidUtilities.dp(v1)))) / ((((float)this.frameHeight)) / 2f)))));
            }
            else {
                this.frameHeight = AndroidUtilities.dp(40f);
                this.framesToLoad = (this.getMeasuredWidth() - AndroidUtilities.dp(v1)) / this.frameHeight;
                this.frameWidth = ((int)Math.ceil(((double)((((float)(this.getMeasuredWidth() - AndroidUtilities.dp(v1)))) / (((float)this.framesToLoad))))));
            }

            this.frameTimeOffset = this.videoLength / (((long)this.framesToLoad));
        }

        this.currentTask = new AsyncTask() {
            private int frameNum;

            protected Bitmap doInBackground(Integer[] arg11) {
                Bitmap v11;
                int v4;
                this.frameNum = arg11[0].intValue();
                Bitmap v1 = null;
                if(this.isCancelled()) {
                    return v1;
                }

                try {
                    v4 = 2;
                    v11 = VideoTimelinePlayView.this.mediaMetadataRetriever.getFrameAtTime(VideoTimelinePlayView.this.frameTimeOffset * (((long)this.frameNum)) * 1000, v4);
                }
                catch(Exception v0) {
                    v11 = v1;
                    goto label_76;
                }

                try {
                    if(this.isCancelled()) {
                        return v1;
                    }

                    if(v11 == null) {
                        return v11;
                    }

                    Bitmap v2 = Bitmap.createBitmap(VideoTimelinePlayView.this.frameWidth, VideoTimelinePlayView.this.frameHeight, v11.getConfig());
                    Canvas v3 = new Canvas(v2);
                    float v5 = (((float)VideoTimelinePlayView.this.frameWidth)) / (((float)v11.getWidth()));
                    float v6 = (((float)VideoTimelinePlayView.this.frameHeight)) / (((float)v11.getHeight()));
                    if(v5 > v6) {
                    }
                    else {
                        v5 = v6;
                    }

                    int v6_1 = ((int)((((float)v11.getWidth())) * v5));
                    int v5_1 = ((int)((((float)v11.getHeight())) * v5));
                    v3.drawBitmap(v11, new Rect(0, 0, v11.getWidth(), v11.getHeight()), new Rect((VideoTimelinePlayView.this.frameWidth - v6_1) / v4, (VideoTimelinePlayView.this.frameHeight - v5_1) / v4, v6_1, v5_1), ((Paint)v1));
                    v11.recycle();
                    return v2;
                }
                catch(Exception v0) {
                }

            label_76:
                FileLog.e(((Throwable)v0));
                return v11;
            }

            protected Object doInBackground(Object[] arg1) {
                return this.doInBackground(((Integer[])arg1));
            }

            protected void onPostExecute(Bitmap arg2) {
                if(!this.isCancelled()) {
                    VideoTimelinePlayView.this.frames.add(arg2);
                    VideoTimelinePlayView.this.invalidate();
                    if(this.frameNum < VideoTimelinePlayView.this.framesToLoad) {
                        VideoTimelinePlayView.this.reloadFrames(this.frameNum + 1);
                    }
                }
            }

            protected void onPostExecute(Object arg1) {
                this.onPostExecute(((Bitmap)arg1));
            }
        };
        this.currentTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Integer[]{Integer.valueOf(arg5), null, null});
    }

    public void setColor(int arg2) {
        this.paint.setColor(arg2);
    }

    public void setDelegate(VideoTimelineViewDelegate arg1) {
        this.delegate = arg1;
    }

    public void setMaxProgressDiff(float arg2) {
        this.maxProgressDiff = arg2;
        if(this.progressRight - this.progressLeft > this.maxProgressDiff) {
            this.progressRight = this.progressLeft + this.maxProgressDiff;
            this.invalidate();
        }
    }

    public void setMinProgressDiff(float arg1) {
        this.minProgressDiff = arg1;
    }

    public void setProgress(float arg1) {
        this.playProgress = arg1;
        this.invalidate();
    }

    public void setRoundFrames(boolean arg5) {
        this.isRoundFrames = arg5;
        if(this.isRoundFrames) {
            this.rect1 = new Rect(AndroidUtilities.dp(14f), AndroidUtilities.dp(14f), AndroidUtilities.dp(42f), AndroidUtilities.dp(42f));
            this.rect2 = new Rect();
        }
    }

    public void setVideoPath(String arg3) {
        this.destroy();
        this.mediaMetadataRetriever = new MediaMetadataRetriever();
        this.progressLeft = 0f;
        this.progressRight = 1f;
        try {
            this.mediaMetadataRetriever.setDataSource(arg3);
            this.videoLength = Long.parseLong(this.mediaMetadataRetriever.extractMetadata(9));
        }
        catch(Exception v3) {
            FileLog.e(((Throwable)v3));
        }

        this.invalidate();
    }
}

