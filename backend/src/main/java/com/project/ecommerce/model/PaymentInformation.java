package com.project.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class PaymentInformation {

    @Column(name = "cardholder_name")
    private String cardholderName;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "expiration_date")
    private String expirationDate;
    @Column(name = "cvv")
    private String cvv;
}
