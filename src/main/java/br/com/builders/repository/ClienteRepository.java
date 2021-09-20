
package br.com.builders.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.builders.entity.Cliente;

public interface  ClienteRepository extends JpaRepository<Cliente, Long>, PagingAndSortingRepository<Cliente, Long> {
	
	 @Query("SELECT a FROM Cliente a WHERE LOWER (a.nome) LIKE LOWER( concat('%',:nome,'%'))")
	 List<Cliente> findByName(@Param("nome")  String nome);
	 
	 @Query("SELECT a FROM Cliente a WHERE a.cpf = :cpf")
	 Cliente findByCpf(@Param("cpf") String cpf);
}



