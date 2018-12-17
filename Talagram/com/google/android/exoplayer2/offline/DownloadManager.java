package com.google.android.exoplayer2.offline;

import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import com.google.android.exoplayer2.upstream.DataSource$Factory;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

public final class DownloadManager {
    public interface Listener {
        void onIdle(DownloadManager arg1);

        void onInitialized(DownloadManager arg1);

        void onTaskStateChanged(DownloadManager arg1, TaskState arg2);
    }

    final class Task implements Runnable {
        @Retention(value=RetentionPolicy.SOURCE) @public interface InternalState {
        }

        public static final int STATE_QUEUED_CANCELING = 5;
        public static final int STATE_STARTED_CANCELING = 6;
        public static final int STATE_STARTED_STOPPING = 7;
        private final DownloadAction action;
        private volatile int currentState;
        private final DownloadManager downloadManager;
        private volatile Downloader downloader;
        private Throwable error;
        private final int id;
        private final int minRetryCount;
        private Thread thread;

        Task(int arg1, DownloadManager arg2, DownloadAction arg3, int arg4, com.google.android.exoplayer2.offline.DownloadManager$1 arg5) {
            this(arg1, arg2, arg3, arg4);
        }

        private Task(int arg1, DownloadManager arg2, DownloadAction arg3, int arg4) {
            super();
            this.id = arg1;
            this.downloadManager = arg2;
            this.action = arg3;
            this.currentState = 0;
            this.minRetryCount = arg4;
        }

        static void access$000(Task arg0) {
            arg0.stop();
        }

        static int access$100(Task arg0) {
            return arg0.currentState;
        }

        static int access$200(Task arg0) {
            return arg0.id;
        }

        static boolean access$2100(Task arg0, int arg1, int arg2) {
            return arg0.changeStateAndNotify(arg1, arg2);
        }

        static boolean access$2500(Task arg0, int arg1, int arg2, Throwable arg3) {
            return arg0.changeStateAndNotify(arg1, arg2, arg3);
        }

        static DownloadAction access$300(Task arg0) {
            return arg0.action;
        }

        static boolean access$500(Task arg0) {
            return arg0.canStart();
        }

        static void access$600(Task arg0) {
            arg0.cancel();
        }

        static void access$700(Task arg0) {
            arg0.start();
        }

        private boolean canStart() {
            boolean v0 = this.currentState == 0 ? true : false;
            return v0;
        }

        private void cancel() {
            if(this.changeStateAndNotify(0, 5)) {
                this.downloadManager.handler.post(new Runnable() {
                    public void run() {
                        Task.this.changeStateAndNotify(5, 3);
                    }
                });
            }
            else if(this.changeStateAndNotify(1, 6)) {
                this.cancelDownload();
            }
        }

        private void cancelDownload() {
            if(this.downloader != null) {
                this.downloader.cancel();
            }

            this.thread.interrupt();
        }

        private boolean changeStateAndNotify(int arg2, int arg3) {
            return this.changeStateAndNotify(arg2, arg3, null);
        }

        private boolean changeStateAndNotify(int arg3, int arg4, Throwable arg5) {
            int v1 = 0;
            if(this.currentState != arg3) {
                return 0;
            }

            this.currentState = arg4;
            this.error = arg5;
            if(this.currentState != this.getExternalState()) {
                v1 = 1;
            }

            if(v1 == 0) {
                this.downloadManager.onTaskStateChange(this);
            }

            return 1;
        }

        public float getDownloadPercentage() {
            float v0 = this.downloader != null ? this.downloader.getDownloadPercentage() : -1f;
            return v0;
        }

        public TaskState getDownloadState() {
            return new TaskState(this.id, this.action, this.getExternalState(), this.getDownloadPercentage(), this.getDownloadedBytes(), this.error, null);
        }

        public long getDownloadedBytes() {
            long v0 = this.downloader != null ? this.downloader.getDownloadedBytes() : 0;
            return v0;
        }

        private int getExternalState() {
            switch(this.currentState) {
                case 5: {
                    return 0;
                }
                case 6: 
                case 7: {
                    return 1;
                }
            }

            return this.currentState;
        }

        private int getRetryDelayMillis(int arg2) {
            return Math.min((arg2 - 1) * 1000, 5000);
        }

        private String getStateString() {
            switch(this.currentState) {
                case 5: 
                case 6: {
                    return "CANCELING";
                }
                case 7: {
                    return "STOPPING";
                }
            }

            return TaskState.getStateString(this.currentState);
        }

