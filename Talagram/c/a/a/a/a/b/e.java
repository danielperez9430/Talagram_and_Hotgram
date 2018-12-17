package c.a.a.a.a.b;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager$NameNotFoundException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import c.a.a.a.c;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

class e implements f {
    class c.a.a.a.a.b.e$1 {
    }

    final class a implements ServiceConnection {
        private boolean a;
        private final LinkedBlockingQueue b;

        a(c.a.a.a.a.b.e$1 arg1) {
            this();
        }

        private a() {
            super();
            this.a = false;
            this.b = new LinkedBlockingQueue(1);
        }

        public IBinder a() {
            if(this.a) {
                c.h().e("Fabric", "getBinder already called");
            }

            this.a = true;
            try {
                return this.b.poll(200, TimeUnit.MILLISECONDS);
            }
            catch(InterruptedException ) {
                return null;
            }
        }

        public void onServiceConnected(ComponentName arg1, IBinder arg2) {
            try {
                this.b.put(arg2);
                return;
            }
            catch(InterruptedException ) {
                return;
            }
        }

        public void onServiceDisconnected(ComponentName arg1) {
            this.b.clear();
        }
    }

    final class b implements IInterface {
        private final IBinder a;

        public b(IBinder arg1) {
            super();
            this.a = arg1;
        }

        public String a() {
            String v2_1;
            Parcel v0 = Parcel.obtain();
            Parcel v1 = Parcel.obtain();
            try {
                v0.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.a.transact(1, v0, v1, 0);
                v1.readException();
                v2_1 = v1.readString();
            }
            catch(Throwable v2) {
            label_23:
                v1.recycle();
                v0.recycle();
                throw v2;
            }
            catch(Exception ) {
                try {
                    c.h().a("Fabric", "Could not get parcel from Google Play Service to capture AdvertisingId");
                }
                catch(Throwable v2) {
                    goto label_23;
                }

                v1.recycle();
                v0.recycle();
                return null;
            }

            v1.recycle();
            v0.recycle();
            return v2_1;
        }

        public IBinder asBinder() {
            return this.a;
        }

        public boolean b() {
            Parcel v0 = Parcel.obtain();
            Parcel v1 = Parcel.obtain();
            boolean v2 = false;
            try {
                v0.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                v0.writeInt(1);
                this.a.transact(2, v0, v1, 0);
                v1.readException();
                if(v1.readInt() == 0) {
                    goto label_21;
                }
            }
            catch(Throwable v2_1) {
            }
            catch(Exception ) {
                try {
                    c.h().a("Fabric", "Could not get parcel from Google Play Service to capture Advertising limitAdTracking");
                    goto label_21;
                }
                catch(Throwable v2_1) {
                    v1.recycle();
                    v0.recycle();
                    throw v2_1;
                }
            }

            v2 = true;
        label_21:
            v1.recycle();
            v0.recycle();
            return v2;
        }
    }

    private final Context a;

    public e(Context arg1) {
        super();
        this.a = arg1.getApplicationContext();
    }

    public c.a.a.a.a.b.b a() {
        c.a.a.a.a.b.b v3;
        c.a.a.a.a.b.b v2 = null;
        if(Looper.myLooper() == Looper.getMainLooper()) {
            c.h().a("Fabric", "AdvertisingInfoServiceStrategy cannot be called on the main thread");
            return v2;
        }

        try {
            this.a.getPackageManager().getPackageInfo("com.android.vending", 0);
        }
        catch(Exception v0) {
            c.h().a("Fabric", "Unable to determine if Google Play Services is available", ((Throwable)v0));
            return v2;
        }
        catch(PackageManager$NameNotFoundException ) {
            c.h().a("Fabric", "Unable to find Google Play Services package name");
            return v2;
        }

        a v0_1 = new a(((c.a.a.a.a.b.e$1)v2));
        Intent v1 = new Intent("com.google.android.gms.ads.identifier.service.START");
        v1.setPackage("com.google.android.gms");
        try {
            if(!this.a.bindService(v1, ((ServiceConnection)v0_1), 1)) {
                goto label_48;
            }
        }
        catch(Throwable v0_2) {
            goto label_57;
        }

        try {
            b v1_3 = new b(v0_1.a());
            v3 = new c.a.a.a.a.b.b(v1_3.a(), v1_3.b());
            goto label_32;
        }
        catch(Throwable v1_1) {
        label_36:
            try {
                this.a.unbindService(((ServiceConnection)v0_1));
                throw v1_1;
            label_32:
                this.a.unbindService(((ServiceConnection)v0_1));
                return v3;
            label_48:
                c.h().a("Fabric", "Could not bind to Google Play Service to capture AdvertisingId");
            }
            catch(Throwable v0_2) {
            label_57:
                c.h().a("Fabric", "Could not bind to Google Play Service to capture AdvertisingId", v0_2);
            }
        }
        catch(Exception v1_2) {
            try {
                c.h().d("Fabric", "Exception in binding to Google Play Service to capture AdvertisingId", ((Throwable)v1_2));
            }
            catch(Throwable v1_1) {
                goto label_36;
            }

            try {
                this.a.unbindService(((ServiceConnection)v0_1));
            }
            catch(Throwable v0_2) {
                goto label_57;
            }
        }

        return v2;
    }
}

