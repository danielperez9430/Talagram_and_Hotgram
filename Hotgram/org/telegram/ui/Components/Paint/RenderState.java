package org.telegram.ui.Components.Paint;

import android.graphics.PointF;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class RenderState {
    private static final int DEFAULT_STATE_SIZE = 256;
    private int allocatedCount;
    public float alpha;
    public float angle;
    public float baseWeight;
    private ByteBuffer buffer;
    private int count;
    public double remainder;
    public float scale;
    public float spacing;

    public RenderState() {
        super();
    }

    public boolean addPoint(PointF arg4, float arg5, float arg6, float arg7, int arg8) {
        int v0 = -1;
        if(arg8 != v0 && arg8 >= this.allocatedCount || this.buffer.position() == this.buffer.limit()) {
            this.resizeBuffer();
            return 0;
        }

        if(arg8 != v0) {
            this.buffer.position(arg8 * 5 * 4);
        }

        this.buffer.putFloat(arg4.x);
        this.buffer.putFloat(arg4.y);
        this.buffer.putFloat(arg5);
        this.buffer.putFloat(arg6);
        this.buffer.putFloat(arg7);
        return 1;
    }

    public void appendValuesCount(int arg2) {
        int v0 = this.count + arg2;
        if(v0 > this.allocatedCount || this.buffer == null) {
            this.resizeBuffer();
        }

        this.count = v0;
    }

    public int getCount() {
        return this.count;
    }

    public void prepare() {
        this.count = 0;
        if(this.buffer != null) {
            return;
        }

        this.allocatedCount = 256;
        this.buffer = ByteBuffer.allocateDirect(this.allocatedCount * 5 * 4);
        this.buffer.order(ByteOrder.nativeOrder());
        this.buffer.position(0);
    }

    public float read() {
        return this.buffer.getFloat();
    }

    public void reset() {
        this.count = 0;
        this.remainder = 0;
        if(this.buffer != null) {
            this.buffer.position(0);
        }
    }

    public void resizeBuffer() {
        if(this.buffer != null) {
            this.buffer = null;
        }

        this.allocatedCount = Math.max(this.allocatedCount * 2, 256);
        this.buffer = ByteBuffer.allocateDirect(this.allocatedCount * 5 * 4);
        this.buffer.order(ByteOrder.nativeOrder());
        this.buffer.position(0);
    }

    public void setPosition(int arg2) {
        if(this.buffer != null && arg2 >= 0) {
            if(arg2 >= this.allocatedCount) {
            }
            else {
                this.buffer.position(arg2 * 5 * 4);
            }
        }
    }
}

