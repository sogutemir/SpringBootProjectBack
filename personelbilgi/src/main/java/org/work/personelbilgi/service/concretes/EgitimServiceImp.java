package org.work.personelbilgi.service.concretes;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.work.personelbilgi.core.result.*;
import org.work.personelbilgi.dto.EgitimDTO;
import org.work.personelbilgi.model.Egitim;
import org.work.personelbilgi.model.Personel;
import org.work.personelbilgi.repository.EgitimRepository;
import org.work.personelbilgi.repository.PersonelRepository;
import org.work.personelbilgi.service.abstracts.EgitimService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EgitimServiceImp implements EgitimService {

    private final EgitimRepository egitimRepository;
    private final ModelMapper modelMapper;
    private final PersonelRepository personelRepository;

    @Transactional(readOnly = true)
    @Override
    public DataResult<List<EgitimDTO>> getAllEgitim() {
        List<Egitim> egitimList = egitimRepository.findAll();
        if (egitimList == null || egitimList.isEmpty()) {
            return new ErrorDataResult<>("No Egitim records found.");
        }
        List<EgitimDTO> egitimDTOS = egitimList.stream()
                .map(egitim -> modelMapper.map(egitim, EgitimDTO.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(egitimDTOS, "All egitim listed successfully.");
    }

    @Transactional(readOnly = true)
    @Override
    public DataResult<EgitimDTO> getEgitimById(Long id) {
        Egitim egitim = egitimRepository.findById(id).orElse(null);
        if (egitim == null) {
            return new ErrorDataResult<>("Egitim not found with id: " + id);
        }
        EgitimDTO egitimDTO = modelMapper.map(egitim, EgitimDTO.class);
        return new SuccessDataResult<>(egitimDTO, "Egitim found successfully.");
    }

    @Transactional(readOnly = true)
    @Override
    public DataResult<List<EgitimDTO>> getEgitimByPersonelId(Long personelId) {
        List<Egitim> egitimList = egitimRepository.findByPersonelId(personelId);
        if (egitimList == null || egitimList.isEmpty()) {
            return new ErrorDataResult<>("No Egitim records found for personel id: " + personelId);
        }
        List<EgitimDTO> egitimDTOS = egitimList.stream()
                .map(egitim -> modelMapper.map(egitim, EgitimDTO.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(egitimDTOS, "Egitim found successfully by personel id.");
    }

    @Transactional
    @Override
    public Result addEgitim(EgitimDTO egitimDTO) {
        if (egitimDTO == null) {
            return new ErrorDataResult<>("EgitimDTO is null.");
        }
        Egitim egitim = modelMapper.map(egitimDTO, Egitim.class);
        Personel personel = personelRepository.findById(egitimDTO.getPersonelId())
                .orElseThrow(() -> new IllegalArgumentException("Personel not found with id: " + egitimDTO.getPersonelId()));

        egitim.setPersonel(personel);
        egitimRepository.save(egitim);
        return new SuccessDataResult<>("Egitim added successfully.");
    }

    @Transactional
    @Override
    public Result updateEgitim(Long id, EgitimDTO egitimDTO) {
        if (egitimDTO == null) {
            return new ErrorDataResult<>("EgitimDTO is null.");
        }
        Egitim existingEgitim = egitimRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Egitim not found with id: " + id));
        modelMapper.map(egitimDTO, existingEgitim);

        Personel personel = personelRepository.findById(egitimDTO.getPersonelId())
                .orElseThrow(() -> new IllegalArgumentException("Personel not found with id: " + egitimDTO.getPersonelId()));

        existingEgitim.setPersonel(personel);

        egitimRepository.save(existingEgitim);
        return new SuccessResult("Egitim updated successfully.");
    }

    @Transactional
    @Override
    public Result deleteEgitim(Long id) {
        boolean exists = egitimRepository.existsById(id);
        if (!exists) {
            return new ErrorResult("Egitim not found with id: " + id);
        }
        egitimRepository.deleteById(id);
        return new SuccessResult("Egitim deleted successfully.");
    }
}
