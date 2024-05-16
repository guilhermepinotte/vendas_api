package io.github.guilhermepinotte.vendas.domain.produtoPedido;

import io.github.guilhermepinotte.vendas.domain.pedido.Pedido;
import io.github.guilhermepinotte.vendas.domain.produto.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "produtos_pedido")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProdutoPedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private Long quantidade;

    public ProdutoPedido(Pedido pedido, Produto produto, DadosCadastroItensPedido dados) {
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = dados.quantidade();
    }
}
