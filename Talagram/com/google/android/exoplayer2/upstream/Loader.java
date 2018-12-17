package com.google.android.exoplayer2.upstream;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.ExecutorService;

public final class Loader implements LoaderErrorThrower {
    class com.google.android.exoplayer2.upstream.Loader$1 {
    }

    public interface Callback {
        void onLoadCanceled(Loadable arg1, long arg2, long arg3, boolean arg4);

        void onLoadCompleted(Loadable arg1, long arg2, long arg3);

        LoadErrorAction onLoadError(Loadable arg1, long arg2, long arg3, IOException arg4, int arg5);
    }

    public final class LoadErrorAction {
        private final long retryDelayMillis;
        private final int type;

        LoadErrorAction(int arg1, long arg2, com.google.android.exoplayer2.upstream.Loader$1 arg4) {
            this(arg1, arg2);
        }

        private LoadErrorAction(int arg1, long arg2) {
            super();
            this.type = arg1;
            this.retryDelayMillis = arg2;
        }

        static int access$300(LoadErrorAction arg0) {
            return arg0.type;
        }

        static long access$400(LoadErrorAction arg2) {
            return arg2.retryDelayMillis;
        }

        public boolean isRetry() {
            boolean v1 = true;
            if(this.type != 0) {
                if(this.type == 1) {
                }
                else {
                    v1 = false;
                }
            }

            return v1;
        }
    }

    @SuppressLint(value={"HandlerLeak"}) final class LoadTask extends Handler implements Runnable {
        private static final int MSG_CANCEL = 1;
        private static final int MSG_END_OF_SOURCE = 2;
        private static final int MSG_FATAL_ERROR = 4;
        private static final int MSG_IO_EXCEPTION = 3;
        private static final int MSG_START = 0;
        private static final String TAG = "LoadTask";
        private Callback callback;
        private volatile boolean canceled;
        private IOException currentError;
        public final int defaultMinRetryCount;
        private int errorCount;
        private volatile Thread executorThread;
        private final Loadable loadable;
        private volatile boolean released;
        private final long startTimeMs;

        public LoadTask(Loader arg1, Looper arg2, Loadable arg3, Callback arg4, int arg5, long arg6) {
            Loader.this = arg1;
            super(arg2);
            this.loadable = arg3;
            this.callback = arg4;
            this.defaultMinRetryCount = arg5;
            this.startTimeMs = arg6;
        }

        public void cancel(boolean arg10) {
            this.released = arg10;
            IOException v0 = null;
            this.currentError = v0;
            if(this.hasMessages(0)) {
                this.removeMessages(0);
                if(!arg10) {
                    this.sendEmptyMessage(1);
                }
            }
            else {
                this.canceled = true;
                this.loadable.cancelLoad();
                if(this.executorThread != null) {
                    this.executorThread.interrupt();
                }
            }

            if(arg10) {
                this.finish();
                long v4 = SystemClock.elapsedRealtime();
                this.callback.onLoadCanceled(this.loadable, v4, v4 - this.startTimeMs, true);
                this.callback = ((Callback)v0);
            }
        }

        private void execute() {
            this.currentError = null;
            Loader.this.downloadExecutorService.execute(Loader.this.currentTask);
        }

        private void finish() {
            Loader.this.currentTask = null;
        }

        private long getRetryDelayMillis() {
            return ((long)Math.min((this.errorCount - 1) * 1000, 5000));
        }

