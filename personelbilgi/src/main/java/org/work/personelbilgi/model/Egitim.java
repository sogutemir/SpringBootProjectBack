package org.work.personelbilgi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Egitim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personel_id", nullable = false)
    private Personel personel;

    @NotEmpty(message = "Eğitim türü boş olamaz")
    private String egitimTuru;

    @NotEmpty(message = "Üniversite/Okul ismi boş olamaz")
    private String universiteOkul;
}
