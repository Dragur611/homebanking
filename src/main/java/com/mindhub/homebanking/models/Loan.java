package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String name;
    private double maxAmount;
    @ElementCollection
    @Column(name = "payment")
    private Set<Integer> payment = new HashSet<>();
    @OneToMany(mappedBy="loan", fetch=FetchType.EAGER)
    private Set<ClientLoan> loanSet;

    public Loan() {
    }

    public Loan(String name, Double maxAmount, Set<Integer> payments) {
        this.name = name;
        this.maxAmount = maxAmount;
        this.payment = payments;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Set<Integer> getPayment() {
        return payment;
    }

    public void setPayment(Set<Integer> payment) {
        this.payment = payment;
    }

    public Set<ClientLoan> getLoanSet() {
        return loanSet;
    }

    public void setLoanSet(Set<ClientLoan> loanSet) {
        this.loanSet = loanSet;
    }
}
