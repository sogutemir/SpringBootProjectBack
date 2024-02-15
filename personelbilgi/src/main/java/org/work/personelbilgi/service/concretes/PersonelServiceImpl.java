package org.work.personelbilgi.service.concretes;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.work.personelbilgi.core.result.*;
import org.work.personelbilgi.dto.PersonelDTO;
import org.springframework.transaction.annotation.Transactional;
import org.work.personelbilgi.model.Personel;
import org.work.personelbilgi.repository.PersonelRepository;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonelServiceImpl implements org.work.personelbilgi.service.abstracts.PersonelService {
    private final PersonelRepository personelRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    @Override
    public DataResult<List<PersonelDTO>> getAllPersonel() {
        List<Personel> personelList = personelRepository.findAll();
        List<PersonelDTO> personelDTOs = personelList.stream()
                .map(personel -> {
                    PersonelDTO personelDTO = modelMapper.map(personel, PersonelDTO.class);
                    if (personel.getFotograf() != null) {
                        String fotografBase64 = Base64.getEncoder().encodeToString(personel.getFotograf());
                        personelDTO.setFotografBase64(fotografBase64);
                    }
                    return personelDTO;
                })
                .collect(Collectors.toList());
        return new SuccessDataResult<>(personelDTOs, "All personnel listed successfully.");
    }

    @Transactional(readOnly = true)
    @Override
    public DataResult<PersonelDTO> getPersonelById(Long id) {
        Personel personel = personelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Personel not found with id: " + id));
        PersonelDTO personelDTO = modelMapper.map(personel, PersonelDTO.class);

        if (personel.getFotograf() != null) {
            String fotografBase64 = Base64.getEncoder().encodeToString(personel.getFotograf());
            personelDTO.setFotografBase64(fotografBase64);
        }

        return new SuccessDataResult<>(personelDTO, "Personel found successfully.");
    }


    @Transactional
    @Override
    public Result addPersonel(PersonelDTO personelDTO) {
        Personel personel = modelMapper.map(personelDTO, Personel.class);
        if(personelDTO.getFotografBase64() != null && !personelDTO.getFotografBase64().isEmpty()){
            byte[] fotograf = Base64.getDecoder().decode(personelDTO.getFotografBase64());
            personel.setFotograf(fotograf);
        }
        personelRepository.save(personel);
        return new SuccessResult("Personel added successfully.");
    }

    @Transactional
    @Override
    public Result updatePersonel(Long id, PersonelDTO personelDTO) {
        Personel existingPersonel = personelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Personel not found with id: " + id));
        modelMapper.map(personelDTO, existingPersonel);
        if(personelDTO.getFotografBase64() != null && !personelDTO.getFotografBase64().isEmpty()){
            byte[] fotograf = Base64.getDecoder().decode(personelDTO.getFotografBase64());
            existingPersonel.setFotograf(fotograf);
        }
        personelRepository.save(existingPersonel);
        return new SuccessResult("Personel updated successfully.");
    }

    @Transactional
    @Override
    public Result deletePersonel(Long id) {
        boolean exists = personelRepository.existsById(id);
        if (!exists) {
            return new ErrorResult("Personel not found with id: " + id);
        }
        personelRepository.deleteById(id);
        return new SuccessResult("Personel deleted successfully.");
    }
}
