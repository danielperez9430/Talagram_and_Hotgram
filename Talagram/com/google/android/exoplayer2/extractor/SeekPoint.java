package com.google.android.exoplayer2.extractor;

public final class SeekPoint {
    public static final SeekPoint START;
    public final long position;
    public final long timeUs;

    static {
        SeekPoint.START = new SeekPoint(0, 0);
    }

    public SeekPoint(long arg1, long arg3) {
        super();
        this.timeUs = arg1;
        this.position = arg3;
    }

    public boolean equals(Object arg8) {
        boolean v0 = true;
        if(this == (((SeekPoint)arg8))) {
            return 1;
        }

        if(arg8 != null) {
            if(this.getClass() != arg8.getClass()) {
            }
            else {
                if(this.timeUs != ((SeekPoint)arg8).timeUs || this.position != ((SeekPoint)arg8).position) {
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
        return (((int)this.timeUs)) * 31 + (((int)this.position));
    }

    public String toString() {
        return "[timeUs=" + this.timeUs + ", position=" + this.position + "]";
    }
}

