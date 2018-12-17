package com.coremedia.iso.boxes.sampleentry;

import com.coremedia.iso.BoxParser;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.DataSource;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.Arrays;

public class TextSampleEntry extends AbstractSampleEntry {
    public class BoxRecord {
        int bottom;
        int left;
        int right;
        int top;

        public BoxRecord() {
            super();
        }

        public BoxRecord(int arg1, int arg2, int arg3, int arg4) {
            super();
            this.top = arg1;
            this.left = arg2;
            this.bottom = arg3;
            this.right = arg4;
        }

        public boolean equals(Object arg5) {
            if(this == (((BoxRecord)arg5))) {
                return 1;
            }

            if(arg5 != null) {
                if(this.getClass() != arg5.getClass()) {
                }
                else if(this.bottom != ((BoxRecord)arg5).bottom) {
                    return 0;
                }
                else if(this.left != ((BoxRecord)arg5).left) {
                    return 0;
                }
                else if(this.right != ((BoxRecord)arg5).right) {
                    return 0;
                }
                else if(this.top != ((BoxRecord)arg5).top) {
                    return 0;
                }
                else {
                    return 1;
                }
            }

            return 0;
        }

        public void getContent(ByteBuffer arg2) {
            IsoTypeWriter.writeUInt16(arg2, this.top);
            IsoTypeWriter.writeUInt16(arg2, this.left);
            IsoTypeWriter.writeUInt16(arg2, this.bottom);
            IsoTypeWriter.writeUInt16(arg2, this.right);
        }

        public int getSize() {
            return 8;
        }

        public int hashCode() {
            return ((this.top * 31 + this.left) * 31 + this.bottom) * 31 + this.right;
        }

        public void parse(ByteBuffer arg2) {
            this.top = IsoTypeReader.readUInt16(arg2);
            this.left = IsoTypeReader.readUInt16(arg2);
            this.bottom = IsoTypeReader.readUInt16(arg2);
            this.right = IsoTypeReader.readUInt16(arg2);
        }
    }

    public class StyleRecord {
        int endChar;
        int faceStyleFlags;
        int fontId;
        int fontSize;
        int startChar;
        int[] textColor;

        public StyleRecord() {
            super();
            this.textColor = new int[]{255, 255, 255, 255};
        }

        public StyleRecord(int arg2, int arg3, int arg4, int arg5, int arg6, int[] arg7) {
            super();
            this.textColor = new int[]{255, 255, 255, 255};
            this.startChar = arg2;
            this.endChar = arg3;
            this.fontId = arg4;
            this.faceStyleFlags = arg5;
            this.fontSize = arg6;
            this.textColor = arg7;
        }

        public boolean equals(Object arg5) {
            if(this == (((StyleRecord)arg5))) {
                return 1;
            }

            if(arg5 != null) {
                if(this.getClass() != arg5.getClass()) {
                }
                else if(this.endChar != ((StyleRecord)arg5).endChar) {
                    return 0;
                }
                else if(this.faceStyleFlags != ((StyleRecord)arg5).faceStyleFlags) {
                    return 0;
                }
                else if(this.fontId != ((StyleRecord)arg5).fontId) {
                    return 0;
                }
                else if(this.fontSize != ((StyleRecord)arg5).fontSize) {
                    return 0;
                }
                else if(this.startChar != ((StyleRecord)arg5).startChar) {
                    return 0;
                }
                else if(!Arrays.equals(this.textColor, ((StyleRecord)arg5).textColor)) {
                    return 0;
                }
                else {
                    return 1;
                }
            }

            return 0;
        }

        public void getContent(ByteBuffer arg3) {
            IsoTypeWriter.writeUInt16(arg3, this.startChar);
            IsoTypeWriter.writeUInt16(arg3, this.endChar);
            IsoTypeWriter.writeUInt16(arg3, this.fontId);
            IsoTypeWriter.writeUInt8(arg3, this.faceStyleFlags);
            IsoTypeWriter.writeUInt8(arg3, this.fontSize);
            IsoTypeWriter.writeUInt8(arg3, this.textColor[0]);
            IsoTypeWriter.writeUInt8(arg3, this.textColor[1]);
            IsoTypeWriter.writeUInt8(arg3, this.textColor[2]);
            IsoTypeWriter.writeUInt8(arg3, this.textColor[3]);
        }

        public int getSize() {
            return 12;
        }

        public int hashCode() {
            int v0 = ((((this.startChar * 31 + this.endChar) * 31 + this.fontId) * 31 + this.faceStyleFlags) * 31 + this.fontSize) * 31;
            int v1 = this.textColor != null ? Arrays.hashCode(this.textColor) : 0;
            return v0 + v1;
        }

        public void parse(ByteBuffer arg4) {
            this.startChar = IsoTypeReader.readUInt16(arg4);
            this.endChar = IsoTypeReader.readUInt16(arg4);
            this.fontId = IsoTypeReader.readUInt16(arg4);
            this.faceStyleFlags = IsoTypeReader.readUInt8(arg4);
            this.fontSize = IsoTypeReader.readUInt8(arg4);
            this.textColor = new int[4];
            this.textColor[0] = IsoTypeReader.readUInt8(arg4);
            this.textColor[1] = IsoTypeReader.readUInt8(arg4);
            this.textColor[2] = IsoTypeReader.readUInt8(arg4);
            this.textColor[3] = IsoTypeReader.readUInt8(arg4);
        }
    }

    public static final String TYPE1 = "tx3g";
    public static final String TYPE_ENCRYPTED = "enct";
    private int[] backgroundColorRgba;
    private BoxRecord boxRecord;
    private long displayFlags;
    private int horizontalJustification;
    private StyleRecord styleRecord;
    private int verticalJustification;

