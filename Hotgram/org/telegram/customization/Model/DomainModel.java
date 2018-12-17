package org.telegram.customization.Model;

public class DomainModel {
    int status;
    String url;

    public DomainModel(int arg2, String arg3) {
        super();
        this.status = 0;
        this.status = arg2;
        this.url = arg3;
    }

    public int getStatus() {
        return this.status;
    }

    public String getUrl() {
        return this.url;
    }

    public void setStatus(int arg1) {
        this.status = arg1;
    }

    public void setUrl(String arg1) {
        this.url = arg1;
    }
}

