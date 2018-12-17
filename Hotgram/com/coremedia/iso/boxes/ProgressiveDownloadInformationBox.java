package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.a.b.b.b;

public class ProgressiveDownloadInformationBox extends AbstractFullBox {
    public class Entry {
        long initialDelay;
        long rate;

        public Entry(long arg1, long arg3) {
            super();
            this.rate = arg1;
            this.initialDelay = arg3;
        }

        public boolean equals(Object arg8) {
            if(this == (((Entry)arg8))) {
                return 1;
            }

            if(arg8 != null) {
                if(this.getClass() != arg8.getClass()) {
                }
                else if(this.initialDelay != ((Entry)arg8).initialDelay) {
                    return 0;
                }
                else if(this.rate != ((Entry)arg8).rate) {
                    return 0;
                }
                else {
                    return 1;
                }
            }

            return 0;
        }

        public long getInitialDelay() {
            return this.initialDelay;
        }

        public long getRate() {
            return this.rate;
        }

        public int hashCode() {
            return (((int)(this.rate ^ this.rate >>> 32))) * 31 + (((int)(this.initialDelay ^ this.initialDelay >>> 32)));
        }

        public void setInitialDelay(long arg1) {
            this.initialDelay = arg1;
        }

        public void setRate(long arg1) {
            this.rate = arg1;
        }

        public String toString() {
            StringBuilder v0 = new StringBuilder("Entry{rate=");
            v0.append(this.rate);
            v0.append(", initialDelay=");
            v0.append(this.initialDelay);
            v0.append('}');
            return v0.toString();
        }
    }

    public static final String TYPE = "pdin";
    List entries;

    static {
        ProgressiveDownloadInformationBox.ajc$preClinit();
    }

    public ProgressiveDownloadInformationBox() {
        super("pdin");
        this.entries = Collections.emptyList();
    }

    public void _parseDetails(ByteBuffer arg6) {
        this.parseVersionAndFlags(arg6);
        this.entries = new LinkedList();
        while(arg6.remaining() >= 8) {
            this.entries.add(new Entry(IsoTypeReader.readUInt32(arg6), IsoTypeReader.readUInt32(arg6)));
        }
    }

    private static void ajc$preClinit() {
        b v8 = new b("ProgressiveDownloadInformationBox.java", ProgressiveDownloadInformationBox.class);
        ProgressiveDownloadInformationBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getEntries", "com.coremedia.iso.boxes.ProgressiveDownloadInformationBox", "", "", "", "java.util.List"), 38);
        ProgressiveDownloadInformationBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setEntries", "com.coremedia.iso.boxes.ProgressiveDownloadInformationBox", "java.util.List", "entries", "", "void"), 42);
        ProgressiveDownloadInformationBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.ProgressiveDownloadInformationBox", "", "", "", "java.lang.String"), 112);
    }

    protected void getContent(ByteBuffer arg5) {
        this.writeVersionAndFlags(arg5);
        Iterator v0 = this.entries.iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            IsoTypeWriter.writeUInt32(arg5, ((Entry)v1).getRate());
            IsoTypeWriter.writeUInt32(arg5, ((Entry)v1).getInitialDelay());
        }
    }

    protected long getContentSize() {
        return ((long)(this.entries.size() * 8 + 4));
    }

    public List getEntries() {
        RequiresParseDetailAspect.aspectOf().before(b.a(ProgressiveDownloadInformationBox.ajc$tjp_0, this, this));
        return this.entries;
    }

    public void setEntries(List arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(ProgressiveDownloadInformationBox.ajc$tjp_1, this, this, arg3));
        this.entries = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(ProgressiveDownloadInformationBox.ajc$tjp_2, this, this));
        StringBuilder v0 = new StringBuilder("ProgressiveDownloadInfoBox{entries=");
        v0.append(this.entries);
        v0.append('}');
        return v0.toString();
    }
}

