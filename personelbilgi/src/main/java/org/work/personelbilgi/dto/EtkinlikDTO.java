package org.work.personelbilgi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EtkinlikDTO {

    private String etkinlikTuru;
    private String aciklama;
    private String link;
    private String ek64;
    private LocalDate yuklemeTarihi;

    @NotNull
    private Long personelId;
}