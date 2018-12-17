package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.googlecode.mp4parser.util.CastUtils;
import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import org.a.b.b.b;

public class TimeToSampleBox extends AbstractFullBox {
    public class Entry {
        long count;
        long delta;

        public Entry(long arg1, long arg3) {
            super();
            this.count = arg1;
            this.delta = arg3;
        }

        public long getCount() {
            return this.count;
        }

        public long getDelta() {
            return this.delta;
        }

        public void setCount(long arg1) {
            this.count = arg1;
        }

        public void setDelta(long arg1) {
            this.delta = arg1;
        }

        public String toString() {
            StringBuilder v0 = new StringBuilder("Entry{count=");
            v0.append(this.count);
            v0.append(", delta=");
            v0.append(this.delta);
            v0.append('}');
            return v0.toString();
        }
    }

    public static final String TYPE = "stts";
    static Map cache;
    List entries;

    static {
        TimeToSampleBox.ajc$preClinit();
        TimeToSampleBox.cache = new WeakHashMap();
    }

    public TimeToSampleBox() {
        super("stts");
        this.entries = Collections.emptyList();
    }

    public void _parseDetails(ByteBuffer arg9) {
        this.parseVersionAndFlags(arg9);
        int v0 = CastUtils.l2i(IsoTypeReader.readUInt32(arg9));
        this.entries = new ArrayList(v0);
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.entries.add(new Entry(IsoTypeReader.readUInt32(arg9), IsoTypeReader.readUInt32(arg9)));
        }
    }

    private static void ajc$preClinit() {
        b v8 = new b("TimeToSampleBox.java", TimeToSampleBox.class);
        TimeToSampleBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getEntries", "com.coremedia.iso.boxes.TimeToSampleBox", "", "", "", "java.util.List"), 79);
        TimeToSampleBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setEntries", "com.coremedia.iso.boxes.TimeToSampleBox", "java.util.List", "entries", "", "void"), 83);
        TimeToSampleBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.TimeToSampleBox", "", "", "", "java.lang.String"), 87);
    }

    public static long[] blowupTimeToSamples(List arg12) {
        long[] v1_2;
        Object v1;
        Class v0 = TimeToSampleBox.class;
        __monitor_enter(v0);
        try {
            v1 = TimeToSampleBox.cache.get(arg12);
            if(v1 != null) {
                v1 = ((SoftReference)v1).get();
                if(v1 != null) {
                    goto label_7;
                }
            }

            goto label_9;
        }
        catch(Throwable v12) {
            goto label_46;
        }

    label_7:
        __monitor_exit(v0);
        return ((long[])v1);
    label_9:
        long v1_1 = 0;
        try {
            Iterator v3 = arg12.iterator();
            while(v3.hasNext()) {
                v1_1 += v3.next().getCount();
            }

            v1_2 = new long[((int)v1_1)];
            Iterator v2 = arg12.iterator();
            int v4;
            for(v4 = 0; v2.hasNext(); v4 = v6) {
                Object v5 = v2.next();
                int v6 = v4;
                v4 = 0;
                while((((long)v4)) < ((Entry)v5).getCount()) {
                    v1_2[v6] = ((Entry)v5).getDelta();
                    ++v4;
                    ++v6;
                }
            }

            TimeToSampleBox.cache.put(arg12, new SoftReference(v1_2));
        }
        catch(Throwable v12) {
            goto label_46;
        }

        __monitor_exit(v0);
        return v1_2;
    label_46:
        __monitor_exit(v0);
        throw v12;
    }

    protected void getContent(ByteBuffer arg5) {
        this.writeVersionAndFlags(arg5);
        IsoTypeWriter.writeUInt32(arg5, ((long)this.entries.size()));
        Iterator v0 = this.entries.iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            IsoTypeWriter.writeUInt32(arg5, ((Entry)v1).getCount());
            IsoTypeWriter.writeUInt32(arg5, ((Entry)v1).getDelta());
        }
    }

    protected long getContentSize() {
        return ((long)(this.entries.size() * 8 + 8));
    }

    public List getEntries() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TimeToSampleBox.ajc$tjp_0, this, this));
        return this.entries;
    }

    public void setEntries(List arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TimeToSampleBox.ajc$tjp_1, this, this, arg3));
        this.entries = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TimeToSampleBox.ajc$tjp_2, this, this));
        StringBuilder v0 = new StringBuilder("TimeToSampleBox[entryCount=");
        v0.append(this.entries.size());
        v0.append("]");
        return v0.toString();
    }
}

