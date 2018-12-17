package org.telegram.customization.Model;

import java.util.HashMap;
import java.util.Map;

public class CheckUrlResponseModel {
    CheckUrlMeta meta;
    Map tag;
    Map url;

    public CheckUrlResponseModel() {
        super();
        this.url = new HashMap();
        this.tag = new HashMap();
    }

    public CheckUrlMeta getMeta() {
        return this.meta;
    }

    public Map getTag() {
        return this.tag;
    }

    public Map getUrl() {
        return this.url;
    }

    public void setMeta(CheckUrlMeta arg1) {
        this.meta = arg1;
    }

    public void setTag(Map arg1) {
        this.tag = arg1;
    }

    public void setUrl(Map arg1) {
        this.url = arg1;
    }
}

