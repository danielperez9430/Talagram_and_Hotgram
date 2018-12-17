package com.google.android.exoplayer2.extractor.wav;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.TrackOutput;

public final class WavExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = null;
    private static final int MAX_INPUT_SIZE = 32768;
    private int bytesPerFrame;
    private ExtractorOutput extractorOutput;
    private int pendingBytes;
    private TrackOutput trackOutput;
    private WavHeader wavHeader;

    static {
        WavExtractor.FACTORY = -$$Lambda$WavExtractor$5r6M_S0QCNNj_Xavzq9WwuFHep0.INSTANCE;
    }

    public WavExtractor() {
        super();
    }

    public void init(ExtractorOutput arg3) {
        this.extractorOutput = arg3;
        this.trackOutput = arg3.track(0, 1);
        this.wavHeader = null;
        arg3.endTracks();
    }

    static Extractor[] lambda$static$0() {
        return new Extractor[]{new WavExtractor()};
    }

    public int read(ExtractorInput arg13, PositionHolder arg14) {
        if(this.wavHeader == null) {
            this.wavHeader = WavHeaderReader.peek(arg13);
            if(this.wavHeader != null) {
                this.trackOutput.format(Format.createAudioSampleFormat(null, "audio/raw", null, this.wavHeader.getBitrate(), 32768, this.wavHeader.getNumChannels(), this.wavHeader.getSampleRateHz(), this.wavHeader.getEncoding(), null, null, 0, null));
                this.bytesPerFrame = this.wavHeader.getBytesPerFrame();
            }
            else {
                throw new ParserException("Unsupported or unrecognized wav header.");
            }
        }

        if(!this.wavHeader.hasDataBounds()) {
            WavHeaderReader.skipToData(arg13, this.wavHeader);
            this.extractorOutput.seekMap(this.wavHeader);
        }

        int v14 = this.trackOutput.sampleData(arg13, 32768 - this.pendingBytes, true);
        int v0 = -1;
        if(v14 != v0) {
            this.pendingBytes += v14;
        }

        int v1 = this.pendingBytes / this.bytesPerFrame;
        if(v1 > 0) {
            long v6 = this.wavHeader.getTimeUs(arg13.getPosition() - (((long)this.pendingBytes)));
            int v9 = v1 * this.bytesPerFrame;
            this.pendingBytes -= v9;
            this.trackOutput.sampleMetadata(v6, 1, v9, this.pendingBytes, null);
        }

        if(v14 == v0) {
        }
        else {
            v0 = 0;
        }

        return v0;
    }

    public void release() {
    }

    public void seek(long arg1, long arg3) {
        this.pendingBytes = 0;
    }

    public boolean sniff(ExtractorInput arg1) {
        boolean v1 = WavHeaderReader.peek(arg1) != null ? true : false;
        return v1;
    }
}

