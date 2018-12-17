package com.googlecode.mp4parser.authoring;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.ChunkOffsetBox;
import com.coremedia.iso.boxes.Container;
import com.coremedia.iso.boxes.TrackBox;
import com.coremedia.iso.boxes.fragment.MovieExtendsBox;
import com.coremedia.iso.boxes.fragment.MovieFragmentBox;
import com.coremedia.iso.boxes.fragment.TrackFragmentBox;
import com.coremedia.iso.boxes.fragment.TrackRunBox;
import com.googlecode.mp4parser.AbstractContainerBox;
import com.googlecode.mp4parser.authoring.tracks.CencEncryptedTrack;
import com.googlecode.mp4parser.util.Path;
import com.mp4parser.iso14496.part12.SampleAuxiliaryInformationOffsetsBox;
import com.mp4parser.iso14496.part12.SampleAuxiliaryInformationSizesBox;
import com.mp4parser.iso23001.part7.TrackEncryptionBox;
import com.mp4parser.iso23001.part7.a$j;
import com.mp4parser.iso23001.part7.a;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class CencMp4TrackImplImpl extends Mp4TrackImpl implements CencEncryptedTrack {
    class FindSaioSaizPair {
        private Container container;
        private SampleAuxiliaryInformationOffsetsBox saio;
        private SampleAuxiliaryInformationSizesBox saiz;

        static {
        }

        public FindSaioSaizPair(CencMp4TrackImplImpl arg1, Container arg2) {
            CencMp4TrackImplImpl.this = arg1;
            super();
            this.container = arg2;
        }

        static SampleAuxiliaryInformationOffsetsBox access$0(FindSaioSaizPair arg0) {
            return arg0.saio;
        }

        static SampleAuxiliaryInformationSizesBox access$1(FindSaioSaizPair arg0) {
            return arg0.saiz;
        }

        public SampleAuxiliaryInformationOffsetsBox getSaio() {
            return this.saio;
        }

        public SampleAuxiliaryInformationSizesBox getSaiz() {
            return this.saiz;
        }

        public FindSaioSaizPair invoke() {
            List v0 = this.container.getBoxes(SampleAuxiliaryInformationSizesBox.class);
            List v1 = this.container.getBoxes(SampleAuxiliaryInformationOffsetsBox.class);
            this.saiz = null;
            this.saio = null;
            int v2;
            for(v2 = 0; true; ++v2) {
                if(v2 >= v0.size()) {
                    return this;
                }

                if((this.saiz != null || v0.get(v2).getAuxInfoType() != null) && !"cenc".equals(v0.get(v2).getAuxInfoType()) && (this.saiz == null || this.saiz.getAuxInfoType() != null || !"cenc".equals(v0.get(v2).getAuxInfoType()))) {
                    goto label_67;
                }

                this.saiz = v0.get(v2);
                if((this.saio != null || v1.get(v2).getAuxInfoType() != null) && !"cenc".equals(v1.get(v2).getAuxInfoType()) && (this.saio == null || this.saio.getAuxInfoType() != null || !"cenc".equals(v1.get(v2).getAuxInfoType()))) {
                    break;
                }

                this.saio = v1.get(v2);
            }

            throw new RuntimeException("Are there two cenc labeled saio?");
        label_67:
            throw new RuntimeException("Are there two cenc labeled saiz?");
            return this;
        }
    }

    private UUID defaultKeyId;
    private List sampleEncryptionEntries;

    static {
    }

    public CencMp4TrackImplImpl(String arg29, TrackBox arg30, IsoFile[] arg31) {
        long v12_2;
        int v3_3;
        int v5_1;
        int v7;
        int v16_1;
        int v10_1;
        List v6;
        long[] v5;
        SampleAuxiliaryInformationSizesBox v15_1;
        long v13;
        Container v12;
        Box v11;
        Object v10;
        Iterator v9;
        Object v8;
        CencMp4TrackImplImpl v0 = this;
        TrackBox v1 = arg30;
        super(arg29, arg30, arg31);
        Path.getPath(((AbstractContainerBox)v1), "mdia[0]/minf[0]/stbl[0]/stsd[0]/enc.[0]/sinf[0]/schm[0]");
        v0.sampleEncryptionEntries = new ArrayList();
        long v2 = arg30.getTrackHeaderBox().getTrackId();
        if(arg30.getParent().getBoxes(MovieExtendsBox.class).size() > 0) {
            Iterator v4 = arg30.getParent().getParent().getBoxes(MovieFragmentBox.class).iterator();
            while(true) {
                if(v4.hasNext()) {
                    v8 = v4.next();
                    v9 = ((MovieFragmentBox)v8).getBoxes(TrackFragmentBox.class).iterator();
                    do {
                    label_27:
                        if(v9.hasNext()) {
                            v10 = v9.next();
                            if(((TrackFragmentBox)v10).getTrackFragmentHeaderBox().getTrackId() != v2) {
                                continue;
                            }

                            break;
                        }
                        else {
                            goto label_29;
                        }
                    }
                    while(true);

                    v11 = Path.getPath(((AbstractContainerBox)v1), "mdia[0]/minf[0]/stbl[0]/stsd[0]/enc.[0]/sinf[0]/schi[0]/tenc[0]");
                    v0.defaultKeyId = ((TrackEncryptionBox)v11).getDefault_KID();
                    if(((TrackFragmentBox)v10).getTrackFragmentHeaderBox().hasBaseDataOffset()) {
                        v12 = arg30.getParent().getParent();
                        v13 = ((TrackFragmentBox)v10).getTrackFragmentHeaderBox().getBaseDataOffset();
                    }
                    else {
                        Object v12_1 = v8;
                        v13 = 0;
                    }

                    FindSaioSaizPair v15 = new FindSaioSaizPair(v0, ((Container)v10)).invoke();
                    SampleAuxiliaryInformationOffsetsBox v16 = v15.getSaio();
                    v15_1 = v15.getSaiz();
                    v5 = v16.getOffsets();
                    v6 = ((TrackFragmentBox)v10).getBoxes(TrackRunBox.class);
                    v10_1 = 0;
                    v16_1 = 0;
                    break;
                label_29:
                    continue;
                }
                else {
                    goto label_22;
                }
            }

            while(v10_1 < v5.length) {
                v7 = v6.get(v10_1).getEntries().size();
                long v19 = v5[v10_1];
                long v21 = v2;
                Iterator v23 = v4;
                long[] v24 = v5;
                int v2_1 = v16_1;
                long v3 = 0;
                while(true) {
                    v5_1 = v16_1 + v7;
                    if(v2_1 >= v5_1) {
                        break;
                    }

                    v3 += ((long)v15_1.getSize(v2_1));
                    ++v2_1;
                    v6 = v6;
                }

                List v25 = v6;
                ByteBuffer v6_1 = v12.getByteBuffer(v13 + v19, v3);
                v2_1 = v16_1;
                while(v2_1 < v5_1) {
                    v0.sampleEncryptionEntries.add(v0.parseCencAuxDataFormat(((TrackEncryptionBox)v11).getDefaultIvSize(), v6_1, ((long)v15_1.getSize(v2_1))));
                    ++v2_1;
                    v8 = v8;
                    v9 = v9;
                }

                ++v10_1;
                v16_1 = v5_1;
                v2 = v21;
                v4 = v23;
                v5 = v24;
                v6 = v25;
            }

            goto label_27;
        label_22:
        }
        else {
            Box v2_2 = Path.getPath(((AbstractContainerBox)v1), "mdia[0]/minf[0]/stbl[0]/stsd[0]/enc.[0]/sinf[0]/schi[0]/tenc[0]");
            v0.defaultKeyId = ((TrackEncryptionBox)v2_2).getDefault_KID();
            Box v3_1 = Path.getPath(((AbstractContainerBox)v1), "mdia[0]/minf[0]/stbl[0]/stco[0]");
            if(v3_1 == null) {
                v3_1 = Path.getPath(((AbstractContainerBox)v1), "mdia[0]/minf[0]/stbl[0]/co64[0]");
            }

            long[] v3_2 = arg30.getSampleTableBox().getSampleToChunkBox().blowup(((ChunkOffsetBox)v3_1).getChunkOffsets().length);
            FindSaioSaizPair v4_1 = new FindSaioSaizPair(v0, Path.getPath(((AbstractContainerBox)v1), "mdia[0]/minf[0]/stbl[0]")).invoke();
            SampleAuxiliaryInformationOffsetsBox v5_2 = FindSaioSaizPair.access$0(v4_1);
            SampleAuxiliaryInformationSizesBox v4_2 = FindSaioSaizPair.access$1(v4_1);
            Container v1_1 = arg30.getParent().getParent();
            if(v5_2.getOffsets().length == 1) {
                int v6_2 = 0;
                long v7_1 = v5_2.getOffsets()[0];
                if(v4_2.getDefaultSampleInfoSize() > 0) {
                    v3_3 = v4_2.getSampleCount() * v4_2.getDefaultSampleInfoSize();
                }
                else {
                    v3_3 = 0;
                    v5_1 = 0;
                    while(v3_3 < v4_2.getSampleCount()) {
                        v5_1 += v4_2.getSampleInfoSizes()[v3_3];
                        ++v3_3;
                    }

                    v3_3 = v5_1;
                }

                ByteBuffer v9_1 = v1_1.getByteBuffer(v7_1, ((long)v3_3));
                while(v6_2 < v4_2.getSampleCount()) {
                    v0.sampleEncryptionEntries.add(v0.parseCencAuxDataFormat(((TrackEncryptionBox)v2_2).getDefaultIvSize(), v9_1, ((long)v4_2.getSize(v6_2))));
                    ++v6_2;
                }

                return;
            }

            if(v5_2.getOffsets().length != v3_2.length) {
                goto label_218;
            }

            v7 = 0;
            int v8_1 = 0;
            while(v7 < v3_2.length) {
                long v10_2 = v5_2.getOffsets()[v7];
                if(v4_2.getDefaultSampleInfoSize() > 0) {
                    v12_2 = (((long)v4_2.getSampleCount())) * v3_2[v7];
                }
                else {
                    v12_2 = 0;
                    int v9_2;
                    for(v9_2 = 0; (((long)v9_2)) < v3_2[v7]; ++v9_2) {
                        v12_2 += ((long)v4_2.getSize(v8_1 + v9_2));
                    }
                }

                ByteBuffer v14 = v1_1.getByteBuffer(v10_2, v12_2);
                for(v9_2 = 0; (((long)v9_2)) < v3_2[v7]; ++v9_2) {
                    v0.sampleEncryptionEntries.add(v0.parseCencAuxDataFormat(((TrackEncryptionBox)v2_2).getDefaultIvSize(), v14, ((long)v4_2.getSize(v8_1 + v9_2))));
                }

                v8_1 = ((int)((((long)v8_1)) + v3_2[v7]));
                ++v7;
            }
        }

        return;
    label_218:
        throw new RuntimeException("Number of saio offsets must be either 1 or number of chunks");
    }

    public UUID getDefaultKeyId() {
        return this.defaultKeyId;
    }

    public String getName() {
        StringBuilder v0 = new StringBuilder("enc(");
        v0.append(super.getName());
        v0.append(")");
        return v0.toString();
    }

    public List getSampleEncryptionEntries() {
        return this.sampleEncryptionEntries;
    }

    public boolean hasSubSampleEncryption() {
        return 0;
    }

    private a parseCencAuxDataFormat(int arg5, ByteBuffer arg6, long arg7) {
        a v0 = new a();
        if(arg7 > 0) {
            v0.a = new byte[arg5];
            arg6.get(v0.a);
            if(arg7 > (((long)arg5))) {
                v0.b = new j[IsoTypeReader.readUInt16(arg6)];
                for(arg5 = 0; arg5 < v0.b.length; ++arg5) {
                    v0.b[arg5] = v0.a(IsoTypeReader.readUInt16(arg6), IsoTypeReader.readUInt32(arg6));
                }
            }
        }

        return v0;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder("CencMp4TrackImpl{handler=\'");
        v0.append(this.getHandler());
        v0.append('\'');
        v0.append('}');
        return v0.toString();
    }
}

