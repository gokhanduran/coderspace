package com.teamdfx.addressregistry.api.controller;


import com.teamdfx.addressregistry.dto.AddressDTO;
import com.teamdfx.addressregistry.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// @RestController, bu sınıfın bir REST API denetleyicisi olduğunu belirtir.
@RestController
public class AddressController {



    @Autowired
    AddressService addressService;

    // @PostMapping, HTTP POST isteği için kullanılır.
    @PostMapping("/createAddress")
    private ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO){
        return new ResponseEntity<>(addressService.createAddress(addressDTO), HttpStatus.CREATED);
    }

    // @GetMapping("/{id}"), belirli bir öğrenciyi ID ile getirir.
    @GetMapping("/getAddressById")
    private ResponseEntity<AddressDTO> getAddressById(@RequestParam Long id){
        return new ResponseEntity<>(addressService.getAddressById(id),HttpStatus.OK);
    }

    @GetMapping("/getAddressByAddressType/{addressType}")
    private ResponseEntity<List<AddressDTO>> getAddressByAddressType(@PathVariable String addressType){
        return new ResponseEntity<>(addressService.getAddressByAddressType(addressType),HttpStatus.OK);
    }

    // @GetMapping, HTTP GET isteği için kullanılır.
    @GetMapping("/getAllAddresses")
    private ResponseEntity<List<AddressDTO>> getAllAddresses(){
        return new ResponseEntity<>(addressService.getAllAddresses(),HttpStatus.OK);
    }

    // @GetMapping, HTTP PUT isteği için kullanılır.
    @PutMapping("/updateAddressById")
    private ResponseEntity<AddressDTO> updateAddressById(@RequestBody AddressDTO addressDTO){
        return new ResponseEntity<>(addressService.updateAddressById(addressDTO),HttpStatus.OK);
    }

    @PutMapping("/updateAddressWithQuery")
    private void updateAddressWithQuery(@RequestBody AddressDTO addressDTO){
        addressService.updateAddressWithQuery(addressDTO);
    }

    /**
    Bu örnekte, güncellemeler dinamik olarak bir Map<String, Object> olarak alınacak ve ilgili alanlara yansıtılacaktır.

     @PatchMapping("/{id}"): HTTP PATCH isteğini /students/{id} endpoint'ine yönlendirir.
     @RequestBody Map<String, Object> updates: İstek gövdesinde gönderilen kısmi güncellemeleri bir Map olarak alır.
     */
    @PatchMapping("/updateAddressPartial/{id}")
    private ResponseEntity<AddressDTO> updateAddressPartial(@PathVariable Long id, @RequestBody Map<String,Object> updates) {
        AddressDTO addressDTO = addressService.updateAddressPartial(id,updates);
        return ResponseEntity.ok(addressDTO);
    }

    // @DeleteMapping, HTTP DELETE isteği için kullanılır.
    @DeleteMapping("/deleteAddressById/{id}")
    private void deleteAddressById(@PathVariable Long id){
        addressService.deleteAddressById(id);
    }

    @DeleteMapping("/deleteAllAddresses")
    private void deleteAllAddresses(){
        addressService.deleteAllAddresses();
    }
}
