package br.com.senac.api.controllers;
import br.com.senac.api.dtos.ClientesRequestDTO;
import br.com.senac.api.entidades.Clientes;
import br.com.senac.api.repositorios.ClientesRepositorio;
import br.com.senac.api.service.ClientesService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
@CrossOrigin
public class ClientesController {

    private ClientesService clientesService;

    public ClientesController(ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Clientes>> listar(){
        List<Clientes> clientesList = clientesService.listar();

        if (clientesList.isEmpty()){
            return ResponseEntity.status(204).body(null);
        }
        return ResponseEntity.ok(clientesService.listar());
    }

    @PostMapping("/criar")
    public ResponseEntity<Clientes> criar (@RequestBody ClientesRequestDTO cliente){

        try {
            return ResponseEntity.ok(clientesService.criar(cliente));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Clientes> atualizar (@RequestBody ClientesRequestDTO cliente, @PathVariable long id) {

        try {
            return ResponseEntity.ok(clientesService.atualizar(id, cliente));
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id){

        try {
            clientesService.deletar(id);
            return ResponseEntity.ok(null);
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body(null);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

}
