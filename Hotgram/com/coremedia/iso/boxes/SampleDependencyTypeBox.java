package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.a.b.b.b;

public class SampleDependencyTypeBox extends AbstractFullBox {
    public class Entry {
        private int value;

        public Entry(int arg1) {
            super();
            this.value = arg1;
        }

        static int access$0(Entry arg0) {
            return arg0.value;
        }

        public boolean equals(Object arg5) {
            if(this == (((Entry)arg5))) {
                return 1;
            }

            if(arg5 != null) {
                if(this.getClass() != arg5.getClass()) {
                }
                else if(this.value != ((Entry)arg5).value) {
                    return 0;
                }
                else {
                    return 1;
                }
            }

            return 0;
        }

        public int getReserved() {
            return this.value >> 6 & 3;
        }

        public int getSampleDependsOn() {
            return this.value >> 4 & 3;
        }

        public int getSampleHasRedundancy() {
            return this.value & 3;
        }

        public int getSampleIsDependentOn() {
            return this.value >> 2 & 3;
        }

        public int hashCode() {
            return this.value;
        }

        public void setReserved(int arg2) {
            this.value = (arg2 & 3) << 6 | this.value & 63;
        }

        public void setSampleDependsOn(int arg2) {
            this.value = (arg2 & 3) << 4 | this.value & 207;
        }

        public void setSampleHasRedundancy(int arg2) {
            this.value = arg2 & 3 | this.value & 252;
        }

        public void setSampleIsDependentOn(int arg2) {
            this.value = (arg2 & 3) << 2 | this.value & 243;
        }

        public String toString() {
            StringBuilder v0 = new StringBuilder("Entry{reserved=");
            v0.append(this.getReserved());
            v0.append(", sampleDependsOn=");
            v0.append(this.getSampleDependsOn());
            v0.append(", sampleIsDependentOn=");
            v0.append(this.getSampleIsDependentOn());
            v0.append(", sampleHasRedundancy=");
            v0.append(this.getSampleHasRedundancy());
            v0.append('}');
            return v0.toString();
        }
    }

    public static final String TYPE = "sdtp";
    private List entries;

    static {
        SampleDependencyTypeBox.ajc$preClinit();
    }

    public SampleDependencyTypeBox() {
        super("sdtp");
        this.entries = new ArrayList();
    }

    public void _parseDetails(ByteBuffer arg4) {
        this.parseVersionAndFlags(arg4);
        while(arg4.remaining() > 0) {
            this.entries.add(new Entry(IsoTypeReader.readUInt8(arg4)));
        }
    }

    private static void ajc$preClinit() {
        b v8 = new b("SampleDependencyTypeBox.java", SampleDependencyTypeBox.class);
        SampleDependencyTypeBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getEntries", "com.coremedia.iso.boxes.SampleDependencyTypeBox", "", "", "", "java.util.List"), 139);
        SampleDependencyTypeBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setEntries", "com.coremedia.iso.boxes.SampleDependencyTypeBox", "java.util.List", "entries", "", "void"), 143);
        SampleDependencyTypeBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.SampleDependencyTypeBox", "", "", "", "java.lang.String"), 148);
    }

    protected void getContent(ByteBuffer arg3) {
        this.writeVersionAndFlags(arg3);
        Iterator v0 = this.entries.iterator();
        while(v0.hasNext()) {
            IsoTypeWriter.writeUInt8(arg3, Entry.access$0(v0.next()));
        }
    }

    protected long getContentSize() {
        return ((long)(this.entries.size() + 4));
    }

    public List getEntries() {
        RequiresParseDetailAspect.aspectOf().before(b.a(SampleDependencyTypeBox.ajc$tjp_0, this, this));
        return this.entries;
    }

    public void setEntries(List arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(SampleDependencyTypeBox.ajc$tjp_1, this, this, arg3));
        this.entries = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(SampleDependencyTypeBox.ajc$tjp_2, this, this));
        return "SampleDependencyTypeBox" + "{entries=" + this.entries + '}';
    }
}

