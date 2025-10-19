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

    public Pagamento validar(Pagamento pagamento) throws PagamentoException {
        if (pagamento.getValor() <= 0) {
            throw new PagamentoException("Valor inválido para pagamento!");
        }

        System.out.println(pagamento.getFormaPagamento());

        if (pagamento.getFormaPagamento() == null || pagamento.getFormaPagamento().isEmpty()) {
            throw new PagamentoException("Forma de pagamento inválida ou não informada");
        }

        String forma = pagamento.getFormaPagamento().toUpperCase();

        switch (forma) {
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
            default:
                throw new PagamentoException("Forma de pagamento não suportada!");
        }

        return pagamento;
    }

    public void registrarPagamento(Pagamento pagamento) throws PagamentoException{
        Pagamento pagamentoValidado = validar(pagamento);

        // Define status como APROVADO por padrão
        pagamentoValidado.setStatus("APROVADO");

        repository.save(pagamentoValidado);
    }

    public List<Pagamento> listarPagamentos() {
        return repository.findAll();
    }

    public void atualizarPagamento(Long id, Pagamento pagamentoAtualizado) throws PagamentoException{
        Optional<Pagamento> pagamentoOptional = repository.findById(id);

        if (pagamentoOptional.isEmpty()) {
            throw new PagamentoException("Pagamento não encontrado!");
        }
        pagamentoAtualizado.setId(id);
        // Reutilização do método para revalidar regras de negócio
        registrarPagamento(pagamentoAtualizado);
    }

    public void cancelarPagamento(Long id) throws PagamentoException{
        Optional<Pagamento> pagamentoOptional = repository.findById(id);

        if (pagamentoOptional.isEmpty()) {
            throw new PagamentoException("Pagamento não encontrado!");
        }

        Pagamento pagamentoEncontrado = pagamentoOptional.get();
        String formaPagamentoEncontrado = pagamentoEncontrado.getFormaPagamento();
        String statusPagamentoEncontrado = pagamentoEncontrado.getStatus();

        if (!formaPagamentoEncontrado.equalsIgnoreCase("CREDITO") && !formaPagamentoEncontrado.equalsIgnoreCase("DEBITO")) {
            throw new PagamentoException("Somente pagamentos com CREDITO ou DEBITO podem ser cancelados!");
        }

        if (statusPagamentoEncontrado.equalsIgnoreCase("CANCELADO")) {
            throw new PagamentoException("Este pagamento já foi cancelado!");
        }

        validar(pagamentoEncontrado);

        pagamentoEncontrado.setStatus("CANCELADO");
        System.out.println(pagamentoEncontrado);

        // Reutilização do método para revalidar regras de negócio
        repository.save(pagamentoEncontrado);
    }

    public void removerPagamento(Long id) throws PagamentoException{
        Optional<Pagamento> pagamentoOptional = repository.findById(id);

        if (pagamentoOptional.isEmpty()) {
            throw new PagamentoException("Pagamento não encontrado!");
        }

        Pagamento pagamentoEncontrado = pagamentoOptional.get();
        repository.delete(pagamentoEncontrado);
    }
}
