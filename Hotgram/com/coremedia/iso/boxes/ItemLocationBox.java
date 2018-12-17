package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeReaderVariable;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.IsoTypeWriterVariable;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.a.b.a.a;
import org.a.b.b.b;

public class ItemLocationBox extends AbstractFullBox {
    public class Extent {
        public long extentIndex;
        public long extentLength;
        public long extentOffset;

        public Extent(ItemLocationBox arg1, long arg2, long arg4, long arg6) {
            ItemLocationBox.this = arg1;
            super();
            this.extentOffset = arg2;
            this.extentLength = arg4;
            this.extentIndex = arg6;
        }

        public Extent(ItemLocationBox arg3, ByteBuffer arg4) {
            ItemLocationBox.this = arg3;
            super();
            if(arg3.getVersion() == 1 && arg3.indexSize > 0) {
                this.extentIndex = IsoTypeReaderVariable.read(arg4, arg3.indexSize);
            }

            this.extentOffset = IsoTypeReaderVariable.read(arg4, arg3.offsetSize);
            this.extentLength = IsoTypeReaderVariable.read(arg4, arg3.lengthSize);
        }

        public boolean equals(Object arg8) {
            if(this == (((Extent)arg8))) {
                return 1;
            }

            if(arg8 != null) {
                if(this.getClass() != arg8.getClass()) {
                }
                else if(this.extentIndex != ((Extent)arg8).extentIndex) {
                    return 0;
                }
                else if(this.extentLength != ((Extent)arg8).extentLength) {
                    return 0;
                }
                else if(this.extentOffset != ((Extent)arg8).extentOffset) {
                    return 0;
                }
                else {
                    return 1;
                }
            }

            return 0;
        }

        public void getContent(ByteBuffer arg4) {
            if(ItemLocationBox.this.getVersion() == 1 && ItemLocationBox.this.indexSize > 0) {
                IsoTypeWriterVariable.write(this.extentIndex, arg4, ItemLocationBox.this.indexSize);
            }

            IsoTypeWriterVariable.write(this.extentOffset, arg4, ItemLocationBox.this.offsetSize);
            IsoTypeWriterVariable.write(this.extentLength, arg4, ItemLocationBox.this.lengthSize);
        }

        public int getSize() {
            int v0 = ItemLocationBox.this.indexSize > 0 ? ItemLocationBox.this.indexSize : 0;
            return v0 + ItemLocationBox.this.offsetSize + ItemLocationBox.this.lengthSize;
        }

        public int hashCode() {
            return ((((int)(this.extentOffset ^ this.extentOffset >>> 32))) * 31 + (((int)(this.extentLength ^ this.extentLength >>> 32)))) * 31 + (((int)(this.extentIndex ^ this.extentIndex >>> 32)));
        }

        public String toString() {
            return "Extent" + "{extentOffset=" + this.extentOffset + ", extentLength=" + this.extentLength + ", extentIndex=" + this.extentIndex + '}';
        }
    }

    public class Item {
        public long baseOffset;
        public int constructionMethod;
        public int dataReferenceIndex;
        public List extents;
        public int itemId;

        public Item(ItemLocationBox arg5, ByteBuffer arg6) {
            ItemLocationBox.this = arg5;
            super();
            this.extents = new LinkedList();
            this.itemId = IsoTypeReader.readUInt16(arg6);
            if(arg5.getVersion() == 1) {
                this.constructionMethod = IsoTypeReader.readUInt16(arg6) & 15;
            }

            this.dataReferenceIndex = IsoTypeReader.readUInt16(arg6);
            long v0 = arg5.baseOffsetSize > 0 ? IsoTypeReaderVariable.read(arg6, arg5.baseOffsetSize) : 0;
            this.baseOffset = v0;
            int v0_1 = IsoTypeReader.readUInt16(arg6);
            int v1;
            for(v1 = 0; v1 < v0_1; ++v1) {
                this.extents.add(new Extent(arg5, arg6));
            }
        }

        public Item(ItemLocationBox arg1, int arg2, int arg3, int arg4, long arg5, List arg7) {
            ItemLocationBox.this = arg1;
            super();
            this.extents = new LinkedList();
            this.itemId = arg2;
            this.constructionMethod = arg3;
            this.dataReferenceIndex = arg4;
            this.baseOffset = arg5;
            this.extents = arg7;
        }

        public boolean equals(Object arg8) {
            if(this == (((Item)arg8))) {
                return 1;
            }

            if(arg8 != null) {
                if(this.getClass() != arg8.getClass()) {
                }
                else if(this.baseOffset != ((Item)arg8).baseOffset) {
                    return 0;
                }
                else if(this.constructionMethod != ((Item)arg8).constructionMethod) {
                    return 0;
                }
                else if(this.dataReferenceIndex != ((Item)arg8).dataReferenceIndex) {
                    return 0;
                }
                else if(this.itemId != ((Item)arg8).itemId) {
                    return 0;
                }
                else {
                    if(this.extents != null) {
                        if(!this.extents.equals(((Item)arg8).extents)) {
                            return 0;
                        }
                    }
                    else if(((Item)arg8).extents != null) {
                        return 0;
                    }

                    return 1;
                }
            }

            return 0;
        }

