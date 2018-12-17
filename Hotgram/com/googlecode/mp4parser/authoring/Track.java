package com.googlecode.mp4parser.authoring;

import com.coremedia.iso.boxes.SampleDescriptionBox;
import com.coremedia.iso.boxes.SubSampleInformationBox;
import java.io.Closeable;
import java.util.List;
import java.util.Map;

public interface Track extends Closeable {
    List getCompositionTimeEntries();

    long getDuration();

    List getEdits();

    String getHandler();

    String getName();

    List getSampleDependencies();

    SampleDescriptionBox getSampleDescriptionBox();

    long[] getSampleDurations();

    Map getSampleGroups();

    List getSamples();

    SubSampleInformationBox getSubsampleInformationBox();

    long[] getSyncSamples();

    TrackMetaData getTrackMetaData();
}

