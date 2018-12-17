package com.google.android.exoplayer2.extractor.flv;

import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Collections;

final class AudioTagPayloadReader extends TagPayloadReader {
    private static final int AAC_PACKET_TYPE_AAC_RAW = 1;
    private static final int AAC_PACKET_TYPE_SEQUENCE_HEADER = 0;
    private static final int AUDIO_FORMAT_AAC = 10;
    private static final int AUDIO_FORMAT_ALAW = 7;
    private static final int AUDIO_FORMAT_MP3 = 2;
    private static final int AUDIO_FORMAT_ULAW = 8;
    private static final int[] AUDIO_SAMPLING_RATE_TABLE;
    private int audioFormat;
    private boolean hasOutputFormat;
    private boolean hasParsedAudioDataHeader;

    static {
        AudioTagPayloadReader.AUDIO_SAMPLING_RATE_TABLE = new int[]{5512, 11025, 22050, 44100};
    }

    public AudioTagPayloadReader(TrackOutput arg1) {
        super(arg1);
    }

    protected boolean parseHeader(ParsableByteArray arg19) {
        Format v1_1;
        AudioTagPayloadReader v0 = this;
        if(!v0.hasParsedAudioDataHeader) {
            int v1 = arg19.readUnsignedByte();
            v0.audioFormat = v1 >> 4 & 15;
            int v4 = 3;
            int v5 = 2;
            if(v0.audioFormat == v5) {
                v1_1 = Format.createAudioSampleFormat(null, "audio/mpeg", null, -1, -1, 1, AudioTagPayloadReader.AUDIO_SAMPLING_RATE_TABLE[v1 >> v5 & v4], null, null, 0, null);
                goto label_27;
            }
            else {
                int v6 = 7;
                if(v0.audioFormat != v6) {
                    if(v0.audioFormat == 8) {
                    }
                    else if(v0.audioFormat == 10) {
                        goto label_76;
                    }
                    else {
                        StringBuilder v2 = new StringBuilder();
                        v2.append("Audio format not supported: ");
                        v2.append(v0.audioFormat);
                        throw new UnsupportedFormatException(v2.toString());
                    }
                }

                String v3 = v0.audioFormat == v6 ? "audio/g711-alaw" : "audio/g711-mlaw";
                String v7 = v3;
                int v13 = (v1 & 1) == 1 ? 2 : 3;
                v1_1 = Format.createAudioSampleFormat(null, v7, null, -1, -1, 1, 8000, v13, null, null, 0, null);
            label_27:
                v0.output.format(v1_1);
                v0.hasOutputFormat = true;
            }

        label_76:
            v0.hasParsedAudioDataHeader = true;
        }
        else {
            arg19.skipBytes(1);
        }

        return 1;
    }

    protected void parsePayload(ParsableByteArray arg16, long arg17) {
        AudioTagPayloadReader v0 = this;
        ParsableByteArray v1 = arg16;
        if(v0.audioFormat == 2) {
            int v8 = arg16.bytesLeft();
            v0.output.sampleData(v1, v8);
            v0.output.sampleMetadata(arg17, 1, v8, 0, null);
        }
        else {
            int v2 = arg16.readUnsignedByte();
            if(v2 == 0 && !v0.hasOutputFormat) {
                byte[] v2_1 = new byte[arg16.bytesLeft()];
                v1.readBytes(v2_1, 0, v2_1.length);
                Pair v1_1 = CodecSpecificDataUtil.parseAacAudioSpecificConfig(v2_1);
                v0.output.format(Format.createAudioSampleFormat(null, "audio/mp4a-latm", null, -1, -1, v1_1.second.intValue(), v1_1.first.intValue(), Collections.singletonList(v2_1), null, 0, null));
                v0.hasOutputFormat = true;
                return;
            }

            if(v0.audioFormat == 10 && v2 != 1) {
                return;
            }

            int v10 = arg16.bytesLeft();
            v0.output.sampleData(v1, v10);
            v0.output.sampleMetadata(arg17, 1, v10, 0, null);
        }
    }

    public void seek() {
    }
}

