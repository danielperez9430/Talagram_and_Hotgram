package com.google.android.exoplayer2.source;

import android.os.Handler;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.TransferListener;

public interface MediaSource {
    public final class MediaPeriodId {
        public final int adGroupIndex;
        public final int adIndexInAdGroup;
        public final long endPositionUs;
        public final int periodIndex;
        public final long windowSequenceNumber;

        public MediaPeriodId(int arg9, int arg10, int arg11, long arg12) {
            this(arg9, arg10, arg11, arg12, -9223372036854775808L);
        }

        public MediaPeriodId(int arg9, long arg10, long arg12) {
            this(arg9, -1, -1, arg10, arg12);
        }

        public MediaPeriodId(int arg3) {
            this(arg3, -1);
        }

        public MediaPeriodId(int arg9, long arg10) {
            this(arg9, -1, -1, arg10, -9223372036854775808L);
        }

        private MediaPeriodId(int arg1, int arg2, int arg3, long arg4, long arg6) {
            super();
            this.periodIndex = arg1;
            this.adGroupIndex = arg2;
            this.adIndexInAdGroup = arg3;
            this.windowSequenceNumber = arg4;
            this.endPositionUs = arg6;
        }

        public MediaPeriodId copyWithPeriodIndex(int arg10) {
            MediaPeriodId v0 = this.periodIndex == arg10 ? this : new MediaPeriodId(arg10, this.adGroupIndex, this.adIndexInAdGroup, this.windowSequenceNumber, this.endPositionUs);
            return v0;
        }

        public boolean equals(Object arg8) {
            boolean v0 = true;
            if(this == (((MediaPeriodId)arg8))) {
                return 1;
            }

            if(arg8 != null) {
                if(this.getClass() != arg8.getClass()) {
                }
                else {
                    if(this.periodIndex != ((MediaPeriodId)arg8).periodIndex || this.adGroupIndex != ((MediaPeriodId)arg8).adGroupIndex || this.adIndexInAdGroup != ((MediaPeriodId)arg8).adIndexInAdGroup || this.windowSequenceNumber != ((MediaPeriodId)arg8).windowSequenceNumber || this.endPositionUs != ((MediaPeriodId)arg8).endPositionUs) {
                        v0 = false;
                    }
                    else {
                    }

                    return v0;
                }
            }

            return 0;
        }

        public int hashCode() {
            return ((((527 + this.periodIndex) * 31 + this.adGroupIndex) * 31 + this.adIndexInAdGroup) * 31 + (((int)this.windowSequenceNumber))) * 31 + (((int)this.endPositionUs));
        }

        public boolean isAd() {
            boolean v0 = this.adGroupIndex != -1 ? true : false;
            return v0;
        }
    }

    public interface SourceInfoRefreshListener {
        void onSourceInfoRefreshed(MediaSource arg1, Timeline arg2, Object arg3);
    }

    void addEventListener(Handler arg1, MediaSourceEventListener arg2);

    MediaPeriod createPeriod(MediaPeriodId arg1, Allocator arg2);

    void maybeThrowSourceInfoRefreshError();

    @Deprecated void prepareSource(ExoPlayer arg1, boolean arg2, SourceInfoRefreshListener arg3);

    void prepareSource(ExoPlayer arg1, boolean arg2, SourceInfoRefreshListener arg3, TransferListener arg4);

    void releasePeriod(MediaPeriod arg1);

    void releaseSource(SourceInfoRefreshListener arg1);

    void removeEventListener(MediaSourceEventListener arg1);
}

