package com.google.android.exoplayer2;

import com.google.android.exoplayer2.util.Assertions;

public final class SeekParameters {
    public static final SeekParameters CLOSEST_SYNC;
    public static final SeekParameters DEFAULT;
    public static final SeekParameters EXACT;
    public static final SeekParameters NEXT_SYNC;
    public static final SeekParameters PREVIOUS_SYNC;
    public final long toleranceAfterUs;
    public final long toleranceBeforeUs;

    static {
        SeekParameters.EXACT = new SeekParameters(0, 0);
        SeekParameters.CLOSEST_SYNC = new SeekParameters(9223372036854775807L, 9223372036854775807L);
        SeekParameters.PREVIOUS_SYNC = new SeekParameters(9223372036854775807L, 0);
        SeekParameters.NEXT_SYNC = new SeekParameters(0, 9223372036854775807L);
        SeekParameters.DEFAULT = SeekParameters.EXACT;
    }

    public SeekParameters(long arg6, long arg8) {
        super();
        long v0 = 0;
        boolean v3 = false;
        boolean v2 = Long.compare(arg6, v0) >= 0 ? true : false;
        Assertions.checkArgument(v2);
        if(arg8 >= v0) {
            v3 = true;
        }

        Assertions.checkArgument(v3);
        this.toleranceBeforeUs = arg6;
        this.toleranceAfterUs = arg8;
    }

    public boolean equals(Object arg8) {
        boolean v0 = true;
        if(this == (((SeekParameters)arg8))) {
            return 1;
        }

        if(arg8 != null) {
            if(this.getClass() != arg8.getClass()) {
            }
            else {
                if(this.toleranceBeforeUs != ((SeekParameters)arg8).toleranceBeforeUs || this.toleranceAfterUs != ((SeekParameters)arg8).toleranceAfterUs) {
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
        return (((int)this.toleranceBeforeUs)) * 31 + (((int)this.toleranceAfterUs));
    }
}

