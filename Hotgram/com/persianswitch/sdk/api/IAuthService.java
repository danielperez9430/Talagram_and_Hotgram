package com.persianswitch.sdk.api;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public interface IAuthService extends IInterface {
    public abstract class Stub extends Binder implements IAuthService {
        class Proxy implements IAuthService {
            private IBinder a;

            public void a(Bundle arg5) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("com.persianswitch.sdk.api.IAuthService");
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.a.transact(1, v0, v1, 0);
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

            public IBinder asBinder() {
                return this.a;
            }

            public Bundle b(Bundle arg5) {
                Object v5_1;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("com.persianswitch.sdk.api.IAuthService");
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.a.transact(2, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() != 0) {
                        v5_1 = Bundle.CREATOR.createFromParcel(v1);
                    }
                    else {
                        goto label_20;
                    }

                    goto label_21;
                }
                catch(Throwable v5) {
                    v1.recycle();
                    v0.recycle();
                    throw v5;
                }

            label_20:
                arg5 = null;
            label_21:
                v1.recycle();
                v0.recycle();
                return ((Bundle)v5_1);
            }

            public Bundle c(Bundle arg5) {
                Object v5_1;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("com.persianswitch.sdk.api.IAuthService");
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.a.transact(3, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() != 0) {
                        v5_1 = Bundle.CREATOR.createFromParcel(v1);
                    }
                    else {
                        goto label_20;
                    }

                    goto label_21;
                }
                catch(Throwable v5) {
                    v1.recycle();
                    v0.recycle();
                    throw v5;
                }

            label_20:
                arg5 = null;
            label_21:
                v1.recycle();
                v0.recycle();
                return ((Bundle)v5_1);
            }
        }

        public Stub() {
            super();
            this.attachInterface(((IInterface)this), "com.persianswitch.sdk.api.IAuthService");
        }

        public boolean onTransact(int arg5, Parcel arg6, Parcel arg7, int arg8) {
            Object v3_1;
            String v0 = "com.persianswitch.sdk.api.IAuthService";
            if(arg5 == 1598968902) {
                goto label_46;
            }

            Bundle v3 = null;
            switch(arg5) {
                case 1: {
                    goto label_37;
                }
                case 2: {
                    goto label_23;
                }
                case 3: {
                    goto label_9;
                }
            }

            return super.onTransact(arg5, arg6, arg7, arg8);
        label_37:
            arg6.enforceInterface(v0);
            if(arg6.readInt() != 0) {
                v3_1 = Bundle.CREATOR.createFromParcel(arg6);
            }

            this.a(v3);
            arg7.writeNoException();
            return 1;
        label_23:
            arg6.enforceInterface(v0);
            if(arg6.readInt() != 0) {
                v3_1 = Bundle.CREATOR.createFromParcel(arg6);
            }

            Bundle v5 = this.b(v3);
            arg7.writeNoException();
            if(v5 != null) {
                arg7.writeInt(1);
                v5.writeToParcel(arg7, 1);
            }
            else {
                arg7.writeInt(0);
            }

            return 1;
        label_9:
            arg6.enforceInterface(v0);
            if(arg6.readInt() != 0) {
                v3_1 = Bundle.CREATOR.createFromParcel(arg6);
            }

            v5 = this.c(v3);
            arg7.writeNoException();
            if(v5 != null) {
                arg7.writeInt(1);
                v5.writeToParcel(arg7, 1);
            }
            else {
                arg7.writeInt(0);
            }

            return 1;
        label_46:
            arg7.writeString(v0);
            return 1;
        }
    }

    void a(Bundle arg1);

    Bundle b(Bundle arg1);

    Bundle c(Bundle arg1);
}

