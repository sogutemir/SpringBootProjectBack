package org.work.personelbilgi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "personel_eğitim")
public class Egitim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personel_id")
    private Personel personel;

    @NotEmpty(message = "Eğitim türü boş olamaz")
    @Column(name = "egitim_turu")
    private String egitimTuru;

    @NotEmpty(message = "Üniversite/Okul ismi boş olamaz")
    @Column(name = "universite_okul")
    private String universiteOkul;

    @NotEmpty(message = "Eğitim başlangıç tarihi boş olamaz")
    @Past
    @Column(name = "egitim_baslangic_tarihi")
    private LocalDate egitimBaslangicTarihi;

    @NotEmpty(message = "Eğitim bitiş tarihi boş olamaz")
    @PastOrPresent
    @Column(name = "egitim_bitis_tarihi")
    private LocalDate egitimBitisTarihi;

    @Column(name = "ek_bilgi")
    private String ekBilgi;
}
