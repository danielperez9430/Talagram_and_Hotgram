package com.googlecode.mp4parser.h264.model;

public class AspectRatio {
    public static final AspectRatio Extended_SAR;
    private int value;

    static {
        AspectRatio.Extended_SAR = new AspectRatio(255);
    }

    private AspectRatio(int arg1) {
        super();
        this.value = arg1;
    }

    public static AspectRatio fromValue(int arg1) {
        if(arg1 == AspectRatio.Extended_SAR.value) {
            return AspectRatio.Extended_SAR;
        }

        return new AspectRatio(arg1);
    }

    public int getValue() {
        return this.value;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder("AspectRatio{");
        v0.append("value=");
        v0.append(this.value);
        v0.append('}');
        return v0.toString();
    }
}

