package com.googlecode.mp4parser;

import com.coremedia.iso.BoxParser;
import com.coremedia.iso.Hex;
import com.coremedia.iso.IsoFile;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.Container;
import com.googlecode.mp4parser.annotations.DoNotParseDetail;
import com.googlecode.mp4parser.util.CastUtils;
import com.googlecode.mp4parser.util.Logger;
import com.googlecode.mp4parser.util.Path;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

public abstract class AbstractBox implements Box {
    private static Logger LOG;
    private ByteBuffer content;
    long contentStartPosition;
    DataSource dataSource;
    private ByteBuffer deadBytes;
    boolean isParsed;
    boolean isRead;
    long memMapSize;
    long offset;
    private Container parent;
    protected String type;
    private byte[] userType;

    static {
        AbstractBox.LOG = Logger.getLogger(AbstractBox.class);
    }

    protected AbstractBox(String arg3) {
        super();
        this.memMapSize = -1;
        this.deadBytes = null;
        this.type = arg3;
        this.isRead = true;
        this.isParsed = true;
    }

    protected AbstractBox(String arg3, byte[] arg4) {
        super();
        this.memMapSize = -1;
        this.deadBytes = null;
        this.type = arg3;
        this.userType = arg4;
        this.isRead = true;
        this.isParsed = true;
    }

    protected abstract void _parseDetails(ByteBuffer arg1);

    public void getBox(WritableByteChannel arg8) {
        Buffer v0_1;
        ByteBuffer v0;
        int v1 = 8;
        int v2 = 0;
        int v3 = 16;
        if(this.isRead) {
            if(this.isParsed) {
                v0 = ByteBuffer.allocate(CastUtils.l2i(this.getSize()));
                this.getHeader(v0);
                this.getContent(v0);
                if(this.deadBytes != null) {
                    this.deadBytes.rewind();
                    while(this.deadBytes.remaining() > 0) {
                        v0.put(this.deadBytes);
                    }
                }

                v0_1 = v0.rewind();
            }
            else {
                if(this.isSmallBox()) {
                }
                else {
                    v1 = 16;
                }

                if("uuid".equals(this.getType())) {
                }
                else {
                    v3 = 0;
                }

                v0 = ByteBuffer.allocate(v1 + v3);
                this.getHeader(v0);
                arg8.write(v0.rewind());
                v0_1 = this.content.position(0);
            }

            arg8.write(((ByteBuffer)v0_1));
        }
        else {
            if(this.isSmallBox()) {
            }
            else {
                v1 = 16;
            }

            if("uuid".equals(this.getType())) {
                v2 = 16;
            }

            v0 = ByteBuffer.allocate(v1 + v2);
            this.getHeader(v0);
            arg8.write(v0.rewind());
            this.dataSource.transferTo(this.contentStartPosition, this.memMapSize, arg8);
        }
    }

    protected abstract void getContent(ByteBuffer arg1);

    protected abstract long getContentSize();

    private void getHeader(ByteBuffer arg3) {
        if(this.isSmallBox()) {
            IsoTypeWriter.writeUInt32(arg3, this.getSize());
            arg3.put(IsoFile.fourCCtoBytes(this.getType()));
        }
        else {
            IsoTypeWriter.writeUInt32(arg3, 1);
            arg3.put(IsoFile.fourCCtoBytes(this.getType()));
            IsoTypeWriter.writeUInt64(arg3, this.getSize());
        }

        if("uuid".equals(this.getType())) {
            arg3.put(this.getUserType());
        }
    }

    public long getOffset() {
        return this.offset;
    }

    @DoNotParseDetail public Container getParent() {
        return this.parent;
    }

    @DoNotParseDetail public String getPath() {
        return Path.createPath(((Box)this));
    }

    public long getSize() {
        // Method was not decompiled
    }

    @DoNotParseDetail public String getType() {
        return this.type;
    }

    @DoNotParseDetail public byte[] getUserType() {
        return this.userType;
    }

    public boolean isParsed() {
        return this.isParsed;
    }

    private boolean isSmallBox() {
        int v0 = "uuid".equals(this.getType()) ? 24 : 8;
        long v3 = 4294967296L;
        if(this.isRead) {
            if(this.isParsed) {
                long v6 = this.getContentSize();
                int v1 = this.deadBytes != null ? this.deadBytes.limit() : 0;
                if(v6 + (((long)v1)) + (((long)v0)) < v3) {
                    return 1;
                }

                return 0;
            }

            if((((long)(this.content.limit() + v0))) < v3) {
                return 1;
            }

            return 0;
        }

        if(this.memMapSize + (((long)v0)) < v3) {
            return 1;
        }

        return 0;
    }

