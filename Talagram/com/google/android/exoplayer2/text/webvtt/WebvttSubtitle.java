package com.google.android.exoplayer2.text.webvtt;

import android.text.SpannableStringBuilder;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class WebvttSubtitle implements Subtitle {
    private final long[] cueTimesUs;
    private final List cues;
    private final int numCues;
    private final long[] sortedCueTimesUs;

    public WebvttSubtitle(List arg7) {
        super();
        this.cues = arg7;
        this.numCues = arg7.size();
        this.cueTimesUs = new long[this.numCues * 2];
        int v0;
        for(v0 = 0; v0 < this.numCues; ++v0) {
            Object v1 = arg7.get(v0);
            int v2 = v0 * 2;
            this.cueTimesUs[v2] = ((WebvttCue)v1).startTime;
            this.cueTimesUs[v2 + 1] = ((WebvttCue)v1).endTime;
        }

        this.sortedCueTimesUs = Arrays.copyOf(this.cueTimesUs, this.cueTimesUs.length);
        Arrays.sort(this.sortedCueTimesUs);
    }

    public List getCues(long arg9) {
        SpannableStringBuilder v5_1;
        SpannableStringBuilder v0 = null;
        int v1 = 0;
        ArrayList v2 = ((ArrayList)v0);
        Object v3 = v2;
        while(v1 < this.numCues) {
            int v5 = v1 * 2;
            if(this.cueTimesUs[v5] <= arg9 && arg9 < this.cueTimesUs[v5 + 1]) {
                if(v2 == null) {
                    v2 = new ArrayList();
                }

                Object v4 = this.cues.get(v1);
                if(((WebvttCue)v4).isNormalCue()) {
                    if(v3 == null) {
                        v3 = v4;
                        goto label_38;
                    }

                    if(v0 == null) {
                        v0 = new SpannableStringBuilder();
                        v5_1 = v0.append(((WebvttCue)v3).text).append("\n");
                    }
                    else {
                        v5_1 = v0.append("\n");
                    }

                    v5_1.append(((WebvttCue)v4).text);
                    goto label_38;
                }

                v2.add(v4);
            }

        label_38:
            ++v1;
        }

        if(v0 != null) {
            v2.add(new WebvttCue(((CharSequence)v0)));
        }
        else if(v3 != null) {
            v2.add(v3);
        }

        if(v2 != null) {
            return ((List)v2);
        }

        return Collections.emptyList();
    }

    public long getEventTime(int arg4) {
        boolean v0 = false;
        boolean v2 = arg4 >= 0 ? true : false;
        Assertions.checkArgument(v2);
        if(arg4 < this.sortedCueTimesUs.length) {
            v0 = true;
        }

        Assertions.checkArgument(v0);
        return this.sortedCueTimesUs[arg4];
    }

    public int getEventTimeCount() {
        return this.sortedCueTimesUs.length;
    }

    public int getNextEventTimeIndex(long arg3) {
        int v3 = Util.binarySearchCeil(this.sortedCueTimesUs, arg3, false, false);
        if(v3 < this.sortedCueTimesUs.length) {
        }
        else {
            v3 = -1;
        }

        return v3;
    }
}

