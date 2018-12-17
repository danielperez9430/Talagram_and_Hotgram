package org.telegram.messenger.support.customtabs;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public interface IPostMessageService extends IInterface {
    public abstract class Stub extends Binder implements IPostMessageService {
        class Proxy implements IPostMessageService {
            private IBinder mRemote;

            Proxy(IBinder arg1) {
                super();
                this.mRemote = arg1;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "android.support.customtabs.IPostMessageService";
            }

            public void onMessageChannelReady(ICustomTabsCallback arg4, Bundle arg5) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.customtabs.IPostMessageService");
                    IBinder v4_1 = arg4 != null ? arg4.asBinder() : null;
                    v0.writeStrongBinder(v4_1);
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

            public void onPostMessage(ICustomTabsCallback arg4, String arg5, Bundle arg6) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.customtabs.IPostMessageService");
                    IBinder v4_1 = arg4 != null ? arg4.asBinder() : null;
                    v0.writeStrongBinder(v4_1);
                    v0.writeString(arg5);
                    if(arg6 != null) {
                        v0.writeInt(1);
                        arg6.writeToParcel(v0, 0);
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
        }

        private static final String DESCRIPTOR = "android.support.customtabs.IPostMessageService";
        static final int TRANSACTION_onMessageChannelReady = 2;
        static final int TRANSACTION_onPostMessage = 3;

        public Stub() {
            super();
            this.attachInterface(((IInterface)this), "android.support.customtabs.IPostMessageService");
        }

        public IBinder asBinder() {
            return this;
        }

        public static IPostMessageService asInterface(IBinder arg2) {
            Proxy v0_1;
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("android.support.customtabs.IPostMessageService");
            if(v0 == null || !(v0 instanceof IPostMessageService)) {
                v0_1 = new Proxy(arg2);
            }
            else {
            }

            return ((IPostMessageService)v0_1);
        }

        public boolean onTransact(int arg4, Parcel arg5, Parcel arg6, int arg7) {
            Object v0_1;
            if(arg4 == 1598968902) {
                goto label_32;
            }

            Bundle v0 = null;
            switch(arg4) {
                case 2: {
                    goto label_20;
                }
                case 3: {
                    goto label_7;
                }
            }

            return super.onTransact(arg4, arg5, arg6, arg7);
        label_20:
            arg5.enforceInterface("android.support.customtabs.IPostMessageService");
            ICustomTabsCallback v4 = org.telegram.messenger.support.customtabs.ICustomTabsCallback$Stub.asInterface(arg5.readStrongBinder());
            if(arg5.readInt() != 0) {
                v0_1 = Bundle.CREATOR.createFromParcel(arg5);
            }

            this.onMessageChannelReady(v4, v0);
            arg6.writeNoException();
            return 1;
        label_7:
            arg5.enforceInterface("android.support.customtabs.IPostMessageService");
            v4 = org.telegram.messenger.support.customtabs.ICustomTabsCallback$Stub.asInterface(arg5.readStrongBinder());
            String v7 = arg5.readString();
            if(arg5.readInt() != 0) {
                v0_1 = Bundle.CREATOR.createFromParcel(arg5);
            }

            this.onPostMessage(v4, v7, v0);
            arg6.writeNoException();
            return 1;
        label_32:
            arg6.writeString("android.support.customtabs.IPostMessageService");
            return 1;
        }
    }

    void onMessageChannelReady(ICustomTabsCallback arg1, Bundle arg2);

    void onPostMessage(ICustomTabsCallback arg1, String arg2, Bundle arg3);
}

