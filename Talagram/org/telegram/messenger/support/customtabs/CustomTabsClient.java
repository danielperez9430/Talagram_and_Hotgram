package org.telegram.messenger.support.customtabs;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CustomTabsClient {
    private final ICustomTabsService mService;
    private final ComponentName mServiceComponentName;

    CustomTabsClient(ICustomTabsService arg1, ComponentName arg2) {
        super();
        this.mService = arg1;
        this.mServiceComponentName = arg2;
    }

    public static boolean bindCustomTabsService(Context arg2, String arg3, CustomTabsServiceConnection arg4) {
        Intent v0 = new Intent("android.support.customtabs.action.CustomTabsService");
        if(!TextUtils.isEmpty(((CharSequence)arg3))) {
            v0.setPackage(arg3);
        }

        return arg2.bindService(v0, ((ServiceConnection)arg4), 33);
    }

    public static boolean connectAndInitialize(Context arg2, String arg3) {
        if(arg3 == null) {
            return 0;
        }

        arg2 = arg2.getApplicationContext();
        org.telegram.messenger.support.customtabs.CustomTabsClient$1 v1 = new CustomTabsServiceConnection(arg2) {
            public final void onCustomTabsServiceConnected(ComponentName arg3, CustomTabsClient arg4) {
                arg4.warmup(0);
                this.val$applicationContext.unbindService(((ServiceConnection)this));
            }

            public final void onServiceDisconnected(ComponentName arg1) {
            }
        };
        try {
            return CustomTabsClient.bindCustomTabsService(arg2, arg3, ((CustomTabsServiceConnection)v1));
        }
        catch(SecurityException ) {
            return 0;
        }
    }

    public Bundle extraCommand(String arg2, Bundle arg3) {
        try {
            return this.mService.extraCommand(arg2, arg3);
        }
        catch(RemoteException ) {
            return null;
        }
    }

    public static String getPackageName(Context arg1, List arg2) {
        return CustomTabsClient.getPackageName(arg1, arg2, false);
    }

    public static String getPackageName(Context arg4, List arg5, boolean arg6) {
        Object v0_2;
        List v0_1;
        ArrayList v0;
        PackageManager v4 = arg4.getPackageManager();
        if(arg5 == null) {
            v0 = new ArrayList();
        }
        else {
            v0_1 = arg5;
        }

        Intent v1 = new Intent("android.intent.action.VIEW", Uri.parse("http://"));
        if(!arg6) {
            ResolveInfo v6 = v4.resolveActivity(v1, 0);
            if(v6 != null) {
                String v6_1 = v6.activityInfo.packageName;
                ArrayList v1_1 = new ArrayList(v0_1.size() + 1);
                ((List)v1_1).add(v6_1);
                if(arg5 != null) {
                    ((List)v1_1).addAll(((Collection)arg5));
                }

                v0 = v1_1;
            }
        }

        Intent v5 = new Intent("android.support.customtabs.action.CustomTabsService");
        Iterator v6_2 = v0_1.iterator();
        do {
            if(!v6_2.hasNext()) {
                return null;
            }

            v0_2 = v6_2.next();
            v5.setPackage(((String)v0_2));
        }
        while(v4.resolveService(v5, 0) == null);

        return ((String)v0_2);
    }

    public CustomTabsSession newSession(CustomTabsCallback arg4) {
        org.telegram.messenger.support.customtabs.CustomTabsClient$2 v0 = new Stub(arg4) {
            private Handler mHandler;

            public void extraCallback(String arg3, Bundle arg4) {
                if(this.val$callback == null) {
                    return;
                }

                this.mHandler.post(new Runnable(arg3, arg4) {
                    public void run() {
                        this.this$1.val$callback.extraCallback(this.val$callbackName, this.val$args);
                    }
                });
            }

            public void onMessageChannelReady(Bundle arg3) {
                if(this.val$callback == null) {
                    return;
                }

                this.mHandler.post(new Runnable(arg3) {
                    public void run() {
                        this.this$1.val$callback.onMessageChannelReady(this.val$extras);
                    }
                });
            }

            public void onNavigationEvent(int arg3, Bundle arg4) {
                if(this.val$callback == null) {
                    return;
                }

                this.mHandler.post(new Runnable(arg3, arg4) {
                    public void run() {
                        this.this$1.val$callback.onNavigationEvent(this.val$navigationEvent, this.val$extras);
                    }
                });
            }

            public void onPostMessage(String arg3, Bundle arg4) {
                if(this.val$callback == null) {
                    return;
                }

                this.mHandler.post(new Runnable(arg3, arg4) {
                    public void run() {
                        this.this$1.val$callback.onPostMessage(this.val$message, this.val$extras);
                    }
                });
            }
        };
        CustomTabsSession v4 = null;
        try {
            if(this.mService.newSession(((ICustomTabsCallback)v0))) {
                goto label_7;
            }
        }
        catch(RemoteException ) {
            return v4;
        }

        return v4;
    label_7:
        return new CustomTabsSession(this.mService, ((ICustomTabsCallback)v0), this.mServiceComponentName);
    }

    public boolean warmup(long arg2) {
        try {
            return this.mService.warmup(arg2);
        }
        catch(RemoteException ) {
            return 0;
        }
    }
}

