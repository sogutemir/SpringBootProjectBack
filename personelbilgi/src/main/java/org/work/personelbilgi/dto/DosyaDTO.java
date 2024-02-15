package org.work.personelbilgi.dto;


import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DosyaDTO {
    private String dosyaTuru;
    private String dosyaAdi;
    private String bolum;
    private LocalDate yuklemeTarihi;
    private String dosyaBase64;

    @NotNull
    private Long personelId;
}
