package br.com.builders.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.builders.dto.ClienteDTO;
import br.com.builders.service.IClienteService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/v1/cliente")
public class ClienteController {

	@Autowired
    private IClienteService clienteService;

  	@ApiOperation(value = "Listar todos os clientes")
    @GetMapping(value = "/listarTodos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClienteDTO>>  listarTodosClientes() {
  		List<ClienteDTO> clientes = clienteService.listarTodosClientes();
        return new ResponseEntity<List<ClienteDTO>>(clientes, new HttpHeaders(),clientes.isEmpty() ? HttpStatus.NOT_FOUND:HttpStatus.OK);
	}
    
  	@ApiOperation(value = "Listar todos os clientes (paginacao)")
    @GetMapping(value = "/listaTodosPaginacao", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClienteDTO>> listaTodosPaginacao(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
  		List<ClienteDTO> clientes = clienteService.listarTodosClientes(pageNo, pageSize);
        return new ResponseEntity<List<ClienteDTO>>(clientes, new HttpHeaders(),clientes.isEmpty() ? HttpStatus.NOT_FOUND:HttpStatus.OK);
	}
  	
  	@ApiOperation(value = "Buscar cliente por id")
    @GetMapping(value = "/buscarPorId", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClienteDTO> buscarClientePorId(@RequestParam(value = "id") Long id) {
		ClienteDTO cliente = clienteService.buscarCliente(id);
        return ResponseEntity.status(cliente != null ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(cliente);
	}
  	
  	@ApiOperation(value = "Buscar cliente por nome")
    @GetMapping(value = "/buscarPorNome", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClienteDTO>> buscarClientePorNome(@RequestParam(value = "nome") String nome) {
  		List<ClienteDTO> clientes = clienteService.buscarCliente(nome);
        return new ResponseEntity<List<ClienteDTO>>(clientes, new HttpHeaders(),clientes.isEmpty() ? HttpStatus.NOT_FOUND:HttpStatus.OK);

	}
  	
  	@ApiOperation(value = "Buscar cliente por CPF")
    @GetMapping(value = "/buscarPorCpf", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClienteDTO> buscarClientePorCpf(@Valid @RequestParam(value = "cpf") String nome) {
    	ClienteDTO cliente = clienteService.buscarClientePorCpf(nome);
        return ResponseEntity.status(cliente != null ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(cliente);
	}
  	
  	@ApiOperation(value = "Cadastrar cliente")
    @PostMapping(value = "/criarCliente", 
            consumes = MediaType.APPLICATION_JSON_VALUE, 
            produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ClienteDTO> criarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
    	ClienteDTO cliente = clienteService.criarCliente(clienteDTO);
        return ResponseEntity.status(cliente != null ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(cliente);

	}
  	
  	@ApiOperation(value = "Alterar cliente")
    @PutMapping(value = "/alterarCliente", 
            consumes = MediaType.APPLICATION_JSON_VALUE, 
            produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ClienteDTO> alterarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
    	ClienteDTO cliente = clienteService.alterarCliente(clienteDTO);
        return ResponseEntity.status(cliente != null ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(cliente);
	}
  	
  	@ApiOperation(value = "Excluir cliente por ID")
    @DeleteMapping(value = "/excluirCliente", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>  excluirCliente(@RequestParam(value = "id") Long id)  {
    	boolean sucesso = clienteService.excluirCliente(id);
    	return  sucesso ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();

	}
}
