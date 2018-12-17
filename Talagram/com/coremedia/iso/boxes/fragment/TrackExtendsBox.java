package com.coremedia.iso.boxes.fragment;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.a.a;
import org.a.b.b.b;

public class TrackExtendsBox extends AbstractFullBox {
    public static final String TYPE = "trex";
    private long defaultSampleDescriptionIndex;
    private long defaultSampleDuration;
    private SampleFlags defaultSampleFlags;
    private long defaultSampleSize;
    private long trackId;

    static {
        TrackExtendsBox.ajc$preClinit();
    }

    public TrackExtendsBox() {
        super("trex");
    }

    public void _parseDetails(ByteBuffer arg3) {
        this.parseVersionAndFlags(arg3);
        this.trackId = IsoTypeReader.readUInt32(arg3);
        this.defaultSampleDescriptionIndex = IsoTypeReader.readUInt32(arg3);
        this.defaultSampleDuration = IsoTypeReader.readUInt32(arg3);
        this.defaultSampleSize = IsoTypeReader.readUInt32(arg3);
        this.defaultSampleFlags = new SampleFlags(arg3);
    }

    private static void ajc$preClinit() {
        b v8 = new b("TrackExtendsBox.java", TrackExtendsBox.class);
        TrackExtendsBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getTrackId", "com.coremedia.iso.boxes.fragment.TrackExtendsBox", "", "", "", "long"), 72);
        TrackExtendsBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "getDefaultSampleDescriptionIndex", "com.coremedia.iso.boxes.fragment.TrackExtendsBox", "", "", "", "long"), 76);
        TrackExtendsBox.ajc$tjp_10 = v8.a("method-execution", v8.a("1", "setDefaultSampleFlags", "com.coremedia.iso.boxes.fragment.TrackExtendsBox", "com.coremedia.iso.boxes.fragment.SampleFlags", "defaultSampleFlags", "", "void"), 112);
        TrackExtendsBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "getDefaultSampleDuration", "com.coremedia.iso.boxes.fragment.TrackExtendsBox", "", "", "", "long"), 80);
        TrackExtendsBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "getDefaultSampleSize", "com.coremedia.iso.boxes.fragment.TrackExtendsBox", "", "", "", "long"), 84);
        TrackExtendsBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "getDefaultSampleFlags", "com.coremedia.iso.boxes.fragment.TrackExtendsBox", "", "", "", "com.coremedia.iso.boxes.fragment.SampleFlags"), 88);
        TrackExtendsBox.ajc$tjp_5 = v8.a("method-execution", v8.a("1", "getDefaultSampleFlagsStr", "com.coremedia.iso.boxes.fragment.TrackExtendsBox", "", "", "", "java.lang.String"), 92);
        TrackExtendsBox.ajc$tjp_6 = v8.a("method-execution", v8.a("1", "setTrackId", "com.coremedia.iso.boxes.fragment.TrackExtendsBox", "long", "trackId", "", "void"), 96);
        TrackExtendsBox.ajc$tjp_7 = v8.a("method-execution", v8.a("1", "setDefaultSampleDescriptionIndex", "com.coremedia.iso.boxes.fragment.TrackExtendsBox", "long", "defaultSampleDescriptionIndex", "", "void"), 100);
        TrackExtendsBox.ajc$tjp_8 = v8.a("method-execution", v8.a("1", "setDefaultSampleDuration", "com.coremedia.iso.boxes.fragment.TrackExtendsBox", "long", "defaultSampleDuration", "", "void"), 104);
        TrackExtendsBox.ajc$tjp_9 = v8.a("method-execution", v8.a("1", "setDefaultSampleSize", "com.coremedia.iso.boxes.fragment.TrackExtendsBox", "long", "defaultSampleSize", "", "void"), 108);
    }

    protected void getContent(ByteBuffer arg3) {
        this.writeVersionAndFlags(arg3);
        IsoTypeWriter.writeUInt32(arg3, this.trackId);
        IsoTypeWriter.writeUInt32(arg3, this.defaultSampleDescriptionIndex);
        IsoTypeWriter.writeUInt32(arg3, this.defaultSampleDuration);
        IsoTypeWriter.writeUInt32(arg3, this.defaultSampleSize);
        this.defaultSampleFlags.getContent(arg3);
    }

    protected long getContentSize() {
        return 24;
    }

    public long getDefaultSampleDescriptionIndex() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackExtendsBox.ajc$tjp_1, this, this));
        return this.defaultSampleDescriptionIndex;
    }

    public long getDefaultSampleDuration() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackExtendsBox.ajc$tjp_2, this, this));
        return this.defaultSampleDuration;
    }

    public SampleFlags getDefaultSampleFlags() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackExtendsBox.ajc$tjp_4, this, this));
        return this.defaultSampleFlags;
    }

    public String getDefaultSampleFlagsStr() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackExtendsBox.ajc$tjp_5, this, this));
        return this.defaultSampleFlags.toString();
    }

    public long getDefaultSampleSize() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackExtendsBox.ajc$tjp_3, this, this));
        return this.defaultSampleSize;
    }

    public long getTrackId() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackExtendsBox.ajc$tjp_0, this, this));
        return this.trackId;
    }

    public void setDefaultSampleDescriptionIndex(long arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackExtendsBox.ajc$tjp_7, this, this, a.a(arg3)));
        this.defaultSampleDescriptionIndex = arg3;
    }

    public void setDefaultSampleDuration(long arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackExtendsBox.ajc$tjp_8, this, this, a.a(arg3)));
        this.defaultSampleDuration = arg3;
    }

    public void setDefaultSampleFlags(SampleFlags arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackExtendsBox.ajc$tjp_10, this, this, arg3));
        this.defaultSampleFlags = arg3;
    }

    public void setDefaultSampleSize(long arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackExtendsBox.ajc$tjp_9, this, this, a.a(arg3)));
        this.defaultSampleSize = arg3;
    }

    public void setTrackId(long arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackExtendsBox.ajc$tjp_6, this, this, a.a(arg3)));
        this.trackId = arg3;
    }
}

