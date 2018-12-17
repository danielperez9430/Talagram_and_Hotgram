package org.telegram.messenger.support.customtabs;

import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.List;

public interface ICustomTabsService extends IInterface {
    public abstract class Stub extends Binder implements ICustomTabsService {
        class Proxy implements ICustomTabsService {
            private IBinder mRemote;

            Proxy(IBinder arg1) {
                super();
                this.mRemote = arg1;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public Bundle extraCommand(String arg4, Bundle arg5) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.customtabs.ICustomTabsService");
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
                    if(v1.readInt() != 0) {
                        Object v4_1 = Bundle.CREATOR.createFromParcel(v1);
                    }
                    else {
                        goto label_21;
                    }

                    goto label_22;
                }
                catch(Throwable v4) {
                    v1.recycle();
                    v0.recycle();
                    throw v4;
                }

            label_21:
                Bundle v4_2 = null;
            label_22:
                v1.recycle();
                v0.recycle();
                return v4_2;
            }

            public String getInterfaceDescriptor() {
                return "android.support.customtabs.ICustomTabsService";
            }

            public boolean mayLaunchUrl(ICustomTabsCallback arg4, Uri arg5, Bundle arg6, List arg7) {
                boolean v4_2;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.customtabs.ICustomTabsService");
                    IBinder v4_1 = arg4 != null ? arg4.asBinder() : null;
                    v0.writeStrongBinder(v4_1);
                    v4_2 = true;
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    if(arg6 != null) {
                        v0.writeInt(1);
                        arg6.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    v0.writeTypedList(arg7);
                    this.mRemote.transact(4, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() == 0) {
                        goto label_29;
                    }
                }
                catch(Throwable v4) {
                    v1.recycle();
                    v0.recycle();
                    throw v4;
                }

                goto label_30;
            label_29:
                v4_2 = false;
            label_30:
                v1.recycle();
                v0.recycle();
                return v4_2;
            }

            public boolean newSession(ICustomTabsCallback arg5) {
                boolean v3;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.customtabs.ICustomTabsService");
                    IBinder v5_1 = arg5 != null ? arg5.asBinder() : null;
                    v0.writeStrongBinder(v5_1);
                    v3 = false;
                    this.mRemote.transact(3, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() == 0) {
                        goto label_17;
                    }
                }
                catch(Throwable v5) {
                    v1.recycle();
                    v0.recycle();
                    throw v5;
                }

                v3 = true;
            label_17:
                v1.recycle();
                v0.recycle();
                return v3;
            }

            public int postMessage(ICustomTabsCallback arg4, String arg5, Bundle arg6) {
                int v4_2;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.customtabs.ICustomTabsService");
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

                    this.mRemote.transact(8, v0, v1, 0);
                    v1.readException();
                    v4_2 = v1.readInt();
                }
                catch(Throwable v4) {
                    v1.recycle();
                    v0.recycle();
                    throw v4;
                }

