package io.github.guilhermepinotte.vendas.domain.pedido;

import io.github.guilhermepinotte.vendas.domain.cliente.Cliente;
import io.github.guilhermepinotte.vendas.domain.produto.Produto;
import io.github.guilhermepinotte.vendas.domain.produto.TipoProduto;
import io.github.guilhermepinotte.vendas.domain.produtoPedido.ProdutoPedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    @Test
    void deveriaCalcularCorretamenteTotalDoPedido() {
        //ASSERT
        var item1 = new ProdutoPedido(1L,new Pedido(),new Produto(1L,"",BigDecimal.valueOf(100.00), TipoProduto.HORTIFRUTI), 2L);
        var item2 = new ProdutoPedido(2L,new Pedido(),new Produto(2L,"",BigDecimal.valueOf(2.50), TipoProduto.HORTIFRUTI), 3L);
        var item3 = new ProdutoPedido(3L,new Pedido(),new Produto(3L,"",BigDecimal.valueOf(0.50), TipoProduto.HORTIFRUTI), 1L);
        List<ProdutoPedido> itens = List.of(item1,item2,item3);
        var pedido = new Pedido(new Cliente());
        pedido.atualizaListaProdutos(itens);

        //ACT
        pedido.calculaTotal();

        //ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(208.00),pedido.getTotal());
    }
}