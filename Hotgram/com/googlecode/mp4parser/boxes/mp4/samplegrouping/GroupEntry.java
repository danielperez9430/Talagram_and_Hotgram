package com.googlecode.mp4parser.boxes.mp4.samplegrouping;

import java.nio.ByteBuffer;

public abstract class GroupEntry {
    public GroupEntry() {
        super();
    }

    public abstract ByteBuffer get();

    public abstract String getType();

    public abstract void parse(ByteBuffer arg1);

    public int size() {
        return this.get().limit();
    }
}