        public void getContent(ByteBuffer arg4) {
            IsoTypeWriter.writeUInt16(arg4, this.itemId);
            if(ItemLocationBox.this.getVersion() == 1) {
                IsoTypeWriter.writeUInt16(arg4, this.constructionMethod);
            }

            IsoTypeWriter.writeUInt16(arg4, this.dataReferenceIndex);
            if(ItemLocationBox.this.baseOffsetSize > 0) {
                IsoTypeWriterVariable.write(this.baseOffset, arg4, ItemLocationBox.this.baseOffsetSize);
            }

            IsoTypeWriter.writeUInt16(arg4, this.extents.size());
            Iterator v0 = this.extents.iterator();
            while(v0.hasNext()) {
                v0.next().getContent(arg4);
            }
        }

        public int getSize() {
            int v1 = 2;
            int v0 = ItemLocationBox.this.getVersion() == 1 ? 4 : 2;
            v0 = v0 + v1 + ItemLocationBox.this.baseOffsetSize + v1;
            Iterator v1_1 = this.extents.iterator();
            while(v1_1.hasNext()) {
                v0 += v1_1.next().getSize();
            }

            return v0;
        }

        public int hashCode() {
            int v0 = (((this.itemId * 31 + this.constructionMethod) * 31 + this.dataReferenceIndex) * 31 + (((int)(this.baseOffset ^ this.baseOffset >>> 32)))) * 31;
            int v1 = this.extents != null ? this.extents.hashCode() : 0;
            return v0 + v1;
        }

        public void setBaseOffset(long arg1) {
            this.baseOffset = arg1;
        }

        public String toString() {
            StringBuilder v0 = new StringBuilder("Item{baseOffset=");
            v0.append(this.baseOffset);
            v0.append(", itemId=");
            v0.append(this.itemId);
            v0.append(", constructionMethod=");
            v0.append(this.constructionMethod);
            v0.append(", dataReferenceIndex=");
            v0.append(this.dataReferenceIndex);
            v0.append(", extents=");
            v0.append(this.extents);
            v0.append('}');
            return v0.toString();
        }
    }

    public static final String TYPE = "iloc";
    public int baseOffsetSize;
    public int indexSize;
    public List items;
    public int lengthSize;
    public int offsetSize;

    static {
        ItemLocationBox.ajc$preClinit();
    }

    public ItemLocationBox() {
        super("iloc");
        this.offsetSize = 8;
        this.lengthSize = 8;
        this.baseOffsetSize = 8;
        this.indexSize = 0;
        this.items = new LinkedList();
    }

