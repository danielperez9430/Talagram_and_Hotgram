package com.coremedia.iso.boxes.mdat;

import com.coremedia.iso.BoxParser;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.Container;
import com.googlecode.mp4parser.DataSource;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.logging.Logger;

public final class MediaDataBox implements Box {
    private static Logger LOG = null;
    public static final String TYPE = "mdat";
    private DataSource dataSource;
    boolean largeBox;
    private long offset;
    Container parent;
    private long size;

    static {
        MediaDataBox.LOG = Logger.getLogger(MediaDataBox.class.getName());
    }

    public MediaDataBox() {
        super();
        this.largeBox = false;
    }

    public void getBox(WritableByteChannel arg7) {
        MediaDataBox.transfer(this.dataSource, this.offset, this.size, arg7);
    }

    public long getOffset() {
        return this.offset;
    }

    public Container getParent() {
        return this.parent;
    }

    public long getSize() {
        return this.size;
    }

    public String getType() {
        return "mdat";
    }

    public void parse(DataSource arg5, ByteBuffer arg6, long arg7, BoxParser arg9) {
        this.offset = arg5.position() - (((long)arg6.remaining()));
        this.dataSource = arg5;
        this.size = (((long)arg6.remaining())) + arg7;
        arg5.position(arg5.position() + arg7);
    }

    public void setParent(Container arg1) {
        this.parent = arg1;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder("MediaDataBox{size=");
        v0.append(this.size);
        v0.append('}');
        return v0.toString();
    }

    private static void transfer(DataSource arg9, long arg10, long arg12, WritableByteChannel arg14) {
        long v0;
        for(v0 = 0; v0 < arg12; v0 += arg9.transferTo(arg10 + v0, Math.min(67076096, arg12 - v0), arg14)) {
        }
    }
}

