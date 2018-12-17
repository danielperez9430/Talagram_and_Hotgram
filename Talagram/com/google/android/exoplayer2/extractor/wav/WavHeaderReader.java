package com.google.android.exoplayer2.extractor.wav;

import android.util.Log;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

final class WavHeaderReader {
    final class ChunkHeader {
        public static final int SIZE_IN_BYTES = 8;
        public final int id;
        public final long size;

        private ChunkHeader(int arg1, long arg2) {
            super();
            this.id = arg1;
            this.size = arg2;
        }

        public static ChunkHeader peek(ExtractorInput arg3, ParsableByteArray arg4) {
            arg3.peekFully(arg4.data, 0, 8);
            arg4.setPosition(0);
            return new ChunkHeader(arg4.readInt(), arg4.readLittleEndianUnsignedInt());
        }
    }

    private static final String TAG = "WavHeaderReader";
    private static final int TYPE_A_LAW = 6;
    private static final int TYPE_FLOAT = 3;
    private static final int TYPE_MU_LAW = 7;
    private static final int TYPE_PCM = 1;
    private static final int TYPE_WAVE_FORMAT_EXTENSIBLE = 65534;

    private WavHeaderReader() {
        super();
    }

    public static WavHeader peek(ExtractorInput arg15) {
        // Method was not decompiled
    }

    public static void skipToData(ExtractorInput arg8, WavHeader arg9) {
        ChunkHeader v2;
        Assertions.checkNotNull(arg8);
        Assertions.checkNotNull(arg9);
        arg8.resetPeekPosition();
        int v1 = 8;
        ParsableByteArray v0 = new ParsableByteArray(v1);
        while(true) {
            v2 = ChunkHeader.peek(arg8, v0);
            if(v2.id == Util.getIntegerCodeForString("data")) {
                goto label_43;
            }

            Log.w("WavHeaderReader", "Ignoring unknown WAV chunk: " + v2.id);
            long v5 = v2.size + 8;
            if(v2.id == Util.getIntegerCodeForString("RIFF")) {
                v5 = 12;
            }

            if(v5 > 2147483647) {
                break;
            }

            arg8.skipFully(((int)v5));
        }

        StringBuilder v9 = new StringBuilder();
        v9.append("Chunk is too large (~2GB+) to skip; id: ");
        v9.append(v2.id);
        throw new ParserException(v9.toString());
    label_43:
        arg8.skipFully(v1);
        arg9.setDataBounds(arg8.getPosition(), v2.size);
    }
}

