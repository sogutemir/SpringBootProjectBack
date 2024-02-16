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
@Table(name = "personel_proje")
public class Proje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Proje adı boş olamaz")
    @Column(name = "proje_adi")
    private String projeAdi;

    @NotEmpty(message = "Takım adı boş olamaz")
    @Column(name = "takim_adi")
    private String takımAdi;

    @NotEmpty(message = "Proje görevi boş olamaz")
    @Column(name = "proje_gorevi")
    private String projeGorevi;

    @Past(message = "Proje başlangıç tarihi geçmişte olmalı")
    @Column(name = "proje_baslangic_tarihi")
    private LocalDate projeBaslangicTarihi;

    @Column(name = "proje_devam_ediyor")
    private Boolean projeDevamEdiyor;

    @PastOrPresent(message = "Proje bitiş tarihi bugün veya geçmişte olmalı")
    @Column(name = "proje_bitis_tarihi")
    private LocalDate projeBitisTarihi;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personel_id", nullable = false)
    private Personel personel;
}
