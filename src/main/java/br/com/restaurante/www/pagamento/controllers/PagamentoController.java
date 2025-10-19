package br.com.restaurante.www.pagamento.controllers;

import br.com.restaurante.www.pagamento.config.PagamentoException;
import br.com.restaurante.www.pagamento.entities.Pagamento;
import br.com.restaurante.www.pagamento.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pagamentos")
public class PagamentoController {
    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    public List<Pagamento> listarPagamentos() {
        return pagamentoService.listarPagamentos();
    }

    @PostMapping
    public void salvarPagamento(@RequestBody Pagamento pagamento) {
        try {
            System.out.println(pagamento);
            pagamentoService.registrarPagamento(pagamento);
        } catch (PagamentoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public void atualizarPagamentoCompleto(@PathVariable Long id, @RequestBody Pagamento pagamento) {
        try {
            pagamentoService.atualizarPagamento(id, pagamento);
        } catch (PagamentoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void removerPagamento(@PathVariable Long id) {
        try {
            pagamentoService.removerPagamento(id);
        } catch (PagamentoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PatchMapping("/cancelamento/{id}")
    public void cancelarPagamento(@PathVariable Long id) {
        try {
            pagamentoService.cancelarPagamento(id);
        } catch (PagamentoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
