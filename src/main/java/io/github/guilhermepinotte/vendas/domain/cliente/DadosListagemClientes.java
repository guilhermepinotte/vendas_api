package io.github.guilhermepinotte.vendas.domain.cliente;

public record DadosListagemClientes(
        Long id,
        String nome,
        String email,
        TipoCliente tipo
) {
    public DadosListagemClientes(Cliente c){
        this(c.getId(),c.getNome(),c.getEmail(),c.getTipo());
    }
}
