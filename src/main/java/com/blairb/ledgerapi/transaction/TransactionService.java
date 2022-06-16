package com.blairb.ledgerapi.transaction;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Iterable<Transaction> list() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    public Transaction create(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> update(Transaction transaction) {
        Optional<Transaction> foundTransaction = transactionRepository.findById(transaction.getId());

        if (foundTransaction.isPresent()) {
            Transaction updatedTransaction = foundTransaction.get();
            updatedTransaction.setDescription(transaction.getDescription());
            updatedTransaction.setAccount(transaction.getAccount());
            updatedTransaction.setCredit(transaction.getCredit());
            updatedTransaction.setAmount(transaction.getAmount());
            updatedTransaction.setDate(transaction.getDate());

            transactionRepository.save(updatedTransaction);
            return Optional.of(updatedTransaction);
        } else {
            return Optional.empty();
        }
    }

    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }

}