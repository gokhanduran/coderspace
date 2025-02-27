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

    /**
     * createAddress ve deleteAllAddresses method controlerini yaz
     *
     * GET—To get a collection or a single resource
     * POST—To create a new resource
     * PUT—To update an existing resource
     * DELETE—To delete a collection or a single resource
     */


    @Autowired
    AddressService addressService;

    //TODO: bu methodu yaz
    // @PostMapping, HTTP POST isteği için kullanılır.
    //@RequestBody: HTTP isteğinin gövdesindeki veriyi belirli bir Java nesnesine dönüştürerek method parametresi olarak kullanır.
    @PostMapping("/createAddress")
    private ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO){
        return new ResponseEntity<>(addressService.createAddress(addressDTO), HttpStatus.CREATED);
    }

    // @GetMapping("/{id}"), belirli bir öğrenciyi ID ile getirir.
    @GetMapping("/getAddressById")
    private ResponseEntity<AddressDTO> getAddressById(@RequestParam Long id){
        return new ResponseEntity<>(addressService.getAddressById(id),HttpStatus.OK);
    }

    //TODO: bu methodu yaz
    // @PathVariable: URL'deki değişken değerleri method parametresi olarak alır.
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
    @PutMapping("/updateAddressById/{id}")
    private ResponseEntity<AddressDTO> updateAddressById(@PathVariable Long id, @RequestBody AddressDTO addressDTO){
        return new ResponseEntity<>(addressService.updateAddressById(id,addressDTO),HttpStatus.OK);
    }

    //TODO: bu methodu yaz
    @PutMapping("/updateAddressWithQuery")
    private void updateAddressWithQuery(@RequestBody AddressDTO addressDTO){
        addressService.updateAddressWithQuery(addressDTO);
    }

    /**
    Bu örnekte, güncellemeler dinamik olarak bir Map<String, Object> olarak alınacak ve ilgili alanlara yansıtılacaktır.

     @PatchMapping("/{id}"): HTTP PATCH isteğini /updateAddressPartial/{id} endpoint'ine yönlendirir.
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

    //TODO: bu methodu yaz
    @DeleteMapping("/deleteAllAddresses")
    private void deleteAllAddresses(){
        addressService.deleteAllAddresses();
    }
}
