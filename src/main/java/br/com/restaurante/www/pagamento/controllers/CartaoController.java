package br.com.restaurante.www.pagamento.controllers;

import br.com.restaurante.www.pagamento.config.CartaoException;
import br.com.restaurante.www.pagamento.entities.Cartao;
import br.com.restaurante.www.pagamento.services.CartaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cartoes")
public class CartaoController {
    @Autowired
    private CartaoService cartaoService;

    @GetMapping
    public List<Cartao> listarCartoes() {
        return cartaoService.listarCartoes();
    }

    @PostMapping
    public void registrarCartao(@RequestBody @Valid Cartao cartao) {
        try {
            cartaoService.registrarCartao(cartao);
        } catch (CartaoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public void atualizarCartao(@PathVariable Long id, @RequestBody Cartao cartao) {
        try {
            cartaoService.atualizarCartao(id, cartao);
        } catch (CartaoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void removerCartao(@PathVariable Long id) {
       try {
            cartaoService.removerCartao(id);
       } catch (CartaoException e) {
           throw new RuntimeException(e.getMessage());
       }
    }
}
