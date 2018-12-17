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

public class MovieHeaderBox extends AbstractFullBox {
    public static final String TYPE = "mvhd";
    private Date creationTime;
    private int currentTime;
    private long duration;
    private Matrix matrix;
    private Date modificationTime;
    private long nextTrackId;
    private int posterTime;
    private int previewDuration;
    private int previewTime;
    private double rate;
    private int selectionDuration;
    private int selectionTime;
    private long timescale;
    private float volume;

    static {
        MovieHeaderBox.ajc$preClinit();
    }

    public MovieHeaderBox() {
        super("mvhd");
        this.rate = 1;
        this.volume = 1f;
        this.matrix = Matrix.ROTATE_0;
    }

    public void _parseDetails(ByteBuffer arg3) {
        long v0;
        this.parseVersionAndFlags(arg3);
        if(this.getVersion() == 1) {
            this.creationTime = DateHelper.convert(IsoTypeReader.readUInt64(arg3));
            this.modificationTime = DateHelper.convert(IsoTypeReader.readUInt64(arg3));
            this.timescale = IsoTypeReader.readUInt32(arg3);
            v0 = IsoTypeReader.readUInt64(arg3);
        }
        else {
            this.creationTime = DateHelper.convert(IsoTypeReader.readUInt32(arg3));
            this.modificationTime = DateHelper.convert(IsoTypeReader.readUInt32(arg3));
            this.timescale = IsoTypeReader.readUInt32(arg3);
            v0 = IsoTypeReader.readUInt32(arg3);
        }

        this.duration = v0;
        this.rate = IsoTypeReader.readFixedPoint1616(arg3);
        this.volume = IsoTypeReader.readFixedPoint88(arg3);
        IsoTypeReader.readUInt16(arg3);
        IsoTypeReader.readUInt32(arg3);
        IsoTypeReader.readUInt32(arg3);
        this.matrix = Matrix.fromByteBuffer(arg3);
        this.previewTime = arg3.getInt();
        this.previewDuration = arg3.getInt();
        this.posterTime = arg3.getInt();
        this.selectionTime = arg3.getInt();
        this.selectionDuration = arg3.getInt();
        this.currentTime = arg3.getInt();
        this.nextTrackId = IsoTypeReader.readUInt32(arg3);
    }

