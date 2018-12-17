package com.google.android.exoplayer2.text.pgs;

import com.google.android.exoplayer2.text.Subtitle;
import java.util.List;

final class PgsSubtitle implements Subtitle {
    private final List cues;

    public PgsSubtitle(List arg1) {
        super();
        this.cues = arg1;
    }

    public List getCues(long arg1) {
        return this.cues;
    }

    public long getEventTime(int arg3) {
        return 0;
    }

    public int getEventTimeCount() {
        return 1;
    }

    public int getNextEventTimeIndex(long arg1) {
        return -1;
    }
}

