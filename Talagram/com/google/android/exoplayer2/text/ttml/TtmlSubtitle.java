package com.google.android.exoplayer2.text.ttml;

import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.util.Util;
import java.util.Collections;
import java.util.List;
import java.util.Map;

final class TtmlSubtitle implements Subtitle {
    private final long[] eventTimesUs;
    private final Map globalStyles;
    private final Map regionMap;
    private final TtmlNode root;

    public TtmlSubtitle(TtmlNode arg1, Map arg2, Map arg3) {
        super();
        this.root = arg1;
        this.regionMap = arg3;
        arg2 = arg2 != null ? Collections.unmodifiableMap(arg2) : Collections.emptyMap();
        this.globalStyles = arg2;
        this.eventTimesUs = arg1.getEventTimesUs();
    }

    public List getCues(long arg4) {
        return this.root.getCues(arg4, this.globalStyles, this.regionMap);
    }

    public long getEventTime(int arg4) {
        return this.eventTimesUs[arg4];
    }

    public int getEventTimeCount() {
        return this.eventTimesUs.length;
    }

    Map getGlobalStyles() {
        return this.globalStyles;
    }

    public int getNextEventTimeIndex(long arg3) {
        int v3 = Util.binarySearchCeil(this.eventTimesUs, arg3, false, false);
        if(v3 < this.eventTimesUs.length) {
        }
        else {
            v3 = -1;
        }

        return v3;
    }

    TtmlNode getRoot() {
        return this.root;
    }
}

