package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;

public class OggExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = null;
    private static final int MAX_VERIFICATION_BYTES = 8;
    private ExtractorOutput output;
    private StreamReader streamReader;
    private boolean streamReaderInitialized;

    static {
        OggExtractor.FACTORY = -$$Lambda$OggExtractor$Ibu4KG2n586HVQ8R-UQJ8hUhsso.INSTANCE;
    }

    public OggExtractor() {
        super();
    }

    public void init(ExtractorOutput arg1) {
        this.output = arg1;
    }

    static Extractor[] lambda$static$0() {
        return new Extractor[]{new OggExtractor()};
    }

    public int read(ExtractorInput arg5, PositionHolder arg6) {
        if(this.streamReader == null) {
            if(this.sniffInternal(arg5)) {
                arg5.resetPeekPosition();
            }
            else {
                throw new ParserException("Failed to determine bitstream type");
            }
        }

        if(!this.streamReaderInitialized) {
            TrackOutput v0 = this.output.track(0, 1);
            this.output.endTracks();
            this.streamReader.init(this.output, v0);
            this.streamReaderInitialized = true;
        }

        return this.streamReader.read(arg5, arg6);
    }

    public void release() {
    }

    private static ParsableByteArray resetPosition(ParsableByteArray arg1) {
        arg1.setPosition(0);
        return arg1;
    }

    public void seek(long arg2, long arg4) {
        if(this.streamReader != null) {
            this.streamReader.seek(arg2, arg4);
        }
    }

    public boolean sniff(ExtractorInput arg1) {
        try {
            return this.sniffInternal(arg1);
        }
        catch(ParserException ) {
            return 0;
        }
    }

    private boolean sniffInternal(ExtractorInput arg6) {
        FlacReader v6;
        OggPageHeader v0 = new OggPageHeader();
        if(v0.populate(arg6, true)) {
            if((v0.type & 2) != 2) {
            }
            else {
                int v0_1 = Math.min(v0.bodySize, 8);
                ParsableByteArray v2 = new ParsableByteArray(v0_1);
                arg6.peekFully(v2.data, 0, v0_1);
                if(FlacReader.verifyBitstreamType(OggExtractor.resetPosition(v2))) {
                    v6 = new FlacReader();
                }
                else if(VorbisReader.verifyBitstreamType(OggExtractor.resetPosition(v2))) {
                    VorbisReader v6_1 = new VorbisReader();
                }
                else if(OpusReader.verifyBitstreamType(OggExtractor.resetPosition(v2))) {
                    OpusReader v6_2 = new OpusReader();
                }
                else {
                    return 0;
                }

                this.streamReader = ((StreamReader)v6);
                return 1;
            }
        }

        return 0;
    }
}

