package org.work.personelbilgi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @Temporal(TemporalType.DATE)
    private String projeBaslangicTarihi;

    @NotEmpty(message = "Proje bitiş tarihi boş olamaz")
    @PastOrPresent
    @Temporal(TemporalType.DATE)
    private String projeBitisTarihi;

}
