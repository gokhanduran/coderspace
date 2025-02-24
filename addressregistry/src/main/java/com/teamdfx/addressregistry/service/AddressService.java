package com.teamdfx.addressregistry.service;

import com.teamdfx.addressregistry.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO);
    AddressDTO readAddressById(Long id);
    List<AddressDTO> readAddressListByAddressType(String addressType);
    List<AddressDTO> readAllAddresses();
    AddressDTO updateAddressById(AddressDTO addressDTO);
    void updateAddressWithQuery(AddressDTO addressDTO);
    void deleteAddressById(Long id);
    void deleteAllAddresses();
}
