package com.teamdfx.addressregistry.service.impl;

import com.teamdfx.addressregistry.dto.AddressDTO;
import com.teamdfx.addressregistry.mapper.AddressMapper;
import com.teamdfx.addressregistry.model.Address;
import com.teamdfx.addressregistry.repository.AddressRepository;
import com.teamdfx.addressregistry.service.AddressService;
import com.teamdfx.addressregistry.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// @Service, bu sınıfın bir servis bileşeni olduğunu belirtir.
@Service
public class AddressServiceImpl implements AddressService {

    /**
     *
     * @Autowired, Spring Dependency Injection (Bağımlılık Enjeksiyonu) mekanizmasını kullanarak bir sınıfa otomatik olarak bağımlılık enjekte etmek için kullanılır.
     * Spring, @Autowired annotation’ı sayesinde uygun bean’leri bulur ve enjekte eder, böylece manuel nesne oluşturma (new anahtar kelimesi) ihtiyacını ortadan kaldırır.
     *
     * @Autowired, bağımlılıkları otomatik olarak enjekte etmek için kullanılır.
    Field Injection (Önerilmez)
    Field Injection doğrudan sınıf değişkenlerine bağımlılık enjekte eder, bu da test yazarken mock nesneleri yerleştirmeyi zorlaştırır.
   Spring çerçevesine çok bağımlı hale gelir, bağımsız çalışabilirliği zorlaşır.

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AddressMapper addressMapper;**/

    /** Constructor Injection (Önerilen Yöntem)
     * Daha güvenlidir (bağımlılıklar final olabilir).
     * Daha okunaklıdır (test edilebilirliği artırır).
     *@RequiredArgsConstructor ile de kullanılabilir.
     **/
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO){
        return addressMapper.toDTO(addressRepository.save(addressMapper.fromDTO(addressDTO)));
    }

    @Override
    public AddressDTO getAddressById(Long id) {
        AddressDTO addressDTO = addressMapper.toDTO(addressRepository.getReferenceById(id));
        addressDTO.setAddressType(decideAddressType(addressDTO.getAddressType()));
        return addressDTO;
    }

    @Override
    public List<AddressDTO> getAddressByAddressType(String addressType) {
        return addressMapper.toDTOList(addressRepository.findByAddressType(addressType)); //TODO: repoyu cagir
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
    public List<AddressDTO> getAllAddresses() {
        return addressMapper.toDTOList(addressRepository.findAll());
    }

    @Override
    public AddressDTO updateAddressById(Long id,AddressDTO addressDTO) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if(addressOptional.isPresent()){
            Address address = addressOptional.get();
            address.setAddressType(addressDTO.getAddressType());
            address.setCity(addressDTO.getCity());
            address.setStreet(addressDTO.getStreet());
            address.setCountry(addressDTO.getCountry());
            address.setPostalCode(addressDTO.getPostalCode());
            addressDTO = addressMapper.toDTO(addressRepository.save(address));
        }
        return addressDTO;
    }

    /**
     *
     *ReflectionUtils: Güncellenecek alanın adını (key) Address sınıfında arar ve varsa değerini (value) günceller.
     *  addressMapper.toDTO(addressRepository.save(address)) güncellenen değerleri veri tabanına kaydeder.
     */
    @Override
    public AddressDTO updateAddressPartial(Long id, Map<String, Object> updates) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if(addressOptional.isPresent()){
            Address address = addressOptional.get();
            updates.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Address.class,key);
                if(field != null) {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field,address,value);
                }
            });
            return addressMapper.toDTO(addressRepository.save(address));
        } else {
            throw new RuntimeException("Address not found with id " + id);
        }
    }

    @Override
    public void updateAddressWithQuery(AddressDTO addressDTO) {
        Address address = addressMapper.fromDTO(addressDTO);
        addressRepository.updateAddressWithQuery(address.getId(),address.getStreet(),address.getCity(),address.getPostalCode(),address.getCountry(),address.getAddressType());
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
