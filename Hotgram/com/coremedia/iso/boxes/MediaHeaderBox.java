package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.googlecode.mp4parser.util.DateHelper;
import java.nio.ByteBuffer;
import java.util.Date;
import org.a.b.a.a;
import org.a.b.b.b;

public class MediaHeaderBox extends AbstractFullBox {
    public static final String TYPE = "mdhd";
    private Date creationTime;
    private long duration;
    private String language;
    private Date modificationTime;
    private long timescale;

    static {
        MediaHeaderBox.ajc$preClinit();
    }

    public MediaHeaderBox() {
        super("mdhd");
        this.creationTime = new Date();
        this.modificationTime = new Date();
        this.language = "eng";
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
        this.language = IsoTypeReader.readIso639(arg3);
        IsoTypeReader.readUInt16(arg3);
    }

    private static void ajc$preClinit() {
        b v8 = new b("MediaHeaderBox.java", MediaHeaderBox.class);
        MediaHeaderBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getCreationTime", "com.coremedia.iso.boxes.MediaHeaderBox", "", "", "", "java.util.Date"), 46);
        MediaHeaderBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "getModificationTime", "com.coremedia.iso.boxes.MediaHeaderBox", "", "", "", "java.util.Date"), 50);
        MediaHeaderBox.ajc$tjp_10 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.MediaHeaderBox", "", "", "", "java.lang.String"), 118);
        MediaHeaderBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "getTimescale", "com.coremedia.iso.boxes.MediaHeaderBox", "", "", "", "long"), 54);
        MediaHeaderBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "getDuration", "com.coremedia.iso.boxes.MediaHeaderBox", "", "", "", "long"), 58);
        MediaHeaderBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "getLanguage", "com.coremedia.iso.boxes.MediaHeaderBox", "", "", "", "java.lang.String"), 62);
        MediaHeaderBox.ajc$tjp_5 = v8.a("method-execution", v8.a("1", "setCreationTime", "com.coremedia.iso.boxes.MediaHeaderBox", "java.util.Date", "creationTime", "", "void"), 79);
        MediaHeaderBox.ajc$tjp_6 = v8.a("method-execution", v8.a("1", "setModificationTime", "com.coremedia.iso.boxes.MediaHeaderBox", "java.util.Date", "modificationTime", "", "void"), 83);
        MediaHeaderBox.ajc$tjp_7 = v8.a("method-execution", v8.a("1", "setTimescale", "com.coremedia.iso.boxes.MediaHeaderBox", "long", "timescale", "", "void"), 87);
        MediaHeaderBox.ajc$tjp_8 = v8.a("method-execution", v8.a("1", "setDuration", "com.coremedia.iso.boxes.MediaHeaderBox", "long", "duration", "", "void"), 91);
        MediaHeaderBox.ajc$tjp_9 = v8.a("method-execution", v8.a("1", "setLanguage", "com.coremedia.iso.boxes.MediaHeaderBox", "java.lang.String", "language", "", "void"), 95);
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

        IsoTypeWriter.writeIso639(arg3, this.language);
        IsoTypeWriter.writeUInt16(arg3, 0);
    }

    protected long getContentSize() {
        long v0 = this.getVersion() == 1 ? 32 : 20;
        return v0 + 4;
    }

    public Date getCreationTime() {
        RequiresParseDetailAspect.aspectOf().before(b.a(MediaHeaderBox.ajc$tjp_0, this, this));
        return this.creationTime;
    }

    public long getDuration() {
        RequiresParseDetailAspect.aspectOf().before(b.a(MediaHeaderBox.ajc$tjp_3, this, this));
        return this.duration;
    }

    public String getLanguage() {
        RequiresParseDetailAspect.aspectOf().before(b.a(MediaHeaderBox.ajc$tjp_4, this, this));
        return this.language;
    }

    public Date getModificationTime() {
        RequiresParseDetailAspect.aspectOf().before(b.a(MediaHeaderBox.ajc$tjp_1, this, this));
        return this.modificationTime;
    }

    public long getTimescale() {
        RequiresParseDetailAspect.aspectOf().before(b.a(MediaHeaderBox.ajc$tjp_2, this, this));
        return this.timescale;
    }

    public void setCreationTime(Date arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(MediaHeaderBox.ajc$tjp_5, this, this, arg3));
        this.creationTime = arg3;
    }

    public void setDuration(long arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(MediaHeaderBox.ajc$tjp_8, this, this, a.a(arg3)));
        this.duration = arg3;
    }

    public void setLanguage(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(MediaHeaderBox.ajc$tjp_9, this, this, arg3));
        this.language = arg3;
    }

    public void setModificationTime(Date arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(MediaHeaderBox.ajc$tjp_6, this, this, arg3));
        this.modificationTime = arg3;
    }

    public void setTimescale(long arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(MediaHeaderBox.ajc$tjp_7, this, this, a.a(arg3)));
        this.timescale = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(MediaHeaderBox.ajc$tjp_10, this, this));
        return "MediaHeaderBox[" + "creationTime=" + this.getCreationTime() + ";" + "modificationTime=" + this.getModificationTime() + ";" + "timescale=" + this.getTimescale() + ";" + "duration=" + this.getDuration() + ";" + "language=" + this.getLanguage() + "]";
    }
}

