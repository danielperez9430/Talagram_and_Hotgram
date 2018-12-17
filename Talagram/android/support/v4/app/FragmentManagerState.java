package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

final class FragmentManagerState implements Parcelable {
    final class android.support.v4.app.FragmentManagerState$1 implements Parcelable$Creator {
        android.support.v4.app.FragmentManagerState$1() {
            super();
        }

        public FragmentManagerState a(Parcel arg2) {
            return new FragmentManagerState(arg2);
        }

        public FragmentManagerState[] a(int arg1) {
            return new FragmentManagerState[arg1];
        }

        public Object createFromParcel(Parcel arg1) {
            return this.a(arg1);
        }

        public Object[] newArray(int arg1) {
            return this.a(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR;
    FragmentState[] a;
    int[] b;
    BackStackState[] c;
    int d;
    int e;

    static {
        FragmentManagerState.CREATOR = new android.support.v4.app.FragmentManagerState$1();
    }

    public FragmentManagerState() {
        super();
        this.d = -1;
    }

    public FragmentManagerState(Parcel arg2) {
        super();
        this.d = -1;
        this.a = arg2.createTypedArray(FragmentState.CREATOR);
        this.b = arg2.createIntArray();
        this.c = arg2.createTypedArray(BackStackState.CREATOR);
        this.d = arg2.readInt();
        this.e = arg2.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel arg2, int arg3) {
        arg2.writeTypedArray(this.a, arg3);
        arg2.writeIntArray(this.b);
        arg2.writeTypedArray(this.c, arg3);
        arg2.writeInt(this.d);
        arg2.writeInt(this.e);
    }
}

