package com.google.android.exoplayer2.offline;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import com.google.android.exoplayer2.scheduler.Requirements;
import com.google.android.exoplayer2.scheduler.RequirementsWatcher;
import com.google.android.exoplayer2.scheduler.Scheduler;
import com.google.android.exoplayer2.util.NotificationUtil;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.HashMap;

public abstract class DownloadService extends Service {
    class com.google.android.exoplayer2.offline.DownloadService$1 {
    }

    final class DownloadManagerListener implements Listener {
        DownloadManagerListener(DownloadService arg1, com.google.android.exoplayer2.offline.DownloadService$1 arg2) {
            this(arg1);
        }

        private DownloadManagerListener(DownloadService arg1) {
            DownloadService.this = arg1;
            super();
        }

        public final void onIdle(DownloadManager arg1) {
            DownloadService.this.stop();
        }

        public void onInitialized(DownloadManager arg2) {
            DownloadService.this.maybeStartWatchingRequirements(DownloadService.this.getRequirements());
        }

        public void onTaskStateChanged(DownloadManager arg1, TaskState arg2) {
            DownloadService.this.onTaskStateChanged(arg2);
            if(DownloadService.this.foregroundNotificationUpdater != null) {
                if(arg2.state == 1) {
                    DownloadService.this.foregroundNotificationUpdater.startPeriodicUpdates();
                }
                else {
                    DownloadService.this.foregroundNotificationUpdater.update();
                }
            }
        }
    }

    final class ForegroundNotificationUpdater implements Runnable {
        private final Handler handler;
        private boolean notificationDisplayed;
        private final int notificationId;
        private boolean periodicUpdatesStarted;
        private final long updateInterval;

        public ForegroundNotificationUpdater(DownloadService arg1, int arg2, long arg3) {
            DownloadService.this = arg1;
            super();
            this.notificationId = arg2;
            this.updateInterval = arg3;
            this.handler = new Handler(Looper.getMainLooper());
        }

        public void run() {
            this.update();
        }

        public void showNotificationIfNotAlready() {
            if(!this.notificationDisplayed) {
                this.update();
            }
        }

        public void startPeriodicUpdates() {
            this.periodicUpdatesStarted = true;
            this.update();
        }

        public void stopPeriodicUpdates() {
            this.periodicUpdatesStarted = false;
            this.handler.removeCallbacks(((Runnable)this));
        }

        public void update() {
            DownloadService.this.startForeground(this.notificationId, DownloadService.this.getForegroundNotification(DownloadService.this.downloadManager.getAllTaskStates()));
            this.notificationDisplayed = true;
            if(this.periodicUpdatesStarted) {
                this.handler.removeCallbacks(((Runnable)this));
                this.handler.postDelayed(((Runnable)this), this.updateInterval);
            }
        }
    }

    final class RequirementsHelper implements com.google.android.exoplayer2.scheduler.RequirementsWatcher$Listener {
        private final Context context;
        private final Requirements requirements;
        private final RequirementsWatcher requirementsWatcher;
        private final Scheduler scheduler;
        private final Class serviceClass;

        RequirementsHelper(Context arg1, Requirements arg2, Scheduler arg3, Class arg4, com.google.android.exoplayer2.offline.DownloadService$1 arg5) {
            this(arg1, arg2, arg3, arg4);
        }

        private RequirementsHelper(Context arg1, Requirements arg2, Scheduler arg3, Class arg4) {
            super();
            this.context = arg1;
            this.requirements = arg2;
            this.scheduler = arg3;
            this.serviceClass = arg4;
            this.requirementsWatcher = new RequirementsWatcher(arg1, ((com.google.android.exoplayer2.scheduler.RequirementsWatcher$Listener)this), arg2);
        }

        private void notifyService() {
            Intent v0 = DownloadService.getIntent(this.context, this.serviceClass, "com.google.android.exoplayer.downloadService.action.INIT");
            try {
                this.context.startService(v0);
                return;
            }
            catch(IllegalStateException v0_1) {
                throw new Exception(((Throwable)v0_1));
            }
        }

        public void requirementsMet(RequirementsWatcher arg1) {
            try {
                this.notifyService();
            }
            catch(Exception ) {
                return;
            }

            if(this.scheduler != null) {
                this.scheduler.cancel();
            }
        }

        public void requirementsNotMet(RequirementsWatcher arg4) {
            try {
                this.notifyService();
                goto label_1;
            }
            catch(Exception ) {
            label_1:
                if(this.scheduler != null && !this.scheduler.schedule(this.requirements, this.context.getPackageName(), "com.google.android.exoplayer.downloadService.action.RESTART")) {
                    Log.e("DownloadService", "Scheduling downloads failed.");
                }

                return;
            }
        }

        public void start() {
            this.requirementsWatcher.start();
        }

        public void stop() {
            this.requirementsWatcher.stop();
            if(this.scheduler != null) {
                this.scheduler.cancel();
            }
        }
    }

