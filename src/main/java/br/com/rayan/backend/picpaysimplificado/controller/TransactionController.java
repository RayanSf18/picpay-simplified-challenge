package br.com.rayan.backend.picpaysimplificado.controller;

import br.com.rayan.backend.picpaysimplificado.domain.transaction.Transaction;
import br.com.rayan.backend.picpaysimplificado.dtos.TransactionDTO;
import br.com.rayan.backend.picpaysimplificado.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transaction) throws Exception {
        Transaction newTransaction = transactionService.createTransaction(transaction);
        return ResponseEntity.ok().body(newTransaction);
    }
}
