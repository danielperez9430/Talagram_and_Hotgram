package org.telegram.customization.Model;

public class FontModel {
    String link;
    String name;
    String showName;

    public FontModel(String arg1, String arg2, String arg3) {
        super();
        this.name = arg1;
        this.showName = arg2;
        this.link = arg3;
    }

    public String getLink() {
        return this.link;
    }

    public String getName() {
        return this.name;
    }

    public String getShowName() {
        return this.showName;
    }

    public void setLink(String arg1) {
        this.link = arg1;
    }

    public void setName(String arg1) {
        this.name = arg1;
    }

    public void setShowName(String arg1) {
        this.showName = arg1;
    }
}

