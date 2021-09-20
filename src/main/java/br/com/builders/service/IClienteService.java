package br.com.builders.service;

import java.util.List;

import br.com.builders.dto.ClienteDTO;

public interface IClienteService  {
	
	List<ClienteDTO> listarTodosClientes();
	
	List<ClienteDTO> listarTodosClientes(Integer pageNo, Integer pageSize);

    ClienteDTO buscarCliente(Long id);
    
    List<ClienteDTO> buscarCliente(String nome);

    ClienteDTO buscarClientePorCpf(String cpf);
    
    ClienteDTO criarCliente(ClienteDTO clienteDTO);
    
    ClienteDTO alterarCliente(ClienteDTO clienteDTO);
    
    boolean excluirCliente(Long id);

}
