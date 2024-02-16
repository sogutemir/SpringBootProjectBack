package org.work.personelbilgi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(name = "yukleme_tarihi")
    private LocalDateTime yuklemeTarihi;
    @PrePersist
    protected void onCreate() {
        yuklemeTarihi = LocalDateTime.now();
    }

    @Lob
    @Column(name = "dosya_icerigi")
    private byte[] dosya;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personel_id", nullable = false)
    private Personel personel;
}
