package com.coremedia.iso.boxes.dece;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.a.b.b.b;

public class TrickPlayBox extends AbstractFullBox {
    public class Entry {
        private int value;

        public Entry(int arg1) {
            super();
            this.value = arg1;
        }

        public Entry() {
            super();
        }

        static int access$0(Entry arg0) {
            return arg0.value;
        }

        public int getDependencyLevel() {
            return this.value & 63;
        }

        public int getPicType() {
            return this.value >> 6 & 3;
        }

        public void setDependencyLevel(int arg2) {
            this.value |= arg2 & 63;
        }

        public void setPicType(int arg2) {
            this.value &= 31;
            this.value |= (arg2 & 3) << 6;
        }

        public String toString() {
            return "Entry" + "{picType=" + this.getPicType() + ",dependencyLevel=" + this.getDependencyLevel() + '}';
        }
    }

    public static final String TYPE = "trik";
    private List entries;

    static {
        TrickPlayBox.ajc$preClinit();
    }

    public TrickPlayBox() {
        super("trik");
        this.entries = new ArrayList();
    }

    public void _parseDetails(ByteBuffer arg4) {
        this.parseVersionAndFlags(arg4);
        while(arg4.remaining() > 0) {
            this.entries.add(new Entry(IsoTypeReader.readUInt8(arg4)));
        }
    }

    private static void ajc$preClinit() {
        b v8 = new b("TrickPlayBox.java", TrickPlayBox.class);
        TrickPlayBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "setEntries", "com.coremedia.iso.boxes.dece.TrickPlayBox", "java.util.List", "entries", "", "void"), 32);
        TrickPlayBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "getEntries", "com.coremedia.iso.boxes.dece.TrickPlayBox", "", "", "", "java.util.List"), 36);
        TrickPlayBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.dece.TrickPlayBox", "", "", "", "java.lang.String"), 103);
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
        RequiresParseDetailAspect.aspectOf().before(b.a(TrickPlayBox.ajc$tjp_1, this, this));
        return this.entries;
    }

    public void setEntries(List arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrickPlayBox.ajc$tjp_0, this, this, arg3));
        this.entries = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrickPlayBox.ajc$tjp_2, this, this));
        return "TrickPlayBox" + "{entries=" + this.entries + '}';
    }
}

