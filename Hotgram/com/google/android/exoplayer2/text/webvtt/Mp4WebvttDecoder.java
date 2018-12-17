package com.google.android.exoplayer2.text.webvtt;

import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Mp4WebvttDecoder extends SimpleSubtitleDecoder {
    private static final int BOX_HEADER_SIZE = 8;
    private static final int TYPE_payl;
    private static final int TYPE_sttg;
    private static final int TYPE_vttc;
    private final Builder builder;
    private final ParsableByteArray sampleData;

    static {
        Mp4WebvttDecoder.TYPE_payl = Util.getIntegerCodeForString("payl");
        Mp4WebvttDecoder.TYPE_sttg = Util.getIntegerCodeForString("sttg");
        Mp4WebvttDecoder.TYPE_vttc = Util.getIntegerCodeForString("vttc");
    }

    public Mp4WebvttDecoder() {
        super("Mp4WebvttDecoder");
        this.sampleData = new ParsableByteArray();
        this.builder = new Builder();
    }

    protected Subtitle decode(byte[] arg1, int arg2, boolean arg3) {
        return this.decode(arg1, arg2, arg3);
    }

    protected Mp4WebvttSubtitle decode(byte[] arg2, int arg3, boolean arg4) {
        this.sampleData.reset(arg2, arg3);
        ArrayList v2 = new ArrayList();
        while(true) {
            if(this.sampleData.bytesLeft() <= 0) {
                goto label_31;
            }

            if(this.sampleData.bytesLeft() < 8) {
                break;
            }

            arg3 = this.sampleData.readInt();
            if(this.sampleData.readInt() == Mp4WebvttDecoder.TYPE_vttc) {
                ((List)v2).add(Mp4WebvttDecoder.parseVttCueBox(this.sampleData, this.builder, arg3 - 8));
                continue;
            }

            this.sampleData.skipBytes(arg3 - 8);
        }

        throw new SubtitleDecoderException("Incomplete Mp4Webvtt Top Level box header found.");
    label_31:
        return new Mp4WebvttSubtitle(((List)v2));
    }

    private static Cue parseVttCueBox(ParsableByteArray arg4, Builder arg5, int arg6) {
        arg5.reset();
        while(true) {
            if(arg6 <= 0) {
                goto label_28;
            }

            int v0 = 8;
            if(arg6 < v0) {
                break;
            }

            int v1 = arg4.readInt();
            int v2 = arg4.readInt();
            v1 -= v0;
            String v0_1 = Util.fromUtf8Bytes(arg4.data, arg4.getPosition(), v1);
            arg4.skipBytes(v1);
            arg6 = arg6 - 8 - v1;
            if(v2 == Mp4WebvttDecoder.TYPE_sttg) {
                WebvttCueParser.parseCueSettingsList(v0_1, arg5);
                continue;
            }

            if(v2 != Mp4WebvttDecoder.TYPE_payl) {
                continue;
            }

            WebvttCueParser.parseCueText(null, v0_1.trim(), arg5, Collections.emptyList());
        }

        throw new SubtitleDecoderException("Incomplete vtt cue box header found.");
    label_28:
        return arg5.build();
    }
}

