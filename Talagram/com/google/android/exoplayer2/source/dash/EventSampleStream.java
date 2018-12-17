package com.google.android.exoplayer2.source.dash;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.metadata.emsg.EventMessageEncoder;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.dash.manifest.EventStream;
import com.google.android.exoplayer2.util.Util;

final class EventSampleStream implements SampleStream {
    private int currentIndex;
    private final EventMessageEncoder eventMessageEncoder;
    private EventStream eventStream;
    private boolean eventStreamUpdatable;
    private long[] eventTimesUs;
    private boolean isFormatSentDownstream;
    private long pendingSeekPositionUs;
    private final Format upstreamFormat;

    EventSampleStream(EventStream arg3, Format arg4, boolean arg5) {
        super();
        this.upstreamFormat = arg4;
        this.eventStream = arg3;
        this.eventMessageEncoder = new EventMessageEncoder();
        this.pendingSeekPositionUs = -9223372036854775807L;
        this.eventTimesUs = arg3.presentationTimesUs;
        this.updateEventStream(arg3, arg5);
    }

    String eventStreamId() {
        return this.eventStream.id();
    }

    public boolean isReady() {
        return 1;
    }

    public void maybeThrowError() {
    }

    public int readData(FormatHolder arg7, DecoderInputBuffer arg8, boolean arg9) {
        if(!arg9) {
            if(!this.isFormatSentDownstream) {
            }
            else {
                int v1 = -3;
                int v2 = -4;
                if(this.currentIndex != this.eventTimesUs.length) {
                    int v7 = this.currentIndex;
                    this.currentIndex = v7 + 1;
                    byte[] v9 = this.eventMessageEncoder.encode(this.eventStream.events[v7], this.eventStream.timescale);
                    if(v9 != null) {
                        arg8.ensureSpaceForWrite(v9.length);
                        arg8.setFlags(1);
                        arg8.data.put(v9);
                        arg8.timeUs = this.eventTimesUs[v7];
                        return v2;
                    }
                    else {
                        return v1;
                    }
                }
                else if(!this.eventStreamUpdatable) {
                    arg8.setFlags(4);
                    return v2;
                }
                else {
                    return v1;
                }
            }
        }

        arg7.format = this.upstreamFormat;
        this.isFormatSentDownstream = true;
        return -5;
    }

    public void seekToUs(long arg5) {
        int v1 = 0;
        this.currentIndex = Util.binarySearchCeil(this.eventTimesUs, arg5, true, false);
        if((this.eventStreamUpdatable) && this.currentIndex == this.eventTimesUs.length) {
            v1 = 1;
        }

        if(v1 != 0) {
        }
        else {
            arg5 = -9223372036854775807L;
        }

        this.pendingSeekPositionUs = arg5;
    }

    public int skipData(long arg5) {
        int v5 = Math.max(this.currentIndex, Util.binarySearchCeil(this.eventTimesUs, arg5, true, false));
        int v6 = v5 - this.currentIndex;
        this.currentIndex = v5;
        return v6;
    }

    void updateEventStream(EventStream arg6, boolean arg7) {
        long v1 = -9223372036854775807L;
        long v3 = this.currentIndex == 0 ? v1 : this.eventTimesUs[this.currentIndex - 1];
        this.eventStreamUpdatable = arg7;
        this.eventStream = arg6;
        this.eventTimesUs = arg6.presentationTimesUs;
        if(this.pendingSeekPositionUs != v1) {
            this.seekToUs(this.pendingSeekPositionUs);
        }
        else if(v3 != v1) {
            this.currentIndex = Util.binarySearchCeil(this.eventTimesUs, v3, false, false);
        }
    }
}

