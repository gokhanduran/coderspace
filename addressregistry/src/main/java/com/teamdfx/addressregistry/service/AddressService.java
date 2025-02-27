package com.teamdfx.addressregistry.service;

import com.teamdfx.addressregistry.dto.AddressDTO;
import com.teamdfx.addressregistry.model.Address;

import java.util.List;
import java.util.Map;

public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO);
    AddressDTO getAddressById(Long id);
    List<AddressDTO> getAllAddresses();
    AddressDTO updateAddressById(Long id,AddressDTO addressDTO);
    AddressDTO updateAddressPartial(Long id, Map<String,Object> updates);
    void deleteAddressById(Long id);

}
