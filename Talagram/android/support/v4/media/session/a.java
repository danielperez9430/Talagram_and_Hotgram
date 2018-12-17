package android.support.v4.media.session;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.media.MediaMetadataCompat;
import android.text.TextUtils;
import java.util.List;

public interface a extends IInterface {
    public abstract class android.support.v4.media.session.a$a extends Binder implements a {
        class android.support.v4.media.session.a$a$a implements a {
            private IBinder a;

            android.support.v4.media.session.a$a$a(IBinder arg1) {
                super();
                this.a = arg1;
            }

            public void a() {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    this.a.transact(2, v0, null, 1);
                }
                catch(Throwable v1) {
                    v0.recycle();
                    throw v1;
                }

                v0.recycle();
            }

            public void a(int arg5) {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    v0.writeInt(arg5);
                    this.a.transact(9, v0, null, 1);
                }
                catch(Throwable v5) {
                    v0.recycle();
                    throw v5;
                }

                v0.recycle();
            }

            public void a(Bundle arg5) {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.a.transact(7, v0, null, 1);
                }
                catch(Throwable v5) {
                    v0.recycle();
                    throw v5;
                }

                v0.recycle();
            }

            public void a(MediaMetadataCompat arg5) {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.a.transact(4, v0, null, 1);
                }
                catch(Throwable v5) {
                    v0.recycle();
                    throw v5;
                }

                v0.recycle();
            }

            public void a(ParcelableVolumeInfo arg5) {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.a.transact(8, v0, null, 1);
                }
                catch(Throwable v5) {
                    v0.recycle();
                    throw v5;
                }

                v0.recycle();
            }

            public void a(PlaybackStateCompat arg5) {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.a.transact(3, v0, null, 1);
                }
                catch(Throwable v5) {
                    v0.recycle();
                    throw v5;
                }

                v0.recycle();
            }

            public void a(CharSequence arg5) {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if(arg5 != null) {
                        v0.writeInt(1);
                        TextUtils.writeToParcel(arg5, v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.a.transact(6, v0, null, 1);
                }
                catch(Throwable v5) {
                    v0.recycle();
                    throw v5;
                }

                v0.recycle();
            }

            public void a(String arg3, Bundle arg4) {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    v0.writeString(arg3);
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

            public void a(List arg5) {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    v0.writeTypedList(arg5);
                    this.a.transact(5, v0, null, 1);
                }
                catch(Throwable v5) {
                    v0.recycle();
                    throw v5;
                }

                v0.recycle();
            }

            public void a(boolean arg5) {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    v0.writeInt(((int)arg5));
                    this.a.transact(10, v0, null, 1);
                }
                catch(Throwable v5) {
                    v0.recycle();
                    throw v5;
                }

                v0.recycle();
            }

            public IBinder asBinder() {
                return this.a;
            }

            public void b() {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    this.a.transact(13, v0, null, 1);
                }
                catch(Throwable v1) {
                    v0.recycle();
                    throw v1;
                }

                v0.recycle();
            }

            public void b(int arg5) {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    v0.writeInt(arg5);
                    this.a.transact(12, v0, null, 1);
                }
                catch(Throwable v5) {
                    v0.recycle();
                    throw v5;
                }

                v0.recycle();
            }

            public void b(boolean arg5) {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    v0.writeInt(((int)arg5));
                    this.a.transact(11, v0, null, 1);
                }
                catch(Throwable v5) {
                    v0.recycle();
                    throw v5;
                }

                v0.recycle();
            }
        }

        public android.support.v4.media.session.a$a() {
            super();
            this.attachInterface(((IInterface)this), "android.support.v4.media.session.IMediaControllerCallback");
        }

        public static a a(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("android.support.v4.media.session.IMediaControllerCallback");
            if(v0 != null && ((v0 instanceof a))) {
                return ((a)v0);
            }

            return new android.support.v4.media.session.a$a$a(arg2);
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int arg4, Parcel arg5, Parcel arg6, int arg7) {
            Object v2_1;
            if(arg4 == 1598968902) {
                goto label_101;
            }

            boolean v0 = false;
            ParcelableVolumeInfo v2 = null;
            switch(arg4) {
                case 1: {
                    goto label_91;
                }
                case 2: {
                    goto label_87;
                }
                case 3: {
                    goto label_78;
                }
                case 4: {
                    goto label_69;
                }
                case 5: {
                    goto label_63;
                }
                case 6: {
                    goto label_54;
                }
                case 7: {
                    goto label_45;
                }
                case 8: {
                    goto label_36;
                }
                case 9: {
                    goto label_31;
                }
                case 10: {
                    goto label_24;
                }
                case 11: {
                    goto label_17;
                }
                case 12: {
                    goto label_12;
                }
                case 13: {
                    goto label_8;
                }
            }

            return super.onTransact(arg4, arg5, arg6, arg7);
        label_36:
            arg5.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            if(arg5.readInt() != 0) {
                v2_1 = ParcelableVolumeInfo.CREATOR.createFromParcel(arg5);
            }

            this.a(((ParcelableVolumeInfo)v2_1));
            return 1;
        label_69:
            arg5.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            if(arg5.readInt() != 0) {
                v2_1 = MediaMetadataCompat.CREATOR.createFromParcel(arg5);
            }

            this.a(((MediaMetadataCompat)v2_1));
            return 1;
        label_8:
            arg5.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            this.b();
            return 1;
        label_12:
            arg5.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            this.b(arg5.readInt());
            return 1;
        label_45:
            arg5.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            if(arg5.readInt() != 0) {
                v2_1 = Bundle.CREATOR.createFromParcel(arg5);
            }

            this.a(((Bundle)v2_1));
            return 1;
        label_78:
            arg5.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            if(arg5.readInt() != 0) {
                v2_1 = PlaybackStateCompat.CREATOR.createFromParcel(arg5);
            }

            this.a(((PlaybackStateCompat)v2_1));
            return 1;
        label_17:
            arg5.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            if(arg5.readInt() != 0) {
                v0 = true;
            }

            this.b(v0);
            return 1;
        label_54:
            arg5.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            if(arg5.readInt() != 0) {
                v2_1 = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(arg5);
            }

            this.a(((CharSequence)v2_1));
            return 1;
        label_87:
            arg5.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            this.a();
            return 1;
        label_24:
            arg5.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            if(arg5.readInt() != 0) {
                v0 = true;
            }

            this.a(v0);
            return 1;
        label_91:
            arg5.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            String v4 = arg5.readString();
            if(arg5.readInt() != 0) {
                v2_1 = Bundle.CREATOR.createFromParcel(arg5);
            }

            this.a(v4, ((Bundle)v2_1));
            return 1;
        label_63:
            arg5.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            this.a(arg5.createTypedArrayList(QueueItem.CREATOR));
            return 1;
        label_31:
            arg5.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            this.a(arg5.readInt());
            return 1;
        label_101:
            arg6.writeString("android.support.v4.media.session.IMediaControllerCallback");
            return 1;
        }
    }

    void a();

    void a(int arg1);

    void a(Bundle arg1);

    void a(MediaMetadataCompat arg1);

    void a(ParcelableVolumeInfo arg1);

    void a(PlaybackStateCompat arg1);

    void a(CharSequence arg1);

    void a(String arg1, Bundle arg2);

    void a(List arg1);

    void a(boolean arg1);

    void b();

    void b(int arg1);

    void b(boolean arg1);
}

