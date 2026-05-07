package br.com.senac.api.service;
import br.com.senac.api.dtos.ClienteFiltroDTO;
import br.com.senac.api.dtos.ClientesRequestDTO;
import br.com.senac.api.entidades.Clientes;
import br.com.senac.api.repositorios.ClientesRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesService {

    private ClientesRepositorio clientesRepositorio;

    public ClientesService(ClientesRepositorio clientesRepositorio) {
        this.clientesRepositorio = clientesRepositorio;
    }

    public List<Clientes> listar(ClienteFiltroDTO filtro) {

        if (filtro.getNome() != null){
            return clientesRepositorio.findByNomeContaining(filtro.getNome());
        }

        if (filtro.getEmail() != null){
            return clientesRepositorio.findByEmail(filtro.getEmail());
        }

        if (filtro.getIdade() != null){
            return clientesRepositorio.findByIdadeGreaterThan(filtro.getIdade());
        }

        return clientesRepositorio.findAll();
    }

    public Clientes criar (ClientesRequestDTO cliente) {
        Clientes clientePersist = this.clientesRequestDtoParaClientes(cliente);

        return clientesRepositorio.save(clientePersist);
    }

    private Clientes clientesRequestDtoParaClientes(ClientesRequestDTO entrada) {
        Clientes saida = new Clientes();

        saida.setNome(entrada.getNome());
        saida.setDocumento(entrada.getDocumento());
        saida.setEmail(entrada.getEmail());
        saida.setIdade(entrada.getIdade());

        return saida;
    }

    public Clientes atualizar(Long id, ClientesRequestDTO cliente) {

        if (clientesRepositorio.existsById(id)) {

            Clientes clientePersist = this.clientesRequestDtoParaClientes(cliente);
            clientePersist.setId(id);

            return clientesRepositorio.save(clientePersist);

        }

        throw new RuntimeException("Cliente não encontrado!");
    }

    public void deletar(Long id) {

        if (clientesRepositorio.existsById(id)) {

            clientesRepositorio.deleteById(id);
        }

        throw new RuntimeException("Cliente não encontrado!");

    }

    public Clientes ListarByID(Long id) {
        if (clientesRepositorio.existsById(id)) {
            return clientesRepositorio.findById(id).get();
        } else {
            throw new RuntimeException("Cliente não existe");
        }
    }
}
