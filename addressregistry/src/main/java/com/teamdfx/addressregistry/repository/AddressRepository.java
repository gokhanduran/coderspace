package com.teamdfx.addressregistry.repository;

import com.teamdfx.addressregistry.dto.AddressDTO;
import com.teamdfx.addressregistry.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// @Repository, bu arayüzün bir veri erişim katmanı bileşeni olduğunu belirtir.
@Repository
/**
 * @Transactional, Spring’in işlemsel (transactional) yönetimini sağlamak için kullanılan bir annotation’dır.
 * Bu annotation sayesinde bir metot veya sınıf içindeki tüm işlemler ya tamamen başarılı olur ya da hata durumunda geri alınır (rollback yapılır).
 *avantajları
 * Veri bütünlüğünü korur: Tüm işlemler ya tam olarak gerçekleştirilir ya da tamamen geri alınır.
 * Bağlantı yönetimini kolaylaştırır: Spring, otomatik olarak veritabanı bağlantılarını açar ve kapatır.
 * Özelleştirilebilir hata yönetimi: Belirli istisnalarda rollback yapılmasını sağlayabiliriz.
 *
 *  Metot seviyesinde veya sınıf seviyesinde kullanılabilir.
 *  Bir sınıfa @Transactional eklendiğinde, tüm metodlar varsayılan olarak işlemsel hale gelir.
 */
@Transactional
public interface AddressRepository extends JpaRepository<Address, AddressDTO> {
    Address findById(Long id);
    List<Address> findByAddressType(String addressType); //TODO: bunu yaz
    List<Address> findAll();

    @Modifying
    @Query("update Address a set a.street = :street, a.city = :city, a.state = :state , a.postalCode = :postalCode, a.country = :country, a.addressType = :addressType where a.id= :id")
    void updateAddressWithQuery(@Param(value = "id") Long id,
                                       @Param(value = "street") String street,
                                       @Param(value = "city") String city,
                                       @Param(value = "state") String state,
                                       @Param(value = "postalCode") String postalCode,
                                       @Param(value = "country") String country,
                                       @Param(value = "addressType") String addressType);

    void deleteById(Long id);

}
