package org.work.personelbilgi.service.concretes;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.work.personelbilgi.core.result.*;
import org.work.personelbilgi.dto.DosyaDTO;
import org.work.personelbilgi.dto.EtkinlikDTO;
import org.work.personelbilgi.model.Dosya;
import org.work.personelbilgi.model.Etkinlik;
import org.work.personelbilgi.model.Personel;
import org.work.personelbilgi.repository.EtkinlikRepository;
import org.work.personelbilgi.repository.PersonelRepository;
import org.work.personelbilgi.service.abstracts.EtkinlikService;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EtkinlikServiceImp implements EtkinlikService {

    private final EtkinlikRepository etkinlikRepository;
    private final ModelMapper modelMapper;
    private final PersonelRepository personelRepository;

    @Transactional(readOnly = true)
    @Override
    public DataResult<List<EtkinlikDTO>> getAllEtkinlik() {
        List<Etkinlik> EtkinlikList = etkinlikRepository.findAll();
        if (EtkinlikList.isEmpty()) {
            return new ErrorDataResult<>("No Etkinlik records found.");
        }
        List<EtkinlikDTO> EtkinlikDTOs = EtkinlikList.stream()
                .map(Etkinlik -> {
                    EtkinlikDTO etkinlikDTO = modelMapper.map(Etkinlik, EtkinlikDTO.class);
                    if (Etkinlik.getEk() != null) {
                        String EtkinlikBase64 = Base64.getEncoder().encodeToString(Etkinlik.getEk());
                        etkinlikDTO.setEk64(EtkinlikBase64);
                    }
                    return etkinlikDTO;
                })
                .collect(Collectors.toList());
        return new SuccessDataResult<>(EtkinlikDTOs, "All personnel listed successfully.");
    }

    @Override
    @Transactional(readOnly = true)
    public DataResult<List<EtkinlikDTO>> getEtkinlikByPersonelId(Long personelID){
        List<Etkinlik> EtkinlikList = etkinlikRepository.findByPersonelId(personelID);
        if (EtkinlikList == null || EtkinlikList.isEmpty()) {
            return new ErrorDataResult<>("No Etkinlik records found for personel id: " + personelID);
        }
        List<EtkinlikDTO> EtkinlikDTOS = EtkinlikList.stream()
                .map(Etkinlik -> {
                    EtkinlikDTO EtkinlikDTO = modelMapper.map(Etkinlik, EtkinlikDTO.class);
                    if(Etkinlik.getEk() != null) {
                        String EtkinlikBase64 = Base64.getEncoder().encodeToString(Etkinlik.getEk());
                        EtkinlikDTO.setEk64(EtkinlikBase64);
                    }
                    return EtkinlikDTO;
                })
                .collect(Collectors.toList());

        return new SuccessDataResult<>(EtkinlikDTOS, "All personnel listed successfully.");
    }

    @Transactional(readOnly = true)
    @Override
    public DataResult<EtkinlikDTO> getEtkinlikById(Long id) {
        Etkinlik Etkinlik = etkinlikRepository.findById(id).orElse(null);
        if (Etkinlik == null) {
            return new ErrorDataResult<>("Etkinlik not found with id: " + id);
        }
        EtkinlikDTO EtkinlikDTO = modelMapper.map(Etkinlik, EtkinlikDTO.class);

        if (Etkinlik.getEk() != null) {
            String EtkinlikBase64 = Base64.getEncoder().encodeToString(Etkinlik.getEk());
            EtkinlikDTO.setEk64(EtkinlikBase64);
        }

        return new SuccessDataResult<>(EtkinlikDTO, "Etkinlik found successfully.");
    }


    @Transactional
    @Override
    public Result addEtkinlik(EtkinlikDTO etkinlikDTO) {
        if (etkinlikDTO == null) {
            return new ErrorDataResult<>("DosyaDTO is null.");
        }
        Etkinlik etkinlik = modelMapper.map(etkinlikDTO, Etkinlik.class);
        if(etkinlikDTO.getEk64() != null && !etkinlikDTO.getEk64().isEmpty()){
            byte[] EtkinlikBase = Base64.getDecoder().decode(etkinlikDTO.getEk64());
            etkinlik.setEk(EtkinlikBase);
        }

        Personel personel = personelRepository.findById(etkinlikDTO.getPersonelId())
                .orElseThrow(() -> new IllegalArgumentException("Personel not found with id: " + etkinlikDTO.getPersonelId()));

        etkinlik.setPersonel(personel);

        etkinlikRepository.save(etkinlik);
        return new SuccessResult("Dosya added successfully.");
    }

    @Transactional
    @Override
    public Result updateEtkinlik(Long id, EtkinlikDTO etkinlikDTO) {
        if (etkinlikDTO == null) {
            return new ErrorDataResult<>("EtkinlikDTO is null.");
        }
        Etkinlik existingEtkinlik = etkinlikRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Etkinlik not found with id: " + id));
        modelMapper.map(etkinlikDTO, existingEtkinlik);
        if(etkinlikDTO.getEk64() != null && !etkinlikDTO.getEk64().isEmpty()){
            byte[] Etkinlik = Base64.getDecoder().decode(etkinlikDTO.getEk64());
            existingEtkinlik.setEk(Etkinlik);
        } else {
            existingEtkinlik.setEk(null);
        }

        Personel personel = personelRepository.findById(etkinlikDTO.getPersonelId())
                .orElseThrow(() -> new IllegalArgumentException("Personel not found with id: " + etkinlikDTO.getPersonelId()));

        existingEtkinlik.setPersonel(personel);

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
