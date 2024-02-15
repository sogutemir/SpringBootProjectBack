package org.work.personelbilgi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeneyimDTO {

    private String kurumAdi;
    private String calismaSekli;
    private LocalDate isBaslangicTarihi;
    private LocalDate isBitisTarihi;
    private String calismaPosizyonu;
    private String ekBilgi;
    
    @NotNull
    private Long personelId;
}
