package org.telegram.ui.Components;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;

@TargetApi(value=10) public class VideoTimelineView extends View {
    public interface VideoTimelineViewDelegate {
        void didStartDragging();

        void didStopDragging();

        void onLeftProgressChanged(float arg1);

        void onRightProgressChanged(float arg1);
    }

    private AsyncTask currentTask;
    private VideoTimelineViewDelegate delegate;
    private int frameHeight;
    private long frameTimeOffset;
    private int frameWidth;
    private ArrayList frames;
    private int framesToLoad;
    private boolean isRoundFrames;
    private float maxProgressDiff;
    private MediaMetadataRetriever mediaMetadataRetriever;
    private float minProgressDiff;
    private Paint paint;
    private Paint paint2;
    private float pressDx;
    private boolean pressedLeft;
    private boolean pressedRight;
    private float progressLeft;
    private float progressRight;
    private Rect rect1;
    private Rect rect2;
    private static final Object sync;
    private long videoLength;

    static {
        VideoTimelineView.sync = new Object();
    }

    public VideoTimelineView(Context arg2) {
        super(arg2);
        this.progressRight = 1f;
        this.frames = new ArrayList();
        this.maxProgressDiff = 1f;
        this.minProgressDiff = 0f;
        this.paint = new Paint(1);
        this.paint.setColor(-1);
        this.paint2 = new Paint();
        this.paint2.setColor(2130706432);
    }

    static long access$000(VideoTimelineView arg2) {
        return arg2.frameTimeOffset;
    }

    static MediaMetadataRetriever access$100(VideoTimelineView arg0) {
        return arg0.mediaMetadataRetriever;
    }

    static int access$200(VideoTimelineView arg0) {
        return arg0.frameWidth;
    }

    static int access$300(VideoTimelineView arg0) {
        return arg0.frameHeight;
    }

    static ArrayList access$400(VideoTimelineView arg0) {
        return arg0.frames;
    }

    static int access$500(VideoTimelineView arg0) {
        return arg0.framesToLoad;
    }

