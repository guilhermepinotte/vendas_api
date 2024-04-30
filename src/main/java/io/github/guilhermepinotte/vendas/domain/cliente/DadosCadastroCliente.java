package io.github.guilhermepinotte.vendas.domain.cliente;

import io.github.guilhermepinotte.vendas.domain.endereco.DadosEndereco;
import io.github.guilhermepinotte.vendas.domain.endereco.Endereco;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCliente(
        @NotBlank
        String nome,
        @NotBlank @Email
        String email,
        @NotNull
        TipoCliente tipo,
        @NotNull @Valid
        DadosEndereco endereco) {
}
