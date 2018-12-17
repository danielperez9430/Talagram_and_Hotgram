package com.google.android.exoplayer2.source.hls;

import android.util.Pair;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.metadata.Metadata$Entry;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.Id3Decoder;
import com.google.android.exoplayer2.metadata.id3.PrivFrame;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$HlsUrl;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

final class HlsMediaChunk extends MediaChunk {
    private static final String PRIV_TIMESTAMP_FRAME_OWNER = "com.apple.streaming.transportStreamTimestamp";
    public final int discontinuitySequenceNumber;
    private final DrmInitData drmInitData;
    private Extractor extractor;
    private final HlsExtractorFactory extractorFactory;
    private final boolean hasGapTag;
    public final HlsUrl hlsUrl;
    private ParsableByteArray id3Data;
    private Id3Decoder id3Decoder;
    private boolean id3TimestampPeeked;
    private final DataSource initDataSource;
    private final DataSpec initDataSpec;
    private boolean initLoadCompleted;
    private int initSegmentBytesLoaded;
    private final boolean isEncrypted;
    private final boolean isMasterTimestampSource;
    private boolean isPackedAudioExtractor;
    private volatile boolean loadCanceled;
    private boolean loadCompleted;
    private final List muxedCaptionFormats;
    private int nextLoadPosition;
    private HlsSampleStreamWrapper output;
    private final Extractor previousExtractor;
    private final boolean shouldSpliceIn;
    private final TimestampAdjuster timestampAdjuster;
    public final int uid;
    private static final AtomicInteger uidSource;

    static {
        HlsMediaChunk.uidSource = new AtomicInteger();
    }

    public HlsMediaChunk(HlsExtractorFactory arg18, DataSource arg19, DataSpec arg20, DataSpec arg21, HlsUrl arg22, List arg23, int arg24, Object arg25, long arg26, long arg28, long arg30, int arg32, boolean arg33, boolean arg34, TimestampAdjuster arg35, HlsMediaChunk arg36, DrmInitData arg37, byte[] arg38, byte[] arg39) {
        HlsMediaChunk v12 = this;
        HlsUrl v14 = arg22;
        int v15 = arg32;
        HlsMediaChunk v13 = arg36;
        super(HlsMediaChunk.buildDataSource(arg19, arg38, arg39), arg20, v14.format, arg24, arg25, arg26, arg28, arg30);
        v12.discontinuitySequenceNumber = v15;
        v12.initDataSpec = arg21;
        v12.hlsUrl = v14;
        v12.isMasterTimestampSource = arg34;
        v12.timestampAdjuster = arg35;
        boolean v0 = true;
        boolean v2 = arg38 != null ? true : false;
        v12.isEncrypted = v2;
        v12.hasGapTag = arg33;
        v12.extractorFactory = arg18;
        v12.muxedCaptionFormats = arg23;
        v12.drmInitData = arg37;
        Extractor v2_1 = null;
        if(v13 != null) {
            v12.id3Decoder = v13.id3Decoder;
            v12.id3Data = v13.id3Data;
            if(v13.hlsUrl != v14) {
            }
            else {
                v0 = false;
            }

            v12.shouldSpliceIn = v0;
            if(v13.discontinuitySequenceNumber != v15) {
                goto label_62;
            }

            if(v12.shouldSpliceIn) {
                goto label_62;
            }

            v2_1 = v13.extractor;
        }
        else {
            v12.shouldSpliceIn = false;
        }

    label_62:
        v12.previousExtractor = v2_1;
        v12.initDataSource = arg19;
        v12.uid = HlsMediaChunk.uidSource.getAndIncrement();
    }

    private static DataSource buildDataSource(DataSource arg1, byte[] arg2, byte[] arg3) {
        if(arg2 != null) {
            return new Aes128DataSource(arg1, arg2, arg3);
        }

        return arg1;
    }

    public void cancelLoad() {
        this.loadCanceled = true;
    }

    public void init(HlsSampleStreamWrapper arg1) {
        this.output = arg1;
    }

    public boolean isLoadCompleted() {
        return this.loadCompleted;
    }

    public void load() {
        this.maybeLoadInitData();
        if(!this.loadCanceled) {
            if(!this.hasGapTag) {
                this.loadMedia();
            }

            this.loadCompleted = true;
        }
    }

    private void loadMedia() {
        DefaultExtractorInput v0_2;
        int v3;
        DataSpec v0;
        int v2 = 0;
        if(this.isEncrypted) {
            v0 = this.dataSpec;
            if(this.nextLoadPosition != 0) {
                v3 = 1;
            }
            else {
                goto label_13;
            }
        }
        else {
            v0 = this.dataSpec.subrange(((long)this.nextLoadPosition));
        label_13:
            v3 = 0;
        }

        if(!this.isMasterTimestampSource) {
            this.timestampAdjuster.waitUntilInitialized();
        }
        else if(this.timestampAdjuster.getFirstSampleTimestampUs() == 9223372036854775807L) {
            this.timestampAdjuster.setFirstSampleTimestampUs(this.startTimeUs);
        }

        try {
            v0_2 = this.prepareExtraction(this.dataSource, v0);
            if((this.isPackedAudioExtractor) && !this.id3TimestampPeeked) {
                long v4 = this.peekId3PrivTimestamp(((ExtractorInput)v0_2));
                this.id3TimestampPeeked = true;
                HlsSampleStreamWrapper v1 = this.output;
                v4 = v4 != -9223372036854775807L ? this.timestampAdjuster.adjustTsTimestamp(v4) : this.startTimeUs;
                v1.setSampleOffsetUs(v4);
            }

            if(v3 != 0) {
                ((ExtractorInput)v0_2).skipFully(this.nextLoadPosition);
            }
        }
        catch(Throwable v0_1) {
            goto label_73;
        }

        while(true) {
            if(v2 == 0) {
                try {
                    if(!this.loadCanceled) {
                        v2 = this.extractor.read(((ExtractorInput)v0_2), null);
                        continue;
                    }

                    goto label_61;
                }
                catch(Throwable v1_1) {
                    try {
                        this.nextLoadPosition = ((int)(((ExtractorInput)v0_2).getPosition() - this.dataSpec.absoluteStreamPosition));
                        throw v1_1;
                    label_61:
                        this.nextLoadPosition = ((int)(((ExtractorInput)v0_2).getPosition() - this.dataSpec.absoluteStreamPosition));
                        break;
                    }
                    catch(Throwable v0_1) {
                        goto label_73;
                    }
                }
            }

            goto label_61;
        }

        Util.closeQuietly(this.dataSource);
        return;
    label_73:
        Util.closeQuietly(this.dataSource);
        throw v0_1;
    }

