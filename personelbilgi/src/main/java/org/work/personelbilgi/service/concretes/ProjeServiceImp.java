package org.work.personelbilgi.service.concretes;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.work.personelbilgi.core.result.*;
import org.work.personelbilgi.dto.ProjeDTO;
import org.work.personelbilgi.model.Personel;
import org.work.personelbilgi.model.Proje;
import org.work.personelbilgi.repository.PersonelRepository;
import org.work.personelbilgi.repository.ProjeRepository;
import org.work.personelbilgi.service.abstracts.ProjeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjeServiceImp implements ProjeService {

    private final ProjeRepository projeRepository;
    private final PersonelRepository personelRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    @Override
    public DataResult<List<ProjeDTO>> getAllProje() {
        List<Proje> ProjeList = projeRepository.findAll();
        if (ProjeList.isEmpty()) {
            return new ErrorDataResult<>("No Proje records found.");
        }
        List<ProjeDTO> ProjeDTOS = ProjeList.stream()
                .map(Proje -> modelMapper.map(Proje, ProjeDTO.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(ProjeDTOS, "All Proje listed successfully.");
    }

    @Transactional(readOnly = true)
    @Override
    public DataResult<ProjeDTO> getProjeById(Long id) {
        Proje Proje = projeRepository.findById(id).orElse(null);
        if (Proje == null) {
            return new ErrorDataResult<>("Proje not found with id: " + id);
        }
        ProjeDTO ProjeDTO = modelMapper.map(Proje, ProjeDTO.class);
        return new SuccessDataResult<>(ProjeDTO, "Proje found successfully.");
    }

    @Transactional(readOnly = true)
    @Override
    public DataResult<List<ProjeDTO>> getProjeByPersonelId(Long personelId) {
        List<Proje> ProjeList = projeRepository.findByPersonelId(personelId);
        if (ProjeList == null || ProjeList.isEmpty()) {
            return new ErrorDataResult<>("No Proje records found for personel id: " + personelId);
        }
        List<ProjeDTO> ProjeDTOS = ProjeList.stream()
                .map(Proje -> modelMapper.map(Proje, ProjeDTO.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(ProjeDTOS, "Proje found successfully by personel id.");
    }

    @Transactional
    @Override
    public Result addProje(ProjeDTO projeDTO) {
        if (projeDTO == null) {
            return new ErrorDataResult<>("ProjeDTO is null.");
        }
        if (!projeDTO.isProjeDevamEdiyor() && projeDTO.getProjeBitisTarihi() == null) {
            return new ErrorDataResult<>("Proje bitiş tarihi is required when the project is finished.");
        }
        Proje proje = modelMapper.map(projeDTO, Proje.class);
        Personel personel = personelRepository.findById(projeDTO.getPersonelId())
                .orElseThrow(() -> new IllegalArgumentException("Personel not found with id: " + projeDTO.getPersonelId()));

        proje.setPersonel(personel);
        projeRepository.save(proje);
        return new SuccessDataResult<>("Proje added successfully.");
    }


    @Transactional
    @Override
    public Result updateProje(Long id, ProjeDTO projeDTO) {
        if (projeDTO == null) {
            return new ErrorDataResult<>("ProjeDTO is null.");
        }
        if (!projeDTO.isProjeDevamEdiyor() && projeDTO.getProjeBitisTarihi() == null) {
            return new ErrorDataResult<>("Proje bitiş tarihi is required when the project is finished.");
        }
        Proje existingProje = projeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Proje not found with id: " + id));
        modelMapper.map(projeDTO, existingProje);

        Personel personel = personelRepository.findById(projeDTO.getPersonelId())
                .orElseThrow(() -> new IllegalArgumentException("Personel not found with id: " + projeDTO.getPersonelId()));

        existingProje.setPersonel(personel);

        projeRepository.save(existingProje);
        return new SuccessResult("Proje updated successfully.");
    }
    @Transactional
    @Override
    public Result deleteProje(Long id) {
        boolean exists = projeRepository.existsById(id);
        if (!exists) {
            return new ErrorResult("Proje not found with id: " + id);
        }
        projeRepository.deleteById(id);
        return new SuccessResult("Proje deleted successfully.");
    }
    
}
