package com.sonic.demo.entity;

public class PaymentData {
    String orderId;
    String paymentId;
    String signature;

    public PaymentData() {
        super();
    }

    public PaymentData(String orderId, String paymentId, String signature) {
        super();
        this.orderId = orderId;
        this.paymentId = paymentId;
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "PaymentData [orderId=" + orderId + ", paymentId=" + paymentId + ", signature=" + signature + "]";
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
