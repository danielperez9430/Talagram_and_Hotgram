package org.telegram.messenger.video;

import android.media.MediaCodec$BufferInfo;
import android.media.MediaFormat;
import com.coremedia.iso.BoxParser;
import com.coremedia.iso.IsoFile;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.CompositionTimeToSample$Entry;
import com.coremedia.iso.boxes.CompositionTimeToSample;
import com.coremedia.iso.boxes.Container;
import com.coremedia.iso.boxes.DataEntryUrlBox;
import com.coremedia.iso.boxes.DataInformationBox;
import com.coremedia.iso.boxes.DataReferenceBox;
import com.coremedia.iso.boxes.FileTypeBox;
import com.coremedia.iso.boxes.HandlerBox;
import com.coremedia.iso.boxes.MediaBox;
import com.coremedia.iso.boxes.MediaHeaderBox;
import com.coremedia.iso.boxes.MediaInformationBox;
import com.coremedia.iso.boxes.MovieBox;
import com.coremedia.iso.boxes.MovieHeaderBox;
import com.coremedia.iso.boxes.SampleSizeBox;
import com.coremedia.iso.boxes.SampleTableBox;
import com.coremedia.iso.boxes.SampleToChunkBox;
import com.coremedia.iso.boxes.StaticChunkOffsetBox;
import com.coremedia.iso.boxes.SyncSampleBox;
import com.coremedia.iso.boxes.TimeToSampleBox;
import com.coremedia.iso.boxes.TrackBox;
import com.coremedia.iso.boxes.TrackHeaderBox;
import com.googlecode.mp4parser.DataSource;
import com.googlecode.mp4parser.util.Matrix;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MP4Builder {
    class org.telegram.messenger.video.MP4Builder$1 {
    }

    class InterleaveChunkMdat implements Box {
        private long contentSize;
        private long dataOffset;
        private Container parent;

        InterleaveChunkMdat(MP4Builder arg1, org.telegram.messenger.video.MP4Builder$1 arg2) {
            this(arg1);
        }

        private InterleaveChunkMdat(MP4Builder arg3) {
            MP4Builder.this = arg3;
            super();
            this.contentSize = 1073741824;
            this.dataOffset = 0;
        }

        public void getBox(WritableByteChannel arg6) {
            ByteBuffer v0 = ByteBuffer.allocate(16);
            long v1 = this.getSize();
            if(this.isSmallBox(v1)) {
                IsoTypeWriter.writeUInt32(v0, v1);
            }
            else {
                IsoTypeWriter.writeUInt32(v0, 1);
            }

            v0.put(IsoFile.fourCCtoBytes("mdat"));
            if(this.isSmallBox(v1)) {
                v0.put(new byte[8]);
            }
            else {
                IsoTypeWriter.writeUInt64(v0, v1);
            }

            v0.rewind();
            arg6.write(v0);
        }

        public long getContentSize() {
            return this.contentSize;
        }

        public long getOffset() {
            return this.dataOffset;
        }

        public Container getParent() {
            return this.parent;
        }

        public long getSize() {
            return this.contentSize + 16;
        }

        public String getType() {
            return "mdat";
        }

        private boolean isSmallBox(long arg4) {
            boolean v4 = arg4 + 8 < 4294967296L ? true : false;
            return v4;
        }

        public void parse(DataSource arg1, ByteBuffer arg2, long arg3, BoxParser arg5) {
        }

        public void setContentSize(long arg1) {
            this.contentSize = arg1;
        }

        public void setDataOffset(long arg1) {
            this.dataOffset = arg1;
        }

        public void setParent(Container arg1) {
            this.parent = arg1;
        }
    }

    private Mp4Movie currentMp4Movie;
    private long dataOffset;
    private FileChannel fc;
    private FileOutputStream fos;
    private InterleaveChunkMdat mdat;
    private ByteBuffer sizeBuffer;
    private boolean splitMdat;
    private HashMap track2SampleSizes;
    private boolean writeNewMdat;
    private long writedSinceLastMdat;

    public MP4Builder() {
        super();
        this.mdat = null;
        this.currentMp4Movie = null;
        this.fos = null;
        this.fc = null;
        this.dataOffset = 0;
        this.writedSinceLastMdat = 0;
        this.writeNewMdat = true;
        this.track2SampleSizes = new HashMap();
        this.sizeBuffer = null;
    }

    public int addTrack(MediaFormat arg2, boolean arg3) {
        return this.currentMp4Movie.addTrack(arg2, arg3);
    }

    protected void createCtts(Track arg7, SampleTableBox arg8) {
        int[] v7 = arg7.getSampleCompositions();
        if(v7 == null) {
            return;
        }

        Entry v0 = null;
        ArrayList v1 = new ArrayList();
        int v2;
        for(v2 = 0; v2 < v7.length; ++v2) {
            int v3 = v7[v2];
            if(v0 == null || v0.getOffset() != v3) {
                v0 = new Entry(1, v3);
                ((List)v1).add(v0);
            }
            else {
                v0.setCount(v0.getCount() + 1);
            }
        }

        CompositionTimeToSample v7_1 = new CompositionTimeToSample();
        v7_1.setEntries(((List)v1));
        arg8.addBox(((Box)v7_1));
    }

    protected FileTypeBox createFileTypeBox() {
        LinkedList v0 = new LinkedList();
        v0.add("isom");
        v0.add("iso2");
        v0.add("avc1");
        v0.add("mp41");
        return new FileTypeBox("isom", 512, ((List)v0));
    }

    public MP4Builder createMovie(Mp4Movie arg5, boolean arg6) {
        this.currentMp4Movie = arg5;
        this.fos = new FileOutputStream(arg5.getCacheFile());
        this.fc = this.fos.getChannel();
        FileTypeBox v5 = this.createFileTypeBox();
        v5.getBox(this.fc);
        this.dataOffset += v5.getSize();
        this.writedSinceLastMdat += this.dataOffset;
        this.splitMdat = arg6;
        this.mdat = new InterleaveChunkMdat(this, null);
        this.sizeBuffer = ByteBuffer.allocateDirect(4);
        return this;
    }

    protected MovieBox createMovieBox(Mp4Movie arg13) {
        MovieBox v0 = new MovieBox();
        MovieHeaderBox v1 = new MovieHeaderBox();
        v1.setCreationTime(new Date());
        v1.setModificationTime(new Date());
        v1.setMatrix(Matrix.ROTATE_0);
        long v2 = this.getTimescale(arg13);
        Iterator v4 = arg13.getTracks().iterator();
        long v5;
        for(v5 = 0; v4.hasNext(); v5 = v8) {
            Object v7 = v4.next();
            ((Track)v7).prepare();
            long v8 = ((Track)v7).getDuration() * v2 / (((long)((Track)v7).getTimeScale()));
            if(v8 <= v5) {
                continue;
            }
        }

        v1.setDuration(v5);
        v1.setTimescale(v2);
        v1.setNextTrackId(((long)(arg13.getTracks().size() + 1)));
        v0.addBox(((Box)v1));
        Iterator v1_1 = arg13.getTracks().iterator();
        while(v1_1.hasNext()) {
            v0.addBox(this.createTrackBox(v1_1.next(), arg13));
        }

        return v0;
    }

    protected Box createStbl(Track arg2) {
        SampleTableBox v0 = new SampleTableBox();
        this.createStsd(arg2, v0);
        this.createStts(arg2, v0);
        this.createCtts(arg2, v0);
        this.createStss(arg2, v0);
        this.createStsc(arg2, v0);
        this.createStsz(arg2, v0);
        this.createStco(arg2, v0);
        return ((Box)v0);
    }

    protected void createStco(Track arg10, SampleTableBox arg11) {
        ArrayList v0 = new ArrayList();
        Iterator v10 = arg10.getSamples().iterator();
        long v1 = -1;
        long v3;
        for(v3 = v1; v10.hasNext(); v3 = ((Sample)v5).getSize() + v6) {
            Object v5 = v10.next();
            long v6 = ((Sample)v5).getOffset();
            if(v3 != v1 && v3 != v6) {
                v3 = v1;
            }

            if(v3 == v1) {
                v0.add(Long.valueOf(v6));
            }
        }

        long[] v10_1 = new long[v0.size()];
        int v1_1;
        for(v1_1 = 0; v1_1 < v0.size(); ++v1_1) {
            v10_1[v1_1] = v0.get(v1_1).longValue();
        }

        StaticChunkOffsetBox v0_1 = new StaticChunkOffsetBox();
        v0_1.setChunkOffsets(v10_1);
        arg11.addBox(((Box)v0_1));
    }

    protected void createStsc(Track arg17, SampleTableBox arg18) {
        SampleToChunkBox v0 = new SampleToChunkBox();
        v0.setEntries(new LinkedList());
        int v1 = arg17.getSamples().size();
        int v4 = 0;
        int v5 = 0;
        int v6 = -1;
        int v7 = 1;
        while(v4 < v1) {
            Object v8 = arg17.getSamples().get(v4);
            long v9 = ((Sample)v8).getOffset() + ((Sample)v8).getSize();
            ++v5;
            int v8_1 = v4 == v1 - 1 || v9 != arg17.getSamples().get(v4 + 1).getOffset() ? 1 : 0;
            if(v8_1 != 0) {
                if(v6 != v5) {
                    v0.getEntries().add(new com.coremedia.iso.boxes.SampleToChunkBox$Entry(((long)v7), ((long)v5), 1));
                }
                else {
                    v5 = v6;
                }

                ++v7;
                v6 = v5;
                v5 = 0;
            }

            ++v4;
        }

        arg18.addBox(((Box)v0));
    }

    protected void createStsd(Track arg1, SampleTableBox arg2) {
        arg2.addBox(arg1.getSampleDescriptionBox());
    }

    protected void createStss(Track arg2, SampleTableBox arg3) {
        long[] v2 = arg2.getSyncSamples();
        if(v2 != null && v2.length > 0) {
            SyncSampleBox v0 = new SyncSampleBox();
            v0.setSampleNumber(v2);
            arg3.addBox(((Box)v0));
        }
    }

    protected void createStsz(Track arg3, SampleTableBox arg4) {
        SampleSizeBox v0 = new SampleSizeBox();
        v0.setSampleSizes(this.track2SampleSizes.get(arg3));
        arg4.addBox(((Box)v0));
    }

    protected void createStts(Track arg11, SampleTableBox arg12) {
        ArrayList v0 = new ArrayList();
        long[] v11 = arg11.getSampleDurations();
        com.coremedia.iso.boxes.TimeToSampleBox$Entry v1 = null;
        int v2;
        for(v2 = 0; v2 < v11.length; ++v2) {
            long v3 = v11[v2];
            long v5 = 1;
            if(v1 == null || v1.getDelta() != v3) {
                v1 = new com.coremedia.iso.boxes.TimeToSampleBox$Entry(v5, v3);
                ((List)v0).add(v1);
            }
            else {
                v1.setCount(v1.getCount() + v5);
            }
        }

        TimeToSampleBox v11_1 = new TimeToSampleBox();
        v11_1.setEntries(((List)v0));
        arg12.addBox(((Box)v11_1));
    }

    protected TrackBox createTrackBox(Track arg9, Mp4Movie arg10) {
        TrackBox v0 = new TrackBox();
        TrackHeaderBox v1 = new TrackHeaderBox();
        v1.setEnabled(true);
        v1.setInMovie(true);
        v1.setInPreview(true);
        Matrix v3 = arg9.isAudio() ? Matrix.ROTATE_0 : arg10.getMatrix();
        v1.setMatrix(v3);
        v1.setAlternateGroup(0);
        v1.setCreationTime(arg9.getCreationTime());
        v1.setDuration(arg9.getDuration() * this.getTimescale(arg10) / (((long)arg9.getTimeScale())));
        v1.setHeight(((double)arg9.getHeight()));
        v1.setWidth(((double)arg9.getWidth()));
        v1.setLayer(0);
        v1.setModificationTime(new Date());
        v1.setTrackId(arg9.getTrackId() + 1);
        v1.setVolume(arg9.getVolume());
        v0.addBox(((Box)v1));
        MediaBox v10 = new MediaBox();
        v0.addBox(((Box)v10));
        MediaHeaderBox v1_1 = new MediaHeaderBox();
        v1_1.setCreationTime(arg9.getCreationTime());
        v1_1.setDuration(arg9.getDuration());
        v1_1.setTimescale(((long)arg9.getTimeScale()));
        v1_1.setLanguage("eng");
        v10.addBox(((Box)v1_1));
        HandlerBox v1_2 = new HandlerBox();
        String v3_1 = arg9.isAudio() ? "SoundHandle" : "VideoHandle";
        v1_2.setName(v3_1);
        v1_2.setHandlerType(arg9.getHandler());
        v10.addBox(((Box)v1_2));
        MediaInformationBox v1_3 = new MediaInformationBox();
        v1_3.addBox(arg9.getMediaHeaderBox());
        DataInformationBox v3_2 = new DataInformationBox();
        DataReferenceBox v4 = new DataReferenceBox();
        v3_2.addBox(((Box)v4));
        DataEntryUrlBox v5 = new DataEntryUrlBox();
        v5.setFlags(1);
        v4.addBox(((Box)v5));
        v1_3.addBox(((Box)v3_2));
        v1_3.addBox(this.createStbl(arg9));
        v10.addBox(((Box)v1_3));
        return v0;
    }

    public void finishMovie() {
        if(this.mdat.getContentSize() != 0) {
            this.flushCurrentMdat();
        }

        Iterator v0 = this.currentMp4Movie.getTracks().iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            ArrayList v2 = ((Track)v1).getSamples();
            long[] v3 = new long[((List)v2).size()];
            int v4;
            for(v4 = 0; v4 < v3.length; ++v4) {
                v3[v4] = ((List)v2).get(v4).getSize();
            }

            this.track2SampleSizes.put(v1, v3);
        }

        this.createMovieBox(this.currentMp4Movie).getBox(this.fc);
        this.fos.flush();
        this.fos.getFD().sync();
        this.fc.close();
        this.fos.close();
    }

    private void flushCurrentMdat() {
        long v0 = this.fc.position();
        this.fc.position(this.mdat.getOffset());
        this.mdat.getBox(this.fc);
        this.fc.position(v0);
        this.mdat.setDataOffset(0);
        this.mdat.setContentSize(0);
        this.fos.flush();
        this.fos.getFD().sync();
    }

    public static long gcd(long arg3, long arg5) {
        if(arg5 == 0) {
            return arg3;
        }

        return MP4Builder.gcd(arg5, arg3 % arg5);
    }

    public long getTimescale(Mp4Movie arg5) {
        long v0 = !arg5.getTracks().isEmpty() ? ((long)arg5.getTracks().iterator().next().getTimeScale()) : 0;
        Iterator v5 = arg5.getTracks().iterator();
        while(v5.hasNext()) {
            v0 = MP4Builder.gcd(((long)v5.next().getTimeScale()), v0);
        }

        return v0;
    }

    public boolean writeSampleData(int arg9, ByteBuffer arg10, MediaCodec$BufferInfo arg11, boolean arg12) {
        // Method was not decompiled
    }
}

