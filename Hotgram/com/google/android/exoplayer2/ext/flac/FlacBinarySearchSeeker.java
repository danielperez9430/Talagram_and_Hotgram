package com.google.android.exoplayer2.ext.flac;

import com.google.android.exoplayer2.extractor.BinarySearchSeeker$OutputFrameHolder;
import com.google.android.exoplayer2.extractor.BinarySearchSeeker$SeekTimestampConverter;
import com.google.android.exoplayer2.extractor.BinarySearchSeeker$TimestampSearchResult;
import com.google.android.exoplayer2.extractor.BinarySearchSeeker$TimestampSeeker;
import com.google.android.exoplayer2.extractor.BinarySearchSeeker;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.FlacStreamInfo;
import java.nio.ByteBuffer;

final class FlacBinarySearchSeeker extends BinarySearchSeeker {
    class com.google.android.exoplayer2.ext.flac.FlacBinarySearchSeeker$1 {
    }

    final class FlacSeekTimestampConverter implements SeekTimestampConverter {
        private final FlacStreamInfo streamInfo;

        public FlacSeekTimestampConverter(FlacStreamInfo arg1) {
            super();
            this.streamInfo = arg1;
        }

        public long timeUsToTargetTime(long arg2) {
            return Assertions.checkNotNull(this.streamInfo).getSampleIndex(arg2);
        }
    }

    final class FlacTimestampSeeker implements TimestampSeeker {
        private final FlacDecoderJni decoderJni;

        FlacTimestampSeeker(FlacDecoderJni arg1, com.google.android.exoplayer2.ext.flac.FlacBinarySearchSeeker$1 arg2) {
            this(arg1);
        }

        private FlacTimestampSeeker(FlacDecoderJni arg1) {
            super();
            this.decoderJni = arg1;
        }

        public int getTimestampSearchBytesRange() {
            return -1;
        }

        public TimestampSearchResult searchForTimestamp(ExtractorInput arg10, long arg11, OutputFrameHolder arg13) {
            ByteBuffer v0 = arg13.byteBuffer;
            long v1 = arg10.getPosition();
            this.getTimestampSearchBytesRange();
            this.decoderJni.reset(v1);
            try {
                this.decoderJni.decodeSampleWithBacktrackPosition(v0, v1);
            }
            catch(FlacFrameDecodeException ) {
                return TimestampSearchResult.NO_TIMESTAMP_IN_RANGE_RESULT;
            }

            if(v0.limit() == 0) {
                return TimestampSearchResult.NO_TIMESTAMP_IN_RANGE_RESULT;
            }

            long v3 = this.decoderJni.getLastFrameFirstSampleIndex();
            long v5 = this.decoderJni.getNextFrameFirstSampleIndex();
            long v7 = this.decoderJni.getDecodePosition();
            int v0_1 = v3 > arg11 || v5 <= arg11 ? 0 : 1;
            if(v0_1 != 0) {
                arg13.timeUs = this.decoderJni.getLastFrameTimestamp();
                return TimestampSearchResult.targetFoundResult(arg10.getPosition());
            }

            if(v5 <= arg11) {
                return TimestampSearchResult.underestimatedResult(v5, v7);
            }

            return TimestampSearchResult.overestimatedResult(v3, v1);
        }
    }

    private final FlacDecoderJni decoderJni;

    public FlacBinarySearchSeeker(FlacStreamInfo arg18, long arg19, long arg21, FlacDecoderJni arg23) {
        super(new FlacSeekTimestampConverter(arg18), new FlacTimestampSeeker(arg23, null), arg18.durationUs(), 0, arg18.totalSamples, arg19, arg21, arg18.getApproxBytesPerFrame(), Math.max(1, arg18.minFrameSize));
        this.decoderJni = Assertions.checkNotNull(arg23);
    }

    protected void onSeekOperationFinished(boolean arg1, long arg2) {
        if(!arg1) {
            this.decoderJni.reset(arg2);
        }
    }
}

