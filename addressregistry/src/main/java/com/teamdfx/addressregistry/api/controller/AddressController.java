package com.teamdfx.addressregistry.api.controller;

import com.teamdfx.addressregistry.dto.AddressDTO;
import com.teamdfx.addressregistry.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping("/createAddress")
    private ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO){
        return new ResponseEntity<>(addressService.createAddress(addressDTO), HttpStatus.CREATED);
    }

    @GetMapping("/readAddressById")
    private ResponseEntity<AddressDTO> readAddressById(@RequestParam Long id){
        return new ResponseEntity<>(addressService.readAddressById(id),HttpStatus.OK);
    }

    @GetMapping("/readAddressListByAddressType/{addressType}")
    private ResponseEntity<List<AddressDTO>> readAddressById(@PathVariable String addressType){
        return new ResponseEntity<>(addressService.readAddressListByAddressType(addressType),HttpStatus.OK);
    }

    @GetMapping("/readAllAddresses")
    private ResponseEntity<List<AddressDTO>> readAllAddresses(){
        return new ResponseEntity<>(addressService.readAllAddresses(),HttpStatus.OK);
    }

    @PutMapping("/updateAddressById")
    private ResponseEntity<AddressDTO> updateAddressById(@RequestBody AddressDTO addressDTO){
        return new ResponseEntity<>(addressService.updateAddressById(addressDTO),HttpStatus.OK);
    }

    @PutMapping("/updateAddressWithQuery")
    private void updateAddressWithQuery(@RequestBody AddressDTO addressDTO){
        addressService.updateAddressWithQuery(addressDTO);
    }

    @DeleteMapping("/deleteAddressById/{id}")
    private void deleteAddressById(@PathVariable Long id){
        addressService.deleteAddressById(id);
    }

    @DeleteMapping("/deleteAllAddresses")
    private void deleteAllAddresses(){
        addressService.deleteAllAddresses();
    }
}
