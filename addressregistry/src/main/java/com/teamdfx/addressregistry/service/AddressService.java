package com.teamdfx.addressregistry.service;

import com.teamdfx.addressregistry.dto.AddressDTO;

import java.util.List;
import java.util.Map;

public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO); // TODO:bu methodu yaz
    AddressDTO getAddressById(Long id);
    List<AddressDTO> getAddressByAddressType(String addressType); //TODO: bu methodu yaz
    List<AddressDTO> getAllAddresses();
    AddressDTO updateAddressById(AddressDTO addressDTO);
    AddressDTO updateAddressPartial(Long id, Map<String,Object> updates);
    void updateAddressWithQuery(AddressDTO addressDTO); //TODO: bu methodu yaz
    void deleteAddressById(Long id);
    void deleteAllAddresses(); //TODO: bu methodu yaz
}
