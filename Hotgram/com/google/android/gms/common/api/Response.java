package com.google.android.gms.common.api;

public class Response {
    private Result zzdm;

    public Response() {
        super();
    }

    protected Response(Result arg1) {
        super();
        this.zzdm = arg1;
    }

    protected Result getResult() {
        return this.zzdm;
    }

    public void setResult(Result arg1) {
        this.zzdm = arg1;
    }
}

