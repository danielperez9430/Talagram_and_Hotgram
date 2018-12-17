package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.List;

final class VorbisReader extends StreamReader {
    final class VorbisSetup {
        public final CommentHeader commentHeader;
        public final int iLogModes;
        public final VorbisIdHeader idHeader;
        public final Mode[] modes;
        public final byte[] setupHeaderData;

        public VorbisSetup(VorbisIdHeader arg1, CommentHeader arg2, byte[] arg3, Mode[] arg4, int arg5) {
            super();
            this.idHeader = arg1;
            this.commentHeader = arg2;
            this.setupHeaderData = arg3;
            this.modes = arg4;
            this.iLogModes = arg5;
        }
    }

    private CommentHeader commentHeader;
    private int previousPacketBlockSize;
    private boolean seenFirstAudioPacket;
    private VorbisIdHeader vorbisIdHeader;
    private VorbisSetup vorbisSetup;

    VorbisReader() {
        super();
    }

    static void appendNumberOfSamples(ParsableByteArray arg6, long arg7) {
        arg6.setLimit(arg6.limit() + 4);
        arg6.data[arg6.limit() - 4] = ((byte)(((int)(arg7 & 255))));
        arg6.data[arg6.limit() - 3] = ((byte)(((int)(arg7 >>> 8 & 255))));
        arg6.data[arg6.limit() - 2] = ((byte)(((int)(arg7 >>> 16 & 255))));
        arg6.data[arg6.limit() - 1] = ((byte)(((int)(arg7 >>> 24 & 255))));
    }

    private static int decodeBlockSize(byte arg2, VorbisSetup arg3) {
        int v2 = !arg3.modes[VorbisReader.readBits(arg2, arg3.iLogModes, 1)].blockFlag ? arg3.idHeader.blockSize0 : arg3.idHeader.blockSize1;
        return v2;
    }

    protected void onSeekEnd(long arg4) {
        // Method was not decompiled
    }

    protected long preparePayload(ParsableByteArray arg6) {
        int v1 = 0;
        if((arg6.data[0] & 1) == 1) {
            return -1;
        }

        int v0 = VorbisReader.decodeBlockSize(arg6.data[0], this.vorbisSetup);
        if(this.seenFirstAudioPacket) {
            v1 = (this.previousPacketBlockSize + v0) / 4;
        }

        long v3 = ((long)v1);
        VorbisReader.appendNumberOfSamples(arg6, v3);
        this.seenFirstAudioPacket = true;
        this.previousPacketBlockSize = v0;
        return v3;
    }

    static int readBits(byte arg0, int arg1, int arg2) {
        return arg0 >> arg2 & 255 >>> 8 - arg1;
    }

    protected boolean readHeaders(ParsableByteArray arg12, long arg13, SetupData arg15) {
        if(this.vorbisSetup != null) {
            return 0;
        }

        this.vorbisSetup = this.readSetupHeaders(arg12);
        if(this.vorbisSetup == null) {
            return 1;
        }

        ArrayList v7 = new ArrayList();
        v7.add(this.vorbisSetup.idHeader.data);
        v7.add(this.vorbisSetup.setupHeaderData);
        arg15.format = Format.createAudioSampleFormat(null, "audio/vorbis", null, this.vorbisSetup.idHeader.bitrateNominal, -1, this.vorbisSetup.idHeader.channels, ((int)this.vorbisSetup.idHeader.sampleRate), ((List)v7), null, 0, null);
        return 1;
    }

    VorbisSetup readSetupHeaders(ParsableByteArray arg8) {
        VorbisSetup v1 = null;
        if(this.vorbisIdHeader == null) {
            this.vorbisIdHeader = VorbisUtil.readVorbisIdentificationHeader(arg8);
            return v1;
        }

        if(this.commentHeader == null) {
            this.commentHeader = VorbisUtil.readVorbisCommentHeader(arg8);
            return v1;
        }

        byte[] v4 = new byte[arg8.limit()];
        System.arraycopy(arg8.data, 0, v4, 0, arg8.limit());
        Mode[] v5 = VorbisUtil.readVorbisModes(arg8, this.vorbisIdHeader.channels);
        return new VorbisSetup(this.vorbisIdHeader, this.commentHeader, v4, v5, VorbisUtil.iLog(v5.length - 1));
    }

    protected void reset(boolean arg1) {
        super.reset(arg1);
        if(arg1) {
            this.vorbisSetup = null;
            this.vorbisIdHeader = null;
            this.commentHeader = null;
        }

        this.previousPacketBlockSize = 0;
        this.seenFirstAudioPacket = false;
    }

    public static boolean verifyBitstreamType(ParsableByteArray arg1) {
        try {
            return VorbisUtil.verifyVorbisHeaderCapturePattern(1, arg1, true);
        }
        catch(ParserException ) {
            return 0;
        }
    }
}