                v1.recycle();
                v0.recycle();
                return v4_2;
            }

            public boolean requestPostMessageChannel(ICustomTabsCallback arg5, Uri arg6) {
                boolean v5_2;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.customtabs.ICustomTabsService");
                    IBinder v5_1 = arg5 != null ? arg5.asBinder() : null;
                    v0.writeStrongBinder(v5_1);
                    v5_2 = true;
                    if(arg6 != null) {
                        v0.writeInt(1);
                        arg6.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.mRemote.transact(7, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() == 0) {
                        goto label_23;
                    }
                }
                catch(Throwable v5) {
                    v1.recycle();
                    v0.recycle();
                    throw v5;
                }

                goto label_24;
            label_23:
                v5_2 = false;
            label_24:
                v1.recycle();
                v0.recycle();
                return v5_2;
            }

            public boolean updateVisuals(ICustomTabsCallback arg5, Bundle arg6) {
                boolean v5_2;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.customtabs.ICustomTabsService");
                    IBinder v5_1 = arg5 != null ? arg5.asBinder() : null;
                    v0.writeStrongBinder(v5_1);
                    v5_2 = true;
                    if(arg6 != null) {
                        v0.writeInt(1);
                        arg6.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.mRemote.transact(6, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() == 0) {
                        goto label_23;
                    }
                }
                catch(Throwable v5) {
                    v1.recycle();
                    v0.recycle();
                    throw v5;
                }

                goto label_24;
            label_23:
                v5_2 = false;
            label_24:
                v1.recycle();
                v0.recycle();
                return v5_2;
            }

            public boolean warmup(long arg4) {
                boolean v2;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.customtabs.ICustomTabsService");
                    v0.writeLong(arg4);
                    v2 = false;
                    this.mRemote.transact(2, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() == 0) {
                        goto label_13;
                    }
                }
                catch(Throwable v4) {
                    v1.recycle();
                    v0.recycle();
                    throw v4;
                }

                v2 = true;
            label_13:
                v1.recycle();
                v0.recycle();
                return v2;
            }
        }

        private static final String DESCRIPTOR = "android.support.customtabs.ICustomTabsService";
        static final int TRANSACTION_extraCommand = 5;
        static final int TRANSACTION_mayLaunchUrl = 4;
        static final int TRANSACTION_newSession = 3;
        static final int TRANSACTION_postMessage = 8;
        static final int TRANSACTION_requestPostMessageChannel = 7;
        static final int TRANSACTION_updateVisuals = 6;
        static final int TRANSACTION_warmup = 2;

        public Stub() {
            super();
            this.attachInterface(((IInterface)this), "android.support.customtabs.ICustomTabsService");
        }

        public IBinder asBinder() {
            return this;
        }

        public static ICustomTabsService asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("android.support.customtabs.ICustomTabsService");
            if(v0 == null || !(v0 instanceof ICustomTabsService)) {
                Proxy v0_1 = new Proxy(arg2);
            }
            else {
            }

            return ((ICustomTabsService)v0);
        }

        public boolean onTransact(int arg4, Parcel arg5, Parcel arg6, int arg7) {
            Object v7_1;
            Object v0_1;
            if(arg4 == 1598968902) {
                goto label_99;
            }

            Bundle v0 = null;
            switch(arg4) {
                case 2: {
                    goto label_92;
                }
                case 3: {
                    goto label_84;
                }
                case 4: {
                    goto label_64;
                }
                case 5: {
                    goto label_47;
                }
                case 6: {
                    goto label_34;
                }
                case 7: {
                    goto label_21;
                }
                case 8: {
                    goto label_7;
                }
            }

            return super.onTransact(arg4, arg5, arg6, arg7);
        label_34:
            arg5.enforceInterface("android.support.customtabs.ICustomTabsService");
            ICustomTabsCallback v4 = org.telegram.messenger.support.customtabs.ICustomTabsCallback$Stub.asInterface(arg5.readStrongBinder());
            if(arg5.readInt() != 0) {
                v0_1 = Bundle.CREATOR.createFromParcel(arg5);
            }

            boolean v4_1 = this.updateVisuals(v4, ((Bundle)v0_1));
            arg6.writeNoException();
            arg6.writeInt(((int)v4_1));
            return 1;
        label_84:
            arg5.enforceInterface("android.support.customtabs.ICustomTabsService");
            v4_1 = this.newSession(org.telegram.messenger.support.customtabs.ICustomTabsCallback$Stub.asInterface(arg5.readStrongBinder()));
            arg6.writeNoException();
            arg6.writeInt(((int)v4_1));
            return 1;
        label_21:
            arg5.enforceInterface("android.support.customtabs.ICustomTabsService");
            v4 = org.telegram.messenger.support.customtabs.ICustomTabsCallback$Stub.asInterface(arg5.readStrongBinder());
            if(arg5.readInt() != 0) {
                v0_1 = Uri.CREATOR.createFromParcel(arg5);
            }

            v4_1 = this.requestPostMessageChannel(v4, ((Uri)v0_1));
            arg6.writeNoException();
            arg6.writeInt(((int)v4_1));
            return 1;
        label_7:
            arg5.enforceInterface("android.support.customtabs.ICustomTabsService");
            v4 = org.telegram.messenger.support.customtabs.ICustomTabsCallback$Stub.asInterface(arg5.readStrongBinder());
            String v7 = arg5.readString();
            if(arg5.readInt() != 0) {
                v0_1 = Bundle.CREATOR.createFromParcel(arg5);
            }

            arg4 = this.postMessage(v4, v7, ((Bundle)v0_1));
            arg6.writeNoException();
            arg6.writeInt(arg4);
            return 1;
        label_92:
            arg5.enforceInterface("android.support.customtabs.ICustomTabsService");
            v4_1 = this.warmup(arg5.readLong());
            arg6.writeNoException();
            arg6.writeInt(((int)v4_1));
            return 1;
        label_47:
            arg5.enforceInterface("android.support.customtabs.ICustomTabsService");
            String v4_2 = arg5.readString();
            if(arg5.readInt() != 0) {
                v0_1 = Bundle.CREATOR.createFromParcel(arg5);
            }

            Bundle v4_3 = this.extraCommand(v4_2, ((Bundle)v0_1));
            arg6.writeNoException();
            if(v4_3 != null) {
                arg6.writeInt(1);
                v4_3.writeToParcel(arg6, 1);
            }
            else {
                arg6.writeInt(0);
            }

            return 1;
        label_64:
            arg5.enforceInterface("android.support.customtabs.ICustomTabsService");
            v4 = org.telegram.messenger.support.customtabs.ICustomTabsCallback$Stub.asInterface(arg5.readStrongBinder());
            if(arg5.readInt() != 0) {
                v7_1 = Uri.CREATOR.createFromParcel(arg5);
            }
            else {
                Uri v7_2 = ((Uri)v0);
            }

            if(arg5.readInt() != 0) {
                v0_1 = Bundle.CREATOR.createFromParcel(arg5);
            }

            v4_1 = this.mayLaunchUrl(v4, ((Uri)v7_1), ((Bundle)v0_1), arg5.createTypedArrayList(Bundle.CREATOR));
            arg6.writeNoException();
            arg6.writeInt(((int)v4_1));
            return 1;
        label_99:
            arg6.writeString("android.support.customtabs.ICustomTabsService");
            return 1;
        }
    }

    Bundle extraCommand(String arg1, Bundle arg2);

    boolean mayLaunchUrl(ICustomTabsCallback arg1, Uri arg2, Bundle arg3, List arg4);

    boolean newSession(ICustomTabsCallback arg1);

    int postMessage(ICustomTabsCallback arg1, String arg2, Bundle arg3);

    boolean requestPostMessageChannel(ICustomTabsCallback arg1, Uri arg2);

    boolean updateVisuals(ICustomTabsCallback arg1, Bundle arg2);

    boolean warmup(long arg1);
}

