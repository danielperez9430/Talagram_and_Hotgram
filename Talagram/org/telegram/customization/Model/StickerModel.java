package org.telegram.customization.Model;

public class StickerModel {
    long id;
    String name;
    String title;

    public StickerModel() {
        super();
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getTitle() {
        return this.title;
    }

    public void setId(long arg1) {
        this.id = arg1;
    }

    public void setName(String arg1) {
        this.name = arg1;
    }

    public void setTitle(String arg1) {
        this.title = arg1;
    }
}

