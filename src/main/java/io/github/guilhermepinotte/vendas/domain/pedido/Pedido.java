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
import java.time.LocalDate;
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
}
