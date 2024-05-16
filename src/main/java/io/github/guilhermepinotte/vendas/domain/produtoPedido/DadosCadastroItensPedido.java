package io.github.guilhermepinotte.vendas.domain.produtoPedido;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroItensPedido(
        @NotNull
        Long produto_id,
        @NotNull
        Long quantidade) {}
