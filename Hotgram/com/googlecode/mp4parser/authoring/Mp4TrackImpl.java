package com.googlecode.mp4parser.authoring;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.Container;
import com.coremedia.iso.boxes.EditListBox;
import com.coremedia.iso.boxes.MediaHeaderBox;
import com.coremedia.iso.boxes.MovieHeaderBox;
import com.coremedia.iso.boxes.SampleDescriptionBox;
import com.coremedia.iso.boxes.SampleTableBox;
import com.coremedia.iso.boxes.SubSampleInformationBox$SubSampleEntry;
import com.coremedia.iso.boxes.SubSampleInformationBox;
import com.coremedia.iso.boxes.TimeToSampleBox$Entry;
import com.coremedia.iso.boxes.TimeToSampleBox;
import com.coremedia.iso.boxes.TrackBox;
import com.coremedia.iso.boxes.TrackHeaderBox;
import com.coremedia.iso.boxes.fragment.MovieExtendsBox;
import com.coremedia.iso.boxes.fragment.MovieFragmentBox;
import com.coremedia.iso.boxes.fragment.SampleFlags;
import com.coremedia.iso.boxes.fragment.TrackExtendsBox;
import com.coremedia.iso.boxes.fragment.TrackFragmentBox;
import com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox;
import com.coremedia.iso.boxes.fragment.TrackRunBox;
import com.coremedia.iso.boxes.mdat.SampleList;
import com.googlecode.mp4parser.AbstractContainerBox;
import com.googlecode.mp4parser.BasicContainer;
import com.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleGroupDescriptionBox;
import com.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleToGroupBox;
import com.googlecode.mp4parser.util.CastUtils;
import com.googlecode.mp4parser.util.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Mp4TrackImpl extends AbstractTrack {
    private List compositionTimeEntries;
    private long[] decodingTimes;
    IsoFile[] fragments;
    private String handler;
    private List sampleDependencies;
    private SampleDescriptionBox sampleDescriptionBox;
    private List samples;
    private SubSampleInformationBox subSampleInformationBox;
    private long[] syncSamples;
    TrackBox trackBox;
    private TrackMetaData trackMetaData;

    public Mp4TrackImpl(String arg35, TrackBox arg36, IsoFile[] arg37) {
        SampleFlags v2_6;
        Entry v2_5;
        int v29;
        Iterator v31;
        ArrayList v30;
        Object v2_4;
        Iterator v26;
        Iterator v24;
        Iterator v3_2;
        long v11_1;
        Iterator v23;
        Iterator v22;
        long v20;
        Box v3_1;
        Object v13;
        Iterator v12;
        long v14;
        Iterator v11;
        LinkedList v10_1;
        Object v6_1;
        Iterator v9_1;
        Iterator v2_2;
        Mp4TrackImpl v0 = this;
        TrackBox v1 = arg36;
        IsoFile[] v2 = arg37;
        super(arg35);
        int v3 = 0;
        v0.syncSamples = new long[0];
        v0.trackMetaData = new TrackMetaData();
        v0.subSampleInformationBox = null;
        long v4 = arg36.getTrackHeaderBox().getTrackId();
        v0.samples = new SampleList(v1, v2);
        SampleTableBox v6 = arg36.getMediaBox().getMediaInformationBox().getSampleTableBox();
        v0.handler = arg36.getMediaBox().getHandlerBox().getHandlerType();
        ArrayList v7 = new ArrayList();
        v0.compositionTimeEntries = new ArrayList();
        v0.sampleDependencies = new ArrayList();
        ((List)v7).addAll(v6.getTimeToSampleBox().getEntries());
        if(v6.getCompositionTimeToSample() != null) {
            v0.compositionTimeEntries.addAll(v6.getCompositionTimeToSample().getEntries());
        }

        if(v6.getSampleDependencyTypeBox() != null) {
            v0.sampleDependencies.addAll(v6.getSampleDependencyTypeBox().getEntries());
        }

        if(v6.getSyncSampleBox() != null) {
            v0.syncSamples = v6.getSyncSampleBox().getSampleNumber();
        }

        v0.subSampleInformationBox = Path.getPath(((AbstractContainerBox)v6), "subs");
        ArrayList v8 = new ArrayList();
        ((List)v8).addAll(arg36.getParent().getParent().getBoxes(MovieFragmentBox.class));
        int v9 = v2.length;
        int v10 = 0;
        while(v10 < v9) {
            v8.addAll(v2[v10].getBoxes(MovieFragmentBox.class));
            ++v10;
            v8 = v8;
            v4 = v4;
            v3 = 0;
        }

        v0.sampleDescriptionBox = v6.getSampleDescriptionBox();
        List v2_1 = arg36.getParent().getBoxes(MovieExtendsBox.class);
        if(v2_1.size() > 0) {
            v2_2 = v2_1.iterator();
            goto label_73;
        }
        else {
            v0.sampleGroups = v0.getSampleGroups(v6.getBoxes(SampleGroupDescriptionBox.class), v6.getBoxes(SampleToGroupBox.class), v0.sampleGroups);
            goto label_346;
            while(true) {
            label_73:
                if(v2_2.hasNext()) {
                    v9_1 = v2_2.next().getBoxes(TrackExtendsBox.class).iterator();
                    do {
                    label_106:
                        if(v9_1.hasNext()) {
                            v6_1 = v9_1.next();
                            if(((TrackExtendsBox)v6_1).getTrackId() != v4) {
                                continue;
                            }

                            break;
                        }
                        else {
                            goto label_108;
                        }
                    }
                    while(true);

                    if(Path.getPaths(arg36.getParent().getParent(), "/moof/traf/subs").size() > 0) {
                        v0.subSampleInformationBox = new SubSampleInformationBox();
                    }

                    v10_1 = new LinkedList();
                    v11 = ((List)v8).iterator();
                    v14 = 1;
                    break;
                label_108:
                    continue;
                }
                else {
                    goto label_75;
                }
            }

            while(true) {
                if(!v11.hasNext()) {
                    goto label_127;
                }

                v12 = v11.next().getBoxes(TrackFragmentBox.class).iterator();
                do {
                label_154:
                    if(!v12.hasNext()) {
                        goto label_156;
                    }

                    v13 = v12.next();
                }
                while(((TrackFragmentBox)v13).getTrackFragmentHeaderBox().getTrackId() != v4);

                v3_1 = Path.getPath(((AbstractContainerBox)v13), "subs");
                if(v3_1 != null) {
                    v20 = v4;
                    v22 = v11;
                    v23 = v12;
                    v11_1 = v14 - (((long)0)) - 1;
                    v3_2 = ((SubSampleInformationBox)v3_1).getEntries().iterator();
                    break;
                }
                else {
                    v24 = v2_2;
                    v20 = v4;
                    v22 = v11;
                    v23 = v12;
                    goto label_206;
                label_156:
                    continue;
                }
            }

            while(v3_2.hasNext()) {
                Object v5 = v3_2.next();
                SubSampleEntry v4_1 = new SubSampleEntry();
                v24 = v2_2;
                Iterator v25 = v3_2;
                v4_1.getSubsampleEntries().addAll(((SubSampleEntry)v5).getSubsampleEntries());
                long v2_3 = 0;
                if(v11_1 != v2_3) {
                    v4_1.setSampleDelta(v11_1 + ((SubSampleEntry)v5).getSampleDelta());
                    v11_1 = v2_3;
                }
                else {
                    v4_1.setSampleDelta(((SubSampleEntry)v5).getSampleDelta());
                }

                v0.subSampleInformationBox.getEntries().add(v4_1);
                v2_2 = v24;
                v3_2 = v25;
            }

            v24 = v2_2;
        label_206:
            v2_2 = ((TrackFragmentBox)v13).getBoxes(TrackRunBox.class).iterator();
            while(v2_2.hasNext()) {
                Object v3_3 = v2_2.next();
                TrackFragmentHeaderBox v4_2 = ((TrackRunBox)v3_3).getParent().getTrackFragmentHeaderBox();
                Iterator v5_1 = ((TrackRunBox)v3_3).getEntries().iterator();
                int v11_2 = 1;
                int v12_1;
                for(v12_1 = 1; v5_1.hasNext(); v12_1 = 0) {
                    v13 = v5_1.next();
                    if(((TrackRunBox)v3_3).isSampleDurationPresent()) {
                        if(((List)v7).size() != 0) {
                            v26 = v2_2;
                            if(((List)v7).get(((List)v7).size() - 1).getDelta() != ((com.coremedia.iso.boxes.fragment.TrackRunBox$Entry)v13).getSampleDuration()) {
                            }
                            else {
                                v2_4 = ((List)v7).get(((List)v7).size() - v11_2);
                                v30 = v8;
                                v31 = v9_1;
                                v29 = v12_1;
                                ((Entry)v2_4).setCount(((Entry)v2_4).getCount() + 1);
                                goto label_276;
                            }
                        }
                        else {
                            v26 = v2_2;
                        }

                        v30 = v8;
                        v31 = v9_1;
                        v29 = v12_1;
                        v2_5 = new Entry(1, ((com.coremedia.iso.boxes.fragment.TrackRunBox$Entry)v13).getSampleDuration());
                        goto label_259;
                    }
                    else {
                        v26 = v2_2;
                        v30 = v8;
                        v31 = v9_1;
                        v29 = v12_1;
                        v11_1 = 1;
                        v2_5 = v4_2.hasDefaultSampleDuration() ? new Entry(v11_1, v4_2.getDefaultSampleDuration()) : new Entry(v11_1, ((TrackExtendsBox)v6_1).getDefaultSampleDuration());
                    label_259:
                        ((List)v7).add(v2_5);
                    }

                label_276:
                    if(((TrackRunBox)v3_3).isSampleCompositionTimeOffsetPresent()) {
                        if(v0.compositionTimeEntries.size() != 0) {
                            if((((long)v0.compositionTimeEntries.get(v0.compositionTimeEntries.size() - 1).getOffset())) != ((com.coremedia.iso.boxes.fragment.TrackRunBox$Entry)v13).getSampleCompositionTimeOffset()) {
                            }
                            else {
                                v2_4 = v0.compositionTimeEntries.get(v0.compositionTimeEntries.size() - 1);
                                ((com.coremedia.iso.boxes.CompositionTimeToSample$Entry)v2_4).setCount(((com.coremedia.iso.boxes.CompositionTimeToSample$Entry)v2_4).getCount() + 1);
                                goto label_311;
                            }
                        }

                        v0.compositionTimeEntries.add(new com.coremedia.iso.boxes.CompositionTimeToSample$Entry(1, CastUtils.l2i(((com.coremedia.iso.boxes.fragment.TrackRunBox$Entry)v13).getSampleCompositionTimeOffset())));
                    }

                label_311:
                    if(((TrackRunBox)v3_3).isSampleFlagsPresent()) {
                        v2_6 = ((com.coremedia.iso.boxes.fragment.TrackRunBox$Entry)v13).getSampleFlags();
                    }
                    else {
                        if(v29 != 0 && (((TrackRunBox)v3_3).isFirstSampleFlagsPresent())) {
                            v2_6 = ((TrackRunBox)v3_3).getFirstSampleFlags();
                            goto label_325;
                        }

                        if(v4_2.hasDefaultSampleFlags()) {
                            v2_6 = v4_2.getDefaultSampleFlags();
                            goto label_325;
                        }

                        v2_6 = ((TrackExtendsBox)v6_1).getDefaultSampleFlags();
                    }

                label_325:
                    if(v2_6 != null && !v2_6.isSampleIsDifferenceSample()) {
                        ((List)v10_1).add(Long.valueOf(v14));
                    }

                    ++v14;
                    v2_2 = v26;
                    v8 = v30;
                    v9_1 = v31;
                    v11_2 = 1;
                }
            }

            v4 = v20;
            v11 = v22;
            v12 = v23;
            v2_2 = v24;
            v3 = 0;
            goto label_154;
        label_127:
            long[] v6_2 = v0.syncSamples;
            v0.syncSamples = new long[v0.syncSamples.length + ((List)v10_1).size()];
            System.arraycopy(v6_2, v3, v0.syncSamples, v3, v6_2.length);
            Iterator v16 = ((List)v10_1).iterator();
            int v6_3;
            for(v6_3 = v6_2.length; v16.hasNext(); ++v6_3) {
                v0.syncSamples[v6_3] = v16.next().longValue();
            }

            goto label_106;
        label_75:
            new ArrayList();
            new ArrayList();
            Iterator v6_4 = ((List)v8).iterator();
            while(v6_4.hasNext()) {
                v9_1 = v6_4.next().getBoxes(TrackFragmentBox.class).iterator();
                while(v9_1.hasNext()) {
                    v2_4 = v9_1.next();
                    if(((TrackFragmentBox)v2_4).getTrackFragmentHeaderBox().getTrackId() != v4) {
                        continue;
                    }

                    v0.sampleGroups = v0.getSampleGroups(Path.getPaths(((Container)v2_4), "sgpd"), Path.getPaths(((Container)v2_4), "sbgp"), v0.sampleGroups);
                }
            }
        }

    label_346:
        v0.decodingTimes = TimeToSampleBox.blowupTimeToSamples(((List)v7));
        MediaHeaderBox v2_7 = arg36.getMediaBox().getMediaHeaderBox();
        TrackHeaderBox v3_4 = arg36.getTrackHeaderBox();
        v0.trackMetaData.setTrackId(v3_4.getTrackId());
        v0.trackMetaData.setCreationTime(v2_7.getCreationTime());
        v0.trackMetaData.setLanguage(v2_7.getLanguage());
        v0.trackMetaData.setModificationTime(v2_7.getModificationTime());
        v0.trackMetaData.setTimescale(v2_7.getTimescale());
        v0.trackMetaData.setHeight(v3_4.getHeight());
        v0.trackMetaData.setWidth(v3_4.getWidth());
        v0.trackMetaData.setLayer(v3_4.getLayer());
        v0.trackMetaData.setMatrix(v3_4.getMatrix());
        v3_1 = Path.getPath(((AbstractContainerBox)v1), "edts/elst");
        Box v1_1 = Path.getPath(((AbstractContainerBox)v1), "../mvhd");
        if(v3_1 != null) {
            for(v3_2 = ((EditListBox)v3_1).getEntries().iterator(); v3_2.hasNext(); v3_2 = v33) {
                Object v4_3 = v3_2.next();
                List v5_2 = v0.edits;
                long v7_1 = ((com.coremedia.iso.boxes.EditListBox$Entry)v4_3).getMediaTime();
                long v9_2 = v2_7.getTimescale();
                double v11_3 = ((com.coremedia.iso.boxes.EditListBox$Entry)v4_3).getMediaRate();
                double v13_1 = ((double)((com.coremedia.iso.boxes.EditListBox$Entry)v4_3).getSegmentDuration());
                MediaHeaderBox v32 = v2_7;
                Iterator v33 = v3_2;
                double v2_8 = ((double)((MovieHeaderBox)v1_1).getTimescale());
                Double.isNaN(v13_1);
                Double.isNaN(v2_8);
                v5_2.add(new Edit(v7_1, v9_2, v11_3, v13_1 / v2_8));
                v2_7 = v32;
            }
        }
    }

    public void close() {
        Container v0 = this.trackBox.getParent();
        if((v0 instanceof BasicContainer)) {
            ((BasicContainer)v0).close();
        }

        IsoFile[] v0_1 = this.fragments;
        int v1 = v0_1.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0_1[v2].close();
        }
    }

    public List getCompositionTimeEntries() {
        return this.compositionTimeEntries;
    }

    public String getHandler() {
        return this.handler;
    }

    public List getSampleDependencies() {
        return this.sampleDependencies;
    }

    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.sampleDescriptionBox;
    }

    public long[] getSampleDurations() {
        long[] v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.decodingTimes;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    private Map getSampleGroups(List arg19, List arg20, Map arg21) {
        long[] v10_1;
        Iterator v7_1;
        Object v7;
        int v6;
        Object v2;
        Map v0 = arg21;
        Iterator v1 = arg19.iterator();
        while(true) {
            if(!v1.hasNext()) {
                return v0;
            }

            v2 = v1.next();
            Iterator v3 = arg20.iterator();
            v6 = 0;
            do {
            label_10:
                if(!v3.hasNext()) {
                    goto label_12;
                }

                v7 = v3.next();
            }
            while(!((SampleToGroupBox)v7).getGroupingType().equals(((SampleGroupDescriptionBox)v2).getGroupEntries().get(0).getType()));

            v7_1 = ((SampleToGroupBox)v7).getEntries().iterator();
            v6 = 0;
            goto label_37;
        label_12:
            if(v6 == 0) {
                break;
            }
        }

        StringBuilder v1_1 = new StringBuilder("Could not find SampleToGroupBox for ");
        v1_1.append(((SampleGroupDescriptionBox)v2).getGroupEntries().get(0).getType());
        v1_1.append(".");
        throw new RuntimeException(v1_1.toString());
    label_37:
        while(v7_1.hasNext()) {
            Object v8 = v7_1.next();
            if(((com.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleToGroupBox$Entry)v8).getGroupDescriptionIndex() > 0) {
                Object v9 = ((SampleGroupDescriptionBox)v2).getGroupEntries().get(((com.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleToGroupBox$Entry)v8).getGroupDescriptionIndex() - 1);
                Object v10 = v0.get(v9);
                if(v10 == null) {
                    v10_1 = new long[0];
                }

                long[] v11 = new long[CastUtils.l2i(((com.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleToGroupBox$Entry)v8).getSampleCount()) + v10_1.length];
                System.arraycopy(v10_1, 0, v11, 0, v10_1.length);
                int v12;
                for(v12 = 0; (((long)v12)) < ((com.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleToGroupBox$Entry)v8).getSampleCount(); ++v12) {
                    v11[v10_1.length + v12] = ((long)(v6 + v12));
                }

                v0.put(v9, v11);
            }

            v6 = ((int)((((long)v6)) + ((com.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleToGroupBox$Entry)v8).getSampleCount()));
        }

        v6 = 1;
        goto label_10;
        return v0;
    }

    public List getSamples() {
        return this.samples;
    }

    public SubSampleInformationBox getSubsampleInformationBox() {
        return this.subSampleInformationBox;
    }

    public long[] getSyncSamples() {
        if(this.syncSamples.length == this.samples.size()) {
            return null;
        }

        return this.syncSamples;
    }

    public TrackMetaData getTrackMetaData() {
        return this.trackMetaData;
    }
}