        public boolean isActive() {
            boolean v1 = true;
            if(this.currentState != 5 && this.currentState != 1 && this.currentState != 7) {
                if(this.currentState == 6) {
                }
                else {
                    v1 = false;
                }
            }

            return v1;
        }

        public boolean isFinished() {
            boolean v0 = this.currentState == 4 || this.currentState == 2 || this.currentState == 3 ? true : false;
            return v0;
        }

        public void run() {
            DownloadManager.logd("Task is started", this);
            try {
                this.downloader = this.action.createDownloader(this.downloadManager.downloaderConstructorHelper);
                if(this.action.isRemoveAction) {
                    this.downloader.remove();
                    goto label_52;
                }

                long v0_1 = -1;
                int v3 = 0;
                while(!Thread.interrupted()) {
                    try {
                        this.downloader.download();
                        break;
                    }
                    catch(IOException v4) {
                        try {
                            long v5 = this.downloader.getDownloadedBytes();
                            if(v5 != v0_1) {
                                DownloadManager.logd("Reset error count. downloadedBytes = " + v5, this);
                                v0_1 = v5;
                                v3 = 0;
                            }

                            if(this.currentState == 1) {
                                ++v3;
                                if(v3 <= this.minRetryCount) {
                                    DownloadManager.logd("Download error. Retry " + v3, this);
                                    Thread.sleep(((long)this.getRetryDelayMillis(v3)));
                                    continue;
                                }
                            }

                            throw v4;
                        }
                        catch(Throwable v0) {
                            goto label_55;
                        }
                    }
                }
            }
            catch(Throwable v0) {
                goto label_55;
            }

        label_52:
            v0 = null;
        label_55:
            this.downloadManager.handler.post(new Runnable(v0) {
                public void run() {
                    Task v0 = Task.this;
                    int v1 = this.val$finalError != null ? 4 : 2;
                    if(!v0.changeStateAndNotify(1, v1, this.val$finalError) && !Task.this.changeStateAndNotify(6, 3)) {
                        if(Task.this.changeStateAndNotify(7, 0)) {
                        }
                        else {
                            throw new IllegalStateException();
                        }
                    }
                }
            });
        }

        private void start() {
            if(this.changeStateAndNotify(0, 1)) {
                this.thread = new Thread(((Runnable)this));
                this.thread.start();
            }
        }

        private void stop() {
            if(this.changeStateAndNotify(1, 7)) {
                DownloadManager.logd("Stopping", this);
                this.cancelDownload();
            }
        }

        private static String toString(byte[] arg2) {
            if(arg2.length > 100) {
                return "<data is too long>";
            }

            return '\'' + Util.fromUtf8Bytes(arg2) + '\'';
        }

        public String toString() {
            return super.toString();
        }
    }

    public final class TaskState {
        @Retention(value=RetentionPolicy.SOURCE) @public interface State {
        }

        public static final int STATE_CANCELED = 3;
        public static final int STATE_COMPLETED = 2;
        public static final int STATE_FAILED = 4;
        public static final int STATE_QUEUED = 0;
        public static final int STATE_STARTED = 1;
        public final DownloadAction action;
        public final float downloadPercentage;
        public final long downloadedBytes;
        public final Throwable error;
        public final int state;
        public final int taskId;

        TaskState(int arg1, DownloadAction arg2, int arg3, float arg4, long arg5, Throwable arg7, com.google.android.exoplayer2.offline.DownloadManager$1 arg8) {
            this(arg1, arg2, arg3, arg4, arg5, arg7);
        }

        private TaskState(int arg1, DownloadAction arg2, int arg3, float arg4, long arg5, Throwable arg7) {
            super();
            this.taskId = arg1;
            this.action = arg2;
            this.state = arg3;
            this.downloadPercentage = arg4;
            this.downloadedBytes = arg5;
            this.error = arg7;
        }

        public static String getStateString(int arg0) {
            switch(arg0) {
                case 0: {
                    return "QUEUED";
                }
                case 1: {
                    return "STARTED";
                }
                case 2: {
                    return "COMPLETED";
                }
                case 3: {
                    return "CANCELED";
                }
                case 4: {
                    return "FAILED";
                }
            }

            throw new IllegalStateException();
            return "FAILED";
        }
    }

