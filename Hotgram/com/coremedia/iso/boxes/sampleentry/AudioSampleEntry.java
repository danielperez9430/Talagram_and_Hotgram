package com.coremedia.iso.boxes.sampleentry;

import com.coremedia.iso.BoxParser;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.Container;
import com.googlecode.mp4parser.DataSource;
import com.googlecode.mp4parser.util.CastUtils;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

public final class AudioSampleEntry extends AbstractSampleEntry {
    public static final String TYPE1 = "samr";
    public static final String TYPE10 = "mlpa";
    public static final String TYPE11 = "dtsl";
    public static final String TYPE12 = "dtsh";
    public static final String TYPE13 = "dtse";
    public static final String TYPE2 = "sawb";
    public static final String TYPE3 = "mp4a";
    public static final String TYPE4 = "drms";
    public static final String TYPE5 = "alac";
    public static final String TYPE7 = "owma";
    public static final String TYPE8 = "ac-3";
    public static final String TYPE9 = "ec-3";
    public static final String TYPE_ENCRYPTED = "enca";
    private long bytesPerFrame;
    private long bytesPerPacket;
    private long bytesPerSample;
    private int channelCount;
    private int compressionId;
    private int packetSize;
    private int reserved1;
    private long reserved2;
    private long sampleRate;
    private int sampleSize;
    private long samplesPerPacket;
    private int soundVersion;
    private byte[] soundVersion2Data;

    static {
    }

    public AudioSampleEntry(String arg1) {
        super(arg1);
    }

    public void getBox(WritableByteChannel arg9) {
        arg9.write(this.getHeader());
        int v1 = 0;
        int v2 = 16;
        int v0 = this.soundVersion == 1 ? 16 : 0;
        v0 += 28;
        int v5 = 2;
        if(this.soundVersion == v5) {
            v1 = 36;
        }

        ByteBuffer v0_1 = ByteBuffer.allocate(v0 + v1);
        v0_1.position(6);
        IsoTypeWriter.writeUInt16(v0_1, this.dataReferenceIndex);
        IsoTypeWriter.writeUInt16(v0_1, this.soundVersion);
        IsoTypeWriter.writeUInt16(v0_1, this.reserved1);
        IsoTypeWriter.writeUInt32(v0_1, this.reserved2);
        IsoTypeWriter.writeUInt16(v0_1, this.channelCount);
        IsoTypeWriter.writeUInt16(v0_1, this.sampleSize);
        IsoTypeWriter.writeUInt16(v0_1, this.compressionId);
        IsoTypeWriter.writeUInt16(v0_1, this.packetSize);
        long v1_1 = this.type.equals("mlpa") ? this.getSampleRate() : this.getSampleRate() << v2;
        IsoTypeWriter.writeUInt32(v0_1, v1_1);
        if(this.soundVersion == 1) {
            IsoTypeWriter.writeUInt32(v0_1, this.samplesPerPacket);
            IsoTypeWriter.writeUInt32(v0_1, this.bytesPerPacket);
            IsoTypeWriter.writeUInt32(v0_1, this.bytesPerFrame);
            IsoTypeWriter.writeUInt32(v0_1, this.bytesPerSample);
        }

        if(this.soundVersion == v5) {
            IsoTypeWriter.writeUInt32(v0_1, this.samplesPerPacket);
            IsoTypeWriter.writeUInt32(v0_1, this.bytesPerPacket);
            IsoTypeWriter.writeUInt32(v0_1, this.bytesPerFrame);
            IsoTypeWriter.writeUInt32(v0_1, this.bytesPerSample);
            v0_1.put(this.soundVersion2Data);
        }

        arg9.write(v0_1.rewind());
        this.writeContainer(arg9);
    }

    public long getBytesPerFrame() {
        return this.bytesPerFrame;
    }

    public long getBytesPerPacket() {
        return this.bytesPerPacket;
    }

    public long getBytesPerSample() {
        return this.bytesPerSample;
    }

    public int getChannelCount() {
        return this.channelCount;
    }

    public int getCompressionId() {
        return this.compressionId;
    }

    public int getPacketSize() {
        return this.packetSize;
    }

    public int getReserved1() {
        return this.reserved1;
    }

    public long getReserved2() {
        return this.reserved2;
    }

    public long getSampleRate() {
        return this.sampleRate;
    }

    public int getSampleSize() {
        return this.sampleSize;
    }

    public long getSamplesPerPacket() {
        return this.samplesPerPacket;
    }

    public long getSize() {
        int v1 = 0;
        int v2 = 16;
        int v0 = this.soundVersion == 1 ? 16 : 0;
        v0 += 28;
        if(this.soundVersion == 2) {
            v1 = 36;
        }

        long v0_1 = (((long)(v0 + v1))) + this.getContainerSize();
        if(!this.largeBox) {
            if(8 + v0_1 >= 4294967296L) {
            }
            else {
                v2 = 8;
            }
        }

        return v0_1 + (((long)v2));
    }

    public int getSoundVersion() {
        return this.soundVersion;
    }

    public byte[] getSoundVersion2Data() {
        return this.soundVersion2Data;
    }

