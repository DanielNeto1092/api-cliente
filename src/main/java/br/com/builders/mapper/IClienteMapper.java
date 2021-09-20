package br.com.builders.mapper;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.builders.dto.ClienteDTO;
import br.com.builders.entity.Cliente;
import br.com.builders.util.DataUtil;
@Mapper(componentModel = "spring")
public interface IClienteMapper {
	
    @Mapping(target = "dataNascimento", source = "dataNascimento", dateFormat = DataUtil.FORMATO_DD_MM_AAAA)
	Cliente toModel(ClienteDTO clienteDTO);
    
    @InheritInverseConfiguration
    ClienteDTO toDto(Cliente cliente);
    
    List<ClienteDTO> listClienteParaListClienteDTO(List<Cliente> clientes);

    
}
