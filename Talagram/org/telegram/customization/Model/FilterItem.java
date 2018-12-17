package org.telegram.customization.Model;

public class FilterItem {
    public static final int FILTER_TYPE_CATEGORY = 1;
    public static final int FILTER_TYPE_MEDIA_TYPE = 2;
    public static final int FILTER_TYPE_SORT = 3;
    long id;
    String name;
    boolean selected;
    int type;

    public FilterItem() {
        super();
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getType() {
        return this.type;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setId(long arg1) {
        this.id = arg1;
    }

    public void setName(String arg1) {
        this.name = arg1;
    }

    public void setSelected(boolean arg1) {
        this.selected = arg1;
    }

    public void setType(int arg1) {
        this.type = arg1;
    }
}

