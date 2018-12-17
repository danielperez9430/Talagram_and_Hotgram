package org.telegram.customization.dynamicadapter.data;

import java.util.ArrayList;

public class SlsFilterHelper {
    int code;
    ArrayList items;
    String message;

    public SlsFilterHelper() {
        super();
    }

    public int getCode() {
        return this.code;
    }

    public ArrayList getItems() {
        return this.items;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int arg1) {
        this.code = arg1;
    }

    public void setItems(ArrayList arg1) {
        this.items = arg1;
    }

    public void setMessage(String arg1) {
        this.message = arg1;
    }
}

