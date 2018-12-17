package org.telegram.customization.Model;

public class HotgramTheme {
    String name;
    String previewUrl;
    boolean selected;
    String serial;

    public HotgramTheme() {
        super();
    }

    public String getName() {
        return this.name;
    }

    public String getPreviewUrl() {
        return this.previewUrl;
    }

    public String getSerial() {
        return this.serial;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setName(String arg1) {
        this.name = arg1;
    }

    public void setPreviewUrl(String arg1) {
        this.previewUrl = arg1;
    }

    public void setSelected(boolean arg1) {
        this.selected = arg1;
    }

    public void setSerial(String arg1) {
        this.serial = arg1;
    }
}