    static void access$600(VideoTimelineView arg0, int arg1) {
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
        Object v0 = VideoTimelineView.sync;
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
                    __monitor_exit(v0_1);
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

    public float getRightProgress() {
        return this.progressRight;
    }

    protected void onDraw(Canvas arg18) {
        VideoTimelineView v0 = this;
        Canvas v7 = arg18;
        int v8 = this.getMeasuredWidth() - AndroidUtilities.dp(36f);
        float v1 = ((float)v8);
        float v9 = 16f;
        int v10 = (((int)(v0.progressLeft * v1))) + AndroidUtilities.dp(v9);
        int v11 = (((int)(v1 * v0.progressRight))) + AndroidUtilities.dp(v9);
        arg18.save();
        int v4 = 0;
        v7.clipRect(AndroidUtilities.dp(v9), 0, AndroidUtilities.dp(20f) + v8, this.getMeasuredHeight());
        float v12 = 2f;
        if(!v0.frames.isEmpty() || v0.currentTask != null) {
            int v1_1 = 0;
            while(v4 < v0.frames.size()) {
                Object v2 = v0.frames.get(v4);
                if(v2 != null) {
                    int v3 = AndroidUtilities.dp(v9);
                    int v5 = v0.isRoundFrames ? v0.frameWidth / 2 : v0.frameWidth;
                    v3 += v5 * v1_1;
                    v5 = AndroidUtilities.dp(v12);
                    Paint v13 = null;
                    if(v0.isRoundFrames) {
                        v0.rect2.set(v3, v5, AndroidUtilities.dp(28f) + v3, AndroidUtilities.dp(28f) + v5);
                        v7.drawBitmap(((Bitmap)v2), v0.rect1, v0.rect2, v13);
                        goto label_68;
                    }

                    v7.drawBitmap(((Bitmap)v2), ((float)v3), ((float)v5), v13);
                }

            label_68:
                ++v1_1;
                ++v4;
            }
        }
        else {
            v0.reloadFrames(0);
        }

        int v13_1 = AndroidUtilities.dp(v12);
        float v14 = ((float)v13_1);
        float v15 = ((float)v10);
        arg18.drawRect(((float)AndroidUtilities.dp(v9)), v14, v15, ((float)(this.getMeasuredHeight() - v13_1)), v0.paint2);
        arg18.drawRect(((float)(AndroidUtilities.dp(4f) + v11)), v14, ((float)(AndroidUtilities.dp(v9) + v8 + AndroidUtilities.dp(4f))), ((float)(this.getMeasuredHeight() - v13_1)), v0.paint2);
        arg18.drawRect(v15, 0f, ((float)(AndroidUtilities.dp(v12) + v10)), ((float)this.getMeasuredHeight()), v0.paint);
        arg18.drawRect(((float)(AndroidUtilities.dp(v12) + v11)), 0f, ((float)(AndroidUtilities.dp(4f) + v11)), ((float)this.getMeasuredHeight()), v0.paint);
        arg18.drawRect(((float)(AndroidUtilities.dp(v12) + v10)), 0f, ((float)(AndroidUtilities.dp(4f) + v11)), v14, v0.paint);
        arg18.drawRect(((float)(v10 + AndroidUtilities.dp(v12))), ((float)(this.getMeasuredHeight() - v13_1)), ((float)(AndroidUtilities.dp(4f) + v11)), ((float)this.getMeasuredHeight()), v0.paint);
        arg18.restore();
        v7.drawCircle(v15, ((float)(this.getMeasuredHeight() / 2)), ((float)AndroidUtilities.dp(7f)), v0.paint);
        v7.drawCircle(((float)(v11 + AndroidUtilities.dp(4f))), ((float)(this.getMeasuredHeight() / 2)), ((float)AndroidUtilities.dp(7f)), v0.paint);
    }

    public boolean onTouchEvent(MotionEvent arg12) {
        float v12_1;
        int v12;
        if(arg12 == null) {
            return 0;
        }

        float v1 = arg12.getX();
        float v2 = arg12.getY();
        int v3 = this.getMeasuredWidth() - AndroidUtilities.dp(32f);
        float v4 = ((float)v3);
        float v6 = 16f;
        int v5 = (((int)(this.progressLeft * v4))) + AndroidUtilities.dp(v6);
        int v7 = (((int)(this.progressRight * v4))) + AndroidUtilities.dp(v6);
        if(arg12.getAction() == 0) {
            this.getParent().requestDisallowInterceptTouchEvent(true);
            if(this.mediaMetadataRetriever == null) {
                return 0;
            }
            else {
                v12 = AndroidUtilities.dp(12f);
                if((((float)(v5 - v12))) > v1 || v1 > (((float)(v5 + v12))) || v2 < 0f || v2 > (((float)this.getMeasuredHeight()))) {
                    if((((float)(v7 - v12))) > v1) {
                        return 0;
                    }

                    if(v1 > (((float)(v12 + v7)))) {
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
                    v12_1 = ((float)v7);
                }
                else {
                    if(this.delegate != null) {
                        this.delegate.didStartDragging();
                    }

                    this.pressedLeft = true;
                    v12_1 = ((float)v5);
                }

                this.pressDx = ((float)(((int)(v1 - v12_1))));
                this.invalidate();
                return 1;
            }
        }
        else {
            if(arg12.getAction() != 1) {
                if(arg12.getAction() == 3) {
                }
                else if(arg12.getAction() != 2) {
                    return 0;
                }
                else if(this.pressedLeft) {
                    v12 = ((int)(v1 - this.pressDx));
                    if(v12 < AndroidUtilities.dp(v6)) {
                        v7 = AndroidUtilities.dp(v6);
                    }
                    else if(v12 > v7) {
                    }
                    else {
                        v7 = v12;
                    }

                    this.progressLeft = (((float)(v7 - AndroidUtilities.dp(v6)))) / v4;
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
                else {
                    if(!this.pressedRight) {
                        return 0;
                    }

                    v12 = ((int)(v1 - this.pressDx));
                    if(v12 < v5) {
                    }
                    else if(v12 > AndroidUtilities.dp(v6) + v3) {
                        v5 = v3 + AndroidUtilities.dp(v6);
                    }
                    else {
                        v5 = v12;
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

            if(!this.pressedRight) {
                return 0;
            }

            if(this.delegate != null) {
                this.delegate.didStopDragging();
            }

            this.pressedRight = false;
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
                    v11 = VideoTimelineView.this.mediaMetadataRetriever.getFrameAtTime(VideoTimelineView.this.frameTimeOffset * (((long)this.frameNum)) * 1000, v4);
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

                    Bitmap v2 = Bitmap.createBitmap(VideoTimelineView.this.frameWidth, VideoTimelineView.this.frameHeight, v11.getConfig());
                    Canvas v3 = new Canvas(v2);
                    float v5 = (((float)VideoTimelineView.this.frameWidth)) / (((float)v11.getWidth()));
                    float v6 = (((float)VideoTimelineView.this.frameHeight)) / (((float)v11.getHeight()));
                    if(v5 > v6) {
                    }
                    else {
                        v5 = v6;
                    }

                    int v6_1 = ((int)((((float)v11.getWidth())) * v5));
                    int v5_1 = ((int)((((float)v11.getHeight())) * v5));
                    v3.drawBitmap(v11, new Rect(0, 0, v11.getWidth(), v11.getHeight()), new Rect((VideoTimelineView.this.frameWidth - v6_1) / v4, (VideoTimelineView.this.frameHeight - v5_1) / v4, v6_1, v5_1), ((Paint)v1));
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
                    VideoTimelineView.this.frames.add(arg2);
                    VideoTimelineView.this.invalidate();
                    if(this.frameNum < VideoTimelineView.this.framesToLoad) {
                        VideoTimelineView.this.reloadFrames(this.frameNum + 1);
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

