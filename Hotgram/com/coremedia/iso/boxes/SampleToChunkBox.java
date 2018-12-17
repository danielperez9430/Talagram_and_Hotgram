package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.googlecode.mp4parser.util.CastUtils;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.a.b.a.a;
import org.a.b.b.b;

public class SampleToChunkBox extends AbstractFullBox {
    public class Entry {
        long firstChunk;
        long sampleDescriptionIndex;
        long samplesPerChunk;

        public Entry(long arg1, long arg3, long arg5) {
            super();
            this.firstChunk = arg1;
            this.samplesPerChunk = arg3;
            this.sampleDescriptionIndex = arg5;
        }

        public boolean equals(Object arg8) {
            if(this == (((Entry)arg8))) {
                return 1;
            }

            if(arg8 != null) {
                if(this.getClass() != arg8.getClass()) {
                }
                else if(this.firstChunk != ((Entry)arg8).firstChunk) {
                    return 0;
                }
                else if(this.sampleDescriptionIndex != ((Entry)arg8).sampleDescriptionIndex) {
                    return 0;
                }
                else if(this.samplesPerChunk != ((Entry)arg8).samplesPerChunk) {
                    return 0;
                }
                else {
                    return 1;
                }
            }

            return 0;
        }

        public long getFirstChunk() {
            return this.firstChunk;
        }

        public long getSampleDescriptionIndex() {
            return this.sampleDescriptionIndex;
        }

        public long getSamplesPerChunk() {
            return this.samplesPerChunk;
        }

        public int hashCode() {
            return ((((int)(this.firstChunk ^ this.firstChunk >>> 32))) * 31 + (((int)(this.samplesPerChunk ^ this.samplesPerChunk >>> 32)))) * 31 + (((int)(this.sampleDescriptionIndex ^ this.sampleDescriptionIndex >>> 32)));
        }

        public void setFirstChunk(long arg1) {
            this.firstChunk = arg1;
        }

        public void setSampleDescriptionIndex(long arg1) {
            this.sampleDescriptionIndex = arg1;
        }

        public void setSamplesPerChunk(long arg1) {
            this.samplesPerChunk = arg1;
        }

        public String toString() {
            StringBuilder v0 = new StringBuilder("Entry{firstChunk=");
            v0.append(this.firstChunk);
            v0.append(", samplesPerChunk=");
            v0.append(this.samplesPerChunk);
            v0.append(", sampleDescriptionIndex=");
            v0.append(this.sampleDescriptionIndex);
            v0.append('}');
            return v0.toString();
        }
    }

    public static final String TYPE = "stsc";
    List entries;

    static {
        SampleToChunkBox.ajc$preClinit();
    }

    public SampleToChunkBox() {
        super("stsc");
        this.entries = Collections.emptyList();
    }

    public void _parseDetails(ByteBuffer arg12) {
        this.parseVersionAndFlags(arg12);
        int v0 = CastUtils.l2i(IsoTypeReader.readUInt32(arg12));
        this.entries = new ArrayList(v0);
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.entries.add(new Entry(IsoTypeReader.readUInt32(arg12), IsoTypeReader.readUInt32(arg12), IsoTypeReader.readUInt32(arg12)));
        }
    }

    private static void ajc$preClinit() {
        b v8 = new b("SampleToChunkBox.java", SampleToChunkBox.class);
        SampleToChunkBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getEntries", "com.coremedia.iso.boxes.SampleToChunkBox", "", "", "", "java.util.List"), 47);
        SampleToChunkBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setEntries", "com.coremedia.iso.boxes.SampleToChunkBox", "java.util.List", "entries", "", "void"), 51);
        SampleToChunkBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.SampleToChunkBox", "", "", "", "java.lang.String"), 84);
        SampleToChunkBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "blowup", "com.coremedia.iso.boxes.SampleToChunkBox", "int", "chunkCount", "", "[J"), 95);
    }

    public long[] blowup(int arg9) {
        RequiresParseDetailAspect.aspectOf().before(b.a(SampleToChunkBox.ajc$tjp_3, this, this, a.a(arg9)));
        long[] v9 = new long[arg9];
        LinkedList v0 = new LinkedList(this.entries);
        Collections.reverse(((List)v0));
        Iterator v0_1 = ((List)v0).iterator();
        Object v1 = v0_1.next();
        int v2;
        for(v2 = v9.length; v2 > 1; --v2) {
            v9[v2 - 1] = ((Entry)v1).getSamplesPerChunk();
            if((((long)v2)) == ((Entry)v1).getFirstChunk()) {
                v1 = v0_1.next();
            }
        }

        v9[0] = ((Entry)v1).getSamplesPerChunk();
        return v9;
    }

    protected void getContent(ByteBuffer arg5) {
        this.writeVersionAndFlags(arg5);
        IsoTypeWriter.writeUInt32(arg5, ((long)this.entries.size()));
        Iterator v0 = this.entries.iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            IsoTypeWriter.writeUInt32(arg5, ((Entry)v1).getFirstChunk());
            IsoTypeWriter.writeUInt32(arg5, ((Entry)v1).getSamplesPerChunk());
            IsoTypeWriter.writeUInt32(arg5, ((Entry)v1).getSampleDescriptionIndex());
        }
    }

    protected long getContentSize() {
        return ((long)(this.entries.size() * 12 + 8));
    }

    public List getEntries() {
        RequiresParseDetailAspect.aspectOf().before(b.a(SampleToChunkBox.ajc$tjp_0, this, this));
        return this.entries;
    }

    public void setEntries(List arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(SampleToChunkBox.ajc$tjp_1, this, this, arg3));
        this.entries = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(SampleToChunkBox.ajc$tjp_2, this, this));
        StringBuilder v0 = new StringBuilder("SampleToChunkBox[entryCount=");
        v0.append(this.entries.size());
        v0.append("]");
        return v0.toString();
    }
}

