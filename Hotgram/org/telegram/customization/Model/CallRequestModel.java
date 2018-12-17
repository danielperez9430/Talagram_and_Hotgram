package org.telegram.customization.Model;

public class CallRequestModel {
    String callId;
    String calleePresence;
    String calleeUser;
    String callerPassword;
    String callerUser;
    String server;

    public CallRequestModel() {
        super();
    }

    public String getCallId() {
        return this.callId;
    }

    public String getCalleePresence() {
        return this.calleePresence;
    }

    public String getCalleeUser() {
        return this.calleeUser;
    }

    public String getCallerPassword() {
        return this.callerPassword;
    }

    public String getCallerUser() {
        return this.callerUser;
    }

    public String getDomain() {
        try {
            return this.server.split(":")[0];
        }
        catch(Exception ) {
            return this.server;
        }
    }

    public int getPort() {
        try {
            return Integer.parseInt(this.server.split(":")[1]);
        }
        catch(Exception ) {
            return 5060;
        }
    }

    public String getServer() {
        return this.server;
    }

    public void setCallId(String arg1) {
        this.callId = arg1;
    }

    public void setCalleePresence(String arg1) {
        this.calleePresence = arg1;
    }

    public void setCalleeUser(String arg1) {
        this.calleeUser = arg1;
    }

    public void setCallerPassword(String arg1) {
        this.callerPassword = arg1;
    }

    public void setCallerUser(String arg1) {
        this.callerUser = arg1;
    }

    public void setServer(String arg1) {
        this.server = arg1;
    }
}

