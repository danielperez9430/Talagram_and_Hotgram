package com.google.android.exoplayer2.metadata;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.List;

public final class Metadata implements Parcelable {
    final class com.google.android.exoplayer2.metadata.Metadata$1 implements Parcelable$Creator {
        com.google.android.exoplayer2.metadata.Metadata$1() {
            super();
        }

        public Metadata createFromParcel(Parcel arg2) {
            return new Metadata(arg2);
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public Metadata[] newArray(int arg1) {
            return new Metadata[0];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    public interface Entry extends Parcelable {
    }

    public static final Parcelable$Creator CREATOR;
    private final Entry[] entries;

    static {
        Metadata.CREATOR = new com.google.android.exoplayer2.metadata.Metadata$1();
    }

    public Metadata(List arg2) {
        super();
        if(arg2 != null) {
            this.entries = new Entry[arg2.size()];
            arg2.toArray(this.entries);
        }
        else {
            this.entries = new Entry[0];
        }
    }

    Metadata(Parcel arg4) {
        super();
        this.entries = new Entry[arg4.readInt()];
        int v0;
        for(v0 = 0; v0 < this.entries.length; ++v0) {
            this.entries[v0] = arg4.readParcelable(Entry.class.getClassLoader());
        }
    }

    public Metadata(Entry[] arg1) {
        super();
        if(arg1 == null) {
            arg1 = new Entry[0];
        }

        this.entries = arg1;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object arg3) {
        if(this == (((Metadata)arg3))) {
            return 1;
        }

        if(arg3 != null) {
            if(this.getClass() != arg3.getClass()) {
            }
            else {
                return Arrays.equals(this.entries, ((Metadata)arg3).entries);
            }
        }

        return 0;
    }

    public Entry get(int arg2) {
        return this.entries[arg2];
    }

    public int hashCode() {
        return Arrays.hashCode(this.entries);
    }

    public int length() {
        return this.entries.length;
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        arg5.writeInt(this.entries.length);
        Entry[] v6 = this.entries;
        int v0 = v6.length;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            arg5.writeParcelable(v6[v2], 0);
        }
    }
}

