package org.work.personelbilgi.dto;


import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DosyaDTO {
    private String dosyaTuru;
    private String dosyaAdi;
    private String bolum;
    private LocalDateTime yuklemeTarihi;
    private String dosyaBase64;

    @NotNull
    private Long personelId;
}
