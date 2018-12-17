package org.telegram.customization.compression.lz4;

public class LZ4Exception extends RuntimeException {
    private static final long serialVersionUID = 1;

    public LZ4Exception(String arg1) {
        super(arg1);
    }

    public LZ4Exception() {
        super();
    }

    public LZ4Exception(String arg1, Throwable arg2) {
        super(arg1, arg2);
    }
}

