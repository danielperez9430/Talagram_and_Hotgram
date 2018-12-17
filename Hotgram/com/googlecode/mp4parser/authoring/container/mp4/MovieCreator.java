package com.googlecode.mp4parser.authoring.container.mp4;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.SchemeTypeBox;
import com.coremedia.iso.boxes.TrackBox;
import com.googlecode.mp4parser.AbstractContainerBox;
import com.googlecode.mp4parser.DataSource;
import com.googlecode.mp4parser.FileDataSourceImpl;
import com.googlecode.mp4parser.authoring.CencMp4TrackImplImpl;
import com.googlecode.mp4parser.authoring.Movie;
import com.googlecode.mp4parser.authoring.Mp4TrackImpl;
import com.googlecode.mp4parser.authoring.Track;
import com.googlecode.mp4parser.util.Path;
import java.io.File;
import java.util.Iterator;

public class MovieCreator {
    public MovieCreator() {
        super();
    }

    public static Movie build(DataSource arg9) {
        Mp4TrackImpl v4_2;
        StringBuilder v6;
        IsoFile v0 = new IsoFile(arg9);
        Movie v1 = new Movie();
        Iterator v2 = v0.getMovieBox().getBoxes(TrackBox.class).iterator();
        while(v2.hasNext()) {
            Object v3 = v2.next();
            Box v4 = Path.getPath(((AbstractContainerBox)v3), "mdia[0]/minf[0]/stbl[0]/stsd[0]/enc.[0]/sinf[0]/schm[0]");
            if(v4 != null) {
                if(!((SchemeTypeBox)v4).getSchemeType().equals("cenc") && !((SchemeTypeBox)v4).getSchemeType().equals("cbc1")) {
                    goto label_44;
                }

                v6 = new StringBuilder(String.valueOf(arg9.toString()));
                v6.append("[");
                v6.append(((TrackBox)v3).getTrackHeaderBox().getTrackId());
                v6.append("]");
                CencMp4TrackImplImpl v4_1 = new CencMp4TrackImplImpl(v6.toString(), ((TrackBox)v3), new IsoFile[0]);
            }
            else {
            label_44:
                v6 = new StringBuilder(String.valueOf(arg9.toString()));
                v6.append("[");
                v6.append(((TrackBox)v3).getTrackHeaderBox().getTrackId());
                v6.append("]");
                v4_2 = new Mp4TrackImpl(v6.toString(), ((TrackBox)v3), new IsoFile[0]);
            }

            v1.addTrack(((Track)v4_2));
        }

        v1.setMatrix(v0.getMovieBox().getMovieHeaderBox().getMatrix());
        return v1;
    }

    public static Movie build(String arg2) {
        return MovieCreator.build(new FileDataSourceImpl(new File(arg2)));
    }
}

