package org.work.personelbilgi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Etkinlik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personel_id", nullable = false)
    private Personel personel;

    @NotEmpty(message = "Etkinlik türü boş olamaz")
    @Size(max = 50, message = "Etkinlik türü en fazla 50 karakter olmalıdır")
    @Column(nullable = false, length = 50)
    private String etkinlikTuru;
}
