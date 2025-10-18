package br.com.restaurante.www.pagamento.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Pagamentos")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PAGAMENTO")
    private Long id;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    @Column(name = "VALOR")
    private double valor;

    @Column(name = "PARCELAS")
    private int parcelas;   // usado apenas em crédito

    @Column(name = "TROCO")
    private double troco;   // usado apenas em dinheiro

    @Column(name = "DATA_PAGAMENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPagamento;

    public Pagamento(Long id, String formaPagamento, double valor, int parcelas, double troco, Date dataPagamento) {
        this.id = id;
        this.formaPagamento = FormaPagamento.encontrarFormaDePagamento(formaPagamento);
        this.valor = valor;
        this.parcelas = parcelas;
        this.troco = troco;
        this.dataPagamento = dataPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public FormaPagamento getFormaDePagamento() {
        return formaPagamento;
    }

    public void setFormaDePagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
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
