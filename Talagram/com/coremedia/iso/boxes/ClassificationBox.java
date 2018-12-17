package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.Utf8;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.a.a;
import org.a.b.b.b;

public class ClassificationBox extends AbstractFullBox {
    public static final String TYPE = "clsf";
    private String classificationEntity;
    private String classificationInfo;
    private int classificationTableIndex;
    private String language;

    static {
        ClassificationBox.ajc$preClinit();
    }

    public ClassificationBox() {
        super("clsf");
    }

    public void _parseDetails(ByteBuffer arg2) {
        this.parseVersionAndFlags(arg2);
        byte[] v0 = new byte[4];
        arg2.get(v0);
        this.classificationEntity = IsoFile.bytesToFourCC(v0);
        this.classificationTableIndex = IsoTypeReader.readUInt16(arg2);
        this.language = IsoTypeReader.readIso639(arg2);
        this.classificationInfo = IsoTypeReader.readString(arg2);
    }

    private static void ajc$preClinit() {
        b v8 = new b("ClassificationBox.java", ClassificationBox.class);
        ClassificationBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getLanguage", "com.coremedia.iso.boxes.ClassificationBox", "", "", "", "java.lang.String"), 44);
        ClassificationBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "getClassificationEntity", "com.coremedia.iso.boxes.ClassificationBox", "", "", "", "java.lang.String"), 48);
        ClassificationBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "getClassificationTableIndex", "com.coremedia.iso.boxes.ClassificationBox", "", "", "", "int"), 52);
        ClassificationBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "getClassificationInfo", "com.coremedia.iso.boxes.ClassificationBox", "", "", "", "java.lang.String"), 56);
        ClassificationBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "setClassificationEntity", "com.coremedia.iso.boxes.ClassificationBox", "java.lang.String", "classificationEntity", "", "void"), 60);
        ClassificationBox.ajc$tjp_5 = v8.a("method-execution", v8.a("1", "setClassificationTableIndex", "com.coremedia.iso.boxes.ClassificationBox", "int", "classificationTableIndex", "", "void"), 64);
        ClassificationBox.ajc$tjp_6 = v8.a("method-execution", v8.a("1", "setLanguage", "com.coremedia.iso.boxes.ClassificationBox", "java.lang.String", "language", "", "void"), 68);
        ClassificationBox.ajc$tjp_7 = v8.a("method-execution", v8.a("1", "setClassificationInfo", "com.coremedia.iso.boxes.ClassificationBox", "java.lang.String", "classificationInfo", "", "void"), 72);
        ClassificationBox.ajc$tjp_8 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.ClassificationBox", "", "", "", "java.lang.String"), 101);
    }

    public String getClassificationEntity() {
        RequiresParseDetailAspect.aspectOf().before(b.a(ClassificationBox.ajc$tjp_1, this, this));
        return this.classificationEntity;
    }

    public String getClassificationInfo() {
        RequiresParseDetailAspect.aspectOf().before(b.a(ClassificationBox.ajc$tjp_3, this, this));
        return this.classificationInfo;
    }

    public int getClassificationTableIndex() {
        RequiresParseDetailAspect.aspectOf().before(b.a(ClassificationBox.ajc$tjp_2, this, this));
        return this.classificationTableIndex;
    }

    protected void getContent(ByteBuffer arg2) {
        arg2.put(IsoFile.fourCCtoBytes(this.classificationEntity));
        IsoTypeWriter.writeUInt16(arg2, this.classificationTableIndex);
        IsoTypeWriter.writeIso639(arg2, this.language);
        arg2.put(Utf8.convert(this.classificationInfo));
        arg2.put(0);
    }

    protected long getContentSize() {
        return ((long)(Utf8.utf8StringLengthInBytes(this.classificationInfo) + 9));
    }

    public String getLanguage() {
        RequiresParseDetailAspect.aspectOf().before(b.a(ClassificationBox.ajc$tjp_0, this, this));
        return this.language;
    }

    public void setClassificationEntity(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(ClassificationBox.ajc$tjp_4, this, this, arg3));
        this.classificationEntity = arg3;
    }

    public void setClassificationInfo(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(ClassificationBox.ajc$tjp_7, this, this, arg3));
        this.classificationInfo = arg3;
    }

    public void setClassificationTableIndex(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(ClassificationBox.ajc$tjp_5, this, this, a.a(arg3)));
        this.classificationTableIndex = arg3;
    }

    public void setLanguage(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(ClassificationBox.ajc$tjp_6, this, this, arg3));
        this.language = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(ClassificationBox.ajc$tjp_8, this, this));
        return "ClassificationBox[language=" + this.getLanguage() + "classificationEntity=" + this.getClassificationEntity() + ";classificationTableIndex=" + this.getClassificationTableIndex() + ";language=" + this.getLanguage() + ";classificationInfo=" + this.getClassificationInfo() + "]";
    }
}