    public void _parseDetails(ByteBuffer arg5) {
        this.parseVersionAndFlags(arg5);
        int v0 = IsoTypeReader.readUInt8(arg5);
        this.offsetSize = v0 >>> 4;
        this.lengthSize = v0 & 15;
        v0 = IsoTypeReader.readUInt8(arg5);
        this.baseOffsetSize = v0 >>> 4;
        if(this.getVersion() == 1) {
            this.indexSize = v0 & 15;
        }

        v0 = IsoTypeReader.readUInt16(arg5);
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.items.add(new Item(this, arg5));
        }
    }

    private static void ajc$preClinit() {
        b v8 = new b("ItemLocationBox.java", ItemLocationBox.class);
        ItemLocationBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getOffsetSize", "com.coremedia.iso.boxes.ItemLocationBox", "", "", "", "int"), 119);
        ItemLocationBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setOffsetSize", "com.coremedia.iso.boxes.ItemLocationBox", "int", "offsetSize", "", "void"), 123);
        ItemLocationBox.ajc$tjp_10 = v8.a("method-execution", v8.a("1", "createItem", "com.coremedia.iso.boxes.ItemLocationBox", "int:int:int:long:java.util.List", "itemId:constructionMethod:dataReferenceIndex:baseOffset:extents", "", "com.coremedia.iso.boxes.ItemLocationBox$Item"), 160);
        ItemLocationBox.ajc$tjp_11 = v8.a("method-execution", v8.a("1", "createExtent", "com.coremedia.iso.boxes.ItemLocationBox", "long:long:long", "extentOffset:extentLength:extentIndex", "", "com.coremedia.iso.boxes.ItemLocationBox$Extent"), 285);
        ItemLocationBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "getLengthSize", "com.coremedia.iso.boxes.ItemLocationBox", "", "", "", "int"), 127);
        ItemLocationBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "setLengthSize", "com.coremedia.iso.boxes.ItemLocationBox", "int", "lengthSize", "", "void"), 131);
        ItemLocationBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "getBaseOffsetSize", "com.coremedia.iso.boxes.ItemLocationBox", "", "", "", "int"), 135);
        ItemLocationBox.ajc$tjp_5 = v8.a("method-execution", v8.a("1", "setBaseOffsetSize", "com.coremedia.iso.boxes.ItemLocationBox", "int", "baseOffsetSize", "", "void"), 139);
        ItemLocationBox.ajc$tjp_6 = v8.a("method-execution", v8.a("1", "getIndexSize", "com.coremedia.iso.boxes.ItemLocationBox", "", "", "", "int"), 143);
        ItemLocationBox.ajc$tjp_7 = v8.a("method-execution", v8.a("1", "setIndexSize", "com.coremedia.iso.boxes.ItemLocationBox", "int", "indexSize", "", "void"), 147);
        ItemLocationBox.ajc$tjp_8 = v8.a("method-execution", v8.a("1", "getItems", "com.coremedia.iso.boxes.ItemLocationBox", "", "", "", "java.util.List"), 151);
        ItemLocationBox.ajc$tjp_9 = v8.a("method-execution", v8.a("1", "setItems", "com.coremedia.iso.boxes.ItemLocationBox", "java.util.List", "items", "", "void"), 155);
    }

    public Extent createExtent(long arg13, long arg15, long arg17) {
        RequiresParseDetailAspect.aspectOf().before(b.a(ItemLocationBox.ajc$tjp_11, this, this, new Object[]{a.a(arg13), a.a(arg15), a.a(arg17)}));
        return new Extent(this, arg13, arg15, arg17);
    }

    Extent createExtent(ByteBuffer arg2) {
        return new Extent(this, arg2);
    }

    public Item createItem(int arg12, int arg13, int arg14, long arg15, List arg17) {
        RequiresParseDetailAspect.aspectOf().before(b.a(ItemLocationBox.ajc$tjp_10, this, this, new Object[]{a.a(arg12), a.a(arg13), a.a(arg14), a.a(arg15), arg17}));
        return new Item(this, arg12, arg13, arg14, arg15, arg17);
    }

    Item createItem(ByteBuffer arg2) {
        return new Item(this, arg2);
    }

    public int getBaseOffsetSize() {
        RequiresParseDetailAspect.aspectOf().before(b.a(ItemLocationBox.ajc$tjp_4, this, this));
        return this.baseOffsetSize;
    }

    protected void getContent(ByteBuffer arg3) {
        this.writeVersionAndFlags(arg3);
        IsoTypeWriter.writeUInt8(arg3, this.offsetSize << 4 | this.lengthSize);
        int v0 = this.getVersion() == 1 ? this.baseOffsetSize << 4 | this.indexSize : this.baseOffsetSize << 4;
        IsoTypeWriter.writeUInt8(arg3, v0);
        IsoTypeWriter.writeUInt16(arg3, this.items.size());
        Iterator v0_1 = this.items.iterator();
        while(v0_1.hasNext()) {
            v0_1.next().getContent(arg3);
        }
    }

    protected long getContentSize() {
        Iterator v0 = this.items.iterator();
        long v1;
        for(v1 = 8; v0.hasNext(); v1 += ((long)v0.next().getSize())) {
        }

        return v1;
    }

    public int getIndexSize() {
        RequiresParseDetailAspect.aspectOf().before(b.a(ItemLocationBox.ajc$tjp_6, this, this));
        return this.indexSize;
    }

    public List getItems() {
        RequiresParseDetailAspect.aspectOf().before(b.a(ItemLocationBox.ajc$tjp_8, this, this));
        return this.items;
    }

    public int getLengthSize() {
        RequiresParseDetailAspect.aspectOf().before(b.a(ItemLocationBox.ajc$tjp_2, this, this));
        return this.lengthSize;
    }

    public int getOffsetSize() {
        RequiresParseDetailAspect.aspectOf().before(b.a(ItemLocationBox.ajc$tjp_0, this, this));
        return this.offsetSize;
    }

    public void setBaseOffsetSize(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(ItemLocationBox.ajc$tjp_5, this, this, a.a(arg3)));
        this.baseOffsetSize = arg3;
    }

    public void setIndexSize(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(ItemLocationBox.ajc$tjp_7, this, this, a.a(arg3)));
        this.indexSize = arg3;
    }

    public void setItems(List arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(ItemLocationBox.ajc$tjp_9, this, this, arg3));
        this.items = arg3;
    }

    public void setLengthSize(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(ItemLocationBox.ajc$tjp_3, this, this, a.a(arg3)));
        this.lengthSize = arg3;
    }

    public void setOffsetSize(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(ItemLocationBox.ajc$tjp_1, this, this, a.a(arg3)));
        this.offsetSize = arg3;
    }
}

