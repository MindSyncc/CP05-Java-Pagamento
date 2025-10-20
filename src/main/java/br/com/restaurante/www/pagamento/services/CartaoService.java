package br.com.restaurante.www.pagamento.services;

import br.com.restaurante.www.pagamento.config.CartaoException;
import br.com.restaurante.www.pagamento.entities.Cartao;
import br.com.restaurante.www.pagamento.repositories.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CartaoService {
    @Autowired
    private CartaoRepository repository;

    public Cartao validar(Cartao cartao) throws CartaoException {

        if (cartao.getNomeTitular().isEmpty()) {
            throw new CartaoException("É necessário informar o nome do titular");
        } else if (cartao.getNumeroCartao().length() < 14 || cartao.getNumeroCartao().length() > 16) {
            throw new CartaoException("É necessário informar o nome do titular");
        } else if (cartao.getValidade().isEmpty()) {
            throw new CartaoException("É necessário informar a validade do cartão");
        } else if (cartao.getCvv().length() != 3 && cartao.getCvv().length() != 4) {
            throw new CartaoException("O código de segurança informado não está de acordo com os padrões");
        } else if (cartao.getSenha().length() != 6) {
            throw new CartaoException("O cartão precisa conter uma senha de 6 dígitos");
        }

        return cartao;
    }

    public void registrarCartao(Cartao cartao) throws CartaoException{
        Cartao cartaoValidado = validar(cartao);
        repository.save(cartaoValidado);
    }

    public List<Cartao> listarCartoes() {
        return repository.findAll();
    }

    public void atualizarCartao(Long id, Cartao cartaoAtualizado) throws CartaoException {
        Optional<Cartao> cartaoOptional = repository.findById(id);

        if (cartaoOptional.isEmpty()) {
            throw new CartaoException("Não foi possível encontrar o cartão");
        }

        cartaoAtualizado.setId(id);
        registrarCartao(cartaoAtualizado);
    }

    public void removerCartao(Long id) throws CartaoException {
        Optional<Cartao> cartaoOptional = repository.findById(id);

        if (cartaoOptional.isEmpty()) {
            throw new CartaoException("Não foi possível encontrar o cartão");
        }

        Cartao cartaoEncontrado = cartaoOptional.get();
        repository.delete(cartaoEncontrado);
    }

    // Talvez possamos, em uma implementação futura, atrelar essa validação ao processo de registro do cartão
    public Map<String, Object> verificarValidade(Long id) throws CartaoException {
        Optional<Cartao> cartaoOptional = repository.findById(id);

        if (cartaoOptional.isEmpty()) {
            throw new CartaoException("Não foi possível encontrar o cartão");
        }

        Cartao cartaoEncontrado = cartaoOptional.get();
        String dataValidadeCartao = cartaoEncontrado.getValidade(); // 09-28

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yy");
        YearMonth validade = YearMonth.parse(dataValidadeCartao, formatter);
        YearMonth atual = YearMonth.now();

        if (atual.isAfter(validade)) {
            return Map.of(
                    "status", "expirado",
                    "mensagem", "O cartão está expirado. Tente utilizar outro cartão",
                    "cartaoId", id
            );
        }

        return Map.of(
                "status", "valido",
                "mensagem", "O cartão ainda está dentro da validade",
                "cartaoId", id
        );
    }
}
