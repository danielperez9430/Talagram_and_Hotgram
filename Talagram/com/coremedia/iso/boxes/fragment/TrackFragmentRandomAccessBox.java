package com.coremedia.iso.boxes.fragment;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeReaderVariable;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.IsoTypeWriterVariable;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.a.b.a.a;
import org.a.b.b.b;

public class TrackFragmentRandomAccessBox extends AbstractFullBox {
    public class Entry {
        private long moofOffset;
        private long sampleNumber;
        private long time;
        private long trafNumber;
        private long trunNumber;

        public Entry() {
            super();
        }

        public Entry(long arg1, long arg3, long arg5, long arg7, long arg9) {
            super();
            this.moofOffset = arg3;
            this.sampleNumber = arg9;
            this.time = arg1;
            this.trafNumber = arg5;
            this.trunNumber = arg7;
        }

        static void access$0(Entry arg0, long arg1) {
            arg0.time = arg1;
        }

        static void access$1(Entry arg0, long arg1) {
            arg0.moofOffset = arg1;
        }

        static void access$2(Entry arg0, long arg1) {
            arg0.trafNumber = arg1;
        }

        static void access$3(Entry arg0, long arg1) {
            arg0.trunNumber = arg1;
        }

        static void access$4(Entry arg0, long arg1) {
            arg0.sampleNumber = arg1;
        }

        static long access$5(Entry arg2) {
            return arg2.time;
        }

        static long access$6(Entry arg2) {
            return arg2.moofOffset;
        }

        static long access$7(Entry arg2) {
            return arg2.trafNumber;
        }

        static long access$8(Entry arg2) {
            return arg2.trunNumber;
        }

        static long access$9(Entry arg2) {
            return arg2.sampleNumber;
        }

        public boolean equals(Object arg8) {
            if(this == (((Entry)arg8))) {
                return 1;
            }

            if(arg8 != null) {
                if(this.getClass() != arg8.getClass()) {
                }
                else if(this.moofOffset != ((Entry)arg8).moofOffset) {
                    return 0;
                }
                else if(this.sampleNumber != ((Entry)arg8).sampleNumber) {
                    return 0;
                }
                else if(this.time != ((Entry)arg8).time) {
                    return 0;
                }
                else if(this.trafNumber != ((Entry)arg8).trafNumber) {
                    return 0;
                }
                else if(this.trunNumber != ((Entry)arg8).trunNumber) {
                    return 0;
                }
                else {
                    return 1;
                }
            }

            return 0;
        }

        public long getMoofOffset() {
            return this.moofOffset;
        }

        public long getSampleNumber() {
            return this.sampleNumber;
        }

        public long getTime() {
            return this.time;
        }

        public long getTrafNumber() {
            return this.trafNumber;
        }

        public long getTrunNumber() {
            return this.trunNumber;
        }

        public int hashCode() {
            return ((((((int)(this.time ^ this.time >>> 32))) * 31 + (((int)(this.moofOffset ^ this.moofOffset >>> 32)))) * 31 + (((int)(this.trafNumber ^ this.trafNumber >>> 32)))) * 31 + (((int)(this.trunNumber ^ this.trunNumber >>> 32)))) * 31 + (((int)(this.sampleNumber ^ this.sampleNumber >>> 32)));
        }

        public void setMoofOffset(long arg1) {
            this.moofOffset = arg1;
        }

        public void setSampleNumber(long arg1) {
            this.sampleNumber = arg1;
        }

        public void setTime(long arg1) {
            this.time = arg1;
        }

        public void setTrafNumber(long arg1) {
            this.trafNumber = arg1;
        }

        public void setTrunNumber(long arg1) {
            this.trunNumber = arg1;
        }

        public String toString() {
            StringBuilder v0 = new StringBuilder("Entry{time=");
            v0.append(this.time);
            v0.append(", moofOffset=");
            v0.append(this.moofOffset);
            v0.append(", trafNumber=");
            v0.append(this.trafNumber);
            v0.append(", trunNumber=");
            v0.append(this.trunNumber);
            v0.append(", sampleNumber=");
            v0.append(this.sampleNumber);
            v0.append('}');
            return v0.toString();
        }
    }

    public static final String TYPE = "tfra";
    private List entries;
    private int lengthSizeOfSampleNum;
    private int lengthSizeOfTrafNum;
    private int lengthSizeOfTrunNum;
    private int reserved;
    private long trackId;

