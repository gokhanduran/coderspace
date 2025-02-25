package com.teamdfx.addressregistry.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @Entity anotasyonu, Java Persistence API (JPA) kullanılarak yazılmış uygulamalarda, bir sınıfın veritabanındaki bir tabloya karşılık geldiğini belirtmek için kullanılır.
 * Yani, bu anotasyon ile işaretlenen sınıf, veritabanında bir entity (varlık) olarak kabul edilir ve ORM (Object-Relational Mapping) mekanizmaları tarafından yönetilir.
 */
@Entity
// Getter metodları, bir değişkenin değerini dış dünyaya açar. Örneğin, getStreet() metodu name değişkeninin değerini döndürür.
// Setter metodları, bir değişkenin değerini dışarıdan değiştirmek için kullanılır. Örneğin, setStreet(String name) metodu name değişkenine yeni bir değer atar.
//Veri kapsülleme (Encapsulation) sağlanır, doğrudan değişkenlere erişmek yerine kontrollü erişim yapılır.
//Kodun yönetilebilirliği artar, gerektiğinde değişkenlerin değerini işlerken özel mantık uygulanabilir.
@Getter
@Setter
// @Table annotation'ı, bir JPA (Java Persistence API) varlığının (entity) hangi veritabanı tablosuna karşılık geldiğini belirtmek için kullanılır.
@Table(name="ADDRESS")
public class Address {
    // @Id, bu alanın birincil anahtar olduğunu belirtir.
    @Id
    // @GeneratedValue, otomatik olarak artırılan bir ID sağlar.
    //Bu strateji, identity column (genellikle AUTO_INCREMENT veya IDENTITY özelliği taşıyan sütun) kullanımını belirtir.
    // Bu durumda, veritabanı kendisi yeni kayıt için benzersiz bir değer üretir.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column anotasyonu, bir entity sınıfındaki alanların veritabanı sütunlarıyla olan eşleşmesini detaylandırmak için kullanılır.
    //Sütun adını, null değeri alabilirliğini, benzersiz olup olmadığını, uzunluk gibi özellikleri belirleyerek veritabanı şemasını daha kontrollü ve optimize bir şekilde oluşturmanızı sağlar.
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
