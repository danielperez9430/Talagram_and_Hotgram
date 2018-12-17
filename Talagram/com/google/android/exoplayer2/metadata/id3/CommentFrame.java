package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.exoplayer2.util.Util;

public final class CommentFrame extends Id3Frame {
    final class com.google.android.exoplayer2.metadata.id3.CommentFrame$1 implements Parcelable$Creator {
        com.google.android.exoplayer2.metadata.id3.CommentFrame$1() {
            super();
        }

        public CommentFrame createFromParcel(Parcel arg2) {
            return new CommentFrame(arg2);
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public CommentFrame[] newArray(int arg1) {
            return new CommentFrame[arg1];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR = null;
    public static final String ID = "COMM";
    public final String description;
    public final String language;
    public final String text;

    static {
        CommentFrame.CREATOR = new com.google.android.exoplayer2.metadata.id3.CommentFrame$1();
    }

    public CommentFrame(String arg2, String arg3, String arg4) {
        super("COMM");
        this.language = arg2;
        this.description = arg3;
        this.text = arg4;
    }

    CommentFrame(Parcel arg2) {
        super("COMM");
        this.language = Util.castNonNull(arg2.readString());
        this.description = Util.castNonNull(arg2.readString());
        this.text = Util.castNonNull(arg2.readString());
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((CommentFrame)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(!Util.areEqual(this.description, ((CommentFrame)arg5).description) || !Util.areEqual(this.language, ((CommentFrame)arg5).language) || !Util.areEqual(this.text, ((CommentFrame)arg5).text)) {
                    v0 = false;
                }
                else {
                }

                return v0;
            }
        }

        return 0;
    }

    public int hashCode() {
        int v1 = 0;
        int v0 = this.language != null ? this.language.hashCode() : 0;
        int v2 = (527 + v0) * 31;
        v0 = this.description != null ? this.description.hashCode() : 0;
        v2 = (v2 + v0) * 31;
        if(this.text != null) {
            v1 = this.text.hashCode();
        }

        return v2 + v1;
    }

    public String toString() {
        return this.id + ": language=" + this.language + ", description=" + this.description;
    }

    public void writeToParcel(Parcel arg1, int arg2) {
        arg1.writeString(this.id);
        arg1.writeString(this.language);
        arg1.writeString(this.text);
    }
}

