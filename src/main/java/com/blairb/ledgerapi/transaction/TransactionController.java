package com.blairb.ledgerapi.transaction;

import java.util.Map;
import java.util.HashMap;

import com.blairb.ledgerapi.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
@RequestMapping("transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Iterable<Transaction>> list() {
        Iterable<Transaction> transactions = transactionService.list();
        return createHashPlural(transactions);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Transaction> read(@PathVariable Long id) {
        Transaction transaction = transactionService
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No resource with that ID"));
        return createHashSingular(transaction);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Transaction> create(@Validated @RequestBody Transaction transaction) {
        Transaction createdResource = transactionService.create(transaction);
        return createHashSingular(createdResource);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Transaction> update(@RequestBody Transaction transaction, @PathVariable Long id) {
        Transaction updatedResource = transactionService
                .update(transaction)
                .orElseThrow(() -> new ResourceNotFoundException("No resource with that ID"));

        return createHashSingular(updatedResource);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        transactionService.deleteById(id);
    }

    private Map<String, Transaction> createHashSingular(Transaction transaction) {
        Map<String, Transaction> response = new HashMap<String, Transaction>();
        response.put("transaction", transaction);

        return response;
    }

    private Map<String, Iterable<Transaction>> createHashPlural(Iterable<Transaction> transactions) {
        Map<String, Iterable<Transaction>> response = new HashMap<String, Iterable<Transaction>>();
        response.put("transactions", transactions);

        return response;
    }
}