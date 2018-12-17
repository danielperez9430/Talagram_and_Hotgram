package com.google.android.exoplayer2.text.dvb;

import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.List;

public final class DvbDecoder extends SimpleSubtitleDecoder {
    private final DvbParser parser;

    public DvbDecoder(List arg3) {
        super("DvbDecoder");
        ParsableByteArray v0 = new ParsableByteArray(arg3.get(0));
        this.parser = new DvbParser(v0.readUnsignedShort(), v0.readUnsignedShort());
    }

    protected Subtitle decode(byte[] arg1, int arg2, boolean arg3) {
        return this.decode(arg1, arg2, arg3);
    }

    protected DvbSubtitle decode(byte[] arg2, int arg3, boolean arg4) {
        if(arg4) {
            this.parser.reset();
        }

        return new DvbSubtitle(this.parser.decode(arg2, arg3));
    }
}

