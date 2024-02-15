package org.work.personelbilgi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "personel_dosya")
public class Dosya {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Dosya türü boş olamaz")
    @Column(name = "dosya_turu")
    private String dosyaTuru;

    @NotBlank(message = "Dosya adı boş olamaz")
    @Column(name = "dosya_adi")
    private String dosyaAdi;

    @NotBlank(message = "Bölüm boş olamaz")
    @Column(name = "bolum")
    private String bolum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personel_id", nullable = false)
    private Personel personel;

    @NotEmpty(message = "Yükleme tarihi boş olamaz")
    @Column(name = "yukleme_tarihi")
    private LocalDate yuklemeTarihi;
}
