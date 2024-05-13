package io.github.guilhermepinotte.vendas.domain.produto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosAtualizacaoProduto(
        @NotNull
        Long id,
        String descricao,
        BigDecimal precoUnitario,
        TipoProduto tipo) {}
