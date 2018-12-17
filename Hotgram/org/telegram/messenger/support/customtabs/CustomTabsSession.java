package org.telegram.messenger.support.customtabs;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.RemoteException;
import android.widget.RemoteViews;
import java.util.List;

public final class CustomTabsSession {
    private static final String TAG = "CustomTabsSession";
    private final ICustomTabsCallback mCallback;
    private final ComponentName mComponentName;
    private final Object mLock;
    private final ICustomTabsService mService;

    CustomTabsSession(ICustomTabsService arg2, ICustomTabsCallback arg3, ComponentName arg4) {
        super();
        this.mLock = new Object();
        this.mService = arg2;
        this.mCallback = arg3;
        this.mComponentName = arg4;
    }

    public static CustomTabsSession createDummySessionForTesting(ComponentName arg3) {
        return new CustomTabsSession(null, new DummyCallback(), arg3);
    }

    IBinder getBinder() {
        return this.mCallback.asBinder();
    }

    ComponentName getComponentName() {
        return this.mComponentName;
    }

    public boolean mayLaunchUrl(Uri arg3, Bundle arg4, List arg5) {
        try {
            return this.mService.mayLaunchUrl(this.mCallback, arg3, arg4, arg5);
        }
        catch(RemoteException ) {
            return 0;
        }
    }

    public int postMessage(String arg4, Bundle arg5) {
        int v4;
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            v4 = this.mService.postMessage(this.mCallback, arg4, arg5);
        }
        catch(RemoteException ) {
            __monitor_exit(v0);
            return -2;
        }

        __monitor_exit(v0);
        return v4;
        __monitor_exit(v0);
        throw v4_1;
    }

    public boolean requestPostMessageChannel(Uri arg3) {
        try {
            return this.mService.requestPostMessageChannel(this.mCallback, arg3);
        }
        catch(RemoteException ) {
            return 0;
        }
    }

    public boolean setActionButton(Bitmap arg3, String arg4) {
        Bundle v0 = new Bundle();
        v0.putParcelable("android.support.customtabs.customaction.ICON", ((Parcelable)arg3));
        v0.putString("android.support.customtabs.customaction.DESCRIPTION", arg4);
        Bundle v3 = new Bundle();
        v3.putBundle("android.support.customtabs.extra.ACTION_BUTTON_BUNDLE", v0);
        try {
            return this.mService.updateVisuals(this.mCallback, v3);
        }
        catch(RemoteException ) {
            return 0;
        }
    }

    public boolean setSecondaryToolbarViews(RemoteViews arg3, int[] arg4, PendingIntent arg5) {
        Bundle v0 = new Bundle();
        v0.putParcelable("android.support.customtabs.extra.EXTRA_REMOTEVIEWS", ((Parcelable)arg3));
        v0.putIntArray("android.support.customtabs.extra.EXTRA_REMOTEVIEWS_VIEW_IDS", arg4);
        v0.putParcelable("android.support.customtabs.extra.EXTRA_REMOTEVIEWS_PENDINGINTENT", ((Parcelable)arg5));
        try {
            return this.mService.updateVisuals(this.mCallback, v0);
        }
        catch(RemoteException ) {
            return 0;
        }
    }

    @Deprecated public boolean setToolbarItem(int arg3, Bitmap arg4, String arg5) {
        Bundle v0 = new Bundle();
        v0.putInt("android.support.customtabs.customaction.ID", arg3);
        v0.putParcelable("android.support.customtabs.customaction.ICON", ((Parcelable)arg4));
        v0.putString("android.support.customtabs.customaction.DESCRIPTION", arg5);
        Bundle v3 = new Bundle();
        v3.putBundle("android.support.customtabs.extra.ACTION_BUTTON_BUNDLE", v0);
        try {
            return this.mService.updateVisuals(this.mCallback, v3);
        }
        catch(RemoteException ) {
            return 0;
        }
    }
}

