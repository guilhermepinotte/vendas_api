package io.github.guilhermepinotte.vendas.domain.cliente;

import io.github.guilhermepinotte.vendas.domain.endereco.Endereco;
import io.github.guilhermepinotte.vendas.domain.pedido.Pedido;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;

    @Enumerated(EnumType.STRING)
    private TipoCliente tipo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;

    private Boolean ativo;

    public Cliente (DadosCadastroCliente dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.tipo = dados.tipo();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    //exclusão lógica
    public void inativar() {
        this.ativo = false;
    }

    public void ativar() {
        this.ativo = true;
    }

    public void atualizarDados(DadosAtualizacaoCliente dados) {
        if (dados.nome() != null) this.nome = dados.nome();
        if (dados.email() != null) this.email = dados.email();
        if (dados.tipo() != null) this.tipo = dados.tipo();
        if (dados.endereco() != null) this.endereco.atualizarDados(dados.endereco());
    }
}
