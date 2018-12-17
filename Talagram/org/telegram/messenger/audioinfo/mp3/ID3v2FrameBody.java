package org.telegram.messenger.audioinfo.mp3;

import java.io.InputStream;
import org.telegram.messenger.audioinfo.util.RangeInputStream;

public class ID3v2FrameBody {
    final class org.telegram.messenger.audioinfo.mp3.ID3v2FrameBody$1 extends ThreadLocal {
        org.telegram.messenger.audioinfo.mp3.ID3v2FrameBody$1() {
            super();
        }

        protected Object initialValue() {
            return this.initialValue();
        }

        protected Buffer initialValue() {
            return new Buffer(4096);
        }
    }

    final class Buffer {
        byte[] bytes;

        Buffer(int arg1) {
            super();
            this.bytes = new byte[arg1];
        }

        byte[] bytes(int arg2) {
            if(arg2 > this.bytes.length) {
                int v0 = this.bytes.length;
                while(true) {
                    v0 *= 2;
                    if(arg2 <= v0) {
                        break;
                    }
                }

                this.bytes = new byte[v0];
            }

            return this.bytes;
        }
    }

    private final ID3v2DataInput data;
    private final ID3v2FrameHeader frameHeader;
    private final RangeInputStream input;
    private final ID3v2TagHeader tagHeader;
    static final ThreadLocal textBuffer;

    static {
        ID3v2FrameBody.textBuffer = new org.telegram.messenger.audioinfo.mp3.ID3v2FrameBody$1();
    }

    ID3v2FrameBody(InputStream arg8, long arg9, int arg11, ID3v2TagHeader arg12, ID3v2FrameHeader arg13) {
        super();
        this.input = new RangeInputStream(arg8, arg9, ((long)arg11));
        this.data = new ID3v2DataInput(this.input);
        this.tagHeader = arg12;
        this.frameHeader = arg13;
    }

    private String extractString(byte[] arg6, int arg7, int arg8, ID3v2Encoding arg9, boolean arg10) {
        String v10_1;
        if(arg10) {
            int v10 = 0;
            int v2 = 0;
            while(v10 < arg8) {
                int v3 = arg7 + v10;
                if(arg6[v3] == 0) {
                    if(arg9 == ID3v2Encoding.UTF_16 && v2 == 0 && v3 % 2 != 0) {
                        goto label_21;
                    }

                    ++v2;
                    if(v2 != arg9.getZeroBytes()) {
                        goto label_22;
                    }

                    arg8 = v10 + 1 - arg9.getZeroBytes();
                    break;
                }
                else {
                label_21:
                    v2 = 0;
                }

            label_22:
                ++v10;
            }
        }

        try {
            v10_1 = new String(arg6, arg7, arg8, arg9.getCharset().name());
            if(v10_1.length() > 0 && v10_1.charAt(0) == 65279) {
                v10_1 = v10_1.substring(1);
            }
        }
        catch(Exception ) {
            return "";
        }

        return v10_1;
    }

    public ID3v2DataInput getData() {
        return this.data;
    }

    public ID3v2FrameHeader getFrameHeader() {
        return this.frameHeader;
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

    public ID3v2Encoding readEncoding() {
        int v0 = this.data.readByte();
        switch(v0) {
            case 0: {
                goto label_18;
            }
            case 1: {
                goto label_16;
            }
            case 2: {
                goto label_14;
            }
            case 3: {
                goto label_12;
            }
        }

        StringBuilder v2 = new StringBuilder();
        v2.append("Invalid encoding: ");
        v2.append(v0);
        throw new ID3v2Exception(v2.toString());
    label_18:
        return ID3v2Encoding.ISO_8859_1;
    label_12:
        return ID3v2Encoding.UTF_8;
    label_14:
        return ID3v2Encoding.UTF_16BE;
    label_16:
        return ID3v2Encoding.UTF_16;
    }

    public String readFixedLengthString(int arg8, ID3v2Encoding arg9) {
        if((((long)arg8)) <= this.getRemainingLength()) {
            byte[] v2 = ID3v2FrameBody.textBuffer.get().bytes(arg8);
            this.data.readFully(v2, 0, arg8);
            return this.extractString(v2, 0, arg8, arg9, true);
        }

        StringBuilder v0 = new StringBuilder();
        v0.append("Could not read fixed-length string of length: ");
        v0.append(arg8);
        throw new ID3v2Exception(v0.toString());
    }

    public String readZeroTerminatedString(int arg8, ID3v2Encoding arg9) {
        arg8 = Math.min(arg8, ((int)this.getRemainingLength()));
        byte[] v2 = ID3v2FrameBody.textBuffer.get().bytes(arg8);
        int v1 = 0;
        int v3 = 0;
        while(v1 < arg8) {
            byte v4 = this.data.readByte();
            v2[v1] = v4;
            if(v4 == 0) {
                if(arg9 == ID3v2Encoding.UTF_16 && v3 == 0 && v1 % 2 != 0) {
                    goto label_31;
                }

                ++v3;
                if(v3 != arg9.getZeroBytes()) {
                    goto label_32;
                }

                return this.extractString(v2, 0, v1 + 1 - arg9.getZeroBytes(), arg9, false);
            }
            else {
            label_31:
                v3 = 0;
            }

        label_32:
            ++v1;
        }

        throw new ID3v2Exception("Could not read zero-termiated string");
    }

    public String toString() {
        return "id3v2frame[pos=" + this.getPosition() + ", " + this.getRemainingLength() + " left]";
    }
}

