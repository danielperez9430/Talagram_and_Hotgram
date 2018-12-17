package com.google.android.exoplayer2.upstream;

import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class DefaultAllocator implements Allocator {
    private static final int AVAILABLE_EXTRA_CAPACITY = 100;
    private int allocatedCount;
    private Allocation[] availableAllocations;
    private int availableCount;
    private final int individualAllocationSize;
    private final byte[] initialAllocationBlock;
    private final Allocation[] singleAllocationReleaseHolder;
    private int targetBufferSize;
    private final boolean trimOnReset;

    public DefaultAllocator(boolean arg2, int arg3) {
        this(arg2, arg3, 0);
    }

    public DefaultAllocator(boolean arg6, int arg7, int arg8) {
        super();
        int v0 = 0;
        boolean v2 = arg7 > 0 ? true : false;
        Assertions.checkArgument(v2);
        v2 = arg8 >= 0 ? true : false;
        Assertions.checkArgument(v2);
        this.trimOnReset = arg6;
        this.individualAllocationSize = arg7;
        this.availableCount = arg8;
        this.availableAllocations = new Allocation[arg8 + 100];
        if(arg8 > 0) {
            this.initialAllocationBlock = new byte[arg8 * arg7];
            while(v0 < arg8) {
                this.availableAllocations[v0] = new Allocation(this.initialAllocationBlock, v0 * arg7);
                ++v0;
            }
        }
        else {
            this.initialAllocationBlock = null;
        }

        this.singleAllocationReleaseHolder = new Allocation[1];
    }

    public Allocation allocate() {
        Allocation v0_2;
        __monitor_enter(this);
        try {
            ++this.allocatedCount;
            if(this.availableCount > 0) {
                Allocation[] v0_1 = this.availableAllocations;
                int v1 = this.availableCount - 1;
                this.availableCount = v1;
                v0_2 = v0_1[v1];
                this.availableAllocations[this.availableCount] = null;
            }
            else {
                v0_2 = new Allocation(new byte[this.individualAllocationSize], 0);
            }
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_2;
    }

    public int getIndividualAllocationLength() {
        return this.individualAllocationSize;
    }

    public int getTotalBytesAllocated() {
        int v1;
        int v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.allocatedCount;
            v1 = this.individualAllocationSize;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1 * v1;
    }

    public void release(Allocation arg3) {
        __monitor_enter(this);
        try {
            this.singleAllocationReleaseHolder[0] = arg3;
            this.release(this.singleAllocationReleaseHolder);
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
    }

    public void release(Allocation[] arg8) {
        __monitor_enter(this);
        try {
            if(this.availableCount + arg8.length >= this.availableAllocations.length) {
                this.availableAllocations = Arrays.copyOf(this.availableAllocations, Math.max(this.availableAllocations.length * 2, this.availableCount + arg8.length));
            }

            int v0 = arg8.length;
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                Allocation v3 = arg8[v2];
                boolean v4 = v3.data == this.initialAllocationBlock || v3.data.length == this.individualAllocationSize ? true : false;
                Assertions.checkArgument(v4);
                Allocation[] v4_1 = this.availableAllocations;
                int v5 = this.availableCount;
                this.availableCount = v5 + 1;
                v4_1[v5] = v3;
            }

            this.allocatedCount -= arg8.length;
            this.notifyAll();
        }
        catch(Throwable v8) {
            goto label_49;
        }

        __monitor_exit(this);
        return;
    label_49:
        __monitor_exit(this);
        throw v8;
    }

    public void reset() {
        __monitor_enter(this);
        try {
            if(this.trimOnReset) {
                this.setTargetBufferSize(0);
            }
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }

    public void setTargetBufferSize(int arg2) {
        __monitor_enter(this);
        try {
            int v0 = arg2 < this.targetBufferSize ? 1 : 0;
            this.targetBufferSize = arg2;
            if(v0 != 0) {
                this.trim();
            }
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    public void trim() {
        int v0_1;
        int v1;
        __monitor_enter(this);
        try {
            v1 = 0;
            v0_1 = Math.max(0, Util.ceilDivide(this.targetBufferSize, this.individualAllocationSize) - this.allocatedCount);
            if(v0_1 < this.availableCount) {
                goto label_12;
            }
        }
        catch(Throwable v0) {
            goto label_53;
        }

        __monitor_exit(this);
        return;
        try {
        label_12:
            if(this.initialAllocationBlock != null) {
                int v2 = this.availableCount - 1;
                while(v1 <= v2) {
                    Allocation v3 = this.availableAllocations[v1];
                    if(v3.data == this.initialAllocationBlock) {
                        ++v1;
                    }
                    else {
                        Allocation v4 = this.availableAllocations[v2];
                        if(v4.data != this.initialAllocationBlock) {
                            --v2;
                        }
                        else {
                            this.availableAllocations[v1] = v4;
                            this.availableAllocations[v2] = v3;
                            --v2;
                            ++v1;
                        }
                    }
                }

                v0_1 = Math.max(v0_1, v1);
                if(v0_1 >= this.availableCount) {
                    goto label_43;
                }
            }

            goto label_45;
        }
        catch(Throwable v0) {
            goto label_53;
        }

    label_43:
        __monitor_exit(this);
        return;
        try {
        label_45:
            Arrays.fill(this.availableAllocations, v0_1, this.availableCount, null);
            this.availableCount = v0_1;
        }
        catch(Throwable v0) {
        label_53:
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }
}

