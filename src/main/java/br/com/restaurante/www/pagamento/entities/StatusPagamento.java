package br.com.restaurante.www.pagamento.entities;

import java.util.Arrays;
import java.util.Optional;

public enum StatusPagamento {
    APROVADO("APROVADO"),
    PENDENTE("PENDENTE"),
    RECUSADO("RECUSADO"),
    CANCELADO("CANCELADO");

    private String status;

    StatusPagamento(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static StatusPagamento encontrarStatusDePagamento(String statusPagamento) {
        Optional<StatusPagamento> statusPagamentoOptional = Arrays.stream(StatusPagamento.values())
                .filter(s -> s.getStatus().equalsIgnoreCase(statusPagamento))
                .findFirst();

        if (statusPagamentoOptional.isPresent()) {
            return statusPagamentoOptional.get();
        }

        return null;
    }
}
