package com.blairb.ledgerapi.transaction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

import lombok.Data;

@Entity
@Data
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "credit")
    private Boolean credit;

    @Column(name = "date")
    private String date;

    @Column(name = "account")
    private String account;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private Float amount;
}