package com.googlecode.mp4parser.authoring;

import com.coremedia.iso.boxes.SampleDescriptionBox;
import com.coremedia.iso.boxes.SubSampleInformationBox;
import java.util.List;
import java.util.Map;

public class WrappingTrack implements Track {
    Track parent;

    public WrappingTrack(Track arg1) {
        super();
        this.parent = arg1;
    }

    public void close() {
        this.parent.close();
    }

    public List getCompositionTimeEntries() {
        return this.parent.getCompositionTimeEntries();
    }

    public long getDuration() {
        return this.parent.getDuration();
    }

    public List getEdits() {
        return this.parent.getEdits();
    }

    public String getHandler() {
        return this.parent.getHandler();
    }

    public String getName() {
        StringBuilder v0 = new StringBuilder(String.valueOf(this.parent.getName()));
        v0.append("\'");
        return v0.toString();
    }

    public List getSampleDependencies() {
        return this.parent.getSampleDependencies();
    }

    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.parent.getSampleDescriptionBox();
    }

    public long[] getSampleDurations() {
        return this.parent.getSampleDurations();
    }

    public Map getSampleGroups() {
        return this.parent.getSampleGroups();
    }

    public List getSamples() {
        return this.parent.getSamples();
    }

    public SubSampleInformationBox getSubsampleInformationBox() {
        return this.parent.getSubsampleInformationBox();
    }

    public long[] getSyncSamples() {
        return this.parent.getSyncSamples();
    }

    public TrackMetaData getTrackMetaData() {
        return this.parent.getTrackMetaData();
    }
}

