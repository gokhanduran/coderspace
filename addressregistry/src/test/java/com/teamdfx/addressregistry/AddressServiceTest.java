package com.teamdfx.addressregistry;


import com.teamdfx.addressregistry.dto.AddressDTO;
import com.teamdfx.addressregistry.mapper.AddressMapper;
import com.teamdfx.addressregistry.model.Address;
import com.teamdfx.addressregistry.repository.AddressRepository;
import com.teamdfx.addressregistry.service.impl.AddressServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressMapper addressMapper;

    @InjectMocks
    private AddressServiceImpl addressService;

    private Address address;
    private AddressDTO addressDTO;

    @BeforeEach
    void setUp() {
        address = getAddress();
        addressDTO = getAddressDTO();
    }

    Address getAddress(){
        address = new Address();
        address.setAddressType("1");
        address.setId(1L);
        address.setCity("İzmir");
        address.setCountry("Türkiye");
        address.setPostalCode("35035");
        address.setStreet("Bornova");
        return address;
    }

    public AddressDTO getAddressDTO() {
        addressDTO = new AddressDTO();
        addressDTO.setId(1L);
        addressDTO.setStreet("Bornova");
        addressDTO.setCountry("Türkiye");
        addressDTO.setAddressType("1");
        addressDTO.setPostalCode("35035");
        addressDTO.setCity("İzmir");
        return addressDTO;
    }

   // @Test
    void createAddress_ShouldReturnSavedAddressDTO() {
        when(addressMapper.fromDTO(addressDTO)).thenReturn(address);
        when(addressRepository.save(address)).thenReturn(address);
        when(addressMapper.toDTO(address)).thenReturn(addressDTO);

     //   AddressDTO result = addressService.createAddress(addressDTO);

     //   assertNotNull(result);
     //   assertEquals(addressDTO.getId(), result.getId());
        verify(addressRepository).save(address);
    }

    @Test
    void getAddressById_ShouldReturnAddressDTO() {
        when(addressRepository.getReferenceById(1L)).thenReturn(address);
        when(addressMapper.toDTO(address)).thenReturn(addressDTO);

        AddressDTO result = addressService.getAddressById(1L);

        assertNotNull(result);
        assertEquals("Bornova", result.getStreet());
    }



    @Test
    void getAllAddresses_ShouldReturnAllAddresses() {
        List<Address> addressList = List.of(address);
        List<AddressDTO> addressDTOList = List.of(addressDTO);

        when(addressRepository.findAll()).thenReturn(addressList);
        when(addressMapper.toDTOList(addressList)).thenReturn(addressDTOList);

        List<AddressDTO> result = addressService.getAllAddresses();

        assertEquals(1, result.size());
        verify(addressRepository).findAll();
    }

//    @Test
    void updateAddressById_ShouldUpdateAndReturnUpdatedDTO() {
        when(addressRepository.getReferenceById(1L)).thenReturn(address);
        when(addressRepository.save(any())).thenReturn(address);
        when(addressMapper.toDTO(any())).thenReturn(addressDTO);

        AddressDTO result = addressService.updateAddressById(1L, addressDTO);

        assertNotNull(result);
        verify(addressRepository).save(any());
    }

    @Test
    void deleteAddressById_ShouldCallDeleteById() {
        doNothing().when(addressRepository).deleteById(1L);

        addressService.deleteAddressById(1L);

        verify(addressRepository).deleteById(1L);
    }

    //@Test
    void deleteAllAddresses_ShouldCallDeleteAll() {
        doNothing().when(addressRepository).deleteAll();

       // addressService.deleteAllAddresses();

        verify(addressRepository).deleteAll();
    }
}

