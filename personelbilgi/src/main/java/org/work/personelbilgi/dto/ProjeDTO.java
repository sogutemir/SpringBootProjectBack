package org.work.personelbilgi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjeDTO {
    private String projeAdi;
    private String takımAdi;
    private String projeGorevi;
    private LocalDate projeBaslangicTarihi;
    private LocalDate projeBitisTarihi;
    private boolean projeDevamEdiyor;



    @NotNull
    private Long personelId;
}
