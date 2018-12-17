package com.coremedia.iso.boxes;

import com.googlecode.mp4parser.AbstractContainerBox;
import java.util.Iterator;

public class SampleTableBox extends AbstractContainerBox {
    public static final String TYPE = "stbl";
    private SampleToChunkBox sampleToChunkBox;

    public SampleTableBox() {
        super("stbl");
    }

    public ChunkOffsetBox getChunkOffsetBox() {
        Object v1;
        Iterator v0 = this.getBoxes().iterator();
        do {
            if(!v0.hasNext()) {
                return null;
            }

            v1 = v0.next();
        }
        while(!(v1 instanceof ChunkOffsetBox));

        return ((ChunkOffsetBox)v1);
    }

    public CompositionTimeToSample getCompositionTimeToSample() {
        Object v1;
        Iterator v0 = this.getBoxes().iterator();
        do {
            if(!v0.hasNext()) {
                return null;
            }

            v1 = v0.next();
        }
        while(!(v1 instanceof CompositionTimeToSample));

        return ((CompositionTimeToSample)v1);
    }

    public SampleDependencyTypeBox getSampleDependencyTypeBox() {
        Object v1;
        Iterator v0 = this.getBoxes().iterator();
        do {
            if(!v0.hasNext()) {
                return null;
            }

            v1 = v0.next();
        }
        while(!(v1 instanceof SampleDependencyTypeBox));

        return ((SampleDependencyTypeBox)v1);
    }

    public SampleDescriptionBox getSampleDescriptionBox() {
        Object v1;
        Iterator v0 = this.getBoxes().iterator();
        do {
            if(!v0.hasNext()) {
                return null;
            }

            v1 = v0.next();
        }
        while(!(v1 instanceof SampleDescriptionBox));

        return ((SampleDescriptionBox)v1);
    }

    public SampleSizeBox getSampleSizeBox() {
        Object v1;
        Iterator v0 = this.getBoxes().iterator();
        do {
            if(!v0.hasNext()) {
                return null;
            }

            v1 = v0.next();
        }
        while(!(v1 instanceof SampleSizeBox));

        return ((SampleSizeBox)v1);
    }

    public SampleToChunkBox getSampleToChunkBox() {
        Object v1;
        if(this.sampleToChunkBox != null) {
            return this.sampleToChunkBox;
        }

        Iterator v0 = this.getBoxes().iterator();
        do {
            if(!v0.hasNext()) {
                return null;
            }

            v1 = v0.next();
        }
        while(!(v1 instanceof SampleToChunkBox));

        this.sampleToChunkBox = ((SampleToChunkBox)v1);
        return this.sampleToChunkBox;
    }

    public SyncSampleBox getSyncSampleBox() {
        Object v1;
        Iterator v0 = this.getBoxes().iterator();
        do {
            if(!v0.hasNext()) {
                return null;
            }

            v1 = v0.next();
        }
        while(!(v1 instanceof SyncSampleBox));

        return ((SyncSampleBox)v1);
    }

    public TimeToSampleBox getTimeToSampleBox() {
        Object v1;
        Iterator v0 = this.getBoxes().iterator();
        do {
            if(!v0.hasNext()) {
                return null;
            }

            v1 = v0.next();
        }
        while(!(v1 instanceof TimeToSampleBox));

        return ((TimeToSampleBox)v1);
    }
}

