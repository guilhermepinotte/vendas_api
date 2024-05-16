package io.github.guilhermepinotte.vendas.domain.pedido;

import io.github.guilhermepinotte.vendas.domain.cliente.Cliente;
import io.github.guilhermepinotte.vendas.domain.produtoPedido.ProdutoPedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "pedidos")
@Entity(name = "Pedido")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @NotNull
    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;

    @Column(precision = 20, scale = 2)
    private BigDecimal total;

    @OneToMany(mappedBy = "pedido",fetch = FetchType.LAZY)
    private List<ProdutoPedido> itens;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.dataPedido = LocalDateTime.now();
//        this.total = BigDecimal.ZERO;
    }

    public void atualizaListaProdutos(List itens){
        this.itens = itens;
    }

    public void calculaTotal() {
        this.total = this.itens
                        .stream()
                        .map(i -> BigDecimal.valueOf(i.getQuantidade())
                                                      .multiply(i.getProduto().getPrecoUnitario()))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

//    public void calculaTotal() {
//        this.total = this.itens
//                .stream()
//                .map(i ->  BigDecimal.valueOf(i.getQuantidade()).multiply(i.getProduto().getPrecoUnitario()))
//                .sum();
//    }

//    public void calculaTotal() {
//        var total = BigDecimal.ZERO;
//
//        for (ProdutoPedido item : itens) {
//            var quantidade = BigDecimal.valueOf(item.getQuantidade());
//            var preco = item.getProduto().getPrecoUnitario();
//            total = total.add(quantidade.multiply(preco));
//        }
//        this.total = total;
//    }
}
