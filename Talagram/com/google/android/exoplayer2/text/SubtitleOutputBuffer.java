package com.google.android.exoplayer2.text;

import com.google.android.exoplayer2.decoder.OutputBuffer;
import java.util.List;

public abstract class SubtitleOutputBuffer extends OutputBuffer implements Subtitle {
    private long subsampleOffsetUs;
    private Subtitle subtitle;

    public SubtitleOutputBuffer() {
        super();
    }

    public void clear() {
        super.clear();
        this.subtitle = null;
    }

    public List getCues(long arg4) {
        return this.subtitle.getCues(arg4 - this.subsampleOffsetUs);
    }

    public long getEventTime(int arg5) {
        return this.subtitle.getEventTime(arg5) + this.subsampleOffsetUs;
    }

    public int getEventTimeCount() {
        return this.subtitle.getEventTimeCount();
    }

    public int getNextEventTimeIndex(long arg4) {
        return this.subtitle.getNextEventTimeIndex(arg4 - this.subsampleOffsetUs);
    }

    public abstract void release();

    public void setContent(long arg1, Subtitle arg3, long arg4) {
        this.timeUs = arg1;
        this.subtitle = arg3;
        if(arg4 == 9223372036854775807L) {
            arg4 = this.timeUs;
        }

        this.subsampleOffsetUs = arg4;
    }
}

