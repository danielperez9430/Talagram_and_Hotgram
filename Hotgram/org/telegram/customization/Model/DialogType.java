package org.telegram.customization.Model;

public class DialogType {
    long dialogId;
    int type;

    public DialogType() {
        super();
    }

    public long getDialogId() {
        return this.dialogId;
    }

    public int getType() {
        return this.type;
    }

    public void setDialogId(long arg1) {
        this.dialogId = arg1;
    }

    public void setType(int arg1) {
        this.type = arg1;
    }
}

