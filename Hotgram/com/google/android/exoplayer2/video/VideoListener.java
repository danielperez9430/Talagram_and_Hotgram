package com.google.android.exoplayer2.video;

import android.graphics.SurfaceTexture;

public interface VideoListener {
    void onRenderedFirstFrame();

    boolean onSurfaceDestroyed(SurfaceTexture arg1);

    void onSurfaceSizeChanged(int arg1, int arg2);

    void onSurfaceTextureUpdated(SurfaceTexture arg1);

    void onVideoSizeChanged(int arg1, int arg2, int arg3, float arg4);
}