    static {
        TrackFragmentRandomAccessBox.ajc$preClinit();
    }

    public TrackFragmentRandomAccessBox() {
        super("tfra");
        this.lengthSizeOfTrafNum = 2;
        this.lengthSizeOfTrunNum = 2;
        this.lengthSizeOfSampleNum = 2;
        this.entries = Collections.emptyList();
    }

    public void _parseDetails(ByteBuffer arg8) {
        long v5;
        this.parseVersionAndFlags(arg8);
        this.trackId = IsoTypeReader.readUInt32(arg8);
        long v0 = IsoTypeReader.readUInt32(arg8);
        this.reserved = ((int)(v0 >> 6));
        this.lengthSizeOfTrafNum = ((((int)(63 & v0))) >> 4) + 1;
        this.lengthSizeOfTrunNum = ((((int)(12 & v0))) >> 2) + 1;
        this.lengthSizeOfSampleNum = (((int)(v0 & 3))) + 1;
        v0 = IsoTypeReader.readUInt32(arg8);
        this.entries = new ArrayList();
        int v2;
        for(v2 = 0; (((long)v2)) < v0; ++v2) {
            Entry v4 = new Entry();
            if(this.getVersion() == 1) {
                Entry.access$0(v4, IsoTypeReader.readUInt64(arg8));
                v5 = IsoTypeReader.readUInt64(arg8);
            }
            else {
                Entry.access$0(v4, IsoTypeReader.readUInt32(arg8));
                v5 = IsoTypeReader.readUInt32(arg8);
            }

            Entry.access$1(v4, v5);
            Entry.access$2(v4, IsoTypeReaderVariable.read(arg8, this.lengthSizeOfTrafNum));
            Entry.access$3(v4, IsoTypeReaderVariable.read(arg8, this.lengthSizeOfTrunNum));
            Entry.access$4(v4, IsoTypeReaderVariable.read(arg8, this.lengthSizeOfSampleNum));
            this.entries.add(v4);
        }
    }

