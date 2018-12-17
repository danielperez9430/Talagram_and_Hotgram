package android.support.v4.os;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class ResultReceiver implements Parcelable {
    final class android.support.v4.os.ResultReceiver$1 implements Parcelable$Creator {
        android.support.v4.os.ResultReceiver$1() {
            super();
        }

        public ResultReceiver a(Parcel arg2) {
            return new ResultReceiver(arg2);
        }

        public ResultReceiver[] a(int arg1) {
            return new ResultReceiver[arg1];
        }

        public Object createFromParcel(Parcel arg1) {
            return this.a(arg1);
        }

        public Object[] newArray(int arg1) {
            return this.a(arg1);
        }
    }

    class a extends android.support.v4.os.c$a {
        a(ResultReceiver arg1) {
            this.a = arg1;
            super();
        }

        public void a(int arg4, Bundle arg5) {
            if(this.a.b != null) {
                this.a.b.post(new b(this.a, arg4, arg5));
            }
            else {
                this.a.a(arg4, arg5);
            }
        }
    }

    class b implements Runnable {
        final int a;
        final Bundle b;

        b(ResultReceiver arg1, int arg2, Bundle arg3) {
            this.c = arg1;
            super();
            this.a = arg2;
            this.b = arg3;
        }

        public void run() {
            this.c.a(this.a, this.b);
        }
    }

    public static final Parcelable$Creator CREATOR;
    final boolean a;
    final Handler b;
    c c;

    static {
        ResultReceiver.CREATOR = new android.support.v4.os.ResultReceiver$1();
    }

    ResultReceiver(Parcel arg2) {
        super();
        this.a = false;
        this.b = null;
        this.c = android.support.v4.os.c$a.a(arg2.readStrongBinder());
    }

    protected void a(int arg1, Bundle arg2) {
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel arg1, int arg2) {
        __monitor_enter(this);
        try {
            if(this.c == null) {
                this.c = new a(this);
            }

            arg1.writeStrongBinder(this.c.asBinder());
            __monitor_exit(this);
            return;
        label_12:
            __monitor_exit(this);
        }
        catch(Throwable v1) {
            goto label_12;
        }

        throw v1;
    }
}

