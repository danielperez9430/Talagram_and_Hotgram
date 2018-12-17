package org.telegram.customization.Model;

public class SipPeerInfo {
    String callid;
    String password;
    String server;
    String user;

    public SipPeerInfo() {
        super();
    }

    public String getCallid() {
        return this.callid;
    }

    public String getDomain() {
        try {
            return this.server.split(":")[0];
        }
        catch(Exception ) {
            return this.server;
        }
    }

    public String getPassword() {
        return this.password;
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

    public String getUser() {
        return this.user;
    }

    public void setCallid(String arg1) {
        this.callid = arg1;
    }

    public void setPassword(String arg1) {
        this.password = arg1;
    }

    public void setServer(String arg1) {
        this.server = arg1;
    }

    public void setUser(String arg1) {
        this.user = arg1;
    }
}

