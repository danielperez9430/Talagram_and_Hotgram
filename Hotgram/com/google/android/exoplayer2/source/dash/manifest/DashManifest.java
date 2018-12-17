package com.google.android.exoplayer2.source.dash.manifest;

import android.net.Uri;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.offline.FilterableManifest;
import com.google.android.exoplayer2.offline.StreamKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DashManifest implements FilterableManifest {
    public final long availabilityStartTimeMs;
    public final long durationMs;
    public final boolean dynamic;
    public final Uri location;
    public final long minBufferTimeMs;
    public final long minUpdatePeriodMs;
    private final List periods;
    public final long publishTimeMs;
    public final long suggestedPresentationDelayMs;
    public final long timeShiftBufferDepthMs;
    public final UtcTimingElement utcTiming;

    public DashManifest(long arg4, long arg6, long arg8, boolean arg10, long arg11, long arg13, long arg15, long arg17, UtcTimingElement arg19, Uri arg20, List arg21) {
        DashManifest v0 = this;
        super();
        v0.availabilityStartTimeMs = arg4;
        v0.durationMs = arg6;
        v0.minBufferTimeMs = arg8;
        v0.dynamic = arg10;
        v0.minUpdatePeriodMs = arg11;
        v0.timeShiftBufferDepthMs = arg13;
        v0.suggestedPresentationDelayMs = arg15;
        v0.publishTimeMs = arg17;
        v0.utcTiming = arg19;
        v0.location = arg20;
        List v1 = arg21 == null ? Collections.emptyList() : arg21;
        v0.periods = v1;
    }

    public final DashManifest copy(List arg25) {
        long v7;
        DashManifest v0 = this;
        LinkedList v1 = new LinkedList(arg25);
        Collections.sort(((List)v1));
        v1.add(new StreamKey(-1, -1, -1));
        ArrayList v2 = new ArrayList();
        long v3 = 0;
        int v5;
        for(v5 = 0; true; ++v5) {
            v7 = -9223372036854775807L;
            if(v5 >= this.getPeriodCount()) {
                break;
            }

            if(v1.peek().periodIndex != v5) {
                long v9 = v0.getPeriodDurationMs(v5);
                if(v9 != v7) {
                    v3 += v9;
                }
            }
            else {
                Period v6 = v0.getPeriod(v5);
                v2.add(new Period(v6.id, v6.startMs - v3, DashManifest.copyAdaptationSets(v6.adaptationSets, v1), v6.eventStreams));
            }
        }

        if(v0.durationMs != v7) {
            v7 = v0.durationMs - v3;
        }

        return new DashManifest(v0.availabilityStartTimeMs, v7, v0.minBufferTimeMs, v0.dynamic, v0.minUpdatePeriodMs, v0.timeShiftBufferDepthMs, v0.suggestedPresentationDelayMs, v0.publishTimeMs, v0.utcTiming, v0.location, v2);
    }

    public Object copy(List arg1) {
        return this.copy(arg1);
    }

    private static ArrayList copyAdaptationSets(List arg12, LinkedList arg13) {
        Object v0 = arg13.poll();
        int v1 = ((StreamKey)v0).periodIndex;
        ArrayList v2 = new ArrayList();
        do {
            int v3 = ((StreamKey)v0).groupIndex;
            Object v4 = arg12.get(v3);
            List v5 = ((AdaptationSet)v4).representations;
            ArrayList v9 = new ArrayList();
            do {
                v9.add(v5.get(((StreamKey)v0).trackIndex));
                v0 = arg13.poll();
                if(((StreamKey)v0).periodIndex != v1) {
                    break;
                }
            }
            while(((StreamKey)v0).groupIndex == v3);

            v2.add(new AdaptationSet(((AdaptationSet)v4).id, ((AdaptationSet)v4).type, ((List)v9), ((AdaptationSet)v4).accessibilityDescriptors, ((AdaptationSet)v4).supplementalProperties));
        }
        while(((StreamKey)v0).periodIndex == v1);

        arg13.addFirst(v0);
        return v2;
    }

    public final Period getPeriod(int arg2) {
        return this.periods.get(arg2);
    }

    public final int getPeriodCount() {
        return this.periods.size();
    }

    public final long getPeriodDurationMs(int arg6) {
        long v0;
        long v1 = -9223372036854775807L;
        if(arg6 != this.periods.size() - 1) {
            v0 = this.periods.get(arg6 + 1).startMs;
        label_15:
            v0 -= this.periods.get(arg6).startMs;
        }
        else if(this.durationMs == v1) {
            v0 = v1;
        }
        else {
            v0 = this.durationMs;
            goto label_15;
        }

        return v0;
    }

    public final long getPeriodDurationUs(int arg3) {
        return C.msToUs(this.getPeriodDurationMs(arg3));
    }
}

