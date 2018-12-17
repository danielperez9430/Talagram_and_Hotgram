package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;

public abstract class BinarySearchSeeker {
    public class BinarySearchSeekMap implements SeekMap {
        private final long approxBytesPerFrame;
        private final long ceilingBytePosition;
        private final long ceilingTimePosition;
        private final long durationUs;
        private final long floorBytePosition;
        private final long floorTimePosition;
        private final SeekTimestampConverter seekTimestampConverter;

        public BinarySearchSeekMap(SeekTimestampConverter arg1, long arg2, long arg4, long arg6, long arg8, long arg10, long arg12) {
            super();
            this.seekTimestampConverter = arg1;
            this.durationUs = arg2;
            this.floorTimePosition = arg4;
            this.ceilingTimePosition = arg6;
            this.floorBytePosition = arg8;
            this.ceilingBytePosition = arg10;
            this.approxBytesPerFrame = arg12;
        }

        static long access$1000(BinarySearchSeekMap arg2) {
            return arg2.floorTimePosition;
        }

        static long access$1100(BinarySearchSeekMap arg2) {
            return arg2.ceilingTimePosition;
        }

        static long access$1200(BinarySearchSeekMap arg2) {
            return arg2.floorBytePosition;
        }

        static long access$1300(BinarySearchSeekMap arg2) {
            return arg2.ceilingBytePosition;
        }

        static long access$1400(BinarySearchSeekMap arg2) {
            return arg2.approxBytesPerFrame;
        }

        public long getDurationUs() {
            return this.durationUs;
        }

        public SeekPoints getSeekPoints(long arg14) {
            return new SeekPoints(new SeekPoint(arg14, SeekOperationParams.calculateNextSearchBytePosition(this.seekTimestampConverter.timeUsToTargetTime(arg14), this.floorTimePosition, this.ceilingTimePosition, this.floorBytePosition, this.ceilingBytePosition, this.approxBytesPerFrame)));
        }

        public boolean isSeekable() {
            return 1;
        }

        public long timeUsToTargetTime(long arg2) {
            return this.seekTimestampConverter.timeUsToTargetTime(arg2);
        }
    }

    final class DefaultSeekTimestampConverter implements SeekTimestampConverter {
        private DefaultSeekTimestampConverter() {
            super();
        }

        public long timeUsToTargetTime(long arg1) {
            return arg1;
        }
    }

    public final class OutputFrameHolder {
        public ByteBuffer byteBuffer;
        public long timeUs;

        public OutputFrameHolder(ByteBuffer arg3) {
            super();
            this.timeUs = 0;
            this.byteBuffer = arg3;
        }
    }

    public class SeekOperationParams {
        private final long approxBytesPerFrame;
        private long ceilingBytePosition;
        private long ceilingTimePosition;
        private long floorBytePosition;
        private long floorTimePosition;
        private long nextSearchBytePosition;
        private final long seekTimeUs;
        private final long targetTimePosition;

        protected SeekOperationParams(long arg1, long arg3, long arg5, long arg7, long arg9, long arg11, long arg13) {
            super();
            this.seekTimeUs = arg1;
            this.targetTimePosition = arg3;
            this.floorTimePosition = arg5;
            this.ceilingTimePosition = arg7;
            this.floorBytePosition = arg9;
            this.ceilingBytePosition = arg11;
            this.approxBytesPerFrame = arg13;
            this.nextSearchBytePosition = SeekOperationParams.calculateNextSearchBytePosition(arg3, arg5, arg7, arg9, arg11, arg13);
        }

        static long access$000(SeekOperationParams arg2) {
            return arg2.getSeekTimeUs();
        }

        static long access$100(SeekOperationParams arg2) {
            return arg2.getFloorBytePosition();
        }

        static long access$200(SeekOperationParams arg2) {
            return arg2.getCeilingBytePosition();
        }

        static long access$300(SeekOperationParams arg2) {
            return arg2.getNextSearchBytePosition();
        }

        static long access$400(SeekOperationParams arg2) {
            return arg2.getTargetTimePosition();
        }

        static void access$800(SeekOperationParams arg0, long arg1, long arg3) {
            arg0.updateSeekCeiling(arg1, arg3);
        }

        static void access$900(SeekOperationParams arg0, long arg1, long arg3) {
            arg0.updateSeekFloor(arg1, arg3);
        }

        protected static long calculateNextSearchBytePosition(long arg10, long arg12, long arg14, long arg16, long arg18, long arg20) {
            long v4 = 1;
            if(arg16 + v4 < arg18) {
                if(arg12 + v4 >= arg14) {
                }
                else {
                    long v0 = ((long)((((float)(arg10 - arg12))) * ((((float)(arg18 - arg16))) / (((float)(arg14 - arg12))))));
                    return Util.constrainValue(arg16 + v0 - arg20 - v0 / 20, arg16, arg18 - v4);
                }
            }

            return arg16;
        }

        private long getCeilingBytePosition() {
            return this.ceilingBytePosition;
        }

        private long getFloorBytePosition() {
            return this.floorBytePosition;
        }

        private long getNextSearchBytePosition() {
            return this.nextSearchBytePosition;
        }

        private long getSeekTimeUs() {
            return this.seekTimeUs;
        }

        private long getTargetTimePosition() {
            return this.targetTimePosition;
        }

