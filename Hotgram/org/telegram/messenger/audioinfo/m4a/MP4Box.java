package org.telegram.messenger.audioinfo.m4a;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.telegram.messenger.audioinfo.util.PositionInputStream;
import org.telegram.messenger.audioinfo.util.RangeInputStream;

public class MP4Box {
    protected static final String ASCII = "ISO8859_1";
    private MP4Atom child;
    protected final DataInput data;
    private final PositionInputStream input;
    private final MP4Box parent;
    private final String type;

    public MP4Box(PositionInputStream arg1, MP4Box arg2, String arg3) {
        super();
        this.input = arg1;
        this.parent = arg2;
        this.type = arg3;
        this.data = new DataInputStream(((InputStream)arg1));
    }

    protected MP4Atom getChild() {
        return this.child;
    }

    public PositionInputStream getInput() {
        return this.input;
    }

    public MP4Box getParent() {
        return this.parent;
    }

    public long getPosition() {
        return this.input.getPosition();
    }

    public String getType() {
        return this.type;
    }

    public MP4Atom nextChild() {
        if(this.child != null) {
            this.child.skip();
        }

        int v0 = this.data.readInt();
        byte[] v1 = new byte[4];
        this.data.readFully(v1);
        String v2 = new String(v1, "ISO8859_1");
        RangeInputStream v0_1 = v0 == 1 ? new RangeInputStream(this.input, 16, this.data.readLong() - 16) : new RangeInputStream(this.input, 8, ((long)(v0 - 8)));
        MP4Atom v1_1 = new MP4Atom(v0_1, this, v2);
        this.child = v1_1;
        return v1_1;
    }

    public MP4Atom nextChild(String arg5) {
        MP4Atom v0 = this.nextChild();
        if(v0.getType().matches(arg5)) {
            return v0;
        }

        StringBuilder v2 = new StringBuilder();
        v2.append("atom type mismatch, expected ");
        v2.append(arg5);
        v2.append(", got ");
        v2.append(v0.getType());
        throw new IOException(v2.toString());
    }
}

