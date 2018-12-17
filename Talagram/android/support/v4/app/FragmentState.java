package android.support.v4.app;

import android.arch.lifecycle.u;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.util.Log;

final class FragmentState implements Parcelable {
    final class android.support.v4.app.FragmentState$1 implements Parcelable$Creator {
        android.support.v4.app.FragmentState$1() {
            super();
        }

        public FragmentState a(Parcel arg2) {
            return new FragmentState(arg2);
        }

        public FragmentState[] a(int arg1) {
            return new FragmentState[arg1];
        }

        public Object createFromParcel(Parcel arg1) {
            return this.a(arg1);
        }

        public Object[] newArray(int arg1) {
            return this.a(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR;
    final String a;
    final int b;
    final boolean c;
    final int d;
    final int e;
    final String f;
    final boolean g;
    final boolean h;
    final Bundle i;
    final boolean j;
    Bundle k;
    Fragment l;

    static {
        FragmentState.CREATOR = new android.support.v4.app.FragmentState$1();
    }

    FragmentState(Parcel arg4) {
        super();
        this.a = arg4.readString();
        this.b = arg4.readInt();
        boolean v1 = false;
        boolean v0 = arg4.readInt() != 0 ? true : false;
        this.c = v0;
        this.d = arg4.readInt();
        this.e = arg4.readInt();
        this.f = arg4.readString();
        v0 = arg4.readInt() != 0 ? true : false;
        this.g = v0;
        v0 = arg4.readInt() != 0 ? true : false;
        this.h = v0;
        this.i = arg4.readBundle();
        if(arg4.readInt() != 0) {
            v1 = true;
        }

        this.j = v1;
        this.k = arg4.readBundle();
    }

    FragmentState(Fragment arg2) {
        super();
        this.a = arg2.getClass().getName();
        this.b = arg2.mIndex;
        this.c = arg2.mFromLayout;
        this.d = arg2.mFragmentId;
        this.e = arg2.mContainerId;
        this.f = arg2.mTag;
        this.g = arg2.mRetainInstance;
        this.h = arg2.mDetached;
        this.i = arg2.mArguments;
        this.j = arg2.mHidden;
    }

    public Fragment a(k arg4, i arg5, Fragment arg6, n arg7, u arg8) {
        if(this.l == null) {
            Context v0 = arg4.i();
            if(this.i != null) {
                this.i.setClassLoader(v0.getClassLoader());
            }

            Fragment v5 = arg5 != null ? arg5.a(v0, this.a, this.i) : Fragment.instantiate(v0, this.a, this.i);
            this.l = v5;
            if(this.k != null) {
                this.k.setClassLoader(v0.getClassLoader());
                this.l.mSavedFragmentState = this.k;
            }

            this.l.setIndex(this.b, arg6);
            this.l.mFromLayout = this.c;
            this.l.mRestored = true;
            this.l.mFragmentId = this.d;
            this.l.mContainerId = this.e;
            this.l.mTag = this.f;
            this.l.mRetainInstance = this.g;
            this.l.mDetached = this.h;
            this.l.mHidden = this.j;
            this.l.mFragmentManager = arg4.b;
            if(!m.a) {
                goto label_67;
            }

            Log.v("FragmentManager", "Instantiated fragment " + this.l);
        }

    label_67:
        this.l.mChildNonConfig = arg7;
        this.l.mViewModelStore = arg8;
        return this.l;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel arg1, int arg2) {
        arg1.writeString(this.a);
        arg1.writeInt(this.b);
        arg1.writeInt(this.c);
        arg1.writeInt(this.d);
        arg1.writeInt(this.e);
        arg1.writeString(this.f);
        arg1.writeInt(this.g);
        arg1.writeInt(this.h);
        arg1.writeBundle(this.i);
        arg1.writeInt(this.j);
        arg1.writeBundle(this.k);
    }
}

