package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.Utf8;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class RatingBox extends AbstractFullBox {
    public static final String TYPE = "rtng";
    private String language;
    private String ratingCriteria;
    private String ratingEntity;
    private String ratingInfo;

    static {
        RatingBox.ajc$preClinit();
    }

    public RatingBox() {
        super("rtng");
    }

    public void _parseDetails(ByteBuffer arg2) {
        this.parseVersionAndFlags(arg2);
        this.ratingEntity = IsoTypeReader.read4cc(arg2);
        this.ratingCriteria = IsoTypeReader.read4cc(arg2);
        this.language = IsoTypeReader.readIso639(arg2);
        this.ratingInfo = IsoTypeReader.readString(arg2);
    }

    private static void ajc$preClinit() {
        b v8 = new b("RatingBox.java", RatingBox.class);
        RatingBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "setRatingEntity", "com.coremedia.iso.boxes.RatingBox", "java.lang.String", "ratingEntity", "", "void"), 46);
        RatingBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setRatingCriteria", "com.coremedia.iso.boxes.RatingBox", "java.lang.String", "ratingCriteria", "", "void"), 50);
        RatingBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "setLanguage", "com.coremedia.iso.boxes.RatingBox", "java.lang.String", "language", "", "void"), 54);
        RatingBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "setRatingInfo", "com.coremedia.iso.boxes.RatingBox", "java.lang.String", "ratingInfo", "", "void"), 58);
        RatingBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "getLanguage", "com.coremedia.iso.boxes.RatingBox", "", "", "", "java.lang.String"), 62);
        RatingBox.ajc$tjp_5 = v8.a("method-execution", v8.a("1", "getRatingEntity", "com.coremedia.iso.boxes.RatingBox", "", "", "", "java.lang.String"), 73);
        RatingBox.ajc$tjp_6 = v8.a("method-execution", v8.a("1", "getRatingCriteria", "com.coremedia.iso.boxes.RatingBox", "", "", "", "java.lang.String"), 83);
        RatingBox.ajc$tjp_7 = v8.a("method-execution", v8.a("1", "getRatingInfo", "com.coremedia.iso.boxes.RatingBox", "", "", "", "java.lang.String"), 87);
        RatingBox.ajc$tjp_8 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.RatingBox", "", "", "", "java.lang.String"), 115);
    }

    protected void getContent(ByteBuffer arg2) {
        this.writeVersionAndFlags(arg2);
        arg2.put(IsoFile.fourCCtoBytes(this.ratingEntity));
        arg2.put(IsoFile.fourCCtoBytes(this.ratingCriteria));
        IsoTypeWriter.writeIso639(arg2, this.language);
        arg2.put(Utf8.convert(this.ratingInfo));
        arg2.put(0);
    }

    protected long getContentSize() {
        return ((long)(Utf8.utf8StringLengthInBytes(this.ratingInfo) + 15));
    }

    public String getLanguage() {
        RequiresParseDetailAspect.aspectOf().before(b.a(RatingBox.ajc$tjp_4, this, this));
        return this.language;
    }

    public String getRatingCriteria() {
        RequiresParseDetailAspect.aspectOf().before(b.a(RatingBox.ajc$tjp_6, this, this));
        return this.ratingCriteria;
    }

    public String getRatingEntity() {
        RequiresParseDetailAspect.aspectOf().before(b.a(RatingBox.ajc$tjp_5, this, this));
        return this.ratingEntity;
    }

    public String getRatingInfo() {
        RequiresParseDetailAspect.aspectOf().before(b.a(RatingBox.ajc$tjp_7, this, this));
        return this.ratingInfo;
    }

    public void setLanguage(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(RatingBox.ajc$tjp_2, this, this, arg3));
        this.language = arg3;
    }

    public void setRatingCriteria(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(RatingBox.ajc$tjp_1, this, this, arg3));
        this.ratingCriteria = arg3;
    }

    public void setRatingEntity(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(RatingBox.ajc$tjp_0, this, this, arg3));
        this.ratingEntity = arg3;
    }

    public void setRatingInfo(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(RatingBox.ajc$tjp_3, this, this, arg3));
        this.ratingInfo = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(RatingBox.ajc$tjp_8, this, this));
        return "RatingBox[language=" + this.getLanguage() + "ratingEntity=" + this.getRatingEntity() + ";ratingCriteria=" + this.getRatingCriteria() + ";language=" + this.getLanguage() + ";ratingInfo=" + this.getRatingInfo() + "]";
    }
}

