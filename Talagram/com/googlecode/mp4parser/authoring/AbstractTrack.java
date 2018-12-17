package com.googlecode.mp4parser.authoring;

import com.coremedia.iso.boxes.SubSampleInformationBox;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractTrack implements Track {
    List edits;
    String name;
    Map sampleGroups;

    public AbstractTrack(String arg2) {
        super();
        this.edits = new ArrayList();
        this.sampleGroups = new HashMap();
        this.name = arg2;
    }

    public List getCompositionTimeEntries() {
        return null;
    }

    public long getDuration() {
        long[] v0 = this.getSampleDurations();
        int v1 = v0.length;
        long v2 = 0;
        int v4;
        for(v4 = 0; v4 < v1; ++v4) {
            v2 += v0[v4];
        }

        return v2;
    }

    public List getEdits() {
        return this.edits;
    }

    public String getName() {
        return this.name;
    }

    public List getSampleDependencies() {
        return null;
    }

    public Map getSampleGroups() {
        return this.sampleGroups;
    }

    public SubSampleInformationBox getSubsampleInformationBox() {
        return null;
    }

    public long[] getSyncSamples() {
        return null;
    }
}

