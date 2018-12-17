package org.telegram.customization.voip.linphoneSip.linphone;

public class PhoneBean {
    private String displayName;
    private String host;
    private String password;
    private String userName;

    public PhoneBean() {
        super();
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getHost() {
        return this.host;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setDisplayName(String arg1) {
        this.displayName = arg1;
    }

    public void setHost(String arg1) {
        this.host = arg1;
    }

    public void setPassword(String arg1) {
        this.password = arg1;
    }

    public void setUserName(String arg1) {
        this.userName = arg1;
    }
}

