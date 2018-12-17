package com.google.android.exoplayer2.text.webvtt;

import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.util.Assertions;
import java.util.Collections;
import java.util.List;

final class Mp4WebvttSubtitle implements Subtitle {
    private final List cues;

    public Mp4WebvttSubtitle(List arg1) {
        super();
        this.cues = Collections.unmodifiableList(arg1);
    }

    public List getCues(long arg4) {
        List v4 = arg4 >= 0 ? this.cues : Collections.emptyList();
        return v4;
    }

    public long getEventTime(int arg3) {
        boolean v3 = arg3 == 0 ? true : false;
        Assertions.checkArgument(v3);
        return 0;
    }

    public int getEventTimeCount() {
        return 1;
    }

    public int getNextEventTimeIndex(long arg4) {
        int v4 = arg4 < 0 ? 0 : -1;
        return v4;
    }
}

