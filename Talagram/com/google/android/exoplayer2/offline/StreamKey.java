package com.google.android.exoplayer2.offline;

public final class StreamKey implements Comparable {
    public final int groupIndex;
    public final int periodIndex;
    public final int trackIndex;

    public StreamKey(int arg2, int arg3) {
        this(0, arg2, arg3);
    }

    public StreamKey(int arg1, int arg2, int arg3) {
        super();
        this.periodIndex = arg1;
        this.groupIndex = arg2;
        this.trackIndex = arg3;
    }

    public int compareTo(StreamKey arg3) {
        int v0 = this.periodIndex - arg3.periodIndex;
        if(v0 == 0) {
            v0 = this.groupIndex - arg3.groupIndex;
            if(v0 == 0) {
                v0 = this.trackIndex - arg3.trackIndex;
            }
        }

        return v0;
    }

    public int compareTo(Object arg1) {
        return this.compareTo(((StreamKey)arg1));
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((StreamKey)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(this.periodIndex != ((StreamKey)arg5).periodIndex || this.groupIndex != ((StreamKey)arg5).groupIndex || this.trackIndex != ((StreamKey)arg5).trackIndex) {
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
        return (this.periodIndex * 31 + this.groupIndex) * 31 + this.trackIndex;
    }

    public String toString() {
        return this.periodIndex + "." + this.groupIndex + "." + this.trackIndex;
    }
}

