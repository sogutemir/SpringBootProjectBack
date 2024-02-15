package org.work.personelbilgi.service.concretes;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.work.personelbilgi.core.result.*;
import org.work.personelbilgi.dto.DosyaDTO;
import org.work.personelbilgi.model.Dosya;
import org.work.personelbilgi.repository.DosyaRepository;
import org.work.personelbilgi.service.abstracts.DosyaService;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DosyaServiceImp implements DosyaService {

    private final DosyaRepository dosyaRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    @Override
    public DataResult<List<DosyaDTO>> getAllDosya() {
        List<Dosya> DosyaList = dosyaRepository.findAll();
        List<DosyaDTO> DosyaDTOs = DosyaList.stream()
                .map(Dosya -> {
                    DosyaDTO DosyaDTO = modelMapper.map(Dosya, DosyaDTO.class);
                    if (Dosya.getDosya() != null) {
                        String DosyaBase64 = Base64.getEncoder().encodeToString(Dosya.getDosya());
                        DosyaDTO.setDosyaBase64(DosyaBase64);
                    }
                    return DosyaDTO;
                })
                .collect(Collectors.toList());
        return new SuccessDataResult<>(DosyaDTOs, "All personnel listed successfully.");
    }

    @Transactional(readOnly = true)
    @Override
    public DataResult<DosyaDTO> getDosyaById(Long id) {
        Dosya Dosya = dosyaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Dosya not found with id: " + id));
        DosyaDTO DosyaDTO = modelMapper.map(Dosya, DosyaDTO.class);

        if (Dosya.getDosya() != null) {
            String DosyaBase64 = Base64.getEncoder().encodeToString(Dosya.getDosya());
            DosyaDTO.setDosyaBase64(DosyaBase64);
        }

        return new SuccessDataResult<>(DosyaDTO, "Dosya found successfully.");
    }

    @Override
    public DataResult<List<DosyaDTO>> getDosyaByDosyaId(Long DosyaId) {
        return null;
    }


    @Transactional
    @Override
    public Result addDosya(DosyaDTO DosyaDTO) {
        Dosya Dosya = modelMapper.map(DosyaDTO, Dosya.class);
        if(DosyaDTO.getDosyaBase64() != null && !DosyaDTO.getDosyaBase64().isEmpty()){
            byte[] DosyaBase = Base64.getDecoder().decode(DosyaDTO.getDosyaBase64());
            Dosya.setDosya(DosyaBase);
        }
        dosyaRepository.save(Dosya);
        return new SuccessResult("Dosya added successfully.");
    }

    @Transactional
    @Override
    public Result updateDosya(Long id, DosyaDTO DosyaDTO) {
        Dosya existingDosya = dosyaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Dosya not found with id: " + id));
        modelMapper.map(DosyaDTO, existingDosya);
        if(DosyaDTO.getDosyaBase64() != null && !DosyaDTO.getDosyaBase64().isEmpty()){
            byte[] Dosya = Base64.getDecoder().decode(DosyaDTO.getDosyaBase64());
            existingDosya.setDosya(Dosya);
        }
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
