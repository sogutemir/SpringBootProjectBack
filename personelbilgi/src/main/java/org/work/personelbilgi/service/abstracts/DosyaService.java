package org.work.personelbilgi.service.abstracts;
import org.work.personelbilgi.core.result.DataResult;
import org.work.personelbilgi.core.result.Result;
import org.work.personelbilgi.dto.DosyaDTO;
import java.util.List;

public interface DosyaService {
    DataResult<List<DosyaDTO>> getAllDosya();
    DataResult<DosyaDTO> getDosyaById(Long id);
    DataResult<List<DosyaDTO>> getDosyaByPersonelId(Long personelID);
    Result addDosya(DosyaDTO dosyaDTO);
    Result updateDosya(Long id,DosyaDTO dosyaDTO);
    Result deleteDosya(Long id);
}
