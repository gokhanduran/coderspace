package com.teamdfx.addressregistry.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Spring Boot’ta @Configuration, konfigürasyon sınıflarını belirtmek için kullanılan bir anotasyondur.
// Uygulamanın başlatılması sırasında Spring Container tarafından okunur ve içerisinde tanımlanan @Bean anotasyonlu metotlar sayesinde Spring IoC (Inversion of Control) Container’a
// bileşenler eklenir.
//Neden Kullanılır?
//Spring Bean Tanımlamak İçin : @Configuration kullanarak özel bileşenler oluşturabilir ve Spring’in yönetmesini sağlayabilirsin.
//Kodun Modüler Olmasını Sağlar : Tüm konfigürasyonları tek bir yerde yöneterek kodun daha okunabilir ve bakımı kolay hale gelir.
//XML Konfigürasyonunun Alternatifidir : Önceden Spring konfigürasyonu XML ile yapılırken, @Configuration sayesinde tamamen Java tabanlı konfigürasyon yapılabilir.
@Configuration
public class ModelMapperConfig {

    /**
     * ModelMapper, nesneler arasında otomatik dönüşüm yapmayı sağlayan bir Java kütüphanesidir.
     * Spring Boot projelerinde, özellikle DTO (Data Transfer Object) ve Entity sınıfları arasında veri dönüştürmek için yaygın olarak kullanılır.
     *
     *
     * Kod Tekrarını Önler: Manuel olarak getter-setter ile veri kopyalamak yerine, ModelMapper bunu otomatik yapar.
     * Okunabilirliği Artırır: Dönüşüm işlemi sadeleştiği için kod daha temiz olur.
     * Hata Riskini Azaltır: Yanlış alan eşleştirme gibi manuel hataların önüne geçer.
     * Esneklik Sağlar: Özel eşleştirme (custom mapping) kuralları ile ihtiyaca göre dönüşüm sağlanabilir.
     *
     *
     *
     * ModelMapper kütüphanesinde kullanılan alan eşleştirme (mapping) stratejisini STRICT (katı) moda ayarlar.
     * ModelMapper, nesneler arasında kolayca dönüşüm yapmamızı sağlayan bir Java kütüphanesidir. Genellikle DTO (Data Transfer Object) ve Entity dönüşümlerinde kullanılır.
     *
     * STRICT Modu Ne Yapar?
     * Alan isimleri ve türleri birebir eşleşmelidir.
     * Örneğin, source nesnesindeki bir alan, destination nesnesinde aynı isimde ve aynı türde yoksa eşleştirme yapılmaz.
     * Daha güvenilir bir dönüşüm sağlar ve yanlış eşleştirmeleri önler.
     *
     *
     * matchingStrategies.LOOSE (Gevşek)
     * Alan isimleri tam birebir eşleşmek zorunda değildir.
     * Tür dönüşümlerini otomatik yapabilir (örneğin Integer → String).
     * Alan isimlerinde küçük büyük harf farkı dikkate alınmaz.
     * Daha esnek bir eşleme yapar, ancak yanlış eşleşmelerin önüne geçmek için dikkatli olunmalıdır.
     *  fullName ve name gibi benzer alanları otomatik eşlemek istiyorsanız kullanabilirsiniz, hata vermez.
     *
     *
     * standard..
     * STRICT ve LOOSE arasında bir dengededir.
     * Alan isimleri mümkün olduğunca birebir eşleşmeli ancak küçük farkları tolere eder.
     * Basit tip dönüşümlerini destekleyebilir (örneğin, int → Integer).
     * LOOSE kadar esnek değildir, ancak STRICT kadar katı da değildir.
     * **/

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}

