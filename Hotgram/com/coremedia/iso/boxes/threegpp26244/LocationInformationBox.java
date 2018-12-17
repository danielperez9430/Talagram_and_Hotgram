package com.coremedia.iso.boxes.threegpp26244;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.Utf8;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.a.a;
import org.a.b.b.b;

public class LocationInformationBox extends AbstractFullBox {
    public static final String TYPE = "loci";
    private String additionalNotes;
    private double altitude;
    private String astronomicalBody;
    private String language;
    private double latitude;
    private double longitude;
    private String name;
    private int role;

    static {
        LocationInformationBox.ajc$preClinit();
    }

    public LocationInformationBox() {
        super("loci");
        this.name = "";
        this.astronomicalBody = "";
        this.additionalNotes = "";
    }

    public void _parseDetails(ByteBuffer arg3) {
        this.parseVersionAndFlags(arg3);
        this.language = IsoTypeReader.readIso639(arg3);
        this.name = IsoTypeReader.readString(arg3);
        this.role = IsoTypeReader.readUInt8(arg3);
        this.longitude = IsoTypeReader.readFixedPoint1616(arg3);
        this.latitude = IsoTypeReader.readFixedPoint1616(arg3);
        this.altitude = IsoTypeReader.readFixedPoint1616(arg3);
        this.astronomicalBody = IsoTypeReader.readString(arg3);
        this.additionalNotes = IsoTypeReader.readString(arg3);
    }

    private static void ajc$preClinit() {
        b v8 = new b("LocationInformationBox.java", LocationInformationBox.class);
        LocationInformationBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getLanguage", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "", "", "", "java.lang.String"), 30);
        LocationInformationBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setLanguage", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "java.lang.String", "language", "", "void"), 34);
        LocationInformationBox.ajc$tjp_10 = v8.a("method-execution", v8.a("1", "getAltitude", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "", "", "", "double"), 70);
        LocationInformationBox.ajc$tjp_11 = v8.a("method-execution", v8.a("1", "setAltitude", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "double", "altitude", "", "void"), 74);
        LocationInformationBox.ajc$tjp_12 = v8.a("method-execution", v8.a("1", "getAstronomicalBody", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "", "", "", "java.lang.String"), 78);
        LocationInformationBox.ajc$tjp_13 = v8.a("method-execution", v8.a("1", "setAstronomicalBody", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "java.lang.String", "astronomicalBody", "", "void"), 82);
        LocationInformationBox.ajc$tjp_14 = v8.a("method-execution", v8.a("1", "getAdditionalNotes", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "", "", "", "java.lang.String"), 86);
        LocationInformationBox.ajc$tjp_15 = v8.a("method-execution", v8.a("1", "setAdditionalNotes", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "java.lang.String", "additionalNotes", "", "void"), 90);
        LocationInformationBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "getName", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "", "", "", "java.lang.String"), 38);
        LocationInformationBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "setName", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "java.lang.String", "name", "", "void"), 42);
        LocationInformationBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "getRole", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "", "", "", "int"), 46);
        LocationInformationBox.ajc$tjp_5 = v8.a("method-execution", v8.a("1", "setRole", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "int", "role", "", "void"), 50);
        LocationInformationBox.ajc$tjp_6 = v8.a("method-execution", v8.a("1", "getLongitude", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "", "", "", "double"), 54);
        LocationInformationBox.ajc$tjp_7 = v8.a("method-execution", v8.a("1", "setLongitude", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "double", "longitude", "", "void"), 58);
        LocationInformationBox.ajc$tjp_8 = v8.a("method-execution", v8.a("1", "getLatitude", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "", "", "", "double"), 62);
        LocationInformationBox.ajc$tjp_9 = v8.a("method-execution", v8.a("1", "setLatitude", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "double", "latitude", "", "void"), 66);
    }

    public String getAdditionalNotes() {
        RequiresParseDetailAspect.aspectOf().before(b.a(LocationInformationBox.ajc$tjp_14, this, this));
        return this.additionalNotes;
    }

    public double getAltitude() {
        RequiresParseDetailAspect.aspectOf().before(b.a(LocationInformationBox.ajc$tjp_10, this, this));
        return this.altitude;
    }

    public String getAstronomicalBody() {
        RequiresParseDetailAspect.aspectOf().before(b.a(LocationInformationBox.ajc$tjp_12, this, this));
        return this.astronomicalBody;
    }

    protected void getContent(ByteBuffer arg4) {
        this.writeVersionAndFlags(arg4);
        IsoTypeWriter.writeIso639(arg4, this.language);
        arg4.put(Utf8.convert(this.name));
        arg4.put(0);
        IsoTypeWriter.writeUInt8(arg4, this.role);
        IsoTypeWriter.writeFixedPoint1616(arg4, this.longitude);
        IsoTypeWriter.writeFixedPoint1616(arg4, this.latitude);
        IsoTypeWriter.writeFixedPoint1616(arg4, this.altitude);
        arg4.put(Utf8.convert(this.astronomicalBody));
        arg4.put(0);
        arg4.put(Utf8.convert(this.additionalNotes));
        arg4.put(0);
    }

    protected long getContentSize() {
        return ((long)(Utf8.convert(this.name).length + 22 + Utf8.convert(this.astronomicalBody).length + Utf8.convert(this.additionalNotes).length));
    }

    public String getLanguage() {
        RequiresParseDetailAspect.aspectOf().before(b.a(LocationInformationBox.ajc$tjp_0, this, this));
        return this.language;
    }

    public double getLatitude() {
        RequiresParseDetailAspect.aspectOf().before(b.a(LocationInformationBox.ajc$tjp_8, this, this));
        return this.latitude;
    }

    public double getLongitude() {
        RequiresParseDetailAspect.aspectOf().before(b.a(LocationInformationBox.ajc$tjp_6, this, this));
        return this.longitude;
    }

    public String getName() {
        RequiresParseDetailAspect.aspectOf().before(b.a(LocationInformationBox.ajc$tjp_2, this, this));
        return this.name;
    }

    public int getRole() {
        RequiresParseDetailAspect.aspectOf().before(b.a(LocationInformationBox.ajc$tjp_4, this, this));
        return this.role;
    }

    public void setAdditionalNotes(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(LocationInformationBox.ajc$tjp_15, this, this, arg3));
        this.additionalNotes = arg3;
    }

    public void setAltitude(double arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(LocationInformationBox.ajc$tjp_11, this, this, a.a(arg3)));
        this.altitude = arg3;
    }

    public void setAstronomicalBody(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(LocationInformationBox.ajc$tjp_13, this, this, arg3));
        this.astronomicalBody = arg3;
    }

    public void setLanguage(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(LocationInformationBox.ajc$tjp_1, this, this, arg3));
        this.language = arg3;
    }

    public void setLatitude(double arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(LocationInformationBox.ajc$tjp_9, this, this, a.a(arg3)));
        this.latitude = arg3;
    }

    public void setLongitude(double arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(LocationInformationBox.ajc$tjp_7, this, this, a.a(arg3)));
        this.longitude = arg3;
    }

    public void setName(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(LocationInformationBox.ajc$tjp_3, this, this, arg3));
        this.name = arg3;
    }

    public void setRole(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(LocationInformationBox.ajc$tjp_5, this, this, a.a(arg3)));
        this.role = arg3;
    }
}

