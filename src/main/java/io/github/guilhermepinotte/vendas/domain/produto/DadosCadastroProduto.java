package io.github.guilhermepinotte.vendas.domain.produto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record DadosCadastroProduto(
        @NotBlank
        String descricao,
        @Positive @DecimalMin("0.00")
        BigDecimal precoUnitario,
        @NotNull
        TipoProduto tipo) {}
