package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.SeekParameters;
import java.util.List;

public interface ChunkSource {
    long getAdjustedSeekPositionUs(long arg1, SeekParameters arg2);

    void getNextChunk(long arg1, long arg2, List arg3, ChunkHolder arg4);

    int getPreferredQueueSize(long arg1, List arg2);

    void maybeThrowError();

    void onChunkLoadCompleted(Chunk arg1);

    boolean onChunkLoadError(Chunk arg1, boolean arg2, Exception arg3);
}

