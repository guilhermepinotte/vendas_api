package io.github.guilhermepinotte.vendas.domain.cliente;

import io.github.guilhermepinotte.vendas.domain.endereco.Endereco;

public record DadosDetalhamentoCliente(
    Long id,
    String nome,
    String email,
    TipoCliente tipo,
    Endereco endereco) {
    public DadosDetalhamentoCliente(Cliente c){
        this(c.getId(),c.getNome(),c.getEmail(),c.getTipo(),c.getEndereco());
    }
}
