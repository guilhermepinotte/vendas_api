package io.github.guilhermepinotte.vendas.service;

import io.github.guilhermepinotte.vendas.domain.cliente.ClienteRepository;
import io.github.guilhermepinotte.vendas.domain.pedido.DadosCadastroPedido;
import io.github.guilhermepinotte.vendas.domain.pedido.DadosDetalhamentoPedido;
import io.github.guilhermepinotte.vendas.domain.pedido.Pedido;
import io.github.guilhermepinotte.vendas.domain.pedido.PedidoRepository;
import io.github.guilhermepinotte.vendas.domain.pedido.exception.PedidoException;
import io.github.guilhermepinotte.vendas.domain.produto.ProdutoRepository;
import io.github.guilhermepinotte.vendas.domain.produtoPedido.DadosCadastroItensPedido;
import io.github.guilhermepinotte.vendas.domain.produtoPedido.DadosDetalhamentoItensPedido;
import io.github.guilhermepinotte.vendas.domain.produtoPedido.ProdutoPedido;
import io.github.guilhermepinotte.vendas.domain.produtoPedido.ProdutoPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private ProdutoPedidoRepository produtoPedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public DadosDetalhamentoPedido criar(DadosCadastroPedido dados) {
        if (!clienteRepository.existsById(dados.cliente_id())) {
            throw new PedidoException("Id do cliente informado não existe");
        }

        var cliente = this.clienteRepository.getReferenceById(dados.cliente_id());

        var pedido = new Pedido(cliente);
        var itens = montaListaDeItensDoPedido(dados.itens(), pedido);
        pedido.atualizaListaProdutos(itens);
        pedido.calculaTotal();

        //persistência
        this.pedidoRepository.save(pedido);
        this.produtoPedidoRepository.saveAll(itens);

        var itensDetalhamento = criaListaItensDetalhamento(itens);

        return new DadosDetalhamentoPedido(pedido,itensDetalhamento);
    }

    private List<DadosDetalhamentoItensPedido> criaListaItensDetalhamento(List<ProdutoPedido> itens) {
        return itens
                .stream()
                .map(DadosDetalhamentoItensPedido::new)
                .toList();
    }

    private List<ProdutoPedido> montaListaDeItensDoPedido(List<DadosCadastroItensPedido> itens, Pedido pedido) {
//        var itensPedido = itens.stream().forEach(i -> montaItem(i));
        List<ProdutoPedido> lista = new ArrayList<>();

        for (DadosCadastroItensPedido item: itens) {
            if(!this.produtoRepository.existsById(item.produto_id())){
                throw new PedidoException("Id do produto informado não existe");
            }
            var produto = this.produtoRepository.getReferenceById(item.produto_id());
            var produtoPedido = new ProdutoPedido(pedido, produto, item);
            lista.add(produtoPedido);
        }

        return lista;
    }


}

