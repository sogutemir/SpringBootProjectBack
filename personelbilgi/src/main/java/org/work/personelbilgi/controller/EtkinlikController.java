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
import org.work.personelbilgi.dto.EtkinlikDTO;
import org.work.personelbilgi.exception.ResourceNotFoundException;
import org.work.personelbilgi.exception.ValidationException;
import org.work.personelbilgi.service.concretes.EtkinlikServiceImp;
import org.work.personelbilgi.utils.ResponseUtil;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/etkinlik")
public class EtkinlikController {
    
    private final EtkinlikServiceImp etkinlikService;

    @GetMapping("/getAllEtkinlik")
    public ResponseEntity<DataResult<List<EtkinlikDTO>>> getAllEtkinlik(){
        try{
            return ResponseUtil.buildDataResultResponse(etkinlikService.getAllEtkinlik());
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDataResult<>(ex.getMessage()));
        }
    }

    @GetMapping("/getEtkinlikById/{id}")
    public ResponseEntity<DataResult<EtkinlikDTO>> getEtkinlikById(@PathVariable Long id){
        try {
            return ResponseUtil.buildDataResultResponse(etkinlikService.getEtkinlikById(id));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDataResult<>(ex.getMessage()));
        }
    }

    @GetMapping("/getEtkinlikByPersonelId/{personelId}")
    public ResponseEntity<DataResult<List<EtkinlikDTO>>> getEtkinlikByPersonelId(@PathVariable Long personelId){
        try{
            return ResponseUtil.buildDataResultResponse(etkinlikService.getEtkinlikByPersonelId(personelId));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDataResult<>(ex.getMessage()));
        }
    }

    @PostMapping("/addEtkinlik")
    public ResponseEntity<Result> addEtkinlik(@Valid @RequestBody EtkinlikDTO etkinlikDTO){
        try {
            return ResponseUtil.buildResultResponse(etkinlikService.addEtkinlik(etkinlikDTO));
        } catch (ValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(ex.getMessage()));
        }
    }

    @PutMapping("/updateEtkinlik/{id}")
    public ResponseEntity<Result> updateEtkinlik(@PathVariable Long id, @Valid @RequestBody EtkinlikDTO etkinlikDTO){
        try {
            return ResponseUtil.buildResultResponse(etkinlikService.updateEtkinlik(id, etkinlikDTO));
        } catch (ValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(ex.getMessage()));
        }
    }

    @DeleteMapping("/deleteEtkinlik/{id}")
    public ResponseEntity<Result> deleteEtkinlik(@PathVariable Long id){
        try {
            return ResponseUtil.buildResultResponse(etkinlikService.deleteEtkinlik(id));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResult(ex.getMessage()));
        }
    }
}
