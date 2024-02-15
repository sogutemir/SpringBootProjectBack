package org.work.personelbilgi.service.abstracts;

import org.work.personelbilgi.core.result.DataResult;
import org.work.personelbilgi.core.result.Result;
import org.work.personelbilgi.dto.PersonelDTO;
import org.work.personelbilgi.model.Personel;

import java.util.List;

public interface PersonelService {
    DataResult<List<PersonelDTO>> getAllPersonel();
    DataResult<PersonelDTO> getPersonelById(Long id);
    Result addPersonel(PersonelDTO personelDTO);
    Result updatePersonel(Long id,PersonelDTO personelDTO);
    Result deletePersonel(Long id);

}