    private static void ajc$preClinit() {
        b v8 = new b("TrackFragmentRandomAccessBox.java", TrackFragmentRandomAccessBox.class);
        TrackFragmentRandomAccessBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "setTrackId", "com.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox", "long", "trackId", "", "void"), 145);
        TrackFragmentRandomAccessBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setLengthSizeOfTrafNum", "com.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox", "int", "lengthSizeOfTrafNum", "", "void"), 149);
        TrackFragmentRandomAccessBox.ajc$tjp_10 = v8.a("method-execution", v8.a("1", "getEntries", "com.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox", "", "", "", "java.util.List"), 185);
        TrackFragmentRandomAccessBox.ajc$tjp_11 = v8.a("method-execution", v8.a("1", "setEntries", "com.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox", "java.util.List", "entries", "", "void"), 189);
        TrackFragmentRandomAccessBox.ajc$tjp_12 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox", "", "", "", "java.lang.String"), 290);
        TrackFragmentRandomAccessBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "setLengthSizeOfTrunNum", "com.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox", "int", "lengthSizeOfTrunNum", "", "void"), 153);
        TrackFragmentRandomAccessBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "setLengthSizeOfSampleNum", "com.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox", "int", "lengthSizeOfSampleNum", "", "void"), 157);
        TrackFragmentRandomAccessBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "getTrackId", "com.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox", "", "", "", "long"), 161);
        TrackFragmentRandomAccessBox.ajc$tjp_5 = v8.a("method-execution", v8.a("1", "getReserved", "com.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox", "", "", "", "int"), 165);
        TrackFragmentRandomAccessBox.ajc$tjp_6 = v8.a("method-execution", v8.a("1", "getLengthSizeOfTrafNum", "com.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox", "", "", "", "int"), 169);
        TrackFragmentRandomAccessBox.ajc$tjp_7 = v8.a("method-execution", v8.a("1", "getLengthSizeOfTrunNum", "com.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox", "", "", "", "int"), 173);
        TrackFragmentRandomAccessBox.ajc$tjp_8 = v8.a("method-execution", v8.a("1", "getLengthSizeOfSampleNum", "com.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox", "", "", "", "int"), 177);
        TrackFragmentRandomAccessBox.ajc$tjp_9 = v8.a("method-execution", v8.a("1", "getNumberOfEntries", "com.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox", "", "", "", "long"), 181);
    }

    protected void getContent(ByteBuffer arg7) {
        this.writeVersionAndFlags(arg7);
        IsoTypeWriter.writeUInt32(arg7, this.trackId);
        IsoTypeWriter.writeUInt32(arg7, (((long)(this.reserved << 6))) | (((long)((this.lengthSizeOfTrafNum - 1 & 3) << 4))) | (((long)((this.lengthSizeOfTrunNum - 1 & 3) << 2))) | (((long)(this.lengthSizeOfSampleNum - 1 & 3))));
        IsoTypeWriter.writeUInt32(arg7, ((long)this.entries.size()));
        Iterator v0 = this.entries.iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            if(this.getVersion() == 1) {
                IsoTypeWriter.writeUInt64(arg7, Entry.access$5(((Entry)v1)));
                IsoTypeWriter.writeUInt64(arg7, Entry.access$6(((Entry)v1)));
            }
            else {
                IsoTypeWriter.writeUInt32(arg7, Entry.access$5(((Entry)v1)));
                IsoTypeWriter.writeUInt32(arg7, Entry.access$6(((Entry)v1)));
            }

            IsoTypeWriterVariable.write(Entry.access$7(((Entry)v1)), arg7, this.lengthSizeOfTrafNum);
            IsoTypeWriterVariable.write(Entry.access$8(((Entry)v1)), arg7, this.lengthSizeOfTrunNum);
            IsoTypeWriterVariable.write(Entry.access$9(((Entry)v1)), arg7, this.lengthSizeOfSampleNum);
        }
    }

    protected long getContentSize() {
        long v1 = 16;
        int v0 = this.getVersion() == 1 ? this.entries.size() * 16 : this.entries.size() * 8;
        v1 += ((long)v0);
        return v1 + (((long)(this.lengthSizeOfTrafNum * this.entries.size()))) + (((long)(this.lengthSizeOfTrunNum * this.entries.size()))) + (((long)(this.lengthSizeOfSampleNum * this.entries.size())));
    }

    public List getEntries() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentRandomAccessBox.ajc$tjp_10, this, this));
        return Collections.unmodifiableList(this.entries);
    }

    public int getLengthSizeOfSampleNum() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentRandomAccessBox.ajc$tjp_8, this, this));
        return this.lengthSizeOfSampleNum;
    }

    public int getLengthSizeOfTrafNum() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentRandomAccessBox.ajc$tjp_6, this, this));
        return this.lengthSizeOfTrafNum;
    }

    public int getLengthSizeOfTrunNum() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentRandomAccessBox.ajc$tjp_7, this, this));
        return this.lengthSizeOfTrunNum;
    }

    public long getNumberOfEntries() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentRandomAccessBox.ajc$tjp_9, this, this));
        return ((long)this.entries.size());
    }

    public int getReserved() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentRandomAccessBox.ajc$tjp_5, this, this));
        return this.reserved;
    }

    public long getTrackId() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentRandomAccessBox.ajc$tjp_4, this, this));
        return this.trackId;
    }

    public void setEntries(List arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentRandomAccessBox.ajc$tjp_11, this, this, arg3));
        this.entries = arg3;
    }

    public void setLengthSizeOfSampleNum(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentRandomAccessBox.ajc$tjp_3, this, this, a.a(arg3)));
        this.lengthSizeOfSampleNum = arg3;
    }

    public void setLengthSizeOfTrafNum(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentRandomAccessBox.ajc$tjp_1, this, this, a.a(arg3)));
        this.lengthSizeOfTrafNum = arg3;
    }

    public void setLengthSizeOfTrunNum(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentRandomAccessBox.ajc$tjp_2, this, this, a.a(arg3)));
        this.lengthSizeOfTrunNum = arg3;
    }

    public void setTrackId(long arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentRandomAccessBox.ajc$tjp_0, this, this, a.a(arg3)));
        this.trackId = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentRandomAccessBox.ajc$tjp_12, this, this));
        StringBuilder v0 = new StringBuilder("TrackFragmentRandomAccessBox{trackId=");
        v0.append(this.trackId);
        v0.append(", entries=");
        v0.append(this.entries);
        v0.append('}');
        return v0.toString();
    }
}

