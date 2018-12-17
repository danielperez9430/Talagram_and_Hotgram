package com.google.android.exoplayer2.text.ssa;

import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Collections;
import java.util.List;

final class SsaSubtitle implements Subtitle {
    private final long[] cueTimesUs;
    private final Cue[] cues;

    public SsaSubtitle(Cue[] arg1, long[] arg2) {
        super();
        this.cues = arg1;
        this.cueTimesUs = arg2;
    }

    public List getCues(long arg4) {
        int v4 = Util.binarySearchFloor(this.cueTimesUs, arg4, true, false);
        if(v4 != -1) {
            if(this.cues[v4] == null) {
            }
            else {
                return Collections.singletonList(this.cues[v4]);
            }
        }

        return Collections.emptyList();
    }

    public long getEventTime(int arg4) {
        boolean v0 = false;
        boolean v2 = arg4 >= 0 ? true : false;
        Assertions.checkArgument(v2);
        if(arg4 < this.cueTimesUs.length) {
            v0 = true;
        }

        Assertions.checkArgument(v0);
        return this.cueTimesUs[arg4];
    }

    public int getEventTimeCount() {
        return this.cueTimesUs.length;
    }

    public int getNextEventTimeIndex(long arg3) {
        int v3 = Util.binarySearchCeil(this.cueTimesUs, arg3, false, false);
        if(v3 < this.cueTimesUs.length) {
        }
        else {
            v3 = -1;
        }

        return v3;
    }
}

