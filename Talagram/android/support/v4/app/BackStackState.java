package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;

final class BackStackState implements Parcelable {
    final class android.support.v4.app.BackStackState$1 implements Parcelable$Creator {
        android.support.v4.app.BackStackState$1() {
            super();
        }

        public BackStackState a(Parcel arg2) {
            return new BackStackState(arg2);
        }

        public BackStackState[] a(int arg1) {
            return new BackStackState[arg1];
        }

        public Object createFromParcel(Parcel arg1) {
            return this.a(arg1);
        }

        public Object[] newArray(int arg1) {
            return this.a(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR;
    final int[] a;
    final int b;
    final int c;
    final String d;
    final int e;
    final int f;
    final CharSequence g;
    final int h;
    final CharSequence i;
    final ArrayList j;
    final ArrayList k;
    final boolean l;

    static {
        BackStackState.CREATOR = new android.support.v4.app.BackStackState$1();
    }

    public BackStackState(Parcel arg2) {
        super();
        this.a = arg2.createIntArray();
        this.b = arg2.readInt();
        this.c = arg2.readInt();
        this.d = arg2.readString();
        this.e = arg2.readInt();
        this.f = arg2.readInt();
        this.g = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(arg2);
        this.h = arg2.readInt();
        this.i = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(arg2);
        this.j = arg2.createStringArrayList();
        this.k = arg2.createStringArrayList();
        boolean v2 = arg2.readInt() != 0 ? true : false;
        this.l = v2;
    }

    public BackStackState(e arg8) {
        super();
        int v0 = arg8.b.size();
        this.a = new int[v0 * 6];
        if(arg8.i) {
            int v1 = 0;
            int v2;
            for(v2 = 0; v1 < v0; v2 = v5 + 1) {
                Object v3 = arg8.b.get(v1);
                int v5 = v2 + 1;
                this.a[v2] = ((a)v3).a;
                int[] v2_1 = this.a;
                int v4 = v5 + 1;
                int v6 = ((a)v3).b != null ? ((a)v3).b.mIndex : -1;
                v2_1[v5] = v6;
                v5 = v4 + 1;
                this.a[v4] = ((a)v3).c;
                v4 = v5 + 1;
                this.a[v5] = ((a)v3).d;
                v5 = v4 + 1;
                this.a[v4] = ((a)v3).e;
                this.a[v5] = ((a)v3).f;
                ++v1;
            }

            this.b = arg8.g;
            this.c = arg8.h;
            this.d = arg8.k;
            this.e = arg8.m;
            this.f = arg8.n;
            this.g = arg8.o;
            this.h = arg8.p;
            this.i = arg8.q;
            this.j = arg8.r;
            this.k = arg8.s;
            this.l = arg8.t;
            return;
        }

        throw new IllegalStateException("Not on back stack");
    }

    public e a(m arg8) {
        Fragment v1_2;
        e v0 = new e(arg8);
        int v1 = 0;
        int v2 = 0;
        while(v1 < this.a.length) {
            a v3 = new a();
            int v5 = v1 + 1;
            v3.a = this.a[v1];
            if(m.a) {
                Log.v("FragmentManager", "Instantiate " + v0 + " op #" + v2 + " base fragment #" + this.a[v5]);
            }

            int v4_1 = v5 + 1;
            v1 = this.a[v5];
            if(v1 >= 0) {
                Object v1_1 = arg8.f.get(v1);
            }
            else {
                v1_2 = null;
            }

            v3.b = v1_2;
            v5 = v4_1 + 1;
            v3.c = this.a[v4_1];
            v4_1 = v5 + 1;
            v3.d = this.a[v5];
            v5 = v4_1 + 1;
            v3.e = this.a[v4_1];
            v3.f = this.a[v5];
            v0.c = v3.c;
            v0.d = v3.d;
            v0.e = v3.e;
            v0.f = v3.f;
            v0.a(v3);
            ++v2;
            v1 = v5 + 1;
        }

        v0.g = this.b;
        v0.h = this.c;
        v0.k = this.d;
        v0.m = this.e;
        v0.i = true;
        v0.n = this.f;
        v0.o = this.g;
        v0.p = this.h;
        v0.q = this.i;
        v0.r = this.j;
        v0.s = this.k;
        v0.t = this.l;
        v0.a(1);
        return v0;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel arg2, int arg3) {
        arg2.writeIntArray(this.a);
        arg2.writeInt(this.b);
        arg2.writeInt(this.c);
        arg2.writeString(this.d);
        arg2.writeInt(this.e);
        arg2.writeInt(this.f);
        TextUtils.writeToParcel(this.g, arg2, 0);
        arg2.writeInt(this.h);
        TextUtils.writeToParcel(this.i, arg2, 0);
        arg2.writeStringList(this.j);
        arg2.writeStringList(this.k);
        arg2.writeInt(this.l);
    }
}

