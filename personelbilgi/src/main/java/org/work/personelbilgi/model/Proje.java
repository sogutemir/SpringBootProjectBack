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
public class Proje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personel_id", nullable = false)
    private Personel personel;

    @NotEmpty(message = "Proje adı boş olamaz")
    private String projeAdi;

    @NotEmpty(message = "Proje başlangıç tarihi boş olamaz")
    @Past
    private LocalDate projeBaslangicTarihi;

    @NotEmpty(message = "Proje bitiş tarihi boş olamaz")
    @PastOrPresent
    private LocalDate projeBitisTarihi;
}
