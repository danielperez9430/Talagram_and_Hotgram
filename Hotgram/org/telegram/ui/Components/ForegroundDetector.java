package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application$ActivityLifecycleCallbacks;
import android.app.Application;
import android.os.Build$VERSION;
import android.os.Bundle;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;

@SuppressLint(value={"NewApi"}) public class ForegroundDetector implements Application$ActivityLifecycleCallbacks {
    public interface Listener {
        void onBecameBackground();

        void onBecameForeground();
    }

    private static ForegroundDetector Instance;
    private long enterBackgroundTime;
    private CopyOnWriteArrayList listeners;
    private int refs;
    private boolean wasInBackground;

    static {
    }

    public ForegroundDetector(Application arg3) {
        super();
        this.wasInBackground = true;
        this.enterBackgroundTime = 0;
        this.listeners = new CopyOnWriteArrayList();
        ForegroundDetector.Instance = this;
        arg3.registerActivityLifecycleCallbacks(((Application$ActivityLifecycleCallbacks)this));
    }

    public void addListener(Listener arg2) {
        this.listeners.add(arg2);
    }

    public static ForegroundDetector getInstance() {
        return ForegroundDetector.Instance;
    }

    public boolean isBackground() {
        boolean v0 = this.refs == 0 ? true : false;
        return v0;
    }

    public boolean isForeground() {
        boolean v0 = this.refs > 0 ? true : false;
        return v0;
    }

    public boolean isWasInBackground(boolean arg5) {
        if((arg5) && Build$VERSION.SDK_INT >= 21 && System.currentTimeMillis() - this.enterBackgroundTime < 200) {
            this.wasInBackground = false;
        }

        return this.wasInBackground;
    }

    public void onActivityCreated(Activity arg1, Bundle arg2) {
    }

    public void onActivityDestroyed(Activity arg1) {
    }

    public void onActivityPaused(Activity arg1) {
    }

    public void onActivityResumed(Activity arg1) {
    }

    public void onActivitySaveInstanceState(Activity arg1, Bundle arg2) {
    }

    public void onActivityStarted(Activity arg5) {
        int v5 = this.refs + 1;
        this.refs = v5;
        if(v5 == 1) {
            if(System.currentTimeMillis() - this.enterBackgroundTime < 200) {
                this.wasInBackground = false;
            }

            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("switch to foreground");
            }

            Iterator v5_1 = this.listeners.iterator();
            while(v5_1.hasNext()) {
                Object v0 = v5_1.next();
                try {
                    ((Listener)v0).onBecameForeground();
                }
                catch(Exception v0_1) {
                    FileLog.e(((Throwable)v0_1));
                }
            }
        }
    }

    public void onActivityStopped(Activity arg4) {
        int v4 = this.refs - 1;
        this.refs = v4;
        if(v4 == 0) {
            this.enterBackgroundTime = System.currentTimeMillis();
            this.wasInBackground = true;
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("switch to background");
            }

            Iterator v4_1 = this.listeners.iterator();
            while(v4_1.hasNext()) {
                Object v0 = v4_1.next();
                try {
                    ((Listener)v0).onBecameBackground();
                }
                catch(Exception v0_1) {
                    FileLog.e(((Throwable)v0_1));
                }
            }
        }
    }

    public void removeListener(Listener arg2) {
        this.listeners.remove(arg2);
    }

    public void resetBackgroundVar() {
        this.wasInBackground = false;
    }
}

