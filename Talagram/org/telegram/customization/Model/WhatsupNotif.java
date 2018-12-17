package org.telegram.customization.Model;

public class WhatsupNotif {
    String fromId;
    String message;
    SipPeerInfo params;
    long sequence;
    long timestamp;
    String toId;
    long ttl;
    String type;

    public WhatsupNotif() {
        super();
    }

    public String getFromId() {
        return this.fromId;
    }

    public String getMessage() {
        return this.message;
    }

    public SipPeerInfo getParams() {
        return this.params;
    }

    public long getSequence() {
        return this.sequence;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getToId() {
        return this.toId;
    }

    public long getTtl() {
        return this.ttl;
    }

    public String getType() {
        return this.type;
    }

    public void setFromId(String arg1) {
        this.fromId = arg1;
    }

    public void setMessage(String arg1) {
        this.message = arg1;
    }

    public void setParams(SipPeerInfo arg1) {
        this.params = arg1;
    }

    public void setSequence(long arg1) {
        this.sequence = arg1;
    }

    public void setTimestamp(long arg1) {
        this.timestamp = arg1;
    }

    public void setToId(String arg1) {
        this.toId = arg1;
    }

    public void setTtl(long arg1) {
        this.ttl = arg1;
    }

    public void setType(String arg1) {
        this.type = arg1;
    }
}