        public void handleMessage(Message arg11) {
            if(this.released) {
                return;
            }

            if(arg11.what == 0) {
                this.execute();
                return;
            }

            if(arg11.what == 4) {
                goto label_74;
            }

            this.finish();
            long v4 = SystemClock.elapsedRealtime();
            long v6 = v4 - this.startTimeMs;
            if(this.canceled) {
                this.callback.onLoadCanceled(this.loadable, v4, v6, false);
                return;
            }

            switch(arg11.what) {
                case 1: {
                    goto label_69;
                }
                case 2: {
                    goto label_56;
                }
                case 3: {
                    goto label_24;
                }
            }

            return;
        label_69:
            this.callback.onLoadCanceled(this.loadable, v4, v6, false);
            return;
            try {
            label_56:
                this.callback.onLoadCompleted(this.loadable, v4, v6);
            }
            catch(RuntimeException v11) {
                Log.e("LoadTask", "Unexpected exception handling load completed", ((Throwable)v11));
                Loader.this.fatalError = new UnexpectedLoaderException(((Throwable)v11));
            }

            return;
        label_24:
            this.currentError = arg11.obj;
            ++this.errorCount;
            LoadErrorAction v11_1 = this.callback.onLoadError(this.loadable, v4, v6, this.currentError, this.errorCount);
            if(v11_1.type == 3) {
                Loader.this.fatalError = this.currentError;
                return;
            }

            if(v11_1.type != 2) {
                if(v11_1.type == 1) {
                    this.errorCount = 1;
                }

                long v0 = v11_1.retryDelayMillis != -9223372036854775807L ? v11_1.retryDelayMillis : this.getRetryDelayMillis();
                this.start(v0);
            }

            return;
        label_74:
            throw arg11.obj;
        }

        public void maybeThrowError(int arg2) {
            if(this.currentError != null) {
                if(this.errorCount <= arg2) {
                }
                else {
                    throw this.currentError;
                }
            }
        }

        public void run() {
            Message v0_5;
            UnexpectedLoaderException v2_2;
            int v0 = 2;
            int v1 = 3;
            try {
                this.executorThread = Thread.currentThread();
                if(!this.canceled) {
                    TraceUtil.beginSection("load:" + this.loadable.getClass().getSimpleName());
                    goto label_16;
                }

                goto label_23;
            }
            catch(Exception v0_1) {
                goto label_51;
            }
            catch(OutOfMemoryError v0_2) {
                goto label_40;
            }
            catch(InterruptedException ) {
                goto label_57;
            }
            catch(Error v0_3) {
                goto label_30;
            }
            catch(IOException v0_4) {
                goto label_65;
            }

            try {
            label_16:
                this.loadable.load();
                goto label_18;
            }
            catch(Throwable v2_1) {
                try {
                    TraceUtil.endSection();
                    throw v2_1;
                label_18:
                    TraceUtil.endSection();
                label_23:
                    if(this.released) {
                        return;
                    }

                    this.sendEmptyMessage(v0);
                }
                catch(Error v0_3) {
                label_30:
                    Log.e("LoadTask", "Unexpected error loading stream", ((Throwable)v0_3));
                    if(!this.released) {
                        this.obtainMessage(4, v0_3).sendToTarget();
                    }

                    throw v0_3;
                }
                catch(OutOfMemoryError v0_2) {
                label_40:
                    Log.e("LoadTask", "OutOfMemory error loading stream", ((Throwable)v0_2));
                    if(this.released) {
                        return;
                    }

                    v2_2 = new UnexpectedLoaderException(((Throwable)v0_2));
                label_45:
                    v0_5 = this.obtainMessage(v1, v2_2);
                label_46:
                    v0_5.sendToTarget();
                }
                catch(Exception v0_1) {
                label_51:
                    Log.e("LoadTask", "Unexpected exception loading stream", ((Throwable)v0_1));
                    if(this.released) {
                        return;
                    }

                    v2_2 = new UnexpectedLoaderException(((Throwable)v0_1));
                    goto label_45;
                }
                catch(InterruptedException ) {
                label_57:
                    Assertions.checkState(this.canceled);
                    if(this.released) {
                        return;
                    }

                    this.sendEmptyMessage(v0);
                }
                catch(IOException v0_4) {
                label_65:
                    if(this.released) {
                        return;
                    }

                    v0_5 = this.obtainMessage(v1, v0_4);
                    goto label_46;
                }
            }
        }

        public void start(long arg5) {
            boolean v0 = Loader.this.currentTask == null ? true : false;
            Assertions.checkState(v0);
            Loader.this.currentTask = this;
            if(arg5 > 0) {
                this.sendEmptyMessageDelayed(0, arg5);
            }
            else {
                this.execute();
            }
        }
    }

