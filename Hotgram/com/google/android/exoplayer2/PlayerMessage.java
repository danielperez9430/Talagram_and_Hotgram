package com.google.android.exoplayer2;

import android.os.Handler;
import com.google.android.exoplayer2.util.Assertions;

public final class PlayerMessage {
    public interface Sender {
        void sendMessage(PlayerMessage arg1);
    }

    public interface Target {
        void handleMessage(int arg1, Object arg2);
    }

    private boolean deleteAfterDelivery;
    private Handler handler;
    private boolean isCanceled;
    private boolean isDelivered;
    private boolean isProcessed;
    private boolean isSent;
    private Object payload;
    private long positionMs;
    private final Sender sender;
    private final Target target;
    private final Timeline timeline;
    private int type;
    private int windowIndex;

    public PlayerMessage(Sender arg1, Target arg2, Timeline arg3, int arg4, Handler arg5) {
        super();
        this.sender = arg1;
        this.target = arg2;
        this.timeline = arg3;
        this.handler = arg5;
        this.windowIndex = arg4;
        this.positionMs = -9223372036854775807L;
        this.deleteAfterDelivery = true;
    }

    public boolean blockUntilDelivered() {
        boolean v0_1;
        __monitor_enter(this);
        try {
            Assertions.checkState(this.isSent);
            v0_1 = this.handler.getLooper().getThread() != Thread.currentThread() ? true : false;
            Assertions.checkState(v0_1);
            while(!this.isProcessed) {
                this.wait();
            }

            v0_1 = this.isDelivered;
        }
        catch(Throwable v0) {
            goto label_20;
        }

        __monitor_exit(this);
        return v0_1;
    label_20:
        __monitor_exit(this);
        throw v0;
    }

    public PlayerMessage cancel() {
        __monitor_enter(this);
        try {
            Assertions.checkState(this.isSent);
            this.isCanceled = true;
            this.markAsProcessed(false);
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return this;
    }

    public boolean getDeleteAfterDelivery() {
        return this.deleteAfterDelivery;
    }

    public Handler getHandler() {
        return this.handler;
    }

    public Object getPayload() {
        return this.payload;
    }

    public long getPositionMs() {
        return this.positionMs;
    }

    public Target getTarget() {
        return this.target;
    }

    public Timeline getTimeline() {
        return this.timeline;
    }

    public int getType() {
        return this.type;
    }

    public int getWindowIndex() {
        return this.windowIndex;
    }

    public boolean isCanceled() {
        boolean v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.isCanceled;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    public void markAsProcessed(boolean arg2) {
        __monitor_enter(this);
        try {
            this.isDelivered |= ((int)arg2);
            this.isProcessed = true;
            this.notifyAll();
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    public PlayerMessage send() {
        Assertions.checkState(this.isSent ^ 1);
        if(this.positionMs == -9223372036854775807L) {
            Assertions.checkArgument(this.deleteAfterDelivery);
        }

        this.isSent = true;
        this.sender.sendMessage(this);
        return this;
    }

    public PlayerMessage setDeleteAfterDelivery(boolean arg2) {
        Assertions.checkState(this.isSent ^ 1);
        this.deleteAfterDelivery = arg2;
        return this;
    }

    public PlayerMessage setHandler(Handler arg2) {
        Assertions.checkState(this.isSent ^ 1);
        this.handler = arg2;
        return this;
    }

    public PlayerMessage setPayload(Object arg2) {
        Assertions.checkState(this.isSent ^ 1);
        this.payload = arg2;
        return this;
    }

    public PlayerMessage setPosition(int arg5, long arg6) {
        boolean v1 = true;
        Assertions.checkState(this.isSent ^ 1);
        if(arg6 != -9223372036854775807L) {
        }
        else {
            v1 = false;
        }

        Assertions.checkArgument(v1);
        if(arg5 >= 0 && ((this.timeline.isEmpty()) || arg5 < this.timeline.getWindowCount())) {
            this.windowIndex = arg5;
            this.positionMs = arg6;
            return this;
        }

        throw new IllegalSeekPositionException(this.timeline, arg5, arg6);
    }

    public PlayerMessage setPosition(long arg2) {
        Assertions.checkState(this.isSent ^ 1);
        this.positionMs = arg2;
        return this;
    }

    public PlayerMessage setType(int arg2) {
        Assertions.checkState(this.isSent ^ 1);
        this.type = arg2;
        return this;
    }
}

