package android.support.v4.media.session;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public interface b extends IInterface {
    public abstract class a extends Binder implements b {
        class android.support.v4.media.session.b$a$a implements b {
            private IBinder a;

            android.support.v4.media.session.b$a$a(IBinder arg1) {
                super();
                this.a = arg1;
            }

            public void a(int arg5) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeInt(arg5);
                    this.a.transact(44, v0, v1, 0);
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

            public void a(int arg4, int arg5, String arg6) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeInt(arg4);
                    v0.writeInt(arg5);
                    v0.writeString(arg6);
                    this.a.transact(11, v0, v1, 0);
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

            public void a(long arg4) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeLong(arg4);
                    this.a.transact(17, v0, v1, 0);
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

            public void a(Uri arg5, Bundle arg6) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
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

                    this.a.transact(36, v0, v1, 0);
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

            public void a(MediaDescriptionCompat arg5) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.a.transact(41, v0, v1, 0);
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

            public void a(MediaDescriptionCompat arg5, int arg6) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    v0.writeInt(arg6);
                    this.a.transact(42, v0, v1, 0);
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

            public void a(RatingCompat arg5) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.a.transact(25, v0, v1, 0);
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

            public void a(RatingCompat arg5, Bundle arg6) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
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

                    this.a.transact(51, v0, v1, 0);
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

            public void a(android.support.v4.media.session.a arg5) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    IBinder v5_1 = arg5 != null ? arg5.asBinder() : null;
                    v0.writeStrongBinder(v5_1);
                    this.a.transact(3, v0, v1, 0);
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

            public void a(String arg4, Bundle arg5) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeString(arg4);
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.a.transact(34, v0, v1, 0);
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

            public void a(String arg4, Bundle arg5, ResultReceiverWrapper arg6) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeString(arg4);
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

                    this.a.transact(1, v0, v1, 0);
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

            public void a(boolean arg5) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeInt(((int)arg5));
                    this.a.transact(46, v0, v1, 0);
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

            public boolean a() {
                boolean v4;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v4 = false;
                    this.a.transact(5, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() == 0) {
                        goto label_12;
                    }
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v4 = true;
            label_12:
                v1.recycle();
                v0.recycle();
                return v4;
            }

            public boolean a(KeyEvent arg6) {
                boolean v2;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v2 = true;
                    if(arg6 != null) {
                        v0.writeInt(1);
                        arg6.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.a.transact(2, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() == 0) {
                        goto label_18;
                    }
                }
                catch(Throwable v6) {
                    v1.recycle();
                    v0.recycle();
                    throw v6;
                }

                goto label_19;
            label_18:
                v2 = false;
            label_19:
                v1.recycle();
                v0.recycle();
                return v2;
            }

            public IBinder asBinder() {
                return this.a;
            }

            public String b() {
                String v2_1;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(6, v0, v1, 0);
                    v1.readException();
                    v2_1 = v1.readString();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
                return v2_1;
            }

            public void b(int arg5) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeInt(arg5);
                    this.a.transact(39, v0, v1, 0);
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

            public void b(int arg4, int arg5, String arg6) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeInt(arg4);
                    v0.writeInt(arg5);
                    v0.writeString(arg6);
                    this.a.transact(12, v0, v1, 0);
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

            public void b(long arg4) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeLong(arg4);
                    this.a.transact(24, v0, v1, 0);
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

            public void b(Uri arg5, Bundle arg6) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
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

                    this.a.transact(16, v0, v1, 0);
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

            public void b(MediaDescriptionCompat arg5) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.a.transact(43, v0, v1, 0);
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

            public void b(android.support.v4.media.session.a arg5) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    IBinder v5_1 = arg5 != null ? arg5.asBinder() : null;
                    v0.writeStrongBinder(v5_1);
                    this.a.transact(4, v0, v1, 0);
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

            public void b(String arg4, Bundle arg5) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeString(arg4);
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.a.transact(35, v0, v1, 0);
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

            public void b(boolean arg5) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeInt(((int)arg5));
                    this.a.transact(40, v0, v1, 0);
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

            public String c() {
                String v2_1;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(7, v0, v1, 0);
                    v1.readException();
                    v2_1 = v1.readString();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
                return v2_1;
            }

            public void c(int arg5) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeInt(arg5);
                    this.a.transact(48, v0, v1, 0);
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

            public void c(String arg4, Bundle arg5) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeString(arg4);
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.a.transact(14, v0, v1, 0);
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

            public PendingIntent d() {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(8, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() != 0) {
                        Object v2_1 = PendingIntent.CREATOR.createFromParcel(v1);
                    }
                    else {
                        goto label_14;
                    }

                    goto label_15;
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

            label_14:
                PendingIntent v2_2 = null;
            label_15:
                v1.recycle();
                v0.recycle();
                return v2_2;
            }

            public void d(String arg4, Bundle arg5) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeString(arg4);
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.a.transact(15, v0, v1, 0);
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

            public long e() {
                long v2_1;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(9, v0, v1, 0);
                    v1.readException();
                    v2_1 = v1.readLong();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
                return v2_1;
            }

            public void e(String arg4, Bundle arg5) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeString(arg4);
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.a.transact(26, v0, v1, 0);
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

            public ParcelableVolumeInfo f() {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(10, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() != 0) {
                        Object v2_1 = ParcelableVolumeInfo.CREATOR.createFromParcel(v1);
                    }
                    else {
                        goto label_14;
                    }

                    goto label_15;
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

            label_14:
                ParcelableVolumeInfo v2_2 = null;
            label_15:
                v1.recycle();
                v0.recycle();
                return v2_2;
            }

            public MediaMetadataCompat g() {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(27, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() != 0) {
                        Object v2_1 = MediaMetadataCompat.CREATOR.createFromParcel(v1);
                    }
                    else {
                        goto label_14;
                    }

                    goto label_15;
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

            label_14:
                MediaMetadataCompat v2_2 = null;
            label_15:
                v1.recycle();
                v0.recycle();
                return v2_2;
            }

            public PlaybackStateCompat h() {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(28, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() != 0) {
                        Object v2_1 = PlaybackStateCompat.CREATOR.createFromParcel(v1);
                    }
                    else {
                        goto label_14;
                    }

                    goto label_15;
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

            label_14:
                PlaybackStateCompat v2_2 = null;
            label_15:
                v1.recycle();
                v0.recycle();
                return v2_2;
            }

            public List i() {
                ArrayList v2_1;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(29, v0, v1, 0);
                    v1.readException();
                    v2_1 = v1.createTypedArrayList(QueueItem.CREATOR);
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
                return ((List)v2_1);
            }

            public CharSequence j() {
                Object v2_1;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(30, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() != 0) {
                        v2_1 = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(v1);
                    }
                    else {
                        goto label_14;
                    }

                    goto label_15;
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

            label_14:
                CharSequence v2_2 = null;
            label_15:
                v1.recycle();
                v0.recycle();
                return ((CharSequence)v2_1);
            }

            public Bundle k() {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(31, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() != 0) {
                        Object v2_1 = Bundle.CREATOR.createFromParcel(v1);
                    }
                    else {
                        goto label_14;
                    }

                    goto label_15;
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

            label_14:
                Bundle v2_2 = null;
            label_15:
                v1.recycle();
                v0.recycle();
                return v2_2;
            }

            public int l() {
                int v2_1;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(32, v0, v1, 0);
                    v1.readException();
                    v2_1 = v1.readInt();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
                return v2_1;
            }

            public boolean m() {
                boolean v4;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v4 = false;
                    this.a.transact(45, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() == 0) {
                        goto label_12;
                    }
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v4 = true;
            label_12:
                v1.recycle();
                v0.recycle();
                return v4;
            }

            public int n() {
                int v2_1;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(37, v0, v1, 0);
                    v1.readException();
                    v2_1 = v1.readInt();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
                return v2_1;
            }

            public boolean o() {
                boolean v4;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v4 = false;
                    this.a.transact(38, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() == 0) {
                        goto label_12;
                    }
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v4 = true;
            label_12:
                v1.recycle();
                v0.recycle();
                return v4;
            }

            public int p() {
                int v2_1;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(47, v0, v1, 0);
                    v1.readException();
                    v2_1 = v1.readInt();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
                return v2_1;
            }

            public void q() {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(33, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
            }

            public void r() {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(13, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
            }

            public void s() {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(18, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
            }

            public void t() {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(19, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
            }

            public void u() {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(20, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
            }

            public void v() {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(21, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
            }

            public void w() {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(22, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
            }

            public void x() {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(23, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
            }
        }

        public static b a(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("android.support.v4.media.session.IMediaSession");
            if(v0 != null && ((v0 instanceof b))) {
                return ((b)v0);
            }

            return new android.support.v4.media.session.b$a$a(arg2);
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int arg4, Parcel arg5, Parcel arg6, int arg7) {
            Object v7;
            Object v1_1;
            Uri v4_2;
            Object v4_1;
            MediaDescriptionCompat v1 = null;
            if(arg4 == 51) {
                goto label_405;
            }

            if(arg4 == 1598968902) {
                goto label_402;
            }

            boolean v0 = false;
            switch(arg4) {
                case 1: {
                    goto label_385;
                }
                case 2: {
                    goto label_374;
                }
                case 3: {
                    goto label_367;
                }
                case 4: {
                    goto label_360;
                }
                case 5: {
                    goto label_354;
                }
                case 6: {
                    goto label_348;
                }
                case 7: {
                    goto label_342;
                }
                case 8: {
                    goto label_332;
                }
                case 9: {
                    goto label_326;
                }
                case 10: {
                    goto label_316;
                }
                case 11: {
                    goto label_308;
                }
                case 12: {
                    goto label_300;
                }
                case 13: {
                    goto label_295;
                }
                case 14: {
                    goto label_284;
                }
                case 15: {
                    goto label_273;
                }
                case 16: {
                    goto label_257;
                }
                case 17: {
                    goto label_251;
                }
                case 18: {
                    goto label_246;
                }
                case 19: {
                    goto label_241;
                }
                case 20: {
                    goto label_236;
                }
                case 21: {
                    goto label_231;
                }
                case 22: {
                    goto label_226;
                }
                case 23: {
                    goto label_221;
                }
                case 24: {
                    goto label_215;
                }
                case 25: {
                    goto label_205;
                }
                case 26: {
                    goto label_194;
                }
                case 27: {
                    goto label_184;
                }
                case 28: {
                    goto label_174;
                }
                case 29: {
                    goto label_168;
                }
                case 30: {
                    goto label_158;
                }
                case 31: {
                    goto label_148;
                }
                case 32: {
                    goto label_142;
                }
                case 33: {
                    goto label_137;
                }
                case 34: {
                    goto label_126;
                }
                case 35: {
                    goto label_115;
                }
                case 36: {
                    goto label_99;
                }
                case 37: {
                    goto label_93;
                }
                case 38: {
                    goto label_87;
                }
                case 39: {
                    goto label_81;
                }
                case 40: {
                    goto label_73;
                }
                case 41: {
                    goto label_63;
                }
                case 42: {
                    goto label_52;
                }
                case 43: {
                    goto label_42;
                }
                case 44: {
                    goto label_36;
                }
                case 45: {
                    goto label_30;
                }
                case 46: {
                    goto label_22;
                }
                case 47: {
                    goto label_16;
                }
                case 48: {
                    goto label_10;
                }
            }

            return super.onTransact(arg4, arg5, arg6, arg7);
        label_354:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            boolean v4 = this.a();
            arg6.writeNoException();
            arg6.writeInt(((int)v4));
            return 1;
        label_226:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.w();
            arg6.writeNoException();
            return 1;
        label_99:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            if(arg5.readInt() != 0) {
                v4_1 = Uri.CREATOR.createFromParcel(arg5);
            }
            else {
                v4_2 = ((Uri)v1);
            }

            if(arg5.readInt() != 0) {
                v1_1 = Bundle.CREATOR.createFromParcel(arg5);
            }

            this.a(((Uri)v4_1), ((Bundle)v1_1));
            arg6.writeNoException();
            return 1;
        label_231:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.v();
            arg6.writeNoException();
            return 1;
        label_360:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.b(android.support.v4.media.session.a$a.a(arg5.readStrongBinder()));
            arg6.writeNoException();
            return 1;
        label_236:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.u();
            arg6.writeNoException();
            return 1;
        label_367:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.a(android.support.v4.media.session.a$a.a(arg5.readStrongBinder()));
            arg6.writeNoException();
            return 1;
        label_241:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.t();
            arg6.writeNoException();
            return 1;
        label_115:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            String v4_3 = arg5.readString();
            if(arg5.readInt() != 0) {
                v1_1 = Bundle.CREATOR.createFromParcel(arg5);
            }

            this.b(v4_3, ((Bundle)v1_1));
            arg6.writeNoException();
            return 1;
        label_374:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            if(arg5.readInt() != 0) {
                v1_1 = KeyEvent.CREATOR.createFromParcel(arg5);
            }

            v4 = this.a(((KeyEvent)v1_1));
            arg6.writeNoException();
            arg6.writeInt(((int)v4));
            return 1;
        label_246:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.s();
            arg6.writeNoException();
            return 1;
        label_251:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.a(arg5.readLong());
            arg6.writeNoException();
            return 1;
        label_126:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            v4_3 = arg5.readString();
            if(arg5.readInt() != 0) {
                v1_1 = Bundle.CREATOR.createFromParcel(arg5);
            }

            this.a(v4_3, ((Bundle)v1_1));
            arg6.writeNoException();
            return 1;
        label_385:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            v4_3 = arg5.readString();
            if(arg5.readInt() != 0) {
                v7 = Bundle.CREATOR.createFromParcel(arg5);
            }
            else {
                Bundle v7_1 = ((Bundle)v1);
            }

            if(arg5.readInt() != 0) {
                v1_1 = ResultReceiverWrapper.CREATOR.createFromParcel(arg5);
            }

            this.a(v4_3, ((Bundle)v7), ((ResultReceiverWrapper)v1_1));
            arg6.writeNoException();
            return 1;
        label_257:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            if(arg5.readInt() != 0) {
                v4_1 = Uri.CREATOR.createFromParcel(arg5);
            }
            else {
                v4_2 = ((Uri)v1);
            }

            if(arg5.readInt() != 0) {
                v1_1 = Bundle.CREATOR.createFromParcel(arg5);
            }

            this.b(((Uri)v4_1), ((Bundle)v1_1));
            arg6.writeNoException();
            return 1;
        label_137:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.q();
            arg6.writeNoException();
            return 1;
        label_10:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.c(arg5.readInt());
            arg6.writeNoException();
            return 1;
        label_142:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            arg4 = this.l();
            arg6.writeNoException();
            arg6.writeInt(arg4);
            return 1;
        label_16:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            arg4 = this.p();
            arg6.writeNoException();
            arg6.writeInt(arg4);
            return 1;
        label_273:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            v4_3 = arg5.readString();
            if(arg5.readInt() != 0) {
                v1_1 = Bundle.CREATOR.createFromParcel(arg5);
            }

            this.d(v4_3, ((Bundle)v1_1));
            arg6.writeNoException();
            return 1;
        label_148:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            Bundle v4_4 = this.k();
            arg6.writeNoException();
            if(v4_4 != null) {
                arg6.writeInt(1);
                v4_4.writeToParcel(arg6, 1);
            }
            else {
                arg6.writeInt(0);
            }

            return 1;
        label_22:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            if(arg5.readInt() != 0) {
                v0 = true;
            }

            this.a(v0);
            arg6.writeNoException();
            return 1;
        label_284:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            v4_3 = arg5.readString();
            if(arg5.readInt() != 0) {
                v1_1 = Bundle.CREATOR.createFromParcel(arg5);
            }

            this.c(v4_3, ((Bundle)v1_1));
            arg6.writeNoException();
            return 1;
        label_158:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            CharSequence v4_5 = this.j();
            arg6.writeNoException();
            if(v4_5 != null) {
                arg6.writeInt(1);
                TextUtils.writeToParcel(v4_5, arg6, 1);
            }
            else {
                arg6.writeInt(0);
            }

            return 1;
        label_30:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            v4 = this.m();
            arg6.writeNoException();
            arg6.writeInt(((int)v4));
            return 1;
        label_36:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.a(arg5.readInt());
            arg6.writeNoException();
            return 1;
        label_295:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.r();
            arg6.writeNoException();
            return 1;
        label_168:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            List v4_6 = this.i();
            arg6.writeNoException();
            arg6.writeTypedList(v4_6);
            return 1;
        label_42:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            if(arg5.readInt() != 0) {
                v1_1 = MediaDescriptionCompat.CREATOR.createFromParcel(arg5);
            }

            this.b(((MediaDescriptionCompat)v1_1));
            arg6.writeNoException();
            return 1;
        label_300:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.b(arg5.readInt(), arg5.readInt(), arg5.readString());
            arg6.writeNoException();
            return 1;
        label_174:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            PlaybackStateCompat v4_7 = this.h();
            arg6.writeNoException();
            if(v4_7 != null) {
                arg6.writeInt(1);
                v4_7.writeToParcel(arg6, 1);
            }
            else {
                arg6.writeInt(0);
            }

            return 1;
        label_308:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.a(arg5.readInt(), arg5.readInt(), arg5.readString());
            arg6.writeNoException();
            return 1;
        label_52:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            if(arg5.readInt() != 0) {
                v1_1 = MediaDescriptionCompat.CREATOR.createFromParcel(arg5);
            }

            this.a(((MediaDescriptionCompat)v1_1), arg5.readInt());
            arg6.writeNoException();
            return 1;
        label_184:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            MediaMetadataCompat v4_8 = this.g();
            arg6.writeNoException();
            if(v4_8 != null) {
                arg6.writeInt(1);
                v4_8.writeToParcel(arg6, 1);
            }
            else {
                arg6.writeInt(0);
            }

            return 1;
        label_316:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            ParcelableVolumeInfo v4_9 = this.f();
            arg6.writeNoException();
            if(v4_9 != null) {
                arg6.writeInt(1);
                v4_9.writeToParcel(arg6, 1);
            }
            else {
                arg6.writeInt(0);
            }

            return 1;
        label_63:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            if(arg5.readInt() != 0) {
                v1_1 = MediaDescriptionCompat.CREATOR.createFromParcel(arg5);
            }

            this.a(((MediaDescriptionCompat)v1_1));
            arg6.writeNoException();
            return 1;
        label_194:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            v4_3 = arg5.readString();
            if(arg5.readInt() != 0) {
                v1_1 = Bundle.CREATOR.createFromParcel(arg5);
            }

            this.e(v4_3, ((Bundle)v1_1));
            arg6.writeNoException();
            return 1;
        label_326:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            long v4_10 = this.e();
            arg6.writeNoException();
            arg6.writeLong(v4_10);
            return 1;
        label_73:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            if(arg5.readInt() != 0) {
                v0 = true;
            }

            this.b(v0);
            arg6.writeNoException();
            return 1;
        label_332:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            PendingIntent v4_11 = this.d();
            arg6.writeNoException();
            if(v4_11 != null) {
                arg6.writeInt(1);
                v4_11.writeToParcel(arg6, 1);
            }
            else {
                arg6.writeInt(0);
            }

            return 1;
        label_205:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            if(arg5.readInt() != 0) {
                v1_1 = RatingCompat.CREATOR.createFromParcel(arg5);
            }

            this.a(((RatingCompat)v1_1));
            arg6.writeNoException();
            return 1;
        label_81:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.b(arg5.readInt());
            arg6.writeNoException();
            return 1;
        label_342:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            v4_3 = this.c();
            arg6.writeNoException();
            arg6.writeString(v4_3);
            return 1;
        label_215:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.b(arg5.readLong());
            arg6.writeNoException();
            return 1;
        label_87:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            v4 = this.o();
            arg6.writeNoException();
            arg6.writeInt(((int)v4));
            return 1;
        label_348:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            v4_3 = this.b();
            arg6.writeNoException();
            arg6.writeString(v4_3);
            return 1;
        label_221:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.x();
            arg6.writeNoException();
            return 1;
        label_93:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            arg4 = this.n();
            arg6.writeNoException();
            arg6.writeInt(arg4);
            return 1;
        label_402:
            arg6.writeString("android.support.v4.media.session.IMediaSession");
            return 1;
        label_405:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            if(arg5.readInt() != 0) {
                v4_1 = RatingCompat.CREATOR.createFromParcel(arg5);
            }
            else {
                RatingCompat v4_12 = ((RatingCompat)v1);
            }

            if(arg5.readInt() != 0) {
                v1_1 = Bundle.CREATOR.createFromParcel(arg5);
            }

            this.a(((RatingCompat)v4_1), ((Bundle)v1_1));
            arg6.writeNoException();
            return 1;
        }
    }

    void a(android.support.v4.media.session.a arg1);

    void a(int arg1);

    void a(int arg1, int arg2, String arg3);

    void a(long arg1);

    void a(Uri arg1, Bundle arg2);

    void a(MediaDescriptionCompat arg1);

    void a(MediaDescriptionCompat arg1, int arg2);

    void a(RatingCompat arg1);

    void a(RatingCompat arg1, Bundle arg2);

    void a(String arg1, Bundle arg2);

    void a(String arg1, Bundle arg2, ResultReceiverWrapper arg3);

    void a(boolean arg1);

    boolean a();

    boolean a(KeyEvent arg1);

    String b();

    void b(int arg1);

    void b(int arg1, int arg2, String arg3);

    void b(long arg1);

    void b(Uri arg1, Bundle arg2);

    void b(MediaDescriptionCompat arg1);

    void b(android.support.v4.media.session.a arg1);

    void b(String arg1, Bundle arg2);

    void b(boolean arg1);

    String c();

    void c(int arg1);

    void c(String arg1, Bundle arg2);

    PendingIntent d();

    void d(String arg1, Bundle arg2);

    long e();

    void e(String arg1, Bundle arg2);

    ParcelableVolumeInfo f();

    MediaMetadataCompat g();

    PlaybackStateCompat h();

    List i();

    CharSequence j();

    Bundle k();

    int l();

    boolean m();

    int n();

    boolean o();

    int p();

    void q();

    void r();

    void s();

    void t();

    void u();

    void v();

    void w();

    void x();
}

