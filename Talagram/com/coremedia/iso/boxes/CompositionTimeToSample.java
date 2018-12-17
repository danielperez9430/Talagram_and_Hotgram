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
import java.util.List;
import org.a.b.b.b;

public class CompositionTimeToSample extends AbstractFullBox {
    public class Entry {
        int count;
        int offset;

        public Entry(int arg1, int arg2) {
            super();
            this.count = arg1;
            this.offset = arg2;
        }

        public int getCount() {
            return this.count;
        }

        public int getOffset() {
            return this.offset;
        }

        public void setCount(int arg1) {
            this.count = arg1;
        }

        public void setOffset(int arg1) {
            this.offset = arg1;
        }

        public String toString() {
            StringBuilder v0 = new StringBuilder("Entry{count=");
            v0.append(this.count);
            v0.append(", offset=");
            v0.append(this.offset);
            v0.append('}');
            return v0.toString();
        }
    }

    public static final String TYPE = "ctts";
    List entries;

    static {
        CompositionTimeToSample.ajc$preClinit();
    }

    public CompositionTimeToSample() {
        super("ctts");
        this.entries = Collections.emptyList();
    }

    public void _parseDetails(ByteBuffer arg6) {
        this.parseVersionAndFlags(arg6);
        int v0 = CastUtils.l2i(IsoTypeReader.readUInt32(arg6));
        this.entries = new ArrayList(v0);
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.entries.add(new Entry(CastUtils.l2i(IsoTypeReader.readUInt32(arg6)), arg6.getInt()));
        }
    }

    private static void ajc$preClinit() {
        b v8 = new b("CompositionTimeToSample.java", CompositionTimeToSample.class);
        CompositionTimeToSample.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getEntries", "com.coremedia.iso.boxes.CompositionTimeToSample", "", "", "", "java.util.List"), 57);
        CompositionTimeToSample.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setEntries", "com.coremedia.iso.boxes.CompositionTimeToSample", "java.util.List", "entries", "", "void"), 61);
    }

    public static int[] blowupCompositionTimes(List arg7) {
        Iterator v0 = arg7.iterator();
        long v1;
        for(v1 = 0; v0.hasNext(); v1 += ((long)v0.next().getCount())) {
        }

        int[] v3 = new int[((int)v1)];
        Iterator v4 = arg7.iterator();
        int v7;
        for(v7 = 0; v4.hasNext(); v7 = v0_1) {
            Object v6 = v4.next();
            int v0_1 = v7;
            v7 = 0;
            while(v7 < ((Entry)v6).getCount()) {
                v3[v0_1] = ((Entry)v6).getOffset();
                ++v7;
                ++v0_1;
            }
        }

        return v3;
    }

    protected void getContent(ByteBuffer arg5) {
        this.writeVersionAndFlags(arg5);
        IsoTypeWriter.writeUInt32(arg5, ((long)this.entries.size()));
        Iterator v0 = this.entries.iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            IsoTypeWriter.writeUInt32(arg5, ((long)((Entry)v1).getCount()));
            arg5.putInt(((Entry)v1).getOffset());
        }
    }

    protected long getContentSize() {
        return ((long)(this.entries.size() * 8 + 8));
    }

    public List getEntries() {
        RequiresParseDetailAspect.aspectOf().before(b.a(CompositionTimeToSample.ajc$tjp_0, this, this));
        return this.entries;
    }

    public void setEntries(List arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(CompositionTimeToSample.ajc$tjp_1, this, this, arg3));
        this.entries = arg3;
    }
}

