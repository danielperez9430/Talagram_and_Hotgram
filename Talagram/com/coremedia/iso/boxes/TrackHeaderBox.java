package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.googlecode.mp4parser.util.DateHelper;
import com.googlecode.mp4parser.util.Matrix;
import java.nio.ByteBuffer;
import java.util.Date;
import org.a.b.a.a;
import org.a.b.b.b;

public class TrackHeaderBox extends AbstractFullBox {
    public static final String TYPE = "tkhd";
    private int alternateGroup;
    private Date creationTime;
    private long duration;
    private double height;
    private int layer;
    private Matrix matrix;
    private Date modificationTime;
    private long trackId;
    private float volume;
    private double width;

    static {
        TrackHeaderBox.ajc$preClinit();
    }

    public TrackHeaderBox() {
        super("tkhd");
        this.matrix = Matrix.ROTATE_0;
    }

    public void _parseDetails(ByteBuffer arg6) {
        this.parseVersionAndFlags(arg6);
        if(this.getVersion() == 1) {
            this.creationTime = DateHelper.convert(IsoTypeReader.readUInt64(arg6));
            this.modificationTime = DateHelper.convert(IsoTypeReader.readUInt64(arg6));
            this.trackId = IsoTypeReader.readUInt32(arg6);
            IsoTypeReader.readUInt32(arg6);
            this.duration = arg6.getLong();
            if(this.duration >= -1) {
            }
            else {
                throw new RuntimeException("The tracks duration is bigger than Long.MAX_VALUE");
            }
        }
        else {
            this.creationTime = DateHelper.convert(IsoTypeReader.readUInt32(arg6));
            this.modificationTime = DateHelper.convert(IsoTypeReader.readUInt32(arg6));
            this.trackId = IsoTypeReader.readUInt32(arg6);
            IsoTypeReader.readUInt32(arg6);
            this.duration = IsoTypeReader.readUInt32(arg6);
        }

        IsoTypeReader.readUInt32(arg6);
        IsoTypeReader.readUInt32(arg6);
        this.layer = IsoTypeReader.readUInt16(arg6);
        this.alternateGroup = IsoTypeReader.readUInt16(arg6);
        this.volume = IsoTypeReader.readFixedPoint88(arg6);
        IsoTypeReader.readUInt16(arg6);
        this.matrix = Matrix.fromByteBuffer(arg6);
        this.width = IsoTypeReader.readFixedPoint1616(arg6);
        this.height = IsoTypeReader.readFixedPoint1616(arg6);
    }

