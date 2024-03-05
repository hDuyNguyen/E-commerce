package com.project.ecommerce.model;

public class PaymentDetails {

    private String paymentMethod;
    private String paymentStatus;
    private String paymentId;
    private String payPaymentLinkId;
    private String payPaymentLinkReferenceId;
    private String payPaymentLinkStatus;
    private String payPaymentId;

    PaymentDetails() {}

    public PaymentDetails(String paymentMethod, String paymentStatus, String paymentId, String payPaymentLinkId,
                          String payPaymentLinkReferenceId, String payPaymentLinkStatus, String payPaymentId) {
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paymentId = paymentId;
        this.payPaymentLinkId = payPaymentLinkId;
        this.payPaymentLinkReferenceId = payPaymentLinkReferenceId;
        this.payPaymentLinkStatus = payPaymentLinkStatus;
        this.payPaymentId = payPaymentId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPayPaymentLinkId() {
        return payPaymentLinkId;
    }

    public void setPayPaymentLinkId(String payPaymentLinkId) {
        this.payPaymentLinkId = payPaymentLinkId;
    }

    public String getPayPaymentLinkReferenceId() {
        return payPaymentLinkReferenceId;
    }

    public void setPayPaymentLinkReferenceId(String payPaymentLinkReferenceId) {
        this.payPaymentLinkReferenceId = payPaymentLinkReferenceId;
    }

    public String getPayPaymentLinkStatus() {
        return payPaymentLinkStatus;
    }

    public void setPayPaymentLinkStatus(String payPaymentLinkStatus) {
        this.payPaymentLinkStatus = payPaymentLinkStatus;
    }

    public String getPayPaymentId() {
        return payPaymentId;
    }

    public void setPayPaymentId(String payPaymentId) {
        this.payPaymentId = payPaymentId;
    }
}
