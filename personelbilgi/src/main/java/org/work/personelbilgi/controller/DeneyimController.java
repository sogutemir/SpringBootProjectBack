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
import org.work.personelbilgi.dto.DeneyimDTO;
import org.work.personelbilgi.exception.ResourceNotFoundException;
import org.work.personelbilgi.exception.ValidationException;
import org.work.personelbilgi.service.concretes.DeneyimServiceImp;
import org.work.personelbilgi.utils.ResponseUtil;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/deneyim")
public class DeneyimController {
    
    private final DeneyimServiceImp deneyimService;

    @GetMapping("/getAllDeneyim")
    public ResponseEntity<DataResult<List<DeneyimDTO>>> getAllDeneyim(){
        try{
            return ResponseUtil.buildDataResultResponse(deneyimService.getAllDeneyim());
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDataResult<>(ex.getMessage()));
        }
    }

    @GetMapping("/getDeneyimById/{id}")
    public ResponseEntity<DataResult<DeneyimDTO>> getDeneyimById(@PathVariable Long id){
        try {
            return ResponseUtil.buildDataResultResponse(deneyimService.getDeneyimById(id));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDataResult<>(ex.getMessage()));
        }
    }

    @GetMapping("/getDeneyimByPersonelId/{personelId}")
    public ResponseEntity<DataResult<List<DeneyimDTO>>> getDeneyimByPersonelId(@PathVariable Long personelId){
        try{
            return ResponseUtil.buildDataResultResponse(deneyimService.getDeneyimByPersonelId(personelId));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDataResult<>(ex.getMessage()));
        }
    }

    @PostMapping("/addDeneyim")
    public ResponseEntity<Result> addDeneyim(@Valid @RequestBody DeneyimDTO DeneyimDTO){
        try {
            return ResponseUtil.buildResultResponse(deneyimService.addDeneyim(DeneyimDTO));
        } catch (ValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(ex.getMessage()));
        }
    }

    @PutMapping("/updateDeneyim/{id}")
    public ResponseEntity<Result> updateDeneyim(@PathVariable Long id, @Valid @RequestBody DeneyimDTO DeneyimDTO){
        try{
            return ResponseUtil.buildResultResponse(deneyimService.updateDeneyim(id, DeneyimDTO));
        } catch (ValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(ex.getMessage()));
        }
    }

    @DeleteMapping("/deleteDeneyim/{id}")
    public ResponseEntity<Result> deleteDeneyim(@PathVariable Long id){
        try{
            return ResponseUtil.buildResultResponse(deneyimService.deleteDeneyim(id));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResult(ex.getMessage()));
        }
    }
}
