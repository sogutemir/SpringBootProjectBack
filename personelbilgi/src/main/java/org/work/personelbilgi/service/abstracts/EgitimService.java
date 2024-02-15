package org.work.personelbilgi.service.abstracts;

import org.work.personelbilgi.core.result.DataResult;
import org.work.personelbilgi.core.result.Result;
import org.work.personelbilgi.dto.EgitimDTO;

import java.util.List;

public interface EgitimService {

    DataResult<List<EgitimDTO>> getAllEgitim();
    DataResult<EgitimDTO> getEgitimById(Long id);
    DataResult<List<EgitimDTO>> getEgitimByPersonelId(Long personelId);
    Result addEgitim(EgitimDTO egitimDTO);
    Result updateEgitim(Long id,EgitimDTO egitimDTO);
    Result deleteEgitim(Long id);

}
