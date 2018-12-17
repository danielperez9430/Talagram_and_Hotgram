package com.google.android.exoplayer2.extractor.ts;

import android.util.SparseArray;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import java.util.Collections;
import java.util.List;

public interface TsPayloadReader {
    public final class DvbSubtitleInfo {
        public final byte[] initializationData;
        public final String language;
        public final int type;

        public DvbSubtitleInfo(String arg1, int arg2, byte[] arg3) {
            super();
            this.language = arg1;
            this.type = arg2;
            this.initializationData = arg3;
        }
    }

    public final class EsInfo {
        public final byte[] descriptorBytes;
        public final List dvbSubtitleInfos;
        public final String language;
        public final int streamType;

        public EsInfo(int arg1, String arg2, List arg3, byte[] arg4) {
            super();
            this.streamType = arg1;
            this.language = arg2;
            List v1 = arg3 == null ? Collections.emptyList() : Collections.unmodifiableList(arg3);
            this.dvbSubtitleInfos = v1;
            this.descriptorBytes = arg4;
        }
    }

    public interface Factory {
        SparseArray createInitialPayloadReaders();

        TsPayloadReader createPayloadReader(int arg1, EsInfo arg2);
    }

    public final class TrackIdGenerator {
        private static final int ID_UNSET = -2147483648;
        private final int firstTrackId;
        private String formatId;
        private final String formatIdPrefix;
        private int trackId;
        private final int trackIdIncrement;

        public TrackIdGenerator(int arg2, int arg3) {
            this(-2147483648, arg2, arg3);
        }

        public TrackIdGenerator(int arg3, int arg4, int arg5) {
            super();
            int v0 = -2147483648;
            String v3 = arg3 != v0 ? arg3 + "/" : "";
            this.formatIdPrefix = v3;
            this.firstTrackId = arg4;
            this.trackIdIncrement = arg5;
            this.trackId = v0;
        }

        public void generateNewId() {
            int v0 = this.trackId == -2147483648 ? this.firstTrackId : this.trackId + this.trackIdIncrement;
            this.trackId = v0;
            this.formatId = this.formatIdPrefix + this.trackId;
        }

        public String getFormatId() {
            this.maybeThrowUninitializedError();
            return this.formatId;
        }

        public int getTrackId() {
            this.maybeThrowUninitializedError();
            return this.trackId;
        }

        private void maybeThrowUninitializedError() {
            if(this.trackId != -2147483648) {
                return;
            }

            throw new IllegalStateException("generateNewId() must be called before retrieving ids.");
        }
    }

    void consume(ParsableByteArray arg1, boolean arg2);

    void init(TimestampAdjuster arg1, ExtractorOutput arg2, TrackIdGenerator arg3);

    void seek();
}

