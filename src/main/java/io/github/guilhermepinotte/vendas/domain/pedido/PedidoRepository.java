package io.github.guilhermepinotte.vendas.domain.pedido;

import io.github.guilhermepinotte.vendas.domain.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {

    @Query("""
            select c 
            from Cliente c
            left join fetch c.pedidos
            where c.id = :id
            order by c.nome
            """)
    Cliente findClienteFechtPedidos(Long id);

//    @Query("""
//            select sum(pp.quantidade*prod.precoUnitario)
//            from Pedido p
//            inner join ProdutoPedido.pedido pp
//            inner join Produto prod on prod.id = pp.produto
//            group by p.id
//            where p.id = :id
//            """)
//    BigDecimal calculaTotal(Long id);

}
