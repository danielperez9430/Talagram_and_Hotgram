package org.telegram.customization.Model.Payment;

import com.google.a.a.c;

public class HostRequestData {
    Long amount;
    String date;
    String description;
    @c(a="hreq") String hostRequest;
    @c(a="hsign") String hostRequestSign;
    int orderId;
    @c(a="ver") String sdkProtocolVersionInfo;
    long tranId;

    public HostRequestData() {
        super();
    }

    public Long getAmount() {
        return this.amount;
    }

    public String getDate() {
        return this.date;
    }

    public String getDescription() {
        return this.description;
    }

    public String getHostRequest() {
        return this.hostRequest;
    }

    public String getHostRequestSign() {
        return this.hostRequestSign;
    }

    public int getOrderId() {
        return this.orderId;
    }

    public String getSdkProtocolVersionInfo() {
        return this.sdkProtocolVersionInfo;
    }

    public long getTranId() {
        return this.tranId;
    }

    public void setAmount(Long arg1) {
        this.amount = arg1;
    }

    public void setDate(String arg1) {
        this.date = arg1;
    }

    public void setDescription(String arg1) {
        this.description = arg1;
    }

    public void setHostRequest(String arg1) {
        this.hostRequest = arg1;
    }

    public void setHostRequestSign(String arg1) {
        this.hostRequestSign = arg1;
    }

    public void setOrderId(int arg1) {
        this.orderId = arg1;
    }

    public void setSdkProtocolVersionInfo(String arg1) {
        this.sdkProtocolVersionInfo = arg1;
    }

    public void setTranId(long arg1) {
        this.tranId = arg1;
    }
}

