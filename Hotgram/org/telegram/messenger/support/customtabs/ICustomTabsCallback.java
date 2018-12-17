package org.telegram.messenger.support.customtabs;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public interface ICustomTabsCallback extends IInterface {
    public abstract class Stub extends Binder implements ICustomTabsCallback {
        class Proxy implements ICustomTabsCallback {
            private IBinder mRemote;

            Proxy(IBinder arg1) {
                super();
                this.mRemote = arg1;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void extraCallback(String arg4, Bundle arg5) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.customtabs.ICustomTabsCallback");
                    v0.writeString(arg4);
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.mRemote.transact(3, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v4) {
                    v1.recycle();
                    v0.recycle();
                    throw v4;
                }

                v1.recycle();
                v0.recycle();
            }

            public String getInterfaceDescriptor() {
                return "android.support.customtabs.ICustomTabsCallback";
            }

            public void onMessageChannelReady(Bundle arg5) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.customtabs.ICustomTabsCallback");
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.mRemote.transact(4, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v5) {
                    v1.recycle();
                    v0.recycle();
                    throw v5;
                }

                v1.recycle();
                v0.recycle();
            }

            public void onNavigationEvent(int arg4, Bundle arg5) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.customtabs.ICustomTabsCallback");
                    v0.writeInt(arg4);
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.mRemote.transact(2, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v4) {
                    v1.recycle();
                    v0.recycle();
                    throw v4;
                }

                v1.recycle();
                v0.recycle();
            }

            public void onPostMessage(String arg4, Bundle arg5) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.customtabs.ICustomTabsCallback");
                    v0.writeString(arg4);
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.mRemote.transact(5, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v4) {
                    v1.recycle();
                    v0.recycle();
                    throw v4;
                }

                v1.recycle();
                v0.recycle();
            }
        }

        private static final String DESCRIPTOR = "android.support.customtabs.ICustomTabsCallback";
        static final int TRANSACTION_extraCallback = 3;
        static final int TRANSACTION_onMessageChannelReady = 4;
        static final int TRANSACTION_onNavigationEvent = 2;
        static final int TRANSACTION_onPostMessage = 5;

        public Stub() {
            super();
            this.attachInterface(((IInterface)this), "android.support.customtabs.ICustomTabsCallback");
        }

        public IBinder asBinder() {
            return this;
        }

        public static ICustomTabsCallback asInterface(IBinder arg2) {
            Proxy v0_1;
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("android.support.customtabs.ICustomTabsCallback");
            if(v0 == null || !(v0 instanceof ICustomTabsCallback)) {
                v0_1 = new Proxy(arg2);
            }
            else {
            }

            return ((ICustomTabsCallback)v0_1);
        }

        public boolean onTransact(int arg3, Parcel arg4, Parcel arg5, int arg6) {
            Object v0_1;
            if(arg3 == 1598968902) {
                goto label_50;
            }

            Bundle v0 = null;
            switch(arg3) {
                case 2: {
                    goto label_39;
                }
                case 3: {
                    goto label_28;
                }
                case 4: {
                    goto label_18;
                }
                case 5: {
                    goto label_7;
                }
            }

            return super.onTransact(arg3, arg4, arg5, arg6);
        label_18:
            arg4.enforceInterface("android.support.customtabs.ICustomTabsCallback");
            if(arg4.readInt() != 0) {
                v0_1 = Bundle.CREATOR.createFromParcel(arg4);
            }

            this.onMessageChannelReady(v0);
            arg5.writeNoException();
            return 1;
        label_39:
            arg4.enforceInterface("android.support.customtabs.ICustomTabsCallback");
            arg3 = arg4.readInt();
            if(arg4.readInt() != 0) {
                v0_1 = Bundle.CREATOR.createFromParcel(arg4);
            }

            this.onNavigationEvent(arg3, v0);
            arg5.writeNoException();
            return 1;
        label_7:
            arg4.enforceInterface("android.support.customtabs.ICustomTabsCallback");
            String v3 = arg4.readString();
            if(arg4.readInt() != 0) {
                v0_1 = Bundle.CREATOR.createFromParcel(arg4);
            }

            this.onPostMessage(v3, v0);
            arg5.writeNoException();
            return 1;
        label_28:
            arg4.enforceInterface("android.support.customtabs.ICustomTabsCallback");
            v3 = arg4.readString();
            if(arg4.readInt() != 0) {
                v0_1 = Bundle.CREATOR.createFromParcel(arg4);
            }

            this.extraCallback(v3, v0);
            arg5.writeNoException();
            return 1;
        label_50:
            arg5.writeString("android.support.customtabs.ICustomTabsCallback");
            return 1;
        }
    }

    void extraCallback(String arg1, Bundle arg2);

    void onMessageChannelReady(Bundle arg1);

    void onNavigationEvent(int arg1, Bundle arg2);

    void onPostMessage(String arg1, Bundle arg2);
}

