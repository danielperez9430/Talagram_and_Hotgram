package com.google.android.exoplayer2.trackselection;

import android.content.Context;
import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.RendererConfiguration;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class DefaultTrackSelector extends MappingTrackSelector {
    class com.google.android.exoplayer2.trackselection.DefaultTrackSelector$1 {
    }

    final class AudioConfigurationTuple {
        public final int channelCount;
        public final String mimeType;
        public final int sampleRate;

        public AudioConfigurationTuple(int arg1, int arg2, String arg3) {
            super();
            this.channelCount = arg1;
            this.sampleRate = arg2;
            this.mimeType = arg3;
        }

        public boolean equals(Object arg5) {
            boolean v0 = true;
            if(this == (((AudioConfigurationTuple)arg5))) {
                return 1;
            }

            if(arg5 != null) {
                if(this.getClass() != arg5.getClass()) {
                }
                else {
                    if(this.channelCount != ((AudioConfigurationTuple)arg5).channelCount || this.sampleRate != ((AudioConfigurationTuple)arg5).sampleRate || !TextUtils.equals(this.mimeType, ((AudioConfigurationTuple)arg5).mimeType)) {
                        v0 = false;
                    }
                    else {
                    }

                    return v0;
                }
            }

            return 0;
        }

        public int hashCode() {
            int v0 = (this.channelCount * 31 + this.sampleRate) * 31;
            int v1 = this.mimeType != null ? this.mimeType.hashCode() : 0;
            return v0 + v1;
        }
    }

    final class AudioTrackScore implements Comparable {
        private final int bitrate;
        private final int channelCount;
        private final int defaultSelectionFlagScore;
        private final int matchLanguageScore;
        private final Parameters parameters;
        private final int sampleRate;
        private final int withinRendererCapabilitiesScore;

        public AudioTrackScore(Format arg2, Parameters arg3, int arg4) {
            super();
            this.parameters = arg3;
            this.withinRendererCapabilitiesScore = DefaultTrackSelector.isSupported(arg4, false);
            this.matchLanguageScore = DefaultTrackSelector.formatHasLanguage(arg2, arg3.preferredAudioLanguage);
            arg4 = 1;
            if((arg2.selectionFlags & 1) != 0) {
            }
            else {
                arg4 = 0;
            }

            this.defaultSelectionFlagScore = arg4;
            this.channelCount = arg2.channelCount;
            this.sampleRate = arg2.sampleRate;
            this.bitrate = arg2.bitrate;
        }

        public int compareTo(AudioTrackScore arg4) {
            int v4;
            int v0;
            if(this.withinRendererCapabilitiesScore != arg4.withinRendererCapabilitiesScore) {
                return DefaultTrackSelector.compareInts(this.withinRendererCapabilitiesScore, arg4.withinRendererCapabilitiesScore);
            }

            if(this.matchLanguageScore != arg4.matchLanguageScore) {
                return DefaultTrackSelector.compareInts(this.matchLanguageScore, arg4.matchLanguageScore);
            }

            if(this.defaultSelectionFlagScore != arg4.defaultSelectionFlagScore) {
                return DefaultTrackSelector.compareInts(this.defaultSelectionFlagScore, arg4.defaultSelectionFlagScore);
            }

            if(this.parameters.forceLowestBitrate) {
                return DefaultTrackSelector.compareInts(arg4.bitrate, this.bitrate);
            }

            int v1 = 1;
            if(this.withinRendererCapabilitiesScore == 1) {
            }
            else {
                v1 = -1;
            }

            if(this.channelCount != arg4.channelCount) {
                v0 = this.channelCount;
                v4 = arg4.channelCount;
            }
            else if(this.sampleRate != arg4.sampleRate) {
                v0 = this.sampleRate;
                v4 = arg4.sampleRate;
            }
            else {
                v0 = this.bitrate;
                v4 = arg4.bitrate;
            }

            return v1 * DefaultTrackSelector.compareInts(v0, v4);
        }

        public int compareTo(Object arg1) {
            return this.compareTo(((AudioTrackScore)arg1));
        }

        public boolean equals(Object arg5) {
            boolean v0 = true;
            if(this == (((AudioTrackScore)arg5))) {
                return 1;
            }

            if(arg5 != null) {
                if(this.getClass() != arg5.getClass()) {
                }
                else {
                    if(this.withinRendererCapabilitiesScore != ((AudioTrackScore)arg5).withinRendererCapabilitiesScore || this.matchLanguageScore != ((AudioTrackScore)arg5).matchLanguageScore || this.defaultSelectionFlagScore != ((AudioTrackScore)arg5).defaultSelectionFlagScore || this.channelCount != ((AudioTrackScore)arg5).channelCount || this.sampleRate != ((AudioTrackScore)arg5).sampleRate || this.bitrate != ((AudioTrackScore)arg5).bitrate) {
                        v0 = false;
                    }
                    else {
                    }

                    return v0;
                }
            }

            return 0;
        }

        public int hashCode() {
            return ((((this.withinRendererCapabilitiesScore * 31 + this.matchLanguageScore) * 31 + this.defaultSelectionFlagScore) * 31 + this.channelCount) * 31 + this.sampleRate) * 31 + this.bitrate;
        }
    }

    public final class Parameters implements Parcelable {
        final class com.google.android.exoplayer2.trackselection.DefaultTrackSelector$Parameters$1 implements Parcelable$Creator {
            com.google.android.exoplayer2.trackselection.DefaultTrackSelector$Parameters$1() {
                super();
            }

            public Parameters createFromParcel(Parcel arg2) {
                return new Parameters(arg2);
            }

            public Object createFromParcel(Parcel arg1) {
                return this.createFromParcel(arg1);
            }

            public Parameters[] newArray(int arg1) {
                return new Parameters[arg1];
            }

            public Object[] newArray(int arg1) {
                return this.newArray(arg1);
            }
        }

        public static final Parcelable$Creator CREATOR;
        public static final Parameters DEFAULT;
        public final boolean allowMixedMimeAdaptiveness;
        public final boolean allowNonSeamlessAdaptiveness;
        public final int disabledTextTrackSelectionFlags;
        public final boolean exceedRendererCapabilitiesIfNecessary;
        public final boolean exceedVideoConstraintsIfNecessary;
        public final boolean forceLowestBitrate;
        public final int maxVideoBitrate;
        public final int maxVideoHeight;
        public final int maxVideoWidth;
        public final String preferredAudioLanguage;
        public final String preferredTextLanguage;
        private final SparseBooleanArray rendererDisabledFlags;
        public final boolean selectUndeterminedTextLanguage;
        private final SparseArray selectionOverrides;
        public final int tunnelingAudioSessionId;
        public final int viewportHeight;
        public final boolean viewportOrientationMayChange;
        public final int viewportWidth;

        static {
            Parameters.DEFAULT = new Parameters();
            Parameters.CREATOR = new com.google.android.exoplayer2.trackselection.DefaultTrackSelector$Parameters$1();
        }

        private Parameters() {
            new SparseArray();
            new SparseBooleanArray();
            this(null, null, null, null, false, 0, false, false, true, 2147483647, 2147483647, 2147483647, true, true, 2147483647, 2147483647, true, 0);
        }

        Parameters(SparseArray arg3, SparseBooleanArray arg4, String arg5, String arg6, boolean arg7, int arg8, boolean arg9, boolean arg10, boolean arg11, int arg12, int arg13, int arg14, boolean arg15, boolean arg16, int arg17, int arg18, boolean arg19, int arg20) {
            super();
            this.selectionOverrides = arg3;
            this.rendererDisabledFlags = arg4;
            this.preferredAudioLanguage = Util.normalizeLanguageCode(arg5);
            this.preferredTextLanguage = Util.normalizeLanguageCode(arg6);
            this.selectUndeterminedTextLanguage = arg7;
            this.disabledTextTrackSelectionFlags = arg8;
            this.forceLowestBitrate = arg9;
            this.allowMixedMimeAdaptiveness = arg10;
            this.allowNonSeamlessAdaptiveness = arg11;
            this.maxVideoWidth = arg12;
            this.maxVideoHeight = arg13;
            this.maxVideoBitrate = arg14;
            this.exceedVideoConstraintsIfNecessary = arg15;
            this.exceedRendererCapabilitiesIfNecessary = arg16;
            this.viewportWidth = arg17;
            this.viewportHeight = arg18;
            this.viewportOrientationMayChange = arg19;
            this.tunnelingAudioSessionId = arg20;
        }

        Parameters(Parcel arg2) {
            super();
            this.selectionOverrides = Parameters.readSelectionOverrides(arg2);
            this.rendererDisabledFlags = arg2.readSparseBooleanArray();
            this.preferredAudioLanguage = arg2.readString();
            this.preferredTextLanguage = arg2.readString();
            this.selectUndeterminedTextLanguage = Util.readBoolean(arg2);
            this.disabledTextTrackSelectionFlags = arg2.readInt();
            this.forceLowestBitrate = Util.readBoolean(arg2);
            this.allowMixedMimeAdaptiveness = Util.readBoolean(arg2);
            this.allowNonSeamlessAdaptiveness = Util.readBoolean(arg2);
            this.maxVideoWidth = arg2.readInt();
            this.maxVideoHeight = arg2.readInt();
            this.maxVideoBitrate = arg2.readInt();
            this.exceedVideoConstraintsIfNecessary = Util.readBoolean(arg2);
            this.exceedRendererCapabilitiesIfNecessary = Util.readBoolean(arg2);
            this.viewportWidth = arg2.readInt();
            this.viewportHeight = arg2.readInt();
            this.viewportOrientationMayChange = Util.readBoolean(arg2);
            this.tunnelingAudioSessionId = arg2.readInt();
        }

        static SparseArray access$000(Parameters arg0) {
            return arg0.selectionOverrides;
        }

        static SparseBooleanArray access$100(Parameters arg0) {
            return arg0.rendererDisabledFlags;
        }

        private static boolean areRendererDisabledFlagsEqual(SparseBooleanArray arg4, SparseBooleanArray arg5) {
            int v0 = arg4.size();
            if(arg5.size() != v0) {
                return 0;
            }

            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                if(arg5.indexOfKey(arg4.keyAt(v1)) < 0) {
                    return 0;
                }
            }

            return 1;
        }

        private static boolean areSelectionOverridesEqual(SparseArray arg5, SparseArray arg6) {
            int v0 = arg5.size();
            if(arg6.size() != v0) {
                return 0;
            }

            int v1 = 0;
            while(true) {
                if(v1 >= v0) {
                    return 1;
                }

                int v3 = arg6.indexOfKey(arg5.keyAt(v1));
                if(v3 >= 0) {
                    if(!Parameters.areSelectionOverridesEqual(arg5.valueAt(v1), arg6.valueAt(v3))) {
                    }
                    else {
                        ++v1;
                        continue;
                    }
                }

                return 0;
            }

            return 0;
        }

        private static boolean areSelectionOverridesEqual(Map arg4, Map arg5) {
            if(arg5.size() != arg4.size()) {
                return 0;
            }

            Iterator v4 = arg4.entrySet().iterator();
            do {
                if(!v4.hasNext()) {
                    return 1;
                }

                Object v0 = v4.next();
                Object v1 = ((Map$Entry)v0).getKey();
                if(!arg5.containsKey(v1)) {
                    return 0;
                }
            }
            while(Util.areEqual(((Map$Entry)v0).getValue(), arg5.get(v1)));

            return 0;
        }

        public ParametersBuilder buildUpon() {
            return new ParametersBuilder(this, null);
        }

        public int describeContents() {
            return 0;
        }

        public boolean equals(Object arg5) {
            boolean v0 = true;
            if(this == (((Parameters)arg5))) {
                return 1;
            }

            if(arg5 != null) {
                if(this.getClass() != arg5.getClass()) {
                }
                else {
                    if(this.selectUndeterminedTextLanguage != ((Parameters)arg5).selectUndeterminedTextLanguage || this.disabledTextTrackSelectionFlags != ((Parameters)arg5).disabledTextTrackSelectionFlags || this.forceLowestBitrate != ((Parameters)arg5).forceLowestBitrate || this.allowMixedMimeAdaptiveness != ((Parameters)arg5).allowMixedMimeAdaptiveness || this.allowNonSeamlessAdaptiveness != ((Parameters)arg5).allowNonSeamlessAdaptiveness || this.maxVideoWidth != ((Parameters)arg5).maxVideoWidth || this.maxVideoHeight != ((Parameters)arg5).maxVideoHeight || this.exceedVideoConstraintsIfNecessary != ((Parameters)arg5).exceedVideoConstraintsIfNecessary || this.exceedRendererCapabilitiesIfNecessary != ((Parameters)arg5).exceedRendererCapabilitiesIfNecessary || this.viewportOrientationMayChange != ((Parameters)arg5).viewportOrientationMayChange || this.viewportWidth != ((Parameters)arg5).viewportWidth || this.viewportHeight != ((Parameters)arg5).viewportHeight || this.maxVideoBitrate != ((Parameters)arg5).maxVideoBitrate || this.tunnelingAudioSessionId != ((Parameters)arg5).tunnelingAudioSessionId || !TextUtils.equals(this.preferredAudioLanguage, ((Parameters)arg5).preferredAudioLanguage) || !TextUtils.equals(this.preferredTextLanguage, ((Parameters)arg5).preferredTextLanguage) || !Parameters.areRendererDisabledFlagsEqual(this.rendererDisabledFlags, ((Parameters)arg5).rendererDisabledFlags) || !Parameters.areSelectionOverridesEqual(this.selectionOverrides, ((Parameters)arg5).selectionOverrides)) {
                        v0 = false;
                    }
                    else {
                    }

                    return v0;
                }
            }

            return 0;
        }

        public final boolean getRendererDisabled(int arg2) {
            return this.rendererDisabledFlags.get(arg2);
        }

        public final SelectionOverride getSelectionOverride(int arg2, TrackGroupArray arg3) {
            SelectionOverride v2_1;
            Object v2 = this.selectionOverrides.get(arg2);
            if(v2 != null) {
                v2 = ((Map)v2).get(arg3);
            }
            else {
                v2_1 = null;
            }

            return v2_1;
        }

        public final boolean hasSelectionOverride(int arg2, TrackGroupArray arg3) {
            Object v2 = this.selectionOverrides.get(arg2);
            boolean v2_1 = v2 == null || !((Map)v2).containsKey(arg3) ? false : true;
            return v2_1;
        }

        public int hashCode() {
            int v0 = (((((((((((((this.selectUndeterminedTextLanguage * 31 + this.disabledTextTrackSelectionFlags) * 31 + this.forceLowestBitrate) * 31 + this.allowMixedMimeAdaptiveness) * 31 + this.allowNonSeamlessAdaptiveness) * 31 + this.maxVideoWidth) * 31 + this.maxVideoHeight) * 31 + this.exceedVideoConstraintsIfNecessary) * 31 + this.exceedRendererCapabilitiesIfNecessary) * 31 + this.viewportOrientationMayChange) * 31 + this.viewportWidth) * 31 + this.viewportHeight) * 31 + this.maxVideoBitrate) * 31 + this.tunnelingAudioSessionId) * 31;
            int v2 = 0;
            int v1 = this.preferredAudioLanguage == null ? 0 : this.preferredAudioLanguage.hashCode();
            v0 = (v0 + v1) * 31;
            if(this.preferredTextLanguage == null) {
            }
            else {
                v2 = this.preferredTextLanguage.hashCode();
            }

            return v0 + v2;
        }

        private static SparseArray readSelectionOverrides(Parcel arg10) {
            int v0 = arg10.readInt();
            SparseArray v1 = new SparseArray(v0);
            int v3;
            for(v3 = 0; v3 < v0; ++v3) {
                int v4 = arg10.readInt();
                int v5 = arg10.readInt();
                HashMap v6 = new HashMap(v5);
                int v7;
                for(v7 = 0; v7 < v5; ++v7) {
                    ((Map)v6).put(arg10.readParcelable(TrackGroupArray.class.getClassLoader()), arg10.readParcelable(SelectionOverride.class.getClassLoader()));
                }

                v1.put(v4, v6);
            }

            return v1;
        }

        private static void writeSelectionOverridesToParcel(Parcel arg6, SparseArray arg7) {
            int v0 = arg7.size();
            arg6.writeInt(v0);
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                int v3 = arg7.keyAt(v2);
                Object v4 = arg7.valueAt(v2);
                int v5 = ((Map)v4).size();
                arg6.writeInt(v3);
                arg6.writeInt(v5);
                Iterator v3_1 = ((Map)v4).entrySet().iterator();
                while(v3_1.hasNext()) {
                    v4 = v3_1.next();
                    arg6.writeParcelable(((Map$Entry)v4).getKey(), 0);
                    arg6.writeParcelable(((Map$Entry)v4).getValue(), 0);
                }
            }
        }

        public void writeToParcel(Parcel arg1, int arg2) {
            Parameters.writeSelectionOverridesToParcel(arg1, this.selectionOverrides);
            arg1.writeSparseBooleanArray(this.rendererDisabledFlags);
            arg1.writeString(this.preferredAudioLanguage);
            arg1.writeString(this.preferredTextLanguage);
            Util.writeBoolean(arg1, this.selectUndeterminedTextLanguage);
            arg1.writeInt(this.disabledTextTrackSelectionFlags);
            Util.writeBoolean(arg1, this.forceLowestBitrate);
            Util.writeBoolean(arg1, this.allowMixedMimeAdaptiveness);
            Util.writeBoolean(arg1, this.allowNonSeamlessAdaptiveness);
            arg1.writeInt(this.maxVideoWidth);
            arg1.writeInt(this.maxVideoHeight);
            arg1.writeInt(this.maxVideoBitrate);
            Util.writeBoolean(arg1, this.exceedVideoConstraintsIfNecessary);
            Util.writeBoolean(arg1, this.exceedRendererCapabilitiesIfNecessary);
            arg1.writeInt(this.viewportWidth);
            arg1.writeInt(this.viewportHeight);
            Util.writeBoolean(arg1, this.viewportOrientationMayChange);
            arg1.writeInt(this.tunnelingAudioSessionId);
        }
    }

    public final class ParametersBuilder {
        private boolean allowMixedMimeAdaptiveness;
        private boolean allowNonSeamlessAdaptiveness;
        private int disabledTextTrackSelectionFlags;
        private boolean exceedRendererCapabilitiesIfNecessary;
        private boolean exceedVideoConstraintsIfNecessary;
        private boolean forceLowestBitrate;
        private int maxVideoBitrate;
        private int maxVideoHeight;
        private int maxVideoWidth;
        private String preferredAudioLanguage;
        private String preferredTextLanguage;
        private final SparseBooleanArray rendererDisabledFlags;
        private boolean selectUndeterminedTextLanguage;
        private final SparseArray selectionOverrides;
        private int tunnelingAudioSessionId;
        private int viewportHeight;
        private boolean viewportOrientationMayChange;
        private int viewportWidth;

        ParametersBuilder(Parameters arg1, com.google.android.exoplayer2.trackselection.DefaultTrackSelector$1 arg2) {
            this(arg1);
        }

        public ParametersBuilder() {
            this(Parameters.DEFAULT);
        }

        private ParametersBuilder(Parameters arg2) {
            super();
            this.selectionOverrides = ParametersBuilder.cloneSelectionOverrides(arg2.selectionOverrides);
            this.rendererDisabledFlags = arg2.rendererDisabledFlags.clone();
            this.preferredAudioLanguage = arg2.preferredAudioLanguage;
            this.preferredTextLanguage = arg2.preferredTextLanguage;
            this.selectUndeterminedTextLanguage = arg2.selectUndeterminedTextLanguage;
            this.disabledTextTrackSelectionFlags = arg2.disabledTextTrackSelectionFlags;
            this.forceLowestBitrate = arg2.forceLowestBitrate;
            this.allowMixedMimeAdaptiveness = arg2.allowMixedMimeAdaptiveness;
            this.allowNonSeamlessAdaptiveness = arg2.allowNonSeamlessAdaptiveness;
            this.maxVideoWidth = arg2.maxVideoWidth;
            this.maxVideoHeight = arg2.maxVideoHeight;
            this.maxVideoBitrate = arg2.maxVideoBitrate;
            this.exceedVideoConstraintsIfNecessary = arg2.exceedVideoConstraintsIfNecessary;
            this.exceedRendererCapabilitiesIfNecessary = arg2.exceedRendererCapabilitiesIfNecessary;
            this.viewportWidth = arg2.viewportWidth;
            this.viewportHeight = arg2.viewportHeight;
            this.viewportOrientationMayChange = arg2.viewportOrientationMayChange;
            this.tunnelingAudioSessionId = arg2.tunnelingAudioSessionId;
        }

        public Parameters build() {
            return new Parameters(this.selectionOverrides, this.rendererDisabledFlags, this.preferredAudioLanguage, this.preferredTextLanguage, this.selectUndeterminedTextLanguage, this.disabledTextTrackSelectionFlags, this.forceLowestBitrate, this.allowMixedMimeAdaptiveness, this.allowNonSeamlessAdaptiveness, this.maxVideoWidth, this.maxVideoHeight, this.maxVideoBitrate, this.exceedVideoConstraintsIfNecessary, this.exceedRendererCapabilitiesIfNecessary, this.viewportWidth, this.viewportHeight, this.viewportOrientationMayChange, this.tunnelingAudioSessionId);
        }

        public final ParametersBuilder clearSelectionOverride(int arg3, TrackGroupArray arg4) {
            Object v0 = this.selectionOverrides.get(arg3);
            if(v0 != null) {
                if(!((Map)v0).containsKey(arg4)) {
                }
                else {
                    ((Map)v0).remove(arg4);
                    if(((Map)v0).isEmpty()) {
                        this.selectionOverrides.remove(arg3);
                    }
                }
            }

            return this;
        }

        public final ParametersBuilder clearSelectionOverrides() {
            if(this.selectionOverrides.size() == 0) {
                return this;
            }

            this.selectionOverrides.clear();
            return this;
        }

        public final ParametersBuilder clearSelectionOverrides(int arg2) {
            Object v0 = this.selectionOverrides.get(arg2);
            if(v0 != null) {
                if(((Map)v0).isEmpty()) {
                }
                else {
                    this.selectionOverrides.remove(arg2);
                }
            }

            return this;
        }

        public ParametersBuilder clearVideoSizeConstraints() {
            return this.setMaxVideoSize(2147483647, 2147483647);
        }

        public ParametersBuilder clearViewportSizeConstraints() {
            return this.setViewportSize(2147483647, 2147483647, true);
        }

        private static SparseArray cloneSelectionOverrides(SparseArray arg5) {
            SparseArray v0 = new SparseArray();
            int v1;
            for(v1 = 0; v1 < arg5.size(); ++v1) {
                v0.put(arg5.keyAt(v1), new HashMap(arg5.valueAt(v1)));
            }

            return v0;
        }

        public ParametersBuilder setAllowMixedMimeAdaptiveness(boolean arg1) {
            this.allowMixedMimeAdaptiveness = arg1;
            return this;
        }

        public ParametersBuilder setAllowNonSeamlessAdaptiveness(boolean arg1) {
            this.allowNonSeamlessAdaptiveness = arg1;
            return this;
        }

        public ParametersBuilder setDisabledTextTrackSelectionFlags(int arg1) {
            this.disabledTextTrackSelectionFlags = arg1;
            return this;
        }

        public ParametersBuilder setExceedRendererCapabilitiesIfNecessary(boolean arg1) {
            this.exceedRendererCapabilitiesIfNecessary = arg1;
            return this;
        }

        public ParametersBuilder setExceedVideoConstraintsIfNecessary(boolean arg1) {
            this.exceedVideoConstraintsIfNecessary = arg1;
            return this;
        }

        public ParametersBuilder setForceLowestBitrate(boolean arg1) {
            this.forceLowestBitrate = arg1;
            return this;
        }

        public ParametersBuilder setMaxVideoBitrate(int arg1) {
            this.maxVideoBitrate = arg1;
            return this;
        }

        public ParametersBuilder setMaxVideoSize(int arg1, int arg2) {
            this.maxVideoWidth = arg1;
            this.maxVideoHeight = arg2;
            return this;
        }

        public ParametersBuilder setMaxVideoSizeSd() {
            return this.setMaxVideoSize(1279, 719);
        }

        public ParametersBuilder setPreferredAudioLanguage(String arg1) {
            this.preferredAudioLanguage = arg1;
            return this;
        }

        public ParametersBuilder setPreferredTextLanguage(String arg1) {
            this.preferredTextLanguage = arg1;
            return this;
        }

        public final ParametersBuilder setRendererDisabled(int arg2, boolean arg3) {
            if(this.rendererDisabledFlags.get(arg2) == arg3) {
                return this;
            }

            if(arg3) {
                this.rendererDisabledFlags.put(arg2, true);
            }
            else {
                this.rendererDisabledFlags.delete(arg2);
            }

            return this;
        }

        public ParametersBuilder setSelectUndeterminedTextLanguage(boolean arg1) {
            this.selectUndeterminedTextLanguage = arg1;
            return this;
        }

        public final ParametersBuilder setSelectionOverride(int arg3, TrackGroupArray arg4, SelectionOverride arg5) {
            Object v0 = this.selectionOverrides.get(arg3);
            if(v0 == null) {
                HashMap v0_1 = new HashMap();
                this.selectionOverrides.put(arg3, v0_1);
            }

            if((((Map)v0).containsKey(arg4)) && (Util.areEqual(((Map)v0).get(arg4), arg5))) {
                return this;
            }

            ((Map)v0).put(arg4, arg5);
            return this;
        }

        public ParametersBuilder setTunnelingAudioSessionId(int arg2) {
            if(this.tunnelingAudioSessionId != arg2) {
                this.tunnelingAudioSessionId = arg2;
            }

            return this;
        }

        public ParametersBuilder setViewportSize(int arg1, int arg2, boolean arg3) {
            this.viewportWidth = arg1;
            this.viewportHeight = arg2;
            this.viewportOrientationMayChange = arg3;
            return this;
        }

        public ParametersBuilder setViewportSizeToPhysicalDisplaySize(Context arg2, boolean arg3) {
            Point v2 = Util.getPhysicalDisplaySize(arg2);
            return this.setViewportSize(v2.x, v2.y, arg3);
        }
    }

    public final class SelectionOverride implements Parcelable {
        final class com.google.android.exoplayer2.trackselection.DefaultTrackSelector$SelectionOverride$1 implements Parcelable$Creator {
            com.google.android.exoplayer2.trackselection.DefaultTrackSelector$SelectionOverride$1() {
                super();
            }

            public SelectionOverride createFromParcel(Parcel arg2) {
                return new SelectionOverride(arg2);
            }

            public Object createFromParcel(Parcel arg1) {
                return this.createFromParcel(arg1);
            }

            public SelectionOverride[] newArray(int arg1) {
                return new SelectionOverride[arg1];
            }

            public Object[] newArray(int arg1) {
                return this.newArray(arg1);
            }
        }

        public static final Parcelable$Creator CREATOR;
        public final int groupIndex;
        public final int length;
        public final int[] tracks;

        static {
            SelectionOverride.CREATOR = new com.google.android.exoplayer2.trackselection.DefaultTrackSelector$SelectionOverride$1();
        }

        public SelectionOverride(int arg1, int[] arg2) {
            super();
            this.groupIndex = arg1;
            this.tracks = Arrays.copyOf(arg2, arg2.length);
            this.length = arg2.length;
            Arrays.sort(this.tracks);
        }

        SelectionOverride(Parcel arg2) {
            super();
            this.groupIndex = arg2.readInt();
            this.length = arg2.readByte();
            this.tracks = new int[this.length];
            arg2.readIntArray(this.tracks);
        }

        public boolean containsTrack(int arg6) {
            int[] v0 = this.tracks;
            int v1 = v0.length;
            int v3;
            for(v3 = 0; v3 < v1; ++v3) {
                if(v0[v3] == arg6) {
                    return 1;
                }
            }

            return 0;
        }

        public int describeContents() {
            return 0;
        }

        public boolean equals(Object arg5) {
            boolean v0 = true;
            if(this == (((SelectionOverride)arg5))) {
                return 1;
            }

            if(arg5 != null) {
                if(this.getClass() != arg5.getClass()) {
                }
                else {
                    if(this.groupIndex != ((SelectionOverride)arg5).groupIndex || !Arrays.equals(this.tracks, ((SelectionOverride)arg5).tracks)) {
                        v0 = false;
                    }
                    else {
                    }

                    return v0;
                }
            }

            return 0;
        }

        public int hashCode() {
            return this.groupIndex * 31 + Arrays.hashCode(this.tracks);
        }

        public void writeToParcel(Parcel arg1, int arg2) {
            arg1.writeInt(this.groupIndex);
            arg1.writeInt(this.tracks.length);
            arg1.writeIntArray(this.tracks);
        }
    }

    private static final float FRACTION_TO_CONSIDER_FULLSCREEN = 0.98f;
    private static final int[] NO_TRACKS = null;
    private static final int WITHIN_RENDERER_CAPABILITIES_BONUS = 1000;
    private final Factory adaptiveTrackSelectionFactory;
    private final AtomicReference parametersReference;

    static {
        DefaultTrackSelector.NO_TRACKS = new int[0];
    }

    public DefaultTrackSelector() {
        this(new com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection$Factory());
    }

    public DefaultTrackSelector(Factory arg2) {
        super();
        this.adaptiveTrackSelectionFactory = arg2;
        this.parametersReference = new AtomicReference(Parameters.DEFAULT);
    }

    @Deprecated public DefaultTrackSelector(BandwidthMeter arg2) {
        this(new com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection$Factory(arg2));
    }

    static int access$300(int arg0, int arg1) {
        return DefaultTrackSelector.compareInts(arg0, arg1);
    }

    public ParametersBuilder buildUponParameters() {
        return this.getParameters().buildUpon();
    }

    @Deprecated public final void clearSelectionOverride(int arg2, TrackGroupArray arg3) {
        this.setParameters(this.buildUponParameters().clearSelectionOverride(arg2, arg3));
    }

    @Deprecated public final void clearSelectionOverrides() {
        this.setParameters(this.buildUponParameters().clearSelectionOverrides());
    }

    @Deprecated public final void clearSelectionOverrides(int arg2) {
        this.setParameters(this.buildUponParameters().clearSelectionOverrides(arg2));
    }

    private static int compareFormatValues(int arg1, int arg2) {
        int v0 = -1;
        if(arg1 == v0) {
            if(arg2 == v0) {
                v0 = 0;
            }
        }
        else if(arg2 == v0) {
            v0 = 1;
        }
        else {
            v0 = arg1 - arg2;
        }

        return v0;
    }

    private static int compareInts(int arg0, int arg1) {
        if(arg0 > arg1) {
            arg0 = 1;
        }
        else if(arg1 > arg0) {
            arg0 = -1;
        }
        else {
            arg0 = 0;
        }

        return arg0;
    }

    private static void filterAdaptiveVideoTrackCountForMimeType(TrackGroup arg11, int[] arg12, int arg13, String arg14, int arg15, int arg16, int arg17, List arg18) {
        List v0 = arg18;
        int v1;
        for(v1 = arg18.size() - 1; v1 >= 0; --v1) {
            int v2 = v0.get(v1).intValue();
            if(!DefaultTrackSelector.isSupportedAdaptiveVideoTrack(arg11.getFormat(v2), arg14, arg12[v2], arg13, arg15, arg16, arg17)) {
                v0.remove(v1);
            }
        }
    }

    protected static boolean formatHasLanguage(Format arg0, String arg1) {
        boolean v0 = arg1 == null || !TextUtils.equals(((CharSequence)arg1), Util.normalizeLanguageCode(arg0.language)) ? false : true;
        return v0;
    }

    protected static boolean formatHasNoLanguage(Format arg1) {
        boolean v1 = (TextUtils.isEmpty(arg1.language)) || (DefaultTrackSelector.formatHasLanguage(arg1, "und")) ? true : false;
        return v1;
    }

    private static int getAdaptiveAudioTrackCount(TrackGroup arg4, int[] arg5, AudioConfigurationTuple arg6) {
        int v0 = 0;
        int v1 = 0;
        while(v0 < arg4.length) {
            if(DefaultTrackSelector.isSupportedAdaptiveAudioTrack(arg4.getFormat(v0), arg5[v0], arg6)) {
                ++v1;
            }

            ++v0;
        }

        return v1;
    }

    private static int[] getAdaptiveAudioTracks(TrackGroup arg10, int[] arg11, boolean arg12) {
        int v0_1;
        int[] v12;
        HashSet v0 = new HashSet();
        Object v1 = null;
        int v2 = 0;
        Object v5 = v1;
        int v3 = 0;
        int v4 = 0;
        while(v3 < arg10.length) {
            Format v6 = arg10.getFormat(v3);
            int v8 = v6.channelCount;
            int v9 = v6.sampleRate;
            String v6_1 = arg12 ? ((String)v1) : v6.sampleMimeType;
            AudioConfigurationTuple v7 = new AudioConfigurationTuple(v8, v9, v6_1);
            if(v0.add(v7)) {
                int v6_2 = DefaultTrackSelector.getAdaptiveAudioTrackCount(arg10, arg11, v7);
                if(v6_2 > v4) {
                    v4 = v6_2;
                    AudioConfigurationTuple v5_1 = v7;
                }
            }

            ++v3;
        }

        if(v4 > 1) {
            v12 = new int[v4];
            v0_1 = 0;
        }
        else {
            return DefaultTrackSelector.NO_TRACKS;
        }

        while(v2 < arg10.length) {
            if(DefaultTrackSelector.isSupportedAdaptiveAudioTrack(arg10.getFormat(v2), arg11[v2], Assertions.checkNotNull(v5_1))) {
                v12[v0_1] = v2;
                ++v0_1;
            }

            ++v2;
        }

        return v12;
    }

    private static int getAdaptiveVideoTrackCountForMimeType(TrackGroup arg12, int[] arg13, int arg14, String arg15, int arg16, int arg17, int arg18, List arg19) {
        int v0 = 0;
        int v1 = 0;
        while(v0 < arg19.size()) {
            int v3 = arg19.get(v0).intValue();
            if(DefaultTrackSelector.isSupportedAdaptiveVideoTrack(arg12.getFormat(v3), arg15, arg13[v3], arg14, arg16, arg17, arg18)) {
                ++v1;
            }

            ++v0;
        }

        return v1;
    }

    private static int[] getAdaptiveVideoTracksForGroup(TrackGroup arg16, int[] arg17, boolean arg18, int arg19, int arg20, int arg21, int arg22, int arg23, int arg24, boolean arg25) {
        String v3;
        TrackGroup v8 = arg16;
        int v9 = 2;
        if(v8.length < v9) {
            return DefaultTrackSelector.NO_TRACKS;
        }

        List v10 = DefaultTrackSelector.getViewportFilteredTrackIndices(v8, arg23, arg24, arg25);
        if(v10.size() < v9) {
            return DefaultTrackSelector.NO_TRACKS;
        }

        String v0 = null;
        if(!arg18) {
            HashSet v11 = new HashSet();
            String v13 = v0;
            int v12 = 0;
            int v14 = 0;
            while(v12 < v10.size()) {
                String v15 = v8.getFormat(v10.get(v12).intValue()).sampleMimeType;
                if(v11.add(v15)) {
                    int v0_1 = DefaultTrackSelector.getAdaptiveVideoTrackCountForMimeType(arg16, arg17, arg19, v15, arg20, arg21, arg22, v10);
                    if(v0_1 > v14) {
                        v14 = v0_1;
                        v13 = v15;
                    }
                }

                ++v12;
            }

            v3 = v13;
        }
        else {
            v3 = v0;
        }

        DefaultTrackSelector.filterAdaptiveVideoTrackCountForMimeType(arg16, arg17, arg19, v3, arg20, arg21, arg22, v10);
        int[] v0_2 = v10.size() < v9 ? DefaultTrackSelector.NO_TRACKS : Util.toArray(v10);
        return v0_2;
    }

    private static Point getMaxVideoSizeInViewport(boolean arg3, int arg4, int arg5, int arg6, int arg7) {
        int v3;
        if(arg3) {
            v3 = 0;
            int v1 = arg6 > arg7 ? 1 : 0;
            if(arg4 > arg5) {
                v3 = 1;
            }

            if(v1 == v3) {
                goto label_11;
            }
        }
        else {
        label_11:
            int v2 = arg5;
            arg5 = arg4;
            arg4 = v2;
        }

        v3 = arg6 * arg4;
        int v0 = arg7 * arg5;
        if(v3 >= v0) {
            return new Point(arg5, Util.ceilDivide(v0, arg6));
        }

        return new Point(Util.ceilDivide(v3, arg7), arg4);
    }

    public Parameters getParameters() {
        return this.parametersReference.get();
    }

    @Deprecated public final boolean getRendererDisabled(int arg2) {
        return this.getParameters().getRendererDisabled(arg2);
    }

    @Deprecated public final SelectionOverride getSelectionOverride(int arg2, TrackGroupArray arg3) {
        return this.getParameters().getSelectionOverride(arg2, arg3);
    }

    private static List getViewportFilteredTrackIndices(TrackGroup arg10, int arg11, int arg12, boolean arg13) {
        ArrayList v0 = new ArrayList(arg10.length);
        int v1 = 0;
        int v2;
        for(v2 = 0; v2 < arg10.length; ++v2) {
            v0.add(Integer.valueOf(v2));
        }

        v2 = 2147483647;
        if(arg11 != v2) {
            if(arg12 == v2) {
            }
            else {
                int v3 = 2147483647;
                while(v1 < arg10.length) {
                    Format v4 = arg10.getFormat(v1);
                    if(v4.width > 0 && v4.height > 0) {
                        Point v5 = DefaultTrackSelector.getMaxVideoSizeInViewport(arg13, arg11, arg12, v4.width, v4.height);
                        int v6 = v4.width * v4.height;
                        float v9 = 0.98f;
                        if(v4.width >= (((int)((((float)v5.x)) * v9))) && v4.height >= (((int)((((float)v5.y)) * v9))) && v6 < v3) {
                            v3 = v6;
                        }
                    }

                    ++v1;
                }

                if(v3 == v2) {
                    goto label_60;
                }

                for(arg11 = v0.size() - 1; arg11 >= 0; --arg11) {
                    arg12 = arg10.getFormat(v0.get(arg11).intValue()).getPixelCount();
                    if(arg12 == -1 || arg12 > v3) {
                        v0.remove(arg11);
                    }
                }
            }
        }

    label_60:
        return ((List)v0);
    }

    @Deprecated public final boolean hasSelectionOverride(int arg2, TrackGroupArray arg3) {
        return this.getParameters().hasSelectionOverride(arg2, arg3);
    }

    protected static boolean isSupported(int arg1, boolean arg2) {
        boolean v1;
        arg1 &= 7;
        if(arg1 != 4) {
            if((arg2) && arg1 == 3) {
                goto label_9;
            }

            v1 = false;
        }
        else {
        label_9:
            v1 = true;
        }

        return v1;
    }

    private static boolean isSupportedAdaptiveAudioTrack(Format arg2, int arg3, AudioConfigurationTuple arg4) {
        boolean v0 = false;
        if((DefaultTrackSelector.isSupported(arg3, false)) && arg2.channelCount == arg4.channelCount && arg2.sampleRate == arg4.sampleRate && (arg4.mimeType == null || (TextUtils.equals(arg4.mimeType, arg2.sampleMimeType)))) {
            v0 = true;
        }

        return v0;
    }

    private static boolean isSupportedAdaptiveVideoTrack(Format arg2, String arg3, int arg4, int arg5, int arg6, int arg7, int arg8) {
        boolean v0 = false;
        if((DefaultTrackSelector.isSupported(arg4, false)) && (arg4 & arg5) != 0 && (arg3 == null || (Util.areEqual(arg2.sampleMimeType, arg3)))) {
            arg4 = -1;
            if(arg2.width != arg4 && arg2.width > arg6) {
                return v0;
            }

            if(arg2.height != arg4 && arg2.height > arg7) {
                return v0;
            }

            if(arg2.bitrate != arg4 && arg2.bitrate > arg8) {
                return v0;
            }

            v0 = true;
        }

        return v0;
    }

    private static void maybeConfigureRenderersForTunneling(MappedTrackInfo arg10, int[][][] arg11, RendererConfiguration[] arg12, TrackSelection[] arg13, int arg14) {
        int v10;
        if(arg14 == 0) {
            return;
        }

        int v0 = 0;
        int v1 = -1;
        int v2 = 0;
        int v3 = -1;
        int v4 = -1;
        while(v2 < arg10.getRendererCount()) {
            int v5 = arg10.getRendererType(v2);
            TrackSelection v7 = arg13[v2];
            if((v5 == 1 || v5 == 2) && (v7 != null && (DefaultTrackSelector.rendererSupportsTunneling(arg11[v2], arg10.getTrackGroups(v2), v7)))) {
                if(v5 == 1) {
                    if(v3 == v1) {
                        v3 = v2;
                        goto label_29;
                    }
                }
                else if(v4 != v1) {
                }
                else {
                    goto label_28;
                }

                v10 = 0;
                goto label_32;
            label_28:
                v4 = v2;
            }

        label_29:
            ++v2;
        }

        v10 = 1;
    label_32:
        if(v3 != v1 && v4 != v1) {
            v0 = 1;
        }

        if((v10 & v0) != 0) {
            RendererConfiguration v10_1 = new RendererConfiguration(arg14);
            arg12[v3] = v10_1;
            arg12[v4] = v10_1;
        }
    }

    private static boolean rendererSupportsTunneling(int[][] arg4, TrackGroupArray arg5, TrackSelection arg6) {
        if(arg6 == null) {
            return 0;
        }

        int v5 = arg5.indexOf(arg6.getTrackGroup());
        int v1;
        for(v1 = 0; v1 < arg6.length(); ++v1) {
            if((arg4[v5][arg6.getIndexInTrackGroup(v1)] & 32) != 32) {
                return 0;
            }
        }

        return 1;
    }

    private static TrackSelection selectAdaptiveVideoTrack(TrackGroupArray arg16, int[][] arg17, int arg18, Parameters arg19, Factory arg20, BandwidthMeter arg21) {
        TrackGroupArray v0 = arg16;
        Parameters v1 = arg19;
        int v2 = v1.allowNonSeamlessAdaptiveness ? 24 : 16;
        boolean v13 = !v1.allowMixedMimeAdaptiveness || (arg18 & v2) == 0 ? false : true;
        int v14;
        for(v14 = 0; v14 < v0.length; ++v14) {
            TrackGroup v15 = v0.get(v14);
            int[] v3 = DefaultTrackSelector.getAdaptiveVideoTracksForGroup(v15, arg17[v14], v13, v2, v1.maxVideoWidth, v1.maxVideoHeight, v1.maxVideoBitrate, v1.viewportWidth, v1.viewportHeight, v1.viewportOrientationMayChange);
            if(v3.length > 0) {
                return Assertions.checkNotNull(arg20).createTrackSelection(v15, arg21, v3);
            }
        }

        return null;
    }

    protected TrackSelection[] selectAllTracks(MappedTrackInfo arg17, int[][][] arg18, int[] arg19, Parameters arg20) {
        DefaultTrackSelector v6 = this;
        MappedTrackInfo v7 = arg17;
        Parameters v8 = arg20;
        int v9 = arg17.getRendererCount();
        TrackSelection[] v10 = new TrackSelection[v9];
        int v0 = 0;
        int v12 = 0;
        int v13 = 0;
        while(true) {
            int v14 = 1;
            if(v12 >= v9) {
                break;
            }

            if(2 == v7.getRendererType(v12)) {
                if(v0 == 0) {
                    v10[v12] = this.selectVideoTrack(v7.getTrackGroups(v12), arg18[v12], arg19[v12], arg20, v6.adaptiveTrackSelectionFactory);
                    v0 = v10[v12] != null ? 1 : 0;
                }

                if(v7.getTrackGroups(v12).length > 0) {
                }
                else {
                    v14 = 0;
                }

                v13 |= v14;
            }

            ++v12;
        }

        v0 = 0;
        v12 = 0;
        int v15 = 0;
        while(v12 < v9) {
            int v1 = v7.getRendererType(v12);
            switch(v1) {
                case 1: {
                    if(v0 != 0) {
                        goto label_79;
                    }

                    TrackGroupArray v1_1 = v7.getTrackGroups(v12);
                    int[][] v2 = arg18[v12];
                    int v3 = arg19[v12];
                    Factory v0_1 = v13 != 0 ? null : v6.adaptiveTrackSelectionFactory;
                    Factory v5 = v0_1;
                    v10[v12] = this.selectAudioTrack(v1_1, v2, v3, arg20, v5);
                    if(v10[v12] != null) {
                        v0 = 1;
                        goto label_79;
                    }

                    v0 = 0;
                    break;
                }
                case 2: {
                    break;
                }
                case 3: {
                    if(v15 != 0) {
                        goto label_79;
                    }

                    v10[v12] = v6.selectTextTrack(v7.getTrackGroups(v12), arg18[v12], v8);
                    v1 = v10[v12] != null ? 1 : 0;
                    v15 = v1;
                    break;
                }
                default: {
                    v10[v12] = v6.selectOtherTrack(v1, v7.getTrackGroups(v12), arg18[v12], v8);
                    break;
                }
            }

        label_79:
            ++v12;
        }

        return v10;
    }

    protected TrackSelection selectAudioTrack(TrackGroupArray arg17, int[][] arg18, int arg19, Parameters arg20, Factory arg21) {
        TrackGroupArray v0 = arg17;
        Parameters v2 = arg20;
        Factory v3 = arg21;
        TrackSelection v5 = null;
        int v6 = -1;
        AudioTrackScore v9 = ((AudioTrackScore)v5);
        int v7 = 0;
        int v8 = -1;
        int v10;
        for(v10 = -1; v7 < v0.length; v10 = v13) {
            TrackGroup v11 = v0.get(v7);
            int[] v12 = arg18[v7];
            int v13 = v10;
            AudioTrackScore v10_1 = v9;
            int v9_1 = v8;
            for(v8 = 0; v8 < v11.length; ++v8) {
                if(DefaultTrackSelector.isSupported(v12[v8], v2.exceedRendererCapabilitiesIfNecessary)) {
                    AudioTrackScore v15 = new AudioTrackScore(v11.getFormat(v8), v2, v12[v8]);
                    if(v10_1 != null && v15.compareTo(v10_1) <= 0) {
                        goto label_33;
                    }

                    v9_1 = v7;
                    v13 = v8;
                    v10_1 = v15;
                }

            label_33:
            }

            ++v7;
            v8 = v9_1;
            v9 = v10_1;
        }

        if(v8 == v6) {
            return v5;
        }

        TrackGroup v0_1 = v0.get(v8);
        if(!v2.forceLowestBitrate && v3 != null) {
            int[] v1 = DefaultTrackSelector.getAdaptiveAudioTracks(v0_1, arg18[v8], v2.allowMixedMimeAdaptiveness);
            if(v1.length > 0) {
                return v3.createTrackSelection(v0_1, this.getBandwidthMeter(), v1);
            }
        }

        return new FixedTrackSelection(v0_1, v10);
    }

    private static TrackSelection selectFixedVideoTrack(TrackGroupArray arg20, int[][] arg21, Parameters arg22) {
        FixedTrackSelection v16_1;
        int v19;
        TrackGroupArray v0 = arg20;
        Parameters v1 = arg22;
        int v3 = -1;
        int v5 = 0;
        TrackGroup v6 = null;
        int v7 = 0;
        int v8 = 0;
        int v9 = -1;
        int v10 = -1;
        while(v5 < v0.length) {
            TrackGroup v11 = v0.get(v5);
            List v12 = DefaultTrackSelector.getViewportFilteredTrackIndices(v11, v1.viewportWidth, v1.viewportHeight, v1.viewportOrientationMayChange);
            int[] v14 = arg21[v5];
            int v15 = v10;
            v10 = v9;
            v9 = v8;
            v8 = v7;
            TrackGroup v7_1 = v6;
            int v6_1 = 0;
            while(v6_1 < v11.length) {
                if(DefaultTrackSelector.isSupported(v14[v6_1], v1.exceedRendererCapabilitiesIfNecessary)) {
                    Format v2 = v11.getFormat(v6_1);
                    if(v12.contains(Integer.valueOf(v6_1))) {
                        if(v2.width != v3 && v2.width > v1.maxVideoWidth) {
                            goto label_53;
                        }

                        if(v2.height != -1 && v2.height > v1.maxVideoHeight) {
                            goto label_53;
                        }

                        if(v2.bitrate != -1 && v2.bitrate > v1.maxVideoBitrate) {
                            goto label_53;
                        }

                        v3 = 1;
                    }
                    else {
                    label_53:
                        v3 = 0;
                    }

                    if(v3 == 0 && !v1.exceedVideoConstraintsIfNecessary) {
                        goto label_101;
                    }

                    int v4 = v3 != 0 ? 2 : 1;
                    v19 = v8;
                    boolean v0_1 = DefaultTrackSelector.isSupported(v14[v6_1], false);
                    if(v0_1) {
                        v4 += 1000;
                    }

                    int v17 = v4 > v9 ? 1 : 0;
                    if(v4 == v9) {
                        if(v1.forceLowestBitrate) {
                            if(DefaultTrackSelector.compareFormatValues(v2.bitrate, v10) >= 0) {
                                goto label_80;
                            }

                            goto label_78;
                        }
                        else {
                            v8 = v2.getPixelCount();
                            v8 = v8 != v15 ? DefaultTrackSelector.compareFormatValues(v8, v15) : DefaultTrackSelector.compareFormatValues(v2.bitrate, v10);
                            if((v0_1) && v3 != 0) {
                                if(v8 > 0) {
                                    goto label_78;
                                }
                                else {
                                    goto label_80;
                                }
                            }

                            if(v8 >= 0) {
                                goto label_80;
                            }

                        label_78:
                            v17 = 1;
                            goto label_94;
                        }

                    label_80:
                        v17 = 0;
                    }

                label_94:
                    if(v17 == 0) {
                        goto label_102;
                    }

                    v10 = v2.bitrate;
                    v15 = v2.getPixelCount();
                    v9 = v4;
                    v8 = v6_1;
                    v7_1 = v11;
                }
                else {
                label_101:
                    v19 = v8;
                label_102:
                    v8 = v19;
                }

                ++v6_1;
                v3 = -1;
            }

            v19 = v8;
            ++v5;
            v6 = v7_1;
            v8 = v9;
            v9 = v10;
            v10 = v15;
            v7 = v19;
            v0 = arg20;
            v3 = -1;
        }

        if(v6 == null) {
            TrackSelection v16 = null;
        }
        else {
            v16_1 = new FixedTrackSelection(v6, v7);
        }

        return ((TrackSelection)v16_1);
    }

    protected TrackSelection selectOtherTrack(int arg11, TrackGroupArray arg12, int[][] arg13, Parameters arg14) {
        TrackSelection v11 = null;
        TrackGroup v2 = ((TrackGroup)v11);
        int v1 = 0;
        int v3 = 0;
        int v4;
        for(v4 = 0; v1 < arg12.length; v4 = v7) {
            TrackGroup v5 = arg12.get(v1);
            int[] v6 = arg13[v1];
            int v7 = v4;
            v4 = v3;
            TrackGroup v3_1 = v2;
            int v2_1;
            for(v2_1 = 0; v2_1 < v5.length; ++v2_1) {
                if(DefaultTrackSelector.isSupported(v6[v2_1], arg14.exceedRendererCapabilitiesIfNecessary)) {
                    int v9 = 1;
                    int v8 = (v5.getFormat(v2_1).selectionFlags & 1) != 0 ? 1 : 0;
                    if(v8 != 0) {
                        v9 = 2;
                    }

                    if(DefaultTrackSelector.isSupported(v6[v2_1], false)) {
                        v9 += 1000;
                    }

                    if(v9 <= v7) {
                        goto label_38;
                    }

                    v4 = v2_1;
                    v3_1 = v5;
                    v7 = v9;
                }

            label_38:
            }

            ++v1;
            v2 = v3_1;
            v3 = v4;
        }

        if(v2 == null) {
        }
        else {
            FixedTrackSelection v11_1 = new FixedTrackSelection(v2, v3);
        }

        return v11;
    }

    protected TrackSelection selectTextTrack(TrackGroupArray arg17, int[][] arg18, Parameters arg19) {
        FixedTrackSelection v15_1;
        int v11_1;
        int v14;
        TrackGroupArray v0 = arg17;
        Parameters v1 = arg19;
        int v4 = 0;
        TrackGroup v5 = null;
        int v6 = 0;
        int v7;
        for(v7 = 0; v4 < v0.length; v7 = v10) {
            TrackGroup v8 = v0.get(v4);
            int[] v9 = arg18[v4];
            int v10 = v7;
            v7 = v6;
            TrackGroup v6_1 = v5;
            int v5_1;
            for(v5_1 = 0; v5_1 < v8.length; ++v5_1) {
                if(DefaultTrackSelector.isSupported(v9[v5_1], v1.exceedRendererCapabilitiesIfNecessary)) {
                    Format v11 = v8.getFormat(v5_1);
                    int v12 = v11.selectionFlags & (v1.disabledTextTrackSelectionFlags ^ -1);
                    int v13 = (v12 & 1) != 0 ? 1 : 0;
                    v12 = (v12 & 2) != 0 ? 1 : 0;
                    boolean v2 = DefaultTrackSelector.formatHasLanguage(v11, v1.preferredTextLanguage);
                    if(!v2) {
                        if((v1.selectUndeterminedTextLanguage) && (DefaultTrackSelector.formatHasNoLanguage(v11))) {
                            goto label_55;
                        }

                        if(v13 != 0) {
                            v14 = 3;
                            goto label_63;
                        }

                        if(v12 == 0) {
                            goto label_71;
                        }

                        if(DefaultTrackSelector.formatHasLanguage(v11, v1.preferredAudioLanguage)) {
                            v14 = 2;
                            goto label_63;
                        }

                        v14 = 1;
                    }
                    else {
                    label_55:
                        if(v13 != 0) {
                            v11_1 = 8;
                        }
                        else if(v12 == 0) {
                            v11_1 = 6;
                        }
                        else {
                            v11_1 = 4;
                        }

                        v14 = v11_1 + (((int)v2));
                    }

                label_63:
                    if(DefaultTrackSelector.isSupported(v9[v5_1], false)) {
                        v14 += 1000;
                    }

                    if(v14 <= v10) {
                        goto label_71;
                    }

                    v7 = v5_1;
                    v6_1 = v8;
                    v10 = v14;
                }

            label_71:
            }

            ++v4;
            v5 = v6_1;
            v6 = v7;
        }

        if(v5 == null) {
            TrackSelection v15 = null;
        }
        else {
            v15_1 = new FixedTrackSelection(v5, v6);
        }

        return ((TrackSelection)v15_1);
    }

    protected final Pair selectTracks(MappedTrackInfo arg10, int[][][] arg11, int[] arg12) {
        int v7_1;
        TrackSelection v4;
        Object v0 = this.parametersReference.get();
        int v1 = arg10.getRendererCount();
        TrackSelection[] v12 = this.selectAllTracks(arg10, arg11, arg12, ((Parameters)v0));
        int v3;
        for(v3 = 0; true; ++v3) {
            v4 = null;
            if(v3 >= v1) {
                break;
            }

            if(((Parameters)v0).getRendererDisabled(v3)) {
                v12[v3] = v4;
            }
            else {
                TrackGroupArray v6 = arg10.getTrackGroups(v3);
                if(((Parameters)v0).hasSelectionOverride(v3, v6)) {
                    SelectionOverride v7 = ((Parameters)v0).getSelectionOverride(v3, v6);
                    if(v7 == null) {
                        v12[v3] = v4;
                    }
                    else if(v7.length == 1) {
                        v12[v3] = new FixedTrackSelection(v6.get(v7.groupIndex), v7.tracks[0]);
                    }
                    else {
                        v12[v3] = Assertions.checkNotNull(this.adaptiveTrackSelectionFactory).createTrackSelection(v6.get(v7.groupIndex), this.getBandwidthMeter(), v7.tracks);
                    }
                }
            }
        }

        RendererConfiguration[] v3_1 = new RendererConfiguration[v1];
        int v6_1;
        for(v6_1 = 0; v6_1 < v1; ++v6_1) {
            if(!((Parameters)v0).getRendererDisabled(v6_1)) {
                if(arg10.getRendererType(v6_1) != 5 && v12[v6_1] == null) {
                    goto label_52;
                }

                v7_1 = 1;
            }
            else {
            label_52:
                v7_1 = 0;
            }

            RendererConfiguration v7_2 = v7_1 != 0 ? RendererConfiguration.DEFAULT : ((RendererConfiguration)v4);
            v3_1[v6_1] = v7_2;
        }

        DefaultTrackSelector.maybeConfigureRenderersForTunneling(arg10, arg11, v3_1, v12, ((Parameters)v0).tunnelingAudioSessionId);
        return Pair.create(v3_1, v12);
    }

    protected TrackSelection selectVideoTrack(TrackGroupArray arg8, int[][] arg9, int arg10, Parameters arg11, Factory arg12) {
        TrackSelection v10 = (arg11.forceLowestBitrate) || arg12 == null ? null : DefaultTrackSelector.selectAdaptiveVideoTrack(arg8, arg9, arg10, arg11, arg12, this.getBandwidthMeter());
        if(v10 == null) {
            v10 = DefaultTrackSelector.selectFixedVideoTrack(arg8, arg9, arg11);
        }

        return v10;
    }

    public void setParameters(ParametersBuilder arg1) {
        this.setParameters(arg1.build());
    }

    public void setParameters(Parameters arg2) {
        Assertions.checkNotNull(arg2);
        if(!this.parametersReference.getAndSet(arg2).equals(arg2)) {
            this.invalidate();
        }
    }

    @Deprecated public final void setRendererDisabled(int arg2, boolean arg3) {
        this.setParameters(this.buildUponParameters().setRendererDisabled(arg2, arg3));
    }

    @Deprecated public final void setSelectionOverride(int arg2, TrackGroupArray arg3, SelectionOverride arg4) {
        this.setParameters(this.buildUponParameters().setSelectionOverride(arg2, arg3, arg4));
    }

    @Deprecated public void setTunnelingAudioSessionId(int arg2) {
        this.setParameters(this.buildUponParameters().setTunnelingAudioSessionId(arg2));
    }
}

