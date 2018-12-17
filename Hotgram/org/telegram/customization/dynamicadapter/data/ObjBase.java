package org.telegram.customization.dynamicadapter.data;

public class ObjBase {
    public static final int ITEM_TYPE_DIFFERNT_TILE_SIZE = 7;
    public static final int ITEM_TYPE_IMAGE_SAME_TILE_SIZE = 6;
    public static final int ITEM_TYPE_MESSAGE = 5;
    public static final int ITEM_TYPE_SEARCH = 2;
    public static final int ITEM_TYPE_STATISTICS = 1;
    public static final int ITEM_TYPE_TAG_COLLECTION = 4;
    public static final int ITEM_TYPE_TITLE = 8;
    public static final int ITEM_TYPE_VIDEO = 3;
    protected String color;
    private long itemId;
    String localHelperField;
    String meta;
    int position;
    int row;
    protected String title;
    protected int type;

    public ObjBase() {
        super();
    }

    public String getColor() {
        return this.color;
    }

    public long getItemId() {
        return this.itemId;
    }

    public String getLocalHelperField() {
        return this.localHelperField;
    }

    public String getMeta() {
        return this.meta;
    }

    public int getPosition() {
        return this.position;
    }

    public int getRow() {
        return this.row;
    }

    public String getTitle() {
        return this.title;
    }

    public int getType() {
        return this.type;
    }

    public void setColor(String arg1) {
        this.color = arg1;
    }

    public void setItemId(long arg1) {
        this.itemId = arg1;
    }

    public void setLocalHelperField(String arg1) {
        this.localHelperField = arg1;
    }

    public void setMeta(String arg1) {
        this.meta = arg1;
    }

    public void setPosition(int arg1) {
        this.position = arg1;
    }

    public void setRow(int arg1) {
        this.row = arg1;
    }

    public void setTitle(String arg1) {
        this.title = arg1;
    }

    public void setType(int arg1) {
        this.type = arg1;
    }
}

