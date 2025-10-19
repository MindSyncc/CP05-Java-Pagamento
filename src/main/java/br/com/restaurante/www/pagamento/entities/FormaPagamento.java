package br.com.restaurante.www.pagamento.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Optional;

public enum FormaPagamento {
    CREDITO("CREDITO"),
    DEBITO("DEBITO"),
    PIX("PIX"),
    DINHEIRO("DINHEIRO");

    private String formaPagamento;

    FormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @JsonValue
    public String getFormaPagamento() {
        return formaPagamento;
    }

    @JsonCreator
    public static FormaPagamento encontrarFormaDePagamento(String formaDePagamento) {
        Optional<FormaPagamento> formaPagamentoOptional = Arrays.stream(FormaPagamento.values())
                .filter(f -> f.getFormaPagamento().equalsIgnoreCase(formaDePagamento))
                .findFirst();

        if (formaPagamentoOptional.isPresent()) {
            return formaPagamentoOptional.get();
        }

        System.out.println("Forma de pagamento não disponível");
        return null;
    }
}
