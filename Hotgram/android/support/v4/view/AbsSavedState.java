package android.support.v4.view;

import android.os.Parcel;
import android.os.Parcelable$ClassLoaderCreator;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public abstract class AbsSavedState implements Parcelable {
    final class android.support.v4.view.AbsSavedState$1 extends AbsSavedState {
        android.support.v4.view.AbsSavedState$1() {
            super(null);
        }
    }

    final class android.support.v4.view.AbsSavedState$2 implements Parcelable$ClassLoaderCreator {
        android.support.v4.view.AbsSavedState$2() {
            super();
        }

        public AbsSavedState a(Parcel arg2) {
            return this.a(arg2, null);
        }

        public AbsSavedState a(Parcel arg1, ClassLoader arg2) {
            if(arg1.readParcelable(arg2) == null) {
                return AbsSavedState.EMPTY_STATE;
            }

            throw new IllegalStateException("superState must be null");
        }

        public AbsSavedState[] a(int arg1) {
            return new AbsSavedState[arg1];
        }

        public Object createFromParcel(Parcel arg1) {
            return this.a(arg1);
        }

        public Object createFromParcel(Parcel arg1, ClassLoader arg2) {
            return this.a(arg1, arg2);
        }

        public Object[] newArray(int arg1) {
            return this.a(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR;
    public static final AbsSavedState EMPTY_STATE;
    private final Parcelable mSuperState;

    static {
        AbsSavedState.EMPTY_STATE = new android.support.v4.view.AbsSavedState$1();
        AbsSavedState.CREATOR = new android.support.v4.view.AbsSavedState$2();
    }

    protected AbsSavedState(Parcel arg1, ClassLoader arg2) {
        super();
        Parcelable v1 = arg1.readParcelable(arg2);
        if(v1 != null) {
        }
        else {
            AbsSavedState v1_1 = AbsSavedState.EMPTY_STATE;
        }

        this.mSuperState = v1;
    }

    protected AbsSavedState(Parcelable arg2) {
        super();
        if(arg2 != null) {
            if(arg2 != AbsSavedState.EMPTY_STATE) {
            }
            else {
                arg2 = null;
            }

            this.mSuperState = arg2;
            return;
        }

        throw new IllegalArgumentException("superState must not be null");
    }

    private AbsSavedState() {
        super();
        this.mSuperState = null;
    }

    protected AbsSavedState(Parcel arg2) {
        this(arg2, null);
    }

    AbsSavedState(android.support.v4.view.AbsSavedState$1 arg1) {
        this();
    }

    public int describeContents() {
        return 0;
    }

    public final Parcelable getSuperState() {
        return this.mSuperState;
    }

    public void writeToParcel(Parcel arg2, int arg3) {
        arg2.writeParcelable(this.mSuperState, arg3);
    }
}

