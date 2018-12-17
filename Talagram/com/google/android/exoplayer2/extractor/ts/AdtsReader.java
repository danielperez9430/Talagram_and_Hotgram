package com.google.android.exoplayer2.extractor.ts;

import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.DummyTrackOutput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Arrays;
import java.util.Collections;

public final class AdtsReader implements ElementaryStreamReader {
    private static final int CRC_SIZE = 2;
    private static final int HEADER_SIZE = 5;
    private static final int ID3_HEADER_SIZE = 10;
    private static final byte[] ID3_IDENTIFIER = null;
    private static final int ID3_SIZE_OFFSET = 6;
    private static final int MATCH_STATE_FF = 512;
    private static final int MATCH_STATE_I = 768;
    private static final int MATCH_STATE_ID = 1024;
    private static final int MATCH_STATE_START = 256;
    private static final int MATCH_STATE_VALUE_SHIFT = 8;
    private static final int STATE_FINDING_SAMPLE = 0;
    private static final int STATE_READING_ADTS_HEADER = 2;
    private static final int STATE_READING_ID3_HEADER = 1;
    private static final int STATE_READING_SAMPLE = 3;
    private static final String TAG = "AdtsReader";
    private final ParsableBitArray adtsScratch;
    private int bytesRead;
    private TrackOutput currentOutput;
    private long currentSampleDuration;
    private final boolean exposeId3;
    private String formatId;
    private boolean hasCrc;
    private boolean hasOutputFormat;
    private final ParsableByteArray id3HeaderBuffer;
    private TrackOutput id3Output;
    private final String language;
    private int matchState;
    private TrackOutput output;
    private long sampleDurationUs;
    private int sampleSize;
    private int state;
    private long timeUs;

    static {
        AdtsReader.ID3_IDENTIFIER = new byte[]{73, 68, 51};
    }

    public AdtsReader(boolean arg2) {
        this(arg2, null);
    }

    public AdtsReader(boolean arg4, String arg5) {
        super();
        this.adtsScratch = new ParsableBitArray(new byte[7]);
        this.id3HeaderBuffer = new ParsableByteArray(Arrays.copyOf(AdtsReader.ID3_IDENTIFIER, 10));
        this.setFindingSampleState();
        this.exposeId3 = arg4;
        this.language = arg5;
    }

    public void consume(ParsableByteArray arg3) {
        while(arg3.bytesLeft() > 0) {
            switch(this.state) {
                case 0: {
                    goto label_25;
                }
                case 1: {
                    goto label_18;
                }
                case 2: {
                    goto label_7;
                }
                case 3: {
                    goto label_5;
                }
            }

            continue;
        label_18:
            if(!this.continueRead(arg3, this.id3HeaderBuffer.data, 10)) {
                continue;
            }

            this.parseId3Header();
            continue;
        label_5:
            this.readSample(arg3);
            continue;
        label_7:
            int v0 = this.hasCrc ? 7 : 5;
            if(!this.continueRead(arg3, this.adtsScratch.data, v0)) {
                continue;
            }

            this.parseAdtsHeader();
            continue;
        label_25:
            this.findNextSample(arg3);
        }
    }

    private boolean continueRead(ParsableByteArray arg3, byte[] arg4, int arg5) {
        int v0 = Math.min(arg3.bytesLeft(), arg5 - this.bytesRead);
        arg3.readBytes(arg4, this.bytesRead, v0);
        this.bytesRead += v0;
        boolean v3 = this.bytesRead == arg5 ? true : false;
        return v3;
    }

    public void createTracks(ExtractorOutput arg4, TrackIdGenerator arg5) {
        arg5.generateNewId();
        this.formatId = arg5.getFormatId();
        this.output = arg4.track(arg5.getTrackId(), 1);
        if(this.exposeId3) {
            arg5.generateNewId();
            this.id3Output = arg4.track(arg5.getTrackId(), 4);
            this.id3Output.format(Format.createSampleFormat(arg5.getFormatId(), "application/id3", null, -1, null));
        }
        else {
            this.id3Output = new DummyTrackOutput();
        }
    }

