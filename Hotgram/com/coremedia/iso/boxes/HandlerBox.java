package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.Utf8;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.a.b.b.b;

public class HandlerBox extends AbstractFullBox {
    public static final String TYPE = "hdlr";
    private long a;
    private long b;
    private long c;
    private String handlerType;
    private String name;
    public static final Map readableTypes;
    private long shouldBeZeroButAppleWritesHereSomeValue;
    private boolean zeroTerm;

    static {
        HandlerBox.ajc$preClinit();
        HashMap v0 = new HashMap();
        v0.put("odsm", "ObjectDescriptorStream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        v0.put("crsm", "ClockReferenceStream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        v0.put("sdsm", "SceneDescriptionStream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        v0.put("m7sm", "MPEG7Stream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        v0.put("ocsm", "ObjectContentInfoStream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        v0.put("ipsm", "IPMP Stream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        v0.put("mjsm", "MPEG-J Stream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        v0.put("mdir", "Apple Meta Data iTunes Reader");
        v0.put("mp7b", "MPEG-7 binary XML");
        v0.put("mp7t", "MPEG-7 XML");
        v0.put("vide", "Video Track");
        v0.put("soun", "Sound Track");
        v0.put("hint", "Hint Track");
        v0.put("appl", "Apple specific");
        v0.put("meta", "Timed Metadata track - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        HandlerBox.readableTypes = Collections.unmodifiableMap(((Map)v0));
    }

    public HandlerBox() {
        super("hdlr");
        this.name = null;
        this.zeroTerm = true;
    }

    public void _parseDetails(ByteBuffer arg4) {
        this.parseVersionAndFlags(arg4);
        this.shouldBeZeroButAppleWritesHereSomeValue = IsoTypeReader.readUInt32(arg4);
        this.handlerType = IsoTypeReader.read4cc(arg4);
        this.a = IsoTypeReader.readUInt32(arg4);
        this.b = IsoTypeReader.readUInt32(arg4);
        this.c = IsoTypeReader.readUInt32(arg4);
        if(arg4.remaining() > 0) {
            this.name = IsoTypeReader.readString(arg4, arg4.remaining());
            if(this.name.endsWith("\u0000")) {
                this.name = this.name.substring(0, this.name.length() - 1);
                this.zeroTerm = true;
            }
            else {
                goto label_30;
            }
        }
        else {
        label_30:
            this.zeroTerm = false;
        }
    }

    private static void ajc$preClinit() {
        b v8 = new b("HandlerBox.java", HandlerBox.class);
        HandlerBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getHandlerType", "com.coremedia.iso.boxes.HandlerBox", "", "", "", "java.lang.String"), 78);
        HandlerBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setName", "com.coremedia.iso.boxes.HandlerBox", "java.lang.String", "name", "", "void"), 87);
        HandlerBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "setHandlerType", "com.coremedia.iso.boxes.HandlerBox", "java.lang.String", "handlerType", "", "void"), 91);
        HandlerBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "getName", "com.coremedia.iso.boxes.HandlerBox", "", "", "", "java.lang.String"), 95);
        HandlerBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "getHumanReadableTrackType", "com.coremedia.iso.boxes.HandlerBox", "", "", "", "java.lang.String"), 99);
        HandlerBox.ajc$tjp_5 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.HandlerBox", "", "", "", "java.lang.String"), 149);
    }

    protected void getContent(ByteBuffer arg3) {
        this.writeVersionAndFlags(arg3);
        IsoTypeWriter.writeUInt32(arg3, this.shouldBeZeroButAppleWritesHereSomeValue);
        arg3.put(IsoFile.fourCCtoBytes(this.handlerType));
        IsoTypeWriter.writeUInt32(arg3, this.a);
        IsoTypeWriter.writeUInt32(arg3, this.b);
        IsoTypeWriter.writeUInt32(arg3, this.c);
        if(this.name != null) {
            arg3.put(Utf8.convert(this.name));
        }

        if(this.zeroTerm) {
            arg3.put(0);
        }
    }

    protected long getContentSize() {
        int v0 = this.zeroTerm ? Utf8.utf8StringLengthInBytes(this.name) + 25 : Utf8.utf8StringLengthInBytes(this.name) + 24;
        return ((long)v0);
    }

    public String getHandlerType() {
        RequiresParseDetailAspect.aspectOf().before(b.a(HandlerBox.ajc$tjp_0, this, this));
        return this.handlerType;
    }

    public String getHumanReadableTrackType() {
        String v0_1;
        RequiresParseDetailAspect.aspectOf().before(b.a(HandlerBox.ajc$tjp_4, this, this));
        if(HandlerBox.readableTypes.get(this.handlerType) != null) {
            Object v0 = HandlerBox.readableTypes.get(this.handlerType);
        }
        else {
            v0_1 = "Unknown Handler Type";
        }

        return v0_1;
    }

    public String getName() {
        RequiresParseDetailAspect.aspectOf().before(b.a(HandlerBox.ajc$tjp_3, this, this));
        return this.name;
    }

    public void setHandlerType(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(HandlerBox.ajc$tjp_2, this, this, arg3));
        this.handlerType = arg3;
    }

    public void setName(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(HandlerBox.ajc$tjp_1, this, this, arg3));
        this.name = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(HandlerBox.ajc$tjp_5, this, this));
        StringBuilder v0 = new StringBuilder("HandlerBox[handlerType=");
        v0.append(this.getHandlerType());
        v0.append(";name=");
        v0.append(this.getName());
        v0.append("]");
        return v0.toString();
    }
}

