package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.a.a;
import org.a.b.b.b;

public class RecordingYearBox extends AbstractFullBox {
    public static final String TYPE = "yrrc";
    int recordingYear;

    static {
        RecordingYearBox.ajc$preClinit();
    }

    public RecordingYearBox() {
        super("yrrc");
    }

    public void _parseDetails(ByteBuffer arg1) {
        this.parseVersionAndFlags(arg1);
        this.recordingYear = IsoTypeReader.readUInt16(arg1);
    }

    private static void ajc$preClinit() {
        b v8 = new b("RecordingYearBox.java", RecordingYearBox.class);
        RecordingYearBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getRecordingYear", "com.coremedia.iso.boxes.RecordingYearBox", "", "", "", "int"), 42);
        RecordingYearBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setRecordingYear", "com.coremedia.iso.boxes.RecordingYearBox", "int", "recordingYear", "", "void"), 46);
    }

    protected void getContent(ByteBuffer arg2) {
        this.writeVersionAndFlags(arg2);
        IsoTypeWriter.writeUInt16(arg2, this.recordingYear);
    }

    protected long getContentSize() {
        return 6;
    }

    public int getRecordingYear() {
        RequiresParseDetailAspect.aspectOf().before(b.a(RecordingYearBox.ajc$tjp_0, this, this));
        return this.recordingYear;
    }

    public void setRecordingYear(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(RecordingYearBox.ajc$tjp_1, this, this, a.a(arg3)));
        this.recordingYear = arg3;
    }
}

