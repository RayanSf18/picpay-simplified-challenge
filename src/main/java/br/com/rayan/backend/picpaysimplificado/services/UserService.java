package br.com.rayan.backend.picpaysimplificado.services;

import br.com.rayan.backend.picpaysimplificado.domain.user.User;
import br.com.rayan.backend.picpaysimplificado.domain.user.UserType;
import br.com.rayan.backend.picpaysimplificado.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getType() == UserType.MERCHANT) throw new Exception("Usuário do tipo Lojista não está autorizado a realizar transação.");
        if (sender.getBalance().compareTo(amount) < 0) throw new Exception("Saldo Insuficiente");
    }

    public User findUserById(Long userId) throws Exception {
        return this.userRepository.findById(userId).orElseThrow(() -> new Exception("Usuário não encontrado."));
    }

    public void saveUser(User newUser) {
        this.userRepository.save(newUser);
    }
}
