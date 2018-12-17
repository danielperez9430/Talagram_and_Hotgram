package android.support.v4.os;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public interface c extends IInterface {
    public abstract class a extends Binder implements c {
        class android.support.v4.os.c$a$a implements c {
            private IBinder a;

            android.support.v4.os.c$a$a(IBinder arg1) {
                super();
                this.a = arg1;
            }

            public void a(int arg3, Bundle arg4) {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.os.IResultReceiver");
                    v0.writeInt(arg3);
                    if(arg4 != null) {
                        v0.writeInt(1);
                        arg4.writeToParcel(v0, 0);
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

        public a() {
            super();
            this.attachInterface(((IInterface)this), "android.support.v4.os.IResultReceiver");
        }

        public static c a(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("android.support.v4.os.IResultReceiver");
            if(v0 != null && ((v0 instanceof c))) {
                return ((c)v0);
            }

            return new android.support.v4.os.c$a$a(arg2);
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int arg3, Parcel arg4, Parcel arg5, int arg6) {
            Object v4;
            if(arg3 != 1) {
                if(arg3 != 1598968902) {
                    return super.onTransact(arg3, arg4, arg5, arg6);
                }

                arg5.writeString("android.support.v4.os.IResultReceiver");
                return 1;
            }

            arg4.enforceInterface("android.support.v4.os.IResultReceiver");
            arg3 = arg4.readInt();
            if(arg4.readInt() != 0) {
                v4 = Bundle.CREATOR.createFromParcel(arg4);
            }
            else {
                Bundle v4_1 = null;
            }

            this.a(arg3, ((Bundle)v4));
            return 1;
        }
    }

    void a(int arg1, Bundle arg2);
}

