package org.work.personelbilgi.service.abstracts;

import org.work.personelbilgi.core.result.DataResult;
import org.work.personelbilgi.core.result.Result;
import org.work.personelbilgi.dto.EtkinlikDTO;

import java.util.List;

public interface EtkinlikService {
    DataResult<List<EtkinlikDTO>> getAllEtkinlik();
    DataResult<EtkinlikDTO> getEtkinlikById(Long id);
    DataResult<List<EtkinlikDTO>> getEtkinlikByPersonelId(Long personelId);
    Result addEtkinlik(EtkinlikDTO EtkinlikDTO);
    Result updateEtkinlik(Long id,EtkinlikDTO EtkinlikDTO);
    Result deleteEtkinlik(Long id);
}
