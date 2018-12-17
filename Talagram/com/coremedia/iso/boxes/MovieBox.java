package com.coremedia.iso.boxes;

import com.googlecode.mp4parser.AbstractContainerBox;
import java.util.Iterator;
import java.util.List;

public class MovieBox extends AbstractContainerBox {
    public static final String TYPE = "moov";

    public MovieBox() {
        super("moov");
    }

    public MovieHeaderBox getMovieHeaderBox() {
        Object v1;
        Iterator v0 = this.getBoxes().iterator();
        do {
            if(!v0.hasNext()) {
                return null;
            }

            v1 = v0.next();
        }
        while(!(v1 instanceof MovieHeaderBox));

        return ((MovieHeaderBox)v1);
    }

    public int getTrackCount() {
        return this.getBoxes(TrackBox.class).size();
    }

    public long[] getTrackNumbers() {
        List v0 = this.getBoxes(TrackBox.class);
        long[] v1 = new long[v0.size()];
        int v2;
        for(v2 = 0; v2 < v0.size(); ++v2) {
            v1[v2] = v0.get(v2).getTrackHeaderBox().getTrackId();
        }

        return v1;
    }
}

