package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.googlecode.mp4parser.util.CastUtils;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.a.b.b.b;

public class EditListBox extends AbstractFullBox {
    public class Entry {
        EditListBox editListBox;
        private double mediaRate;
        private long mediaTime;
        private long segmentDuration;

        public Entry(EditListBox arg3, ByteBuffer arg4) {
            long v0;
            super();
            if(arg3.getVersion() == 1) {
                this.segmentDuration = IsoTypeReader.readUInt64(arg4);
                v0 = arg4.getLong();
            }
            else {
                this.segmentDuration = IsoTypeReader.readUInt32(arg4);
                v0 = ((long)arg4.getInt());
            }

            this.mediaTime = v0;
            this.mediaRate = IsoTypeReader.readFixedPoint1616(arg4);
            this.editListBox = arg3;
        }

        public Entry(EditListBox arg1, long arg2, long arg4, double arg6) {
            super();
            this.segmentDuration = arg2;
            this.mediaTime = arg4;
            this.mediaRate = arg6;
            this.editListBox = arg1;
        }

        public boolean equals(Object arg8) {
            if(this == (((Entry)arg8))) {
                return 1;
            }

            if(arg8 != null) {
                if(this.getClass() != arg8.getClass()) {
                }
                else if(this.mediaTime != ((Entry)arg8).mediaTime) {
                    return 0;
                }
                else if(this.segmentDuration != ((Entry)arg8).segmentDuration) {
                    return 0;
                }
                else {
                    return 1;
                }
            }

            return 0;
        }

        public void getContent(ByteBuffer arg3) {
            if(this.editListBox.getVersion() == 1) {
                IsoTypeWriter.writeUInt64(arg3, this.segmentDuration);
                arg3.putLong(this.mediaTime);
            }
            else {
                IsoTypeWriter.writeUInt32(arg3, ((long)CastUtils.l2i(this.segmentDuration)));
                arg3.putInt(CastUtils.l2i(this.mediaTime));
            }

            IsoTypeWriter.writeFixedPoint1616(arg3, this.mediaRate);
        }

        public double getMediaRate() {
            return this.mediaRate;
        }

        public long getMediaTime() {
            return this.mediaTime;
        }

        public long getSegmentDuration() {
            return this.segmentDuration;
        }

        public int hashCode() {
            return (((int)(this.segmentDuration ^ this.segmentDuration >>> 32))) * 31 + (((int)(this.mediaTime ^ this.mediaTime >>> 32)));
        }

        public void setMediaRate(double arg1) {
            this.mediaRate = arg1;
        }

        public void setMediaTime(long arg1) {
            this.mediaTime = arg1;
        }

        public void setSegmentDuration(long arg1) {
            this.segmentDuration = arg1;
        }

        public String toString() {
            StringBuilder v0 = new StringBuilder("Entry{segmentDuration=");
            v0.append(this.segmentDuration);
            v0.append(", mediaTime=");
            v0.append(this.mediaTime);
            v0.append(", mediaRate=");
            v0.append(this.mediaRate);
            v0.append('}');
            return v0.toString();
        }
    }

    public static final String TYPE = "elst";
    private List entries;

    static {
        EditListBox.ajc$preClinit();
    }

    public EditListBox() {
        super("elst");
        this.entries = new LinkedList();
    }

    public void _parseDetails(ByteBuffer arg5) {
        this.parseVersionAndFlags(arg5);
        int v0 = CastUtils.l2i(IsoTypeReader.readUInt32(arg5));
        this.entries = new LinkedList();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.entries.add(new Entry(this, arg5));
        }
    }

    private static void ajc$preClinit() {
        b v8 = new b("EditListBox.java", EditListBox.class);
        EditListBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getEntries", "com.coremedia.iso.boxes.EditListBox", "", "", "", "java.util.List"), 68);
        EditListBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setEntries", "com.coremedia.iso.boxes.EditListBox", "java.util.List", "entries", "", "void"), 72);
        EditListBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.EditListBox", "", "", "", "java.lang.String"), 108);
    }

    protected void getContent(ByteBuffer arg3) {
        this.writeVersionAndFlags(arg3);
        IsoTypeWriter.writeUInt32(arg3, ((long)this.entries.size()));
        Iterator v0 = this.entries.iterator();
        while(v0.hasNext()) {
            v0.next().getContent(arg3);
        }
    }

    protected long getContentSize() {
        long v1 = 8;
        int v0 = this.getVersion() == 1 ? this.entries.size() * 20 : this.entries.size() * 12;
        long v3 = (((long)v0)) + v1;
        return v3;
    }

    public List getEntries() {
        RequiresParseDetailAspect.aspectOf().before(b.a(EditListBox.ajc$tjp_0, this, this));
        return this.entries;
    }

    public void setEntries(List arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(EditListBox.ajc$tjp_1, this, this, arg3));
        this.entries = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(EditListBox.ajc$tjp_2, this, this));
        StringBuilder v0 = new StringBuilder("EditListBox{entries=");
        v0.append(this.entries);
        v0.append('}');
        return v0.toString();
    }
}

