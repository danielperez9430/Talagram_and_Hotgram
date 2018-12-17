package android.support.v4.app;

import android.app.Notification;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public interface s extends IInterface {
    public abstract class a extends Binder implements s {
        class android.support.v4.app.s$a$a implements s {
            private IBinder a;

            android.support.v4.app.s$a$a(IBinder arg1) {
                super();
                this.a = arg1;
            }

            public void a(String arg5) {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
                    v0.writeString(arg5);
                    this.a.transact(3, v0, null, 1);
                }
                catch(Throwable v5) {
                    v0.recycle();
                    throw v5;
                }

                v0.recycle();
            }

            public void a(String arg3, int arg4, String arg5) {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
                    v0.writeString(arg3);
                    v0.writeInt(arg4);
                    v0.writeString(arg5);
                    this.a.transact(2, v0, null, 1);
                }
                catch(Throwable v3) {
                    v0.recycle();
                    throw v3;
                }

                v0.recycle();
            }

            public void a(String arg3, int arg4, String arg5, Notification arg6) {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
                    v0.writeString(arg3);
                    v0.writeInt(arg4);
                    v0.writeString(arg5);
                    if(arg6 != null) {
                        v0.writeInt(1);
                        arg6.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.a.transact(1, v0, null, 1);
                }
                catch(Throwable v3) {
                    v0.recycle();
                    throw v3;
                }

                v0.recycle();
            }

            public IBinder asBinder() {
                return this.a;
            }
        }

        public static s a(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("android.support.v4.app.INotificationSideChannel");
            if(v0 != null && ((v0 instanceof s))) {
                return ((s)v0);
            }

            return new android.support.v4.app.s$a$a(arg2);
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int arg3, Parcel arg4, Parcel arg5, int arg6) {
            Notification v4_1;
            if(arg3 == 1598968902) {
                goto label_31;
            }

            switch(arg3) {
                case 1: {
                    goto label_18;
                }
                case 2: {
                    goto label_11;
                }
                case 3: {
                    goto label_6;
                }
            }

            return super.onTransact(arg3, arg4, arg5, arg6);
        label_18:
            arg4.enforceInterface("android.support.v4.app.INotificationSideChannel");
            String v3 = arg4.readString();
            int v5 = arg4.readInt();
            String v6 = arg4.readString();
            if(arg4.readInt() != 0) {
                Object v4 = Notification.CREATOR.createFromParcel(arg4);
            }
            else {
                v4_1 = null;
            }

            this.a(v3, v5, v6, v4_1);
            return 1;
        label_6:
            arg4.enforceInterface("android.support.v4.app.INotificationSideChannel");
            this.a(arg4.readString());
            return 1;
        label_11:
            arg4.enforceInterface("android.support.v4.app.INotificationSideChannel");
            this.a(arg4.readString(), arg4.readInt(), arg4.readString());
            return 1;
        label_31:
            arg5.writeString("android.support.v4.app.INotificationSideChannel");
            return 1;
        }
    }

    void a(String arg1);

    void a(String arg1, int arg2, String arg3);

    void a(String arg1, int arg2, String arg3, Notification arg4);
}

