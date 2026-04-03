package br.com.senac.api.controllers;


import br.com.senac.api.dtos.ClientesRequestDTO;
import br.com.senac.api.entidades.Clientes;
import br.com.senac.api.repositorios.ClientesRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
@CrossOrigin
public class ClientesController {

    private ClientesRepositorio clientesRepositorio;

    public ClientesController (ClientesRepositorio clientesRepositorio) { this.clientesRepositorio = clientesRepositorio; }

    @GetMapping("/listar")
    public ResponseEntity<List<Clientes>> listar(){
        List<Clientes> clientesList = clientesRepositorio.findAll();

        if (clientesList.isEmpty()){
            return ResponseEntity.status(204).body(null);
        }
        return ResponseEntity.ok(clientesList);
    }

    @PostMapping("/criar")
    public ResponseEntity<Clientes> criar (@RequestBody ClientesRequestDTO cliente){

        Clientes clientesPersist = new Clientes();
        clientesPersist.setNome(cliente.getNome());
        clientesPersist.setEmail(cliente.getEmail());
        clientesPersist.setDocumento(cliente.getDocumento());
        clientesPersist.setIdade(cliente.getIdade());

        Clientes retorno = clientesRepositorio.save(clientesPersist);

        return ResponseEntity.status(201).body(retorno);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Clientes> atualizar (@RequestBody ClientesRequestDTO cliente, @PathVariable long id){

        if(clientesRepositorio.existsById(id)){

            Clientes clientesPersist = new Clientes();
            clientesPersist.setNome(cliente.getNome());
            clientesPersist.setEmail(cliente.getEmail());
            clientesPersist.setDocumento(cliente.getDocumento());
            clientesPersist.setIdade(cliente.getIdade());

            Clientes retorno = clientesRepositorio.save(clientesPersist);

            return ResponseEntity.ok(retorno);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id){

        if (clientesRepositorio.existsById(id)) {

            clientesRepositorio.deleteById(id);

            return ResponseEntity.ok(null);
        }
        return ResponseEntity.badRequest().body(null);
    }

}
