package com.coremedia.iso.boxes;

import com.googlecode.mp4parser.AbstractBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class UserBox extends AbstractBox {
    public static final String TYPE = "uuid";
    byte[] data;

    static {
        UserBox.ajc$preClinit();
    }

    public UserBox(byte[] arg2) {
        super("uuid", arg2);
    }

    public void _parseDetails(ByteBuffer arg2) {
        this.data = new byte[arg2.remaining()];
        arg2.get(this.data);
    }

    private static void ajc$preClinit() {
        b v8 = new b("UserBox.java", UserBox.class);
        UserBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.UserBox", "", "", "", "java.lang.String"), 40);
        UserBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "getData", "com.coremedia.iso.boxes.UserBox", "", "", "", "[B"), 47);
        UserBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "setData", "com.coremedia.iso.boxes.UserBox", "[B", "data", "", "void"), 51);
    }

    protected void getContent(ByteBuffer arg2) {
        arg2.put(this.data);
    }

    protected long getContentSize() {
        return ((long)this.data.length);
    }

    public byte[] getData() {
        RequiresParseDetailAspect.aspectOf().before(b.a(UserBox.ajc$tjp_1, this, this));
        return this.data;
    }

    public void setData(byte[] arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(UserBox.ajc$tjp_2, this, this, arg3));
        this.data = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(UserBox.ajc$tjp_0, this, this));
        StringBuilder v0 = new StringBuilder("UserBox[type=");
        v0.append(this.getType());
        v0.append(";userType=");
        v0.append(new String(this.getUserType()));
        v0.append(";contentLength=");
        v0.append(this.data.length);
        v0.append("]");
        return v0.toString();
    }
}

