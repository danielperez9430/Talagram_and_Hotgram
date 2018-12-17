package com.google.android.exoplayer2;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.ColorInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Format implements Parcelable {
    final class com.google.android.exoplayer2.Format$1 implements Parcelable$Creator {
        com.google.android.exoplayer2.Format$1() {
            super();
        }

        public Format createFromParcel(Parcel arg2) {
            return new Format(arg2);
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public Format[] newArray(int arg1) {
            return new Format[arg1];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR = null;
    public static final int NO_VALUE = -1;
    public static final long OFFSET_SAMPLE_RELATIVE = 9223372036854775807L;
    public final int accessibilityChannel;
    public final int bitrate;
    public final int channelCount;
    public final String codecs;
    public final ColorInfo colorInfo;
    public final String containerMimeType;
    public final DrmInitData drmInitData;
    public final int encoderDelay;
    public final int encoderPadding;
    public final float frameRate;
    private int hashCode;
    public final int height;
    public final String id;
    public final List initializationData;
    public final String label;
    public final String language;
    public final int maxInputSize;
    public final Metadata metadata;
    public final int pcmEncoding;
    public final float pixelWidthHeightRatio;
    public final byte[] projectionData;
    public final int rotationDegrees;
    public final String sampleMimeType;
    public final int sampleRate;
    public final int selectionFlags;
    public final int stereoMode;
    public final long subsampleOffsetUs;
    public final int width;

    static {
        Format.CREATOR = new com.google.android.exoplayer2.Format$1();
    }

    Format(Parcel arg5) {
        super();
        this.id = arg5.readString();
        this.label = arg5.readString();
        this.containerMimeType = arg5.readString();
        this.sampleMimeType = arg5.readString();
        this.codecs = arg5.readString();
        this.bitrate = arg5.readInt();
        this.maxInputSize = arg5.readInt();
        this.width = arg5.readInt();
        this.height = arg5.readInt();
        this.frameRate = arg5.readFloat();
        this.rotationDegrees = arg5.readInt();
        this.pixelWidthHeightRatio = arg5.readFloat();
        byte[] v0 = Util.readBoolean(arg5) ? arg5.createByteArray() : null;
        this.projectionData = v0;
        this.stereoMode = arg5.readInt();
        this.colorInfo = arg5.readParcelable(ColorInfo.class.getClassLoader());
        this.channelCount = arg5.readInt();
        this.sampleRate = arg5.readInt();
        this.pcmEncoding = arg5.readInt();
        this.encoderDelay = arg5.readInt();
        this.encoderPadding = arg5.readInt();
        this.selectionFlags = arg5.readInt();
        this.language = arg5.readString();
        this.accessibilityChannel = arg5.readInt();
        this.subsampleOffsetUs = arg5.readLong();
        int v0_1 = arg5.readInt();
        this.initializationData = new ArrayList(v0_1);
        int v1;
        for(v1 = 0; v1 < v0_1; ++v1) {
            this.initializationData.add(arg5.createByteArray());
        }

        this.drmInitData = arg5.readParcelable(DrmInitData.class.getClassLoader());
        this.metadata = arg5.readParcelable(Metadata.class.getClassLoader());
    }

    Format(String arg5, String arg6, String arg7, String arg8, String arg9, int arg10, int arg11, int arg12, int arg13, float arg14, int arg15, float arg16, byte[] arg17, int arg18, ColorInfo arg19, int arg20, int arg21, int arg22, int arg23, int arg24, int arg25, String arg26, int arg27, long arg28, List arg30, DrmInitData arg31, Metadata arg32) {
        Format v0 = this;
        super();
        v0.id = arg5;
        v0.label = arg6;
        v0.containerMimeType = arg7;
        v0.sampleMimeType = arg8;
        v0.codecs = arg9;
        v0.bitrate = arg10;
        v0.maxInputSize = arg11;
        v0.width = arg12;
        v0.height = arg13;
        v0.frameRate = arg14;
        int v2 = -1;
        int v3 = arg15;
        if(v3 == v2) {
            v3 = 0;
        }

        v0.rotationDegrees = v3;
        float v3_1 = arg16 == -1f ? 1f : arg16;
        v0.pixelWidthHeightRatio = v3_1;
        v0.projectionData = arg17;
        v0.stereoMode = arg18;
        v0.colorInfo = arg19;
        v0.channelCount = arg20;
        v0.sampleRate = arg21;
        v0.pcmEncoding = arg22;
        v3 = arg23;
        if(v3 == v2) {
            v3 = 0;
        }

        v0.encoderDelay = v3;
        v3 = arg24;
        if(v3 == v2) {
            v3 = 0;
        }

        v0.encoderPadding = v3;
        v0.selectionFlags = arg25;
        v0.language = arg26;
        v0.accessibilityChannel = arg27;
        v0.subsampleOffsetUs = arg28;
        List v1 = arg30 == null ? Collections.emptyList() : arg30;
        v0.initializationData = v1;
        v0.drmInitData = arg31;
        v0.metadata = arg32;
    }

    public Format copyWithContainerInfo(String arg34, String arg35, String arg36, String arg37, int arg38, int arg39, int arg40, int arg41, String arg42) {
        return new Format(arg34, arg35, this.containerMimeType, arg36, arg37, arg38, this.maxInputSize, arg39, arg40, this.frameRate, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, arg41, arg42, this.accessibilityChannel, this.subsampleOffsetUs, this.initializationData, this.drmInitData, this.metadata);
    }

    public Format copyWithDrmInitData(DrmInitData arg34) {
        return new Format(this.id, this.label, this.containerMimeType, this.sampleMimeType, this.codecs, this.bitrate, this.maxInputSize, this.width, this.height, this.frameRate, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.selectionFlags, this.language, this.accessibilityChannel, this.subsampleOffsetUs, this.initializationData, arg34, this.metadata);
    }

    public Format copyWithGaplessInfo(int arg34, int arg35) {
        return new Format(this.id, this.label, this.containerMimeType, this.sampleMimeType, this.codecs, this.bitrate, this.maxInputSize, this.width, this.height, this.frameRate, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, arg34, arg35, this.selectionFlags, this.language, this.accessibilityChannel, this.subsampleOffsetUs, this.initializationData, this.drmInitData, this.metadata);
    }

    public Format copyWithManifestFormatInfo(Format arg34) {
        String v8;
        Format v0 = this;
        Format v1 = arg34;
        if(v0 == v1) {
            return v0;
        }

        int v2 = MimeTypes.getTrackType(v0.sampleMimeType);
        String v4 = v1.id;
        String v3 = v1.label != null ? v1.label : v0.label;
        String v5 = v3;
        v3 = v0.language;
        if((v2 == 3 || v2 == 1) && v1.language != null) {
            v3 = v1.language;
        }

        String v25 = v3;
        int v3_1 = v0.bitrate == -1 ? v1.bitrate : v0.bitrate;
        int v9 = v3_1;
        v3 = v0.codecs;
        if(v3 == null) {
            String v6 = Util.getCodecsOfType(v1.codecs, v2);
            if(Util.splitCodecs(v6).length == 1) {
                v8 = v6;
            }
            else {
                goto label_40;
            }
        }
        else {
        label_40:
            v8 = v3;
        }

        float v3_2 = v0.frameRate;
        float v13 = v3_2 != -1f || v2 != 2 ? v3_2 : v1.frameRate;
        return new Format(v4, v5, v0.containerMimeType, v0.sampleMimeType, v8, v9, v0.maxInputSize, v0.width, v0.height, v13, v0.rotationDegrees, v0.pixelWidthHeightRatio, v0.projectionData, v0.stereoMode, v0.colorInfo, v0.channelCount, v0.sampleRate, v0.pcmEncoding, v0.encoderDelay, v0.encoderPadding, v0.selectionFlags | v1.selectionFlags, v25, v0.accessibilityChannel, v0.subsampleOffsetUs, v0.initializationData, DrmInitData.createSessionCreationData(v1.drmInitData, v0.drmInitData), v0.metadata);
    }

    public Format copyWithMaxInputSize(int arg34) {
        return new Format(this.id, this.label, this.containerMimeType, this.sampleMimeType, this.codecs, this.bitrate, arg34, this.width, this.height, this.frameRate, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.selectionFlags, this.language, this.accessibilityChannel, this.subsampleOffsetUs, this.initializationData, this.drmInitData, this.metadata);
    }

    public Format copyWithMetadata(Metadata arg34) {
        return new Format(this.id, this.label, this.containerMimeType, this.sampleMimeType, this.codecs, this.bitrate, this.maxInputSize, this.width, this.height, this.frameRate, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.selectionFlags, this.language, this.accessibilityChannel, this.subsampleOffsetUs, this.initializationData, this.drmInitData, arg34);
    }

    public Format copyWithRotationDegrees(int arg34) {
        return new Format(this.id, this.label, this.containerMimeType, this.sampleMimeType, this.codecs, this.bitrate, this.maxInputSize, this.width, this.height, this.frameRate, arg34, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.selectionFlags, this.language, this.accessibilityChannel, this.subsampleOffsetUs, this.initializationData, this.drmInitData, this.metadata);
    }

    public Format copyWithSubsampleOffsetUs(long arg33) {
        return new Format(this.id, this.label, this.containerMimeType, this.sampleMimeType, this.codecs, this.bitrate, this.maxInputSize, this.width, this.height, this.frameRate, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.selectionFlags, this.language, this.accessibilityChannel, arg33, this.initializationData, this.drmInitData, this.metadata);
    }

    @Deprecated public static Format createAudioContainerFormat(String arg11, String arg12, String arg13, String arg14, int arg15, int arg16, int arg17, List arg18, int arg19, String arg20) {
        return Format.createAudioContainerFormat(arg11, null, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20);
    }

    public static Format createAudioContainerFormat(String arg30, String arg31, String arg32, String arg33, String arg34, int arg35, int arg36, int arg37, List arg38, int arg39, String arg40) {
        return new Format(arg30, arg31, arg32, arg33, arg34, arg35, -1, -1, -1, -1f, -1, -1f, null, -1, null, arg36, arg37, -1, -1, -1, arg39, arg40, -1, 9223372036854775807L, arg38, null, null);
    }

    public static Format createAudioSampleFormat(String arg30, String arg31, String arg32, int arg33, int arg34, int arg35, int arg36, int arg37, int arg38, int arg39, List arg40, DrmInitData arg41, int arg42, String arg43, Metadata arg44) {
        return new Format(arg30, null, null, arg31, arg32, arg33, arg34, -1, -1, -1f, -1, -1f, null, -1, null, arg35, arg36, arg37, arg38, arg39, arg42, arg43, -1, 9223372036854775807L, arg40, arg41, arg44);
    }

    public static Format createAudioSampleFormat(String arg15, String arg16, String arg17, int arg18, int arg19, int arg20, int arg21, int arg22, List arg23, DrmInitData arg24, int arg25, String arg26) {
        return Format.createAudioSampleFormat(arg15, arg16, arg17, arg18, arg19, arg20, arg21, arg22, -1, -1, arg23, arg24, arg25, arg26, null);
    }

    public static Format createAudioSampleFormat(String arg12, String arg13, String arg14, int arg15, int arg16, int arg17, int arg18, List arg19, DrmInitData arg20, int arg21, String arg22) {
        return Format.createAudioSampleFormat(arg12, arg13, arg14, arg15, arg16, arg17, arg18, -1, arg19, arg20, arg21, arg22);
    }

    @Deprecated public static Format createContainerFormat(String arg8, String arg9, String arg10, String arg11, int arg12, int arg13, String arg14) {
        return Format.createContainerFormat(arg8, null, arg9, arg10, arg11, arg12, arg13, arg14);
    }

    public static Format createContainerFormat(String arg30, String arg31, String arg32, String arg33, String arg34, int arg35, int arg36, String arg37) {
        return new Format(arg30, arg31, arg32, arg33, arg34, arg35, -1, -1, -1, -1f, -1, -1f, null, -1, null, -1, -1, -1, -1, -1, arg36, arg37, -1, 9223372036854775807L, null, null, null);
    }

    public static Format createImageSampleFormat(String arg30, String arg31, String arg32, int arg33, int arg34, List arg35, String arg36, DrmInitData arg37) {
        return new Format(arg30, null, null, arg31, arg32, arg33, -1, -1, -1, -1f, -1, -1f, null, -1, null, -1, -1, -1, -1, -1, arg34, arg36, -1, 9223372036854775807L, arg35, arg37, null);
    }

    public static Format createSampleFormat(String arg30, String arg31, long arg32) {
        return new Format(arg30, null, null, arg31, null, -1, -1, -1, -1, -1f, -1, -1f, null, -1, null, -1, -1, -1, -1, -1, 0, null, -1, arg32, null, null, null);
    }

    public static Format createSampleFormat(String arg30, String arg31, String arg32, int arg33, DrmInitData arg34) {
        return new Format(arg30, null, null, arg31, arg32, arg33, -1, -1, -1, -1f, -1, -1f, null, -1, null, -1, -1, -1, -1, -1, 0, null, -1, 9223372036854775807L, null, arg34, null);
    }

    @Deprecated public static Format createTextContainerFormat(String arg8, String arg9, String arg10, String arg11, int arg12, int arg13, String arg14) {
        return Format.createTextContainerFormat(arg8, null, arg9, arg10, arg11, arg12, arg13, arg14);
    }

    public static Format createTextContainerFormat(String arg9, String arg10, String arg11, String arg12, String arg13, int arg14, int arg15, String arg16) {
        return Format.createTextContainerFormat(arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, -1);
    }

    public static Format createTextContainerFormat(String arg30, String arg31, String arg32, String arg33, String arg34, int arg35, int arg36, String arg37, int arg38) {
        return new Format(arg30, arg31, arg32, arg33, arg34, arg35, -1, -1, -1, -1f, -1, -1f, null, -1, null, -1, -1, -1, -1, -1, arg36, arg37, arg38, 9223372036854775807L, null, null, null);
    }

    public static Format createTextSampleFormat(String arg1, String arg2, int arg3, String arg4) {
        return Format.createTextSampleFormat(arg1, arg2, arg3, arg4, null);
    }

    public static Format createTextSampleFormat(String arg11, String arg12, int arg13, String arg14, DrmInitData arg15) {
        return Format.createTextSampleFormat(arg11, arg12, null, -1, arg13, arg14, -1, arg15, 9223372036854775807L, Collections.emptyList());
    }

    public static Format createTextSampleFormat(String arg30, String arg31, String arg32, int arg33, int arg34, String arg35, int arg36, DrmInitData arg37, long arg38, List arg40) {
        return new Format(arg30, null, null, arg31, arg32, arg33, -1, -1, -1, -1f, -1, -1f, null, -1, null, -1, -1, -1, -1, -1, arg34, arg35, arg36, arg38, arg40, arg37, null);
    }

    public static Format createTextSampleFormat(String arg11, String arg12, String arg13, int arg14, int arg15, String arg16, int arg17, DrmInitData arg18) {
        return Format.createTextSampleFormat(arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, 9223372036854775807L, Collections.emptyList());
    }

    public static Format createTextSampleFormat(String arg11, String arg12, String arg13, int arg14, int arg15, String arg16, DrmInitData arg17, long arg18) {
        return Format.createTextSampleFormat(arg11, arg12, arg13, arg14, arg15, arg16, -1, arg17, arg18, Collections.emptyList());
    }

    @Deprecated public static Format createVideoContainerFormat(String arg11, String arg12, String arg13, String arg14, int arg15, int arg16, int arg17, float arg18, List arg19, int arg20) {
        return Format.createVideoContainerFormat(arg11, null, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20);
    }

    public static Format createVideoContainerFormat(String arg30, String arg31, String arg32, String arg33, String arg34, int arg35, int arg36, int arg37, float arg38, List arg39, int arg40) {
        return new Format(arg30, arg31, arg32, arg33, arg34, arg35, -1, arg36, arg37, arg38, -1, -1f, null, -1, null, -1, -1, -1, -1, -1, arg40, null, -1, 9223372036854775807L, arg39, null, null);
    }

    public static Format createVideoSampleFormat(String arg15, String arg16, String arg17, int arg18, int arg19, int arg20, int arg21, float arg22, List arg23, int arg24, float arg25, DrmInitData arg26) {
        return Format.createVideoSampleFormat(arg15, arg16, arg17, arg18, arg19, arg20, arg21, arg22, arg23, arg24, arg25, null, -1, null, arg26);
    }

    public static Format createVideoSampleFormat(String arg30, String arg31, String arg32, int arg33, int arg34, int arg35, int arg36, float arg37, List arg38, int arg39, float arg40, byte[] arg41, int arg42, ColorInfo arg43, DrmInitData arg44) {
        return new Format(arg30, null, null, arg31, arg32, arg33, arg34, arg35, arg36, arg37, arg39, arg40, arg41, arg42, arg43, -1, -1, -1, -1, -1, 0, null, -1, 9223372036854775807L, arg38, arg44, null);
    }

    public static Format createVideoSampleFormat(String arg12, String arg13, String arg14, int arg15, int arg16, int arg17, int arg18, float arg19, List arg20, DrmInitData arg21) {
        return Format.createVideoSampleFormat(arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20, -1, -1f, arg21);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object arg8) {
        boolean v0 = true;
        if(this == (((Format)arg8))) {
            return 1;
        }

        if(arg8 != null) {
            if(this.getClass() != arg8.getClass()) {
            }
            else {
                if(this.bitrate != ((Format)arg8).bitrate || this.maxInputSize != ((Format)arg8).maxInputSize || this.width != ((Format)arg8).width || this.height != ((Format)arg8).height || this.frameRate != ((Format)arg8).frameRate || this.rotationDegrees != ((Format)arg8).rotationDegrees || this.pixelWidthHeightRatio != ((Format)arg8).pixelWidthHeightRatio || this.stereoMode != ((Format)arg8).stereoMode || this.channelCount != ((Format)arg8).channelCount || this.sampleRate != ((Format)arg8).sampleRate || this.pcmEncoding != ((Format)arg8).pcmEncoding || this.encoderDelay != ((Format)arg8).encoderDelay || this.encoderPadding != ((Format)arg8).encoderPadding || this.subsampleOffsetUs != ((Format)arg8).subsampleOffsetUs || this.selectionFlags != ((Format)arg8).selectionFlags || !Util.areEqual(this.id, ((Format)arg8).id) || !Util.areEqual(this.label, ((Format)arg8).label) || !Util.areEqual(this.language, ((Format)arg8).language) || this.accessibilityChannel != ((Format)arg8).accessibilityChannel || !Util.areEqual(this.containerMimeType, ((Format)arg8).containerMimeType) || !Util.areEqual(this.sampleMimeType, ((Format)arg8).sampleMimeType) || !Util.areEqual(this.codecs, ((Format)arg8).codecs) || !Util.areEqual(this.drmInitData, ((Format)arg8).drmInitData) || !Util.areEqual(this.metadata, ((Format)arg8).metadata) || !Util.areEqual(this.colorInfo, ((Format)arg8).colorInfo) || !Arrays.equals(this.projectionData, ((Format)arg8).projectionData) || !this.initializationDataEquals(((Format)arg8))) {
                    v0 = false;
                }
                else {
                }

                return v0;
            }
        }

        return 0;
    }

    public int getPixelCount() {
        int v1 = -1;
        if(this.width != v1) {
            if(this.height == v1) {
            }
            else {
                v1 = this.height * this.width;
            }
        }

        return v1;
    }

    public int hashCode() {
        if(this.hashCode == 0) {
            int v0 = 527;
            int v2 = 0;
            int v1 = this.id == null ? 0 : this.id.hashCode();
            v0 = (v0 + v1) * 31;
            v1 = this.containerMimeType == null ? 0 : this.containerMimeType.hashCode();
            v0 = (v0 + v1) * 31;
            v1 = this.sampleMimeType == null ? 0 : this.sampleMimeType.hashCode();
            v0 = (v0 + v1) * 31;
            v1 = this.codecs == null ? 0 : this.codecs.hashCode();
            v0 = ((((((v0 + v1) * 31 + this.bitrate) * 31 + this.width) * 31 + this.height) * 31 + this.channelCount) * 31 + this.sampleRate) * 31;
            v1 = this.language == null ? 0 : this.language.hashCode();
            v0 = ((v0 + v1) * 31 + this.accessibilityChannel) * 31;
            v1 = this.drmInitData == null ? 0 : this.drmInitData.hashCode();
            v0 = (v0 + v1) * 31;
            if(this.metadata == null) {
            }
            else {
                v2 = this.metadata.hashCode();
            }

            this.hashCode = v0 + v2;
        }

        return this.hashCode;
    }

    public boolean initializationDataEquals(Format arg5) {
        if(this.initializationData.size() != arg5.initializationData.size()) {
            return 0;
        }

        int v0;
        for(v0 = 0; v0 < this.initializationData.size(); ++v0) {
            if(!Arrays.equals(this.initializationData.get(v0), arg5.initializationData.get(v0))) {
                return 0;
            }
        }

        return 1;
    }

    public static String toLogString(Format arg4) {
        if(arg4 == null) {
            return "null";
        }

        StringBuilder v0 = new StringBuilder();
        v0.append("id=");
        v0.append(arg4.id);
        v0.append(", mimeType=");
        v0.append(arg4.sampleMimeType);
        int v2 = -1;
        if(arg4.bitrate != v2) {
            v0.append(", bitrate=");
            v0.append(arg4.bitrate);
        }

        if(arg4.codecs != null) {
            v0.append(", codecs=");
            v0.append(arg4.codecs);
        }

        if(arg4.width != v2 && arg4.height != v2) {
            v0.append(", res=");
            v0.append(arg4.width);
            v0.append("x");
            v0.append(arg4.height);
        }

        if(arg4.frameRate != -1f) {
            v0.append(", fps=");
            v0.append(arg4.frameRate);
        }

        if(arg4.channelCount != v2) {
            v0.append(", channels=");
            v0.append(arg4.channelCount);
        }

        if(arg4.sampleRate != v2) {
            v0.append(", sample_rate=");
            v0.append(arg4.sampleRate);
        }

        if(arg4.language != null) {
            v0.append(", language=");
            v0.append(arg4.language);
        }

        if(arg4.label != null) {
            v0.append(", label=");
            v0.append(arg4.label);
        }

        return v0.toString();
    }

    public String toString() {
        return "Format(" + this.id + ", " + this.label + ", " + this.containerMimeType + ", " + this.sampleMimeType + ", " + this.codecs + ", " + this.bitrate + ", " + this.language + ", [" + this.width + ", " + this.height + ", " + this.frameRate + "], [" + this.channelCount + ", " + this.sampleRate + "])";
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        arg5.writeString(this.id);
        arg5.writeString(this.label);
        arg5.writeString(this.containerMimeType);
        arg5.writeString(this.sampleMimeType);
        arg5.writeString(this.codecs);
        arg5.writeInt(this.bitrate);
        arg5.writeInt(this.maxInputSize);
        arg5.writeInt(this.width);
        arg5.writeInt(this.height);
        arg5.writeFloat(this.frameRate);
        arg5.writeInt(this.rotationDegrees);
        arg5.writeFloat(this.pixelWidthHeightRatio);
        boolean v0 = this.projectionData != null ? true : false;
        Util.writeBoolean(arg5, v0);
        if(this.projectionData != null) {
            arg5.writeByteArray(this.projectionData);
        }

        arg5.writeInt(this.stereoMode);
        arg5.writeParcelable(this.colorInfo, arg6);
        arg5.writeInt(this.channelCount);
        arg5.writeInt(this.sampleRate);
        arg5.writeInt(this.pcmEncoding);
        arg5.writeInt(this.encoderDelay);
        arg5.writeInt(this.encoderPadding);
        arg5.writeInt(this.selectionFlags);
        arg5.writeString(this.language);
        arg5.writeInt(this.accessibilityChannel);
        arg5.writeLong(this.subsampleOffsetUs);
        arg6 = this.initializationData.size();
        arg5.writeInt(arg6);
        int v0_1;
        for(v0_1 = 0; v0_1 < arg6; ++v0_1) {
            arg5.writeByteArray(this.initializationData.get(v0_1));
        }

        arg5.writeParcelable(this.drmInitData, 0);
        arg5.writeParcelable(this.metadata, 0);
    }
}

