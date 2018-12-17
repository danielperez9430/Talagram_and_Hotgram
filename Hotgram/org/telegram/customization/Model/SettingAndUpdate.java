package org.telegram.customization.Model;

import java.util.ArrayList;

public class SettingAndUpdate {
    ArrayList settings;
    Update update;

    public SettingAndUpdate() {
        super();
    }

    public ArrayList getSetting() {
        return this.settings;
    }

    public Update getUpdate() {
        return this.update;
    }

    public void setSetting(ArrayList arg1) {
        this.settings = arg1;
    }

    public void setUpdate(Update arg1) {
        this.update = arg1;
    }
}

