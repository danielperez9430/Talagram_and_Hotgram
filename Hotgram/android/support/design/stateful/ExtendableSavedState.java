package android.support.design.stateful;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$ClassLoaderCreator;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.v4.f.m;
import android.support.v4.view.AbsSavedState;

public class ExtendableSavedState extends AbsSavedState {
    final class android.support.design.stateful.ExtendableSavedState$1 implements Parcelable$ClassLoaderCreator {
        android.support.design.stateful.ExtendableSavedState$1() {
            super();
        }

        public ExtendableSavedState a(Parcel arg3) {
            return new ExtendableSavedState(arg3, null, null);
        }

        public ExtendableSavedState a(Parcel arg3, ClassLoader arg4) {
            return new ExtendableSavedState(arg3, arg4, null);
        }

        public ExtendableSavedState[] a(int arg1) {
            return new ExtendableSavedState[arg1];
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
    public final m a;

    static {
        ExtendableSavedState.CREATOR = new android.support.design.stateful.ExtendableSavedState$1();
    }

    private ExtendableSavedState(Parcel arg6, ClassLoader arg7) {
        super(arg6, arg7);
        int v7 = arg6.readInt();
        String[] v0 = new String[v7];
        arg6.readStringArray(v0);
        Bundle[] v1 = new Bundle[v7];
        arg6.readTypedArray(((Object[])v1), Bundle.CREATOR);
        this.a = new m(v7);
        int v6;
        for(v6 = 0; v6 < v7; ++v6) {
            this.a.put(v0[v6], v1[v6]);
        }
    }

    ExtendableSavedState(Parcel arg1, ClassLoader arg2, android.support.design.stateful.ExtendableSavedState$1 arg3) {
        this(arg1, arg2);
    }

    public ExtendableSavedState(Parcelable arg1) {
        super(arg1);
        this.a = new m();
    }

    public String toString() {
        return "ExtendableSavedState{" + Integer.toHexString(System.identityHashCode(this)) + " states=" + this.a + "}";
    }

    public void writeToParcel(Parcel arg6, int arg7) {
        super.writeToParcel(arg6, arg7);
        arg7 = this.a.size();
        arg6.writeInt(arg7);
        String[] v0 = new String[arg7];
        Bundle[] v1 = new Bundle[arg7];
        int v3;
        for(v3 = 0; v3 < arg7; ++v3) {
            v0[v3] = this.a.b(v3);
            v1[v3] = this.a.c(v3);
        }

        arg6.writeStringArray(v0);
        arg6.writeTypedArray(((Parcelable[])v1), 0);
    }
}

