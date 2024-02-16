package org.work.personelbilgi.service.concretes;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.work.personelbilgi.core.result.*;
import org.work.personelbilgi.dto.DosyaDTO;
import org.work.personelbilgi.model.Dosya;
import org.work.personelbilgi.model.Personel;
import org.work.personelbilgi.repository.DosyaRepository;
import org.work.personelbilgi.repository.PersonelRepository;
import org.work.personelbilgi.service.abstracts.DosyaService;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DosyaServiceImp implements DosyaService {

    private final DosyaRepository dosyaRepository;
    private final PersonelRepository personelRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    @Override
    public DataResult<List<DosyaDTO>> getAllDosya() {
        List<Dosya> DosyaList = dosyaRepository.findAll();
        if (DosyaList.isEmpty()) {
            return new ErrorDataResult<>("No Dosya records found.");
        }
        List<DosyaDTO> DosyaDTOs = DosyaList.stream()
                .map(Dosya -> {
                    DosyaDTO dosyaDTO = modelMapper.map(Dosya, DosyaDTO.class);
                    if (Dosya.getDosya() != null) {
                        String DosyaBase64 = Base64.getEncoder().encodeToString(Dosya.getDosya());
                        dosyaDTO.setDosyaBase64(DosyaBase64);
                    }
                    return dosyaDTO;
                })
                .collect(Collectors.toList());
        return new SuccessDataResult<>(DosyaDTOs, "All personnel listed successfully.");
    }

    @Override
    @Transactional(readOnly = true)
    public DataResult<List<DosyaDTO>> getDosyaByPersonelId(Long personelID){
        List<Dosya> dosyaList = dosyaRepository.findByPersonelId(personelID);
        if (dosyaList == null || dosyaList.isEmpty()) {
            return new ErrorDataResult<>("No Dosya records found for personel id: " + personelID);
        }
        List<DosyaDTO> dosyaDTOS = dosyaList.stream()
                .map(Dosya -> {
                    DosyaDTO dosyaDTO = modelMapper.map(Dosya, DosyaDTO.class);
                    if(Dosya.getDosya() != null) {
                        String DosyaBase64 = Base64.getEncoder().encodeToString(Dosya.getDosya());
                        dosyaDTO.setDosyaBase64(DosyaBase64);
                    }
                    return dosyaDTO;
                })
                .collect(Collectors.toList());

        return new SuccessDataResult<>(dosyaDTOS, "All personnel listed successfully.");
    }

    @Transactional(readOnly = true)
    @Override
    public DataResult<DosyaDTO> getDosyaById(Long id) {
        Dosya dosya = dosyaRepository.findById(id).orElse(null);
        if (dosya == null) {
            return new ErrorDataResult<>("Dosya not found with id: " + id);
        }
        DosyaDTO dosyaDTO = modelMapper.map(dosya, DosyaDTO.class);

        if (dosya.getDosya() != null) {
            String DosyaBase64 = Base64.getEncoder().encodeToString(dosya.getDosya());
            dosyaDTO.setDosyaBase64(DosyaBase64);
        }

        return new SuccessDataResult<>(dosyaDTO, "Dosya found successfully.");
    }


    @Transactional
    @Override
    public Result addDosya(DosyaDTO dosyaDTO) {
        if (dosyaDTO == null) {
            return new ErrorDataResult<>("DosyaDTO is null.");
        }
        Dosya dosya = modelMapper.map(dosyaDTO, Dosya.class);
        if(dosyaDTO.getDosyaBase64() != null && !dosyaDTO.getDosyaBase64().isEmpty()){
            byte[] DosyaBase = Base64.getDecoder().decode(dosyaDTO.getDosyaBase64());
            dosya.setDosya(DosyaBase);
        }

        Personel personel = personelRepository.findById(dosyaDTO.getPersonelId())
                .orElseThrow(() -> new IllegalArgumentException("Personel not found with id: " + dosyaDTO.getPersonelId()));

        dosya.setPersonel(personel);

        dosyaRepository.save(dosya);
        return new SuccessResult("Dosya added successfully.");
    }

    @Transactional
    @Override
    public Result updateDosya(Long id, DosyaDTO dosyaDTO) {
        if (dosyaDTO == null) {
            return new ErrorDataResult<>("DosyaDTO is null.");
        }
        Dosya existingDosya = dosyaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Dosya not found with id: " + id));
        modelMapper.map(dosyaDTO, existingDosya);
        if(dosyaDTO.getDosyaBase64() != null && !dosyaDTO.getDosyaBase64().isEmpty()){
            byte[] Dosya = Base64.getDecoder().decode(dosyaDTO.getDosyaBase64());
            existingDosya.setDosya(Dosya);
        }else {
            existingDosya.setDosya(null);
        }


        Personel personel = personelRepository.findById(dosyaDTO.getPersonelId())
                .orElseThrow(() -> new IllegalArgumentException("Personel not found with id: " + dosyaDTO.getPersonelId()));

        existingDosya.setPersonel(personel);

        dosyaRepository.save(existingDosya);
        return new SuccessResult("Dosya updated successfully.");
    }

    @Transactional
    @Override
    public Result deleteDosya(Long id) {
        boolean exists = dosyaRepository.existsById(id);
        if (!exists) {
            return new ErrorResult("Dosya not found with id: " + id);
        }
        dosyaRepository.deleteById(id);
        return new SuccessResult("Dosya deleted successfully.");
    }
}
