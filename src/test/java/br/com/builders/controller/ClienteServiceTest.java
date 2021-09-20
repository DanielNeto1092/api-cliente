package br.com.builders.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.com.builders.Application;
import br.com.builders.dto.ClienteDTO;
import br.com.builders.service.IClienteService;

@SpringBootTest(classes = Application.class, webEnvironment=WebEnvironment.NONE)
public class ClienteServiceTest {

	@Autowired
	private IClienteService clienteService;

	
	
	@Test 
	public void listarClientes () throws Exception {
		clienteService.criarCliente(new ClienteDTO("Alex", "91680169050","10/10/1910"));
		List<ClienteDTO> clientesRetorno = clienteService.listarTodosClientes();
        assertNotNull(clientesRetorno);
	}
	
	@Test 
	public void listarClientesPaginacao () throws Exception {
		clienteService.criarCliente(new ClienteDTO("Alex", "28036697068","10/10/1910"));
		List<ClienteDTO> clientesRetorno = clienteService.listarTodosClientes(1, 1);
        assertNotNull(clientesRetorno);
	}
	
    @Test
    public void incluirCliente() {
		ClienteDTO cliente = new ClienteDTO("Alex", "43172444031","10/10/1910");
		ClienteDTO clienteDTO = clienteService.criarCliente(cliente);
        assertNotNull(clienteDTO);
        assertEquals(clienteDTO.getCpf(), cliente.getCpf());
    }
    
    @Test
    public void alterarCliente() {
		ClienteDTO clienteDTO = clienteService.criarCliente(new ClienteDTO("Alex", "81835259030","10/10/1910"));
		clienteDTO.setNome("Alex Silva");
		ClienteDTO cliente = clienteService.alterarCliente(clienteDTO);
        assertNotNull(cliente);
        assertEquals(cliente.getNome(), clienteDTO.getNome());
    }
    
	@Test 
	public void excluirCliente() throws Exception {
		ClienteDTO cliente = clienteService.criarCliente(new ClienteDTO("Alex", "76626563068","10/10/1910"));
		boolean retorno = clienteService.excluirCliente(cliente.getId());
		ClienteDTO clienteRetorno = clienteService.buscarCliente(cliente.getId());
        assertNull(clienteRetorno);
        assertEquals(true, retorno);
	}

    @Test
    public void buscarClientePorNome() {
		ClienteDTO cliente = new ClienteDTO("Alex Oliveira", "96942294055","10/10/1910");
		ClienteDTO clienteDTO = clienteService.criarCliente(cliente);
		List<ClienteDTO> clientesRetorno = clienteService.buscarCliente(clienteDTO.getNome());
        assertNotNull(clientesRetorno);
        assertEquals(1, clientesRetorno.size());
    }
    
    @Test
    public void buscarClientePorCPF() {
		ClienteDTO clienteDTO = clienteService.criarCliente(new ClienteDTO("Alex", "54547617023","10/10/1910"));
		ClienteDTO clienteRetorno = clienteService.buscarClientePorCpf(clienteDTO.getCpf());
        assertNotNull(clienteRetorno);
        assertEquals(clienteRetorno, clienteDTO);
    }
    
    @Test
    public void buscarClientePorId() {
		ClienteDTO clienteDTO = clienteService.criarCliente(new ClienteDTO("Alex", "92132488061","10/10/1910"));
		ClienteDTO clienteRetorno = clienteService.buscarCliente(clienteDTO.getId());
        assertNotNull(clienteRetorno);
        assertEquals(clienteRetorno, clienteDTO);
    }
    
    @Test
    public void buscarClientePorCPFNull() {
		clienteService.criarCliente(new ClienteDTO("Alex", "80936652004","10/10/1910"));
    	ClienteDTO clienteRetorno = clienteService.buscarClientePorCpf("01234567890");
        assertNull(clienteRetorno);
    }
    
    @Test
    public void buscarClientePorIdNull() {
    	clienteService.criarCliente(new ClienteDTO("Alex", "95613969043","10/10/1910"));
		ClienteDTO clienteRetorno = clienteService.buscarCliente(0L);
        assertNull(clienteRetorno);
    }
    
    @Test
    public void buscarClientePorNomeEmpty() {
		ClienteDTO cliente = new ClienteDTO("Alex", "87456663009","10/10/1910");
		ClienteDTO clienteDTO = clienteService.criarCliente(cliente);
		List<ClienteDTO> clientes = new ArrayList<>();
		clientes.add(clienteDTO);
    	List<ClienteDTO> clientesRetorno = clienteService.buscarCliente("Ronaldo");
        assertEquals(0, clientesRetorno.size());
    }
	
    @Test
    public void incluirClienteDuplicado() {
    	ClienteDTO cliente = clienteService.criarCliente(new ClienteDTO("Alex", "95613969043","10/10/1910"));
    	ClienteDTO clienteDTO = clienteService.criarCliente(cliente);
        assertNull(clienteDTO);
    }
    
    @Test
    public void incluirClienteNull() {
		ClienteDTO clienteDTO = clienteService.criarCliente(null);
        assertNull(clienteDTO);
    }
    
	@Test 
	public void excluirClienteNaoEncontrado() throws Exception {
		boolean retorno = clienteService.excluirCliente(0L);
        assertEquals(false, retorno);
	}
    
    @Test
    public void alterarClienteNaoEncontrado() {
		ClienteDTO cliente = clienteService.alterarCliente(new ClienteDTO("Alex", "53097810056","10/10/1910"));
        assertNull(cliente);
    }
	
}