    private static void ajc$preClinit() {
        b v8 = new b("MovieHeaderBox.java", MovieHeaderBox.class);
        MovieHeaderBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getCreationTime", "com.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "java.util.Date"), 63);
        MovieHeaderBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "getModificationTime", "com.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "java.util.Date"), 67);
        MovieHeaderBox.ajc$tjp_10 = v8.a("method-execution", v8.a("1", "setModificationTime", "com.coremedia.iso.boxes.MovieHeaderBox", "java.util.Date", "modificationTime", "", "void"), 203);
        MovieHeaderBox.ajc$tjp_11 = v8.a("method-execution", v8.a("1", "setTimescale", "com.coremedia.iso.boxes.MovieHeaderBox", "long", "timescale", "", "void"), 211);
        MovieHeaderBox.ajc$tjp_12 = v8.a("method-execution", v8.a("1", "setDuration", "com.coremedia.iso.boxes.MovieHeaderBox", "long", "duration", "", "void"), 215);
        MovieHeaderBox.ajc$tjp_13 = v8.a("method-execution", v8.a("1", "setRate", "com.coremedia.iso.boxes.MovieHeaderBox", "double", "rate", "", "void"), 222);
        MovieHeaderBox.ajc$tjp_14 = v8.a("method-execution", v8.a("1", "setVolume", "com.coremedia.iso.boxes.MovieHeaderBox", "float", "volume", "", "void"), 226);
        MovieHeaderBox.ajc$tjp_15 = v8.a("method-execution", v8.a("1", "setMatrix", "com.coremedia.iso.boxes.MovieHeaderBox", "com.googlecode.mp4parser.util.Matrix", "matrix", "", "void"), 230);
        MovieHeaderBox.ajc$tjp_16 = v8.a("method-execution", v8.a("1", "setNextTrackId", "com.coremedia.iso.boxes.MovieHeaderBox", "long", "nextTrackId", "", "void"), 234);
        MovieHeaderBox.ajc$tjp_17 = v8.a("method-execution", v8.a("1", "getPreviewTime", "com.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "int"), 238);
        MovieHeaderBox.ajc$tjp_18 = v8.a("method-execution", v8.a("1", "setPreviewTime", "com.coremedia.iso.boxes.MovieHeaderBox", "int", "previewTime", "", "void"), 242);
        MovieHeaderBox.ajc$tjp_19 = v8.a("method-execution", v8.a("1", "getPreviewDuration", "com.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "int"), 246);
        MovieHeaderBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "getTimescale", "com.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "long"), 71);
        MovieHeaderBox.ajc$tjp_20 = v8.a("method-execution", v8.a("1", "setPreviewDuration", "com.coremedia.iso.boxes.MovieHeaderBox", "int", "previewDuration", "", "void"), 250);
        MovieHeaderBox.ajc$tjp_21 = v8.a("method-execution", v8.a("1", "getPosterTime", "com.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "int"), 254);
        MovieHeaderBox.ajc$tjp_22 = v8.a("method-execution", v8.a("1", "setPosterTime", "com.coremedia.iso.boxes.MovieHeaderBox", "int", "posterTime", "", "void"), 258);
        MovieHeaderBox.ajc$tjp_23 = v8.a("method-execution", v8.a("1", "getSelectionTime", "com.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "int"), 262);
        MovieHeaderBox.ajc$tjp_24 = v8.a("method-execution", v8.a("1", "setSelectionTime", "com.coremedia.iso.boxes.MovieHeaderBox", "int", "selectionTime", "", "void"), 266);
        MovieHeaderBox.ajc$tjp_25 = v8.a("method-execution", v8.a("1", "getSelectionDuration", "com.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "int"), 270);
        MovieHeaderBox.ajc$tjp_26 = v8.a("method-execution", v8.a("1", "setSelectionDuration", "com.coremedia.iso.boxes.MovieHeaderBox", "int", "selectionDuration", "", "void"), 274);
        MovieHeaderBox.ajc$tjp_27 = v8.a("method-execution", v8.a("1", "getCurrentTime", "com.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "int"), 278);
        MovieHeaderBox.ajc$tjp_28 = v8.a("method-execution", v8.a("1", "setCurrentTime", "com.coremedia.iso.boxes.MovieHeaderBox", "int", "currentTime", "", "void"), 282);
        MovieHeaderBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "getDuration", "com.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "long"), 75);
        MovieHeaderBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "getRate", "com.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "double"), 79);
        MovieHeaderBox.ajc$tjp_5 = v8.a("method-execution", v8.a("1", "getVolume", "com.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "float"), 83);
        MovieHeaderBox.ajc$tjp_6 = v8.a("method-execution", v8.a("1", "getMatrix", "com.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "com.googlecode.mp4parser.util.Matrix"), 87);
        MovieHeaderBox.ajc$tjp_7 = v8.a("method-execution", v8.a("1", "getNextTrackId", "com.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "long"), 91);
        MovieHeaderBox.ajc$tjp_8 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "java.lang.String"), 139);
        MovieHeaderBox.ajc$tjp_9 = v8.a("method-execution", v8.a("1", "setCreationTime", "com.coremedia.iso.boxes.MovieHeaderBox", "java.util.Date", "creationTime", "", "void"), 195);
    }

    protected void getContent(ByteBuffer arg3) {
        this.writeVersionAndFlags(arg3);
        if(this.getVersion() == 1) {
            IsoTypeWriter.writeUInt64(arg3, DateHelper.convert(this.creationTime));
            IsoTypeWriter.writeUInt64(arg3, DateHelper.convert(this.modificationTime));
            IsoTypeWriter.writeUInt32(arg3, this.timescale);
            IsoTypeWriter.writeUInt64(arg3, this.duration);
        }
        else {
            IsoTypeWriter.writeUInt32(arg3, DateHelper.convert(this.creationTime));
            IsoTypeWriter.writeUInt32(arg3, DateHelper.convert(this.modificationTime));
            IsoTypeWriter.writeUInt32(arg3, this.timescale);
            IsoTypeWriter.writeUInt32(arg3, this.duration);
        }

        IsoTypeWriter.writeFixedPoint1616(arg3, this.rate);
        IsoTypeWriter.writeFixedPoint88(arg3, ((double)this.volume));
        IsoTypeWriter.writeUInt16(arg3, 0);
        IsoTypeWriter.writeUInt32(arg3, 0);
        IsoTypeWriter.writeUInt32(arg3, 0);
        this.matrix.getContent(arg3);
        arg3.putInt(this.previewTime);
        arg3.putInt(this.previewDuration);
        arg3.putInt(this.posterTime);
        arg3.putInt(this.selectionTime);
        arg3.putInt(this.selectionDuration);
        arg3.putInt(this.currentTime);
        IsoTypeWriter.writeUInt32(arg3, this.nextTrackId);
    }

    protected long getContentSize() {
        long v0 = this.getVersion() == 1 ? 32 : 20;
        return v0 + 80;
    }

    public Date getCreationTime() {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_0, this, this));
        return this.creationTime;
    }

    public int getCurrentTime() {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_27, this, this));
        return this.currentTime;
    }

    public long getDuration() {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_3, this, this));
        return this.duration;
    }

    public Matrix getMatrix() {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_6, this, this));
        return this.matrix;
    }

    public Date getModificationTime() {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_1, this, this));
        return this.modificationTime;
    }

    public long getNextTrackId() {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_7, this, this));
        return this.nextTrackId;
    }

    public int getPosterTime() {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_21, this, this));
        return this.posterTime;
    }

    public int getPreviewDuration() {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_19, this, this));
        return this.previewDuration;
    }

    public int getPreviewTime() {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_17, this, this));
        return this.previewTime;
    }

    public double getRate() {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_4, this, this));
        return this.rate;
    }

    public int getSelectionDuration() {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_25, this, this));
        return this.selectionDuration;
    }

    public int getSelectionTime() {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_23, this, this));
        return this.selectionTime;
    }

    public long getTimescale() {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_2, this, this));
        return this.timescale;
    }

    public float getVolume() {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_5, this, this));
        return this.volume;
    }

    public void setCreationTime(Date arg5) {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_9, this, this, arg5));
        this.creationTime = arg5;
        if(DateHelper.convert(arg5) >= 4294967296L) {
            this.setVersion(1);
        }
    }

    public void setCurrentTime(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_28, this, this, a.a(arg3)));
        this.currentTime = arg3;
    }

    public void setDuration(long arg4) {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_12, this, this, a.a(arg4)));
        this.duration = arg4;
        if(arg4 >= 4294967296L) {
            this.setVersion(1);
        }
    }

    public void setMatrix(Matrix arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_15, this, this, arg3));
        this.matrix = arg3;
    }

    public void setModificationTime(Date arg5) {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_10, this, this, arg5));
        this.modificationTime = arg5;
        if(DateHelper.convert(arg5) >= 4294967296L) {
            this.setVersion(1);
        }
    }

    public void setNextTrackId(long arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_16, this, this, a.a(arg3)));
        this.nextTrackId = arg3;
    }

    public void setPosterTime(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_22, this, this, a.a(arg3)));
        this.posterTime = arg3;
    }

    public void setPreviewDuration(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_20, this, this, a.a(arg3)));
        this.previewDuration = arg3;
    }

    public void setPreviewTime(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_18, this, this, a.a(arg3)));
        this.previewTime = arg3;
    }

    public void setRate(double arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_13, this, this, a.a(arg3)));
        this.rate = arg3;
    }

    public void setSelectionDuration(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_26, this, this, a.a(arg3)));
        this.selectionDuration = arg3;
    }

    public void setSelectionTime(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_24, this, this, a.a(arg3)));
        this.selectionTime = arg3;
    }

    public void setTimescale(long arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_11, this, this, a.a(arg3)));
        this.timescale = arg3;
    }

    public void setVolume(float arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_14, this, this, a.a(arg3)));
        this.volume = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieHeaderBox.ajc$tjp_8, this, this));
        return "MovieHeaderBox[" + "creationTime=" + this.getCreationTime() + ";" + "modificationTime=" + this.getModificationTime() + ";" + "timescale=" + this.getTimescale() + ";" + "duration=" + this.getDuration() + ";" + "rate=" + this.getRate() + ";" + "volume=" + this.getVolume() + ";" + "matrix=" + this.matrix + ";" + "nextTrackId=" + this.getNextTrackId() + "]";
    }
}

