package br.com.restaurante.www.pagamento.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Cartoes")
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CARTAO")
    private Long id;

    @Column(name = "NUMERO")
    private String numeroCartao;

    @Column(name = "TITULAR")
    private String nomeTitular;

    @Column(name = "VALIDADE")
    private String validade;

    @Column(name = "CVV")
    private String cvv;

    @Column(name = "SENHA")
    private String senha;

    public Cartao(Long id, String numeroCartao, String nomeTitular, String validade, String cvv, String senha) {
        this.id = id;
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
        this.validade = validade;
        this.cvv = cvv;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
