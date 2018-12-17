package org.telegram.customization.Model;

public class RequestLog {
    int duration;
    String host;
    int logType;
    String serviceName;
    int statusCode;

    public RequestLog(int arg1, String arg2, int arg3, String arg4, int arg5) {
        super();
        this.logType = arg1;
        this.host = arg2;
        this.statusCode = arg3;
        this.serviceName = arg4;
        this.duration = arg5;
    }

    public int getDuration() {
        return this.duration;
    }

    public String getHost() {
        return this.host;
    }

    public int getLogType() {
        return this.logType;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setDuration(int arg1) {
        this.duration = arg1;
    }

    public void setHost(String arg1) {
        this.host = arg1;
    }

    public void setLogType(int arg1) {
        this.logType = arg1;
    }

    public void setServiceName(String arg1) {
        this.serviceName = arg1;
    }

    public void setStatusCode(int arg1) {
        this.statusCode = arg1;
    }
}

