package io.github.guilhermepinotte.vendas.controller;

import io.github.guilhermepinotte.vendas.domain.cliente.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder){
        var cliente = new Cliente(dados);
        this.clienteRepository.save(cliente);

        var location = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(location).body(new DadosDetalhamentoCliente(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var cliente = this.clienteRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        this.clienteRepository.getReferenceById(id).inativar();

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
     public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoCliente dados){
        var cliente = this.clienteRepository.getReferenceById(dados.id());
        cliente.atualizarDados(dados);

        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }
}
