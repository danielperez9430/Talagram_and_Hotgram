package com.googlecode.mp4parser.authoring.samples;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.boxes.Container;
import com.coremedia.iso.boxes.TrackBox;
import com.coremedia.iso.boxes.fragment.MovieFragmentBox;
import com.coremedia.iso.boxes.fragment.TrackExtendsBox;
import com.coremedia.iso.boxes.fragment.TrackFragmentBox;
import com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox;
import com.coremedia.iso.boxes.fragment.TrackRunBox$Entry;
import com.coremedia.iso.boxes.fragment.TrackRunBox;
import com.googlecode.mp4parser.authoring.Sample;
import com.googlecode.mp4parser.util.CastUtils;
import com.googlecode.mp4parser.util.Path;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FragmentedMp4SampleList extends AbstractList {
    private List allTrafs;
    private int[] firstSamples;
    IsoFile[] fragments;
    private SoftReference[] sampleCache;
    private int size_;
    Container topLevel;
    TrackBox trackBox;
    TrackExtendsBox trex;
    private Map trunDataCache;

    public FragmentedMp4SampleList(long arg5, Container arg7, IsoFile[] arg8) {
        Iterator v7;
        super();
        this.trackBox = null;
        this.trex = null;
        this.trunDataCache = new HashMap();
        this.size_ = -1;
        this.topLevel = arg7;
        this.fragments = arg8;
        Iterator v8 = Path.getPaths(arg7, "moov[0]/trak").iterator();
        while(v8.hasNext()) {
            Object v0 = v8.next();
            if(((TrackBox)v0).getTrackHeaderBox().getTrackId() != arg5) {
                continue;
            }

            this.trackBox = ((TrackBox)v0);
        }

        if(this.trackBox != null) {
            v7 = Path.getPaths(arg7, "moov[0]/mvex[0]/trex").iterator();
        }
        else {
            StringBuilder v8_1 = new StringBuilder("This MP4 does not contain track ");
            v8_1.append(arg5);
            throw new RuntimeException(v8_1.toString());
        }

        while(v7.hasNext()) {
            Object v5 = v7.next();
            if(((TrackExtendsBox)v5).getTrackId() != this.trackBox.getTrackHeaderBox().getTrackId()) {
                continue;
            }

            this.trex = ((TrackExtendsBox)v5);
        }

        this.sampleCache = Array.newInstance(SoftReference.class, this.size());
        this.initAllFragments();
    }

    public Sample get(int arg17) {
        ByteBuffer v5_1;
        int v3_3;
        Iterator v1_3;
        long v14;
        long v3_2;
        int v10;
        Object v8;
        FragmentedMp4SampleList v7 = this;
        if(v7.sampleCache[arg17] != null) {
            Object v1 = v7.sampleCache[arg17].get();
            if(v1 != null) {
                return ((Sample)v1);
            }
        }

        int v1_1 = arg17 + 1;
        int v2;
        for(v2 = v7.firstSamples.length - 1; v1_1 - v7.firstSamples[v2] < 0; --v2) {
        }

        Object v3 = v7.allTrafs.get(v2);
        v1_1 -= v7.firstSamples[v2];
        Container v2_1 = ((TrackFragmentBox)v3).getParent();
        Iterator v4 = ((TrackFragmentBox)v3).getBoxes().iterator();
        int v6;
        for(v6 = 0; true; v6 += ((TrackRunBox)v8).getEntries().size()) {
        label_26:
            if(!v4.hasNext()) {
                goto label_136;
            }

            v8 = v4.next();
            if(!(v8 instanceof TrackRunBox)) {
                goto label_26;
            }

            v10 = v1_1 - v6;
            if(((TrackRunBox)v8).getEntries().size() >= v10) {
                break;
            }
        }

        List v9 = ((TrackRunBox)v8).getEntries();
        TrackFragmentHeaderBox v1_2 = ((TrackFragmentBox)v3).getTrackFragmentHeaderBox();
        boolean v11 = ((TrackRunBox)v8).isSampleSizePresent();
        boolean v3_1 = v1_2.hasDefaultSampleSize();
        long v12 = 0;
        if(!v11) {
            if(v3_1) {
                v3_2 = v1_2.getDefaultSampleSize();
            }
            else if(v7.trex != null) {
                v3_2 = v7.trex.getDefaultSampleSize();
            }
            else {
                goto label_54;
            }

            v14 = v3_2;
            goto label_59;
        label_54:
            throw new RuntimeException("File doesn\'t contain trex box but track fragments aren\'t fully self contained. Cannot determine sample size.");
        }
        else {
            v14 = v12;
        }

    label_59:
        v3 = v7.trunDataCache.get(v8);
        v3 = v3 != null ? ((SoftReference)v3).get() : null;
        if(v3 == null) {
            if(v1_2.hasBaseDataOffset()) {
                v12 += v1_2.getBaseDataOffset();
                v2_1 = ((MovieFragmentBox)v2_1).getParent();
            }

            if(((TrackRunBox)v8).isDataOffsetPresent()) {
                v12 += ((long)((TrackRunBox)v8).getDataOffset());
            }

            v1_3 = v9.iterator();
            v3_3 = 0;
            goto label_78;
        }
        else {
            Object v5 = v3;
            goto label_104;
        label_136:
            throw new RuntimeException("Couldn\'t find sample in the traf I was looking");
        label_78:
            while(v1_3.hasNext()) {
                Object v4_1 = v1_3.next();
                if(v11) {
                    v3_3 = ((int)((((long)v3_3)) + ((Entry)v4_1).getSampleSize()));
                    continue;
                }

                v3_3 = ((int)((((long)v3_3)) + v14));
            }

            v3_2 = ((long)v3_3);
            try {
                ByteBuffer v1_4 = v2_1.getByteBuffer(v12, v3_2);
                v7.trunDataCache.put(v8, new SoftReference(v1_4));
                v5_1 = v1_4;
            }
            catch(IOException v0) {
                throw new RuntimeException(((Throwable)v0));
            }
        }

    label_104:
        v1_1 = 0;
        v6 = 0;
        while(v1_1 < v10) {
            long v2_2 = v11 ? (((long)v6)) + v9.get(v1_1).getSampleSize() : (((long)v6)) + v14;
            v6 = ((int)v2_2);
            ++v1_1;
        }

        v3_2 = v11 ? v9.get(v10).getSampleSize() : v14;
        com.googlecode.mp4parser.authoring.samples.FragmentedMp4SampleList$1 v8_1 = new Sample(v3_2, v5_1, v6) {
            public ByteBuffer asByteBuffer() {
                return this.val$finalTrunData.position(this.val$finalOffset).slice().limit(CastUtils.l2i(this.val$sampleSize));
            }

            public long getSize() {
                return this.val$sampleSize;
            }

            public void writeTo(WritableByteChannel arg2) {
                arg2.write(this.asByteBuffer());
            }
        };
        v7.sampleCache[arg17] = new SoftReference(v8_1);
        return ((Sample)v8_1);
    }

    public Object get(int arg1) {
        return this.get(arg1);
    }

    private int getTrafSize(TrackFragmentBox arg5) {
        List v5 = arg5.getBoxes();
        int v0 = 0;
        int v1 = 0;
        while(v0 < v5.size()) {
            Object v2 = v5.get(v0);
            if((v2 instanceof TrackRunBox)) {
                v1 += CastUtils.l2i(((TrackRunBox)v2).getSampleCount());
            }

            ++v0;
        }

        return v1;
    }

    private List initAllFragments() {
        if(this.allTrafs != null) {
            return this.allTrafs;
        }

        ArrayList v0 = new ArrayList();
        Iterator v1 = this.topLevel.getBoxes(MovieFragmentBox.class).iterator();
        while(v1.hasNext()) {
            Iterator v2 = v1.next().getBoxes(TrackFragmentBox.class).iterator();
            while(v2.hasNext()) {
                Object v3 = v2.next();
                if(((TrackFragmentBox)v3).getTrackFragmentHeaderBox().getTrackId() != this.trackBox.getTrackHeaderBox().getTrackId()) {
                    continue;
                }

                ((List)v0).add(v3);
            }
        }

        int v2_1 = 0;
        if(this.fragments != null) {
            IsoFile[] v1_1 = this.fragments;
            int v3_1 = v1_1.length;
            int v4;
            for(v4 = 0; v4 < v3_1; ++v4) {
                Iterator v5 = v1_1[v4].getBoxes(MovieFragmentBox.class).iterator();
                while(v5.hasNext()) {
                    Iterator v6 = v5.next().getBoxes(TrackFragmentBox.class).iterator();
                    while(v6.hasNext()) {
                        Object v7 = v6.next();
                        if(((TrackFragmentBox)v7).getTrackFragmentHeaderBox().getTrackId() != this.trackBox.getTrackHeaderBox().getTrackId()) {
                            continue;
                        }

                        ((List)v0).add(v7);
                    }
                }
            }
        }

        this.allTrafs = ((List)v0);
        this.firstSamples = new int[this.allTrafs.size()];
        int v1_2 = 1;
        while(v2_1 < this.allTrafs.size()) {
            this.firstSamples[v2_1] = v1_2;
            v1_2 += this.getTrafSize(this.allTrafs.get(v2_1));
            ++v2_1;
        }

        return ((List)v0);
    }

    public int size() {
        if(this.size_ != -1) {
            return this.size_;
        }

        Iterator v0 = this.topLevel.getBoxes(MovieFragmentBox.class).iterator();
        int v2 = 0;
        while(v0.hasNext()) {
            Iterator v3 = v0.next().getBoxes(TrackFragmentBox.class).iterator();
            while(v3.hasNext()) {
                Object v4 = v3.next();
                if(((TrackFragmentBox)v4).getTrackFragmentHeaderBox().getTrackId() != this.trackBox.getTrackHeaderBox().getTrackId()) {
                    continue;
                }

                v2 = ((int)((((long)v2)) + ((TrackFragmentBox)v4).getBoxes(TrackRunBox.class).get(0).getSampleCount()));
            }
        }

        IsoFile[] v3_1 = this.fragments;
        int v4_1 = v3_1.length;
        int v5;
        for(v5 = 0; v5 < v4_1; ++v5) {
            Iterator v6 = v3_1[v5].getBoxes(MovieFragmentBox.class).iterator();
            while(v6.hasNext()) {
                Iterator v7 = v6.next().getBoxes(TrackFragmentBox.class).iterator();
                while(v7.hasNext()) {
                    Object v0_1 = v7.next();
                    if(((TrackFragmentBox)v0_1).getTrackFragmentHeaderBox().getTrackId() != this.trackBox.getTrackHeaderBox().getTrackId()) {
                        continue;
                    }

                    v2 = ((int)((((long)v2)) + ((TrackFragmentBox)v0_1).getBoxes(TrackRunBox.class).get(0).getSampleCount()));
                }
            }
        }

        this.size_ = v2;
        return v2;
    }
}

