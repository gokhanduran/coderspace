package com.teamdfx.addressregistry.api.controller;


import com.teamdfx.addressregistry.dto.AddressDTO;
import com.teamdfx.addressregistry.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class AddressController {


    @Autowired
    AddressService addressService;


    @GetMapping("/getAddressById")
    private ResponseEntity<AddressDTO> getAddressById(@RequestParam Long id){
        return new ResponseEntity<>(addressService.getAddressById(id),HttpStatus.OK);
    }

    @GetMapping("/getAllAddresses")
    private ResponseEntity<List<AddressDTO>> getAllAddresses(){
        return new ResponseEntity<>(addressService.getAllAddresses(),HttpStatus.OK);
    }

    @PutMapping("/updateAddressById/{id}")
    private ResponseEntity<AddressDTO> updateAddressById(@PathVariable Long id, @RequestBody AddressDTO addressDTO){
        return new ResponseEntity<>(addressService.updateAddressById(id,addressDTO),HttpStatus.OK);
    }

    @PatchMapping("/updateAddressPartial/{id}")
    private ResponseEntity<AddressDTO> updateAddressPartial(@PathVariable Long id, @RequestBody Map<String,Object> updates) {
        AddressDTO addressDTO = addressService.updateAddressPartial(id,updates);
        return ResponseEntity.ok(addressDTO);
    }

    @DeleteMapping("/deleteAddressById/{id}")
    private void deleteAddressById(@PathVariable Long id){
        addressService.deleteAddressById(id);
    }



}