    public static final String ACTION_ADD = "com.google.android.exoplayer.downloadService.action.ADD";
    public static final String ACTION_INIT = "com.google.android.exoplayer.downloadService.action.INIT";
    public static final String ACTION_RELOAD_REQUIREMENTS = "com.google.android.exoplayer.downloadService.action.RELOAD_REQUIREMENTS";
    private static final String ACTION_RESTART = "com.google.android.exoplayer.downloadService.action.RESTART";
    private static final boolean DEBUG = false;
    public static final long DEFAULT_FOREGROUND_NOTIFICATION_UPDATE_INTERVAL = 1000;
    private static final Requirements DEFAULT_REQUIREMENTS = null;
    public static final int FOREGROUND_NOTIFICATION_ID_NONE = 0;
    public static final String KEY_DOWNLOAD_ACTION = "download_action";
    public static final String KEY_FOREGROUND = "foreground";
    private static final String TAG = "DownloadService";
    private final String channelId;
    private final int channelName;
    private DownloadManager downloadManager;
    private DownloadManagerListener downloadManagerListener;
    private final ForegroundNotificationUpdater foregroundNotificationUpdater;
    private int lastStartId;
    private static final HashMap requirementsHelpers;
    private boolean startedInForeground;
    private boolean taskRemoved;

    static {
        DownloadService.requirementsHelpers = new HashMap();
        DownloadService.DEFAULT_REQUIREMENTS = new Requirements(1, false, false);
    }

    protected DownloadService(int arg3) {
        this(arg3, 1000);
    }

    protected DownloadService(int arg7, long arg8) {
        this(arg7, arg8, null, 0);
    }

    protected DownloadService(int arg2, long arg3, String arg5, int arg6) {
        super();
        ForegroundNotificationUpdater v2 = arg2 == 0 ? null : new ForegroundNotificationUpdater(this, arg2, arg3);
        this.foregroundNotificationUpdater = v2;
        this.channelId = arg5;
        this.channelName = arg6;
    }

    static void access$200(DownloadService arg0, Requirements arg1) {
        arg0.maybeStartWatchingRequirements(arg1);
    }

    static ForegroundNotificationUpdater access$300(DownloadService arg0) {
        return arg0.foregroundNotificationUpdater;
    }

    static void access$400(DownloadService arg0) {
        arg0.stop();
    }

    static DownloadManager access$500(DownloadService arg0) {
        return arg0.downloadManager;
    }

    static Intent access$600(Context arg0, Class arg1, String arg2) {
        return DownloadService.getIntent(arg0, arg1, arg2);
    }

    public static Intent buildAddActionIntent(Context arg1, Class arg2, DownloadAction arg3, boolean arg4) {
        return DownloadService.getIntent(arg1, arg2, "com.google.android.exoplayer.downloadService.action.ADD").putExtra("download_action", arg3.toByteArray()).putExtra("foreground", arg4);
    }

    protected abstract DownloadManager getDownloadManager();

    protected Notification getForegroundNotification(TaskState[] arg3) {
        StringBuilder v0 = new StringBuilder();
        v0.append(this.getClass().getName());
        v0.append(" is started in the foreground but getForegroundNotification() is not implemented.");
        throw new IllegalStateException(v0.toString());
    }

    private static Intent getIntent(Context arg1, Class arg2, String arg3) {
        return new Intent(arg1, arg2).setAction(arg3);
    }

    protected Requirements getRequirements() {
        return DownloadService.DEFAULT_REQUIREMENTS;
    }

    protected abstract Scheduler getScheduler();

    private void logd(String arg1) {
    }

    private void maybeStartWatchingRequirements(Requirements arg9) {
        if(this.downloadManager.getDownloadCount() == 0) {
            return;
        }

        Class v0 = this.getClass();
        if(DownloadService.requirementsHelpers.get(v0) == null) {
            RequirementsHelper v7 = new RequirementsHelper(this, arg9, this.getScheduler(), v0, null);
            DownloadService.requirementsHelpers.put(v0, v7);
            v7.start();
            this.logd("started watching requirements");
        }
    }

    private void maybeStopWatchingRequirements() {
        if(this.downloadManager.getDownloadCount() > 0) {
            return;
        }

        this.stopWatchingRequirements();
    }

    public IBinder onBind(Intent arg1) {
        return null;
    }

    public void onCreate() {
        this.logd("onCreate");
        if(this.channelId != null) {
            NotificationUtil.createNotificationChannel(((Context)this), this.channelId, this.channelName, 2);
        }

        this.downloadManager = this.getDownloadManager();
        this.downloadManagerListener = new DownloadManagerListener(this, null);
        this.downloadManager.addListener(this.downloadManagerListener);
    }

    public void onDestroy() {
        this.logd("onDestroy");
        if(this.foregroundNotificationUpdater != null) {
            this.foregroundNotificationUpdater.stopPeriodicUpdates();
        }

        this.downloadManager.removeListener(this.downloadManagerListener);
        this.maybeStopWatchingRequirements();
    }

