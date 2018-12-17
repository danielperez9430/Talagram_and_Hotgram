package com.google.android.exoplayer2.text;

import android.os.Handler$Callback;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.exoplayer2.BaseRenderer;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.List;

public final class TextRenderer extends BaseRenderer implements Handler$Callback {
    @Deprecated public interface Output extends TextOutput {
    }

    @Retention(value=RetentionPolicy.SOURCE) @interface ReplacementState {
    }

    private static final int MSG_UPDATE_OUTPUT = 0;
    private static final int REPLACEMENT_STATE_NONE = 0;
    private static final int REPLACEMENT_STATE_SIGNAL_END_OF_STREAM = 1;
    private static final int REPLACEMENT_STATE_WAIT_END_OF_STREAM = 2;
    private SubtitleDecoder decoder;
    private final SubtitleDecoderFactory decoderFactory;
    private int decoderReplacementState;
    private final FormatHolder formatHolder;
    private boolean inputStreamEnded;
    private SubtitleInputBuffer nextInputBuffer;
    private SubtitleOutputBuffer nextSubtitle;
    private int nextSubtitleEventIndex;
    private final TextOutput output;
    private final Handler outputHandler;
    private boolean outputStreamEnded;
    private Format streamFormat;
    private SubtitleOutputBuffer subtitle;

    public TextRenderer(TextOutput arg2, Looper arg3) {
        this(arg2, arg3, SubtitleDecoderFactory.DEFAULT);
    }

    public TextRenderer(TextOutput arg2, Looper arg3, SubtitleDecoderFactory arg4) {
        super(3);
        this.output = Assertions.checkNotNull(arg2);
        Handler v2 = arg3 == null ? null : Util.createHandler(arg3, ((Handler$Callback)this));
        this.outputHandler = v2;
        this.decoderFactory = arg4;
        this.formatHolder = new FormatHolder();
    }

    private void clearOutput() {
        this.updateOutput(Collections.emptyList());
    }

    private long getNextEventTime() {
        long v0 = this.nextSubtitleEventIndex == -1 || this.nextSubtitleEventIndex >= this.subtitle.getEventTimeCount() ? 9223372036854775807L : this.subtitle.getEventTime(this.nextSubtitleEventIndex);
        return v0;
    }

    public boolean handleMessage(Message arg2) {
        if(arg2.what == 0) {
            this.invokeUpdateOutputInternal(arg2.obj);
            return 1;
        }

        throw new IllegalStateException();
    }

    private void invokeUpdateOutputInternal(List arg2) {
        this.output.onCues(arg2);
    }

    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    public boolean isReady() {
        return 1;
    }

    protected void onDisabled() {
        this.streamFormat = null;
        this.clearOutput();
        this.releaseDecoder();
    }

    protected void onPositionReset(long arg1, boolean arg3) {
        this.clearOutput();
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        if(this.decoderReplacementState != 0) {
            this.replaceDecoder();
        }
        else {
            this.releaseBuffers();
            this.decoder.flush();
        }
    }

    protected void onStreamChanged(Format[] arg1, long arg2) {
        this.streamFormat = arg1[0];
        if(this.decoder != null) {
            this.decoderReplacementState = 1;
        }
        else {
            this.decoder = this.decoderFactory.createDecoder(this.streamFormat);
        }
    }

    private void releaseBuffers() {
        SubtitleInputBuffer v0 = null;
        this.nextInputBuffer = v0;
        this.nextSubtitleEventIndex = -1;
        if(this.subtitle != null) {
            this.subtitle.release();
            this.subtitle = ((SubtitleOutputBuffer)v0);
        }

        if(this.nextSubtitle != null) {
            this.nextSubtitle.release();
            this.nextSubtitle = ((SubtitleOutputBuffer)v0);
        }
    }

    private void releaseDecoder() {
        this.releaseBuffers();
        this.decoder.release();
        this.decoder = null;
        this.decoderReplacementState = 0;
    }