        private void updateNextSearchBytePosition() {
            this.nextSearchBytePosition = SeekOperationParams.calculateNextSearchBytePosition(this.targetTimePosition, this.floorTimePosition, this.ceilingTimePosition, this.floorBytePosition, this.ceilingBytePosition, this.approxBytesPerFrame);
        }

        private void updateSeekCeiling(long arg1, long arg3) {
            this.ceilingTimePosition = arg1;
            this.ceilingBytePosition = arg3;
            this.updateNextSearchBytePosition();
        }

        private void updateSeekFloor(long arg1, long arg3) {
            this.floorTimePosition = arg1;
            this.floorBytePosition = arg3;
            this.updateNextSearchBytePosition();
        }
    }

    public interface SeekTimestampConverter {
        long timeUsToTargetTime(long arg1);
    }

    public final class TimestampSearchResult {
        @Retention(value=RetentionPolicy.SOURCE) @interface SearchResult {
        }

        public static final TimestampSearchResult NO_TIMESTAMP_IN_RANGE_RESULT = null;
        public static final int RESULT_NO_TIMESTAMP = -3;
        public static final int RESULT_POSITION_OVERESTIMATED = -1;
        public static final int RESULT_POSITION_UNDERESTIMATED = -2;
        public static final int RESULT_TARGET_TIMESTAMP_FOUND;
        private final long bytePositionToUpdate;
        private final int result;
        private final long timestampToUpdate;

        static {
            TimestampSearchResult.NO_TIMESTAMP_IN_RANGE_RESULT = new TimestampSearchResult(-3, -9223372036854775807L, -1);
        }

        private TimestampSearchResult(int arg1, long arg2, long arg4) {
            super();
            this.result = arg1;
            this.timestampToUpdate = arg2;
            this.bytePositionToUpdate = arg4;
        }

        static int access$500(TimestampSearchResult arg0) {
            return arg0.result;
        }

        static long access$600(TimestampSearchResult arg2) {
            return arg2.timestampToUpdate;
        }

        static long access$700(TimestampSearchResult arg2) {
            return arg2.bytePositionToUpdate;
        }

        public static TimestampSearchResult overestimatedResult(long arg7, long arg9) {
            return new TimestampSearchResult(-1, arg7, arg9);
        }

        public static TimestampSearchResult targetFoundResult(long arg7) {
            return new TimestampSearchResult(0, -9223372036854775807L, arg7);
        }

        public static TimestampSearchResult underestimatedResult(long arg7, long arg9) {
            return new TimestampSearchResult(-2, arg7, arg9);
        }
    }

    public interface TimestampSeeker {
        int getTimestampSearchBytesRange();

        TimestampSearchResult searchForTimestamp(ExtractorInput arg1, long arg2, OutputFrameHolder arg3);
    }

    private static final long MAX_SKIP_BYTES = 262144;
    private final int minimumSearchRange;
    protected final BinarySearchSeekMap seekMap;
    protected SeekOperationParams seekOperationParams;
    protected final TimestampSeeker timestampSeeker;

    protected BinarySearchSeeker(SeekTimestampConverter arg17, TimestampSeeker arg18, long arg19, long arg21, long arg23, long arg25, long arg27, long arg29, int arg31) {
        super();
        this.timestampSeeker = arg18;
        this.minimumSearchRange = arg31;
        this.seekMap = new BinarySearchSeekMap(arg17, arg19, arg21, arg23, arg25, arg27, arg29);
    }

    protected SeekOperationParams createSeekParamsForTargetTimeUs(long arg18) {
        return new SeekOperationParams(arg18, this.seekMap.timeUsToTargetTime(arg18), BinarySearchSeekMap.access$1000(this.seekMap), BinarySearchSeekMap.access$1100(this.seekMap), BinarySearchSeekMap.access$1200(this.seekMap), BinarySearchSeekMap.access$1300(this.seekMap), BinarySearchSeekMap.access$1400(this.seekMap));
    }

    public final SeekMap getSeekMap() {
        return this.seekMap;
    }

    public int handlePendingSeek(ExtractorInput arg12, PositionHolder arg13, OutputFrameHolder arg14) {
        // Method was not decompiled
    }

    public final boolean isSeeking() {
        boolean v0 = this.seekOperationParams != null ? true : false;
        return v0;
    }

    protected final void markSeekOperationFinished(boolean arg2, long arg3) {
        this.seekOperationParams = null;
        this.onSeekOperationFinished(arg2, arg3);
    }

    protected void onSeekOperationFinished(boolean arg1, long arg2) {
    }

    protected final int seekToPosition(ExtractorInput arg3, long arg4, PositionHolder arg6) {
        if(arg4 == arg3.getPosition()) {
            return 0;
        }

        arg6.position = arg4;
        return 1;
    }

    public final void setSeekTargetUs(long arg4) {
        if(this.seekOperationParams != null && SeekOperationParams.access$000(this.seekOperationParams) == arg4) {
            return;
        }

        this.seekOperationParams = this.createSeekParamsForTargetTimeUs(arg4);
    }

    protected final boolean skipInputUntilPosition(ExtractorInput arg4, long arg5) {
        arg5 -= arg4.getPosition();
        if(arg5 >= 0 && arg5 <= 262144) {
            arg4.skipFully(((int)arg5));
            return 1;
        }

        return 0;
    }
}

