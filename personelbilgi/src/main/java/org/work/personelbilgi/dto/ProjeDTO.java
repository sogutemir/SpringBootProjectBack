package org.work.personelbilgi.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjeDTO {
    private Long id;
    private String projeAdi;
    private String takımAdi;
    private String projeGorevi;
    private LocalDate projeBaslangicTarihi;
    private LocalDate projeBitisTarihi;

}