    private static void ajc$preClinit() {
        b v8 = new b("TrackHeaderBox.java", TrackHeaderBox.class);
        TrackHeaderBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getCreationTime", "com.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "java.util.Date"), 60);
        TrackHeaderBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "getModificationTime", "com.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "java.util.Date"), 64);
        TrackHeaderBox.ajc$tjp_10 = v8.a("method-execution", v8.a("1", "getContent", "com.coremedia.iso.boxes.TrackHeaderBox", "java.nio.ByteBuffer", "byteBuffer", "", "void"), 142);
        TrackHeaderBox.ajc$tjp_11 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "java.lang.String"), 170);
        TrackHeaderBox.ajc$tjp_12 = v8.a("method-execution", v8.a("1", "setCreationTime", "com.coremedia.iso.boxes.TrackHeaderBox", "java.util.Date", "creationTime", "", "void"), 196);
        TrackHeaderBox.ajc$tjp_13 = v8.a("method-execution", v8.a("1", "setModificationTime", "com.coremedia.iso.boxes.TrackHeaderBox", "java.util.Date", "modificationTime", "", "void"), 203);
        TrackHeaderBox.ajc$tjp_14 = v8.a("method-execution", v8.a("1", "setTrackId", "com.coremedia.iso.boxes.TrackHeaderBox", "long", "trackId", "", "void"), 211);
        TrackHeaderBox.ajc$tjp_15 = v8.a("method-execution", v8.a("1", "setDuration", "com.coremedia.iso.boxes.TrackHeaderBox", "long", "duration", "", "void"), 215);
        TrackHeaderBox.ajc$tjp_16 = v8.a("method-execution", v8.a("1", "setLayer", "com.coremedia.iso.boxes.TrackHeaderBox", "int", "layer", "", "void"), 222);
        TrackHeaderBox.ajc$tjp_17 = v8.a("method-execution", v8.a("1", "setAlternateGroup", "com.coremedia.iso.boxes.TrackHeaderBox", "int", "alternateGroup", "", "void"), 226);
        TrackHeaderBox.ajc$tjp_18 = v8.a("method-execution", v8.a("1", "setVolume", "com.coremedia.iso.boxes.TrackHeaderBox", "float", "volume", "", "void"), 230);
        TrackHeaderBox.ajc$tjp_19 = v8.a("method-execution", v8.a("1", "setMatrix", "com.coremedia.iso.boxes.TrackHeaderBox", "com.googlecode.mp4parser.util.Matrix", "matrix", "", "void"), 234);
        TrackHeaderBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "getTrackId", "com.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "long"), 68);
        TrackHeaderBox.ajc$tjp_20 = v8.a("method-execution", v8.a("1", "setWidth", "com.coremedia.iso.boxes.TrackHeaderBox", "double", "width", "", "void"), 238);
        TrackHeaderBox.ajc$tjp_21 = v8.a("method-execution", v8.a("1", "setHeight", "com.coremedia.iso.boxes.TrackHeaderBox", "double", "height", "", "void"), 242);
        TrackHeaderBox.ajc$tjp_22 = v8.a("method-execution", v8.a("1", "isEnabled", "com.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "boolean"), 247);
        TrackHeaderBox.ajc$tjp_23 = v8.a("method-execution", v8.a("1", "isInMovie", "com.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "boolean"), 251);
        TrackHeaderBox.ajc$tjp_24 = v8.a("method-execution", v8.a("1", "isInPreview", "com.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "boolean"), 255);
        TrackHeaderBox.ajc$tjp_25 = v8.a("method-execution", v8.a("1", "isInPoster", "com.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "boolean"), 259);
        TrackHeaderBox.ajc$tjp_26 = v8.a("method-execution", v8.a("1", "setEnabled", "com.coremedia.iso.boxes.TrackHeaderBox", "boolean", "enabled", "", "void"), 263);
        TrackHeaderBox.ajc$tjp_27 = v8.a("method-execution", v8.a("1", "setInMovie", "com.coremedia.iso.boxes.TrackHeaderBox", "boolean", "inMovie", "", "void"), 271);
        TrackHeaderBox.ajc$tjp_28 = v8.a("method-execution", v8.a("1", "setInPreview", "com.coremedia.iso.boxes.TrackHeaderBox", "boolean", "inPreview", "", "void"), 279);
        TrackHeaderBox.ajc$tjp_29 = v8.a("method-execution", v8.a("1", "setInPoster", "com.coremedia.iso.boxes.TrackHeaderBox", "boolean", "inPoster", "", "void"), 287);
        TrackHeaderBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "getDuration", "com.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "long"), 72);
        TrackHeaderBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "getLayer", "com.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "int"), 76);
        TrackHeaderBox.ajc$tjp_5 = v8.a("method-execution", v8.a("1", "getAlternateGroup", "com.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "int"), 80);
        TrackHeaderBox.ajc$tjp_6 = v8.a("method-execution", v8.a("1", "getVolume", "com.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "float"), 84);
        TrackHeaderBox.ajc$tjp_7 = v8.a("method-execution", v8.a("1", "getMatrix", "com.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "com.googlecode.mp4parser.util.Matrix"), 88);
        TrackHeaderBox.ajc$tjp_8 = v8.a("method-execution", v8.a("1", "getWidth", "com.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "double"), 92);
        TrackHeaderBox.ajc$tjp_9 = v8.a("method-execution", v8.a("1", "getHeight", "com.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "double"), 96);
    }

    public int getAlternateGroup() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_5, this, this));
        return this.alternateGroup;
    }

    public void getContent(ByteBuffer arg6) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_10, this, this, arg6));
        this.writeVersionAndFlags(arg6);
        long v1 = 0;
        if(this.getVersion() == 1) {
            IsoTypeWriter.writeUInt64(arg6, DateHelper.convert(this.creationTime));
            IsoTypeWriter.writeUInt64(arg6, DateHelper.convert(this.modificationTime));
            IsoTypeWriter.writeUInt32(arg6, this.trackId);
            IsoTypeWriter.writeUInt32(arg6, v1);
            IsoTypeWriter.writeUInt64(arg6, this.duration);
        }
        else {
            IsoTypeWriter.writeUInt32(arg6, DateHelper.convert(this.creationTime));
            IsoTypeWriter.writeUInt32(arg6, DateHelper.convert(this.modificationTime));
            IsoTypeWriter.writeUInt32(arg6, this.trackId);
            IsoTypeWriter.writeUInt32(arg6, v1);
            IsoTypeWriter.writeUInt32(arg6, this.duration);
        }

        IsoTypeWriter.writeUInt32(arg6, v1);
        IsoTypeWriter.writeUInt32(arg6, v1);
        IsoTypeWriter.writeUInt16(arg6, this.layer);
        IsoTypeWriter.writeUInt16(arg6, this.alternateGroup);
        IsoTypeWriter.writeFixedPoint88(arg6, ((double)this.volume));
        IsoTypeWriter.writeUInt16(arg6, 0);
        this.matrix.getContent(arg6);
        IsoTypeWriter.writeFixedPoint1616(arg6, this.width);
        IsoTypeWriter.writeFixedPoint1616(arg6, this.height);
    }

    protected long getContentSize() {
        long v0 = this.getVersion() == 1 ? 36 : 24;
        return v0 + 60;
    }

    public Date getCreationTime() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_0, this, this));
        return this.creationTime;
    }

    public long getDuration() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_3, this, this));
        return this.duration;
    }

    public double getHeight() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_9, this, this));
        return this.height;
    }

    public int getLayer() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_4, this, this));
        return this.layer;
    }

    public Matrix getMatrix() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_7, this, this));
        return this.matrix;
    }

    public Date getModificationTime() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_1, this, this));
        return this.modificationTime;
    }

    public long getTrackId() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_2, this, this));
        return this.trackId;
    }

    public float getVolume() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_6, this, this));
        return this.volume;
    }

    public double getWidth() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_8, this, this));
        return this.width;
    }

    public boolean isEnabled() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_22, this, this));
        if((this.getFlags() & 1) > 0) {
            return 1;
        }

        return 0;
    }

    public boolean isInMovie() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_23, this, this));
        if((this.getFlags() & 2) > 0) {
            return 1;
        }

        return 0;
    }

    public boolean isInPoster() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_25, this, this));
        if((this.getFlags() & 8) > 0) {
            return 1;
        }

        return 0;
    }

    public boolean isInPreview() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_24, this, this));
        if((this.getFlags() & 4) > 0) {
            return 1;
        }

        return 0;
    }

    public void setAlternateGroup(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_17, this, this, a.a(arg3)));
        this.alternateGroup = arg3;
    }

    public void setCreationTime(Date arg5) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_12, this, this, arg5));
        this.creationTime = arg5;
        if(DateHelper.convert(arg5) >= 4294967296L) {
            this.setVersion(1);
        }
    }

    public void setDuration(long arg4) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_15, this, this, a.a(arg4)));
        this.duration = arg4;
        if(arg4 >= 4294967296L) {
            this.setFlags(1);
        }
    }

    public void setEnabled(boolean arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_26, this, this, a.a(arg3)));
        int v3 = arg3 ? this.getFlags() | 1 : this.getFlags() & -2;
        this.setFlags(v3);
    }

    public void setHeight(double arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_21, this, this, a.a(arg3)));
        this.height = arg3;
    }

    public void setInMovie(boolean arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_27, this, this, a.a(arg3)));
        int v3 = arg3 ? this.getFlags() | 2 : this.getFlags() & -3;
        this.setFlags(v3);
    }

    public void setInPoster(boolean arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_29, this, this, a.a(arg3)));
        int v3 = arg3 ? this.getFlags() | 8 : this.getFlags() & -9;
        this.setFlags(v3);
    }

    public void setInPreview(boolean arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_28, this, this, a.a(arg3)));
        int v3 = arg3 ? this.getFlags() | 4 : this.getFlags() & -5;
        this.setFlags(v3);
    }

    public void setLayer(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_16, this, this, a.a(arg3)));
        this.layer = arg3;
    }

    public void setMatrix(Matrix arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_19, this, this, arg3));
        this.matrix = arg3;
    }

    public void setModificationTime(Date arg5) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_13, this, this, arg5));
        this.modificationTime = arg5;
        if(DateHelper.convert(arg5) >= 4294967296L) {
            this.setVersion(1);
        }
    }

    public void setTrackId(long arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_14, this, this, a.a(arg3)));
        this.trackId = arg3;
    }

    public void setVolume(float arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_18, this, this, a.a(arg3)));
        this.volume = arg3;
    }

    public void setWidth(double arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_20, this, this, a.a(arg3)));
        this.width = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackHeaderBox.ajc$tjp_11, this, this));
        return "TrackHeaderBox[" + "creationTime=" + this.getCreationTime() + ";" + "modificationTime=" + this.getModificationTime() + ";" + "trackId=" + this.getTrackId() + ";" + "duration=" + this.getDuration() + ";" + "layer=" + this.getLayer() + ";" + "alternateGroup=" + this.getAlternateGroup() + ";" + "volume=" + this.getVolume() + ";" + "matrix=" + this.matrix + ";" + "width=" + this.getWidth() + ";" + "height=" + this.getHeight() + "]";
    }
}

