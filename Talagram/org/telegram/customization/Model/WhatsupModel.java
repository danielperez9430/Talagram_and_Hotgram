package org.telegram.customization.Model;

import java.util.ArrayList;

public class WhatsupModel {
    ArrayList notifs;
    String status;

    public WhatsupModel() {
        super();
    }

    public ArrayList getNotifs() {
        return this.notifs;
    }

    public String getStatus() {
        return this.status;
    }

    public void setNotifs(ArrayList arg1) {
        this.notifs = arg1;
    }

    public void setStatus(String arg1) {
        this.status = arg1;
    }
}

