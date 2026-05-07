package br.com.senac.api.repositorios;

import br.com.senac.api.entidades.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientesRepositorio extends JpaRepository<Clientes, Long> {
    List<Clientes> findByNomeContaining(String nome);

    List<Clientes> findByEmail(String email);

    List<Clientes> findByIdadeGreaterThan(Integer idade);

}
