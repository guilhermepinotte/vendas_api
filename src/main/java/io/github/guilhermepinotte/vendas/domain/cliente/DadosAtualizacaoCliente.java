package io.github.guilhermepinotte.vendas.domain.cliente;

import io.github.guilhermepinotte.vendas.domain.endereco.DadosEndereco;
import io.github.guilhermepinotte.vendas.domain.endereco.Endereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCliente(
        @NotNull
        Long id,
        String nome,
        String email,
        TipoCliente tipo,
        DadosEndereco endereco) {}
