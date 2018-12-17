package com.googlecode.mp4parser.h264.model;

import java.io.OutputStream;

public abstract class BitstreamElement {
    public BitstreamElement() {
        super();
    }

    public abstract void write(OutputStream arg1);
}

