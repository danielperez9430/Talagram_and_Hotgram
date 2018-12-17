package com.coremedia.iso.boxes;

import com.coremedia.iso.BoxParser;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.DataSource;
import com.googlecode.mp4parser.util.CastUtils;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FreeBox implements Box {
    public static final String TYPE = "free";
    ByteBuffer data;
    private long offset;
    private Container parent;
    List replacers;

    static {
    }

    public FreeBox() {
        super();
        this.replacers = new LinkedList();
        this.data = ByteBuffer.wrap(new byte[0]);
    }

    public FreeBox(int arg2) {
        super();
        this.replacers = new LinkedList();
        this.data = ByteBuffer.allocate(arg2);
    }

    public void addAndReplace(Box arg4) {
        this.data.position(CastUtils.l2i(arg4.getSize()));
        this.data = this.data.slice();
        this.replacers.add(arg4);
    }

    public boolean equals(Object arg5) {
        if(this == (((FreeBox)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(this.getData() != null) {
                    if(!this.getData().equals(((FreeBox)arg5).getData())) {
                        return 0;
                    }
                }
                else if(((FreeBox)arg5).getData() != null) {
                    return 0;
                }

                return 1;
            }
        }

        return 0;
    }

    public void getBox(WritableByteChannel arg5) {
        Iterator v0 = this.replacers.iterator();
        while(v0.hasNext()) {
            v0.next().getBox(arg5);
        }

        ByteBuffer v1 = ByteBuffer.allocate(8);
        IsoTypeWriter.writeUInt32(v1, ((long)(this.data.limit() + 8)));
        v1.put("free".getBytes());
        v1.rewind();
        arg5.write(v1);
        v1.rewind();
        this.data.rewind();
        arg5.write(this.data);
        this.data.rewind();
    }

    public ByteBuffer getData() {
        if(this.data != null) {
            return this.data.duplicate().rewind();
        }

        return null;
    }

    public long getOffset() {
        return this.offset;
    }

    public Container getParent() {
        return this.parent;
    }

    public long getSize() {
        Iterator v0 = this.replacers.iterator();
        long v1;
        for(v1 = 8; v0.hasNext(); v1 += v0.next().getSize()) {
        }

        return v1 + (((long)this.data.limit()));
    }

    public String getType() {
        return "free";
    }

    public int hashCode() {
        int v0 = this.data != null ? this.data.hashCode() : 0;
        return v0;
    }

    public void parse(DataSource arg5, ByteBuffer arg6, long arg7, BoxParser arg9) {
        this.offset = arg5.position() - (((long)arg6.remaining()));
        if(arg7 > 1048576) {
            this.data = arg5.map(arg5.position(), arg7);
            arg5.position(arg5.position() + arg7);
        }
        else {
            this.data = ByteBuffer.allocate(CastUtils.l2i(arg7));
            arg5.read(this.data);
        }
    }

    public void setData(ByteBuffer arg1) {
        this.data = arg1;
    }

    public void setParent(Container arg1) {
        this.parent = arg1;
    }
}

