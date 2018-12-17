package com.google.android.gms.common.internal.safeparcel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

@VisibleForTesting public final class SafeParcelableSerializer {
    public SafeParcelableSerializer() {
        super();
    }

    public static SafeParcelable deserializeFromBytes(byte[] arg3, Parcelable$Creator arg4) {
        Preconditions.checkNotNull(arg4);
        Parcel v0 = Parcel.obtain();
        v0.unmarshall(arg3, 0, arg3.length);
        v0.setDataPosition(0);
        Object v3 = arg4.createFromParcel(v0);
        v0.recycle();
        return ((SafeParcelable)v3);
    }

    public static SafeParcelable deserializeFromIntentExtra(Intent arg0, String arg1, Parcelable$Creator arg2) {
        byte[] v0 = arg0.getByteArrayExtra(arg1);
        if(v0 == null) {
            return null;
        }

        return SafeParcelableSerializer.deserializeFromBytes(v0, arg2);
    }

    public static SafeParcelable deserializeFromString(String arg0, Parcelable$Creator arg1) {
        return SafeParcelableSerializer.deserializeFromBytes(Base64Utils.decodeUrlSafe(arg0), arg1);
    }

    public static ArrayList deserializeIterableFromBundle(Bundle arg3, String arg4, Parcelable$Creator arg5) {
        Serializable v3 = arg3.getSerializable(arg4);
        if(v3 == null) {
            return null;
        }

        ArrayList v4 = new ArrayList(((ArrayList)v3).size());
        int v0 = ((ArrayList)v3).size();
        int v1 = 0;
        while(v1 < v0) {
            Object v2 = ((ArrayList)v3).get(v1);
            ++v1;
            v4.add(SafeParcelableSerializer.deserializeFromBytes(((byte[])v2), arg5));
        }

        return v4;
    }

    public static ArrayList deserializeIterableFromIntentExtra(Intent arg3, String arg4, Parcelable$Creator arg5) {
        Serializable v3 = arg3.getSerializableExtra(arg4);
        if(v3 == null) {
            return null;
        }

        ArrayList v4 = new ArrayList(((ArrayList)v3).size());
        int v0 = ((ArrayList)v3).size();
        int v1 = 0;
        while(v1 < v0) {
            Object v2 = ((ArrayList)v3).get(v1);
            ++v1;
            v4.add(SafeParcelableSerializer.deserializeFromBytes(((byte[])v2), arg5));
        }

        return v4;
    }

    public static void serializeIterableToBundle(Iterable arg2, Bundle arg3, String arg4) {
        ArrayList v0 = new ArrayList();
        Iterator v2 = arg2.iterator();
        while(v2.hasNext()) {
            v0.add(SafeParcelableSerializer.serializeToBytes(v2.next()));
        }

        arg3.putSerializable(arg4, ((Serializable)v0));
    }

    public static void serializeIterableToIntentExtra(Iterable arg2, Intent arg3, String arg4) {
        ArrayList v0 = new ArrayList();
        Iterator v2 = arg2.iterator();
        while(v2.hasNext()) {
            v0.add(SafeParcelableSerializer.serializeToBytes(v2.next()));
        }

        arg3.putExtra(arg4, ((Serializable)v0));
    }

    public static byte[] serializeToBytes(SafeParcelable arg2) {
        Parcel v0 = Parcel.obtain();
        arg2.writeToParcel(v0, 0);
        byte[] v2 = v0.marshall();
        v0.recycle();
        return v2;
    }

    public static void serializeToIntentExtra(SafeParcelable arg0, Intent arg1, String arg2) {
        arg1.putExtra(arg2, SafeParcelableSerializer.serializeToBytes(arg0));
    }

    public static String serializeToString(SafeParcelable arg0) {
        return Base64Utils.encodeUrlSafe(SafeParcelableSerializer.serializeToBytes(arg0));
    }
}

