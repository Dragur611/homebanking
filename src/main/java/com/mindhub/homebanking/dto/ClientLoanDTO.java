package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.ClientLoan;

public class ClientLoanDTO {
    private long idLoan;
    private long idClientLoan;
    private int payment;
    private double amount;
    private String loanName;

    public ClientLoanDTO() {
    }

    public ClientLoanDTO(ClientLoan clientLoan) {
        this.idLoan = clientLoan.getLoan().getId();
        this.idClientLoan = clientLoan.getId();
        this.payment = clientLoan.getPayments();
        this.amount = clientLoan.getAmount();
        this.loanName = clientLoan.getLoan().getName();
    }

    public long getIdLoan() {
        return idLoan;
    }

    public long getIdClientLoan() {
        return idClientLoan;
    }

    public int getPayment() {
        return payment;
    }

    public double getAmount() {
        return amount;
    }

    public String getLoanName() {
        return loanName;
    }
}