    public interface Loadable {
        void cancelLoad();

        void load();
    }

    public interface ReleaseCallback {
        void onLoaderReleased();
    }

    final class ReleaseTask implements Runnable {
        private final ReleaseCallback callback;

        public ReleaseTask(ReleaseCallback arg1) {
            super();
            this.callback = arg1;
        }

        public void run() {
            this.callback.onLoaderReleased();
        }
    }

    @Retention(value=RetentionPolicy.SOURCE) @interface RetryActionType {
    }

    public final class UnexpectedLoaderException extends IOException {
        public UnexpectedLoaderException(Throwable arg3) {
            super("Unexpected " + arg3.getClass().getSimpleName() + ": " + arg3.getMessage(), arg3);
        }
    }

    private static final int ACTION_TYPE_DONT_RETRY = 2;
    private static final int ACTION_TYPE_DONT_RETRY_FATAL = 3;
    private static final int ACTION_TYPE_RETRY = 0;
    private static final int ACTION_TYPE_RETRY_AND_RESET_ERROR_COUNT = 1;
    public static final LoadErrorAction DONT_RETRY;
    public static final LoadErrorAction DONT_RETRY_FATAL;
    public static final LoadErrorAction RETRY;
    public static final LoadErrorAction RETRY_RESET_ERROR_COUNT;
    private LoadTask currentTask;
    private final ExecutorService downloadExecutorService;
    private IOException fatalError;

    static {
        Loader.RETRY = Loader.createRetryAction(false, -9223372036854775807L);
        Loader.RETRY_RESET_ERROR_COUNT = Loader.createRetryAction(true, -9223372036854775807L);
        Loader.DONT_RETRY = new LoadErrorAction(2, -9223372036854775807L, null);
        Loader.DONT_RETRY_FATAL = new LoadErrorAction(3, -9223372036854775807L, null);
    }

    public Loader(String arg1) {
        super();
        this.downloadExecutorService = Util.newSingleThreadExecutor(arg1);
    }

    static LoadTask access$100(Loader arg0) {
        return arg0.currentTask;
    }

    static LoadTask access$102(Loader arg0, LoadTask arg1) {
        arg0.currentTask = arg1;
        return arg1;
    }

    static IOException access$202(Loader arg0, IOException arg1) {
        arg0.fatalError = arg1;
        return arg1;
    }

    static ExecutorService access$500(Loader arg0) {
        return arg0.downloadExecutorService;
    }

    public void cancelLoading() {
        this.currentTask.cancel(false);
    }

    public static LoadErrorAction createRetryAction(boolean arg2, long arg3) {
        return new LoadErrorAction(((int)arg2), arg3, null);
    }

    public boolean isLoading() {
        boolean v0 = this.currentTask != null ? true : false;
        return v0;
    }

    public void maybeThrowError() {
        this.maybeThrowError(-2147483648);
    }

    public void maybeThrowError(int arg3) {
        if(this.fatalError == null) {
            if(this.currentTask != null) {
                LoadTask v0 = this.currentTask;
                if(arg3 == -2147483648) {
                    arg3 = this.currentTask.defaultMinRetryCount;
                }

                v0.maybeThrowError(arg3);
            }

            return;
        }

        throw this.fatalError;
    }

    public void release(ReleaseCallback arg3) {
        if(this.currentTask != null) {
            this.currentTask.cancel(true);
        }

        if(arg3 != null) {
            this.downloadExecutorService.execute(new ReleaseTask(arg3));
        }

        this.downloadExecutorService.shutdown();
    }

    public void release() {
        this.release(null);
    }

    public long startLoading(Loadable arg12, Callback arg13, int arg14) {
        Looper v2 = Looper.myLooper();
        boolean v0 = v2 != null ? true : false;
        Assertions.checkState(v0);
        this.fatalError = null;
        long v8 = SystemClock.elapsedRealtime();
        new LoadTask(this, v2, arg12, arg13, arg14, v8).start(0);
        return v8;
    }
}

