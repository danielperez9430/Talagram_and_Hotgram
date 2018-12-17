package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.googlecode.mp4parser.annotations.DoNotParseDetail;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.a.b.a.a;
import org.a.b.b.b;

public class FileTypeBox extends AbstractBox {
    public static final String TYPE = "ftyp";
    private List compatibleBrands;
    private String majorBrand;
    private long minorVersion;

    static {
        FileTypeBox.ajc$preClinit();
    }

    public FileTypeBox() {
        super("ftyp");
        this.compatibleBrands = Collections.emptyList();
    }

    public FileTypeBox(String arg2, long arg3, List arg5) {
        super("ftyp");
        this.compatibleBrands = Collections.emptyList();
        this.majorBrand = arg2;
        this.minorVersion = arg3;
        this.compatibleBrands = arg5;
    }

    public void _parseDetails(ByteBuffer arg5) {
        this.majorBrand = IsoTypeReader.read4cc(arg5);
        this.minorVersion = IsoTypeReader.readUInt32(arg5);
        int v0 = arg5.remaining() / 4;
        this.compatibleBrands = new LinkedList();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.compatibleBrands.add(IsoTypeReader.read4cc(arg5));
        }
    }

    private static void ajc$preClinit() {
        b v8 = new b("FileTypeBox.java", FileTypeBox.class);
        FileTypeBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getMajorBrand", "com.coremedia.iso.boxes.FileTypeBox", "", "", "", "java.lang.String"), 85);
        FileTypeBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setMajorBrand", "com.coremedia.iso.boxes.FileTypeBox", "java.lang.String", "majorBrand", "", "void"), 94);
        FileTypeBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "setMinorVersion", "com.coremedia.iso.boxes.FileTypeBox", "long", "minorVersion", "", "void"), 103);
        FileTypeBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "getMinorVersion", "com.coremedia.iso.boxes.FileTypeBox", "", "", "", "long"), 113);
        FileTypeBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "getCompatibleBrands", "com.coremedia.iso.boxes.FileTypeBox", "", "", "", "java.util.List"), 122);
        FileTypeBox.ajc$tjp_5 = v8.a("method-execution", v8.a("1", "setCompatibleBrands", "com.coremedia.iso.boxes.FileTypeBox", "java.util.List", "compatibleBrands", "", "void"), 126);
    }

    public List getCompatibleBrands() {
        RequiresParseDetailAspect.aspectOf().before(b.a(FileTypeBox.ajc$tjp_4, this, this));
        return this.compatibleBrands;
    }

    protected void getContent(ByteBuffer arg3) {
        arg3.put(IsoFile.fourCCtoBytes(this.majorBrand));
        IsoTypeWriter.writeUInt32(arg3, this.minorVersion);
        Iterator v0 = this.compatibleBrands.iterator();
        while(v0.hasNext()) {
            arg3.put(IsoFile.fourCCtoBytes(v0.next()));
        }
    }

    protected long getContentSize() {
        return ((long)(this.compatibleBrands.size() * 4 + 8));
    }

    public String getMajorBrand() {
        RequiresParseDetailAspect.aspectOf().before(b.a(FileTypeBox.ajc$tjp_0, this, this));
        return this.majorBrand;
    }

    public long getMinorVersion() {
        RequiresParseDetailAspect.aspectOf().before(b.a(FileTypeBox.ajc$tjp_3, this, this));
        return this.minorVersion;
    }

    public void setCompatibleBrands(List arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(FileTypeBox.ajc$tjp_5, this, this, arg3));
        this.compatibleBrands = arg3;
    }

    public void setMajorBrand(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(FileTypeBox.ajc$tjp_1, this, this, arg3));
        this.majorBrand = arg3;
    }

    public void setMinorVersion(long arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(FileTypeBox.ajc$tjp_2, this, this, a.a(arg3)));
        this.minorVersion = arg3;
    }

    @DoNotParseDetail public String toString() {
        StringBuilder v0 = new StringBuilder();
        v0.append("FileTypeBox[");
        v0.append("majorBrand=");
        v0.append(this.getMajorBrand());
        v0.append(";");
        v0.append("minorVersion=");
        v0.append(this.getMinorVersion());
        Iterator v1 = this.compatibleBrands.iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            v0.append(";");
            v0.append("compatibleBrand=");
            v0.append(((String)v2));
        }

        v0.append("]");
        return v0.toString();
    }
}

