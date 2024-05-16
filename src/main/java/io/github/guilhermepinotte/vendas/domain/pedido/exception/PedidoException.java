package io.github.guilhermepinotte.vendas.domain.pedido.exception;

public class PedidoException extends RuntimeException {
    public PedidoException(String mensagem) {
        super(mensagem);
    }
}
