package com.teamdfx.addressregistry.service.impl;

import com.teamdfx.addressregistry.dto.AddressDTO;
import com.teamdfx.addressregistry.mapper.AddressMapper;
import com.teamdfx.addressregistry.model.Address;
import com.teamdfx.addressregistry.repository.AddressRepository;
import com.teamdfx.addressregistry.service.AddressService;
import com.teamdfx.addressregistry.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AddressMapper addressMapper;

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO){
        return addressMapper.toDTO(addressRepository.save(addressMapper.fromDTO(addressDTO)));
    }

    @Override
    public AddressDTO readAddressById(Long id) {
        AddressDTO addressDTO = addressMapper.toDTO(addressRepository.findById(id));
        addressDTO.setAddressType(decideAddressType(addressDTO.getAddressType()));
        return addressDTO;
    }

    @Override
    public List<AddressDTO> readAddressListByAddressType(String addressType) {
        return addressMapper.toDTOList(addressRepository.findByAddressType(addressType));
    }

    private String decideAddressType(String addressType){
        if(addressType.equals("1")){
            return Constants.ADDRESS_TYPE_1;
        } else if(addressType.equals("2")){
            return Constants.ADDRESS_TYPE_2;
        }else {
            return Constants.ADDRESS_TYPE_OTHER;
        }
    }

    @Override
    public List<AddressDTO> readAllAddresses() {
        return addressMapper.toDTOList(addressRepository.findAll());
    }

    @Override
    public AddressDTO updateAddressById(AddressDTO addressDTO) {
        Address address = addressRepository.findById(addressDTO.getId());
        address.setAddressType(addressDTO.getAddressType());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setStreet(addressDTO.getStreet());
        address.setCountry(addressDTO.getCountry());
        address.setPostalCode(addressDTO.getPostalCode());
        addressDTO = addressMapper.toDTO(addressRepository.save(address));
        return addressDTO;
    }

    @Override
    public void updateAddressWithQuery(AddressDTO addressDTO) {
        Address address = addressMapper.fromDTO(addressDTO);
        addressRepository.updateAddressWithQuery(address.getId(),address.getStreet(),address.getCity(),address.getState(),address.getPostalCode(),address.getCountry(),address.getAddressType());
    }

    @Override
    public void deleteAddressById(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public void deleteAllAddresses() {
        addressRepository.deleteAll();
    }
}
