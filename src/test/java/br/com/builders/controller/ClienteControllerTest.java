package br.com.builders.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.builders.Application;
import br.com.builders.dto.ClienteDTO;

@SpringBootTest(classes = Application.class, webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClienteControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	
	@Test 
	public void incluirCliente_200() throws Exception {
		ClienteDTO cliente = new ClienteDTO(1L,"Ze das Couves","42997475007","30/10/1992");
		mockMvc.perform(post("/api/v1/cliente/criarCliente")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(cliente)))
			.andExpect(status().isCreated()).andDo(print());
	}
	
	@Test 
    @Sql(statements = "INSERT INTO Cliente (id, nome, cpf, data_nascimento) VALUES (1, 'Ze das Couves', '42997475007', '1992-10-30')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Cliente", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void listarTodosClientes_200() throws Exception {
		mockMvc.perform(get("/api/v1/cliente/listarTodos")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andDo(print());
	}
	
	
	@Test 
    @Sql(statements = "INSERT INTO Cliente (id, nome, cpf, data_nascimento) VALUES (1, 'Ze das Couves', '42997475007', '1992-10-30')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Cliente", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void listarTodosClientesPaginacao_200() throws Exception {
		mockMvc.perform(get("/api/v1/cliente/listaTodosPaginacao")
			.param("pageNo", "0")
			.param("pageSize","10")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andDo(print());
	}
	
	
	@Test 
    @Sql(statements = "INSERT INTO Cliente (id, nome, cpf, data_nascimento) VALUES (1, 'Ze das Couves', '42997475007', '1992-10-30')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Cliente", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void alterarCliente_200() throws Exception {
		ClienteDTO cliente = new ClienteDTO(1L,"Ze","42997475007","30/10/1992");
		mockMvc.perform(put("/api/v1/cliente/alterarCliente")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(cliente)))
			.andExpect(status().isOk()).andDo(print());
	}
	
	@Test 
    @Sql(statements = "INSERT INTO Cliente (id, nome, cpf, data_nascimento) VALUES (1, 'Ze das Couves', '92519137061', '1992-10-30')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Cliente", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void excluirCliente_200() throws Exception {
		mockMvc.perform(delete("/api/v1/cliente/excluirCliente")
			.contentType(MediaType.APPLICATION_JSON)
			.param("id", "1"))
			.andExpect(status().isOk()).andDo(print());
	}
	
	@Test 
    @Sql(statements = "INSERT INTO Cliente (id, nome, cpf, data_nascimento) VALUES (1, 'Ze das Couves', '04716201066', '1992-10-30')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Cliente", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void buscarPorNome_200() throws Exception {
		mockMvc.perform(get("/api/v1/cliente/buscarPorNome")
			.param("nome", "Ze das Couves")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void buscarPorNome_404() throws Exception {
		mockMvc.perform(get("/api/v1/cliente/buscarPorNome")
			.param("nome", "Ana")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound()).andDo(print());
	}

	@Test 
    @Sql(statements = "INSERT INTO Cliente (id, nome, cpf, data_nascimento) VALUES (1, 'Ze das Couves', '84842507098', '1992-10-30')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Cliente", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void buscarPorCPF_200() throws Exception {
		mockMvc.perform(get("/api/v1/cliente/buscarPorCpf")
			.param("cpf", "84842507098")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void buscarPorCPF_404() throws Exception {
		mockMvc.perform(get("/api/v1/cliente/buscarPorCpf")
			.param("cpf", "84743862094")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound()).andDo(print());
	}
	
	@Test 
	public void incluirClienteCpfInvalido_400() throws Exception {
		ClienteDTO cliente = new ClienteDTO(1L,"Ze das Couves","42997475004","30/10/1992");
		mockMvc.perform(post("/api/v1/cliente/criarCliente")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(cliente)))
			.andExpect(status().isBadRequest()).andDo(print());
	}
	
	@Test 
	public void alterarCliente_404() throws Exception {
		ClienteDTO cliente = new ClienteDTO(0L,"Ze","42997475007","30/10/1992");
		mockMvc.perform(put("/api/v1/cliente/alterarCliente")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(cliente)))
			.andExpect(status().isNotFound()).andDo(print());
	}
	
	@Test 
	public void alterarClienteCpfInvalido_400() throws Exception {
		ClienteDTO cliente = new ClienteDTO(0L,"Ze","42997475008","30/10/1992");
		mockMvc.perform(put("/api/v1/cliente/alterarCliente")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(cliente)))
			.andExpect(status().isBadRequest()).andDo(print());
	}
	
	@Test 
	public void excluirCliente_404() throws Exception {
		mockMvc.perform(delete("/api/v1/cliente/excluirCliente")
			.contentType(MediaType.APPLICATION_JSON)
			.param("id", "0"))
			.andExpect(status().isNotFound()).andDo(print());
	}
	
}