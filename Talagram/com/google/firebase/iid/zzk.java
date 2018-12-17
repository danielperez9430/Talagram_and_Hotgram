package com.google.firebase.iid;

import android.os.Build$VERSION;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.util.Log;

public class zzk implements Parcelable {
    public final class a extends ClassLoader {
        public a() {
            super();
        }

        protected final Class loadClass(String arg2, boolean arg3) {
            if("com.google.android.gms.iid.MessengerCompat".equals(arg2)) {
                if(FirebaseInstanceId.g()) {
                    Log.d("FirebaseInstanceId", "Using renamed FirebaseIidMessengerCompat class");
                }

                return zzk.class;
            }

            return super.loadClass(arg2, arg3);
        }
    }

    public static final Parcelable$Creator CREATOR;
    private Messenger a;
    private av b;

    static {
        zzk.CREATOR = new am();
    }

    public zzk(IBinder arg3) {
        super();
        if(Build$VERSION.SDK_INT >= 21) {
            this.a = new Messenger(arg3);
            return;
        }

        this.b = new aw(arg3);
    }

    public final void a(Message arg2) {
        if(this.a != null) {
            this.a.send(arg2);
            return;
        }

        this.b.a(arg2);
    }

    private final IBinder a() {
        if(this.a != null) {
            return this.a.getBinder();
        }

        return this.b.asBinder();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object arg3) {
        if(arg3 == null) {
            return 0;
        }

        try {
            return this.a().equals(((zzk)arg3).a());
        }
        catch(ClassCastException ) {
            return 0;
        }
    }

    public int hashCode() {
        return this.a().hashCode();
    }

    public void writeToParcel(Parcel arg1, int arg2) {
        IBinder v2 = this.a != null ? this.a.getBinder() : this.b.asBinder();
        arg1.writeStrongBinder(v2);
    }
}

