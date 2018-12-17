package com.google.android.exoplayer2.mediacodec;

import android.annotation.TargetApi;
import android.media.MediaFormat;
import com.google.android.exoplayer2.video.ColorInfo;
import java.nio.ByteBuffer;
import java.util.List;

@TargetApi(value=16) public final class MediaFormatUtil {
    private MediaFormatUtil() {
        super();
    }

    public static void maybeSetByteBuffer(MediaFormat arg0, String arg1, byte[] arg2) {
        if(arg2 != null) {
            arg0.setByteBuffer(arg1, ByteBuffer.wrap(arg2));
        }
    }

    public static void maybeSetColorInfo(MediaFormat arg2, ColorInfo arg3) {
        if(arg3 != null) {
            MediaFormatUtil.maybeSetInteger(arg2, "color-transfer", arg3.colorTransfer);
            MediaFormatUtil.maybeSetInteger(arg2, "color-standard", arg3.colorSpace);
            MediaFormatUtil.maybeSetInteger(arg2, "color-range", arg3.colorRange);
            MediaFormatUtil.maybeSetByteBuffer(arg2, "hdr-static-info", arg3.hdrStaticInfo);
        }
    }

    public static void maybeSetFloat(MediaFormat arg1, String arg2, float arg3) {
        if(arg3 != -1f) {
            arg1.setFloat(arg2, arg3);
        }
    }

    public static void maybeSetInteger(MediaFormat arg1, String arg2, int arg3) {
        if(arg3 != -1) {
            arg1.setInteger(arg2, arg3);
        }
    }

    public static void setCsdBuffers(MediaFormat arg3, List arg4) {
        int v0;
        for(v0 = 0; v0 < arg4.size(); ++v0) {
            arg3.setByteBuffer("csd-" + v0, ByteBuffer.wrap(arg4.get(v0)));
        }
    }

    public static void setString(MediaFormat arg0, String arg1, String arg2) {
        arg0.setString(arg1, arg2);
    }
}

