package io.github.guilhermepinotte.vendas.domain.produto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "produtos")
@Entity(name = "Produto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;

    @Enumerated(EnumType.STRING)
    private TipoProduto tipo;

    public Produto(DadosCadastroProduto dados){
        this.descricao = dados.descricao();
        this.precoUnitario = dados.precoUnitario();
        this.tipo = dados.tipo();
    }

    public void atualizarDados(DadosAtualizacaoProduto dados) {
        if (dados.descricao() != null) this.descricao = dados.descricao();
        if (dados.precoUnitario() != null) this.precoUnitario = dados.precoUnitario();
        if (dados.tipo() != null) this.tipo = dados.tipo();
    }
}
