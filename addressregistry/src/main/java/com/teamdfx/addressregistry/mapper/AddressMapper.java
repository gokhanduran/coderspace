package com.teamdfx.addressregistry.mapper;

import com.teamdfx.addressregistry.dto.AddressDTO;
import com.teamdfx.addressregistry.model.Address;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AddressMapper {
    private final ModelMapper modelMapper;

    public AddressMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AddressDTO toDTO(Address address){
        AddressDTO addressDTO = new AddressDTO();
        if(Objects.nonNull(address)){
            addressDTO = modelMapper.map(address, AddressDTO.class);
        }
        return  addressDTO;
    }

    public Address fromDTO(AddressDTO addressDTO){
        Address address = new Address();
        if (Objects.nonNull(addressDTO)){
            address = modelMapper.map(addressDTO, Address.class);
        }
        return address;
    }

    public List<AddressDTO> toDTOList(List<Address> addressList){
        return addressList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<Address> fromDTOList(List<AddressDTO> addressDTOList){
        return addressDTOList.stream().map(this::fromDTO).collect(Collectors.toList());
    }
}
