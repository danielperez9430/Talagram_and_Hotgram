package com.google.android.exoplayer2.metadata;

import android.os.Handler$Callback;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.exoplayer2.BaseRenderer;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class MetadataRenderer extends BaseRenderer implements Handler$Callback {
    @Deprecated public interface Output extends MetadataOutput {
    }

    private static final int MAX_PENDING_METADATA_COUNT = 5;
    private static final int MSG_INVOKE_RENDERER;
    private final MetadataInputBuffer buffer;
    private MetadataDecoder decoder;
    private final MetadataDecoderFactory decoderFactory;
    private final FormatHolder formatHolder;
    private boolean inputStreamEnded;
    private final MetadataOutput output;
    private final Handler outputHandler;
    private final Metadata[] pendingMetadata;
    private int pendingMetadataCount;
    private int pendingMetadataIndex;
    private final long[] pendingMetadataTimestamps;

    public MetadataRenderer(MetadataOutput arg2, Looper arg3) {
        this(arg2, arg3, MetadataDecoderFactory.DEFAULT);
    }

    public MetadataRenderer(MetadataOutput arg2, Looper arg3, MetadataDecoderFactory arg4) {
        super(4);
        this.output = Assertions.checkNotNull(arg2);
        Handler v2 = arg3 == null ? null : Util.createHandler(arg3, ((Handler$Callback)this));
        this.outputHandler = v2;
        this.decoderFactory = Assertions.checkNotNull(arg4);
        this.formatHolder = new FormatHolder();
        this.buffer = new MetadataInputBuffer();
        this.pendingMetadata = new Metadata[5];
        this.pendingMetadataTimestamps = new long[5];
    }

    private void flushPendingMetadata() {
        Arrays.fill(this.pendingMetadata, null);
        this.pendingMetadataIndex = 0;
        this.pendingMetadataCount = 0;
    }

    public boolean handleMessage(Message arg2) {
        if(arg2.what == 0) {
            this.invokeRendererInternal(arg2.obj);
            return 1;
        }

        throw new IllegalStateException();
    }

    private void invokeRenderer(Metadata arg3) {
        if(this.outputHandler != null) {
            this.outputHandler.obtainMessage(0, arg3).sendToTarget();
        }
        else {
            this.invokeRendererInternal(arg3);
        }
    }

    private void invokeRendererInternal(Metadata arg2) {
        this.output.onMetadata(arg2);
    }

    public boolean isEnded() {
        return this.inputStreamEnded;
    }

    public boolean isReady() {
        return 1;
    }

    protected void onDisabled() {
        this.flushPendingMetadata();
        this.decoder = null;
    }

    protected void onPositionReset(long arg1, boolean arg3) {
        this.flushPendingMetadata();
        this.inputStreamEnded = false;
    }

    protected void onStreamChanged(Format[] arg1, long arg2) {
        this.decoder = this.decoderFactory.createDecoder(arg1[0]);
    }

    public void render(long arg5, long arg7) {
        int v8 = 5;
        if(!this.inputStreamEnded && this.pendingMetadataCount < v8) {
            this.buffer.clear();
            if(this.readSource(this.formatHolder, this.buffer, false) == -4) {
                if(this.buffer.isEndOfStream()) {
                    this.inputStreamEnded = true;
                }
                else if(this.buffer.isDecodeOnly()) {
                }
                else {
                    this.buffer.subsampleOffsetUs = this.formatHolder.format.subsampleOffsetUs;
                    this.buffer.flip();
                    int v7 = (this.pendingMetadataIndex + this.pendingMetadataCount) % v8;
                    this.pendingMetadata[v7] = this.decoder.decode(this.buffer);
                    this.pendingMetadataTimestamps[v7] = this.buffer.timeUs;
                    ++this.pendingMetadataCount;
                }
            }
        }

        if(this.pendingMetadataCount > 0 && this.pendingMetadataTimestamps[this.pendingMetadataIndex] <= arg5) {
            this.invokeRenderer(this.pendingMetadata[this.pendingMetadataIndex]);
            this.pendingMetadata[this.pendingMetadataIndex] = null;
            this.pendingMetadataIndex = (this.pendingMetadataIndex + 1) % v8;
            --this.pendingMetadataCount;
        }
    }

    public int supportsFormat(Format arg2) {
        if(this.decoderFactory.supportsFormat(arg2)) {
            int v2 = MetadataRenderer.supportsFormatDrm(null, arg2.drmInitData) ? 4 : 2;
            return v2;
        }

        return 0;
    }
}

