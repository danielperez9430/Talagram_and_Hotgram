package org.telegram.customization.Model;

import org.telegram.messenger.LocaleController;

public class ContactChangeLog {
    public static final int CHANGE_TYPE_ALL = 0;
    public static final int CHANGE_TYPE_AVATAR = 1;
    public static final int CHANGE_TYPE_NAME = 2;
    public static final int CHANGE_TYPE_PHONE_NUMBER = 3;
    public static final int CHANGE_TYPE_USER_NAME = 4;
    long chatId;
    long date;
    int id;
    String previousName;
    int type;

    public ContactChangeLog(int arg1, long arg2, int arg4, String arg5, long arg6) {
        super();
        this.id = arg1;
        this.chatId = arg2;
        this.type = arg4;
        this.previousName = arg5;
        this.date = arg6;
    }

    public ContactChangeLog(long arg2, int arg4, String arg5, long arg6) {
        super();
        this.id = this.id;
        this.chatId = arg2;
        this.type = arg4;
        this.previousName = arg5;
        this.date = arg6;
    }

    public long getChatId() {
        return this.chatId;
    }

    public long getDate() {
        return this.date;
    }

    public int getId() {
        return this.id;
    }

    public static String getLogStringByType(int arg1) {
        switch(arg1) {
            case 1: {
                goto label_10;
            }
            case 2: {
                goto label_7;
            }
            case 3: {
                goto label_3;
            }
        }

        return null;
    label_3:
        String v1 = "ChangeProfileUsername";
        int v0 = 2131624278;
        goto label_5;
    label_7:
        v1 = "ChangeProfileName";
        v0 = 2131624276;
        goto label_5;
    label_10:
        v1 = "ChangeProfilePic";
        v0 = 2131624277;
    label_5:
        return LocaleController.getString(v1, v0);
    }

    public String getPreviousName() {
        return this.previousName;
    }

    public int getType() {
        return this.type;
    }

    public void setChatId(long arg1) {
        this.chatId = arg1;
    }

    public void setDate(long arg1) {
        this.date = arg1;
    }

    public void setId(int arg1) {
        this.id = arg1;
    }

    public void setPreviousName(String arg1) {
        this.previousName = arg1;
    }

    public void setType(int arg1) {
        this.type = arg1;
    }
}