    private static final boolean DEBUG = false;
    public static final int DEFAULT_MAX_SIMULTANEOUS_DOWNLOADS = 1;
    public static final int DEFAULT_MIN_RETRY_COUNT = 5;
    private static final String TAG = "DownloadManager";
    private final ActionFile actionFile;
    private final ArrayList activeDownloadTasks;
    private final Deserializer[] deserializers;
    private final DownloaderConstructorHelper downloaderConstructorHelper;
    private boolean downloadsStopped;
    private final Handler fileIOHandler;
    private final HandlerThread fileIOThread;
    private final Handler handler;
    private boolean initialized;
    private final CopyOnWriteArraySet listeners;
    private final int maxActiveDownloadTasks;
    private final int minRetryCount;
    private int nextTaskId;
    private boolean released;
    private final ArrayList tasks;

    public DownloadManager(DownloaderConstructorHelper arg1, int arg2, int arg3, File arg4, Deserializer[] arg5) {
        super();
        this.downloaderConstructorHelper = arg1;
        this.maxActiveDownloadTasks = arg2;
        this.minRetryCount = arg3;
        this.actionFile = new ActionFile(arg4);
        if(arg5.length > 0) {
        }
        else {
            arg5 = DownloadAction.getDefaultDeserializers();
        }

        this.deserializers = arg5;
        this.downloadsStopped = true;
        this.tasks = new ArrayList();
        this.activeDownloadTasks = new ArrayList();
        Looper v1 = Looper.myLooper();
        if(v1 == null) {
            v1 = Looper.getMainLooper();
        }

        this.handler = new Handler(v1);
        this.fileIOThread = new HandlerThread("DownloadManager file i/o");
        this.fileIOThread.start();
        this.fileIOHandler = new Handler(this.fileIOThread.getLooper());
        this.listeners = new CopyOnWriteArraySet();
        this.loadActions();
        DownloadManager.logd("Created");
    }

    public DownloadManager(DownloaderConstructorHelper arg7, File arg8, Deserializer[] arg9) {
        this(arg7, 1, 5, arg8, arg9);
    }

    public DownloadManager(Cache arg2, Factory arg3, File arg4, Deserializer[] arg5) {
        this(new DownloaderConstructorHelper(arg2, arg3), arg4, arg5);
    }

    static void access$1000(String arg0) {
        DownloadManager.logd(arg0);
    }

    static boolean access$1100(DownloadManager arg0) {
        return arg0.released;
    }

    static ArrayList access$1200(DownloadManager arg0) {
        return arg0.tasks;
    }

    static Task access$1300(DownloadManager arg0, DownloadAction arg1) {
        return arg0.addTaskForAction(arg1);
    }

    static boolean access$1402(DownloadManager arg0, boolean arg1) {
        arg0.initialized = arg1;
        return arg1;
    }

    static CopyOnWriteArraySet access$1500(DownloadManager arg0) {
        return arg0.listeners;
    }

    static void access$1600(DownloadManager arg0) {
        arg0.saveActions();
    }

    static void access$1700(DownloadManager arg0) {
        arg0.maybeStartTasks();
    }

    static void access$1800(DownloadManager arg0, Task arg1) {
        arg0.notifyListenersTaskStateChange(arg1);
    }

    static Handler access$1900(DownloadManager arg0) {
        return arg0.handler;
    }

    static void access$2200(String arg0, Task arg1) {
        DownloadManager.logd(arg0, arg1);
    }

    static void access$2300(DownloadManager arg0, Task arg1) {
        arg0.onTaskStateChange(arg1);
    }

    static DownloaderConstructorHelper access$2400(DownloadManager arg0) {
        return arg0.downloaderConstructorHelper;
    }

    static Deserializer[] access$800(DownloadManager arg0) {
        return arg0.deserializers;
    }

    static ActionFile access$900(DownloadManager arg0) {
        return arg0.actionFile;
    }

    public void addListener(Listener arg2) {
        this.listeners.add(arg2);
    }

    private Task addTaskForAction(DownloadAction arg8) {
        int v1 = this.nextTaskId;
        this.nextTaskId = v1 + 1;
        Task v6 = new Task(v1, this, arg8, this.minRetryCount, null);
        this.tasks.add(v6);
        DownloadManager.logd("Task is added", v6);
        return v6;
    }

    public TaskState[] getAllTaskStates() {
        Assertions.checkState(this.released ^ 1);
        TaskState[] v0 = new TaskState[this.tasks.size()];
        int v1;
        for(v1 = 0; v1 < v0.length; ++v1) {
            v0[v1] = this.tasks.get(v1).getDownloadState();
        }

        return v0;
    }

