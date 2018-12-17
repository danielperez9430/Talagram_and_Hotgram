package com.coremedia.iso.boxes.sampleentry;

import com.coremedia.iso.BoxParser;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.Utf8;
import com.coremedia.iso.boxes.Container;
import com.googlecode.mp4parser.DataSource;
import com.googlecode.mp4parser.util.CastUtils;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

public final class VisualSampleEntry extends AbstractSampleEntry implements Container {
    public static final String TYPE1 = "mp4v";
    public static final String TYPE2 = "s263";
    public static final String TYPE3 = "avc1";
    public static final String TYPE4 = "avc3";
    public static final String TYPE5 = "drmi";
    public static final String TYPE6 = "hvc1";
    public static final String TYPE7 = "hev1";
    public static final String TYPE_ENCRYPTED = "encv";
    private String compressorname;
    private int depth;
    private int frameCount;
    private int height;
    private double horizresolution;
    private long[] predefined;
    private double vertresolution;
    private int width;

    static {
    }

    public VisualSampleEntry() {
        super("avc1");
        this.horizresolution = 72;
        this.vertresolution = 72;
        this.frameCount = 1;
        this.compressorname = "";
        this.depth = 24;
        this.predefined = new long[3];
    }

    public VisualSampleEntry(String arg3) {
        super(arg3);
        this.horizresolution = 72;
        this.vertresolution = 72;
        this.frameCount = 1;
        this.compressorname = "";
        this.depth = 24;
        this.predefined = new long[3];
    }

    public void getBox(WritableByteChannel arg6) {
        arg6.write(this.getHeader());
        ByteBuffer v0 = ByteBuffer.allocate(78);
        v0.position(6);
        IsoTypeWriter.writeUInt16(v0, this.dataReferenceIndex);
        IsoTypeWriter.writeUInt16(v0, 0);
        IsoTypeWriter.writeUInt16(v0, 0);
        IsoTypeWriter.writeUInt32(v0, this.predefined[0]);
        IsoTypeWriter.writeUInt32(v0, this.predefined[1]);
        IsoTypeWriter.writeUInt32(v0, this.predefined[2]);
        IsoTypeWriter.writeUInt16(v0, this.getWidth());
        IsoTypeWriter.writeUInt16(v0, this.getHeight());
        IsoTypeWriter.writeFixedPoint1616(v0, this.getHorizresolution());
        IsoTypeWriter.writeFixedPoint1616(v0, this.getVertresolution());
        IsoTypeWriter.writeUInt32(v0, 0);
        IsoTypeWriter.writeUInt16(v0, this.getFrameCount());
        IsoTypeWriter.writeUInt8(v0, Utf8.utf8StringLengthInBytes(this.getCompressorname()));
        v0.put(Utf8.convert(this.getCompressorname()));
        int v2 = Utf8.utf8StringLengthInBytes(this.getCompressorname());
        while(v2 < 31) {
            ++v2;
            v0.put(0);
        }

        IsoTypeWriter.writeUInt16(v0, this.getDepth());
        IsoTypeWriter.writeUInt16(v0, 65535);
        arg6.write(v0.rewind());
        this.writeContainer(arg6);
    }

    public String getCompressorname() {
        return this.compressorname;
    }

    public int getDepth() {
        return this.depth;
    }

    public int getFrameCount() {
        return this.frameCount;
    }

    public int getHeight() {
        return this.height;
    }

    public double getHorizresolution() {
        return this.horizresolution;
    }

    public long getSize() {
        long v0 = this.getContainerSize() + 78;
        int v2 = (this.largeBox) || 8 + v0 >= 4294967296L ? 16 : 8;
        return v0 + (((long)v2));
    }

    public double getVertresolution() {
        return this.vertresolution;
    }

    public int getWidth() {
        return this.width;
    }

    public void parse(DataSource arg7, ByteBuffer arg8, long arg9, BoxParser arg11) {
        long v0 = arg7.position() + arg9;
        arg8 = ByteBuffer.allocate(78);
        arg7.read(arg8);
        arg8.position(6);
        this.dataReferenceIndex = IsoTypeReader.readUInt16(arg8);
        IsoTypeReader.readUInt16(arg8);
        IsoTypeReader.readUInt16(arg8);
        this.predefined[0] = IsoTypeReader.readUInt32(arg8);
        this.predefined[1] = IsoTypeReader.readUInt32(arg8);
        this.predefined[2] = IsoTypeReader.readUInt32(arg8);
        this.width = IsoTypeReader.readUInt16(arg8);
        this.height = IsoTypeReader.readUInt16(arg8);
        this.horizresolution = IsoTypeReader.readFixedPoint1616(arg8);
        this.vertresolution = IsoTypeReader.readFixedPoint1616(arg8);
        IsoTypeReader.readUInt32(arg8);
        this.frameCount = IsoTypeReader.readUInt16(arg8);
        int v2 = IsoTypeReader.readUInt8(arg8);
        int v3 = 31;
        if(v2 > v3) {
            v2 = 31;
        }

        byte[] v4 = new byte[v2];
        arg8.get(v4);
        this.compressorname = Utf8.convert(v4);
        if(v2 < v3) {
            arg8.get(new byte[v3 - v2]);
        }

        this.depth = IsoTypeReader.readUInt16(arg8);
        IsoTypeReader.readUInt16(arg8);
        this.initContainer(new DataSource(v0, arg7) {
            public void close() {
                this.val$dataSource.close();
            }

            public ByteBuffer map(long arg2, long arg4) {
                return this.val$dataSource.map(arg2, arg4);
            }

            public long position() {
                return this.val$dataSource.position();
            }

            public void position(long arg2) {
                this.val$dataSource.position(arg2);
            }

            public int read(ByteBuffer arg7) {
                if(this.val$endPosition == this.val$dataSource.position()) {
                    return -1;
                }

                if((((long)arg7.remaining())) > this.val$endPosition - this.val$dataSource.position()) {
                    ByteBuffer v0 = ByteBuffer.allocate(CastUtils.l2i(this.val$endPosition - this.val$dataSource.position()));
                    this.val$dataSource.read(v0);
                    arg7.put(v0.rewind());
                    return v0.capacity();
                }

                return this.val$dataSource.read(arg7);
            }

            public long size() {
                return this.val$endPosition;
            }

            public long transferTo(long arg7, long arg9, WritableByteChannel arg11) {
                return this.val$dataSource.transferTo(arg7, arg9, arg11);
            }
        }, arg9 - 78, arg11);
    }

    public void setCompressorname(String arg1) {
        this.compressorname = arg1;
    }

    public void setDepth(int arg1) {
        this.depth = arg1;
    }

    public void setFrameCount(int arg1) {
        this.frameCount = arg1;
    }

    public void setHeight(int arg1) {
        this.height = arg1;
    }

    public void setHorizresolution(double arg1) {
        this.horizresolution = arg1;
    }

    public void setType(String arg1) {
        this.type = arg1;
    }

    public void setVertresolution(double arg1) {
        this.vertresolution = arg1;
    }

    public void setWidth(int arg1) {
        this.width = arg1;
    }
}

