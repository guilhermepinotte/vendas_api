package io.github.guilhermepinotte.vendas.domain.produtoPedido;

import java.math.BigDecimal;

public record DadosDetalhamentoItensPedido(
        String descricao,
        Long quantidade) {

    public DadosDetalhamentoItensPedido (ProdutoPedido item){
        this(item.getProduto().getDescricao(), item.getQuantidade());
    }
}
