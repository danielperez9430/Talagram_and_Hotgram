package org.telegram.customization.dynamicadapter.data;

import java.util.ArrayList;

public class SLSSaveTag {
    ArrayList tags;
    long userId;

    public SLSSaveTag() {
        super();
    }

    public ArrayList getTags() {
        return this.tags;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setTags(ArrayList arg1) {
        this.tags = arg1;
    }

    public void setUserId(long arg1) {
        this.userId = arg1;
    }
}

