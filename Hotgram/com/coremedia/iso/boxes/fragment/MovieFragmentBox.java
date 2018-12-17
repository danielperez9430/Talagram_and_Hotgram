package com.coremedia.iso.boxes.fragment;

import com.coremedia.iso.boxes.SampleDependencyTypeBox;
import com.googlecode.mp4parser.AbstractContainerBox;
import com.googlecode.mp4parser.DataSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MovieFragmentBox extends AbstractContainerBox {
    public static final String TYPE = "moof";

    public MovieFragmentBox() {
        super("moof");
    }

    public DataSource getFileChannel() {
        return this.dataSource;
    }

    public List getSyncSamples(SampleDependencyTypeBox arg8) {
        ArrayList v0 = new ArrayList();
        Iterator v8 = arg8.getEntries().iterator();
        long v1 = 1;
        long v3;
        for(v3 = v1; v8.hasNext(); v3 += v1) {
            if(v8.next().getSampleDependsOn() == 2) {
                ((List)v0).add(Long.valueOf(v3));
            }
        }

        return ((List)v0);
    }

    public int getTrackCount() {
        return this.getBoxes(TrackFragmentBox.class, false).size();
    }

    public List getTrackFragmentHeaderBoxes() {
        return this.getBoxes(TrackFragmentHeaderBox.class, true);
    }

    public long[] getTrackNumbers() {
        int v1 = 0;
        List v0 = this.getBoxes(TrackFragmentBox.class, false);
        long[] v2 = new long[v0.size()];
        while(v1 < v0.size()) {
            v2[v1] = v0.get(v1).getTrackFragmentHeaderBox().getTrackId();
            ++v1;
        }

        return v2;
    }

    public List getTrackRunBoxes() {
        return this.getBoxes(TrackRunBox.class, true);
    }
}