    private void findNextSample(ParsableByteArray arg8) {
        byte[] v0 = arg8.data;
        int v1 = arg8.getPosition();
        int v2 = arg8.limit();
        while(v1 < v2) {
            int v3 = v1 + 1;
            int v4 = 255;
            v1 = v0[v1] & v4;
            int v6 = 512;
            if(this.matchState != v6 || v1 < 240 || v1 == v4) {
                v1 |= this.matchState;
                if(v1 == 329) {
                    goto label_45;
                }
                else if(v1 == 511) {
                    goto label_43;
                }
                else if(v1 == 836) {
                    goto label_41;
                }
                else if(v1 != 1075) {
                    v4 = 256;
                    if(this.matchState != v4) {
                        this.matchState = v4;
                        --v3;
                    }
                }
                else {
                    this.setReadingId3HeaderState();
                    goto label_21;
                }

                goto label_47;
            }
            else {
                boolean v0_1 = true;
                if((v1 & 1) == 0) {
                }
                else {
                    v0_1 = false;
                }

                this.hasCrc = v0_1;
                this.setReadingAdtsHeaderState();
            }

        label_21:
            arg8.setPosition(v3);
            return;
        label_41:
            v1 = 1024;
            goto label_46;
        label_43:
            this.matchState = v6;
            goto label_47;
        label_45:
            v1 = 768;
        label_46:
            this.matchState = v1;
        label_47:
            v1 = v3;
        }

        arg8.setPosition(v1);
    }

    public void packetFinished() {
    }

    public void packetStarted(long arg1, boolean arg3) {
        this.timeUs = arg1;
    }

    private void parseAdtsHeader() {
        int v0;
        AdtsReader v6 = this;
        v6.adtsScratch.setPosition(0);
        int v1 = 4;
        int v2 = 2;
        if(!v6.hasOutputFormat) {
            v0 = v6.adtsScratch.readBits(v2) + 1;
            if(v0 != v2) {
                Log.w("AdtsReader", "Detected audio object type: " + v0 + ", but assuming AAC LC.");
                v0 = 2;
            }

            int v4 = v6.adtsScratch.readBits(v1);
            v6.adtsScratch.skipBits(1);
            byte[] v0_1 = CodecSpecificDataUtil.buildAacAudioSpecificConfig(v0, v4, v6.adtsScratch.readBits(3));
            Pair v4_1 = CodecSpecificDataUtil.parseAacAudioSpecificConfig(v0_1);
            Format v0_2 = Format.createAudioSampleFormat(v6.formatId, "audio/mp4a-latm", null, -1, -1, v4_1.second.intValue(), v4_1.first.intValue(), Collections.singletonList(v0_1), null, 0, v6.language);
            v6.sampleDurationUs = 1024000000 / (((long)v0_2.sampleRate));
            v6.output.format(v0_2);
            v6.hasOutputFormat = true;
        }
        else {
            v6.adtsScratch.skipBits(10);
        }

        v6.adtsScratch.skipBits(v1);
        v0 = v6.adtsScratch.readBits(13) - v2 - 5;
        if(v6.hasCrc) {
            v0 += -2;
        }

        this.setReadingSampleState(v6.output, v6.sampleDurationUs, 0, v0);
    }

    private void parseId3Header() {
        this.id3Output.sampleData(this.id3HeaderBuffer, 10);
        this.id3HeaderBuffer.setPosition(6);
        this.setReadingSampleState(this.id3Output, 0, 10, this.id3HeaderBuffer.readSynchSafeInt() + 10);
    }

    private void readSample(ParsableByteArray arg9) {
        int v0 = Math.min(arg9.bytesLeft(), this.sampleSize - this.bytesRead);
        this.currentOutput.sampleData(arg9, v0);
        this.bytesRead += v0;
        if(this.bytesRead == this.sampleSize) {
            this.currentOutput.sampleMetadata(this.timeUs, 1, this.sampleSize, 0, null);
            this.timeUs += this.currentSampleDuration;
            this.setFindingSampleState();
        }
    }

    public void seek() {
        this.setFindingSampleState();
    }

    private void setFindingSampleState() {
        this.state = 0;
        this.bytesRead = 0;
        this.matchState = 256;
    }

    private void setReadingAdtsHeaderState() {
        this.state = 2;
        this.bytesRead = 0;
    }

    private void setReadingId3HeaderState() {
        this.state = 1;
        this.bytesRead = AdtsReader.ID3_IDENTIFIER.length;
        this.sampleSize = 0;
        this.id3HeaderBuffer.setPosition(0);
    }

    private void setReadingSampleState(TrackOutput arg2, long arg3, int arg5, int arg6) {
        this.state = 3;
        this.bytesRead = arg5;
        this.currentOutput = arg2;
        this.currentSampleDuration = arg3;
        this.sampleSize = arg6;
    }
}

