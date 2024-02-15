package org.work.personelbilgi.service.concretes;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.work.personelbilgi.core.result.*;
import org.work.personelbilgi.dto.PersonelDTO;
import org.springframework.transaction.annotation.Transactional;
import org.work.personelbilgi.model.Personel;
import org.work.personelbilgi.repository.PersonelRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonelService implements org.work.personelbilgi.service.abstracts.PersonelService {
    private final PersonelRepository personelRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    @Override
    public DataResult<List<PersonelDTO>> getAllPersonel() {
        List<Personel> personelList = personelRepository.findAll();
        List<PersonelDTO> personelDTOs = personelList.stream()
                .map(personel -> modelMapper.map(personel, PersonelDTO.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(personelDTOs, "All personnel listed successfully.");
    }

    @Transactional(readOnly = true)
    @Override
    public DataResult<PersonelDTO> getPersonelById(Long id) {
        Personel personel = personelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Personel not found with id: " + id));
        PersonelDTO personelDTO = modelMapper.map(personel, PersonelDTO.class);
        return new SuccessDataResult<>(personelDTO, "Personel found successfully.");
    }

    @Transactional
    @Override
    public Result addPersonel(PersonelDTO personelDTO) {
        Personel personel = modelMapper.map(personelDTO, Personel.class);
        personelRepository.save(personel);
        return new SuccessResult("Personel added successfully.");
    }

    @Transactional
    @Override
    public Result updatePersonel(Long id, PersonelDTO personelDTO) {
        Personel existingPersonel = personelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Personel not found with id: " + id));
        modelMapper.map(personelDTO, existingPersonel);
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
