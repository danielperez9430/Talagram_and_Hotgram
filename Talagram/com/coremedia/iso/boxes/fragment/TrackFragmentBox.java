package com.coremedia.iso.boxes.fragment;

import com.googlecode.mp4parser.AbstractContainerBox;
import com.googlecode.mp4parser.annotations.DoNotParseDetail;
import java.util.Iterator;

public class TrackFragmentBox extends AbstractContainerBox {
    public static final String TYPE = "traf";

    public TrackFragmentBox() {
        super("traf");
    }

    @DoNotParseDetail public TrackFragmentHeaderBox getTrackFragmentHeaderBox() {
        Object v1;
        Iterator v0 = this.getBoxes().iterator();
        do {
            if(!v0.hasNext()) {
                return null;
            }

            v1 = v0.next();
        }
        while(!(v1 instanceof TrackFragmentHeaderBox));

        return ((TrackFragmentHeaderBox)v1);
    }
}

