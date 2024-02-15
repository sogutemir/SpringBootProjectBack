package org.work.personelbilgi.service.abstracts;

import org.work.personelbilgi.core.result.DataResult;
import org.work.personelbilgi.core.result.Result;
import org.work.personelbilgi.dto.DeneyimDTO;

import java.util.List;

public interface DeneyimService {
    DataResult<List<DeneyimDTO>> getAllDeneyim();
    DataResult<DeneyimDTO> getDeneyimById(Long id);
    DataResult<List<DeneyimDTO>> getDeneyimByPersonelId(Long personelId);
    Result addDeneyim(DeneyimDTO DeneyimDTO);
    Result updateDeneyim(Long id,DeneyimDTO DeneyimDTO);
    Result deleteDeneyim(Long id);

}
