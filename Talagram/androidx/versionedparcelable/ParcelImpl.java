package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class ParcelImpl implements Parcelable {
    final class androidx.versionedparcelable.ParcelImpl$1 implements Parcelable$Creator {
        androidx.versionedparcelable.ParcelImpl$1() {
            super();
        }

        public ParcelImpl a(Parcel arg2) {
            return new ParcelImpl(arg2);
        }

        public ParcelImpl[] a(int arg1) {
            return new ParcelImpl[arg1];
        }

        public Object createFromParcel(Parcel arg1) {
            return this.a(arg1);
        }

        public Object[] newArray(int arg1) {
            return this.a(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR;
    private final c a;

    static {
        ParcelImpl.CREATOR = new androidx.versionedparcelable.ParcelImpl$1();
    }

    protected ParcelImpl(Parcel arg2) {
        super();
        this.a = new b(arg2).h();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel arg1, int arg2) {
        new b(arg1).a(this.a);
    }
}

