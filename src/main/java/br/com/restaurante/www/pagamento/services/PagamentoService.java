package br.com.restaurante.www.pagamento.services;

import br.com.restaurante.www.pagamento.config.PagamentoException;
import br.com.restaurante.www.pagamento.entities.Pagamento;
import br.com.restaurante.www.pagamento.entities.StatusPagamento;
import br.com.restaurante.www.pagamento.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository repository;

    public void registrarPagamento(Pagamento pagamento) throws PagamentoException{
        if (pagamento.getValor() <= 0) {
            throw new PagamentoException("Valor inválido para pagamento!");
        }

        if (pagamento.getFormaDePagamento() == null) {
            throw new PagamentoException("Forma de pagamento inválida");
        }

        switch (pagamento.getFormaDePagamento().getFormaPagamento()) {
            case "DINHEIRO":
                if (pagamento.getTroco() < 0) {
                    throw new PagamentoException("Troco não pode ser negativo!");
                }
                break;
            case "CREDITO":
                if (pagamento.getParcelas() <= 0) {
                    throw new PagamentoException("Número de parcelas informado deve ser maior que zero!");
                }
                break;

            case "DEBITO":
            case "PIX":
                if (pagamento.getTroco() > 0) {
                    throw new PagamentoException("Troco não é permitido para esta forma de pagamento!");
                }
                if (pagamento.getParcelas() > 0) {
                    throw new PagamentoException("Parcelas não são permitidas para esta forma de pagamento!");
                }
                break;
        }

        // Define status como APROVADO por padrão
        pagamento.setStatus(StatusPagamento.encontrarStatusDePagamento("Aprovado"));

        repository.save(pagamento);
    }

    public List<Pagamento> listarPagamentos() {
        return repository.findAll();
    }

    public List<Pagamento> buscarPagamentoPorTitular(String nome) {
        return repository.findByTitular(nome);
    }

    public void atualizarPagamento(Long id) {
        Optional<Pagamento> pagamentoOptional = repository.findById(id);

        if (pagamentoOptional.isPresent()) {
            repository.save(pagamentoOptional.get());
        }

        System.out.println("Pagamento não encontrado!");
    }

    public void cancelarPagamento(Long id) throws PagamentoException{
        Optional<Pagamento> pagamentoOptional = repository.findById(id);

        if (pagamentoOptional.isEmpty()) {
            throw new PagamentoException("Pagamento não encontrado!");
        }

        Pagamento pagamentoEncontrado = pagamentoOptional.get();
        String formaPagamentoEncontrado = pagamentoEncontrado.getFormaDePagamento().getFormaPagamento();
        String statusPagamentoEncontrado = pagamentoEncontrado.getStatus().getStatusPagamento();

        if (!formaPagamentoEncontrado.equalsIgnoreCase("CREDITO") && !formaPagamentoEncontrado.equalsIgnoreCase("DEBITO")) {
            throw new PagamentoException("Somente pagamentos com CREDITO ou DEBITO podem ser cancelados!");
        }

        if (statusPagamentoEncontrado.equalsIgnoreCase("CANCELADO")) {
            throw new PagamentoException("Este pagamento já foi cancelado!");
        }

        pagamentoEncontrado.setStatus(StatusPagamento.encontrarStatusDePagamento("CANCELADO"));
        atualizarPagamento(pagamentoEncontrado.getId());
    }
}