    public void parse(DataSource arg8, ByteBuffer arg9, long arg10, BoxParser arg12) {
        arg9 = ByteBuffer.allocate(28);
        arg8.read(arg9);
        arg9.position(6);
        this.dataReferenceIndex = IsoTypeReader.readUInt16(arg9);
        this.soundVersion = IsoTypeReader.readUInt16(arg9);
        this.reserved1 = IsoTypeReader.readUInt16(arg9);
        this.reserved2 = IsoTypeReader.readUInt32(arg9);
        this.channelCount = IsoTypeReader.readUInt16(arg9);
        this.sampleSize = IsoTypeReader.readUInt16(arg9);
        this.compressionId = IsoTypeReader.readUInt16(arg9);
        this.packetSize = IsoTypeReader.readUInt16(arg9);
        this.sampleRate = IsoTypeReader.readUInt32(arg9);
        int v0 = 16;
        if(!this.type.equals("mlpa")) {
            this.sampleRate >>>= v0;
        }

        if(this.soundVersion == 1) {
            arg9 = ByteBuffer.allocate(v0);
            arg8.read(arg9);
            arg9.rewind();
            this.samplesPerPacket = IsoTypeReader.readUInt32(arg9);
            this.bytesPerPacket = IsoTypeReader.readUInt32(arg9);
            this.bytesPerFrame = IsoTypeReader.readUInt32(arg9);
            this.bytesPerSample = IsoTypeReader.readUInt32(arg9);
        }

        int v2 = 36;
        int v3 = 2;
        if(this.soundVersion == v3) {
            arg9 = ByteBuffer.allocate(v2);
            arg8.read(arg9);
            arg9.rewind();
            this.samplesPerPacket = IsoTypeReader.readUInt32(arg9);
            this.bytesPerPacket = IsoTypeReader.readUInt32(arg9);
            this.bytesPerFrame = IsoTypeReader.readUInt32(arg9);
            this.bytesPerSample = IsoTypeReader.readUInt32(arg9);
            this.soundVersion2Data = new byte[20];
            arg9.get(this.soundVersion2Data);
        }

        long v4 = 28;
        if("owma".equals(this.type)) {
            System.err.println("owma");
            arg10 -= v4;
            if(this.soundVersion == 1) {
            }
            else {
                v0 = 0;
            }

            arg10 -= ((long)v0);
            if(this.soundVersion == v3) {
            }
            else {
                v2 = 0;
            }

            arg10 -= ((long)v2);
            arg9 = ByteBuffer.allocate(CastUtils.l2i(arg10));
            arg8.read(arg9);
            this.addBox(new Box(arg10, arg9) {
                public void getBox(WritableByteChannel arg2) {
                    this.val$owmaSpecifics.rewind();
                    arg2.write(this.val$owmaSpecifics);
                }

                public long getOffset() {
                    return 0;
                }

                public Container getParent() {
                    return AudioSampleEntry.this;
                }

                public long getSize() {
                    return this.val$remaining;
                }

                public String getType() {
                    return "----";
                }

                public void parse(DataSource arg1, ByteBuffer arg2, long arg3, BoxParser arg5) {
                    throw new RuntimeException("NotImplemented");
                }

                public void setParent(Container arg2) {
                    if(!AudioSampleEntry.$assertionsDisabled) {
                        if(arg2 == AudioSampleEntry.this) {
                        }
                        else {
                            throw new AssertionError("you cannot diswown this special box");
                        }
                    }
                }
            });
        }
        else {
            arg10 -= v4;
            if(this.soundVersion == 1) {
            }
            else {
                v0 = 0;
            }

            arg10 -= ((long)v0);
            if(this.soundVersion == v3) {
            }
            else {
                v2 = 0;
            }

            this.initContainer(arg8, arg10 - (((long)v2)), arg12);
        }
    }

    public void setBytesPerFrame(long arg1) {
        this.bytesPerFrame = arg1;
    }

    public void setBytesPerPacket(long arg1) {
        this.bytesPerPacket = arg1;
    }

    public void setBytesPerSample(long arg1) {
        this.bytesPerSample = arg1;
    }

    public void setChannelCount(int arg1) {
        this.channelCount = arg1;
    }

    public void setCompressionId(int arg1) {
        this.compressionId = arg1;
    }

    public void setPacketSize(int arg1) {
        this.packetSize = arg1;
    }

    public void setReserved1(int arg1) {
        this.reserved1 = arg1;
    }

    public void setReserved2(long arg1) {
        this.reserved2 = arg1;
    }

    public void setSampleRate(long arg1) {
        this.sampleRate = arg1;
    }

    public void setSampleSize(int arg1) {
        this.sampleSize = arg1;
    }

    public void setSamplesPerPacket(long arg1) {
        this.samplesPerPacket = arg1;
    }

    public void setSoundVersion(int arg1) {
        this.soundVersion = arg1;
    }

    public void setSoundVersion2Data(byte[] arg1) {
        this.soundVersion2Data = arg1;
    }

    public void setType(String arg1) {
        this.type = arg1;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder("AudioSampleEntry{bytesPerSample=");
        v0.append(this.bytesPerSample);
        v0.append(", bytesPerFrame=");
        v0.append(this.bytesPerFrame);
        v0.append(", bytesPerPacket=");
        v0.append(this.bytesPerPacket);
        v0.append(", samplesPerPacket=");
        v0.append(this.samplesPerPacket);
        v0.append(", packetSize=");
        v0.append(this.packetSize);
        v0.append(", compressionId=");
        v0.append(this.compressionId);
        v0.append(", soundVersion=");
        v0.append(this.soundVersion);
        v0.append(", sampleRate=");
        v0.append(this.sampleRate);
        v0.append(", sampleSize=");
        v0.append(this.sampleSize);
        v0.append(", channelCount=");
        v0.append(this.channelCount);
        v0.append(", boxes=");
        v0.append(this.getBoxes());
        v0.append('}');
        return v0.toString();
    }
}

