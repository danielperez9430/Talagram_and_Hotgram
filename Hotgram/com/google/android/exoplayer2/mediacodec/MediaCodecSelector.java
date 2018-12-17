package com.google.android.exoplayer2.mediacodec;

import com.google.android.exoplayer2.Format;
import java.util.Collections;
import java.util.List;

public interface MediaCodecSelector {
    final class com.google.android.exoplayer2.mediacodec.MediaCodecSelector$1 implements MediaCodecSelector {
        com.google.android.exoplayer2.mediacodec.MediaCodecSelector$1() {
            super();
        }

        public List getDecoderInfos(Format arg1, boolean arg2) {
            List v1 = MediaCodecUtil.getDecoderInfos(arg1.sampleMimeType, arg2);
            return v1.isEmpty() ? Collections.emptyList() : Collections.singletonList(v1.get(0));
        }

        public MediaCodecInfo getPassthroughDecoderInfo() {
            return MediaCodecUtil.getPassthroughDecoderInfo();
        }
    }

    final class com.google.android.exoplayer2.mediacodec.MediaCodecSelector$2 implements MediaCodecSelector {
        com.google.android.exoplayer2.mediacodec.MediaCodecSelector$2() {
            super();
        }

        public List getDecoderInfos(Format arg1, boolean arg2) {
            return MediaCodecUtil.getDecoderInfos(arg1.sampleMimeType, arg2);
        }

        public MediaCodecInfo getPassthroughDecoderInfo() {
            return MediaCodecUtil.getPassthroughDecoderInfo();
        }
    }

    public static final MediaCodecSelector DEFAULT;
    public static final MediaCodecSelector DEFAULT_WITH_FALLBACK;

    static {
        MediaCodecSelector.DEFAULT = new com.google.android.exoplayer2.mediacodec.MediaCodecSelector$1();
        MediaCodecSelector.DEFAULT_WITH_FALLBACK = new com.google.android.exoplayer2.mediacodec.MediaCodecSelector$2();
    }

    List getDecoderInfos(Format arg1, boolean arg2);

    MediaCodecInfo getPassthroughDecoderInfo();
}

