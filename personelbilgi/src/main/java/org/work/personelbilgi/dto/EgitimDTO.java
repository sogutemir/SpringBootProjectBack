package org.work.personelbilgi.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EgitimDTO {

    private String egitimTuru;
    private String universiteOkul;
    private LocalDate egitimBaslangicTarihi;
    private LocalDate egitimBitisTarihi;
    private String ekBilgi;
}
