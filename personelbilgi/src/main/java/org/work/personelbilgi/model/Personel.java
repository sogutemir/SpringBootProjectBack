package org.work.personelbilgi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.work.personelbilgi.validation.tcKimlikNo.ValidTCKimlikNo;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"egitimler", "projeler", "etkinlikler"})
@NoArgsConstructor
@AllArgsConstructor
public class Personel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ad boş bırakılamaz")
    @Size(min = 2, max = 50, message = "Ad en az 2 en fazla 50 karakter olmalıdır")
    private String ad;

    @NotBlank(message = "Soyad boş bırakılamaz")
    @Size(min = 2, max = 60, message = "Soyad en az 2 en fazla 60 karakter olmalıdır")
    private String soyad;

    @NotBlank(message = "T.C. Kimlik Numarası boş bırakılamaz")
    @Size(min = 11, max = 11, message = "T.C. Kimlik Numarası 11 karakter olmalıdır")
    @ValidTCKimlikNo
    private String tcKimlikNo;

    @Size(max = 50, message = "Akademik Ünvan en fazla 50 karakter olmalıdır")
    private String akademikUnvan;

    @Past
    private LocalDate dogumTarihi;

    @NotBlank(message = "E-posta boş olamaz")
    @Email
    private String ePosta;

    @NotBlank(message = "Telefon numarası gereklidir")
    private String telefon;

    @NotBlank(message = "Acil durumda ulaşılacak kişi gereklidir")
    private String acilDurumKisi;

    @NotBlank(message = "Acil durumda ulaşılacak kişi telefonu gereklidir")
    private String acilDurumKisiTel;

    @NotBlank(message = "İkametgah adresi gereklidir")
    private String ikametgahAdresi;

    @PastOrPresent
    private LocalDate iseGirisTarihi;

    private String sicilNo;

    @NotBlank(message = "Kadro bilgisi gereklidir")
    private String kadro;

    @NotBlank(message = "Unvan bilgisi gereklidir")
    private String unvan;

    @NotBlank(message = "Birim bilgisi gereklidir")
    private String birim;

    @NotBlank(message = "Görev bilgisi gereklidir")
    private String gorevi;

    @NotBlank(message = "Personel türü bilgisi gereklidir")
    private String personelTuru;

    @NotBlank(message = "Çalışma durumu bilgisi gereklidir")
    private String calismaDurumu;

    private String servisKullanimi;

    private String dahiliNumara;

    private String odaNumarasi;

    @OneToMany(mappedBy = "personel", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Egitim> egitimler;

    @OneToMany(mappedBy = "personel", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Proje> projeler;

    @OneToMany(mappedBy = "personel", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Etkinlik> etkinlikler;
}