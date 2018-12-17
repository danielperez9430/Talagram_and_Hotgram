package com.google.android.exoplayer2.source.chunk;

import android.util.SparseArray;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.DummyTrackOutput;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput$CryptoData;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;

public final class ChunkExtractorWrapper implements ExtractorOutput {
    final class BindingTrackOutput implements TrackOutput {
        private final int id;
        private final Format manifestFormat;
        public Format sampleFormat;
        private TrackOutput trackOutput;
        private final int type;

        public BindingTrackOutput(int arg1, int arg2, Format arg3) {
            super();
            this.id = arg1;
            this.type = arg2;
            this.manifestFormat = arg3;
        }

        public void bind(TrackOutputProvider arg3) {
            if(arg3 == null) {
                this.trackOutput = new DummyTrackOutput();
                return;
            }

            this.trackOutput = arg3.track(this.id, this.type);
            if(this.sampleFormat != null) {
                this.trackOutput.format(this.sampleFormat);
            }
        }

        public void format(Format arg2) {
            if(this.manifestFormat != null) {
                arg2 = arg2.copyWithManifestFormatInfo(this.manifestFormat);
            }

            this.sampleFormat = arg2;
            this.trackOutput.format(this.sampleFormat);
        }

        public int sampleData(ExtractorInput arg2, int arg3, boolean arg4) {
            return this.trackOutput.sampleData(arg2, arg3, arg4);
        }

        public void sampleData(ParsableByteArray arg2, int arg3) {
            this.trackOutput.sampleData(arg2, arg3);
        }

        public void sampleMetadata(long arg8, int arg10, int arg11, int arg12, CryptoData arg13) {
            this.trackOutput.sampleMetadata(arg8, arg10, arg11, arg12, arg13);
        }
    }

    public interface TrackOutputProvider {
        TrackOutput track(int arg1, int arg2);
    }

    private final SparseArray bindingTrackOutputs;
    public final Extractor extractor;
    private boolean extractorInitialized;
    private final Format primaryTrackManifestFormat;
    private final int primaryTrackType;
    private Format[] sampleFormats;
    private SeekMap seekMap;
    private TrackOutputProvider trackOutputProvider;

    public ChunkExtractorWrapper(Extractor arg1, int arg2, Format arg3) {
        super();
        this.extractor = arg1;
        this.primaryTrackType = arg2;
        this.primaryTrackManifestFormat = arg3;
        this.bindingTrackOutputs = new SparseArray();
    }

    public void endTracks() {
        Format[] v0 = new Format[this.bindingTrackOutputs.size()];
        int v1;
        for(v1 = 0; v1 < this.bindingTrackOutputs.size(); ++v1) {
            v0[v1] = this.bindingTrackOutputs.valueAt(v1).sampleFormat;
        }

        this.sampleFormats = v0;
    }

    public Format[] getSampleFormats() {
        return this.sampleFormats;
    }

    public SeekMap getSeekMap() {
        return this.seekMap;
    }

    public void init(TrackOutputProvider arg7, long arg8) {
        this.trackOutputProvider = arg7;
        long v1 = -9223372036854775807L;
        long v3 = 0;
        if(!this.extractorInitialized) {
            this.extractor.init(((ExtractorOutput)this));
            if(arg8 != v1) {
                this.extractor.seek(v3, arg8);
            }

            this.extractorInitialized = true;
        }
        else {
            Extractor v0 = this.extractor;
            if(arg8 == v1) {
                arg8 = v3;
            }

            v0.seek(v3, arg8);
            int v8;
            for(v8 = 0; v8 < this.bindingTrackOutputs.size(); ++v8) {
                this.bindingTrackOutputs.valueAt(v8).bind(arg7);
            }
        }
    }

    public void seekMap(SeekMap arg1) {
        this.seekMap = arg1;
    }

    public TrackOutput track(int arg3, int arg4) {
        BindingTrackOutput v0_2;
        Object v0 = this.bindingTrackOutputs.get(arg3);
        if(v0 == null) {
            boolean v0_1 = this.sampleFormats == null ? true : false;
            Assertions.checkState(v0_1);
            Format v1 = arg4 == this.primaryTrackType ? this.primaryTrackManifestFormat : null;
            v0_2 = new BindingTrackOutput(arg3, arg4, v1);
            v0_2.bind(this.trackOutputProvider);
            this.bindingTrackOutputs.put(arg3, v0_2);
        }

        return ((TrackOutput)v0_2);
    }
}

