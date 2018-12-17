package org.telegram.customization.Model.Payment;

public class PaymentLink {
    String link;
    String paymentId;

    public PaymentLink() {
        super();
    }

    public String getLink() {
        return this.link;
    }

    public String getPaymentId() {
        return this.paymentId;
    }

    public void setLink(String arg1) {
        this.link = arg1;
    }

    public void setPaymentId(String arg1) {
        this.paymentId = arg1;
    }
}

