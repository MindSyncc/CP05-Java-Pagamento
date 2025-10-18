package br.com.restaurante.www.pagamento.services;

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

    public void registrarPagamento(Pagamento pagamento) {
        if (pagamento.getValor() <= 0) {
            System.out.println("Exceção");
        }

        if (pagamento.getFormaDePagamento() == null) {
            System.out.println("Exceção");
        }

        switch (pagamento.getFormaDePagamento().getFormaPagamento()) {
            case "DINHEIRO":
                if (pagamento.getTroco() < 0) {
                    System.out.println("Exceção");
                }
                break;
            case "CREDITO":
                if (pagamento.getParcelas() <= 0) {
                    System.out.println("Exceção");
                }
                break;

            case "DEBITO":
            case "PIX":
                if (pagamento.getTroco() > 0) {
                    System.out.println("Exceção");
                }
                if (pagamento.getParcelas() > 0) {
                    System.out.println("Exceção");
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

    public void cancelarPagamento(Long id) {
        Optional<Pagamento> pagamentoOptional = repository.findById(id);

        if (pagamentoOptional.isEmpty()) {
            System.out.println("Pagamento não encontrado!");
        }

        Pagamento pagamentoEncontrado = pagamentoOptional.get();
        String formaPagamentoEncontrado = pagamentoEncontrado.getFormaDePagamento().getFormaPagamento();
        String statusPagamentoEncontrado = pagamentoEncontrado.getStatus().getStatusPagamento();

        if (!formaPagamentoEncontrado.equalsIgnoreCase("CREDITO") && !formaPagamentoEncontrado.equalsIgnoreCase("DEBITO")) {
            System.out.println("Exceção");
        }

        if (statusPagamentoEncontrado.equalsIgnoreCase("CANCELADO")) {
            System.out.println("Exceção");
        }

        pagamentoEncontrado.setStatus(StatusPagamento.encontrarStatusDePagamento("CANCELADO"));
        atualizarPagamento(pagamentoEncontrado.getId());
    }
}
