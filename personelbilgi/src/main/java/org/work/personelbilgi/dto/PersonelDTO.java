package org.work.personelbilgi.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonelDTO {
    private Long id;
    private String ad;
    private String soyad;
    private String tcKimlikNo;
    private String akademikUnvan;
    private LocalDate dogumTarihi;
    private String ePosta;
    private String telefon;
    private String acilDurumKisi;
    private String acilDurumKisiTel;
    private String ikametgahAdresi;
    private LocalDate iseGirisTarihi;
    private String sicilNo;
    private String kadro;
    private String unvan;
    private String birim;
    private String gorevi;
    private String personelTuru;
    private String calismaDurumu;
    private String servisKullanimi;
    private String dahiliNumara;
    private String odaNumarasi;
    private byte[] fotograf;
}
