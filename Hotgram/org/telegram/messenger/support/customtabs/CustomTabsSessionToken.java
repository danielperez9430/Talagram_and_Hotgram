package org.telegram.messenger.support.customtabs;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.f;
import android.util.Log;

public class CustomTabsSessionToken {
    class DummyCallback extends Stub {
        DummyCallback() {
            super();
        }

        public IBinder asBinder() {
            return this;
        }

        public void extraCallback(String arg1, Bundle arg2) {
        }

        public void onMessageChannelReady(Bundle arg1) {
        }

        public void onNavigationEvent(int arg1, Bundle arg2) {
        }

        public void onPostMessage(String arg1, Bundle arg2) {
        }
    }

    private static final String TAG = "CustomTabsSessionToken";
    private final CustomTabsCallback mCallback;
    private final ICustomTabsCallback mCallbackBinder;

    CustomTabsSessionToken(ICustomTabsCallback arg1) {
        super();
        this.mCallbackBinder = arg1;
        this.mCallback = new CustomTabsCallback() {
            public void extraCallback(String arg2, Bundle arg3) {
                try {
                    CustomTabsSessionToken.access$000(CustomTabsSessionToken.this).extraCallback(arg2, arg3);
                }
                catch(RemoteException ) {
                    Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
                }
            }

            public void onMessageChannelReady(Bundle arg2) {
                try {
                    CustomTabsSessionToken.access$000(CustomTabsSessionToken.this).onMessageChannelReady(arg2);
                }
                catch(RemoteException ) {
                    Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
                }
            }

            public void onNavigationEvent(int arg2, Bundle arg3) {
                try {
                    CustomTabsSessionToken.access$000(CustomTabsSessionToken.this).onNavigationEvent(arg2, arg3);
                }
                catch(RemoteException ) {
                    Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
                }
            }

            public void onPostMessage(String arg2, Bundle arg3) {
                try {
                    CustomTabsSessionToken.access$000(CustomTabsSessionToken.this).onPostMessage(arg2, arg3);
                }
                catch(RemoteException ) {
                    Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
                }
            }
        };
    }

    static ICustomTabsCallback access$000(CustomTabsSessionToken arg0) {
        return arg0.mCallbackBinder;
    }

    public static CustomTabsSessionToken createDummySessionTokenForTesting() {
        return new CustomTabsSessionToken(new DummyCallback());
    }

    public boolean equals(Object arg2) {
        if(!(arg2 instanceof CustomTabsSessionToken)) {
            return 0;
        }

        return ((CustomTabsSessionToken)arg2).getCallbackBinder().equals(this.mCallbackBinder.asBinder());
    }

    public CustomTabsCallback getCallback() {
        return this.mCallback;
    }

    IBinder getCallbackBinder() {
        return this.mCallbackBinder.asBinder();
    }

    public static CustomTabsSessionToken getSessionTokenFromIntent(Intent arg1) {
        IBinder v1 = f.a(arg1.getExtras(), "android.support.customtabs.extra.SESSION");
        if(v1 == null) {
            return null;
        }

        return new CustomTabsSessionToken(Stub.asInterface(v1));
    }

    public int hashCode() {
        return this.getCallbackBinder().hashCode();
    }

    public boolean isAssociatedWith(CustomTabsSession arg2) {
        return arg2.getBinder().equals(this.mCallbackBinder);
    }
}

