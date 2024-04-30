package io.github.guilhermepinotte.vendas.controller;

import io.github.guilhermepinotte.vendas.domain.cliente.Cliente;
import io.github.guilhermepinotte.vendas.domain.cliente.ClienteRepository;
import io.github.guilhermepinotte.vendas.domain.cliente.DadosCadastroCliente;
import io.github.guilhermepinotte.vendas.domain.cliente.DadosDetalhamentoCliente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        clienteRepository.save(cliente);

        var location = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(location).body(new DadosDetalhamentoCliente(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var cliente = clienteRepository.getReferenceById(id);
        System.out.println(cliente);
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }
}
