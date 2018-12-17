package org.telegram.customization.Model;

public class CUrl {
    boolean enable;
    long prd;
    String url;

    public CUrl() {
        super();
    }

    public long getPrd() {
        return this.prd;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public void setEnable(boolean arg1) {
        this.enable = arg1;
    }

    public void setPrd(long arg1) {
        this.prd = arg1;
    }

    public void setUrl(String arg1) {
        this.url = arg1;
    }
}