    public int onStartCommand(Intent arg5, int arg6, int arg7) {
        String v1;
        this.lastStartId = arg7;
        arg6 = 0;
        this.taskRemoved = false;
        if(arg5 != null) {
            v1 = arg5.getAction();
            boolean v2 = this.startedInForeground;
            int v3 = (arg5.getBooleanExtra("foreground", false)) || ("com.google.android.exoplayer.downloadService.action.RESTART".equals(v1)) ? 1 : 0;
            this.startedInForeground = (((int)v2)) | v3;
        }
        else {
            v1 = null;
        }

        if(v1 == null) {
            v1 = "com.google.android.exoplayer.downloadService.action.INIT";
        }

        this.logd("onStartCommand action: " + v1 + " startId: " + arg7);
        int v2_2 = v1.hashCode();
        if(v2_2 != -871181424) {
            if(v2_2 != -608867945) {
                if(v2_2 != -382886238) {
                    if(v2_2 != 1015676687) {
                        goto label_63;
                    }
                    else if(v1.equals("com.google.android.exoplayer.downloadService.action.INIT")) {
                    }
                    else {
                        goto label_63;
                    }
                }
                else if(v1.equals("com.google.android.exoplayer.downloadService.action.ADD")) {
                    arg6 = 2;
                }
                else {
                    goto label_63;
                }
            }
            else if(v1.equals("com.google.android.exoplayer.downloadService.action.RELOAD_REQUIREMENTS")) {
                arg6 = 3;
            }
            else {
                goto label_63;
            }
        }
        else if(v1.equals("com.google.android.exoplayer.downloadService.action.RESTART")) {
            arg6 = 1;
        }
        else {
        label_63:
            arg6 = -1;
        }

        switch(arg6) {
            case 0: 
            case 1: {
                goto label_89;
            }
            case 2: {
                goto label_76;
            }
            case 3: {
                goto label_74;
            }
        }

        String v5 = "DownloadService";
        String v6_1 = "Ignoring unrecognized action: " + v1;
        goto label_72;
    label_74:
        this.stopWatchingRequirements();
        goto label_89;
    label_76:
        byte[] v5_1 = arg5.getByteArrayExtra("download_action");
        if(v5_1 == null) {
            v5 = "DownloadService";
            v6_1 = "Ignoring ADD action with no action data";
        label_72:
            Log.e(v5, v6_1);
            goto label_89;
        }

        try {
            this.downloadManager.handleAction(v5_1);
        }
        catch(IOException v5_2) {
            Log.e("DownloadService", "Failed to handle ADD action", ((Throwable)v5_2));
        }

    label_89:
        Requirements v5_3 = this.getRequirements();
        if(v5_3.checkRequirements(((Context)this))) {
            this.downloadManager.startDownloads();
        }
        else {
            this.downloadManager.stopDownloads();
        }

        this.maybeStartWatchingRequirements(v5_3);
        if(this.downloadManager.isIdle()) {
            this.stop();
        }

        return 1;
    }

    public void onTaskRemoved(Intent arg3) {
        this.logd("onTaskRemoved rootIntent: " + arg3);
        this.taskRemoved = true;
    }

    protected void onTaskStateChanged(TaskState arg1) {
    }

    public static void start(Context arg1, Class arg2) {
        arg1.startService(DownloadService.getIntent(arg1, arg2, "com.google.android.exoplayer.downloadService.action.INIT"));
    }

    public static void startForeground(Context arg2, Class arg3) {
        Util.startForegroundService(arg2, DownloadService.getIntent(arg2, arg3, "com.google.android.exoplayer.downloadService.action.INIT").putExtra("foreground", true));
    }

    public static void startWithAction(Context arg0, Class arg1, DownloadAction arg2, boolean arg3) {
        Intent v1 = DownloadService.buildAddActionIntent(arg0, arg1, arg2, arg3);
        if(arg3) {
            Util.startForegroundService(arg0, v1);
        }
        else {
            arg0.startService(v1);
        }
    }

    private void stop() {
        String v0;
        if(this.foregroundNotificationUpdater != null) {
            this.foregroundNotificationUpdater.stopPeriodicUpdates();
            if((this.startedInForeground) && Util.SDK_INT >= 26) {
                this.foregroundNotificationUpdater.showNotificationIfNotAlready();
            }
        }

        if(Util.SDK_INT >= 28 || !this.taskRemoved) {
            boolean v0_1 = this.stopSelfResult(this.lastStartId);
            v0 = "stopSelf(" + this.lastStartId + ") result: " + v0_1;
        }
        else {
            this.stopSelf();
            v0 = "stopSelf()";
        }

        this.logd(v0);
    }

    private void stopWatchingRequirements() {
        Object v0 = DownloadService.requirementsHelpers.remove(this.getClass());
        if(v0 != null) {
            ((RequirementsHelper)v0).stop();
            this.logd("stopped watching requirements");
        }
    }
}