    public int getDownloadCount() {
        int v0 = 0;
        int v1 = 0;
        while(v0 < this.tasks.size()) {
            if(!Task.access$300(this.tasks.get(v0)).isRemoveAction) {
                ++v1;
            }

            ++v0;
        }

        return v1;
    }

    public int getTaskCount() {
        Assertions.checkState(this.released ^ 1);
        return this.tasks.size();
    }

    public TaskState getTaskState(int arg4) {
        Assertions.checkState(this.released ^ 1);
        int v0;
        for(v0 = 0; v0 < this.tasks.size(); ++v0) {
            Object v1 = this.tasks.get(v0);
            if(Task.access$200(((Task)v1)) == arg4) {
                return ((Task)v1).getDownloadState();
            }
        }

        return null;
    }

    public int handleAction(DownloadAction arg2) {
        Assertions.checkState(this.released ^ 1);
        Task v2 = this.addTaskForAction(arg2);
        if(this.initialized) {
            this.saveActions();
            this.maybeStartTasks();
            if(Task.access$100(v2) == 0) {
                this.notifyListenersTaskStateChange(v2);
            }
        }

        return Task.access$200(v2);
    }

    public int handleAction(byte[] arg2) {
        Assertions.checkState(this.released ^ 1);
        return this.handleAction(DownloadAction.deserializeFromStream(this.deserializers, new ByteArrayInputStream(arg2)));
    }

    public boolean isIdle() {
        Assertions.checkState(this.released ^ 1);
        if(!this.initialized) {
            return 0;
        }

        int v0;
        for(v0 = 0; v0 < this.tasks.size(); ++v0) {
            if(this.tasks.get(v0).isActive()) {
                return 0;
            }
        }

        return 1;
    }

    public boolean isInitialized() {
        Assertions.checkState(this.released ^ 1);
        return this.initialized;
    }

    private void loadActions() {
        this.fileIOHandler.post(new Runnable() {
            public void run() {
                DownloadAction[] v0_1;
                try {
                    v0_1 = DownloadManager.this.actionFile.load(DownloadManager.this.deserializers);
                    DownloadManager.logd("Action file is loaded.");
                }
                catch(Throwable v0) {
                    Log.e("DownloadManager", "Action file loading failed.", v0);
                    v0_1 = new DownloadAction[0];
                }

                DownloadManager.this.handler.post(new Runnable(v0_1) {
                    public void run() {
                        if(this.this$1.this$0.released) {
                            return;
                        }

                        ArrayList v0 = new ArrayList(this.this$1.this$0.tasks);
                        this.this$1.this$0.tasks.clear();
                        DownloadAction[] v1 = this.val$actions;
                        int v2 = v1.length;
                        int v3 = 0;
                        int v4;
                        for(v4 = 0; v4 < v2; ++v4) {
                            this.this$1.this$0.addTaskForAction(v1[v4]);
                        }

                        DownloadManager.logd("Tasks are created.");
                        this.this$1.this$0.initialized = true;
                        Iterator v1_1 = this.this$1.this$0.listeners.iterator();
                        while(v1_1.hasNext()) {
                            v1_1.next().onInitialized(this.this$1.this$0);
                        }

                        if(!((List)v0).isEmpty()) {
                            this.this$1.this$0.tasks.addAll(((Collection)v0));
                            this.this$1.this$0.saveActions();
                        }

                        this.this$1.this$0.maybeStartTasks();
                        while(v3 < this.this$1.this$0.tasks.size()) {
                            Object v0_1 = this.this$1.this$0.tasks.get(v3);
                            if(Task.access$100(((Task)v0_1)) == 0) {
                                this.this$1.this$0.notifyListenersTaskStateChange(((Task)v0_1));
                            }

                            ++v3;
                        }
                    }
                });
            }
        });
    }

    private static void logd(String arg0) {
    }

    private static void logd(String arg1, Task arg2) {
        DownloadManager.logd(arg1 + ": " + arg2);
    }

    private void maybeNotifyListenersIdle() {
        if(!this.isIdle()) {
            return;
        }

        DownloadManager.logd("Notify idle state");
        Iterator v0 = this.listeners.iterator();
        while(v0.hasNext()) {
            v0.next().onIdle(this);
        }
    }

