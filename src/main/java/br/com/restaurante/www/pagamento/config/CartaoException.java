package br.com.restaurante.www.pagamento.config;

public class CartaoException extends Exception{
    public CartaoException(String mensagem) {
        super(mensagem);
    }
}
