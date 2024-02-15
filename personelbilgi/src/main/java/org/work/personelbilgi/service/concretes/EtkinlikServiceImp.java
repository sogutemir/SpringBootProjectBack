package org.work.personelbilgi.service.concretes;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.work.personelbilgi.core.result.*;
import org.work.personelbilgi.dto.EtkinlikDTO;
import org.work.personelbilgi.dto.EtkinlikDTO;
import org.work.personelbilgi.model.Etkinlik;
import org.work.personelbilgi.repository.EtkinlikRepository;
import org.work.personelbilgi.service.abstracts.EtkinlikService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EtkinlikServiceImp implements EtkinlikService {

    private final EtkinlikRepository etkinlikRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public DataResult<List<EtkinlikDTO>> getAllEtkinlik() {
        List<Etkinlik> EtkinlikList = etkinlikRepository.findAll();
        List<EtkinlikDTO> EtkinlikDTOS = EtkinlikList.stream()
                .map(Etkinlik -> modelMapper.map(Etkinlik, EtkinlikDTO.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(EtkinlikDTOS, "All Etkinlik listed successfully.");
    }

    @Transactional
    @Override
    public DataResult<EtkinlikDTO> getEtkinlikById(Long id) {
        Etkinlik Etkinlik = etkinlikRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Etkinlik not found with id: " + id));
        EtkinlikDTO EtkinlikDTO = modelMapper.map(Etkinlik, EtkinlikDTO.class);
        return new SuccessDataResult<>(EtkinlikDTO, "Etkinlik found successfully.");
    }
    @Transactional
    @Override
    public DataResult<List<EtkinlikDTO>> getEtkinlikByPersonelId(Long personelId) {
        List<Etkinlik> EtkinlikList = etkinlikRepository.findByPersonelId(personelId);
        if (EtkinlikList.isEmpty()) {
            throw new IllegalArgumentException("Etkinlik not found with personel id: " + personelId);
        }
        List<EtkinlikDTO> EtkinlikDTOS = EtkinlikList.stream()
                .map(Etkinlik -> modelMapper.map(Etkinlik, EtkinlikDTO.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(EtkinlikDTOS, "Etkinlik found successfully by personel id.");
    }

    @Transactional
    @Override
    public Result addEtkinlik(EtkinlikDTO EtkinlikDTO) {
        Etkinlik Etkinlik = modelMapper.map(EtkinlikDTO, Etkinlik.class);
        etkinlikRepository.save(Etkinlik);
        return new SuccessDataResult<>("Etkinlik added successfully.");
    }

    @Transactional
    @Override
    public Result updateEtkinlik(Long id, EtkinlikDTO EtkinlikDTO) {
        Etkinlik existingEtkinlik = etkinlikRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Etkinlik not found with id: " + id));
        modelMapper.map(EtkinlikDTO, existingEtkinlik);
        etkinlikRepository.save(existingEtkinlik);
        return new SuccessResult("Etkinlik updated successfully.");
    }

    @Transactional
    @Override
    public Result deleteEtkinlik(Long id) {
        boolean exists = etkinlikRepository.existsById(id);
        if (!exists) {
            return new ErrorResult("Etkinlik not found with id: " + id);
        }
        etkinlikRepository.deleteById(id);
        return new SuccessResult("Etkinlik deleted successfully.");
    }
}
