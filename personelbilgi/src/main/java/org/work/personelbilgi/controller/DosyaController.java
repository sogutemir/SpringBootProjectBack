package org.work.personelbilgi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.work.personelbilgi.core.result.DataResult;
import org.work.personelbilgi.core.result.Result;
import org.work.personelbilgi.dto.DosyaDTO;
import org.work.personelbilgi.service.concretes.DosyaServiceImp;
import org.work.personelbilgi.utils.ResponseUtil;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/dosya")
public class DosyaController {

    private final DosyaServiceImp dosyaService;

    @GetMapping("/getAllDosya")
    public ResponseEntity<DataResult<List<DosyaDTO>>> getAllDosya(){
        return ResponseUtil.buildDataResultResponse(dosyaService.getAllDosya());
    }

    @GetMapping("/getDosyaById/{id}")
    public ResponseEntity<DataResult<DosyaDTO>> getDosyaById(@PathVariable Long id){
        return ResponseUtil.buildDataResultResponse(dosyaService.getDosyaById(id));
    }

    @GetMapping("/getDosyaByPersonelId/{personelId}")
    public ResponseEntity<DataResult<List<DosyaDTO>>> getDosyaByPersonelId(@PathVariable Long personelId){
        return ResponseUtil.buildDataResultResponse(dosyaService.getDosyaByPersonelId(personelId));
    }

    @PostMapping("/addDosya")
    public ResponseEntity<Result> addDosya(@Valid @RequestBody DosyaDTO DosyaDTO){
        return ResponseUtil.buildResultResponse(dosyaService.addDosya(DosyaDTO));
    }

    @PutMapping("/updateDosya/{id}")
    public ResponseEntity<Result> updateDosya(@PathVariable Long id, @Valid @RequestBody DosyaDTO DosyaDTO){
        return ResponseUtil.buildResultResponse(dosyaService.updateDosya(id, DosyaDTO));
    }

    @DeleteMapping("/deleteDosya/{id}")
    public ResponseEntity<Result> deleteDosya(@PathVariable Long id){
        return ResponseUtil.buildResultResponse(dosyaService.deleteDosya(id));
    }
}
