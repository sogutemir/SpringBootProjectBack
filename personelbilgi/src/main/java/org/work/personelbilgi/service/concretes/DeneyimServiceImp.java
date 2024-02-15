package org.work.personelbilgi.service.concretes;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.work.personelbilgi.core.result.*;
import org.work.personelbilgi.dto.DeneyimDTO;
import org.work.personelbilgi.model.Deneyim;
import org.work.personelbilgi.model.Personel;
import org.work.personelbilgi.repository.DeneyimRepository;
import org.work.personelbilgi.repository.PersonelRepository;
import org.work.personelbilgi.service.abstracts.DeneyimService;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeneyimServiceImp implements DeneyimService {

    private final DeneyimRepository deneyimRepository;
    private final ModelMapper modelMapper;
    private final PersonelRepository personelRepository;

    @Transactional(readOnly = true)
    @Override
    public DataResult<List<DeneyimDTO>> getAllDeneyim() {
        List<Deneyim> DeneyimList = deneyimRepository.findAll();
        if (DeneyimList.isEmpty()) {
            return new ErrorDataResult<>("No Deneyim records found.");
        }
        List<DeneyimDTO> DeneyimDTOS = DeneyimList.stream()
                .map(Deneyim -> modelMapper.map(Deneyim, DeneyimDTO.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(DeneyimDTOS, "All Deneyim listed successfully.");
    }

    @Transactional(readOnly = true)
    @Override
    public DataResult<DeneyimDTO> getDeneyimById(Long id) {
        Deneyim Deneyim = deneyimRepository.findById(id).orElse(null);
        if (Deneyim == null) {
            return new ErrorDataResult<>("Deneyim not found with id: " + id);
        }
        DeneyimDTO DeneyimDTO = modelMapper.map(Deneyim, DeneyimDTO.class);
        return new SuccessDataResult<>(DeneyimDTO, "Deneyim found successfully.");
    }

    @Transactional(readOnly = true)
    @Override
    public DataResult<List<DeneyimDTO>> getDeneyimByPersonelId(Long personelId) {
        List<Deneyim> DeneyimList = deneyimRepository.findByPersonelId(personelId);
        if (DeneyimList == null || DeneyimList.isEmpty()) {
            return new ErrorDataResult<>("No Deneyim records found for personel id: " + personelId);
        }
        List<DeneyimDTO> DeneyimDTOS = DeneyimList.stream()
                .map(Deneyim -> modelMapper.map(Deneyim, DeneyimDTO.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(DeneyimDTOS, "Deneyim found successfully by personel id.");
    }

    @Transactional
    @Override
    public Result addDeneyim(DeneyimDTO deneyimDTO) {
        if (deneyimDTO == null) {
            return new ErrorDataResult<>("DeneyimDTO is null.");
        }
        Deneyim deneyim = modelMapper.map(deneyimDTO, Deneyim.class);
        Personel personel = personelRepository.findById(deneyimDTO.getPersonelId()).orElse(null);
        if (personel == null) {
            return new ErrorDataResult<>("Personel not found with id: " + deneyimDTO.getPersonelId());
        }
        deneyim.setPersonel(personel);
        deneyimRepository.save(deneyim);
        return new SuccessDataResult<>("Deneyim added successfully.");
    }

    @Transactional
    @Override
    public Result updateDeneyim(Long id, DeneyimDTO deneyimDTO) {
        if (deneyimDTO == null) {
            return new ErrorDataResult<>("DeneyimDTO is null.");
        }
        Deneyim existingDeneyim = deneyimRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Deneyim not found with id: " + id));
        modelMapper.map(deneyimDTO, existingDeneyim);

        Personel personel = personelRepository.findById(deneyimDTO.getPersonelId())
                .orElseThrow(() -> new IllegalArgumentException("Personel not found with id: " + deneyimDTO.getPersonelId()));

        existingDeneyim.setPersonel(personel);

        deneyimRepository.save(existingDeneyim);
        return new SuccessResult("Deneyim updated successfully.");
    }

    @Transactional
    @Override
    public Result deleteDeneyim(Long id) {
        boolean exists = deneyimRepository.existsById(id);
        if (!exists) {
            return new ErrorResult("Deneyim not found with id: " + id);
        }
        deneyimRepository.deleteById(id);
        return new SuccessResult("Deneyim deleted successfully.");
    }
}