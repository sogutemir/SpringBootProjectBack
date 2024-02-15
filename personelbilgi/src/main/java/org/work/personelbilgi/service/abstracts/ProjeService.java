package org.work.personelbilgi.service.abstracts;

import org.work.personelbilgi.core.result.DataResult;
import org.work.personelbilgi.core.result.Result;
import org.work.personelbilgi.dto.ProjeDTO;

import java.util.List;


public interface ProjeService {
    DataResult<List<ProjeDTO>> getAllProje();
    DataResult<ProjeDTO> getProjeById(Long id);
    DataResult<List<ProjeDTO>> getProjeByPersonelId(Long personelId);
    Result addProje(ProjeDTO ProjeDTO);
    Result updateProje(Long id,ProjeDTO ProjeDTO);
    Result deleteProje(Long id);
}
