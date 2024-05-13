package io.github.guilhermepinotte.vendas.domain.produto;

import io.github.guilhermepinotte.vendas.domain.cliente.DadosDetalhamentoCliente;

import java.math.BigDecimal;

public record DadosDetalhamentoProduto(
        Long id,
        String descricao,
        BigDecimal precoUnitario,
        TipoProduto tipo) {

    public DadosDetalhamentoProduto(Produto p){
        this(p.getId(),p.getDescricao(),p.getPrecoUnitario(),p.getTipo());
    }
}
