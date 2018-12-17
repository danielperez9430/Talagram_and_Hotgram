package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.Format;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class Track {
    @Retention(value=RetentionPolicy.SOURCE) @public interface Transformation {
    }

    public static final int TRANSFORMATION_CEA608_CDAT = 1;
    public static final int TRANSFORMATION_NONE;
    public final long durationUs;
    public final long[] editListDurations;
    public final long[] editListMediaTimes;
    public final Format format;
    public final int id;
    public final long movieTimescale;
    public final int nalUnitLengthFieldLength;
    private final TrackEncryptionBox[] sampleDescriptionEncryptionBoxes;
    public final int sampleTransformation;
    public final long timescale;
    public final int type;

    public Track(int arg1, int arg2, long arg3, long arg5, long arg7, Format arg9, int arg10, TrackEncryptionBox[] arg11, int arg12, long[] arg13, long[] arg14) {
        super();
        this.id = arg1;
        this.type = arg2;
        this.timescale = arg3;
        this.movieTimescale = arg5;
        this.durationUs = arg7;
        this.format = arg9;
        this.sampleTransformation = arg10;
        this.sampleDescriptionEncryptionBoxes = arg11;
        this.nalUnitLengthFieldLength = arg12;
        this.editListDurations = arg13;
        this.editListMediaTimes = arg14;
    }

    public TrackEncryptionBox getSampleDescriptionEncryptionBox(int arg2) {
        TrackEncryptionBox v2 = this.sampleDescriptionEncryptionBoxes == null ? null : this.sampleDescriptionEncryptionBoxes[arg2];
        return v2;
    }
}

