package br.com.rayan.backend.picpaysimplificado.services;

import br.com.rayan.backend.picpaysimplificado.dtos.TransactionDTO;
import br.com.rayan.backend.picpaysimplificado.domain.transaction.Transaction;
import br.com.rayan.backend.picpaysimplificado.domain.user.User;
import br.com.rayan.backend.picpaysimplificado.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    private final UserService userService;

    private final TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    public TransactionService(UserService userService, TransactionRepository transactionRepository) {
        this.userService = userService;
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        User sender = userService.findUserById(transaction.senderId());
        User receiver = userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

        boolean isAuthorized = authorizeTransaction(sender, transaction.value());

        if (!isAuthorized) throw new Exception("Transação não autorizada.");

        Transaction newTransaction = new Transaction(null, transaction.value(), sender, receiver, LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.transactionRepository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        notificationService.sendNotification(sender, "Transação realizada com sucesso.");

        notificationService.sendNotification(receiver, "Transação recebida com sucesso.");

        return newTransaction;
    }

    public boolean authorizeTransaction(User sender, BigDecimal amount) {
        String path = "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc";
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity(path, Map.class);

        boolean isStatusOk = authorizationResponse.getStatusCode() == HttpStatus.OK;
        boolean isMessageValid = authorizationResponse.getBody().get("message").equals("Autorizado");

        return isStatusOk && isMessageValid;

    }
}
