package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class ChapterTocFrame extends Id3Frame {
    final class com.google.android.exoplayer2.metadata.id3.ChapterTocFrame$1 implements Parcelable$Creator {
        com.google.android.exoplayer2.metadata.id3.ChapterTocFrame$1() {
            super();
        }

        public ChapterTocFrame createFromParcel(Parcel arg2) {
            return new ChapterTocFrame(arg2);
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public ChapterTocFrame[] newArray(int arg1) {
            return new ChapterTocFrame[arg1];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR = null;
    public static final String ID = "CTOC";
    public final String[] children;
    public final String elementId;
    public final boolean isOrdered;
    public final boolean isRoot;
    private final Id3Frame[] subFrames;

    static {
        ChapterTocFrame.CREATOR = new com.google.android.exoplayer2.metadata.id3.ChapterTocFrame$1();
    }

    public ChapterTocFrame(String arg2, boolean arg3, boolean arg4, String[] arg5, Id3Frame[] arg6) {
        super("CTOC");
        this.elementId = arg2;
        this.isRoot = arg3;
        this.isOrdered = arg4;
        this.children = arg5;
        this.subFrames = arg6;
    }

    ChapterTocFrame(Parcel arg5) {
        super("CTOC");
        this.elementId = Util.castNonNull(arg5.readString());
        int v1 = 0;
        boolean v2 = true;
        boolean v0 = arg5.readByte() != 0 ? true : false;
        this.isRoot = v0;
        if(arg5.readByte() != 0) {
        }
        else {
            v2 = false;
        }

        this.isOrdered = v2;
        this.children = arg5.createStringArray();
        int v0_1 = arg5.readInt();
        this.subFrames = new Id3Frame[v0_1];
        while(v1 < v0_1) {
            this.subFrames[v1] = arg5.readParcelable(Id3Frame.class.getClassLoader());
            ++v1;
        }
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((ChapterTocFrame)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(this.isRoot != ((ChapterTocFrame)arg5).isRoot || this.isOrdered != ((ChapterTocFrame)arg5).isOrdered || !Util.areEqual(this.elementId, ((ChapterTocFrame)arg5).elementId) || !Arrays.equals(this.children, ((ChapterTocFrame)arg5).children) || !Arrays.equals(this.subFrames, ((ChapterTocFrame)arg5).subFrames)) {
                    v0 = false;
                }
                else {
                }

                return v0;
            }
        }

        return 0;
    }

    public Id3Frame getSubFrame(int arg2) {
        return this.subFrames[arg2];
    }

    public int getSubFrameCount() {
        return this.subFrames.length;
    }

    public int hashCode() {
        int v1 = ((527 + this.isRoot) * 31 + this.isOrdered) * 31;
        int v0 = this.elementId != null ? this.elementId.hashCode() : 0;
        return v1 + v0;
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        arg5.writeString(this.elementId);
        arg5.writeByte(((byte)this.isRoot));
        arg5.writeByte(((byte)this.isOrdered));
        arg5.writeStringArray(this.children);
        arg5.writeInt(this.subFrames.length);
        Id3Frame[] v6 = this.subFrames;
        int v0 = v6.length;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            arg5.writeParcelable(v6[v2], 0);
        }
    }
}

