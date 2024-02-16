package org.work.personelbilgi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.work.personelbilgi.core.result.DataResult;
import org.work.personelbilgi.core.result.ErrorDataResult;
import org.work.personelbilgi.core.result.ErrorResult;
import org.work.personelbilgi.core.result.Result;
import org.work.personelbilgi.dto.DosyaDTO;
import org.work.personelbilgi.exception.ResourceNotFoundException;
import org.work.personelbilgi.exception.ValidationException;
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
        try{
            return ResponseUtil.buildDataResultResponse(dosyaService.getAllDosya());
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDataResult<>(ex.getMessage()));
        }
    }

    @GetMapping("/getDosyaById/{id}")
    public ResponseEntity<DataResult<DosyaDTO>> getDosyaById(@PathVariable Long id){
        try {
            return ResponseUtil.buildDataResultResponse(dosyaService.getDosyaById(id));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDataResult<>(ex.getMessage()));
        }
    }

    @GetMapping("/getDosyaByPersonelId/{personelId}")
    public ResponseEntity<DataResult<List<DosyaDTO>>> getDosyaByPersonelId(@PathVariable Long personelId){
        try{
            return ResponseUtil.buildDataResultResponse(dosyaService.getDosyaByPersonelId(personelId));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDataResult<>(ex.getMessage()));
        }
    }

    @PostMapping("/addDosya")
    public ResponseEntity<Result> addDosya(@Valid @RequestBody DosyaDTO DosyaDTO){
        try {
            return ResponseUtil.buildResultResponse(dosyaService.addDosya(DosyaDTO));
        } catch (ValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(ex.getMessage()));
        }
    }

    @PutMapping("/updateDosya/{id}")
    public ResponseEntity<Result> updateDosya(@PathVariable Long id, @Valid @RequestBody DosyaDTO DosyaDTO){
        try{
            return ResponseUtil.buildResultResponse(dosyaService.updateDosya(id, DosyaDTO));
        } catch (ValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(ex.getMessage()));
        }
    }

    @DeleteMapping("/deleteDosya/{id}")
    public ResponseEntity<Result> deleteDosya(@PathVariable Long id){
        try{
            return ResponseUtil.buildResultResponse(dosyaService.deleteDosya(id));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResult(ex.getMessage()));
        }
    }
}
