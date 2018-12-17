package com.google.android.exoplayer2.trackselection;

import java.util.Arrays;

public final class TrackSelectionArray {
    private int hashCode;
    public final int length;
    private final TrackSelection[] trackSelections;

    public TrackSelectionArray(TrackSelection[] arg1) {
        super();
        this.trackSelections = arg1;
        this.length = arg1.length;
    }

    public boolean equals(Object arg3) {
        if(this == (((TrackSelectionArray)arg3))) {
            return 1;
        }

        if(arg3 != null) {
            if(this.getClass() != arg3.getClass()) {
            }
            else {
                return Arrays.equals(this.trackSelections, ((TrackSelectionArray)arg3).trackSelections);
            }
        }

        return 0;
    }

    public TrackSelection get(int arg2) {
        return this.trackSelections[arg2];
    }

    public TrackSelection[] getAll() {
        // Method was not decompiled
    }

    public int hashCode() {
        if(this.hashCode == 0) {
            this.hashCode = 527 + Arrays.hashCode(this.trackSelections);
        }

        return this.hashCode;
    }
}

