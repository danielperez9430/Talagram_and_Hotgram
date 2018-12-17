package com.coremedia.iso.boxes;

import com.googlecode.mp4parser.AbstractContainerBox;
import java.util.Iterator;
import java.util.List;

public class TrackBox extends AbstractContainerBox {
    public static final String TYPE = "trak";
    private SampleTableBox sampleTableBox;

    public TrackBox() {
        super("trak");
    }

    public MediaBox getMediaBox() {
        Object v1;
        Iterator v0 = this.getBoxes().iterator();
        do {
            if(!v0.hasNext()) {
                return null;
            }

            v1 = v0.next();
        }
        while(!(v1 instanceof MediaBox));

        return ((MediaBox)v1);
    }

    public SampleTableBox getSampleTableBox() {
        if(this.sampleTableBox != null) {
            return this.sampleTableBox;
        }

        MediaBox v0 = this.getMediaBox();
        if(v0 != null) {
            MediaInformationBox v0_1 = v0.getMediaInformationBox();
            if(v0_1 != null) {
                this.sampleTableBox = v0_1.getSampleTableBox();
                return this.sampleTableBox;
            }
        }

        return null;
    }

    public TrackHeaderBox getTrackHeaderBox() {
        Object v1;
        Iterator v0 = this.getBoxes().iterator();
        do {
            if(!v0.hasNext()) {
                return null;
            }

            v1 = v0.next();
        }
        while(!(v1 instanceof TrackHeaderBox));

        return ((TrackHeaderBox)v1);
    }

    public void setBoxes(List arg1) {
        super.setBoxes(arg1);
        this.sampleTableBox = null;
    }
}