    public TextSampleEntry() {
        super("tx3g");
        this.backgroundColorRgba = new int[4];
        this.boxRecord = new BoxRecord();
        this.styleRecord = new StyleRecord();
    }

    public TextSampleEntry(String arg1) {
        super(arg1);
        this.backgroundColorRgba = new int[4];
        this.boxRecord = new BoxRecord();
        this.styleRecord = new StyleRecord();
    }

    public int[] getBackgroundColorRgba() {
        return this.backgroundColorRgba;
    }

    public void getBox(WritableByteChannel arg4) {
        arg4.write(this.getHeader());
        ByteBuffer v0 = ByteBuffer.allocate(38);
        v0.position(6);
        IsoTypeWriter.writeUInt16(v0, this.dataReferenceIndex);
        IsoTypeWriter.writeUInt32(v0, this.displayFlags);
        IsoTypeWriter.writeUInt8(v0, this.horizontalJustification);
        IsoTypeWriter.writeUInt8(v0, this.verticalJustification);
        IsoTypeWriter.writeUInt8(v0, this.backgroundColorRgba[0]);
        IsoTypeWriter.writeUInt8(v0, this.backgroundColorRgba[1]);
        IsoTypeWriter.writeUInt8(v0, this.backgroundColorRgba[2]);
        IsoTypeWriter.writeUInt8(v0, this.backgroundColorRgba[3]);
        this.boxRecord.getContent(v0);
        this.styleRecord.getContent(v0);
        arg4.write(v0.rewind());
        this.writeContainer(arg4);
    }

    public BoxRecord getBoxRecord() {
        return this.boxRecord;
    }

    public int getHorizontalJustification() {
        return this.horizontalJustification;
    }

    public long getSize() {
        long v0 = this.getContainerSize() + 38;
        int v2 = (this.largeBox) || v0 >= 4294967296L ? 16 : 8;
        return v0 + (((long)v2));
    }

    public StyleRecord getStyleRecord() {
        return this.styleRecord;
    }

    public int getVerticalJustification() {
        return this.verticalJustification;
    }

    public boolean isContinuousKaraoke() {
        if((this.displayFlags & 2048) == 2048) {
            return 1;
        }

        return 0;
    }

    public boolean isFillTextRegion() {
        if((this.displayFlags & 262144) == 262144) {
            return 1;
        }

        return 0;
    }

    public boolean isScrollDirection() {
        if((this.displayFlags & 384) == 384) {
            return 1;
        }

        return 0;
    }

    public boolean isScrollIn() {
        if((this.displayFlags & 32) == 32) {
            return 1;
        }

        return 0;
    }

    public boolean isScrollOut() {
        if((this.displayFlags & 64) == 64) {
            return 1;
        }

        return 0;
    }

    public boolean isWriteTextVertically() {
        if((this.displayFlags & 131072) == 131072) {
            return 1;
        }

        return 0;
    }

    public void parse(DataSource arg4, ByteBuffer arg5, long arg6, BoxParser arg8) {
        arg5 = ByteBuffer.allocate(38);
        arg4.read(arg5);
        arg5.position(6);
        this.dataReferenceIndex = IsoTypeReader.readUInt16(arg5);
        this.displayFlags = IsoTypeReader.readUInt32(arg5);
        this.horizontalJustification = IsoTypeReader.readUInt8(arg5);
        this.verticalJustification = IsoTypeReader.readUInt8(arg5);
        this.backgroundColorRgba = new int[4];
        this.backgroundColorRgba[0] = IsoTypeReader.readUInt8(arg5);
        this.backgroundColorRgba[1] = IsoTypeReader.readUInt8(arg5);
        this.backgroundColorRgba[2] = IsoTypeReader.readUInt8(arg5);
        this.backgroundColorRgba[3] = IsoTypeReader.readUInt8(arg5);
        this.boxRecord = new BoxRecord();
        this.boxRecord.parse(arg5);
        this.styleRecord = new StyleRecord();
        this.styleRecord.parse(arg5);
        this.initContainer(arg4, arg6 - 38, arg8);
    }

    public void setBackgroundColorRgba(int[] arg1) {
        this.backgroundColorRgba = arg1;
    }

    public void setBoxRecord(BoxRecord arg1) {
        this.boxRecord = arg1;
    }

    public void setContinuousKaraoke(boolean arg5) {
        long v0 = arg5 ? this.displayFlags | 2048 : this.displayFlags & -2049;
        this.displayFlags = v0;
    }

    public void setFillTextRegion(boolean arg5) {
        long v0 = arg5 ? this.displayFlags | 262144 : this.displayFlags & -262145;
        this.displayFlags = v0;
    }

    public void setHorizontalJustification(int arg1) {
        this.horizontalJustification = arg1;
    }

    public void setScrollDirection(boolean arg5) {
        long v0 = arg5 ? this.displayFlags | 384 : this.displayFlags & -385;
        this.displayFlags = v0;
    }

    public void setScrollIn(boolean arg5) {
        long v0 = arg5 ? this.displayFlags | 32 : this.displayFlags & -33;
        this.displayFlags = v0;
    }

    public void setScrollOut(boolean arg5) {
        long v0 = arg5 ? this.displayFlags | 64 : this.displayFlags & -65;
        this.displayFlags = v0;
    }

    public void setStyleRecord(StyleRecord arg1) {
        this.styleRecord = arg1;
    }

    public void setType(String arg1) {
        this.type = arg1;
    }

    public void setVerticalJustification(int arg1) {
        this.verticalJustification = arg1;
    }

    public void setWriteTextVertically(boolean arg5) {
        long v0 = arg5 ? this.displayFlags | 131072 : this.displayFlags & -131073;
        this.displayFlags = v0;
    }

    public String toString() {
        return "TextSampleEntry";
    }
}