    public void render(long arg9, long arg11) {
        if(this.outputStreamEnded) {
            return;
        }

        if(this.nextSubtitle == null) {
            this.decoder.setPositionUs(arg9);
            try {
                this.nextSubtitle = this.decoder.dequeueOutputBuffer();
            }
            catch(SubtitleDecoderException v9) {
                throw ExoPlaybackException.createForRenderer(((Exception)v9), this.getIndex());
            }
        }

        int v12 = 2;
        if(this.getState() != v12) {
            return;
        }

        if(this.subtitle != null) {
            long v2 = this.getNextEventTime();
            int v11;
            for(v11 = 0; v2 <= arg9; v11 = 1) {
                ++this.nextSubtitleEventIndex;
                v2 = this.getNextEventTime();
            }
        }
        else {
            v11 = 0;
        }

        SubtitleOutputBuffer v3 = null;
        if(this.nextSubtitle != null) {
            if(this.nextSubtitle.isEndOfStream()) {
                if(v11 == 0 && this.getNextEventTime() == 9223372036854775807L) {
                    if(this.decoderReplacementState == v12) {
                        this.replaceDecoder();
                    }
                    else {
                        this.releaseBuffers();
                        this.outputStreamEnded = true;
                    }
                }
            }
            else if(this.nextSubtitle.timeUs <= arg9) {
                if(this.subtitle != null) {
                    this.subtitle.release();
                }

                this.subtitle = this.nextSubtitle;
                this.nextSubtitle = v3;
                this.nextSubtitleEventIndex = this.subtitle.getNextEventTimeIndex(arg9);
                v11 = 1;
            }
        }

        if(v11 != 0) {
            this.updateOutput(this.subtitle.getCues(arg9));
        }

        if(this.decoderReplacementState == v12) {
            return;
        }

        try {
            do {
            label_71:
                if(!this.inputStreamEnded) {
                    if(this.nextInputBuffer == null) {
                        this.nextInputBuffer = this.decoder.dequeueInputBuffer();
                        if(this.nextInputBuffer == null) {
                            return;
                        }
                    }

                    if(this.decoderReplacementState == 1) {
                        this.nextInputBuffer.setFlags(4);
                        this.decoder.queueInputBuffer(this.nextInputBuffer);
                        this.nextInputBuffer = ((SubtitleInputBuffer)v3);
                        this.decoderReplacementState = v12;
                        return;
                    }

                    int v9_1 = this.readSource(this.formatHolder, this.nextInputBuffer, false);
                    if(v9_1 != -4) {
                        break;
                    }

                    if(this.nextInputBuffer.isEndOfStream()) {
                        this.inputStreamEnded = true;
                    }
                    else {
                        this.nextInputBuffer.subsampleOffsetUs = this.formatHolder.format.subsampleOffsetUs;
                        this.nextInputBuffer.flip();
                    }

                    this.decoder.queueInputBuffer(this.nextInputBuffer);
                    this.nextInputBuffer = ((SubtitleInputBuffer)v3);
                    continue;
                }

                return;
            }
            while(true);
        }
        catch(SubtitleDecoderException v9) {
            throw ExoPlaybackException.createForRenderer(((Exception)v9), this.getIndex());
        }

        if(v9_1 != -3) {
            goto label_71;
        }
    }

    private void replaceDecoder() {
        this.releaseDecoder();
        this.decoder = this.decoderFactory.createDecoder(this.streamFormat);
    }

    public int supportsFormat(Format arg2) {
        if(this.decoderFactory.supportsFormat(arg2)) {
            int v2 = TextRenderer.supportsFormatDrm(null, arg2.drmInitData) ? 4 : 2;
            return v2;
        }

        if(MimeTypes.isText(arg2.sampleMimeType)) {
            return 1;
        }

        return 0;
    }

    private void updateOutput(List arg3) {
        if(this.outputHandler != null) {
            this.outputHandler.obtainMessage(0, arg3).sendToTarget();
        }
        else {
            this.invokeUpdateOutputInternal(arg3);
        }
    }
}

