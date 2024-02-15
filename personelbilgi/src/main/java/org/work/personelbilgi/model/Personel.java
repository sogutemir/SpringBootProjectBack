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
@Table(name = "personel")
public class Personel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ad boş bırakılamaz")
    @Size(min = 2, max = 50, message = "Ad en az 2 en fazla 50 karakter olmalıdır")
    @Column(name = "ad")
    private String ad;

    @NotBlank(message = "Soyad boş bırakılamaz")
    @Size(min = 2, max = 60, message = "Soyad en az 2 en fazla 60 karakter olmalıdır")
    @Column(name = "soyad")
    private String soyad;

    @NotBlank(message = "T.C. Kimlik Numarası boş bırakılamaz")
    @Size(min = 11, max = 11, message = "T.C. Kimlik Numarası 11 karakter olmalıdır")
    @Column(name = "tc_kimlik_no")
    @ValidTCKimlikNo
    private String tcKimlikNo;

    @Size(max = 50, message = "Akademik Ünvan en fazla 50 karakter olmalıdır")
    @Column(name = "akademik_unvan")
    private String akademikUnvan;

    @Past
    @Column(name = "dogum_tarihi")
    private LocalDate dogumTarihi;

    @NotBlank(message = "E-posta boş olamaz")
    @Column(name = "e_posta")
    @Email
    private String ePosta;

    @NotBlank(message = "Telefon numarası gereklidir")
    @Column(name = "telefon")
    private String telefon;

    @NotBlank(message = "Acil durumda ulaşılacak kişi gereklidir")
    @Column(name = "acil_durum_kisi")
    private String acilDurumKisi;

    @NotBlank(message = "Acil durumda ulaşılacak kişi telefonu gereklidir")
    @Column(name = "acil_durum_kisi_tel")
    private String acilDurumKisiTel;

    @NotBlank(message = "İkametgah adresi gereklidir")
    @Column(name = "ikametgah_adresi")
    private String ikametgahAdresi;

    @PastOrPresent
    @Column(name = "ise_giris_tarihi")
    private LocalDate iseGirisTarihi;

    @NotBlank(message = "Sicil numarası gereklidir")
    private String sicilNo;

    @NotBlank(message = "Kadro bilgisi gereklidir")
    @Column(name = "kadro")
    private String kadro;

    @NotBlank(message = "Unvan bilgisi gereklidir")
    @Column(name = "unvan")
    private String unvan;

    @NotBlank(message = "Birim bilgisi gereklidir")
    @Column(name = "birim")
    private String birim;

    @NotBlank(message = "Görev bilgisi gereklidir")
    @Column(name = "gorevi")
    private String gorevi;

    @NotBlank(message = "Personel türü bilgisi gereklidir")
    @Column(name = "personel_turu")
    private String personelTuru;

    @NotBlank(message = "Çalışma durumu bilgisi gereklidir")
    @Column(name = "calisma_durumu")
    private String calismaDurumu;

    @Column(name = "servis_kullanimi")
    private String servisKullanimi;

    @Column(name = "dahili_numara")
    private String dahiliNumara;

    @Column(name = "oda_numarasi")
    private String odaNumarasi;

    @OneToMany(mappedBy = "personel", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Egitim> egitimler;

    @OneToMany(mappedBy = "personel", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Proje> projeler;

    @OneToMany(mappedBy = "personel", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Etkinlik> etkinlikler;

    @OneToMany(mappedBy = "personel", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dosya> dosyalar;
}
