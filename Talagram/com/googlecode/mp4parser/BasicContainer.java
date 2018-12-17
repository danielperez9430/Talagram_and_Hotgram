package com.googlecode.mp4parser;

import com.coremedia.iso.BoxParser;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.Container;
import com.googlecode.mp4parser.util.CastUtils;
import com.googlecode.mp4parser.util.LazyList;
import com.googlecode.mp4parser.util.Logger;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BasicContainer implements Container, Closeable, Iterator {
    class com.googlecode.mp4parser.BasicContainer$1 extends AbstractBox {
        com.googlecode.mp4parser.BasicContainer$1(String arg1) {
            super(arg1);
        }

        protected void _parseDetails(ByteBuffer arg1) {
        }

        protected void getContent(ByteBuffer arg1) {
        }

        protected long getContentSize() {
            return 0;
        }
    }

    private static final Box EOF;
    private static Logger LOG;
    protected BoxParser boxParser;
    private List boxes;
    protected DataSource dataSource;
    long endPosition;
    Box lookahead;
    long parsePosition;
    long startPosition;

    static {
        BasicContainer.EOF = new com.googlecode.mp4parser.BasicContainer$1("eof ");
        BasicContainer.LOG = Logger.getLogger(BasicContainer.class);
    }

    public BasicContainer() {
        super();
        this.lookahead = null;
        this.parsePosition = 0;
        this.startPosition = 0;
        this.endPosition = 0;
        this.boxes = new ArrayList();
    }

    public void addBox(Box arg3) {
        if(arg3 != null) {
            this.boxes = new ArrayList(this.getBoxes());
            arg3.setParent(((Container)this));
            this.boxes.add(arg3);
        }
    }

    public void close() {
        this.dataSource.close();
    }

    public List getBoxes() {
        if(this.dataSource != null && this.lookahead != BasicContainer.EOF) {
            return new LazyList(this.boxes, ((Iterator)this));
        }

        return this.boxes;
    }

    public List getBoxes(Class arg7) {
        List v0 = this.getBoxes();
        ArrayList v1 = null;
        int v2 = 0;
        Object v3 = v1;
        while(v2 < v0.size()) {
            Object v4 = v0.get(v2);
            if(arg7.isInstance(v4)) {
                if(v3 == null) {
                    v3 = v4;
                }
                else {
                    if(v1 == null) {
                        v1 = new ArrayList(2);
                        ((List)v1).add(v3);
                    }

                    ((List)v1).add(v4);
                }
            }

            ++v2;
        }

        if(v1 != null) {
            return ((List)v1);
        }

        if(v3 != null) {
            return Collections.singletonList(v3);
        }

        return Collections.emptyList();
    }

    public List getBoxes(Class arg6, boolean arg7) {
        ArrayList v0 = new ArrayList(2);
        List v1 = this.getBoxes();
        int v2;
        for(v2 = 0; v2 < v1.size(); ++v2) {
            Object v3 = v1.get(v2);
            if(arg6.isInstance(v3)) {
                ((List)v0).add(v3);
            }

            if((arg7) && ((v3 instanceof Container))) {
                ((List)v0).addAll(((Container)v3).getBoxes(arg6, arg7));
            }
        }

        return ((List)v0);
    }

    public ByteBuffer getByteBuffer(long arg16, long arg18) {
        long v11_1;
        BasicContainer v1 = this;
        long v2 = arg18;
        if(v1.dataSource != null) {
            DataSource v4 = v1.dataSource;
            __monitor_enter(v4);
            try {
                __monitor_exit(v4);
                return v1.dataSource.map(v1.startPosition + arg16, v2);
            label_14:
                __monitor_exit(v4);
            }
            catch(Throwable v0) {
                goto label_14;
            }

            throw v0;
        }

        ByteBuffer v0_1 = ByteBuffer.allocate(CastUtils.l2i(arg18));
        v2 = arg16 + v2;
        long v4_1 = 0;
        Iterator v6 = v1.boxes.iterator();
        while(v6.hasNext()) {
            Object v7 = v6.next();
            long v8 = ((Box)v7).getSize() + v4_1;
            if(v8 > arg16 && v4_1 < v2) {
                ByteArrayOutputStream v10 = new ByteArrayOutputStream();
                WritableByteChannel v11 = Channels.newChannel(((OutputStream)v10));
                ((Box)v7).getBox(v11);
                v11.close();
                if(v4_1 >= arg16 && v8 <= v2) {
                    v0_1.put(v10.toByteArray());
                    goto label_68;
                }

                if(v4_1 >= arg16 || v8 <= v2) {
                    if(v4_1 < arg16 && v8 <= v2) {
                        v4_1 = arg16 - v4_1;
                        v11_1 = ((Box)v7).getSize() - v4_1;
                        goto label_48;
                    }

                    goto label_59;
                }
                else {
                    v4_1 = arg16 - v4_1;
                    v11_1 = ((Box)v7).getSize() - v4_1 - (v8 - v2);
                }

            label_48:
                v0_1.put(v10.toByteArray(), CastUtils.l2i(v4_1), CastUtils.l2i(v11_1));
                goto label_68;
            label_59:
                if(v4_1 < arg16) {
                    goto label_68;
                }

                if(v8 <= v2) {
                    goto label_68;
                }

                v0_1.put(v10.toByteArray(), 0, CastUtils.l2i(((Box)v7).getSize() - (v8 - v2)));
            }

        label_68:
            v4_1 = v8;
        }

        return v0_1.rewind();
    }

    protected long getContainerSize() {
        long v0 = 0;
        int v2;
        for(v2 = 0; v2 < this.getBoxes().size(); ++v2) {
            v0 += this.boxes.get(v2).getSize();
        }

        return v0;
    }

    public boolean hasNext() {
        if(this.lookahead == BasicContainer.EOF) {
            return 0;
        }

        if(this.lookahead != null) {
            return 1;
        }

        try {
            this.lookahead = this.next();
            return 1;
        }
        catch(NoSuchElementException ) {
            this.lookahead = BasicContainer.EOF;
            return 0;
        }
    }

    public void initContainer(DataSource arg3, long arg4, BoxParser arg6) {
        this.dataSource = arg3;
        long v0 = arg3.position();
        this.startPosition = v0;
        this.parsePosition = v0;
        arg3.position(arg3.position() + arg4);
        this.endPosition = arg3.position();
        this.boxParser = arg6;
    }

    public Box next() {
        DataSource v0_1;
        if(this.lookahead != null && this.lookahead != BasicContainer.EOF) {
            Box v0 = this.lookahead;
            this.lookahead = null;
            return v0;
        }

        if(this.dataSource != null && this.parsePosition < this.endPosition) {
            try {
                v0_1 = this.dataSource;
                __monitor_enter(v0_1);
            }
            catch(EOFException ) {
                goto label_33;
            }
            catch(IOException ) {
                goto label_30;
            }

            try {
                this.dataSource.position(this.parsePosition);
                Box v1_1 = this.boxParser.parseBox(this.dataSource, ((Container)this));
                this.parsePosition = this.dataSource.position();
                __monitor_exit(v0_1);
                return v1_1;
            label_28:
                __monitor_exit(v0_1);
            }
            catch(Throwable v1) {
                goto label_28;
            }

            try {
                throw v1;
            }
            catch(IOException ) {
            label_30:
                throw new NoSuchElementException();
            }
            catch(EOFException ) {
            label_33:
                throw new NoSuchElementException();
            }
        }

        this.lookahead = BasicContainer.EOF;
        throw new NoSuchElementException();
    }

    public Object next() {
        return this.next();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public void setBoxes(List arg2) {
        this.boxes = new ArrayList(((Collection)arg2));
        this.lookahead = BasicContainer.EOF;
        this.dataSource = null;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder();
        v0.append(this.getClass().getSimpleName());
        v0.append("[");
        int v1;
        for(v1 = 0; v1 < this.boxes.size(); ++v1) {
            if(v1 > 0) {
                v0.append(";");
            }

            v0.append(this.boxes.get(v1).toString());
        }

        v0.append("]");
        return v0.toString();
    }

    public final void writeContainer(WritableByteChannel arg3) {
        Iterator v0 = this.getBoxes().iterator();
        while(v0.hasNext()) {
            v0.next().getBox(arg3);
        }
    }
}

