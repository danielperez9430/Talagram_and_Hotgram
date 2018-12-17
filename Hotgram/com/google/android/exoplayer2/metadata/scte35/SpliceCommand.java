package com.google.android.exoplayer2.metadata.scte35;

import com.google.android.exoplayer2.metadata.Metadata$Entry;

public abstract class SpliceCommand implements Entry {
    public SpliceCommand() {
        super();
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "SCTE-35 splice command: type=" + this.getClass().getSimpleName();
    }
}

