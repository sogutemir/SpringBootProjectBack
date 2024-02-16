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
@Table(name = "personel_deneyim")
public class Deneyim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Kurum adi boş olamaz")
    @Column(name = "kurum_adi")
    private String kurumAdi;

    @NotEmpty(message = "Calisma Sekli Bos Olamaz")
    @Column(name = "calism_Sekli")
    private String calismaSekli;

    @Past(message = "İş başlangıç tarihi geçmişte olmalı")
    @Column(name = "is_baslangic_tarihi")
    private LocalDate isBaslangicTarihi;

    @PastOrPresent(message = "İş bitiş tarihi bugün veya geçmişte olmalı")
    @Column(name = "is_bitis_tarihi")
    private LocalDate isBitisTarihi;

    @Column(name = "calisma_pozisyonu")
    private String calismaPosizyonu;

    @Column(name = "ek_bilgi")
    private String ekBilgi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personel_id", nullable = false)
    private Personel personel;
}
