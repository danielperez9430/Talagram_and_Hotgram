package com.googlecode.mp4parser.h264.model;

import java.util.Arrays;
import java.util.List;

public class ScalingMatrix {
    public ScalingList[] ScalingList4x4;
    public ScalingList[] ScalingList8x8;

    public ScalingMatrix() {
        super();
    }

    public String toString() {
        List v1_1;
        StringBuilder v0 = new StringBuilder("ScalingMatrix{ScalingList4x4=");
        Object v2 = null;
        if(this.ScalingList4x4 == null) {
            Object v1 = v2;
        }
        else {
            v1_1 = Arrays.asList(this.ScalingList4x4);
        }

        v0.append(v1_1);
        v0.append("\n");
        v0.append(", ScalingList8x8=");
        if(this.ScalingList8x8 == null) {
        }
        else {
            List v2_1 = Arrays.asList(this.ScalingList8x8);
        }

        v0.append(v2);
        v0.append("\n");
        v0.append('}');
        return v0.toString();
    }
}

