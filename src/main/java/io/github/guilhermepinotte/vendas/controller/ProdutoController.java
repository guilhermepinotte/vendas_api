package io.github.guilhermepinotte.vendas.controller;

import io.github.guilhermepinotte.vendas.domain.produto.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroProduto dados, UriComponentsBuilder uriBuilder) {
        var produto = new Produto(dados);
        this.produtoRepository.save(produto);

        var location = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(location).body(new DadosDetalhamentoProduto(produto));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar (@PathVariable Long id){
        var produto = this.produtoRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        this.produtoRepository.delete(this.produtoRepository.getReferenceById(id));

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoProduto dados) {
        var produto = this.produtoRepository.getReferenceById(dados.id());
        produto.atualizarDados(dados);

        return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
    }

    @GetMapping
    public ResponseEntity listar (@PageableDefault(size = 10,sort = {"descricao"}) Pageable paginacao){
        var page = this.produtoRepository
                    .findAll(paginacao)
                    .map(DadosDetalhamentoProduto::new);
        return ResponseEntity.ok(page);
    }
}
