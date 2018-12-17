package com.googlecode.mp4parser.h264.model;

import com.googlecode.mp4parser.h264.read.CAVLCReader;
import com.googlecode.mp4parser.h264.write.CAVLCWriter;

public class ScalingList {
    public int[] scalingList;
    public boolean useDefaultScalingMatrixFlag;

    public ScalingList() {
        super();
    }

    public static ScalingList read(CAVLCReader arg6, int arg7) {
        ScalingList v0 = new ScalingList();
        v0.scalingList = new int[arg7];
        int v2 = 0;
        int v3 = 8;
        int v4 = 8;
        while(v2 < arg7) {
            if(v3 != 0) {
                v3 = (arg6.readSE("deltaScale") + v4 + 256) % 256;
                boolean v5 = v2 != 0 || v3 != 0 ? false : true;
                v0.useDefaultScalingMatrixFlag = v5;
            }

            int[] v5_1 = v0.scalingList;
            if(v3 == 0) {
            }
            else {
                v4 = v3;
            }

            v5_1[v2] = v4;
            v4 = v0.scalingList[v2];
            ++v2;
        }

        return v0;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder("ScalingList{scalingList=");
        v0.append(this.scalingList);
        v0.append(", useDefaultScalingMatrixFlag=");
        v0.append(this.useDefaultScalingMatrixFlag);
        v0.append('}');
        return v0.toString();
    }

    public void write(CAVLCWriter arg4) {
        int v1 = 0;
        if(this.useDefaultScalingMatrixFlag) {
            arg4.writeSE(0, "SPS: ");
            return;
        }

        int v0 = 8;
        while(v1 < this.scalingList.length) {
            arg4.writeSE(this.scalingList[v1] - v0 - 256, "SPS: ");
            v0 = this.scalingList[v1];
            ++v1;
        }
    }
}

