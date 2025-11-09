package org.dd.bre.model;

public enum PaymentMethod {
    CREDIT_CARD, DEBIT_CARD, PAYPAL, STRIPE, CASH_ON_DELIVERY, NOT_EXIST;

    public static PaymentMethod fromString(String method){
        if (method.replaceAll("_"," ").equalsIgnoreCase("CASH ON DELIVERY"))
            return CASH_ON_DELIVERY;
        return NOT_EXIST;
    }
}