    @DoNotParseDetail public void parse(DataSource arg5, ByteBuffer arg6, long arg7, BoxParser arg9) {
        this.contentStartPosition = arg5.position();
        this.offset = this.contentStartPosition - (((long)arg6.remaining()));
        this.memMapSize = arg7;
        this.dataSource = arg5;
        arg5.position(arg5.position() + arg7);
        this.isRead = false;
        this.isParsed = false;
    }

    public final void parseDetails() {
        __monitor_enter(this);
        try {
            this.readContent();
            Logger v0_1 = AbstractBox.LOG;
            StringBuilder v1 = new StringBuilder("parsing details of ");
            v1.append(this.getType());
            v0_1.logDebug(v1.toString());
            if(this.content != null) {
                ByteBuffer v0_2 = this.content;
                this.isParsed = true;
                v0_2.rewind();
                this._parseDetails(v0_2);
                if(v0_2.remaining() > 0) {
                    this.deadBytes = v0_2.slice();
                }

                this.content = null;
            }
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }

    private void readContent() {
        __monitor_enter(this);
        try {
            if(!this.isRead) {
                try {
                    Logger v0_2 = AbstractBox.LOG;
                    StringBuilder v1 = new StringBuilder("mem mapping ");
                    v1.append(this.getType());
                    v0_2.logDebug(v1.toString());
                    this.content = this.dataSource.map(this.contentStartPosition, this.memMapSize);
                }
                catch(IOException v0_1) {
                    throw new RuntimeException(((Throwable)v0_1));
                }

                this.isRead = true;
            }
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }

    protected void setDeadBytes(ByteBuffer arg1) {
        this.deadBytes = arg1;
    }

    @DoNotParseDetail public void setParent(Container arg1) {
        this.parent = arg1;
    }

    private boolean verify(ByteBuffer arg12) {
        long v0 = this.getContentSize();
        int v2 = this.deadBytes != null ? this.deadBytes.limit() : 0;
        ByteBuffer v0_1 = ByteBuffer.allocate(CastUtils.l2i(v0 + (((long)v2))));
        this.getContent(v0_1);
        if(this.deadBytes != null) {
            this.deadBytes.rewind();
            while(this.deadBytes.remaining() > 0) {
                v0_1.put(this.deadBytes);
            }
        }

        arg12.rewind();
        v0_1.rewind();
        if(arg12.remaining() != v0_1.remaining()) {
            PrintStream v1 = System.err;
            StringBuilder v2_1 = new StringBuilder(String.valueOf(this.getType()));
            v2_1.append(": remaining differs ");
            v2_1.append(arg12.remaining());
            v2_1.append(" vs. ");
            v2_1.append(v0_1.remaining());
            v1.print(v2_1.toString());
            Logger v1_1 = AbstractBox.LOG;
            v2_1 = new StringBuilder(String.valueOf(this.getType()));
            v2_1.append(": remaining differs ");
            v2_1.append(arg12.remaining());
            v2_1.append(" vs. ");
            v2_1.append(v0_1.remaining());
            v1_1.logError(v2_1.toString());
            return 0;
        }

        int v1_2 = arg12.position();
        v2 = arg12.limit() - 1;
        int v5;
        for(v5 = v0_1.limit() - 1; v2 >= v1_2; --v5) {
            byte v6 = arg12.get(v2);
            byte v7 = v0_1.get(v5);
            if(v6 != v7) {
                AbstractBox.LOG.logError(String.format("%s: buffers differ at %d: %2X/%2X", this.getType(), Integer.valueOf(v2), Byte.valueOf(v6), Byte.valueOf(v7)));
                byte[] v1_3 = new byte[arg12.remaining()];
                byte[] v2_2 = new byte[v0_1.remaining()];
                arg12.get(v1_3);
                v0_1.get(v2_2);
                PrintStream v12 = System.err;
                StringBuilder v0_2 = new StringBuilder("original      : ");
                v0_2.append(Hex.encodeHex(v1_3, 4));
                v12.println(v0_2.toString());
                v12 = System.err;
                v0_2 = new StringBuilder("reconstructed : ");
                v0_2.append(Hex.encodeHex(v2_2, 4));
                v12.println(v0_2.toString());
                return 0;
            }

            --v2;
        }

        return 1;
    }
}