    private void maybeLoadInitData() {
        int v1;
        DefaultExtractorInput v0_2;
        if(!this.initLoadCompleted) {
            if(this.initDataSpec == null) {
            }
            else {
                DataSpec v0 = this.initDataSpec.subrange(((long)this.initSegmentBytesLoaded));
                try {
                    v0_2 = this.prepareExtraction(this.initDataSource, v0);
                    v1 = 0;
                }
                catch(Throwable v0_1) {
                    goto label_42;
                }

                while(true) {
                    if(v1 == 0) {
                        try {
                            if(!this.loadCanceled) {
                                v1 = this.extractor.read(((ExtractorInput)v0_2), null);
                                continue;
                            }

                            goto label_28;
                        }
                        catch(Throwable v1_1) {
                            try {
                                this.initSegmentBytesLoaded = ((int)(v0_2.getPosition() - this.initDataSpec.absoluteStreamPosition));
                                throw v1_1;
                            label_28:
                                this.initSegmentBytesLoaded = ((int)(v0_2.getPosition() - this.initDataSpec.absoluteStreamPosition));
                                break;
                            }
                            catch(Throwable v0_1) {
                                goto label_42;
                            }
                        }
                    }

                    goto label_28;
                }

                Util.closeQuietly(this.initDataSource);
                this.initLoadCompleted = true;
                return;
            label_42:
                Util.closeQuietly(this.initDataSource);
                throw v0_1;
            }
        }
    }

    private long peekId3PrivTimestamp(ExtractorInput arg10) {
        arg10.resetPeekPosition();
        int v2 = 10;
        long v4 = -9223372036854775807L;
        if(!arg10.peekFully(this.id3Data.data, 0, v2, true)) {
            return v4;
        }

        this.id3Data.reset(v2);
        if(this.id3Data.readUnsignedInt24() != Id3Decoder.ID3_TAG) {
            return v4;
        }

        this.id3Data.skipBytes(3);
        int v0 = this.id3Data.readSynchSafeInt();
        int v6 = v0 + 10;
        if(v6 > this.id3Data.capacity()) {
            byte[] v7 = this.id3Data.data;
            this.id3Data.reset(v6);
            System.arraycopy(v7, 0, this.id3Data.data, 0, v2);
        }

        if(!arg10.peekFully(this.id3Data.data, v2, v0, true)) {
            return v4;
        }

        Metadata v10 = this.id3Decoder.decode(this.id3Data.data, v0);
        if(v10 == null) {
            return v4;
        }

        v0 = v10.length();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Entry v2_1 = v10.get(v1);
            if(((v2_1 instanceof PrivFrame)) && ("com.apple.streaming.transportStreamTimestamp".equals(((PrivFrame)v2_1).owner))) {
                System.arraycopy(((PrivFrame)v2_1).privateData, 0, this.id3Data.data, 0, 8);
                this.id3Data.reset(8);
                return this.id3Data.readLong() & 8589934591L;
            }
        }

        return v4;
    }

    private DefaultExtractorInput prepareExtraction(DataSource arg16, DataSpec arg17) {
        HlsMediaChunk v0 = this;
        DataSpec v1 = arg17;
        long v5 = arg16.open(arg17);
        if(v0.extractor == null) {
            Pair v2 = v0.extractorFactory.createExtractor(v0.previousExtractor, v1.uri, v0.trackFormat, v0.muxedCaptionFormats, v0.drmInitData, v0.timestampAdjuster, arg16.getResponseHeaders());
            v0.extractor = v2.first;
            v0.isPackedAudioExtractor = v2.second.booleanValue();
            boolean v4 = false;
            boolean v2_1 = v0.extractor == v0.previousExtractor ? true : false;
            if((v2_1) && v0.initDataSpec != null) {
                v4 = true;
            }

            v0.initLoadCompleted = v4;
            if((v0.isPackedAudioExtractor) && v0.id3Data == null) {
                v0.id3Decoder = new Id3Decoder();
                v0.id3Data = new ParsableByteArray(10);
            }

            v0.output.init(v0.uid, v0.shouldSpliceIn, v2_1);
            if(v2_1) {
                goto label_51;
            }

            v0.extractor.init(v0.output);
        }

    label_51:
        return new DefaultExtractorInput(arg16, v1.absoluteStreamPosition, v5);
    }
}

