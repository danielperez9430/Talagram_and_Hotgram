package com.coremedia.iso.boxes.mdat;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.boxes.Container;
import com.coremedia.iso.boxes.TrackBox;
import com.coremedia.iso.boxes.fragment.MovieExtendsBox;
import com.googlecode.mp4parser.authoring.Sample;
import com.googlecode.mp4parser.authoring.samples.DefaultMp4SampleList;
import com.googlecode.mp4parser.authoring.samples.FragmentedMp4SampleList;
import java.util.AbstractList;
import java.util.List;

public class SampleList extends AbstractList {
    List samples;

    public SampleList(TrackBox arg5, IsoFile[] arg6) {
        super();
        Container v0 = arg5.getParent().getParent();
        if(!arg5.getParent().getBoxes(MovieExtendsBox.class).isEmpty()) {
            this.samples = new FragmentedMp4SampleList(arg5.getTrackHeaderBox().getTrackId(), v0, arg6);
        }
        else if(arg6.length <= 0) {
            this.samples = new DefaultMp4SampleList(arg5.getTrackHeaderBox().getTrackId(), v0);
        }
        else {
            throw new RuntimeException("The TrackBox comes from a standard MP4 file. Only use the additionalFragments param if you are dealing with ( fragmented MP4 files AND additional fragments in standalone files )");
        }
    }

    public Sample get(int arg2) {
        return this.samples.get(arg2);
    }

    public Object get(int arg1) {
        return this.get(arg1);
    }

    public int size() {
        return this.samples.size();
    }
}

