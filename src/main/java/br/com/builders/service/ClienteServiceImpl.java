package br.com.builders.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.builders.dto.ClienteDTO;
import br.com.builders.entity.Cliente;
import br.com.builders.mapper.IClienteMapper;
import br.com.builders.repository.ClienteRepository;
import br.com.builders.util.DataUtil;

@Service
public class ClienteServiceImpl implements IClienteService{
	
	@Autowired
	private  ClienteRepository clienteRepository;

	@Autowired
	private IClienteMapper clienteMapper;
	
	@Override
	public List<ClienteDTO> listarTodosClientes() {
		return  clienteMapper.listClienteParaListClienteDTO(clienteRepository.findAll());

	}
	
	@Override
	public List<ClienteDTO> listarTodosClientes(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Cliente> resultado = clienteRepository.findAll(paging);
        if(resultado.hasContent()) {
            return clienteMapper.listClienteParaListClienteDTO(resultado.getContent());
        } else {
            return new ArrayList<ClienteDTO>();
        }
	}

	@Override
	public ClienteDTO buscarCliente(Long id) {
		return clienteMapper.toDto(clienteRepository.findById(id).orElse(null));
	}

	@Override
	public List<ClienteDTO> buscarCliente(String nome) {
		return clienteMapper.listClienteParaListClienteDTO(clienteRepository.findByName(nome));
	}
	
	@Override
	public ClienteDTO buscarClientePorCpf(String cpf) {
		return clienteMapper.toDto(clienteRepository.findByCpf(cpf));
	}

	@Override
	public ClienteDTO alterarCliente(ClienteDTO clienteDTO) {
		if(clienteDTO != null) {
			Cliente cliente = clienteRepository.findByCpf(clienteMapper.toModel(clienteDTO).getCpf());
			if(cliente != null) {
				Cliente novoCliente = cliente;
				novoCliente.setNome(clienteDTO.getNome());
				novoCliente.setCpf(clienteDTO.getCpf());
				novoCliente.setDataNascimento(DataUtil.converterStringToDate(clienteDTO.getDataNascimento()));
				return clienteMapper.toDto(clienteRepository.save(novoCliente));
			}else {
				return null;
			}
		}
		return null;
	}
	
	@Override
	public ClienteDTO criarCliente(ClienteDTO clienteDTO) {
		if(clienteDTO != null) {
			Cliente cliente = clienteRepository.findByCpf(clienteMapper.toModel(clienteDTO).getCpf());
			if(cliente != null) {
				return null;
			}else {
				return clienteMapper.toDto(clienteRepository.save(clienteMapper.toModel(clienteDTO)));
			}
		}
		return null;
	}
	
	@Override
	public boolean excluirCliente(Long id){
		if(clienteRepository.existsById(id)) {
			clienteRepository.deleteById(id);
			return true;
	    } else {
	    	return false;
	    }
	}
}
