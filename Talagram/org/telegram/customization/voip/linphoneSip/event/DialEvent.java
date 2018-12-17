package org.telegram.customization.voip.linphoneSip.event;

public class DialEvent {
    private String mNumber;

    public DialEvent(String arg1) {
        super();
        this.mNumber = arg1;
    }

    public String getNumber() {
        return this.mNumber;
    }
}

