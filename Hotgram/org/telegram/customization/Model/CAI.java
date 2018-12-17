package org.telegram.customization.Model;

public class CAI {
    boolean enable;
    String msg;
    String pkg;

    public CAI() {
        super();
        this.enable = false;
        this.pkg = "";
        this.msg = "";
    }

    public String getMsg() {
        return this.msg;
    }

    public String getPkg() {
        return this.pkg;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public void setEnable(boolean arg1) {
        this.enable = arg1;
    }

    public void setMsg(String arg1) {
        this.msg = arg1;
    }

    public void setPkg(String arg1) {
        this.pkg = arg1;
    }
}

