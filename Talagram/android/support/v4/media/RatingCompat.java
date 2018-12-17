package android.support.v4.media;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public final class RatingCompat implements Parcelable {
    final class android.support.v4.media.RatingCompat$1 implements Parcelable$Creator {
        android.support.v4.media.RatingCompat$1() {
            super();
        }

        public RatingCompat a(Parcel arg3) {
            return new RatingCompat(arg3.readInt(), arg3.readFloat());
        }

        public RatingCompat[] a(int arg1) {
            return new RatingCompat[arg1];
        }

        public Object createFromParcel(Parcel arg1) {
            return this.a(arg1);
        }

        public Object[] newArray(int arg1) {
            return this.a(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR;
    private final int a;
    private final float b;

    static {
        RatingCompat.CREATOR = new android.support.v4.media.RatingCompat$1();
    }

    RatingCompat(int arg1, float arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public int describeContents() {
        return this.a;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder();
        v0.append("Rating:style=");
        v0.append(this.a);
        v0.append(" rating=");
        String v1 = this.b < 0f ? "unrated" : String.valueOf(this.b);
        v0.append(v1);
        return v0.toString();
    }

    public void writeToParcel(Parcel arg1, int arg2) {
        arg1.writeInt(this.a);
        arg1.writeFloat(this.b);
    }
}

