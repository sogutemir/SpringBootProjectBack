package org.work.personelbilgi.service.concretes;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.work.personelbilgi.core.result.*;
import org.work.personelbilgi.dto.EgitimDTO;
import org.work.personelbilgi.model.Egitim;
import org.work.personelbilgi.repository.EgitimRepository;
import org.work.personelbilgi.service.abstracts.EgitimService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EgitimServiceImp implements EgitimService {

    private final EgitimRepository egitimRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public DataResult<List<EgitimDTO>> getAllEgitim() {
        List<Egitim> egitimList = egitimRepository.findAll();
        List<EgitimDTO> egitimDTOS = egitimList.stream()
                .map(egitim -> modelMapper.map(egitim, EgitimDTO.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(egitimDTOS, "All egitim listed successfully.");
    }

    @Transactional
    @Override
    public DataResult<EgitimDTO> getEgitimById(Long id) {
        Egitim egitim = egitimRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Egitim not found with id: " + id));
        EgitimDTO egitimDTO = modelMapper.map(egitim, EgitimDTO.class);
        return new SuccessDataResult<>(egitimDTO, "Egitim found successfully.");
    }
    @Transactional
    @Override
    public DataResult<List<EgitimDTO>> getEgitimByPersonelId(Long personelId) {
        List<Egitim> egitimList = egitimRepository.findByPersonelId(personelId);
        if (egitimList.isEmpty()) {
            throw new IllegalArgumentException("Egitim not found with personel id: " + personelId);
        }
        List<EgitimDTO> egitimDTOS = egitimList.stream()
                .map(egitim -> modelMapper.map(egitim, EgitimDTO.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(egitimDTOS, "Egitim found successfully by personel id.");
    }

    @Transactional
    @Override
    public Result addEgitim(EgitimDTO egitimDTO) {
        Egitim egitim = modelMapper.map(egitimDTO, Egitim.class);
        egitimRepository.save(egitim);
        return new SuccessDataResult<>("Egitim added successfully.");
    }

    @Transactional
    @Override
    public Result updateEgitim(Long id, EgitimDTO egitimDTO) {
        Egitim existingEgitim = egitimRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Egitim not found with id: " + id));
        modelMapper.map(egitimDTO, existingEgitim);
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
