package org.telegram.messenger.video;

import android.media.MediaCodec$BufferInfo;
import android.media.MediaFormat;
import com.coremedia.iso.boxes.AbstractMediaHeaderBox;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.SampleDescriptionBox;
import com.coremedia.iso.boxes.SoundMediaHeaderBox;
import com.coremedia.iso.boxes.VideoMediaHeaderBox;
import com.coremedia.iso.boxes.sampleentry.AudioSampleEntry;
import com.coremedia.iso.boxes.sampleentry.VisualSampleEntry;
import com.googlecode.mp4parser.boxes.mp4.ESDescriptorBox;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.AudioSpecificConfig;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.DecoderConfigDescriptor;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.ESDescriptor;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.SLConfigDescriptor;
import com.mp4parser.iso14496.part15.AvcConfigurationBox;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Track {
    class SamplePresentationTime {
        private long dt;
        private int index;
        private long presentationTime;

        public SamplePresentationTime(Track arg1, int arg2, long arg3) {
            Track.this = arg1;
            super();
            this.index = arg2;
            this.presentationTime = arg3;
        }

        static long access$000(SamplePresentationTime arg2) {
            return arg2.presentationTime;
        }

        static int access$100(SamplePresentationTime arg0) {
            return arg0.index;
        }

        static long access$200(SamplePresentationTime arg2) {
            return arg2.dt;
        }

        static long access$202(SamplePresentationTime arg0, long arg1) {
            arg0.dt = arg1;
            return arg1;
        }
    }

    private Date creationTime;
    private long duration;
    private boolean first;
    private String handler;
    private AbstractMediaHeaderBox headerBox;
    private int height;
    private boolean isAudio;
    private int[] sampleCompositions;
    private SampleDescriptionBox sampleDescriptionBox;
    private long[] sampleDurations;
    private ArrayList samplePresentationTimes;
    private ArrayList samples;
    private static Map samplingFrequencyIndexMap;
    private LinkedList syncSamples;
    private int timeScale;
    private long trackId;
    private float volume;
    private int width;

    static {
        Track.samplingFrequencyIndexMap = new HashMap();
        Track.samplingFrequencyIndexMap.put(Integer.valueOf(96000), Integer.valueOf(0));
        Track.samplingFrequencyIndexMap.put(Integer.valueOf(88200), Integer.valueOf(1));
        Track.samplingFrequencyIndexMap.put(Integer.valueOf(64000), Integer.valueOf(2));
        Track.samplingFrequencyIndexMap.put(Integer.valueOf(48000), Integer.valueOf(3));
        Track.samplingFrequencyIndexMap.put(Integer.valueOf(44100), Integer.valueOf(4));
        Track.samplingFrequencyIndexMap.put(Integer.valueOf(32000), Integer.valueOf(5));
        Track.samplingFrequencyIndexMap.put(Integer.valueOf(24000), Integer.valueOf(6));
        Track.samplingFrequencyIndexMap.put(Integer.valueOf(22050), Integer.valueOf(7));
        Track.samplingFrequencyIndexMap.put(Integer.valueOf(16000), Integer.valueOf(8));
        Track.samplingFrequencyIndexMap.put(Integer.valueOf(12000), Integer.valueOf(9));
        Track.samplingFrequencyIndexMap.put(Integer.valueOf(11025), Integer.valueOf(10));
        Track.samplingFrequencyIndexMap.put(Integer.valueOf(8000), Integer.valueOf(11));
    }

    public Track(int arg13, MediaFormat arg14, boolean arg15) {
        VisualSampleEntry v13_1;
        super();
        this.samples = new ArrayList();
        this.duration = 0;
        this.syncSamples = null;
        this.creationTime = new Date();
        this.volume = 0f;
        this.samplePresentationTimes = new ArrayList();
        this.first = true;
        this.trackId = ((long)arg13);
        this.isAudio = arg15;
        int v15 = 5;
        int v2 = 64;
        int v3 = 16;
        int v4 = 2;
        if(!this.isAudio) {
            this.width = arg14.getInteger("width");
            this.height = arg14.getInteger("height");
            this.timeScale = 90000;
            this.syncSamples = new LinkedList();
            this.handler = "vide";
            this.headerBox = new VideoMediaHeaderBox();
            this.sampleDescriptionBox = new SampleDescriptionBox();
            String v13 = arg14.getString("mime");
            int v6 = 24;
            double v7 = 72;
            if(v13.equals("video/avc")) {
                v13_1 = new VisualSampleEntry("avc1");
                v13_1.setDataReferenceIndex(1);
                v13_1.setDepth(v6);
                v13_1.setFrameCount(1);
                v13_1.setHorizresolution(v7);
                v13_1.setVertresolution(v7);
                v13_1.setWidth(this.width);
                v13_1.setHeight(this.height);
                AvcConfigurationBox v5 = new AvcConfigurationBox();
                int v7_1 = 4;
                if(arg14.getByteBuffer("csd-0") != null) {
                    ArrayList v6_1 = new ArrayList();
                    ByteBuffer v8 = arg14.getByteBuffer("csd-0");
                    v8.position(v7_1);
                    byte[] v9 = new byte[v8.remaining()];
                    v8.get(v9);
                    v6_1.add(v9);
                    ArrayList v8_1 = new ArrayList();
                    ByteBuffer v9_1 = arg14.getByteBuffer("csd-1");
                    v9_1.position(v7_1);
                    byte[] v10 = new byte[v9_1.remaining()];
                    v9_1.get(v10);
                    v8_1.add(v10);
                    v5.setSequenceParameterSets(((List)v6_1));
                    v5.setPictureParameterSets(((List)v8_1));
                }

                int v8_2 = 3;
                int v9_2 = 8;
                int v10_1 = 13;
                int v11 = 32;
                if(arg14.containsKey("level")) {
                    v6 = arg14.getInteger("level");
                    if(v6 == 1) {
                        v5.setAvcLevelIndication(1);
                    }
                    else if(v6 == v11) {
                        v5.setAvcLevelIndication(v4);
                    }
                    else {
                        if(v6 == v7_1) {
                            v15 = 11;
                        }
                        else if(v6 == v9_2) {
                            v15 = 12;
                        }
                        else if(v6 == v3) {
                            goto label_161;
                        }
                        else if(v6 == v2) {
                            v15 = 21;
                        }
                        else if(v6 == 128) {
                            v15 = 22;
                        }
                        else if(v6 == 256) {
                            v5.setAvcLevelIndication(v8_2);
                            goto label_162;
                        }
                        else if(v6 == 512) {
                            v15 = 31;
                        }
                        else if(v6 == 1024) {
                            v5.setAvcLevelIndication(v11);
                            goto label_162;
                        }
                        else if(v6 == 2048) {
                            v5.setAvcLevelIndication(v7_1);
                            goto label_162;
                        }
                        else if(v6 == 4096) {
                            v15 = 41;
                        }
                        else if(v6 == 8192) {
                            v15 = 42;
                        }
                        else if(v6 == 16384) {
                        }
                        else if(v6 == 32768) {
                            v15 = 51;
                        }
                        else if(v6 == 65536) {
                            v15 = 52;
                        }
                        else if(v6 == v4) {
                            v15 = 27;
                        }
                        else {
                            goto label_162;
                        }

                        v5.setAvcLevelIndication(v15);
                    }
                }
                else {
                label_161:
                    v5.setAvcLevelIndication(v10_1);
                }

            label_162:
                v6 = 100;
                if(arg14.containsKey("profile")) {
                    int v14 = arg14.getInteger("profile");
                    if(v14 == 1) {
                        v14 = 66;
                    }
                    else if(v14 == v4) {
                        v14 = 77;
                    }
                    else if(v14 == v7_1) {
                        v14 = 88;
                    }
                    else if(v14 == v9_2) {
                        goto label_189;
                    }
                    else if(v14 == v3) {
                        v14 = 110;
                    }
                    else if(v14 == v11) {
                        v14 = 122;
                    }
                    else if(v14 == v2) {
                        v14 = 244;
                    }
                    else {
                        goto label_190;
                    }

                    v5.setAvcProfileIndication(v14);
                }
                else {
                label_189:
                    v5.setAvcProfileIndication(v6);
                }

            label_190:
                v5.setBitDepthLumaMinus8(-1);
                v5.setBitDepthChromaMinus8(-1);
                v5.setChromaFormat(-1);
                v5.setConfigurationVersion(1);
                v5.setLengthSizeMinusOne(v8_2);
                v5.setProfileCompatibility(0);
                v13_1.addBox(((Box)v5));
            }
            else {
                if(!v13.equals("video/mp4v")) {
                    return;
                }

                v13_1 = new VisualSampleEntry("mp4v");
                v13_1.setDataReferenceIndex(1);
                v13_1.setDepth(v6);
                v13_1.setFrameCount(1);
                v13_1.setHorizresolution(v7);
                v13_1.setVertresolution(v7);
                v13_1.setWidth(this.width);
                v13_1.setHeight(this.height);
            }

            goto label_286;
        }
        else {
            this.volume = 1f;
            this.timeScale = arg14.getInteger("sample-rate");
            this.handler = "soun";
            this.headerBox = new SoundMediaHeaderBox();
            this.sampleDescriptionBox = new SampleDescriptionBox();
            AudioSampleEntry v13_2 = new AudioSampleEntry("mp4a");
            v13_2.setChannelCount(arg14.getInteger("channel-count"));
            v13_2.setSampleRate(((long)arg14.getInteger("sample-rate")));
            v13_2.setDataReferenceIndex(1);
            v13_2.setSampleSize(v3);
            ESDescriptorBox v0 = new ESDescriptorBox();
            ESDescriptor v3_1 = new ESDescriptor();
            v3_1.setEsId(0);
            SLConfigDescriptor v1 = new SLConfigDescriptor();
            v1.setPredefined(v4);
            v3_1.setSlConfigDescriptor(v1);
            DecoderConfigDescriptor v1_1 = new DecoderConfigDescriptor();
            v1_1.setObjectTypeIndication(v2);
            v1_1.setStreamType(v15);
            v1_1.setBufferSizeDB(1536);
            long v14_1 = arg14.containsKey("max-bitrate") ? ((long)arg14.getInteger("max-bitrate")) : 96000;
            v1_1.setMaxBitRate(v14_1);
            v1_1.setAvgBitRate(((long)this.timeScale));
            AudioSpecificConfig v14_2 = new AudioSpecificConfig();
            v14_2.setAudioObjectType(v4);
            v14_2.setSamplingFrequencyIndex(Track.samplingFrequencyIndexMap.get(Integer.valueOf(((int)v13_2.getSampleRate()))).intValue());
            v14_2.setChannelConfiguration(v13_2.getChannelCount());
            v1_1.setAudioSpecificInfo(v14_2);
            v3_1.setDecoderConfigDescriptor(v1_1);
            ByteBuffer v14_3 = v3_1.serialize();
            v0.setEsDescriptor(v3_1);
            v0.setData(v14_3);
            v13_2.addBox(((Box)v0));
        label_286:
            this.sampleDescriptionBox.addBox(((Box)v13_1));
        }
    }

    public void addSample(long arg6, MediaCodec$BufferInfo arg8) {
        int v1 = 1;
        if((this.isAudio) || (arg8.flags & 1) == 0) {
            v1 = 0;
        }
        else {
        }

        this.samples.add(new Sample(arg6, ((long)arg8.size)));
        if(this.syncSamples != null && v1 != 0) {
            this.syncSamples.add(Integer.valueOf(this.samples.size()));
        }

        this.samplePresentationTimes.add(new SamplePresentationTime(this, this.samplePresentationTimes.size(), (arg8.presentationTimeUs * (((long)this.timeScale)) + 500000) / 1000000));
    }

    public Date getCreationTime() {
        return this.creationTime;
    }

    public long getDuration() {
        return this.duration;
    }

    public String getHandler() {
        return this.handler;
    }

    public int getHeight() {
        return this.height;
    }

    public AbstractMediaHeaderBox getMediaHeaderBox() {
        return this.headerBox;
    }

    public int[] getSampleCompositions() {
        return this.sampleCompositions;
    }

    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.sampleDescriptionBox;
    }

    public long[] getSampleDurations() {
        return this.sampleDurations;
    }

    public ArrayList getSamples() {
        return this.samples;
    }

    public long[] getSyncSamples() {
        if(this.syncSamples != null) {
            if(this.syncSamples.isEmpty()) {
            }
            else {
                long[] v0 = new long[this.syncSamples.size()];
                int v1;
                for(v1 = 0; v1 < this.syncSamples.size(); ++v1) {
                    v0[v1] = ((long)this.syncSamples.get(v1).intValue());
                }

                return v0;
            }
        }

        return null;
    }

    public int getTimeScale() {
        return this.timeScale;
    }

    public long getTrackId() {
        return this.trackId;
    }

    public float getVolume() {
        return this.volume;
    }

    public int getWidth() {
        return this.width;
    }

    public boolean isAudio() {
        return this.isAudio;
    }

    static int lambda$prepare$0(SamplePresentationTime arg5, SamplePresentationTime arg6) {
        if(SamplePresentationTime.access$000(arg5) > SamplePresentationTime.access$000(arg6)) {
            return 1;
        }

        if(SamplePresentationTime.access$000(arg5) < SamplePresentationTime.access$000(arg6)) {
            return -1;
        }

        return 0;
    }

    public void prepare() {
        int v3;
        int v17;
        int v12;
        Track v0 = this;
        ArrayList v1 = new ArrayList(v0.samplePresentationTimes);
        Collections.sort(v0.samplePresentationTimes, -$$Lambda$Track$WwpAJwhUb2DZllFb8kOYdyyS8pU.INSTANCE);
        v0.sampleDurations = new long[v0.samplePresentationTimes.size()];
        long v2 = 0;
        long v8 = 9223372036854775807L;
        int v5 = 0;
        int v10 = 0;
        long v6 = v2;
        while(true) {
            v12 = 1;
            if(v5 >= v0.samplePresentationTimes.size()) {
                break;
            }

            Object v11 = v0.samplePresentationTimes.get(v5);
            long v13 = SamplePresentationTime.access$000(((SamplePresentationTime)v11)) - v6;
            v6 = SamplePresentationTime.access$000(((SamplePresentationTime)v11));
            v0.sampleDurations[SamplePresentationTime.access$100(((SamplePresentationTime)v11))] = v13;
            if(SamplePresentationTime.access$100(((SamplePresentationTime)v11)) != 0) {
                v17 = v5;
                v0.duration += v13;
            }
            else {
                v17 = v5;
            }

            if(v13 != v2) {
                v8 = Math.min(v8, v13);
            }

            v5 = v17;
            if(SamplePresentationTime.access$100(((SamplePresentationTime)v11)) != v5) {
                v10 = 1;
            }

            ++v5;
        }

        if(v0.sampleDurations.length > 0) {
            v3 = 0;
            v0.sampleDurations[0] = v8;
            v0.duration += v8;
        }
        else {
            v3 = 0;
        }

        while(v12 < v1.size()) {
            SamplePresentationTime.access$202(v1.get(v12), v0.sampleDurations[v12] + SamplePresentationTime.access$200(v1.get(v12 - 1)));
            ++v12;
        }

        if(v10 != 0) {
            v0.sampleCompositions = new int[v0.samplePresentationTimes.size()];
            while(v3 < v0.samplePresentationTimes.size()) {
                Object v1_1 = v0.samplePresentationTimes.get(v3);
                v0.sampleCompositions[SamplePresentationTime.access$100(((SamplePresentationTime)v1))] = ((int)(SamplePresentationTime.access$000(((SamplePresentationTime)v1)) - SamplePresentationTime.access$200(((SamplePresentationTime)v1))));
                ++v3;
            }
        }
    }
}

