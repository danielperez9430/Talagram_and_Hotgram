package org.telegram.messenger.audioinfo.mp3;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.zip.InflaterInputStream;
import org.telegram.messenger.audioinfo.util.RangeInputStream;

public class ID3v2TagBody {
    private final ID3v2DataInput data;
    private final RangeInputStream input;
    private final ID3v2TagHeader tagHeader;

    ID3v2TagBody(InputStream arg8, long arg9, int arg11, ID3v2TagHeader arg12) {
        super();
        this.input = new RangeInputStream(arg8, arg9, ((long)arg11));
        this.data = new ID3v2DataInput(this.input);
        this.tagHeader = arg12;
    }

    public ID3v2FrameBody frameBody(ID3v2FrameHeader arg11) {
        InflaterInputStream v4_1;
        int v7_1;
        int v0 = arg11.getBodySize();
        RangeInputStream v1 = this.input;
        if(arg11.isUnsynchronization()) {
            byte[] v0_1 = this.data.readFully(arg11.getBodySize());
            int v1_1 = -1;
            int v2 = v0_1.length;
            int v4 = 0;
            int v5 = 0;
            int v6 = 0;
            while(v4 < v2) {
                byte v7 = v0_1[v4];
                if(v5 == 0 || v7 != 0) {
                    v0_1[v6] = v7;
                    ++v6;
                }

                v5 = v7 == v1_1 ? 1 : 0;
                ++v4;
            }

            ByteArrayInputStream v1_2 = new ByteArrayInputStream(v0_1, 0, v6);
            v0 = v6;
        }

        if(!arg11.isEncryption()) {
            if(arg11.isCompression()) {
                v7_1 = arg11.getDataLengthIndicator();
                v4_1 = new InflaterInputStream(((InputStream)v1));
            }
            else {
                v7_1 = v0;
                RangeInputStream v4_2 = v1;
            }

            return new ID3v2FrameBody(((InputStream)v4_1), ((long)arg11.getHeaderSize()), v7_1, this.tagHeader, arg11);
        }

        throw new ID3v2Exception("Frame encryption is not supported");
    }

    public ID3v2DataInput getData() {
        return this.data;
    }

    public long getPosition() {
        return this.input.getPosition();
    }

    public long getRemainingLength() {
        return this.input.getRemainingLength();
    }

    public ID3v2TagHeader getTagHeader() {
        return this.tagHeader;
    }

    public String toString() {
        return "id3v2tag[pos=" + this.getPosition() + ", " + this.getRemainingLength() + " left]";
    }
}

