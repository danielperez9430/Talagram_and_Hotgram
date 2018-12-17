package com.googlecode.mp4parser.authoring.tracks.h265;

import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BitReaderBuffer;
import java.io.PrintStream;

public class SEIMessage {
    public SEIMessage(BitReaderBuffer arg9) {
        int v1;
        super();
        int v0;
        for(v0 = 0; true; v0 += 255) {
            v1 = 8;
            long v4 = 255;
            if((((long)arg9.readBits(v1))) != v4) {
                break;
            }
        }

        int v2 = arg9.readBits(v1) + v0;
        do {
        }
        while((((long)arg9.readBits(v1))) == v4);

        arg9.readBits(v1);
        PrintStream v9 = System.err;
        StringBuilder v0_1 = new StringBuilder("payloadType ");
        v0_1.append(v2);
        v9.println(v0_1.toString());
    }
}

