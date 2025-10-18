package br.com.restaurante.www.pagamento.entities;

import java.time.LocalDate;

public class Pagamento {
    // Constantes de formas de pagamento
    public static final String FORMA_CREDITO = "CREDITO";
    public static final String FORMA_DEBITO = "DEBITO";
    public static final String FORMA_PIX = "PIX";
    public static final String FORMA_DINHEIRO = "DINHEIRO";

    // Constantes de status
    public static final String STATUS_APROVADO = "APROVADO";
    public static final String STATUS_PENDENTE = "PENDENTE";
    public static final String STATUS_RECUSADO = "RECUSADO";
    public static final String STATUS_CANCELADO = "CANCELADO";

    // Atributos
    private int id; // id_pagamento
    private double valor;
    private String formaPagamento;
    private String status;
    private LocalDate dataPagamento;
    private int parcelas;   // usado apenas em crédito
    private double troco;   // usado apenas em dinheiro

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public double getTroco() {
        return troco;
    }

    public void setTroco(double troco) {
        this.troco = troco;
    }
}
