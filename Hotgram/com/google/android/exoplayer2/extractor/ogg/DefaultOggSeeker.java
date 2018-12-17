package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.SeekMap$SeekPoints;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.util.Assertions;
import java.io.EOFException;

final class DefaultOggSeeker implements OggSeeker {
    class com.google.android.exoplayer2.extractor.ogg.DefaultOggSeeker$1 {
    }

    class OggSeekMap implements SeekMap {
        private OggSeekMap(DefaultOggSeeker arg1) {
            DefaultOggSeeker.this = arg1;
            super();
        }

        OggSeekMap(DefaultOggSeeker arg1, com.google.android.exoplayer2.extractor.ogg.DefaultOggSeeker$1 arg2) {
            this(arg1);
        }

        public long getDurationUs() {
            return DefaultOggSeeker.this.streamReader.convertGranuleToTime(DefaultOggSeeker.this.totalGranules);
        }

        public SeekPoints getSeekPoints(long arg9) {
            long v0 = 0;
            if(arg9 == v0) {
                return new SeekPoints(new SeekPoint(v0, DefaultOggSeeker.this.startPosition));
            }

            return new SeekPoints(new SeekPoint(arg9, DefaultOggSeeker.this.getEstimatedPosition(DefaultOggSeeker.this.startPosition, DefaultOggSeeker.this.streamReader.convertTimeToGranule(arg9), 30000)));
        }

        public boolean isSeekable() {
            return 1;
        }
    }

    private static final int DEFAULT_OFFSET = 30000;
    public static final int MATCH_BYTE_RANGE = 100000;
    public static final int MATCH_RANGE = 72000;
    private static final int STATE_IDLE = 3;
    private static final int STATE_READ_LAST_PAGE = 1;
    private static final int STATE_SEEK = 2;
    private static final int STATE_SEEK_TO_END;
    private long end;
    private long endGranule;
    private final long endPosition;
    private final OggPageHeader pageHeader;
    private long positionBeforeSeekToEnd;
    private long start;
    private long startGranule;
    private final long startPosition;
    private int state;
    private final StreamReader streamReader;
    private long targetGranule;
    private long totalGranules;

    public DefaultOggSeeker(long arg4, long arg6, StreamReader arg8, int arg9, long arg10) {
        // Method was not decompiled
    }

    static long access$100(DefaultOggSeeker arg2) {
        return arg2.startPosition;
    }

    static StreamReader access$200(DefaultOggSeeker arg0) {
        return arg0.streamReader;
    }

    static long access$300(DefaultOggSeeker arg0, long arg1, long arg3, long arg5) {
        return arg0.getEstimatedPosition(arg1, arg3, arg5);
    }

    static long access$400(DefaultOggSeeker arg2) {
        return arg2.totalGranules;
    }

    public SeekMap createSeekMap() {
        return this.createSeekMap();
    }

    public OggSeekMap createSeekMap() {
        // Method was not decompiled
    }

    private long getEstimatedPosition(long arg5, long arg7, long arg9) {
        arg5 += arg7 * (this.endPosition - this.startPosition) / this.totalGranules - arg9;
        if(arg5 < this.startPosition) {
            arg5 = this.startPosition;
        }

        if(arg5 >= this.endPosition) {
            arg5 = this.endPosition - 1;
        }

        return arg5;
    }

    public long getNextSeekPosition(long arg17, ExtractorInput arg19) {
        // Method was not decompiled
    }

    public long read(ExtractorInput arg15) {
        // Method was not decompiled
    }

    long readGranuleOfLastPage(ExtractorInput arg6) {
        this.skipToNextPage(arg6);
        this.pageHeader.reset();
        while((this.pageHeader.type & 4) != 4) {
            if(arg6.getPosition() >= this.endPosition) {
                break;
            }

            this.pageHeader.populate(arg6, false);
            arg6.skipFully(this.pageHeader.headerSize + this.pageHeader.bodySize);
        }

        return this.pageHeader.granulePosition;
    }

    public void resetSeeking() {
        this.start = this.startPosition;
        this.end = this.endPosition;
        this.startGranule = 0;
        this.endGranule = this.totalGranules;
    }

    void skipToNextPage(ExtractorInput arg3) {
        if(this.skipToNextPage(arg3, this.endPosition)) {
            return;
        }

        throw new EOFException();
    }

    boolean skipToNextPage(ExtractorInput arg7, long arg8) {
        // Method was not decompiled
    }

    long skipToPageOfGranule(ExtractorInput arg5, long arg6, long arg8) {
        OggPageHeader v0;
        for(v0 = this.pageHeader; true; v0 = this.pageHeader) {
            v0.populate(arg5, false);
            if(this.pageHeader.granulePosition >= arg6) {
                break;
            }

            arg5.skipFully(this.pageHeader.headerSize + this.pageHeader.bodySize);
            arg8 = this.pageHeader.granulePosition;
        }

        arg5.resetPeekPosition();
        return arg8;
    }

    public long startSeek(long arg5) {
        int v1 = 2;
        boolean v0 = this.state == 3 || this.state == v1 ? true : false;
        Assertions.checkArgument(v0);
        long v2 = 0;
        if(arg5 == v2) {
        }
        else {
            v2 = this.streamReader.convertTimeToGranule(arg5);
        }

        this.targetGranule = v2;
        this.state = v1;
        this.resetSeeking();
        return this.targetGranule;
    }
}

