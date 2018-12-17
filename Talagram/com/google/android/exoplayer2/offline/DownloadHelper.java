package com.google.android.exoplayer2.offline;

import android.os.Handler;
import android.os.Looper;
import com.google.android.exoplayer2.source.TrackGroupArray;
import java.io.IOException;
import java.util.List;

public abstract class DownloadHelper {
    public interface Callback {
        void onPrepareError(DownloadHelper arg1, IOException arg2);

        void onPrepared(DownloadHelper arg1);
    }

    public DownloadHelper() {
        super();
    }

    public abstract DownloadAction getDownloadAction(byte[] arg1, List arg2);

    public abstract int getPeriodCount();

    public abstract DownloadAction getRemoveAction(byte[] arg1);

    public abstract TrackGroupArray getTrackGroups(int arg1);

    public void prepare(Callback arg3) {
        Looper v1 = Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper();
        new Thread(new Handler(v1), arg3) {
            public void run() {
                try {
                    DownloadHelper.this.prepareInternal();
                    this.val$handler.post(new Runnable() {
                        public void run() {
                            this.this$1.val$callback.onPrepared(this.this$1.this$0);
                        }
                    });
                }
                catch(IOException v0) {
                    this.val$handler.post(new Runnable(v0) {
                        public void run() {
                            this.this$1.val$callback.onPrepareError(this.this$1.this$0, this.val$e);
                        }
                    });
                }
            }
        }.start();
    }

    protected abstract void prepareInternal();
}

