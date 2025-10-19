package br.com.restaurante.www.pagamento.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Cartoes")
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CARTAO")
    private Long id;

    @NotBlank(message = "O campo número é obrigatório")
    @Size(max = 16, message = "O campo número deve possuir 16, 15 ou 14 digitos")
    @Column(name = "NUMERO")
    private String numeroCartao;

    @NotBlank(message = "O campo titular é obrigatório")
    @Column(name = "TITULAR")
    private String nomeTitular;

    @NotBlank(message = "O campo validade é obrigatório")
    @Column(name = "VALIDADE")
    private String validade;

    @NotBlank(message = "O campo CVV é obrigatório")
    @Size(max = 4, message = "O código de segurança deve possuir 3 ou 4 digitos")
    @Column(name = "CVV")
    private String cvv;

    @NotBlank(message = "O campo senha é obrigatório")
    @Size(max = 6, message = "A senha deve possui 6 digitos")
    @Column(name = "SENHA")
    private String senha;

    public Cartao() {

    }

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
