package org.work.personelbilgi.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EtkinlikDTO {

    private Long id;
    private String etkinlikTuru;
    private String aciklama;
    private String link;
    private String ek;
    private LocalDate yuklemeTarihi;
}