package io.github.guilhermepinotte.vendas.domain.pedido;

import io.github.guilhermepinotte.vendas.domain.produtoPedido.DadosCadastroItensPedido;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosCadastroPedido(
        @NotNull
        Long cliente_id,
        List<DadosCadastroItensPedido> itens) {}
