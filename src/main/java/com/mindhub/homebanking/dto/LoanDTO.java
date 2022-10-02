package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Loan;

import java.util.Set;

public class LoanDTO {
    private long id;
    private String name;
    private double maxAmount;
    private Set<Integer> payment;

    public LoanDTO() {
    }

    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.name = loan.getName();
        this.maxAmount = loan.getMaxAmount();
        this.payment = loan.getPayment();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public Set<Integer> getPayment() {
        return payment;
    }
}
