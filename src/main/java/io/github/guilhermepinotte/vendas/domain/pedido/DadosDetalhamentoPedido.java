package io.github.guilhermepinotte.vendas.domain.pedido;

import io.github.guilhermepinotte.vendas.domain.produtoPedido.DadosDetalhamentoItensPedido;
import io.github.guilhermepinotte.vendas.domain.produtoPedido.ProdutoPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record DadosDetalhamentoPedido(
        Long id,
        String nomeCliente,
        LocalDateTime dataPedido,
        BigDecimal total,
        List<DadosDetalhamentoItensPedido> dados) {

    public DadosDetalhamentoPedido(Pedido p,  List<DadosDetalhamentoItensPedido> listaDadosDetalhamentoItensPedidos) {
        this(p.getId(),
                p.getCliente().getNome(),
                p.getDataPedido(),
                p.getTotal(),
                listaDadosDetalhamentoItensPedidos);
    }


}
