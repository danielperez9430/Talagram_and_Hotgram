package com.google.android.exoplayer2.upstream;

public interface Allocator {
    Allocation allocate();

    int getIndividualAllocationLength();

    int getTotalBytesAllocated();

    void release(Allocation arg1);

    void release(Allocation[] arg1);

    void trim();
}

