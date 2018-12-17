package com.google.android.exoplayer2.source;

import android.os.Handler;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.PlayerMessage$Target;
import com.google.android.exoplayer2.Timeline$Period;
import com.google.android.exoplayer2.Timeline$Window;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ConcatenatingMediaSource extends CompositeMediaSource implements Target {
    class com.google.android.exoplayer2.source.ConcatenatingMediaSource$1 {
    }

    final class ConcatenatedTimeline extends AbstractConcatenatedTimeline {
        private final HashMap childIndexByUid;
        private final int[] firstPeriodInChildIndices;
        private final int[] firstWindowInChildIndices;
        private final int periodCount;
        private final Timeline[] timelines;
        private final Object[] uids;
        private final int windowCount;

        public ConcatenatedTimeline(Collection arg1, int arg2, int arg3, ShuffleOrder arg4, boolean arg5) {
            super(arg5, arg4);
            this.windowCount = arg2;
            this.periodCount = arg3;
            arg2 = arg1.size();
            this.firstPeriodInChildIndices = new int[arg2];
            this.firstWindowInChildIndices = new int[arg2];
            this.timelines = new Timeline[arg2];
            this.uids = new Object[arg2];
            this.childIndexByUid = new HashMap();
            Iterator v1 = arg1.iterator();
            for(arg2 = 0; v1.hasNext(); ++arg2) {
                Object v3 = v1.next();
                this.timelines[arg2] = ((MediaSourceHolder)v3).timeline;
                this.firstPeriodInChildIndices[arg2] = ((MediaSourceHolder)v3).firstPeriodIndexInChild;
                this.firstWindowInChildIndices[arg2] = ((MediaSourceHolder)v3).firstWindowIndexInChild;
                this.uids[arg2] = ((MediaSourceHolder)v3).uid;
                this.childIndexByUid.put(this.uids[arg2], Integer.valueOf(arg2));
            }
        }

        protected int getChildIndexByChildUid(Object arg2) {
            arg2 = this.childIndexByUid.get(arg2);
            int v2 = arg2 == null ? -1 : ((Integer)arg2).intValue();
            return v2;
        }

        protected int getChildIndexByPeriodIndex(int arg3) {
            return Util.binarySearchFloor(this.firstPeriodInChildIndices, arg3 + 1, false, false);
        }

        protected int getChildIndexByWindowIndex(int arg3) {
            return Util.binarySearchFloor(this.firstWindowInChildIndices, arg3 + 1, false, false);
        }

        protected Object getChildUidByChildIndex(int arg2) {
            return this.uids[arg2];
        }

        protected int getFirstPeriodIndexByChildIndex(int arg2) {
            return this.firstPeriodInChildIndices[arg2];
        }

        protected int getFirstWindowIndexByChildIndex(int arg2) {
            return this.firstWindowInChildIndices[arg2];
        }

        public int getPeriodCount() {
            return this.periodCount;
        }

        protected Timeline getTimelineByChildIndex(int arg2) {
            return this.timelines[arg2];
        }

        public int getWindowCount() {
            return this.windowCount;
        }
    }

    final class DeferredTimeline extends ForwardingTimeline {
        private static final Object DUMMY_ID;
        private static final DummyTimeline dummyTimeline;
        private final Object replacedId;

        static {
            DeferredTimeline.DUMMY_ID = new Object();
            DeferredTimeline.dummyTimeline = new DummyTimeline(null);
        }

        public DeferredTimeline() {
            this(DeferredTimeline.dummyTimeline, DeferredTimeline.DUMMY_ID);
        }

        private DeferredTimeline(Timeline arg1, Object arg2) {
            super(arg1);
            this.replacedId = arg2;
        }

        static Object access$100() {
            return DeferredTimeline.DUMMY_ID;
        }

        public DeferredTimeline cloneWithNewTimeline(Timeline arg4) {
            Object v1 = this.replacedId != DeferredTimeline.DUMMY_ID || arg4.getPeriodCount() <= 0 ? this.replacedId : arg4.getUidOfPeriod(0);
            return new DeferredTimeline(arg4, v1);
        }

        public int getIndexOfPeriod(Object arg3) {
            Timeline v0 = this.timeline;
            if(DeferredTimeline.DUMMY_ID.equals(arg3)) {
                arg3 = this.replacedId;
            }

            return v0.getIndexOfPeriod(arg3);
        }

        public Period getPeriod(int arg2, Period arg3, boolean arg4) {
            this.timeline.getPeriod(arg2, arg3, arg4);
            if(Util.areEqual(arg3.uid, this.replacedId)) {
                arg3.uid = DeferredTimeline.DUMMY_ID;
            }

            return arg3;
        }

        public Timeline getTimeline() {
            return this.timeline;
        }

        public Object getUidOfPeriod(int arg2) {
            Object v2 = this.timeline.getUidOfPeriod(arg2);
            if(Util.areEqual(v2, this.replacedId)) {
                v2 = DeferredTimeline.DUMMY_ID;
            }

            return v2;
        }
    }

    final class DummyTimeline extends Timeline {
        private DummyTimeline() {
            super();
        }

        DummyTimeline(com.google.android.exoplayer2.source.ConcatenatingMediaSource$1 arg1) {
            this();
        }

        public int getIndexOfPeriod(Object arg2) {
            int v2 = arg2 == DeferredTimeline.access$100() ? 0 : -1;
            return v2;
        }

        public Period getPeriod(int arg9, Period arg10, boolean arg11) {
            return arg10.set(Integer.valueOf(0), DeferredTimeline.access$100(), 0, -9223372036854775807L, 0);
        }

        public int getPeriodCount() {
            return 1;
        }

        public Object getUidOfPeriod(int arg1) {
            return DeferredTimeline.access$100();
        }

        public Window getWindow(int arg17, Window arg18, boolean arg19, long arg20) {
            return arg18.set(null, -9223372036854775807L, -9223372036854775807L, false, true, 0, -9223372036854775807L, 0, 0, 0);
        }

        public int getWindowCount() {
            return 1;
        }
    }

    final class MediaSourceHolder implements Comparable {
        public List activeMediaPeriods;
        public int childIndex;
        public int firstPeriodIndexInChild;
        public int firstWindowIndexInChild;
        public boolean hasStartedPreparing;
        public boolean isPrepared;
        public boolean isRemoved;
        public final MediaSource mediaSource;
        public DeferredTimeline timeline;
        public final Object uid;

        public MediaSourceHolder(MediaSource arg1) {
            super();
            this.mediaSource = arg1;
            this.timeline = new DeferredTimeline();
            this.activeMediaPeriods = new ArrayList();
            this.uid = new Object();
        }

        public int compareTo(MediaSourceHolder arg2) {
            return this.firstPeriodIndexInChild - arg2.firstPeriodIndexInChild;
        }

        public int compareTo(Object arg1) {
            return this.compareTo(((MediaSourceHolder)arg1));
        }

        public void reset(int arg1, int arg2, int arg3) {
            this.childIndex = arg1;
            this.firstWindowIndexInChild = arg2;
            this.firstPeriodIndexInChild = arg3;
            this.hasStartedPreparing = false;
            this.isPrepared = false;
            this.isRemoved = false;
            this.activeMediaPeriods.clear();
        }
    }

    final class MessageData {
        public final Runnable actionOnCompletion;
        public final Object customData;
        public final int index;

        public MessageData(int arg1, Object arg2, Runnable arg3) {
            super();
            this.index = arg1;
            this.actionOnCompletion = arg3;
            this.customData = arg2;
        }
    }

    private static final int MSG_ADD = 0;
    private static final int MSG_ADD_MULTIPLE = 1;
    private static final int MSG_CLEAR = 4;
    private static final int MSG_MOVE = 3;
    private static final int MSG_NOTIFY_LISTENER = 5;
    private static final int MSG_ON_COMPLETION = 6;
    private static final int MSG_REMOVE = 2;
    private final boolean isAtomic;
    private boolean listenerNotificationScheduled;
    private final Map mediaSourceByMediaPeriod;
    private final List mediaSourceHolders;
    private final List mediaSourcesPublic;
    private final List pendingOnCompletionActions;
    private int periodCount;
    private ExoPlayer player;
    private Handler playerApplicationHandler;
    private final MediaSourceHolder query;
    private ShuffleOrder shuffleOrder;
    private final boolean useLazyPreparation;
    private final Window window;
    private int windowCount;

    public ConcatenatingMediaSource(boolean arg2, ShuffleOrder arg3, MediaSource[] arg4) {
        this(arg2, false, arg3, arg4);
    }

    public ConcatenatingMediaSource(boolean arg4, boolean arg5, ShuffleOrder arg6, MediaSource[] arg7) {
        super();
        int v0 = arg7.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Assertions.checkNotNull(arg7[v1]);
        }

        if(arg6.getLength() > 0) {
            arg6 = arg6.cloneAndClear();
        }

        this.shuffleOrder = arg6;
        this.mediaSourceByMediaPeriod = new IdentityHashMap();
        this.mediaSourcesPublic = new ArrayList();
        this.mediaSourceHolders = new ArrayList();
        this.pendingOnCompletionActions = new ArrayList();
        this.query = new MediaSourceHolder(null);
        this.isAtomic = arg4;
        this.useLazyPreparation = arg5;
        this.window = new Window();
        this.addMediaSources(Arrays.asList(((Object[])arg7)));
    }

    public ConcatenatingMediaSource(boolean arg3, MediaSource[] arg4) {
        this(arg3, new DefaultShuffleOrder(0), arg4);
    }

    public ConcatenatingMediaSource(MediaSource[] arg2) {
        this(false, arg2);
    }

    public final void addMediaSource(int arg2, MediaSource arg3) {
        __monitor_enter(this);
        Runnable v0 = null;
        try {
            this.addMediaSource(arg2, arg3, v0);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    public final void addMediaSource(int arg3, MediaSource arg4, Runnable arg5) {
        __monitor_enter(this);
        try {
            Assertions.checkNotNull(arg4);
            MediaSourceHolder v0 = new MediaSourceHolder(arg4);
            this.mediaSourcesPublic.add(arg3, v0);
            if(this.player != null) {
                this.player.createMessage(((Target)this)).setType(0).setPayload(new MessageData(arg3, v0, arg5)).send();
            }
            else if(arg5 != null) {
                arg5.run();
            }
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
    }

    public final void addMediaSource(MediaSource arg3) {
        __monitor_enter(this);
        try {
            this.addMediaSource(this.mediaSourcesPublic.size(), arg3, null);
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
    }

    public final void addMediaSource(MediaSource arg2, Runnable arg3) {
        __monitor_enter(this);
        try {
            this.addMediaSource(this.mediaSourcesPublic.size(), arg2, arg3);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    private void addMediaSourceInternal(int arg4, MediaSourceHolder arg5) {
        if(arg4 > 0) {
            Object v0 = this.mediaSourceHolders.get(arg4 - 1);
            arg5.reset(arg4, ((MediaSourceHolder)v0).firstWindowIndexInChild + ((MediaSourceHolder)v0).timeline.getWindowCount(), ((MediaSourceHolder)v0).firstPeriodIndexInChild + ((MediaSourceHolder)v0).timeline.getPeriodCount());
        }
        else {
            arg5.reset(arg4, 0, 0);
        }

        this.correctOffsets(arg4, 1, arg5.timeline.getWindowCount(), arg5.timeline.getPeriodCount());
        this.mediaSourceHolders.add(arg4, arg5);
        if(!this.useLazyPreparation) {
            arg5.hasStartedPreparing = true;
            this.prepareChildSource(arg5, arg5.mediaSource);
        }
    }

    public final void addMediaSources(Collection arg3) {
        __monitor_enter(this);
        try {
            this.addMediaSources(this.mediaSourcesPublic.size(), arg3, null);
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
    }

    public final void addMediaSources(int arg2, Collection arg3) {
        __monitor_enter(this);
        Runnable v0 = null;
        try {
            this.addMediaSources(arg2, arg3, v0);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    public final void addMediaSources(int arg5, Collection arg6, Runnable arg7) {
        __monitor_enter(this);
        try {
            Iterator v0 = arg6.iterator();
            while(v0.hasNext()) {
                Assertions.checkNotNull(v0.next());
            }

            ArrayList v0_1 = new ArrayList(arg6.size());
            Iterator v1 = arg6.iterator();
            while(v1.hasNext()) {
                ((List)v0_1).add(new MediaSourceHolder(v1.next()));
            }

            this.mediaSourcesPublic.addAll(arg5, ((Collection)v0_1));
            if(this.player != null && !arg6.isEmpty()) {
                this.player.createMessage(((Target)this)).setType(1).setPayload(new MessageData(arg5, v0_1, arg7)).send();
            }
            else if(arg7 != null) {
                arg7.run();
            }
        }
        catch(Throwable v5) {
            goto label_38;
        }

        __monitor_exit(this);
        return;
    label_38:
        __monitor_exit(this);
        throw v5;
    }

    public final void addMediaSources(Collection arg2, Runnable arg3) {
        __monitor_enter(this);
        try {
            this.addMediaSources(this.mediaSourcesPublic.size(), arg2, arg3);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    private void addMediaSourcesInternal(int arg3, Collection arg4) {
        Iterator v4 = arg4.iterator();
        while(v4.hasNext()) {
            this.addMediaSourceInternal(arg3, v4.next());
            ++arg3;
        }
    }

    public final void clear() {
        __monitor_enter(this);
        Runnable v0 = null;
        try {
            this.clear(v0);
        }
        catch(Throwable v0_1) {
            __monitor_exit(this);
            throw v0_1;
        }

        __monitor_exit(this);
    }

    public final void clear(Runnable arg3) {
        __monitor_enter(this);
        try {
            this.mediaSourcesPublic.clear();
            if(this.player != null) {
                this.player.createMessage(((Target)this)).setType(4).setPayload(arg3).send();
            }
            else if(arg3 != null) {
                arg3.run();
            }
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
    }

    private void clearInternal() {
        int v0;
        for(v0 = this.mediaSourceHolders.size() - 1; v0 >= 0; --v0) {
            this.removeMediaSourceInternal(v0);
        }
    }

    private void correctOffsets(int arg3, int arg4, int arg5, int arg6) {
        this.windowCount += arg5;
        this.periodCount += arg6;
        while(arg3 < this.mediaSourceHolders.size()) {
            Object v0 = this.mediaSourceHolders.get(arg3);
            ((MediaSourceHolder)v0).childIndex += arg4;
            v0 = this.mediaSourceHolders.get(arg3);
            ((MediaSourceHolder)v0).firstWindowIndexInChild += arg5;
            v0 = this.mediaSourceHolders.get(arg3);
            ((MediaSourceHolder)v0).firstPeriodIndexInChild += arg6;
            ++arg3;
        }
    }

    public final MediaPeriod createPeriod(MediaPeriodId arg4, Allocator arg5) {
        Object v0 = this.mediaSourceHolders.get(this.findMediaSourceHolderByPeriodIndex(arg4.periodIndex));
        DeferredMediaPeriod v1 = new DeferredMediaPeriod(((MediaSourceHolder)v0).mediaSource, arg4, arg5);
        this.mediaSourceByMediaPeriod.put(v1, v0);
        ((MediaSourceHolder)v0).activeMediaPeriods.add(v1);
        if(!((MediaSourceHolder)v0).hasStartedPreparing) {
            ((MediaSourceHolder)v0).hasStartedPreparing = true;
            this.prepareChildSource(v0, ((MediaSourceHolder)v0).mediaSource);
        }
        else if(((MediaSourceHolder)v0).isPrepared) {
            v1.createPeriod(arg4.copyWithPeriodIndex(arg4.periodIndex - ((MediaSourceHolder)v0).firstPeriodIndexInChild));
        }

        return ((MediaPeriod)v1);
    }

    private int findMediaSourceHolderByPeriodIndex(int arg4) {
        this.query.firstPeriodIndexInChild = arg4;
        int v0 = Collections.binarySearch(this.mediaSourceHolders, this.query);
        if(v0 < 0) {
            return -v0 - 2;
        }

        while(v0 < this.mediaSourceHolders.size() - 1) {
            int v2 = v0 + 1;
            if(this.mediaSourceHolders.get(v2).firstPeriodIndexInChild != arg4) {
                return v0;
            }

            v0 = v2;
        }

        return v0;
    }

    protected MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSourceHolder arg7, MediaPeriodId arg8) {
        int v0;
        for(v0 = 0; v0 < arg7.activeMediaPeriods.size(); ++v0) {
            if(arg7.activeMediaPeriods.get(v0).id.windowSequenceNumber == arg8.windowSequenceNumber) {
                return arg8.copyWithPeriodIndex(arg8.periodIndex + arg7.firstPeriodIndexInChild);
            }
        }

        return null;
    }

    protected MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(Object arg1, MediaPeriodId arg2) {
        return this.getMediaPeriodIdForChildMediaPeriodId(((MediaSourceHolder)arg1), arg2);
    }

    public final MediaSource getMediaSource(int arg2) {
        MediaSource v2_1;
        __monitor_enter(this);
        try {
            v2_1 = this.mediaSourcesPublic.get(arg2).mediaSource;
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
        return v2_1;
    }

    public final int getSize() {
        int v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.mediaSourcesPublic.size();
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    protected int getWindowIndexForChildWindowIndex(MediaSourceHolder arg1, int arg2) {
        return arg2 + arg1.firstWindowIndexInChild;
    }

    protected int getWindowIndexForChildWindowIndex(Object arg1, int arg2) {
        return this.getWindowIndexForChildWindowIndex(((MediaSourceHolder)arg1), arg2);
    }

    public final void handleMessage(int arg3, Object arg4) {
        if(this.player == null) {
            return;
        }

        switch(arg3) {
            case 0: {
                goto label_53;
            }
            case 1: {
                goto label_43;
            }
            case 2: {
                goto label_36;
            }
            case 3: {
                goto label_22;
            }
            case 4: {
                goto label_19;
            }
            case 5: {
                goto label_17;
            }
            case 6: {
                goto label_8;
            }
        }

        throw new IllegalStateException();
    label_17:
        this.notifyListener();
        return;
    label_19:
        this.clearInternal();
        this.scheduleListenerNotification(((Runnable)arg4));
        return;
    label_36:
        this.shuffleOrder = this.shuffleOrder.cloneAndRemove(((MessageData)arg4).index);
        this.removeMediaSourceInternal(((MessageData)arg4).index);
        goto label_60;
    label_53:
        this.shuffleOrder = this.shuffleOrder.cloneAndInsert(((MessageData)arg4).index, 1);
        this.addMediaSourceInternal(((MessageData)arg4).index, ((MessageData)arg4).customData);
        goto label_60;
    label_22:
        this.shuffleOrder = this.shuffleOrder.cloneAndRemove(((MessageData)arg4).index);
        this.shuffleOrder = this.shuffleOrder.cloneAndInsert(((MessageData)arg4).customData.intValue(), 1);
        this.moveMediaSourceInternal(((MessageData)arg4).index, ((MessageData)arg4).customData.intValue());
        goto label_60;
    label_8:
        Object v3 = Assertions.checkNotNull(this.playerApplicationHandler);
        int v0;
        for(v0 = 0; true; ++v0) {
            if(v0 >= ((List)arg4).size()) {
                return;
            }

            ((Handler)v3).post(((List)arg4).get(v0));
        }

    label_43:
        this.shuffleOrder = this.shuffleOrder.cloneAndInsert(((MessageData)arg4).index, ((MessageData)arg4).customData.size());
        this.addMediaSourcesInternal(((MessageData)arg4).index, ((MessageData)arg4).customData);
    label_60:
        this.scheduleListenerNotification(((MessageData)arg4).actionOnCompletion);
    }

    public final void moveMediaSource(int arg2, int arg3) {
        __monitor_enter(this);
        Runnable v0 = null;
        try {
            this.moveMediaSource(arg2, arg3, v0);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    public final void moveMediaSource(int arg3, int arg4, Runnable arg5) {
        __monitor_enter(this);
        if(arg3 == arg4) {
            __monitor_exit(this);
            return;
        }

        try {
            this.mediaSourcesPublic.add(arg4, this.mediaSourcesPublic.remove(arg3));
            if(this.player != null) {
                this.player.createMessage(((Target)this)).setType(3).setPayload(new MessageData(arg3, Integer.valueOf(arg4), arg5)).send();
            }
            else if(arg5 != null) {
                arg5.run();
            }
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
    }

    private void moveMediaSourceInternal(int arg7, int arg8) {
        int v0 = Math.min(arg7, arg8);
        int v1 = Math.max(arg7, arg8);
        int v2 = this.mediaSourceHolders.get(v0).firstWindowIndexInChild;
        int v3 = this.mediaSourceHolders.get(v0).firstPeriodIndexInChild;
        this.mediaSourceHolders.add(arg8, this.mediaSourceHolders.remove(arg7));
        while(v0 <= v1) {
            Object v7 = this.mediaSourceHolders.get(v0);
            ((MediaSourceHolder)v7).firstWindowIndexInChild = v2;
            ((MediaSourceHolder)v7).firstPeriodIndexInChild = v3;
            v2 += ((MediaSourceHolder)v7).timeline.getWindowCount();
            v3 += ((MediaSourceHolder)v7).timeline.getPeriodCount();
            ++v0;
        }
    }

    private void notifyListener() {
        List v0;
        this.listenerNotificationScheduled = false;
        if(this.pendingOnCompletionActions.isEmpty()) {
            v0 = Collections.emptyList();
        }
        else {
            ArrayList v0_1 = new ArrayList(this.pendingOnCompletionActions);
        }

        this.pendingOnCompletionActions.clear();
        this.refreshSourceInfo(new ConcatenatedTimeline(this.mediaSourceHolders, this.windowCount, this.periodCount, this.shuffleOrder, this.isAtomic), null);
        if(!v0.isEmpty()) {
            Assertions.checkNotNull(this.player).createMessage(((Target)this)).setType(6).setPayload(v0).send();
        }
    }

    protected final void onChildSourceInfoRefreshed(MediaSourceHolder arg1, MediaSource arg2, Timeline arg3, Object arg4) {
        this.updateMediaSourceInternal(arg1, arg3);
    }

    protected void onChildSourceInfoRefreshed(Object arg1, MediaSource arg2, Timeline arg3, Object arg4) {
        this.onChildSourceInfoRefreshed(((MediaSourceHolder)arg1), arg2, arg3, arg4);
    }

    public final void prepareSourceInternal(ExoPlayer arg1, boolean arg2, TransferListener arg3) {
        __monitor_enter(this);
        try {
            super.prepareSourceInternal(arg1, arg2, arg3);
            this.player = arg1;
            this.playerApplicationHandler = new Handler(arg1.getApplicationLooper());
            if(this.mediaSourcesPublic.isEmpty()) {
                this.notifyListener();
            }
            else {
                this.shuffleOrder = this.shuffleOrder.cloneAndInsert(0, this.mediaSourcesPublic.size());
                this.addMediaSourcesInternal(0, this.mediaSourcesPublic);
                this.scheduleListenerNotification(null);
            }
        }
        catch(Throwable v1) {
            __monitor_exit(this);
            throw v1;
        }

        __monitor_exit(this);
    }

    public final void releasePeriod(MediaPeriod arg3) {
        Object v0 = Assertions.checkNotNull(this.mediaSourceByMediaPeriod.remove(arg3));
        arg3.releasePeriod();
        ((MediaSourceHolder)v0).activeMediaPeriods.remove(arg3);
        if((((MediaSourceHolder)v0).activeMediaPeriods.isEmpty()) && (((MediaSourceHolder)v0).isRemoved)) {
            this.releaseChildSource(v0);
        }
    }

    public final void releaseSourceInternal() {
        super.releaseSourceInternal();
        this.mediaSourceHolders.clear();
        this.player = null;
        this.playerApplicationHandler = null;
        this.shuffleOrder = this.shuffleOrder.cloneAndClear();
        this.windowCount = 0;
        this.periodCount = 0;
    }

    public final void removeMediaSource(int arg2) {
        __monitor_enter(this);
        Runnable v0 = null;
        try {
            this.removeMediaSource(arg2, v0);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    public final void removeMediaSource(int arg4, Runnable arg5) {
        __monitor_enter(this);
        try {
            this.mediaSourcesPublic.remove(arg4);
            if(this.player != null) {
                this.player.createMessage(((Target)this)).setType(2).setPayload(new MessageData(arg4, null, arg5)).send();
            }
            else if(arg5 != null) {
                arg5.run();
            }
        }
        catch(Throwable v4) {
            __monitor_exit(this);
            throw v4;
        }

        __monitor_exit(this);
    }

    private void removeMediaSourceInternal(int arg5) {
        Object v0 = this.mediaSourceHolders.remove(arg5);
        this.correctOffsets(arg5, -1, -((MediaSourceHolder)v0).timeline.getWindowCount(), -((MediaSourceHolder)v0).timeline.getPeriodCount());
        ((MediaSourceHolder)v0).isRemoved = true;
        if(((MediaSourceHolder)v0).activeMediaPeriods.isEmpty()) {
            this.releaseChildSource(v0);
        }
    }

    private void scheduleListenerNotification(Runnable arg3) {
        if(!this.listenerNotificationScheduled) {
            Assertions.checkNotNull(this.player).createMessage(((Target)this)).setType(5).send();
            this.listenerNotificationScheduled = true;
        }

        if(arg3 != null) {
            this.pendingOnCompletionActions.add(arg3);
        }
    }

    private void updateMediaSourceInternal(MediaSourceHolder arg8, Timeline arg9) {
        if(arg8 != null) {
            DeferredTimeline v0 = arg8.timeline;
            if(v0.getTimeline() == arg9) {
                return;
            }

            int v1 = arg9.getWindowCount() - v0.getWindowCount();
            int v2 = arg9.getPeriodCount() - v0.getPeriodCount();
            int v3 = 0;
            if(v1 != 0 || v2 != 0) {
                this.correctOffsets(arg8.childIndex + 1, 0, v1, v2);
            }

            arg8.timeline = v0.cloneWithNewTimeline(arg9);
            if(!arg8.isPrepared && !arg9.isEmpty()) {
                arg9.getWindow(0, this.window);
                long v0_1 = this.window.getPositionInFirstPeriodUs() + this.window.getDefaultPositionUs();
                while(v3 < arg8.activeMediaPeriods.size()) {
                    Object v9 = arg8.activeMediaPeriods.get(v3);
                    ((DeferredMediaPeriod)v9).setDefaultPreparePositionUs(v0_1);
                    ((DeferredMediaPeriod)v9).createPeriod(((DeferredMediaPeriod)v9).id.copyWithPeriodIndex(((DeferredMediaPeriod)v9).id.periodIndex - arg8.firstPeriodIndexInChild));
                    ++v3;
                }

                arg8.isPrepared = true;
            }

            this.scheduleListenerNotification(null);
            return;
        }

        throw new IllegalArgumentException();
    }
}

