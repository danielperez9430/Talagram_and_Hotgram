package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.googlecode.mp4parser.util.CastUtils;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.a.b.b.b;

public class SubSampleInformationBox extends AbstractFullBox {
    public class SubSampleEntry {
        public class SubsampleEntry {
            private int discardable;
            private long reserved;
            private int subsamplePriority;
            private long subsampleSize;

            public SubsampleEntry() {
                super();
            }

            public int getDiscardable() {
                return this.discardable;
            }

            public long getReserved() {
                return this.reserved;
            }

            public int getSubsamplePriority() {
                return this.subsamplePriority;
            }

            public long getSubsampleSize() {
                return this.subsampleSize;
            }

            public void setDiscardable(int arg1) {
                this.discardable = arg1;
            }

            public void setReserved(long arg1) {
                this.reserved = arg1;
            }

            public void setSubsamplePriority(int arg1) {
                this.subsamplePriority = arg1;
            }

            public void setSubsampleSize(long arg1) {
                this.subsampleSize = arg1;
            }

            public String toString() {
                StringBuilder v0 = new StringBuilder("SubsampleEntry{subsampleSize=");
                v0.append(this.subsampleSize);
                v0.append(", subsamplePriority=");
                v0.append(this.subsamplePriority);
                v0.append(", discardable=");
                v0.append(this.discardable);
                v0.append(", reserved=");
                v0.append(this.reserved);
                v0.append('}');
                return v0.toString();
            }
        }

        private long sampleDelta;
        private List subsampleEntries;

        public SubSampleEntry() {
            super();
            this.subsampleEntries = new ArrayList();
        }

        public long getSampleDelta() {
            return this.sampleDelta;
        }

        public int getSubsampleCount() {
            return this.subsampleEntries.size();
        }

        public List getSubsampleEntries() {
            return this.subsampleEntries;
        }

        public void setSampleDelta(long arg1) {
            this.sampleDelta = arg1;
        }

        public String toString() {
            StringBuilder v0 = new StringBuilder("SampleEntry{sampleDelta=");
            v0.append(this.sampleDelta);
            v0.append(", subsampleCount=");
            v0.append(this.subsampleEntries.size());
            v0.append(", subsampleEntries=");
            v0.append(this.subsampleEntries);
            v0.append('}');
            return v0.toString();
        }
    }

    public static final String TYPE = "subs";
    private List entries;

    static {
        SubSampleInformationBox.ajc$preClinit();
    }

    public SubSampleInformationBox() {
        super("subs");
        this.entries = new ArrayList();
    }

    public void _parseDetails(ByteBuffer arg11) {
        this.parseVersionAndFlags(arg11);
        long v0 = IsoTypeReader.readUInt32(arg11);
        int v3;
        for(v3 = 0; (((long)v3)) < v0; ++v3) {
            SubSampleEntry v4 = new SubSampleEntry();
            v4.setSampleDelta(IsoTypeReader.readUInt32(arg11));
            int v5 = IsoTypeReader.readUInt16(arg11);
            int v6;
            for(v6 = 0; v6 < v5; ++v6) {
                SubsampleEntry v7 = new SubsampleEntry();
                long v8 = this.getVersion() == 1 ? IsoTypeReader.readUInt32(arg11) : ((long)IsoTypeReader.readUInt16(arg11));
                v7.setSubsampleSize(v8);
                v7.setSubsamplePriority(IsoTypeReader.readUInt8(arg11));
                v7.setDiscardable(IsoTypeReader.readUInt8(arg11));
                v7.setReserved(IsoTypeReader.readUInt32(arg11));
                v4.getSubsampleEntries().add(v7);
            }

            this.entries.add(v4);
        }
    }

    private static void ajc$preClinit() {
        b v8 = new b("SubSampleInformationBox.java", SubSampleInformationBox.class);
        SubSampleInformationBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getEntries", "com.coremedia.iso.boxes.SubSampleInformationBox", "", "", "", "java.util.List"), 50);
        SubSampleInformationBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setEntries", "com.coremedia.iso.boxes.SubSampleInformationBox", "java.util.List", "entries", "", "void"), 54);
        SubSampleInformationBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.SubSampleInformationBox", "", "", "", "java.lang.String"), 124);
    }

    protected void getContent(ByteBuffer arg6) {
        this.writeVersionAndFlags(arg6);
        IsoTypeWriter.writeUInt32(arg6, ((long)this.entries.size()));
        Iterator v0 = this.entries.iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            IsoTypeWriter.writeUInt32(arg6, ((SubSampleEntry)v1).getSampleDelta());
            IsoTypeWriter.writeUInt16(arg6, ((SubSampleEntry)v1).getSubsampleCount());
            Iterator v1_1 = ((SubSampleEntry)v1).getSubsampleEntries().iterator();
            while(v1_1.hasNext()) {
                Object v2 = v1_1.next();
                if(this.getVersion() == 1) {
                    IsoTypeWriter.writeUInt32(arg6, ((SubsampleEntry)v2).getSubsampleSize());
                }
                else {
                    IsoTypeWriter.writeUInt16(arg6, CastUtils.l2i(((SubsampleEntry)v2).getSubsampleSize()));
                }

                IsoTypeWriter.writeUInt8(arg6, ((SubsampleEntry)v2).getSubsamplePriority());
                IsoTypeWriter.writeUInt8(arg6, ((SubsampleEntry)v2).getDiscardable());
                IsoTypeWriter.writeUInt32(arg6, ((SubsampleEntry)v2).getReserved());
            }
        }
    }

    protected long getContentSize() {
        Iterator v0 = this.entries.iterator();
        long v1 = 8;
        while(v0.hasNext()) {
            Object v3 = v0.next();
            long v4 = 4;
            long v6 = 2;
            v1 = v1 + v4 + v6;
            int v8;
            for(v8 = 0; v8 < ((SubSampleEntry)v3).getSubsampleEntries().size(); ++v8) {
                v1 += this.getVersion() == 1 ? v4 : v6;
                v1 = v1 + v6 + v4;
            }
        }

        return v1;
    }

    public List getEntries() {
        RequiresParseDetailAspect.aspectOf().before(b.a(SubSampleInformationBox.ajc$tjp_0, this, this));
        return this.entries;
    }

    public void setEntries(List arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(SubSampleInformationBox.ajc$tjp_1, this, this, arg3));
        this.entries = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(SubSampleInformationBox.ajc$tjp_2, this, this));
        StringBuilder v0 = new StringBuilder("SubSampleInformationBox{entryCount=");
        v0.append(this.entries.size());
        v0.append(", entries=");
        v0.append(this.entries);
        v0.append('}');
        return v0.toString();
    }
}

