package org.telegram.customization.Model;

import java.util.HashMap;

public class HttpJob {
    String body;
    HashMap headers;
    String id;
    String method;
    int number;
    HashMap parameters;
    String url;

    public HttpJob() {
        super();
    }

    public String getBody() {
        return this.body;
    }

    public HashMap getHeaders() {
        return this.headers;
    }

    public String getId() {
        return this.id;
    }

    public String getMethod() {
        return this.method;
    }

    public int getNumber() {
        return this.number;
    }

    public HashMap getParameters() {
        return this.parameters;
    }

    public String getUrl() {
        return this.url;
    }

    public void setBody(String arg1) {
        this.body = arg1;
    }

    public void setHeaders(HashMap arg1) {
        this.headers = arg1;
    }

    public void setId(String arg1) {
        this.id = arg1;
    }

    public void setMethod(String arg1) {
        this.method = arg1;
    }

    public void setNumber(int arg1) {
        this.number = arg1;
    }

    public void setParameters(HashMap arg1) {
        this.parameters = arg1;
    }

    public void setUrl(String arg1) {
        this.url = arg1;
    }
}

