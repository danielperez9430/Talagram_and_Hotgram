package org.telegram.customization.dynamicadapter.data;

public class SlsFilter {
    public static final int TYPE_FILTER = 1;
    public static final int TYPE_SORT = 2;
    boolean clickable;
    boolean deletable;
    String description;
    int id;
    String image;
    String name;
    boolean selected;
    private int type;
    int value;

    public SlsFilter() {
        super();
        this.clickable = true;
    }

    public String getDescription() {
        return this.description;
    }

    public int getId() {
        return this.value;
    }

    public String getImage() {
        return this.image;
    }

    public String getName() {
        return this.name;
    }

    public int getType() {
        return this.type;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isClickable() {
        return this.clickable;
    }

    public boolean isDeletable() {
        return this.deletable;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setClickable(boolean arg1) {
        this.clickable = arg1;
    }

    public void setDeletable(boolean arg1) {
        this.deletable = arg1;
    }

    public void setDescription(String arg1) {
        this.description = arg1;
    }

    public void setId(int arg1) {
        this.id = arg1;
    }

    public void setImage(String arg1) {
        this.image = arg1;
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

    public void setValue(int arg1) {
        this.value = arg1;
    }
}

