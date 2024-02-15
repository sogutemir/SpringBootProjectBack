package org.work.personelbilgi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.net.URL;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "personel_etkinlik")
public class Etkinlik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personel_id")
    private Personel personel;

    @NotEmpty(message = "Etkinlik türü boş olamaz")
    @Size(max = 50, message = "Etkinlik türü en fazla 50 karakter olmalıdır")
    @Column(name = "etkinlik_turu")
    private String etkinlikTuru;

    @Size(max = 255, message = "Açıklama 255 karakteri geçmemelidir")
    @Column(name = "aciklama")
    private String aciklama;

    // Regex ile URL doğrulama
    @Pattern(regexp = "^(http://www\\.|https://www\\.|http://|https://)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(/.*)?$",
            message = "Geçersiz URL")
    @Column(name = "link")
    private String link;

    @Column(name = "ek")
    private String ek;

    @Column(name = "yukleme_Tarihi")
    private LocalDate yuklemeTarihi;
}
