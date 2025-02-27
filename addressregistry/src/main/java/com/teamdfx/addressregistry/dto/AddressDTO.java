package com.teamdfx.addressregistry.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Java'da DTO (Data Transfer Object), verileri bir katmandan diğerine taşımak için kullanılan basit bir nesnedir.
 * DTO'lar genellikle veritabanı katmanı ile servis katmanı veya servis katmanı ile istemci (client) katmanı arasında veri aktarımını sağlamak için kullanılır.
 */

@Getter
@Setter
public class AddressDTO {
    private Long id;
    private String street;
    private String city;
    private String postalCode;
    private String country;
    private String addressType;

}