    private void maybeStartTasks() {
        if(this.initialized) {
            if(this.released) {
            }
            else {
                int v0 = (this.downloadsStopped) || this.activeDownloadTasks.size() == this.maxActiveDownloadTasks ? 1 : 0;
                int v3 = v0;
                for(v0 = 0; v0 < this.tasks.size(); ++v0) {
                    Object v4 = this.tasks.get(v0);
                    if(!Task.access$500(((Task)v4))) {
                    }
                    else {
                        DownloadAction v5 = Task.access$300(((Task)v4));
                        boolean v6 = v5.isRemoveAction;
                        if(!v6 && v3 != 0) {
                            goto label_72;
                        }

                        int v7 = 0;
                        int v8 = 1;
                        while(v7 < v0) {
                            Object v9 = this.tasks.get(v7);
                            if(Task.access$300(((Task)v9)).isSameMedia(v5)) {
                                if(v6) {
                                    DownloadManager.logd(v4 + " clashes with " + v9);
                                    Task.access$600(((Task)v9));
                                    v8 = 0;
                                }
                                else if(Task.access$300(((Task)v9)).isRemoveAction) {
                                    v3 = 1;
                                    v8 = 0;
                                    break;
                                }
                            }

                            ++v7;
                        }

                        if(v8 == 0) {
                            goto label_72;
                        }

                        Task.access$700(((Task)v4));
                        if(v6) {
                            goto label_72;
                        }

                        this.activeDownloadTasks.add(v4);
                        if(this.activeDownloadTasks.size() == this.maxActiveDownloadTasks) {
                            v3 = 1;
                            goto label_72;
                        }

                        v3 = 0;
                    }

                label_72:
                }
            }
        }
    }

    private void notifyListenersTaskStateChange(Task arg3) {
        DownloadManager.logd("Task state is changed", arg3);
        TaskState v3 = arg3.getDownloadState();
        Iterator v0 = this.listeners.iterator();
        while(v0.hasNext()) {
            v0.next().onTaskStateChanged(this, v3);
        }
    }

    private void onTaskStateChange(Task arg3) {
        if(this.released) {
            return;
        }

        int v0 = arg3.isActive() ^ 1;
        if(v0 != 0) {
            this.activeDownloadTasks.remove(arg3);
        }

        this.notifyListenersTaskStateChange(arg3);
        if(arg3.isFinished()) {
            this.tasks.remove(arg3);
            this.saveActions();
        }

        if(v0 != 0) {
            this.maybeStartTasks();
            this.maybeNotifyListenersIdle();
        }
    }

    public void release() {
        if(this.released) {
            return;
        }

        this.released = true;
        int v0;
        for(v0 = 0; v0 < this.tasks.size(); ++v0) {
            Task.access$000(this.tasks.get(v0));
        }

        ConditionVariable v0_1 = new ConditionVariable();
        this.fileIOHandler.post(new Runnable(v0_1) {
            public void run() {
                this.val$fileIOFinishedCondition.open();
            }
        });
        v0_1.block();
        this.fileIOThread.quit();
        DownloadManager.logd("Released");
    }

    public void removeListener(Listener arg2) {
        this.listeners.remove(arg2);
    }

    private void saveActions() {
        if(this.released) {
            return;
        }

        DownloadAction[] v0 = new DownloadAction[this.tasks.size()];
        int v1;
        for(v1 = 0; v1 < this.tasks.size(); ++v1) {
            v0[v1] = Task.access$300(this.tasks.get(v1));
        }

        this.fileIOHandler.post(new Runnable(v0) {
            public void run() {
                try {
                    DownloadManager.this.actionFile.store(this.val$actions);
                    DownloadManager.logd("Actions persisted.");
                }
                catch(IOException v0) {
                    Log.e("DownloadManager", "Persisting actions failed.", ((Throwable)v0));
                }
            }
        });
    }

    public void startDownloads() {
        Assertions.checkState(this.released ^ 1);
        if(this.downloadsStopped) {
            this.downloadsStopped = false;
            this.maybeStartTasks();
            DownloadManager.logd("Downloads are started");
        }
    }

    public void stopDownloads() {
        Assertions.checkState(this.released ^ 1);
        if(!this.downloadsStopped) {
            this.downloadsStopped = true;
            int v0;
            for(v0 = 0; v0 < this.activeDownloadTasks.size(); ++v0) {
                Task.access$000(this.activeDownloadTasks.get(v0));
            }

            DownloadManager.logd("Downloads are stopping");
        }
    }
}

