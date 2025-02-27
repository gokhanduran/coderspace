package com.teamdfx.addressregistry.mapper;

import com.teamdfx.addressregistry.dto.AddressDTO;
import com.teamdfx.addressregistry.model.Address;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

//@Component ile işaretlenen sınıflar otomatik olarak Spring konteynerine eklenir.
//@Component, Spring Framework’te bir sınıfın Spring Container tarafından yönetilen bir Bean (bileşen) olmasını sağlar. Yani, Spring bu sınıfı otomatik olarak algılar
// ve uygulama konteynerine dahil eder.
@Component
public class AddressMapper {

    /**
     * Model maapperdan ModelMapperConfig sınıfının içinde bahset.
     */
    private final ModelMapper modelMapper;

    public AddressMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AddressDTO toDTO(Address address){
        AddressDTO addressDTO = new AddressDTO();
        if(Objects.nonNull(address)){
            addressDTO = modelMapper.map(address, AddressDTO.class);
        }
        return  addressDTO;
    }

    public Address fromDTO(AddressDTO addressDTO){
        Address address = new Address();
        if (Objects.nonNull(addressDTO)){
            address = modelMapper.map(addressDTO, Address.class);
        }
        return address;
    }

    /**
     * Bu ifade, addressList isimli bir listeyi stream kullanarak dolaşır, her öğeyi toDTO metoduna dönüştürür ve sonuçları yeni bir List içinde toplar.
     *
     * addressList.stream()
     *
     stream() → Listeyi akışa çevirir.
     map(this::toDTO) → Her Address nesnesini AddressDTO nesnesine dönüştürür.
     collect(Collectors.toList()) → Dönüştürülen nesneleri liste olarak toplar.
     *
     * Dönüştürülen öğeleri bir liste (List) olarak toplar ve döndürür.
     */
    public List<AddressDTO> toDTOList(List<Address> addressList){
        return addressList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<Address> fromDTOList(List<AddressDTO> addressDTOList){
        return addressDTOList.stream().map(this::fromDTO).collect(Collectors.toList());
    }
}
