package org.work.personelbilgi.service.concretes;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.work.personelbilgi.core.result.*;
import org.work.personelbilgi.dto.ProjeDTO;
import org.work.personelbilgi.dto.ProjeDTO;
import org.work.personelbilgi.model.Proje;
import org.work.personelbilgi.repository.ProjeRepository;
import org.work.personelbilgi.service.abstracts.ProjeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjeServiceImp implements ProjeService {

    private final ProjeRepository projeRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public DataResult<List<ProjeDTO>> getAllProje() {
        List<Proje> ProjeList = projeRepository.findAll();
        List<ProjeDTO> ProjeDTOS = ProjeList.stream()
                .map(Proje -> modelMapper.map(Proje, ProjeDTO.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(ProjeDTOS, "All Proje listed successfully.");
    }

    @Transactional
    @Override
    public DataResult<ProjeDTO> getProjeById(Long id) {
        Proje Proje = projeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Proje not found with id: " + id));
        ProjeDTO ProjeDTO = modelMapper.map(Proje, ProjeDTO.class);
        return new SuccessDataResult<>(ProjeDTO, "Proje found successfully.");
    }
    @Transactional
    @Override
    public DataResult<List<ProjeDTO>> getProjeByPersonelId(Long personelId) {
        List<Proje> ProjeList = projeRepository.findByPersonelId(personelId);
        if (ProjeList.isEmpty()) {
            throw new IllegalArgumentException("Proje not found with personel id: " + personelId);
        }
        List<ProjeDTO> ProjeDTOS = ProjeList.stream()
                .map(Proje -> modelMapper.map(Proje, ProjeDTO.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(ProjeDTOS, "Proje found successfully by personel id.");
    }

    @Transactional
    @Override
    public Result addProje(ProjeDTO ProjeDTO) {
        Proje Proje = modelMapper.map(ProjeDTO, Proje.class);
        projeRepository.save(Proje);
        return new SuccessDataResult<>("Proje added successfully.");
    }

    @Transactional
    @Override
    public Result updateProje(Long id, ProjeDTO ProjeDTO) {
        Proje existingProje = projeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Proje not found with id: " + id));
        modelMapper.map(ProjeDTO, existingProje);
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
