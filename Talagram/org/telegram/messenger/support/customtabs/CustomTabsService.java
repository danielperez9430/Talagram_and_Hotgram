package org.telegram.messenger.support.customtabs;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder$DeathRecipient;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.f.a;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public abstract class CustomTabsService extends Service {
    class org.telegram.messenger.support.customtabs.CustomTabsService$1 extends Stub {
        org.telegram.messenger.support.customtabs.CustomTabsService$1(CustomTabsService arg1) {
            CustomTabsService.this = arg1;
            super();
        }

        public Bundle extraCommand(String arg2, Bundle arg3) {
            return CustomTabsService.this.extraCommand(arg2, arg3);
        }

        public boolean mayLaunchUrl(ICustomTabsCallback arg3, Uri arg4, Bundle arg5, List arg6) {
            return CustomTabsService.this.mayLaunchUrl(new CustomTabsSessionToken(arg3), arg4, arg5, arg6);
        }

        public boolean newSession(ICustomTabsCallback arg6) {
            Map v3;
            org.telegram.messenger.support.customtabs.CustomTabsService$1$1 v2;
            CustomTabsSessionToken v0 = new CustomTabsSessionToken(arg6);
            try {
                v2 = new IBinder$DeathRecipient(v0) {
                    public void binderDied() {
                        this.this$1.this$0.cleanUpSession(this.val$sessionToken);
                    }
                };
                v3 = CustomTabsService.this.mDeathRecipientMap;
                __monitor_enter(v3);
            }
            catch(RemoteException ) {
                return 0;
            }

            try {
                arg6.asBinder().linkToDeath(((IBinder$DeathRecipient)v2), 0);
                CustomTabsService.this.mDeathRecipientMap.put(arg6.asBinder(), v2);
                __monitor_exit(v3);
                goto label_15;
            }
            catch(Throwable v6) {
                try {
                label_19:
                    __monitor_exit(v3);
                }
                catch(Throwable v6) {
                    goto label_19;
                }

                try {
                    throw v6;
                label_15:
                    return CustomTabsService.this.newSession(v0);
                }
                catch(RemoteException ) {
                    return 0;
                }
            }
        }

        public int postMessage(ICustomTabsCallback arg3, String arg4, Bundle arg5) {
            return CustomTabsService.this.postMessage(new CustomTabsSessionToken(arg3), arg4, arg5);
        }

        public boolean requestPostMessageChannel(ICustomTabsCallback arg3, Uri arg4) {
            return CustomTabsService.this.requestPostMessageChannel(new CustomTabsSessionToken(arg3), arg4);
        }

        public boolean updateVisuals(ICustomTabsCallback arg3, Bundle arg4) {
            return CustomTabsService.this.updateVisuals(new CustomTabsSessionToken(arg3), arg4);
        }

        public boolean warmup(long arg2) {
            return CustomTabsService.this.warmup(arg2);
        }
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface Result {
    }

    public static final String ACTION_CUSTOM_TABS_CONNECTION = "android.support.customtabs.action.CustomTabsService";
    public static final String KEY_URL = "android.support.customtabs.otherurls.URL";
    public static final int RESULT_FAILURE_DISALLOWED = -1;
    public static final int RESULT_FAILURE_MESSAGING_ERROR = -3;
    public static final int RESULT_FAILURE_REMOTE_ERROR = -2;
    public static final int RESULT_SUCCESS;
    private Stub mBinder;
    private final Map mDeathRecipientMap;

    public CustomTabsService() {
        super();
        this.mDeathRecipientMap = new a();
        this.mBinder = new org.telegram.messenger.support.customtabs.CustomTabsService$1(this);
    }

    static Map access$000(CustomTabsService arg0) {
        return arg0.mDeathRecipientMap;
    }

    protected boolean cleanUpSession(CustomTabsSessionToken arg4) {
        Map v1;
        try {
            v1 = this.mDeathRecipientMap;
            __monitor_enter(v1);
        }
        catch(NoSuchElementException ) {
            return 0;
        }

        try {
            IBinder v4_1 = arg4.getCallbackBinder();
            v4_1.unlinkToDeath(this.mDeathRecipientMap.get(v4_1), 0);
            this.mDeathRecipientMap.remove(v4_1);
            __monitor_exit(v1);
            return 1;
        label_13:
            __monitor_exit(v1);
        }
        catch(Throwable v4) {
            goto label_13;
        }

        try {
            throw v4;
        }
        catch(NoSuchElementException ) {
            return 0;
        }
    }

    protected abstract Bundle extraCommand(String arg1, Bundle arg2);

    protected abstract boolean mayLaunchUrl(CustomTabsSessionToken arg1, Uri arg2, Bundle arg3, List arg4);

    protected abstract boolean newSession(CustomTabsSessionToken arg1);

    public IBinder onBind(Intent arg1) {
        return this.mBinder;
    }

    protected abstract int postMessage(CustomTabsSessionToken arg1, String arg2, Bundle arg3);

    protected abstract boolean requestPostMessageChannel(CustomTabsSessionToken arg1, Uri arg2);

    protected abstract boolean updateVisuals(CustomTabsSessionToken arg1, Bundle arg2);

    protected abstract boolean warmup(long arg1);
}

