package io.github.guilhermepinotte.vendas.domain.pedido;

import io.github.guilhermepinotte.vendas.domain.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {

    @Query("""
            select c 
            from Cliente c
            left join fetch c.pedidos
            where c.id = :id
            order by c.nome
            """)
    Cliente findClienteFechtPedidos(Long id);

}
