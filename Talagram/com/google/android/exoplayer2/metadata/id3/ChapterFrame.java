package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class ChapterFrame extends Id3Frame {
    final class com.google.android.exoplayer2.metadata.id3.ChapterFrame$1 implements Parcelable$Creator {
        com.google.android.exoplayer2.metadata.id3.ChapterFrame$1() {
            super();
        }

        public ChapterFrame createFromParcel(Parcel arg2) {
            return new ChapterFrame(arg2);
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public ChapterFrame[] newArray(int arg1) {
            return new ChapterFrame[arg1];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR = null;
    public static final String ID = "CHAP";
    public final String chapterId;
    public final long endOffset;
    public final int endTimeMs;
    public final long startOffset;
    public final int startTimeMs;
    private final Id3Frame[] subFrames;

    static {
        ChapterFrame.CREATOR = new com.google.android.exoplayer2.metadata.id3.ChapterFrame$1();
    }

    public ChapterFrame(String arg2, int arg3, int arg4, long arg5, long arg7, Id3Frame[] arg9) {
        super("CHAP");
        this.chapterId = arg2;
        this.startTimeMs = arg3;
        this.endTimeMs = arg4;
        this.startOffset = arg5;
        this.endOffset = arg7;
        this.subFrames = arg9;
    }

    ChapterFrame(Parcel arg5) {
        super("CHAP");
        this.chapterId = Util.castNonNull(arg5.readString());
        this.startTimeMs = arg5.readInt();
        this.endTimeMs = arg5.readInt();
        this.startOffset = arg5.readLong();
        this.endOffset = arg5.readLong();
        int v0 = arg5.readInt();
        this.subFrames = new Id3Frame[v0];
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.subFrames[v1] = arg5.readParcelable(Id3Frame.class.getClassLoader());
        }
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object arg8) {
        boolean v0 = true;
        if(this == (((ChapterFrame)arg8))) {
            return 1;
        }

        if(arg8 != null) {
            if(this.getClass() != arg8.getClass()) {
            }
            else {
                if(this.startTimeMs != ((ChapterFrame)arg8).startTimeMs || this.endTimeMs != ((ChapterFrame)arg8).endTimeMs || this.startOffset != ((ChapterFrame)arg8).startOffset || this.endOffset != ((ChapterFrame)arg8).endOffset || !Util.areEqual(this.chapterId, ((ChapterFrame)arg8).chapterId) || !Arrays.equals(this.subFrames, ((ChapterFrame)arg8).subFrames)) {
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
        int v1 = ((((527 + this.startTimeMs) * 31 + this.endTimeMs) * 31 + (((int)this.startOffset))) * 31 + (((int)this.endOffset))) * 31;
        int v0 = this.chapterId != null ? this.chapterId.hashCode() : 0;
        return v1 + v0;
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        arg5.writeString(this.chapterId);
        arg5.writeInt(this.startTimeMs);
        arg5.writeInt(this.endTimeMs);
        arg5.writeLong(this.startOffset);
        arg5.writeLong(this.endOffset);
        arg5.writeInt(this.subFrames.length);
        Id3Frame[] v6 = this.subFrames;
        int v0 = v6.length;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            arg5.writeParcelable(v6[v2], 0);
        }
    }
}

