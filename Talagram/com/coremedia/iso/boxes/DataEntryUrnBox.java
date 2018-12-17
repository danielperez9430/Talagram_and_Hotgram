package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.Utf8;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class DataEntryUrnBox extends AbstractFullBox {
    public static final String TYPE = "urn ";
    private String location;
    private String name;

    static {
        DataEntryUrnBox.ajc$preClinit();
    }

    public DataEntryUrnBox() {
        super("urn ");
    }

    public void _parseDetails(ByteBuffer arg2) {
        this.name = IsoTypeReader.readString(arg2);
        this.location = IsoTypeReader.readString(arg2);
    }

    private static void ajc$preClinit() {
        b v8 = new b("DataEntryUrnBox.java", DataEntryUrnBox.class);
        DataEntryUrnBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getName", "com.coremedia.iso.boxes.DataEntryUrnBox", "", "", "", "java.lang.String"), 40);
        DataEntryUrnBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "getLocation", "com.coremedia.iso.boxes.DataEntryUrnBox", "", "", "", "java.lang.String"), 44);
        DataEntryUrnBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.DataEntryUrnBox", "", "", "", "java.lang.String"), 67);
    }

    protected void getContent(ByteBuffer arg3) {
        arg3.put(Utf8.convert(this.name));
        arg3.put(0);
        arg3.put(Utf8.convert(this.location));
        arg3.put(0);
    }

    protected long getContentSize() {
        return ((long)(Utf8.utf8StringLengthInBytes(this.name) + 1 + Utf8.utf8StringLengthInBytes(this.location) + 1));
    }

    public String getLocation() {
        RequiresParseDetailAspect.aspectOf().before(b.a(DataEntryUrnBox.ajc$tjp_1, this, this));
        return this.location;
    }

    public String getName() {
        RequiresParseDetailAspect.aspectOf().before(b.a(DataEntryUrnBox.ajc$tjp_0, this, this));
        return this.name;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(DataEntryUrnBox.ajc$tjp_2, this, this));
        StringBuilder v0 = new StringBuilder("DataEntryUrlBox[name=");
        v0.append(this.getName());
        v0.append(";location=");
        v0.append(this.getLocation());
        v0.append("]");
        return v0.toString();
    }
}

