package org.telegram.customization.Model;

public class DialogTab {
    int dialogType;
    boolean hidden;
    String showName;
    int tabDrawable;
    int tabLayoutResource;
    String tag;

    public DialogTab(int arg1, int arg2, String arg3, boolean arg4, int arg5, String arg6) {
        super();
        this.tabLayoutResource = arg1;
        this.tabDrawable = arg2;
        this.tag = arg3;
        this.hidden = arg4;
        this.dialogType = arg5;
        this.showName = arg6;
    }

    public int getDialogType() {
        return this.dialogType;
    }

    public String getShowName() {
        return this.showName;
    }

    public int getTabDrawable() {
        return this.tabDrawable;
    }

    public int getTabLayoutResource() {
        return this.tabLayoutResource;
    }

    public String getTag() {
        return this.tag;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public void setDialogType(int arg1) {
        this.dialogType = arg1;
    }

    public void setHidden(boolean arg1) {
        this.hidden = arg1;
    }

    public void setShowName(String arg1) {
        this.showName = arg1;
    }

    public void setTabDrawable(int arg1) {
        this.tabDrawable = arg1;
    }

    public void setTabLayoutResource(int arg1) {
        this.tabLayoutResource = arg1;
    }

    public void setTag(String arg1) {
        this.tag = arg1;
    }
}

