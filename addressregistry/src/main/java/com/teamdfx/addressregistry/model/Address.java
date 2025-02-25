package com.teamdfx.addressregistry.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// Getter metodları, bir değişkenin değerini dış dünyaya açar. Örneğin, getStreet() metodu name değişkeninin değerini döndürür.
// Setter metodları, bir değişkenin değerini dışarıdan değiştirmek için kullanılır. Örneğin, setStreet(String name) metodu name değişkenine yeni bir değer atar.
//Veri kapsülleme (Encapsulation) sağlanır, doğrudan değişkenlere erişmek yerine kontrollü erişim yapılır.
//Kodun yönetilebilirliği artar, gerektiğinde değişkenlerin değerini işlerken özel mantık uygulanabilir.
// @Table annotation'ı, bir JPA (Java Persistence API) varlığının (entity) hangi veritabanı tablosuna karşılık geldiğini belirtmek için kullanılır.
@Getter
@Setter
@Entity
@Table(name="ADDRESS")
public class Address {
    // @Id, bu alanın birincil anahtar olduğunu belirtir.
    // @GeneratedValue, otomatik olarak artırılan bir ID sağlar.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Column veri tabanında tabloda bulunan ismi
    @Column(name = "id")
    private Long id;
    @Column(name = "street")
    private String street;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "country")
    private String country;
    @Column(name = "address_type")
    private String addressType;
}
